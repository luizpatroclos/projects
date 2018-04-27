package br.gov.inpi.mail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.ejb.ScheduleExpression;

import org.apache.commons.lang3.time.DurationFormatUtils;

public abstract class Estatistica {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private static final String HH_MM_SS = "HH:mm:ss";
	private static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";

	private static Integer quantidadeCargaFinalizada = null;

	private static HashMap<String, String> parametros = new HashMap<String, String>();

	private static DateFormat dateFormat = new SimpleDateFormat();	
	
	public static void addString(String chave, String valor) {
		parametros.put(chave, valor);
	}

	public static void addTime(String chave, Long valor) {
		parametros.put(chave, DurationFormatUtils.formatDuration(valor, DD_MM_YYYY_HH_MM_SS));
	}

	public static void addInt(String chave, Integer valor) {
		parametros.put(chave, String.valueOf(valor));
	}

	public static void addDate(String chave, Date valor) {

		dateFormat = new SimpleDateFormat(DD_MM_YYYY_HH_MM_SS);
		parametros.put(chave, dateFormat.format(valor));
	}

	public static HashMap<String, String> getParametros() {
		return parametros;
	}

	public static void finalizarCarga() {

		quantidadeCargaFinalizada = getQuantidadeCargaFinalizada();

		if (quantidadeCargaFinalizada > 2) {

			addDate("termino_execucao", new Date());

//			formatarParaExibicaoNoRelatorio();

			enviarEmail();
		} else {
			quantidadeCargaFinalizada++;

		}

	}

	public static Integer getQuantidadeCargaFinalizada() {

		if (quantidadeCargaFinalizada == null) {
			quantidadeCargaFinalizada = 1;
		}

		return quantidadeCargaFinalizada;
	}

	private static void formatarParaExibicaoNoRelatorio() {

		addString("tempo_execucao", getTempoProcessamento(getValueAsDateTime("inicio_execucao"), getValueAsDateTime("termino_execucao")));

		addString("tempo_processamento_marcas", getTempoProcessamento(getValueAsDateTime("inicio_processamento_marcas"), getValueAsDateTime("termino_processamento_marcas")));
		addString("tempo_processamento_patentes", getTempoProcessamento(getValueAsDateTime("inicio_processamento_patentes"), getValueAsDateTime("termino_processamento_patentes")));
		addString("tempo_processamento_desenhos", getTempoProcessamento(getValueAsDateTime("inicio_processamento_desenhos"), getValueAsDateTime("termino_processamento_desenhos")));

		addString("inicio_processamento_marcas", getValueAsTime("inicio_processamento_marcas"));
		addString("termino_processamento_marcas", getValueAsTime("termino_processamento_marcas"));

		addString("inicio_processamento_patentes", getValueAsTime("inicio_processamento_patentes"));
		addString("termino_processamento_patentes", getValueAsTime("termino_processamento_patentes"));

		addString("inicio_processamento_desenhos", getValueAsTime("inicio_processamento_desenhos"));
		addString("termino_processamento_desenhos", getValueAsTime("termino_processamento_desenhos"));

		addString("inicio_execucao", getValueAsTime("inicio_execucao"));
		addString("termino_execucao", getValueAsTime("termino_execucao"));
		addString("data_processamento", formatStringAsDate(new Date()));
		addString("resultado_erros", getErros());

	}

	private static String getValueAsString(String valor) {
		return parametros.get(valor);
	}

	public static Date getValueAsDateTime(String valor) {
		dateFormat = new SimpleDateFormat(DD_MM_YYYY_HH_MM_SS);

		try {
			return dateFormat.parse(getValueAsString(valor));
		} catch (ParseException e) {
			throw new IllegalStateException("Erro ao Na conversão getValueAsDate de " + valor);
		}

	}

	private static String getValueAsTime(String valor) {
		dateFormat = new SimpleDateFormat(HH_MM_SS);

		return dateFormat.format(getValueAsDateTime(valor));
	}

	private static String formatStringAsDate(Date data) {
		dateFormat = new SimpleDateFormat(DD_MM_YYYY);

		return dateFormat.format(data);
	}

	public static void enviarEmail() {

		EmailService emailService = new EmailService();

		emailService.sendMail(getParametros());

	}

	public static String getHoraInicioFormatada(ScheduleExpression agendaCargaSemanal) {

		StringBuffer inicio = new StringBuffer();
		Calendar calendar = Calendar.getInstance();

		inicio.append(calendar.get(Calendar.DAY_OF_MONTH));
		inicio.append("/");
		inicio.append(calendar.get(Calendar.MONTH) + 1);
		inicio.append("/");
		inicio.append(calendar.get(Calendar.YEAR));
		inicio.append(" ");
		inicio.append(agendaCargaSemanal.getHour());
		inicio.append(":");
		inicio.append(agendaCargaSemanal.getMinute());
		inicio.append(":");
		inicio.append(agendaCargaSemanal.getSecond());

		return inicio.toString();
	}

	public static String getTempoProcessamento(Date horaInicio, Date horaTermino) {

		return DurationFormatUtils.formatPeriod(horaInicio.getTime(), horaTermino.getTime(), HH_MM_SS);

	}

	public static String getErros() {

		int errosPatentes = 0, errosDesenhos = 0, errosMarcas = 0, totalErros = 0;

		errosPatentes = Integer.valueOf(parametros.get("processados_erros_patentes"));
		errosDesenhos = Integer.valueOf(parametros.get("processados_erros_desenhos"));
		errosMarcas = Integer.valueOf(parametros.get("processados_erros_marcas"));
		totalErros = errosPatentes + errosDesenhos + errosMarcas;

		String mensagem = "Nenhum erro foi encontrado durante o processo";

		if (totalErros > 0) {
			mensagem = "Foram encontrados " + totalErros + " erros no processo";
		}

		return mensagem;

	}

}
