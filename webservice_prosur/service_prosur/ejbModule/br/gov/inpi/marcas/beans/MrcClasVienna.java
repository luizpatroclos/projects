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
@Table(name = "mrc_clas_vienna")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MrcClasVienna.findAll", query = "SELECT m FROM MrcClasVienna m"),
    @NamedQuery(name = "MrcClasVienna.findByCdVienna", query = "SELECT m FROM MrcClasVienna m WHERE m.cdVienna = :cdVienna"),
    @NamedQuery(name = "MrcClasVienna.findByCdClasVienna", query = "SELECT m FROM MrcClasVienna m WHERE m.cdClasVienna = :cdClasVienna"),
    @NamedQuery(name = "MrcClasVienna.findByDsNota", query = "SELECT m FROM MrcClasVienna m WHERE m.dsNota = :dsNota"),
    @NamedQuery(name = "MrcClasVienna.findByCdComplem", query = "SELECT m FROM MrcClasVienna m WHERE m.cdComplem = :cdComplem"),
    @NamedQuery(name = "MrcClasVienna.findByDsVienna", query = "SELECT m FROM MrcClasVienna m WHERE m.dsVienna = :dsVienna"),
    @NamedQuery(name = "MrcClasVienna.findByNoRevisao", query = "SELECT m FROM MrcClasVienna m WHERE m.noRevisao = :noRevisao")})
public class MrcClasVienna implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_vienna")
    private Integer cdVienna;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cd_clas_vienna")
    private String cdClasVienna;
    @Size(max = 500)
    @Column(name = "ds_nota")
    private String dsNota;
    @Column(name = "cd_complem")
    private Character cdComplem;
    @Size(max = 255)
    @Column(name = "ds_vienna")
    private String dsVienna;
    @Size(max = 18)
    @Column(name = "no_revisao")
    private String noRevisao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mrcClasVienna")
    private Collection<MrcClassifVienna> mrcClassifViennaCollection;

    public MrcClasVienna() {
    }

    public MrcClasVienna(Integer cdVienna) {
        this.cdVienna = cdVienna;
    }

    public MrcClasVienna(Integer cdVienna, String cdClasVienna) {
        this.cdVienna = cdVienna;
        this.cdClasVienna = cdClasVienna;
    }

    public Integer getCdVienna() {
        return cdVienna;
    }

    public void setCdVienna(Integer cdVienna) {
        this.cdVienna = cdVienna;
    }

    public String getCdClasVienna() {
        return cdClasVienna;
    }

    public void setCdClasVienna(String cdClasVienna) {
        this.cdClasVienna = cdClasVienna;
    }

    public String getDsNota() {
        return dsNota;
    }

    public void setDsNota(String dsNota) {
        this.dsNota = dsNota;
    }

    public Character getCdComplem() {
        return cdComplem;
    }

    public void setCdComplem(Character cdComplem) {
        this.cdComplem = cdComplem;
    }

    public String getDsVienna() {
        return dsVienna;
    }

    public void setDsVienna(String dsVienna) {
        this.dsVienna = dsVienna;
    }

    public String getNoRevisao() {
        return noRevisao;
    }

    public void setNoRevisao(String noRevisao) {
        this.noRevisao = noRevisao;
    }

    @XmlTransient
    public Collection<MrcClassifVienna> getMrcClassifViennaCollection() {
        return mrcClassifViennaCollection;
    }

    public void setMrcClassifViennaCollection(Collection<MrcClassifVienna> mrcClassifViennaCollection) {
        this.mrcClassifViennaCollection = mrcClassifViennaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdVienna != null ? cdVienna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MrcClasVienna)) {
            return false;
        }
        MrcClasVienna other = (MrcClasVienna) object;
        if ((this.cdVienna == null && other.cdVienna != null) || (this.cdVienna != null && !this.cdVienna.equals(other.cdVienna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.MrcClasVienna[ cdVienna=" + cdVienna + " ]";
    }
    
}
