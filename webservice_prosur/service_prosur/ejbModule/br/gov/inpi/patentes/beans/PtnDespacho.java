/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.patentes.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "ptn_despacho")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtnDespacho.findAll", query = "SELECT p FROM PtnDespacho p"),
    @NamedQuery(name = "PtnDespacho.findByCodPedido", query = "SELECT p FROM PtnDespacho p WHERE p.ptnDespachoPK.codPedido = :codPedido"),
    @NamedQuery(name = "PtnDespacho.findByCdTipoDespacho", query = "SELECT p FROM PtnDespacho p WHERE p.ptnDespachoPK.cdTipoDespacho = :cdTipoDespacho"),
    @NamedQuery(name = "PtnDespacho.findByNumRpi", query = "SELECT p FROM PtnDespacho p WHERE p.ptnDespachoPK.numRpi = :numRpi")})
public class PtnDespacho implements Serializable {
   
	
	private static final long serialVersionUID = 1L;
    
	@EmbeddedId
    protected PtnDespachoPK ptnDespachoPK;
    
	@Lob
    @Size(max = 56)
    @Column(name = "texto_despacho")
    private String textoDespacho;

    public PtnDespacho() {
    }

    public PtnDespacho(PtnDespachoPK ptnDespachoPK) {
        this.ptnDespachoPK = ptnDespachoPK;
    }

    public PtnDespacho(int codPedido, int cdTipoDespacho, short numRpi) {
        this.ptnDespachoPK = new PtnDespachoPK(codPedido, cdTipoDespacho, numRpi);
    }

    public PtnDespachoPK getPtnDespachoPK() {
        return ptnDespachoPK;
    }

    public void setPtnDespachoPK(PtnDespachoPK ptnDespachoPK) {
        this.ptnDespachoPK = ptnDespachoPK;
    }

    public String getTextoDespacho() {
        return textoDespacho;
    }

    public void setTextoDespacho(String textoDespacho) {
        this.textoDespacho = textoDespacho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ptnDespachoPK != null ? ptnDespachoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnDespacho)) {
            return false;
        }
        PtnDespacho other = (PtnDespacho) object;
        if ((this.ptnDespachoPK == null && other.ptnDespachoPK != null) || (this.ptnDespachoPK != null && !this.ptnDespachoPK.equals(other.ptnDespachoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnDespacho[ ptnDespachoPK=" + ptnDespachoPK + " ]";
    }
    
}
