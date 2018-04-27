package br.gov.inpi.cliente.timer;

import static br.gov.inpi.mail.EstatisticaCargaManual.finalizaCargaProcesso;
import static br.gov.inpi.mail.EstatisticaCargaManual.inicializaCarga;
import static br.gov.inpi.prosur.carga.util.Validador.vazio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;

import org.jboss.ejb3.annotation.TransactionTimeout;
import org.jboss.logging.Logger;

import br.gov.inpi.intercarga.beans.AgendaCarga;
import br.gov.inpi.intercarga.beans.TbAgendaCarga;
import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;
import br.gov.inpi.mail.EstatisticaCargaAutomatica;
import br.gov.inpi.mail.EstatisticaCargaManual;
import br.gov.inpi.services.CargaPatenteAutomaticoInterface;
import br.gov.inpi.services.CargaPatenteManualInterface;
import br.gov.inpi.services.EntityInterfaceIntercarga;

@ApplicationScoped
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Singleton
@Lock(LockType.READ)
public class ScheduleExecucaoPatente extends AgendamentosTimer {

	public static final String RETROATIVA = "cargaRetroativa";

	public static final String SEMANAL = "cargaSemanal";
	
	public static final String PROCESSO = "cargaProcesso";
	
	private static final Logger LOGGER = Logger.getLogger(ScheduleExecucaoPatente.class);
	
	TbAgendaCarga tbAgendaCarga;
	AgendaCarga agendaCarga;
	
	@EJB
	private EntityInterfaceIntercarga service;
	
	@EJB
	private CargaPatenteAutomaticoInterface cargaPatenteAuto;
	
	@EJB
	private CargaPatenteManualInterface cargaPatenteManual;
	
	
	//SEMANAL
	@TransactionTimeout(value = 360, unit = TimeUnit.MINUTES)
	@Schedule(minute="*", second="50", hour="*")
	public void timerSemanalAutoPatente() {
		
		try {
		
			if (agendamentoAutoSemanal) {
					
				contadorAutoSemanal++;
				LOGGER.info("*********** COME�OU PROCESSO DE AGENDAMENTO AUTOMATICO SEMANAL PATENTE: " + contadorAutoSemanal + " ***********");
				
				if (contadorAutoSemanal == 3) {
					
					agendamentoAutoSemanal = false;
					contadorAutoSemanal = 0;
				}
				
				if (this.agendaCarga == null) {

					this.agendaCarga = new AgendaCarga();
					this.agendaCarga = service.retornarAgendamentoExecucao(idAgenda);
				}
				
				LOGGER.info(this.agendaCarga.toString());
				
				EstatisticaCargaAutomatica.inicializaCarga();
				
				this.cargaPatenteAuto.preparaCargaPatente(SEMANAL, processos);
				
				service.salvarHistoricoCargaAutomatica(this.agendaCarga, processos);
				
				service.atualizarAgendamentoAutomatico(this.agendaCarga);
			}
		} catch (Exception e) {

			LOGGER.info("******* Erro: " + e.getMessage() + " *******");
		}
	}
	
	@TransactionTimeout(value = 360, unit = TimeUnit.MINUTES)
	@Schedule(minute="*", second="50", hour="*")
	public void timerSemanalManualPatentes(){		
		
		try {
		
			if (agendamentoManualSemanal) {
				
				contadorManualSemanal++;
				
				if (contadorManualSemanal == 3) {
					
					agendamentoManualSemanal = false;
					contadorManualSemanal = 0;
				}

				this.agendaCarga = new AgendaCarga();
				this.agendaCarga = service.retornarAgendamentoExecucao(idAgenda);
				
				LOGGER.info(this.agendaCarga.toString());
				
				if (this.agendaCarga.getTipoBase().contains("P")) {
					
					LOGGER.info("*********** COME�OU PROCESSO DE AGENDAMENTO MANUAL SEMANAL PATENTE: " + contadorManualSemanal + " ***********");
					
					String numRpi = this.agendaCarga.getNumRpi();
					
					EstatisticaCargaManual.inicializaCarga();
					
					this.cargaPatenteManual.preparaCargaPatente(SEMANAL, numRpi, null, processos);
					
					EstatisticaCargaManual.finalizaCarga();
					
					service.salvarHistoricoCargaManual(this.agendaCarga, processos);
					service.atualizarAgendamentoManual(this.agendaCarga);
				} else {
					
					EstatisticaCargaManual.inicializaCarga();
					EstatisticaCargaManual.finalizaCarga();
					
					service.salvarHistoricoCargaManual(this.agendaCarga, null);
					service.atualizarAgendamentoManual(this.agendaCarga);
				}
			}
		} catch (Exception e) {

			LOGGER.info("******* Erro: " + e.getMessage() + " *******");
		}
	}

