package br.gov.inpi.services;

import java.util.List;

import javax.ejb.Local;

import br.gov.inpi.intercarga.beans.AgendaCarga;
import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;

@Local
public interface CargaMarcasManualInterface {

	public abstract void preparaCargaMarcas(String tipoCarga, String valorConsulta, AgendaCarga agendaCarga, List<TbHistoricoCargaProcesso> processos);
	public abstract void executaCargaMarcas(List<Object[]> objectProcessosMarcas);
	public List<Object[]> buscarPedidosMarcas (String tipoEnvio, String valorConsulta);
	public String executarPedidoMarca(Object[] marca) throws Exception;
	public abstract void salvarStatusRetroativa(String valorConsulta, int idAgendaCarga);
}
