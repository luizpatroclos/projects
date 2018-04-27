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
public class PtnClassifPedidoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_pedido")
    private int codPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_classificacao")
    private int codClassificacao;

    public PtnClassifPedidoPK() {
    }

    public PtnClassifPedidoPK(int codPedido, int codClassificacao) {
        this.codPedido = codPedido;
        this.codClassificacao = codClassificacao;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getCodClassificacao() {
        return codClassificacao;
    }

    public void setCodClassificacao(int codClassificacao) {
        this.codClassificacao = codClassificacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codPedido;
        hash += (int) codClassificacao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnClassifPedidoPK)) {
            return false;
        }
        PtnClassifPedidoPK other = (PtnClassifPedidoPK) object;
        if (this.codPedido != other.codPedido) {
            return false;
        }
        if (this.codClassificacao != other.codClassificacao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnClassifPedidoPK[ codPedido=" + codPedido + ", codClassificacao=" + codClassificacao + " ]";
    }
    
}
