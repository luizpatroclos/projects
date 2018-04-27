/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.patentes.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "ptn_pct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtnPct.findAll", query = "SELECT p FROM PtnPct p"),
    @NamedQuery(name = "PtnPct.findByCodPedido", query = "SELECT p FROM PtnPct p WHERE p.ptnPctPK.codPedido = :codPedido"),
    @NamedQuery(name = "PtnPct.findByNumPct", query = "SELECT p FROM PtnPct p WHERE p.ptnPctPK.numPct = :numPct"),
    @NamedQuery(name = "PtnPct.findByDtPct", query = "SELECT p FROM PtnPct p WHERE p.dtPct = :dtPct"),
    @NamedQuery(name = "PtnPct.findByCdOmpi", query = "SELECT p FROM PtnPct p WHERE p.cdOmpi = :cdOmpi"),
    @NamedQuery(name = "PtnPct.findByDtOmpi", query = "SELECT p FROM PtnPct p WHERE p.dtOmpi = :dtOmpi")})
public class PtnPct implements Serializable {
   
	
	private static final long serialVersionUID = 1L;
    
	@EmbeddedId
    protected PtnPctPK ptnPctPK;
    
	@Basic(optional = false)
    @NotNull
    @Column(name = "dt_pct")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtPct;
    
	@Size(max = 14)
    @Column(name = "cd_ompi")
    private String cdOmpi;
    
	@Column(name = "dt_ompi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtOmpi;

    public PtnPct() {
    }

    public PtnPct(PtnPctPK ptnPctPK) {
        this.ptnPctPK = ptnPctPK;
    }

    public PtnPct(PtnPctPK ptnPctPK, Date dtPct) {
        this.ptnPctPK = ptnPctPK;
        this.dtPct = dtPct;
    }

    public PtnPct(int codPedido, String numPct) {
        this.ptnPctPK = new PtnPctPK(codPedido, numPct);
    }

    public PtnPctPK getPtnPctPK() {
        return ptnPctPK;
    }

    public void setPtnPctPK(PtnPctPK ptnPctPK) {
        this.ptnPctPK = ptnPctPK;
    }

    public Date getDtPct() {
        return dtPct;
    }

    public void setDtPct(Date dtPct) {
        this.dtPct = dtPct;
    }

    public String getCdOmpi() {
        return cdOmpi;
    }

    public void setCdOmpi(String cdOmpi) {
        this.cdOmpi = cdOmpi;
    }

    public Date getDtOmpi() {
        return dtOmpi;
    }

    public void setDtOmpi(Date dtOmpi) {
        this.dtOmpi = dtOmpi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ptnPctPK != null ? ptnPctPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnPct)) {
            return false;
        }
        PtnPct other = (PtnPct) object;
        if ((this.ptnPctPK == null && other.ptnPctPK != null) || (this.ptnPctPK != null && !this.ptnPctPK.equals(other.ptnPctPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnPct[ ptnPctPK=" + ptnPctPK + " ]";
    }
    
}
