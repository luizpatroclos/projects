package br.gov.inpi.cliente.timer;

import static br.gov.inpi.mail.EstatisticaCargaManual.inicializaCarga;
import static br.gov.inpi.prosur.carga.util.Validador.vazio;

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
import br.gov.inpi.mail.EstatisticaCargaAutomatica;
import br.gov.inpi.mail.EstatisticaCargaManual;
import br.gov.inpi.services.CargaDesenhoAutomaticoInterface;
import br.gov.inpi.services.CargaDesenhoManualInterface;
import br.gov.inpi.services.EntityInterfaceIntercarga;

@ApplicationScoped
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Singleton
@Lock(LockType.READ)
public class ScheduleExecucaoDesenho extends AgendamentosTimer {

	public static final String RETROATIVA = "cargaRetroativa";

	public static final String SEMANAL = "cargaSemanal";
	
	public static final String PROCESSO = "cargaProcesso";
	
	private static final Logger LOGGER = Logger.getLogger(ScheduleExecucaoDesenho.class);
	
	TbAgendaCarga tbAgendaCarga;
	AgendaCarga agendaCarga;
	
	@EJB
	private EntityInterfaceIntercarga service;
	
	@EJB
	private CargaDesenhoAutomaticoInterface cargaDesenhoAuto;
	
	@EJB
	private CargaDesenhoManualInterface cargaDesenhoManual;
	
	//SEMANAL
	@TransactionTimeout(value = 360, unit = TimeUnit.MINUTES)
	@Schedule(minute="*", second="45", hour="*")
	public void timerSemanalAutoDesenho() {
		
		try {
		
			if (agendamentoAutoSemanal) {
				
				contadorAutoSemanal++;
				LOGGER.info("*********** COME�OU PROCESSO DE AGENDAMENTO AUTOMATICO SEMANAL DESENHO: " + contadorAutoSemanal + " ***********");
				
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
				
				this.cargaDesenhoAuto.preparaCargaDesenho(SEMANAL, processos);
				
				service.salvarHistoricoCargaAutomatica(this.agendaCarga, processos);
				service.atualizarAgendamentoAutomatico(this.agendaCarga);
			}
		} catch (Exception e) {
			
			LOGGER.info("******* Erro: " + e.getMessage() + " *******");
		}		
	}
	
	@TransactionTimeout(value = 360, unit = TimeUnit.MINUTES)
	@Schedule(minute="*", second="45", hour="*")
	public void timerSemanalManualDesenho(){		
		
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
				
				if (this.agendaCarga.getTipoBase().contains("D")) {
					
					LOGGER.info("*********** COME�OU PROCESSO DE AGENDAMENTO MANUAL SEMANAL DESENHO: " + contadorManualSemanal + " ***********");
					
					String numRpi = this.agendaCarga.getNumRpi();
					
					EstatisticaCargaManual.inicializaCarga();
					
					this.cargaDesenhoManual.preparaCargaDesenho(SEMANAL, numRpi, null, processos);
					
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
	@Schedule(minute="*", second="45", hour="*")
	public void timerRetroativaAutoDesenho() {
		
		try {
			
			if (agendamentoAutoRetro) {
		
				contadorAutoRetro++;
				LOGGER.info("*********** COME�OU PROCESSO DE AGENDAMENTO AUTOMATICO RETROATIVO DESENHO: " + contadorAutoRetro + " ***********");
				
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
				
				this.cargaDesenhoAuto.preparaCargaDesenho(RETROATIVA, processos);
				
				service.salvarHistoricoCargaAutomatica(this.agendaCarga, processos);
				service.atualizarAgendamentoAutomatico(this.agendaCarga);
			}
		} catch (Exception e) {
			
			LOGGER.info("******* Erro: " + e.getMessage() + " *******");
		}
	}
	
	@TransactionTimeout(value = 360, unit = TimeUnit.MINUTES)
	@Schedule(minute="*", second="45", hour="*")
	public void timerRetroativaManualDesenho(){
		
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
				
				if (!vazio(agendaCarga.getQtdDesenho())) {
					
					LOGGER.info("*********** COME�OU PROCESSO DE AGENDAMENTO MANUAL RETROATIVO DESENHO: " + contadorManualRetro + " ***********");
					
					String qtDesenho = this.agendaCarga.getQtdDesenho();
					
					EstatisticaCargaManual.inicializaCarga();
					
					this.cargaDesenhoManual.preparaCargaDesenho(RETROATIVA, qtDesenho, agendaCarga, processos);
					
					this.cargaDesenhoManual.salvarStatusRetroativa(EstatisticaCargaManual.getQuantidadeParametro("quantidade_desenho_sucesso"), 
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
	
	
	@TransactionTimeout(value = 360, unit = TimeUnit.MINUTES)
	@Schedule(minute="*", second="45", hour="*")
	public void timerProcessoManualDesenho(){
		
		try {

			if (agendamentoManualProcessoDesenho) {
				
				agendamentoManualProcessoDesenho = false;

				this.agendaCarga = new AgendaCarga();
				this.agendaCarga = service.retornarAgendamentoExecucao(idAgenda);
				
				LOGGER.info(this.agendaCarga.toString());
				
				if (!vazio(agendaCarga.getQtdProcesso())) {
					
					LOGGER.info("*********** COME�OU A CARGA DE AGENDAMENTO MANUAL POR PROCESSO DE DESENHO");//: " + contadorManualRetro + " ***********");
					
					String qtProcesso = this.agendaCarga.getQtdProcesso();
					
					inicializaCarga();
					
					this.cargaDesenhoManual.preparaCargaDesenho(PROCESSO, qtProcesso, agendaCarga,processos);
				
					EstatisticaCargaManual.finalizaCargaProcesso();
					
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
