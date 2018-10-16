package br.gov.inpi.epec.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

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
import br.gov.inpi.epec.facade.EpecServiceFacade;

@Stateless(name = "EpecServiceImpl")
public class EpecServiceImpl implements EpecServiceFacade {

	private static final Logger LOGGER = Logger.getLogger(EpecServiceImpl.class);

	@PersistenceContext(unitName = "epec_mysql")
	protected EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void save(Object object) {
		
		System.out.println(object);
		try{
			if (object != null) {
				this.entityManager.persist(object);
				this.entityManager.flush();
			}
			
	} catch (Exception e) {
		System.out.println(e);
	}

	

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Object merge(Object object) {
		return this.entityManager.merge(object);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void apagar(Tbcadentidade tbcadentidade) {
		this.entityManager.remove(entityManager.getReference(Tbcadentidade.class, tbcadentidade.getIdEntidadeEc()));

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void apagarPais(Tbcadpais tbcadpais) {
		this.entityManager.remove(entityManager.getReference(Tbcadpais.class, tbcadpais.getIdPais()));

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void delete(Object object) {
		// this.entityManager.remove(object);
		this.entityManager.remove(entityManager.contains(object) ? object : entityManager.merge(object));

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteTbno(Tbnos tbnos) {
		this.entityManager.remove(entityManager.getReference(Tbnos.class, tbnos.getIdNo()));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteClassificacaoPatente(Tbclassificacaopatente tbclassificacaopatente) {
		this.entityManager.remove(entityManager.getReference(Tbclassificacaopatente.class, tbclassificacaopatente.getTbclassificacaopatentePK()));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteClassificacao(Tbclassificacao tbclassificacao) {
		this.entityManager.remove(entityManager.getReference(Tbclassificacao.class, tbclassificacao.getIdClassificacao()));

		// String query =
		// ("DELETE FROM TBCLASSIFICACAO WHERE ID_CLASSIFICACAO = " +
		// tbclassificacao.getIdClassificacao());
		// Query q = entityManager.createNativeQuery(query,
		// Tbclassificacao.class);
		// q.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteDepositantePatente(Tbdepositantepatente tbdepositantepatente) {
		this.entityManager.remove(entityManager.getReference(Tbdepositantepatente.class, tbdepositantepatente));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteInventorPatente(Tbinventorpatente tbinventorpatente) {
		this.entityManager.remove(entityManager.getReference(Tbinventorpatente.class, tbinventorpatente));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteInventor(Tbinventor tbinventor) {
		this.entityManager.remove(entityManager.getReference(Tbinventor.class, tbinventor.getIdInventor()));

		// String query = ("DELETE FROM TBINVENTOR WHERE ID_INVENTOR = " +
		// tbinventor.getIdInventor());
		// Query q = entityManager.createNativeQuery(query, Tbinventor.class);
		// q.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteInventorPatenteTodos(Long id) {
		String query = ("DELETE FROM TBINVENTORPATENTE WHERE ID_PATENTE_EC=" + id);
		Query q = entityManager.createNativeQuery(query, Tbinventorpatente.class);
		q.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteDepositantePatenteTodos(Long id) {
		String query = ("DELETE FROM TBDEPOSITANTEPATENTE WHERE ID_PATENTE_EC=" + id);
		Query q = entityManager.createNativeQuery(query, Tbdepositantepatente.class);
		q.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deletePrioridade(Tbprioridadeec tbprioridadeec) {

		String query = ("DELETE FROM TBPRIORIDADEEC WHERE ID_PRIORIDADE_EC=" + tbprioridadeec.getIdPrioridadeEc());
		Query q = entityManager.createNativeQuery(query, Tbprioridadeec.class);
		q.executeUpdate();

		// this.entityManager.remove(entityManager.getReference(Tbprioridadeec.class,
		// tbprioridadeec.getIdPrioridadeEc()));
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Object> listAll(String namedQuery) {

		Query query = this.entityManager.createNamedQuery(namedQuery);

		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object findOneResult(String namedQuery, HashMap mapParameters) {
		Object result = null;

		try {
			Query query = entityManager.createNamedQuery(namedQuery);

			if (!mapParameters.isEmpty()) {
				Set<String> chaves = mapParameters.keySet();
				for (String chave : chaves) {
					if (chave != null)
						query.setParameter(chave, mapParameters.get(chave));
				}
			}

			result = (Object) query.getSingleResult();

		} catch (NoResultException e) {

		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object> list(String namedQuery, HashMap mapParameters) {

		Query query = null;

		try {
			query = entityManager.createNamedQuery(namedQuery);

			if (!mapParameters.isEmpty()) {
				Set<String> chaves = mapParameters.keySet();
				for (String chave : chaves) {
					if (chave != null)
						query.setParameter(chave, mapParameters.get(chave));
				}
			}

			query.getResultList();

		} catch (NoResultException e) {

		}
		return query.getResultList();
	}

	public Tbcadpais findPaisPorNome(String nome) {

		Tbcadpais tbPais = null;

		Query query = this.entityManager.createNamedQuery("Tbcadpais.findByStrNomePais");
		query.setParameter("strNomePais", nome);

		tbPais = (Tbcadpais) query.getSingleResult();

		return tbPais;
	}

	// Query utilizada Crud Usu�rio
	@SuppressWarnings("unchecked")
	public List<Tbcadusuario> findUserPorNome(String nome) {

		List<Tbcadusuario> user = null;

		Query query = this.entityManager.createNamedQuery("Tbcadusuario.findByStrUsuario");
		query.setParameter("strUsuario", nome);

		user = ((List<Tbcadusuario>) query.getResultList());

		return user;
	}

	@SuppressWarnings("unchecked")
	public List<Tbcadtipoanterioridade> listarNaoPatentarias() {

		List<Tbcadtipoanterioridade> ant = null;

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("SELECT * FROM TBCADTIPOANTERIORIDADE an ");
		strBuilder.append(" WHERE an.ID_ANTERIORIDADENAOPATENTARIA = 0");

		try {
			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbcadtipoanterioridade.class);

			ant = ((List<Tbcadtipoanterioridade>) query.getResultList());
		} catch (Exception ignore) {
		}

		return ant;
	}

	// Query utilizada em ConsultaPedido
	public Tbinventor findInventorPorNome(String nome) {

		Tbinventor tbInventor = null;

		try {
			
			Query query = this.entityManager.createNamedQuery("Tbinventor.findByTxInventor");
			query.setParameter("txInventor", nome);
			
			tbInventor = (Tbinventor) query.getSingleResult();

		} catch (Exception ignore) {
		}

		return tbInventor;
	}

	public Tbcadusuario findUsuarioPorNome(String nome) {

		Tbcadusuario tbcadusuario = null;

		Query query = this.entityManager.createNamedQuery("Tbcadusuario.findByStrUsuario");
		query.setParameter("strUsuario", nome);

		try {

			tbcadusuario = (Tbcadusuario) query.getSingleResult();

		} catch (Exception ignore) {
		}

		return tbcadusuario;
	}

	// Query utilizada em ConsultaPedido
	public Tbclassificacao findClassificacaoPorNome(String nome) {

		Tbclassificacao tbClassificacao = null;

		Query query = this.entityManager.createNamedQuery("Tbclassificacao.findByTxClassificacao");
		query.setParameter("txClassificacao", nome);

		try {

			tbClassificacao = (Tbclassificacao) query.getSingleResult();

		} catch (Exception ignore) {
		}

		return tbClassificacao;
	}

	// Query utilizada em ConsultaPedido
	public Tbdepositante findDepositantePorNome(String nome) {

		Tbdepositante tbDepositante = null;

		Query query = this.entityManager.createNamedQuery 	
("Tbdepositante.findByTxDepositante");
		query.setParameter("txDepositante", nome);

		try {

			tbDepositante = (Tbdepositante) query.getSingleResult();

		} catch (Exception ignore) {
		}

		return tbDepositante;
	}

	// Query utilizada em ConsultaPedido
	public Tbdepositantepatente findDepositantePorId(Long id) {

		Tbdepositantepatente tbdepositantepatente = null;

		Query query = this.entityManager.createNamedQuery("Tbdepositantepatente.findByIdDepositantePatente_2");
		query.setParameter("idPatenteEc", id);

		try {

			tbdepositantepatente = (Tbdepositantepatente) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

		return tbdepositantepatente;
	}

	// Query utilizada em ConsultaPedido
	public Tbinventorpatente findInventorPorId(Tbinventorpatente tbinventorpatente) {

		Tbinventorpatente tbinventorpatenteAux = null;

		Query query = this.entityManager.createNamedQuery("Tbinventorpatente.findByIdInventorPatente_3");
		query.setParameter("idPatenteEc", tbinventorpatente.getIdPatenteEc());
		query.setParameter("idInventor", tbinventorpatente.getIdInventor());

		try {
			tbinventorpatenteAux = (Tbinventorpatente) query.getSingleResult();

		} catch (Exception e) {
			return null;
		}
		return tbinventorpatenteAux;
	}

	// Query utilizada em ConsultaPedido
	public Tbclassificacaopatente findClassificacaoPorId(TbclassificacaopatentePK tbclassificacaopatentePK) {

		Tbclassificacaopatente tbclassificacaopatente = null;

		Query query = this.entityManager.createNamedQuery("Tbclassificacaopatente.findByClaasificacaoandPatente");
		query.setParameter("idPatenteEc", tbclassificacaopatentePK.getIdPatenteEc());
		query.setParameter("idClassificacao", tbclassificacaopatentePK.getIdClassificacao());

		try {

			tbclassificacaopatente = (Tbclassificacaopatente) query.getSingleResult();

		} catch (Exception e) {
			return null;
		}

		return tbclassificacaopatente;
	}

	// Query utilizada em ConsultaPedido
	public Tbcadentidade findEntidadePorIdPais(Long idPais, Tbcadusuario tbcadusuario) {

		System.out.println(idPais);
		Tbcadentidade entidade = null;

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("SELECT * FROM  TBCADENTIDADE en ");
		strBuilder.append(" WHERE en.ID_PAIS = " + idPais + " and en.B_OFICINA = true");

		Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbcadentidade.class);

		entidade = (Tbcadentidade) query.getResultList().get(0);

		return entidade;
	}

	// Query utilizada em Cad. Ativo
	public Tbcadativo findCadAtivoPorNome(String nome) {

		Tbcadativo cadAtivo = null;

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("SELECT * FROM  TBCADATIVO en ");
		strBuilder.append(" WHERE en.STR_ATIVO like'" + nome + "%'");

		Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbcadativo.class);
		cadAtivo = (Tbcadativo) query.getSingleResult();

		return cadAtivo;
	}

	// Query utilizada em ConsultaPedido
	@SuppressWarnings("unchecked")
	public List<Tbpatenteec> buscaFamiliaPatente(Long idFamilia) {

		List<Tbpatenteec> familias = null;

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("SELECT * FROM  TBPATENTEEC pt ");
		strBuilder.append(" WHERE pt.ID_FAMILIA_EC = " + idFamilia + "");

		try {

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbpatenteec.class);
			familias = ((List<Tbpatenteec>) query.getResultList());

		} catch (Exception e) {
		}

		return familias;
	}

	// Query utilizada em ConsultaPedido
	@SuppressWarnings("unchecked")
	public List<Tbpatenteec> buscaPatentes(String idPatente) {

		List<Tbpatenteec> patentes = null;

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("SELECT * FROM  TBPATENTEEC pt ");
		strBuilder.append(" WHERE pt.STR_PED_PAIS_DOCDB like'%" + idPatente + "%'");
		strBuilder.append(" OR pt.STR_PED_NUM_DOCDB like'%" + idPatente + "%'");
		strBuilder.append(" OR pt.STR_PED_NUM_EPODOC like'%" + idPatente + "%'");
		strBuilder.append(" OR pt.STR_PED_NUM_ORIGINAL like'%" + idPatente + "%'");
		strBuilder.append(" OR pt.STR_PUB_NUM_DOCDB like'%" + idPatente + "%'");

		try {

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbpatenteec.class);
			patentes = ((List<Tbpatenteec>) query.getResultList());

		} catch (Exception e) {
		}

		return patentes;
	}
	
	// Query utilizada em ConsultaPedido
		@SuppressWarnings("unchecked")
		public List<Tbpatenteec> buscaPatentesPorPedido(String idPatente) {

			List<Tbpatenteec> patentes = null;

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("SELECT * FROM  TBPATENTEEC pt ");			
			strBuilder.append(" WHERE pt.STR_PED_NUM_EPODOC like'%" + idPatente + "%'");	

			try {

				Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbpatenteec.class);
				patentes = ((List<Tbpatenteec>) query.getResultList());

			} catch (Exception e) {
			}

			return patentes;
		}
		
		
		// Query utilizada em ConsultaPedido
		@SuppressWarnings("unchecked")
		public List<Tbpatenteec> buscaPatentesPorPublicacao(String idPatente, String pais) {

			List<Tbpatenteec> patentes = null;

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("SELECT * FROM  TBPATENTEEC pt ");			
			strBuilder.append(" WHERE pt.STR_PUB_NUM_DOCDB like'%" + idPatente + "%' and pt.STR_PUB_PAIS_DOCDB ='"+ pais+"'");	

			try {

				Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbpatenteec.class);
				patentes = ((List<Tbpatenteec>) query.getResultList());

			} catch (Exception e) {
			}

			return patentes;
		}

	// Query utilizada em ConsultaPedido
	@SuppressWarnings("unchecked")
	public List<Tbpatenteec> buscaPatentesPrioridade(String idPatente) {

		List<Tbpatenteec> patentes = null;

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("SELECT * FROM  TBPATENTEEC pt, TBPRIORIDADEEC pri");
		strBuilder.append(" WHERE  pt.ID_PATENTE_EC = pri.ID_PATENTE_EC and pri.STR_PRIORIDADE like'%" + idPatente + "%'");

		try {

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbpatenteec.class);
			patentes = ((List<Tbpatenteec>) query.getResultList());

		} catch (Exception e) {
		}

		return patentes;
	}

	public void update(Object object) {

		merge(object);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Tbrelatorioec> relatorioFamilia(String namedQuery, HashMap mapParameters) {

		Query query = null;

		try {
			query = entityManager.createNamedQuery(namedQuery);

			if (!mapParameters.isEmpty()) {
				Set<String> chaves = mapParameters.keySet();
				for (String chave : chaves) {
					if (chave != null)
						query.setParameter(chave, mapParameters.get(chave));
				}
			}

			query.getResultList();

		} catch (NoResultException e) {

		}
		return query.getResultList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Tbpatenteec findPatentePorDocDb(String numeroDocDb) {

		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPubNumDocdb");
			query.setParameter("strPubNumDocdb", numeroDocDb);
			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Patente não encontrada DocDb :: " + numeroDocDb);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numeroDocDb);
		}
		return null;
	}

	// Original -------
	@Override
	public Tbpatenteec findPatentePorNumeroOriginal(String numeroOriginal) {

		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPedNumOriginal");
			query.setParameter("strPedNumOriginal", numeroOriginal);
			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Patente não encontrada EpoDoc :: " + numeroOriginal);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numeroOriginal);
		}
		return null;
	}

	@Override
	public Tbpatenteec findPatentePorNumeroOriginalEEntidade(String numeroOriginal, String entidade) {

		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPedNumOriginalAndEntidade");
			query.setParameter("strPedNumOriginal", numeroOriginal);
			query.setParameter("txOrganizacao", entidade);

			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Patente não encontrada EpoDoc :: " + numeroOriginal);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numeroOriginal);
		}
		return null;
	}

	// EpoDoc -----
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Tbpatenteec findPatentePorAplicacaoEpoDoc(String numeroEpoDoc) {

		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPedNumEpodoc");
			query.setParameter("strPedNumEpodoc", numeroEpoDoc);
			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Patente não encontrada EpoDoc :: " + numeroEpoDoc);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numeroEpoDoc);
		}
		return null;

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Tbpatenteec findPatentePorAplicacaoEpoDocEEntidade(String numeroEpoDoc, String entidade) {

		try {

			Tbpatenteec tbPatenteec = null;

			Query query = null;

			if (entidade.equalsIgnoreCase("Sistema_ePec")) {
				query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPedNumEpodoc");

			} else {
				query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPedNumEpodocAndEntidade");
				query.setParameter("txOrganizacao", entidade);
			}
			query.setParameter("strPedNumEpodoc", numeroEpoDoc);

			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Patente não encontrada EpoDoc :: " + numeroEpoDoc);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numeroEpoDoc);
		}
		return null;

	}

	@Override
	public Tbpatenteec findPatentePorPublicacaoEpoDoc(String numeroDocDb, String pais) {

		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPublicacaoPedidoEpoDoc");
			query.setParameter("strPubPaisDocdb", pais);
			query.setParameter("strPubNumDocdb", numeroDocDb);
			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Patente não encontrada EpoDoc :: " + pais + numeroDocDb);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + pais + numeroDocDb);
		}
		return null;
	}

	@Override
	public Tbpatenteec findPatentePorPublicacaoEpoDocEEntidade(String numeroDocDb, String pais, String entidade) {

		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPublicacaoPedidoEpoDocAndEntidade");
			query.setParameter("strPubPaisDocdb", pais);
			query.setParameter("strPubNumDocdb", numeroDocDb);
			query.setParameter("txOrganizacao", entidade);
			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Patente não encontrada EpoDoc :: " + pais + numeroDocDb);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + pais + numeroDocDb);
		}
		return null;
	}

	// DocDb ---------

	@Override
	public Tbpatenteec findPatentePorPublicacaoDocDb(String numeroDocDb, String pais, String kindCode) {

		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPublicacaoPedidoDocDb");
			query.setParameter("strPubPaisDocdb", pais);
			query.setParameter("strPubNumDocdb", numeroDocDb);
			query.setParameter("strPubKindDocdb", kindCode);

			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Patente não encontrada DocDb :: " + pais + numeroDocDb + kindCode);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + pais + numeroDocDb + kindCode);
		}
		return null;
	}

	@Override
	public Tbpatenteec findPatentePorPublicacaoDocDbEEntidade(String numeroDocDb, String pais, String kindCode, String entidade) {

		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPublicacaoPedidoDocDbAndEntidade");
			query.setParameter("strPubPaisDocdb", pais);
			query.setParameter("strPubNumDocdb", numeroDocDb);
			query.setParameter("strPubKindDocdb", kindCode);
			query.setParameter("txOrganizacao", entidade);

			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Patente não encontrada DocDb :: " + pais + numeroDocDb + kindCode);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + pais + numeroDocDb + kindCode);
		}
		return null;
	}

	@Override
	public Tbpatenteec findPatentePorAplicacaoDocDb(String numeroDocDb, String pais, String kindCode) {

		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrAplicacaoPedidoDocDb");
			query.setParameter("strPedPaisDocdb", pais);
			query.setParameter("strPedNumDocdb", numeroDocDb);
			query.setParameter("strPedKindDocdb", kindCode);

			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Patente não encontrada DocDb :: " + pais + numeroDocDb + kindCode);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + pais + numeroDocDb + kindCode);
		}
		return null;
	}

	@Override
	public Tbpatenteec findPatentePorAplicacaoDocDbEEntidade(String numeroDocDb, String pais, String kindCode, String entidade) {

		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrAplicacaoPedidoDocDbAndEntidade");
			query.setParameter("strPedPaisDocdb", pais);
			query.setParameter("strPedNumDocdb", numeroDocDb);
			query.setParameter("strPedKindDocdb", kindCode);
			query.setParameter("txOrganizacao", entidade);

			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Patente não encontrada DocDb :: " + pais + numeroDocDb + kindCode);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + pais + numeroDocDb + kindCode);
		}
		return null;
	}

	// -------------------------------------------------------------------------------------------------
	// Familia
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------

	@Override
	public Tbfamiliaec findIdFamiliaPatentePorEpoDoc(String numeroEpoDoc) {

		try {

			Tbfamiliaec tbfamiliaec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findIdFamilyByStrNumEpodoc");
			query.setParameter("strPedNumEpodoc", numeroEpoDoc);
			tbfamiliaec = (Tbfamiliaec) query.getSingleResult();
			if (tbfamiliaec != null) {
				return tbfamiliaec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Familia não encontrada EpoDoc :: " + numeroEpoDoc);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numeroEpoDoc);
		}
		return null;
	}

	@Override
	public Tbfamiliaec findIdFamiliaPatentePublicacaoPorEpodoc(String numero, String pais) {

		try {

			Tbfamiliaec tbfamiliaec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findIdFamilyByPublicacaoEpoDoc");// estranho
			query.setParameter("strPubNumDocdb", numero);
			query.setParameter("strPubPaisDocdb", pais);
			tbfamiliaec = (Tbfamiliaec) query.getSingleResult();
			if (tbfamiliaec != null) {
				return tbfamiliaec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Familia não encontrada EpoDoc :: " + pais + numero);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + pais + numero);
		}
		return null;
	}

	@Override
	public Tbfamiliaec findIdFamiliaAplicacaoPatentePorDocDb(String numero, String pais, String kindCode) {

		try {

			Tbfamiliaec tbfamiliaec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrAplicacaoFamiliaDocDb");
			query.setParameter("strPedPaisDocdb", pais);
			query.setParameter("strPedNumDocdb", numero);
			query.setParameter("strPedKindDocdb", kindCode);

			tbfamiliaec = (Tbfamiliaec) query.getSingleResult();
			if (tbfamiliaec != null) {
				return tbfamiliaec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Familia não encontrada EpoDoc :: " + pais + numero);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + pais + numero);
		}
		return null;

	}

	@Override
	public Tbfamiliaec findIdFamiliaPatenteAplicacaoPorEpodoc(String numeroDocDb) {

		try {

			Tbfamiliaec tbfamiliaec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findIdFamilyByStrNumDocDb");
			query.setParameter("strPubNumDocdb", numeroDocDb);
			tbfamiliaec = (Tbfamiliaec) query.getSingleResult();
			if (tbfamiliaec != null) {
				return tbfamiliaec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Familia não encontrada DocDb :: " + numeroDocDb);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numeroDocDb);
		}
		return null;

	}

	@Override
	public Tbfamiliaec findIdFamiliaPatenteNumeroOriginal(String numeroOriginal) {

		try {

			Tbfamiliaec tbfamiliaec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findIdFamilyByStrNumOriginal");
			query.setParameter("strPedNumOriginal", numeroOriginal);
			tbfamiliaec = (Tbfamiliaec) query.getSingleResult();
			if (tbfamiliaec != null) {
				return tbfamiliaec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Familia não encontrada DocDb :: " + numeroOriginal);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numeroOriginal);
		}
		return null;
	}

	@Override
	public Tbfamiliaec findIdFamiliaPublicacaoPatentePorDocDb(String numero, String pais, String kindCode) {

		try {

			Tbfamiliaec tbfamiliaec = null;

			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPublicacaoFamiliaDocDb");
			query.setParameter("strPubPaisDocdb", pais);
			query.setParameter("strPubNumDocdb", numero);
			query.setParameter("strPubKindDocdb", kindCode);

			tbfamiliaec = (Tbfamiliaec) query.getSingleResult();
			if (tbfamiliaec != null) {
				return tbfamiliaec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Familia não encontrada DocDb :: " + pais + numero + kindCode);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + pais + numero + kindCode);
		}
		return null;

	}

	@Override
	public Tbfamiliaec findIdFamiliaPatentePorDocDb(String numeroDocDb) {

		try {

			Tbfamiliaec tbfamiliaec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findIdFamilyByStrNumDocDb");
			query.setParameter("strPubNumDocdb", numeroDocDb);
			tbfamiliaec = (Tbfamiliaec) query.getSingleResult();
			if (tbfamiliaec != null) {
				return tbfamiliaec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Familia não encontrada DocDb :: " + numeroDocDb);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numeroDocDb);
		}
		return null;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Tbpatenteec findPatentePorEpoDoc(String numeroEpoDoc) {

		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPedNumEpodoc");
			query.setParameter("strPedNumEpodoc", numeroEpoDoc);
			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Patente não encontrada EpoDoc :: " + numeroEpoDoc);
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numeroEpoDoc);
		}
		return null;

	}

	// Consulta utilizada em busca Pedidos / publicação / prioridade
	public Tbpatenteec buscaPatentePorEpodoc(String pedido) {

		Tbpatenteec tbPatenteec = null;

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("SELECT * FROM  TBPATENTEEC pt ");
		strBuilder.append(" WHERE pt.STR_PED_NUM_EPODOC like'%" + pedido + "%'");

		try {

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbpatenteec.class);
			tbPatenteec = (Tbpatenteec) query.getSingleResult();

		} catch (Exception e) {
		}

		return tbPatenteec;
	}
	
	
	// Consulta utilizada em busca Pedidos / publicação / prioridade
	public Tbpatenteec buscaPatentePorPublicacao(String pedido) {

		Tbpatenteec tbPatenteec = null;

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("SELECT * FROM  TBPATENTEEC pt ");
		strBuilder.append(" WHERE pt.STR_PUB_NUM_DOCDB like'%" + pedido + "%'");

		try {

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbpatenteec.class);
			tbPatenteec = (Tbpatenteec) query.getSingleResult();

		} catch (Exception e) {
		}

		return tbPatenteec;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Tbcomentariosfamiliaec> comentariosFamilia(Tbfamiliaec tbfamiliaec) {
		try {

			List<Tbcomentariosfamiliaec> cometarios = null;
			Query query = this.entityManager.createNamedQuery("Tbcomentariosfamiliaec.findByIdComentariosfamiliaEc");
			query.setParameter("idComentariosfamiliaEc", tbfamiliaec);
			cometarios = query.getResultList();

			return cometarios;

		} catch (NoResultException nre) {
			LOGGER.error(" Patente comentario encontrada");
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: ");
		}
		return null;
	}

	@Override
	public Tbrelatorioec findPorPedido(String pedido) {

		try {

			Tbrelatorioec tbrelatorioec = null;
			Query query = this.entityManager.createNamedQuery("Tbrelatorioec.findByStrRelatorio");
			query.setParameter("strRelatorio", pedido);
			tbrelatorioec = (Tbrelatorioec) query.getSingleResult();

			if (tbrelatorioec != null) {
				return tbrelatorioec;
			}

		} catch (NoResultException nre) {
			// LOGGER.error(" Relatorio não encontrado. ");
		} catch (NonUniqueResultException nou) {
			// LOGGER.error(" Mais de um pedido encontrado :: " + pedido);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Tbcatrelatorioec> findCatRelatorio(Long id) {

		List<Tbcatrelatorioec> catRel = new ArrayList<Tbcatrelatorioec>();

		try {

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT ID_CATEGORIA, ID_RELATORIO_EC,TX_RESUMO,ID_CATRELATORIOEC ");
			strBuilder.append(" FROM TBCATRELATORIOEC WHERE ID_RELATORIO_EC =" + id);

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbcatrelatorioec.class);
			catRel = query.getResultList();
			if (catRel == null) {
				return null;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Categorias do relatorio n�o encontrados. ");
		}

		return catRel;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaPatentariaEntity> findAnterioridadesPatentariaCategoriaRelatorioPorIdCat(long id, long idCategoria) {

		List<CategoriaPatentariaEntity> documentos = new ArrayList<CategoriaPatentariaEntity>();

		try {

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT CAT.ID_CATEGORIA,RELEC.ID_ANTPATENTARIACATRELEC, REL.STR_RELATORIO, TIPO.STR_OMPI, ");
			strBuilder.append(" TIPO.STR_PORTUGUES, RELEC.TX_RELACAO, RELEC.TX_REIVINDICACAO, RELEC.STR_ANTPATENTARIA ");
			strBuilder.append(" FROM TBRELATORIOEC REL, TBANTPATENTARIACATRELEC RELEC, ");
			strBuilder.append(" TBCADTIPOANTERIORIDADE TIPO, TBCATRELATORIOEC CAT ");
			strBuilder.append(" WHERE REL.ID_RELATORIO_EC = :id ");
			strBuilder.append(" AND REL.ID_RELATORIO_EC = CAT.ID_RELATORIO_EC  ");
			strBuilder.append(" AND CAT.ID_CATRELATORIOEC = RELEC.ID_CATRELATORIOEC ");
			strBuilder.append(" AND RELEC.ID_CADTIPOANTERIORIDADE = TIPO.ID_CADTIPOANTERIORIDADE ");
			strBuilder.append(" AND CAT.ID_CATEGORIA = :idCategoria ");
			strBuilder.append(" ORDER BY ID_CATEGORIA,  RELEC.ID_ANTPATENTARIACATRELEC   ASC ");

			LOGGER.info(" >> SQL :: " + strBuilder.toString() + " - " + id + "- " + idCategoria);

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), CategoriaPatentariaEntity.class);
			query.setParameter("id", id);
			query.setParameter("idCategoria", idCategoria);

			documentos = (List<CategoriaPatentariaEntity>) query.getResultList();
			if (documentos == null) {
				return null;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Categorias  Anterioridades Patentarias do relatorio n�o encontrados. ");
		}

		return documentos;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaNaoPatentetariaEntity> findAnterioridadesNaoPatentariaCategoriaRelatorioPorIdCat(long id, long idCategoria) {

		List<CategoriaNaoPatentetariaEntity> documentos = new ArrayList<CategoriaNaoPatentetariaEntity>();

		try {

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT  CAT.ID_CATEGORIA,RELEC.ID_ANTNAOPATENTARIACATRELEC, REL.STR_RELATORIO, TIPO.STR_OMPI, ");
			strBuilder.append(" TIPO.STR_PORTUGUES, RELEC.TX_RELACAO, RELEC.TX_REIVINDICACAO, RELEC.TX_TITULO ");
			strBuilder.append(" FROM TBRELATORIOEC REL, TBANTNAOPATENTARIACATRELEC RELEC, ");
			strBuilder.append(" TBCADTIPOANTERIORIDADE TIPO, TBCATRELATORIOEC CAT ");
			strBuilder.append(" WHERE REL.ID_RELATORIO_EC = :id ");
			strBuilder.append(" AND REL.ID_RELATORIO_EC = CAT.ID_RELATORIO_EC  ");
			strBuilder.append(" AND CAT.ID_CATRELATORIOEC = RELEC.ID_CATRELATORIOEC ");
			strBuilder.append(" AND RELEC.ID_CADTIPOANTERIORIDADE = TIPO.ID_CADTIPOANTERIORIDADE ");
			strBuilder.append(" AND CAT.ID_CATEGORIA = :idCategoria ");
			strBuilder.append(" ORDER BY ID_CATEGORIA,RELEC.ID_ANTNAOPATENTARIACATRELEC ASC ");

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), CategoriaNaoPatentetariaEntity.class);
			query.setParameter("id", id);
			query.setParameter("idCategoria", idCategoria);

			documentos = (List<CategoriaNaoPatentetariaEntity>) query.getResultList();
			if (documentos == null) {
				return null;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Categorias  Anterioridades N�o Patentarias do relatorio n�o encontrados. ");
		}

		return documentos;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaRelatorioEntity> findCaracteristicasRelatorioPorId(long id, long idCategoria) {

		List<CategoriaRelatorioEntity> cats = new ArrayList<CategoriaRelatorioEntity>();

		try {

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT CAT.ID_CATEGORIA, CAR.ID_CARACCATRELATORIO, CAR.TX_CARACTERISTICA ");
			strBuilder.append(" FROM TBCARACCATRELATORIOEC CAR,TBCATRELATORIOEC CAT ");
			strBuilder.append(" WHERE CAT.ID_RELATORIO_EC = :id ");
			strBuilder.append(" AND CAT.ID_CATRELATORIOEC = CAR.ID_CATRELATORIOEC ");
			strBuilder.append(" AND CAT.ID_CATEGORIA = :idCategoria ");

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), CategoriaRelatorioEntity.class);
			query.setParameter("id", id);
			query.setParameter("idCategoria", idCategoria);

			cats = (List<CategoriaRelatorioEntity>) query.getResultList();
			if (cats == null) {
				return null;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Categorias  Anterioridades N�o Patentarias do relatorio n�o encontrados. ");
		}
		return cats;
	}

	@Override
	public Tbcaraccatrelatorioec findCaracteristicaRelatorioPorIdCaracteristica(long id) {

		Tbcaraccatrelatorioec tb = new Tbcaraccatrelatorioec();
		try {

			Query query = this.entityManager.createNamedQuery("Tbcaraccatrelatorioec.findByIdCaraccatrelatorio");
			query.setParameter("idCaraccatrelatorio", id);
			tb = (Tbcaraccatrelatorioec) query.getSingleResult();
			if (tb == null) {
				return null;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Categorias  Anterioridades N�o Patentarias do relatorio n�o encontrados. ");
		}
		return tb;
	}

	@Override
	public Tbrelatoriopatente findRelatorioTecnicoPorIdPatente(long id) {

		try {

			Tbrelatoriopatente tbrelatoriopatente = null;
			Query query = this.entityManager.createNamedQuery("Tbrelatoriopatente.findByIdRelatorioPatente_3");
			query.setParameter("idPatente", id);
			tbrelatoriopatente = (Tbrelatoriopatente) query.setMaxResults(1).getSingleResult();

			if (tbrelatoriopatente != null) {
				return tbrelatoriopatente;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Categorias  Anterioridades Não Patentarias do relatorio não encontrados. ");
		}

		return null;
	}

	@Override
	public Tbrelatorioec findRelatorioTecnicoPorId(long idRelatorio) {

		try {

			Tbrelatorioec tbrelatorioec = null;
			Query query = this.entityManager.createNamedQuery("Tbrelatorioec.findByIdRelatorioEc");
			query.setParameter("idRelatorioEc", idRelatorio);
			tbrelatorioec = (Tbrelatorioec) query.getSingleResult();

			if (tbrelatorioec != null) {
				return tbrelatorioec;
			}

		} catch (NoResultException nre) {
			LOGGER.error(" Relatorio não encontrado. ");
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + idRelatorio);
		}
		return null;
	}

	@Override
	public void deleteColaboracao(Tbrelatoriocolaboracao tbrelatoriocolaboracao) {

		this.entityManager.remove(entityManager.getReference(Tbrelatoriocolaboracao.class, tbrelatoriocolaboracao.getColaboracaoPK()));

	}

	@Override
	public void deleteAntPatentaria(Tbantpatentariarelec tbantpatentariarelec) {

		this.entityManager.remove(entityManager.getReference(Tbantpatentariarelec.class, tbantpatentariarelec.getIdAntpatentariarelEc()));

	}

	public void deleteAntNaoPatentaria(Tbantnaopatentariarelec tbantnaopatentariarelec) {

		this.entityManager.remove(entityManager.getReference(Tbantnaopatentariarelec.class, tbantnaopatentariarelec.getIdAntnaopatentariarelec()));

	}

	public Tbcadcategoria bucarPorId(Integer id) {
		try {

			Tbcadcategoria tbcadcategoria = null;
			Query query = this.entityManager.createNamedQuery("Tbcadcategoria.findByIdCategoria");
			query.setParameter("idCategoria", id);
			tbcadcategoria = (Tbcadcategoria) query.getSingleResult();

			return tbcadcategoria;
		} catch (NoResultException nre) {
			LOGGER.error(" Relatorio não encontrado. ");
		} catch (NonUniqueResultException nou) {
		}
		return null;
	}

	@Override
	public Tbcadtipoanterioridade buscaPorIdTipoAnterioridade(Integer id) {
		try {

			Tbcadtipoanterioridade tbcadtipoanterioridade = null;
			Query query = this.entityManager.createNamedQuery("Tbcadtipoanterioridade.findByIdCadtipoanterioridade");
			query.setParameter("idCadtipoanterioridade", id);
			tbcadtipoanterioridade = (Tbcadtipoanterioridade) query.getSingleResult();

			return tbcadtipoanterioridade;
		} catch (NoResultException nre) {
			LOGGER.error(" Relatorio não encontrado. ");
		} catch (NonUniqueResultException nou) {
		}
		return null;
	}

	@Override
	public void deleteCategoria(Tbcatrelatorioec tbcatrelatorioec) {

		this.entityManager.remove(entityManager.getReference(Tbcatrelatorioec.class, tbcatrelatorioec.getIdCatrelatorioec()));
	}

	@Override
	public Tbfilhos bucarFilhosPorId(Long id) {

		Tbfilhos filho = null;

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("SELECT * FROM  TBFILHOS en ");
		strBuilder.append(" WHERE en.ID_FILHO = " + id + "");

		Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbfilhos.class);
		filho = (Tbfilhos) query.getSingleResult();

		return filho;

	}

	public void deleteFilhos(Tbfilhos tbfilhos) {
		this.entityManager.remove(entityManager.getReference(Tbfilhos.class, tbfilhos.getIdPai()));

	}

	@Override
	public void deleteClausulaTipo(Tbclausulatipo tbClausula) {
		this.entityManager.remove(entityManager.getReference(Tbclausulatipo.class, tbClausula.getIdClausulaTipo()));
	}

	public void deleteAntPatentariaCat(Tbantpatentariacatrelec tbantpatentariacatrelec) {
		this.entityManager.remove(entityManager.getReference(Tbantpatentariacatrelec.class, tbantpatentariacatrelec.getIdAntPatentariaCatRelec()));

	}

	@Override
	public void deleteAntNaoPatentariaCat(Tbantnaopatentariacatrelec tbantnaopatentariacatrelec) {
		this.entityManager.remove(entityManager.getReference(Tbantnaopatentariacatrelec.class, tbantnaopatentariacatrelec.getIdAntnaopatentariacatrelec()));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tbnos> findNosPorIdAtivo(long idAtivo) {

		List<Tbnos> nos = null;

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("SELECT pn.ID_NO, pn.TX_TITULO_PORTUGUES, pn.TX_TITULO_INGLES, pn.TX_TITULO_ESPANHOL, pn.ID_CLAUSULA_TIPO FROM  TBNOS pn, TBCLAUSULATIPO pc  ");
		strBuilder.append(" WHERE pn.ID_CLAUSULA_TIPO = pc.ID_CLAUSULA_TIPO");
		strBuilder.append(" AND pc.ID_ATIVO ='" + idAtivo + "'");

		try {

			Query query = this.entityManager.createNativeQuery(strBuilder.toString());
			nos = (List<Tbnos>) query.getResultList();

		} catch (Exception e) {
		}

		return nos;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Tbcadusuario findUsuarioPorLogin(String login) {

		Tbcadusuario usuario = null;
		try {

			Query query = this.entityManager.createNamedQuery("Tbcadusuario.findByStrUsuario");
			query.setParameter("strUsuario", login);
			usuario = (Tbcadusuario) query.getSingleResult();

		} catch (NoResultException nre) {
			LOGGER.error(" Usu�rio n�o encontrado");
		}
		return usuario;
	}

	public List<Tbclausulatipo> findClausulaTipoPorIdAtivo(long idAtivo) {

		List<Tbclausulatipo> clausula = null;

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("SELECT * FROM TBCLAUSULATIPO pc  ");
		// strBuilder.append(" WHERE pc.ID_ATIVO ='" + idAtivo + "'");

		try {

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbclausulatipo.class);
			clausula = (List<Tbclausulatipo>) query.getResultList();

		} catch (Exception e) {
		}

		return clausula;
	}

	@Override
	public void deleteCaracteristica(Tbcaraccatrelatorioec tbcaraccatrelatorioec) {
		this.entityManager.remove(entityManager.getReference(Tbcaraccatrelatorioec.class, tbcaraccatrelatorioec.getIdCaraccatrelatorio()));

	}

	@Override
	public void deleteAntPatentariaCarac(Tbantpatentaria tbantpatentaria) {
		this.entityManager.remove(entityManager.getReference(Tbantpatentaria.class, tbantpatentaria.getIdAntpatentaria()));
	}

	@Override
	public Tbcadativo findCadAtivoPorId(long idAtivo) {

		Tbcadativo cadativo = null;

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("SELECT * FROM  TBCADATIVO en ");
		strBuilder.append(" WHERE en.ID_ATIVO = " + idAtivo + "");

		Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbcadativo.class);
		cadativo = (Tbcadativo) query.getSingleResult();

		return cadativo;
	}

	@Override
	public List<Tbcadusuario> findUserPorIdEntidade(Long idEntidade) {

		List<Tbcadusuario> user = null;

		Query query = this.entityManager.createNamedQuery("Tbcadusuario.findByIdEntidade");
		query.setParameter("idEntidadeEc", idEntidade);
		user = ((List<Tbcadusuario>) query.getResultList());

		return user;
	}

	@Override
	public void deleteAntNaoPatentariaCarac(Tbantnaopatentaria tbantnaopatentaria) {
		this.entityManager.remove(entityManager.getReference(Tbantnaopatentaria.class, tbantnaopatentaria.getIdAntnaopatentaria()));

	}

	@Override
	public Tbcadentidade pesquisarOrganizacaoPorId(Long id) {

		Tbcadentidade tbCadentidade = null;

		try {

			Query query = this.entityManager.createNamedQuery("Tbcadentidade.findByIdEntidadeEc");
			query.setParameter("idEntidadeEc", id);
			tbCadentidade = (Tbcadentidade) query.getSingleResult();

		} catch (NoResultException nre) {
			LOGGER.error(" Organiza��o n�o encontrada " + id);
		}

		return tbCadentidade;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HistoricodeAcessoEntity> findAllHistorico() {

		List<HistoricodeAcessoEntity> acessos = new ArrayList<HistoricodeAcessoEntity>();

		try {

			StringBuilder strBuilder = new StringBuilder();

			strBuilder.append("SELECT LOG.STR_USUARIO, PAIS.STR_NOME_PAIS, ENT.TX_ORGANIZACAO, USR.B_LOGADO, LOG.DT_REGISTRO,LOG.STR_IP");
			strBuilder.append(" FROM TBLOGLOGIN AS LOG INNER JOIN TBCADUSUARIO AS USR ON LOG.STR_USUARIO = USR.STR_USUARIO");
			strBuilder.append(" INNER JOIN TBCADENTIDADE AS ENT ON USR.ID_ENTIDADE_EC = ENT.ID_ENTIDADE_EC");
			strBuilder.append(" INNER JOIN TBCADPAIS AS PAIS ON ENT.ID_PAIS = PAIS.ID_PAIS");
			strBuilder.append(" ORDER BY DT_REGISTRO DESC");

			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), HistoricodeAcessoEntity.class);

			acessos = (List<HistoricodeAcessoEntity>) query.getResultList();
			if (acessos == null) {
				return null;
			}

		} catch (NoResultException nre) {
			LOGGER.error("Não há registro de acessos.");
		}

		return acessos;

	}

	@Override
	public List<Tbcadusuario> findUserPorIdPais(Long pais) {
		List<Tbcadusuario> user = null;

		Query query = this.entityManager.createNamedQuery("Tbcadusuario.findByIdPais");
		query.setParameter("idPais", pais);
		user = ((List<Tbcadusuario>) query.getResultList());

		return user;
	}

	@Override
	public void deleteColaboracao(Tbcadcolaboracao tbcadcolaboracao) {
		this.entityManager.remove(entityManager.getReference(Tbcadcolaboracao.class, tbcadcolaboracao.getIdColaboracao()));

	}

	@SuppressWarnings("unchecked")
	@Override
	public Tbnos buscarRaiz(Long idEntidade) {
		Tbnos no = new Tbnos();
		List<Tbnos> nos = new ArrayList<>();

		try {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("select * from TBNOS nos where ");
			strBuilder.append("nos.ID_ENTIDADE_EC ='" + idEntidade + "'");
			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), Tbnos.class);

			nos = ((List<Tbnos>) query.getResultList());

		} catch (Exception e) {
			LOGGER.error("Não há registro de acessos.");
		}

		return no;
	}

	@Override
	public Tbdepositante obterDepositante(String texto) {
		try {

			Tbdepositante tbdepositante = null;
			Query query = this.entityManager.createNamedQuery("Tbdepositante.findByTxDepositante");
			query.setParameter("txDepositante", texto);
			tbdepositante = (Tbdepositante) query.getSingleResult();
			if (tbdepositante != null) {
				return tbdepositante;
			}

		} catch (NoResultException nre) {

		} catch (NonUniqueResultException nou) {

		}
		return null;
	}

	@Override
	public Tbpatenteec findPatentePublicacao(String numero) {
		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPubNumDocdb");
			query.setParameter("strPubNumDocdb", numero);
			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numero);
		} catch (NoResultException nre) {

		 }
		return null;
	}

	@Override
	public Tbpatenteec findPatentePorPedido(String numero) {
		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPedNumDocdb");
			query.setParameter("strPedNumDocdb", numero);
			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}

		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numero);
		} catch (NoResultException nre) {

		 }
		return null;
	}

	@Override
	public Tbpatenteec findPatentePorOriginal(String numero) {
		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPedNumOriginal");
			query.setParameter("strPedNumOriginal", numero);
			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}


		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numero);
		}catch (NoResultException nre) {

		 }
		return null;
	}

	@Override
	public Tbpatenteec findPatentePorEpodoc(String numero) {
		try {

			Tbpatenteec tbPatenteec = null;
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.findByStrPedNumEpodoc");
			query.setParameter("strPedNumEpodoc", numero);
			tbPatenteec = (Tbpatenteec) query.getSingleResult();
			if (tbPatenteec != null) {
				return tbPatenteec;
			}
		} catch (NonUniqueResultException nou) {
			LOGGER.error(" Mais de um pedido encontrado :: " + numero);
		}		catch (NoResultException nre) {

		 }
		return null;
	}

}
