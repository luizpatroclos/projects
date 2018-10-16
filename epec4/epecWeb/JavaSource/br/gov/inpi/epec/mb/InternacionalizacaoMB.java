package br.gov.inpi.epec.mb;

import java.io.Serializable;
import java.util.Locale;

import javax.ejb.Init;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@SessionScoped
@ManagedBean
public class InternacionalizacaoMB extends AbstractMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String linguagem = "";
	private String pais = "";

	
	 private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

	
	
	public void mudarIdioma() {
		if (!"".equals(pais)) {
			this.mudarLocalidade(new Locale(linguagem, pais));
		} else {
			this.mudarLocalidade(new Locale(linguagem));
		}

	}
		
	public void mudarEnglish(){
		linguagem = "en";
		pais = "";
		locale = new Locale("en", "");
		this.idioma = "1";
		this.mudarLocalidade(locale);
	}
	
	public void mudarEspanhol(){
		linguagem = "es";
		pais = "";
		this.idioma = "2";
		locale = new Locale("es", "");
		this.mudarLocalidade(locale);
	}
	
	
	public void mudarPortugues(){
		
		locale =new Locale("pt_BR");
		this.idioma = "0";
		this.mudarLocalidade(locale);
	}

	private void mudarLocalidade(Locale locale) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	/** Metodos get e set */

	public String getLinguagem() {
		if(locale == null){
			locale = new Locale("pt_BR");
			mudarLocalidade(locale);
		}
		if(locale.getCountry().isEmpty()){
			return locale.getLanguage();
		}else{
			return locale.getLanguage() +"_"+locale.getCountry();
		}
		
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	@Init
	private void iniciar(){
		
	}


}
