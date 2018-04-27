/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.patentes.beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "crp_uf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrpUf.findAll", query = "SELECT c FROM CrpUf c"),
    @NamedQuery(name = "CrpUf.findByCodUf", query = "SELECT c FROM CrpUf c WHERE c.codUf = :codUf"),
    @NamedQuery(name = "CrpUf.findByNomeUf", query = "SELECT c FROM CrpUf c WHERE c.nomeUf = :nomeUf")})
public class CrpUf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cod_uf")
    private String codUf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 532)
    @Column(name = "nome_uf")
    private String nomeUf;
    @OneToMany(mappedBy = "codUf")
    private Collection<CrpEndereco> crpEnderecoCollection;

    public CrpUf() {
    }

    public CrpUf(String codUf) {
        this.codUf = codUf;
    }

    public CrpUf(String codUf, String nomeUf) {
        this.codUf = codUf;
        this.nomeUf = nomeUf;
    }

    public String getCodUf() {
        return codUf;
    }

    public void setCodUf(String codUf) {
        this.codUf = codUf;
    }

    public String getNomeUf() {
        return nomeUf;
    }

    public void setNomeUf(String nomeUf) {
        this.nomeUf = nomeUf;
    }

    @XmlTransient
    public Collection<CrpEndereco> getCrpEnderecoCollection() {
        return crpEnderecoCollection;
    }

    public void setCrpEnderecoCollection(Collection<CrpEndereco> crpEnderecoCollection) {
        this.crpEnderecoCollection = crpEnderecoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUf != null ? codUf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrpUf)) {
            return false;
        }
        CrpUf other = (CrpUf) object;
        if ((this.codUf == null && other.codUf != null) || (this.codUf != null && !this.codUf.equals(other.codUf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.CrpUf[ codUf=" + codUf + " ]";
    }
    
}
