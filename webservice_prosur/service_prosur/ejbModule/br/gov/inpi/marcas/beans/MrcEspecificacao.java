/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.marcas.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "mrc_especificacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MrcEspecificacao.findAll", query = "SELECT m FROM MrcEspecificacao m"),
    @NamedQuery(name = "MrcEspecificacao.findByCdEspecif", query = "SELECT m FROM MrcEspecificacao m WHERE m.cdEspecif = :cdEspecif"),
    @NamedQuery(name = "MrcEspecificacao.findByCdProcess", query = "SELECT m FROM MrcEspecificacao m WHERE m.cdProcess = :cdProcess")})
public class MrcEspecificacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_especif")
    private Integer cdEspecif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_process")
    private int cdProcess;
    
    @Size(max = 56)
    @Column(name = "tx_especif")
    private String txEspecif;
    @JoinColumn(name = "cd_nice", referencedColumnName = "cd_nice")
    @ManyToOne
    private MrcClasNice cdNice;

    public MrcEspecificacao() {
    }

    public MrcEspecificacao(Integer cdEspecif) {
        this.cdEspecif = cdEspecif;
    }

    public MrcEspecificacao(Integer cdEspecif, int cdProcess) {
        this.cdEspecif = cdEspecif;
        this.cdProcess = cdProcess;
    }

    public Integer getCdEspecif() {
        return cdEspecif;
    }

    public void setCdEspecif(Integer cdEspecif) {
        this.cdEspecif = cdEspecif;
    }

    public int getCdProcess() {
        return cdProcess;
    }

    public void setCdProcess(int cdProcess) {
        this.cdProcess = cdProcess;
    }

    public String getTxEspecif() {
        return txEspecif;
    }

    public void setTxEspecif(String txEspecif) {
        this.txEspecif = txEspecif;
    }

    public MrcClasNice getCdNice() {
        return cdNice;
    }

    public void setCdNice(MrcClasNice cdNice) {
        this.cdNice = cdNice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdEspecif != null ? cdEspecif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MrcEspecificacao)) {
            return false;
        }
        MrcEspecificacao other = (MrcEspecificacao) object;
        if ((this.cdEspecif == null && other.cdEspecif != null) || (this.cdEspecif != null && !this.cdEspecif.equals(other.cdEspecif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.MrcEspecificacao[ cdEspecif=" + cdEspecif + " ]";
    }
    
}
