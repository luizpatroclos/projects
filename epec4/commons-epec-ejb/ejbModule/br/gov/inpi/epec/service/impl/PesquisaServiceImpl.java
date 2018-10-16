package br.gov.inpi.epec.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import br.gov.inpi.epec.EpecXmlWriter;
import br.gov.inpi.epec.beans.CategoriaNaoPatentetariaEntity;
import br.gov.inpi.epec.beans.CategoriaPatentariaEntity;
import br.gov.inpi.epec.beans.CategoriaRelatorioEntity;
import br.gov.inpi.epec.beans.Tbantnaopatentaria;
import br.gov.inpi.epec.beans.Tbantpatentaria;
import br.gov.inpi.epec.beans.Tbcaraccatrelatorioec;
import br.gov.inpi.epec.beans.Tbcatrelatorioec;
import br.gov.inpi.epec.beans.Tbfamiliaec;
import br.gov.inpi.epec.beans.Tbpatenteec;
import br.gov.inpi.epec.beans.Tbrelatorioec;
import br.gov.inpi.epec.beans.Tbrelatoriopatente;
import br.gov.inpi.epec.entities.Caracteristica;
import br.gov.inpi.epec.entities.CategoriaRelatorio;
import br.gov.inpi.epec.entities.Familia;
import br.gov.inpi.epec.entities.Patente;
import br.gov.inpi.epec.entities.RelatorioPatente;
import br.gov.inpi.epec.entities.RelatorioTecnico;
import br.gov.inpi.epec.facade.EpecServiceFacade;
import br.gov.inpi.epec.facade.PesquisaServiceFacade;
import br.gov.inpi.epec.service.impl.helper.PatenteHelper;
import br.gov.inpi.epec.service.impl.helper.RelatorioTecnicoHelper;
import br.gov.inpi.epec.writer.EquivalentsIquiryXmlWriter;
import br.gov.inpi.epec.writer.ReportDataXmlWriter;
import br.gov.inpi.epec.xml.bibliographicdata.BibliographicData;
import br.gov.inpi.epec.xml.family.EquivalentsInquiry;
import br.gov.inpi.epec.xml.report.ReportApplicationReference;

/***
 * 
 * Implementação da interface PesquisaService
 * 
 * @author allanlq
 *
 */
@Stateless(name = "PesquisaServiceImpl")
public class PesquisaServiceImpl implements PesquisaServiceFacade {

	private static final Logger LOGGER = Logger.getLogger(PesquisaServiceImpl.class);

	private RelatorioTecnicoHelper helper;

	@PersistenceContext(unitName = "epec_mysql")
	protected EntityManager entityManager;

	@EJB
	private EpecServiceFacade epecServiceFacade;

	private String tipoPesquisa;

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	// Dados Bibliograficos
	// ----------------------------------------------------------------
	@Override
	public BibliographicData pesquisarPatentePorPublicacaoNumeroDocDb(String numeroPedido) {

		BibliographicData biblio = null;

		try {

			if (numeroPedido != null && !"".equals(numeroPedido.trim())) {

				biblio = this.pesquisarPublicacaoPorNumeroDocDbConverterXml(numeroPedido);

			}

		} catch (Exception e) {
			LOGGER.error(" >> ERRO pesquisarPatentePorNumeroDocDb :: " + e.getLocalizedMessage());
		}

		return biblio;
	}

	private BibliographicData pesquisarPublicacaoPorNumeroDocDbConverterXml(String numeroPedido) {

		BibliographicData biblio = null;
		Tbpatenteec tbPatenteec = this.pesquisarPublicacaoPorNumeroDocDb(numeroPedido);
		if (tbPatenteec != null) {
			biblio = this.converterXmlBibliographicData(tbPatenteec, "docdb");
		}
		return biblio;

	}

	/**
	 * 
	 * Pesquisa documentos de Patente pelo seu numero docdb.
	 * 
	 * @param numeroPedido
	 * 
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private Tbpatenteec pesquisarPublicacaoPorNumeroDocDb(String numeroPedido) {

		int total = numeroPedido.length();
		String pais = numeroPedido.substring(0, 2);
		String numeroDocDb = this.retornarPedido(numeroPedido.substring(2, total));
		String kindCode = this.retornarKindCode(numeroPedido);

		Tbpatenteec tbPatenteec = this.epecServiceFacade.findPatentePorPublicacaoDocDb(numeroDocDb, pais, kindCode);
		return tbPatenteec;
	}

	@Override
	public BibliographicData pesquisarPatentePorAplicacaoNumeroDocDb(String numeroPedido) {

		BibliographicData biblio = null;

		try {

			if (numeroPedido != null && !"".equals(numeroPedido.trim())) {
				biblio = this.pesquisarAplicacaoPorDocDbConveterXml(numeroPedido);
			}

		} catch (Exception e) {
			LOGGER.error(" >> ERRO pesquisarPatentePorNumeroDocDb :: " + e.getLocalizedMessage());
		}
		return biblio;
	}

	private BibliographicData pesquisarAplicacaoPorDocDbConveterXml(String numeroPedido) {

		BibliographicData biblio = null;
		Tbpatenteec tbPatenteec = this.pesquisarAplicacaoPorDocDb(numeroPedido);
		if (tbPatenteec != null) {
			biblio = this.converterXmlBibliographicData(tbPatenteec, "docdb");
		}
		return biblio;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private Tbpatenteec pesquisarAplicacaoPorDocDb(String numeroPedido) {

		int total = numeroPedido.length();
		String pais = numeroPedido.substring(0, 2);
		String numeroDocDb = this.retornarPedido(numeroPedido.substring(2, total));
		String kindCode = this.retornarKindCode(numeroPedido);

		Tbpatenteec tbPatenteec = this.epecServiceFacade.findPatentePorAplicacaoDocDb(numeroDocDb, pais, kindCode);
		return tbPatenteec;

	}

	@Override
	public BibliographicData pesquisarAplicacaoPatentePorNumeroEpoDoc(String numeroEpoDoc) {

		BibliographicData biblio = null;
		try {

			if (numeroEpoDoc != null && !"".equals(numeroEpoDoc.trim())) {
				biblio = this.pesquisarAplicacaoPorEpoDocConverterXml(numeroEpoDoc);
			}

		} catch (Exception e) {
			LOGGER.error(" >> ERRO pesquisarPatentePorNumeroDocDb :: " + e.getLocalizedMessage());
		}
		return biblio;
	}

	private BibliographicData pesquisarAplicacaoPorEpoDocConverterXml(String numeroEpoDoc) {

		BibliographicData biblio = null;
		Tbpatenteec tbPatenteec = this.pesquisarAplicacaoPorEpoDoc(numeroEpoDoc);
		if (tbPatenteec != null) {
			biblio = this.converterXmlBibliographicData(tbPatenteec, "epodoc");
		}
		return biblio;

	}

	/**
	 * 
	 * M�todo respons�vel em pesquisar base de dados patente de aplica��o pelo
	 * n�mero EpoDoc.
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private Tbpatenteec pesquisarAplicacaoPorEpoDoc(String numeroEpoDoc) {
		Tbpatenteec tbPatenteec = this.epecServiceFacade.findPatentePorAplicacaoEpoDoc(numeroEpoDoc);
		return tbPatenteec;
	}

	@Override
	public BibliographicData pesquisarAplicacaoPatentePorNumeroEpoDocEEntidadeConverterXml(String numeroEpoDoc, String entidade) {

		BibliographicData biblio = null;
		try {

			Tbpatenteec tbPatenteec = this.pesquisarAplicacaoPatentePorNumeroEpoDocEEntidade(numeroEpoDoc, entidade);
			if (tbPatenteec != null) {
				biblio = this.converterXmlBibliographicData(tbPatenteec, "epodoc");
			}

		} catch (Exception e) {
			LOGGER.error(" >> ERRO pesquisarPatentePorNumeroDocDb :: " + e.getLocalizedMessage());
		}
		return biblio;
	}

	/***
	 * 
	 * M�todo respons�vel em pesquisar uma patente por n�mero de EpoDoc e para
	 * sua respectiva entidade.
	 * 
	 * @param numeroEpoDoc
	 * @param entidade
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private Tbpatenteec pesquisarAplicacaoPatentePorNumeroEpoDocEEntidade(String numeroEpoDoc, String entidade) {
		Tbpatenteec tbPatenteec = this.epecServiceFacade.findPatentePorAplicacaoEpoDocEEntidade(numeroEpoDoc, entidade);
		return tbPatenteec;
	}

	@Override
	public BibliographicData pesquisarPublicacaoPatentePorNumeroEpoDoc(String numeroPedido) {

		BibliographicData biblio = null;
		try {

			if (numeroPedido != null && !"".equals(numeroPedido)) {
				biblio = this.pesquisarPublicacaoPatenteEpoDocConveterXml(numeroPedido);
			}

		} catch (Exception e) {
			LOGGER.error(" >> ERRO pesquisarPatentePorNumeroDocDb :: " + e.getLocalizedMessage());
		}
		return biblio;

	}

	private BibliographicData pesquisarPublicacaoPatenteEpoDocConveterXml(String numeroPedido) {

		BibliographicData biblio = null;
		Tbpatenteec tbPatenteec = this.pesquisarPublicacaoPatenteEpoDoc(numeroPedido);
		if (tbPatenteec != null) {
			biblio = this.converterXmlBibliographicData(tbPatenteec, "epodoc");
		}
		return biblio;

	}

	/***
	 * 
	 * M�todo respons�vel em pesquisar publica��o de patente pelo epo doc
	 * 
	 * @param numeroPedido
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private Tbpatenteec pesquisarPublicacaoPatenteEpoDoc(String numeroPedido) {

		String pais = numeroPedido.substring(0, 2);
		String numeroEpoDoc = numeroPedido.substring(2, numeroPedido.trim().length());

		Tbpatenteec tbPatenteec = this.epecServiceFacade.findPatentePorPublicacaoEpoDoc(numeroEpoDoc, pais);
		return tbPatenteec;

	}

	@Override
	public BibliographicData pesquisarPatentePorNumeroOriginal(String numeroOriginal) {

		BibliographicData biblio = null;
		try {

			if (numeroOriginal != null && !"".equals(numeroOriginal.trim())) {
				biblio = this.pesquisarPeloNumeroOriginalConverterXml(numeroOriginal);
			}

		} catch (Exception e) {
			LOGGER.error(" >> ERRO pesquisarPatentePorNumeroDocDb :: " + e.getLocalizedMessage());
		}
		return biblio;
	}

	private BibliographicData pesquisarPeloNumeroOriginalConverterXml(String numeroOriginal) {

		BibliographicData biblio = null;
		Tbpatenteec tbPatenteec = this.pesquisarPeloNumeroOriginal(numeroOriginal);
		if (tbPatenteec != null) {
			biblio = this.converterXmlBibliographicData(tbPatenteec, "original");
		}
		return biblio;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private Tbpatenteec pesquisarPeloNumeroOriginal(String numeroOriginal) {
		Tbpatenteec tbPatenteec = this.epecServiceFacade.findPatentePorNumeroOriginal(numeroOriginal);
		return tbPatenteec;

	}

	/**
	 * 
	 * Converte para xml os dados bibliogr�ficos de uma patente.
	 * 
	 * @param tbPatenteec
	 * @param docType
	 * @return
	 */
	private BibliographicData converterXmlBibliographicData(Tbpatenteec tbPatenteec, String docType) {

		BibliographicData biblio = new BibliographicData();
		Patente patente = this.obterDadosBibliograficos(tbPatenteec, docType);
		if (patente != null) {

			EpecXmlWriter epecXmlWriter = new EpecXmlWriter();
			biblio = epecXmlWriter.converterPatenteParaXml(patente, docType);
			epecXmlWriter = null;

		}

		return biblio;

	}

