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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "tb_historico_carga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbHistoricoCarga.findAll", query = "SELECT t FROM TbHistoricoCarga t"),
    @NamedQuery(name = "TbHistoricoCarga.findByIdHistoricoCarga", query = "SELECT t FROM TbHistoricoCarga t WHERE t.idHistoricoCarga = :idHistoricoCarga"),
    @NamedQuery(name = "TbHistoricoCarga.findByDhInicioCarga", query = "SELECT t FROM TbHistoricoCarga t WHERE t.dhInicioCarga = :dhInicioCarga"),
    @NamedQuery(name = "TbHistoricoCarga.findByDhFimCarga", query = "SELECT t FROM TbHistoricoCarga t WHERE t.dhFimCarga = :dhFimCarga"),
    @NamedQuery(name = "TbHistoricoCarga.findByQtMarcasProc", query = "SELECT t FROM TbHistoricoCarga t WHERE t.qtMarcasProc = :qtMarcasProc"),
    @NamedQuery(name = "TbHistoricoCarga.findByDhInicioMarcaProc", query = "SELECT t FROM TbHistoricoCarga t WHERE t.dhInicioMarcaProc = :dhInicioMarcaProc"),
    @NamedQuery(name = "TbHistoricoCarga.findByDhFimMarcaProc", query = "SELECT t FROM TbHistoricoCarga t WHERE t.dhFimMarcaProc = :dhFimMarcaProc"),
    @NamedQuery(name = "TbHistoricoCarga.findByQtMarcasOk", query = "SELECT t FROM TbHistoricoCarga t WHERE t.qtMarcasOk = :qtMarcasOk"),
    @NamedQuery(name = "TbHistoricoCarga.findByQtMarcasErro", query = "SELECT t FROM TbHistoricoCarga t WHERE t.qtMarcasErro = :qtMarcasErro"),
    @NamedQuery(name = "TbHistoricoCarga.findByQtPatentesProc", query = "SELECT t FROM TbHistoricoCarga t WHERE t.qtPatentesProc = :qtPatentesProc"),
    @NamedQuery(name = "TbHistoricoCarga.findByQtPatentesSigiloProc", query = "SELECT t FROM TbHistoricoCarga t WHERE t.qtPatentesSigiloProc = :qtPatentesSigiloProc"),
    @NamedQuery(name = "TbHistoricoCarga.findByDhInicioPatenteProc", query = "SELECT t FROM TbHistoricoCarga t WHERE t.dhInicioPatenteProc = :dhInicioPatenteProc"),
    @NamedQuery(name = "TbHistoricoCarga.findByDhFimPatenteProc", query = "SELECT t FROM TbHistoricoCarga t WHERE t.dhFimPatenteProc = :dhFimPatenteProc"),
    @NamedQuery(name = "TbHistoricoCarga.findByQtPatenteOk", query = "SELECT t FROM TbHistoricoCarga t WHERE t.qtPatenteOk = :qtPatenteOk"),
    @NamedQuery(name = "TbHistoricoCarga.findByQtPatenteErro", query = "SELECT t FROM TbHistoricoCarga t WHERE t.qtPatenteErro = :qtPatenteErro"),
    @NamedQuery(name = "TbHistoricoCarga.findByQtDiProc", query = "SELECT t FROM TbHistoricoCarga t WHERE t.qtDiProc = :qtDiProc"),
    @NamedQuery(name = "TbHistoricoCarga.findByQtDiSigiloProc", query = "SELECT t FROM TbHistoricoCarga t WHERE t.qtDiSigiloProc = :qtDiSigiloProc"),
    @NamedQuery(name = "TbHistoricoCarga.findByDhInicioDiProc", query = "SELECT t FROM TbHistoricoCarga t WHERE t.dhInicioDiProc = :dhInicioDiProc"),
    @NamedQuery(name = "TbHistoricoCarga.findByDhFimDiProc", query = "SELECT t FROM TbHistoricoCarga t WHERE t.dhFimDiProc = :dhFimDiProc"),
    @NamedQuery(name = "TbHistoricoCarga.findByQtDiOk", query = "SELECT t FROM TbHistoricoCarga t WHERE t.qtDiOk = :qtDiOk"),
    @NamedQuery(name = "TbHistoricoCarga.findByQtDiErro", query = "SELECT t FROM TbHistoricoCarga t WHERE t.qtDiErro = :qtDiErro"),
    @NamedQuery(name = "TbHistoricoCarga.findByDhProcesso", query = "SELECT t FROM TbHistoricoCarga t WHERE t.dhProcesso = :dhProcesso"),
    @NamedQuery(name = "TbHistoricoCarga.findByNoRpi", query = "SELECT t FROM TbHistoricoCarga t WHERE t.noRpi = :noRpi"),
    @NamedQuery(name = "TbHistoricoCarga.findByDsCaminhoArquivoLog", query = "SELECT t FROM TbHistoricoCarga t WHERE t.dsCaminhoArquivoLog = :dsCaminhoArquivoLog")})
