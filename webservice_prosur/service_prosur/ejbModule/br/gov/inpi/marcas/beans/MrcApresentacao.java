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
@Table(name = "mrc_apresentacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MrcApresentacao.findAll", query = "SELECT m FROM MrcApresentacao m"),
    @NamedQuery(name = "MrcApresentacao.findByCdApresenMarca", query = "SELECT m FROM MrcApresentacao m WHERE m.cdApresenMarca = :cdApresenMarca"),
    @NamedQuery(name = "MrcApresentacao.findByDsApresenMarca", query = "SELECT m FROM MrcApresentacao m WHERE m.dsApresenMarca = :dsApresenMarca")})
public class MrcApresentacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "cd_apresen_marca")
    private String cdApresenMarca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ds_apresen_marca")
    private String dsApresenMarca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdApresenMarca")
    private Collection<MrcProcesso> mrcProcessoCollection;

    public MrcApresentacao() {
    }

    public MrcApresentacao(String cdApresenMarca) {
        this.cdApresenMarca = cdApresenMarca;
    }

    public MrcApresentacao(String cdApresenMarca, String dsApresenMarca) {
        this.cdApresenMarca = cdApresenMarca;
        this.dsApresenMarca = dsApresenMarca;
    }

    public String getCdApresenMarca() {
        return cdApresenMarca;
    }

    public void setCdApresenMarca(String cdApresenMarca) {
        this.cdApresenMarca = cdApresenMarca;
    }

    public String getDsApresenMarca() {
        return dsApresenMarca;
    }

    public void setDsApresenMarca(String dsApresenMarca) {
        this.dsApresenMarca = dsApresenMarca;
    }

    @XmlTransient
    public Collection<MrcProcesso> getMrcProcessoCollection() {
        return mrcProcessoCollection;
    }

    public void setMrcProcessoCollection(Collection<MrcProcesso> mrcProcessoCollection) {
        this.mrcProcessoCollection = mrcProcessoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdApresenMarca != null ? cdApresenMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MrcApresentacao)) {
            return false;
        }
        MrcApresentacao other = (MrcApresentacao) object;
        if ((this.cdApresenMarca == null && other.cdApresenMarca != null) || (this.cdApresenMarca != null && !this.cdApresenMarca.equals(other.cdApresenMarca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.MrcApresentacao[ cdApresenMarca=" + cdApresenMarca + " ]";
    }
    
}
