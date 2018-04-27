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
public interface ProcessoMarcasManualInterface {

	public abstract void save(Object object);
	public abstract void update(Object object);
	
	/**
	 * 
	 * Lista todos os pedidos de Marcas de forma retroativa.
	 * 
	 * @return
	 */
	public List<Object[]> listarPedidosMarcasRetroativo(String quantidadeMarcas);

	/***
	 * Lista todos os processos publicados na ultima RPI publicada pelo INPI.
	 * 
	 * @return
	 */
	public List<Object[]> listarPedidosMarcasSemanal(String numRpi);

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
	 * Retorna a descricao da Classe Nice.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	public String buscaClasseNice(String codigoProcesso);

	/***
	 * Retorna todas as classificacoes de Nice para um determinado pedido de Marcas utilizando como criterio de busca as
	 * classificacoes nacionais de um determinado pedido.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	public List<NiceClassAux> buscaClasseNiceAuxPorId(Integer codigoProcesso);

	/***
	 * 
	 * Retorna os pedidos de classificacao de Nice para um determinado pedido de Marcas.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	public abstract List<MrcClasNice> pesquisarClasseNicePorId(int codigoProcesso);

	/***
	 * 
	 * Retorna a especificacao de um processo de Marcas.
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
	 * Retorna os dados da figura da Marca pelo seu numero de processo.
	 * 
	 * @param numeroProcess
	 * @return
	 */
	public abstract MrcFiguraProcess pesquisarFiguraPorNumeroProcesso(String numeroProcess);

	/***
	 * Retorna as classificacao de Vienna de um determinado processo.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	public abstract List<MrcClassifVienna> pesquisarClassificacaoViennaPorId(int codigoProcesso);

	public abstract List<MrcEspecificacaoTexto> retornarEspecificacaoDoProcesso(int codigoProcesso);

	/**
	 * Retorna o Numero da RPI Corrente
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	public abstract String recuperarNumeroRPICorrente();
	
	/**
	 * Retorna o volume total restante de marcas retroativo
	 * @return
	 */
	public String retornarVolumeTotalMarcasRetroativo();
	
	/**
	 * Verifica se o Rpi inserido existe na base
	 * @param numeroRpi
	 * @return
	 */
	public abstract boolean verificarRpiExiste(String numeroRpi);
	
	public List<Object[]> listarPorProcessoMarcas(String quantidadeMarcas);
	
	public Boolean verificaProceso(String processo);
	
	public Boolean validaProceso(String processo);
}
