package br.com.inpi.prosur.validacoes;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.inpi.prosur.exception.ErroProsur;
import br.com.inpi.prosur.util.ConsultasProsur;
import br.com.inpi.usuarioprosur.bean.UsuarioProsur;

public class ProsurUtil {	
	
	private ProsurUtil(){}
	
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

	public static String convertCalendarToString(Calendar data){
		
		return new SimpleDateFormat("dd/MM/yyyy").format(data.getTime());
	}
	
	public static String convertDateToString(Date data){
		
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}
	
	public static void fecharSessao(){

		//Contexto da Aplica��o  
		FacesContext context = FacesContext.getCurrentInstance();  
		//Verifica a sessao e a grava na variavel  
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
		//Fecha/Destroi sessao  
		session.invalidate();
	}

	public static void redirecionar(String parametro){
		try {			
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/ProsurWeb/pages/" + parametro);
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
     // Obt�m a sess�o atual
        HttpSession sessao = (HttpSession) ec.getSession(true);
     // Restaga o nome do usu�rio logado
        UsuarioProsur user = (UsuarioProsur) sessao.getAttribute(ConsultasProsur.USUARIO);
     
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

	/**
	 * 
	 * @Autor Wenderson Souza
	 * @Data: 07/12/2012
	 * @param value - data 
	 * @param formato - formata��o da data
	 * @return
	 */
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

	/**
	 * 
	 * @Autor Wenderson Souza
	 * @Data: 12/12/2012
	 * @param time - tempo do Date e milisegundos
	 * @param format - formata��o do Date  (dd/mm/yyyy HH:mm:ss) 
	 * @return date type texto formatado
	 */

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
	/**
	 * 
	 * @Autor Wenderson Souza
	 * @Data: 19/03/2013
	 * @param strCpf - numero do CPF
	 * @return True se o CPF � valido e False se CPF � invalido 
	 */
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
	 * @return True se o CNPJ � valido e False se CNPJ � invalido 
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
	
	/*public static void formatarDataHoraMensagem(List<Generic> listaGeneric, int index){
		
		String output;       
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); //formato que deve aparecer para o usuario 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//data conforme foi carregada do bd
		
		try {  
	          Date d = null; 
	          d = sdf.parse(listaGeneric.get(index).getVar2());//user entry date  
	          output = formatter.format(d.getTime());
	          listaGeneric.get(index).setVar2(output);
	        } catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
	}*/
	
	/*public static void formatarDtHoraMensagem(List<Generic> listaGeneric, int index){
		
		String output;       
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //formato que deve aparecer para o usuario 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//data conforme foi carregada do bd
		
		try {  
	          Date d = null; 
	          d = sdf.parse(listaGeneric.get(index).getVar2());//user entry date  
	          output = formatter.format(d.getTime());
	          String outputAux = output.replace(" 24", " 00");
	          listaGeneric.get(index).setVar2(outputAux);
	        } catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
	}*/
	
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
			
			mensagemErro =  "Hora in�cio inv�lida.";
			return mensagemErro;
			
		}else if (!validaHoraSemSegundos(hrFinal, minFim)){
			
			mensagemErro = "Hora fim inv�lida.";
			return mensagemErro;
				
		}else if( (hrIni > hrFinal)  || (hrIni == hrFinal && minIni > minFim) ){
			
			mensagemErro =  "Hora in�cio maior que a hora fim.";
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
	
	public static Calendar convertStringToCalendar(String data) throws ErroProsur{
		
		Calendar calendar = Calendar.getInstance();
		
		try {
		
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			calendar.setTime(dateFormat.parse(data));
		} catch (ParseException e) {
			
			throw new ErroProsur("erro_sistema", "A data " + data + " não pode ser formatada");
		}
		return calendar;
	}
	
	public static String extrairData(Date data) throws ErroProsur{
		
		SimpleDateFormat dateFormat = null;
		String dataFormatada;
		
		try {
		
			dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dataFormatada = dateFormat.format(data);
			
		} catch (IllegalArgumentException e) {
			
			throw new ErroProsur("erro_sistema", "A data " + data.toString() + " não pode ser formatada");
		}
		return dataFormatada;
	}
	
	public static String extrairHora(Date hora) throws ErroProsur{
		
		SimpleDateFormat hourFormat = null;
		String horaFormatada;
		
		try {
			
			hourFormat = new SimpleDateFormat("HH:mm");
			horaFormatada = hourFormat.format(hora);
		} catch (IllegalArgumentException e) {
			
			throw new ErroProsur("erro_sistema", "A hora " + hora.toString() + " não pode ser formatada");
		}
		return horaFormatada;
	}
	
	public static String extrairDiaSemana(Date data) throws ErroProsur{
		
		SimpleDateFormat dayFormat = null; 
		String diaFormatado;
		
		try {
			
			dayFormat = new SimpleDateFormat("EEEEEE");
			diaFormatado = dayFormat.format(data);
		} catch (IllegalArgumentException e) {

			throw new ErroProsur("erro_sistema", "O dia " + data.toString() + " não pode ser formatado");
		}
		return diaFormatado;
	}
	
	public static String[] separaTipoBase(String tipoBase){
		
		String[] splitTipoBase = tipoBase.split(", ");
		
		return splitTipoBase;
	} 
}