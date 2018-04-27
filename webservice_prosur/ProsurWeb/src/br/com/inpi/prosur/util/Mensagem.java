package br.com.inpi.prosur.util;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Responsavel por retornar mensagens customizadas de qualquer arquivo .properties
 * na package resource.
 * Para buscar a mensagem basta instanciar o objeto passando como parametro
 * o arquivo de mensagens que deve ser carregado e chamar o metodo responsavel
 * com o nome da mensagem.
 * @author ahlucena
 *
 */
public final class Mensagem {	

	public Mensagem(String pathEArquivo){
		
		Mensagem.pathEArquivo = pathEArquivo;
	}
	
	private static String pathEArquivo;
	private static String idioma = "pt";
	private static String pais = "BR";
	private static Locale currentLocale = new Locale(idioma, pais);
	private static ResourceBundle mensagemResource; 
	
	public String retornaMensagem(final String mensagem){
		
		mensagemResource = ResourceBundle.getBundle(pathEArquivo, currentLocale);
		return mensagemResource.getString(mensagem);
	}
	
}
