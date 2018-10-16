/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "TBDEPOSITANTEPATENTE")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbdepositantepatente.findByIdDepositantePatente_1", query = "SELECT t FROM Tbdepositantepatente t WHERE t.idDepositante = :idDepositante"),
	 @NamedQuery(name = "Tbdepositantepatente.findByIdDepositantePatente_2", query = "SELECT t FROM Tbdepositantepatente t WHERE t.idPatenteEc = :idPatenteEc")})
public class Tbdepositantepatente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "ID_DEPOSITANTE")
	private Long idDepositante;

	@Id
	@Column(name = "ID_PATENTE_EC")
	private Long idPatenteEc;

	public Long getIdPatenteEc() {
		return idPatenteEc;
	}

	public void setIdPatenteEc(Long idPatenteEc) {
		this.idPatenteEc = idPatenteEc;
	}

	public Long getIdDepositante() {
		return idDepositante;
	}

	public void setIdDepositante(Long idDepositante) {
		this.idDepositante = idDepositante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDepositante == null) ? 0 : idDepositante.hashCode());
		result = prime * result + ((idPatenteEc == null) ? 0 : idPatenteEc.hashCode());
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
		Tbdepositantepatente other = (Tbdepositantepatente) obj;
		if (idDepositante == null) {
			if (other.idDepositante != null)
				return false;
		} else if (!idDepositante.equals(other.idDepositante))
			return false;
		if (idPatenteEc == null) {
			if (other.idPatenteEc != null)
				return false;
		} else if (!idPatenteEc.equals(other.idPatenteEc))
			return false;
		return true;
	}

}
