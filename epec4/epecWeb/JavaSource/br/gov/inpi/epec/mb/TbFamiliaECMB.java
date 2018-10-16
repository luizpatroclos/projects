package br.gov.inpi.epec.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

import br.gov.inpi.epec.beans.TbRelatorioPatentePK;
import br.gov.inpi.epec.beans.Tbcadentidade;
import br.gov.inpi.epec.beans.Tbcadpais;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbclassificacao;
import br.gov.inpi.epec.beans.Tbclassificacaopatente;
import br.gov.inpi.epec.beans.TbclassificacaopatentePK;
import br.gov.inpi.epec.beans.Tbcolaboracaoentidade;
import br.gov.inpi.epec.beans.Tbcomentariosfamiliaec;
import br.gov.inpi.epec.beans.Tbdepositante;
import br.gov.inpi.epec.beans.Tbdepositantepatente;
import br.gov.inpi.epec.beans.Tbfamiliaec;
import br.gov.inpi.epec.beans.Tbinventor;
import br.gov.inpi.epec.beans.Tbinventorpatente;
import br.gov.inpi.epec.beans.Tblogfamilia;
import br.gov.inpi.epec.beans.Tblogpatente;
import br.gov.inpi.epec.beans.Tblogrelatorio;
import br.gov.inpi.epec.beans.Tbpatenteec;
import br.gov.inpi.epec.beans.Tbrelatoriocolaboracao;
import br.gov.inpi.epec.beans.Tbrelatorioec;
import br.gov.inpi.epec.beans.Tbrelatoriopatente;
import br.gov.inpi.epec.beans.mensagemEmail;
import br.gov.inpi.epec.entities.Familia;
import br.gov.inpi.epec.facade.ConsultaServiceFacade;
import br.gov.inpi.epec.facade.EpecServiceFacade;
import br.gov.inpi.epec.service.impl.helper.FamiliaHelper;
import br.gov.inpi.epec.util.EPECUtil;
import br.gov.inpi.epec.util.EmailUtils;
import br.gov.inpi.epec.util.JSFMessageUtil;

@SessionScoped
@ManagedBean
public class TbFamiliaECMB extends AbstractMB implements Serializable {

	private static final long serialVersionUID = -765540670107589990L;

	private static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@EJB
	EpecServiceFacade service;

	@EJB
	private ConsultaServiceFacade serviceConsulta;

	private EmailUtils emailUtils = new EmailUtils();

	private String numeroPatente;

	private String relatorioCopiar;

	private Tbfamiliaec familia;

	public String numerofamilia;

	public String numeroEpodoc;

	public String numeroPublicacaoKind;

	private List<Tbpatenteec> patentes;

	@SuppressWarnings("unused")
	private List<Tbrelatorioec> relatorioSemVinculado;

	private List<Tbrelatorioec> relatorio;

	private Tbrelatorioec tbrelatorioec;

	private Tbrelatorioec relatorioInserirNovo = new Tbrelatorioec();

	private Tbrelatorioec relatorioInserir;

	@SuppressWarnings("unused")
	private List<Tbrelatorioec> patentesComRelatorio;

	private String textoComentario;

	private List<Tblogfamilia> logFamilia;

	private Tbpatenteec patenteManual = new Tbpatenteec();

	private mensagemEmail mensagem = new mensagemEmail();

	private List<Tbcadentidade> entidades;

	private String numeroRelatorio;

	private List<Tbinventor> inventor;

	private List<Tbinventor> escolherInventor;

	private Tbinventor inventorCreate = new Tbinventor();

	private List<Tbclassificacao> classificacao;

	private List<Tbclassificacao> escolherClassificacao;

	private DualListModel<String> packiClassificacao;

	private List<Tbclassificacao> classificacaoAux;

	private List<Tbclassificacao> classificaoSalvar;

	private List<Tbdepositante> depositante;

	private List<Tbdepositante> escolherDepositante;

	private Tbdepositante depositanteCreate = new Tbdepositante();

	private Tbclassificacao classificacaoCreate = new Tbclassificacao();

	private List<Tbpatenteec> copiarRelatorioPatente;

	private String patenteRelatorioCopiar;

	private List<String> colaboracoesCopiarRelatorio;

	private String vincularRelatorio;

	public ExternalContext externalContext;

	public List<Tbcadusuario> usuarios;

	public String usuarioSelecionado;

	private HttpSession session;

	private String trocarUsuarioLogado;

	private String salvarPedidoManual;

	private List<String> classificacaoTexto;

	private List<String> classificacaoTextoAux;

	private String usuarioLabel;
	
	private String retornarLinkPedido;
	
	private String retornarLinkPdf;
	
	public List<String> getClassificacaoTexto() {
		return classificacaoTexto;
	}

	public void setClassificacaoTexto(List<String> classificacaoTexto) {
		this.classificacaoTexto = classificacaoTexto;
	}

	public List<String> getClassificacaoTextoAux() {
		return classificacaoTextoAux;
	}

	public void setClassificacaoTextoAux(List<String> classificacaoTextoAux) {
		this.classificacaoTextoAux = classificacaoTextoAux;
	}

	public String getSalvarPedidoManual() {
		return salvarPedidoManual;
	}

	public void setSalvarPedidoManual(String salvarPedidoManual) {
		this.salvarPedidoManual = salvarPedidoManual;
	}

	public String getTrocarUsuarioLogado() {
		return trocarUsuarioLogado;
	}

	public void setTrocarUsuarioLogado(String trocarUsuarioLogado) {
		this.trocarUsuarioLogado = trocarUsuarioLogado;
	}

	public Tbcadusuario usuarioLogado;

	Locale localeES = new Locale("es", "");
	Locale localeEN = new Locale("en", "");

	public Tbcadusuario getUsuarioLogado() {
		obterUsuarioSessao1();
		return usuarioLogado;
	}

	private void obterUsuarioSessao1() {
		FacesContext fc = FacesContext.getCurrentInstance();

		session = (HttpSession) fc.getExternalContext().getSession(true);

		usuarioLogado = (Tbcadusuario) session.getAttribute("usuarioAutenticado");

	}

