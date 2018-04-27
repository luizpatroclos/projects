package br.gov.inpi.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.gov.inpi.marcas.beans.MrcClasNice;
import br.gov.inpi.marcas.beans.MrcClassifVienna;
import br.gov.inpi.marcas.beans.MrcEspecificacao;
import br.gov.inpi.marcas.beans.MrcEspecificacaoTexto;
import br.gov.inpi.marcas.beans.MrcFiguraProcess;
import br.gov.inpi.prosur.beans.NiceClassAux;
import br.gov.inpi.services.ProcessoMarcasAutomaticoInterface;

@Stateless(name = "ProcessoMarcasAutomaticoFacade")
public class ProcessoMarcasAutomaticoFacade implements ProcessoMarcasAutomaticoInterface{

	@PersistenceContext(unitName = "prosur_marcas")
	protected EntityManager entityManager;
	
	private static final Logger LOGGER = Logger.getLogger(ProcessoMarcasAutomaticoFacade.class);
		
	protected EntityManagerFactory entityManagerFactory;
	
	public ProcessoMarcasAutomaticoFacade(){}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void save(Object object) {
	
		if (object != null) {
			this.entityManager.persist(object);
			this.entityManager.flush();
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Object object) {
		
		this.entityManager.merge(object);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	public List<Object[]> listarPedidosMarcasRetroativo() {
		
		List<Object[]> processos = new ArrayList<>();

		try {

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT FIRST 5 mp.cd_process,  mp.no_process, mp.dt_deposit_process, ");
			strBuilder.append(" mp.dt_Publicacao, cp.nm_Complet_Pfpj, mp.nm_Marca_Process, ");
			strBuilder.append(" ma.ds_Apresen_Marca,mp.dt_Concess_Marca, mp.dt_Vigencia_Marca, ");
			strBuilder.append(" mp.dt_atualizacao_ipas, msp.cd_situaca_process,msp.st_Prosur ");
			strBuilder.append(" FROM  Mrc_Processo mp, Mrc_Situaca_Proces msp,  ");
			strBuilder.append(" outer(Crp_Pfpj cp), Mrc_Apresentacao ma ");
			//strBuilder.append(" Mrc_Apresentacao ma,  Mrc_Despacho md   ");
			//strBuilder.append(" WHERE mp.cd_Process = md.cd_Process     ");
			strBuilder.append(" WHERE mp.cd_Situaca_Process NOT IN ('0') ");
			strBuilder.append(" AND mp.cd_process > 500  ");
			strBuilder.append(" AND mp.cd_Situaca_Process = msp.cd_Situaca_Process ");
			strBuilder.append(" AND mp.cd_Pfpj_Titular = cp.cd_Pfpj   ");
			//strBuilder.append(" AND mp.cd_Apresen_Marca = mn.cd_Naturez_Marca   ");
			strBuilder.append(" AND mp.cd_Apresen_Marca = ma.cd_Apresen_Marca  ");
			//strBuilder.append(" AND mp.cd_Situaca_Process = msp.cd_Situaca_Process ");
			strBuilder.append(" AND mp.cd_process NOT IN (SELECT cd_process FROM mrc_processo_prosur) ");
			strBuilder.append(" ORDER BY mp.cd_process ");
			

			Query query = this.entityManager.createNativeQuery(strBuilder.toString());
			@SuppressWarnings("rawtypes")
			List listaAuxliar = query.getResultList();

			if (listaAuxliar != null && !listaAuxliar.isEmpty()) {
				processos.addAll(listaAuxliar);
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Sem Pedidos Retroativos ");
		}

		return processos;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String retornarVolumeTotalMarcasRetroativo(){
		
		String retorno = "";
		
		try {
			
			StringBuilder strBuilder = new StringBuilder();
			
			strBuilder.append("SELECT count(mp.cd_process) ");
			strBuilder.append("FROM Mrc_Processo mp, Mrc_Situaca_Proces msp, ");
			strBuilder.append("OUTER(Crp_Pfpj cp), Mrc_Apresentacao ma ");
			strBuilder.append("WHERE mp.cd_Situaca_Process NOT IN ('0') ");
			strBuilder.append("AND mp.cd_process > 500 ");
			strBuilder.append("AND mp.cd_Situaca_Process = msp.cd_Situaca_Process ");
			strBuilder.append("AND mp.cd_Pfpj_Titular = cp.cd_Pfpj ");
			strBuilder.append("AND mp.cd_Apresen_Marca = ma.cd_Apresen_Marca ");
			strBuilder.append("AND mp.cd_process NOT IN(SELECT cd_process FROM mrc_processo_prosur) ");
			
			Query query = this.entityManager.createNativeQuery(strBuilder.toString());
			retorno = query.getSingleResult().toString();
			
			LOGGER.info("********** Volume total marcas: " + retorno + " ********** ");
		}catch (NoResultException nre) {
			
			LOGGER.error(" Sem Pedidos Retroativos ");
		}
		
		return retorno;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listarPedidosMarcasSemanal() {
		
		List<Object[]> processos = new ArrayList<>();

		try {

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT FIRST 10 DISTINCT mp.cd_process,  mp.no_process, mp.dt_deposit_process, ");
			strBuilder.append(" mp.dt_Publicacao, cp.nm_Complet_Pfpj, mp.nm_Marca_Process, ");
			strBuilder.append(" ma.ds_Apresen_Marca,mp.dt_Concess_Marca, mp.dt_Vigencia_Marca, ");
			strBuilder.append(" mp.dt_atualizacao_ipas, msp.cd_situaca_process,msp.st_Prosur ");
			strBuilder.append(" FROM  Mrc_Processo mp, Mrc_Situaca_Proces msp, ");
			strBuilder.append(" outer(Crp_Pfpj cp), Mrc_Natureza mn, ");
			strBuilder.append(" Mrc_Apresentacao ma,  Mrc_Despacho md   ");
			strBuilder.append(" WHERE md.no_rpi = (SELECT MAX(no_rpi) FROM mrc_despacho) ");
			strBuilder.append(" AND mp.cd_Process = md.cd_Process ");
			strBuilder.append(" AND mp.cd_Situaca_Process NOT IN ('0') ");
			strBuilder.append(" AND mp.cd_Situaca_Process = msp.cd_Situaca_Process ");
			strBuilder.append(" AND mp.cd_Pfpj_Titular = cp.cd_Pfpj ");
			strBuilder.append(" AND mp.cd_Apresen_Marca = mn.cd_Naturez_Marca ");
			strBuilder.append(" AND mp.cd_Apresen_Marca = ma.cd_Apresen_Marca ");

			Query query = this.entityManager.createNativeQuery(strBuilder.toString());
			@SuppressWarnings("rawtypes")
			List listaAuxliar = query.getResultList();
			if (listaAuxliar != null && !listaAuxliar.isEmpty()) {
				processos.addAll(listaAuxliar);
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Nenhum pedido encontrado por RPI ");
		}

		return processos;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listarPedidosMarcasRetroativoPorProcesso(String processo) {

		List<Object[]> processos = new ArrayList<>();

		try {

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT DISTINCT mp.cd_process,  mp.no_process, mp.dt_deposit_process, ");
			strBuilder.append(" mp.dt_Publicacao, cp.nm_Complet_Pfpj, mp.nm_Marca_Process, ");
			strBuilder.append(" ma.ds_Apresen_Marca,mp.dt_Concess_Marca, mp.dt_Vigencia_Marca, ");
			strBuilder.append(" mp.dt_atualizacao_ipas, msp.cd_situaca_process,msp.st_Prosur ");
			strBuilder.append(" FROM  Mrc_Processo mp, Mrc_Situaca_Proces msp,  ");
			strBuilder.append(" outer(Crp_Pfpj cp), Mrc_Natureza mn, ");
			strBuilder.append(" Mrc_Apresentacao ma,  Mrc_Despacho md   ");
			strBuilder.append(" WHERE mp.cd_Process = md.cd_Process     ");
			strBuilder.append(" AND mp.cd_Situaca_Process = msp.cd_Situaca_Process ");
			strBuilder.append(" AND mp.cd_Pfpj_Titular = cp.cd_Pfpj   ");
			strBuilder.append(" AND mp.cd_Apresen_Marca = mn.cd_Naturez_Marca   ");
			strBuilder.append(" AND mp.cd_Apresen_Marca = ma.cd_Apresen_Marca  ");
			strBuilder.append(" AND mp.cd_Situaca_Process = msp.cd_Situaca_Process ");
			strBuilder.append(" AND mp.no_process IN ");
			strBuilder.append(processo);
			// strBuilder.append(" AND mp.cd_process NOT IN (SELECT cd_process FROM mrc_processo_prosur) ");

			Query query = this.entityManager.createNativeQuery(strBuilder.toString());
			@SuppressWarnings("rawtypes")
			List listaAuxliar = query.getResultList();

			if (listaAuxliar != null && !listaAuxliar.isEmpty()) {
				processos.addAll(listaAuxliar);
			}

		} catch (NoResultException nre) {
			LOGGER.error(" ERRO :: nenhum pedido encontrado ");
		}

		return processos;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public String buscaClasseNice(String codigoProcesso) {

		MrcClasNice niceClass = new MrcClasNice();
		String classe = null;

		try {

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT t2.no_process, t1.cd_process, t3.cd_classe ");
			strBuilder.append(" FROM mrc_especificacao t1, mrc_processo t2, mrc_clas_nice t3 ");
			strBuilder.append(" des,ptn_tipo_despacho tp ");
			strBuilder.append(" WHERE t1.cd_process = t2.cd_process ");
			strBuilder.append(" AND t1.cd_nice = t3.cd_nice");
			strBuilder.append(" AND t2.no_process = '" + codigoProcesso + "'");
			strBuilder.append(" ORDER BY t3.cd_classe ASC ");

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), MrcClasNice.class);
			niceClass = (MrcClasNice) query.getResultList();

			if (niceClass.getCdNice() != null) {

				classe = niceClass.getCdClasse();

			}

		} catch (NoResultException nre) {
			LOGGER.error(" Nenhuma classifica��o de Nice encontrada para o registro : " + codigoProcesso);
		}

		return classe;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	public List<NiceClassAux> buscaClasseNiceAuxPorId(Integer codigoProcesso) {

		List<NiceClassAux> marcasAux = null;

		try {
			StringBuilder str = new StringBuilder();
			str.append(" SELECT DISTINCT t1.cd_process, t2.no_process, t4.cd_classe  ");
			str.append(" FROM mrc_classif_marca t1, mrc_processo t2, mrc_relac_nice t3,mrc_clas_nice t4 ");
			str.append(" WHERE t1.cd_process = t2.cd_process ");
			str.append(" AND t1.cd_nacion_classe = t3.cd_nacion_classe ");
			str.append(" AND t1.cd_nacion_subclas = t3.cd_nacion_subclas ");
			str.append(" AND t3.cd_nice = t4.cd_nice ");
			str.append(" AND t2.cd_process = " + codigoProcesso);
			str.append(" ORDER BY cd_classe ASC ");

			Query query = this.entityManager.createNativeQuery(str.toString(), NiceClassAux.class);
			marcasAux = query.getResultList();

		} catch (NoResultException nre) {
			LOGGER.error(" Nenhuma classifica��o encontrada para o registro : " + codigoProcesso);
		}

		return marcasAux;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	@Override
	public List<MrcClasNice> pesquisarClasseNicePorId(int codigoProcesso) {

		List<MrcClasNice> classificacoes = new ArrayList<MrcClasNice>();

		try {

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT t3.cd_nice, t3.cd_classe, t3.ds_classe,");
			strBuilder.append(" t3.ds_nota, t3.no_revisao ");
			strBuilder.append(" FROM mrc_especificacao t1,mrc_processo t2, mrc_clas_nice t3");
			strBuilder.append(" WHERE t1.cd_process = t2.cd_process ");
			strBuilder.append(" AND t1.cd_nice = t3.cd_nice  AND t2.cd_process = :cdPedido ");

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), MrcClasNice.class);
			query.setParameter("cdPedido", codigoProcesso);
			classificacoes = query.getResultList();

		} catch (NoResultException nre) {
			LOGGER.error(" Pedido sem Classifica��o de Nice : " + codigoProcesso);
		}

		return classificacoes;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	@SuppressWarnings("unchecked")
	public List<MrcEspecificacao> pesquisarEspecificacaoPorId(int codigoProcesso) {

		List<MrcEspecificacao> espeficacoes = new ArrayList<MrcEspecificacao>();

		try {

			Query query = this.entityManager.createNamedQuery("MrcEspecificacao.findByCdProcess");
			query.setParameter("cdProcess", codigoProcesso);
			espeficacoes = (List<MrcEspecificacao>) query.getResultList();

		} catch (NoResultException nre) {
			LOGGER.error(" Pedido sem Especifica��o " + codigoProcesso);
		}
		return espeficacoes;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public MrcFiguraProcess pesquisarFiguraPorId(int codigoProcesso) {

		MrcFiguraProcess figura = new MrcFiguraProcess();
		try {

			Query query = this.entityManager.createNamedQuery("MrcFiguraProcess.findByCdProcess");
			query.setParameter("cdProcess", codigoProcesso);
			figura = (MrcFiguraProcess) query.getSingleResult();

		} catch (Throwable nre) {
			return null;
		}
		return figura;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public MrcFiguraProcess pesquisarFiguraPorNumeroProcesso(String numeroProcess) {

		MrcFiguraProcess figura = new MrcFiguraProcess();
		try {

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT fg.cd_process, fg.im_process_figura, fg.tipo_figura ");
			strBuilder.append(" FROM mrc_figura_process fg, Mrc_Processo mp  ");
			strBuilder.append(" WHERE mp.no_process = '" + numeroProcess + "'");
			strBuilder.append(" AND mp.cd_process = fg.cd_process ");

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), MrcFiguraProcess.class);
			figura = (MrcFiguraProcess) query.getSingleResult();

		} catch (NoResultException nre) {
			LOGGER.error(" Pedido sem Figura por n�mero de processo : " + numeroProcess);
		}
		return figura;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	@Override
	public List<MrcClassifVienna> pesquisarClassificacaoViennaPorId(int codigoProcesso) {

		List<MrcClassifVienna> classificacoes = new ArrayList<MrcClassifVienna>();
		try {

			Query query = this.entityManager.createNamedQuery("MrcClassifVienna.findByCdProcess");
			query.setParameter("cdProcess", codigoProcesso);
			classificacoes = query.getResultList();

		} catch (NoResultException nre) {
			LOGGER.error(" Pedido sem classifica��o de Viena : " + codigoProcesso);
		}
		return classificacoes;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	@Override
	public List<MrcEspecificacaoTexto> retornarEspecificacaoDoProcesso(int codigoProcesso) {

		List<MrcEspecificacaoTexto> especificacoes = new ArrayList<MrcEspecificacaoTexto>();
		try {

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT cd_especif, cd_process,tx_especif,cd_nice ");
			strBuilder.append(" FROM Mrc_Especificacao WHERE cd_Process = " + codigoProcesso);

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), MrcEspecificacaoTexto.class);
			especificacoes = (List<MrcEspecificacaoTexto>) query.getResultList();

		} catch (NoResultException nre) {
			LOGGER.error(" Pedido sem especifica��o do processo : " + codigoProcesso);
		}

		return especificacoes;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public String recuperarNumeroRPICorrente() {

		try {

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT MAX(no_rpi) FROM mrc_despacho ");

			Query query = this.entityManager.createNativeQuery(strBuilder.toString());

			return String.valueOf(query.getSingleResult());

		} catch (NoResultException nre) {
			LOGGER.error(" Nenhuma RPI encontrada");
		}

		return "";

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Boolean verificaProceso(String processo) {
	
		@SuppressWarnings("unused")
		String numProc = null;
		
		try{
			
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT no_process ");
			strBuilder.append(" FROM mrc_processo ss, mrc_processo_prosur pp");
			strBuilder.append(" WHERE pp.cd_process = ss.no_process");
			strBuilder.append(" AND ss.no_process = '"+processo+"'");
			Query query = this.entityManager.createNativeQuery(strBuilder.toString());
			numProc = (String) query.getSingleResult();
			
		}catch(NoResultException e){
			return false;
		}
		return true;
	}
}
