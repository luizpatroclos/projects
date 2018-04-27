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
public class PtnDespachoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_pedido")
    private int codPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_tipo_despacho")
    private int cdTipoDespacho;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_rpi")
    private short numRpi;

    public PtnDespachoPK() {
    }

    public PtnDespachoPK(int codPedido, int cdTipoDespacho, short numRpi) {
        this.codPedido = codPedido;
        this.cdTipoDespacho = cdTipoDespacho;
        this.numRpi = numRpi;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getCdTipoDespacho() {
        return cdTipoDespacho;
    }

    public void setCdTipoDespacho(int cdTipoDespacho) {
        this.cdTipoDespacho = cdTipoDespacho;
    }

    public short getNumRpi() {
        return numRpi;
    }

    public void setNumRpi(short numRpi) {
        this.numRpi = numRpi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codPedido;
        hash += (int) cdTipoDespacho;
        hash += (int) numRpi;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnDespachoPK)) {
            return false;
        }
        PtnDespachoPK other = (PtnDespachoPK) object;
        if (this.codPedido != other.codPedido) {
            return false;
        }
        if (this.cdTipoDespacho != other.cdTipoDespacho) {
            return false;
        }
        if (this.numRpi != other.numRpi) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnDespachoPK[ codPedido=" + codPedido + ", cdTipoDespacho=" + cdTipoDespacho + ", numRpi=" + numRpi + " ]";
    }
    
}
