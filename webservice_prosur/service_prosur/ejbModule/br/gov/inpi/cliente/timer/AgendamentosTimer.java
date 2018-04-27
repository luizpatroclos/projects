package br.gov.inpi.cliente.timer;

import java.util.ArrayList;
import java.util.List;

import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;


public class AgendamentosTimer {
	
	protected static int idAgenda;
	
	//AUTO
	protected static int contadorAutoSemanal = 0;
	protected static int contadorAutoRetro = 0;
	protected static boolean agendamentoAutoSemanal;
	protected static boolean agendamentoAutoRetro;
	
	//MANUAL
	protected static int contadorManualSemanal = 0;
	protected static int contadorManualRetro = 0;
	protected static int contadorManualProcesso = 0;
	protected static boolean agendamentoManualSemanal;
	protected static boolean agendamentoManualRetro;
	protected static boolean agendamentoManualProcessoDesenho;
	protected static boolean agendamentoManualProcessoMarcas;
	protected static boolean agendamentoManualProcessoPatente;
	protected static List<TbHistoricoCargaProcesso> processos;
	
	public static int getIdAgenda(){
		
		return idAgenda;
	}
}
