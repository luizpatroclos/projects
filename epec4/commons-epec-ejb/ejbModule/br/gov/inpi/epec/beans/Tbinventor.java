/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBINVENTOR")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbinventor.findAll", query = "SELECT t FROM Tbinventor t order by t.txInventor"),
		@NamedQuery(name = "Tbinventor.findByIdInventor", query = "SELECT t FROM Tbinventor t WHERE t.idInventor = :idInventor"),
		@NamedQuery(name = "Tbinventor.findByTxInventor", query = "SELECT t FROM Tbinventor t WHERE t.txInventor = :txInventor") })
public class Tbinventor implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INVENTOR")
	private Long idInventor;

	@Size(max = 200)
	@Column(name = "TX_INVENTOR")
	private String txInventor;

	@JoinTable(name = "TBINVENTORPATENTE", joinColumns = { @JoinColumn(name = "ID_INVENTOR", referencedColumnName = "ID_INVENTOR", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "ID_PATENTE_EC", referencedColumnName = "ID_PATENTE_EC", nullable = false) })
	@ManyToMany
	private List<Tbpatenteec> tbpatenteecList;

	public Tbinventor() {
	}

	public Tbinventor(Long idInventor) {
		this.idInventor = idInventor;
	}

	public Long getIdInventor() {
		return idInventor;
	}

	public void setIdInventor(Long idInventor) {
		this.idInventor = idInventor;
	}

	public String getTxInventor() {
		return txInventor;
	}

	public void setTxInventor(String txInventor) {
		this.txInventor = txInventor;
	}

	@XmlTransient
	public List<Tbpatenteec> getTbpatenteecList() {
		return tbpatenteecList;
	}

	public void setTbpatenteecList(List<Tbpatenteec> tbpatenteecList) {
		this.tbpatenteecList = tbpatenteecList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idInventor != null ? idInventor.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Tbinventor)) {
			return false;
		}
		Tbinventor other = (Tbinventor) object;
		if ((this.idInventor == null && other.idInventor != null) || (this.idInventor != null && !this.idInventor.equals(other.idInventor))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.gov.inpi.epec.beans.Tbinventor[ idInventor=" + idInventor + " ]";
	}

}
