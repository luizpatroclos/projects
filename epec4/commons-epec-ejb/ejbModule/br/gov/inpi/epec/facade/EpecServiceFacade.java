package br.gov.inpi.epec.facade;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import br.gov.inpi.epec.beans.CategoriaNaoPatentetariaEntity;
import br.gov.inpi.epec.beans.CategoriaPatentariaEntity;
import br.gov.inpi.epec.beans.CategoriaRelatorioEntity;
import br.gov.inpi.epec.beans.HistoricodeAcessoEntity;
import br.gov.inpi.epec.beans.Tbantnaopatentaria;
import br.gov.inpi.epec.beans.Tbantnaopatentariacatrelec;
import br.gov.inpi.epec.beans.Tbantnaopatentariarelec;
import br.gov.inpi.epec.beans.Tbantpatentaria;
import br.gov.inpi.epec.beans.Tbantpatentariacatrelec;
import br.gov.inpi.epec.beans.Tbantpatentariarelec;
import br.gov.inpi.epec.beans.Tbcadativo;
import br.gov.inpi.epec.beans.Tbcadcategoria;
import br.gov.inpi.epec.beans.Tbcadcolaboracao;
import br.gov.inpi.epec.beans.Tbcadentidade;
import br.gov.inpi.epec.beans.Tbcadpais;
import br.gov.inpi.epec.beans.Tbcadtipoanterioridade;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbcaraccatrelatorioec;
import br.gov.inpi.epec.beans.Tbcatrelatorioec;
import br.gov.inpi.epec.beans.Tbclassificacao;
import br.gov.inpi.epec.beans.Tbclassificacaopatente;
import br.gov.inpi.epec.beans.TbclassificacaopatentePK;
import br.gov.inpi.epec.beans.Tbclausulatipo;
import br.gov.inpi.epec.beans.Tbcomentariosfamiliaec;
import br.gov.inpi.epec.beans.Tbdepositante;
import br.gov.inpi.epec.beans.Tbdepositantepatente;
import br.gov.inpi.epec.beans.Tbfamiliaec;
import br.gov.inpi.epec.beans.Tbfilhos;
import br.gov.inpi.epec.beans.Tbinventor;
import br.gov.inpi.epec.beans.Tbinventorpatente;
import br.gov.inpi.epec.beans.Tbnos;
import br.gov.inpi.epec.beans.Tbpatenteec;
import br.gov.inpi.epec.beans.Tbprioridadeec;
import br.gov.inpi.epec.beans.Tbrelatoriocolaboracao;
import br.gov.inpi.epec.beans.Tbrelatorioec;
import br.gov.inpi.epec.beans.Tbrelatoriopatente;

@Local
public interface EpecServiceFacade {

	public void save(Object object);

	public Object merge(Object object);

	public void delete(Object object);

	public List<Object> listAll(String namedQuery);

	@SuppressWarnings("rawtypes")
	public Object findOneResult(String namedQuery, HashMap mapParameters);

	@SuppressWarnings("rawtypes")
	public List<Object> list(String namedQuery, HashMap mapParameters);

	@SuppressWarnings("rawtypes")
	public List<Tbrelatorioec> relatorioFamilia(String namedQuery, HashMap mapParameters);
	
	public void apagar(Tbcadentidade tbcadentidade) ;
	
	public void apagarPais(Tbcadpais tbcadpais) ;

	public void update(Object object);

	public Tbcadcategoria bucarPorId(Integer id);

	public Tbfilhos bucarFilhosPorId(Long id);

	public Tbcadentidade pesquisarOrganizacaoPorId(Long id);

	public Tbcadtipoanterioridade buscaPorIdTipoAnterioridade(Integer id);

	public Tbpatenteec findPatentePorEpoDoc(String numeroEpoDoc);

	public List<Tbcomentariosfamiliaec> comentariosFamilia(Tbfamiliaec tbfamiliaec);

