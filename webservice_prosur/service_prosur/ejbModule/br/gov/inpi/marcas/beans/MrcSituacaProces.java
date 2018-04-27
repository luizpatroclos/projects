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
@Table(name = "mrc_situaca_proces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MrcSituacaProces.findAll", query = "SELECT m FROM MrcSituacaProces m"),
    @NamedQuery(name = "MrcSituacaProces.findByCdSituacaProcess", query = "SELECT m FROM MrcSituacaProces m WHERE m.cdSituacaProcess = :cdSituacaProcess"),
    @NamedQuery(name = "MrcSituacaProces.findByDsSituacaProcess", query = "SELECT m FROM MrcSituacaProces m WHERE m.dsSituacaProcess = :dsSituacaProcess"),
    @NamedQuery(name = "MrcSituacaProces.findByDsSituacaAbrevia", query = "SELECT m FROM MrcSituacaProces m WHERE m.dsSituacaAbrevia = :dsSituacaAbrevia"),
    @NamedQuery(name = "MrcSituacaProces.findByStProsur", query = "SELECT m FROM MrcSituacaProces m WHERE m.stProsur = :stProsur"),
    @NamedQuery(name = "MrcSituacaProces.findByDsSituacaPosPrazo", query = "SELECT m FROM MrcSituacaProces m WHERE m.dsSituacaPosPrazo = :dsSituacaPosPrazo"),
    @NamedQuery(name = "MrcSituacaProces.findByCdPrazoSituaca", query = "SELECT m FROM MrcSituacaProces m WHERE m.cdPrazoSituaca = :cdPrazoSituaca"),
    @NamedQuery(name = "MrcSituacaProces.findByDsSituacaExtra", query = "SELECT m FROM MrcSituacaProces m WHERE m.dsSituacaExtra = :dsSituacaExtra"),
    @NamedQuery(name = "MrcSituacaProces.findByCdPrazoExtra", query = "SELECT m FROM MrcSituacaProces m WHERE m.cdPrazoExtra = :cdPrazoExtra")})
public class MrcSituacaProces implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cd_situaca_process")
    private String cdSituacaProcess;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 260)
    @Column(name = "ds_situaca_process")
    private String dsSituacaProcess;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 260)
    @Column(name = "ds_situaca_abrevia")
    private String dsSituacaAbrevia;
    @Size(max = 10)
    @Column(name = "st_prosur")
    private String stProsur;
    @Size(max = 100)
    @Column(name = "ds_situaca_pos_prazo")
    private String dsSituacaPosPrazo;
    @Column(name = "cd_prazo_situaca")
    private Integer cdPrazoSituaca;
    @Size(max = 100)
    @Column(name = "ds_situaca_extra")
    private String dsSituacaExtra;
    @Column(name = "cd_prazo_extra")
    private Integer cdPrazoExtra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdSituacaProcess")
    private Collection<MrcProcesso> mrcProcessoCollection;

    public MrcSituacaProces() {
    }

    public MrcSituacaProces(String cdSituacaProcess) {
        this.cdSituacaProcess = cdSituacaProcess;
    }

    public MrcSituacaProces(String cdSituacaProcess, String dsSituacaProcess, String dsSituacaAbrevia) {
        this.cdSituacaProcess = cdSituacaProcess;
        this.dsSituacaProcess = dsSituacaProcess;
        this.dsSituacaAbrevia = dsSituacaAbrevia;
    }

    public String getCdSituacaProcess() {
        return cdSituacaProcess;
    }

    public void setCdSituacaProcess(String cdSituacaProcess) {
        this.cdSituacaProcess = cdSituacaProcess;
    }

    public String getDsSituacaProcess() {
        return dsSituacaProcess;
    }

    public void setDsSituacaProcess(String dsSituacaProcess) {
        this.dsSituacaProcess = dsSituacaProcess;
    }

    public String getDsSituacaAbrevia() {
        return dsSituacaAbrevia;
    }

    public void setDsSituacaAbrevia(String dsSituacaAbrevia) {
        this.dsSituacaAbrevia = dsSituacaAbrevia;
    }

    public String getStProsur() {
        return stProsur;
    }

    public void setStProsur(String stProsur) {
        this.stProsur = stProsur;
    }

    public String getDsSituacaPosPrazo() {
        return dsSituacaPosPrazo;
    }

    public void setDsSituacaPosPrazo(String dsSituacaPosPrazo) {
        this.dsSituacaPosPrazo = dsSituacaPosPrazo;
    }

    public Integer getCdPrazoSituaca() {
        return cdPrazoSituaca;
    }

    public void setCdPrazoSituaca(Integer cdPrazoSituaca) {
        this.cdPrazoSituaca = cdPrazoSituaca;
    }

    public String getDsSituacaExtra() {
        return dsSituacaExtra;
    }

    public void setDsSituacaExtra(String dsSituacaExtra) {
        this.dsSituacaExtra = dsSituacaExtra;
    }

    public Integer getCdPrazoExtra() {
        return cdPrazoExtra;
    }

    public void setCdPrazoExtra(Integer cdPrazoExtra) {
        this.cdPrazoExtra = cdPrazoExtra;
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
        hash += (cdSituacaProcess != null ? cdSituacaProcess.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MrcSituacaProces)) {
            return false;
        }
        MrcSituacaProces other = (MrcSituacaProces) object;
        if ((this.cdSituacaProcess == null && other.cdSituacaProcess != null) || (this.cdSituacaProcess != null && !this.cdSituacaProcess.equals(other.cdSituacaProcess))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.MrcSituacaProces[ cdSituacaProcess=" + cdSituacaProcess + " ]";
    }
    
}
