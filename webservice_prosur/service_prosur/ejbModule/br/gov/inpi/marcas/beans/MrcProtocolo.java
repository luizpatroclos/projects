/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.marcas.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "mrc_protocolo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MrcProtocolo.findAll", query = "SELECT m FROM MrcProtocolo m"),
    @NamedQuery(name = "MrcProtocolo.findByCdProtocolo", query = "SELECT m FROM MrcProtocolo m WHERE m.cdProtocolo = :cdProtocolo"),
    @NamedQuery(name = "MrcProtocolo.findByDtProtocolo", query = "SELECT m FROM MrcProtocolo m WHERE m.dtProtocolo = :dtProtocolo"),
    @NamedQuery(name = "MrcProtocolo.findByNumprotocolo", query = "SELECT m FROM MrcProtocolo m WHERE m.numprotocolo = :numprotocolo"),
    @NamedQuery(name = "MrcProtocolo.findByNumprotocoloafetado", query = "SELECT m FROM MrcProtocolo m WHERE m.numprotocoloafetado = :numprotocoloafetado"),
    @NamedQuery(name = "MrcProtocolo.findByCodServico", query = "SELECT m FROM MrcProtocolo m WHERE m.codServico = :codServico")})
public class MrcProtocolo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_protocolo")
    private Integer cdProtocolo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_protocolo")
    @Temporal(TemporalType.DATE)
    private Date dtProtocolo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "numprotocolo")
    private String numprotocolo;
    @Size(max = 30)
    @Column(name = "numprotocoloafetado")
    private String numprotocoloafetado;
    @Size(max = 6)
    @Column(name = "cod_servico")
    private String codServico;
    @JoinColumn(name = "cd_pfpj_cedente", referencedColumnName = "cd_pfpj")
    @ManyToOne
    private CrpPfpj cdPfpjCedente;
    @JoinColumn(name = "cd_pfpj_cessionario", referencedColumnName = "cd_pfpj")
    @ManyToOne
    private CrpPfpj cdPfpjCessionario;
    @JoinColumn(name = "cd_pfpj_procura", referencedColumnName = "cd_pfpj")
    @ManyToOne
    private CrpPfpj cdPfpjProcura;
    @JoinColumn(name = "cd_pfpj_titular", referencedColumnName = "cd_pfpj")
    @ManyToOne(optional = false)
    private CrpPfpj cdPfpjTitular;
    @OneToMany(mappedBy = "cdProtocolo")
    private Collection<MrcDespacho> mrcDespachoCollection;

    public MrcProtocolo() {
    }

    public MrcProtocolo(Integer cdProtocolo) {
        this.cdProtocolo = cdProtocolo;
    }

    public MrcProtocolo(Integer cdProtocolo, Date dtProtocolo, String numprotocolo) {
        this.cdProtocolo = cdProtocolo;
        this.dtProtocolo = dtProtocolo;
        this.numprotocolo = numprotocolo;
    }

    public Integer getCdProtocolo() {
        return cdProtocolo;
    }

    public void setCdProtocolo(Integer cdProtocolo) {
        this.cdProtocolo = cdProtocolo;
    }

    public Date getDtProtocolo() {
        return dtProtocolo;
    }

    public void setDtProtocolo(Date dtProtocolo) {
        this.dtProtocolo = dtProtocolo;
    }

    public String getNumprotocolo() {
        return numprotocolo;
    }

    public void setNumprotocolo(String numprotocolo) {
        this.numprotocolo = numprotocolo;
    }

    public String getNumprotocoloafetado() {
        return numprotocoloafetado;
    }

    public void setNumprotocoloafetado(String numprotocoloafetado) {
        this.numprotocoloafetado = numprotocoloafetado;
    }

    public String getCodServico() {
        return codServico;
    }

    public void setCodServico(String codServico) {
        this.codServico = codServico;
    }

    public CrpPfpj getCdPfpjCedente() {
        return cdPfpjCedente;
    }

    public void setCdPfpjCedente(CrpPfpj cdPfpjCedente) {
        this.cdPfpjCedente = cdPfpjCedente;
    }

    public CrpPfpj getCdPfpjCessionario() {
        return cdPfpjCessionario;
    }

    public void setCdPfpjCessionario(CrpPfpj cdPfpjCessionario) {
        this.cdPfpjCessionario = cdPfpjCessionario;
    }

    public CrpPfpj getCdPfpjProcura() {
        return cdPfpjProcura;
    }

    public void setCdPfpjProcura(CrpPfpj cdPfpjProcura) {
        this.cdPfpjProcura = cdPfpjProcura;
    }

    public CrpPfpj getCdPfpjTitular() {
        return cdPfpjTitular;
    }

    public void setCdPfpjTitular(CrpPfpj cdPfpjTitular) {
        this.cdPfpjTitular = cdPfpjTitular;
    }

    @XmlTransient
    public Collection<MrcDespacho> getMrcDespachoCollection() {
        return mrcDespachoCollection;
    }

    public void setMrcDespachoCollection(Collection<MrcDespacho> mrcDespachoCollection) {
        this.mrcDespachoCollection = mrcDespachoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdProtocolo != null ? cdProtocolo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MrcProtocolo)) {
            return false;
        }
        MrcProtocolo other = (MrcProtocolo) object;
        if ((this.cdProtocolo == null && other.cdProtocolo != null) || (this.cdProtocolo != null && !this.cdProtocolo.equals(other.cdProtocolo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.marcas.model.MrcProtocolo[ cdProtocolo=" + cdProtocolo + " ]";
    }
    
}
