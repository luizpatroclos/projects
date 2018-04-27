/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.marcas.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "mrc_item_clas_nice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MrcItemClasNice.findAll", query = "SELECT m FROM MrcItemClasNice m"),
    @NamedQuery(name = "MrcItemClasNice.findByCdItemNice", query = "SELECT m FROM MrcItemClasNice m WHERE m.mrcItemClasNicePK.cdItemNice = :cdItemNice"),
    @NamedQuery(name = "MrcItemClasNice.findByCdNice", query = "SELECT m FROM MrcItemClasNice m WHERE m.mrcItemClasNicePK.cdNice = :cdNice"),
    @NamedQuery(name = "MrcItemClasNice.findByCdSeriePor", query = "SELECT m FROM MrcItemClasNice m WHERE m.cdSeriePor = :cdSeriePor"),
    @NamedQuery(name = "MrcItemClasNice.findByDsItemPor", query = "SELECT m FROM MrcItemClasNice m WHERE m.dsItemPor = :dsItemPor"),
    @NamedQuery(name = "MrcItemClasNice.findByCdSerieIng", query = "SELECT m FROM MrcItemClasNice m WHERE m.cdSerieIng = :cdSerieIng"),
    @NamedQuery(name = "MrcItemClasNice.findByDsItemIng", query = "SELECT m FROM MrcItemClasNice m WHERE m.dsItemIng = :dsItemIng"),
    @NamedQuery(name = "MrcItemClasNice.findByCdBase", query = "SELECT m FROM MrcItemClasNice m WHERE m.cdBase = :cdBase")})
public class MrcItemClasNice implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MrcItemClasNicePK mrcItemClasNicePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "cd_serie_por")
    private String cdSeriePor;
    @Size(max = 255)
    @Column(name = "ds_item_por")
    private String dsItemPor;
    @Size(max = 5)
    @Column(name = "cd_serie_ing")
    private String cdSerieIng;
    @Size(max = 255)
    @Column(name = "ds_item_ing")
    private String dsItemIng;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "cd_base")
    private String cdBase;
    @JoinColumn(name = "cd_nice", referencedColumnName = "cd_nice", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MrcClasNice mrcClasNice;

    public MrcItemClasNice() {
    }

    public MrcItemClasNice(MrcItemClasNicePK mrcItemClasNicePK) {
        this.mrcItemClasNicePK = mrcItemClasNicePK;
    }

    public MrcItemClasNice(MrcItemClasNicePK mrcItemClasNicePK, String cdSeriePor, String cdBase) {
        this.mrcItemClasNicePK = mrcItemClasNicePK;
        this.cdSeriePor = cdSeriePor;
        this.cdBase = cdBase;
    }

    public MrcItemClasNice(int cdItemNice, int cdNice) {
        this.mrcItemClasNicePK = new MrcItemClasNicePK(cdItemNice, cdNice);
    }

    public MrcItemClasNicePK getMrcItemClasNicePK() {
        return mrcItemClasNicePK;
    }

    public void setMrcItemClasNicePK(MrcItemClasNicePK mrcItemClasNicePK) {
        this.mrcItemClasNicePK = mrcItemClasNicePK;
    }

    public String getCdSeriePor() {
        return cdSeriePor;
    }

    public void setCdSeriePor(String cdSeriePor) {
        this.cdSeriePor = cdSeriePor;
    }

    public String getDsItemPor() {
        return dsItemPor;
    }

    public void setDsItemPor(String dsItemPor) {
        this.dsItemPor = dsItemPor;
    }

    public String getCdSerieIng() {
        return cdSerieIng;
    }

    public void setCdSerieIng(String cdSerieIng) {
        this.cdSerieIng = cdSerieIng;
    }

    public String getDsItemIng() {
        return dsItemIng;
    }

    public void setDsItemIng(String dsItemIng) {
        this.dsItemIng = dsItemIng;
    }

    public String getCdBase() {
        return cdBase;
    }

    public void setCdBase(String cdBase) {
        this.cdBase = cdBase;
    }

    public MrcClasNice getMrcClasNice() {
        return mrcClasNice;
    }

    public void setMrcClasNice(MrcClasNice mrcClasNice) {
        this.mrcClasNice = mrcClasNice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mrcItemClasNicePK != null ? mrcItemClasNicePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MrcItemClasNice)) {
            return false;
        }
        MrcItemClasNice other = (MrcItemClasNice) object;
        if ((this.mrcItemClasNicePK == null && other.mrcItemClasNicePK != null) || (this.mrcItemClasNicePK != null && !this.mrcItemClasNicePK.equals(other.mrcItemClasNicePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.MrcItemClasNice[ mrcItemClasNicePK=" + mrcItemClasNicePK + " ]";
    }
    
}
