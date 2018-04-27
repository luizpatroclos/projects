package br.gov.inpi.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;
import org.prosur.catalog.ws.IpRecord;

import br.gov.inpi.cliente.timer.AgendamentosTimer;
import br.gov.inpi.intercarga.beans.TbCargaRetroativaStatus;
import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;
import br.gov.inpi.mail.Estatistica;
import br.gov.inpi.mail.EstatisticaCargaAutomatica;
import br.gov.inpi.marcas.beans.Marca;
import br.gov.inpi.marcas.beans.MrcProsur;
import br.gov.inpi.prosur.carga.util.MarcasHelperAutoManual;
import br.gov.inpi.services.CargaMarcasAutomaticoInterface;
import br.gov.inpi.services.EntityInterfaceIntercarga;
import br.gov.inpi.services.ProcessoMarcasAutomaticoInterface;
import br.gov.inpi.services.ServicosInterface;

@Stateless(name = "CargaMarcasAutomaticoFacade")
public class CargaMarcasAutomaticoFacade implements CargaMarcasAutomaticoInterface {
	
	public static final String RETROATIVA = "cargaRetroativa";

	public static final String SEMANAL = "cargaSemanal";
	
	private static final Logger LOGGER = Logger.getLogger(CargaMarcasAutomaticoFacade.class);
	
	private TbCargaRetroativaStatus retroativaStatus;
	
	@EJB
	private ProcessoMarcasAutomaticoInterface persistMarcas;

	@EJB
	private ServicosInterface servicosProsur;
	
	@EJB
	private EntityInterfaceIntercarga serviceIntercarga;
	
	public void preparaCargaMarcas(String tipoCarga, List<TbHistoricoCargaProcesso> processos) {
		
		List<Object[]> objectProcessosMarcas = null;
		
		Date horaInicio = null;
		
		if (RETROATIVA.equals(tipoCarga)) {
			
			 objectProcessosMarcas = new ArrayList<>();
			 
			 LOGGER.info("Entrou na Carga Retroativa Marcas !!!");
			 LOGGER.info("---------------------------------------");
			 LOGGER.info("Vai buscar os pedidos de Marcas !!!!!");
			 
			 objectProcessosMarcas = this.buscarPedidosMarcas(RETROATIVA);
			 
			 LOGGER.info("Retornou os Pedidos de Marcas -- QTD a ser processada = "+ objectProcessosMarcas.size());
			 
			 horaInicio = new Date();
			 
			 executaCargaMarcas(objectProcessosMarcas, processos);
			 salvarStatusRetroativa(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_marcas_sucesso"),
					 				AgendamentosTimer.getIdAgenda());
		} else if (SEMANAL.equals(tipoCarga)) {

			objectProcessosMarcas = new ArrayList<>();

			LOGGER.info("Entrou na Carga Semanal Marcas !!!");
			LOGGER.info("---------------------------------------");
			LOGGER.info("Vai buscar os pedidos de Marcas !!!!!");

			objectProcessosMarcas = this.buscarPedidosMarcas(SEMANAL);

			LOGGER.info("Retornou os Pedidos de Marcas -- QTD a ser processada = " + objectProcessosMarcas.size());

			horaInicio = new Date();
			
			this.executaCargaMarcas(objectProcessosMarcas, processos);
		}
		
		Date horaTermino = new Date();
		 
		EstatisticaCargaAutomatica.setQuantidadeParametro("quantidade_processados_marcas", objectProcessosMarcas.size());
		EstatisticaCargaAutomatica.setDataParametro("inicio_processo_marcas", horaInicio);
		EstatisticaCargaAutomatica.setDataParametro("termino_processo_marcas", horaTermino);
		EstatisticaCargaAutomatica.finalizaCarga(); 
	}