	public Tbcadpais findPaisPorNome(String nome);

	public void deletePrioridade(Tbprioridadeec tbprioridadeec);

	public Tbcadentidade findEntidadePorIdPais(Long idPais, Tbcadusuario tbcadusuario);

	public Tbinventor findInventorPorNome(String nome);

	public Tbclassificacao findClassificacaoPorNome(String nome);

	public Tbdepositante findDepositantePorNome(String nome);

	public Tbdepositantepatente findDepositantePorId(Long id);

	public Tbinventorpatente findInventorPorId(Tbinventorpatente tbinventorpatente);

	public Tbclassificacaopatente findClassificacaoPorId(TbclassificacaopatentePK tbclassificacaopatentePK);

	public Tbfamiliaec findIdFamiliaPatentePorDocDb(String numeroDocDb);

	public List<Tbpatenteec> buscaPatentes(String idPatente);

	public List<Tbpatenteec> buscaPatentesPrioridade(String idPatente);

	public List<Tbpatenteec> buscaFamiliaPatente(Long idFamilia);

	public Tbrelatorioec findPorPedido(String pedido);

	public Tbcadusuario findUsuarioPorNome(String nome);

	public List<Tbcatrelatorioec> findCatRelatorio(Long id);

	public List<CategoriaPatentariaEntity> findAnterioridadesPatentariaCategoriaRelatorioPorIdCat(long id, long idCategoria);

	public List<CategoriaNaoPatentetariaEntity> findAnterioridadesNaoPatentariaCategoriaRelatorioPorIdCat(long id, long idCategoria);

	public List<CategoriaRelatorioEntity> findCaracteristicasRelatorioPorId(long id, long idCategoria);

	public Tbcaraccatrelatorioec findCaracteristicaRelatorioPorIdCaracteristica(long id);

	public List<Tbcadusuario> findUserPorIdEntidade(Long idEntidade);
	
	public List<Tbcadusuario> findUserPorIdPais(Long pais);

	public Tbcadativo findCadAtivoPorNome(String nome);

	public List<Tbclausulatipo> findClausulaTipoPorIdAtivo(long idAtivo);

	public Tbrelatoriopatente findRelatorioTecnicoPorIdPatente(long id);

	public Tbpatenteec buscaPatentePorEpodoc(String pedido);
	
	public Tbpatenteec buscaPatentePorPublicacao(String pedido);

	public Tbrelatorioec findRelatorioTecnicoPorId(long idRelatorio);

	public List<Tbcadusuario> findUserPorNome(String nome);

	public List<Tbnos> findNosPorIdAtivo(long idAtivo);

	public Tbcadativo findCadAtivoPorId(long idAtivo);

	public void deleteColaboracao(Tbrelatoriocolaboracao tbrelatoriocolaboracao);

	public List<HistoricodeAcessoEntity> findAllHistorico();

	/**
	 * Método responsável em realizar a pesquisa de uma Patente por número de
	 * DocDb.
	 * 
	 * @param numeroDocDb
	 * @return
	 */
	public Tbpatenteec findPatentePorDocDb(String numeroDocDb);

	/**
	 * Método responsável em realizar a pesquisa de uma Patente por número de
	 * EpoDoc.
	 * 
	 * @param entidade
	 * 
	 * @param numeroDocDb
	 * @return
	 */
	public Tbpatenteec findPatentePorAplicacaoEpoDoc(String numeroEpoDoc);

	public Tbpatenteec findPatentePorAplicacaoEpoDocEEntidade(String numeroEpoDoc, String entidade);

	public Tbpatenteec findPatentePorPublicacaoEpoDoc(String numeroDocDb, String pais);

	public Tbpatenteec findPatentePorNumeroOriginal(String numeroOriginal);

	public Tbpatenteec findPatentePorPublicacaoDocDb(String numeroDocDb, String pais, String kindCode);

	public Tbpatenteec findPatentePorAplicacaoDocDb(String numeroDocDb, String pais, String kindCode);

