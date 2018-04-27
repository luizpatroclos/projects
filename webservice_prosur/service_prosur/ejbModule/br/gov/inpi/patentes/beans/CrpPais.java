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
@Table(name = "crp_pais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrpPais.findAll", query = "SELECT c FROM CrpPais c"),
    @NamedQuery(name = "CrpPais.findByCodPais", query = "SELECT c FROM CrpPais c WHERE c.codPais = :codPais"),
    @NamedQuery(name = "CrpPais.findByNomePais", query = "SELECT c FROM CrpPais c WHERE c.nomePais = :nomePais")})
public class CrpPais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cod_pais")
    private String codPais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nome_pais")
    private String nomePais;
    @OneToMany(mappedBy = "codPais")
    private Collection<CrpEndereco> crpEnderecoCollection;

    public CrpPais() {
    }

    public CrpPais(String codPais) {
        this.codPais = codPais;
    }

    public CrpPais(String codPais, String nomePais) {
        this.codPais = codPais;
        this.nomePais = nomePais;
    }

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
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
        hash += (codPais != null ? codPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrpPais)) {
            return false;
        }
        CrpPais other = (CrpPais) object;
        if ((this.codPais == null && other.codPais != null) || (this.codPais != null && !this.codPais.equals(other.codPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.CrpPais[ codPais=" + codPais + " ]";
    }
    
}
