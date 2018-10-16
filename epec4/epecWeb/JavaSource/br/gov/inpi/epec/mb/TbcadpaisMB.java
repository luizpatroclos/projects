package br.gov.inpi.epec.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.gov.inpi.epec.beans.Tbcadentidade;
import br.gov.inpi.epec.beans.Tbcadpais;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbclausulatipo;
import br.gov.inpi.epec.beans.Tbcolaboracaoentidade;
import br.gov.inpi.epec.beans.Tbpatenteec;
import br.gov.inpi.epec.facade.EpecServiceFacade;
import br.gov.inpi.epec.util.EPECUtil;

@SessionScoped
@ManagedBean
public class TbcadpaisMB extends AbstractMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	EpecServiceFacade service;

	private Tbcadpais pais = new Tbcadpais();
	private Tbcadpais paisSelected;
	private Tbcadpais paisSelected2;
	private List<Tbcadpais> paises;
	private Tbcadpais selectedPaises;
	private List<Tbcadpais> escolherPais;
	private static final String PAGINA_INSERIR_MENSAGEM = "mensagemInserir.jsf";
	private static final String PAGINA_EDITAR_PAIS = "paisEditar.jsf";
	private static final String PAGINA_SUCESSO_PAIS = "mensagemSucessoPais.jsf";
	private static final String PAGINA_CONFIRMACAO_EXCLUSAO_MENSAGEM = "desejaExcluirMensagem.jsf";
	private Tbcadpais selectedPais;
	
	Locale localeES = new Locale("es", "");
	Locale localeEN = new Locale("en", "");

	@PostConstruct
	public void init() {
		escolherPais = new ArrayList<Tbcadpais>();

		loadPais();
		escolherPais.addAll(paises);

	}

	public Tbcadpais getPais() {
		return pais;
	}

	public void setPais(Tbcadpais pais) {
		this.pais = pais;
	}

	public List<Tbcadpais> getAllPais() {
		if (paises == null) {
			loadPais();
		}

		return paises;
	}

	@SuppressWarnings("unchecked")
	private void loadPais() {

		paises = (List<Tbcadpais>) (Object) service.listAll(FIND_ALL_PAIS);
	}

	public void resetPais() {
		pais = new Tbcadpais();
	}

	public void intermediarInsercao() {

		EPECUtil.redirecionar(PAGINA_INSERIR_MENSAGEM);

	}

	public void alterar() {

		String nome = pais.getStrNomePais();

		EPECUtil.redirecionar(PAGINA_INSERIR_MENSAGEM);

	}

	public void intermediarExclusao() {

		if (pais != null) {

			EPECUtil.redirecionar(PAGINA_CONFIRMACAO_EXCLUSAO_MENSAGEM);

		} else {
			nenhumItemSelecionado();
		}
	}

	public void intermediarAtualizacao(ActionEvent e) {

		if (pais != null) {

			EPECUtil.redirecionar(PAGINA_EDITAR_PAIS);

		} else {

			nenhumItemSelecionado();
			
		}

	}

	public void atualizar() {

		EPECUtil.redirecionar(PAGINA_SUCESSO_PAIS);

	}

	public Tbcadpais getSelectedPais() {
		return selectedPais;
	}

	public void setSelectedPais(Tbcadpais selectedPais) {
		this.selectedPais = selectedPais;
	}

	public List<Tbcadpais> getPaises() {
			loadPais();
		return paises;
	}

	public void setPaises(List<Tbcadpais> paises) {
		this.paises = paises;
	}

	public Tbcadpais getPaisSelected() {
		return paisSelected;
	}

	public void setPaisSelected(Tbcadpais paisSelected) {
		this.paisSelected = paisSelected;

	}

	public void createPais() {

		if (pais.getStrNomePais().isEmpty()) {
			
			dadosIncorretosVerifique();
			
		} else if (pais.getStrCodPais().isEmpty()) {
			dadosIncorretosVerifique();
		} else {
			if (pais.getStrCodPais().length() > 2) {
				dadosIncorretosVerifique();
			}else if (verificarNomeDuplicado()) {

				service.save(pais);

				incluidoComSucesso();
				
			} 
		}
		flush();

	}

	@SuppressWarnings("unchecked")
	private boolean verificarNomeDuplicado() {
		boolean verificarNomeDuplicado = true;
		paises = (List<Tbcadpais>) (Object) service.listAll(FIND_ALL_PAIS);
		for (int i = 0; i < paises.size(); i++) {
		
			if (paises.get(i).getStrNomePais().toUpperCase().equals(pais.getStrNomePais().toUpperCase())) {
				
				verificarNomeDuplicado = false;
				nomeDoPaisJaExiste(pais.getStrNomePais());
			
			
		}
			
		}
		
		
		for (int i = 0; i < paises.size(); i++) {
			if (paises.get(i).getStrCodPais().toUpperCase().equals(pais.getStrCodPais().toUpperCase())) {
				verificarNomeDuplicado = false;
				
				CodigoJaExiste(pais.getStrCodPais());
				
			}
		}
		return verificarNomeDuplicado;
	}

	@SuppressWarnings("unchecked")
	public void excluirPais(){
		getHashParametro().put("idPais", paisSelected2.getIdPais());		
		
		List<Tbcadentidade> tbcadentidade = (List<Tbcadentidade>) (Object) service.list(FIND_ALL_ENTIDADE_PAIS, hashParametro);

		
		if(tbcadentidade.size() == 0){
			
			service.apagarPais(paisSelected2);
			
			excluidoComSucesso();			
			
		}else{
			operacaoNaoPodeSerRealizada();			
		}

	}
	
	
	
	public Tbcadpais getPaisSelected2() {
		return paisSelected2;
	}

	public void setPaisSelected2(Tbcadpais paisSelected2) {
		this.paisSelected2 = paisSelected2;
	}

	public Tbcadpais getSelectedPaises() {
		return selectedPaises;
	}

	public void flush() {
		pais = new Tbcadpais();
		loadPais();
	}

	public void setSelectedPaises(Tbcadpais selectedCities) {
		this.selectedPaises = selectedCities;
	}

	public List<Tbcadpais> getEscolherPais() {
		init();
		return escolherPais;
	}

}