	public void executaCargaMarcas(List<Object[]> objectProcessosMarcas, List<TbHistoricoCargaProcesso> processos) {

		LOGGER.info("-------------------------------------");
		LOGGER.info("Vai Processar e Enviar para o Prosur!!");
		String idProsur = null;

		int p = 0;
		int s = 0;
		int w = 0;
		
		TbHistoricoCargaProcesso histProcesso = null;

		try {

			for (int i = 0; i < objectProcessosMarcas.size(); i++) {

				Object[] objects = objectProcessosMarcas.get(i);
				
				histProcesso = new TbHistoricoCargaProcesso();

				try {
					
					if (persistMarcas.verificaProceso(objects[1].toString())) {
						histProcesso.setCdTipo("A");
					}else{
						histProcesso.setCdTipo("C");}
					
						histProcesso.setNumProcesso(objects[1].toString());

					idProsur = this.executarPedidoMarca(objects);

				} catch (Exception e) {
					LOGGER.info("Marcas - Erro!  CodPedido = " + objects[1] + " n�o pode ser Processado!!!!!");
					w++;
					histProcesso.setCdSituacao((short) 0);
				}

				if (idProsur == null) {
					LOGGER.info("Marcas - idProsur = NULL !  CodPedido = " + objects[1]);
					s++;
					histProcesso.setCdSituacao((short) 0);
				} else {
					LOGGER.info("Marcas - Sucesso! IdProsur = " + idProsur + " CodPedido = " + objects[1]);
					p++;
					histProcesso.setCdSituacao((short) 1);
				}
				
				processos.add(histProcesso);
			}

			LOGGER.info("************* Fim Carga  Marcas *************");

			LOGGER.info("Total de Marcas  Processado com Sucesso = " + p);
			LOGGER.info("Total    de    Marcas    com      Erros = " + w);
			LOGGER.info("Total   de   Marcas  com  Erros  Prosur = " + s);

			EstatisticaCargaAutomatica.setQuantidadeParametro("quantidade_marcas_sucesso", (p > 0 ? (p - w) : 0));
			EstatisticaCargaAutomatica.setQuantidadeParametro("quantidade_marcas_erros", w);
			
			
			Estatistica.addInt("processados_sucesso_marcas", (p > 0 ? (p - w) : 0));
			Estatistica.addInt("processados_erros_marcas", w);
		} catch (Exception e) {
			LOGGER.error(" ERRO no Bloco Principal : " + e.getMessage());
		}
	}	
	
	public List<Object[]> buscarPedidosMarcas(String tipoCarga) {

		List<Object[]> objectProcessosMarcas = new ArrayList<>();

		if (RETROATIVA.equals(tipoCarga)) {

			objectProcessosMarcas = persistMarcas.listarPedidosMarcasRetroativo();
		} else if (SEMANAL.equals(tipoCarga)) {

			objectProcessosMarcas = persistMarcas.listarPedidosMarcasSemanal();
			
			EstatisticaCargaAutomatica.setRpi("numero_rpi", persistMarcas.recuperarNumeroRPICorrente());
		}
		return objectProcessosMarcas;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String executarPedidoMarca(Object[] marca) throws Exception {

		String idProsur = null;

		try {

			Marca pedidoMarca = new Marca();
			MarcasHelperAutoManual helper = new MarcasHelperAutoManual();
			pedidoMarca = helper.retornarPedidosMarcas(marca, persistMarcas);

			if (pedidoMarca != null & !"".equals(pedidoMarca)) {

				idProsur = this.enviarProsur(pedidoMarca);
			}

		} catch (Exception e) {
			LOGGER.info(" Erro ao executar o pedido de Marcas " + e.getMessage());
			throw e;

		}

		return idProsur;

	}
	
	private String enviarProsur(Marca pedidoMarca) throws Exception {

		String idProsur = null;

		try {

			IpRecord iprecordRetorno = servicosProsur.createProsur(pedidoMarca.getDistinctiveSign());

			if (iprecordRetorno != null) {

				MrcProsur prosur = new MrcProsur();
				prosur.setDataEnvio(new Date());
				prosur.setIdProsur(iprecordRetorno.getIpRecordId());
				prosur.setCodigoPedidoMarcaInpi(pedidoMarca.getCodigoProcesso());
				idProsur = iprecordRetorno.getIpRecordId().toString();
				gravarLog(prosur);
			}

		} catch (Exception e) {
			LOGGER.error(" >> ERRO :: enviar pedido de Patente para o servi�o Prosur : " + e.getMessage());
			throw e;
		}

		return idProsur;
	}
	
	private void gravarLog(MrcProsur prosur) {

		try {
			persistMarcas.save(prosur);

		} catch (Exception e) {
			LOGGER.error(" Erro ao gravar o log " + e.getMessage());
		}

	}

	public void salvarStatusRetroativa(String qtVolExecutado, int idAgendaCarga){
		
		this.retroativaStatus = new TbCargaRetroativaStatus();
			
		try {
			
			String qtVolExecutar = persistMarcas.retornarVolumeTotalMarcasRetroativo();

			this.retroativaStatus.setTpCarga("M");
			this.retroativaStatus.setQtVolExecutadoMrc(qtVolExecutado);
			this.retroativaStatus.setQtVolExecutarMrc(qtVolExecutar);
			this.retroativaStatus.setIdCargaRetroativa(idAgendaCarga);
			
			serviceIntercarga.save(this.retroativaStatus);
			
			LOGGER.info("********** salvou quantidade retroativa de marcas a executar: " + qtVolExecutar + " **********");
		} catch (Exception e) {

			LOGGER.error(" >> ERRO ao gravar retroativa: " + e.getMessage());
		}
	}
}
