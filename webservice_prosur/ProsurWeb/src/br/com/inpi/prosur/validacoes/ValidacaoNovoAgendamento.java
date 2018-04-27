package br.com.inpi.prosur.validacoes;

import static br.com.inpi.prosur.validacoes.Validador.*;

import java.util.HashMap;

import javax.ejb.EJB;

import br.com.inpi.prosur.util.FacesMessageUtil;
import br.gov.inpi.intercarga.beans.TbCargaRetroativa;
import br.gov.inpi.intercarga.beans.TbCargaSemanal;
import br.gov.inpi.services.ProcessoMarcasManualInterface;
import br.gov.inpi.services.ProcessoPatenteManualInterface;

public class ValidacaoNovoAgendamento{
	
	@EJB
	ProcessoPatenteManualInterface persistPatentes;
	
	@EJB
	ProcessoMarcasManualInterface persistMarcas;
	
	public static final String AUTOMATICO = "AUTOMATICO";

	public static final String MANUAL = "MANUAL";
	
	public static final String SEMANAL = "SEMANAL";

	public static final String RETROATIVA = "RETROATIVA";
	
	public static final String PROCESSO = "PROCESSO";
	
	public boolean msgErroProcesso = false;
	
	public ValidacaoNovoAgendamento(){}
	
	public ValidacaoNovoAgendamento(ProcessoPatenteManualInterface persistPatentes, ProcessoMarcasManualInterface persistMarcas){
		
		this.persistPatentes = persistPatentes;
		this.persistMarcas = persistMarcas;
	}
	
