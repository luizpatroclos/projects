package br.gov.inpi.patentes.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class DiClassificacao {
	

	
	@Id
	@Column(name="locarno")
	private String codInternacional;

	public String getCodInternacional() {
		return codInternacional;
	}

	public void setCodInternacional(String codInternacional) {
		this.codInternacional = codInternacional;
	}


	
	
	
	
	
	

}
