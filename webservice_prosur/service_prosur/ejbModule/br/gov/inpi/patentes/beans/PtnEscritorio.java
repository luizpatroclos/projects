package br.gov.inpi.patentes.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class PtnEscritorio {

		
	
	@Id
	@Column(name="cod_procurador")
	private Integer codigoProcurador;
	
	@Column(name="nome_completo")
	private String nomeCompleto;

	public Integer getCodigoProcurador() {
		return codigoProcurador;
	}

	public void setCodigoProcurador(Integer codigoProcurador) {
		this.codigoProcurador = codigoProcurador;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	
	
	
	
	
}
