package br.gov.inpi.services;

import java.util.List;

import javax.ejb.Local;

import br.gov.inpi.intercarga.beans.AgendaCarga;
import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;

@Local
public interface CargaMarcasAutomaticoInterface {

	public abstract void preparaCargaMarcas(String tipoCarga, List<TbHistoricoCargaProcesso> processos);
	public abstract void executaCargaMarcas(List<Object[]> objectProcessosMarcas, List<TbHistoricoCargaProcesso> processos);
	public List<Object[]> buscarPedidosMarcas (String tipoEnvio);
	public String executarPedidoMarca(Object[] marca) throws Exception;
}
