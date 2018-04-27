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
import br.gov.inpi.patentes.beans.Desenho;
import br.gov.inpi.patentes.beans.DiPedido;
import br.gov.inpi.patentes.beans.PtnProcessoProsur;
import br.gov.inpi.prosur.carga.util.PatenteHelperAutomatico;
import br.gov.inpi.services.CargaDesenhoAutomaticoInterface;
import br.gov.inpi.services.EntityInterfaceIntercarga;
import br.gov.inpi.services.ProcessoPatenteAutomaticoInterface;
import br.gov.inpi.services.ServicosInterface;

@Stateless(name = "CargaDesenhoAutomaticoFacade")
public class CargaDesenhoAutomaticoFacade implements CargaDesenhoAutomaticoInterface {

	public static final String RETROATIVA = "cargaRetroativa";

	public static final String SEMANAL = "cargaSemanal";
	
	private static final Logger LOGGER = Logger.getLogger(CargaDesenhoAutomaticoFacade.class);
	
	private TbCargaRetroativaStatus retroativaStatus;
	
	@EJB
	private ProcessoPatenteAutomaticoInterface persistDesenho;

	@EJB
	private ServicosInterface servicosProsur;
	
	@EJB
	private EntityInterfaceIntercarga serviceIntercarga;
	
	public void preparaCargaDesenho(String tipoCarga, List<TbHistoricoCargaProcesso> processos){
		
		List<DiPedido> diPedidos = null;
		
		Date horaInicio = null;
		
		if (RETROATIVA.equals(tipoCarga)) {
			
			 diPedidos = new ArrayList<DiPedido>();
			 
			 LOGGER.info("Entrou na Carga Retroativa de DI !!!");
			 LOGGER.info("---------------------------------------"); 
			 LOGGER.info("Vai buscar os pedidos de DI !!!!!");
			 diPedidos = this.buscarPedidosDesenho(RETROATIVA);
			 LOGGER.info("Retornou os Pedidos de DI -- QTD a ser processada = "+ diPedidos.size());
			 
			 horaInicio = new Date();
			 
			 executaCargaDesenho(diPedidos, processos);
			 salvarStatusRetroativa(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_desenho_sucesso"),
					 				AgendamentosTimer.getIdAgenda());
		} else if (SEMANAL.equals(tipoCarga)) {

			diPedidos = new ArrayList<DiPedido>();

			LOGGER.info("Entrou na Carga Semanal de DI !!!");
			LOGGER.info("---------------------------------------");
			LOGGER.info("Vai buscar os pedidos de DI !!!!!");
			diPedidos = this.buscarPedidosDesenho(SEMANAL);
			LOGGER.info("Retornou os Pedidos de DI -- QTD a ser processada = " + diPedidos.size());

			horaInicio = new Date();

			this.executaCargaDesenho(diPedidos, processos);
		}		
		
		Date horaTermino = new Date();

		EstatisticaCargaAutomatica.setQuantidadeParametro("quantidade_processados_desenho", diPedidos.size());
		EstatisticaCargaAutomatica.setDataParametro("inicio_processo_desenho", horaInicio);
		EstatisticaCargaAutomatica.setDataParametro("termino_processo_desenho", horaTermino);
		EstatisticaCargaAutomatica.finalizaCarga();
	}
	
