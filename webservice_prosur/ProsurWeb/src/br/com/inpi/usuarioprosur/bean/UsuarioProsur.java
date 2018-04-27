package br.com.inpi.usuarioprosur.bean;

import java.io.Serializable;
import java.util.Collection;



public class UsuarioProsur implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String psw;
	private boolean autenticado;
	private Collection<String> urlPermitidas;
	
	public Collection<String> getUrlPermitidas() {
		return urlPermitidas;
	}
	public void setUrlPermitidas(Collection<String> urlPermitidas) {
		this.urlPermitidas = urlPermitidas;
	}
	public boolean isAutenticado() {
		return autenticado;
	}
	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	
	public boolean isUrlAutorizada(String url){
		
		boolean isAutorizado = false;
		
		if (this.urlPermitidas == null || this.urlPermitidas.isEmpty() ){
			
			isAutorizado = false;
			
		}else {
			
			for (String currentUrl : urlPermitidas){
				
				if (url.toUpperCase().contains((currentUrl.toUpperCase()))){
	
					isAutorizado = true; 
					break;
				}
				
			}
			
			
		}
		
		return isAutorizado;
		
	}
	

}