	/**
	 * Validacao dos agendamentos manuais
	 * 
	 * @param hora
	 * @param quantidadeMarca
	 * @param quantidadeDesenho
	 * @param quantidadePatente
	 */
	 public boolean agendamentoManual(String hora, String tipoCarga, String tipoAgendamento,
			 						  TbCargaRetroativa cargaRetroativa, TbCargaSemanal cargaSemanal,
			 						  String dataNovoAgendamento, String[] checkTipoBase) {

		FacesMessageUtil facesMessageUtil = new FacesMessageUtil();
 		HashMap<Integer, String> msgErro = new HashMap<Integer, String>();
		int cont = 0;
		
		try {

			if (vazio(dataNovoAgendamento)) {

				cont++;
				msgErro.put(cont, "data");
			}
			if (vazio(hora) || !validaHora(hora)) {

				cont++;
				msgErro.put(cont, "hora");
			}
			if (vazio(tipoCarga)) {
				
				cont++;
				msgErro.put(cont, "tipo_carga");
			}			
			
			retroativaManual(cont, msgErro, tipoCarga, cargaRetroativa);
			semanalManual(cont, msgErro, tipoCarga, cargaSemanal, checkTipoBase);

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
	 
	 /**
	 * valida valores retroativos isoladamente
	 * 
	 * @param mensagem
	 * @param tipoCarga
	 */
	private void retroativaManual(int cont, HashMap<Integer, String> msgErro, String tipoCarga,
										  TbCargaRetroativa cargaRetroativa) {

		if (tipoCarga.equals(RETROATIVA)) {

			if (inteiro(cargaRetroativa.getQtMarcas()) && !vazio(cargaRetroativa.getQtMarcas())) {

				int qtd = Integer.parseInt(cargaRetroativa.getQtMarcas());

				if (qtd > 1000000) {

					cont++;
					msgErro.put(cont, "limite_marca");
				}
			} else if (!inteiro(cargaRetroativa.getQtMarcas())) {

				cont++;
				msgErro.put(cont, "formato_marca");
			}
			if (inteiro(cargaRetroativa.getQtDi()) && !vazio(cargaRetroativa.getQtDi())) {

				int qtd = Integer.parseInt(cargaRetroativa.getQtDi());

				if (qtd > 1000000) {

					cont++;
					msgErro.put(cont, "limite_desenho");
				}
			} else if (!inteiro(cargaRetroativa.getQtDi())) {

				cont++;
				msgErro.put(cont, "formato_desenho");
			}
			if (inteiro(cargaRetroativa.getQtPatentes()) && !vazio(cargaRetroativa.getQtPatentes())) {

				int qtd = Integer.parseInt(cargaRetroativa.getQtPatentes());

				if (qtd > 1000000) {

					cont++;
					msgErro.put(cont, "limite_patente");
				}
			} else if (!inteiro(cargaRetroativa.getQtPatentes())) {
				
				cont++;
				msgErro.put(cont, "formato_patente");
			}
			
			if (vazio(cargaRetroativa.getQtMarcas()) && vazio(cargaRetroativa.getQtDi()) 
				&& vazio(cargaRetroativa.getQtPatentes())) {
				
				cont++;
				msgErro.put(cont, "quantidade_vazia");
			}
		}
	}
	
	/**
	 * valida valores semanais isoladamente
	 * 
	 * @param mensagem
	 * @param tipoCarga
	 */
	public void semanalManual(int cont, HashMap<Integer, String> msgErro, String tipoCarga,
							  TbCargaSemanal cargaSemanal, String[] checkTipoBase) {		
		
		String numeroRpi = cargaSemanal.getNoRpi();
		
		if (tipoCarga.equals(SEMANAL)) {

			if (vazio(cargaSemanal.getNoRpi()) || !numero(cargaSemanal.getNoRpi())) {

				cont++;
				msgErro.put(cont, "num_rpi_vazio");
			} else if (checkTipoBase.length == 0) {
				
				cont++;
				msgErro.put(cont, "selecione_carga_agendamento");
			} else {
				
				for (String tipoBase : checkTipoBase) {
						
					if ("M".equals(tipoBase)) {
						
						if (!persistMarcas.verificarRpiExiste(numeroRpi)) {
							
							cont++;
							msgErro.put(cont, "numero_rpi_nao_encontrado");
						}
					} else if ("D".equals(tipoBase) || "P".equals(tipoBase)) {
						
						if (!persistPatentes.verificarRpiExiste(numeroRpi)) {
							
							cont++;
							msgErro.put(cont, "numero_rpi_nao_encontrado");
						}
					}
				}
			}
		}
	}
	
	public boolean agendamentoAutomatico(String hora, String tipoCarga, 
										 String tipoAgendamento, String dataNovoAgendamento){
		
		FacesMessageUtil facesMessageUtil = new FacesMessageUtil();
 		HashMap<Integer, String> msgErro = new HashMap<Integer, String>();
		int cont = 0;
		
		try {
			
			if (vazio(dataNovoAgendamento)) {

				cont++;
				msgErro.put(cont, "data");
			}
			if (vazio(hora) || !validaHora(hora)) {

				cont++;
				msgErro.put(cont, "hora");
			}
			if (vazio(tipoCarga)) {
				
				cont++;
				msgErro.put(cont, "tipo_carga");
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
	
	 /**
	 * valida valor processo marcas
	 * 
	 * @param num_processo
	 */
	public void processoMarcas(String num_processo) {
		
		FacesMessageUtil facesMessageUtil = new FacesMessageUtil();
		
		if (!validaMarcas(num_processo)){
			this.setMsgErroProcesso(true);
			facesMessageUtil.mensagemErro("processo_invalido"); 
			
		}else if(!persistMarcas.validaProceso(num_processo)){
			     this.setMsgErroProcesso(true);
			     facesMessageUtil.mensagemErro("processo_inexistente");}
		
	}
	
	/**
	 * valida valor processo Desenho
	 * 
	 * @param num_processo
	 */
	public void processoDesenho(String num_processo) {
		
		FacesMessageUtil facesMessageUtil = new FacesMessageUtil();
		
		 if (num_processo.startsWith("DI")){
			 
			 	if (!validaDesenhoPri(num_processo)){
			 		this.setMsgErroProcesso(true);
					facesMessageUtil.mensagemErro("processo_invalido");
				} else 
					buscaProc(num_processo,"D");
			 	
			}else{
				
			 if(!validaDesenhoSeg(num_processo)){
				 this.setMsgErroProcesso(true);
				 facesMessageUtil.mensagemErro("processo_invalido");
			 } else
				  buscaProc(num_processo,"D");} 
		
		}
	
	/**
	 * valida valor processo Patentes
	 * 
	 * @param num_processo
	 */
	public void processoPatente(String num_processo) {
		
		FacesMessageUtil facesMessageUtil = new FacesMessageUtil();
		
	 if ((num_processo.startsWith("MU")) || (num_processo.startsWith("PI"))) {
		 
		 	if (!validaPatentePri(num_processo)){
		 		this.setMsgErroProcesso(true);
				facesMessageUtil.mensagemErro("processo_invalido");
				} else
					buscaProc(num_processo,"P");
		}else{
			
		 if(!validaPatenteSeg(num_processo)){
			 this.setMsgErroProcesso(true);
			 facesMessageUtil.mensagemErro("processo_invalido");
			 } else
				 buscaProc(num_processo,"P");
		
		 } 
			
	}

	public static boolean validaMarcas(String processo){
    	boolean isUtilityModele = processo.matches("[0-9]{9}");
    	if(isUtilityModele){
    		return Boolean.TRUE;
    	}
    	return Boolean.FALSE;
    }
	
	 public static boolean validaPatentePri(String processo){
	    	boolean isUtilityModele = processo.matches("(PI|MU)[0-9]{7}");
	    	if(isUtilityModele){
	    		return Boolean.TRUE;
	    	}
	    	return Boolean.FALSE;
	    }
	 
	 public static boolean validaPatenteSeg(String processo){
	    	boolean isUtilityModele = processo.matches("(10|11|12|13|20|21|22)[0-9]{10}");
	    	if(isUtilityModele){
	    		return Boolean.TRUE;
	    	}
	    	return Boolean.FALSE;
	    }
	 
	 public static boolean validaDesenhoPri(String processo){
	    	boolean isUtilityModele = processo.matches("DI[0-9]{7}");
	    	if(isUtilityModele){
	    		return Boolean.TRUE;
	    	}
	    	return Boolean.FALSE;
	    }
	 
	 public static boolean validaDesenhoSeg(String processo){
	    	boolean isUtilityModele = processo.matches("(30|32)[0-9]{10}");
	    	if(isUtilityModele){
	    		return Boolean.TRUE;
	    	}
	    	return Boolean.FALSE;
	    }
	 
	 public void buscaProc(String processo, String b){
		 
		 FacesMessageUtil facesMessageUtil = new FacesMessageUtil();
		 
		 if ("P".equals(b)) {
			
			 if(!persistPatentes.validaProcesoPatente(processo))
			 		this.setMsgErroProcesso(true);
					facesMessageUtil.mensagemErro("processo_inexistente");
		} else{
			
			if(!persistPatentes.validaProcesoDesenho(processo)){
		 		this.setMsgErroProcesso(true);
				facesMessageUtil.mensagemErro("processo_inexistente");}}
	 }
	 

	public void setMsgErroProcesso(boolean msgErroProcesso) {
		this.msgErroProcesso = msgErroProcesso;
	}

	public boolean isMsgErroProcesso() {
		return msgErroProcesso;
	}

}