	/***
	 * 
	 * Método responsável em realizar a pesquisa da familia de uma Patente.
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	public Tbfamiliaec findIdFamiliaPatentePorEpoDoc(String numeroEpoDoc);

	/***
	 * 
	 * Método responsável em realizar a pesquisa da familia de uma Patente.
	 * 
	 * @param numeroDocDb
	 * @return
	 */
	public Tbfamiliaec findIdFamiliaPatenteAplicacaoPorEpodoc(String numeroDocDb);

	public Tbfamiliaec findIdFamiliaPatentePublicacaoPorEpodoc(String numero, String pais);

	public Tbfamiliaec findIdFamiliaPatenteNumeroOriginal(String numeroOriginal);

	public Tbfamiliaec findIdFamiliaAplicacaoPatentePorDocDb(String numero, String pais, String kindCode);

	public Tbfamiliaec findIdFamiliaPublicacaoPatentePorDocDb(String numero, String pais, String kindCode);

	public Tbpatenteec findPatentePorAplicacaoDocDbEEntidade(String numeroDocDb, String pais, String kindCode, String entidade);

	public Tbpatenteec findPatentePorPublicacaoEpoDocEEntidade(String numeroDocDb, String pais, String entidade);

	public Tbpatenteec findPatentePorNumeroOriginalEEntidade(String numeroOriginal, String entidade);

	public Tbpatenteec findPatentePorPublicacaoDocDbEEntidade(String numeroDocDb, String pais, String kindCode, String entidade);

	public void deleteAntPatentaria(Tbantpatentariarelec tbantpatentariarelec);

	public void deleteAntNaoPatentaria(Tbantnaopatentariarelec tbantnaopatentariarelec);

	public void deleteFilhos(Tbfilhos tbfilhos);

	public void deleteClausulaTipo(Tbclausulatipo tbClausula);

	public void deleteCategoria(Tbcatrelatorioec tbcatrelatorioec);

	public void deleteAntPatentariaCat(Tbantpatentariacatrelec tbantpatentariacatrelec);

	public void deleteAntNaoPatentariaCat(Tbantnaopatentariacatrelec tbantnaopatentariacatrelec);

	public void deleteCaracteristica(Tbcaraccatrelatorioec tbcaraccatrelatorioec);

	public void deleteAntPatentariaCarac(Tbantpatentaria tbantpatentaria);

	public void deleteAntNaoPatentariaCarac(Tbantnaopatentaria tbantnaopatentaria);

	public void deleteClassificacaoPatente(Tbclassificacaopatente tbclassificacaopatente);
	
	public void deleteClassificacao(Tbclassificacao tbclassificacao);

	public void deleteDepositantePatente(Tbdepositantepatente tbdepositantepatente);

	public void deleteInventorPatente(Tbinventorpatente tbinventorpatente);
	
	public void deleteInventor(Tbinventor tbinventor);
	
	public void deleteColaboracao(Tbcadcolaboracao tbcadcolaboracao);

	public void deleteInventorPatenteTodos(Long id);

	public void deleteDepositantePatenteTodos(Long id);

	public Tbcadusuario findUsuarioPorLogin(String login);

	public void deleteTbno(Tbnos tbnos);
	
	public Tbnos buscarRaiz(Long idEntidade);
	
	public List<Tbcadtipoanterioridade> listarNaoPatentarias();
	
	public Tbdepositante obterDepositante(String texto);
	
	
	public Tbpatenteec findPatentePublicacao(String numero);

	public Tbpatenteec findPatentePorPedido(String numero);

	public Tbpatenteec findPatentePorOriginal(String numero);

	public Tbpatenteec findPatentePorEpodoc(String numero);
	
	public List<Tbpatenteec> buscaPatentesPorPedido(String idPatente);
	
	public List<Tbpatenteec> buscaPatentesPorPublicacao(String idPatente, String pais);

	
	
	

}
