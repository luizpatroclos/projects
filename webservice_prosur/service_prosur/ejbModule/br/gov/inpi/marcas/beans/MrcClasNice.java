/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.marcas.beans;

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
@Table(name = "mrc_clas_nice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MrcClasNice.findAll", query = "SELECT m FROM MrcClasNice m"),
    @NamedQuery(name = "MrcClasNice.findByCdNice", query = "SELECT m FROM MrcClasNice m WHERE m.cdNice = :cdNice"),
    @NamedQuery(name = "MrcClasNice.findByCdClasse", query = "SELECT m FROM MrcClasNice m WHERE m.cdClasse = :cdClasse"),
    @NamedQuery(name = "MrcClasNice.findByDsClasse", query = "SELECT m FROM MrcClasNice m WHERE m.dsClasse = :dsClasse"),
    @NamedQuery(name = "MrcClasNice.findByDsNota", query = "SELECT m FROM MrcClasNice m WHERE m.dsNota = :dsNota"),
    @NamedQuery(name = "MrcClasNice.findByNoRevisao", query = "SELECT m FROM MrcClasNice m WHERE m.noRevisao = :noRevisao")})
public class MrcClasNice implements Serializable {
    
	private static final long serialVersionUID = 1L;
    
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_nice")
    private Integer cdNice;
    
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cd_classe")
    private String cdClasse;
    
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 700)
    @Column(name = "ds_classe")
    private String dsClasse;
    
	@Size(max = 700)
    @Column(name = "ds_nota")
    private String dsNota;
    
    @Size(max = 2)
    @Column(name = "no_revisao")
    private String noRevisao;
    
    @OneToMany(mappedBy = "cdNice")
    private Collection<MrcEspecificacao> mrcEspecificacaoCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mrcClasNice")
    private Collection<MrcItemClasNice> mrcItemClasNiceCollection;

    public MrcClasNice() {
    }
    
    
    

    public MrcClasNice(Integer cdNice) {
        this.cdNice = cdNice;
    }

    public MrcClasNice(Integer cdNice, String cdClasse, String dsClasse) {
        this.cdNice = cdNice;
        this.cdClasse = cdClasse;
        this.dsClasse = dsClasse;
    }
    
    
    

    public MrcClasNice(String cdClasse) {
		super();
		this.cdClasse = cdClasse;
	}




	public Integer getCdNice() {
        return cdNice;
    }

    public void setCdNice(Integer cdNice) {
        this.cdNice = cdNice;
    }

    public String getCdClasse() {
        return cdClasse;
    }

    public void setCdClasse(String cdClasse) {
        this.cdClasse = cdClasse;
    }

    public String getDsClasse() {
        return dsClasse;
    }

    public void setDsClasse(String dsClasse) {
        this.dsClasse = dsClasse;
    }

    public String getDsNota() {
        return dsNota;
    }

    public void setDsNota(String dsNota) {
        this.dsNota = dsNota;
    }

    public String getNoRevisao() {
        return noRevisao;
    }

    public void setNoRevisao(String noRevisao) {
        this.noRevisao = noRevisao;
    }

    @XmlTransient
    public Collection<MrcEspecificacao> getMrcEspecificacaoCollection() {
        return mrcEspecificacaoCollection;
    }

    public void setMrcEspecificacaoCollection(Collection<MrcEspecificacao> mrcEspecificacaoCollection) {
        this.mrcEspecificacaoCollection = mrcEspecificacaoCollection;
    }

    @XmlTransient
    public Collection<MrcItemClasNice> getMrcItemClasNiceCollection() {
        return mrcItemClasNiceCollection;
    }

    public void setMrcItemClasNiceCollection(Collection<MrcItemClasNice> mrcItemClasNiceCollection) {
        this.mrcItemClasNiceCollection = mrcItemClasNiceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdNice != null ? cdNice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MrcClasNice)) {
            return false;
        }
        MrcClasNice other = (MrcClasNice) object;
        if ((this.cdNice == null && other.cdNice != null) || (this.cdNice != null && !this.cdNice.equals(other.cdNice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.MrcClasNice[ cdNice=" + cdNice + " ]";
    }
    
}
