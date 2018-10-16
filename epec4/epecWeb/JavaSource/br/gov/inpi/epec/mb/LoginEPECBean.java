package br.gov.inpi.epec.mb;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.gov.inpi.epec.beans.HistoricodeAcessoEntity;
import br.gov.inpi.epec.beans.Tbcadentidade;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbloglogin;
import br.gov.inpi.epec.beans.UsuarioEPEC;
import br.gov.inpi.epec.beans.mensagemEmail;
import br.gov.inpi.epec.facade.EpecServiceFacade;
import br.gov.inpi.epec.util.ConsultasEPEC;
import br.gov.inpi.epec.util.EPECUtil;
import br.gov.inpi.epec.util.EmailUtils;

@ManagedBean
@SessionScoped
public class LoginEPECBean extends AbstractMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private UsuarioEPEC usuarioEPEC = new UsuarioEPEC();
	private mensagemEmail msgEmail = new mensagemEmail();
	private HttpSession session;
	private String recuperaUser;
	private String recuperaEmail;
	private String isLogin = "0";
	private List<HistoricodeAcessoEntity> acessos;
	private EmailUtils emailUtils = new EmailUtils();
	String senha = null;
	String adm = null;
	Tbcadusuario usuarioTable;
	String usuario = null;

	@EJB
	EpecServiceFacade service;

	@PostConstruct
	public void efetuarAutenficicacao() {

		boolean validaSenha = false;

		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			usuario = usuarioEPEC.getUsuario();
			senha = usuarioEPEC.getPsw();
			usuarioTable = new Tbcadusuario();

			if (usuario != null) {

				usuarioTable = service.findUsuarioPorLogin(usuario);

				if (usuarioTable.getIdEntidadeEc().getIdAtivo() == 1) {

					if (usuarioTable.getIdAtivo() == 1) {
						if ((usuarioTable == null) || ("".equals(usuarioTable))) {
							clean();
							displayErrorMessageToUser("Usuario não Encontrado !");
						} else {
							validaSenha = autenticaUsuario(usuarioTable.getStrSenha());

							if (validaSenha) {

								session = (HttpSession) fc.getExternalContext().getSession(true);
								usuarioEPEC.setAutenticado(true);
								session.setAttribute(ConsultasEPEC.USUARIO, usuarioEPEC);
								FacesContext ctx = FacesContext.getCurrentInstance();
								session = (HttpSession) ctx.getExternalContext().getSession(false);

								usuarioTable.setBLogado(true);
								service.update(usuarioTable);

								salvarLog(fc);

								DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
								Date date = new Date();

								fc.getExternalContext().getSessionMap().put("usuario", usuario);
								fc.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioTable);
								fc.getExternalContext().getSessionMap().put("data", dateFormat.format(date));

								EPECUtil.redirecionar("home.jsf");

							} else {
								clean();

								if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
									displaySucessoMessageToUser("Error! Usuario o contraseña incorrecta.");
								} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
									displaySucessoMessageToUser("Error! User or incorrect password.");
								} else {
									displayErrorMessageToUser("Erro! Usuário ou senha incorreto.");
								}
							}

						}
					} else {
						if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
							displaySucessoMessageToUser("Error! El usuario está inactivo en el sistema de");
						} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
							displaySucessoMessageToUser("Error! The user entity is inactive in the system");
						} else {
							displayErrorMessageToUser("Erro! O usuário está inativo no sistema");
						}
					}
				} else {
					if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
						displaySucessoMessageToUser("Error! La entidad usuario está inactivo en el sistema de");
					} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
						displaySucessoMessageToUser("Error! The user is inactive in the system");
					} else {
						displayErrorMessageToUser("Erro! A entidade do usuário está inativa no sistema");
					}
				}

			}

		} catch (Exception e) {
			clean();
			displayErrorMessageToUser(" Atenção: Erro de Autenticação, Contactar a área de suporte !");
		}

	}

	private void salvarLog(FacesContext fc) {
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
		Tbloglogin login = new Tbloglogin();
		login.setStrUsuario(usuarioTable.getStrUsuario());
		login.setDtRegistro(new Date());
		login.setStrIp(request.getRemoteAddr());
		service.save(login);
	}

	public boolean autenticaUsuario(String usuarioTableSenha) {

		String pass = convertStringToMd5(senha);

		if (pass.contains(usuarioTableSenha)) {
			return true;
		} else {
			return false;
		}
	}

	public void validarLogin(ComponentSystemEvent cse) {

		FacesContext fc = FacesContext.getCurrentInstance();

		if ("0".equals(fc.getExternalContext().getRequestParameterMap().get("isLogin"))) {

			String msg = "";

			try {

				UIComponent components = cse.getComponent();

				if (((UIInput) components.findComponent("usuario")).getValue() == null || ((UIInput) components.findComponent("password")).getValue() == null
						|| ((UIInput) components.findComponent("usuario")).getValue() == "" || ((UIInput) components.findComponent("password")).getValue() == "") {
					throw new Exception("Informe o login e a senha.");
				}

			} catch (Exception e) {

				msg += e.getMessage();

			} finally {

				if (!msg.isEmpty()) {

					fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção: ", msg));
					fc.renderResponse();
				}
			}

		}

	}

	public void encerraSessao() {

		Tbcadusuario usuario = (Tbcadusuario) session.getAttribute("usuarioAutenticado");

		if (usuario != null) {

			usuario.setBLogado(false);
			usuario.setPublico(false);

			service.update(usuario);

		}

		FacesContext ctx = FacesContext.getCurrentInstance();
		session = (HttpSession) ctx.getExternalContext().getSession(false);
		session.setAttribute(ConsultasEPEC.USUARIO, null);
		usuarioEPEC.setAutenticado(false);
		session.invalidate();
		EPECUtil.redirecionar(ConsultasEPEC.OUTCOME_LOGIN);
	}

	public void recuperaSenha() {

		if (recuperaUser != null) {

			if (recuperaUser.isEmpty()) {
				if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
					displaySucessoMessageToUser("Usuario o e-mail no coincide con el registro en el e-PEC");
				} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
					displaySucessoMessageToUser("User or e-mail does not match the registration on the e-PEC");
				} else {
					displaySucessoMessageToUser("Usuário ou e-mail não confere com o cadastro no e-PEC");
				}
			} else {

				Tbcadusuario usuarioTable = service.findUsuarioPorLogin(recuperaUser);
				if(usuarioTable != null){
					if (usuarioTable.getStrEmail().equals(recuperaEmail)) {
						String novaSenha = gerarNovaSenha();

						String criptSenha = convertStringToMd5(novaSenha);

						usuarioTable.setStrSenha(criptSenha);

						service.update(usuarioTable);

						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						DateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
						Date date = new Date();

						String dd = dateFormat.format(date);
						String hh = dateFormat2.format(date);

						msgEmail.setDestino(usuarioTable.getStrEmail());
						msgEmail.setTitulo("Recuperação de senha e-PEC");
						msgEmail.setMensagem("Conforme a solicitação de recuperação da senha de acesso ao sistema e-PEC 4.0  na data " + dd + "\n" + " ás " + hh + " a nova senha do usuário "
								+ usuarioTable.getStrUsuario() + " é " + novaSenha + "." + "\n"
								+ "Para efetuar a alteração da senha acesse o menu 'Usuários' e em seguida a opção 'Dados Pessoais' logo após efetuar o acesso ao sistema." + "\n \n"
								+ " ATENÇÃO: Por favor não responda essa mensagem, esse é um e-mail automático do sistema e-PEC 4.0 para recuperação da senha de acesso.");
						emailUtils.sendMail(msgEmail);

						clean();

						if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
							displaySucessoMessageToUser("Una nueva contraseña ha sido enviada a la dirección de correo " + recuperaEmail);
						} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
							displaySucessoMessageToUser("A new password has been sent to the e-mail" + recuperaEmail);
						} else {
							displaySucessoMessageToUser("A nova senha foi enviada para o e-mail " + recuperaEmail);
						}

					} else {
						if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
							displaySucessoMessageToUser("Usuario o e-mail no coincide con el registro en el e-PEC");
						} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
							displaySucessoMessageToUser("User or e-mail does not match the registration on the e-PEC");
						} else {
							displaySucessoMessageToUser("Usuário ou e-mail não confere com o cadastro no e-PEC");
						}
					}
				}else{
					if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
						displaySucessoMessageToUser("Usuario o e-mail no coincide con el registro en el e-PEC");
					} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
						displaySucessoMessageToUser("User or e-mail does not match the registration on the e-PEC");
					} else {
						displaySucessoMessageToUser("Usuário ou e-mail não confere com o cadastro no e-PEC");
					}
				}

				
			}

		} else {
			if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
				displaySucessoMessageToUser("Usuario o e-mail no coincide con el registro en el e-PEC");
			} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
				displaySucessoMessageToUser("User or e-mail does not match the registration on the e-PEC");
			} else {
				displaySucessoMessageToUser("Usuário ou e-mail não confere com o cadastro no e-PEC");
			}

		}
	}

	public void sendRecuperarsenha() {

		FacesContext fc = FacesContext.getCurrentInstance();
		session = (HttpSession) fc.getExternalContext().getSession(true);
		fc.getExternalContext().getSessionMap().put("isRecuperaSenha", "1");

		EPECUtil.redirecionar("/epecWeb/pages/recuperarSenha.jsf");

	}

	public void acessoVisitante() {

		FacesContext fc = FacesContext.getCurrentInstance();

		session = (HttpSession) fc.getExternalContext().getSession(true);
		usuario = "Visitante";
		fc.getExternalContext().getSessionMap().put("isAdminstrador", "4");

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();

		fc.getExternalContext().getSessionMap().put("usuario", usuario);
		fc.getExternalContext().getSessionMap().put("data", dateFormat.format(date));

		EPECUtil.redirecionar("home.jsf");

	}

	public void sendLogin() {

		EPECUtil.redirecionar("/epecWeb/loginEPEC.jsf");

	}

	public UsuarioEPEC getUsuarioEPEC() {
		return usuarioEPEC;
	}

	public void setUsuarioEPEC(UsuarioEPEC usuarioEPEC) {
		this.usuarioEPEC = usuarioEPEC;
	}

	public String getRecuperaUser() {

		FacesContext fc = FacesContext.getCurrentInstance();
		session = (HttpSession) fc.getExternalContext().getSession(true);
		fc.getExternalContext().getSessionMap().put("isRecuperaSenha", "0");

		return recuperaUser;
	}

	public void setRecuperaUser(String recuperaUser) {
		this.recuperaUser = recuperaUser;
	}

	public String getRecuperaEmail() {
		return recuperaEmail;
	}

	public void setRecuperaEmail(String recuperaEmail) {
		this.recuperaEmail = recuperaEmail;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	@SuppressWarnings("unchecked")
	private void getAllLogsLoginHistorico() {
		this.acessos = (List<HistoricodeAcessoEntity>) (Object) service.findAllHistorico();
	}

	public List<HistoricodeAcessoEntity> getAcessos() {
		this.getAllLogsLoginHistorico();

		return acessos;
	}

	public void setAcessos(List<HistoricodeAcessoEntity> acessos) {
		this.acessos = acessos;
	}

	private String convertStringToMd5(String valor) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("MD5");

			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String gerarNovaSenha() {
		String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
				"y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "!", "@", "#", "$", "%", "&", "*", "." };

		String senha = "";

		for (int x = 0; x < 10; x++) {
			int j = (int) (Math.random() * carct.length);
			senha += carct[j];

		}

		return senha;
	}

	public void clean() {
		usuarioEPEC = new UsuarioEPEC();
		recuperaEmail = "";
		recuperaUser = "";
	}

}
