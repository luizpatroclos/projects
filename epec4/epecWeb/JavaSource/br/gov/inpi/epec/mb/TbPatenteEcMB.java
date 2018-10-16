package br.gov.inpi.epec.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

import br.gov.inpi.epec.beans.Tbcadentidade;
import br.gov.inpi.epec.beans.Tbcadpais;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbclassificacao;
import br.gov.inpi.epec.beans.Tbclassificacaopatente;
import br.gov.inpi.epec.beans.TbclassificacaopatentePK;
import br.gov.inpi.epec.beans.Tbcolaboracaoentidade;
import br.gov.inpi.epec.beans.Tbdepositante;
import br.gov.inpi.epec.beans.Tbdepositantepatente;
import br.gov.inpi.epec.beans.Tbfamiliaec;
import br.gov.inpi.epec.beans.Tbinventor;
import br.gov.inpi.epec.beans.Tbinventorpatente;
import br.gov.inpi.epec.beans.Tblogfamilia;
import br.gov.inpi.epec.beans.Tblogpatente;
import br.gov.inpi.epec.beans.Tbpatenteec;
import br.gov.inpi.epec.beans.Tbprioridadeec;
import br.gov.inpi.epec.beans.Tbrelatoriocolaboracao;
import br.gov.inpi.epec.beans.Tbrelatorioec;
import br.gov.inpi.epec.beans.Tbrelatoriopatente;
import br.gov.inpi.epec.entities.Familia;
import br.gov.inpi.epec.facade.ConsultaServiceFacade;
import br.gov.inpi.epec.facade.EpecServiceFacade;
import br.gov.inpi.epec.service.impl.helper.FamiliaHelper;
import br.gov.inpi.epec.util.EPECUtil;

