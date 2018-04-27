package br.gov.inpi.marcas.beans;

import javax.persistence.Column;
import javax.persistence.Id;

public abstract class IdentificadorProsur {
	
	@Id
	@Column(name="cd_processo")
	private String idPedido;

	public String getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}
	
	

}
