package br.gov.inpi.patentes.beans;

import org.prosur.catalog.ws.DesignPatent;

/**
 * 
 * POJO - Classe responsável em encapsular um processo
 * de Desenho Industrial no formato utilizado pelo Prosur
 * assim como o seu código identificador referente a 
 * base de dados de dbptn "di_pedido"  
 * 
 * @author tgouvea
 *
 */
public class Desenho {

	private DesignPatent designPatent;
	
	private Integer codigoIdentificador;

	public DesignPatent getDesignPatent() {
		return designPatent;
	}

	public void setDesignPatent(DesignPatent designPatent) {
		this.designPatent = designPatent;
	}

	public Integer getCodigoIdentificador() {
		return codigoIdentificador;
	}

	public void setCodigoIdentificador(Integer codigoIdentificador) {
		this.codigoIdentificador = codigoIdentificador;
	}
	
	
	
	
}