	public void setUsuarioLogado(Tbcadusuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	private String pedidoRelatorio;

	private List<Tbpatenteec> copiarRelatorioPatenteTeste;

	private List<Familia> familias;

	private boolean verificaDiagrama = true;

	public String getPatenteRelatorioCopiar() {
		return patenteRelatorioCopiar;
	}

	public void setPatenteRelatorioCopiar(String patenteRelatorioCopiar) {
		this.patenteRelatorioCopiar = patenteRelatorioCopiar;
	}

	@SuppressWarnings("unchecked")
	public void salvarPatenteManual() {

		if (validardados()) {
			obterUsuarioSessao1();
			Tbdepositantepatente tbdepositantepatente;
			Tbinventorpatente tbinventorpatente;
			Tbclassificacaopatente tbclassificacaopatente;

			// Valores Obrigatórios
			patenteManual.setStrPubPaisDocdb(patenteManual.getPatenteManual().substring(0, 2));
			patenteManual.setStrPedPaisDocdb(patenteManual.getPatenteManual2().substring(0, 2));
			patenteManual.setStrPubNumDocdb(patenteManual.getPatenteManual().substring(2, patenteManual.getPatenteManual().length()));
			patenteManual.setStrPedNumDocdb(patenteManual.getPatenteManual2().substring(2, patenteManual.getPatenteManual2().length()));
			// Valor do login Logado
			patenteManual.setIdEntidadeEc(usuarioLogado.getIdEntidadeEc());

			patenteManual.setIdFamiliaEc(familia);

			service.save(patenteManual);

			if (patenteManual.getDepositante() != null) {
				for (int d = 0; d < patenteManual.getDepositante().size(); d++) {

					Tbdepositante tbdepositante = service.obterDepositante(patenteManual.getDepositante().get(d));

					tbdepositantepatente = new Tbdepositantepatente();

					tbdepositantepatente.setIdDepositante(tbdepositante.getIdDepositante());
					tbdepositantepatente.setIdPatenteEc(patenteManual.getIdPatenteEc());
					service.save(tbdepositantepatente);
				}

			}

			if (patenteManual.getInventor() != null) {
				for (int i = 0; i < patenteManual.getInventor().size(); i++) {
					tbinventorpatente = new Tbinventorpatente();

					Tbinventor tbinventor = service.findInventorPorNome(patenteManual.getInventor().get(i));

					tbinventorpatente.setIdInventor(tbinventor.getIdInventor());
					tbinventorpatente.setIdPatenteEc(patenteManual.getIdPatenteEc());
					service.save(tbinventorpatente);
				}
			}

			if (packiClassificacao.getTarget() != null) {
				for (int c = 0; c < packiClassificacao.getTarget().size(); c++) {

					Tbclassificacao classifi = service.findClassificacaoPorNome(packiClassificacao.getTarget().get(c));

					tbclassificacaopatente = new Tbclassificacaopatente();

					TbclassificacaopatentePK pk = new TbclassificacaopatentePK();
					pk.setIdClassificacao(classifi.getIdClassificacao());
					pk.setIdPatenteEc(patenteManual.getIdPatenteEc());

					tbclassificacaopatente.setIdOrdem(c + 1);
					tbclassificacaopatente.setTbclassificacaopatentePK(pk);

					service.save(tbclassificacaopatente);
				}
			}

			loadClassificacao();

			classificacaoAux = new ArrayList<Tbclassificacao>();

			packiClassificacao = new DualListModel<String>(classificacaoTexto, classificacaoTextoAux);

			this.salvarPedidoManual = "1";

			salvarLogPedido(usuarioLogado.getStrUsuario(), patenteManual.getIdPatenteEc(), "Inserir", "Pedido", "Manual");
				
			
			
			salvarLogFamilia(familia.getIdFamiliaEc(), "Inserir", "Pedido", patenteManual.getNumeroPedido(), usuarioLogado.getStrUsuario());

			
			patenteManual = new Tbpatenteec();

			EPECUtil.redirecionar("/epecWeb/pages/manterFamilia.jsf");

		}

	}

	private boolean validardados() {
		// TODO FIX

		String NumeroPublicacaoDOCDB = "";
		String kindcodePublicacao = "";
		String numeroPedidoDOCDB = "";
		String kindcodePedido = "";
		String numeroPedidoEpodoc = "";
		String NumeroPedidoOriginal = "";
		String titulo = "";
		String dataDeposito = "";
		String dataPublicacao = "";

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			NumeroPublicacaoDOCDB = "Número de publicación DOCDB";
			kindcodePublicacao = "Kindcode (publicación)";
			numeroPedidoDOCDB = "Número de solicitud DOCDB";
			kindcodePedido = "Kindcode (solicitud)";
			numeroPedidoEpodoc = "Número de solicitud EPODOC";
			NumeroPedidoOriginal = "Número de solicitud ORIGINAL";
			titulo = "Título";
			dataDeposito = "Fecha de Solicitud";
			dataPublicacao = "Fecha de Publicación";

		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			NumeroPublicacaoDOCDB = "Publication number DOCDB";
			kindcodePublicacao = "Kindcode (publication)";
			numeroPedidoDOCDB = "Application number DOCDB";
			kindcodePedido = "Kindcode (application)";
			numeroPedidoEpodoc = "Application number EPODOC";
			NumeroPedidoOriginal = "Application number ORIGINAL";
			titulo = "Title";
			dataDeposito = "Date of Application";
			dataPublicacao = "Published Date";

		} else {
			NumeroPublicacaoDOCDB = "Número de publicação DOCDB";
			kindcodePublicacao = "Kindcode (publicação)";
			numeroPedidoDOCDB = "Número de pedido DOCDB";
			kindcodePedido = "Kindcode (pedido)";
			numeroPedidoEpodoc = "Número de pedido EPODOC";
			NumeroPedidoOriginal = "Número de pedido ORIGINAL";
			titulo = "Título";
			dataDeposito = "Data de Depósito";
			dataPublicacao = "Data de Pulicação";

		}

		// Validar Campo Numero de publicacao docdb
		if (patenteManual.getPatenteManual() == null) {
			campoObrigatorio(NumeroPublicacaoDOCDB);
			return false;
		} else if (patenteManual.getPatenteManual().isEmpty()) {
			campoObrigatorio(NumeroPublicacaoDOCDB);
			return false;
		} else if (patenteManual.getPatenteManual().length() < 2) {
			campoInvalido(NumeroPublicacaoDOCDB);
			return false;
		} else {

			List<Tbcadpais> paises = (List<Tbcadpais>) (Object) service.listAll(FIND_ALL_PAIS);
			List<String> auxPaises = new ArrayList<String>();

			for (Tbcadpais pais : paises) {
				auxPaises.add(pais.getStrCodPais());
			}

			String paisPedido = patenteManual.getPatenteManual().substring(0, 2);
			boolean ehValido = false;

			for (int i = 0; i < auxPaises.size(); i++) {
				if (auxPaises.get(i).equals(paisPedido)) {
					if (paisPedido.equals(usuarioLogado.getIdEntidadeEc().getIdPais().getStrCodPais())) {
						ehValido = true;
					}
				}
			}

			if (!ehValido) {
				campoInvalido(NumeroPublicacaoDOCDB);
				return false;
			}

			// validar numero publicacao

			Tbpatenteec tbpatenteec = service.findPatentePublicacao(patenteManual.getPatenteManual().substring(2, patenteManual.getPatenteManual().length()));

			if (tbpatenteec != null) {
				campoInvalido(NumeroPublicacaoDOCDB);
				return false;
			}

		}

		// Validar kindCode(publicacao)
		if (patenteManual.getStrPubKindDocdb() == null) {
			campoObrigatorio(kindcodePublicacao);
			return false;
		} else if (patenteManual.getStrPubKindDocdb().isEmpty()) {
			campoObrigatorio(kindcodePublicacao);
			return false;
		}

		// validar numero de pedido docdb
		if (patenteManual.getPatenteManual2() == null) {
			campoObrigatorio(numeroPedidoDOCDB);
			return false;
		} else if (patenteManual.getPatenteManual2().isEmpty()) {
			campoObrigatorio(numeroPedidoDOCDB);
			return false;
		} else if (patenteManual.getPatenteManual2().length() < 2) {
			campoInvalido(numeroPedidoDOCDB);
			return false;
		} else {
			List<Tbcadpais> paises = (List<Tbcadpais>) (Object) service.listAll(FIND_ALL_PAIS);
			List<String> auxPaises = new ArrayList<String>();

			for (Tbcadpais pais : paises) {
				auxPaises.add(pais.getStrCodPais());
			}

			String paisPedido = patenteManual.getPatenteManual2().substring(0, 2);
			boolean ehValido = false;

			for (int i = 0; i < auxPaises.size(); i++) {
				if (auxPaises.get(i).equals(paisPedido)) {
					if (paisPedido.equals(usuarioLogado.getIdEntidadeEc().getIdPais().getStrCodPais())) {
						ehValido = true;
					}
				}
			}

			if (!ehValido) {
				campoInvalido(numeroPedidoDOCDB);
				return false;
			}

			// validar numero pedido docdb

			Tbpatenteec tbpatenteec = service.findPatentePorPedido(patenteManual.getPatenteManual2().substring(2, patenteManual.getPatenteManual2().length()));

			if (tbpatenteec != null) {
				campoInvalido(numeroPedidoDOCDB);
				return false;
			}

		}

		// validar kindcode(pedido)
		if (patenteManual.getStrPedKindDocdb() == null) {
			campoObrigatorio(kindcodePedido);
			return false;
		} else if (patenteManual.getStrPedKindDocdb().isEmpty()) {
			campoObrigatorio(kindcodePedido);
			return false;
		}

		// validar pedido epodoc
		if (patenteManual.getStrPedNumEpodoc() == null) {
			campoObrigatorio(numeroPedidoEpodoc);
			return false;
		} else if (patenteManual.getStrPedNumEpodoc().isEmpty()) {
			campoObrigatorio(numeroPedidoEpodoc);
			return false;
		} else {
			// validar numero epodoc

			Tbpatenteec tbpatenteec = service.findPatentePorEpodoc(patenteManual.getStrPedNumEpodoc());

			if (tbpatenteec != null) {
				campoInvalido(numeroPedidoEpodoc);
				return false;
			}
		}

		// validar pedido original
		if (patenteManual.getStrPedNumOriginal() == null) {
			campoObrigatorio(NumeroPedidoOriginal);
			return false;
		} else if (patenteManual.getStrPedNumOriginal().isEmpty()) {
			campoObrigatorio(NumeroPedidoOriginal);
			return false;
		} else {
			Tbpatenteec tbpatenteec = service.findPatentePorOriginal(patenteManual.getStrPedNumOriginal());

			if (tbpatenteec != null) {
				campoInvalido(NumeroPedidoOriginal);
				return false;
			}
		}

		// validar titulo
		if (patenteManual.getTxTitulo() == null) {
			campoObrigatorio(titulo);
			return false;
		} else if (patenteManual.getTxTitulo().isEmpty()) {
			campoObrigatorio(titulo);
			return false;
		}

		// validar de deposito
		if (patenteManual.getDtDeposito() == null) {
			campoObrigatorio(dataDeposito);
			return false;
		}

		// validar de publicacao
		if (patenteManual.getDtPublicacao() == null) {
			campoObrigatorio(dataPublicacao);
			return false;
		}

		return true;
	}

