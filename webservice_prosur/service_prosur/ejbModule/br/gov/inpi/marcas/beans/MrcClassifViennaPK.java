/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.marcas.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tgouvea
 */
@Embeddable
public class MrcClassifViennaPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name = "cd_vienna")
    private int cdVienna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_process")
    private int cdProcess;

    public MrcClassifViennaPK() {
    }

    public MrcClassifViennaPK(int cdVienna, int cdProcess) {
        this.cdVienna = cdVienna;
        this.cdProcess = cdProcess;
    }

    public int getCdVienna() {
        return cdVienna;
    }

    public void setCdVienna(int cdVienna) {
        this.cdVienna = cdVienna;
    }

    public int getCdProcess() {
        return cdProcess;
    }

    public void setCdProcess(int cdProcess) {
        this.cdProcess = cdProcess;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cdVienna;
        hash += (int) cdProcess;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MrcClassifViennaPK)) {
            return false;
        }
        MrcClassifViennaPK other = (MrcClassifViennaPK) object;
        if (this.cdVienna != other.cdVienna) {
            return false;
        }
        if (this.cdProcess != other.cdProcess) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.MrcClassifViennaPK[ cdVienna=" + cdVienna + ", cdProcess=" + cdProcess + " ]";
    }
    
}
