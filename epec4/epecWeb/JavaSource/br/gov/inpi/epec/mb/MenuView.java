package br.gov.inpi.epec.mb;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.Init;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.gov.inpi.epec.util.EPECUtil;

@ManagedBean
@SessionScoped
public class MenuView extends AbstractMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4884185311854177920L;

	private int gerenciamentoSistema;

	private int busca;
	
	Locale localeES = new Locale("es", "");
	Locale localeEN = new Locale("en", "");

	public void manterPais() {
		EPECUtil.redirecionar("/epecWeb/pages/manterPais.jsf");
	}

	public void colaboracao() {
		EPECUtil.redirecionar("/epecWeb/pages/manterColaboracao.jsf");
	}

	public void manterEntidade() {
		EPECUtil.redirecionar("/epecWeb/pages/manterEntidade.jsf");
	}

	public void manterUsuario() {
		EPECUtil.redirecionar("/epecWeb/pages/manterUsuario.jsf");
	}

	public void gerenciarUsuarios() {
		EPECUtil.redirecionar("/epecWeb/pages/gerenciarUsuarios.jsf");
	}

	public void manterClausulaTipo() {
		EPECUtil.redirecionar("/epecWeb/pages/manterClausulaTipo.jsf");
	}

	public void estatistica() {
		EPECUtil.redirecionar("/epecWeb/pages/estatistica.jsf");
	}

	public void buscaPedido() {
		limpaBeanDaSessao();
		EPECUtil.redirecionar("/epecWeb/pages/buscarPedido.jsf");

	}

	public void buscaPublicacao() {
		limpaBeanDaSessao();
		EPECUtil.redirecionar("/epecWeb/pages/buscarPublicacao.jsf");

	}

	public void buscaPrioridade() {
		limpaBeanDaSessao();
		EPECUtil.redirecionar("/epecWeb/pages/buscarPrioridade.jsf");

	}

	public void logFamilia() {
		EPECUtil.redirecionar("/epecWeb/pages/logFamilia.jsf");
	}

	public void acessarHistorico() {
		EPECUtil.redirecionar("/epecWeb/pages/acessarHistorico.jsf");
	}

	public void logRelatorio() {
		EPECUtil.redirecionar("/epecWeb/pages/logRelatorio.jsf");
	}

	public void logPedido() {
		EPECUtil.redirecionar("/epecWeb/pages/logPedido.jsf");
	}

	public void visualizarDiagramaFamilia() {

		EPECUtil.redirecionar("/epecWeb/pages/visualizarDiagramaFamilia.jsf");
	}

	public void homeTela() {
		EPECUtil.redirecionar("/epecWeb/home.jsf");

	}
	
	public void manualPdf(){
		
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			EPECUtil.redirecionar("/epecWeb/resources/documentos/manual_es.pdf");
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			EPECUtil.redirecionar("/epecWeb/resources/documentos/manual_en.pdf");
		} else {
			EPECUtil.redirecionar("/epecWeb/resources/documentos/manual_pt.pdf");
		}
	}

	public void gerenciamentoHome() {
		busca = 0;
		getBusca();
		gerenciamentoSistema = 1;
		getGerenciamentoSistema();
		EPECUtil.redirecionar("home.jsf");
	}

	public void buscaHome() {
		busca = 1;
		getBusca();
		gerenciamentoSistema = 0;
		getGerenciamentoSistema();
		EPECUtil.redirecionar("home.jsf");

	}

	public int getGerenciamentoSistema() {
		return gerenciamentoSistema;
	}

	public void setGerenciamentoSistema(int gerenciamentoSistema) {
		this.gerenciamentoSistema = gerenciamentoSistema;
	}

	public int getBusca() {
		return busca;
	}

	public void setBusca(int busca) {
		this.busca = busca;
	}

	private void limpaBeanDaSessao() {

		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("consultaPedidoMB")) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("consultaPedidoMB");
		}
	}

}