	public void voltarFamiliaPatenteManual() {
		patenteManual = new Tbpatenteec();
		loadClassificacao();

		classificacaoTextoAux = new ArrayList<String>();

		packiClassificacao = new DualListModel<String>(classificacaoTexto, classificacaoTextoAux);

		EPECUtil.redirecionar("/epecWeb/pages/manterFamilia.jsf");

	}

	public String getNumeroPatente() {
		return numeroPatente;
	}

	public void setNumeroPatente(String numeroPatente) {
		this.numeroPatente = numeroPatente;
	}

	public Tbfamiliaec getFamilia() {

		getHashParametro().put("idFamiliaEc", Long.parseLong(this.numerofamilia));

		familia = (Tbfamiliaec) service.findOneResult(FIND_FAMILIA, hashParametro);

		return familia;
	}

	public void setFamilia(Tbfamiliaec familia) {
		this.familia = familia;
	}

	@SuppressWarnings("unchecked")
	public List<Tbpatenteec> getPatentes() {

		if (familia == null) {
			familia = getFamilia();
		}

		for (int i = 0; familia.getTbpatenteecList().size() > i; i++) {

			getHashParametro().put("idPatente", familia.getTbpatenteecList().get(i).getIdPatenteEc());
			List<Tbrelatoriopatente> relatorioPatente = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_PATENTE, hashParametro);

			if (relatorioPatente.size() == 0) {
				familia.getTbpatenteecList().get(i).setTbrelatorioec(null);

				familia.getTbpatenteecList().get(i).setEmColaboracao("");
			} else if (relatorioPatente.size() == 1) {

				getHashParametro().put("idRelatorioEc", relatorioPatente.get(0).getTbRelatorioPatentePK().getIdRelatorio());
				Tbrelatorioec tbrelatorioec = (Tbrelatorioec) service.findOneResult(FIND_BY_ID_RELATORIO, hashParametro);

				familia.getTbpatenteecList().get(i).setTbrelatorioec(tbrelatorioec);
				familia.getTbpatenteecList().get(i).setEmColaboracao("Não");

				getHashParametro().put("idRelatorio", tbrelatorioec.getIdRelatorioEc());
				List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);
				for (int b = 0; b < relatorioColaboracao.size(); b++) {

					getHashParametro().put("idColaboracao", relatorioColaboracao.get(b).getColaboracaoPK().getIdColaboracao());
					List<Tbcolaboracaoentidade> tbcolaboEntidades = (List<Tbcolaboracaoentidade>) (Object) service.list(FIND_ENTIDADE_COLABORACAO, hashParametro);

					for (int f = 0; f < tbcolaboEntidades.size(); f++) {
						getHashParametro().put("idEntidadeEc", tbcolaboEntidades.get(f).getIdEntidadeEc());
						Tbcadentidade tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);

		
						if ("Visitante".equals(usuarioLabel)) {
							familia.getTbpatenteecList().get(i).setEmColaboracao("Não");
						}else{
							if (tbcadentidade.equals(usuarioLogado.getIdEntidadeEc())) {
								familia.getTbpatenteecList().get(i).setEmColaboracao("Sim");
							}
						}
						
						
					}
				}

			} else {

				Tbrelatorioec tbrelatorioecNovo = null;

				for (int p = 0; p < relatorioPatente.size(); p++) {
					getHashParametro().put("idRelatorioEc", relatorioPatente.get(p).getTbRelatorioPatentePK().getIdRelatorio());
					Tbrelatorioec tbrelatorioec = (Tbrelatorioec) service.findOneResult(FIND_BY_ID_RELATORIO, hashParametro);
					// teste

					if (tbrelatorioec.getIdStatus().getIdStatus() != 3L) {
						tbrelatorioecNovo = tbrelatorioec;
					}
				}

				if (tbrelatorioecNovo == null) {
					for (int p = 0; p < relatorioPatente.size(); p++) {
						getHashParametro().put("idRelatorioEc", relatorioPatente.get(p).getTbRelatorioPatentePK().getIdRelatorio());
						Tbrelatorioec tbrelatorioec = (Tbrelatorioec) service.findOneResult(FIND_BY_ID_RELATORIO, hashParametro);
						// teste

						if (tbrelatorioecNovo == null) {
							tbrelatorioecNovo = tbrelatorioec;
						} else if (tbrelatorioecNovo.getDtStatus().before(tbrelatorioec.getDtStatus())) {
							tbrelatorioecNovo = tbrelatorioec;
						}

					}
				}

				familia.getTbpatenteecList().get(i).setTbrelatorioec(tbrelatorioecNovo);

				familia.getTbpatenteecList().get(i).setEmColaboracao("Não");

				getHashParametro().put("idRelatorio", tbrelatorioecNovo.getIdRelatorioEc());
				List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);
				for (int b = 0; b < relatorioColaboracao.size(); b++) {

					getHashParametro().put("idColaboracao", relatorioColaboracao.get(b).getColaboracaoPK().getIdColaboracao());
					List<Tbcolaboracaoentidade> tbcolaboEntidades = (List<Tbcolaboracaoentidade>) (Object) service.list(FIND_ENTIDADE_COLABORACAO, hashParametro);

					for (int f = 0; f < tbcolaboEntidades.size(); f++) {
						getHashParametro().put("idEntidadeEc", tbcolaboEntidades.get(f).getIdEntidadeEc());
						Tbcadentidade tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);

						if (tbcadentidade.equals(usuarioLogado.getIdEntidadeEc())) {
							familia.getTbpatenteecList().get(i).setEmColaboracao("Sim");
						}
					}
				}
			}

		}

		return familia.getTbpatenteecList();

	}

	public void setPatentes(List<Tbpatenteec> patentes) {
		this.patentes = patentes;
	}

	@SuppressWarnings("unchecked")
	public List<Tbrelatorioec> getRelatorioSemVinculado() {

		String auxUsuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("isAdminstrador");

		if (auxUsuario != "4") {
			List<Tbrelatorioec> relatorios = new ArrayList<>();
			List<Tbrelatorioec> relatorios2 = getRelatorio();

			for (int r = 0; r < relatorios2.size(); r++) {
				getHashParametro().put("idRelatorio", relatorios2.get(r).getIdRelatorioEc());
				List<Tbrelatoriopatente> relatorioPatente = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_RELATORIO, hashParametro);

				if (relatorioPatente.size() == 0) {
					relatorios2.get(r).setEmColaboracao("Não");

					getHashParametro().put("idRelatorio", relatorios2.get(r).getIdRelatorioEc());
					List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);
					for (int b = 0; b < relatorioColaboracao.size(); b++) {

						getHashParametro().put("idColaboracao", relatorioColaboracao.get(b).getColaboracaoPK().getIdColaboracao());
						List<Tbcolaboracaoentidade> tbcolaboEntidades = (List<Tbcolaboracaoentidade>) (Object) service.list(FIND_ENTIDADE_COLABORACAO, hashParametro);

						for (int f = 0; f < tbcolaboEntidades.size(); f++) {
							getHashParametro().put("idEntidadeEc", tbcolaboEntidades.get(f).getIdEntidadeEc());
							Tbcadentidade tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);

							if (tbcadentidade.equals(usuarioLogado.getIdEntidadeEc())) {
								relatorios2.get(r).setEmColaboracao("Sim");
							}
						}
					}

					relatorios.add(relatorios2.get(r));
				}
			}

			return relatorios;
		} else {
			List<Tbrelatorioec> relatorios = new ArrayList<>();
			List<Tbrelatorioec> relatorios2 = getRelatorio();

			for (int r = 0; r < relatorios2.size(); r++) {
				getHashParametro().put("idRelatorio", relatorios2.get(r).getIdRelatorioEc());
				List<Tbrelatoriopatente> relatorioPatente = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_RELATORIO, hashParametro);

				if (relatorioPatente.size() == 0) {
					if (relatorios2.get(r).getBPublico()) {
						relatorios.add(relatorios2.get(r));
					}

				}
			}

			return relatorios;
		}

	}

	public void setRelatorioSemVinculado(List<Tbrelatorioec> relatorioSemVinculado) {
		this.relatorioSemVinculado = relatorioSemVinculado;
	}

	public List<Tbrelatorioec> getRelatorio() {

		getHashParametro().put("idFamiliaEc", Long.parseLong(numerofamilia));
		relatorio = service.relatorioFamilia(FIND_RELATORIO_FAMILIA, hashParametro);

		return relatorio;
	}

	public void setRelatorio(List<Tbrelatorioec> relatorio) {
		this.relatorio = relatorio;
	}

	public Tbrelatorioec getTbrelatorioec() {
		return tbrelatorioec;
	}

	public void setTbrelatorioec(Tbrelatorioec tbrelatorioec) {
		this.tbrelatorioec = tbrelatorioec;
	}

	@SuppressWarnings("unchecked")
	public List<Tbrelatorioec> getPatentesComRelatorio() {

		String auxUsuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("isAdminstrador");

		if (auxUsuario != "4") {
			List<Tbrelatorioec> relatorios = new ArrayList<>();
			List<Tbrelatorioec> relatorios2 = getRelatorio();

			for (int r = 0; r < relatorios2.size(); r++) {
				getHashParametro().put("idRelatorio", relatorios2.get(r).getIdRelatorioEc());
				List<Tbrelatoriopatente> relatorioPatente = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_RELATORIO, hashParametro);

				if (relatorioPatente.size() > 0) {
					relatorios2.get(r).setEmColaboracao("Não");

					getHashParametro().put("idRelatorio", relatorios2.get(r).getIdRelatorioEc());
					List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);
					for (int b = 0; b < relatorioColaboracao.size(); b++) {

						getHashParametro().put("idColaboracao", relatorioColaboracao.get(b).getColaboracaoPK().getIdColaboracao());
						List<Tbcolaboracaoentidade> tbcolaboEntidades = (List<Tbcolaboracaoentidade>) (Object) service.list(FIND_ENTIDADE_COLABORACAO, hashParametro);

						for (int f = 0; f < tbcolaboEntidades.size(); f++) {
							getHashParametro().put("idEntidadeEc", tbcolaboEntidades.get(f).getIdEntidadeEc());
							Tbcadentidade tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);

							if (tbcadentidade.equals(usuarioLogado.getIdEntidadeEc())) {
								relatorios2.get(r).setEmColaboracao("Sim");
							}
						}
					}
					relatorios.add(relatorios2.get(r));
				}
			}

			return relatorios;
		} else {
			List<Tbrelatorioec> relatorios = new ArrayList<>();
			List<Tbrelatorioec> relatorios2 = getRelatorio();

			for (int r = 0; r < relatorios2.size(); r++) {
				getHashParametro().put("idRelatorio", relatorios2.get(r).getIdRelatorioEc());
				List<Tbrelatoriopatente> relatorioPatente = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_RELATORIO, hashParametro);

				if (relatorioPatente.size() > 0) {
					if (relatorios2.get(r).getBPublico()) {
						relatorios.add(relatorios2.get(r));
					}

				}
			}

			return relatorios;
		}

	}

	public void setPatentesComRelatorio(List<Tbrelatorioec> patentesComRelatorio) {
		this.patentesComRelatorio = patentesComRelatorio;
	}

	private List<Tbcomentariosfamiliaec> comentarios;

	public List<Tbcomentariosfamiliaec> getComentarios() {
		getHashParametro().put("idFamiliaEc", familia.getIdFamiliaEc());

		comentarios = service.comentariosFamilia(getFamilia());
		return comentarios;
	}

	public void setComentarios(List<Tbcomentariosfamiliaec> comentarios) {
		this.comentarios = comentarios;
	}

	private boolean enviarMensagem;

	public boolean isEnviarMensagem() {
		return enviarMensagem;
	}

	public void setEnviarMensagem(boolean enviarMensagem) {
		this.enviarMensagem = enviarMensagem;
	}

	public void salvarComentario() {
		Tbcomentariosfamiliaec comentario = new Tbcomentariosfamiliaec();
		comentario.setDtRegistro(new Date());
		comentario.setTxComentario(textoComentario);
		comentario.setIdFamiliaEc(familia);

		obterUsuarioSessao1();

		comentario.setIdUsuario(usuarioLogado);

		service.save(comentario);

		if (isEnviarMensagem()) {
			List<Tbrelatorioec> relatorio = getRelatorio();

			@SuppressWarnings("unused")
			String destino = "";
			List<Tbcadusuario> usuario = new ArrayList<>();
			for (int i = 0; i < relatorio.size(); i++) {
				if (!(usuario.contains(relatorio.get(i).getIdUsuario()))) {
					usuario.add(relatorio.get(i).getIdUsuario());
				}
			}
			mensagem.setMensagem("Usuário: " + usuarioLogado.getStrUsuario().toString() + "--" + "País: " + usuarioLogado.getIdEntidadeEc().getIdPais().getStrNomePais().toString() + "--" + "Data: "
					+ comentario.getDtRegistro().toString() + " \n" + textoComentario);
			mensagem.setTitulo("Comentários da Família");
			for (int i = 0; i < usuario.size(); i++) {
				if (validarEmail(usuario.get(i).getStrEmail())) {
					mensagem.setDestino(usuario.get(i).getStrEmail());
					emailUtils.sendMail(mensagem);
				}

			}
		}

		textoComentario = null;

		salvarLogFamilia(familia.getIdFamiliaEc(), "Inserir", "Comentário", usuarioLogado.getStrUsuario(), usuarioLogado.getStrUsuario());
		ComentarioEnviadoComSucesso();

	}

	private boolean validarEmail(String email) {
		boolean isValido = Boolean.FALSE;
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		isValido = matcher.find();
		return isValido;
	}

	public String getTextoComentario() {
		return textoComentario;
	}

	public void setTextoComentario(String textoComentario) {
		this.textoComentario = textoComentario;
	}

	@SuppressWarnings("unchecked")
	public List<Tblogfamilia> getLogFamilia() {

		getHashParametro().put("idFamiliaEc", Long.parseLong(numerofamilia));
		logFamilia = (List<Tblogfamilia>) (Object) service.list(FIND_LOGFAMILIA_BY_FAMILIAID, hashParametro);

		for (int i = 0; i < logFamilia.size(); i++) {

			logFamilia.get(i).setUsuario(service.findUsuarioPorNome(logFamilia.get(i).getStrUsuario()));
		}

		return logFamilia;

	}

	public void setLogFamilia(List<Tblogfamilia> logFamilia) {
		this.logFamilia = logFamilia;
	}

	public void inserirPedidoManualmente() {

		patenteManual = new Tbpatenteec();
		EPECUtil.redirecionar("/epecWeb/pages/inserirPedidoManualmente.jsf");

	}



	public mensagemEmail getMensagem() {
		return mensagem;
	}

	public void setMensagem(mensagemEmail mensagem) {
		this.mensagem = mensagem;
	}

	public void enviarMensagemFamilia() {

		if (mensagem.getMensagem().isEmpty() || mensagem.getTitulo().isEmpty()) {

			dadosIncorretosVerifique();

		} else {

			List<Tbrelatorioec> relatorio = getRelatorio();

			@SuppressWarnings("unused")
			String destino = "";
			List<Tbcadusuario> usuario = new ArrayList<>();
			for (int i = 0; i < relatorio.size(); i++) {
				if (!(usuario.contains(relatorio.get(i).getIdUsuario()))) {
					usuario.add(relatorio.get(i).getIdUsuario());
				}
			}

			for (int i = 0; i < usuario.size(); i++) {
				if(validarEmail(usuario.get(i).getStrEmail())){
					mensagem.setDestino(usuario.get(i).getStrEmail());
					emailUtils.sendMail(mensagem);
				}				
				
			}

			
			mensagem.setMensagem("");
			mensagem.setTitulo("");
			mensagemEnviadaComSucesso();

			
		}

	}
	
	
	public void mensagemFamilia() {
		mensagem.setMensagem("");
		mensagem.setTitulo("");
		EPECUtil.redirecionar("/epecWeb/pages/mensagemFamilia.jsf");
	}
	
	
	public void comentarioFamilia() {
		mensagem.setMensagem("");
		mensagem.setTitulo("");
		EPECUtil.redirecionar("/epecWeb/pages/comentarioFamilia.jsf");
	}

	public String getNumerofamilia() {
		return numerofamilia;
	}

	public void setNumerofamilia(String numerofamilia) {
		this.numerofamilia = numerofamilia;
	}

	public Tbpatenteec getPatenteManual() {

		return patenteManual;
	}

	public void setPatenteManual(Tbpatenteec patenteManual) {
		this.patenteManual = patenteManual;
	}

	@PostConstruct
	public void init() {

		if (inventor == null) {
			loadInventor();
		}

		if (depositante == null) {
			loadDepositante();
		}

		if (classificacao == null) {
			loadClassificacao();
		}
		usuarioLogado = getUsuarioLogado();

		classificacaoTextoAux = new ArrayList<String>();

		packiClassificacao = new DualListModel<String>(classificacaoTexto, classificacaoTextoAux);
				
		usuarioLabel = (String) session.getAttribute("usuario");
	}

	@SuppressWarnings("unchecked")
	private void loadInventor() {
		inventor = (List<Tbinventor>) (Object) service.listAll(FIND_ALL_INVENTOR);
	}

	public List<Tbinventor> getInventor() {
		return inventor;
	}

	public void setInventor(List<Tbinventor> inventor) {
		this.inventor = inventor;
	}

	public List<Tbinventor> getEscolherInventor() {
		if (escolherInventor == null) {
			loadInventor();
		}
		return inventor;
	}

	public void setEscolherInventor(List<Tbinventor> escolherInventor) {
		this.escolherInventor = escolherInventor;
	}

	public Tbinventor getInventorCreate() {
		return inventorCreate;
	}

	public void setInventorCreate(Tbinventor inventorCreate) {
		this.inventorCreate = inventorCreate;
	}

	public void salvarInventor() {

		if (inventorCreate.getTxInventor() == null) {

			nomeDoinventorEmBranco();

		} else if (inventorCreate.getTxInventor().isEmpty()) {
			nomeDoinventorEmBranco();
		} else {
			Tbinventor a = service.findInventorPorNome(inventorCreate.getTxInventor());

			if (a == null) {
				inventorCreate.setTxInventor(inventorCreate.getTxInventor().toUpperCase());
				service.save(inventorCreate);
				loadInventor();
				inventorCreate = new Tbinventor();
			} else {
				OhValoraExiste(inventorCreate.getTxInventor());
			}

		}

		inventorCreate = new Tbinventor();

	}

	// depositante

	@SuppressWarnings("unchecked")
	private void loadDepositante() {
		depositante = (List<Tbdepositante>) (Object) service.listAll(FIND_ALL_DEPOSITANTE);
	}

	public List<Tbdepositante> getDepositante() {
		return depositante;
	}

	public void setDepositante(List<Tbdepositante> depositante) {
		this.depositante = depositante;
	}

	public List<Tbdepositante> getEscolherDepositante() {
		if (escolherDepositante == null) {
			loadDepositante();
		}
		return depositante;
	}

	public void setEscolherDepositante(List<Tbdepositante> escolherDepositante) {
		this.escolherDepositante = escolherDepositante;
	}

	public Tbdepositante getDepositanteCreate() {
		return depositanteCreate;
	}

	public void setDepositanteCreate(Tbdepositante depositanteCreate) {
		this.depositanteCreate = depositanteCreate;
	}

	public void salvarDepositante() {
		if (depositanteCreate.getTxDepositante() == null) {

			nomeDepositanteEmBranco();

		} else if (depositanteCreate.getTxDepositante().isEmpty()) {

			nomeDepositanteEmBranco();

		} else {
			Tbdepositante b = service.findDepositantePorNome(depositanteCreate.getTxDepositante());

			if (b == null) {
				depositanteCreate.setTxDepositante(depositanteCreate.getTxDepositante().toUpperCase());
				service.save(depositanteCreate);
				loadDepositante();
				depositanteCreate = new Tbdepositante();
			} else {
				OhValoraExiste(depositanteCreate.getTxDepositante());
			}

		}
	}

	public void salvarClassificacao() {
		if (classificacaoCreate.getTxClassificacao() == null) {

			nomeClassificaoEmBranco();

		} else if (classificacaoCreate.getTxClassificacao().isEmpty()) {

			nomeClassificaoEmBranco();

		} else {
			Tbclassificacao c = service.findClassificacaoPorNome(classificacaoCreate.getTxClassificacao());

			if (c == null) {
				classificacaoCreate.setTxClassificacao(classificacaoCreate.getTxClassificacao().toUpperCase());
				service.save(classificacaoCreate);
				loadClassificacao();
				packiClassificacao.setSource(classificacaoTexto);
				classificacaoCreate = new Tbclassificacao();

			} else {
				OhValoraExiste(classificacaoCreate.getTxClassificacao());
			}

		}
	}

	// /classifica

	@SuppressWarnings("unchecked")
	private void loadClassificacao() {

		classificacao = (List<Tbclassificacao>) (Object) service.listAll(FIND_ALL_CLASSIFICACAO);

		classificacaoTexto = new ArrayList<>();
		for (int i = 0; i < classificacao.size(); i++) {

			classificacaoTexto.add(classificacao.get(i).getTxClassificacao());
		}

	}

	public List<Tbclassificacao> getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(List<Tbclassificacao> classificacao) {
		this.classificacao = classificacao;
	}

	public List<Tbclassificacao> getEscolherClassificacao() {
		if (escolherClassificacao == null) {
			loadClassificacao();
		}
		return classificacao;
	}

	public void setEscolherClassificacao(List<Tbclassificacao> escolherClassificacao) {
		this.escolherClassificacao = escolherClassificacao;
	}

	public List<Tbclassificacao> getClassificaoSalvar() {
		return classificaoSalvar;
	}

	public void setClassificaoSalvar(List<Tbclassificacao> classificaoSalvar) {
		this.classificaoSalvar = classificaoSalvar;
	}

	public DualListModel<String> getPackiClassificacao() {
		if (packiClassificacao == null) {
			loadClassificacao();

			classificacaoTextoAux = new ArrayList<String>();

			packiClassificacao = new DualListModel<String>(classificacaoTexto, classificacaoTextoAux);

		}

		return packiClassificacao;
	}

	public void setPackiClassificacao(DualListModel<String> packiClassificacao) {
		this.packiClassificacao = packiClassificacao;
	}

	public List<Tbclassificacao> getClassificacaoAux() {

		return classificacaoAux;
	}

	public void setClassificacaoAux(List<Tbclassificacao> classificacaoAux) {
		this.classificacaoAux = classificacaoAux;
	}

	public List<Tbcadentidade> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<Tbcadentidade> entidades) {
		this.entidades = entidades;
	}

	public String getNumeroRelatorio() {
		return numeroRelatorio;
	}

	public void setNumeroRelatorio(String numeroRelatorio) {
		this.numeroRelatorio = numeroRelatorio;
	}

	public String getRelatorioCopiar() {
		return relatorioCopiar;
	}

	public void setRelatorioCopiar(String relatorioCopiar) {
		this.relatorioCopiar = relatorioCopiar;
	}

	public String getPedidoRelatorioParam(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("pedidoRelatorio");
	}

	@SuppressWarnings("unchecked")
	private boolean relatorioAcesso(Tbcadusuario tbcadusuario, Tbrelatorioec tbrelatorioec, Tbpatenteec tbpatenteec, boolean ehVisualizar) {

		boolean teste = false;

		// As colaborações associadas
		getHashParametro().put("idRelatorio", Long.parseLong(numeroRelatorio));
		List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);
		for (int i = 0; i < relatorioColaboracao.size(); i++) {

			getHashParametro().put("idColaboracao", relatorioColaboracao.get(i).getColaboracaoPK().getIdColaboracao());
			List<Tbcolaboracaoentidade> tbcolaboEntidades = (List<Tbcolaboracaoentidade>) (Object) service.list(FIND_ENTIDADE_COLABORACAO, hashParametro);

			for (int f = 0; f < tbcolaboEntidades.size(); f++) {
				getHashParametro().put("idEntidadeEc", tbcolaboEntidades.get(f).getIdEntidadeEc());
				Tbcadentidade tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);

				if (tbcadentidade.equals(usuarioLogado.getIdEntidadeEc())) {
					teste = true;
				}
			}
		}

		if (tbpatenteec != null) {
			if (tbpatenteec.getIdEntidadeEc().equals(tbrelatorioec.getIdUsuario().getIdEntidadeEc())) {
				if (tbrelatorioec.getIdUsuario().equals(tbcadusuario)) {
					teste = true;

				} else if (tbrelatorioec.getIdUsuario().getIdEntidadeEc().equals(tbcadusuario.getIdEntidadeEc())) {
					teste = true;
				}
			}
		} else {
			if (tbrelatorioec.getIdUsuario().equals(tbcadusuario)) {
				teste = true;
			} else if (tbrelatorioec.getIdUsuario().getIdEntidadeEc().equals(tbcadusuario.getIdEntidadeEc())) {
				teste = true;
			}
		}

		// relatorio Publico
		if (tbrelatorioec.getBPublico()) {
			teste = true;
		}

		return teste;
	}

	@SuppressWarnings("unchecked")
	public void vincularRelatorioPatente() {
		// TODO
		boolean vinculo;

		FacesContext fc = FacesContext.getCurrentInstance();
		this.pedidoRelatorio = getPedidoRelatorioParam(fc);

		obterUsuarioSessao1();

		if (numeroRelatorio == null) {
			this.numeroRelatorio = getNumeroRelatorioParam(fc);
		}
		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));

		tbrelatorioec = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

		if (relatorioAcesso(usuarioLogado, tbrelatorioec, null, false)) {
			this.copiarRelatorioPatente = getCopiarRelatorioPatente();

			getHashParametro().put("idRelatorio", tbrelatorioec.getIdRelatorioEc());
			List<Tbrelatoriopatente> relatorioPatente = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_RELATORIO, hashParametro);

			int teste = 0;

			copiarRelatorioPatenteTeste = new ArrayList<>();
			for (int i = 0; i < copiarRelatorioPatente.size(); i++) {
				vinculo = true;
				for (int e = 0; e < relatorioPatente.size(); e++) {
					if (copiarRelatorioPatente.get(i).getIdPatenteEc().equals(relatorioPatente.get(e).getTbRelatorioPatentePK().getIdPatente())) {
						teste++;
						vinculo = false;
					}
				}
				if (vinculo) {
					copiarRelatorioPatenteTeste.add(copiarRelatorioPatente.get(i));
				}
			}

			if (teste == copiarRelatorioPatente.size()) {
				vinculo = true;
				relatorioJaVinculado();

			} else {

				if (copiarRelatorioPatenteTeste.size() > 0) {
					EPECUtil.redirecionar("/epecWeb/pages/vincularRelatorio.jsf");
				} else {
					operacaoNaoPodeSerRealizada();
				}

			}

		} else {

			relatorioNaoPodeVincularOuCopiar();

		}

	}

	@SuppressWarnings("unchecked")
	public void salvarVinculoRelatorioPatente() {

		getHashParametro().put("idPatente", Long.parseLong(vincularRelatorio));
		List<Tbrelatoriopatente> relatorioPatente = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_PATENTE, hashParametro);

		for (int e = 0; e < relatorioPatente.size(); e++) {
			getHashParametro().put("idRelatorioEc", relatorioPatente.get(e).getTbRelatorioPatentePK().getIdRelatorio());
			Tbrelatorioec relatorio2 = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

			if (relatorio2.getIdStatus().getIdStatus() != 3L) {
				service.delete(relatorioPatente.get(e));
			}

		}

		if ((relatorioCopiar != null) && (vincularRelatorio != null)) {
			Tbrelatoriopatente tbrelatoriopatente = new Tbrelatoriopatente();
			TbRelatorioPatentePK patentePK = new TbRelatorioPatentePK();

			patentePK.setIdPatente(Long.parseLong(vincularRelatorio));
			patentePK.setIdRelatorio(Long.parseLong(relatorioCopiar));

			tbrelatoriopatente.setTbRelatorioPatentePK(patentePK);
			service.save(tbrelatoriopatente);

		}

		getHashParametro().put("idRelatorioEc", Long.parseLong(relatorioCopiar));
		Tbrelatorioec relatorio2 = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

		getHashParametro().put("idPatenteEc", Long.parseLong(vincularRelatorio));

		Tbpatenteec patente = (Tbpatenteec) service.findOneResult(FIND_BY_ID_PEDIDO, hashParametro);

		String teste = relatorio2.getStrRelatorio().toString() + relatorio2.getIdRelatorioEc().toString();

		salvarLogRelatorio(Long.parseLong(relatorioCopiar), "Vincular", "Pedido", patente.getNumeroPedido(), usuarioLogado.getStrUsuario());
		salvarLogFamilia(familia.getIdFamiliaEc(), "Vincular", "Relatório", teste, usuarioLogado.getStrUsuario());

		vincularRelatorio = null;

		// verificarLogFamilia
		verificarTodosRelatoriosAssociados();

		salvarPedidoManual = "2";

		EPECUtil.redirecionar("/epecWeb/pages/manterFamilia.jsf");

	}

	private void verificarTodosRelatoriosAssociados() {
		int cont = 0;
		int rela = 0;

		getHashParametro().put("idFamiliaEc", familia.getIdFamiliaEc());
		List<Tbrelatorioec> relatorios2 = service.relatorioFamilia(FIND_RELATORIO_FAMILIA, hashParametro);

		for (int r = 0; r < relatorios2.size(); r++) {
			getHashParametro().put("idRelatorio", relatorios2.get(r).getIdRelatorioEc());
			List<Tbrelatoriopatente> relatorioPatente = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_RELATORIO, hashParametro);

			if (relatorioPatente.size() > 0) {
				rela++;
				if (relatorios2.get(r).getBPublico()) {
					cont++;
				}

			}
		}

		if (cont == rela && rela > 0) {
			if (!(familia.getPublico())) {
				familia.setPublico(true);
				service.update(familia);

				salvarLogFamilia(familia.getIdFamiliaEc(), "Publicar", "Família", "-", getUsuarioLogado().getStrUsuario());

			}
		} else {
			if (familia.getPublico()) {
				familia.setPublico(false);
				service.update(familia);

				salvarLogFamilia(familia.getIdFamiliaEc(), "Privado", "Família", "-", getUsuarioLogado().getStrUsuario());

			}
		}

	}

	public List<Tbpatenteec> getCopiarRelatorioPatente() {
		obterUsuarioSessao1();
		copiarRelatorioPatente = new ArrayList<>();

		patentes = getPatentes();
		for (int i = 0; i < patentes.size(); i++) {
			if (patentes.get(i).getIdEntidadeEc().equals(usuarioLogado.getIdEntidadeEc())) {
				if (patentes.get(i).getTbrelatorioec() != null) {
					if (patentes.get(i).getTbrelatorioec().getIdUsuario().getIdEntidadeEc().equals(patentes.get(i).getIdEntidadeEc())) {
						if (patentes.get(i).getTbrelatorioec().getIdUsuario().equals(usuarioLogado)) {
							copiarRelatorioPatente.add(patentes.get(i));
						} else if (!(patentes.get(i).getTbrelatorioec().isNaoFinalizado())) {
							copiarRelatorioPatente.add(patentes.get(i));
						}

					} else {
						copiarRelatorioPatente.add(patentes.get(i));

					}

				} else {
					copiarRelatorioPatente.add(patentes.get(i));
				}

			}
		}

		return copiarRelatorioPatente;

	}

	public void setCopiarRelatorioPatente(List<Tbpatenteec> copiarRelatorioPatente) {
		this.copiarRelatorioPatente = copiarRelatorioPatente;
	}

	public List<Tbpatenteec> getCopiarRelatorioPatenteTeste() {
		return copiarRelatorioPatenteTeste;

	}

	public void setCopiarRelatorioPatenteTeste(List<Tbpatenteec> copiarRelatorioPatenteTeste) {
		this.copiarRelatorioPatenteTeste = copiarRelatorioPatenteTeste;
	}

	public List<String> getColaboracoesCopiarRelatorio() {
		return colaboracoesCopiarRelatorio;
	}

	public void setColaboracoesCopiarRelatorio(List<String> colaboracoesCopiarRelatorio) {
		this.colaboracoesCopiarRelatorio = colaboracoesCopiarRelatorio;
	}

	public String getVincularRelatorio() {
		return vincularRelatorio;
	}

	public void setVincularRelatorio(String vincularRelatorio) {

		if (this.vincularRelatorio == null || "".equals(this.vincularRelatorio)) {
			this.vincularRelatorio = vincularRelatorio;
		}
	}

	public Tbrelatorioec getRelatorioInserir() {
		return relatorioInserir;
	}

	public void setRelatorioInserir(Tbrelatorioec relatorioInserir) {
		this.relatorioInserir = relatorioInserir;
	}

	public Tbrelatorioec getRelatorioInserirNovo() {
		return relatorioInserirNovo;
	}

	public void setRelatorioInserirNovo(Tbrelatorioec relatorioInserirNovo) {
		this.relatorioInserirNovo = relatorioInserirNovo;
	}

	@SuppressWarnings("unchecked")
	public List<Tbcadusuario> getUsuarios() {
		// usuario Logado

		List<Tbcadusuario> tbcadusuario = new ArrayList<>();

		usuarios = (List<Tbcadusuario>) (Object) service.listAll(FIND_ALL_USERS);

		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getIdEntidadeEc().equals(tbrelatorioec.getIdUsuario().getIdEntidadeEc())) {
				tbcadusuario.add(usuarios.get(i));
			}
		}

		return tbcadusuario;
	}

	public void setUsuarios(List<Tbcadusuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(String usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Familia> getFamilias() {
		return familias;
	}

	public void setFamilias(List<Familia> familias) {
		this.familias = familias;
	}

	public void trocarRelatorio() {

		obterUsuarioSessao1();
	
		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));

		tbrelatorioec = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

		if (tbrelatorioec.getIdStatus().getIdStatus() == 3L) {

			relatorioSelecinadonaoFinalizado();

		} else if (usuarioLogado.getIdPerfilusuario().getIdPerfilusuario().equals(1L)) {

			oUsuarioLogadoNaoPodeRealizarTalOperacao();

		} else {

			if (usuarioLogado.getIdPerfilusuario().getIdPerfilusuario().equals(2L)) {
				if (tbrelatorioec.getIdUsuario().getIdEntidadeEc().equals(usuarioLogado.getIdEntidadeEc())) {
					EPECUtil.redirecionar("/epecWeb/pages/alterarResponsavel.jsf");
				} else if (usuarioLogado.getIdEntidadeEc().getOficina()) {
					EPECUtil.redirecionar("/epecWeb/pages/alterarResponsavel.jsf");
				} else {
					oUsuarioLogadoNaoPodeRealizarTalOperacao();
				}

			} else if (usuarioLogado.getIdPerfilusuario().getIdPerfilusuario().equals(3L)) {
				EPECUtil.redirecionar("/epecWeb/pages/alterarResponsavel.jsf");

			}

		}

	}

	private void obterUsuarioSessao() {
		FacesContext fc = FacesContext.getCurrentInstance();

		session = (HttpSession) fc.getExternalContext().getSession(true);

		usuarioLogado = (Tbcadusuario) session.getAttribute("usuarioAutenticado");
	}

	public String getNumeroRelatorioParam(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("numeroRelatorio");
	}

	public void salvarTrocarUsuario() {

		
		if(usuarioSelecionado.isEmpty()){
			
			selecionarResponsavel();
		}else{
			getHashParametro().put("idUsuario", Long.parseLong(usuarioSelecionado));
			Tbcadusuario tbcadusuario = (Tbcadusuario) service.findOneResult(FIND_USUARIO, hashParametro);

			tbrelatorioec.setIdUsuario(tbcadusuario);

			service.update(tbrelatorioec);

			salvarLogRelatorio(tbrelatorioec.getIdRelatorioEc(), "Alterar", "Responsável", "-", usuarioLogado.getStrUsuario());
			String textoAtivo = tbrelatorioec.getStrRelatorio().toString() + tbrelatorioec.getIdRelatorioEc().toString();
			salvarLogFamilia(tbrelatorioec.getIdFamiliaEc().getIdFamiliaEc(), "Alterar Responsável", "Relatório", textoAtivo, usuarioLogado.getStrUsuario());
			 this.trocarUsuarioLogado= "1";
			

			EPECUtil.redirecionar("/epecWeb/pages/manterFamilia.jsf");
		}
		
	}

	public void mensagemSalvarPedidoManual() {
		
		if(trocarUsuarioLogado != null){
			if(trocarUsuarioLogado.equals("1")){
				alteradoComSucesso();
			}
		}
				
		if (salvarPedidoManual != null) {
			if (salvarPedidoManual.equals("1")) {
				incluidoComSucesso();
			}

			if (salvarPedidoManual.equals("2")) {
				relatorioVinculadoComSucesso();
			}
			if(salvarPedidoManual.equals("3")){
				mensagemEnviadaComSucesso();

			}
		}

		this.trocarUsuarioLogado = null;
		this.salvarPedidoManual = null;

	}

	public String getPedidoRelatorio() {
		return pedidoRelatorio;
	}

	public void setPedidoRelatorio(String pedidoRelatorio) {
		this.pedidoRelatorio = pedidoRelatorio;
	}

	public String getNumeroEpodoc() {

		return numeroEpodoc;
	}

	public void setNumeroEpodoc(String numeroEpodoc) {
		this.numeroEpodoc = numeroEpodoc;
	}

	public String getNumeroPublicacaoKind() {
		return numeroPublicacaoKind;
	}

	public void setNumeroPublicacaoKind(String numeroPublicacaoKind) {
		this.numeroPublicacaoKind = numeroPublicacaoKind;
	}

	public void salvarLogRelatorio(Long idRelatorio, String acao, String ativo, String textoAtivo, String usuario) {
		Tblogrelatorio tblogrelatorio = new Tblogrelatorio();
		tblogrelatorio.setDatahora(new Date());
		tblogrelatorio.setIdRelatorio(idRelatorio);
		tblogrelatorio.setStrAcao(acao);
		tblogrelatorio.setStrAtivo(ativo);
		tblogrelatorio.setTxAtivo(textoAtivo);
		tblogrelatorio.setStrUsuario(usuario);

		service.save(tblogrelatorio);
	}

	public void salvarLogFamilia(Long familia, String acao, String ativo, String textoAtivo, String usuario) {
		Tblogfamilia tblogfamilia = new Tblogfamilia();
		tblogfamilia.setDatahora(new Date());
		tblogfamilia.setIdFamiliaEc(familia);
		tblogfamilia.setStrAcao(acao);
		tblogfamilia.setStrAtivo(ativo);
		tblogfamilia.setTxAtivo(textoAtivo);
		tblogfamilia.setStrUsuario(usuario);

		service.save(tblogfamilia);
	}

	public void salvarLogPedido(String usuario, Long patente, String acao, String ativo, String textoAtivo) {
		Tblogpatente tblogpatente = new Tblogpatente();
		tblogpatente.setStrUsuario(usuario);
		tblogpatente.setIdPatenteEc(patente);
		tblogpatente.setdDatahora(new Date());
		tblogpatente.setStrAcao(acao);
		tblogpatente.setStrAtivo(ativo);
		tblogpatente.setTxAtivo(textoAtivo);

		service.save(tblogpatente);

	}

	public void obterDadosCompletoFamilia() throws Exception {
		try {

			retornarEnderecoINPADOC();

			String xmlResultBiblio = serviceConsulta.retornaXML("4", "", numeroEpodoc);


			
			if(serviceConsulta.verificarXmL(xmlResultBiblio)){
				

				if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
					displayErrorMessageToUser("Document Not Found");
				} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
					displayErrorMessageToUser("Document Not Found");
				} else {
					displayErrorMessageToUser("Documento não Encontrado");
				}

				return;
			}
			if ("HTTP Status 404".equals(xmlResultBiblio) || "not found".equals(xmlResultBiblio)) {
				if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
					displayErrorMessageToUser("Document Not Found");
				} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
					displayErrorMessageToUser("Document Not Found");
				} else {
					displayErrorMessageToUser("Documento não Encontrado");
				}
				return;
			}

			FamiliaHelper helper = new FamiliaHelper();
			this.familias = helper.obterFamilia(xmlResultBiblio, "citacao");
			EPECUtil.redirecionar("/epecWeb/pages/visualizarDadosFamilia.jsf");
		} catch (ClientProtocolException e) {
			displayErrorFatalMessageToUser("Erro na solicitação do serviço!");
		} catch (IOException e) {
			displayErrorFatalMessageToUser("Erro na solicitação do serviço!");
		}
	}

	public String retornarEnderecoINPADOC() {
		String pais = numeroEpodoc.substring(0, 2);
		String numerodocdb = numeroEpodoc.substring(2);
		return "http://worldwide.espacenet.com/publicationDetails/inpadocPatentFamily?CC=" + pais + "&NR=" + numerodocdb + this.numeroPublicacaoKind + "&FT=D&ND=&DB=&&locale=en_EP";
	}

	public Tbclassificacao getClassificacaoCreate() {
		return classificacaoCreate;
	}

	public void setClassificacaoCreate(Tbclassificacao classificacaoCreate) {
		this.classificacaoCreate = classificacaoCreate;
	}

	public String getRetornarLinkPedido() {
		return retornarLinkPedido;
	}

	public void teste(String teste){
		this.retornarLinkPedido = teste;
		System.out.println(retornarLinkPedido);
		
	}
	
	public void setRetornarLinkPedido(String retornarLinkPedido) {
		this.retornarLinkPedido = retornarLinkPedido;
	}
	
	
	
	public String getRetornarLinkPdf() {
		return retornarLinkPdf;
	}

	public void teste02(String teste){
		this.retornarLinkPdf = teste;
		System.out.println(retornarLinkPdf);
		
	}
	
	public void setRetornarLinkPdf(String retornarLinkPdf) {
		this.retornarLinkPdf = retornarLinkPdf;
	}

}
