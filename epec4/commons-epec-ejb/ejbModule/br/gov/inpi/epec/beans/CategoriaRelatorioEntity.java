package br.gov.inpi.epec.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class CategoriaRelatorioEntity {

	@Id
	@Column(name="ID_CARACCATRELATORIO")
	private long id;
	
	@Column(name="TX_CARACTERISTICA")
	private String caracteristica;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCaracteristica() {
		return caracteristica;
	}


	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}
	
	
	
	
	
}
