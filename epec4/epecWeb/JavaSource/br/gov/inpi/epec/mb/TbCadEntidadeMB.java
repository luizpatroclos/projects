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
import javax.servlet.http.HttpSession;

import br.gov.inpi.epec.beans.Tbcadentidade;
import br.gov.inpi.epec.beans.Tbcadpais;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbclausulatipo;
import br.gov.inpi.epec.beans.Tbcolaboracaoentidade;
import br.gov.inpi.epec.beans.Tbnos;
import br.gov.inpi.epec.beans.Tbpatenteec;
import br.gov.inpi.epec.facade.EpecServiceFacade;

@SessionScoped
@ManagedBean
public class TbCadEntidadeMB extends AbstractMB implements Serializable {

	private static final long serialVersionUID = -3094381938450502330L;

	@EJB
	EpecServiceFacade service;

	private String console;

	private List<Tbcadentidade> entidades;
	private Tbcadentidade entidade = new Tbcadentidade();
	private List<Tbcadentidade> selectedEntidades = new ArrayList<>();
	private Tbcadentidade entidadeSelected;
	private String[] selectedStatus;
	private List<String> escolherStatus;
	private Tbcadentidade selectedEntidade;
	private List<Tbcadentidade> escolherEntidade;
	private List<Tbcadpais> paises;

	private HttpSession session;
	public Tbcadusuario usuarioLogado;
	
	public boolean acesso;

	
	
	private String txDescricao;

	public String getTxDescricao() {
		return txDescricao;
	}

