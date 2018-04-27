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
@Table(name = "crp_pes_requerente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrpPesRequerente.findAll", query = "SELECT c FROM CrpPesRequerente c"),
    @NamedQuery(name = "CrpPesRequerente.findByCodPessoa", query = "SELECT c FROM CrpPesRequerente c WHERE c.codPessoa = :codPessoa"),
    @NamedQuery(name = "CrpPesRequerente.findByNumCgcCpf", query = "SELECT c FROM CrpPesRequerente c WHERE c.numCgcCpf = :numCgcCpf"),
    @NamedQuery(name = "CrpPesRequerente.findByTipoPessoa", query = "SELECT c FROM CrpPesRequerente c WHERE c.tipoPessoa = :tipoPessoa"),
    @NamedQuery(name = "CrpPesRequerente.findByNomeCompleto", query = "SELECT c FROM CrpPesRequerente c WHERE c.nomeCompleto = :nomeCompleto")})
public class CrpPesRequerente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_pessoa")
    private Integer codPessoa;
    @Size(max = 14)
    @Column(name = "num_cgc_cpf")
    private String numCgcCpf;
    @Column(name = "tipo_pessoa")
    private Character tipoPessoa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nome_completo")
    private String nomeCompleto;

    public CrpPesRequerente() {
    }

    public CrpPesRequerente(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public CrpPesRequerente(Integer codPessoa, String nomeCompleto) {
        this.codPessoa = codPessoa;
        this.nomeCompleto = nomeCompleto;
    }

    public Integer getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getNumCgcCpf() {
        return numCgcCpf;
    }

    public void setNumCgcCpf(String numCgcCpf) {
        this.numCgcCpf = numCgcCpf;
    }

    public Character getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Character tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
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
        if (!(object instanceof CrpPesRequerente)) {
            return false;
        }
        CrpPesRequerente other = (CrpPesRequerente) object;
        if ((this.codPessoa == null && other.codPessoa != null) || (this.codPessoa != null && !this.codPessoa.equals(other.codPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.CrpPesRequerente[ codPessoa=" + codPessoa + " ]";
    }
    
}
