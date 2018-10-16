package br.gov.inpi.epec.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class JSFMessageUtil {
	

	
	public void sendInfoMessageToUser(String message, String tipo) {
		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO, message, tipo);
		addMessageToJsfContext(facesMessage);
	}

	public void sendErrorMessageToUser(String message, String tipo) {
		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_WARN, message, tipo);
		addMessageToJsfContext(facesMessage);
	}

	public void sendErrorFatalMessageToUser(String message, String tipo) {
		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_FATAL, message, tipo);
		addMessageToJsfContext(facesMessage);
	}

	private FacesMessage createMessage(Severity severity, String mensagemErro, String tipo) {
		return new FacesMessage(severity, tipo, mensagemErro);
	}

	private void addMessageToJsfContext(FacesMessage facesMessage) {
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}


}