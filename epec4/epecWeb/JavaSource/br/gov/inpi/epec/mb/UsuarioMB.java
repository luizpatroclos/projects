package br.gov.inpi.epec.mb;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.gov.inpi.epec.beans.Tbcadcolaboracao;
import br.gov.inpi.epec.beans.Tbcadentidade;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbperfilusuario;
import br.gov.inpi.epec.facade.EpecServiceFacade;
import br.gov.inpi.epec.util.EPECUtil;
import br.gov.inpi.epec.util.I18N;

@SessionScoped
@ManagedBean
public class UsuarioMB extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@EJB
	private EpecServiceFacade service;

	private Tbcadusuario newUser = new Tbcadusuario();

	private Long idUsuarioSelecionado;

	private String idPerfilUsaurio;

	private String idPerfilUsaurioSelecionado;

	private String idOrganizacao;

	private String confirmacaoSenha;

	private String trocarSenhaAntiga;

	private String trocarSenhaNova;

	private List<Tbcadcolaboracao> colaboracoes;

	private List<Tbcadusuario> usurario;

	private List<Tbcadusuario> usurarios;

	private List<Tbcadusuario> usurariosLogados;

	private List<Tbcadentidade> entidades;

	private Tbcadusuario usuarioSelecionado;

	private String login;

	private String pais;

	private String organizacao;

	private String privilegio;

	private String situacao;

	private String statusSelecionado;

	private String isStatusAtivo;

	private String perfilPublico;

	private String nomeSelecionado;

	private String desiginacaoSelecionado;

	private String emailSelecionado;

	private String telefone;

	private List<Tbcadusuario> usuarioFiltro;

	private boolean valorValido;

	private Tbcadusuario usuarioOnline;

	private String perfil;

	private String inserirUsuario;

	private String alterarUsuario;

	private HttpSession session;

	Locale localeES = new Locale("es", "");
	Locale localeEN = new Locale("en", "");

	public void mensagemInserirUsuario() {
		if (inserirUsuario != null){
			if (inserirUsuario.equals("1")) {

				incluidoComSucesso();

			}
		}

		this.inserirUsuario = null;
		
		
		if (alterarUsuario != null){
			if (alterarUsuario.equals("1")) {

				alteradoComSucesso();

			}
		}

		this.alterarUsuario = null;
	}


	public String getInserirUsuario() {
		return inserirUsuario;
	}

	public void setInserirUsuario(String inserirUsuario) {
		this.inserirUsuario = inserirUsuario;
	}

	public String getAlterarUsuario() {
		return alterarUsuario;
	}

	public void setAlterarUsuario(String alterarUsuario) {
		this.alterarUsuario = alterarUsuario;
	}

	public String getPerfil() {
		String auxUsuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

		Tbcadusuario tbCadusuario = this.service.findUsuarioPorLogin(auxUsuario);
		if (tbCadusuario != null) {

			perfil = tbCadusuario.getIdPerfilusuario().getIdPerfilusuario().toString();
		}

		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public Tbcadusuario getUsuarioOnline() {
		return usuarioOnline;
	}

	public void setUsuarioOnline(Tbcadusuario usuarioOnline) {
		this.usuarioOnline = usuarioOnline;
	}

	public boolean getValorValido() {
		return valorValido;
	}

	public void setValorValido(boolean valorValido) {
		this.valorValido = valorValido;
	}

	public String getNomeSelecionado() {
		return nomeSelecionado;
	}

	public void setNomeSelecionado(String nomeSelecionado) {
		this.nomeSelecionado = nomeSelecionado;
	}

	public String getDesiginacaoSelecionado() {
		return desiginacaoSelecionado;
	}

	public void setDesiginacaoSelecionado(String desiginacaoSelecionado) {
		this.desiginacaoSelecionado = desiginacaoSelecionado;
	}

	public String getEmailSelecionado() {
		return emailSelecionado;
	}

	public void setEmailSelecionado(String emailSelecionado) {
		this.emailSelecionado = emailSelecionado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Tbcadusuario> getUsurariosLogados() {
		return usurariosLogados;
	}

	public void setUsurariosLogados(List<Tbcadusuario> usurariosLogados) {
		this.usurariosLogados = usurariosLogados;
	}

	public String getPerfilPublico() {
		return perfilPublico;
	}

	public void setPerfilPublico(String perfilPublico) {
		this.perfilPublico = perfilPublico;
	}

	public String getIsStatusAtivo() {
		return isStatusAtivo;
	}

	public void setIsStatusAtivo(String isStatusAtivo) {
		this.isStatusAtivo = isStatusAtivo;
	}

	public String getStatusSelecionado() {
		return statusSelecionado;
	}

	public void setStatusSelecionado(String statusSelecionado) {
		this.statusSelecionado = statusSelecionado;
	}

	public String getIdPerfilUsaurioSelecionado() {
		return idPerfilUsaurioSelecionado;
	}

	public void setIdPerfilUsaurioSelecionado(String idPerfilUsaurioSelecionado) {
		this.idPerfilUsaurioSelecionado = idPerfilUsaurioSelecionado;
	}

	public String getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(String privilegio) {
		this.privilegio = privilegio;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getOrganizacao() {
		return organizacao;
	}

	public void setOrganizacao(String organizacao) {
		this.organizacao = organizacao;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Tbcadusuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Tbcadusuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public Long getIdUsuarioSelecionado() {
		return idUsuarioSelecionado;
	}

	public void setIdUsuarioSelecionado(Long idUsuarioSelecionado) {
		this.idUsuarioSelecionado = idUsuarioSelecionado;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public String getIdOrganizacao() {
		return idOrganizacao;
	}

	public void setIdOrganizacao(String idOrganizacao) {
		this.idOrganizacao = idOrganizacao;
	}

	public EpecServiceFacade getService() {
		return service;
	}

	public void setService(EpecServiceFacade service) {
		this.service = service;
	}

	public List<Tbcadcolaboracao> getColaboracoes() {
		return colaboracoes;
	}

	public void setColaboracoes(List<Tbcadcolaboracao> colaboracoes) {
		this.colaboracoes = colaboracoes;
	}

	public void setNewUser(Tbcadusuario newUser) {
		this.newUser = newUser;
	}

	public String getIdPerfilUsaurio() {
		if (idPerfilUsaurio == null) {
			idPerfilUsaurio = "1";
		}
		return idPerfilUsaurio;
	}

	public void setIdPerfilUsaurio(String idPerfilUsaurio) {
		this.idPerfilUsaurio = idPerfilUsaurio;
	}

	@SuppressWarnings("unchecked")
	private void loadManegerUsuarios() {

		String auxUsuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

		Tbcadusuario tbCadusuario = this.service.findUsuarioPorLogin(auxUsuario);
		if (tbCadusuario != null) {

			long idPerfil = tbCadusuario.getIdPerfilusuario().getIdPerfilusuario();
			// idPerfil = 3;
			if (idPerfil == 3L) {

				this.usurarios = (List<Tbcadusuario>) (Object) service.listAll(FIND_ALL_USERS);

			} else if (idPerfil == 2L) {
				
				usurarios = new ArrayList<>();

				if (tbCadusuario.getIdEntidadeEc().getOficina()) {

					List<Tbcadusuario> aux = this.service.findUserPorIdPais(tbCadusuario.getIdEntidadeEc().getIdPais().getIdPais());

					for (int i = 0; i < aux.size(); i++) {
						if (!(aux.get(i).getIdPerfilusuario().isEhAdmSistema())) {
							this.usurarios.add(aux.get(i));
						}

					}

				} else {

					List<Tbcadusuario> aux = this.service.findUserPorIdEntidade(tbCadusuario.getIdEntidadeEc().getIdEntidadeEc());

					for (int i = 0; i < aux.size(); i++) {
						if (!(aux.get(i).getIdPerfilusuario().isEhAdmSistema())) {
							this.usurarios.add(aux.get(i));
						}

					}

				}

			} else if (idPerfil == 1L) {

				this.usurarios = new ArrayList<Tbcadusuario>();
				this.usurarios.add(tbCadusuario);

			}

		}

	}

	private void loadUsuario() {
		String auxUsuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		this.usurario = this.service.findUserPorNome(auxUsuario);
	}

	public void createUser() {

		Tbperfilusuario idPerfilusuario = new Tbperfilusuario(this.obterIdPerfilUsuario(this.getIdPerfilUsaurio()));

		this.getNewUser().setIdUsuario(null);
		this.getNewUser().setIdPerfilusuario(idPerfilusuario);

		this.getNewUser().setIdAtivo((short) 1);

		boolean isValido = this.validarUsuarioNovo(this.getNewUser());
		if (isValido) {			
			
			Tbcadentidade tbcadentidade = this.pesquisarEntidade();
			if (tbcadentidade != null) {

				this.getNewUser().setIdEntidadeEc(tbcadentidade);
				this.getNewUser().setTxOrganizacao(tbcadentidade.getTxOrganizacao());

			}

			String strSenha = md5(getNewUser().getStrSenha());
			this.getNewUser().setStrSenha(strSenha);
			this.service.save(this.getNewUser());

			this.setNewUser(new Tbcadusuario());
			this.limpar();

			this.inserirUsuario = "1";

			EPECUtil.redirecionar("/epecWeb/pages/gerenciarUsuarios.jsf");
		}

	}

	public static String md5(String senha) {
		String sen = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
		sen = hash.toString(16);
		return sen;
	}

	private void limpar() {

		this.setConfirmacaoSenha(null);
		this.setIdOrganizacao(null);
		this.setIdPerfilUsaurio(null);
		this.setUsuarioSelecionado(null);
	}

	public void trocarSenhar() {
		EPECUtil.redirecionar("/epecWeb/pages/alterarSenha.jsf");

	}

	public void salvarNovaSenha() {

		FacesContext fc = FacesContext.getCurrentInstance();

		session = (HttpSession) fc.getExternalContext().getSession(true);

		usuarioSelecionado = (Tbcadusuario) session.getAttribute("usuarioAutenticado");

		String strSenha = md5(trocarSenhaAntiga);

		if (!(trocarSenhaAntiga == null || trocarSenhaNova == null || confirmacaoSenha == null)) {

			if (!(trocarSenhaAntiga.isEmpty() || trocarSenhaNova.isEmpty() || confirmacaoSenha.isEmpty())) {

				if (trocarSenhaNova.length() < 6) {
					minimoSeisCaracteres();
				} else {
					if (strSenha.equals(usuarioSelecionado.getStrSenha())) {

						if (confirmacaoSenha.equals(trocarSenhaNova)) {
							String strSenhaNova = md5(trocarSenhaNova);

							usuarioSelecionado.setStrSenha(strSenhaNova);

							service.update(usuarioSelecionado);

							this.alterarUsuario = "1";

							EPECUtil.redirecionar("/epecWeb/pages/manterUsuario.jsf");

						} else {
							confirmarSenhaDiferente();
						}

					} else {
						senhaIncorreta();

					}

				}

			} else {
				preencherTodosOsCampos();
			}
		} else {
			preencherTodosOsCampos();
		}

	}

	private Long obterIdPerfilUsuario(String idView) {

		Long id = null;
		if (idView != null) {
			id = Long.parseLong(idView);
		}

		return id;

	}

	private Tbcadentidade pesquisarEntidade() {

		Tbcadentidade tbCadentidade = null;
		tbCadentidade = this.getService().pesquisarOrganizacaoPorId(Long.parseLong(this.getIdOrganizacao()));
		return tbCadentidade;

	}

	private boolean validarUsuarioNovo(Tbcadusuario usuario) {

		boolean isValido = Boolean.TRUE;
		if (usuario.getStrUsuario() == null || "".equals(usuario.getStrUsuario().trim())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.usuario_obrigatorio")));
			isValido = Boolean.FALSE;
		} else {

			isValido = this.pesquisarUsuarioPorLogin(usuario.getStrUsuario());
			if (isValido) {

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.usuario_existente")));
				return isValido = Boolean.FALSE;

			} else {

				if (usuario.getStrSenha() == null || "".equals(usuario.getStrSenha().trim())) {

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.senha_obrigatorio")));
					return isValido = Boolean.FALSE;

				} else {

					int tamanho = usuario.getStrSenha().length();
					if (tamanho >= 6) {

						if (!this.getConfirmacaoSenha().equals(usuario.getStrSenha())) {

							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.senha_naoconfere")));
							return isValido = Boolean.FALSE;

						}

					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.senha_menor")));
						return isValido = Boolean.FALSE;
					}

				}



				if (usuario.getTxNome() == null || "".equals(usuario.getTxNome().trim())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.txnome_obrigatorio")));
					return isValido = Boolean.FALSE;
				}

				
				if(idOrganizacao == null || "".equals(idOrganizacao.trim())){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro",  I18N.getString("fatal.entidadeNaoSelecionada")));
					return isValido = Boolean.FALSE;
				}else if (idOrganizacao == "0") {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.entidadeNaoSelecionada")));
					return isValido = Boolean.FALSE;
				}else{
					usuario.setIdEntidadeEc(pesquisarEntidade());
				}
				


				if (usuario.getStrEmail() == null || "".equals(usuario.getStrEmail().trim())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.txemail_obrigatorio")));
					return isValido = Boolean.FALSE;
				} else {

					isValido = this.validarEmail(usuario.getStrEmail().trim());
					if (!isValido) {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.txemail_invalido")));
						return isValido = Boolean.FALSE;
					}

				}

				if (!(usuario.getIdEntidadeEc().getOficina())) {

					if (usuario.getIdPerfilusuario().isEhAdmSistema()) {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.usuario.sem.permissão")));
						return isValido = Boolean.FALSE;
					}
				}

				isValido = Boolean.TRUE;
			}
		}
		return isValido;
	}

	private boolean pesquisarUsuarioPorLogin(String login) {

		boolean isExistente = Boolean.FALSE;
		Tbcadusuario usuario = this.service.findUsuarioPorLogin(login);
		if (usuario != null) {
			isExistente = Boolean.TRUE;
		}
		return isExistente;

	}

	private boolean validarTelefoneNumerico(String numeroTelefone) {
		boolean isValido = Boolean.FALSE;
		Pattern pattern = Pattern.compile("^[0-9]");
		Matcher matcher = pattern.matcher(numeroTelefone);
		isValido = matcher.find();
		return isValido;
	}

	private boolean validarEmail(String email) {
		boolean isValido = Boolean.FALSE;
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		isValido = matcher.find();
		return isValido;
	}

	public List<Tbcadentidade> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<Tbcadentidade> entidades) {
		this.entidades = entidades;
	}

	public List<Tbcadusuario> getUsurario() {

		this.confirmacaoSenha = null;

		loadUsuario();
		
		return usurario;
	}

	public void setUsurario(List<Tbcadusuario> usurario) {
		this.usurario = usurario;
	}

	public List<Tbcadusuario> getUsurarios() {

		loadManegerUsuarios();

		return usurarios;
	}

	public void setUsurarios(List<Tbcadusuario> usurarios) {
		this.usurarios = usurarios;
	}

	public Tbcadusuario getNewUser() {
		return newUser;
	}

	public void carregarUsuario() {

		this.privilegio = this.getUsuarioSelecionado().getIdPerfilusuario().getStrPerfil();
		boolean isPublico = this.getUsuarioSelecionado().getPublico();
		if (isPublico) {
			this.situacao = "Público";
		} else {
			this.situacao = "Privado";
		}

		short idAtivo = this.getUsuarioSelecionado().getIdAtivo();
		if (idAtivo == 1) {
			this.statusSelecionado = "Ativo";
		} else {
			this.statusSelecionado = "Inativado";
		}
	}

	public void exibirDadosUsuarioSelecionado() {

		this.nomeSelecionado = this.getUsuarioSelecionado().getTxNome();
		this.desiginacaoSelecionado = this.getUsuarioSelecionado().getTxDesignacao();
		this.organizacao = this.getUsuarioSelecionado().getTxOrganizacao();
		this.emailSelecionado = this.getUsuarioSelecionado().getStrEmail();
		this.telefone = this.getUsuarioSelecionado().getStrTelefone();

		this.carregarUsuario();
		this.valorValido = true;

	}

	public void alterar() throws IOException {

		boolean isValido = this.validarAlteracaoDados(this.getUsuarioSelecionado());
		if (isValido) {

			this.service.merge(this.getUsuarioSelecionado());
			this.setUsuarioSelecionado(null);

			this.alterarUsuario = "1";

			FacesContext.getCurrentInstance().getExternalContext().redirect("/epecWeb/pages/gerenciarUsuarios.jsf");

		}

	}

	public void alterarDadosPessoais() throws IOException {

		boolean isValido = this.validarAlteracaoDados02(this.getUsuarioSelecionado());
		if (isValido) {

			//String strSenha = md5(getUsuarioSelecionado().getStrSenha());
			//this.getUsuarioSelecionado().setStrSenha(strSenha);

			this.service.merge(this.getUsuarioSelecionado());
			this.setUsuarioSelecionado(null);

			this.alterarUsuario = "1";

			FacesContext.getCurrentInstance().getExternalContext().redirect("/epecWeb/pages/manterUsuario.jsf");

		}

	}

	private short obterStatus(String idStatusAtivoView) {
		short i = 0;
		if ("true".equals(idStatusAtivoView)) {
			i = 1;
		} else if ("false".equals(idStatusAtivoView)) {
			i = 0;
		}
		return i;
	}

	private boolean validarAlteracaoDados(Tbcadusuario usuario) {

		boolean isValido = Boolean.TRUE;
		if (usuario.getStrTelefone() != null || !"".equals(usuario.getStrTelefone())) {

			/*
			 * isValido =
			 * this.validarTelefoneNumerico(usuario.getStrTelefone());
			 * if(!isValido){
			 * FacesContext.getCurrentInstance().addMessage(null,new
			 * FacesMessage("Erro", I18N.getString("fatal.telefoneinvalido")));
			 * return isValido = Boolean.FALSE; }
			 */

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.telefoneobrigatorio")));
			return isValido = Boolean.FALSE;
		}

		if (usuario.getTxNome() == null || "".equals(usuario.getTxNome().trim())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.txnome_obrigatorio")));
			return isValido = Boolean.FALSE;
		}

		if (usuario.getStrEmail() == null || "".equals(usuario.getStrEmail().trim())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.txemail_obrigatorio")));
			return isValido = Boolean.FALSE;
		} else {

			isValido = this.validarEmail(usuario.getStrEmail().trim());
			if (!isValido) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.txemail_invalido")));
				return isValido = Boolean.FALSE;
			}

		}

		if (!(usuario.getIdEntidadeEc().getOficina())) {

			if (usuario.getIdPerfilusuario().isEhAdmSistema()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.usuario.sem.permissão")));
				return isValido = Boolean.FALSE;
			}
		}

		return isValido;

	}

	private boolean validarAlteracaoDados02(Tbcadusuario usuario) {

		boolean isValido = Boolean.TRUE;
		if (usuario.getStrTelefone() != null || !"".equals(usuario.getStrTelefone())) {

			/*
			 * isValido =
			 * this.validarTelefoneNumerico(usuario.getStrTelefone());
			 * if(!isValido){
			 * FacesContext.getCurrentInstance().addMessage(null,new
			 * FacesMessage("Erro", I18N.getString("fatal.telefoneinvalido")));
			 * return isValido = Boolean.FALSE; }
			 */

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.telefoneobrigatorio")));
			return isValido = Boolean.FALSE;
		}

		if (usuario.getTxNome() == null || "".equals(usuario.getTxNome().trim())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.txnome_obrigatorio")));
			return isValido = Boolean.FALSE;
		}

		/*if (usuario.getStrSenha() == null || "".equals(usuario.getStrSenha().trim())) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.senha_obrigatorio")));
			return isValido = Boolean.FALSE;

		} else {

			int tamanho = usuario.getStrSenha().length();
			if (tamanho >= 6) {

				if (!this.getConfirmacaoSenha().equals(usuario.getStrSenha())) {

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.senha_naoconfere")));
					return isValido = Boolean.FALSE;

				}

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.senha_menor")));
				return isValido = Boolean.FALSE;
			}

		}*/

		if (usuario.getStrEmail() == null || "".equals(usuario.getStrEmail().trim())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.txemail_obrigatorio")));
			return isValido = Boolean.FALSE;
		} else {

			isValido = this.validarEmail(usuario.getStrEmail().trim());
			if (!isValido) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.txemail_invalido")));
				return isValido = Boolean.FALSE;
			}

		}

		if (!(usuario.getIdEntidadeEc().getOficina())) {

			if (usuario.getIdPerfilusuario().isEhAdmSistema()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", I18N.getString("fatal.usuario.sem.permissão")));
				return isValido = Boolean.FALSE;
			}
		}

		return isValido;

	}

	public void obterUsuariosOnline() {

		if (this.getUsurarios() != null && !this.getUsurarios().isEmpty()) {

			this.limparUsuarioSelecionado();
			this.usurariosLogados = new ArrayList<Tbcadusuario>();
			Iterator<Tbcadusuario> it = this.getUsurarios().iterator();
			while (it.hasNext()) {

				Tbcadusuario usuario = it.next();
				if (usuario.getBLogado() == true) {
					this.usurariosLogados.add(usuario);
				}

			}
			EPECUtil.redirecionar("/epecWeb/pages/usuarioOnline.jsf");
		}

	}

	public void limparUsuarioSelecionado() {

		this.setPrivilegio(null);
		this.setSituacao(null);
		this.setStatusSelecionado(null);
		this.setNomeSelecionado(null);
		this.setDesiginacaoSelecionado(null);
		this.setEmailSelecionado(null);
		this.setTelefone(null);

	}

	public List<Tbcadusuario> getUsuarioFiltro() {
		return usuarioFiltro;
	}

	public void setUsuarioFiltro(List<Tbcadusuario> usuarioFiltro) {
		this.usuarioFiltro = usuarioFiltro;
	}

	public String getTrocarSenhaAntiga() {
		return trocarSenhaAntiga;
	}

	public void setTrocarSenhaAntiga(String trocarSenhaAntiga) {
		this.trocarSenhaAntiga = trocarSenhaAntiga;
	}

	public String getTrocarSenhaNova() {
		return trocarSenhaNova;
	}

	public void setTrocarSenhaNova(String trocarSenhaNova) {
		this.trocarSenhaNova = trocarSenhaNova;
	}

}
