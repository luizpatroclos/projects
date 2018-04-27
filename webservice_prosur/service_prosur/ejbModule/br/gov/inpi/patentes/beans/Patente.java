package br.gov.inpi.patentes.beans;


import org.prosur.catalog.ws.InventionPatent;

public class Patente {
	
	
	private InventionPatent inventionPatent;
	
	
	private Integer codigoPedido;


	public InventionPatent getInventionPatent() {
		return inventionPatent;
	}


	public void setInventionPatent(InventionPatent inventionPatent) {
		this.inventionPatent = inventionPatent;
	}


	public Integer getCodigoPedido() {
		return codigoPedido;
	}


	public void setCodigoPedido(Integer codigoPedido) {
		this.codigoPedido = codigoPedido;
	}


	@Override
	public String toString() {
		return "Patente [inventionPatent=" + inventionPatent
				+ ", codigoPedido=" + codigoPedido + "]";
	}
	
	
	
	     
	       
       

}
