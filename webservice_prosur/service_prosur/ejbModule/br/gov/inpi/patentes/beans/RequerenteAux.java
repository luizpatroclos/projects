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
@Table(name = "requerente_aux")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequerenteAux.findAll", query = "SELECT r FROM RequerenteAux r"),
    @NamedQuery(name = "RequerenteAux.findByCodPedido", query = "SELECT r FROM RequerenteAux r WHERE r.requerenteAuxPK.codPedido = :codPedido"),
    @NamedQuery(name = "RequerenteAux.findByCodRequerente", query = "SELECT r FROM RequerenteAux r WHERE r.requerenteAuxPK.codRequerente = :codRequerente"),
    @NamedQuery(name = "RequerenteAux.findByDtInicio", query = "SELECT r FROM RequerenteAux r WHERE r.requerenteAuxPK.dtInicio = :dtInicio"),
    @NamedQuery(name = "RequerenteAux.findByDtTermino", query = "SELECT r FROM RequerenteAux r WHERE r.dtTermino = :dtTermino"),
    @NamedQuery(name = "RequerenteAux.findByOrdemImportancia", query = "SELECT r FROM RequerenteAux r WHERE r.ordemImportancia = :ordemImportancia")})
public class RequerenteAux implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RequerenteAuxPK requerenteAuxPK;
    @Column(name = "dt_termino")
    @Temporal(TemporalType.DATE)
    private Date dtTermino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ordem_importancia")
    private short ordemImportancia;

    public RequerenteAux() {
    }

    public RequerenteAux(RequerenteAuxPK requerenteAuxPK) {
        this.requerenteAuxPK = requerenteAuxPK;
    }

    public RequerenteAux(RequerenteAuxPK requerenteAuxPK, short ordemImportancia) {
        this.requerenteAuxPK = requerenteAuxPK;
        this.ordemImportancia = ordemImportancia;
    }

    public RequerenteAux(int codPedido, int codRequerente, Date dtInicio) {
        this.requerenteAuxPK = new RequerenteAuxPK(codPedido, codRequerente, dtInicio);
    }

    public RequerenteAuxPK getRequerenteAuxPK() {
        return requerenteAuxPK;
    }

    public void setRequerenteAuxPK(RequerenteAuxPK requerenteAuxPK) {
        this.requerenteAuxPK = requerenteAuxPK;
    }

    public Date getDtTermino() {
        return dtTermino;
    }

    public void setDtTermino(Date dtTermino) {
        this.dtTermino = dtTermino;
    }

    public short getOrdemImportancia() {
        return ordemImportancia;
    }

    public void setOrdemImportancia(short ordemImportancia) {
        this.ordemImportancia = ordemImportancia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requerenteAuxPK != null ? requerenteAuxPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequerenteAux)) {
            return false;
        }
        RequerenteAux other = (RequerenteAux) object;
        if ((this.requerenteAuxPK == null && other.requerenteAuxPK != null) || (this.requerenteAuxPK != null && !this.requerenteAuxPK.equals(other.requerenteAuxPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.RequerenteAux[ requerenteAuxPK=" + requerenteAuxPK + " ]";
    }
    
}
