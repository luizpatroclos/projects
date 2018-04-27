package br.gov.inpi.services;

import java.util.List;

import javax.ejb.Local;

import br.gov.inpi.patentes.beans.PtnPedido;

/**
 *
 * @author luizAlbuquerque
 */
@Local
public interface CargaPatentesInterface {

	public List<PtnPedido> buscarPedidosPatentes(String tipoEnvio);

	public String executarPedidoPatente(PtnPedido pedido) throws Exception;

}
