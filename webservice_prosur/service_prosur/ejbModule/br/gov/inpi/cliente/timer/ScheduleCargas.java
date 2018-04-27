package br.gov.inpi.cliente.timer;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;

import br.gov.inpi.intercarga.beans.AgendaCarga;
import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;
import br.gov.inpi.services.EntityInterfaceIntercarga;

@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Singleton
@Lock(LockType.READ)
@Startup
public class ScheduleCargas extends AgendamentosTimer {

	private static final Logger LOGGER = Logger.getLogger(ScheduleCargas.class);
	
	public static final String RETROATIVA = "RETROATIVA";
	
	public static final String PROCESSO = "PROCESSO";

	public static final String SEMANAL = "SEMANAL";
	
	public static final String AUTOMATICO = "AUTOMATICO";

	public static final String MANUAL = "MANUAL";
	
	public static final String MARCAS = "M";
	
	public static final String DESENHO = "D";
	
	public static final String PATENTE = "P";
	
	@EJB
	private EntityInterfaceIntercarga service;

	AgendaCarga proximoAgendamento;
	
	public AgendaCarga resgatarProximoAgendamento(){
		
		return this.proximoAgendamento = service.retornarProximoAgendamento();
	}
	
	@Schedule(minute="*", hour="*")
	public void prepararAgendamento() throws IOException{
		
		String tipoAgendamento;
		String tipoCarga;
		String tipoBase;
		
		try {

			this.resgatarProximoAgendamento();
			
			if (this.proximoAgendamento.getIdAgendaCarga() != null) {
				
				tipoAgendamento = this.proximoAgendamento.getTipoAgendamento();
				tipoCarga = this.proximoAgendamento.getTipoCarga();
				tipoBase = this.proximoAgendamento.getTipoBase();
				int id = this.proximoAgendamento.getIdAgendaCarga();
				
				processos = new ArrayList<TbHistoricoCargaProcesso>();
				
				if (tipoAgendamento.equals(AUTOMATICO) && tipoCarga.equals(SEMANAL)) { //AUTOMATICO

					agendamentoAutoSemanal = true;
					idAgenda = id;
				} else if (tipoAgendamento.equals(AUTOMATICO) && tipoCarga.equals(RETROATIVA)) {

					agendamentoAutoRetro = true;
					idAgenda = id;
				}
				
				if (tipoAgendamento.equals(MANUAL) && tipoCarga.equals(SEMANAL)) { //MANUAL
					
					agendamentoManualSemanal = true;
					idAgenda = id;
				} else if (tipoAgendamento.equals(MANUAL) && tipoCarga.equals(RETROATIVA)) {

					agendamentoManualRetro = true;
					idAgenda = id;
				} else if (tipoAgendamento.equals(MANUAL) && tipoCarga.equals(PROCESSO) && tipoBase.equals(DESENHO)) {
					
					agendamentoManualProcessoDesenho = true;
					idAgenda = id;
					
				} else if (tipoAgendamento.equals(MANUAL) && tipoCarga.equals(PROCESSO) && tipoBase.equals(PATENTE)) {
					
					agendamentoManualProcessoPatente = true;
					idAgenda = id;
					
				} else if (tipoAgendamento.equals(MANUAL) && tipoCarga.equals(PROCESSO) && tipoBase.equals(MARCAS)) {
					
					agendamentoManualProcessoMarcas = true;
					idAgenda = id;
				}
			}
		} catch (Exception e) {
			
			LOGGER.info("ERRO: " + e.getMessage());
		}
	}
}
