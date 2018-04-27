package br.gov.inpi.marcas.beans;

import org.prosur.catalog.ws.DistinctiveSign;

public class Marca {
	
		
	private DistinctiveSign distinctiveSign;
	
	
	private Integer codigoProcesso;
	

	public DistinctiveSign getDistinctiveSign() {
		return distinctiveSign;
	}

	public void setDistinctiveSign(DistinctiveSign distinctiveSign) {
		this.distinctiveSign = distinctiveSign;
	}

	public Integer getCodigoProcesso() {
		return codigoProcesso;
	}

	public void setCodigoProcesso(Integer codigoProcesso) {
		this.codigoProcesso = codigoProcesso;
	}

	@Override
	public String toString() {
		return "Marca [distinctiveSign=" + distinctiveSign
				+ ", codigoProcesso=" + codigoProcesso + "]";
	}
	
	
	

}
