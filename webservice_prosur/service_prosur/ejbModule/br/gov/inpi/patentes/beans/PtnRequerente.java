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
@Table(name = "ptn_requerente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtnRequerente.findAll", query = "SELECT p FROM PtnRequerente p"),
    @NamedQuery(name = "PtnRequerente.findByCodPedido", query = " SELECT p FROM PtnRequerente p " +
                                                                " WHERE p.ptnRequerentePK.codPedido = :codPedido" +
    		                                                    " AND p.dtTermino = null "),
    @NamedQuery(name = "PtnRequerente.findByCodRequerente", query = "SELECT p FROM PtnRequerente p WHERE p.ptnRequerentePK.codRequerente = :codRequerente"),
    @NamedQuery(name = "PtnRequerente.findByDtInicio", query = "SELECT p FROM PtnRequerente p WHERE p.ptnRequerentePK.dtInicio = :dtInicio"),
    @NamedQuery(name = "PtnRequerente.findByDtTermino", query = "SELECT p FROM PtnRequerente p WHERE p.dtTermino = :dtTermino"),
    @NamedQuery(name = "PtnRequerente.findByOrdemImportancia", query = "SELECT p FROM PtnRequerente p WHERE p.ordemImportancia = :ordemImportancia")})
public class PtnRequerente implements Serializable {
    
	
	private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PtnRequerentePK ptnRequerentePK;
    
    @Column(name = "dt_termino")
    @Temporal(TemporalType.DATE)
    private Date dtTermino;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ordem_importancia")
    private short ordemImportancia;

    public PtnRequerente() {
    }

    public PtnRequerente(PtnRequerentePK ptnRequerentePK) {
        this.ptnRequerentePK = ptnRequerentePK;
    }

    public PtnRequerente(PtnRequerentePK ptnRequerentePK, short ordemImportancia) {
        this.ptnRequerentePK = ptnRequerentePK;
        this.ordemImportancia = ordemImportancia;
    }

    public PtnRequerente(int codPedido, int codRequerente, Date dtInicio) {
        this.ptnRequerentePK = new PtnRequerentePK(codPedido, codRequerente, dtInicio);
    }

    public PtnRequerentePK getPtnRequerentePK() {
        return ptnRequerentePK;
    }

    public void setPtnRequerentePK(PtnRequerentePK ptnRequerentePK) {
        this.ptnRequerentePK = ptnRequerentePK;
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
        hash += (ptnRequerentePK != null ? ptnRequerentePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnRequerente)) {
            return false;
        }
        PtnRequerente other = (PtnRequerente) object;
        if ((this.ptnRequerentePK == null && other.ptnRequerentePK != null) || (this.ptnRequerentePK != null && !this.ptnRequerentePK.equals(other.ptnRequerentePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnRequerente[ ptnRequerentePK=" + ptnRequerentePK + " ]";
    }
    
}
