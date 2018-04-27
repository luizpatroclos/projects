/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.marcas.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tgouvea
 */
@Embeddable
public class MrcItemClasNicePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_item_nice")
    private int cdItemNice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_nice")
    private int cdNice;

    public MrcItemClasNicePK() {
    }

    public MrcItemClasNicePK(int cdItemNice, int cdNice) {
        this.cdItemNice = cdItemNice;
        this.cdNice = cdNice;
    }

    public int getCdItemNice() {
        return cdItemNice;
    }

    public void setCdItemNice(int cdItemNice) {
        this.cdItemNice = cdItemNice;
    }

    public int getCdNice() {
        return cdNice;
    }

    public void setCdNice(int cdNice) {
        this.cdNice = cdNice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cdItemNice;
        hash += (int) cdNice;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MrcItemClasNicePK)) {
            return false;
        }
        MrcItemClasNicePK other = (MrcItemClasNicePK) object;
        if (this.cdItemNice != other.cdItemNice) {
            return false;
        }
        if (this.cdNice != other.cdNice) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.MrcItemClasNicePK[ cdItemNice=" + cdItemNice + ", cdNice=" + cdNice + " ]";
    }
    
}
