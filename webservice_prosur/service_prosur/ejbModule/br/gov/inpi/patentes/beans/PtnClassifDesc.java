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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "ptn_classif_desc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtnClassifDesc.findAll", query = "SELECT p FROM PtnClassifDesc p"),
    @NamedQuery(name = "PtnClassifDesc.findByCodClassificacao", query = "SELECT p FROM PtnClassifDesc p WHERE p.codClassificacao = :codClassificacao"),
    @NamedQuery(name = "PtnClassifDesc.findByLastUpdate", query = "SELECT p FROM PtnClassifDesc p WHERE p.lastUpdate = :lastUpdate")})
public class PtnClassifDesc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_classificacao")
    private Integer codClassificacao;
    @Lob
    @Size(max = 56)
    @Column(name = "des_classificacao")
    private String desClassificacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    public PtnClassifDesc() {
    }

    public PtnClassifDesc(Integer codClassificacao) {
        this.codClassificacao = codClassificacao;
    }

    public PtnClassifDesc(Integer codClassificacao, Date lastUpdate) {
        this.codClassificacao = codClassificacao;
        this.lastUpdate = lastUpdate;
    }

    public Integer getCodClassificacao() {
        return codClassificacao;
    }

    public void setCodClassificacao(Integer codClassificacao) {
        this.codClassificacao = codClassificacao;
    }

    public String getDesClassificacao() {
        return desClassificacao;
    }

    public void setDesClassificacao(String desClassificacao) {
        this.desClassificacao = desClassificacao;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
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
        if (!(object instanceof PtnClassifDesc)) {
            return false;
        }
        PtnClassifDesc other = (PtnClassifDesc) object;
        if ((this.codClassificacao == null && other.codClassificacao != null) || (this.codClassificacao != null && !this.codClassificacao.equals(other.codClassificacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnClassifDesc[ codClassificacao=" + codClassificacao + " ]";
    }
    
}
