package br.gov.inpi.epec.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.gov.inpi.epec.beans.Tbantnaopatentaria;
import br.gov.inpi.epec.beans.Tbantnaopatentariacatrelec;
import br.gov.inpi.epec.beans.Tbantpatentaria;
import br.gov.inpi.epec.beans.Tbantpatentariacatrelec;
import br.gov.inpi.epec.beans.Tbcadativo;
import br.gov.inpi.epec.beans.Tbcadcategoria;
import br.gov.inpi.epec.beans.Tbcadtipoanterioridade;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbcaraccatrelatorioec;
import br.gov.inpi.epec.beans.Tbcatrelatorioec;
import br.gov.inpi.epec.beans.Tbclausulatipo;
import br.gov.inpi.epec.beans.Tblogrelatorio;
import br.gov.inpi.epec.beans.Tbnos;
import br.gov.inpi.epec.beans.Tbrelatorioec;
import br.gov.inpi.epec.facade.EpecServiceFacade;
import br.gov.inpi.epec.util.Document;
import br.gov.inpi.epec.util.EPECUtil;

@SessionScoped
@ManagedBean
public class TbCadCategoriaMB extends AbstractMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3953931804960603846L;

	@EJB
	EpecServiceFacade service;

	private Tbrelatorioec relatorio;

	private String numeroRelatorio;

	private String categorias;

	private String resumo;

	private String catRelatorio;

	private Tbcadcategoria categoriaInserir;

	private List<Tbantpatentariacatrelec> antPatentariacat;

	private List<Tbantnaopatentariacatrelec> antnaoPatentariacat;

	private List<Tbcaraccatrelatorioec> caracteristicaRelacionada;

	private List<Tbcaraccatrelatorioec> caracteristcaNaoRelacionada;

	private Tbantpatentariacatrelec antpatentariacatrelec = new Tbantpatentariacatrelec();

	private Tbantnaopatentariacatrelec antnaopatentariacatrelec = new Tbantnaopatentariacatrelec();

	private Tbantpatentariacatrelec antpatentariacatrelecselect;

	private Tbantpatentariacatrelec antpatentariacatrelecExcluir;

	private Tbantnaopatentariacatrelec antnaopatentariacatrelecSelect;

	private Tbantnaopatentariacatrelec antnaopatentariacatrelecaExcluir;

	private Tbcatrelatorioec tbcatrelatorioec;

	private Tbcatrelatorioec tbcatrelatorioecSelect;

	private String resumoCat;

	private TreeNode selectedNode1;

	private TreeNode selectedNode2;

	private TreeNode selectedNode3;

	private TreeNode selectedNode4;

	private TreeNode selectedNode5;

	@ManagedProperty("#{documentService}")
	private DocumentService clausulaList;

	private TreeNode root1;

	private TreeNode root2;

	private TreeNode root3;

	private List<Tbclausulatipo> clausulaTipoList;

	private String relacao;

	private String reivindicacao;

	private String relacaoNP;

	private String reivindicacaoNP;

	private HttpSession session;

	public Tbcadusuario usuarioLogado;

	private String salvarAnterioridadePatentariaCat;

	private String salvarAnterioridadeNaoPatentariaCat;

	private String alterarAnterioridadePatentariaCat;

	private String alterarAnterioridadeNaoPatentariaCat;

	private String excluirAnterioridadePatentariaCat;

	private String excluirAnterioridadeNaoPatentariaCat;

	private String excluirCaracteristica;

	private String excluirAnterioridadePatentariaCarac;
	
	private String excluirAnterioridadeNaoPatentariaCarac;

	Locale localeES = new Locale("es", "");
	Locale localeEN = new Locale("en", "");

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

	@PostConstruct
	public void init() {
		this.resumoCat = "";
		loadClausulaTipoResumo();
		usuarioLogado = getUsuarioLogado();
	}

	public void mensagemInserirAntPatent() {
		if (salvarAnterioridadePatentariaCat != null){
			if (salvarAnterioridadePatentariaCat.equals("1")) {
				incluidoComSucesso();

			}
		}
		if (salvarAnterioridadeNaoPatentariaCat != null){
			if (salvarAnterioridadeNaoPatentariaCat.equals("1")) {
				incluidoComSucesso();

			}
		}
		
		if (alterarAnterioridadePatentariaCat != null){
			if (alterarAnterioridadePatentariaCat.equals("1")) {
				alteradoComSucesso();

			}
		}
		
		if (alterarAnterioridadeNaoPatentariaCat != null){
			if (alterarAnterioridadeNaoPatentariaCat.equals("1")) {
				alteradoComSucesso();

			}
		}
		if (excluirAnterioridadePatentariaCat != null){
			if (excluirAnterioridadePatentariaCat.equals("1")) {
				excluidoComSucesso();

			}
		}
		if (excluirAnterioridadeNaoPatentariaCat != null){
			if (excluirAnterioridadeNaoPatentariaCat.equals("1")) {
				excluidoComSucesso();

			}
		}
		

		this.excluirAnterioridadeNaoPatentariaCat = null;
		this.excluirAnterioridadePatentariaCat = null;		
		this.alterarAnterioridadeNaoPatentariaCat = null;		
		this.alterarAnterioridadePatentariaCat = null;
		this.salvarAnterioridadeNaoPatentariaCat = null;		
		this.salvarAnterioridadePatentariaCat = null;
	
	}




	public void menuVoltarCategoria() {
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/inserirCategoria.jsf");
	}

	public void botaoInserirCategoria(){
		resumoCat = null;		
	}	
	
	public void salvarCategoria() {

		if (tbcatrelatorioec.getTxResumo() == null) {
			if (resumoCat != null) {
				salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Alterar", "Resumo da Categoria", tbcatrelatorioec.getIdCategoria().getStrPortugues(), usuarioLogado.getStrUsuario());
			}

		} else if (!(tbcatrelatorioec.getTxResumo().equals(resumoCat))) {
			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Alterar", "Resumo da Categoria", tbcatrelatorioec.getIdCategoria().getStrPortugues(), usuarioLogado.getStrUsuario());
		}

		tbcatrelatorioec.setTxResumo(resumoCat);
		service.update(tbcatrelatorioec);
		
	
		
		salvoComSucesso();
		

	}

	public void inserirCaracteristica() {
		this.relacao = null;
		this.reivindicacao = null;
		this.relacaoNP = null;
		this.reivindicacaoNP = null;
		this.inserirCategoria = null;
		this.inserirCaracteristica = null;
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/inserirCaracteristica.jsf");
	}

	public Tbrelatorioec getRelatorio() {
		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		relatorio = (Tbrelatorioec) service.findOneResult(FIND_ID_RELATORIO, hashParametro);
		if (!categorias.isEmpty()) {
			Tbcadcategoria tbcadcategoria = (Tbcadcategoria) service.bucarPorId(Integer.parseInt(categorias));
			relatorio.setTbcadcategoria(tbcadcategoria);
		}

		return relatorio;
	}

	public void inserirAnterioridadePatentaria() {

		antpatentariacatrelec = new Tbantpatentariacatrelec();
		antpatentariacatrelecselect = null;
		this.relacao = null;
		this.reivindicacao = null;
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/inserirAnterioridadePatentariaCategoria.jsf");
	}

	public void inserirAnterioridadeNaoPatentaria() {
		antnaopatentariacatrelec = new Tbantnaopatentariacatrelec();
		antnaopatentariacatrelecSelect = null;
		this.relacaoNP = null;
		this.reivindicacaoNP = null;
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/inserirAnterioridadeNaoPatentariaCategoria.jsf");
	}

	public void alterarAnterioridadePatentaria() {
		this.relacao = null;
		this.reivindicacao = null;
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/alterarAntPatentariaCat.jsf");
	}

	public void alterarAnterioridadeNaoPatentaria() {
		this.relacaoNP = null;
		this.reivindicacaoNP = null;
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/alterarAntNaoPatentariaCat.jsf");
	}

	public void updateAntPatentaria() {

		if (antpatentariacatrelecselect.getStrAntpatentaria() == null) {
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else if (antpatentariacatrelecselect.getStrAntpatentaria().isEmpty()) {
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else {
			if (antpatentariacatrelecselect.getCadTipoAnteriodadePatentaria() != null) {
				Tbcadtipoanterioridade tbcadtipoanterioridade = service.buscaPorIdTipoAnterioridade(Integer.parseInt(antpatentariacatrelecselect.getCadTipoAnteriodadePatentaria()));
				antpatentariacatrelecselect.setIdCadtipoanterioridade(tbcadtipoanterioridade);

			}

			antpatentariacatrelecselect.setTxReivindicacao(reivindicacao);
			antpatentariacatrelecselect.setTxRelacao(relacao);
			service.update(antpatentariacatrelecselect);

			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Alterar", "Anterioridade Patentária da Categoria", antpatentariacatrelecselect.getStrAntpatentaria(), usuarioLogado.getStrUsuario());

			this.alterarAnterioridadePatentariaCat = "1";

			clausulaTipoZeradas();
			EPECUtil.redirecionar("/epecWeb/pages/inserirCategoria.jsf");
		}
	}

	public void updateAntNaoPatentaria() {

		if (antnaopatentariacatrelecSelect.getTxTitulo() == null) {
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else if (antnaopatentariacatrelecSelect.getTxTitulo().isEmpty()) {
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else {

			if (antnaopatentariacatrelec.getCadTipoAnteriodadePatentaria() != null) {
				Tbcadtipoanterioridade tbcadtipoanterioridade = service.buscaPorIdTipoAnterioridade(Integer.parseInt(antnaopatentariacatrelec.getCadTipoAnteriodadePatentaria()));
				antnaopatentariacatrelecSelect.setIdCadtipoanterioridade(tbcadtipoanterioridade);
			}
			antnaopatentariacatrelecSelect.setTxReivindicacao(reivindicacaoNP);
			antnaopatentariacatrelecSelect.setTxRelacao(relacaoNP);

			service.update(antnaopatentariacatrelecSelect);

			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Alterar", "Anterioridade Não Patentária da Categoria", antnaopatentariacatrelecSelect.getTxTitulo(), usuarioLogado.getStrUsuario());

			this.alterarAnterioridadeNaoPatentariaCat = "1";
			clausulaTipoZeradas();
			EPECUtil.redirecionar("/epecWeb/pages/inserirCategoria.jsf");
		}
	}

	public void removerAntPatentaria() {
		if (antpatentariacatrelecExcluir != null) {
			service.deleteAntPatentariaCat(antpatentariacatrelecExcluir);
			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Excluir", "Anterioridade Patentária da Categoria", antpatentariacatrelecExcluir.getStrAntpatentaria(), usuarioLogado.getStrUsuario());

		}
		this.excluirAnterioridadePatentariaCat = "1";
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/inserirCategoria.jsf");

	}

	public void removerAntNaoPatentaria() {
		if (antnaopatentariacatrelecaExcluir != null) {
			service.deleteAntNaoPatentariaCat(antnaopatentariacatrelecaExcluir);
			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Excluir", "Anterioridade Não Patentária da Categoria", antnaopatentariacatrelecaExcluir.getTxTitulo(), usuarioLogado.getStrUsuario());

		}

		this.excluirAnterioridadeNaoPatentariaCat = "1";
		clausulaTipoZeradas();

		EPECUtil.redirecionar("/epecWeb/pages/inserirCategoria.jsf");
	}

	public void visualizarAnterioridadePatentaria() {
		EPECUtil.redirecionar("/epecWeb/pages/visualizarAntPatentariaCat.jsf");
	}

	public void visualizarAnterioridadeNaoPatentaria() {
		EPECUtil.redirecionar("/epecWeb/pages/visualizarAntNaoPatentariaCat.jsf");

	}

	public void salvarAntPatentaria() {
		if (antpatentariacatrelec.getStrAntpatentaria() == null) {
			antpatentariacatrelec = new Tbantpatentariacatrelec();
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else if (antpatentariacatrelec.getStrAntpatentaria().isEmpty()) {
			antpatentariacatrelec = new Tbantpatentariacatrelec();
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else {
			if (tbcatrelatorioec == null) {
				getTbcatrelatorioec();
			}

			Tbcadtipoanterioridade tbcadtipoanterioridade = service.buscaPorIdTipoAnterioridade(Integer.parseInt(antpatentariacatrelec.getCadTipoAnteriodadePatentaria()));

			antpatentariacatrelec.setIdCadtipoanterioridade(tbcadtipoanterioridade);
			antpatentariacatrelec.setIdCatrelatorioec(tbcatrelatorioec);
			antpatentariacatrelec.setTxReivindicacao(reivindicacao);
			antpatentariacatrelec.setTxRelacao(relacao);

			service.save(antpatentariacatrelec);

			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Inserir", "Anterioridade Patentária da Categoria", antpatentariacatrelec.getStrAntpatentaria(), usuarioLogado.getStrUsuario());

			antpatentariacatrelec = new Tbantpatentariacatrelec();

			this.salvarAnterioridadePatentariaCat = "1";
			clausulaTipoZeradas();

			EPECUtil.redirecionar("/epecWeb/pages/inserirCategoria.jsf");
		}

	}

	public void salvarAntNaoPatentaria() {
		if (antnaopatentariacatrelec.getTxTitulo() == null) {
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();
		}
		if (antnaopatentariacatrelec.getTxTitulo().isEmpty()) {
			antnaopatentariacatrelec = new Tbantnaopatentariacatrelec();
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else {
			if (tbcatrelatorioec == null) {
				getTbcatrelatorioec();
			}

			Tbcadtipoanterioridade tbcadtipoanterioridade = service.buscaPorIdTipoAnterioridade(Integer.parseInt(antnaopatentariacatrelec.getCadTipoAnteriodadePatentaria()));

			antnaopatentariacatrelec.setIdCadtipoanterioridade(tbcadtipoanterioridade);
			antnaopatentariacatrelec.setIdCatrelatorioec(tbcatrelatorioec);
			antnaopatentariacatrelec.setTxReivindicacao(reivindicacaoNP);
			antnaopatentariacatrelec.setTxRelacao(relacaoNP);

			service.save(antnaopatentariacatrelec);

			salvarLogRelatorio(relatorio.getIdRelatorioEc(), "Inserir", "Anterioridade Não Patentária da Categoria", antnaopatentariacatrelec.getTxTitulo(), usuarioLogado.getStrUsuario());

			antnaopatentariacatrelec = new Tbantnaopatentariacatrelec();
			this.salvarAnterioridadeNaoPatentariaCat = "1";
			clausulaTipoZeradas();

			EPECUtil.redirecionar("/epecWeb/pages/inserirCategoria.jsf");
		}

	}

	public void loadClausulaTipoResumo() {

		root1 = buscaArvore();

		root2 = buscaArvore();

		root3 = buscaArvore();

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
			for (int i = 0; i < noRaiz.getTbnosList().size(); i++) {
				ValidarArvore(noRaiz.getTbnosList().get(i), node0, null);
			}

		}

		return root;

	}

	private void ValidarArvore(Tbnos no, TreeNode pai, TreeNode filho) {

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {

			filho = new DefaultTreeNode(new Document(no.getTxTituloEspanhol(), "-", "Folder", null), pai);

			if (no.getIdClausulaTipo() != null) {

				// TreeNode clausula = new DefaultTreeNode(new
				// Document(no.getIdClausulaTipo().getTxEspanhol(), "-",
				// "Pastas", null), filho);

				filho.getChildren().add(new DefaultTreeNode(no.getIdClausulaTipo().getTxEspanhol()));
			}
			for (int i = 0; i < no.getTbnosList().size(); i++) {

				ValidarArvore(no.getTbnosList().get(i), filho, null);
			}

		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {

			filho = new DefaultTreeNode(new Document(no.getTxTituloIngles(), "-", "Folder", null), pai);

			if (no.getIdClausulaTipo() != null) {

				filho.getChildren().add(new DefaultTreeNode(no.getIdClausulaTipo().getTxIngles()));

				// /TreeNode clausula = new DefaultTreeNode(new
				// Document(no.getIdClausulaTipo().getTxIngles(), "-", "Pastas",
				// null), filho);
			}
			for (int i = 0; i < no.getTbnosList().size(); i++) {

				ValidarArvore(no.getTbnosList().get(i), filho, null);
			}

		} else {
			filho = new DefaultTreeNode(new Document(no.getTxTituloPortugues(), "-", "Folder", null), pai);

			if (no.getIdClausulaTipo() != null) {
				filho.getChildren().add(new DefaultTreeNode(no.getIdClausulaTipo().getTxPortugues()));

				// TreeNode clausula = new DefaultTreeNode(new
				// Document(no.getIdClausulaTipo().getTxPortugues(), "-",
				// "Pastas", null), filho);
			}
			for (int i = 0; i < no.getTbnosList().size(); i++) {

				ValidarArvore(no.getTbnosList().get(i), filho, null);
			}
		}

	}

	public void displaySelectedSingle() {

		if (selectedNode1 != null) {

			String valor = selectedNode1.getData().toString();
			this.resumoCat = resumoCat + valor;
			this.setResumoCat(resumoCat);
			
			
		}

		if (selectedNode2 != null) {

			String valor = selectedNode2.getData().toString();
			if (relacao == null)
				relacao = "";
			this.setRelacao(this.relacao + valor);

		}

		if (selectedNode3 != null) {

			String valor = selectedNode3.getData().toString();

			if (reivindicacao == null)
				reivindicacao = "";
			this.setReivindicacao(this.reivindicacao + valor);

		}

		if (selectedNode4 != null) {

			String valor = selectedNode4.getData().toString();
			if (relacaoNP == null)
				relacaoNP = "";
			this.setRelacaoNP(this.relacaoNP + valor);

		}

		if (selectedNode5 != null) {

			String valor = selectedNode5.getData().toString();
			if (reivindicacaoNP == null)
				reivindicacaoNP = "";
			this.setReivindicacaoNP(this.reivindicacaoNP + valor);

		}

		clausulaTipoZeradas();
	}
	
	private void clausulaTipoZeradas() {
		this.selectedNode1 = null;
		this.selectedNode2 = null;
		this.selectedNode3 = null;
		this.selectedNode4 = null;
		this.selectedNode5 = null;
	}

	public void setRelatorio(Tbrelatorioec relatorio) {
		this.relatorio = relatorio;
	}

	public String getNumeroRelatorio() {
		return numeroRelatorio;
	}

	public void setNumeroRelatorio(String numeroRelatorio) {
		this.numeroRelatorio = numeroRelatorio;
	}

	public String getCategorias() {
		return categorias;
	}

	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}

	public Tbantpatentariacatrelec getAntpatentariacatrelec() {
		return antpatentariacatrelec;
	}

	public void setAntpatentariacatrelec(Tbantpatentariacatrelec antpatentariacatrelec) {
		this.antpatentariacatrelec = antpatentariacatrelec;
	}

	public Tbantnaopatentariacatrelec getAntnaopatentariacatrelec() {
		return antnaopatentariacatrelec;
	}

	public void setAntnaopatentariacatrelec(Tbantnaopatentariacatrelec antnaopatentariacatrelec) {
		this.antnaopatentariacatrelec = antnaopatentariacatrelec;
	}

	public Tbantpatentariacatrelec getAntpatentariacatrelecselect() {
		return antpatentariacatrelecselect;
	}

	public void setAntpatentariacatrelecselect(Tbantpatentariacatrelec antpatentariacatrelecselect) {
		this.antpatentariacatrelecselect = antpatentariacatrelecselect;
	}

	public Tbantpatentariacatrelec getAntpatentariacatrelecExcluir() {
		return antpatentariacatrelecExcluir;
	}

	public void setAntpatentariacatrelecExcluir(Tbantpatentariacatrelec antpatentariacatrelecExcluir) {
		this.antpatentariacatrelecExcluir = antpatentariacatrelecExcluir;
	}

	public Tbantnaopatentariacatrelec getAntnaopatentariacatrelecSelect() {
		return antnaopatentariacatrelecSelect;
	}

	public void setAntnaopatentariacatrelecSelect(Tbantnaopatentariacatrelec antnaopatentariacatrelecSelect) {
		this.antnaopatentariacatrelecSelect = antnaopatentariacatrelecSelect;
	}

	public Tbantnaopatentariacatrelec getAntnaopatentariacatrelecaExcluir() {
		return antnaopatentariacatrelecaExcluir;
	}

	public void setAntnaopatentariacatrelecaExcluir(Tbantnaopatentariacatrelec antnaopatentariacatrelecaExcluir) {
		this.antnaopatentariacatrelecaExcluir = antnaopatentariacatrelecaExcluir;
	}

	@SuppressWarnings("unchecked")
	public List<Tbantpatentariacatrelec> getAntPatentariacat() {

		List<Tbantpatentariacatrelec> list = new ArrayList<>();
		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		List<Tbcatrelatorioec> tbcatrelatorioecs = (List<Tbcatrelatorioec>) (Object) service.list(FIND_CATRELATORIO_BY_RELATORIO, hashParametro);

		Tbcadcategoria tbcadcategoria = (Tbcadcategoria) service.bucarPorId(Integer.parseInt(categorias));

		for (int i = 0; i < tbcatrelatorioecs.size(); i++) {

			if (tbcatrelatorioecs.get(i).getIdCategoria().getIdCategoria() == tbcadcategoria.getIdCategoria()) {
				getHashParametro().put("idCatrelatorioec", tbcatrelatorioecs.get(i).getIdCatrelatorioec());
				List<Tbantpatentariacatrelec> tbantpatentariacatrelec = (List<Tbantpatentariacatrelec>) (Object) service.list(FIND_ANTPATENTARIACATRELEC_BY_CATRELATORIO, hashParametro);

				if (tbantpatentariacatrelec != null) {
					list.addAll(tbantpatentariacatrelec);
				}

			}
		}

		return list;
	}

	public void setAntPatentariacat(List<Tbantpatentariacatrelec> antPatentariacat) {
		this.antPatentariacat = antPatentariacat;
	}

	@SuppressWarnings("unchecked")
	public List<Tbantnaopatentariacatrelec> getAntnaoPatentariacat() {

		List<Tbantnaopatentariacatrelec> list = new ArrayList<>();
		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		List<Tbcatrelatorioec> tbcatrelatorioecs = (List<Tbcatrelatorioec>) (Object) service.list(FIND_CATRELATORIO_BY_RELATORIO, hashParametro);

		Tbcadcategoria tbcadcategoria = (Tbcadcategoria) service.bucarPorId(Integer.parseInt(categorias));

		for (int i = 0; i < tbcatrelatorioecs.size(); i++) {

			if (tbcatrelatorioecs.get(i).getIdCategoria().getIdCategoria() == tbcadcategoria.getIdCategoria()) {
				getHashParametro().put("idCatrelatorioec", tbcatrelatorioecs.get(i).getIdCatrelatorioec());
				List<Tbantnaopatentariacatrelec> tbantpatentariacatrelec = (List<Tbantnaopatentariacatrelec>) (Object) service.list(FIND_ANTNAOPATENTARIACATRELEC_BY_CATRELATORIO, hashParametro);

				if (tbantpatentariacatrelec != null) {
					list.addAll(tbantpatentariacatrelec);
				}

			}
		}

		return list;

	}

	public void setAntnaoPatentariacat(List<Tbantnaopatentariacatrelec> antnaoPatentariacat) {
		this.antnaoPatentariacat = antnaoPatentariacat;
	}

	public Tbcadcategoria getCategoriaInserir() {

		categoriaInserir = (Tbcadcategoria) service.bucarPorId(Integer.parseInt(categorias));
		return categoriaInserir;
	}

	public void setCategoriaInserir(Tbcadcategoria categoriaInserir) {
		this.categoriaInserir = categoriaInserir;
	}

	@SuppressWarnings("unchecked")
	public Tbcatrelatorioec getTbcatrelatorioec() {

		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		List<Tbcatrelatorioec> tbcatrelatorioecs = (List<Tbcatrelatorioec>) (Object) service.list(FIND_CATRELATORIO_BY_RELATORIO, hashParametro);

		Tbcadcategoria tbcadcategoria = (Tbcadcategoria) service.bucarPorId(Integer.parseInt(categorias));

		for (int i = 0; i < tbcatrelatorioecs.size(); i++) {
			if (tbcatrelatorioecs.get(i).getIdCategoria().getIdCategoria() == tbcadcategoria.getIdCategoria()) {
				tbcatrelatorioec = tbcatrelatorioecs.get(i);
			}
		}

		return tbcatrelatorioec;
	}

	public void setTbcatrelatorioec(Tbcatrelatorioec tbcatrelatorioec) {
		this.tbcatrelatorioec = tbcatrelatorioec;
	}

	public String getResumo() {
		if (resumo.isEmpty()) {
			tbcatrelatorioec = getTbcatrelatorioec();
			resumo = tbcatrelatorioec.getTxResumo();
		}
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getCatRelatorio() {
		return catRelatorio;
	}

	public void setCatRelatorio(String catRelatorio) {
		this.catRelatorio = catRelatorio;
	}

	@SuppressWarnings("unchecked")
	public Tbcatrelatorioec getTbcatrelatorioecSelect() {

		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		List<Tbcatrelatorioec> categoriaRelatorio = (List<Tbcatrelatorioec>) (Object) service.list(FIND_CATEGORIARELATORIO_BY_RELATORIO, hashParametro);

		for (int i = 0; i < categoriaRelatorio.size(); i++) {
			if (categoriaRelatorio.get(i).getIdCategoria().getIdCategoria() == Integer.parseInt(categorias)) {

				getHashParametro().put("idCatrelatorioec", categoriaRelatorio.get(i).getIdCatrelatorioec());
				tbcatrelatorioecSelect = (Tbcatrelatorioec) service.findOneResult(FIND_CATRELATORIO_BY_ID, hashParametro);

				return tbcatrelatorioecSelect;
			}
		}
		return tbcatrelatorioecSelect = new Tbcatrelatorioec();
	}

	public void setTbcatrelatorioecSelect(Tbcatrelatorioec tbcatrelatorioecSelect) {
		this.tbcatrelatorioecSelect = tbcatrelatorioecSelect;
	}

	@SuppressWarnings({ "unchecked" })
	public List<Tbcaraccatrelatorioec> getCaracteristicaRelacionada() {

		List<Tbcaraccatrelatorioec> tbcaraccatrelatorioecs = new ArrayList<>();

		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		List<Tbcatrelatorioec> categoriaRelatorio = (List<Tbcatrelatorioec>) (Object) service.list(FIND_CATEGORIARELATORIO_BY_RELATORIO, hashParametro);

		for (int i = 0; i < categoriaRelatorio.size(); i++) {
			if (categoriaRelatorio.get(i).getIdCategoria().getIdCategoria() == Integer.parseInt(categorias)) {

				getHashParametro().put("idCatrelatorioec", categoriaRelatorio.get(i).getIdCatrelatorioec());
				caracteristicaRelacionada = (List<Tbcaraccatrelatorioec>) (Object) service.list(FIND_CARACCATRELATORIO_BY_CATRELATORIO, hashParametro);

				for (int e = 0; e < caracteristicaRelacionada.size(); e++) {

					getHashParametro().put("idCaraccatrelatorio", caracteristicaRelacionada.get(e).getIdCaraccatrelatorio());
					List<Tbantpatentaria> tbantpatentarias = (List<Tbantpatentaria>) (Object) service.list(FIND_PATENTARIA_BY_CARAC, hashParametro);

					getHashParametro().put("idCaraccatrelatorio", caracteristicaRelacionada.get(e).getIdCaraccatrelatorio());
					List<Tbantnaopatentaria> tbantnaopatentarias = (List<Tbantnaopatentaria>) (Object) service.list(FIND_NAO_PATENTARIA_BY_CARC, hashParametro);

					if (tbantpatentarias.size() > 0 || tbantnaopatentarias.size() > 0) {
						tbcaraccatrelatorioecs.add(caracteristicaRelacionada.get(e));

					}
				}

			}
		}

		return tbcaraccatrelatorioecs;
	}

	public void setCaracteristicaRelacionada(List<Tbcaraccatrelatorioec> caracteristicaRelacionada) {
		this.caracteristicaRelacionada = caracteristicaRelacionada;
	}

	@SuppressWarnings("unchecked")
	public List<Tbcaraccatrelatorioec> getCaracteristcaNaoRelacionada() {

		List<Tbcaraccatrelatorioec> tbcaraccatrelatorioecs = new ArrayList<>();

		getHashParametro().put("idRelatorioEc", Long.parseLong(numeroRelatorio));
		List<Tbcatrelatorioec> categoriaRelatorio = (List<Tbcatrelatorioec>) (Object) service.list(FIND_CATEGORIARELATORIO_BY_RELATORIO, hashParametro);

		for (int i = 0; i < categoriaRelatorio.size(); i++) {
			if (categoriaRelatorio.get(i).getIdCategoria().getIdCategoria() == Integer.parseInt(categorias)) {

				getHashParametro().put("idCatrelatorioec", categoriaRelatorio.get(i).getIdCatrelatorioec());
				caracteristicaRelacionada = (List<Tbcaraccatrelatorioec>) (Object) service.list(FIND_CARACCATRELATORIO_BY_CATRELATORIO, hashParametro);

				for (int e = 0; e < caracteristicaRelacionada.size(); e++) {

					getHashParametro().put("idCaraccatrelatorio", caracteristicaRelacionada.get(e).getIdCaraccatrelatorio());
					List<Tbantpatentaria> tbantpatentarias = (List<Tbantpatentaria>) (Object) service.list(FIND_PATENTARIA_BY_CARAC, hashParametro);

					getHashParametro().put("idCaraccatrelatorio", caracteristicaRelacionada.get(e).getIdCaraccatrelatorio());
					List<Tbantnaopatentaria> tbantnaopatentarias = (List<Tbantnaopatentaria>) (Object) service.list(FIND_NAO_PATENTARIA_BY_CARC, hashParametro);

					if (tbantpatentarias.size() == 0 && tbantnaopatentarias.size() == 0) {
						tbcaraccatrelatorioecs.add(caracteristicaRelacionada.get(e));

					}
				}

			}
		}

		return tbcaraccatrelatorioecs;

	}

	public void setCaracteristcaNaoRelacionada(List<Tbcaraccatrelatorioec> caracteristcaNaoRelacionada) {
		this.caracteristcaNaoRelacionada = caracteristcaNaoRelacionada;
	}

	public String getResumoCat() {
		if (resumoCat != null) {
			if (!(resumoCat.trim().isEmpty()))
				return resumoCat;

		}
		if (this.getTbcatrelatorioec().getTxResumo() != null) {
			return this.getTbcatrelatorioec().getTxResumo();
		}
		if (this.getTbcatrelatorioecSelect().getTxResumo() != null) {
			return this.getTbcatrelatorioecSelect().getTxResumo();
		}

		return "";

	}

	public void setResumoCat(String resumoCat) {

		if ((!"".equals(resumoCat)) & (resumoCat != null)) {
			this.resumoCat = resumoCat;
		}
	}

	public TreeNode getSelectedNode1() {
		return selectedNode1;
	}

	public void setSelectedNode1(TreeNode selectedNode1) {
		this.selectedNode1 = selectedNode1;
	}

	public DocumentService getClausulaList() {
		return clausulaList;
	}

	public void setClausulaList(DocumentService clausulaList) {
		this.clausulaList = clausulaList;
	}

	public TreeNode getRoot1() {
		return root1;
	}

	public void setRoot1(TreeNode root1) {
		this.root1 = root1;
	}

	public List<Tbclausulatipo> getClausulaTipoList() {
		return clausulaTipoList;
	}

	public void setClausulaTipoList(List<Tbclausulatipo> clausulaTipoList) {
		this.clausulaTipoList = clausulaTipoList;
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

	public TreeNode getSelectedNode4() {
		return selectedNode4;
	}

	public void setSelectedNode4(TreeNode selectedNode4) {
		this.selectedNode4 = selectedNode4;
	}

	public TreeNode getSelectedNode5() {
		return selectedNode5;
	}

	public void setSelectedNode5(TreeNode selectedNode5) {
		this.selectedNode5 = selectedNode5;
	}

	public TreeNode getRoot2() {
		return root2;
	}

	public void setRoot2(TreeNode root2) {
		this.root2 = root2;
	}

	public TreeNode getRoot3() {
		return root3;
	}

	public void setRoot3(TreeNode root3) {
		this.root3 = root3;
	}

	public String getRelacao() {

		if (relacao != null) {
			return relacao;
		}
		if (this.getAntpatentariacatrelec().getTxRelacao() != null) {
			return this.getAntpatentariacatrelec().getTxRelacao();
		}
		if (this.getAntpatentariacatrelecselect() != null) {
			if (this.getAntpatentariacatrelecselect().getTxRelacao() != null) {
				return this.getAntpatentariacatrelecselect().getTxRelacao();
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
		if (this.getAntpatentariacatrelec().getTxReivindicacao() != null) {
			return this.getAntpatentariacatrelec().getTxReivindicacao();
		}
		if (this.getAntpatentariacatrelecselect() != null) {
			if (this.getAntpatentariacatrelecselect().getTxReivindicacao() != null) {
				return this.getAntpatentariacatrelecselect().getTxReivindicacao();
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
		if (this.getAntnaopatentariacatrelec().getTxRelacao() != null) {
			return this.getAntnaopatentariacatrelec().getTxRelacao();
		}
		if (this.getAntnaopatentariacatrelecSelect() != null) {
			if (this.getAntnaopatentariacatrelecSelect().getTxRelacao() != null) {
				return this.getAntnaopatentariacatrelecSelect().getTxRelacao();
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
		if (this.getAntnaopatentariacatrelec().getTxReivindicacao() != null) {
			return this.getAntnaopatentariacatrelec().getTxReivindicacao();
		}
		if (this.getAntnaopatentariacatrelecSelect() != null) {
			if (this.getAntnaopatentariacatrelecSelect().getTxReivindicacao() != null) {
				return this.getAntnaopatentariacatrelecSelect().getTxReivindicacao();
			}
		}

		return "";

	}

	public void setReivindicacaoNP(String reivindicacaoNP) {

		if ((!"".equals(reivindicacaoNP)) & (reivindicacaoNP != null)) {
			this.reivindicacaoNP = reivindicacaoNP;
		}

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

	public String getSalvarAnterioridadePatentariaCat() {
		return salvarAnterioridadePatentariaCat;
	}

	public void setSalvarAnterioridadePatentariaCat(String salvarAnterioridadePatentariaCat) {
		this.salvarAnterioridadePatentariaCat = salvarAnterioridadePatentariaCat;
	}

	public String getSalvarAnterioridadeNaoPatentariaCat() {
		return salvarAnterioridadeNaoPatentariaCat;
	}

	public void setSalvarAnterioridadeNaoPatentariaCat(String salvarAnterioridadeNaoPatentariaCat) {
		this.salvarAnterioridadeNaoPatentariaCat = salvarAnterioridadeNaoPatentariaCat;
	}

	public String getAlterarAnterioridadePatentariaCat() {
		return alterarAnterioridadePatentariaCat;
	}

	public void setAlterarAnterioridadePatentariaCat(String alterarAnterioridadePatentariaCat) {
		this.alterarAnterioridadePatentariaCat = alterarAnterioridadePatentariaCat;
	}

	public String getAlterarAnterioridadeNaoPatentariaCat() {
		return alterarAnterioridadeNaoPatentariaCat;
	}

	public void setAlterarAnterioridadeNaoPatentariaCat(String alterarAnterioridadeNaoPatentariaCat) {
		this.alterarAnterioridadeNaoPatentariaCat = alterarAnterioridadeNaoPatentariaCat;
	}

	public String getExcluirAnterioridadePatentariaCat() {
		return excluirAnterioridadePatentariaCat;
	}

	public void setExcluirAnterioridadePatentariaCat(String excluirAnterioridadePatentariaCat) {
		this.excluirAnterioridadePatentariaCat = excluirAnterioridadePatentariaCat;
	}

	public String getExcluirAnterioridadeNaoPatentariaCat() {
		return excluirAnterioridadeNaoPatentariaCat;
	}

	public void setExcluirAnterioridadeNaoPatentariaCat(String excluirAnterioridadeNaoPatentariaCat) {
		this.excluirAnterioridadeNaoPatentariaCat = excluirAnterioridadeNaoPatentariaCat;
	}

}
