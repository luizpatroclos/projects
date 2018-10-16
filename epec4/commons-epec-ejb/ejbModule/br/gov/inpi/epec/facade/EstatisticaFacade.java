package br.gov.inpi.epec.facade;

import java.util.List;

import javax.ejb.Local;

@Local
public abstract interface EstatisticaFacade {
	public abstract int countEntidades();

	public abstract int countFamilias();

	public abstract int countUsuarios(Long paramLong);

	public abstract int countPedidosPatentes(Long paramLong);

	public abstract int countPrioridades(Long paramLong);

	public abstract int countRelatoriosTecnicos(Long paramLong);

	public abstract int countAnterioridadesPatentarias(Long paramLong);

	public abstract int countAnterioridadesNaoPatentarias(Long paramLong);

	public abstract int countComentarios(Long paramLong);

	public abstract int countLogs(Long paramLong);

	public abstract List<Object> listAll();
}