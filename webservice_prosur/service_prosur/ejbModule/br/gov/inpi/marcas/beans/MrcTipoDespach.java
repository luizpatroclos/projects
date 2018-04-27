/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.marcas.beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "mrc_tipo_despach")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MrcTipoDespach.findAll", query = "SELECT m FROM MrcTipoDespach m"),
    @NamedQuery(name = "MrcTipoDespach.findByCdTipoDespach", query = "SELECT m FROM MrcTipoDespach m WHERE m.cdTipoDespach = :cdTipoDespach"),
    @NamedQuery(name = "MrcTipoDespach.findByCdDespach", query = "SELECT m FROM MrcTipoDespach m WHERE m.cdDespach = :cdDespach"),
    @NamedQuery(name = "MrcTipoDespach.findByCdSituacaDespach", query = "SELECT m FROM MrcTipoDespach m WHERE m.cdSituacaDespach = :cdSituacaDespach"),
    @NamedQuery(name = "MrcTipoDespach.findByCdPublicaRpi", query = "SELECT m FROM MrcTipoDespach m WHERE m.cdPublicaRpi = :cdPublicaRpi")})
public class MrcTipoDespach implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_tipo_despach")
    private Integer cdTipoDespach;
    @Lob
    @Size(max = 2048)
    @Column(name = "ds_tipo_despach")
    private String dsTipoDespach;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cd_despach")
    private String cdDespach;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_situaca_despach")
    private Character cdSituacaDespach;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_publica_rpi")
    private Character cdPublicaRpi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdTipoDespach")
    private Collection<MrcDespacho> mrcDespachoCollection;

    public MrcTipoDespach() {
    }

    public MrcTipoDespach(Integer cdTipoDespach) {
        this.cdTipoDespach = cdTipoDespach;
    }

    public MrcTipoDespach(Integer cdTipoDespach, String cdDespach, Character cdSituacaDespach, Character cdPublicaRpi) {
        this.cdTipoDespach = cdTipoDespach;
        this.cdDespach = cdDespach;
        this.cdSituacaDespach = cdSituacaDespach;
        this.cdPublicaRpi = cdPublicaRpi;
    }

    public Integer getCdTipoDespach() {
        return cdTipoDespach;
    }

    public void setCdTipoDespach(Integer cdTipoDespach) {
        this.cdTipoDespach = cdTipoDespach;
    }

    public String getDsTipoDespach() {
        return dsTipoDespach;
    }

    public void setDsTipoDespach(String dsTipoDespach) {
        this.dsTipoDespach = dsTipoDespach;
    }

    public String getCdDespach() {
        return cdDespach;
    }

    public void setCdDespach(String cdDespach) {
        this.cdDespach = cdDespach;
    }

    public Character getCdSituacaDespach() {
        return cdSituacaDespach;
    }

    public void setCdSituacaDespach(Character cdSituacaDespach) {
        this.cdSituacaDespach = cdSituacaDespach;
    }

    public Character getCdPublicaRpi() {
        return cdPublicaRpi;
    }

    public void setCdPublicaRpi(Character cdPublicaRpi) {
        this.cdPublicaRpi = cdPublicaRpi;
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
        hash += (cdTipoDespach != null ? cdTipoDespach.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MrcTipoDespach)) {
            return false;
        }
        MrcTipoDespach other = (MrcTipoDespach) object;
        if ((this.cdTipoDespach == null && other.cdTipoDespach != null) || (this.cdTipoDespach != null && !this.cdTipoDespach.equals(other.cdTipoDespach))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.MrcTipoDespach[ cdTipoDespach=" + cdTipoDespach + " ]";
    }
    
}
