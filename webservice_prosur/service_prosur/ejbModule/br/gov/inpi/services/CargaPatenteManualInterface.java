package br.gov.inpi.services;

import java.util.List;

import javax.ejb.Local;

import br.gov.inpi.intercarga.beans.AgendaCarga;
import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;
import br.gov.inpi.patentes.beans.PtnPedido;

@Local
public interface CargaPatenteManualInterface {

	public abstract void preparaCargaPatente(String tipoCarga, String valorConsulta, AgendaCarga agendaCarga, List<TbHistoricoCargaProcesso> processos);
	public abstract void executaCargaPatente(List<PtnPedido> ptnPedidos);
	public List<PtnPedido> buscarPedidosPatente(String tipoCarga, String valorConsulta);
	public String executarPedidoPatente(PtnPedido diPedido) throws Exception;
	public abstract void salvarStatusRetroativa(String valorConsulta, int idAgendaCarga);
	public void executaCargaPatenteProcesso(List<PtnPedido> ptnPedidos, List<TbHistoricoCargaProcesso> processos);
}
