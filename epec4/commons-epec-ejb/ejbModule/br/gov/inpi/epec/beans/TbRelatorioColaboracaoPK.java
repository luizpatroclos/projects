package br.gov.inpi.epec.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TbRelatorioColaboracaoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID_RELATORIO_EC")
	private Long idRelatorio;

	@Column(name = "ID_COLABORACAO")
	private Long idColaboracao;

	public Long getIdRelatorio() {
		return idRelatorio;
	}

	public void setIdRelatorio(Long idRelatorio) {
		this.idRelatorio = idRelatorio;
	}

	public Long getIdColaboracao() {
		return idColaboracao;
	}

	public void setIdColaboracao(Long idColaboracao) {
		this.idColaboracao = idColaboracao;
	}



}
