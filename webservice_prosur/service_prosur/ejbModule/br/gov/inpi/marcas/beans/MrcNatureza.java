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
@Table(name = "mrc_natureza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MrcNatureza.findAll", query = "SELECT m FROM MrcNatureza m"),
    @NamedQuery(name = "MrcNatureza.findByCdNaturezMarca", query = "SELECT m FROM MrcNatureza m WHERE m.cdNaturezMarca = :cdNaturezMarca"),
    @NamedQuery(name = "MrcNatureza.findByDsNaturezMarca", query = "SELECT m FROM MrcNatureza m WHERE m.dsNaturezMarca = :dsNaturezMarca")})
public class MrcNatureza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "cd_naturez_marca")
    private String cdNaturezMarca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ds_naturez_marca")
    private String dsNaturezMarca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdNaturezMarca")
    private Collection<MrcProcesso> mrcProcessoCollection;

    public MrcNatureza() {
    }

    public MrcNatureza(String cdNaturezMarca) {
        this.cdNaturezMarca = cdNaturezMarca;
    }

    public MrcNatureza(String cdNaturezMarca, String dsNaturezMarca) {
        this.cdNaturezMarca = cdNaturezMarca;
        this.dsNaturezMarca = dsNaturezMarca;
    }

    public String getCdNaturezMarca() {
        return cdNaturezMarca;
    }

    public void setCdNaturezMarca(String cdNaturezMarca) {
        this.cdNaturezMarca = cdNaturezMarca;
    }

    public String getDsNaturezMarca() {
        return dsNaturezMarca;
    }

    public void setDsNaturezMarca(String dsNaturezMarca) {
        this.dsNaturezMarca = dsNaturezMarca;
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
        hash += (cdNaturezMarca != null ? cdNaturezMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MrcNatureza)) {
            return false;
        }
        MrcNatureza other = (MrcNatureza) object;
        if ((this.cdNaturezMarca == null && other.cdNaturezMarca != null) || (this.cdNaturezMarca != null && !this.cdNaturezMarca.equals(other.cdNaturezMarca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.MrcNatureza[ cdNaturezMarca=" + cdNaturezMarca + " ]";
    }
    
}
