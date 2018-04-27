package br.gov.inpi.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;
import org.prosur.catalog.ws.IpRecord;

import br.gov.inpi.intercarga.beans.AgendaCarga;
import br.gov.inpi.intercarga.beans.TbCargaRetroativaStatus;
import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;
import br.gov.inpi.mail.EstatisticaCargaManual;
import br.gov.inpi.patentes.beans.DiPedido;
import br.gov.inpi.patentes.beans.Patente;
import br.gov.inpi.patentes.beans.PtnPedido;
import br.gov.inpi.patentes.beans.PtnProcessoProsur;
import br.gov.inpi.prosur.carga.util.PatenteHelperManual;
import br.gov.inpi.services.CargaPatenteManualInterface;
import br.gov.inpi.services.EntityInterfaceIntercarga;
import br.gov.inpi.services.ProcessoPatenteManualInterface;
import br.gov.inpi.services.ServicosInterface;

@Stateless(name = "CargaPatenteManualFacade")
public class CargaPatenteManualFacade implements CargaPatenteManualInterface {

	public static final String RETROATIVA = "cargaRetroativa";

	public static final String SEMANAL = "cargaSemanal";
	
	public static final String PROCESSO = "cargaProcesso";
	
	private static final Logger LOGGER = Logger.getLogger(CargaPatenteManualFacade.class);
	
	private TbCargaRetroativaStatus retroativaStatus;
	
	@EJB
	private ProcessoPatenteManualInterface persistPatentes;
	
	@EJB
	private ServicosInterface servicosProsur;
	
	@EJB
	private EntityInterfaceIntercarga serviceIntercarga;

	public void preparaCargaPatente(String tipoCarga, String valorConsulta, AgendaCarga agendaCarga, List<TbHistoricoCargaProcesso> processos) {

		List<PtnPedido> pedidos = null;

		Date horaInicio = null;
		
		if (RETROATIVA.equals(tipoCarga)) {
			
			 pedidos = new ArrayList<PtnPedido>();
			 
			 LOGGER.info("Entrou na Carga Retroativa Patentes !!!");
			 LOGGER.info("---------------------------------------");
			 LOGGER.info("Vai buscar os pedidos de Patentes !!!!!"); 
			 
			 pedidos = this.buscarPedidosPatente(RETROATIVA, valorConsulta);
			 
			 LOGGER.info("Retornou os Pedidos de Patentes -- QTD a ser processada = "+ pedidos.size());
			 
			 horaInicio = new Date();
			 
			 this.executaCargaPatenteProcesso(pedidos, processos);
			 
		} else if (SEMANAL.equals(tipoCarga)) {

			pedidos = new ArrayList<PtnPedido>();

			LOGGER.info("Entrou na Carga Semanal Patentes !!!");
			LOGGER.info("---------------------------------------");
			LOGGER.info("Vai buscar os pedidos de Patentes !!!!!");
			
			pedidos = this.buscarPedidosPatente(SEMANAL, valorConsulta);
			
			LOGGER.info("Retornou os Pedidos de Patentes -- QTD a ser processada = " + pedidos.size());

			horaInicio = new Date();

			this.executaCargaPatente(pedidos);
			
		} else if (PROCESSO.equals(tipoCarga)) {

			pedidos = new ArrayList<PtnPedido>();

			LOGGER.info("Entrou na Carga Semanal Patentes !!!");
			LOGGER.info("---------------------------------------");
			LOGGER.info("Vai buscar os pedidos de Patentes !!!!!");
			
			
			
			// prpcessos dever�o ser recuperados de acordo com o id da agenda
			pedidos = this.buscarPedidosPatente(PROCESSO, agendaCarga.getIdAgendaCarga().toString());
			
			
			LOGGER.info("Retornou os Pedidos de Patente -- QTD a ser processada = " + pedidos.size());

			horaInicio = new Date();

			this.executaCargaPatenteProcesso(pedidos, processos);
		}		
		
		Date horaTermino = new Date();
		
		EstatisticaCargaManual.setQuantidadeParametro("quantidade_processados_patente", pedidos.size());
		EstatisticaCargaManual.setDataParametro("inicio_processo_patente", horaInicio);
		EstatisticaCargaManual.setDataParametro("termino_processo_patente", horaTermino);
	}
	
