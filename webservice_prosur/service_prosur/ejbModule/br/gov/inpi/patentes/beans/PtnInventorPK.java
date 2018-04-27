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

/**
 *
 * @author tgouvea
 */
@Embeddable
public class PtnInventorPK implements Serializable {
    
	@Basic(optional = false)
    @NotNull
    @Column(name = "cod_pedido")
    private int codPedido;
    
	@Basic(optional = false)
    @NotNull
    @Column(name = "cod_inventor")
    private int codInventor;

    public PtnInventorPK() {
    }

    public PtnInventorPK(int codPedido, int codInventor) {
        this.codPedido = codPedido;
        this.codInventor = codInventor;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getCodInventor() {
        return codInventor;
    }

    public void setCodInventor(int codInventor) {
        this.codInventor = codInventor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codPedido;
        hash += (int) codInventor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnInventorPK)) {
            return false;
        }
        PtnInventorPK other = (PtnInventorPK) object;
        if (this.codPedido != other.codPedido) {
            return false;
        }
        if (this.codInventor != other.codInventor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnInventorPK[ codPedido=" + codPedido + ", codInventor=" + codInventor + " ]";
    }
    
}
