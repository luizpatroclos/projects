package br.gov.inpi.patentes.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="ptn_pais_prosur")
@NamedQueries({
@NamedQuery(name="PtnPaisProsur.findAll",
			query="SELECT p FROM PtnPaisProsur p ")
})
public class PtnPaisProsur {
	
	@Id
	@Column(name="cod_pais")
	private String codPais;
	
	
	@Column(name="nome_pais")
	private String nomePais;


	public String getCodPais() {
		return codPais;
	}


	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}


	public String getNomePais() {
		return nomePais;
	}


	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}
	
	
	

}