public class TbHistoricoCarga implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico_carga", nullable = false)
    private Integer idHistoricoCarga;
    
    @Column(name = "dh_inicio_carga")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhInicioCarga;
    
    @Column(name = "dh_fim_carga")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhFimCarga;
    
    @Size(max = 7)
    @Column(name = "qt_marcas_proc")
    private String qtMarcasProc;
    
    @Column(name = "dh_inicio_marca_proc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhInicioMarcaProc;
    
    @Column(name = "dh_fim_marca_proc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhFimMarcaProc;
    
    @Size(max = 7)
    @Column(name = "qt_marcas_ok")
    private String qtMarcasOk;
    
    @Size(max = 7)
    @Column(name = "qt_marcas_erro")
    private String qtMarcasErro;
    
    @Size(max = 7)
    @Column(name = "qt_patentes_proc")
    private String qtPatentesProc;
    
    @Size(max = 7)
    @Column(name = "qt_patentes_sigilo_proc")
    private String qtPatentesSigiloProc;
    
    @Column(name = "dh_inicio_patente_proc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhInicioPatenteProc;
    
    @Column(name = "dh_fim_patente_proc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhFimPatenteProc;
    
    @Size(max = 7)
    @Column(name = "qt_patente_ok")
    private String qtPatenteOk;
    
    @Size(max = 7)
    @Column(name = "qt_patente_erro")
    private String qtPatenteErro;
    
    @Size(max = 7)
    @Column(name = "qt_di_proc")
    private String qtDiProc;
    
    @Size(max = 7)
    @Column(name = "qt_di_sigilo_proc")
    private String qtDiSigiloProc;
    
    @Column(name = "dh_inicio_di_proc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhInicioDiProc;
    
    @Column(name = "dh_fim_di_proc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhFimDiProc;
    
    @Size(max = 7)
    @Column(name = "qt_di_ok")
    private String qtDiOk;
    
    @Size(max = 7)
    @Column(name = "qt_di_erro")
    private String qtDiErro;
    
    @Column(name = "dh_processo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhProcesso;
    
    @Size(max = 4)
    @Column(name = "no_rpi")
    private String noRpi;
    
    @Size(max = 255)
    @Column(name = "ds_caminho_arquivo_log")
    private String dsCaminhoArquivoLog;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHistoricoCarga", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<TbHistoricoCargaProcesso> tbHistoricoCargaProcessoList;
    
    @JoinColumn(name = "id_agenda", referencedColumnName = "id_agenda")
    @ManyToOne
    private TbAgendaCarga idAgenda;

    public TbHistoricoCarga() {
    }

    public TbHistoricoCarga(Integer idHistoricoCarga) {
        this.idHistoricoCarga = idHistoricoCarga;
    }

    public Integer getIdHistoricoCarga() {
        return idHistoricoCarga;
    }

    public void setIdHistoricoCarga(Integer idHistoricoCarga) {
        this.idHistoricoCarga = idHistoricoCarga;
    }

    public Date getDhInicioCarga() {
        return dhInicioCarga;
    }

    public void setDhInicioCarga(Date dhInicioCarga) {
        this.dhInicioCarga = dhInicioCarga;
    }

    public Date getDhFimCarga() {
        return dhFimCarga;
    }

    public void setDhFimCarga(Date dhFimCarga) {
        this.dhFimCarga = dhFimCarga;
    }

    public String getQtMarcasProc() {
        return qtMarcasProc;
    }

    public void setQtMarcasProc(String qtMarcasProc) {
        this.qtMarcasProc = qtMarcasProc;
    }

    public Date getDhInicioMarcaProc() {
        return dhInicioMarcaProc;
    }

    public void setDhInicioMarcaProc(Date dhInicioMarcaProc) {
        this.dhInicioMarcaProc = dhInicioMarcaProc;
    }

    public Date getDhFimMarcaProc() {
        return dhFimMarcaProc;
    }

    public void setDhFimMarcaProc(Date dhFimMarcaProc) {
        this.dhFimMarcaProc = dhFimMarcaProc;
    }

    public String getQtMarcasOk() {
        return qtMarcasOk;
    }

    public void setQtMarcasOk(String qtMarcasOk) {
        this.qtMarcasOk = qtMarcasOk;
    }

    public String getQtMarcasErro() {
        return qtMarcasErro;
    }

    public void setQtMarcasErro(String qtMarcasErro) {
        this.qtMarcasErro = qtMarcasErro;
    }

    public String getQtPatentesProc() {
        return qtPatentesProc;
    }

    public void setQtPatentesProc(String qtPatentesProc) {
        this.qtPatentesProc = qtPatentesProc;
    }

    public String getQtPatentesSigiloProc() {
        return qtPatentesSigiloProc;
    }

    public void setQtPatentesSigiloProc(String qtPatentesSigiloProc) {
        this.qtPatentesSigiloProc = qtPatentesSigiloProc;
    }

    public Date getDhInicioPatenteProc() {
        return dhInicioPatenteProc;
    }

    public void setDhInicioPatenteProc(Date dhInicioPatenteProc) {
        this.dhInicioPatenteProc = dhInicioPatenteProc;
    }

    public Date getDhFimPatenteProc() {
        return dhFimPatenteProc;
    }

    public void setDhFimPatenteProc(Date dhFimPatenteProc) {
        this.dhFimPatenteProc = dhFimPatenteProc;
    }

    public String getQtPatenteOk() {
        return qtPatenteOk;
    }

    public void setQtPatenteOk(String qtPatenteOk) {
        this.qtPatenteOk = qtPatenteOk;
    }

    public String getQtPatenteErro() {
        return qtPatenteErro;
    }

    public void setQtPatenteErro(String qtPatenteErro) {
        this.qtPatenteErro = qtPatenteErro;
    }

    public String getQtDiProc() {
        return qtDiProc;
    }

    public void setQtDiProc(String qtDiProc) {
        this.qtDiProc = qtDiProc;
    }

    public String getQtDiSigiloProc() {
        return qtDiSigiloProc;
    }

    public void setQtDiSigiloProc(String qtDiSigiloProc) {
        this.qtDiSigiloProc = qtDiSigiloProc;
    }

    public Date getDhInicioDiProc() {
        return dhInicioDiProc;
    }

    public void setDhInicioDiProc(Date dhInicioDiProc) {
        this.dhInicioDiProc = dhInicioDiProc;
    }

    public Date getDhFimDiProc() {
        return dhFimDiProc;
    }

    public void setDhFimDiProc(Date dhFimDiProc) {
        this.dhFimDiProc = dhFimDiProc;
    }

    public String getQtDiOk() {
        return qtDiOk;
    }

    public void setQtDiOk(String qtDiOk) {
        this.qtDiOk = qtDiOk;
    }

    public String getQtDiErro() {
        return qtDiErro;
    }

    public void setQtDiErro(String qtDiErro) {
        this.qtDiErro = qtDiErro;
    }

    public Date getDhProcesso() {
        return dhProcesso;
    }

    public void setDhProcesso(Date dhProcesso) {
        this.dhProcesso = dhProcesso;
    }

    public String getNoRpi() {
        return noRpi;
    }

    public void setNoRpi(String noRpi) {
        this.noRpi = noRpi;
    }

    public String getDsCaminhoArquivoLog() {
        return dsCaminhoArquivoLog;
    }

    public void setDsCaminhoArquivoLog(String dsCaminhoArquivoLog) {
        this.dsCaminhoArquivoLog = dsCaminhoArquivoLog;
    }

    @XmlTransient
    public List<TbHistoricoCargaProcesso> getTbHistoricoCargaProcessoList() {
        return tbHistoricoCargaProcessoList;
    }

    public void setTbHistoricoCargaProcessoList(List<TbHistoricoCargaProcesso> tbHistoricoCargaProcessoList) {
        this.tbHistoricoCargaProcessoList = tbHistoricoCargaProcessoList;
    }

    public TbAgendaCarga getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(TbAgendaCarga idAgenda) {
        this.idAgenda = idAgenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoricoCarga != null ? idHistoricoCarga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbHistoricoCarga)) {
            return false;
        }
        TbHistoricoCarga other = (TbHistoricoCarga) object;
        if ((this.idHistoricoCarga == null && other.idHistoricoCarga != null) || (this.idHistoricoCarga != null && !this.idHistoricoCarga.equals(other.idHistoricoCarga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.intercarga.beans.TbHistoricoCarga[ idHistoricoCarga=" + idHistoricoCarga + " ]";
    }
    
}
