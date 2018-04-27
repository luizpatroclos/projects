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
@Table(name = "mrc_despacho")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MrcDespacho.findAll", query = "SELECT m FROM MrcDespacho m"),
    @NamedQuery(name = "MrcDespacho.findByCdDespach", query = "SELECT m FROM MrcDespacho m WHERE m.cdDespach = :cdDespach"),
    @NamedQuery(name = "MrcDespacho.findByCdProcess", query = "SELECT m FROM MrcDespacho m WHERE m.cdProcess = :cdProcess"),
    @NamedQuery(name = "MrcDespacho.findByCdSituacaProces", query = "SELECT m FROM MrcDespacho m WHERE m.cdSituacaProces = :cdSituacaProces"),
    @NamedQuery(name = "MrcDespacho.findByNumprotocolo", query = "SELECT m FROM MrcDespacho m WHERE m.numprotocolo = :numprotocolo"),
    @NamedQuery(name = "MrcDespacho.findByCdTipoDocumento", query = "SELECT m FROM MrcDespacho m WHERE m.cdTipoDocumento = :cdTipoDocumento")})
public class MrcDespacho implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_despach")
    private Integer cdDespach;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_process")
    private int cdProcess;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cd_situaca_proces")
    private String cdSituacaProces;
    @Lob
    @Size(max = 10000)
    @Column(name = "tx_despach_publica")
    private String txDespachPublica;
    @Lob
    @Size(max = 10000)
    @Column(name = "tx_despach_basico")
    private String txDespachBasico;
    @Size(max = 30)
    @Column(name = "numprotocolo")
    private String numprotocolo;
    @Column(name = "cd_tipo_documento")
    private Integer cdTipoDocumento;
   
    @Column(name = "cd_tipo_despach")
    private Integer cdTipoDespach;
  
    @Column(name = "cd_protocolo")
    private Integer cdProtocolo;
  
    @Column(name = "no_rpi")
    private Short noRpi;

    public MrcDespacho() {
    }

    public MrcDespacho(Integer cdDespach) {
        this.cdDespach = cdDespach;
    }

    public MrcDespacho(Integer cdDespach, int cdProcess, String cdSituacaProces) {
        this.cdDespach = cdDespach;
        this.cdProcess = cdProcess;
        this.cdSituacaProces = cdSituacaProces;
    }

    public Integer getCdDespach() {
        return cdDespach;
    }

    public void setCdDespach(Integer cdDespach) {
        this.cdDespach = cdDespach;
    }

    public int getCdProcess() {
        return cdProcess;
    }

    public void setCdProcess(int cdProcess) {
        this.cdProcess = cdProcess;
    }

    public String getCdSituacaProces() {
        return cdSituacaProces;
    }

    public void setCdSituacaProces(String cdSituacaProces) {
        this.cdSituacaProces = cdSituacaProces;
    }

    public String getTxDespachPublica() {
        return txDespachPublica;
    }

    public void setTxDespachPublica(String txDespachPublica) {
        this.txDespachPublica = txDespachPublica;
    }

    public String getTxDespachBasico() {
        return txDespachBasico;
    }

    public void setTxDespachBasico(String txDespachBasico) {
        this.txDespachBasico = txDespachBasico;
    }

    public String getNumprotocolo() {
        return numprotocolo;
    }

    public void setNumprotocolo(String numprotocolo) {
        this.numprotocolo = numprotocolo;
    }

    public Integer getCdTipoDocumento() {
        return cdTipoDocumento;
    }

    public void setCdTipoDocumento(Integer cdTipoDocumento) {
        this.cdTipoDocumento = cdTipoDocumento;
    }
    
    /**
	 * @return the cdTipoDespach
	 */
	public Integer getCdTipoDespach() {
		return cdTipoDespach;
	}

	/**
	 * @param cdTipoDespach the cdTipoDespach to set
	 */
	public void setCdTipoDespach(Integer cdTipoDespach) {
		this.cdTipoDespach = cdTipoDespach;
	}

	/**
	 * @return the cdProtocolo
	 */
	public Integer getCdProtocolo() {
		return cdProtocolo;
	}

	/**
	 * @param cdProtocolo the cdProtocolo to set
	 */
	public void setCdProtocolo(Integer cdProtocolo) {
		this.cdProtocolo = cdProtocolo;
	}

	/**
	 * @return the noRpi
	 */
	public Short getNoRpi() {
		return noRpi;
	}

	/**
	 * @param noRpi the noRpi to set
	 */
	public void setNoRpi(Short noRpi) {
		this.noRpi = noRpi;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (cdDespach != null ? cdDespach.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
    	
        if (!(object instanceof MrcDespacho)) {
            return false;
        }
        MrcDespacho other = (MrcDespacho) object;
        if ((this.cdDespach == null && other.cdDespach != null) || (this.cdDespach != null && !this.cdDespach.equals(other.cdDespach))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "MrcDespacho [cdDespach=" + cdDespach + ", cdProcess="
				+ cdProcess + ", cdSituacaProces=" + cdSituacaProces
				+ ", txDespachPublica=" + txDespachPublica
				+ ", txDespachBasico=" + txDespachBasico + ", numprotocolo="
				+ numprotocolo + ", cdTipoDocumento=" + cdTipoDocumento
				+ ", cdTipoDespach=" + cdTipoDespach + ", cdProtocolo="
				+ cdProtocolo + ", noRpi=" + noRpi + "]";
	}

    
    
}
