package br.gov.inpi.services;

import java.util.List;

import javax.ejb.Local;

import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;
import br.gov.inpi.patentes.beans.PtnPedido;

@Local
public interface CargaPatenteAutomaticoInterface {

	public abstract void preparaCargaPatente(String tipoCarga, List<TbHistoricoCargaProcesso> processos);
	public abstract void executaCargaPatente(List<PtnPedido> ptnPedidos, List<TbHistoricoCargaProcesso> processos);
	public List<PtnPedido> buscarPedidosPatente(String tipoCarga);
	public String executarPedidoPatente(PtnPedido diPedido) throws Exception;
}