	public void executaCargaPatente(List<PtnPedido> ptnPedidos) {
		
		try {
			
			LOGGER.info("-------------------------------------");
			LOGGER.info("Vai Processar e Eviar para o Prosur!!");
			String idProsur = null;
			int p = 0;
			int s = 0;
			int w = 0;

			for (int i = 0; i < ptnPedidos.size(); i++) {

				PtnPedido pedido = ptnPedidos.get(i);

				try {

					idProsur = this.executarPedidoPatente(pedido);

				} catch (Exception e) {
					LOGGER.info("Patente - Erro!  CodPedido = " + pedido.getNumPedido() + " n�o pode ser Processado!!!!!");
					w++;
				}

				if (idProsur == null) {
					LOGGER.info("Pedido - Sigilo!  CodPedido = " + pedido.getNumPedido());
					s++;
				} else {
					LOGGER.info("Patente - Sucesso! IdProsur = " + idProsur + " CodPedido = " + pedido.getNumPedido());
					p++;
				}
			}

			LOGGER.info("************* Fim Carga  Patentes ************");

			LOGGER.info("Total de Patente Processado com Sucesso = " + p);
			LOGGER.info("Total    de    Patente    em     Sigilo = " + s);
			LOGGER.info("Total    de    Patente    com     Erros = " + w);

			EstatisticaCargaManual.setQuantidadeParametro("quantidade_patente_sucesso", p);
			EstatisticaCargaManual.setQuantidadeParametro("quantidade_patente_erros", w);
			EstatisticaCargaManual.setQuantidadeParametro("quantidade_patente_sigilo", s);
		} catch (Exception e) {
			LOGGER.error(" ERRO no Bloco Principal : " + e.getMessage());
		}
	}
	
	public void executaCargaPatenteProcesso(List<PtnPedido> ptnPedidos, List<TbHistoricoCargaProcesso> processos) {
		
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
					
					if (persistPatentes.verificaProcesoPatente(pedido.getNumPedido().trim())) {
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

			EstatisticaCargaManual.setQuantidadeParametro("quantidade_patente_sucesso", p);
			EstatisticaCargaManual.setQuantidadeParametro("quantidade_patente_erros", w);
			EstatisticaCargaManual.setQuantidadeParametro("quantidade_patente_sigilo", s);
		} catch (Exception e) {
			LOGGER.error(" ERRO no Bloco Principal : " + e.getMessage());
		}
	}
	
	public List<PtnPedido> buscarPedidosPatente(String tipoCarga, String valorConsulta) {
		
		List<PtnPedido> pedidos = new ArrayList<PtnPedido>();
		String processos = null;
		
		if (RETROATIVA.equals(tipoCarga)) {

			pedidos = this.persistPatentes.pesquisarPedidosDePatenteRetroativa(valorConsulta);

		} else if (SEMANAL.equals(tipoCarga)) {

			pedidos = persistPatentes.pesquisarPedidosDePatentePorSemana(valorConsulta);

		} else if (PROCESSO.equals(tipoCarga)) {
			
			processos = serviceIntercarga.recuperaProcessos(valorConsulta);

			pedidos = persistPatentes.pesquisarProcessoPatente(processos);

		}

		return pedidos;
	}
	
	public String executarPedidoPatente(PtnPedido ptnPedido) throws Exception {
		
		String idProsur = null;
		
		try {

			PatenteHelperManual helper = new PatenteHelperManual();
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
	
	public void salvarStatusRetroativa(String qtVolExecutado, int idAgendaCarga){
		
		this.retroativaStatus = new TbCargaRetroativaStatus();
		
		try {
			
			String qtVolExecutar = persistPatentes.retornarVolumeTotalPatenteRetroativo();
			
			this.retroativaStatus.setTpCarga("P");
			this.retroativaStatus.setQtVolExecutadoPatente(qtVolExecutado);
			this.retroativaStatus.setQtVolExecutarPatente(qtVolExecutar);
			this.retroativaStatus.setIdCargaRetroativa(idAgendaCarga);
			
			serviceIntercarga.save(this.retroativaStatus);
			
			LOGGER.info("********** salvou quantidade retroativa de patente a executar: " + qtVolExecutar + " **********");
		} catch (Exception e) {

			LOGGER.error(" >> ERRO ao gravar retroativa: " + e.getMessage());
		}
	}
}
