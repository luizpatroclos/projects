package br.gov.inpi.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;
import org.prosur.catalog.ws.IpRecord;

import br.gov.inpi.cliente.timer.AgendamentosTimer;
import br.gov.inpi.intercarga.beans.TbCargaRetroativaStatus;
import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;
import br.gov.inpi.mail.Estatistica;
import br.gov.inpi.mail.EstatisticaCargaAutomatica;
import br.gov.inpi.patentes.beans.Patente;
import br.gov.inpi.patentes.beans.PtnPedido;
import br.gov.inpi.patentes.beans.PtnProcessoProsur;
import br.gov.inpi.prosur.carga.util.PatenteHelperAutomatico;
import br.gov.inpi.services.CargaPatenteAutomaticoInterface;
import br.gov.inpi.services.EntityInterfaceIntercarga;
import br.gov.inpi.services.ProcessoPatenteAutomaticoInterface;
import br.gov.inpi.services.ServicosInterface;

@Stateless(name = "CargaPatenteAutomaticoFacade")
public class CargaPatenteAutomaticoFacade implements CargaPatenteAutomaticoInterface{

	public static final String RETROATIVA = "cargaRetroativa";

	public static final String SEMANAL = "cargaSemanal";
	
	private static final Logger LOGGER = Logger.getLogger(CargaPatenteAutomaticoFacade.class);
	
	private TbCargaRetroativaStatus retroativaStatus;
	
	@EJB
	private ProcessoPatenteAutomaticoInterface persistPatentes;

	@EJB
	private ServicosInterface servicosProsur;
	
	@EJB
	private EntityInterfaceIntercarga serviceIntercarga;
	
	public void preparaCargaPatente(String tipoCarga, List<TbHistoricoCargaProcesso> processos) {

		List<PtnPedido> pedidos = null;

		Date horaInicio = null;
		
		if (RETROATIVA.equals(tipoCarga)) {

			
			 pedidos = new ArrayList<PtnPedido>();
			 
			 LOGGER.info("Entrou na Carga Retroativa Patentes !!!");
			 LOGGER.info("---------------------------------------");
			 LOGGER.info("Vai buscar os pedidos de Patentes !!!!!"); 
			 pedidos = this.buscarPedidosPatente(RETROATIVA);
			 LOGGER.info("Retornou os Pedidos de Patentes -- QTD a ser processada = "+ pedidos.size());
			 
			 horaInicio = new Date();
			 
			 executaCargaPatente(pedidos, processos);
			 salvarStatusRetroativa(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_patente_sucesso"),
					 				AgendamentosTimer.getIdAgenda());
		} else if (SEMANAL.equals(tipoCarga)) {

			pedidos = new ArrayList<PtnPedido>();

			LOGGER.info("Entrou na Carga Semanal Patentes !!!");
			LOGGER.info("---------------------------------------");
			LOGGER.info("Vai buscar os pedidos de Patentes !!!!!");
			pedidos = this.buscarPedidosPatente(SEMANAL);
			LOGGER.info("Retornou os Pedidos de Patentes -- QTD a ser processada = " + pedidos.size());

			horaInicio = new Date();

			this.executaCargaPatente(pedidos, processos);
		}
		
		Date horaTermino = new Date();
		
		EstatisticaCargaAutomatica.setQuantidadeParametro("quantidade_processados_patente", pedidos.size());
		EstatisticaCargaAutomatica.setDataParametro("inicio_processo_patente", horaInicio);
		EstatisticaCargaAutomatica.setDataParametro("termino_processo_patente", horaTermino);
		EstatisticaCargaAutomatica.finalizaCarga();
		
		
		Estatistica.addInt("pedidos_processados_patentes", pedidos.size());
		Estatistica.addDate("inicio_processamento_patentes", horaInicio);
		Estatistica.addDate("termino_processamento_patentes", horaTermino);
//		Estatistica.finalizarCarga();
		
		
	}
	
