package br.gov.inpi.services;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import br.gov.inpi.intercarga.beans.AgendaCarga;
import br.gov.inpi.intercarga.beans.HistoricoCarga;
import br.gov.inpi.intercarga.beans.TbAcessoCarga;
import br.gov.inpi.intercarga.beans.TbAgendaCarga;
import br.gov.inpi.intercarga.beans.TbHistoricoCarga;
import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;

@Local
public interface EntityInterfaceIntercarga {

	public abstract void save(Object object);
	
	public abstract void update(Object object);
	
	public abstract void deleteUsuarioProsur(TbAcessoCarga acessoCarga);
	
	public abstract List<AgendaCarga> listaAgendaCarga();
	
	public abstract List<AgendaCarga> listarAgendamentosAtivos();
	
	public abstract List<TbAcessoCarga> listaUsuarioProsur();	
	
	public abstract List<HistoricoCarga> listaHistoricoCarga();
	
	public abstract AgendaCarga retornarProximoAgendamento();
	
	public abstract AgendaCarga retornarAgendamentoExecucao(int idAgenda);
	
	@SuppressWarnings("rawtypes")
	public abstract Object findOneResult(String namedQuery, HashMap mapParameters);
	
	public abstract void deleteAgendaCarga(TbAgendaCarga agendaCarga);
	
	public abstract List<String> retornarQtdCargaMarcasRetro();
	
	public abstract List<String> retornarQtdCargaDiRetro();
	
	public abstract List<String> retornarQtdCargaPatenteRetro();
	
	public abstract void salvarHistoricoCargaAutomatica(AgendaCarga agendaCarga, List<TbHistoricoCargaProcesso> histProcessos);
	
	public abstract void salvarHistoricoCargaManual(AgendaCarga agendaCarga, List<TbHistoricoCargaProcesso> histProcessos);
	
	public abstract void atualizarAgendamentoManual(AgendaCarga agendaCarga);
	
	public TbAcessoCarga buscaUsuarioProsur(String nmLogin);

	public abstract List<TbHistoricoCargaProcesso> listarHistoricoProcessoCarga(TbHistoricoCarga historicoCarga);
	
	public abstract TbHistoricoCarga getHistoricoCarga(int idHistoricoCarga);

	public String recuperaProcessos(String id_Agenda);
	
	public void atualizarAgendamentoAutomatico(AgendaCarga agendaCarga);
	
	public TbAgendaCarga retornaAgenda(String idAgenda);
	
	public void deleteProcessoTodos(Integer id);
}
