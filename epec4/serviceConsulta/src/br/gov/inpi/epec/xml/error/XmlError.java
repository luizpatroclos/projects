package br.gov.inpi.epec.xml.error;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlRootElement(name="error")
@XmlType(propOrder={"codigo","mensagem"})
public class XmlError {

    
	private String codigo;
	
	
	private String mensagem;


	/**
	 * @return the codigo
	 */
	@XmlElement(name="code")
	public String getCodigo() {
		return codigo;
	}


	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	/**
	 * @return the mensagem
	 */
	@XmlElement(name="message")
	public String getMensagem() {
		return mensagem;
	}


	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "XmlErro [codigo=" + codigo + ", mensagem=" + mensagem + "]";
	}
	

	
	
}
