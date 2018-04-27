/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.patentes.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "ptn_lei_patente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtnLeiPatente.findAll", query = "SELECT p FROM PtnLeiPatente p"),
    @NamedQuery(name = "PtnLeiPatente.findByNumLei", query = "SELECT p FROM PtnLeiPatente p WHERE p.numLei = :numLei"),
    @NamedQuery(name = "PtnLeiPatente.findByDtInicioVigencia", query = "SELECT p FROM PtnLeiPatente p WHERE p.dtInicioVigencia = :dtInicioVigencia"),
    @NamedQuery(name = "PtnLeiPatente.findByDtTerminoVigenci", query = "SELECT p FROM PtnLeiPatente p WHERE p.dtTerminoVigenci = :dtTerminoVigenci")})
public class PtnLeiPatente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "num_lei")
    private String numLei;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_inicio_vigencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInicioVigencia;
    @Column(name = "dt_termino_vigenci")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtTerminoVigenci;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ptnLeiPatente")
    private Collection<PtnTipoDespacho> ptnTipoDespachoCollection;

    public PtnLeiPatente() {
    }

    public PtnLeiPatente(String numLei) {
        this.numLei = numLei;
    }

    public PtnLeiPatente(String numLei, Date dtInicioVigencia) {
        this.numLei = numLei;
        this.dtInicioVigencia = dtInicioVigencia;
    }

    public String getNumLei() {
        return numLei;
    }

    public void setNumLei(String numLei) {
        this.numLei = numLei;
    }

    public Date getDtInicioVigencia() {
        return dtInicioVigencia;
    }

    public void setDtInicioVigencia(Date dtInicioVigencia) {
        this.dtInicioVigencia = dtInicioVigencia;
    }

    public Date getDtTerminoVigenci() {
        return dtTerminoVigenci;
    }

    public void setDtTerminoVigenci(Date dtTerminoVigenci) {
        this.dtTerminoVigenci = dtTerminoVigenci;
    }

    @XmlTransient
    public Collection<PtnTipoDespacho> getPtnTipoDespachoCollection() {
        return ptnTipoDespachoCollection;
    }

    public void setPtnTipoDespachoCollection(Collection<PtnTipoDespacho> ptnTipoDespachoCollection) {
        this.ptnTipoDespachoCollection = ptnTipoDespachoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numLei != null ? numLei.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnLeiPatente)) {
            return false;
        }
        PtnLeiPatente other = (PtnLeiPatente) object;
        if ((this.numLei == null && other.numLei != null) || (this.numLei != null && !this.numLei.equals(other.numLei))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnLeiPatente[ numLei=" + numLei + " ]";
    }
    
}