	public void executaCargaPatente(List<PtnPedido> ptnPedidos, List<TbHistoricoCargaProcesso> processos) {
		
		try {
			
			LOGGER.info("-------------------------------------");
			LOGGER.info("Vai Processar e Eviar para o Prosur!!");
			String idProsur = null;
			int p = 0;
			int s = 0;
			int w = 0;
			
			TbHistoricoCargaProcesso histProcesso = null;

			for (int i = 0; i < ptnPedidos.size(); i++) {

				PtnPedido pedido = ptnPedidos.get(i);
				
				histProcesso = new TbHistoricoCargaProcesso();

				try {
					
					if (persistPatentes.verificaProceso(pedido.getNumPedido().trim())) {
						histProcesso.setCdTipo("A");
					}else{
						histProcesso.setCdTipo("C");}
					
						histProcesso.setNumProcesso(pedido.getNumPedido().trim());

					idProsur = this.executarPedidoPatente(pedido);

				} catch (Exception e) {
					LOGGER.info("Patente - Erro!  CodPedido = " + pedido.getNumPedido() + " n�o pode ser Processado!!!!!");
					w++;
					histProcesso.setCdSituacao((short) 0);
				}

				if (idProsur == null) {
					LOGGER.info("Pedido - Sigilo!  CodPedido = " + pedido.getNumPedido());
					s++;
					histProcesso.setCdSituacao((short) 0);
				} else {
					LOGGER.info("Patente - Sucesso! IdProsur = " + idProsur + " CodPedido = " + pedido.getNumPedido());
					p++;
					histProcesso.setCdSituacao((short) 1);
				}
				
				processos.add(histProcesso);
			}

			LOGGER.info("************* Fim Carga  Patentes ************");

			LOGGER.info("Total de Patente Processado com Sucesso = " + p);
			LOGGER.info("Total    de    Patente    em     Sigilo = " + s);
			LOGGER.info("Total    de    Patente    com     Erros = " + w);

			EstatisticaCargaAutomatica.setQuantidadeParametro("quantidade_patente_sucesso", (p > 0 ? (p - w) : 0));
			EstatisticaCargaAutomatica.setQuantidadeParametro("quantidade_patente_erros", w);
			EstatisticaCargaAutomatica.setQuantidadeParametro("quantidade_patente_sigilo", s);
			
			
			Estatistica.addInt("processados_sucesso_patentes", (p > 0 ? (p - w) : 0));
			Estatistica.addInt("processados_erros_patentes", w);
			Estatistica.addInt("processados_sigilo_patentes", s);
			
		} catch (Exception e) {
			LOGGER.error(" ERRO no Bloco Principal : " + e.getMessage());
		}
	}
	
	public List<PtnPedido> buscarPedidosPatente(String tipoCarga) {
		
		List<PtnPedido> pedidos = new ArrayList<PtnPedido>();		
		
		if ("cargaRetroativa".equals(tipoCarga)) {

			pedidos = this.persistPatentes.pesquisarPedidosDePatenteRetroativa();

		} else if ("cargaSemanal".equals(tipoCarga)) {

			pedidos = persistPatentes.pesquisarPedidosDePatentePorSemana();

		}

		return pedidos;
	}
	
	public String executarPedidoPatente(PtnPedido ptnPedido) throws Exception {
		
		String idProsur = null;
		
		try {

			PatenteHelperAutomatico helper = new PatenteHelperAutomatico();
			helper.setPersistPatentes(this.persistPatentes);
			Patente patente = helper.retornarPedidoPatente(ptnPedido);

			if (patente.getCodigoPedido() != null && !"".equals(patente.getCodigoPedido())) {

				idProsur = this.enviarProsur(patente);
			}

		} catch (Exception e) {
			LOGGER.info(" Erro ao executar o pedido de Patente " + e.getMessage());
			throw e;
		}

		return idProsur;
	}

	private String enviarProsur(Patente patente) throws Exception {

		String idProsur = null;

		try {

			IpRecord iprecordRetorno = servicosProsur.createProsur(patente.getInventionPatent());

			if (iprecordRetorno != null) {

				PtnProcessoProsur prosur = new PtnProcessoProsur();
				prosur.setIdProsur(iprecordRetorno.getIpRecordId());
				prosur.setDataEnvio(new Date());
				prosur.setCodigoPedidoInpi(patente.getCodigoPedido());
				idProsur = iprecordRetorno.getIpRecordId().toString();
				gravarLog(prosur);
			}

		} catch (Throwable e) {
			LOGGER.error(" >> ERRO :: enviar pedido de Patente para o servi�o Prosur : " + e.getMessage());
			throw e;
		}
		return idProsur;
	}
	
	private void gravarLog(PtnProcessoProsur prosur) {
		
		try {
			
			persistPatentes.save(prosur);
		} catch (Exception e) {
			
			LOGGER.error(" Erro ao gravar o log " + e.getMessage());
		}
	
	}

	public void salvarStatusRetroativa(String qtdVolExecutado, int idAgendaCarga){
		
		this.retroativaStatus = new TbCargaRetroativaStatus();
		
		try {
			
			String qtVolExecutar = persistPatentes.retornarVolumeTotalPatenteRetroativo();
			
			this.retroativaStatus.setTpCarga("P");
			this.retroativaStatus.setQtVolExecutadoPatente(qtdVolExecutado);
			this.retroativaStatus.setQtVolExecutarPatente(qtVolExecutar);
			this.retroativaStatus.setIdCargaRetroativa(idAgendaCarga);
			
			serviceIntercarga.save(this.retroativaStatus);
			
			LOGGER.info("********** salvou quantidade retroativa de patente a executar: " + qtVolExecutar + " **********");
		} catch (Exception e) {

			LOGGER.error(" >> ERRO ao gravar retroativa: " + e.getMessage());
		}
	}
}
