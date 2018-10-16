package br.gov.inpi.epec.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.gov.inpi.epec.beans.UsuarioEPEC;

public class EPECUtil {

	private static FacesContext facesContext;
	private static Flash flash;

	public static String coverteDateToText(Date date){

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		/*
		 * Dateformat.SHORT  03/04/10
		 * Dateformat.MEDIUM  03/04/2010
		 */

		DateFormat  df = DateFormat.getDateInstance(DateFormat.MEDIUM);

		return df.format(c.getTime());
	}

	public static void fecharSessao(){

		//Contexto da Aplicação  
		FacesContext context = FacesContext.getCurrentInstance();  
		//Verifica a sessao e a grava na variavel  
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
		//Fecha/Destroi sessao  
		session.invalidate();
	}

	public static void redirecionar(String parametro){
		try {			
			FacesContext.getCurrentInstance().getExternalContext().redirect(parametro);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static String getUrl(FacesContext fc, String path){
		
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest(); 
		
		return "http://"+request.getServerName()+":"+String.valueOf(request.getServerPort()) + path;
		
	}
	
	public static String getUsuario(){
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
     // Obtém a sessão atual
        HttpSession sessao = (HttpSession) ec.getSession(true);
     // Restaga o nome do usuário logado
        UsuarioEPEC user = (UsuarioEPEC) sessao.getAttribute(ConsultasEPEC.USUARIO);
     
        return user.getUsuario();
	}
	

	public static String dateTime() { 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}


	public static String getTime(Date dataTime){

		Date date = dataTime;    	
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

		return dateFormat.format(date);

	}

	public static int diferencaDias (Date inicial, Date cfim){

		long ini = inicial.getTime();
		long fim = cfim.getTime();
		long nroHoras = (fim-ini) / 1000 / 3600;
		int nroDias = (int) nroHoras / 24;

		return nroDias;
	}

	
	public static java.util.Date getDateFromString(String value, String formato)  {

		if (value == null || value.length() == 0) {
			return null;
		}

		if (formato == null || formato.length() == 0) {
			formato = "dd/MM/yyyy";
		}

		java.util.Date timeDate = null;
		String[] formats = formato.split(" ");
		
		String[] comps = value.split(" ");
		String datePortion = comps[0];

		if (comps.length > 1) {
			String timePortion = value.substring(comps[0].length() + 1);
			try {
				timeDate = DateFormat.getTimeInstance(DateFormat.SHORT).parse(timePortion);
			} catch (ParseException e) {

				throw new IllegalArgumentException("CANNOT_PARSE_ERROR_MESSAGE");
			}
		}

		DateFormat dateFormat = new SimpleDateFormat(formats[0]);

		try {
			java.util.Date date = dateFormat.parse(datePortion);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			if (timeDate != null) {
				Calendar tcal = Calendar.getInstance();
				tcal.setTime(timeDate);
				cal.add(Calendar.HOUR_OF_DAY, tcal.get(Calendar.HOUR_OF_DAY));
				cal.add(Calendar.MINUTE, tcal.get(Calendar.MINUTE));
				cal.add(Calendar.SECOND, tcal.get(Calendar.SECOND));
			}
			return cal.getTime();
		} catch (ParseException e) {

			throw new IllegalArgumentException("erroooo");
		}
	}

	
	public static String DateforText(Long time, String format){	 

		return new SimpleDateFormat(format).format(time);

	}

	public static  double formatValor(double valor1){

		java.text.NumberFormat nf = java.text.NumberFormat.getNumberInstance();    
		nf.setMinimumFractionDigits(3);    
		nf.setMaximumFractionDigits(3);    
		String str = nf.format(valor1);   

		str = str.replaceAll(",", ".");  
		valor1 = Double.parseDouble(str);

		return valor1;
	}	
	
	public static boolean validaCPF(String strCpf){
		
		int iDigito1Aux = 0, iDigito2Aux = 0, iDigitoCPF;
		int iDigito1 = 0, iDigito2 = 0, iRestoDivisao = 0;
		String strDigitoVerificador, strDigitoResultado;

		if (! strCpf.substring(0,1).equals("")){
			try{
				strCpf = strCpf.replace('.',' ');
				strCpf = strCpf.replace('-',' ');
				strCpf = strCpf.replaceAll(" ","");
				for (int iCont = 1; iCont < strCpf.length() -1; iCont++) {
					iDigitoCPF = Integer.valueOf(strCpf.substring(iCont -1, iCont)).intValue();
					iDigito1Aux = iDigito1Aux + (11 - iCont) * iDigitoCPF;
					iDigito2Aux = iDigito2Aux + (12 - iCont) * iDigitoCPF;
				}
				iRestoDivisao = (iDigito1Aux % 11);
				if (iRestoDivisao < 2) {
					iDigito1 = 0;
				} else {
					iDigito1 = 11 - iRestoDivisao;
				}
				iDigito2Aux += 2 * iDigito1;
				iRestoDivisao = (iDigito2Aux % 11);
				if (iRestoDivisao < 2) {
					iDigito2 = 0;
				} else {
					iDigito2 = 11 - iRestoDivisao;
				}
				strDigitoVerificador = strCpf.substring(strCpf.length()-2, strCpf.length());
				strDigitoResultado = String.valueOf(iDigito1) + String.valueOf(iDigito2);
				return strDigitoVerificador.equals(strDigitoResultado);
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @Autor Wenderson Souza
	 * @Data: 19/03/2013
	 * @param strCNPJ - numero do CNPJ
	 * @return True se o CNPJ é valido e False se CNPJ é invalido 
	 */
	public static boolean validaCNPJ(String strCNPJ) {
	    int iSoma = 0, iDigito;
	    char[] chCaracteresCNPJ;
	    String strCNPJ_Calculado;
	 
	    if (! strCNPJ.substring(0,1).equals("")){
	        try{
	            strCNPJ=strCNPJ.replace('.',' ');
	            strCNPJ=strCNPJ.replace('/',' ');
	            strCNPJ=strCNPJ.replace('-',' ');
	            strCNPJ=strCNPJ.replaceAll(" ","");
	            strCNPJ_Calculado = strCNPJ.substring(0,12);
	            if ( strCNPJ.length() != 14 ) return false;
	            chCaracteresCNPJ = strCNPJ.toCharArray();
	            for(int i = 0; i < 4; i++) {
	                if ((chCaracteresCNPJ[i]-48 >= 0) && (chCaracteresCNPJ[i]-48 <= 9)) {
	                    iSoma += (chCaracteresCNPJ[i] - 48 ) * (6 - (i + 1)) ;
	                }
	            }
	            for( int i = 0; i < 8; i++ ) {
	                if ((chCaracteresCNPJ[i+4]-48 >= 0) && (chCaracteresCNPJ[i+4]-48 <= 9)) {
	                    iSoma += (chCaracteresCNPJ[i+4] - 48 ) * (10 - (i + 1)) ;
	                }
	            }
	            iDigito = 11 - (iSoma % 11);
	            strCNPJ_Calculado += ((iDigito == 10) || (iDigito == 11)) ? "0" : Integer.toString(iDigito);
	               /* Segunda parte */
	            iSoma = 0;
	            for (int i = 0; i < 5; i++) {
	                if ((chCaracteresCNPJ[i]-48 >= 0) && (chCaracteresCNPJ[i]-48 <= 9)) {
	                    iSoma += (chCaracteresCNPJ[i] - 48) * (7 - (i + 1)) ;
	                }
	            }
	            for (int i = 0; i < 8; i++) {
	                if ((chCaracteresCNPJ[i+5]-48 >= 0) && (chCaracteresCNPJ[i+5]-48 <= 9)) {
	                    iSoma += (chCaracteresCNPJ[i+5] - 48) * (10 - (i + 1)) ;
	                }
	            }
	            iDigito = 11 - (iSoma % 11);
	            strCNPJ_Calculado += ((iDigito == 10) || (iDigito == 11)) ? "0" : Integer.toString(iDigito);
	            return strCNPJ.equals(strCNPJ_Calculado);
	        } catch (Exception e) {
	            return false;
	        }
	    } else return false;
	}
	
	public static java.util.Calendar converterDateToCalendar(Date date){ 
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		
		cal.setTime(date);
		
		return cal;
	}
	
	public static long calendarDiferencaDias(java.util.Calendar calDataInicial, java.util.Calendar calDataFinal){
			
        // Find date difference in milliseconds
        long diffInMSec = calDataFinal.getTimeInMillis() - calDataInicial.getTimeInMillis();;

        // Find date difference in days 
        // (24 hours 60 minutes 60 seconds 1000 millisecond)
        long diffOfDays = getDia(diffInMSec);

        return diffOfDays;
	
	}
	
	public static long getDia(long diffInMSec){
	
		return diffInMSec / (24 * 60 * 60 * 1000);
	
	}
	
	public static Calendar formatarHoraParaJanela(String parametro){
		
		String array[] = new String[3];  
		  
		array = parametro.split(":"); 
		String hora = array[0];
		String min 	= array[1];
		String seg 	= array[2];
		
	    Calendar cal = GregorianCalendar.getInstance();		    
	    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
	    cal.set(Calendar.MINUTE, Integer.parseInt(min));
	    cal.set(Calendar.SECOND, Integer.parseInt(seg));
		
		return cal;		
	}
	
	private static boolean validaHoraSemSegundos(int hora, int minuto){
		
		if ((hora < 0 || hora >= 24 ) || minuto < 0 || minuto >= 60){
			
			return false;
		}else{
			
			return true;
		}
		
	}
	
	private static boolean validaHoraComSegundos(int hora, int minuto, int segundos){
		
		if ((hora < 0 || hora >= 24 ) || (minuto < 0 || minuto >= 60) || (segundos < 0 || segundos >= 60)){
			
			return false;
		}else{
			
			return true;
		}
		
	}
	
	public static String validarHoraSemSegundos(String horaInicio, String horaFim){
		
		String mensagemErro = null;
		
		int hrIni  = Integer.parseInt(horaInicio.split(":")[0]);
		int minIni = Integer.parseInt(horaInicio.split(":")[1]);
		
		int hrFinal  = Integer.parseInt(horaFim.split(":")[0]);
		int minFim = Integer.parseInt(horaFim.split(":")[1]);
		
		
		if (!validaHoraSemSegundos(hrIni, minIni)){
			
			mensagemErro =  "Hora início inválida.";
			return mensagemErro;
			
		}else if (!validaHoraSemSegundos(hrFinal, minFim)){
			
			mensagemErro = "Hora fim inválida.";
			return mensagemErro;
				
		}else if( (hrIni > hrFinal)  || (hrIni == hrFinal && minIni > minFim) ){
			
			mensagemErro =  "Hora início maior que a hora fim.";
			return mensagemErro;
		}
		
		return mensagemErro;
	}
	
	public static String validarHoraComSegundos(String horaInicio, String horaFim){
		
		String mensagemErro = null;
		
		int hrIni  = Integer.parseInt(horaInicio.split(":")[0]);
		int minIni = Integer.parseInt(horaInicio.split(":")[1]);
		int segIni = Integer.parseInt(horaInicio.split(":")[2]);
		
		int hrFinal  = Integer.parseInt(horaFim.split(":")[0]);
		int minFim = Integer.parseInt(horaFim.split(":")[1]);
		int segFim = Integer.parseInt(horaFim.split(":")[2]);
		
		
		if (!validaHoraComSegundos(hrIni, minIni, segIni)){
			
			mensagemErro =  "Hora início inválida.";
			return mensagemErro;
			
		}else if (!validaHoraComSegundos(hrFinal, minFim, segFim)){
			
			mensagemErro = "Hora final inválida.";
			return mensagemErro;
				
		}else if( (hrIni > hrFinal)  || (hrIni == hrFinal && minIni > minFim) ){
			
			mensagemErro =  "Hora início maior que a hora final.";
			return mensagemErro;
		}
		
		return mensagemErro;
	}

}