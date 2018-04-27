/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.patentes.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "ptn_inventor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtnInventor.findAll", query = "SELECT p FROM PtnInventor p"),
    @NamedQuery(name = "PtnInventor.findByCodPedido", query = "SELECT p FROM PtnInventor p WHERE p.ptnInventorPK.codPedido = :codPedido"),
    @NamedQuery(name = "PtnInventor.findByCodInventor", query = "SELECT p FROM PtnInventor p WHERE p.ptnInventorPK.codInventor = :codInventor"),
    @NamedQuery(name = "PtnInventor.findByOrdemImportancia", query = "SELECT p FROM PtnInventor p WHERE p.ordemImportancia = :ordemImportancia"),
    @NamedQuery(name = "PtnInventor.findByAnonimato", query = "SELECT p FROM PtnInventor p WHERE p.anonimato = :anonimato")})
public class PtnInventor implements Serializable {
    
	private static final long serialVersionUID = 1L;
    
	@EmbeddedId
    protected PtnInventorPK ptnInventorPK;
    
	@Basic(optional = false)
    @NotNull
    @Column(name = "ordem_importancia")
    private short ordemImportancia;
    
	@Basic(optional = false)
    @NotNull
    @Column(name = "anonimato")
    private Character anonimato;

    public PtnInventor() {
    }

    public PtnInventor(PtnInventorPK ptnInventorPK) {
        this.ptnInventorPK = ptnInventorPK;
    }

    public PtnInventor(PtnInventorPK ptnInventorPK, short ordemImportancia, Character anonimato) {
        this.ptnInventorPK = ptnInventorPK;
        this.ordemImportancia = ordemImportancia;
        this.anonimato = anonimato;
    }

    public PtnInventor(int codPedido, int codInventor) {
        this.ptnInventorPK = new PtnInventorPK(codPedido, codInventor);
    }

    public PtnInventorPK getPtnInventorPK() {
        return ptnInventorPK;
    }

    public void setPtnInventorPK(PtnInventorPK ptnInventorPK) {
        this.ptnInventorPK = ptnInventorPK;
    }

    public short getOrdemImportancia() {
        return ordemImportancia;
    }

    public void setOrdemImportancia(short ordemImportancia) {
        this.ordemImportancia = ordemImportancia;
    }

    public Character getAnonimato() {
        return anonimato;
    }

    public void setAnonimato(Character anonimato) {
        this.anonimato = anonimato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ptnInventorPK != null ? ptnInventorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnInventor)) {
            return false;
        }
        PtnInventor other = (PtnInventor) object;
        if ((this.ptnInventorPK == null && other.ptnInventorPK != null) || (this.ptnInventorPK != null && !this.ptnInventorPK.equals(other.ptnInventorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnInventor[ ptnInventorPK=" + ptnInventorPK + " ]";
    }
    
}
