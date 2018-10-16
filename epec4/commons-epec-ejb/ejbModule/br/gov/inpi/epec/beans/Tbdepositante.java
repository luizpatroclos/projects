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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBDEPOSITANTE", uniqueConstraints = { @UniqueConstraint(columnNames = { "TX_DEPOSITANTE" }) })
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbdepositante.findAll", query = "SELECT t FROM Tbdepositante t order by t.txDepositante"),
		@NamedQuery(name = "Tbdepositante.findByIdDepositante", query = "SELECT t FROM Tbdepositante t WHERE t.idDepositante = :idDepositante"),
		@NamedQuery(name = "Tbdepositante.findByTxDepositante", query = "SELECT t FROM Tbdepositante t WHERE t.txDepositante = :txDepositante") })
public class Tbdepositante implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DEPOSITANTE")
	private Long idDepositante;

	@Column(name = "TX_DEPOSITANTE", length = 200)
	private String txDepositante;

	@JoinTable(name = "TBDEPOSITANTEPATENTE", joinColumns = { @JoinColumn(name = "ID_DEPOSITANTE", referencedColumnName = "ID_DEPOSITANTE") }, inverseJoinColumns = { @JoinColumn(name = "ID_PATENTE_EC", referencedColumnName = "ID_PATENTE_EC") })
	@ManyToMany
	private List<Tbpatenteec> tbpatenteecList;

	public Tbdepositante() {
	}

	public Tbdepositante(Long idDepositante) {
		this.idDepositante = idDepositante;
	}

	public Long getIdDepositante() {
		return idDepositante;
	}

	public void setIdDepositante(Long idDepositante) {
		this.idDepositante = idDepositante;
	}

	public String getTxDepositante() {
		return txDepositante;
	}

	public void setTxDepositante(String txDepositante) {
		this.txDepositante = txDepositante;
	}

	public List<Tbpatenteec> getTbpatenteecList() {
		return tbpatenteecList;
	}

	public void setTbpatenteecList(List<Tbpatenteec> tbpatenteecList) {
		this.tbpatenteecList = tbpatenteecList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idDepositante != null ? idDepositante.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Tbdepositante)) {
			return false;
		}
		Tbdepositante other = (Tbdepositante) object;
		if ((this.idDepositante == null && other.idDepositante != null) || (this.idDepositante != null && !this.idDepositante.equals(other.idDepositante))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.gov.inpi.epec.beans.Tbdepositante[ idDepositante=" + idDepositante + " ]";
	}

}
