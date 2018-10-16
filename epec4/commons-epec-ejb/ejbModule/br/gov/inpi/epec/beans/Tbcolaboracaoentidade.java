/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author lasilva
 */
@Entity
@Table(name = "TBCOLABORACAOENTIDADE")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbcolaboracaoentidade.findByIdColaboracaoEntidade_1", query = "SELECT t FROM Tbcolaboracaoentidade t WHERE t.idColaboracao = :idColaboracao"),
		@NamedQuery(name = "Tbcolaboracaoentidade.findByIdColaboracaoEntidade_2", query = "SELECT t FROM Tbcolaboracaoentidade t WHERE t.idEntidadeEc = :idEntidadeEc") })
public class Tbcolaboracaoentidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COLABORACAO", nullable = false)
	private Long idColaboracao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ENTIDADE_EC", nullable = false)
	private Long idEntidadeEc;

	public Long getIdColaboracao() {
		return idColaboracao;
	}

	public void setIdColaboracao(Long idColaboracao) {
		this.idColaboracao = idColaboracao;
	}

	public Long getIdEntidadeEc() {
		return idEntidadeEc;
	}

	public void setIdEntidadeEc(Long idEntidadeEc) {
		this.idEntidadeEc = idEntidadeEc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idColaboracao == null) ? 0 : idColaboracao.hashCode());
		result = prime * result + ((idEntidadeEc == null) ? 0 : idEntidadeEc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tbcolaboracaoentidade other = (Tbcolaboracaoentidade) obj;
		if (idColaboracao == null) {
			if (other.idColaboracao != null)
				return false;
		} else if (!idColaboracao.equals(other.idColaboracao))
			return false;
		if (idEntidadeEc == null) {
			if (other.idEntidadeEc != null)
				return false;
		} else if (!idEntidadeEc.equals(other.idEntidadeEc))
			return false;
		return true;
	}

}
