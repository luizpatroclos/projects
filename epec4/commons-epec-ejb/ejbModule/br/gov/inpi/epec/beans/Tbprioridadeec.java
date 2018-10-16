/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBPRIORIDADEEC")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbprioridadeec.findAll", query = "SELECT t FROM Tbprioridadeec t"), 
	@NamedQuery(name = "Tbprioridadeec.countAll", query = "SELECT COUNT(t) FROM Tbprioridadeec t"),
	@NamedQuery(name = "Tbprioridadeec.findByIdPrioridadeEc", query = "SELECT t FROM Tbprioridadeec t WHERE t.idPrioridadeEc = :idPrioridadeEc"),
	@NamedQuery(name = "Tbprioridadeec.findByDtDeposito", query = "SELECT t FROM Tbprioridadeec t WHERE t.dtDeposito = :dtDeposito"),
	@NamedQuery(name = "Tbprioridadeec.findByIdPatenteEc", query = "SELECT t FROM Tbprioridadeec t WHERE t.idPatenteEc.idPatenteEc = :idPatenteEc "),
	@NamedQuery(name = "Tbprioridadeec.countAllByIdPatenteEc", query = "SELECT COUNT(t) FROM Tbprioridadeec t WHERE t.idPatenteEc.idEntidadeEc.idEntidadeEc = :idEntidadeEc "),
	@NamedQuery(name = "Tbprioridadeec.findByStrPrioridade", query = "SELECT t FROM Tbprioridadeec t WHERE t.strPrioridade = :strPrioridade") })
public class Tbprioridadeec implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRIORIDADE_EC")
	private Long idPrioridadeEc;

	@Column(name = "DT_DEPOSITO")
	@Temporal(TemporalType.DATE)
	private Date dtDeposito;

	@Column(name = "STR_PRIORIDADE",length = 45)
	private String strPrioridade;

	@JoinColumn(name = "ID_PATENTE_EC", referencedColumnName = "ID_PATENTE_EC")
	@ManyToOne
	private Tbpatenteec idPatenteEc;

	public Tbprioridadeec() {
	}

	public Tbprioridadeec(Long idPrioridadeEc) {
		this.idPrioridadeEc = idPrioridadeEc;
	}

	public Tbprioridadeec(Long idPrioridadeEc, Date dtDeposito, String strPrioridade) {
		this.idPrioridadeEc = idPrioridadeEc;
		this.dtDeposito = dtDeposito;
		this.strPrioridade = strPrioridade;
	}

	public Long getIdPrioridadeEc() {
		return idPrioridadeEc;
	}

	public void setIdPrioridadeEc(Long idPrioridadeEc) {
		this.idPrioridadeEc = idPrioridadeEc;
	}

	public Date getDtDeposito() {
		return dtDeposito;
	}

	public void setDtDeposito(Date dtDeposito) {
		this.dtDeposito = dtDeposito;
	}

	public String getStrPrioridade() {
		return strPrioridade;
	}

	public void setStrPrioridade(String strPrioridade) {
		this.strPrioridade = strPrioridade;
	}

	public Tbpatenteec getIdPatenteEc() {
		return idPatenteEc;
	}

	public void setIdPatenteEc(Tbpatenteec idPatenteEc) {
		this.idPatenteEc = idPatenteEc;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idPrioridadeEc != null ? idPrioridadeEc.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Tbprioridadeec)) {
			return false;
		}
		Tbprioridadeec other = (Tbprioridadeec) object;
		if ((this.idPrioridadeEc == null && other.idPrioridadeEc != null) || (this.idPrioridadeEc != null && !this.idPrioridadeEc.equals(other.idPrioridadeEc))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.gov.inpi.epec.beans.Tbprioridadeec[ idPrioridadeEc=" + idPrioridadeEc + " ]";
	}

}
