package br.gov.inpi.services;

import java.util.List;

import javax.ejb.Local;

import br.gov.inpi.patentes.beans.DiPedido;

/*
 * 
 * 
 * @author luizAlbuquerque
 */
@Local
public interface CargaDesenhosInterface {

	public List<DiPedido> buscarPedidosDesenho(String tipoEnvio);

	public String executarPedidoDesenho(DiPedido pedido) throws Exception;

}
