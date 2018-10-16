package br.gov.inpi.epec.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.gov.inpi.epec.facade.EstatisticaFacade;
import br.gov.inpi.epec.service.impl.helper.QueryAnterioridadesHelper;
import br.gov.inpi.epec.service.impl.helper.QueryLogsHelper;

@Stateless(name = "EstatisticaServiceImpl")
public class EstatisticaServiceImpl implements EstatisticaFacade {
	private static final Logger LOGGER = Logger.getLogger(EstatisticaServiceImpl.class);

	@PersistenceContext(unitName = "epec_mysql")
	protected EntityManager entityManager;

	public int countEntidades() {
		int count = 0;
		count = ((Number) this.entityManager.createNamedQuery("Tbcadentidade.countAll").getSingleResult()).intValue();
		return count;
	}

	public int countFamilias() {
		int count = ((Number) this.entityManager.createNamedQuery("Tbfamiliaec.countAll").getSingleResult()).intValue();
		return count;
	}

	public int countUsuarios(Long idEntidade) {
		int count = 0;
		if (idEntidade == null) {
			count = ((Number) this.entityManager.createNamedQuery("Tbcadusuario.countAll").getSingleResult()).intValue();
		} else {
			Query query = this.entityManager.createNamedQuery("Tbcadusuario.countAllByIdEntidadeEc");
			query.setParameter("idEntidadeEc", idEntidade);
			count = ((Number) query.getSingleResult()).intValue();
		}
		return count;
	}

	public int countPedidosPatentes(Long idEntidade) {
		int count = 0;
		if (idEntidade == null) {
			count = ((Number) this.entityManager.createNamedQuery("Tbpatenteec.countAll").getSingleResult()).intValue();
		} else {
			Query query = this.entityManager.createNamedQuery("Tbpatenteec.countAllByIdEntidadeEc");
			query.setParameter("idEntidadeEc", idEntidade);
			count = ((Number) query.getSingleResult()).intValue();
		}
		return count;
	}

	public int countPrioridades(Long idEntidade) {
		int count = 0;
		if (idEntidade == null) {
			count = ((Number) this.entityManager.createNamedQuery("Tbprioridadeec.countAll").getSingleResult()).intValue();
		} else {
			Query query = this.entityManager.createNamedQuery("Tbprioridadeec.countAllByIdPatenteEc");
			query.setParameter("idEntidadeEc", idEntidade);
			count = ((Number) query.getSingleResult()).intValue();
		}
		return count;
	}

	public int countRelatoriosTecnicos(Long idEntidade) {
		int count = 0;
		if (idEntidade == null) {
			count = ((Number) this.entityManager.createNamedQuery("Tbrelatorioec.countAll").getSingleResult()).intValue();
		} else {
			Query query = this.entityManager.createNamedQuery("Tbrelatorioec.countAllByIdPatenteEc");
			query.setParameter("idEntidadeEc", idEntidade);
			count = ((Number) query.getSingleResult()).intValue();
		}
		return count;
	}

	public int countComentarios(Long idEntidade) {
		int count = 0;
		if (idEntidade == null) {
			count = ((Number) this.entityManager.createNamedQuery("Tbcomentariosfamiliaec.countAll").getSingleResult()).intValue();
		} else {
			Query query = this.entityManager.createNamedQuery("Tbcomentariosfamiliaec.countAllByIdPatenteEc");
			query.setParameter("idEntidadeEc", idEntidade);
			count = ((Number) query.getSingleResult()).intValue();
		}
		return count;
	}

	public int countAnterioridadesPatentarias(Long idEntidade) {
		int count = 0;

		if (idEntidade == null) {
			count = ((Number) this.entityManager.createNamedQuery("Tbantpatentaria.countAll").getSingleResult()).intValue();
			count += ((Number) this.entityManager.createNamedQuery("Tbantpatentariacatrelec.countAll").getSingleResult()).intValue();
			count += ((Number) this.entityManager.createNamedQuery("Tbantpatentariarelec.countAll").getSingleResult()).intValue();
		} else {
			Query query1 = this.entityManager.createNativeQuery(QueryAnterioridadesHelper.query1("TBANTPATENTARIA", idEntidade));
			Query query2 = this.entityManager.createNativeQuery(QueryAnterioridadesHelper.query2("TBANTPATENTARIACATRELEC", idEntidade));
			Query query3 = this.entityManager.createNativeQuery(QueryAnterioridadesHelper.query3("TBANTPATENTARIARELEC", idEntidade));

			count = ((Number) query1.getSingleResult()).intValue();
			count += ((Number) query2.getSingleResult()).intValue();
			count += ((Number) query3.getSingleResult()).intValue();
		}
		return count;
	}

	public int countAnterioridadesNaoPatentarias(Long idEntidade) {
		int count = 0;

		if (idEntidade == null) {
			count = ((Number) this.entityManager.createNamedQuery("Tbantnaopatentaria.countAll").getSingleResult()).intValue();
			count += ((Number) this.entityManager.createNamedQuery("Tbantnaopatentariacatrelec.countAll").getSingleResult()).intValue();
			count += ((Number) this.entityManager.createNamedQuery("Tbantnaopatentariarelec.countAll").getSingleResult()).intValue();
		} else {
			Query query1 = this.entityManager.createNativeQuery(QueryAnterioridadesHelper.query1("TBANTNAOPATENTARIA", idEntidade));
			Query query2 = this.entityManager.createNativeQuery(QueryAnterioridadesHelper.query2("TBANTNAOPATENTARIACATRELEC", idEntidade));
			Query query3 = this.entityManager.createNativeQuery(QueryAnterioridadesHelper.query3("TBANTNAOPATENTARIARELEC", idEntidade));

			count = ((Number) query1.getSingleResult()).intValue();
			count += ((Number) query2.getSingleResult()).intValue();
			count += ((Number) query3.getSingleResult()).intValue();
		}

		return count;
	}

	public int countLogs(Long idEntidade) {
		int count = 0;
		if (idEntidade == null) {
			count = ((Number) this.entityManager.createNamedQuery("Tblogfamilia.countAll").getSingleResult()).intValue();
			count += ((Number) this.entityManager.createNamedQuery("Tblogpatente.countAll").getSingleResult()).intValue();
			count += ((Number) this.entityManager.createNamedQuery("Tblogrelatorio.countAll").getSingleResult()).intValue();
		} else {
			Query query1 = this.entityManager.createNativeQuery(QueryLogsHelper.logFamilia(idEntidade));
			Query query2 = this.entityManager.createNativeQuery(QueryLogsHelper.logPatentes(idEntidade));
			Query query3 = this.entityManager.createNativeQuery(QueryLogsHelper.logRelatorio(idEntidade));

			count = ((Number) query1.getSingleResult()).intValue();
			count += ((Number) query2.getSingleResult()).intValue();
			count += ((Number) query3.getSingleResult()).intValue();
		}
		return count;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Object> listAll() {
		Query query = this.entityManager.createNamedQuery("Tbcadentidade.findAll");

		return query.getResultList();
	}
}
