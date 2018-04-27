package br.gov.inpi.patentes.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class DesignPatentLocarno {
	
	@Id
	@Column(name="cod_pedido")
	private Integer codigoPedido;
	
	
	@Column(name="cod_internacional")
	private String locarnoClassification;
	
	
	@Column(name="des_classificacao")
	private String designClassification;
	
	
	@Column(name="num_pedido")
	private String numeroPedido;
	

	public String getLocarnoClassification() {
		return locarnoClassification;
	}

	public void setLocarnoClassification(String locarnoClassification) {
		this.locarnoClassification = locarnoClassification;
	}

	public String getDesignClassification() {
		return designClassification;
	}

	public void setDesignClassification(String designClassification) {
		this.designClassification = designClassification;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	@Override
	public String toString() {
		return "DesignPatentLocarno [locarnoClassification=" + locarnoClassification
				+ ", designClassification=" + designClassification
				+ ", numeroPedido=" + numeroPedido + "]";
	}
	
	
	

	
	

}
