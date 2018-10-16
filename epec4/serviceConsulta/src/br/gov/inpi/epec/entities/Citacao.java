package br.gov.inpi.epec.entities;

public class Citacao {

	private String text;
	
	private String docNumber;
	
	private String kind;
	
	private String categoria;
	
	private String pais;
	
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	@Override
	public String toString() {
		return "Citacao [text=" + text + ", docNumber=" + docNumber + ", kind=" + kind + ", categoria=" + categoria + "]";
	}
}
