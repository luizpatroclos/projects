package br.gov.inpi.services;

import java.util.List;

import javax.ejb.Local;

import br.gov.inpi.intercarga.beans.AgendaCarga;
import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;
import br.gov.inpi.patentes.beans.DiPedido;

@Local
public interface CargaDesenhoManualInterface {

	public abstract void preparaCargaDesenho(String tipoCarga, String valorConsulta, AgendaCarga agendaCarga, List<TbHistoricoCargaProcesso> processos);
	public abstract void executaCargaDesenho(List<DiPedido> diPedidos);
	public List<DiPedido> buscarPedidosDesenho (String tipoCarga, String valorConsulta);
	public String executarPedidoDesenho(DiPedido diPedido) throws Exception;
	public abstract void salvarStatusRetroativa(String valorConsulta, int idAgendaCarga);
	public abstract void executaCargaDesenhoProcesso(List<DiPedido> diPedidos, List<TbHistoricoCargaProcesso> processos);
}
