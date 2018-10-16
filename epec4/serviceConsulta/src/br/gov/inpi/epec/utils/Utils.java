package br.gov.inpi.epec.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	
	public String converterData(Date data){
		
		String dataFormatada = null;
		SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");  
		dataFormatada = out.format(data);
		return dataFormatada;
		
	}
	

}
