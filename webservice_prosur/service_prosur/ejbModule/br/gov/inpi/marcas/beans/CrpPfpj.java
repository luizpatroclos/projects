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
@Table(name = "crp_pfpj")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrpPfpj.findAll", query = "SELECT c FROM CrpPfpj c"),
    @NamedQuery(name = "CrpPfpj.findByCdPfpj", query = "SELECT c FROM CrpPfpj c WHERE c.cdPfpj = :cdPfpj"),
    @NamedQuery(name = "CrpPfpj.findByNoInpi", query = "SELECT c FROM CrpPfpj c WHERE c.noInpi = :noInpi"),
    @NamedQuery(name = "CrpPfpj.findByNoCgcCpf", query = "SELECT c FROM CrpPfpj c WHERE c.noCgcCpf = :noCgcCpf"),
    @NamedQuery(name = "CrpPfpj.findByCdTipoPfpj", query = "SELECT c FROM CrpPfpj c WHERE c.cdTipoPfpj = :cdTipoPfpj"),
    @NamedQuery(name = "CrpPfpj.findByNmCompletPfpj", query = "SELECT c FROM CrpPfpj c WHERE c.nmCompletPfpj = :nmCompletPfpj")})
public class CrpPfpj implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_pfpj")
    private Integer cdPfpj;
    @Size(max = 14)
    @Column(name = "no_inpi")
    private String noInpi;
    @Size(max = 14)
    @Column(name = "no_cgc_cpf")
    private String noCgcCpf;
    @Column(name = "cd_tipo_pfpj")
    private Character cdTipoPfpj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nm_complet_pfpj")
    private String nmCompletPfpj;
    @OneToMany(mappedBy = "cdPfpjCedente")
    private Collection<MrcProtocolo> mrcProtocoloCollection;
    @OneToMany(mappedBy = "cdPfpjCessionario")
    private Collection<MrcProtocolo> mrcProtocoloCollection1;
    @OneToMany(mappedBy = "cdPfpjProcura")
    private Collection<MrcProtocolo> mrcProtocoloCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdPfpjTitular")
    private Collection<MrcProtocolo> mrcProtocoloCollection3;
    @OneToMany(mappedBy = "cdPfpjTitular")
    private Collection<MrcProcesso> mrcProcessoCollection;

    public CrpPfpj() {
    }

    public CrpPfpj(Integer cdPfpj) {
        this.cdPfpj = cdPfpj;
    }

    public CrpPfpj(Integer cdPfpj, String nmCompletPfpj) {
        this.cdPfpj = cdPfpj;
        this.nmCompletPfpj = nmCompletPfpj;
    }

    public Integer getCdPfpj() {
        return cdPfpj;
    }

    public void setCdPfpj(Integer cdPfpj) {
        this.cdPfpj = cdPfpj;
    }

    public String getNoInpi() {
        return noInpi;
    }

    public void setNoInpi(String noInpi) {
        this.noInpi = noInpi;
    }

    public String getNoCgcCpf() {
        return noCgcCpf;
    }

    public void setNoCgcCpf(String noCgcCpf) {
        this.noCgcCpf = noCgcCpf;
    }

    public Character getCdTipoPfpj() {
        return cdTipoPfpj;
    }

    public void setCdTipoPfpj(Character cdTipoPfpj) {
        this.cdTipoPfpj = cdTipoPfpj;
    }

    public String getNmCompletPfpj() {
        return nmCompletPfpj;
    }

    public void setNmCompletPfpj(String nmCompletPfpj) {
        this.nmCompletPfpj = nmCompletPfpj;
    }

    @XmlTransient
    public Collection<MrcProtocolo> getMrcProtocoloCollection() {
        return mrcProtocoloCollection;
    }

    public void setMrcProtocoloCollection(Collection<MrcProtocolo> mrcProtocoloCollection) {
        this.mrcProtocoloCollection = mrcProtocoloCollection;
    }

    @XmlTransient
    public Collection<MrcProtocolo> getMrcProtocoloCollection1() {
        return mrcProtocoloCollection1;
    }

    public void setMrcProtocoloCollection1(Collection<MrcProtocolo> mrcProtocoloCollection1) {
        this.mrcProtocoloCollection1 = mrcProtocoloCollection1;
    }

    @XmlTransient
    public Collection<MrcProtocolo> getMrcProtocoloCollection2() {
        return mrcProtocoloCollection2;
    }

    public void setMrcProtocoloCollection2(Collection<MrcProtocolo> mrcProtocoloCollection2) {
        this.mrcProtocoloCollection2 = mrcProtocoloCollection2;
    }

    @XmlTransient
    public Collection<MrcProtocolo> getMrcProtocoloCollection3() {
        return mrcProtocoloCollection3;
    }

    public void setMrcProtocoloCollection3(Collection<MrcProtocolo> mrcProtocoloCollection3) {
        this.mrcProtocoloCollection3 = mrcProtocoloCollection3;
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
        hash += (cdPfpj != null ? cdPfpj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrpPfpj)) {
            return false;
        }
        CrpPfpj other = (CrpPfpj) object;
        if ((this.cdPfpj == null && other.cdPfpj != null) || (this.cdPfpj != null && !this.cdPfpj.equals(other.cdPfpj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.CrpPfpj[ cdPfpj=" + cdPfpj + " ]";
    }
    
}
