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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "ptn_tipo_despacho")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtnTipoDespacho.findAll", query = "SELECT p FROM PtnTipoDespacho p"),
    @NamedQuery(name = "PtnTipoDespacho.findByCdTipoDespacho", query = "SELECT p FROM PtnTipoDespacho p WHERE p.ptnTipoDespachoPK.cdTipoDespacho = :cdTipoDespacho"),
    @NamedQuery(name = "PtnTipoDespacho.findByNumLei", query = "SELECT p FROM PtnTipoDespacho p WHERE p.ptnTipoDespachoPK.numLei = :numLei"),
    @NamedQuery(name = "PtnTipoDespacho.findByCodDespachoRpi", query = "SELECT p FROM PtnTipoDespacho p WHERE p.codDespachoRpi = :codDespachoRpi"),
    @NamedQuery(name = "PtnTipoDespacho.findByDesTipoDespacho", query = "SELECT p FROM PtnTipoDespacho p WHERE p.desTipoDespacho = :desTipoDespacho")})
public class PtnTipoDespacho implements Serializable {
    
	private static final long serialVersionUID = 1L;
    
	@EmbeddedId
    protected PtnTipoDespachoPK ptnTipoDespachoPK;
    
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "cod_despacho_rpi")
    private String codDespachoRpi;
    
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "des_tipo_despacho")
    private String desTipoDespacho;
    
	@JoinColumn(name = "num_lei", referencedColumnName = "num_lei", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PtnLeiPatente ptnLeiPatente;

    public PtnTipoDespacho() {
    }

    public PtnTipoDespacho(PtnTipoDespachoPK ptnTipoDespachoPK) {
        this.ptnTipoDespachoPK = ptnTipoDespachoPK;
    }

    public PtnTipoDespacho(PtnTipoDespachoPK ptnTipoDespachoPK, String codDespachoRpi, String desTipoDespacho) {
        this.ptnTipoDespachoPK = ptnTipoDespachoPK;
        this.codDespachoRpi = codDespachoRpi;
        this.desTipoDespacho = desTipoDespacho;
    }

    public PtnTipoDespacho(int cdTipoDespacho, String numLei) {
        this.ptnTipoDespachoPK = new PtnTipoDespachoPK(cdTipoDespacho, numLei);
    }

    public PtnTipoDespachoPK getPtnTipoDespachoPK() {
        return ptnTipoDespachoPK;
    }

    public void setPtnTipoDespachoPK(PtnTipoDespachoPK ptnTipoDespachoPK) {
        this.ptnTipoDespachoPK = ptnTipoDespachoPK;
    }

    public String getCodDespachoRpi() {
        return codDespachoRpi;
    }

    public void setCodDespachoRpi(String codDespachoRpi) {
        this.codDespachoRpi = codDespachoRpi;
    }

    public String getDesTipoDespacho() {
        return desTipoDespacho;
    }

    public void setDesTipoDespacho(String desTipoDespacho) {
        this.desTipoDespacho = desTipoDespacho;
    }

    public PtnLeiPatente getPtnLeiPatente() {
        return ptnLeiPatente;
    }

    public void setPtnLeiPatente(PtnLeiPatente ptnLeiPatente) {
        this.ptnLeiPatente = ptnLeiPatente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ptnTipoDespachoPK != null ? ptnTipoDespachoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnTipoDespacho)) {
            return false;
        }
        PtnTipoDespacho other = (PtnTipoDespacho) object;
        if ((this.ptnTipoDespachoPK == null && other.ptnTipoDespachoPK != null) || (this.ptnTipoDespachoPK != null && !this.ptnTipoDespachoPK.equals(other.ptnTipoDespachoPK))) {
            return false;
        }
        return true;
    }
    
 public int getCodigoTipoDespacho(){
    	
    	return this.ptnTipoDespachoPK.getCdTipoDespacho();
    	
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnTipoDespacho[ ptnTipoDespachoPK=" + ptnTipoDespachoPK + " ]";
    }
    
}