	public void setTxDescricao(String txDescricao) {
		this.txDescricao = txDescricao;
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

	public void carregarEntidade() {
		System.out.println(entidadeSelected);

	}

	private void obterUsuarioSessao() {
		FacesContext fc = FacesContext.getCurrentInstance();

		session = (HttpSession) fc.getExternalContext().getSession(true);

		usuarioLogado = (Tbcadusuario) session.getAttribute("usuarioAutenticado");
	}

	@PostConstruct
	public void init() {
		escolherStatus = new ArrayList<String>();

		escolherStatus.add("Ativo");
		escolherStatus.add("Inativo");

		escolherEntidade = new ArrayList<Tbcadentidade>();

		loadEntidade();
		escolherEntidade.addAll(entidades);
		obterUsuarioSessao();
	}

	public Tbcadentidade getEntidade() {
		return entidade;
	}

	public List<Tbcadentidade> getEntidadeListar() {

		loadEntidade();
		List<Tbcadentidade> entidadesTeste = new ArrayList<>();

		if (usuarioLogado.getIdPerfilusuario().isEhAdmEntidade()) {
			for (int i = 0; i < entidades.size(); i++) {
				if (usuarioLogado.getIdEntidadeEc().getOficina()) {
					if (usuarioLogado.getIdEntidadeEc().getIdPais().equals(entidades.get(i).getIdPais())) {
						entidadesTeste.add(entidades.get(i));
					}

				} else {
					if (usuarioLogado.getIdEntidadeEc().equals(entidades.get(i))) {
						entidadesTeste.add(entidades.get(i));
					}
				}

			}
			
			return entidadesTeste;
		} else {
			return entidades;

		}

	}
	
	
	@SuppressWarnings("unchecked")
	public List<Tbcadpais> getPaises(){
		List<Tbcadpais> paises = (List<Tbcadpais>) (Object) service.listAll(FIND_ALL_PAIS);

		List<Tbcadpais> paisesTeste = new ArrayList<>();
		if (usuarioLogado.getIdPerfilusuario().isEhAdmEntidade()) {
			paisesTeste.add(usuarioLogado.getIdEntidadeEc().getIdPais());
			
			return paisesTeste;
		}else{
			return paises;
		}
		
	
	}

	@SuppressWarnings("unchecked")
	private void loadEntidade() {

		entidades = (List<Tbcadentidade>) (Object) service.listAll(FIND_ALL_ENTIDADE);

		for (int i = 0; i < entidades.size(); i++) {
			if (entidades.get(i).getIdAtivo() == 1) {
				entidades.get(i).setTextoAtivo("Ativo");
			} else {
				entidades.get(i).setTextoAtivo("Inativo");
			}
		}
	}

	public void createEntidade() {
		entidade.setTxDescricao(entidade.getTxOrganizacao());

		if ((!usuarioLogado.getIdEntidadeEc().getOficina()) && usuarioLogado.getIdPerfilusuario().isEhAdmEntidade()) {

			oUsuarioLogadoNaoPodeRealizarTalOperacao();
		} else {
			if (entidade.getTxOrganizacao().trim().isEmpty() || entidade.getNomePais().isEmpty() || entidade.getTxDescricao().trim().isEmpty()) {

				dadosIncorretosVerifique();
				flush();
			} else if (console.isEmpty()) {

				dadosIncorretosVerifique();
				flush();
			} else {
				if (verificarNomeDuplicado()) {
					if (console.equals("1")) {
						entidade.setOficina(true);
					} else {
						entidade.setOficina(false);
					}

					Tbcadpais tbcadpais = null;

					tbcadpais = service.findPaisPorNome(entidade.getNomePais());

					entidade.setIdPais(tbcadpais);
					entidade.setIdAtivo((short) 1);

					getHashParametro().put("idPais", tbcadpais.getIdPais());

					@SuppressWarnings("unchecked")
					List<Tbcadentidade> entidadesPais = (List<Tbcadentidade>) (Object) service.list(FIND_ALL_ENTIDADE_PAIS, hashParametro);

					if (usuarioLogado.getIdPerfilusuario().isEhAdmEntidade()) {
						if (usuarioLogado.getIdEntidadeEc().getIdPais().equals(tbcadpais)) {

							if (validoEscritorioPatente(entidade, entidadesPais, false)) {
								service.save(entidade);
								
								Tbnos tbnos = new Tbnos();
								tbnos.setTxTituloPortugues("Raiz_"+entidade.getTxOrganizacao());
								tbnos.setTxTituloEspanhol("Raiz_"+entidade.getTxOrganizacao());
								tbnos.setTxTituloIngles("Root_"+entidade.getTxOrganizacao());
								tbnos.setIdEntidadeEc(entidade);
								service.save(tbnos);
								
								incluidoComSucesso();

							} else {
								soUmaEntidadeMesmoPaisPodeSerEscritorio();
							}

						} else {
							
							soPodeCriarEntidadeParaoPais(usuarioLogado.getIdEntidadeEc().getIdPais().getStrNomePais());
							
						}

					} else {

						if (validoEscritorioPatente(entidade, entidadesPais, false)) {
							service.save(entidade);
							Tbnos tbnos = new Tbnos();
							tbnos.setTxTituloPortugues("Raiz_"+entidade.getTxOrganizacao());
							tbnos.setTxTituloEspanhol("Raiz_"+entidade.getTxOrganizacao());
							tbnos.setTxTituloIngles("Root_"+entidade.getTxOrganizacao());
							tbnos.setIdEntidadeEc(entidade);
							service.save(tbnos);
							incluidoComSucesso();

						} else {

							soUmaEntidadeMesmoPaisPodeSerEscritorio();

						}
					}

					flush();

				} else {

					entidadeJaExiste(entidade.getTxOrganizacao());
					
					flush();
				}
			}

		}

	}

	private boolean validoEscritorioPatente(Tbcadentidade entidade2, List<Tbcadentidade> entidadesPais, boolean alterar) {
		if (entidade2.getOficina() == false) {
			return true;
		} else {
			for (int i = 0; i < entidadesPais.size(); i++) {
				if (entidadesPais.get(i).getOficina() == true) {
					if(alterar){
						if(entidadesPais.get(i).equals(entidade2)){
							return true;
						}else{
							return false;

						}
					}else{
						return false;
					}
				}
			}
			return true;
		}

	}
	

	

	public void updateEntidade() {

		if (usuarioLogado.getIdPerfilusuario().isEhAdmEntidade()) {

			if (usuarioLogado.getIdEntidadeEc().getOficina()) {
				if (entidadeSelected.getIdPais().equals(usuarioLogado.getIdEntidadeEc().getIdPais())) {
					alterarEntidade();
				} else {					
					soPodeAlterarParaoPais(usuarioLogado.getIdEntidadeEc().getIdPais().getStrNomePais());					
				}

			} else {
				if (entidadeSelected.equals(usuarioLogado.getIdEntidadeEc())) {
					alterarEntidade();
				} else {
					operacaoNaoPodeSerRealizada();
				}
			}
		} else {
			alterarEntidade();
		}
	}

	private void alterarEntidade() {

		Tbcadentidade auxEntidade = entidadeSelected;

		getHashParametro().put("idPais", auxEntidade.getIdPais().getIdPais());

		@SuppressWarnings("unchecked")
		List<Tbcadentidade> entidadesPais = (List<Tbcadentidade>) (Object) service.list(FIND_ALL_ENTIDADE_PAIS, hashParametro);

		console = entidadeSelected.getBoescritorioPatente();

		if (console.equals("1")) {
			auxEntidade.setOficina(true);
		} else {
			auxEntidade.setOficina(false);
		}

		if ("Inativo".equals(entidadeSelected.getTextoAtivo())) {

			auxEntidade.setIdAtivo((short) 0);

		} else {
			auxEntidade.setIdAtivo((short) 1);
		}

		if (validoEscritorioPatente(auxEntidade, entidadesPais, true)) {
			service.update(auxEntidade);

			alteradoComSucesso();
		} else {
			soUmaEntidadeMesmoPaisPodeSerEscritorio();
		}

		flush();
	}

	@SuppressWarnings("unchecked")
	public void excluirEntidade() {
		getHashParametro().put("idEntidadeEc", entidadeSelected.getIdEntidadeEc());

		List<Tbpatenteec> patentes = (List<Tbpatenteec>) (Object) service.list(FIND_PATENTE_BY_ENTIDADE, hashParametro);

		List<Tbcadusuario> usuarios = (List<Tbcadusuario>) (Object) service.list(FIND_USUARIO_BY_ENTIDADE, hashParametro);

		List<Tbcolaboracaoentidade> colaboracao = (List<Tbcolaboracaoentidade>) (Object) service.list(FIND_COLABORACAO_ENTIDADE_02, hashParametro);

		getHashParametro().put("idEntidadeEc", entidadeSelected.getIdEntidadeEc());

		List<Tbnos> nos = (List<Tbnos>) (Object) service.list(FIND_TBNO_BY_ENTIDADE, hashParametro);
		
		
		if (patentes.size() == 0 && usuarios.size() == 0 && colaboracao.size() == 0 && nos.size() == 1) {
	
								
			service.deleteTbno(nos.get(0));		
			
			
			service.apagar(entidadeSelected);
				
			
			loadEntidade();

			excluidoComSucesso();

		} else {
			operacaoNaoPodeSerRealizada();
		}

	}

	public Tbcadentidade getEntidadeSelected() {
		return entidadeSelected;
	}

	public void setEntidadeSelected(Tbcadentidade entidadeSelected) {
		this.entidadeSelected = entidadeSelected;
	}

	public String[] getSelectedPaises() {
		return selectedStatus;
	}

	public void setSelectedStatus(String[] selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	public List<String> getEscolherStatus() {
		return escolherStatus;
	}

	public Tbcadentidade getSelectedEntidade() {
		return selectedEntidade;
	}

	public void setSelectedEntidade(Tbcadentidade selectedEntidade) {
		this.selectedEntidade = selectedEntidade;
	}

	public List<Tbcadentidade> getEscolherEntidade() {
		return escolherEntidade;
	}

	public List<Tbcadentidade> getSelectedEntidades() {
		return selectedEntidades;
	}

	public void setSelectedEntidades(List<Tbcadentidade> selectedEntidades) {
		this.selectedEntidades = selectedEntidades;
	}

	@SuppressWarnings("unchecked")
	private boolean verificarNomeDuplicado() {
		boolean verificarNomeDuplicado = true;
		entidades = (List<Tbcadentidade>) (Object) service.listAll(FIND_ALL_ENTIDADE);
		for (int i = 0; i < entidades.size(); i++) {
			if (entidades.get(i).getTxOrganizacao().toUpperCase().equals(entidade.getTxOrganizacao().toUpperCase())) {
				verificarNomeDuplicado = false;
			}

		}

		if (entidade.getTxOrganizacao().isEmpty()) {
			verificarNomeDuplicado = false;
		}

		return verificarNomeDuplicado;
	}

	public void flush() {
		entidade = new Tbcadentidade();
		loadEntidade();
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public void setPaises(List<Tbcadpais> paises) {
		this.paises = paises;
	}

	public boolean isAcesso() {
		getUsuarioLogado();	
		
		if(usuarioLogado.getIdPerfilusuario().isEhAdmSistema()){
			return true;
		}else if(usuarioLogado.getIdEntidadeEc().getOficina()){
			return true;
		}
		return false;
	}

	public void setAcesso(boolean acesso) {
		this.acesso = acesso;
	}


}
