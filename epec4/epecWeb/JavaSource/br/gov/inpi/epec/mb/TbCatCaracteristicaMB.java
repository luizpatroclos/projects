package br.gov.inpi.epec.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.gov.inpi.epec.beans.Tbantnaopatentaria;
import br.gov.inpi.epec.beans.Tbantpatentaria;
import br.gov.inpi.epec.beans.Tbcadtipoanterioridade;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbcaraccatrelatorioec;
import br.gov.inpi.epec.beans.Tbcatrelatorioec;
import br.gov.inpi.epec.beans.Tbclausulatipo;
import br.gov.inpi.epec.beans.Tblogrelatorio;
import br.gov.inpi.epec.beans.Tbnos;
import br.gov.inpi.epec.facade.EpecServiceFacade;
import br.gov.inpi.epec.util.Document;
import br.gov.inpi.epec.util.EPECUtil;

@SessionScoped
@ManagedBean
public class TbCatCaracteristicaMB extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 3953931804960603846L;

	@EJB
	EpecServiceFacade service;

	Tbcatrelatorioec tbcatrelatorioec;

	Tbcaraccatrelatorioec tbcaraccatrelatorioec = new Tbcaraccatrelatorioec();

	Tbcaraccatrelatorioec tbcaraccatrelatorioecSelect;

	Tbcaraccatrelatorioec tbcaraccatrelatorioecExcluir;

	List<Tbantpatentaria> antPatentaria;

	List<Tbantnaopatentaria> antNaoPatentaria;

	Tbantpatentaria tbantpatentaria = new Tbantpatentaria();

	Tbantnaopatentaria tbantnaopatentaria = new Tbantnaopatentaria();

	Tbantpatentaria tbantpatentariaSelect;

	Tbantpatentaria tbantpatentariaExcluir;

	Tbantnaopatentaria tbantnaopatentariaSelect;

	Tbantnaopatentaria tbantnaopatentariaExcluir;

	private String caracteristica;

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

	public List<Tblogrelatorio> logRelatorio;
		
	private String inserirCaracteristicaMsg;
	
	private String excluirAnterioridadePatentariaCarac;
	
	private String excluirAnterioridadeNaoPatentariaCarac;
	
	
	public void mensagemCaracteristica(){

		if(inserirCaracteristicaMsg != null){
			if(inserirCaracteristicaMsg.equals("1")){
				incluidoComSucesso();
			}else if(inserirCaracteristicaMsg.equals("2")){
				alteradoComSucesso();
			}
			inserirCaracteristicaMsg = null;
		}		
		
		if (excluirAnterioridadePatentariaCarac != null){
			if (excluirAnterioridadePatentariaCarac.equals("1")) {
				excluidoComSucesso();
				

			}
		this.excluirAnterioridadePatentariaCarac = null;
		}
		
		if (excluirAnterioridadeNaoPatentariaCarac != null){
			if (excluirAnterioridadeNaoPatentariaCarac.equals("1")) {
				excluidoComSucesso();
				

			}
		this.excluirAnterioridadeNaoPatentariaCarac = null;
		}		
		
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

	private void obterUsuarioSessao() {
		FacesContext fc = FacesContext.getCurrentInstance();

		session = (HttpSession) fc.getExternalContext().getSession(true);

		usuarioLogado = (Tbcadusuario) session.getAttribute("usuarioAutenticado");
	}

	@PostConstruct
	public void init() {

		usuarioLogado = getUsuarioLogado();
		this.caracteristica = null;
		this.relacao = null;
		this.relacaoNP = null;
		this.reivindicacao = null;
		this.reivindicacaoNP = null;

		loadClausulaTipoResumo();
	}

	public void salvarCaracteristica() {
		if(caracteristica != null){
			
			if(!(caracteristica.trim().isEmpty())){
				tbcaraccatrelatorioec.setIdCatrelatorioec(tbcatrelatorioec);
				tbcaraccatrelatorioec.setTxCaracteristica(caracteristica);
				service.save(tbcaraccatrelatorioec);

				salvarLogRelatorio(tbcatrelatorioec.getIdRelatorioEc().getIdRelatorioEc(), "Inserir", "Característica", caracteristica, usuarioLogado.getStrUsuario());
				tbcaraccatrelatorioecSelect = tbcaraccatrelatorioec;
				
				tbcaraccatrelatorioec = new Tbcaraccatrelatorioec();
				this.relacao = null;
				this.relacaoNP = null;
				this.reivindicacao = null;
				this.reivindicacaoNP = null;
				this.inserirCategoria = null;			
				clausulaTipoZeradas();	
				inserirCaracteristicaMsg = "1";
				
				
				EPECUtil.redirecionar("/epecWeb/pages/alterarCaracteristica.jsf");		
				
			}else{
				mensagemOcampoCaracteristicaNaoPodeFicarEmBranco();
			}
		}else{
			mensagemOcampoCaracteristicaNaoPodeFicarEmBranco();
		}
		
		
	}

	public void alterarCaracteristica() {
		this.caracteristica = null;
		this.relacao = null;
		this.relacaoNP = null;
		this.reivindicacao = null;
		this.reivindicacaoNP = null;
		this.inserirCategoria = "1";
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/alterarCaracteristica.jsf");
	}

	
	public void menuVoltarCategoria() {
		this.inserirCategoria = null;
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/inserirCategoria.jsf");
	}
	
	public void updateCaracteristica() {		

		if(caracteristica != null){
			if(!(caracteristica.trim().isEmpty())){
				tbcaraccatrelatorioecSelect.setTxCaracteristica(caracteristica);				
				
				service.update(tbcaraccatrelatorioecSelect);

				salvarLogRelatorio(tbcaraccatrelatorioecSelect.getIdCatrelatorioec().getIdRelatorioEc().getIdRelatorioEc(), "Alterar", "Característica", tbcaraccatrelatorioecSelect.getTxCaracteristica(), usuarioLogado.getStrUsuario());

				this.caracteristica = null;
				this.relacao = null;
				this.relacaoNP = null;
				this.reivindicacao = null;
				this.reivindicacaoNP = null;
				this.inserirCategoria = null;
				
				
				clausulaTipoZeradas();
				alteradoComSucesso();			
			}else{
				mensagemOcampoCaracteristicaNaoPodeFicarEmBranco();
			}
		}else{
			mensagemOcampoCaracteristicaNaoPodeFicarEmBranco();
		}
			
			
			
	}

	public void visualizarCaracteristica() {
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/visualizarCaracteristica.jsf");
	}

	public void excluirCaracteristica() {

		if (tbcaraccatrelatorioecExcluir != null) {
			service.deleteCaracteristica(tbcaraccatrelatorioecExcluir);

			salvarLogRelatorio(tbcaraccatrelatorioecExcluir.getIdCatrelatorioec().getIdRelatorioEc().getIdRelatorioEc(), "Excluir", "Característica", tbcaraccatrelatorioecExcluir.getTxCaracteristica(), usuarioLogado.getStrUsuario());

		}
		
		clausulaTipoZeradas();
		excluidoComSucesso();
	}
	

	public void visualizarAnterioridades() {
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/anterioridadeDaCaracteristica.jsf");
	}

	public void inserirAnterioridadePatentaria() {
		this.relacao = null;
		this.reivindicacao = null;
		this.tbantpatentaria = new Tbantpatentaria();
		this.tbantpatentariaSelect = null;
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/inserirAntPatentaria.jsf");
	}

	public void inserirAnterioridadeNaoPatentaria() {
		this.relacaoNP = null;
		this.reivindicacaoNP = null;
		this.tbantnaopatentaria = new Tbantnaopatentaria();
		this.tbantnaopatentariaSelect = null;
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/inserirAntNaoPatentaria.jsf");
	}

	public void menuVoltarCaracteristica() {
		this.inserirCategoria= "1";
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/alterarCaracteristica.jsf");
	}

	public void salvarAntPatentaria() {
		if (tbantpatentaria.getStrAntpatentaria() == null) {
			tbantpatentaria = new Tbantpatentaria();
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();
		} else if (tbantpatentaria.getStrAntpatentaria().isEmpty()) {
			tbantpatentaria = new Tbantpatentaria();
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();
		} else {
			Tbcadtipoanterioridade tbcadtipoanterioridade = service.buscaPorIdTipoAnterioridade(Integer.parseInt(tbantpatentaria.getCadTipoAnteriodadePatentaria()));
			tbantpatentaria.setIdCadtipoanterioridade(tbcadtipoanterioridade);
			tbantpatentaria.setIdCaraccatrelatorio(tbcaraccatrelatorioecSelect);
			tbantpatentaria.setTxReivindicacao(reivindicacao);
			tbantpatentaria.setTxRelacao(relacao);

			service.save(tbantpatentaria);

			salvarLogRelatorio(tbcaraccatrelatorioecSelect.getIdCatrelatorioec().getIdRelatorioEc().getIdRelatorioEc(), "Inserir", "Anterioridade Patentária da Característica",
					tbantpatentaria.getStrAntpatentaria(), usuarioLogado.getStrUsuario());

			tbantpatentaria = new Tbantpatentaria();
	
			this.inserirCaracteristicaMsg = "1";
			visualizarAnterioridades();
		}

	}

	public void salvarAntNaoPatentaria() {

		if (tbantnaopatentaria.getTxTitulo() == null) {
			tbantnaopatentaria = new Tbantnaopatentaria();
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else if (tbantnaopatentaria.getTxTitulo().isEmpty()) {
			tbantnaopatentaria = new Tbantnaopatentaria();
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else {

			Tbcadtipoanterioridade tbcadtipoanterioridade = service.buscaPorIdTipoAnterioridade(Integer.parseInt(tbantnaopatentaria.getCadTipoAnteriodadePatentaria()));
			tbantnaopatentaria.setIdCadtipoanterioridade(tbcadtipoanterioridade);
			tbantnaopatentaria.setIdCaraccatrelatorio(tbcaraccatrelatorioecSelect);
			tbantnaopatentaria.setTxRelacao(relacaoNP);
			tbantnaopatentaria.setTxReivindicacao(reivindicacaoNP);

			service.save(tbantnaopatentaria);

			salvarLogRelatorio(tbantnaopatentaria.getIdCaraccatrelatorio().getIdCatrelatorioec().getIdRelatorioEc().getIdRelatorioEc(), "Inserir", "Anterioridade Não Patentária da Característica",
					tbantnaopatentaria.getTxTitulo(), usuarioLogado.getStrUsuario());

			tbantnaopatentaria = new Tbantnaopatentaria();			
			this.inserirCaracteristicaMsg = "1";
			visualizarAnterioridades();	
		}
	}

	public void loadClausulaTipoResumo() {

		root1 = buscaArvore();

		root2 = buscaArvore();

		root3 = buscaArvore();

	}
	
	public TreeNode buscaArvore() {

		if(usuarioLogado == null){
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
			
			if (no.getIdClausulaTipo() != null ) {		
				
					//TreeNode clausula = new DefaultTreeNode(new Document(no.getIdClausulaTipo().getTxEspanhol(), "-", "Pastas", null), filho);
				
				filho.getChildren().add(new DefaultTreeNode(no.getIdClausulaTipo().getTxEspanhol()));
			}
			for (int i = 0; i < no.getTbnosList().size(); i++) {		
				
				ValidarArvore(no.getTbnosList().get(i), filho, null);
			}
			

		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			
			filho = new DefaultTreeNode(new Document(no.getTxTituloIngles(), "-", "Folder", null), pai);
			
			if (no.getIdClausulaTipo() != null ) {		
				
				filho.getChildren().add(new DefaultTreeNode(no.getIdClausulaTipo().getTxIngles()));
				
					///TreeNode clausula = new DefaultTreeNode(new Document(no.getIdClausulaTipo().getTxIngles(), "-", "Pastas", null), filho);
			}
			for (int i = 0; i < no.getTbnosList().size(); i++) {		
				
				ValidarArvore(no.getTbnosList().get(i), filho, null);
			}
			

		} else {
			filho = new DefaultTreeNode(new Document(no.getTxTituloPortugues(), "-", "Folder", null), pai);

			if (no.getIdClausulaTipo() != null) {
				filho.getChildren().add(new DefaultTreeNode(no.getIdClausulaTipo().getTxPortugues()));

				//TreeNode clausula = new DefaultTreeNode(new Document(no.getIdClausulaTipo().getTxPortugues(), "-", "Pastas", null), filho);
			}
			for (int i = 0; i < no.getTbnosList().size(); i++) {

				ValidarArvore(no.getTbnosList().get(i), filho, null);
			}
		}

	}


	public void displaySelectedSingle() {

		if (selectedNode1 != null) {

			String valor = selectedNode1.getData().toString();
			if(caracteristica == null)
				caracteristica = "";
			this.setCaracteristica(this.caracteristica + valor);
		}

		if (selectedNode2 != null) {

			String valor = selectedNode2.getData().toString();
			if(relacao == null)
				relacao = "";
			this.setRelacao(this.relacao + valor);

		}

		if (selectedNode3 != null) {

			String valor = selectedNode3.getData().toString();
			if(reivindicacao == null)
				reivindicacao = "";
			this.setReivindicacao(this.reivindicacao + valor);

		}

		if (selectedNode4 != null) {

			String valor = selectedNode4.getData().toString();
			if(relacaoNP == null)
				relacaoNP = "";
			this.setRelacaoNP(this.relacaoNP + valor);

		}

		if (selectedNode5 != null) {

			String valor = selectedNode5.getData().toString();
			if(reivindicacaoNP == null)
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

	public TreeNode buscaArvore(int idClausula) {

		List<Tbclausulatipo> clausulas = service.findClausulaTipoPorIdAtivo(idClausula);

		TreeNode root = clausulaList.createDocuments(clausulas);

		return root;
	}

	public void visualizarAntPatentaria() {
		EPECUtil.redirecionar("/epecWeb/pages/visualizarAntPatentaria.jsf");
	}

	public void visualizarAntNaoPatentaria() {
		EPECUtil.redirecionar("/epecWeb/pages/visualizarAntNaoPatentaria.jsf");
	}

	public void alterarAntPatentaria() {
		this.relacao = null;
		this.reivindicacao = null;
		clausulaTipoZeradas();
		
		EPECUtil.redirecionar("/epecWeb/pages/alterarAntPatentaria.jsf");
	}

	public void alterarAntNaoPatentaria() {
		this.reivindicacaoNP = null;
		this.relacaoNP = null;
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/alterarAntNaoPatentaria.jsf");
	}

	public void updateAntPatentaria() {
		if (tbantpatentariaSelect.getStrAntpatentaria() == null) {
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else if (tbantpatentariaSelect.getStrAntpatentaria().isEmpty()) {
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else {

			if (tbantpatentaria.getCadTipoAnteriodadePatentaria() != null) {
				Tbcadtipoanterioridade tbcadtipoanterioridade = service.buscaPorIdTipoAnterioridade(Integer.parseInt(tbantpatentaria.getCadTipoAnteriodadePatentaria()));
				tbantpatentariaSelect.setIdCadtipoanterioridade(tbcadtipoanterioridade);
			}
			
			tbantpatentariaSelect.setTxReivindicacao(reivindicacao);
			tbantpatentariaSelect.setTxRelacao(relacao);
			
			salvarLogRelatorio(tbantpatentariaSelect.getIdCaraccatrelatorio().getIdCatrelatorioec().getIdRelatorioEc().getIdRelatorioEc(), "Alterar", "Anterioridade Patentária da Característica",
					tbantpatentariaSelect.getStrAntpatentaria(), usuarioLogado.getStrUsuario());

			service.update(tbantpatentariaSelect);
			this.inserirCaracteristicaMsg = "2";
			visualizarAnterioridades();	;
		}
	}

	public void updateAntNaoPatentaria() {
		if (tbantnaopatentariaSelect.getTxTitulo() == null) {
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();

		} else if (tbantnaopatentariaSelect.getTxTitulo().isEmpty()) {
			mensagemOcampoAnterioridadeNaoPodeFicarEmBranco();
		} else {

			if (tbantnaopatentariaSelect.getCadTipoAnteriodadePatentaria() != null) {
				Tbcadtipoanterioridade tbcadtipoanterioridade = service.buscaPorIdTipoAnterioridade(Integer.parseInt(tbantnaopatentaria.getCadTipoAnteriodadePatentaria()));
				tbantnaopatentariaSelect.setIdCadtipoanterioridade(tbcadtipoanterioridade);				
			}

			tbantnaopatentariaSelect.setTxReivindicacao(reivindicacaoNP);
			tbantnaopatentariaSelect.setTxRelacao(relacaoNP);			salvarLogRelatorio(tbantnaopatentariaSelect.getIdCaraccatrelatorio().getIdCatrelatorioec().getIdRelatorioEc().getIdRelatorioEc(), "Alterar",
					"Anterioridade Não Patentária da Característica", tbantnaopatentariaSelect.getTxTitulo(), usuarioLogado.getStrUsuario());

			service.update(tbantnaopatentariaSelect);

			
			this.inserirCaracteristicaMsg = "2";
			visualizarAnterioridades();	
		}
	}

	public void voltarAnterioridadeCaracteristica() {
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/anterioridadeDaCaracteristica.jsf");
	}

	public void excluirAntPatentaria() {
		if (tbantpatentariaExcluir != null) {
			service.deleteAntPatentariaCarac(tbantpatentariaExcluir);
			salvarLogRelatorio(tbantpatentariaExcluir.getIdCaraccatrelatorio().getIdCatrelatorioec().getIdRelatorioEc().getIdRelatorioEc(), "Excluir", "Anterioridade Patentária da Característica",
					tbantpatentariaExcluir.getStrAntpatentaria(), usuarioLogado.getStrUsuario());

		}
		this.excluirAnterioridadePatentariaCarac = "1";
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/anterioridadeDaCaracteristica.jsf");
	}

	public void excluirAntNaoPatentaria() {
		if (tbantnaopatentariaExcluir != null) {
			service.deleteAntNaoPatentariaCarac(tbantnaopatentariaExcluir);

			salvarLogRelatorio(tbantnaopatentariaExcluir.getIdCaraccatrelatorio().getIdCatrelatorioec().getIdRelatorioEc().getIdRelatorioEc(), "Excluir",
					"Anterioridade Não Patentária da Característica", tbantnaopatentariaExcluir.getTxTitulo(), usuarioLogado.getStrUsuario());

		}
		this.excluirAnterioridadeNaoPatentariaCarac = "1";
		clausulaTipoZeradas();
		EPECUtil.redirecionar("/epecWeb/pages/anterioridadeDaCaracteristica.jsf");

	}

	public Tbcaraccatrelatorioec getTbcaraccatrelatorioec() {
		return tbcaraccatrelatorioec;
	}

	public void setTbcaraccatrelatorioec(Tbcaraccatrelatorioec tbcaraccatrelatorioec) {
		this.tbcaraccatrelatorioec = tbcaraccatrelatorioec;
	}

	public Tbcaraccatrelatorioec getTbcaraccatrelatorioecSelect() {
		return tbcaraccatrelatorioecSelect;
	}

	public void setTbcaraccatrelatorioecSelect(Tbcaraccatrelatorioec tbcaraccatrelatorioecSelect) {
		this.tbcaraccatrelatorioecSelect = tbcaraccatrelatorioecSelect;
	}

	public Tbcaraccatrelatorioec getTbcaraccatrelatorioecExcluir() {
		return tbcaraccatrelatorioecExcluir;
	}

	public void setTbcaraccatrelatorioecExcluir(Tbcaraccatrelatorioec tbcaraccatrelatorioecExcluir) {
		this.tbcaraccatrelatorioecExcluir = tbcaraccatrelatorioecExcluir;
	}

	public Tbcatrelatorioec getTbcatrelatorioec() {
		return tbcatrelatorioec;
	}

	public void setTbcatrelatorioec(Tbcatrelatorioec tbcatrelatorioec) {
		this.tbcatrelatorioec = tbcatrelatorioec;
	}

	@SuppressWarnings("unchecked")
	public List<Tbantpatentaria> getAntPatentaria() {
		getHashParametro().put("idCaraccatrelatorio", tbcaraccatrelatorioecSelect.getIdCaraccatrelatorio());
		antPatentaria = (List<Tbantpatentaria>) (Object) service.list(FIND_PATENTARIA_BY_CARAC, hashParametro);

		return antPatentaria;
	}

	public void setAntPatentaria(List<Tbantpatentaria> antPatentaria) {
		this.antPatentaria = antPatentaria;
	}

	@SuppressWarnings("unchecked")
	public List<Tbantnaopatentaria> getAntNaoPatentaria() {

		getHashParametro().put("idCaraccatrelatorio", tbcaraccatrelatorioecSelect.getIdCaraccatrelatorio());
		antNaoPatentaria = (List<Tbantnaopatentaria>) (Object) service.list(FIND_NAO_PATENTARIA_BY_CARC, hashParametro);

		return antNaoPatentaria;
	}

	public void setAntNaoPatentaria(List<Tbantnaopatentaria> antNaoPatentaria) {
		this.antNaoPatentaria = antNaoPatentaria;
	}

	public Tbantpatentaria getTbantpatentaria() {
		return tbantpatentaria;
	}

	public void setTbantpatentaria(Tbantpatentaria tbantpatentaria) {
		this.tbantpatentaria = tbantpatentaria;
	}

	public Tbantnaopatentaria getTbantnaopatentaria() {
		return tbantnaopatentaria;
	}

	public void setTbantnaopatentaria(Tbantnaopatentaria tbantnaopatentaria) {
		this.tbantnaopatentaria = tbantnaopatentaria;
	}

	public Tbantpatentaria getTbantpatentariaSelect() {
		return tbantpatentariaSelect;
	}

	public void setTbantpatentariaSelect(Tbantpatentaria tbantpatentariaSelect) {
		this.tbantpatentariaSelect = tbantpatentariaSelect;
	}

	public Tbantpatentaria getTbantpatentariaExcluir() {
		return tbantpatentariaExcluir;
	}

	public void setTbantpatentariaExcluir(Tbantpatentaria tbantpatentariaExcluir) {
		this.tbantpatentariaExcluir = tbantpatentariaExcluir;
	}

	public Tbantnaopatentaria getTbantnaopatentariaSelect() {
		return tbantnaopatentariaSelect;
	}

	public void setTbantnaopatentariaSelect(Tbantnaopatentaria tbantnaopatentariaSelect) {
		this.tbantnaopatentariaSelect = tbantnaopatentariaSelect;
	}

	public Tbantnaopatentaria getTbantnaopatentariaExcluir() {
		return tbantnaopatentariaExcluir;
	}

	public void setTbantnaopatentariaExcluir(Tbantnaopatentaria tbantnaopatentariaExcluir) {
		this.tbantnaopatentariaExcluir = tbantnaopatentariaExcluir;
	}

	public void apagarCaracteristica(){
		caracteristica = null;
		tbcaraccatrelatorioecSelect = null;
		
	}
	
	public String getCaracteristica() {
		
		if(this.inserirCaracteristica!= null){
			if(this.inserirCaracteristica.equals("1")){

				if(caracteristica != null){
					return caracteristica;
				}
				
				if (this.getTbcaraccatrelatorioecSelect() == null) {
					this.tbcaraccatrelatorioecSelect = new Tbcaraccatrelatorioec();
				}

				if (this.getTbcaraccatrelatorioecSelect().getTxCaracteristica() != null) {
					return this.getTbcaraccatrelatorioecSelect().getTxCaracteristica();
				}

				if (this.getTbcaraccatrelatorioec().getTxCaracteristica() != null) {
					return this.getTbcaraccatrelatorioec().getTxCaracteristica();
				}
				
			}
		}
		
	
			return "";

		

	}

	public void setCaracteristica(String caracteristica) {

		if ((!"".equals(caracteristica)) & (caracteristica != null)) {
			this.caracteristica = caracteristica;
		}

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

	public List<Tbclausulatipo> getClausulaTipoList() {
		return clausulaTipoList;
	}

	public void setClausulaTipoList(List<Tbclausulatipo> clausulaTipoList) {
		this.clausulaTipoList = clausulaTipoList;
	}

	public String getRelacao() {

		if (relacao != null) {
			return relacao;
		}

		if (this.getTbantpatentaria().getTxRelacao() != null) {

			return this.getTbantpatentaria().getTxRelacao();

		}
		if (this.getTbantpatentariaSelect() != null) {
			if (this.getTbantpatentariaSelect().getTxRelacao() != null) {

				return this.getTbantpatentariaSelect().getTxRelacao();

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
		if (this.getTbantpatentaria().getTxReivindicacao() != null) {
			return this.getTbantpatentaria().getTxReivindicacao();
		}
		if (this.getTbantpatentariaSelect() != null) {

			if (this.getTbantpatentariaSelect().getTxReivindicacao() != null) {
				return this.getTbantpatentariaSelect().getTxReivindicacao();
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

		if (this.getTbantnaopatentaria().getTxRelacao() != null) {

			return this.getTbantnaopatentaria().getTxRelacao();
		}
		if (this.getTbantnaopatentariaSelect() != null) {
			if (this.getTbantnaopatentariaSelect().getTxRelacao() != null) {

				return this.getTbantnaopatentariaSelect().getTxRelacao();

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

		if (this.getTbantnaopatentaria().getTxReivindicacao() != null) {
			return this.getTbantnaopatentaria().getTxReivindicacao();
		}
		if (this.getTbantnaopatentariaSelect() != null) {

			if (this.getTbantnaopatentariaSelect().getTxReivindicacao() != null) {
				return this.getTbantnaopatentariaSelect().getTxReivindicacao();
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




	public String getExcluirAnterioridadePatentariaCarac() {
		return excluirAnterioridadePatentariaCarac;
	}

	public void setExcluirAnterioridadePatentariaCarac(String excluirAnterioridadePatentariaCarac) {
		this.excluirAnterioridadePatentariaCarac = excluirAnterioridadePatentariaCarac;
	}

	public String getExcluirAnterioridadeNaoPatentariaCarac() {
		return excluirAnterioridadeNaoPatentariaCarac;
	}

	public void setExcluirAnterioridadeNaoPatentariaCarac(String excluirAnterioridadeNaoPatentariaCarac) {
		this.excluirAnterioridadeNaoPatentariaCarac = excluirAnterioridadeNaoPatentariaCarac;
	}

	public String getInserirCaracteristica() {
		return inserirCaracteristica;
	}

	public void setInserirCaracteristica(String inserirCaracteristica) {
		this.inserirCaracteristica = inserirCaracteristica;
	}

	public String getInserirCaracteristicaMsg() {
		return inserirCaracteristicaMsg;
	}

	public void setInserirCaracteristicaMsg(String inserirCaracteristicaMsg) {
		this.inserirCaracteristicaMsg = inserirCaracteristicaMsg;
	}

}