	/***
	 * 
	 * Converte a entidade Tbpatenteec e uma classe POJO do tipo Patente com
	 * seus respectivos dados bibliográficos.
	 * 
	 * @param tbPatenteec
	 * @param docType
	 * @return
	 */
	private Patente obterDadosBibliograficos(Tbpatenteec tbPatenteec, String docType) {

		Patente patente = null;
		if (tbPatenteec != null) {

			PatenteHelper patenteHelper = new PatenteHelper();
			patente = patenteHelper.obterDadosPatente(tbPatenteec, docType);
			patenteHelper = null;

		}
		return patente;
	}

	// Fim Dados Bibliograficos
	// --------------------------------------------------------------------

	// Familia
	// -------------------------------------------------------------------------------------
	@Override
	public EquivalentsInquiry pesquisarFamiliaAplicacaoPorNumeroEpoDoc(String numeroEpoDoc) {

		EquivalentsInquiry equivalentsInquiry = null;
		try {

			if (numeroEpoDoc != null && !"".equals(numeroEpoDoc.trim())) {
				equivalentsInquiry = this.pesquisarFamiliaPorAplicacaoNumeroEpoDocConverterXml(numeroEpoDoc);
			}

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarFamiliaPorNumeroEpoDoc :: " + e.getMessage());
		}

		return equivalentsInquiry;

	}

	private EquivalentsInquiry pesquisarFamiliaPorAplicacaoNumeroEpoDocConverterXml(String numeroEpoDoc) {

		EquivalentsInquiry equivalentsInquiry = null;
		Tbfamiliaec tbfamiliaec = this.pesquisarFamiliaPorAplicacaoNumeroEpoDoc(numeroEpoDoc);
		if (tbfamiliaec != null) {

			List<Familia> documentos = this.retornarFamiliasDocumento(tbfamiliaec, "epodoc");
			if (documentos != null && documentos.size() >= 1) {
				this.setTipoPesquisa("application");
				equivalentsInquiry = this.converterFamiliaParaXml(documentos, numeroEpoDoc, "epodoc");
			}

		}
		return equivalentsInquiry;
	}

