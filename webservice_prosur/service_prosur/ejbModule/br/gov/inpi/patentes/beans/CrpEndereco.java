/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.patentes.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "crp_endereco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrpEndereco.findAll", query = "SELECT c FROM CrpEndereco c"),
    @NamedQuery(name = "CrpEndereco.findByCodPessoa", query = "SELECT c FROM CrpEndereco c WHERE c.codPessoa = :codPessoa"),
    @NamedQuery(name = "CrpEndereco.findByNomLogradouro", query = "SELECT c FROM CrpEndereco c WHERE c.nomLogradouro = :nomLogradouro"),
    @NamedQuery(name = "CrpEndereco.findByNomBairro", query = "SELECT c FROM CrpEndereco c WHERE c.nomBairro = :nomBairro"),
    @NamedQuery(name = "CrpEndereco.findByNomCidade", query = "SELECT c FROM CrpEndereco c WHERE c.nomCidade = :nomCidade"),
    @NamedQuery(name = "CrpEndereco.findByNumCep", query = "SELECT c FROM CrpEndereco c WHERE c.numCep = :numCep"),
    @NamedQuery(name = "CrpEndereco.findByNumTelefone", query = "SELECT c FROM CrpEndereco c WHERE c.numTelefone = :numTelefone"),
    @NamedQuery(name = "CrpEndereco.findByNumFax", query = "SELECT c FROM CrpEndereco c WHERE c.numFax = :numFax"),
    @NamedQuery(name = "CrpEndereco.findByNomEmail", query = "SELECT c FROM CrpEndereco c WHERE c.nomEmail = :nomEmail")})
public class CrpEndereco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_pessoa")
    private Integer codPessoa;
    @Size(max = 200)
    @Column(name = "nom_logradouro")
    private String nomLogradouro;
    @Size(max = 30)
    @Column(name = "nom_bairro")
    private String nomBairro;
    @Size(max = 30)
    @Column(name = "nom_cidade")
    private String nomCidade;
    @Size(max = 15)
    @Column(name = "num_cep")
    private String numCep;
    @Size(max = 20)
    @Column(name = "num_telefone")
    private String numTelefone;
    @Size(max = 20)
    @Column(name = "num_fax")
    private String numFax;
    @Size(max = 255)
    @Column(name = "nom_email")
    private String nomEmail;
    @JoinColumn(name = "cod_uf", referencedColumnName = "cod_uf")
    @ManyToOne
    private CrpUf codUf;
    @JoinColumn(name = "cod_pais", referencedColumnName = "cod_pais")
    @ManyToOne
    private CrpPais codPais;

    public CrpEndereco() {
    }

    public CrpEndereco(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public Integer getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getNomLogradouro() {
        return nomLogradouro;
    }

    public void setNomLogradouro(String nomLogradouro) {
        this.nomLogradouro = nomLogradouro;
    }

    public String getNomBairro() {
        return nomBairro;
    }

    public void setNomBairro(String nomBairro) {
        this.nomBairro = nomBairro;
    }

    public String getNomCidade() {
        return nomCidade;
    }

    public void setNomCidade(String nomCidade) {
        this.nomCidade = nomCidade;
    }

    public String getNumCep() {
        return numCep;
    }

    public void setNumCep(String numCep) {
        this.numCep = numCep;
    }

    public String getNumTelefone() {
        return numTelefone;
    }

    public void setNumTelefone(String numTelefone) {
        this.numTelefone = numTelefone;
    }

    public String getNumFax() {
        return numFax;
    }

    public void setNumFax(String numFax) {
        this.numFax = numFax;
    }

    public String getNomEmail() {
        return nomEmail;
    }

    public void setNomEmail(String nomEmail) {
        this.nomEmail = nomEmail;
    }

    public CrpUf getCodUf() {
        return codUf;
    }

    public void setCodUf(CrpUf codUf) {
        this.codUf = codUf;
    }

    public CrpPais getCodPais() {
        return codPais;
    }

    public void setCodPais(CrpPais codPais) {
        this.codPais = codPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPessoa != null ? codPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrpEndereco)) {
            return false;
        }
        CrpEndereco other = (CrpEndereco) object;
        if ((this.codPessoa == null && other.codPessoa != null) || (this.codPessoa != null && !this.codPessoa.equals(other.codPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.CrpEndereco[ codPessoa=" + codPessoa + " ]";
    }
    
}
