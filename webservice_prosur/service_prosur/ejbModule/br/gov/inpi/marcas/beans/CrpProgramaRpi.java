/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.marcas.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "crp_programa_rpi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrpProgramaRpi.findAll", query = "SELECT c FROM CrpProgramaRpi c"),
    @NamedQuery(name = "CrpProgramaRpi.findByNoRpi", query = "SELECT c FROM CrpProgramaRpi c WHERE c.noRpi = :noRpi"),
    @NamedQuery(name = "CrpProgramaRpi.findByDtPublicaMrc", query = "SELECT c FROM CrpProgramaRpi c WHERE c.dtPublicaMrc = :dtPublicaMrc")})
public class CrpProgramaRpi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "no_rpi")
    private Short noRpi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_publica_mrc")
    @Temporal(TemporalType.DATE)
    private Date dtPublicaMrc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "noRpi")
    private Collection<MrcDespacho> mrcDespachoCollection;

    public CrpProgramaRpi() {
    }

    public CrpProgramaRpi(Short noRpi) {
        this.noRpi = noRpi;
    }

    public CrpProgramaRpi(Short noRpi, Date dtPublicaMrc) {
        this.noRpi = noRpi;
        this.dtPublicaMrc = dtPublicaMrc;
    }

    public Short getNoRpi() {
        return noRpi;
    }

    public void setNoRpi(Short noRpi) {
        this.noRpi = noRpi;
    }

    public Date getDtPublicaMrc() {
        return dtPublicaMrc;
    }

    public void setDtPublicaMrc(Date dtPublicaMrc) {
        this.dtPublicaMrc = dtPublicaMrc;
    }

    @XmlTransient
    public Collection<MrcDespacho> getMrcDespachoCollection() {
        return mrcDespachoCollection;
    }

    public void setMrcDespachoCollection(Collection<MrcDespacho> mrcDespachoCollection) {
        this.mrcDespachoCollection = mrcDespachoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noRpi != null ? noRpi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrpProgramaRpi)) {
            return false;
        }
        CrpProgramaRpi other = (CrpProgramaRpi) object;
        if ((this.noRpi == null && other.noRpi != null) || (this.noRpi != null && !this.noRpi.equals(other.noRpi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.CrpProgramaRpi[ noRpi=" + noRpi + " ]";
    }
    
}
