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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tgouvea
 */
@Embeddable
public class PtnProcuradorPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_pedido")
    private int codPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_procurador")
    private int codProcurador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_inicio")
    @Temporal(TemporalType.DATE)
    private Date dtInicio;

    public PtnProcuradorPK() {
    }

    public PtnProcuradorPK(int codPedido, int codProcurador, Date dtInicio) {
        this.codPedido = codPedido;
        this.codProcurador = codProcurador;
        this.dtInicio = dtInicio;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getCodProcurador() {
        return codProcurador;
    }

    public void setCodProcurador(int codProcurador) {
        this.codProcurador = codProcurador;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codPedido;
        hash += (int) codProcurador;
        hash += (dtInicio != null ? dtInicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnProcuradorPK)) {
            return false;
        }
        PtnProcuradorPK other = (PtnProcuradorPK) object;
        if (this.codPedido != other.codPedido) {
            return false;
        }
        if (this.codProcurador != other.codProcurador) {
            return false;
        }
        if ((this.dtInicio == null && other.dtInicio != null) || (this.dtInicio != null && !this.dtInicio.equals(other.dtInicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnProcuradorPK[ codPedido=" + codPedido + ", codProcurador=" + codProcurador + ", dtInicio=" + dtInicio + " ]";
    }
    
}