	/***
	 * 
	 * M�todo respons�vel em pesquisar Familia por aplica��o e Numero EpoDoc
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private Tbfamiliaec pesquisarFamiliaPorAplicacaoNumeroEpoDoc(String numeroEpoDoc) {
		Tbfamiliaec tbfamiliaec = this.epecServiceFacade.findIdFamiliaPatentePorEpoDoc(numeroEpoDoc);
		return tbfamiliaec;
	}

	@Override
	public EquivalentsInquiry pesquisarFamiliaPublicacaoPorNumeroEpoDoc(String numeroEpoDoc) {

		EquivalentsInquiry equivalentsInquiry = null;
		try {

			if (numeroEpoDoc != null && !"".equals(numeroEpoDoc.trim())) {

				String pais = numeroEpoDoc.substring(0, 2);
				String numeroEpoDocAux = numeroEpoDoc.substring(2, numeroEpoDoc.trim().length());
				equivalentsInquiry = this.pesquisarFamiliaPorPublicacaoEpoDocConverterXml(numeroEpoDocAux, pais);

			}

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarFamiliaPorNumeroEpoDoc :: " + e.getMessage());
		}

		return equivalentsInquiry;

	}

	private EquivalentsInquiry pesquisarFamiliaPorPublicacaoEpoDocConverterXml(String numeroEpoDoc, String pais) {

		EquivalentsInquiry equivalentsInquiry = null;
		Tbfamiliaec tbfamiliaec = this.pesquisarFamiliaPorPublicacaoEpoDoc(numeroEpoDoc, pais);
		if (tbfamiliaec != null) {

			List<Familia> documentos = this.retornarFamiliasDocumento(tbfamiliaec, "epodoc");
			if (documentos != null && documentos.size() >= 1) {

				this.setTipoPesquisa("publication");
				equivalentsInquiry = this.converterFamiliaParaXml(documentos, pais + numeroEpoDoc, "epodoc");

			}

		}
		return equivalentsInquiry;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private Tbfamiliaec pesquisarFamiliaPorPublicacaoEpoDoc(String numeroEpoDoc, String pais) {
		Tbfamiliaec tbfamiliaec = this.epecServiceFacade.findIdFamiliaPatentePublicacaoPorEpodoc(numeroEpoDoc, pais);
		return tbfamiliaec;

	}

	@Override
	public EquivalentsInquiry pesquisarFamiliaAplicacaoDocDb(String numeroPedido) {

		EquivalentsInquiry equivalentsInquiry = null;
		try {

			if (numeroPedido != null && !"".equals(numeroPedido.trim())) {

				int total = numeroPedido.length();
				String pais = numeroPedido.substring(0, 2);
				String numero = this.retornarPedido(numeroPedido.substring(2, total));
				String kindCode = this.retornarKindCode(numeroPedido);

				equivalentsInquiry = this.pesquisarFamiliaPorAplicacaoDocDbConverterXml(numero, pais, kindCode);
			}

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarFamiliaPorNumeroEpoDoc :: " + e.getMessage());
		}

		return equivalentsInquiry;

	}

	private EquivalentsInquiry pesquisarFamiliaPorAplicacaoDocDbConverterXml(String numeroDocDb, String pais, String kindCode) {

		EquivalentsInquiry equivalentsInquiry = null;

		Tbfamiliaec tbfamiliaec = this.pesquisarFamiliaPorAplicacaoDocDb(numeroDocDb, pais, kindCode);
		if (tbfamiliaec != null) {

			String parametro = "docdb";
			List<Familia> documentos = this.retornarFamiliasDocumento(tbfamiliaec, parametro);
			if (documentos != null && documentos.size() >= 1) {
				this.setTipoPesquisa("application");
				equivalentsInquiry = this.converterFamiliaParaXml(documentos, numeroDocDb, parametro);
			}

		}
		return equivalentsInquiry;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private Tbfamiliaec pesquisarFamiliaPorAplicacaoDocDb(String numeroDocDb, String pais, String kindCode) {
		Tbfamiliaec tbfamiliaec = this.epecServiceFacade.findIdFamiliaAplicacaoPatentePorDocDb(numeroDocDb, pais, kindCode);
		return tbfamiliaec;
	}

	@Override
	public EquivalentsInquiry pesquisarFamiliaNumeroOriginal(String numeroOriginal) {

		EquivalentsInquiry equivalentsInquiry = null;
		try {

			if (numeroOriginal != null && !"".equals(numeroOriginal.trim())) {
				equivalentsInquiry = this.pesquisarFamiliaPatenteNumeroOriginalConverterXml(numeroOriginal);
			}

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarFamiliaPorNumeroEpoDoc :: " + e.getMessage());
		}
		return equivalentsInquiry;
	}

	private EquivalentsInquiry pesquisarFamiliaPatenteNumeroOriginalConverterXml(String numeroOriginal) {

		EquivalentsInquiry equivalentsInquiry = null;
		Tbfamiliaec tbfamiliaec = this.pesquisarFamiliaPatenteNumeroOriginal(numeroOriginal);
		if (tbfamiliaec != null) {

			equivalentsInquiry = new EquivalentsInquiry();
			String parametro = "original";
			List<Familia> documentos = this.retornarFamiliasDocumento(tbfamiliaec, parametro);
			if (documentos != null && documentos.size() >= 1) {
				equivalentsInquiry = this.converterFamiliaParaXml(documentos, numeroOriginal, parametro);
			}

		}

		return equivalentsInquiry;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private Tbfamiliaec pesquisarFamiliaPatenteNumeroOriginal(String numeroOriginal) {
		Tbfamiliaec tbfamiliaec = this.epecServiceFacade.findIdFamiliaPatenteNumeroOriginal(numeroOriginal);
		return tbfamiliaec;

	}

	@Override
	public EquivalentsInquiry pesquisarFamiliaPublicationDocDb(String numeroPedido) {

		EquivalentsInquiry equivalentsInquiry = null;
		try {

			if (numeroPedido != null && !"".equals(numeroPedido.trim())) {

				int total = numeroPedido.length();
				String pais = numeroPedido.substring(0, 2);
				String numero = this.retornarPedido(numeroPedido.substring(2, total));
				String kindCode = this.retornarKindCode(numeroPedido);

				equivalentsInquiry = this.pesquisarFamiliaPelaPublicacaoDocDbConverterXml(numero, pais, kindCode);

			}

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarFamiliaPorNumeroEpoDoc :: " + e.getMessage());
		}

		return equivalentsInquiry;

	}

	private EquivalentsInquiry pesquisarFamiliaPelaPublicacaoDocDbConverterXml(String numeroDocDb, String pais, String kindCode) {

		EquivalentsInquiry equivalentsInquiry = null;
		Tbfamiliaec tbfamiliaec = this.pesquisarFamiliaPelaPublicacaoDocDb(numeroDocDb, pais, kindCode);
		if (tbfamiliaec != null) {

			String parametro = "docdb";
			List<Familia> documentos = this.retornarFamiliasDocumento(tbfamiliaec, parametro);
			if (documentos != null && documentos.size() >= 1) {
				equivalentsInquiry = this.converterFamiliaParaXml(documentos, numeroDocDb, parametro);
			}

		}
		return equivalentsInquiry;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private Tbfamiliaec pesquisarFamiliaPelaPublicacaoDocDb(String numeroDocDb, String pais, String kindCode) {

		Tbfamiliaec tbfamiliaec = this.epecServiceFacade.findIdFamiliaPublicacaoPatentePorDocDb(numeroDocDb, pais, kindCode);
		return tbfamiliaec;

	}

	/**
	 * 
	 * M�todo respons�vel em obter as familias do pertecente ao documento de
	 * patente.
	 * 
	 * @param tbfamiliaec
	 * @param docType
	 * @return
	 */
	private List<Familia> retornarFamiliasDocumento(Tbfamiliaec tbfamiliaec, String docType) {

		List<Familia> documentos = new ArrayList<Familia>();
		if (tbfamiliaec != null) {

			String tipoPublico = "0";
			Long idFamiliaEc = tbfamiliaec.getIdFamiliaEc();
			Boolean isSigilo = tbfamiliaec.getPublico();
			if (isSigilo) {
				tipoPublico = "1";
			}

			List<Tbpatenteec> patentes = tbfamiliaec.getTbpatenteecList();
			Iterator<Tbpatenteec> it = patentes.iterator();
			while (it.hasNext()) {

				Tbpatenteec tb = it.next();
				Familia familia = new Familia();
				familia.setId(Long.toString(idFamiliaEc));
				familia.setPublico(tipoPublico);
				Patente patente = this.obterDadosBibliograficosFamilia(tb, docType);
				familia.setPatente(patente);
				documentos.add(familia);

			}

		}
		return documentos;

	}

	/***
	 * 
	 * Obtem os dados da patente referentes a familia.
	 * 
	 * @param tbPatenteec
	 * @param docType
	 * @return
	 */
	private Patente obterDadosBibliograficosFamilia(Tbpatenteec tbPatenteec, String docType) {

		Patente patente = null;
		if (tbPatenteec != null) {

			PatenteHelper patenteHelper = new PatenteHelper();
			patente = patenteHelper.obterDadosPatenteFamilia(tbPatenteec, docType);
			patenteHelper = null;

		}
		return patente;
	}

	private EquivalentsInquiry converterFamiliaParaXml(List<Familia> documentos, String numeroPedido, String docType) {

		EquivalentsInquiry equivalentsInquiry = new EquivalentsInquiry();
		EquivalentsIquiryXmlWriter writer = new EquivalentsIquiryXmlWriter();
		writer.setTipoDocType(docType);
		writer.setTipoPesquisa(this.getTipoPesquisa());
		equivalentsInquiry = writer.converterFamilia(documentos, numeroPedido);
		return equivalentsInquiry;

	}

	// Fim Familia
	// ---------------------------------------------------------------------------------

