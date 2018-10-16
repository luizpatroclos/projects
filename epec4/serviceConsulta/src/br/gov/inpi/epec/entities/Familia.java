package br.gov.inpi.epec.entities;

import java.util.Date;

import javax.xml.crypto.Data;

import com.sun.org.apache.regexp.internal.recompile;

public class Familia {

	private String id;

	private String publico;

	private Patente patente;

	private String isPrincipal;

	public String isPrincipal() {
		return isPrincipal;
	}

	public void setPrincipal(String isPrincipal) {
		this.isPrincipal = isPrincipal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPublico() {
		return publico;
	}

	public void setPublico(String publico) {
		this.publico = publico;
	}

	public Patente getPatente() {
		return patente;
	}

	public void setPatente(Patente patente) {
		this.patente = patente;
	}

	@Override
	public String toString() {
		return "Familia [id=" + id + ", publico=" + publico + ", patente=" + patente + ", isPrincipal=" + isPrincipal + "]";
	}
	public String getNumeroPublicacao(){
		return getPatente().getNumeroPublicacao();
	}
	public String getTitulo(){
		return getPatente().getTitulo();
	}
	public Date getDataDeposito(){
		return getPatente().getDataDeposito();
	}
	public Date getDataPublicacao(){
		return getPatente().getDataPublicacao();
	}
	public String getPais() {
		return getPatente().getPais();
		
	}

}
