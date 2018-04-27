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
import javax.persistence.Lob;
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
@Table(name = "mrc_figura_process")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MrcFiguraProcess.findAll", query = "SELECT m FROM MrcFiguraProcess m"),
    @NamedQuery(name = "MrcFiguraProcess.findByCdProcess", query = "SELECT m FROM MrcFiguraProcess m WHERE m.cdProcess = :cdProcess"),
    @NamedQuery(name = "MrcFiguraProcess.findByTipoFigura", query = "SELECT m FROM MrcFiguraProcess m WHERE m.tipoFigura = :tipoFigura")})
public class MrcFiguraProcess implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_process")
    private Integer cdProcess;
    @Lob
    @Column(name = "im_process_figura")
    private byte[] imProcessFigura;
    @Size(max = 3)
    @Column(name = "tipo_figura")
    private String tipoFigura;

    public MrcFiguraProcess() {
    }

    public MrcFiguraProcess(Integer cdProcess) {
        this.cdProcess = cdProcess;
    }

    public Integer getCdProcess() {
        return cdProcess;
    }

    public void setCdProcess(Integer cdProcess) {
        this.cdProcess = cdProcess;
    }

    public byte[] getImProcessFigura() {
        return imProcessFigura;
    }

    public void setImProcessFigura(byte[] imProcessFigura) {
        this.imProcessFigura = imProcessFigura;
    }

    public String getTipoFigura() {
        return tipoFigura;
    }

    public void setTipoFigura(String tipoFigura) {
        this.tipoFigura = tipoFigura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdProcess != null ? cdProcess.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MrcFiguraProcess)) {
            return false;
        }
        MrcFiguraProcess other = (MrcFiguraProcess) object;
        if ((this.cdProcess == null && other.cdProcess != null) || (this.cdProcess != null && !this.cdProcess.equals(other.cdProcess))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.MrcFiguraProcess[ cdProcess=" + cdProcess + " ]";
    }
    
}
