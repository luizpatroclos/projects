package br.gov.inpi.epec.beans;

import java.io.Serializable;

public class mensagemEmail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2458810133710304344L;

	private String destino;
	private String titulo;
	private String mensagem;

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
