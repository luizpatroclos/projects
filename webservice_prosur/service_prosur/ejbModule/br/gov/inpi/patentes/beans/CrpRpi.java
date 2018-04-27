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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "crp_rpi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrpRpi.findAll", query = "SELECT c FROM CrpRpi c"),
    @NamedQuery(name = "CrpRpi.findByNumRpi", query = "SELECT c FROM CrpRpi c WHERE c.numRpi = :numRpi"),
    @NamedQuery(name = "CrpRpi.findByDtPublicaPtn", query = "SELECT c FROM CrpRpi c WHERE c.dtPublicaPtn = :dtPublicaPtn")})
public class CrpRpi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_rpi")
    private Short numRpi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_publica_ptn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtPublicaPtn;

    public CrpRpi() {
    }

    public CrpRpi(Short numRpi) {
        this.numRpi = numRpi;
    }

    public CrpRpi(Short numRpi, Date dtPublicaPtn) {
        this.numRpi = numRpi;
        this.dtPublicaPtn = dtPublicaPtn;
    }

    public Short getNumRpi() {
        return numRpi;
    }

    public void setNumRpi(Short numRpi) {
        this.numRpi = numRpi;
    }

    public Date getDtPublicaPtn() {
        return dtPublicaPtn;
    }

    public void setDtPublicaPtn(Date dtPublicaPtn) {
        this.dtPublicaPtn = dtPublicaPtn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numRpi != null ? numRpi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrpRpi)) {
            return false;
        }
        CrpRpi other = (CrpRpi) object;
        if ((this.numRpi == null && other.numRpi != null) || (this.numRpi != null && !this.numRpi.equals(other.numRpi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.CrpRpi[ numRpi=" + numRpi + " ]";
    }
    
}