	public void executaCargaDesenho(List<DiPedido> diPedidos, List<TbHistoricoCargaProcesso> processos) {
		
		int p = 0;
		int s = 0;
		int w = 0;
		
		TbHistoricoCargaProcesso histProcesso = null;

		try {

			LOGGER.info("-------------------------------------");
			LOGGER.info("Vai Processar e Eviar para o Prosur!!");
			String idProsur = null;

			for (int i = 0; i < diPedidos.size(); i++) {

				DiPedido pedido = diPedidos.get(i);
				
				histProcesso = new TbHistoricoCargaProcesso();

				try {
					
					if (persistDesenho.verificaProceso(pedido.getNumPedido().trim())) {
						histProcesso.setCdTipo("A");
					}else{
						histProcesso.setCdTipo("C");}
					
						histProcesso.setNumProcesso(pedido.getNumPedido().trim());

					idProsur = this.executarPedidoDesenho(pedido);

				} catch (Exception e) {
					LOGGER.info("Desenho - Erro!  CodPedido = " + pedido.getNumPedido() + " n�o pode ser Processado!!!!!");
					w++;
					histProcesso.setCdSituacao((short) 0);
				}
				if (idProsur == null) {
					LOGGER.info("Desenho - Sigilo!  CodPedido = " + pedido.getNumPedido());
					s++;
					histProcesso.setCdSituacao((short) 0);
				} else {
					LOGGER.info("Desenho - Sucesso! IdProsur = " + idProsur + " CodPedido = " + pedido.getNumPedido());
					p++;
					histProcesso.setCdSituacao((short) 1);
				}
				
				processos.add(histProcesso);
			}

			LOGGER.info("******** Fim Carga  Desenho Industrial *******");

			LOGGER.info("Total de Desenho Processado com Sucesso = " + p);
			LOGGER.info("Total    de    Desenho    em     Sigilo = " + s);
			LOGGER.info("Total    de    Desenho    com     Erros = " + w);

			EstatisticaCargaAutomatica.setQuantidadeParametro("quantidade_desenho_sucesso", (p > 0 ? (p - w) : 0));
			EstatisticaCargaAutomatica.setQuantidadeParametro("quantidade_desenho_erros", w);
			EstatisticaCargaAutomatica.setQuantidadeParametro("quantidade_desenho_sigilo", s);
			
			Estatistica.addInt("processados_sucesso_desenhos", (p > 0 ? (p - w) : 0));
			Estatistica.addInt("processados_erros_desenhos", w);
			Estatistica.addInt("processados_sigilo_desenhos", s);
		} catch (Exception e) {
			LOGGER.error(" ERRO no Bloco Principal : " + e.getMessage());
		}
	}

	public List<DiPedido> buscarPedidosDesenho(String tipoEnvio) {

		List<DiPedido> diPedidos = new ArrayList<DiPedido>();

		LOGGER.info(" Inicio da carga de Desenho Industrial...........");

		if ("cargaSemanal".equals(tipoEnvio)) {

			diPedidos = persistDesenho.pesquisarPedidosDiPorSemana();

		} else if ("cargaRetroativa".equals(tipoEnvio)) {

			diPedidos = persistDesenho.pesquisarPedidosDiRetroativa();

		} 

		return diPedidos;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String executarPedidoDesenho(DiPedido diPedido) throws Exception {

		String idProsur = null;

		try {

			PatenteHelperAutomatico helper = new PatenteHelperAutomatico();
			helper.setPersistPatentes(this.persistDesenho);
			Desenho desenho = helper.retornarDesenho(diPedido);

			if (desenho != null && !"".equals(desenho)) {

				idProsur = this.enviarProsur(desenho);
			}

		} catch (Exception e) {
			LOGGER.info(" Nenhum pedido de Desenho Industrial encontrado " + e.getMessage());
			throw e;

		}

		return idProsur;

	}
	
	private String enviarProsur(Desenho desenho) throws Exception {

		String idProsur = null;

		try {

			IpRecord iprecordRetorno = servicosProsur.createProsur(desenho.getDesignPatent());

			if (iprecordRetorno != null) {

				PtnProcessoProsur prosur = new PtnProcessoProsur();
				prosur.setIdProsur(iprecordRetorno.getIpRecordId());
				prosur.setDataEnvio(new Date());
				prosur.setCodigoPedidoInpi(desenho.getCodigoIdentificador());
				idProsur = iprecordRetorno.getIpRecordId().toString();
				gravarLog(prosur);
			}

		} catch (Exception e) {
			LOGGER.error(" >> ERRO :: enviar pedido de Patente para o servi�o Prosur : " + e.getMessage());
			throw e;

		}
		return idProsur;

	}
	
	private void gravarLog(PtnProcessoProsur prosur) {

		try {
			this.persistDesenho.save(prosur);

		} catch (Exception e) {
			LOGGER.error(" Erro ao gravar o log " + e.getMessage());
		}

	}	
	
	public void salvarStatusRetroativa(String qtVolExecutado, int idAgendaCarga){
		
		this.retroativaStatus = new TbCargaRetroativaStatus();
		
		try {
		
			String qtVolExecutar = persistDesenho.retornarVolumeTotalDiRetroativo();
			
			this.retroativaStatus.setTpCarga("D");
			this.retroativaStatus.setQtVolExecutadoDi(qtVolExecutado);
			this.retroativaStatus.setQtVolExecutarDi(qtVolExecutar);
			this.retroativaStatus.setIdCargaRetroativa(idAgendaCarga);
			
			serviceIntercarga.save(this.retroativaStatus);
			
			LOGGER.info("********** salvou quantidade retroativa de DI a executar: " + qtVolExecutar + " **********");
		} catch (Exception e) {

			LOGGER.error(" >> ERRO ao gravar retroativa: " + e.getMessage());
		}
	}
}
