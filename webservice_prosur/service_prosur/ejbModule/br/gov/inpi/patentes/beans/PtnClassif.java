/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.patentes.beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "ptn_classif")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtnClassif.findAll", query = "SELECT p FROM PtnClassif p"),
    @NamedQuery(name = "PtnClassif.findByCodClassificacao", query = "SELECT p FROM PtnClassif p WHERE p.codClassificacao = :codClassificacao"),
    @NamedQuery(name = "PtnClassif.findByCodInternacional", query = "SELECT p FROM PtnClassif p WHERE p.codInternacional = :codInternacional"),
    @NamedQuery(name = "PtnClassif.findByNumVersao", query = "SELECT p FROM PtnClassif p WHERE p.numVersao = :numVersao"),
    @NamedQuery(name = "PtnClassif.findByCdSistemaClassif", query = "SELECT p FROM PtnClassif p WHERE p.cdSistemaClassif = :cdSistemaClassif"),
    @NamedQuery(name = "PtnClassif.findByVersionIndicator", query = "SELECT p FROM PtnClassif p WHERE p.versionIndicator = :versionIndicator")})
public class PtnClassif implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_classificacao")
    private Integer codClassificacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "cod_internacional")
    private String codInternacional;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_versao")
    private short numVersao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cd_sistema_classif")
    private String cdSistemaClassif;
    @Size(max = 20)
    @Column(name = "version_indicator")
    private String versionIndicator;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ptnClassif")
    private Collection<PtnClassifPedido> ptnClassifPedidoCollection;

    public PtnClassif() {
    }

    public PtnClassif(Integer codClassificacao) {
        this.codClassificacao = codClassificacao;
    }

    public PtnClassif(Integer codClassificacao, String codInternacional, short numVersao, String cdSistemaClassif) {
        this.codClassificacao = codClassificacao;
        this.codInternacional = codInternacional;
        this.numVersao = numVersao;
        this.cdSistemaClassif = cdSistemaClassif;
    }

    public Integer getCodClassificacao() {
        return codClassificacao;
    }

    public void setCodClassificacao(Integer codClassificacao) {
        this.codClassificacao = codClassificacao;
    }

    public String getCodInternacional() {
        return codInternacional;
    }

    public void setCodInternacional(String codInternacional) {
        this.codInternacional = codInternacional;
    }

    public short getNumVersao() {
        return numVersao;
    }

    public void setNumVersao(short numVersao) {
        this.numVersao = numVersao;
    }

    public String getCdSistemaClassif() {
        return cdSistemaClassif;
    }

    public void setCdSistemaClassif(String cdSistemaClassif) {
        this.cdSistemaClassif = cdSistemaClassif;
    }

    public String getVersionIndicator() {
        return versionIndicator;
    }

    public void setVersionIndicator(String versionIndicator) {
        this.versionIndicator = versionIndicator;
    }

    @XmlTransient
    public Collection<PtnClassifPedido> getPtnClassifPedidoCollection() {
        return ptnClassifPedidoCollection;
    }

    public void setPtnClassifPedidoCollection(Collection<PtnClassifPedido> ptnClassifPedidoCollection) {
        this.ptnClassifPedidoCollection = ptnClassifPedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codClassificacao != null ? codClassificacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnClassif)) {
            return false;
        }
        PtnClassif other = (PtnClassif) object;
        if ((this.codClassificacao == null && other.codClassificacao != null) || (this.codClassificacao != null && !this.codClassificacao.equals(other.codClassificacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnClassif[ codClassificacao=" + codClassificacao + " ]";
    }
    
}
