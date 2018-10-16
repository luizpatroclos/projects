package br.gov.inpi.epec.filter;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.FactoryFinder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.mb.AbstractMB;
import br.gov.inpi.epec.util.ConsultasEPEC;

public class FaseListener extends AbstractMB implements PhaseListener {
	 
    private static final long serialVersionUID = 1L;
    
   
	
   
    public FaseListener() {
    }
 
    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext fc = event.getFacesContext();
        ExternalContext ec = fc.getExternalContext();
        
        // Obt�m a p�gina que atualmente est� interagindo com o ciclo
        String paginaAtual = fc.getViewRoot().getViewId();
        
     // Se for a p�gina 'loginSPN.xhtml' seta a vari�vel como true
        boolean isLoginPage = paginaAtual.lastIndexOf(ConsultasEPEC.PAGINA_LOGIN) > -1;
 
 
        if (paginaAtual != null) {
            try {
 
                // fix for renderer kit (Mojarra's and PrimeFaces's ajax redirect)
                if ((RequestContext.getCurrentInstance().isAjaxRequest() || fc.getPartialViewContext().isPartialRequest()) && fc.getResponseWriter() == null && fc.getRenderKit() == null) {
                    ServletResponse response = (ServletResponse) ec.getResponse();
                    ServletRequest request = (ServletRequest) ec.getRequest();
                    response.setCharacterEncoding(request.getCharacterEncoding());
 
                    RenderKitFactory factory = (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
                    RenderKit renderKit = factory.getRenderKit(fc, fc.getApplication().getViewHandler().calculateRenderKitId(fc));
 
                    ResponseWriter responseWriter = renderKit.createResponseWriter(response.getWriter(), null, request.getCharacterEncoding());
                    fc.setResponseWriter(responseWriter);
                }
                HttpSession session = (HttpSession) ec.getSession(true);
                
                /* Aqui recuperamos o usuário autenticado setado no MB autenticacaoController */
                Tbcadusuario usuario = (Tbcadusuario) session.getAttribute("usuarioAutenticado");
                
                String usuarioVisitante = (String)session.getAttribute("usuario");
                
                String isRecuperaSenha = (String) session.getAttribute("isRecuperaSenha");
                
                if (usuario != null && usuario.getIdPerfilusuario().getStrPerfil().equals(PERFIL_ADM_SISTEMAS)) {
                	
                	this.setIsAdminstrador("1");
                	
				}else if (usuario != null && usuario.getIdPerfilusuario().getStrPerfil().equals(PERFIL_ADM_ENTIDADES)) {
					
					this.setIsAdminstrador("2");
                	
				}else if (usuario != null && usuario.getIdPerfilusuario().getStrPerfil().equals(PERFIL_EXAMINADOR)) {
					
					this.setIsAdminstrador("3");
					
				}else if (usuarioVisitante != null && usuarioVisitante.equals(PERFIL_VISITANTE)) {
					
					this.setIsAdminstrador("4");
				}
                
                ec.getApplicationMap().put("isAdminstrador", isAdminstrador);
                
                // Verifica se o usu�rio est� logado e se n�o est� na p�gina de login
                if ((!isLoginPage && usuario == null && !"1".equals(isRecuperaSenha) && !"4".equals(isAdminstrador)) && (!fc.getViewRoot().getViewId().contains(ConsultasEPEC.PAGINA_SESSAO_EXPIRADA_RELOAD) && !fc.getViewRoot().getViewId().contains(ConsultasEPEC.PAGINA_RECUPERA_SENHA) && !fc.getViewRoot().getViewId().contains(ConsultasEPEC.PAGINA_LOGIN))) {
                	
                	ec.redirect(ec.getRequestContextPath()  + ConsultasEPEC.PAGINA_SESSAO_EXPIRADA);
                	
                } else if ((isLoginPage && usuario != null) && usuario.getIdPerfilusuario().getStrPerfil().equals(PERFIL_EXAMINADOR)) {
        	
                		fc.getApplication().getNavigationHandler().handleNavigation(fc, null, ConsultasEPEC.PAGINA_HOME);
                	
                  } 
                
                
                if ((usuario != null && usuario.getIdPerfilusuario().getStrPerfil().equals(PERFIL_ADM_ENTIDADES)) && (fc.getViewRoot().getViewId().contains(ConsultasEPEC.PAGINA_PAIS))) {
                	
                	ec.redirect(ec.getRequestContextPath()  + ConsultasEPEC.PAGINA_HOME);
					
				}
 
            } catch (IOException e) {
                System.out.println("Redirect to the specified login page " + ConsultasEPEC.PAGINA_LOGIN + " failed");
                throw new FacesException(e);
            }
        }
    }
 
    @Override
    public void beforePhase(PhaseEvent event) {
    }
 
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
   
}
