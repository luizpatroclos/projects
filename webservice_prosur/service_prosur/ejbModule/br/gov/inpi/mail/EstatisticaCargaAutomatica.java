package br.gov.inpi.mail;

import static br.gov.inpi.prosur.carga.util.ProsurUtil.*;
import static br.gov.inpi.prosur.carga.util.Validador.vazio;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.time.DurationFormatUtils;

import br.gov.inpi.cliente.timer.AgendamentosTimer;
import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;
import br.gov.inpi.prosur.carga.util.Validador;

public class EstatisticaCargaAutomatica {

	private EstatisticaCargaAutomatica(){}
	
	private static HashMap<String, Date> dataParametro = new HashMap<String, Date>();	
	private static HashMap<String, Integer> quantidadeParametro = new HashMap<String, Integer>();
	private static HashMap<String, String> rpi = new HashMap<String, String>();
	private static HashMap<String, String> parametrosRelatorio = new HashMap<String, String>();
	
	private static int quantidadeCarga = 0;
	private static boolean cargasInicializadas;
	private static boolean cargasFinalizadas;
	
	public static HashMap<String, String> getParametrosRelatorio(){
		
		return parametrosRelatorio;
	}
	
	public static boolean getCargasFinalizadas(){
		
		return cargasFinalizadas;
	}	
	public static Date getDataParametro(String chave){
		
		return dataParametro.get(chave);
	}
	public static void setDataParametro(String chave, Date data){
		
		dataParametro.put(chave, data);
	}
	public static String getQuantidadeParametro(String quantidade){
		
		return quantidadeParametro.get(quantidade).toString();
	}
	public static void setQuantidadeParametro(String chave, Integer quantidade){
		
		quantidadeParametro.put(chave, quantidade);
	}
	public static String getRpi(String chave){
		
		return rpi.get(chave);
	}
	public static void setRpi(String chave, String valor){
		
		rpi.put(chave, valor);
	}
	
	public static void inicializaCarga(){
		
		if (!cargasInicializadas) {
			
			setDataParametro("inicio_carga", new Date());
			cargasInicializadas = true;
		}
	}
	
	public static void finalizaCarga(){
		
		cargasFinalizadas = false;
		
		if (quantidadeCarga > 1) {
			
			setDataParametro("termino_carga", new Date());
			
			cargasInicializadas = false;
			cargasFinalizadas = true;
			quantidadeCarga = 0;
			
			formatarParaExibicaoNoRelatorio();
			enviarEmail();
		} else {
			
			quantidadeCarga++;
		}
	}
	
