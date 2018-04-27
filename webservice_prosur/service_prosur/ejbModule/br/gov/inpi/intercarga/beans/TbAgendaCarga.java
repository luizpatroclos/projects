/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.intercarga.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "tb_agenda_carga")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TbAgendaCarga.findAll", query = "SELECT t FROM TbAgendaCarga t WHERE (t.tpAgenda = :manual AND t.stAgenda = :s "
		    + " OR t.tpAgenda = :manual AND t.stAgenda = :n) "
		    + " OR tpAgenda = :automatico AND t.stAgenda = :n "
		    + " OR t.tpAgenda = :automatico AND t.stAgenda = :s)"),
	@NamedQuery(name = "TbAgendaCarga.findByIdAgendaCarga", query = "SELECT t FROM TbAgendaCarga t WHERE t.idAgenda = :idAgenda"),
	@NamedQuery(name = "TbAgendaCarga.findAtivos", query = "SELECT t FROM TbAgendaCarga t WHERE t.stAgenda = :stAgenda")})
public class TbAgendaCarga implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "id_agenda", nullable = false)
    private Integer idAgenda;
    
    @Column(name = "tp_agenda")
    private String tpAgenda;
    
    @Column(name = "st_agenda")
    private String stAgenda;
    
    @Column(name = "dh_agenda")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhAgenda;
    
    @Column(name = "tp_carga")
    private String tpCarga;
    
    @Column(name = "tp_base")
    private String tpBase;
    @JoinColumn(name = "id_login", referencedColumnName = "id_login")
    @ManyToOne
    private TbAcessoCarga idLogin;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgenda", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<TbCargaRetroativa> tbCargaRetroativaList;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "idAgenda", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<TbCargaSemanal> tbCargaSemanalList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgenda", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<TbHistoricoCarga> tbHistoricoCargaList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgenda", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<TbCargaProcesso> tbCargaProcessoList;

    public TbAgendaCarga() {
    }

    public TbAgendaCarga(Integer idAgenda) {
        this.idAgenda = idAgenda;
    }

    public TbAgendaCarga(Integer idAgenda, String tpAgenda, String stAgenda, Date dhAgenda, String tpCarga) {
        this.idAgenda = idAgenda;
        this.tpAgenda = tpAgenda;
        this.stAgenda = stAgenda;
        this.dhAgenda = dhAgenda;
        this.tpCarga = tpCarga;
    }

    public Integer getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Integer idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getTpAgenda() {
        return tpAgenda;
    }

    public void setTpAgenda(String tpAgenda) {
        this.tpAgenda = tpAgenda;
    }

    public String getStAgenda() {
        return stAgenda;
    }

    public void setStAgenda(String stAgenda) {
        this.stAgenda = stAgenda;
    }

    public Date getDhAgenda() {
        return dhAgenda;
    }

    public void setDhAgenda(Date dhAgenda) {
        this.dhAgenda = dhAgenda;
    }

    public String getTpCarga() {
        return tpCarga;
    }

    public void setTpCarga(String tpCarga) {
        this.tpCarga = tpCarga;
    }

    public String getTpBase() {
        return tpBase;
    }

    public void setTpBase(String tpBase) {
        this.tpBase = tpBase;
    }

    public TbAcessoCarga getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(TbAcessoCarga idLogin) {
        this.idLogin = idLogin;
    }

    @XmlTransient
    public List<TbCargaRetroativa> getTbCargaRetroativaList() {
        return tbCargaRetroativaList;
    }

    public void setTbCargaRetroativaList(List<TbCargaRetroativa> tbCargaRetroativaList) {
        this.tbCargaRetroativaList = tbCargaRetroativaList;
    }

    @XmlTransient
    public List<TbCargaSemanal> getTbCargaSemanalList() {
        return tbCargaSemanalList;
    }

    public void setTbCargaSemanalList(List<TbCargaSemanal> tbCargaSemanalList) {
        this.tbCargaSemanalList = tbCargaSemanalList;
    }

    @XmlTransient
    public List<TbHistoricoCarga> getTbHistoricoCargaList() {
        return tbHistoricoCargaList;
    }

    public void setTbHistoricoCargaList(List<TbHistoricoCarga> tbHistoricoCargaList) {
        this.tbHistoricoCargaList = tbHistoricoCargaList;
    }

    @XmlTransient
    public List<TbCargaProcesso> getTbCargaProcessoList() {
        return tbCargaProcessoList;
    }

    public void setTbCargaProcessoList(List<TbCargaProcesso> tbCargaProcessoList) {
        this.tbCargaProcessoList = tbCargaProcessoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgenda != null ? idAgenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbAgendaCarga)) {
            return false;
        }
        TbAgendaCarga other = (TbAgendaCarga) object;
        if ((this.idAgenda == null && other.idAgenda != null) || (this.idAgenda != null && !this.idAgenda.equals(other.idAgenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.intercarga.beans.TbAgendaCarga[ idAgenda=" + idAgenda + " ]";
    }
    
}
