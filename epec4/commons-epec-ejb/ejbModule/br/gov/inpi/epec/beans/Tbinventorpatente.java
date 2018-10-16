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
@Table(name = "TBINVENTORPATENTE")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbinventorpatente.findByIdInvertorPatente_1", query = "SELECT t FROM Tbinventorpatente t WHERE t.idInventor = :idInventor"),
@NamedQuery(name = "Tbinventorpatente.findByIdInventorPatente_2", query = "SELECT t FROM Tbinventorpatente t WHERE t.idPatenteEc = :idPatenteEc"),
@NamedQuery(name = "Tbinventorpatente.findByIdInventorPatente_3", query = "SELECT t FROM Tbinventorpatente t WHERE t.idPatenteEc = :idPatenteEc AND t.idInventor = :idInventor")})
public class Tbinventorpatente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_INVENTOR")
	private Long idInventor;

	@Id
	@Column(name = "ID_PATENTE_EC")
	private Long idPatenteEc;

	public Long getIdInventor() {
		return idInventor;
	}

	public void setIdInventor(Long idInventor) {
		this.idInventor = idInventor;
	}

	public Long getIdPatenteEc() {
		return idPatenteEc;
	}

	public void setIdPatenteEc(Long idPatenteEc) {
		this.idPatenteEc = idPatenteEc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idInventor == null) ? 0 : idInventor.hashCode());
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
		Tbinventorpatente other = (Tbinventorpatente) obj;
		if (idInventor == null) {
			if (other.idInventor != null)
				return false;
		} else if (!idInventor.equals(other.idInventor))
			return false;
		if (idPatenteEc == null) {
			if (other.idPatenteEc != null)
				return false;
		} else if (!idPatenteEc.equals(other.idPatenteEc))
			return false;
		return true;
	}

}
