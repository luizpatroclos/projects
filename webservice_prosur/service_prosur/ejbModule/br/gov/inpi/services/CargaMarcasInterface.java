
package br.gov.inpi.services;

import java.util.List;

import javax.ejb.Local;

/**
 *
 * @author luizAlbuquerque
 */
@Local
public interface CargaMarcasInterface {

    public List<Object[]> buscarPedidosMarcas (String tipoEnvio);
	
	public String executarPedidoMarca(Object[] marca) throws Exception;
	
}

