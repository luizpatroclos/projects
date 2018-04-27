package br.com.inpi.prosur.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 * @author ahlucena
 *
 */
public class FacesMessageUtil {

	private FacesContext facesContext;
	private Flash flash;
	private Mensagem msgProperties;
	
	private boolean isRedirect;
	
	public FacesMessageUtil(){}
	
	/**
	 * A mensagem será mantida para a próxima página carregada.
	 * @param isRedirect
	 */
	public FacesMessageUtil(boolean isRedirect){
		
		this.isRedirect = isRedirect;
	}
	
	public void mensagemSucesso(String mensagem){
		
		setMensagem(FacesMessage.SEVERITY_INFO, mensagem);
	}
	
	public void mensagemErro(String mensagem){
		
		setMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
	}
	
	public void mensagemAviso(String mensagem){
		
		setMensagem(FacesMessage.SEVERITY_WARN, mensagem);
	}
	
	public void setMensagem(Severity aviso, String mensagem){
		
		this.msgProperties = new Mensagem("resource.mensagens_pt_BR");
		this.facesContext = FacesContext.getCurrentInstance();
		
		if (isRedirect) {
			
			this.flash = this.facesContext.getExternalContext().getFlash();
			this.flash.setKeepMessages(true);
		}
		
		this.facesContext.addMessage("", new FacesMessage(aviso, this.msgProperties.retornaMensagem(mensagem), 
									 ""));
		this.facesContext.renderResponse();
	}
}
