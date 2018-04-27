package br.gov.inpi.intercarga.beans;

import java.util.Date;

public class AgendaCarga {

	private Integer idAgendaCarga;
	private Integer idTipoCarga;
	private String tipoCarga;
	private String tipoAgendamento;
	private Date dataHoraAgenda;
	private String statusAgenda;
	private String tipoBase;
	private String numRpi;
	private String qtdMarcas;
	private String qtdPatente;
	private String qtdDesenho;
	private String qtdProcesso;
	
	public String getTipoCarga() {
		return tipoCarga;
	}
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
	public String getTipoAgendamento() {
		return tipoAgendamento;
	}
	public void setTipoAgendamento(String tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}
	public Date getDataHoraAgenda() {
		return dataHoraAgenda;
	}
	public void setDataHoraAgenda(Date dataHoraAgenda) {
		this.dataHoraAgenda = dataHoraAgenda;
	}
	public String getStatusAgenda() {
		return statusAgenda;
	}
	public void setStatusAgenda(String statusAgenda) {
		this.statusAgenda = statusAgenda;
	}
	public String getTipoBase() {
		return tipoBase;
	}
	public void setTipoBase(String tipoBase) {
		this.tipoBase = tipoBase;
	}
	public String getNumRpi() {
		return numRpi;
	}
	public void setNumRpi(String numRpi) {
		this.numRpi = numRpi;
	}
	public String getQtdMarcas() {
		return qtdMarcas;
	}
	public void setQtdMarcas(String qtdMarcas) {
		this.qtdMarcas = qtdMarcas;
	}
	public String getQtdPatente() {
		return qtdPatente;
	}
	public void setQtdPatente(String qtdPatente) {
		this.qtdPatente = qtdPatente;
	}
	public String getQtdDesenho() {
		return qtdDesenho;
	}
	public void setQtdDesenho(String qtdDesenho) {
		this.qtdDesenho = qtdDesenho;
	}
	public Integer getIdAgendaCarga() {
		return idAgendaCarga;
	}
	public void setIdAgendaCarga(Integer idAgendaCarga) {
		this.idAgendaCarga = idAgendaCarga;
	}
	public Integer getIdTipoCarga() {
		return idTipoCarga;
	}
	public void setIdTipoCarga(Integer idTipoCarga) {
		this.idTipoCarga = idTipoCarga;
	}
	
	@Override
	public String toString() {
		return "AgendaCarga [idAgendaCarga=" + idAgendaCarga + ", idTipoCarga="
				+ idTipoCarga + ", tipoCarga=" + tipoCarga
				+ ", tipoAgendamento=" + tipoAgendamento + ", dataHoraAgenda="
				+ dataHoraAgenda + ", statusAgenda=" + statusAgenda
				+ ", tipoBase=" + tipoBase + ", numRpi=" + numRpi
				+ ", qtdMarcas=" + qtdMarcas + ", qtdPatente=" + qtdPatente
				+ ", qtdDesenho=" + qtdDesenho + "]";
	}
	
	public void setQtdProcesso(String qtdProcesso) {
		this.qtdProcesso = qtdProcesso;
	}
	public String getQtdProcesso() {
		return qtdProcesso;
	}
}
