package br.gov.inpi.epec.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbclausulatipo;
import br.gov.inpi.epec.beans.Tbfilhos;
import br.gov.inpi.epec.beans.Tbnos;
import br.gov.inpi.epec.facade.EpecServiceFacade;
import br.gov.inpi.epec.util.EPECUtil;

@SessionScoped
@ManagedBean
public class ClausulaTipoMB extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	EpecServiceFacade service;

	private Tbclausulatipo newClausula = new Tbclausulatipo();

	private List<Tbclausulatipo> clausulaTipoList;

	private Tbnos newNos = new Tbnos();

	private List<Tbnos> nosList;

	private List<Tbnos> filteredNosList;

	private List<Tbnos> nosPaiList;

	private String dirAux;

	String auxUsuario = null;

	private List<Tbcadusuario> usurario;

	private Tbnos noSelected = new Tbnos();

	public String tipo;

	public ExternalContext externalContext;

	private HttpSession session;

	public Tbcadusuario usuarioLogado;

	Locale localeES = new Locale("es", "");
	Locale localeEN = new Locale("en", "");

	boolean condicao = true;

	List<Tbnos> apagarNoLista = new ArrayList<>();

	private String mensagem;

	public void mensagemClausula() {
		if (mensagem != null) {
			if (mensagem.equals("1")) {
				incluidoComSucesso();
			}
			if (mensagem.equals("2")) {
				alteradoComSucesso();
			}
			if (mensagem.equals("3")) {
				excluidoComSucesso();
			}
			if (mensagem.equals("4")) {
				mensagemDeSucessoClausulaTipo();
			}
			if (mensagem.equals("5")) {
				mensagemDeErroClausulaTipo();
			}

		}
		this.mensagem = null;

	}

	@SuppressWarnings("unchecked")
	private void loadNos() {

		usuarioLogado();

		if (usuarioLogado.getIdPerfilusuario().isEhAdmEntidade()) {

			getHashParametro().put("idEntidadeEc", usuarioLogado.getIdEntidadeEc().getIdEntidadeEc());

			nosPaiList = (List<Tbnos>) (Object) service.list(FIND_TBNO_BY_ENTIDADE, hashParametro);

			nosList = (List<Tbnos>) (Object) service.list(FIND_TBNO_BY_ENTIDADE, hashParametro);

			if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
				for (int i = 0; i < nosPaiList.size(); i++) {
					if (nosPaiList.get(i).getIdClausulaTipo() != null) {
						nosPaiList.get(i).getIdClausulaTipo().setName(validarClasula(nosPaiList.get(i).getIdClausulaTipo().getTxEspanhol()));
					}
				}
				for (int i = 0; i < nosList.size(); i++) {
					if (nosList.get(i).getIdClausulaTipo() != null) {
						nosList.get(i).getIdClausulaTipo().setName(validarClasula(nosList.get(i).getIdClausulaTipo().getTxEspanhol()));
					}
				}

			} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
				for (int i = 0; i < nosPaiList.size(); i++) {
					if (nosPaiList.get(i).getIdClausulaTipo() != null) {
						nosPaiList.get(i).getIdClausulaTipo().setName(validarClasula(nosPaiList.get(i).getIdClausulaTipo().getTxIngles()));

					}
				}

				for (int i = 0; i < nosList.size(); i++) {
					if (nosList.get(i).getIdClausulaTipo() != null) {
						nosList.get(i).getIdClausulaTipo().setName(validarClasula(nosList.get(i).getIdClausulaTipo().getTxIngles()));
					}
				}
			} else {
				for (int i = 0; i < nosPaiList.size(); i++) {
					if (nosPaiList.get(i).getIdClausulaTipo() != null) {
						nosPaiList.get(i).getIdClausulaTipo().setName(validarClasula(nosPaiList.get(i).getIdClausulaTipo().getTxPortugues()));

					}
				}

				for (int i = 0; i < nosList.size(); i++) {
					if (nosList.get(i).getIdClausulaTipo() != null) {
						nosList.get(i).getIdClausulaTipo().setName(validarClasula(nosList.get(i).getIdClausulaTipo().getTxPortugues()));

					}
				}
			}

		} else {
			nosPaiList = (List<Tbnos>) (Object) service.listAll(FIND_ALL_NOS);

			nosList = (List<Tbnos>) (Object) service.listAll(FIND_ALL_NOS);
			if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
				for (int i = 0; i < nosPaiList.size(); i++) {
					if (nosPaiList.get(i).getIdClausulaTipo() != null) {
						nosPaiList.get(i).getIdClausulaTipo().setName(validarClasula(nosPaiList.get(i).getIdClausulaTipo().getTxEspanhol()));
					}
				}
				for (int i = 0; i < nosList.size(); i++) {
					if (nosList.get(i).getIdClausulaTipo() != null) {
						nosList.get(i).getIdClausulaTipo().setName(validarClasula(nosList.get(i).getIdClausulaTipo().getTxEspanhol()));
					}
				}

			} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
				for (int i = 0; i < nosPaiList.size(); i++) {
					if (nosPaiList.get(i).getIdClausulaTipo() != null) {
						nosPaiList.get(i).getIdClausulaTipo().setName(validarClasula(nosPaiList.get(i).getIdClausulaTipo().getTxIngles()));

					}
				}

				for (int i = 0; i < nosList.size(); i++) {
					if (nosList.get(i).getIdClausulaTipo() != null) {
						nosList.get(i).getIdClausulaTipo().setName(validarClasula(nosList.get(i).getIdClausulaTipo().getTxIngles()));
					}
				}

			} else {
				for (int i = 0; i < nosPaiList.size(); i++) {
					if (nosPaiList.get(i).getIdClausulaTipo() != null) {
						nosPaiList.get(i).getIdClausulaTipo().setName(validarClasula(nosPaiList.get(i).getIdClausulaTipo().getTxPortugues()));

					}
				}

				for (int i = 0; i < nosList.size(); i++) {
					if (nosList.get(i).getIdClausulaTipo() != null) {
						nosList.get(i).getIdClausulaTipo().setName(validarClasula(nosList.get(i).getIdClausulaTipo().getTxPortugues()));

					}
				}

			}
		}

	}

	private String validarClasula(String texto) {
		if (texto.length() > 100) {
			return texto.substring(0, 99) + "(...)";
		} else {
			return texto;
		}
	}

	private void usuarioLogado() {
		FacesContext fc = FacesContext.getCurrentInstance();

		session = (HttpSession) fc.getExternalContext().getSession(true);

		usuarioLogado = (Tbcadusuario) session.getAttribute("usuarioAutenticado");

	}

	public void createClausulaTipo() {

		try {

			usurario = service.findUserPorNome(auxUsuario);

			// 1 - Salvar cláusula

			if (newNos.getTxTituloPortugues() != null || newNos.getTxTituloEspanhol() != null || newNos.getTxTituloIngles() != null) {

				if (!(newNos.getTxTituloPortugues().isEmpty() || newNos.getTxTituloEspanhol().isEmpty() || newNos.getTxTituloIngles().isEmpty())) {

					if (newClausula.getTxPortugues() != null || newClausula.getTxEspanhol() != null || newClausula.getTxIngles() != null) {

						if (!(newClausula.getTxPortugues().isEmpty() || newClausula.getTxEspanhol().isEmpty() || newClausula.getTxIngles().isEmpty())) {

							service.save(newClausula);

							newNos.setIdClausulaTipo(newClausula);
							
							salvarFilhoMetodoIncluirFilho();

						} else {
							
							if((newClausula.getTxPortugues().isEmpty() && newClausula.getTxEspanhol().isEmpty() && newClausula.getTxIngles().isEmpty())){
								if (dirAux.isEmpty()) {
									preencherTodosOsCampos();
								} else {


									salvarFilhoMetodoIncluirFilho();
								}
							}else{
								preencherTodosOsCamposDaClausula();

							}

						}
					}else{
						if (dirAux.isEmpty()) {
							preencherTodosOsCampos();
						} else {

	

							salvarFilhoMetodoIncluirFilho();
						}
						
						
					}

					

				} else {
					preencherTodosOsCampos();
				}

			} else {
				preencherTodosOsCampos();

			}

		} catch (Exception e) {

			this.mensagem = "5";
			flush();
			EPECUtil.redirecionar("/epecWeb/pages/manterClausulaTipo.jsf");

		}

	}

	private void salvarFilhoMetodoIncluirFilho() {
		getHashParametro().put("idNo", Long.parseLong(dirAux));
		Tbnos newNo = (Tbnos) service.findOneResult(FIND_TBNOS_BY_ID, hashParametro);

		// 2.1 - e setar entidade do no de acordo com o pai
		newNos.setIdEntidadeEc(newNo.getIdEntidadeEc());

		service.save(newNos);

		// 3 - Criar Filho

		Tbfilhos novoFilho = new Tbfilhos();

		novoFilho.setIdPai(Long.parseLong(dirAux));
		novoFilho.setIdFilho(newNos.getIdNo());

		service.save(novoFilho);

		this.mensagem = "4";
		flush();
		EPECUtil.redirecionar("/epecWeb/pages/manterClausulaTipo.jsf");
	}

	public void updateClausulaTipo() {

		boolean update = false;

		externalContext = FacesContext.getCurrentInstance().getExternalContext();

		tipo = externalContext.getRequestParameterMap().get("tipo");

		if ("no".equals(tipo)) {

			if (noSelected.getTxTituloPortugues() != null || noSelected.getTxTituloEspanhol() != null || noSelected.getTxTituloIngles() != null) {

				if (!(noSelected.getTxTituloPortugues().isEmpty() || noSelected.getTxTituloEspanhol().isEmpty() || noSelected.getTxTituloIngles().isEmpty())) {
					service.update(noSelected);

					this.mensagem = "2";
					update = true;
				} else {
					update = false;
				}

			} else {
				update = false;
			}

		} else if ("pai".equals(tipo)) {

			if (noSelected.getIdTbnoPai().getTxTituloPortugues() != null || noSelected.getIdTbnoPai().getTxTituloEspanhol() != null || noSelected.getIdTbnoPai().getTxTituloIngles() != null) {

				if (!(noSelected.getIdTbnoPai().getTxTituloPortugues().isEmpty() || noSelected.getIdTbnoPai().getTxTituloEspanhol().isEmpty() || noSelected.getIdTbnoPai().getTxTituloIngles()
						.isEmpty())) {

					Tbnos updateNoPai = noSelected.getIdTbnoPai();
					service.update(updateNoPai);

					this.mensagem = "2";
					update = true;
				} else {
					update = false;
				}

			} else {
				update = false;

			}

		} else if ("clausula".equals(tipo)) {

			if (noSelected.getIdClausulaTipo().getTxPortugues() != null || noSelected.getIdClausulaTipo().getTxEspanhol() != null || noSelected.getIdClausulaTipo().getTxIngles() != null) {

				if (!(noSelected.getIdClausulaTipo().getTxPortugues().isEmpty() || noSelected.getIdClausulaTipo().getTxEspanhol().isEmpty() || noSelected.getIdClausulaTipo().getTxIngles().isEmpty())) {

					Tbclausulatipo updateClausulaTipo = noSelected.getIdClausulaTipo();

					service.update(updateClausulaTipo);

					this.mensagem = "2";
					update = true;
				} else {
					update = false;
				}

			} else {
				update = false;
			}

		}
		if (update) {
			EPECUtil.redirecionar("/epecWeb/pages/manterClausulaTipo.jsf");
		} else {
			preencherTodosOsCampos();

		}

	}

	public void removerClausulaTipo() {

		Tbclausulatipo removeClausulaTipo = noSelected.getIdClausulaTipo();

		noSelected.setIdClausulaTipo(null);
		service.update(noSelected);
		service.deleteClausulaTipo(removeClausulaTipo);

		this.mensagem = "3";

		EPECUtil.redirecionar("/epecWeb/pages/manterClausulaTipo.jsf");

	}

	public void removerNO() {
		condicao = true;

		ValidarNO(noSelected);

		if (condicao) {

			apagarNo(noSelected);

			this.mensagem = "3";

			EPECUtil.redirecionar("/epecWeb/pages/manterClausulaTipo.jsf");
		} else {

			noComClausulaTipo();
		}

	}

	private void ValidarNO(Tbnos no) {

		if (no.getIdClausulaTipo() != null) {
			condicao = false;
		}
		for (int i = 0; i < no.getTbnosList().size(); i++) {

			ValidarNO(no.getTbnosList().get(i));
		}
	}

	private void apagarNo(Tbnos no) {
		service.deleteTbno(no);

		for (int i = 0; i < no.getTbnosList().size(); i++) {
			apagarNo(no.getTbnosList().get(i));
		}

	}

	public void inserirClausulaTipoEmNoExistente() {

		if (newClausula.getTxPortugues() != null || newClausula.getTxEspanhol() != null || newClausula.getTxIngles() != null) {

			if (!(newClausula.getTxPortugues().isEmpty() || newClausula.getTxEspanhol().isEmpty() || newClausula.getTxIngles().isEmpty())) {

				auxUsuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
				usurario = service.findUserPorNome(auxUsuario);
				service.save(newClausula);
				noSelected.setIdClausulaTipo(newClausula);
				service.update(noSelected);

				this.mensagem = "1";

				EPECUtil.redirecionar("/epecWeb/pages/manterClausulaTipo.jsf");
				newClausula = new Tbclausulatipo();
			} else {
				preencherTodosOsCampos();

			}
		} else {
			preencherTodosOsCampos();
		}

	}

	public Tbclausulatipo getNewClausula() {
		return newClausula;
	}

	public void setNewClausula(Tbclausulatipo newClausula) {
		this.newClausula = newClausula;
	}

	public List<Tbclausulatipo> getClausulaTipoList() {
		return clausulaTipoList;
	}

	public void setClausulaTipoList(List<Tbclausulatipo> clausulaTipoList) {
		this.clausulaTipoList = clausulaTipoList;
	}

	public Tbnos getNewNos() {
		return newNos;
	}

	public void setNewNos(Tbnos newNos) {
		this.newNos = newNos;
	}

	public List<Tbnos> getNosList() {

		loadNos();
		return nosList;
	}

	public void setNosList(List<Tbnos> nosList) {
		this.nosList = nosList;
	}

	public List<Tbnos> getNosPaiList() {
		return nosPaiList;
	}

	public void setNosPaiList(List<Tbnos> nosPaiList) {
		this.nosPaiList = nosPaiList;
	}

	public void flush() {

		newNos = new Tbnos();
		newClausula = new Tbclausulatipo();
		loadNos();
	}

	public String getDirAux() {
		return dirAux;
	}

	public void setDirAux(String dirAux) {
		this.dirAux = dirAux;
	}

	public List<Tbcadusuario> getUsurario() {
		return usurario;
	}

	public void setUsurario(List<Tbcadusuario> usurario) {
		this.usurario = usurario;
	}

	public Tbnos getNoSelected() {
		return noSelected;
	}

	public void setNoSelected(Tbnos noSelected) {
		this.noSelected = noSelected;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Tbnos> getFilteredNosList() {
		return filteredNosList;
	}

	public void setFilteredNosList(List<Tbnos> filteredNosList) {
		this.filteredNosList = filteredNosList;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
