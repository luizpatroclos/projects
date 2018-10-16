package br.gov.inpi.epec.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.sun.java.swing.plaf.windows.resources.windows;

import br.gov.inpi.epec.beans.TbRelatorioColaboracaoPK;
import br.gov.inpi.epec.beans.TbRelatorioPatentePK;
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
import br.gov.inpi.epec.beans.Tbcadstatusrelatorio;
import br.gov.inpi.epec.beans.Tbcadtipoanterioridade;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbcaraccatrelatorioec;
import br.gov.inpi.epec.beans.Tbcatrelatorioec;
import br.gov.inpi.epec.beans.Tbclausulatipo;
import br.gov.inpi.epec.beans.Tbcolaboracaoentidade;
import br.gov.inpi.epec.beans.Tblogfamilia;
import br.gov.inpi.epec.beans.Tblogrelatorio;
import br.gov.inpi.epec.beans.Tbnos;
import br.gov.inpi.epec.beans.Tbfamiliaec;
import br.gov.inpi.epec.beans.Tbpatenteec;
import br.gov.inpi.epec.beans.Tbrelatoriocolaboracao;
import br.gov.inpi.epec.beans.Tbrelatorioec;
import br.gov.inpi.epec.beans.Tbrelatoriopatente;
import br.gov.inpi.epec.facade.EpecServiceFacade;
import br.gov.inpi.epec.util.Document;
import br.gov.inpi.epec.util.EPECUtil;