	// Relatório Técnico
	// --------------------------------------------------------------------------
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ReportApplicationReference pesquisarRelatorioTecnicoAplicacaoPorNumeroEpoDoc(String numeroPedido, String entidade) {

		ReportApplicationReference xml = null;
		try {

			RelatorioTecnico relatorioTecnico = pesquisarRelatorioTecnicoAplicacaoPorNumeroEpoDocEntidade(numeroPedido, entidade, "xml");
			if (relatorioTecnico != null) {

				xml = this.converterRelatorioTecnicoParaXml(relatorioTecnico);

			}

			/*
			 * Tbpatenteec tbPatenteec =
			 * this.epecServiceFacade.findPatentePorAplicacaoEpoDocEEntidade
			 * (numeroPedido, entidade); if (tbPatenteec != null) {
			 * 
			 * Tbrelatoriopatente tbRelatoriopatente =
			 * this.epecServiceFacade.findRelatorioTecnicoPorIdPatente
			 * (tbPatenteec.getIdPatenteEc()); if (tbRelatoriopatente != null) {
			 * 
			 * Tbrelatorioec tbRelatorioEc =
			 * this.epecServiceFacade.findRelatorioTecnicoPorId
			 * (tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio());
			 * if (tbRelatorioEc != null) {
			 * 
			 * this.helper = new RelatorioTecnicoHelper(); RelatorioTecnico
			 * relatorioTecnico =
			 * this.helper.obterRelatorioTecnico(tbPatenteec,tbRelatorioEc);
			 * 
			 * List<Tbcatrelatorioec> relatorioAux =
			 * this.epecServiceFacade.findCatRelatorio
			 * (tbRelatorioEc.getIdRelatorioEc()); if (relatorioAux != null &&
			 * !relatorioAux.isEmpty()) { List<CategoriaRelatorio> categorias =
			 * this.obterCategorias(relatorioAux);
			 * relatorioTecnico.setCategoriasRelatorio(categorias); } xml =
			 * this.converterRelatorioTecnicoParaXml(relatorioTecnico);
			 * 
			 * } else { return null; }
			 * 
			 * } else { return null; }
			 * 
			 * } else { return null; }
			 */

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " + numeroPedido + " - " + e.getMessage());
		}
		return xml;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public RelatorioTecnico pesquisarRelatorioTecnicoAplicacaoPorNumeroEpoDocEntidade(String numeroPedido, String entidade, String tipoRelatorio) {

		RelatorioTecnico relatorioTecnico = null;
		try {

			Tbpatenteec tbPatenteec = this.epecServiceFacade.findPatentePorAplicacaoEpoDocEEntidade(numeroPedido, entidade);
			if (tbPatenteec != null) {

				Tbrelatoriopatente tbRelatoriopatente = this.epecServiceFacade.findRelatorioTecnicoPorIdPatente(tbPatenteec.getIdPatenteEc());
				if (tbRelatoriopatente != null) {

					Tbrelatorioec tbRelatorioEc = this.epecServiceFacade.findRelatorioTecnicoPorId(tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio());
					if (tbRelatorioEc != null) {
						this.helper = new RelatorioTecnicoHelper();
						if ("relatorioCompleto".equals(tipoRelatorio)) {
							PatenteHelper patenteHelper = new PatenteHelper();
							Patente patente = patenteHelper.obterDadosPatente(tbPatenteec, "epodoc");
							relatorioTecnico = helper.obterRelatorioTecnicoCompleto(patente, tbRelatorioEc);

						} else {

							relatorioTecnico = this.helper.obterRelatorioTecnico(tbPatenteec, tbRelatorioEc);
						}

						List<Tbcatrelatorioec> relatorioAux = this.epecServiceFacade.findCatRelatorio(tbRelatorioEc.getIdRelatorioEc());
						if (relatorioAux != null && !relatorioAux.isEmpty()) {
							List<CategoriaRelatorio> categorias = this.obterCategorias(relatorioAux);
							relatorioTecnico.setCategoriasRelatorio(categorias);
						}

					} else {
						return null;
					}

				} else {
					return null;
				}

			} else {
				return null;
			}

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " + numeroPedido + " - " + e.getMessage());
		}
		return relatorioTecnico;

	}

	/*
	 * @Override public ReportApplicationReference
	 * pesquisarRelatorioTecnicoPublicacaoPorNumeroEpoDoc(String numeroPedido) {
	 * 
	 * ReportApplicationReference xml = new ReportApplicationReference(); try {
	 * 
	 * String pais = numeroPedido.substring(0, 2); String numero =
	 * numeroPedido.substring(2, numeroPedido.trim().length());
	 * 
	 * Tbpatenteec tbPatenteec =
	 * this.epecServiceFacade.findPatentePorPublicacaoEpoDoc(numero, pais); if
	 * (tbPatenteec != null) {
	 * 
	 * Tbrelatoriopatente tbRelatoriopatente =
	 * this.epecServiceFacade.findRelatorioTecnicoPorIdPatente
	 * (tbPatenteec.getIdPatenteEc()); if (tbRelatoriopatente != null) {
	 * 
	 * Tbrelatorioec tbRelatorioEc =
	 * this.epecServiceFacade.findRelatorioTecnicoPorId
	 * (tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio()); if
	 * (tbRelatorioEc != null) {
	 * 
	 * this.helper = new RelatorioTecnicoHelper(); RelatorioTecnico
	 * relatorioTecnico = this.helper.obterRelatorioTecnico(tbPatenteec,
	 * tbRelatorioEc);
	 * 
	 * List<Tbcatrelatorioec> relatorioAux =
	 * this.epecServiceFacade.findCatRelatorio
	 * (tbRelatorioEc.getIdRelatorioEc()); if (relatorioAux != null &&
	 * !relatorioAux.isEmpty()) { List<CategoriaRelatorio> categorias =
	 * this.obterCategorias(relatorioAux);
	 * relatorioTecnico.setCategoriasRelatorio(categorias); }
	 * 
	 * xml = this.converterRelatorioTecnicoParaXml(relatorioTecnico);
	 * 
	 * } else { return null; }
	 * 
	 * } else { return null; }
	 * 
	 * } else { return null; }
	 * 
	 * } catch (Exception e) {
	 * LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " +
	 * numeroPedido + " - " + e.getMessage()); } return xml; }
	 */

	@Override
	public ReportApplicationReference pesquisarRelatorioTecnicoPublicacaoPorNumeroEpoDocEEntidade(String numeroPedido, String entidade) {

		ReportApplicationReference xml = null;
		try {

			RelatorioTecnico relatorioTecnico = this.pesquisarRelatorioTecnicoPublicacaoPorNumeroEpoDocEntidade(numeroPedido, entidade, "xml");
			if (relatorioTecnico != null) {

				xml = this.converterRelatorioTecnicoParaXml(relatorioTecnico);

			}

			/*
			 * String pais = numeroPedido.substring(0, 2); String numero =
			 * numeroPedido.substring(2, numeroPedido.trim().length());
			 * 
			 * Tbpatenteec tbPatenteec =
			 * this.epecServiceFacade.findPatentePorPublicacaoEpoDocEEntidade
			 * (numero, pais, entidade); if (tbPatenteec != null) {
			 * 
			 * Tbrelatoriopatente tbRelatoriopatente =
			 * this.epecServiceFacade.findRelatorioTecnicoPorIdPatente
			 * (tbPatenteec.getIdPatenteEc()); if (tbRelatoriopatente != null) {
			 * 
			 * Tbrelatorioec tbRelatorioEc =
			 * this.epecServiceFacade.findRelatorioTecnicoPorId
			 * (tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio());
			 * if (tbRelatorioEc != null) {
			 * 
			 * this.helper = new RelatorioTecnicoHelper(); RelatorioTecnico
			 * relatorioTecnico = this.helper.obterRelatorioTecnico(tbPatenteec,
			 * tbRelatorioEc);
			 * 
			 * List<Tbcatrelatorioec> relatorioAux =
			 * this.epecServiceFacade.findCatRelatorio
			 * (tbRelatorioEc.getIdRelatorioEc()); if (relatorioAux != null &&
			 * !relatorioAux.isEmpty()) { List<CategoriaRelatorio> categorias =
			 * this.obterCategorias(relatorioAux);
			 * relatorioTecnico.setCategoriasRelatorio(categorias); }
			 * 
			 * xml = this.converterRelatorioTecnicoParaXml(relatorioTecnico);
			 * 
			 * } else { return null; }
			 * 
			 * } else { return null; }
			 * 
			 * } else { return null; }
			 */

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " + numeroPedido + " - " + e.getMessage());
		}
		return xml;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private RelatorioTecnico pesquisarRelatorioTecnicoPublicacaoPorNumeroEpoDocEntidade(String numeroPedido, String entidade, String tipoRelatorio) {

		RelatorioTecnico relatorioTecnico = null;
		try {

			String pais = numeroPedido.substring(0, 2);
			String numero = numeroPedido.substring(2, numeroPedido.trim().length());

			Tbpatenteec tbPatenteec = this.epecServiceFacade.findPatentePorPublicacaoEpoDocEEntidade(numero, pais, entidade);
			if (tbPatenteec != null) {

				Tbrelatoriopatente tbRelatoriopatente = this.epecServiceFacade.findRelatorioTecnicoPorIdPatente(tbPatenteec.getIdPatenteEc());
				if (tbRelatoriopatente != null) {

					Tbrelatorioec tbRelatorioEc = this.epecServiceFacade.findRelatorioTecnicoPorId(tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio());
					if (tbRelatorioEc != null) {

						this.helper = new RelatorioTecnicoHelper();
						if ("relatorioCompleto".equals(tipoRelatorio)) {
							PatenteHelper patenteHelper = new PatenteHelper();
							Patente patente = patenteHelper.obterDadosPatente(tbPatenteec, "epodoc");
							relatorioTecnico = helper.obterRelatorioTecnicoCompleto(patente, tbRelatorioEc);

						} else {

							relatorioTecnico = this.helper.obterRelatorioTecnico(tbPatenteec, tbRelatorioEc);
						}
						List<Tbcatrelatorioec> relatorioAux = this.epecServiceFacade.findCatRelatorio(tbRelatorioEc.getIdRelatorioEc());
						if (relatorioAux != null && !relatorioAux.isEmpty()) {
							List<CategoriaRelatorio> categorias = this.obterCategorias(relatorioAux);
							relatorioTecnico.setCategoriasRelatorio(categorias);
						}

					} else {
						return null;
					}

				} else {
					return null;
				}

			} else {
				return null;
			}

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " + numeroPedido + " - " + e.getMessage());
		}
		return relatorioTecnico;
	}

	/*
	 * @Override
	 * 
	 * @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) public
	 * ReportApplicationReference
	 * pesquisarRelatorioTecnicoAplicacaoPorNumeroDocDb(String numeroPedido) {
	 * 
	 * ReportApplicationReference xml = null; try {
	 * 
	 * int total = numeroPedido.length(); String pais =
	 * numeroPedido.substring(0, 2); String numero =
	 * this.retornarPedido(numeroPedido.substring(2, total)); String kindCode =
	 * this.retornarKindCode(numeroPedido);
	 * 
	 * Tbpatenteec tbPatenteec =
	 * this.epecServiceFacade.findPatentePorAplicacaoDocDb(numero, pais,
	 * kindCode); if (tbPatenteec != null) {
	 * 
	 * Tbrelatoriopatente tbRelatoriopatente =
	 * this.epecServiceFacade.findRelatorioTecnicoPorIdPatente
	 * (tbPatenteec.getIdPatenteEc()); if (tbRelatoriopatente != null) {
	 * 
	 * Tbrelatorioec tbRelatorioEc =
	 * this.epecServiceFacade.findRelatorioTecnicoPorId
	 * (tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio()); if
	 * (tbRelatorioEc != null) {
	 * 
	 * this.helper = new RelatorioTecnicoHelper(); RelatorioTecnico
	 * relatorioTecnico = this.helper.obterRelatorioTecnico(tbPatenteec,
	 * tbRelatorioEc);
	 * 
	 * List<Tbcatrelatorioec> relatorioAux =
	 * this.epecServiceFacade.findCatRelatorio
	 * (tbRelatorioEc.getIdRelatorioEc()); if (relatorioAux != null &&
	 * !relatorioAux.isEmpty()) { List<CategoriaRelatorio> categorias =
	 * this.obterCategorias(relatorioAux);
	 * relatorioTecnico.setCategoriasRelatorio(categorias); } xml =
	 * this.converterRelatorioTecnicoParaXml(relatorioTecnico);
	 * 
	 * } else { return null; }
	 * 
	 * } else { return null; }
	 * 
	 * } else { return null; }
	 * 
	 * } catch (Exception e) {
	 * LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " +
	 * numeroPedido + " - " + e.getMessage()); } return xml;
	 * 
	 * }
	 */

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ReportApplicationReference pesquisarRelatorioTecnicoAplicacaoPorNumeroDocDbEEntidade(String numeroPedido, String entidade) {

		ReportApplicationReference xml = null;
		try {

			RelatorioTecnico relatorioTecnico = this.pesquisarRelatorioTecnicoAplicacaoPorNumeroDocDbEntidade(numeroPedido, entidade, "xml");
			if (relatorioTecnico != null) {
				xml = this.converterRelatorioTecnicoParaXml(relatorioTecnico);
			}

			/*
			 * int total = numeroPedido.length(); String pais =
			 * numeroPedido.substring(0, 2); String numero =
			 * this.retornarPedido(numeroPedido.substring(2, total)); String
			 * kindCode = this.retornarKindCode(numeroPedido);
			 * 
			 * Tbpatenteec tbPatenteec =
			 * this.epecServiceFacade.findPatentePorAplicacaoDocDbEEntidade
			 * (numero, pais, kindCode, entidade); if (tbPatenteec != null) {
			 * 
			 * Tbrelatoriopatente tbRelatoriopatente =
			 * this.epecServiceFacade.findRelatorioTecnicoPorIdPatente
			 * (tbPatenteec.getIdPatenteEc()); if (tbRelatoriopatente != null) {
			 * 
			 * Tbrelatorioec tbRelatorioEc =
			 * this.epecServiceFacade.findRelatorioTecnicoPorId
			 * (tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio());
			 * if (tbRelatorioEc != null) {
			 * 
			 * this.helper = new RelatorioTecnicoHelper(); RelatorioTecnico
			 * relatorioTecnico = this.helper.obterRelatorioTecnico(tbPatenteec,
			 * tbRelatorioEc); List<Tbcatrelatorioec> relatorioAux =
			 * this.epecServiceFacade
			 * .findCatRelatorio(tbRelatorioEc.getIdRelatorioEc()); if
			 * (relatorioAux != null && !relatorioAux.isEmpty()) {
			 * List<CategoriaRelatorio> categorias =
			 * this.obterCategorias(relatorioAux);
			 * relatorioTecnico.setCategoriasRelatorio(categorias); } xml =
			 * this.converterRelatorioTecnicoParaXml(relatorioTecnico);
			 * 
			 * } else { return null; }
			 * 
			 * } else { return null; }
			 * 
			 * } else { return null; }
			 */

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " + numeroPedido + " - " + e.getMessage());
		}
		return xml;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private RelatorioTecnico pesquisarRelatorioTecnicoAplicacaoPorNumeroDocDbEntidade(String numeroPedido, String entidade, String tipoRelatorio) {

		RelatorioTecnico relatorioTecnico = null;
		try {

			int total = numeroPedido.length();
			String pais = numeroPedido.substring(0, 2);
			String numero = this.retornarPedido(numeroPedido.substring(2, total));
			String kindCode = this.retornarKindCode(numeroPedido);

			Tbpatenteec tbPatenteec = this.epecServiceFacade.findPatentePorAplicacaoDocDbEEntidade(numero, pais, kindCode, entidade);
			if (tbPatenteec != null) {

				Tbrelatoriopatente tbRelatoriopatente = this.epecServiceFacade.findRelatorioTecnicoPorIdPatente(tbPatenteec.getIdPatenteEc());
				if (tbRelatoriopatente != null) {

					Tbrelatorioec tbRelatorioEc = this.epecServiceFacade.findRelatorioTecnicoPorId(tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio());
					if (tbRelatorioEc != null) {

						this.helper = new RelatorioTecnicoHelper();
						if ("relatorioCompleto".equals(tipoRelatorio)) {
							PatenteHelper patenteHelper = new PatenteHelper();
							Patente patente = patenteHelper.obterDadosPatente(tbPatenteec, "docdb");
							relatorioTecnico = helper.obterRelatorioTecnicoCompleto(patente, tbRelatorioEc);

						} else {

							relatorioTecnico = this.helper.obterRelatorioTecnico(tbPatenteec, tbRelatorioEc);
						}
						List<Tbcatrelatorioec> relatorioAux = this.epecServiceFacade.findCatRelatorio(tbRelatorioEc.getIdRelatorioEc());
						if (relatorioAux != null && !relatorioAux.isEmpty()) {
							List<CategoriaRelatorio> categorias = this.obterCategorias(relatorioAux);
							relatorioTecnico.setCategoriasRelatorio(categorias);
						}

					} else {
						return null;
					}

				} else {
					return null;
				}

			} else {
				return null;
			}

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " + numeroPedido + " - " + e.getMessage());
		}
		return relatorioTecnico;

	}

	/*
	 * @Override public ReportApplicationReference
	 * pesquisarRelatorioTecnicoPublicacaoPorNumeroDocDb(String numeroPedido) {
	 * 
	 * ReportApplicationReference xml = null; try {
	 * 
	 * int total = numeroPedido.length(); String pais =
	 * numeroPedido.substring(0, 2); String numero =
	 * this.retornarPedido(numeroPedido.substring(2, total)); String kindCode =
	 * this.retornarKindCode(numeroPedido);
	 * 
	 * Tbpatenteec tbPatenteec =
	 * this.epecServiceFacade.findPatentePorPublicacaoDocDb(numero, pais,
	 * kindCode); if (tbPatenteec != null) {
	 * 
	 * Tbrelatoriopatente tbRelatoriopatente =
	 * this.epecServiceFacade.findRelatorioTecnicoPorIdPatente
	 * (tbPatenteec.getIdPatenteEc()); if (tbRelatoriopatente != null) {
	 * 
	 * Tbrelatorioec tbRelatorioEc =
	 * this.epecServiceFacade.findRelatorioTecnicoPorId
	 * (tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio()); if
	 * (tbRelatorioEc != null) {
	 * 
	 * this.helper = new RelatorioTecnicoHelper(); RelatorioTecnico
	 * relatorioTecnico = this.helper.obterRelatorioTecnico(tbPatenteec,
	 * tbRelatorioEc);
	 * 
	 * List<Tbcatrelatorioec> relatorioAux =
	 * this.epecServiceFacade.findCatRelatorio
	 * (tbRelatorioEc.getIdRelatorioEc()); if (relatorioAux != null &&
	 * !relatorioAux.isEmpty()) { List<CategoriaRelatorio> categorias =
	 * this.obterCategorias(relatorioAux);
	 * relatorioTecnico.setCategoriasRelatorio(categorias); }
	 * 
	 * xml = this.converterRelatorioTecnicoParaXml(relatorioTecnico);
	 * 
	 * } else { return null; }
	 * 
	 * } else { return null; }
	 * 
	 * } else { return null; }
	 * 
	 * } catch (Exception e) {
	 * LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " +
	 * numeroPedido + " - " + e.getMessage()); } return xml;
	 * 
	 * }
	 */

	@Override
	public ReportApplicationReference pesquisarRelatorioTecnicoPublicacaoPorNumeroDocDb(String numeroPedido, String entidade) {

		ReportApplicationReference xml = null;

		try {

			RelatorioTecnico relatorioTecnico = this.pesquisarRelatorioTecnicoPorPublicacaoPorNumeroDocDbEntidade(numeroPedido, entidade, "xml");
			if (relatorioTecnico != null) {
				xml = this.converterRelatorioTecnicoParaXml(relatorioTecnico);
			}

			/*
			 * int total = numeroPedido.length(); String pais =
			 * numeroPedido.substring(0, 2); String numero =
			 * this.retornarPedido(numeroPedido.substring(2, total)); String
			 * kindCode = this.retornarKindCode(numeroPedido);
			 * 
			 * Tbpatenteec tbPatenteec =
			 * this.epecServiceFacade.findPatentePorPublicacaoDocDbEEntidade
			 * (numero, pais, kindCode, entidade); if (tbPatenteec != null) {
			 * 
			 * Tbrelatoriopatente tbRelatoriopatente =
			 * this.epecServiceFacade.findRelatorioTecnicoPorIdPatente
			 * (tbPatenteec.getIdPatenteEc()); if (tbRelatoriopatente != null) {
			 * 
			 * Tbrelatorioec tbRelatorioEc =
			 * this.epecServiceFacade.findRelatorioTecnicoPorId
			 * (tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio());
			 * if (tbRelatorioEc != null) {
			 * 
			 * this.helper = new RelatorioTecnicoHelper(); RelatorioTecnico
			 * relatorioTecnico = this.helper.obterRelatorioTecnico(tbPatenteec,
			 * tbRelatorioEc);
			 * 
			 * List<Tbcatrelatorioec> relatorioAux =
			 * this.epecServiceFacade.findCatRelatorio
			 * (tbRelatorioEc.getIdRelatorioEc()); if (relatorioAux != null &&
			 * !relatorioAux.isEmpty()) { List<CategoriaRelatorio> categorias =
			 * this.obterCategorias(relatorioAux);
			 * relatorioTecnico.setCategoriasRelatorio(categorias); }
			 * 
			 * xml = this.converterRelatorioTecnicoParaXml(relatorioTecnico);
			 * 
			 * } else { return null; }
			 * 
			 * } else { return null; }
			 * 
			 * } else { return null; }
			 */

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " + numeroPedido + " - " + e.getMessage());
		}
		return xml;

	}

	private RelatorioTecnico pesquisarRelatorioTecnicoPorPublicacaoPorNumeroDocDbEntidade(String numeroPedido, String entidade, String tipoRelatorio) {

		RelatorioTecnico relatorioTecnico = null;

		try {

			int total = numeroPedido.length();
			String pais = numeroPedido.substring(0, 2);
			String numero = this.retornarPedido(numeroPedido.substring(2, total));
			String kindCode = this.retornarKindCode(numeroPedido);

			Tbpatenteec tbPatenteec = this.epecServiceFacade.findPatentePorPublicacaoDocDbEEntidade(numero, pais, kindCode, entidade);
			if (tbPatenteec != null) {

				Tbrelatoriopatente tbRelatoriopatente = this.epecServiceFacade.findRelatorioTecnicoPorIdPatente(tbPatenteec.getIdPatenteEc());
				if (tbRelatoriopatente != null) {

					Tbrelatorioec tbRelatorioEc = this.epecServiceFacade.findRelatorioTecnicoPorId(tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio());
					if (tbRelatorioEc != null) {

						this.helper = new RelatorioTecnicoHelper();
						if ("relatorioCompleto".equals(tipoRelatorio)) {
							PatenteHelper patenteHelper = new PatenteHelper();
							Patente patente = patenteHelper.obterDadosPatente(tbPatenteec, "docdb");
							relatorioTecnico = helper.obterRelatorioTecnicoCompleto(patente, tbRelatorioEc);

						} else {
							relatorioTecnico = this.helper.obterRelatorioTecnico(tbPatenteec, tbRelatorioEc);
						}
						List<Tbcatrelatorioec> relatorioAux = this.epecServiceFacade.findCatRelatorio(tbRelatorioEc.getIdRelatorioEc());
						if (relatorioAux != null && !relatorioAux.isEmpty()) {
							List<CategoriaRelatorio> categorias = this.obterCategorias(relatorioAux);
							relatorioTecnico.setCategoriasRelatorio(categorias);
						}

					} else {
						return null;
					}

				} else {
					return null;
				}

			} else {
				return null;
			}

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " + numeroPedido + " - " + e.getMessage());
		}
		return relatorioTecnico;

	}

	/*
	 * @Override public ReportApplicationReference
	 * pesquisarRelatorioTecnicoPorNumeroOriginal(String numeroOriginal) {
	 * 
	 * ReportApplicationReference xml = null;
	 * 
	 * try {
	 * 
	 * //Tbpatenteec tbPatenteec =
	 * this.epecServiceFacade.findPatentePorNumeroOriginal(numeroOriginal);
	 * Tbpatenteec tbPatenteec =
	 * this.pesquisarPeloNumeroOriginal(numeroOriginal); if (tbPatenteec !=
	 * null) {
	 * 
	 * Tbrelatoriopatente tbRelatoriopatente =
	 * this.epecServiceFacade.findRelatorioTecnicoPorIdPatente
	 * (tbPatenteec.getIdPatenteEc()); if (tbRelatoriopatente != null) {
	 * 
	 * Tbrelatorioec tbRelatorioEc =
	 * this.epecServiceFacade.findRelatorioTecnicoPorId
	 * (tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio()); if
	 * (tbRelatorioEc != null) {
	 * 
	 * this.helper = new RelatorioTecnicoHelper(); RelatorioTecnico
	 * relatorioTecnico = this.helper.obterRelatorioTecnico(tbPatenteec,
	 * tbRelatorioEc);
	 * 
	 * List<Tbcatrelatorioec> relatorioAux =
	 * this.epecServiceFacade.findCatRelatorio
	 * (tbRelatorioEc.getIdRelatorioEc()); if (relatorioAux != null &&
	 * !relatorioAux.isEmpty()) { List<CategoriaRelatorio> categorias =
	 * this.obterCategorias(relatorioAux);
	 * relatorioTecnico.setCategoriasRelatorio(categorias); }
	 * 
	 * xml = this.converterRelatorioTecnicoParaXml(relatorioTecnico);
	 * 
	 * } else { return null; }
	 * 
	 * } else { return null; }
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " +
	 * numeroOriginal + " - " + e.getMessage()); } return xml; }
	 */

	@Override
	public ReportApplicationReference pesquisarRelatorioTecnicoPorNumeroOriginalEEntidade(String numeroOriginal, String entidade) {

		ReportApplicationReference xml = null;

		try {

			RelatorioTecnico relatorioTecnico = this.pesquisarRelatorioTecnicoPorNumeroOriginalEntidade(numeroOriginal, entidade, "xml");
			if (relatorioTecnico != null) {

				xml = this.converterRelatorioTecnicoParaXml(relatorioTecnico);

			}
			/*
			 * Tbpatenteec tbPatenteec =
			 * this.epecServiceFacade.findPatentePorNumeroOriginalEEntidade
			 * (numeroOriginal, entidade); if (tbPatenteec != null) {
			 * 
			 * Tbrelatoriopatente tbRelatoriopatente =
			 * this.epecServiceFacade.findRelatorioTecnicoPorIdPatente
			 * (tbPatenteec.getIdPatenteEc()); if (tbRelatoriopatente != null) {
			 * 
			 * Tbrelatorioec tbRelatorioEc =
			 * this.epecServiceFacade.findRelatorioTecnicoPorId
			 * (tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio());
			 * if (tbRelatorioEc != null) {
			 * 
			 * this.helper = new RelatorioTecnicoHelper(); RelatorioTecnico
			 * relatorioTecnico = this.helper.obterRelatorioTecnico(tbPatenteec,
			 * tbRelatorioEc);
			 * 
			 * List<Tbcatrelatorioec> relatorioAux =
			 * this.epecServiceFacade.findCatRelatorio
			 * (tbRelatorioEc.getIdRelatorioEc()); if (relatorioAux != null &&
			 * !relatorioAux.isEmpty()) { List<CategoriaRelatorio> categorias =
			 * this.obterCategorias(relatorioAux);
			 * relatorioTecnico.setCategoriasRelatorio(categorias); }
			 * 
			 * xml = this.converterRelatorioTecnicoParaXml(relatorioTecnico);
			 * 
			 * } else { return null; }
			 * 
			 * } else { return null; }
			 * 
			 * }
			 */

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " + numeroOriginal + " - " + e.getMessage());
		}
		return xml;
	}

	private RelatorioTecnico pesquisarRelatorioTecnicoPorNumeroOriginalEntidade(String numeroOriginal, String entidade, String tipoRelatorio) {

		RelatorioTecnico relatorioTecnico = null;

		try {

			Tbpatenteec tbPatenteec = this.epecServiceFacade.findPatentePorNumeroOriginalEEntidade(numeroOriginal, entidade);
			if (tbPatenteec != null) {

				Tbrelatoriopatente tbRelatoriopatente = this.epecServiceFacade.findRelatorioTecnicoPorIdPatente(tbPatenteec.getIdPatenteEc());
				if (tbRelatoriopatente != null) {

					Tbrelatorioec tbRelatorioEc = this.epecServiceFacade.findRelatorioTecnicoPorId(tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio());
					if (tbRelatorioEc != null) {

						this.helper = new RelatorioTecnicoHelper();
						if ("relatorioCompleto".equals(tipoRelatorio)) {
							PatenteHelper patenteHelper = new PatenteHelper();
							Patente patente = patenteHelper.obterDadosPatente(tbPatenteec, "original");
							relatorioTecnico = helper.obterRelatorioTecnicoCompleto(patente, tbRelatorioEc);

						} else {
							relatorioTecnico = this.helper.obterRelatorioTecnico(tbPatenteec, tbRelatorioEc);
						}
						List<Tbcatrelatorioec> relatorioAux = this.epecServiceFacade.findCatRelatorio(tbRelatorioEc.getIdRelatorioEc());
						if (relatorioAux != null && !relatorioAux.isEmpty()) {
							List<CategoriaRelatorio> categorias = this.obterCategorias(relatorioAux);
							relatorioTecnico.setCategoriasRelatorio(categorias);
						}

					} else {
						return null;
					}

				} else {
					return null;
				}

			}

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroEpoDoc :: " + numeroOriginal + " - " + e.getMessage());
		}
		return relatorioTecnico;
	}

	private List<CategoriaRelatorio> obterCategorias(List<Tbcatrelatorioec> relatorioAux) {

		List<CategoriaRelatorio> categorias = new ArrayList<CategoriaRelatorio>();
		if (relatorioAux != null && !relatorioAux.isEmpty()) {

			Iterator<Tbcatrelatorioec> it = relatorioAux.iterator();
			while (it.hasNext()) {

				Tbcatrelatorioec tb = it.next();
				CategoriaRelatorio catRelatorio = new CategoriaRelatorio();
				catRelatorio.setNome(tb.getIdCategoria().getStrPortugues());
				catRelatorio.setResumo(tb.getTxResumo());

				long id = tb.getIdRelatorioEc().getIdRelatorioEc();
				int idCategoria = tb.getIdCategoria().getIdCategoria();

				List<RelatorioPatente> anterioridades = this.obterAnterioridadesPatentariasPorCategoria(id, idCategoria);
				if (anterioridades != null && !anterioridades.isEmpty()) {
					catRelatorio.setAnterioridadesPatentariasPorCategoria(anterioridades);
				}

				List<RelatorioPatente> anterioridadesNao = this.obterAnterioridadesNaoPatentariasPorCategoria(id, idCategoria);
				if (anterioridadesNao != null && !anterioridadesNao.isEmpty()) {
					catRelatorio.setAnterioridadesNaoPatentariasPorCategoria(anterioridadesNao);
				}

				List<Caracteristica> caracteristicas = this.obterCaracteristicasRelatorio(id, idCategoria);
				if (caracteristicas != null && !caracteristicas.isEmpty()) {
					catRelatorio.setCaracteristica(caracteristicas);
				}

				LOGGER.info(" >>>>> CategoriaRelatorio " + catRelatorio.toString());
				categorias.add(catRelatorio);

			}

		}
		return categorias;

	}

	private List<RelatorioPatente> obterAnterioridadesPatentariasPorCategoria(long id, long idCategoria) {

		List<RelatorioPatente> anterioridadesPatentariasPorCategoria = new ArrayList<RelatorioPatente>();

		List<CategoriaPatentariaEntity> entities = this.epecServiceFacade.findAnterioridadesPatentariaCategoriaRelatorioPorIdCat(id, idCategoria);
		if (entities != null && !entities.isEmpty()) {

			Iterator<CategoriaPatentariaEntity> it = entities.iterator();
			while (it.hasNext()) {

				CategoriaPatentariaEntity rel = it.next();

				RelatorioPatente relatorio = new RelatorioPatente();
				relatorio.setDocumentCategory(rel.getDocumentCategory());
				relatorio.setNamePrior(rel.getAntPatentaria());
				relatorio.setRelations(rel.getRelations());
				relatorio.setRelevance(rel.getRelevance());
				relatorio.setRelationsClaims(rel.getRelationsClaims());
				relatorio.setIdCategoria(rel.getIdCategoria());

				anterioridadesPatentariasPorCategoria.add(relatorio);

			}

		}

		return anterioridadesPatentariasPorCategoria;

	}

	private List<RelatorioPatente> obterAnterioridadesNaoPatentariasPorCategoria(long id, long idCategoria) {

		List<RelatorioPatente> naoPatentarias = new ArrayList<RelatorioPatente>();

		List<CategoriaNaoPatentetariaEntity> entities = this.epecServiceFacade.findAnterioridadesNaoPatentariaCategoriaRelatorioPorIdCat(id, idCategoria);
		if (entities != null && !entities.isEmpty()) {

			Iterator<CategoriaNaoPatentetariaEntity> it = entities.iterator();
			while (it.hasNext()) {

				CategoriaNaoPatentetariaEntity rel = it.next();

				RelatorioPatente relatorio = new RelatorioPatente();
				relatorio.setDocumentCategory(rel.getDocumentCategory());
				relatorio.setNamePrior(rel.getTxTitulo());
				relatorio.setRelations(rel.getRelations());
				relatorio.setRelevance(rel.getRelevance());
				relatorio.setRelationsClaims(rel.getRelationsClaims());

				naoPatentarias.add(relatorio);

			}

		}

		return naoPatentarias;

	}

	private List<Caracteristica> obterCaracteristicasRelatorio(long id, long idCategoria) {

		List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();
		List<CategoriaRelatorioEntity> cats = this.epecServiceFacade.findCaracteristicasRelatorioPorId(id, idCategoria);

		if (cats != null && !cats.isEmpty()) {

			Iterator<CategoriaRelatorioEntity> it = cats.iterator();
			while (it.hasNext()) {

				CategoriaRelatorioEntity catRelatorio = it.next();

				long idCaracteristica = catRelatorio.getId();
				Tbcaraccatrelatorioec tbCaraccaRelatiorEc = this.epecServiceFacade.findCaracteristicaRelatorioPorIdCaracteristica(idCaracteristica);
				if (tbCaraccaRelatiorEc != null) {

					List<RelatorioPatente> patentaria = null;
					List<RelatorioPatente> naopatentarias = null;

					Caracteristica caracteristica = new Caracteristica();
					caracteristica.setTextoCaracteristica(tbCaraccaRelatiorEc.getTxCaracteristica());

					List<Tbantpatentaria> pantentariasE = tbCaraccaRelatiorEc.getTbantpatentariaList();
					List<Tbantnaopatentaria> naopatentariasE = tbCaraccaRelatiorEc.getTbantnaopatentariaList();
					// aqui
					if (pantentariasE != null && !pantentariasE.isEmpty()) {
						patentaria = this.obterAnterioridadesPorCaracteristica(pantentariasE);
						caracteristica.setTemAnterioridades(true);
					}

					if (naopatentariasE != null && !naopatentariasE.isEmpty()) {
						naopatentarias = this.obterAnterioridadesNaoPatentariasPorCaracteristica(naopatentariasE);
						caracteristica.setTemAnterioridades(true);

					}

					caracteristica.setAnterioridadesPatentariasPorCaracteristica(patentaria);
					caracteristica.setAnterioridadesNaoPatentariasPorCaracteristica(naopatentarias);
					caracteristicas.add(caracteristica);

				}

			}

		}

		return caracteristicas;

	}

	private List<RelatorioPatente> obterAnterioridadesPorCaracteristica(List<Tbantpatentaria> tbantpatentariaList) {

		List<RelatorioPatente> anterioridades = new ArrayList<RelatorioPatente>();
		if (tbantpatentariaList != null && !tbantpatentariaList.isEmpty()) {

			Iterator<Tbantpatentaria> it = tbantpatentariaList.iterator();
			while (it.hasNext()) {

				Tbantpatentaria tbantpatentaria = it.next();

				RelatorioPatente relatorioPatente = new RelatorioPatente();
				// relatorioPatente.setNamePrior(tbantpatentaria.getIdCaraccatrelatorio().getIdCatrelatorioec().getIdRelatorioEc().getStrRelatorio());
				relatorioPatente.setNamePrior(tbantpatentaria.getStrAntpatentaria());
				relatorioPatente.setDocumentCategory(tbantpatentaria.getIdCadtipoanterioridade().getStrOmpi());
				relatorioPatente.setRelevance(tbantpatentaria.getIdCadtipoanterioridade().getStrPortugues());
				relatorioPatente.setRelations(tbantpatentaria.getTxRelacao());
				relatorioPatente.setRelationsClaims(tbantpatentaria.getTxReivindicacao());

				anterioridades.add(relatorioPatente);

			}

		}
		return anterioridades;

	}

	private List<RelatorioPatente> obterAnterioridadesNaoPatentariasPorCaracteristica(List<Tbantnaopatentaria> tbantnaopatentariaList) {

		List<RelatorioPatente> anterioridadesNao = new ArrayList<RelatorioPatente>();
		if (tbantnaopatentariaList != null && !tbantnaopatentariaList.isEmpty()) {

			Iterator<Tbantnaopatentaria> it = tbantnaopatentariaList.iterator();
			while (it.hasNext()) {

				Tbantnaopatentaria tbAntnaopatentaria = it.next();

				RelatorioPatente relatorioPatente = new RelatorioPatente();
				relatorioPatente.setNamePrior(tbAntnaopatentaria.getTxTitulo());
				relatorioPatente.setDocumentCategory(tbAntnaopatentaria.getIdCadtipoanterioridade().getStrOmpi());
				relatorioPatente.setRelevance(tbAntnaopatentaria.getIdCadtipoanterioridade().getStrPortugues());
				relatorioPatente.setRelations(tbAntnaopatentaria.getTxRelacao());
				relatorioPatente.setRelationsClaims(tbAntnaopatentaria.getTxReivindicacao());

				anterioridadesNao.add(relatorioPatente);
			}

		}
		return anterioridadesNao;

	}

	private ReportApplicationReference converterRelatorioTecnicoParaXml(RelatorioTecnico relatorioTecnico) {

		ReportApplicationReference xml = new ReportApplicationReference();
		ReportDataXmlWriter writer = new ReportDataXmlWriter();
		xml = writer.converterRelatorioTecnico(relatorioTecnico);
		return xml;

	}

	// Fim Relatório Técnico
	// ------------------------------------------------------------------------

	private String retornarPedido(String numeroPedido) {

		StringBuffer sbuf = new StringBuffer();
		try {

			for (int i = 0; i < numeroPedido.length(); i++) {

				char c = numeroPedido.charAt(i);
				if (i <= 1) {
					sbuf.append(c);
				} else {

					try {

						String obj = Character.toString(c);
						int valor = Integer.parseInt(obj);
						sbuf.append(obj);

					} catch (NumberFormatException nfe) {
						return sbuf.toString();
					}

				}

			}

		} catch (Exception e) {

		}

		return sbuf.toString();
	}

	private String retornarKindCode(String numeroPedido) {

		String kind = null;
		try {

			int total = numeroPedido.length();
			String ultimo = numeroPedido.trim().substring(total - 1);
			try {

				int valor = Integer.parseInt(ultimo);
				kind = numeroPedido.substring(total - 2, total);

			} catch (NumberFormatException nfe) {
				kind = numeroPedido.substring(total - 1, total);
			}

		} catch (Exception e) {

		}

		return kind;

	}

	// Início - Pesquisas Relatório ODT
	// recuperarRelatorioTecnicoCompletoPublicacaoPorNumeroEpoDoc
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public RelatorioTecnico pesquisarRelatorioOdt(String numeroPedido) {
 		RelatorioTecnico relatorioTecnico = null;
		Tbfamiliaec familiaEc = null;
		List<Familia> familias = null;
		try {
			if (numeroPedido != null && !"".equals(numeroPedido.trim())) {
				
				int total = numeroPedido.length();
				String pais = numeroPedido.substring(0, 2);
				String numero = numeroPedido.substring(2, total);

				relatorioTecnico = this.pesquisarRelatorioTecnicoNumeroEpodoc(numeroPedido);
				if (relatorioTecnico != null) {
					
					familiaEc = this.pesquisarFamiliaPorPublicacaoEpoDoc(numero, pais);
					familias = this.retornarFamiliasDocumento(familiaEc, "epodoc");
					relatorioTecnico.setFamilias(familias);
				}else {
					return null;
				}
				return relatorioTecnico;
			}
		} catch (Exception e) {
			LOGGER.error(" ERRO ::  :: " + numeroPedido + " - " + e.getMessage());
		}
		return null;

	}
			
			
					
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private RelatorioTecnico pesquisarRelatorioTecnicoNumeroEpodoc(String numeroPedido) {

		RelatorioTecnico relatorioTecnico = null;
		try {
			

			String pais = numeroPedido.substring(0, 2);
			String numero = numeroPedido.substring(2, numeroPedido.trim().length());

			Tbpatenteec tbPatenteec = this.epecServiceFacade.findPatentePorPublicacaoEpoDoc(numero, pais);

			if (tbPatenteec != null) {

				Tbrelatoriopatente tbRelatoriopatente = this.epecServiceFacade.findRelatorioTecnicoPorIdPatente(tbPatenteec.getIdPatenteEc());
				if (tbRelatoriopatente != null) {

					Tbrelatorioec tbRelatorioEc = this.epecServiceFacade.findRelatorioTecnicoPorId(tbRelatoriopatente.getTbRelatorioPatentePK().getIdRelatorio());
					if (tbRelatorioEc != null) {

						this.helper = new RelatorioTecnicoHelper();
						PatenteHelper patenteHelper = new PatenteHelper();
						Patente patente = patenteHelper.obterDadosPatente(tbPatenteec, "epodoc");
						relatorioTecnico = helper.obterRelatorioTecnicoCompleto(patente, tbRelatorioEc);

						List<Tbcatrelatorioec> relatorioAux = this.epecServiceFacade.findCatRelatorio(tbRelatorioEc.getIdRelatorioEc());
						if (relatorioAux != null && !relatorioAux.isEmpty()) {
							List<CategoriaRelatorio> categorias = this.obterCategorias(relatorioAux);
							relatorioTecnico.setCategoriasRelatorio(categorias);
						}
					} else {
						return null;
					}
				} else {
					return null;
				}
			} else {
				return null;
			}

		} catch (Exception e) {
			LOGGER.error(" ERRO :: pesquisarRelatorioTecnicoPorNumeroDocDB :: " + numeroPedido + " - " + e.getMessage());
		}
		return relatorioTecnico;
	}
	// Fim - Pesquisas Relatório ODT

}