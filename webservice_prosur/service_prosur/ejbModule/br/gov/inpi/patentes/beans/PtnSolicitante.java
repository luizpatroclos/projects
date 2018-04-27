package br.gov.inpi.patentes.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;



/**
 * 
 * POJO - Encapsula os dados de um solcintante.
 * 
 * @author tgouvea
 *
 */
@Entity
public class PtnSolicitante {
	
	@Id
	@Column(name="cod_pessoa")
	private Integer codigoPessoa;
	
	@Column(name="nome_completo")
	private String nome;
	
	@Column(name="cod_pais")
	private String paisOrigem;
	
	@Column(name="ordem_importancia")
	private Integer ordemImportancia;
	
	public PtnSolicitante(){}
	
	public PtnSolicitante(String nome, String paisOrigem) {
		super();
		this.nome = nome;
		this.paisOrigem = paisOrigem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Integer getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(Integer codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public Integer getOrdemImportancia() {
		return ordemImportancia;
	}

	public void setOrdemImportancia(Integer ordemImportancia) {
		this.ordemImportancia = ordemImportancia;
	}
	
	

}
