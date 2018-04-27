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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "ptn_classif_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtnClassifPedido.findAll", query = "SELECT p FROM PtnClassifPedido p"),
    @NamedQuery(name = "PtnClassifPedido.findByCodPedido", query = "SELECT p FROM PtnClassifPedido p WHERE p.ptnClassifPedidoPK.codPedido = :codPedido"),
    @NamedQuery(name = "PtnClassifPedido.findByCodClassificacao", query = "SELECT p FROM PtnClassifPedido p WHERE p.ptnClassifPedidoPK.codClassificacao = :codClassificacao"),
    @NamedQuery(name = "PtnClassifPedido.findByDtClassificacao", query = "SELECT p FROM PtnClassifPedido p WHERE p.dtClassificacao = :dtClassificacao"),
    @NamedQuery(name = "PtnClassifPedido.findByOrdemPedido", query = "SELECT p FROM PtnClassifPedido p WHERE p.ordemPedido = :ordemPedido")})
public class PtnClassifPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PtnClassifPedidoPK ptnClassifPedidoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_classificacao")
    @Temporal(TemporalType.DATE)
    private Date dtClassificacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ordem_pedido")
    private short ordemPedido;
    @JoinColumn(name = "cod_classificacao", referencedColumnName = "cod_classificacao", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PtnClassif ptnClassif;

    public PtnClassifPedido() {
    }

    public PtnClassifPedido(PtnClassifPedidoPK ptnClassifPedidoPK) {
        this.ptnClassifPedidoPK = ptnClassifPedidoPK;
    }

    public PtnClassifPedido(PtnClassifPedidoPK ptnClassifPedidoPK, Date dtClassificacao, short ordemPedido) {
        this.ptnClassifPedidoPK = ptnClassifPedidoPK;
        this.dtClassificacao = dtClassificacao;
        this.ordemPedido = ordemPedido;
    }

    public PtnClassifPedido(int codPedido, int codClassificacao) {
        this.ptnClassifPedidoPK = new PtnClassifPedidoPK(codPedido, codClassificacao);
    }

    public PtnClassifPedidoPK getPtnClassifPedidoPK() {
        return ptnClassifPedidoPK;
    }

    public void setPtnClassifPedidoPK(PtnClassifPedidoPK ptnClassifPedidoPK) {
        this.ptnClassifPedidoPK = ptnClassifPedidoPK;
    }

    public Date getDtClassificacao() {
        return dtClassificacao;
    }

    public void setDtClassificacao(Date dtClassificacao) {
        this.dtClassificacao = dtClassificacao;
    }

    public short getOrdemPedido() {
        return ordemPedido;
    }

    public void setOrdemPedido(short ordemPedido) {
        this.ordemPedido = ordemPedido;
    }

    public PtnClassif getPtnClassif() {
        return ptnClassif;
    }

    public void setPtnClassif(PtnClassif ptnClassif) {
        this.ptnClassif = ptnClassif;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ptnClassifPedidoPK != null ? ptnClassifPedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnClassifPedido)) {
            return false;
        }
        PtnClassifPedido other = (PtnClassifPedido) object;
        if ((this.ptnClassifPedidoPK == null && other.ptnClassifPedidoPK != null) || (this.ptnClassifPedidoPK != null && !this.ptnClassifPedidoPK.equals(other.ptnClassifPedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnClassifPedido[ ptnClassifPedidoPK=" + ptnClassifPedidoPK + " ]";
    }
    
}
