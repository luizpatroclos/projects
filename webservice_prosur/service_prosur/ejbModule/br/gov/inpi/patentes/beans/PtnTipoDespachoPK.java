/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.patentes.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tgouvea
 */
@Embeddable
public class PtnTipoDespachoPK implements Serializable {
   
	@Basic(optional = false)
    @NotNull
    @Column(name = "cd_tipo_despacho")
    
	private int cdTipoDespacho;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "num_lei")
    private String numLei;

    public PtnTipoDespachoPK() {
    }

    public PtnTipoDespachoPK(int cdTipoDespacho, String numLei) {
        this.cdTipoDespacho = cdTipoDespacho;
        this.numLei = numLei;
    }

    public int getCdTipoDespacho() {
        return cdTipoDespacho;
    }

    public void setCdTipoDespacho(int cdTipoDespacho) {
        this.cdTipoDespacho = cdTipoDespacho;
    }

    public String getNumLei() {
        return numLei;
    }

    public void setNumLei(String numLei) {
        this.numLei = numLei;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cdTipoDespacho;
        hash += (numLei != null ? numLei.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnTipoDespachoPK)) {
            return false;
        }
        PtnTipoDespachoPK other = (PtnTipoDespachoPK) object;
        if (this.cdTipoDespacho != other.cdTipoDespacho) {
            return false;
        }
        if ((this.numLei == null && other.numLei != null) || (this.numLei != null && !this.numLei.equals(other.numLei))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnTipoDespachoPK[ cdTipoDespacho=" + cdTipoDespacho + ", numLei=" + numLei + " ]";
    }
    
}