@SessionScoped
@ManagedBean
public class TbRelatorioEcMB extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1250004247667424142L;

	@EJB
	EpecServiceFacade service;

	private Tbfamiliaec tbfamiliaec;

	private String numeroRelatorio;

	private String pedidoRelatorio;

	private Tbrelatorioec relatorioSelecionado;

	private Tbrelatorioec relatorio;

	private List<Tbpatenteec> patentes;

	private TreeNode selectedNode1;

	private TreeNode selectedNode2;

	private TreeNode selectedNode3;

	private TreeNode selectedNode4;

	private TreeNode selectedNode5;

	private TreeNode selectedNode6;

	private String resumo;
	
	private String link;

	private String conclusao;

	private String relacao;

	private String reivindicacao;

	private String relacaoNP;

	private String reivindicacaoNP;

	@SuppressWarnings("unused")
	private List<Tbcadcolaboracao> colaboracao;

	private List<String> alterarColaboracao = new ArrayList<>();

	private List<Tbcadcategoria> categorias;

	private List<Tbcadcategoria> escolherCategoria;

	private List<Tbantpatentariarelec> antPatentaria;

	private List<Tbantnaopatentariarelec> antnaoPatentaria;

	private Tbantpatentariarelec anterioridadePatentaria = new Tbantpatentariarelec();

	private Tbantnaopatentariarelec anterioridadeNaoPatentaria = new Tbantnaopatentariarelec();

	private List<Tbcadtipoanterioridade> escolherRelevancia;

	private List<Tbcadtipoanterioridade> escolherRelevanciaNaoPatentaria;

	private List<Tbcatrelatorioec> categoriaRelatorio;

	private Tbantpatentariarelec anterioridadePatentariaselect;

	private Tbantpatentariarelec anterioridadePatentariaExcluir;

	private Tbantnaopatentariarelec anterioridadeNaoPatentariaSelect;

	private Tbantnaopatentariarelec anterioridadeNaoPatentariaExcluir;

	private String categoria;

	private Tbcatrelatorioec tbcatrelatorioecExcluir;

	private Tbcatrelatorioec tbcatrelatorioec;

	private Tbclausulatipo clausulaTipo;

	public String tipo;

	public ExternalContext externalContext;

	private TreeNode root1;

	private TreeNode root2;

	private TreeNode root3;

	private TreeNode root4;

	private int tabIndex = 1;

	private List<Tbnos> nos;

	private List<Tbclausulatipo> clausulaTipoList;

	private Tbrelatorioec relatorioInserirNovo = new Tbrelatorioec();

	private Tbrelatorioec relatorioInserir;

	private Tbpatenteec pedidoRelacionadoAoRelatorioNovo;

	private Tbrelatorioec relatorioHTML;

	private HttpSession session;

	public Tbcadusuario usuarioLogado;

	public List<Tblogrelatorio> logRelatorio;

	private String relatorioCopiar;

	private String copiarRelatorio;

	private String alterarAntPatentaria;

	private String alterarAntNaoPatentaria;

	private String excluirAntPatentaria;

	private String excluirAntNaoPatentaria;

	private String salvarAntPatentaria;

	private String salvarAntNaoPatentaria;

	private String excluirCategoria;

	private String textoOutras;

	private String usuarioLabel;
	
	public String getExcluirCategoria() {
		return excluirCategoria;
	}

	public void setExcluirCategoria(String excluirCategoria) {
		this.excluirCategoria = excluirCategoria;
	}

	public String getSalvarAntPatentaria() {
		return salvarAntPatentaria;
	}

	public void setSalvarAntPatentaria(String salvarAntPatentaria) {
		this.salvarAntPatentaria = salvarAntPatentaria;
	}

	public String getSalvarAntNaoPatentaria() {
		return salvarAntNaoPatentaria;
	}

	public void setSalvarAntNaoPatentaria(String salvarAntNaoPatentaria) {
		this.salvarAntNaoPatentaria = salvarAntNaoPatentaria;
	}

	public String getAlterarAntPatentaria() {
		return alterarAntPatentaria;
	}

	public void setAlterarAntPatentaria(String alterarAntPatentaria) {
		this.alterarAntPatentaria = alterarAntPatentaria;
	}

	public String getAlterarAntNaoPatentaria() {
		return alterarAntNaoPatentaria;
	}

	public void setAlterarAntNaoPatentaria(String alterarAntNaoPatentaria) {
		this.alterarAntNaoPatentaria = alterarAntNaoPatentaria;
	}

	public String getExcluirAntPatentaria() {
		return excluirAntPatentaria;
	}

	public void setExcluirAntPatentaria(String excluirAntPatentaria) {
		this.excluirAntPatentaria = excluirAntPatentaria;
	}

	public String getExcluirAntNaoPatentaria() {
		return excluirAntNaoPatentaria;
	}

	public void setExcluirAntNaoPatentaria(String excluirAntNaoPatentaria) {
		this.excluirAntNaoPatentaria = excluirAntNaoPatentaria;
	}

	Locale localeES = new Locale("es", "");
	Locale localeEN = new Locale("en", "");

	public Tbcadusuario getUsuarioLogado() {
		obterUsuarioSessao();
		return usuarioLogado;
	}

	public void setUsuarioLogado(Tbcadusuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getRelatorioCopiar() {
		return relatorioCopiar;
	}

	public void setRelatorioCopiar(String relatorioCopiar) {
		this.relatorioCopiar = relatorioCopiar;
	}

	private List<String> colaboracoesCopiarRelatorio = new ArrayList<>();

	@PostConstruct
	public void init() {
		
		
		this.conclusao = "";
		this.resumo = "";

		escolherCategoria = new ArrayList<Tbcadcategoria>();

		loadPais();
		
		obterUsuarioSessao();
		
		usuarioLabel = (String) session.getAttribute("usuario");
		if ("Visitante".equals(usuarioLabel)) {
		
		}else{
			escolherCategoria.addAll(categorias);
			loadClausulaTipoResumo();
			obterUsuarioSessao();
		}

	}
	
	
	public void voltarColaboracao(){
		//TODO
		
		alterarColaboracao = new ArrayList<>();

		EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");
	}

	private void obterUsuarioSessao() {
		FacesContext fc = FacesContext.getCurrentInstance();

		session = (HttpSession) fc.getExternalContext().getSession(true);

		usuarioLogado = (Tbcadusuario) session.getAttribute("usuarioAutenticado");
	}

	public void inserirRelatorio() {
		// ver se o usuario é examinador e se o relatorio que está na lista está
		if (numeroRelatorio != null) {

			if (!(numeroRelatorio.isEmpty())) {
				getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));

				relatorioInserir = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

				if (relatorioInserir.getIdStatus().getIdStatus() != 3L && (!(pedidoRelacionadoAoRelatorioNovo.getIdEntidadeEc().equals(usuarioLogado.getIdEntidadeEc())))) {

					relatorioFinalNaoEstaFinalizado();

				} else if (pedidoRelacionadoAoRelatorioNovo.getIdEntidadeEc().equals(usuarioLogado.getIdEntidadeEc())) {
					this.colaboracoesCopiarRelatorio = null;
					EPECUtil.redirecionar("/epecWeb/pages/inserirRelatorioColaboracao.jsf");
				} else {
					this.colaboracoesCopiarRelatorio = null;
					EPECUtil.redirecionar("/epecWeb/pages/inserirRelatorioColaboracao.jsf");
				}
			} else {
				this.colaboracoesCopiarRelatorio = null;
				EPECUtil.redirecionar("/epecWeb/pages/inserirRelatorioColaboracao.jsf");
			}
		} else {
			this.colaboracoesCopiarRelatorio = null;
			EPECUtil.redirecionar("/epecWeb/pages/inserirRelatorioColaboracao.jsf");
		}
	}

	public void trocarStatusConstrucao() {
		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		relatorio = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

		obterUsuarioSessao();

		if (relatorio.getIdStatus().getIdStatus() == 3L) {
			oUsuarioLogadoNaoPodeRealizarTalOperacao();
		} else if (usuarioLogado.equals(relatorio.getIdUsuario())) {
			getHashParametro().put("idStatus", 1L);
			Tbcadstatusrelatorio idStatus = (Tbcadstatusrelatorio) service.findOneResult(FIND_CADSTATUSRELATORIOBYID, hashParametro);

			relatorio.setIdStatus(idStatus);
			relatorio.setDtStatus(new Date());

			service.update(relatorio);
			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Alterar", "Status", "Construir", usuarioLogado.getStrUsuario());

			alteradoComSucesso();

		} else {
			oUsuarioLogadoNaoPodeRealizarTalOperacao();
		}

	}

	public void trocarStatusRevisao() {

		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		relatorio = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

		obterUsuarioSessao();

		if (relatorio.getIdStatus().getIdStatus() == 3L) {
			oUsuarioLogadoNaoPodeRealizarTalOperacao();

		} else if (usuarioLogado.equals(relatorio.getIdUsuario())) {
			getHashParametro().put("idStatus", 2L);
			Tbcadstatusrelatorio idStatus = (Tbcadstatusrelatorio) service.findOneResult(FIND_CADSTATUSRELATORIOBYID, hashParametro);

			relatorio.setIdStatus(idStatus);
			relatorio.setDtStatus(new Date());

			service.update(relatorio);

			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Alterar", "Status", "Revisar", usuarioLogado.getStrUsuario());

			alteradoComSucesso();
		} else {
			oUsuarioLogadoNaoPodeRealizarTalOperacao();
		}

	}

	public void trocarStatusFinalizado() {
		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		relatorio = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

		obterUsuarioSessao();

		if (relatorio.getIdStatus().getIdStatus() == 1L) {
			oUsuarioLogadoNaoPodeRealizarTalOperacao();
		} else if (usuarioLogado.equals(relatorio.getIdUsuario())) {
			getHashParametro().put("idStatus", 3L);
			Tbcadstatusrelatorio idStatus = (Tbcadstatusrelatorio) service.findOneResult(FIND_CADSTATUSRELATORIOBYID, hashParametro);

			relatorio.setIdStatus(idStatus);
			relatorio.setDtStatus(new Date());

			service.update(relatorio);
			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Alterar", "Status", "Finalizar", usuarioLogado.getStrUsuario());
			salvarLogFamilia(relatorio.getIdFamiliaEc().getIdFamiliaEc(), "Finalizar", "Relatório", relatorio.getStrRelatorio().toString()+relatorio.getIdRelatorioEc().toString(), usuarioLogado.getStrUsuario());
			
			alteradoComSucesso();
		} else {
			oUsuarioLogadoNaoPodeRealizarTalOperacao();
		}

	}

	public void publicarRelatorio() {

		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		relatorio = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

		if (relatorio.getIdStatus().getIdStatus() == 3L) {
			relatorio.setBPublico(true);

			service.update(relatorio);
			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Publicar", "Relatório", "-", usuarioLogado.getStrUsuario());
			salvarLogFamilia(relatorio.getIdFamiliaEc().getIdFamiliaEc(), "Publicar", "Relatório", relatorio.getStrRelatorio().toString()+relatorio.getIdRelatorioEc().toString() , usuarioLogado.getStrUsuario());

			
			
			relatorioPublicado();

			verificarTodosRelatoriosAssociados();

		} else {
			soEhPossivelPublicarRelatorioFinalizado();
		}

	}

	private void verificarTodosRelatoriosAssociados() {
		int cont = 0;
		int rela = 0;

		getHashParametro().put("idFamiliaEc", tbfamiliaec.getIdFamiliaEc());
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
			tbfamiliaec.setPublico(true);
			service.update(tbfamiliaec);

			salvarLogFamilia(tbfamiliaec.getIdFamiliaEc(), "Publicar", "Família", "-", getUsuarioLogado().getStrUsuario());
		}

	}

	@SuppressWarnings("unchecked")
	public void inserirNovoRelatorio() {
		relatorioInserirNovo = new Tbrelatorioec();
		if (colaboracoesCopiarRelatorio.size() == 0) {
			relatorioSemColaboracao();
		} else {
			getHashParametro().put("idPatente", pedidoRelacionadoAoRelatorioNovo.getIdPatenteEc());
			List<Tbrelatoriopatente> relatorioPatente2 = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_PATENTE, hashParametro);

			for (int e = 0; e < relatorioPatente2.size(); e++) {
				getHashParametro().put("idRelatorioEc", relatorioPatente2.get(e).getTbRelatorioPatentePK().getIdRelatorio());
				Tbrelatorioec relatorio2 = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

				if (relatorio2.getIdStatus().getIdStatus() != 3L) {
					service.delete(relatorioPatente2.get(e));

				} else if (!(pedidoRelacionadoAoRelatorioNovo.getIdEntidadeEc().equals(usuarioLogado.getIdEntidadeEc()))) {
					service.delete(relatorioPatente2.get(e));
				}
			}

			getHashParametro().put("idStatus", 1L);
			Tbcadstatusrelatorio idStatus = (Tbcadstatusrelatorio) service.findOneResult(FIND_CADSTATUSRELATORIOBYID, hashParametro);

			obterUsuarioSessao();

			relatorioInserirNovo.setBPublico(false);
			relatorioInserirNovo.setIdStatus(idStatus);
			relatorioInserirNovo.setDtStatus(new Date());
			relatorioInserirNovo.setIdFamiliaEc(tbfamiliaec);
			relatorioInserirNovo.setIdUsuario(usuarioLogado);
			relatorioInserirNovo.setStrRelatorio(pedidoRelacionadoAoRelatorioNovo.getNumeroPedido());
			relatorioInserirNovo.setTxResumo("");
			relatorioInserirNovo.setTxConclusao("");
			service.save(relatorioInserirNovo);

			getHashParametro().put("idRelatorio", relatorioInserirNovo.getIdRelatorioEc());
			List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);

			for (int i = 0; i < relatorioColaboracao.size(); i++) {
				service.deleteColaboracao(relatorioColaboracao.get(i));
			}

			for (int i = 0; i < colaboracoesCopiarRelatorio.size(); i++) {

				getHashParametro().put("idColaboracao", Long.parseLong(colaboracoesCopiarRelatorio.get(i)));
				Tbcadcolaboracao colaboracao = (Tbcadcolaboracao) service.findOneResult(FIND_BY_ID_COLABORACAO, hashParametro);

				Tbrelatoriocolaboracao tbrelatoriocolaboracao = new Tbrelatoriocolaboracao();
				TbRelatorioColaboracaoPK colaboracaoPK = new TbRelatorioColaboracaoPK();
				colaboracaoPK.setIdColaboracao(colaboracao.getIdColaboracao());
				colaboracaoPK.setIdRelatorio(relatorioInserirNovo.getIdRelatorioEc());

				tbrelatoriocolaboracao.setColaboracaoPK(colaboracaoPK);

				service.save(tbrelatoriocolaboracao);

			}

			resumo = relatorioInserirNovo.getTxResumo();
			conclusao = relatorioInserirNovo.getTxConclusao();
			numeroRelatorio = relatorioInserirNovo.getIdRelatorioEc().toString();
			relatorio = relatorioInserirNovo;

			Tbrelatoriopatente tbrelatoriopatente = new Tbrelatoriopatente();

			TbRelatorioPatentePK patentePK = new TbRelatorioPatentePK();
			patentePK.setIdPatente(pedidoRelacionadoAoRelatorioNovo.getIdPatenteEc());
			patentePK.setIdRelatorio(relatorio.getIdRelatorioEc());

			tbrelatoriopatente.setTbRelatorioPatentePK(patentePK);

			service.save(tbrelatoriopatente);

			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Inserir", "Relatório", "-", usuarioLogado.getStrUsuario());
			String ativo = relatorio.getStrRelatorio().toString() + relatorio.getIdRelatorioEc().toString();
			salvarLogFamilia(relatorio.getIdFamiliaEc().getIdFamiliaEc(), "Inserir", "Relatório", ativo, usuarioLogado.getStrUsuario());

			// ver

			if (tbfamiliaec.getPublico()) {
				tbfamiliaec.setPublico(false);
				service.update(tbfamiliaec);
				salvarLogFamilia(tbfamiliaec.getIdFamiliaEc(), "Privado", "Família", "-", getUsuarioLogado().getStrUsuario());
			}
			clausulaTipoZeradas();

			
			
			System.out.println(relatorio.getIdRelatorioEc());
			EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");

		}

	}

	public Tbrelatorioec getRelatorioInserir() {
		return relatorioInserir;
	}

	public void setRelatorioInserir(Tbrelatorioec relatorioInserir) {
		this.relatorioInserir = relatorioInserir;
	}

	public void visualizarRelatorio() {		
		loadClausulaTipoResumo();
		FacesContext fc = FacesContext.getCurrentInstance();
		this.numeroRelatorio = getCountryParam(fc);

		this.pedidoRelatorio = getPedidoRelatorioParam(fc);

		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		relatorio = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

		obterUsuarioSessao();
		if (pedidoRelatorio != "0") {
			getHashParametro().put("idPatenteEc", Long.parseLong(pedidoRelatorio));
			Tbpatenteec patente = (Tbpatenteec) service.findOneResult(FIND_BY_ID_PEDIDO, hashParametro);

			if (relatorioAcesso(usuarioLogado, relatorio, patente, true)) {
				if (relatorio.isRelatorioEmConstrucao()) {

					oUsuarioLogadoNaoTemAcessoAoRelatorioDesejado();

				} else {
					RequestContext.getCurrentInstance().openDialog("visualizarRelatorioHTML");
				}

			} else {
				oUsuarioLogadoNaoTemAcessoAoRelatorioDesejado();
			}
		} else {
			if (relatorioAcesso(usuarioLogado, relatorio, null, true)) {

				if (relatorio.isRelatorioEmConstrucao()) {
					oUsuarioLogadoNaoTemAcessoAoRelatorioDesejado();
				} else {
					RequestContext.getCurrentInstance().openDialog("visualizarRelatorioHTML");
				}
			} else {
				oUsuarioLogadoNaoTemAcessoAoRelatorioDesejado();

			}
		}

	}

	@SuppressWarnings("unchecked")
	private boolean relatorioAcesso(Tbcadusuario tbcadusuario, Tbrelatorioec tbrelatorioec, Tbpatenteec tbpatenteec, boolean ehVisualizar) {

		boolean teste = false;

		if (tbrelatorioec.getBPublico()) {
			return true;
		}

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
					if (tbrelatorioec.getIdStatus().getIdStatus() == 1L && ehVisualizar == true) {
						clausulaTipoZeradas();
						System.out.println(relatorio.getIdRelatorioEc());
						EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");
					} else {
						teste = true;
					}
				} else if (tbrelatorioec.getIdUsuario().getIdEntidadeEc().equals(tbcadusuario.getIdEntidadeEc())) {
					teste = true;
				}
			}
		} else {
			if (tbrelatorioec.getIdUsuario().equals(tbcadusuario)) {
				if (tbrelatorioec.getIdStatus().getIdStatus() == 1L && ehVisualizar == true) {
					clausulaTipoZeradas();
					System.out.println(relatorio.getIdRelatorioEc());
					
					EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");
				} else {
					teste = true;
				}
			} else if (tbrelatorioec.getIdUsuario().getIdEntidadeEc().equals(tbcadusuario.getIdEntidadeEc())) {
				teste = true;
			}
		}
		return teste;
	}

	public String getCountryParam(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("numeroRelatorio");
	}

	public String getPedidoRelatorioParam(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("pedidoRelatorio");
	}

	public String getPedidoCopiarRelaotrio(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("copiarRelatorio");
	}

	public void visualizarPedido() {
		EPECUtil.redirecionar("/epecWeb/pages/visualizarPedido.jsf");
	}

	public void editarResumo() {
		EPECUtil.redirecionar("/epecWeb/pages/editarResumo.jsf");
	}

	public void editarConclusao() {
		EPECUtil.redirecionar("/epecWeb/pages/editarConclusao.jsf");
	}

	public void menuVoltarFamilia() {
		EPECUtil.redirecionar("/epecWeb/pages/manterFamilia.jsf");
	}

	public void menuVoltarRelatorio() {
		clausulaTipoZeradas();
		System.out.println(relatorio.getIdRelatorioEc());
		EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");
	}

	public void inserirCategoria() {
		boolean tem = false;
		for (int i = 0; i < categoriaRelatorio.size(); i++) {
			if (categoriaRelatorio.get(i).getIdCategoria().getIdCategoria() == Integer.parseInt(categoria)) {
				tem = true;
			}
		}
		if (tem) {
			categoriaJaexiste();
		} else {
			tbcatrelatorioec = new Tbcatrelatorioec();

			Tbcadcategoria tbcadcategoria = (Tbcadcategoria) service.bucarPorId(Integer.parseInt(categoria));

			tbcatrelatorioec.setIdCategoria(tbcadcategoria);
			tbcatrelatorioec.setIdRelatorioEc(relatorio);
			tbcatrelatorioec.setTxResumo("");

			service.save(tbcatrelatorioec);

			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Inserir", "Categoria", tbcadcategoria.getStrPortugues(), usuarioLogado.getStrUsuario());
			clausulaTipoZeradas();
			EPECUtil.redirecionar("/epecWeb/pages/inserirCategoria.jsf");
		}

	}

	public void alterarCategoria() {
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/inserirCategoria.jsf");

	}

	public void visualizarCategoria() {
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/visualizarCategoria.jsf");
	}

	public void removerCategoria() {
		if (tbcatrelatorioecExcluir == null) {
			getAnterioridadePatentariaExcluir();
		}

		service.deleteCategoria(tbcatrelatorioecExcluir);
		salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Excluir", "Categoria", tbcatrelatorioecExcluir.getIdCategoria().getStrPortugues(), usuarioLogado.getStrUsuario());

		this.excluirCategoria = "1";
		clausulaTipoZeradas();
		System.out.println(relatorio.getIdRelatorioEc());
		EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");
	}

	public void salvarRelatorio() {

		if (!(relatorio.getTxConclusao().equals(conclusao))) {
			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Alterar", "Conclusão do Relatório", "-", usuarioLogado.getStrUsuario());
		}
		if (!(relatorio.getTxResumo().equals(resumo))) {
			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Alterar", "Resumo do Relatório", "-", usuarioLogado.getStrUsuario());
		}

		if (conclusao != null) {
			relatorio.setTxConclusao(conclusao);
		} else {
			relatorio.setTxConclusao("");
		}

		if (resumo != null) {
			relatorio.setTxResumo(resumo);
		} else {
			relatorio.setTxResumo("");
		}
		
			relatorio.setTxLink(link);
	
		
		service.update(relatorio);
		relatorioSalvoComSucesso();
	}

	public void salvarUmNovo() {
		service.update(relatorioInserirNovo);
		relatorioSalvoComSucesso();
	}

	public void visualizarAntPatentaria() {
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/visualizarAntPatentariaRelec.jsf");
	}

	public void visualizarAntNaoPatentaria() {
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/visualizarAntNaoPatentariaRelec.jsf");
	}

	public void alterarAntPatentaria() {
		this.relacao = null;
		this.reivindicacao = null;
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/alterarAntPatentariaRelec.jsf");
	}

	private void clausulaTipoZeradas() {
		this.selectedNode1 = null;
		this.selectedNode2 = null;
		this.selectedNode3 = null;
		this.selectedNode4 = null;
		this.selectedNode5 = null;
		this.selectedNode6 = null;
		this.resumo = null;
		this.conclusao = null;
	}

	public void alterarAntNaoPatentaria() {
		this.relacaoNP = null;
		this.reivindicacaoNP = null;
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/alterarAntNaoPatentariaRelec.jsf");
	}

	public void updateAntPatentaria() {

		if (anterioridadePatentariaselect.getStrAntpatentaria() == null) {
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();
		} else if (anterioridadePatentariaselect.getStrAntpatentaria().isEmpty()) {
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else {

			if (anterioridadePatentariaselect.getCadTipoAnteriodadePatentaria() != null) {
				Tbcadtipoanterioridade tbcadtipoanterioridade = service.buscaPorIdTipoAnterioridade(Integer.parseInt(anterioridadePatentariaselect.getCadTipoAnteriodadePatentaria()));
				anterioridadePatentariaselect.setIdCadtipoanterioridade(tbcadtipoanterioridade);
			}

			anterioridadePatentariaselect.setTxReivindicacao(reivindicacao);
			anterioridadePatentariaselect.setTxRelacao(relacao);
			service.update(anterioridadePatentariaselect);
			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Alterar", "Anterioridade Patentária do Relatório", anterioridadePatentariaselect.getStrAntpatentaria(), usuarioLogado.getStrUsuario());

			this.alterarAntPatentaria = "1";
			clausulaTipoZeradas();
			System.out.println(relatorio.getIdRelatorioEc());
			EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");
		}
	}

	public void updateAntNaoPatentaria() {

		if (anterioridadeNaoPatentariaSelect.getTxTitulo() == null) {
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();
		} else if (anterioridadeNaoPatentariaSelect.getTxTitulo().isEmpty()) {
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else {
			if (anterioridadeNaoPatentariaSelect.getCadTipoAnteriodadePatentaria() != null) {
				Tbcadtipoanterioridade tbcadtipoanterioridade = service.buscaPorIdTipoAnterioridade(Integer.parseInt(anterioridadeNaoPatentariaSelect.getCadTipoAnteriodadePatentaria()));
				anterioridadeNaoPatentariaSelect.setIdCadtipoanterioridade(tbcadtipoanterioridade);
			}

			anterioridadeNaoPatentariaSelect.setTxReivindicacao(reivindicacaoNP);
			anterioridadeNaoPatentariaSelect.setTxRelacao(relacaoNP);
			service.update(anterioridadeNaoPatentariaSelect);
			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Alterar", "Anterioridade Não Patentária do Relatório", anterioridadeNaoPatentariaSelect.getTxTitulo(), usuarioLogado.getStrUsuario());

			this.alterarAntNaoPatentaria = "1";
			clausulaTipoZeradas();
			System.out.println(relatorio.getIdRelatorioEc());
			EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");
		}

	}

	public void removerAntPatentaria() {
		if (anterioridadePatentariaExcluir == null) {
			getAnterioridadePatentariaExcluir();
		}

		service.deleteAntPatentaria(anterioridadePatentariaExcluir);
		salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Excluir", "Anterioridade Patentária do Relatório", anterioridadePatentariaExcluir.getStrAntpatentaria(), usuarioLogado.getStrUsuario());

		this.excluirAntPatentaria = "1";
		clausulaTipoZeradas();
		System.out.println(relatorio.getIdRelatorioEc());
		EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");
	}

	public void removerAntNaoPatentaria() {
		if (anterioridadeNaoPatentariaExcluir == null) {
			getAnterioridadeNaoPatentaria();
		}

		service.deleteAntNaoPatentaria(anterioridadeNaoPatentariaExcluir);
		salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Excluir", "Anterioridade Não Patentária do Relatório", anterioridadeNaoPatentariaExcluir.getTxTitulo(), usuarioLogado.getStrUsuario());

		this.excluirAntNaoPatentaria = "1";
		clausulaTipoZeradas();
		System.out.println(relatorio.getIdRelatorioEc());
		EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");
	}

	public void salvarAntPatentaria2() {
		if (anterioridadePatentaria.getStrAntpatentaria() == null) {
			anterioridadePatentaria = new Tbantpatentariarelec();
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else if (anterioridadePatentaria.getStrAntpatentaria().isEmpty()) {
			anterioridadePatentaria = new Tbantpatentariarelec();
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else {
			getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
			relatorio = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

			Tbcadtipoanterioridade tbcadtipoanterioridade = service.buscaPorIdTipoAnterioridade(Integer.parseInt(anterioridadePatentaria.getCadTipoAnteriodadePatentaria()));
			anterioridadePatentaria.setIdCadtipoanterioridade(tbcadtipoanterioridade);
			anterioridadePatentaria.setIdRelatorioEc(relatorio);
			anterioridadePatentaria.setTxReivindicacao(reivindicacao);
			anterioridadePatentaria.setTxRelacao(relacao);
			service.save(anterioridadePatentaria);
			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Inserir", "Anterioridade Patentária do Relatório", anterioridadePatentaria.getStrAntpatentaria(), usuarioLogado.getStrUsuario());

			anterioridadePatentaria = new Tbantpatentariarelec();

			this.salvarAntPatentaria = "1";
			clausulaTipoZeradas();
			System.out.println(relatorio.getIdRelatorioEc());
			EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");
		}
	}

	public void salvarAntNaoPatentaria2() {

		if (anterioridadeNaoPatentaria.getTxTitulo() == null) {
			anterioridadeNaoPatentaria = new Tbantnaopatentariarelec();
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();
		} else if (anterioridadeNaoPatentaria.getTxTitulo().isEmpty()) {
			anterioridadeNaoPatentaria = new Tbantnaopatentariarelec();
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else {

			getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));

			relatorio = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

			Tbcadtipoanterioridade tbcadtipoanterioridade = service.buscaPorIdTipoAnterioridade(Integer.parseInt(anterioridadeNaoPatentaria.getCadTipoAnteriodadePatentaria()));
			anterioridadeNaoPatentaria.setIdCadtipoanterioridade(tbcadtipoanterioridade);
			anterioridadeNaoPatentaria.setIdRelatorioEc(relatorio);
			anterioridadeNaoPatentaria.setTxRelacao(relacaoNP);
			anterioridadeNaoPatentaria.setTxReivindicacao(reivindicacaoNP);

			service.save(anterioridadeNaoPatentaria);
			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Inserir", "Anterioridade Não Patentária do Relatório", anterioridadeNaoPatentaria.getTxTitulo(), usuarioLogado.getStrUsuario());

			anterioridadeNaoPatentaria = new Tbantnaopatentariarelec();

			this.salvarAntNaoPatentaria = "1";
			clausulaTipoZeradas();
			System.out.println(relatorio.getIdRelatorioEc());
			EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");
		}

	}

	@SuppressWarnings("unchecked")
	private void loadPais() {

		categorias = (List<Tbcadcategoria>) (Object) service.listAll(FIND_ALL_CATEGORIA);
	}

	public String getNumeroRelatorio() {
		return numeroRelatorio;
	}

	public void setNumeroRelatorio(String numeroRelatorio) {
		this.numeroRelatorio = numeroRelatorio;
	}

	@SuppressWarnings("unchecked")
	public void createColaboracao() {

		if (alterarColaboracao.size() == 0) {
			
			this.copiarRelatorio ="3";
			System.out.println(relatorio.getIdRelatorioEc());
			EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");
	

		} else {			
			
			
			getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));

			relatorio = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

			getHashParametro().put("idRelatorio", Long.parseLong(numeroRelatorio));
			List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);

			
			if(podeAlterarColaboracao()){
				for (int i = 0; i < relatorioColaboracao.size(); i++) {
					service.deleteColaboracao(relatorioColaboracao.get(i));
				}

				for (int i = 0; i < alterarColaboracao.size(); i++) {
					getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
					relatorio = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);
					getHashParametro().put("idColaboracao", Long.parseLong(alterarColaboracao.get(i)));
					Tbcadcolaboracao colaboracao = (Tbcadcolaboracao) service.findOneResult(FIND_BY_ID_COLABORACAO, hashParametro);

					Tbrelatoriocolaboracao tbrelatoriocolaboracao = new Tbrelatoriocolaboracao();
					TbRelatorioColaboracaoPK colaboracaoPK = new TbRelatorioColaboracaoPK();
					colaboracaoPK.setIdColaboracao(colaboracao.getIdColaboracao());
					colaboracaoPK.setIdRelatorio(relatorio.getIdRelatorioEc());

					tbrelatoriocolaboracao.setColaboracaoPK(colaboracaoPK);

					service.save(tbrelatoriocolaboracao);

				}
				alterarAntPatentaria = "1";
				
				
				salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Alterar", "Colaboração", "-", usuarioLogado.getStrUsuario());

				System.out.println(relatorio.getIdRelatorioEc());
				EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");

			}else{
				
				this.copiarRelatorio ="2";
				System.out.println(relatorio.getIdRelatorioEc());
				EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");
			}
					
		}
	}

	@SuppressWarnings("unchecked")
	private boolean podeAlterarColaboracao() {
		
		 List<String> entidadesRelacioandaAoRelatorio = new ArrayList<>();

		 List<String> entidadesSelecionadasNova = new ArrayList<>();
		 
		for(int a = 0 ; a < alterarColaboracao.size(); a++){
				getHashParametro().put("idColaboracao",  Long.parseLong(alterarColaboracao.get(a)));				
				List<Tbcolaboracaoentidade> tbcolaboEntidades = (List<Tbcolaboracaoentidade>) (Object) service.list(FIND_ENTIDADE_COLABORACAO, hashParametro);
				for(int b = 0; b<tbcolaboEntidades.size();b++){
					
					if(validarEntidade(tbcolaboEntidades.get(b).getIdEntidadeEc().toString(),entidadesSelecionadasNova)){
						entidadesSelecionadasNova.add(tbcolaboEntidades.get(b).getIdEntidadeEc().toString());
					}
				}				
		}
		getHashParametro().put("idRelatorio", Long.parseLong(numeroRelatorio));
		List<Tbrelatoriopatente> relatorioPatente = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_RELATORIO, hashParametro);
		
		for(int c = 0; c < relatorioPatente.size(); c++){
			getHashParametro().put("idPatenteEc", relatorioPatente.get(c).getTbRelatorioPatentePK().getIdPatente());
			Tbpatenteec patente = (Tbpatenteec) service.findOneResult(FIND_BY_ID_PEDIDO, hashParametro);
			if(validarEntidade(patente.getIdEntidadeEc().getIdEntidadeEc().toString(), entidadesRelacioandaAoRelatorio)){
				entidadesRelacioandaAoRelatorio.add(patente.getIdEntidadeEc().getIdEntidadeEc().toString());

			}
			
		}
		
		int contX = 0;
		
		for(int d = 0 ;d<entidadesRelacioandaAoRelatorio.size();d ++){
			for(int e = 0 ; e < entidadesSelecionadasNova.size(); e++){
				if(entidadesRelacioandaAoRelatorio.get(d).equals(entidadesSelecionadasNova.get(e))){
					contX ++;
				}
			}		
			
		}
		
		if(contX == entidadesRelacioandaAoRelatorio.size()){
			return true;
		}else{
			return false;
		}
	}

	private boolean validarEntidade(String string, List<String> entidade) {
		
		for(int a = 0; a < entidade.size(); a ++){
			if(entidade.get(a).equals(string)){
				return false;
			}
		}
		
		return true;
	}

	public void loadClausulaTipo() {

		externalContext = FacesContext.getCurrentInstance().getExternalContext();
		@SuppressWarnings("unused")
		String index = externalContext.getRequestParameterMap().get("tabIndex");
		tipo = externalContext.getRequestParameterMap().get("tipo");

		if (tipo != null) {

			Tbcadativo cadAtivo = service.findCadAtivoPorNome(tipo);

			clausulaTipoList = service.findClausulaTipoPorIdAtivo(cadAtivo.getIdAtivo());

			root1 = createDocuments(usuarioLogado);

			nos = service.findNosPorIdAtivo(cadAtivo.getIdAtivo());

			setTabIndex(Integer.parseInt("2"));

			externalContext.getSessionMap().put("tabIndex", 2);

		}

	}

	public void loadClausulaTipoResumo() {

		root1 = buscaArvore();

		root2 = root1;

		root3 = root1;

		root4 = root1;
	}

	public TreeNode buscaArvore() {

		if (usuarioLogado == null) {
			getUsuarioLogado();
		}
		TreeNode root = createDocuments(usuarioLogado);

		return root;
	}

	@SuppressWarnings("unchecked")
	public TreeNode createDocuments(Tbcadusuario tbcadusuario) {

		Tbnos noRaiz = new Tbnos();
		TreeNode root = null;

		getHashParametro().put("idEntidadeEc", tbcadusuario.getIdEntidadeEc().getIdEntidadeEc());

		List<Tbnos> nosRaiz = (List<Tbnos>) (Object) service.list(FIND_TBNO_BY_ENTIDADE, hashParametro);

		for (int i = 0; i < nosRaiz.size(); i++) {
			if (nosRaiz.get(i).getIdTbnoPai() == null) {
				noRaiz = nosRaiz.get(i);
			}
		}

		// montar Arvore pelo idioma;

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			root = new DefaultTreeNode("Root", null);
			TreeNode node0 = new DefaultTreeNode(noRaiz.getTxTituloEspanhol(), root);

			for (int i = 0; i < noRaiz.getTbnosList().size(); i++) {
				ValidarArvore(noRaiz.getTbnosList().get(i), node0, null);
			}

		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			root = new DefaultTreeNode("Root", null);
			TreeNode node0 = new DefaultTreeNode(noRaiz.getTxTituloIngles(), root);
			for (int i = 0; i < noRaiz.getTbnosList().size(); i++) {
				ValidarArvore(noRaiz.getTbnosList().get(i), node0, null);
			}

		} else {
			root = new DefaultTreeNode("Root", null);
			TreeNode node0 = new DefaultTreeNode(noRaiz.getTxTituloPortugues(), root);

			if (noRaiz.getTbnosList() != null) {
				for (int i = 0; i < noRaiz.getTbnosList().size(); i++) {
					ValidarArvore(noRaiz.getTbnosList().get(i), node0, null);
				}

			}

		}

		return root;

	}


	
	private void ValidarArvore(Tbnos no, TreeNode pai, TreeNode filho) {

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {

			filho = new DefaultTreeNode(new Document(no.getTxTituloEspanhol(), "-", "Folder", null), pai);

			if (no.getIdClausulaTipo() != null) {


				filho.getChildren().add(new DefaultTreeNode(no.getIdClausulaTipo().getTxEspanhol()));
			}
			for (int i = 0; i < no.getTbnosList().size(); i++) {

				ValidarArvore(no.getTbnosList().get(i), filho, null);
			}

		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {

			filho = new DefaultTreeNode(new Document(no.getTxTituloIngles(), "-", "Folder", null), pai);

			if (no.getIdClausulaTipo() != null) {

				filho.getChildren().add(new DefaultTreeNode(no.getIdClausulaTipo().getTxIngles()));

	
			}
			for (int i = 0; i < no.getTbnosList().size(); i++) {

				ValidarArvore(no.getTbnosList().get(i), filho, null);
			}

		} else {
			filho = new DefaultTreeNode(new Document(no.getTxTituloPortugues(), "-", "Folder", null), pai);

			if (no.getIdClausulaTipo() != null) {
				filho.getChildren().add(new DefaultTreeNode(no.getIdClausulaTipo().getTxPortugues()));

			}
			for (int i = 0; i < no.getTbnosList().size(); i++) {

				ValidarArvore(no.getTbnosList().get(i), filho, null);
			}
		}

	}

	public void visualizarRelatorioHTML() {

		FacesContext fc = FacesContext.getCurrentInstance();
		this.numeroRelatorio = getCountryParam(fc);

		this.pedidoRelatorio = getPedidoRelatorioParam(fc);

		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		relatorio = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

		obterUsuarioSessao();

		if (pedidoRelatorio != "0") {
			getHashParametro().put("idPatenteEc", Long.parseLong(pedidoRelatorio));
			Tbpatenteec patente = (Tbpatenteec) service.findOneResult(FIND_BY_ID_PEDIDO, hashParametro);

			if (relatorioAcesso(usuarioLogado, relatorio, patente, true)) {

				RequestContext.getCurrentInstance().openDialog("visualizarRelatorioHTML");

			} else {
				oUsuarioLogadoNaoTemAcessoAoRelatorioDesejado();
			}
		} else {
			if (relatorioAcesso(usuarioLogado, relatorio, null, true)) {

				RequestContext.getCurrentInstance().openDialog("visualizarRelatorioHTML");

			} else {
				oUsuarioLogadoNaoTemAcessoAoRelatorioDesejado();

			}
		}
	}

	public Tbrelatorioec getRelatorioSelecionado() {
		return relatorioSelecionado;
	}

	public void setRelatorioSelecionado(Tbrelatorioec relatorioSelecionado) {
		this.relatorioSelecionado = relatorioSelecionado;
	}

	public void deletarRelatorio() {
		System.out.println("apagou");
	}

	public Tbrelatorioec getRelatorio() {
		getHashParametro().put("idRelatorioEc", Long.parseLong(getNumeroRelatorio()));
		relatorio = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);
		return relatorio;
	}

	public void setRelatorio(Tbrelatorioec relatorio) {
		this.relatorio = relatorio;
	}

	@SuppressWarnings("unchecked")
	public List<Tbpatenteec> getPatentes() {

		if (tbfamiliaec == null) {
			tbfamiliaec = getTbfamiliaec();
		}

		for (int i = 0; tbfamiliaec.getTbpatenteecList().size() > i; i++) {

			getHashParametro().put("idPatente", tbfamiliaec.getTbpatenteecList().get(i).getIdPatenteEc());
			List<Tbrelatoriopatente> relatorioPatente = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_PATENTE, hashParametro);

			if (relatorioPatente.size() == 0) {
				tbfamiliaec.getTbpatenteecList().get(i).setTbrelatorioec(null);

				tbfamiliaec.getTbpatenteecList().get(i).setEmColaboracao("");
			} else if (relatorioPatente.size() == 1) {

				getHashParametro().put("idRelatorioEc", relatorioPatente.get(0).getTbRelatorioPatentePK().getIdRelatorio());
				Tbrelatorioec tbrelatorioec = (Tbrelatorioec) service.findOneResult(FIND_BY_ID_RELATORIO, hashParametro);

				tbfamiliaec.getTbpatenteecList().get(i).setTbrelatorioec(tbrelatorioec);
				tbfamiliaec.getTbpatenteecList().get(i).setEmColaboracao("Não");

				getHashParametro().put("idRelatorio", tbrelatorioec.getIdRelatorioEc());
				List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);
				for (int b = 0; b < relatorioColaboracao.size(); b++) {

					getHashParametro().put("idColaboracao", relatorioColaboracao.get(b).getColaboracaoPK().getIdColaboracao());
					List<Tbcolaboracaoentidade> tbcolaboEntidades = (List<Tbcolaboracaoentidade>) (Object) service.list(FIND_ENTIDADE_COLABORACAO, hashParametro);

					for (int f = 0; f < tbcolaboEntidades.size(); f++) {
						getHashParametro().put("idEntidadeEc", tbcolaboEntidades.get(f).getIdEntidadeEc());
						Tbcadentidade tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);

						if (tbcadentidade.equals(usuarioLogado.getIdEntidadeEc())) {
							tbfamiliaec.getTbpatenteecList().get(i).setEmColaboracao("Sim");
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

				tbfamiliaec.getTbpatenteecList().get(i).setTbrelatorioec(tbrelatorioecNovo);

				tbfamiliaec.getTbpatenteecList().get(i).setEmColaboracao("Não");

				getHashParametro().put("idRelatorio", tbrelatorioecNovo.getIdRelatorioEc());
				List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);
				for (int b = 0; b < relatorioColaboracao.size(); b++) {

					getHashParametro().put("idColaboracao", relatorioColaboracao.get(b).getColaboracaoPK().getIdColaboracao());
					List<Tbcolaboracaoentidade> tbcolaboEntidades = (List<Tbcolaboracaoentidade>) (Object) service.list(FIND_ENTIDADE_COLABORACAO, hashParametro);

					for (int f = 0; f < tbcolaboEntidades.size(); f++) {
						getHashParametro().put("idEntidadeEc", tbcolaboEntidades.get(f).getIdEntidadeEc());
						Tbcadentidade tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);

						if (tbcadentidade.equals(usuarioLogado.getIdEntidadeEc())) {
							tbfamiliaec.getTbpatenteecList().get(i).setEmColaboracao("Sim");
						}
					}
				}
			}

		}

		return tbfamiliaec.getTbpatenteecList();

	}

	public void buscaRelatorio() {

		if (copiarRelatorio != null){
			if (copiarRelatorio.equals("1")) {

				relatorioCopiadoComSucesso();

			}
			if (copiarRelatorio.equals("2")) {

				relatorioNaoPodealterarColaboracao();
			}
			if(copiarRelatorio.equals("3")){
				
				relatorioSemColaboracao();
			}
			
		}
		
		if (excluirCategoria != null){
			if (excluirCategoria.equals("1")) {
				excluidoComSucesso();
			}
		}

		
		if (salvarAntNaoPatentaria != null){
			if (salvarAntNaoPatentaria.equals("1")) {

				incluidoComSucesso();

			}
		}
		if (salvarAntPatentaria != null){
			if (salvarAntPatentaria.equals("1")) {

				incluidoComSucesso();

			}
		}
		
		if (excluirAntNaoPatentaria != null){
			if (excluirAntNaoPatentaria.equals("1")) {

				excluidoComSucesso();

			}
		}
		
		if (excluirAntPatentaria != null){
			if (excluirAntPatentaria.equals("1")) {

				excluidoComSucesso();

			}
		}

		
		if (alterarAntNaoPatentaria != null){
			if (alterarAntNaoPatentaria.equals("1")) {

				alteradoComSucesso();

			}
		}

		
		if (alterarAntPatentaria != null){
			if (alterarAntPatentaria.equals("1")) {

				alteradoComSucesso();

			}
			
		}

		alterarAntPatentaria = null;	
		alterarAntNaoPatentaria = null;
		excluirAntPatentaria = null;
		excluirAntNaoPatentaria = null;	
		salvarAntPatentaria = null;		
		salvarAntNaoPatentaria = null;		
		excluirCategoria = null;
		copiarRelatorio = null;

	}





	public void setPatentes(List<Tbpatenteec> patentes) {
		this.patentes = patentes;
	}

	@SuppressWarnings("unchecked")
	public List<Tbpatenteec> getPatentes2() {
		List<Tbpatenteec> teste = new ArrayList<>();
		getHashParametro().put("idRelatorio", Long.parseLong(numeroRelatorio));
		List<Tbrelatoriopatente> relatorioPatente = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_RELATORIO, hashParametro);

		for (int i = 0; i < relatorioPatente.size(); i++) {
			getHashParametro().put("idPatenteEc", relatorioPatente.get(i).getTbRelatorioPatentePK().getIdPatente());
			Tbpatenteec patente = (Tbpatenteec) service.findOneResult(FIND_BY_ID_PEDIDO, hashParametro);
			teste.add(patente);
		}

		return teste;
	}

	@SuppressWarnings("unchecked")
	public List<Tbcadcolaboracao> getColaboracao() {

		List<Tbcadcolaboracao> cola = new ArrayList<>();
		getHashParametro().put("idRelatorio", Long.parseLong(numeroRelatorio));

		List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);

		for (int i = 0; i < relatorioColaboracao.size(); i++) {
			getHashParametro().put("idColaboracao", relatorioColaboracao.get(i).getColaboracaoPK().getIdColaboracao());
			Tbcadcolaboracao colaboracao = (Tbcadcolaboracao) service.findOneResult(FIND_BY_ID_COLABORACAO, hashParametro);
			cola.add(colaboracao);
		}

		return cola;
	}

	public void setColaboracao(List<Tbcadcolaboracao> colaboracao) {
		this.colaboracao = colaboracao;
	}

	public List<Tbcadcategoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Tbcadcategoria> categorias) {
		this.categorias = categorias;
	}

	public List<Tbcadcategoria> getEscolherCategoria() {
		return escolherCategoria;
	}

	public void setEscolherCategoria(List<Tbcadcategoria> escolherCategoria) {
		this.escolherCategoria = escolherCategoria;
	}

	@SuppressWarnings("unchecked")
	public List<String> getAlterarColaboracao() {
		getHashParametro().put("idRelatorio", Long.parseLong(numeroRelatorio));
		List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);

		for (int i = 0; i < relatorioColaboracao.size(); i++) {
			getHashParametro().put("idColaboracao", relatorioColaboracao.get(i).getColaboracaoPK().getIdColaboracao());
			Tbcadcolaboracao colaboracao = (Tbcadcolaboracao) service.findOneResult(FIND_BY_ID_COLABORACAO, hashParametro);
			alterarColaboracao.add(colaboracao.getIdColaboracao().toString());
		}

		return alterarColaboracao;
	}

	public void setAlterarColaboracao(List<String> alterarColaboracao) {
		this.alterarColaboracao = alterarColaboracao;
	}

	public void inserirAnterioridadePatentaria() {

		if (usuarioTemAcessoRelatorio(numeroRelatorio)) {

			oUsuarioLogadoNaoPodeRealizarTalOperacao();

		} else {

			this.relacao = null;
			this.reivindicacao = null;
			this.anterioridadePatentaria = new Tbantpatentariarelec();
			this.anterioridadePatentariaselect = null;
			clausulaTipoZeradas();
			EPECUtil.redirecionar("/epecWeb/pages/inserirAnterioridadePatentariaRelatorio.jsf");
		}

	}

	private boolean usuarioTemAcessoRelatorio(String numeroRelatorio2) {

		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio2));
		relatorio = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);
		obterUsuarioSessao();

		if (usuarioLogado.equals(relatorio.getIdUsuario())) {
			return false;
		}

		return true;

	}

	public void inserirAnterioridadeNaoPatentaria() {

		if (usuarioTemAcessoRelatorio(numeroRelatorio)) {

			oUsuarioLogadoNaoPodeRealizarTalOperacao();
		} else {
			this.relacaoNP = null;
			this.reivindicacaoNP = null;
			this.anterioridadeNaoPatentaria = new Tbantnaopatentariarelec();
			this.anterioridadeNaoPatentariaSelect = null;
			clausulaTipoZeradas();
			EPECUtil.redirecionar("/epecWeb/pages/inserirAnterioridadeNaoPatentariaRelatorio.jsf");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Tbantpatentariarelec> getAntPatentaria() {
		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		antPatentaria = (List<Tbantpatentariarelec>) (Object) service.list(FIND_ANTPATENTARIARELATORIO_PELO_RELATORIO, hashParametro);

		return antPatentaria;
	}

	public void setAntPatentaria(List<Tbantpatentariarelec> antPatentaria) {
		this.antPatentaria = antPatentaria;
	}

	@SuppressWarnings("unchecked")
	public List<Tbantnaopatentariarelec> getAntnaoPatentaria() {

		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		antnaoPatentaria = (List<Tbantnaopatentariarelec>) (Object) service.list(FIND_ANTNAOPATENTARIARELATORIO_PELO_RELATORIO, hashParametro);

		return antnaoPatentaria;
	}

	public void setAntnaoPatentaria(List<Tbantnaopatentariarelec> antnaoPatentaria) {
		this.antnaoPatentaria = antnaoPatentaria;
	}

	public Tbantpatentariarelec getAnterioridadePatentaria() {
		return anterioridadePatentaria;
	}

	public void setAnterioridadePatentaria(Tbantpatentariarelec anterioridadePatentaria) {
		this.anterioridadePatentaria = anterioridadePatentaria;
	}

	@SuppressWarnings("unchecked")
	public List<Tbcadtipoanterioridade> getEscolherRelevancia() {
		escolherRelevancia = (List<Tbcadtipoanterioridade>) (Object) service.listAll(FIND_ALL_CADTIPOANTERIORIDADE);
		return escolherRelevancia;
	}

	public void setEscolherRelevancia(List<Tbcadtipoanterioridade> escolherRelevancia) {
		this.escolherRelevancia = escolherRelevancia;
	}

	public List<Tbcadtipoanterioridade> getEscolherRelevanciaNaoPatentaria() {
		escolherRelevanciaNaoPatentaria = service.listarNaoPatentarias();
		return escolherRelevanciaNaoPatentaria;
	}

	public void setEscolherRelevanciaNaoPatentaria(List<Tbcadtipoanterioridade> escolherRelevanciaNaoPatentaria) {
		this.escolherRelevanciaNaoPatentaria = escolherRelevanciaNaoPatentaria;
	}

	public Tbantnaopatentariarelec getAnterioridadeNaoPatentaria() {
		return anterioridadeNaoPatentaria;
	}

	public void setAnterioridadeNaoPatentaria(Tbantnaopatentariarelec anterioridadeNaoPatentaria) {
		this.anterioridadeNaoPatentaria = anterioridadeNaoPatentaria;
	}

	@SuppressWarnings("unchecked")
	public List<Tbcatrelatorioec> getCategoriaRelatorio() {
		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		categoriaRelatorio = (List<Tbcatrelatorioec>) (Object) service.list(FIND_CATEGORIARELATORIO_BY_RELATORIO, hashParametro);

		return categoriaRelatorio;
	}

	public void setCategoriaRelatorio(List<Tbcatrelatorioec> categoriaRelatorio) {
		this.categoriaRelatorio = categoriaRelatorio;
	}

	public Tbantpatentariarelec getAnterioridadePatentariaselect() {
		return anterioridadePatentariaselect;
	}

	public void setAnterioridadePatentariaselect(Tbantpatentariarelec anterioridadePatentariaselect) {
		this.anterioridadePatentariaselect = anterioridadePatentariaselect;
	}

	public Tbantpatentariarelec getAnterioridadePatentariaExcluir() {
		return anterioridadePatentariaExcluir;
	}

	public void setAnterioridadePatentariaExcluir(Tbantpatentariarelec anterioridadePatentariaExcluir) {
		this.anterioridadePatentariaExcluir = anterioridadePatentariaExcluir;
	}

	public Tbantnaopatentariarelec getAnterioridadeNaoPatentariaSelect() {
		return anterioridadeNaoPatentariaSelect;
	}

	public void setAnterioridadeNaoPatentariaSelect(Tbantnaopatentariarelec anterioridadeNaoPatentariaSelect) {
		this.anterioridadeNaoPatentariaSelect = anterioridadeNaoPatentariaSelect;
	}

	public Tbantnaopatentariarelec getAnterioridadeNaoPatentariaExcluir() {
		return anterioridadeNaoPatentariaExcluir;
	}

	public void setAnterioridadeNaoPatentariaExcluir(Tbantnaopatentariarelec anterioridadeNaoPatentariaExcluir) {
		this.anterioridadeNaoPatentariaExcluir = anterioridadeNaoPatentariaExcluir;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Tbcatrelatorioec getTbcatrelatorioec() {
		return tbcatrelatorioec;
	}

	public void setTbcatrelatorioec(Tbcatrelatorioec tbcatrelatorioec) {
		this.tbcatrelatorioec = tbcatrelatorioec;
	}

	public Tbcatrelatorioec getTbcatrelatorioecExcluir() {
		return tbcatrelatorioecExcluir;
	}

	public void setTbcatrelatorioecExcluir(Tbcatrelatorioec tbcatrelatorioecExcluir) {
		this.tbcatrelatorioecExcluir = tbcatrelatorioecExcluir;
	}

	public Tbclausulatipo getClausulaTipo() {
		return clausulaTipo;
	}

	public void setClausulaTipo(Tbclausulatipo clausulaTipo) {
		this.clausulaTipo = clausulaTipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public TreeNode getRoot1() {
		return root1;
	}

	public void setRoot1(TreeNode root1) {
		this.root1 = root1;
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public List<Tbnos> getNos() {
		return nos;
	}

	public void setNos(List<Tbnos> nos) {
		this.nos = nos;
	}

	public List<Tbclausulatipo> getClausulaTipoList() {
		return clausulaTipoList;
	}

	public void setClausulaTipoList(List<Tbclausulatipo> clausulaTipoList) {
		this.clausulaTipoList = clausulaTipoList;
	}

	public void displaySelectedSingle() {

		if (selectedNode1 != null) {

			String valor = selectedNode1.getData().toString();
			resumo = resumo + valor;
		}

		if (selectedNode2 != null) {
			String valor = selectedNode2.getData().toString();
			this.setConclusao(conclusao + valor);
		}

		if (selectedNode3 != null) {
			String valor = selectedNode3.getData().toString();
			if (relacao == null)
				relacao = "";
			this.setRelacao(relacao + valor);
		}

		if (selectedNode4 != null) {
			String valor = selectedNode4.getData().toString();
			if (reivindicacao == null)
				reivindicacao = "";
			this.setReivindicacao(reivindicacao + valor);
		}

		if (selectedNode5 != null) {
			String valor = selectedNode5.getData().toString();
			if (relacaoNP == null)
				relacaoNP = "";
			this.setRelacaoNP(relacaoNP + valor);
		}

		if (selectedNode6 != null) {
			String valor = selectedNode6.getData().toString();
			if (reivindicacaoNP == null)
				reivindicacaoNP = "";
			this.setReivindicacaoNP(reivindicacaoNP + valor);
		}
		
		clausulaTipoZeradas();

		
	}

	public String getCopiarRelatorio() {
		return copiarRelatorio;
	}

	public void setCopiarRelatorio(String copiarRelatorio) {
		this.copiarRelatorio = copiarRelatorio;
	}

	public TreeNode getRoot2() {
		return root2;
	}

	public void setRoot2(TreeNode root2) {
		this.root2 = root2;
	}

	public TreeNode getSelectedNode1() {
		return selectedNode1;
	}

	public void setSelectedNode1(TreeNode selectedNode1) {
		this.selectedNode1 = selectedNode1;
	}

	public TreeNode getSelectedNode2() {
		return selectedNode2;
	}

	public void setSelectedNode2(TreeNode selectedNode2) {
		this.selectedNode2 = selectedNode2;
	}

	public TreeNode getSelectedNode3() {
		return selectedNode3;
	}

	public void setSelectedNode3(TreeNode selectedNode3) {
		this.selectedNode3 = selectedNode3;
	}

	public TreeNode getRoot3() {
		return root3;
	}

	public void setRoot3(TreeNode root3) {
		this.root3 = root3;
	}

	public TreeNode getRoot4() {
		return root4;
	}

	public void setRoot4(TreeNode root4) {
		this.root4 = root4;
	}

	public TreeNode getSelectedNode4() {
		return selectedNode4;
	}

	public void setSelectedNode4(TreeNode selectedNode4) {
		this.selectedNode4 = selectedNode4;
	}

	public String getResumo() {

		if (resumo != null) {
			if (resumo.trim().isEmpty()) {
				return relatorio.getTxResumo();
			} else {
				return resumo;
			}

		}

		return relatorio.getTxResumo();
	}

	public void setResumo(String resumo) {

		if ((!"".equals(resumo)) & (resumo != null)) {
			this.resumo = resumo;
		}
	}

	public String getConclusao() {

		if (conclusao != null) {
			if (conclusao.trim().isEmpty()) {
				return relatorio.getTxConclusao();
			} else {
				return conclusao;
			}

		}

		return relatorio.getTxConclusao();
	}

	public void setConclusao(String conclusao) {

		if ((!"".equals(conclusao)) & (conclusao != null)) {
			this.conclusao = conclusao;
		}

	}

	public String getRelacao() {

		if (relacao != null) {
			return relacao;
		}
		
		if (this.getAnterioridadePatentaria().getTxRelacao() != null) {

			return this.getAnterioridadePatentaria().getTxRelacao();

		}
		if (this.getAnterioridadePatentariaselect() != null) {
			if (this.getAnterioridadePatentariaselect().getTxRelacao() != null) {

				return this.getAnterioridadePatentariaselect().getTxRelacao();

			}
		}

		return "";
	}

	public void setRelacao(String relacao) {

		if ((!"".equals(relacao)) & (relacao != null)) {
			this.relacao = relacao;
		}
	}

	public String getReivindicacao() {

		if (reivindicacao != null) {
			return reivindicacao;
		}
		if (this.getAnterioridadePatentaria().getTxReivindicacao() != null) {
			return this.getAnterioridadePatentaria().getTxReivindicacao();
		}
		if (this.getAnterioridadePatentariaselect() != null) {

			if (this.getAnterioridadePatentariaselect().getTxReivindicacao() != null) {
				return this.getAnterioridadePatentariaselect().getTxReivindicacao();
			}
		}
		return "";

	}

	public void setReivindicacao(String reivindicacao) {

		if ((!"".equals(reivindicacao)) & (reivindicacao != null)) {
			this.reivindicacao = reivindicacao;
		}
	}

	public String getRelacaoNP() {

		if (relacaoNP != null) {
			return relacaoNP;
		}
		if (this.getAnterioridadeNaoPatentaria().getTxRelacao() != null) {

			return this.getAnterioridadeNaoPatentaria().getTxRelacao();

		}
		if (this.getAnterioridadeNaoPatentariaSelect() != null) {
			if (this.getAnterioridadeNaoPatentariaSelect().getTxRelacao() != null) {

				return this.getAnterioridadeNaoPatentariaSelect().getTxRelacao();

			}
		}
		return "";

	}

	public void setRelacaoNP(String relacaoNP) {

		if ((!"".equals(relacaoNP)) & (relacaoNP != null)) {
			this.relacaoNP = relacaoNP;
		}
	}

	public String getReivindicacaoNP() {

		if (reivindicacaoNP != null) {
			return reivindicacaoNP;
		}
		if (this.getAnterioridadeNaoPatentaria().getTxReivindicacao() != null) {
			return this.getAnterioridadeNaoPatentaria().getTxReivindicacao();
		}
		if (this.getAnterioridadeNaoPatentariaSelect() != null) {
			if (this.getAnterioridadeNaoPatentariaSelect().getTxReivindicacao() != null) {
				return this.getAnterioridadeNaoPatentariaSelect().getTxReivindicacao();
			}
		}
		return "";
	}

	public void setReivindicacaoNP(String reivindicacaoNP) {

		if ((!"".equals(reivindicacaoNP)) & (reivindicacaoNP != null)) {
			this.reivindicacaoNP = reivindicacaoNP;
		}
	}

	public TreeNode getSelectedNode5() {
		return selectedNode5;
	}

	public void setSelectedNode5(TreeNode selectedNode5) {
		this.selectedNode5 = selectedNode5;
	}

	public TreeNode getSelectedNode6() {
		return selectedNode6;
	}

	public void setSelectedNode6(TreeNode selectedNode6) {
		this.selectedNode6 = selectedNode6;

	}

	public Tbrelatorioec getRelatorioInserirNovo() {
		return relatorioInserirNovo;
	}

	public void setRelatorioInserirNovo(Tbrelatorioec relatorioInserirNovo) {
		this.relatorioInserirNovo = relatorioInserirNovo;
	}

	public Tbpatenteec getPedidoRelacionadoAoRelatorioNovo() {
		return pedidoRelacionadoAoRelatorioNovo;
	}

	public void setPedidoRelacionadoAoRelatorioNovo(Tbpatenteec pedidoRelacionadoAoRelatorioNovo) {
		this.pedidoRelacionadoAoRelatorioNovo = pedidoRelacionadoAoRelatorioNovo;
	}

	public Tbfamiliaec getTbfamiliaec() {
		return tbfamiliaec;
	}

	public void setTbfamiliaec(Tbfamiliaec tbfamiliaec) {
		this.tbfamiliaec = tbfamiliaec;
	}

	@SuppressWarnings("unchecked")
	public Tbrelatorioec getRelatorioHTML() {

		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		relatorioHTML = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

		relatorioHTML.setTbantpatentariarelecList(getAntPatentaria());
		relatorioHTML.setTbantnaopatentariarelecList(getAntnaoPatentaria());

		relatorioHTML.setTbcatrelatorioecList(getCategoriaRelatorio());

		for (int i = 0; i < relatorioHTML.getTbcatrelatorioecList().size(); i++) {

			List<Tbantnaopatentariacatrelec> list = new ArrayList<>();

			getHashParametro().put("idCatrelatorioec", relatorioHTML.getTbcatrelatorioecList().get(i).getIdCatrelatorioec());
			List<Tbantnaopatentariacatrelec> tbantNaopatentariacatrelec = (List<Tbantnaopatentariacatrelec>) (Object) service.list(FIND_ANTNAOPATENTARIACATRELEC_BY_CATRELATORIO, hashParametro);

			if (tbantNaopatentariacatrelec != null) {
				list.addAll(tbantNaopatentariacatrelec);
			}

			List<Tbantpatentariacatrelec> list1 = new ArrayList<>();

			getHashParametro().put("idCatrelatorioec", relatorioHTML.getTbcatrelatorioecList().get(i).getIdCatrelatorioec());
			List<Tbantpatentariacatrelec> tbantpatentariacatrelec = (List<Tbantpatentariacatrelec>) (Object) service.list(FIND_ANTPATENTARIACATRELEC_BY_CATRELATORIO, hashParametro);

			if (tbantpatentariacatrelec != null) {
				list1.addAll(tbantpatentariacatrelec);
			}

			List<Tbcaraccatrelatorioec> tbcaraccatrelatorioecs = new ArrayList<>();

			getHashParametro().put("idCatrelatorioec", relatorioHTML.getTbcatrelatorioecList().get(i).getIdCatrelatorioec());
			List<Tbcaraccatrelatorioec> caracteristicaRelacionada = (List<Tbcaraccatrelatorioec>) (Object) service.list(FIND_CARACCATRELATORIO_BY_CATRELATORIO, hashParametro);

			for (int e = 0; e < caracteristicaRelacionada.size(); e++) {

				getHashParametro().put("idCaraccatrelatorio", caracteristicaRelacionada.get(e).getIdCaraccatrelatorio());
				List<Tbantpatentaria> tbantpatentarias = (List<Tbantpatentaria>) (Object) service.list(FIND_PATENTARIA_BY_CARAC, hashParametro);

				getHashParametro().put("idCaraccatrelatorio", caracteristicaRelacionada.get(e).getIdCaraccatrelatorio());
				List<Tbantnaopatentaria> tbantnaopatentarias = (List<Tbantnaopatentaria>) (Object) service.list(FIND_NAO_PATENTARIA_BY_CARC, hashParametro);

				caracteristicaRelacionada.get(e).setTbantpatentariaList(tbantpatentarias);
				caracteristicaRelacionada.get(e).setTbantnaopatentariaList(tbantnaopatentarias);

				tbcaraccatrelatorioecs.add(caracteristicaRelacionada.get(e));

			}

			relatorioHTML.getTbcatrelatorioecList().get(i).setTbantpatentariacatrelecList(list1);
			relatorioHTML.getTbcatrelatorioecList().get(i).setTbantnaopatentariacatrelecList(list);
			relatorioHTML.getTbcatrelatorioecList().get(i).setTbcaraccatrelatorioecList(tbcaraccatrelatorioecs);

		}
		return relatorioHTML;
	}

	public String getIdiomaParam(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("idioma");
	}

	public void setRelatorioHTML(Tbrelatorioec relatorioHTML) {
		this.relatorioHTML = relatorioHTML;
	}

	public String getPedidoRelatorio() {
		return pedidoRelatorio;
	}

	public void setPedidoRelatorio(String pedidoRelatorio) {
		this.pedidoRelatorio = pedidoRelatorio;
	}

	public List<String> getColaboracoesCopiarRelatorio() {
		return colaboracoesCopiarRelatorio;
	}

	public void setColaboracoesCopiarRelatorio(List<String> colaboracoesCopiarRelatorio) {
		this.colaboracoesCopiarRelatorio = colaboracoesCopiarRelatorio;
	}

	@SuppressWarnings("unchecked")
	public List<Tblogrelatorio> getLogRelatorio() {

		getHashParametro().put("idRelatorio", relatorio.getIdRelatorioEc());
		logRelatorio = (List<Tblogrelatorio>) (Object) service.list(FIND_LOGRELATORIO_BY_ID_RELATORIO, hashParametro);

		for (int i = 0; i < logRelatorio.size(); i++) {
			logRelatorio.get(i).setUsuario(service.findUsuarioPorNome(logRelatorio.get(i).getStrUsuario()));
		}

		return logRelatorio;
	}

	public void setLogRelatorio(List<Tblogrelatorio> logRelatorio) {
		this.logRelatorio = logRelatorio;
	}

	/*
	 * Copiar Relatorio
	 */
	@SuppressWarnings("unchecked")
	public void copiarRelatorioRelacionarPatente() {
		if (colaboracoesCopiarRelatorio.size() == 0) {
			relatorioSemColaboracao();
		} else {

			getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
			Tbrelatorioec relatorioCopiar = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

			getHashParametro().put("idStatus", 1L);
			Tbcadstatusrelatorio idStatus = (Tbcadstatusrelatorio) service.findOneResult(FIND_CADSTATUSRELATORIOBYID, hashParametro);

			getHashParametro().put("idPatenteEc", Long.parseLong(patenteRelatorioCopiar));

			Tbpatenteec patente = (Tbpatenteec) service.findOneResult(FIND_BY_ID_PEDIDO, hashParametro);

			Tbrelatorioec relatorioNovo = new Tbrelatorioec();
			relatorioNovo.setBPublico(false);
			relatorioNovo.setIdStatus(idStatus);
			relatorioNovo.setDtStatus(new Date());
			relatorioNovo.setIdFamiliaEc(relatorioCopiar.getIdFamiliaEc());
			relatorioNovo.setIdUsuario(usuarioLogado);
			relatorioNovo.setStrRelatorio(patente.getNumeroPedido());
			relatorioNovo.setTxResumo(relatorioCopiar.getTxResumo());
			relatorioNovo.setTxConclusao(relatorioCopiar.getTxConclusao());
			service.save(relatorioNovo);

			// Salvar Colaborações
			getHashParametro().put("idRelatorio", relatorioNovo.getIdRelatorioEc());
			List<Tbrelatoriocolaboracao> relatorioColaboracao = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO, hashParametro);

			for (int i = 0; i < relatorioColaboracao.size(); i++) {
				service.deleteColaboracao(relatorioColaboracao.get(i));
			}

			for (int i = 0; i < colaboracoesCopiarRelatorio.size(); i++) {

				getHashParametro().put("idColaboracao", Long.parseLong(colaboracoesCopiarRelatorio.get(i)));
				Tbcadcolaboracao colaboracao = (Tbcadcolaboracao) service.findOneResult(FIND_BY_ID_COLABORACAO, hashParametro);

				Tbrelatoriocolaboracao tbrelatoriocolaboracao = new Tbrelatoriocolaboracao();
				TbRelatorioColaboracaoPK colaboracaoPK = new TbRelatorioColaboracaoPK();
				colaboracaoPK.setIdColaboracao(colaboracao.getIdColaboracao());
				colaboracaoPK.setIdRelatorio(relatorioNovo.getIdRelatorioEc());

				tbrelatoriocolaboracao.setColaboracaoPK(colaboracaoPK);

				service.save(tbrelatoriocolaboracao);

			}

			getHashParametro().put("idPatente", Long.parseLong(patenteRelatorioCopiar));
			List<Tbrelatoriopatente> relatorioPatente2 = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_PATENTE, hashParametro);

			for (int e = 0; e < relatorioPatente2.size(); e++) {
				getHashParametro().put("idRelatorioEc", relatorioPatente2.get(e).getTbRelatorioPatentePK().getIdRelatorio());
				Tbrelatorioec relatorio2 = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

				if (relatorio2.getIdStatus().getIdStatus() != 3L) {
					service.delete(relatorioPatente2.get(e));

				}
			}

			Tbrelatoriopatente tbrelatoriopatente = new Tbrelatoriopatente();
			TbRelatorioPatentePK patentePK = new TbRelatorioPatentePK();

			patentePK.setIdPatente(Long.parseLong(patenteRelatorioCopiar));
			patentePK.setIdRelatorio(relatorioNovo.getIdRelatorioEc());

			tbrelatoriopatente.setTbRelatorioPatentePK(patentePK);
			service.save(tbrelatoriopatente);

			// Configurar Anterioridade patentaria e salvar
			getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
			List<Tbantpatentariarelec> antPatentaria = (List<Tbantpatentariarelec>) (Object) service.list(FIND_ANTPATENTARIARELATORIO_PELO_RELATORIO, hashParametro);

			for (int i = 0; i < antPatentaria.size(); i++) {
				Tbantpatentariarelec tbantpatentariarelec = new Tbantpatentariarelec();

				tbantpatentariarelec.setIdCadtipoanterioridade(antPatentaria.get(i).getIdCadtipoanterioridade());
				tbantpatentariarelec.setStrAntpatentaria(antPatentaria.get(i).getStrAntpatentaria());
				tbantpatentariarelec.setTxReivindicacao(antPatentaria.get(i).getTxReivindicacao());
				tbantpatentariarelec.setTxRelacao(antPatentaria.get(i).getTxRelacao());
				tbantpatentariarelec.setIdRelatorioEc(relatorioNovo);

				service.save(tbantpatentariarelec);

			}

			// configurar Anteriodade Não patentaria e salvar
			getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
			List<Tbantnaopatentariarelec> antnaoPatentaria = (List<Tbantnaopatentariarelec>) (Object) service.list(FIND_ANTNAOPATENTARIARELATORIO_PELO_RELATORIO, hashParametro);

			for (int i = 0; i < antnaoPatentaria.size(); i++) {
				Tbantnaopatentariarelec tbantnaopatentariarelec = new Tbantnaopatentariarelec();

				tbantnaopatentariarelec.setIdCadtipoanterioridade(antnaoPatentaria.get(i).getIdCadtipoanterioridade());
				tbantnaopatentariarelec.setTxTitulo(antnaoPatentaria.get(i).getTxTitulo());
				tbantnaopatentariarelec.setTxReivindicacao(antnaoPatentaria.get(i).getTxReivindicacao());
				tbantnaopatentariarelec.setTxRelacao(antnaoPatentaria.get(i).getTxRelacao());
				tbantnaopatentariarelec.setIdRelatorioEc(relatorioNovo);

				service.save(tbantnaopatentariarelec);

			}

			// categoria e salvar
			getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
			List<Tbcatrelatorioec> categoriaRelatorio = (List<Tbcatrelatorioec>) (Object) service.list(FIND_CATEGORIARELATORIO_BY_RELATORIO, hashParametro);

			for (int i = 0; i < categoriaRelatorio.size(); i++) {
				Tbcatrelatorioec tbcatrelatorioec = new Tbcatrelatorioec();

				tbcatrelatorioec.setTxResumo(categoriaRelatorio.get(i).getTxResumo());
				tbcatrelatorioec.setIdRelatorioEc(relatorioNovo);
				tbcatrelatorioec.setIdCategoria(categoriaRelatorio.get(i).getIdCategoria());
				service.save(tbcatrelatorioec);

				getHashParametro().put("idCatrelatorioec", categoriaRelatorio.get(i).getIdCatrelatorioec());
				List<Tbantnaopatentariacatrelec> tbantNaopatentariacatrelec = (List<Tbantnaopatentariacatrelec>) (Object) service.list(FIND_ANTNAOPATENTARIACATRELEC_BY_CATRELATORIO, hashParametro);

				for (int e = 0; e < tbantNaopatentariacatrelec.size(); e++) {
					Tbantnaopatentariacatrelec antnaoPatentariaCat = new Tbantnaopatentariacatrelec();

					antnaoPatentariaCat.setIdCadtipoanterioridade(tbantNaopatentariacatrelec.get(e).getIdCadtipoanterioridade());
					antnaoPatentariaCat.setTxTitulo(tbantNaopatentariacatrelec.get(e).getTxTitulo());
					antnaoPatentariaCat.setTxReivindicacao(tbantNaopatentariacatrelec.get(e).getTxReivindicacao());
					antnaoPatentariaCat.setTxRelacao(tbantNaopatentariacatrelec.get(e).getTxRelacao());
					antnaoPatentariaCat.setIdCatrelatorioec(tbcatrelatorioec);

					service.save(antnaoPatentariaCat);

				}

				getHashParametro().put("idCatrelatorioec", categoriaRelatorio.get(i).getIdCatrelatorioec());
				List<Tbantpatentariacatrelec> tbantpatentariacatrelec = (List<Tbantpatentariacatrelec>) (Object) service.list(FIND_ANTPATENTARIACATRELEC_BY_CATRELATORIO, hashParametro);

				for (int e = 0; e < tbantpatentariacatrelec.size(); e++) {
					Tbantpatentariacatrelec antPatentariaCat = new Tbantpatentariacatrelec();

					antPatentariaCat.setIdCadtipoanterioridade(tbantpatentariacatrelec.get(e).getIdCadtipoanterioridade());
					antPatentariaCat.setStrAntpatentaria(tbantpatentariacatrelec.get(e).getStrAntpatentaria());
					antPatentariaCat.setTxReivindicacao(tbantpatentariacatrelec.get(e).getTxReivindicacao());
					antPatentariaCat.setTxRelacao(tbantpatentariacatrelec.get(e).getTxRelacao());
					antPatentariaCat.setIdCatrelatorioec(tbcatrelatorioec);
					service.save(antPatentariaCat);
				}

				getHashParametro().put("idCatrelatorioec", categoriaRelatorio.get(i).getIdCatrelatorioec());
				List<Tbcaraccatrelatorioec> caracteristicaRelacionada = (List<Tbcaraccatrelatorioec>) (Object) service.list(FIND_CARACCATRELATORIO_BY_CATRELATORIO, hashParametro);

				for (int e = 0; e < caracteristicaRelacionada.size(); e++) {

					Tbcaraccatrelatorioec tbcaraccatrelatorioec = new Tbcaraccatrelatorioec();
					tbcaraccatrelatorioec.setTxCaracteristica(caracteristicaRelacionada.get(e).getTxCaracteristica());
					tbcaraccatrelatorioec.setIdCatrelatorioec(tbcatrelatorioec);
					service.save(tbcaraccatrelatorioec);

					
					getHashParametro().put("idCaraccatrelatorio", caracteristicaRelacionada.get(e).getIdCaraccatrelatorio());
					List<Tbantpatentaria> tbantpatentarias = (List<Tbantpatentaria>) (Object) service.list(FIND_PATENTARIA_BY_CARAC, hashParametro);

					for (int p = 0; p < tbantpatentarias.size(); p++) {
						Tbantpatentaria tbantpatentaria = new Tbantpatentaria();

						tbantpatentaria.setIdCadtipoanterioridade(tbantpatentarias.get(p).getIdCadtipoanterioridade());
						tbantpatentaria.setStrAntpatentaria(tbantpatentarias.get(p).getStrAntpatentaria());
						tbantpatentaria.setTxReivindicacao(tbantpatentarias.get(p).getTxReivindicacao());
						tbantpatentaria.setTxRelacao(tbantpatentarias.get(p).getTxRelacao());
						tbantpatentaria.setIdCaraccatrelatorio(tbcaraccatrelatorioec);
						service.save(tbantpatentaria);

					}

					getHashParametro().put("idCaraccatrelatorio", caracteristicaRelacionada.get(e).getIdCaraccatrelatorio());
					List<Tbantnaopatentaria> tbantnaopatentarias = (List<Tbantnaopatentaria>) (Object) service.list(FIND_NAO_PATENTARIA_BY_CARC, hashParametro);

					for (int p = 0; p < tbantnaopatentarias.size(); p++) {
						Tbantnaopatentaria tbantnaopatentaria = new Tbantnaopatentaria();

						tbantnaopatentaria.setIdCadtipoanterioridade(tbantnaopatentarias.get(p).getIdCadtipoanterioridade());
						tbantnaopatentaria.setTxTitulo(tbantnaopatentarias.get(p).getTxTitulo());
						tbantnaopatentaria.setTxReivindicacao(tbantnaopatentarias.get(p).getTxReivindicacao());
						tbantnaopatentaria.setTxRelacao(tbantnaopatentarias.get(p).getTxRelacao());
						tbantnaopatentaria.setIdCaraccatrelatorio(tbcaraccatrelatorioec);
						service.save(tbantnaopatentaria);
					}
				}
			}

			salvarLogRelatorio(relatorioNovo.getIdRelatorioEc(), "Copiar", "Relatório", relatorioCopiar.getStrRelatorio().toString()+relatorioCopiar.getIdRelatorioEc().toString(), usuarioLogado.getStrUsuario());
			salvarLogRelatorio(relatorioCopiar.getIdRelatorioEc(), "Copiar", "Pedido", patente.getNumeroPedido(), usuarioLogado.getStrUsuario());
			salvarLogFamilia(relatorioNovo.getIdFamiliaEc().getIdFamiliaEc(), "Copiar", "Relatório", relatorioNovo.getStrRelatorio().toString()+relatorioNovo.getIdRelatorioEc().toString(), usuarioLogado.getStrUsuario());
			
			
			numeroRelatorio = relatorioNovo.getIdRelatorioEc().toString();

			msgCopiarRelatorio = true;

			// trocar status da familia

			if (tbfamiliaec.getPublico()) {
				tbfamiliaec.setPublico(false);
				service.update(tbfamiliaec);

				salvarLogFamilia(tbfamiliaec.getIdFamiliaEc(), "Privado", "Família", "-", getUsuarioLogado().getStrUsuario());
			}
			
			this.resumo = null;
			this.conclusao = null;
			clausulaTipoZeradas();
			System.out.println(relatorio.getIdRelatorioEc());
			EPECUtil.redirecionar("/epecWeb/pages/visualizarRelatorio.jsf");

		}

	}

	public void copiarRelatorioFuncao() {

		boolean vinculo = false;
		FacesContext fc = FacesContext.getCurrentInstance();
		this.pedidoRelatorio = getPedidoRelatorioParam(fc);

		obterUsuarioSessao();

		this.numeroRelatorio = relatorioCopiar;

		getHashParametro().put("idRelatorioEc", Long.parseLong(relatorioCopiar));
		Tbrelatorioec relatorioCopiar = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);

		if (relatorioAcesso(usuarioLogado, relatorioCopiar, null, false)) {
			this.copiarRelatorioPatente = getCopiarRelatorioPatente();

			getHashParametro().put("idRelatorio", relatorioCopiar.getIdRelatorioEc());
			@SuppressWarnings("unchecked")
			List<Tbrelatoriopatente> relatorioPatente = (List<Tbrelatoriopatente>) (Object) service.list(FIND_RELATORIOPATENTE_RELATORIO, hashParametro);

			int vinculoteste = 0;
			boolean copiar;
			copiarRelatorioPatenteTeste = new ArrayList<>();

			for (int i = 0; i < copiarRelatorioPatente.size(); i++) {
				copiar = true;
				for (int e = 0; e < relatorioPatente.size(); e++) {
					if (copiarRelatorioPatente.get(i).getIdPatenteEc().equals(relatorioPatente.get(e).getTbRelatorioPatentePK().getIdPatente())) {
						vinculoteste++;
						copiar = false;

					}
				}
				if (copiar) {
					copiarRelatorioPatenteTeste.add(copiarRelatorioPatente.get(i));
				}

			}

			if (vinculoteste == copiarRelatorioPatente.size()) {
				vinculo = true;
			}

			if (vinculo) {
				relatorioJaVinculado();
			} else if (copiarRelatorioPatenteTeste.size() > 0) {
				copiarRelatorio = "1";
				this.colaboracoesCopiarRelatorio = null;
				EPECUtil.redirecionar("/epecWeb/pages/copiarRelatorio.jsf");
			} else {
				oUsuarioLogadoNaoPodeRealizarTalOperacao();
			}

		} else {
			
			relatorioNaoPodeVincularOuCopiar();
		}

	}

	private List<Tbpatenteec> copiarRelatorioPatente;

	private List<Tbpatenteec> copiarRelatorioPatenteTeste;

	public List<Tbpatenteec> getCopiarRelatorioPatenteTeste() {
		return copiarRelatorioPatenteTeste;
	}

	public void setCopiarRelatorioPatenteTeste(List<Tbpatenteec> copiarRelatorioPatenteTeste) {
		this.copiarRelatorioPatenteTeste = copiarRelatorioPatenteTeste;
	}

	private String patenteRelatorioCopiar;

	public String getPatenteRelatorioCopiar() {
		return patenteRelatorioCopiar;
	}

	public void setPatenteRelatorioCopiar(String patenteRelatorioCopiar) {
		this.patenteRelatorioCopiar = patenteRelatorioCopiar;
	}

	public List<Tbpatenteec> getCopiarRelatorioPatente() {
		obterUsuarioSessao();
		copiarRelatorioPatente = new ArrayList<>();

		patentes = getPatentes();
		for (int i = 0; i < patentes.size(); i++) {
			if (patentes.get(i).getIdEntidadeEc().equals(usuarioLogado.getIdEntidadeEc())) {
				if (patentes.get(i).getTbrelatorioec() != null) {
					if(patentes.get(i).getTbrelatorioec().getIdUsuario().getIdEntidadeEc().equals(patentes.get(i).getIdEntidadeEc())){
					
					if (patentes.get(i).getTbrelatorioec().getIdUsuario().equals(usuarioLogado)) {
						copiarRelatorioPatente.add(patentes.get(i));
					} else if (!(patentes.get(i).getTbrelatorioec().isNaoFinalizado())) {
						copiarRelatorioPatente.add(patentes.get(i));
					}
					
					}else{
						copiarRelatorioPatente.add(patentes.get(i));

					}
				} else {
					copiarRelatorioPatente.add(patentes.get(i));
				}

			}
		}

		return copiarRelatorioPatente;

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

	private boolean msgCopiarRelatorio;

	public boolean isMsgCopiarRelatorio() {
		return msgCopiarRelatorio;
	}

	public void setMsgCopiarRelatorio(boolean msgCopiarRelatorio) {
		this.msgCopiarRelatorio = msgCopiarRelatorio;
	}

	public String getTextoOutras() {
		return textoOutras;
	}

	public void setTextoOutras(String textoOutras) {
		this.textoOutras = textoOutras;
	}

	public String getLink() {
		 link = relatorio.getTxLink();
		
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public void openLink(){
	
		
	}
	
	


}
