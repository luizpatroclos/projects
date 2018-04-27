package br.com.inpi.prosur.validacoes;

import static br.com.inpi.prosur.validacoes.Validador.*;

import java.util.HashMap;

import javax.ejb.EJB;

import br.com.inpi.prosur.util.FacesMessageUtil;
import br.gov.inpi.intercarga.beans.AgendaCarga;
import br.gov.inpi.services.ProcessoMarcasManualInterface;
import br.gov.inpi.services.ProcessoPatenteManualInterface;

public class ValidacaoAtualizarAgendamento {

	@EJB
	ProcessoPatenteManualInterface persistPatentes;
	
	@EJB
	ProcessoMarcasManualInterface persistMarcas;
	
	public static final String AUTOMATICO = "AUTOMATICO";

	public static final String MANUAL = "MANUAL";
	
	public static final String SEMANAL = "SEMANAL";

	public static final String RETROATIVA = "RETROATIVA";	
	
	public ValidacaoAtualizarAgendamento(){}
	
	public ValidacaoAtualizarAgendamento(ProcessoPatenteManualInterface persistPatentes, ProcessoMarcasManualInterface persistMarcas){
		
		this.persistPatentes = persistPatentes;
		this.persistMarcas = persistMarcas;
	}
	
	/**
	 * Validacao da edicao de agendamento no caso do tipo de carga MANUAL
	 * @return
	 */
	public boolean agendamentoManual(String statusAgenda, String dataNovoAgendamento, String hora, AgendaCarga agendaCarga){
		
		FacesMessageUtil facesMessageUtil = new FacesMessageUtil();
 		HashMap<Integer, String> msgErro = new HashMap<Integer, String>();
 		
		String tipoCarga = agendaCarga.getTipoCarga();
		String numeroRpi = agendaCarga.getNumRpi();
		String tipoBase = agendaCarga.getTipoBase();
		
		int cont = 0;
		
		try {
				
			if (vazio(statusAgenda)) {
				
				cont++;
				msgErro.put(cont, "status");
			}
			if (vazio(dataNovoAgendamento)) {

				cont++;
				msgErro.put(cont, "data");
			}
			if (vazio(hora) || !validaHora(hora)) {

				cont++;
				msgErro.put(cont, "hora");
			}			
			
			if ("SEMANAL".equals(tipoCarga)) {
				
				rpiETipoBase(cont, msgErro, numeroRpi, tipoBase);
			}
			
			if (msgErro.size() > 0) {
				
				for (int i = 0; i < msgErro.size() + 1; i++) {
					
					if (i > 0) {
						
						facesMessageUtil.mensagemErro(msgErro.get(i));
					}
				}
				return false;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return true;
	}
	
	private void rpiETipoBase(int cont, HashMap<Integer, String> msgErro, String numeroRpi, String tipoBase){
		
		if (vazio(numeroRpi) || !numero(numeroRpi)) {

			cont++;
			msgErro.put(cont, "num_rpi_vazio");
		} else if (!persistPatentes.verificarRpiExiste(numeroRpi)) {

			cont++;
			msgErro.put(cont, "numero_rpi_nao_encontrado");
		} else if (!persistMarcas.verificarRpiExiste(numeroRpi)) {
			
			cont++;
			msgErro.put(cont, "numero_rpi_nao_encontrado");
		}
		
		if (vazio(tipoBase)) {
			
			cont++;
			msgErro.put(cont, "selecione_carga_agendamento");
		}
	}
	
	/**
	 * Validacao da edicao de agendamento no caso do tipo de carga AUTOMATICO
	 * @return
	 */
	public boolean agendamentoAutomatico(String statusAgenda, String dataNovoAgendamento, String hora){
		
		FacesMessageUtil facesMessageUtil = new FacesMessageUtil();
 		HashMap<Integer, String> msgErro = new HashMap<Integer, String>();
		int cont = 0;
		
		try {
				
			if (vazio(statusAgenda)) {
				
				cont++;
				msgErro.put(cont, "status");
			}			
			if (vazio(dataNovoAgendamento)) {
				
				cont++;
				msgErro.put(cont, "data");
			}
			if (vazio(hora) || !validaHora(hora)) {
				
				cont++;
				msgErro.put(cont, "hora");
			}			
			
			if (msgErro.size() > 0) {
				
				for (int i = 0; i < msgErro.size() + 1; i++) {
					
					if (i > 0) {
						
						facesMessageUtil.mensagemErro(msgErro.get(i));
					}
				}
				
				return false;
			}			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return true;
	}
}
