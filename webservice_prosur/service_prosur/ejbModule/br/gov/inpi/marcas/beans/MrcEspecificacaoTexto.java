package br.gov.inpi.marcas.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity	
public class MrcEspecificacaoTexto {
	
	
	@Id
	@Column(name="cd_especif")
	private Integer cdEspecif;
	
	
	@Column(name="cd_Process")
	private Integer cdProcess;

	@Column(name="tx_especif")
	private String txEspecif;
	
	@Column(name="cd_nice")
	private Integer cdNice;

	public Integer getCdEspecif() {
		return cdEspecif;
	}

	public void setCdEspecif(Integer cdEspecif) {
		this.cdEspecif = cdEspecif;
	}

	public Integer getCdProcess() {
		return cdProcess;
	}

	public void setCdProcess(Integer cdProcess) {
		this.cdProcess = cdProcess;
	}

	public String getTxEspecif() {
		return txEspecif;
	}

	public void setTxEspecif(String txEspecif) {
		this.txEspecif = txEspecif;
	}

	public Integer getCdNice() {
		return cdNice;
	}

	public void setCdNice(Integer cdNice) {
		this.cdNice = cdNice;
	}
	
	
	
	
	
	

}
