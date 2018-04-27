/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.marcas.beans;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "mrc_classif_vienna")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MrcClassifVienna.findAll", query = "SELECT m FROM MrcClassifVienna m"),
    @NamedQuery(name = "MrcClassifVienna.findByCdVienna", query = "SELECT m FROM MrcClassifVienna m WHERE m.mrcClassifViennaPK.cdVienna = :cdVienna"),
    @NamedQuery(name = "MrcClassifVienna.findByCdProcess", query = "SELECT m FROM MrcClassifVienna m WHERE m.mrcClassifViennaPK.cdProcess = :cdProcess")})
public class MrcClassifVienna implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MrcClassifViennaPK mrcClassifViennaPK;
    @JoinColumn(name = "cd_vienna", referencedColumnName = "cd_vienna", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MrcClasVienna mrcClasVienna;

    public MrcClassifVienna() {
    }

    public MrcClassifVienna(MrcClassifViennaPK mrcClassifViennaPK) {
        this.mrcClassifViennaPK = mrcClassifViennaPK;
    }

    public MrcClassifVienna(int cdVienna, int cdProcess) {
        this.mrcClassifViennaPK = new MrcClassifViennaPK(cdVienna, cdProcess);
    }

    public MrcClassifViennaPK getMrcClassifViennaPK() {
        return mrcClassifViennaPK;
    }

    public void setMrcClassifViennaPK(MrcClassifViennaPK mrcClassifViennaPK) {
        this.mrcClassifViennaPK = mrcClassifViennaPK;
    }

    public MrcClasVienna getMrcClasVienna() {
        return mrcClasVienna;
    }

    public void setMrcClasVienna(MrcClasVienna mrcClasVienna) {
        this.mrcClasVienna = mrcClasVienna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mrcClassifViennaPK != null ? mrcClassifViennaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MrcClassifVienna)) {
            return false;
        }
        MrcClassifVienna other = (MrcClassifVienna) object;
        if ((this.mrcClassifViennaPK == null && other.mrcClassifViennaPK != null) || (this.mrcClassifViennaPK != null && !this.mrcClassifViennaPK.equals(other.mrcClassifViennaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.MrcClassifVienna[ mrcClassifViennaPK=" + mrcClassifViennaPK + " ]";
    }
    
}
