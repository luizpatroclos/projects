package br.gov.inpi.services;

import java.util.List;

import javax.ejb.Local;

import br.gov.inpi.marcas.beans.MrcClasNice;
import br.gov.inpi.marcas.beans.MrcClassifVienna;
import br.gov.inpi.marcas.beans.MrcEspecificacao;
import br.gov.inpi.marcas.beans.MrcEspecificacaoTexto;
import br.gov.inpi.marcas.beans.MrcFiguraProcess;
import br.gov.inpi.prosur.beans.NiceClassAux;

@Local
public interface EntityInterfaceMarcas {

	public abstract void save(Object object);

	public abstract void update(Object object);

	/**
	 * 
	 * Lista todos os pedidos de Marcas de forma retroativa.
	 * 
	 * @return
	 */
	public List<Object[]> listarPedidosMarcasRetroativo();

	/***
	 * Lista todos os processos publicados na ultima RPI publicada pelo INPI.
	 * 
	 * @return
	 */
	public List<Object[]> listarPedidosMarcasRetroativoPorRPI();

	/***
	 * 
	 * Lista dados de um ou mais processos.
	 * 
	 * @param processo
	 * @return
	 */
	public List<Object[]> listarPedidosMarcasRetroativoPorProcesso(String processo);

	/**
	 * 
	 * Retorna a descri��o da Classe Nice.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	public String buscaClasseNice(String codigoProcesso);

	/***
	 * Retorna todas as classifica��es de Nice para um determinado pedido de Marcas utilizando como crit�rio de busca as
	 * classifica��es nacionais de um determinado pedido.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	public List<NiceClassAux> buscaClasseNiceAuxPorId(Integer codigoProcesso);

	/***
	 * 
	 * Retorna os pedidos de Classifica��o de Nice para um determinado pedido de Marcas.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	public abstract List<MrcClasNice> pesquisarClasseNicePorId(int codigoProcesso);

	/***
	 * 
	 * Retorna a especifica��o de um processo de Marcas.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	public abstract List<MrcEspecificacao> pesquisarEspecificacaoPorId(int codigoProcesso);

	/***
	 * 
	 * Retorna os dados da figura da Marca de um determinado processo.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	public abstract MrcFiguraProcess pesquisarFiguraPorId(int codigoProcesso);

	/***
	 * 
	 * Retorna os dados da figura da Marca pelo seu n�mero de processo.
	 * 
	 * @param numeroProcess
	 * @return
	 */
	public abstract MrcFiguraProcess pesquisarFiguraPorNumeroProcesso(String numeroProcess);

	/***
	 * Retorna as Classifica��es de Vienna de um determinado processo.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	public abstract List<MrcClassifVienna> pesquisarClassificacaoViennaPorId(int codigoProcesso);

	public abstract List<MrcEspecificacaoTexto> retornarEspecificacaoDoProcesso(int codigoProcesso);

	/**
	 * Retorna o Número da RPI Corrente
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	public abstract String recuperarNumeroRPICorrente();

}