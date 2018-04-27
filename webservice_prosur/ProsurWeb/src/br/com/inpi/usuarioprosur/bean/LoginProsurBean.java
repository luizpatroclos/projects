package br.com.inpi.usuarioprosur.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javax.naming.directory.DirContext;
import javax.servlet.http.HttpSession;

import br.com.inpi.prosur.util.ConsultasProsur;
import br.com.inpi.prosur.util.FacesMessageUtil;
import br.com.inpi.prosur.validacoes.ProsurUtil;
import br.gov.inpi.intercarga.beans.TbAcessoCarga;
import br.gov.inpi.services.EntityInterfaceIntercarga;

@ManagedBean
@SessionScoped
public class LoginProsurBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioProsur usuarioProsur = new UsuarioProsur();
	private HttpSession session;
	private List<TbAcessoCarga> listaAcessoCarga = null;
	
	public List<TbAcessoCarga> getListaAcessoCarga(){
		
		if (this.listaAcessoCarga == null || this.listaAcessoCarga.size() <= 0) {
			
			this.listaAcessoCarga = service.listaUsuarioProsur();
		}
		
		return this.listaAcessoCarga;
	}
	
	@EJB
	EntityInterfaceIntercarga service;
	
	@PostConstruct
	public void efetuarAutenficicacao() {

		String usuario = null;
		String senha = null;
		
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			if (fc.isPostback()) {
				
				usuario = usuarioProsur.getUsuario();
				senha = usuarioProsur.getPsw();
				boolean isUsuarioProsur = false;

				boolean isValidoLDAP = autenticarUsuarioLDAP(usuario, senha);
				
				for (TbAcessoCarga acessoCarga : this.getListaAcessoCarga()) {
					
					if (acessoCarga.getNmLogin().equals(usuario)) {
						
						isUsuarioProsur = true;
					}
				}

				if (isValidoLDAP) {
					
					if (isUsuarioProsur) {
					
						session = (HttpSession) fc.getExternalContext().getSession(true);

						usuarioProsur.setAutenticado(true);

						session.setAttribute("usuarioProsur", usuarioProsur);

						System.out.println("# Usuário " + usuario + " logado no Prosur");

						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
						Date date = new Date();

						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("data", dateFormat.format(date));
						
						ProsurUtil.redirecionar("agendamento.jsf");
					} else {
						
						new FacesMessageUtil().mensagemAviso("usuario_acesso_carga");
					}
				} else {

					new FacesMessageUtil().mensagemAviso("usuario_senha_invalido");
				}
			}
		} catch (Exception ex) {

			new FacesMessageUtil().mensagemErro("erro_sistema");
		}

	}

	private boolean autenticarUsuarioLDAP(final String login, final String senha) throws Exception {
		try {

			DirContext dirContext = br.com.inpi.prosur.util.ConnectServiceLDAP.connect(login, senha);

			if (dirContext == null) {
				return false;
			}

		} catch (Exception e) {
			throw e;
		}

		return true;
	}
	
	public void validarLogin(ComponentSystemEvent cse) {		
		

		FacesContext fc = FacesContext.getCurrentInstance();

		String msg = "";

		try {

			UIComponent components = cse.getComponent();

			if (((UIInput) components.findComponent("usuario")).getValue() == null
					|| ((UIInput) components.findComponent("password")).getValue() == null
					|| ((UIInput) components.findComponent("usuario")).getValue() == ""
					|| ((UIInput) components.findComponent("password")).getValue() == "") {
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

	public void encerraSessao() {

		FacesContext ctx = FacesContext.getCurrentInstance();
		session = (HttpSession) ctx.getExternalContext().getSession(false);
		session.setAttribute(ConsultasProsur.USUARIO, null);
		usuarioProsur.setAutenticado(false);
		session.invalidate();
		ProsurUtil.redirecionar("loginProsur.jsf");
	}

	public UsuarioProsur getUsuarioProsur() {
		return usuarioProsur;
	}

	public void setUsuarioProsur(UsuarioProsur usuarioProsur) {
		this.usuarioProsur = usuarioProsur;
	}

}

