package br.gov.inpi.services;

import java.util.List;

import javax.ejb.Local;

import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;
import br.gov.inpi.patentes.beans.DiPedido;

@Local
public interface CargaDesenhoAutomaticoInterface {

	public abstract void preparaCargaDesenho(String tipoCarga, List<TbHistoricoCargaProcesso> processos);
	public abstract void executaCargaDesenho(List<DiPedido> diPedidos, List<TbHistoricoCargaProcesso> processos);
	public List<DiPedido> buscarPedidosDesenho (String tipoCarga);
	public String executarPedidoDesenho(DiPedido diPedido) throws Exception;
}