	//RETROATIVO
	@TransactionTimeout(value = 360, unit = TimeUnit.MINUTES)
	@Schedule(minute="*", second="50", hour="*")
	public void timerRetroativaAutoPatente() {
		
		try {
			
			if (agendamentoAutoRetro) {
		
				contadorAutoRetro++;
				LOGGER.info("*********** COME�OU PROCESSO DE AGENDAMENTO AUTOMATICO RETROATIVO PATENTE: " + contadorAutoRetro + " ***********");
				
				if (contadorAutoRetro == 3) {
					
					agendamentoAutoRetro = false;
					contadorAutoRetro = 0;
				}
				
				if (this.agendaCarga == null) {
					
					this.agendaCarga = new AgendaCarga();
					this.agendaCarga = service.retornarAgendamentoExecucao(idAgenda);
				}
				
				LOGGER.info(this.agendaCarga.toString());
				
				EstatisticaCargaAutomatica.inicializaCarga();
				
				this.cargaPatenteAuto.preparaCargaPatente(RETROATIVA, processos);
				
				service.salvarHistoricoCargaAutomatica(this.agendaCarga, processos);
				service.atualizarAgendamentoAutomatico(this.agendaCarga);
			}
		} catch (Exception e) {

			LOGGER.info("******* Erro: " + e.getMessage() + " *******");
		}
	}
	
	@TransactionTimeout(value = 360, unit = TimeUnit.MINUTES)
	@Schedule(minute="*", second="50", hour="*")
	public void timerProcessoPatentes(){
		
		try {
		
			if (agendamentoManualProcessoPatente) {
				
				agendamentoManualProcessoPatente = false;

				this.agendaCarga = new AgendaCarga();
				this.agendaCarga = service.retornarAgendamentoExecucao(idAgenda);
				
				LOGGER.info(this.agendaCarga.toString());
				
				if (!vazio(agendaCarga.getQtdProcesso())) {
					
					LOGGER.info("*********** COME�OU PROCESSO DE AGENDAMENTO MANUAL POR PROCESSO DE PATENTE: ");
					
					List<TbHistoricoCargaProcesso> processos = new ArrayList<TbHistoricoCargaProcesso>();
					String qtPatente = this.agendaCarga.getQtdProcesso();
					
					
					inicializaCarga();
					
					
					
					this.cargaPatenteManual.preparaCargaPatente(PROCESSO, qtPatente, agendaCarga, processos);
					
					
					finalizaCargaProcesso();
					
					service.salvarHistoricoCargaManual(this.agendaCarga, processos);
					service.atualizarAgendamentoManual(this.agendaCarga);
				} else {
					
					EstatisticaCargaManual.inicializaCarga();
					EstatisticaCargaManual.finalizaCarga();
					
					service.salvarHistoricoCargaManual(this.agendaCarga, null);
					service.atualizarAgendamentoManual(this.agendaCarga);
				}
			}
		} catch (Exception e) {

			LOGGER.info("******* Erro: " + e.getMessage() + " *******");
		}
	}
	
	@TransactionTimeout(value = 360, unit = TimeUnit.MINUTES)
	@Schedule(minute="*", second="50", hour="*")
	public void timerRetroativaManualPatentes(){
		
		try {
		
			if (agendamentoManualRetro) {
				
				contadorManualRetro++;
				
				if (contadorManualRetro == 3) {
					
					agendamentoManualRetro = false;
					contadorManualRetro = 0;
				}

				this.agendaCarga = new AgendaCarga();
				this.agendaCarga = service.retornarAgendamentoExecucao(idAgenda);
				
				LOGGER.info(this.agendaCarga.toString());
				
				if (!vazio(agendaCarga.getQtdPatente())) {
					
					LOGGER.info("*********** COME�OU PROCESSO DE AGENDAMENTO MANUAL RETROATIVO PATENTE: " + contadorManualRetro + " ***********");
					
					String qtPatente = this.agendaCarga.getQtdPatente();
					
					inicializaCarga();
					
					this.cargaPatenteManual.preparaCargaPatente(RETROATIVA, qtPatente, agendaCarga, processos);
					
					this.cargaPatenteManual.salvarStatusRetroativa(EstatisticaCargaManual.getQuantidadeParametro("quantidade_patente_sucesso"),
																   idAgenda);
					
					EstatisticaCargaManual.finalizaCarga();
					
					service.salvarHistoricoCargaManual(this.agendaCarga, processos);
					service.atualizarAgendamentoManual(this.agendaCarga);	
				} else {
					
					EstatisticaCargaManual.inicializaCarga();
					EstatisticaCargaManual.finalizaCarga();
					
					service.salvarHistoricoCargaManual(this.agendaCarga, null);
					service.atualizarAgendamentoManual(this.agendaCarga);
				}
			}
		} catch (Exception e) {

			LOGGER.info("******* Erro: " + e.getMessage() + " *******");
		}
	}
}