	private static void formatarParaExibicaoNoRelatorio() {

		try {
			
			String tipoCarga = getRpi("numero_rpi") != null ? "semanal" : "retroativa";
			
			if ("semanal".equals(tipoCarga)) {
				
				parametrosRelatorio.put("tipo_carga", "Carga Semanal");
				parametrosRelatorio.put("numero_rpi", " - RPI - " + getRpi("numero_rpi"));
			} else {
				
				parametrosRelatorio.put("tipo_carga", "Carga Retroativa");
				parametrosRelatorio.put("numero_rpi", "");
			}
			
			String quantidadeErros = sumAll(getQuantidadeParametro("quantidade_marcas_erros"), getQuantidadeParametro("quantidade_desenho_erros"),
				        					getQuantidadeParametro("quantidade_patente_erros"));

			parametrosRelatorio.put("tempo_execucao", getTempoProcessamento(getDataParametro("inicio_carga"), getDataParametro("termino_carga")));
			parametrosRelatorio.put("data_processamento", extrairData(getDataParametro("inicio_carga")));
			parametrosRelatorio.put("inicio_execucao", extrairHora(getDataParametro("inicio_carga")));
			parametrosRelatorio.put("termino_execucao", extrairHora(getDataParametro("termino_carga")));
			parametrosRelatorio.put("resultado_erros", "0".equals(quantidadeErros) ? "Nenhum erro foi encontrado durante o processo." :
													   "Foram encontrados " + quantidadeErros + " erros no processo.");
			
			//PEDIDOS PROCESSADOS
			parametrosRelatorio.put("pedidos_processados_marcas", getQuantidadeParametro("quantidade_processados_marcas"));
			parametrosRelatorio.put("pedidos_processados_desenhos", getQuantidadeParametro("quantidade_processados_desenho"));
			parametrosRelatorio.put("pedidos_processados_patentes", getQuantidadeParametro("quantidade_processados_patente"));
			
			//DETALHES PATENTE
			parametrosRelatorio.put("tempo_processamento_patentes", getTempoProcessamento(getDataParametro("inicio_processo_patente"), 
																			  			  getDataParametro("termino_processo_patente")));
			parametrosRelatorio.put("inicio_processamento_patentes", extrairHora(getDataParametro("inicio_processo_patente")));
			parametrosRelatorio.put("termino_processamento_patentes", extrairHora(getDataParametro("termino_processo_patente")));
			
			//PROCESSADOS - SUCESSO, SIGILO E ERRO
			parametrosRelatorio.put("processados_sucesso_patentes", getQuantidadeParametro("quantidade_patente_sucesso"));
			parametrosRelatorio.put("processados_sigilo_patentes", getQuantidadeParametro("quantidade_patente_sigilo"));
			parametrosRelatorio.put("processados_erros_patentes", getQuantidadeParametro("quantidade_patente_erros"));
			//FIM DETALHES PATENTE
			
			//DETALHES DESENHO
			parametrosRelatorio.put("tempo_processamento_desenhos", getTempoProcessamento(getDataParametro("inicio_processo_desenho"), 
																			  			  getDataParametro("termino_processo_desenho")));
			parametrosRelatorio.put("inicio_processamento_desenhos", extrairHora(getDataParametro("inicio_processo_desenho")));
			parametrosRelatorio.put("termino_processamento_desenhos", extrairHora(getDataParametro("termino_processo_desenho")));
			
			//PROCESSADOS - SUCESSO, SIGILO E ERRO
			parametrosRelatorio.put("processados_sucesso_desenhos", getQuantidadeParametro("quantidade_desenho_sucesso"));
			parametrosRelatorio.put("processados_sigilo_desenhos", getQuantidadeParametro("quantidade_desenho_sigilo"));
			parametrosRelatorio.put("processados_erros_desenhos", getQuantidadeParametro("quantidade_desenho_erros"));
			//FIM DETALHES DESENHO
			
			//DETALHES MARCAS
			parametrosRelatorio.put("tempo_processamento_marcas", getTempoProcessamento(getDataParametro("inicio_processo_marcas"), 
																			  			getDataParametro("termino_processo_marcas")));
			parametrosRelatorio.put("inicio_processamento_marcas", extrairHora(getDataParametro("inicio_processo_marcas")));
			parametrosRelatorio.put("termino_processamento_marcas", extrairHora(getDataParametro("termino_processo_marcas")));
			
			//PROCESSADOS - SUCESSO, SIGILO E ERRO
			parametrosRelatorio.put("processados_sucesso_marcas", getQuantidadeParametro("quantidade_marcas_sucesso"));
			parametrosRelatorio.put("processados_erros_marcas", getQuantidadeParametro("quantidade_marcas_erros"));
			//FIM DETALHES MARCAS
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void enviarEmail() {

		EmailService emailService = new EmailService();

		emailService.sendMail(getParametrosRelatorio());
	}
	
	public static String getTempoProcessamento(Date horaInicio, Date horaTermino) {
		
		return DurationFormatUtils.formatPeriod(horaInicio.getTime(), horaTermino.getTime(), "HH:mm:ss");
	}
	
	public static String getErros() {

		int errosPatentes = 0, errosDesenhos = 0, errosMarcas = 0, totalErros = 0;

		errosPatentes = Integer.valueOf(quantidadeParametro.get("quantidade_patente_erros"));
		errosDesenhos = Integer.valueOf(quantidadeParametro.get("quantidade_desenho_erros"));
		errosMarcas = Integer.valueOf(quantidadeParametro.get("quantidade_marcas_erros"));
		totalErros = errosPatentes + errosDesenhos + errosMarcas;

		String mensagem = "Nenhum erro foi encontrado durante o processo";

		if (totalErros > 0) {
			mensagem = "Foram encontrados " + totalErros + " erros no processo";
		}

		return mensagem;
	}
	
	public static void recarregarValores(){
		
		dataParametro = new HashMap<String, Date>();
		quantidadeParametro = new HashMap<String, Integer>();
		rpi = new HashMap<String, String>();
	}
}