@SessionScoped
@ManagedBean
public class TbPatenteEcMB extends AbstractMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1786888023512327100L;

	@EJB
	EpecServiceFacade service;

	@EJB
	private ConsultaServiceFacade serviceConsulta;

	private String pedido;

	private Tbpatenteec patente;

	private Tbpatenteec patenteSelecionada;

	@SuppressWarnings("unused")
	private List<Tbclassificacao> obterClassificacao;

	@SuppressWarnings("unused")
	private List<Tbdepositante> obterDepositante;

	private List<Tbdepositante> depositanteList;

	@SuppressWarnings("unused")
	private List<Tbinventor> obterInventor;

	private List<Tbclassificacao> classificacaoAux;

	private List<Tbclassificacao> classificacao;

	private List<Tblogpatente> logPedido;

	public ExternalContext externalContext;

	private String tipo;

	private String tipoConsulta;

	private String numeroEpodoc;

	private List<String> classificacaoTexto;

	private List<String> classificacaoTextoAux;

	private Tbclassificacao classificacaoCreate = new Tbclassificacao();

	public Tbclassificacao getClassificacaoCreate() {
		return classificacaoCreate;
	}

	public void setClassificacaoCreate(Tbclassificacao classificacaoCreate) {
		this.classificacaoCreate = classificacaoCreate;
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

	@SuppressWarnings("unchecked")
	private void loadClassificacao() {

		classificacao = (List<Tbclassificacao>) (Object) service.listAll(FIND_ALL_CLASSIFICACAO);

		classificacaoTexto = new ArrayList<>();
		for (int i = 0; i < classificacao.size(); i++) {

			classificacaoTexto.add(classificacao.get(i).getTxClassificacao());
		}

	}

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

	@EJB
	private EpecServiceFacade persist;

	public HttpSession session;

	@Transient
	private Tbcadusuario usuario;

	public Tbcadusuario usuarioLogado;

	Locale localeES = new Locale("es", "");
	Locale localeEN = new Locale("en", "");

	private String mensagemEditarPedido;

	private String mensagemEditarPrioridade;

	public String getMensagemEditarPrioridade() {
		return mensagemEditarPrioridade;
	}

	public void setMensagemEditarPrioridade(String mensagemEditarPrioridade) {
		this.mensagemEditarPrioridade = mensagemEditarPrioridade;
	}

	public String getMensagemEditarPedido() {
		return mensagemEditarPedido;
	}

	public void setMensagemEditarPedido(String mensagemEditarPedido) {
		this.mensagemEditarPedido = mensagemEditarPedido;
	}

	public List<Familia> familias = new ArrayList<Familia>();

	public String getNumeroEpodoc() {
		return numeroEpodoc;
	}

	public void setNumeroEpodoc(String numeroEpodoc) {
		this.numeroEpodoc = numeroEpodoc;
	}

	public Tbcadusuario getUsuarioLogado() {
		obterUsuarioSessao();
		return usuarioLogado;
	}

	public void setUsuarioLogado(Tbcadusuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	private void obterUsuarioSessao() {
		FacesContext fc = FacesContext.getCurrentInstance();

		session = (HttpSession) fc.getExternalContext().getSession(true);

		usuarioLogado = (Tbcadusuario) session.getAttribute("usuarioAutenticado");
	}

	public Tbcadusuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Tbcadusuario usuario) {
		this.usuario = usuario;
	}

	@SuppressWarnings("unchecked")
	public List<Tbclassificacao> getObterClassificacao() {
		Tbclassificacao tbclassificacao;
		List<Tbclassificacao> classificacoes = new ArrayList<>();

		getHashParametro().put("idPatenteEc", Long.parseLong(pedido));
		List<Tbclassificacaopatente> tbclassificacaopatentes = (List<Tbclassificacaopatente>) (Object) service.list(FIND_CLASSIFICACAOPATENTE_PELA_PATENTE, hashParametro);

		for (int i = 0; i < tbclassificacaopatentes.size(); i++) {
			getHashParametro().put("idClassificacao", tbclassificacaopatentes.get(i).getTbclassificacaopatentePK().getIdClassificacao());

			tbclassificacao = (Tbclassificacao) service.findOneResult(FIND_ID_CLASSIFICACAO, hashParametro);
			classificacoes.add(tbclassificacao);
		}

		return classificacoes;
	}

	public void setObterClassificacao(List<Tbclassificacao> obterClassificacao) {
		this.obterClassificacao = obterClassificacao;
	}

	@SuppressWarnings("unchecked")
	public List<Tbdepositante> getObterDepositante() {
		Tbdepositante tbdepositante;
		List<Tbdepositante> depositantes = new ArrayList<>();

		getHashParametro().put("idPatenteEc", Long.parseLong(pedido));
		List<Tbdepositantepatente> tbdepositantepatente = (List<Tbdepositantepatente>) (Object) service.list(FIND_DEPOSITANTEPATENTE_PELA_PATENTE, hashParametro);

		for (int i = 0; i < tbdepositantepatente.size(); i++) {
			getHashParametro().put("idDepositante", tbdepositantepatente.get(i).getIdDepositante());

			tbdepositante = (Tbdepositante) service.findOneResult(FIND_ID_DEPOSITANTE, hashParametro);
			depositantes.add(tbdepositante);
		}
		depositanteList = depositantes;
		return depositantes;
	}

	public void setObterDepositante(List<Tbdepositante> obterDepositante) {
		this.obterDepositante = obterDepositante;
	}

	public List<Tbdepositante> getDepositanteList() {
		return depositanteList;
	}

	public void setDepositanteList(List<Tbdepositante> depositanteList) {
		this.depositanteList = depositanteList;
	}

	@SuppressWarnings("unchecked")
	public List<Tbinventor> getObterInventor() {
		Tbinventor tbinventor;
		List<Tbinventor> inventores = new ArrayList<>();

		getHashParametro().put("idPatenteEc", Long.parseLong(pedido));

		List<Tbinventorpatente> tbinventors = (List<Tbinventorpatente>) (Object) service.list(FIND_INVENTORPATENTE_PELA_PATENTE, hashParametro);

		for (int i = 0; i < tbinventors.size(); i++) {

			getHashParametro().put("idInventor", tbinventors.get(i).getIdInventor());

			tbinventor = (Tbinventor) service.findOneResult(FIND_ID_INVENTOR, hashParametro);
			inventores.add(tbinventor);
		}

		return inventores;
	}

	public void setObterInventor(List<Tbinventor> obterInventor) {
		this.obterInventor = obterInventor;
	}

	private DualListModel<String> packiClassificacao;

	public DualListModel<String> getPackiClassificacao() {

		teste();

		return packiClassificacao;
	}

	public void setPackiClassificacao(DualListModel<String> packiClassificacao) {
		this.packiClassificacao = packiClassificacao;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public void buscarPedido() {
		EPECUtil.redirecionar("/epecWeb/pages/visualizarPedido.jsf");
	}

	public void editarPedido() {

		if (usuarioLogado.getIdEntidadeEc().getIdPais().getIdPais().equals(patente.getIdEntidadeEc().getIdPais().getIdPais())) {
			
			this.getPatenteSelecionada().setInventor(null);
			this.getPatenteSelecionada().setClassificacao(null);
			this.getPatenteSelecionada().setDepositante(null);
			
			EPECUtil.redirecionar("/epecWeb/pages/editarPedido.jsf");

		} else {
			oUsuarioLogadoNaoPodeRealizarTalOperacao();
		}

	}

	@SuppressWarnings("unchecked")
	public void updatePedido() {

		if (isValidarDados()) {
			Tbdepositantepatente tbdepositantepatente;
			Tbinventorpatente tbinventorpatente;
			Tbclassificacaopatente tbclassificacaopatente;

			service.update(patenteSelecionada);

			getHashParametro().put("idPatenteEc", patenteSelecionada.getIdPatenteEc());
			List<Tbdepositantepatente> tbdepositantepatente2 = (List<Tbdepositantepatente>) (Object) service.list(FIND_DEPOSITANTEPATENTE_PELA_PATENTE, hashParametro);
			for (int i = 0; i < tbdepositantepatente2.size(); i++) {
				service.deleteDepositantePatente(tbdepositantepatente2.get(i));
			}

			for (int d = 0; d < patenteSelecionada.getDepositante().size(); d++) {
				tbdepositantepatente = new Tbdepositantepatente();

				Tbdepositante tbdepositante = service.findDepositantePorNome(patenteSelecionada.getDepositante().get(d));

				tbdepositantepatente.setIdDepositante(tbdepositante.getIdDepositante());
				tbdepositantepatente.setIdPatenteEc(patenteSelecionada.getIdPatenteEc());
				service.save(tbdepositantepatente);
			}

			getHashParametro().put("idPatenteEc", patenteSelecionada.getIdPatenteEc());

			List<Tbinventorpatente> tbinventors = (List<Tbinventorpatente>) (Object) service.list(FIND_INVENTORPATENTE_PELA_PATENTE, hashParametro);

			for (int i = 0; i < tbinventors.size(); i++) {
				service.deleteInventorPatente(tbinventors.get(i));
			}

			for (int i = 0; i < patenteSelecionada.getInventor().size(); i++) {
				tbinventorpatente = new Tbinventorpatente();
				Tbinventor tbinventor = service.findInventorPorNome(patenteSelecionada.getInventor().get(i));

				tbinventorpatente.setIdInventor(tbinventor.getIdInventor());
				tbinventorpatente.setIdPatenteEc(patenteSelecionada.getIdPatenteEc());
				service.save(tbinventorpatente);
			}

			getHashParametro().put("idPatenteEc", patenteSelecionada.getIdPatenteEc());
			List<Tbclassificacaopatente> tbclassificacaopatentes = (List<Tbclassificacaopatente>) (Object) service.list(FIND_CLASSIFICACAOPATENTE_PELA_PATENTE, hashParametro);

			for (int i = 0; i < tbclassificacaopatentes.size(); i++) {
				service.deleteClassificacaoPatente(tbclassificacaopatentes.get(i));
			}

			for (int c = 0; c < packiClassificacao.getTarget().size(); c++) {

				Tbclassificacao classifi = service.findClassificacaoPorNome(packiClassificacao.getTarget().get(c));

				tbclassificacaopatente = new Tbclassificacaopatente();

				TbclassificacaopatentePK pk = new TbclassificacaopatentePK();
				pk.setIdClassificacao(classifi.getIdClassificacao());
				pk.setIdPatenteEc(patenteSelecionada.getIdPatenteEc());

				tbclassificacaopatente.setIdOrdem(c + 1);
				tbclassificacaopatente.setTbclassificacaopatentePK(pk);

				service.save(tbclassificacaopatente);
			}
			salvarLogPedido(patenteSelecionada, "Editar", "Pedido", usuarioLogado.getStrUsuario(), "-");
			salvarLogFamilia(patenteSelecionada.getIdFamiliaEc().getIdFamiliaEc(), "Alterar", "Pedido", patenteSelecionada.getNumeroPedido(), usuarioLogado.getStrUsuario());

			
			this.mensagemEditarPedido = "1";

			EPECUtil.redirecionar("/epecWeb/pages/visualizarPedido.jsf");
		}

	}

	private boolean isValidarDados() {

		String titulo = "";
		String dataDeposito = "";
		String dataPublicacao = "";

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			titulo = "Título";
			dataDeposito = "Fecha de Solicitud";
			dataPublicacao = "Fecha de Publicación";

		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			titulo = "Title";
			dataDeposito = "Date of Application";
			dataPublicacao = "Published Date";

		} else {
			titulo = "Título";
			dataDeposito = "Data de Depósito";
			dataPublicacao = "Data de Pulicação";

		}

		// validar titulo
		if (patenteSelecionada.getTxTitulo() == null) {
			campoObrigatorio(titulo);
			return false;
		} else if (patenteSelecionada.getTxTitulo().isEmpty()) {
			campoObrigatorio(titulo);
			return false;
		}

		// validar de deposito
		if (patenteSelecionada.getDtDeposito() == null) {
			campoObrigatorio(dataDeposito);
			return false;
		}

		// validar de publicacao
		if (patenteSelecionada.getDtPublicacao() == null) {
			campoObrigatorio(dataPublicacao);
			return false;
		}

		return true;
	}

	public void mensagemEditarPrioridade() {
		if (mensagemEditarPrioridade != null) {
			if (mensagemEditarPrioridade.equals("1")) {
				alteradoComSucesso();
			}

			if (mensagemEditarPrioridade.equals("2")) {
				salvoComSucesso();
			}

			if (mensagemEditarPrioridade.equals("3")) {
				excluidoComSucesso();
			}

		}

		if (mensagemEditarPedido != null) {
			if (mensagemEditarPedido.equals("1")) {

				alteradoComSucesso();

			}

			if (mensagemEditarPedido.equals("2")) {
				oUsuarioLogadoNaoPodeRealizarTalOperacao();
			}
			
			if(mensagemEditarPedido.equals("3")){
				pedidoRecuperado();
			}
			
			if(mensagemEditarPedido.equals("4")){
				erroPedidoManual();
			}
			
			if(mensagemEditarPedido.equals("5")){
				erroSolicitacao();
			}
			if(mensagemEditarPedido.equals("6")){
				operacaoNaoPodeSerRealizada();
			}

		}

		this.mensagemEditarPedido = null;

		this.mensagemEditarPrioridade = null;

	}

	@SuppressWarnings("unchecked")
	public Tbpatenteec getPatente() {

		usuarioLogado = getUsuarioLogado();
		getHashParametro().put("idPatenteEc", Long.parseLong(pedido));

		patente = (Tbpatenteec) service.findOneResult(FIND_BY_ID_PEDIDO, hashParametro);

		try {

			getHashParametro().put("idPatente", patente.getIdPatenteEc());
			List<Tbrelatoriopatente> relatorioPatente = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_PATENTE, hashParametro);

			if (relatorioPatente.size() == 0) {
				patente.setTbrelatorioec(null);

				patente.setEmColaboracao("");
			} else if (relatorioPatente.size() == 1) {

				getHashParametro().put("idRelatorioEc", relatorioPatente.get(0).getTbRelatorioPatentePK().getIdRelatorio());
				Tbrelatorioec tbrelatorioec = (Tbrelatorioec) service.findOneResult(FIND_BY_ID_RELATORIO, hashParametro);

				patente.setTbrelatorioec(tbrelatorioec);
				patente.setEmColaboracao("Não");

				getHashParametro().put("idRelatorio", tbrelatorioec.getIdRelatorioEc());
				List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);
				for (int b = 0; b < relatorioColaboracao.size(); b++) {

					getHashParametro().put("idColaboracao", relatorioColaboracao.get(b).getColaboracaoPK().getIdColaboracao());
					List<Tbcolaboracaoentidade> tbcolaboEntidades = (List<Tbcolaboracaoentidade>) (Object) service.list(FIND_ENTIDADE_COLABORACAO, hashParametro);

					for (int f = 0; f < tbcolaboEntidades.size(); f++) {
						getHashParametro().put("idEntidadeEc", tbcolaboEntidades.get(f).getIdEntidadeEc());
						Tbcadentidade tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);

						if (tbcadentidade.equals(usuarioLogado.getIdEntidadeEc())) {
							patente.setEmColaboracao("Sim");
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

				patente.setTbrelatorioec(tbrelatorioecNovo);

				patente.setEmColaboracao("Não");

				getHashParametro().put("idRelatorio", tbrelatorioecNovo.getIdRelatorioEc());
				List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);
				for (int b = 0; b < relatorioColaboracao.size(); b++) {

					getHashParametro().put("idColaboracao", relatorioColaboracao.get(b).getColaboracaoPK().getIdColaboracao());
					List<Tbcolaboracaoentidade> tbcolaboEntidades = (List<Tbcolaboracaoentidade>) (Object) service.list(FIND_ENTIDADE_COLABORACAO, hashParametro);

					for (int f = 0; f < tbcolaboEntidades.size(); f++) {
						getHashParametro().put("idEntidadeEc", tbcolaboEntidades.get(f).getIdEntidadeEc());
						Tbcadentidade tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);

						if (tbcadentidade.equals(usuarioLogado.getIdEntidadeEc())) {
							patente.setEmColaboracao("Sim");
						}
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return patente;
	}

	public void setPatente(Tbpatenteec patente) {
		this.patente = patente;
	}

	public Tbpatenteec getPatenteSelecionada() {

		patenteSelecionada = patente;

		if (patenteSelecionada.getInventor() == null) {
			buscarInventor();
		}

		if (patenteSelecionada.getDepositante() == null) {
			buscarDepositante();
		}

		if (patenteSelecionada.getClassificacao() == null) {
			buscarClassificacao();
		}

		return patenteSelecionada;
	}

	private void buscarClassificacao() {
		List<String> classificacoes = new ArrayList<>();
		List<Tbclassificacao> classificacao = getObterClassificacao();
		for (int i = 0; i < classificacao.size(); i++) {
			classificacoes.add(classificacao.get(i).getTxClassificacao());
		}

		patenteSelecionada.setClassificacao(classificacoes);

	}

	private void buscarInventor() {

		List<String> inventores = new ArrayList<>();
		List<Tbinventor> inventor = getObterInventor();
		for (int i = 0; i < inventor.size(); i++) {
			inventores.add(inventor.get(i).getTxInventor());
		}

		patenteSelecionada.setInventor(inventores);
	}

	private void buscarDepositante() {
		List<String> deposistantes = new ArrayList<>();
		List<Tbdepositante> depositante = getObterDepositante();
		for (int i = 0; i < depositante.size(); i++) {
			deposistantes.add(depositante.get(i).getTxDepositante());
		}

		patenteSelecionada.setDepositante(deposistantes);

	}

	public void setPatenteSelecionada(Tbpatenteec patenteSelecionada) {
		this.patenteSelecionada = patenteSelecionada;
	}

	@SuppressWarnings("unchecked")
	public void teste() {

		if (packiClassificacao == null) {
			classificacao = (List<Tbclassificacao>) (Object) service.listAll(FIND_ALL_CLASSIFICACAO);

			classificacaoTexto = new ArrayList<>();
			for (int i = 0; i < classificacao.size(); i++) {
				classificacaoTexto.add(classificacao.get(i).getTxClassificacao());
			}

			classificacaoAux = getObterClassificacao();

			classificacaoTextoAux = new ArrayList<>();
			for (int i = 0; i < classificacaoAux.size(); i++) {
				classificacaoTextoAux.add(classificacaoAux.get(i).getTxClassificacao());
			}

			packiClassificacao = new DualListModel<String>(classificacaoTexto, classificacaoTextoAux);
		}

	}

	public List<Tbclassificacao> getClassificacaoAux() {
		return classificacaoAux;
	}

	public void setClassificacaoAux(List<Tbclassificacao> classificacaoAux) {
		this.classificacaoAux = classificacaoAux;
	}

	public List<Tbclassificacao> getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(List<Tbclassificacao> classificacao) {
		this.classificacao = classificacao;
	}

	@SuppressWarnings("unchecked")
	public List<Tblogpatente> getLogPedido() {
		getHashParametro().put("idPatenteEc", patente.getIdPatenteEc());
		logPedido = (List<Tblogpatente>) (Object) service.list(FIND_LOGPEDIDO_BY_PEDIDOID, hashParametro);

		for (int i = 0; i < logPedido.size(); i++) {

			logPedido.get(i).setUsuario(service.findUsuarioPorNome(logPedido.get(i).getStrUsuario()));
		}

		return logPedido;
	}

	public void setLogPedido(List<Tblogpatente> logPedido) {
		this.logPedido = logPedido;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	@SuppressWarnings("unchecked")
	public void recuperarPedido() {

		if (usuarioLogado.getIdEntidadeEc().getIdPais().getIdPais().equals(patente.getIdEntidadeEc().getIdPais().getIdPais())) {

			RequestContext requestContext = RequestContext.getCurrentInstance();

			try {
				Tbpatenteec patenteUpdate = new Tbpatenteec();

				String valorPedido = patente.getNumeroPublicacaEPODOC();
				String xmlResult = serviceConsulta.retornaXML("1", "", valorPedido);

				
				if(serviceConsulta.verificarXmL(xmlResult)){
					this.mensagemEditarPedido = "6";
				}
				
				if ("HTTP Status 404".equals(xmlResult) || "not found".equals(xmlResult)) {

					this.mensagemEditarPedido = "6";
					
				}

				patenteUpdate.setIdPatenteEc(patente.getIdPatenteEc());
				String xmlResultBiblio = serviceConsulta.retornaXML("1", "", valorPedido);
				List<Tbcadpais> paises = (List<Tbcadpais>) (Object) persist.listAll(FIND_ALL_PAIS);
				Tbfamiliaec familia = new Tbfamiliaec();
				Tbcadentidade entidade = serviceConsulta.buscaEntidadeByIdPais(paises, valorPedido, getUsuarioLogado());
				familia = patente.getIdFamiliaEc();
				if (patente.getAutomatico() == true) {
					getHashParametro().put("idPatenteEc", patente.getIdPatenteEc());

					List<Tbdepositantepatente> tbdepositantepatente2 = (List<Tbdepositantepatente>) (Object) service.list(FIND_DEPOSITANTEPATENTE_PELA_PATENTE, hashParametro);
					for (int i = 0; i < tbdepositantepatente2.size(); i++) {
						service.deleteDepositantePatente(tbdepositantepatente2.get(i));
					}

					List<Tbinventorpatente> tbInventorPatente = (List<Tbinventorpatente>) (Object) service.list(FIND_INVENTORPATENTE_PELA_PATENTE, hashParametro);

					for (int i = 0; i < tbInventorPatente.size(); i++) {
						service.deleteInventorPatente(tbInventorPatente.get(i));
					}

					List<Tbclassificacaopatente> tbclassificacaopatentes = (List<Tbclassificacaopatente>) (Object) service.list(FIND_CLASSIFICACAOPATENTE_PELA_PATENTE, hashParametro);

					for (int i = 0; i < tbclassificacaopatentes.size(); i++) {
						service.deleteClassificacaoPatente(tbclassificacaopatentes.get(i));
					}

					List<Tbprioridadeec> tbPrioridadeEc = (List<Tbprioridadeec>) (Object) service.list(FIND_PRIORIDADE_PEDIDO, hashParametro);

					for (int i = 0; i < tbPrioridadeEc.size(); i++) {
						service.deletePrioridade(tbPrioridadeEc.get(i));
					}

					patenteUpdate.setIdEntidadeEc(entidade);
					patenteUpdate.setIdFamiliaEc(familia);
					patenteUpdate.setIdPatenteEc(patente.getIdPatenteEc());
					patenteUpdate.setAutomatico(true);
					serviceConsulta.alterarPedidoXML(xmlResultBiblio, patenteUpdate);				
					
					
					this.mensagemEditarPedido = "3";

					salvarLogPedido(patenteUpdate, "Recuperar", "Pedido", usuarioLogado.getStrUsuario(), "-");

					salvarLogFamilia(patenteUpdate.getIdFamiliaEc().getIdFamiliaEc(), "Alterar", "Pedido", patenteUpdate.getNumeroPedido(), usuarioLogado.getStrUsuario());

					requestContext.execute("PF('dialogRecuperarPedido').hide()");

				}
				if (patente.getAutomatico() == false) {
					this.mensagemEditarPedido = "4";
				}
			} catch (IOException e) {
				this.mensagemEditarPedido = "5";
			} catch (Throwable e) {
				this.mensagemEditarPedido = "5";
			}
		} else {

			this.mensagemEditarPedido = "2";
		}

	}

	/**
	 * Prepara o relatorio que sera gerado para o Diagrama de Familia
	 * 
	 * @param codPedido
	 * @throws ClientProtocolException
	 * @throws IOException
	 */

	public void prepararRelatorio() throws ClientProtocolException, IOException {

		String xml;
		String tipo = "4";
		String tipoConsulta = "biblio";
		String valorPedido = numeroEpodoc;

		try {

			FamiliaHelper helper = new FamiliaHelper();
			DiagramaFamiliaMB diagramaFamilia = new DiagramaFamiliaMB();

			List<Familia> listaFamilia = new ArrayList<Familia>();

			xml = serviceConsulta.retornaXML(tipo, tipoConsulta, valorPedido);

			listaFamilia = helper.obterFamilia(xml, "diagrama");

			if (!xml.contains("Error")) {

				diagramaFamilia.carregaDiagramaFamilia(listaFamilia, helper);

				RequestContext.getCurrentInstance().openDialog("diagramaFamiliaTeste");
			} else {

				if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
					displayErrorMessageToUser("Document Not Found");
				} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
					displayErrorMessageToUser("Document Not Found");
				} else {
					displayErrorMessageToUser("Documento não Encontrado");
				}
			}
		} catch (Exception e) {

			if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
				displayErrorMessageToUser("Document Not Found");
			} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
				displayErrorMessageToUser("Document Not Found");
			} else {
				displayErrorMessageToUser("Documento não Encontrado");
			}
		}
	}

	public void salvarLogPedido(Tbpatenteec idPatenteEc, String strAcao, String strAtivo, String strUsuario, String txAtivo) {
		Tblogpatente tblogpatente = new Tblogpatente();
		tblogpatente.setdDatahora(new Date());
		tblogpatente.setIdPatenteEc(idPatenteEc.getIdPatenteEc());
		tblogpatente.setStrAcao(strAcao);
		tblogpatente.setStrAtivo(strAtivo);
		tblogpatente.setStrUsuario(strUsuario);
		tblogpatente.setTxAtivo(txAtivo);

		service.save(tblogpatente);

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

	// prioridade

	private String numeroPatente;

	private Tbpatenteec patentes;

	private Tbpatenteec patentePrioridadeExcluir;

	@SuppressWarnings("unused")
	private List<Tbprioridadeec> prioridades;

	private Tbprioridadeec prioridade = new Tbprioridadeec();

	private Tbprioridadeec prioridadeSelecionada;

	public Tbprioridadeec getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Tbprioridadeec prioridade) {
		this.prioridade = prioridade;
	}

	public void manterPrioridade() {
		EPECUtil.redirecionar("/epecWeb/pages/manterPrioridade.jsf");
	}

	public void redirecionarParaSalvarPrioridade() {
		prioridade = new Tbprioridadeec();
		EPECUtil.redirecionar("/epecWeb/pages/salvarPrioridade.jsf");
	}

	public Tbpatenteec getPatentes() {
		getHashParametro().put("idPatenteEc", Long.parseLong(numeroPatente));

		patentes = (Tbpatenteec) service.findOneResult(FIND_BY_ID_PEDIDO, hashParametro);
		return patentes;
	}

	public void setPatentes(Tbpatenteec patentes) {
		this.patentes = patentes;
	}

	@SuppressWarnings("unchecked")
	public List<Tbprioridadeec> getPrioridades() {

		getHashParametro().put("idPatenteEc", Long.parseLong(numeroPatente));

		List<Tbprioridadeec> tbprioridadeecs = (List<Tbprioridadeec>) (Object) service.list(FIND_PRIORIDADE_PEDIDO, hashParametro);

		return tbprioridadeecs;

	}

	public void setPrioridades(List<Tbprioridadeec> prioridades) {
		this.prioridades = prioridades;
	}

	public void salvarPrioridade() {

		if (prioridade.getDtDeposito() == null) {
			preencherTodosOsCampos();
		} else if (prioridade.getStrPrioridade() == null) {
			preencherTodosOsCampos();

		} else if (prioridade.getStrPrioridade().isEmpty()) {
			preencherTodosOsCampos();

		} else {
			prioridade.setIdPatenteEc(patente);
			service.save(prioridade);
			this.mensagemEditarPrioridade = "2";
			salvarLogPedido(patente, "Inserir", "Prioridade", usuarioLogado.getStrUsuario(), prioridade.getStrPrioridade());

			EPECUtil.redirecionar("/epecWeb/pages/visualizarPedido.jsf");
		}

	}

	public void updatePrioridade() {

		if (prioridadeSelecionada.getDtDeposito() == null) {
			preencherTodosOsCampos();
		} else if (prioridadeSelecionada.getStrPrioridade() == null) {
			preencherTodosOsCampos();

		} else if (prioridadeSelecionada.getStrPrioridade().isEmpty()) {
			preencherTodosOsCampos();

		} else {

			prioridadeSelecionada.setIdPatenteEc(patente);
			service.update(prioridadeSelecionada);
			this.mensagemEditarPrioridade = "1";
			salvarLogPedido(patente, "Alterar", "Prioridade", usuarioLogado.getStrUsuario(), prioridadeSelecionada.getStrPrioridade());
			EPECUtil.redirecionar("/epecWeb/pages/visualizarPedido.jsf");
		}
	}

	public void deletePrioridade() {
		getHashParametro().put("idPrioridadeEc", prioridadeSelecionada.getIdPrioridadeEc());
		prioridadeSelecionada = (Tbprioridadeec) service.findOneResult(FIND_PRIORIDADE, hashParametro);

		service.deletePrioridade(prioridadeSelecionada);
		salvarLogPedido(patente, "Excluir", "Prioridade", usuarioLogado.getStrUsuario(), prioridadeSelecionada.getStrPrioridade());

		this.mensagemEditarPrioridade = "3";
		EPECUtil.redirecionar("/epecWeb/pages/visualizarPedido.jsf");
	}

	public void atualizarPrioridade() {

		patentes = (Tbpatenteec) service.findOneResult(FIND_BY_ID_PEDIDO, hashParametro);
		EPECUtil.redirecionar("/epecWeb/pages/editarPrioridade.jsf");

	}

	public Tbprioridadeec getPrioridadeSelecionada() {
		return prioridadeSelecionada;
	}

	public void setPrioridadeSelecionada(Tbprioridadeec prioridadeSelecionada) {
		this.prioridadeSelecionada = prioridadeSelecionada;
	}

	public Tbpatenteec getPatentePrioridadeExcluir() {
		return patentePrioridadeExcluir;
	}

	public void setPatentePrioridadeExcluir(Tbpatenteec patentePrioridadeExcluir) {
		this.patentePrioridadeExcluir = patentePrioridadeExcluir;
	}

	public String getNumeroPatente() {
		return numeroPatente;
	}

	public void setNumeroPatente(String numeroPatente) {
		this.numeroPatente = numeroPatente;
	}

}