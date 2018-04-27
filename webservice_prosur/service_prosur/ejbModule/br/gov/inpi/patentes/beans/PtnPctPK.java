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
public class PtnPctPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_pedido")
    private int codPedido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "num_pct")
    private String numPct;

    public PtnPctPK() {
    }

    public PtnPctPK(int codPedido, String numPct) {
        this.codPedido = codPedido;
        this.numPct = numPct;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public String getNumPct() {
        return numPct;
    }

    public void setNumPct(String numPct) {
        this.numPct = numPct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codPedido;
        hash += (numPct != null ? numPct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnPctPK)) {
            return false;
        }
        PtnPctPK other = (PtnPctPK) object;
        if (this.codPedido != other.codPedido) {
            return false;
        }
        if ((this.numPct == null && other.numPct != null) || (this.numPct != null && !this.numPct.equals(other.numPct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnPctPK[ codPedido=" + codPedido + ", numPct=" + numPct + " ]";
    }
    
}
