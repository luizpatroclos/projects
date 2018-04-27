package br.com.inpi.prosur.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.inpi.prosur.exception.ErroProsur;
import br.com.inpi.prosur.validacoes.Validador;
import br.gov.inpi.intercarga.beans.AgendaCarga;

public class AgendamentoUtil {

	public static final String AUTOMATICO = "AUTOMATICO";

	public static final String MANUAL = "MANUAL";
	
	public static final String SEMANAL = "SEMANAL";

	public static final String RETROATIVA = "RETROATIVA";
	
	public static boolean isNovoAgendamento = false;
	
	private static AgendaCarga dtoAgendaCarga;
	
	
	private AgendamentoUtil(){}
	
	/**
	 * Faz o merge da data e hora em campos separados
	 * para um novo agendamento
	 * @param data
	 * @param hora
	 * @return
	 * @throws ErroProsur 
	 */
	public static Date converteDataHora(String data, String hora, String tipoAgendamento) throws ErroProsur{

		SimpleDateFormat dateFormat = null;
		isNovoAgendamento = true;		
		
		if (tipoAgendamento.equals(AUTOMATICO)) {
			
			dateFormat = new SimpleDateFormat("EEEEEE HH:mm");		
		} else{
			
			dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");			
		}
		
		Date dataRetorno = null;
		
		try {
			
			dataRetorno = new Date(dateFormat.parse(data + " " + hora).getTime());			
		} catch (ParseException e) {

			throw new ErroProsur("formato_data", "Formato inválido para a data inserida", e);
		}
		return dataRetorno;
	}
	
	/**
	 * Faz o merge da data e hora em campos separados
	 * para a atualizacao
	 * @param data
	 * @param hora
	 * @return
	 * @throws ErroProsur 
	 */
	public static Date converteDataHora(String data, String hora, AgendaCarga agendaCarga) throws ErroProsur{

		SimpleDateFormat dateFormat = null;
		isNovoAgendamento = false;
		dtoAgendaCarga = agendaCarga;
		
		if (agendaCarga.getTipoAgendamento().equals(AUTOMATICO)) {
			
			dateFormat = new SimpleDateFormat("EEEEEE HH:mm");
		} else{ 
			
			dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");			
		}
		
		Date dataRetorno = null;
		
		try {
			
			dataRetorno = new Date(dateFormat.parse(data + " " + hora).getTime());			
		} catch (ParseException e) {

			throw new ErroProsur("formato_data", "Formato inválido para a data inserida", e);
		}
		return dataRetorno;
	}
	
	public static String preparaTipoBase(String[] checkTipoBase){
		
		StringBuilder tipoBase = new StringBuilder();
		
		if (checkTipoBase != null) {
			
			int cont = checkTipoBase.length - 1;
			
			for (int i = 0; i < checkTipoBase.length; i++) {
				
				if (i == 0) { //primeira vez
					
					if (cont > 0) {
						
						tipoBase.append(checkTipoBase[i] + ",");
					} else {
						
						tipoBase.append(checkTipoBase[i]);
					}
				} else if (i > 0) { //a partir da segunda vez
				
					if (i < cont) {
						
						tipoBase.append(checkTipoBase[i] + ",");
					} else{
						
						tipoBase.append(checkTipoBase[i]);
					}
				}
			}
		}
		return tipoBase.toString();
	}

	@SuppressWarnings("unchecked")
	public static List<Date> separaDataAgendamento(){
		
		FacesContext facesCtx = FacesContext.getCurrentInstance();
		
		List<Date> listaDatas = new ArrayList<Date>();
		List<AgendaCarga> listaAgendamentos = new ArrayList<AgendaCarga>();
		
		try {
			
			listaAgendamentos.addAll((List<AgendaCarga>)facesCtx.getExternalContext().getSessionMap().get("listaAgendamento"));
			
			for (AgendaCarga agendaCarga : listaAgendamentos) {
				
				if (!isNovoAgendamento) {
				
					if (!agendaCarga.getIdAgendaCarga().equals(dtoAgendaCarga.getIdAgendaCarga())) { //verificacao para garantir 
																							   //que a data adicionada a lista nao e a mesma
																							   //da que esta sendo atualizada
						
						listaDatas.add(agendaCarga.getDataHoraAgenda());
					}
				} else {
					
					listaDatas.add(agendaCarga.getDataHoraAgenda());
				}
			}
		} catch (Exception e) {

			new FacesMessageUtil().mensagemErro("erro_sistema");
		}
		
		return listaDatas;
	}
	
	/**
	 * Verifica se a data que vai ser agendada nao entra em conflito com alguma data já agendada
	 * com um intervalo de até 5 horas
	 * @param cont
	 * @param tipoAgendamento
	 * @throws ParseException
	 * @throws ErroProsur 
	 */
	public static void validaDataAgendamento(String tipoAgendamento, Date dataAgendamentoConvertida, String dataNovoAgendamento) throws ErroProsur{
		
		try{
		
			int horas = 0;
			
			SimpleDateFormat dateFormatData = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat dateFormatDia = new SimpleDateFormat("EEEEEE");
				
			List<Date> listaDatas = separaDataAgendamento(); //separa todas as datas ja agendadas
			
			for (Date dataHoraAgendada : listaDatas) {
			
				SimpleDateFormat dateFormatHora = new SimpleDateFormat("HH:mm");
				String horaDataAgendada = dateFormatHora.format(dataHoraAgendada);
				String horaDataNovoAgendamento = dateFormatHora.format(dataAgendamentoConvertida);
				String lDataNovoAgendamento = dataNovoAgendamento;
				
				if (tipoAgendamento.equals(MANUAL)) {
					
					horas = Validador.calculaDiferencaDataEmHoras(horaDataAgendada, horaDataNovoAgendamento); //calcula a diferenca de horas					
					
					String dataAgendada = dateFormatData.format(dataHoraAgendada).substring(0, 10); //resgata a data, somente
					
					if ("1970".equals(dataAgendada.substring(6))) {
						
						Date data = dateFormatData.parse(lDataNovoAgendamento); //atribuir dataNovoAgendamento a uma variavel local
						lDataNovoAgendamento = dateFormatDia.format(data);
						dataAgendada = dateFormatDia.format(dataHoraAgendada);
					}
					
					if (lDataNovoAgendamento.equals(dataAgendada) && (horas <= 5 && horas >= 0)) {
						
						throw new ErroProsur("data_agendamento_existente", "Já existe um agendamento para: " + dataNovoAgendamento);
					}
				} else if (tipoAgendamento.equals(AUTOMATICO)) {
					
					horas = Validador.calculaDiferencaDataEmHoras(horaDataAgendada, horaDataNovoAgendamento); //calcula a diferenca de horas
					
					String diaSemanaAgendado = dateFormatDia.format(dataHoraAgendada); //resgata o dia da semana, somente
					
					if (lDataNovoAgendamento.equals(diaSemanaAgendado) && (horas <= 5 && horas >= 0)) {
						
						throw new ErroProsur("data_agendamento_existente", "Já existe um agendamento para: " + dataNovoAgendamento);
					}
				}
			}	
		} catch(ParseException e) {
			
			throw new ErroProsur("formato_data", "Formato inválido para a data inserida", e);
		}
	}
}
