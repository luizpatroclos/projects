package br.gov.inpi.epec.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.facade.EpecServiceFacade;
import br.gov.inpi.epec.util.ConsultasEPEC;
import br.gov.inpi.epec.util.EPECUtil;

@ManagedBean
public class sessaoUsuarioMB implements Serializable {
	

	private static final long serialVersionUID = -1773948283255601577L;
	
	@EJB
	EpecServiceFacade service;
			
	private HttpSession session;
	
	@SuppressWarnings("unused")
	private String paginaAtual;
	
	@SuppressWarnings("unused")
	private String expirouSessao;
	
	private String login;
	
	private String sessao;
	
	
	 public void validaSessao(){
	    	
	    	FacesContext ctx = FacesContext.getCurrentInstance();
	    	
	    	session = (HttpSession) ctx.getExternalContext().getSession(true);
	    	
	    	Tbcadusuario usuario = (Tbcadusuario) session.getAttribute("usuarioAutenticado");
	    	 String usuarioVisitante = (String)session.getAttribute("usuario");
	    	
	    	if (usuario != null) {
			
	    	usuario.setBLogado(false);
	    	service.update(usuario);
	    	
			session = (HttpSession) ctx.getExternalContext().getSession(false);
			session.setAttribute(ConsultasEPEC.USUARIO, null);
			session.invalidate();
			EPECUtil.redirecionar(ConsultasEPEC.OUTCOME_SESSAO_EXPIRADA);
	    	
	    	}else if(usuarioVisitante != null){
	    		
	    		session = (HttpSession) ctx.getExternalContext().getSession(false);
				session.setAttribute(ConsultasEPEC.USUARIO, null);
				session.invalidate();
				EPECUtil.redirecionar(ConsultasEPEC.OUTCOME_SESSAO_EXPIRADA);
	    		
	    	}
	    }

	public String getPaginaAtual() {
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		
		// Obt�m a p�gina que atualmente est� interagindo com o ciclo
        String paginaAtual = ctx.getViewRoot().getViewId();
		
		return paginaAtual;
	}


	public String getExpirouSessao() {	
		return expirouSessao = "Página com mais de 30 min sem atividade.";
	}

	public HttpSession getSession() {
		return session;
	}

	public String getSessao() {
		return ConsultasEPEC.PAGINA_SESSAO_EXPIRADA_RELOAD;
	}

	public String getLogin() {
		return ConsultasEPEC.PAGINA_LOGIN;
	}

}
