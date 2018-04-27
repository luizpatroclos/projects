/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.intercarga.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "tb_carga_retroativa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbCargaRetroativa.findAll", query = "SELECT t FROM TbCargaRetroativa t"),
    @NamedQuery(name = "TbCargaRetroativa.findByIdCargaRetroativa", query = "SELECT t FROM TbCargaRetroativa t WHERE t.idCargaRetroativa = :idCargaRetroativa"),
    @NamedQuery(name = "TbCargaRetroativa.findByQtMarcas", query = "SELECT t FROM TbCargaRetroativa t WHERE t.qtMarcas = :qtMarcas"),
    @NamedQuery(name = "TbCargaRetroativa.findByQtPatentes", query = "SELECT t FROM TbCargaRetroativa t WHERE t.qtPatentes = :qtPatentes"),
    @NamedQuery(name = "TbCargaRetroativa.findByQtDi", query = "SELECT t FROM TbCargaRetroativa t WHERE t.qtDi = :qtDi")})
public class TbCargaRetroativa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id        
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "id_carga_retroativa", nullable = false)
    private Integer idCargaRetroativa;
    
    @Size(max = 7)
    @Column(name = "qt_marcas")
    private String qtMarcas;
    
    @Size(max = 7)
    @Column(name = "qt_patentes")
    private String qtPatentes;
    
    @Size(max = 7)
    @Column(name = "qt_di")
    private String qtDi;
    
    @OneToMany(mappedBy = "idCargaRetroativa")
    private List<TbCargaRetroativaStatus> tbCargaRetroativaStatusList;
    
    @JoinColumn(name = "id_agenda", referencedColumnName = "id_agenda")
    @ManyToOne(optional = false)
    private TbAgendaCarga idAgenda;

    public TbCargaRetroativa() {
    }

    public TbCargaRetroativa(Integer idCargaRetroativa) {
        this.idCargaRetroativa = idCargaRetroativa;
    }

    public TbCargaRetroativa(Integer idCargaRetroativa, String qtMarcas, String qtPatentes, String qtDi) {
        this.idCargaRetroativa = idCargaRetroativa;
        this.qtMarcas = qtMarcas;
        this.qtPatentes = qtPatentes;
        this.qtDi = qtDi;
    }

    public Integer getIdCargaRetroativa() {
        return idCargaRetroativa;
    }

    public void setIdCargaRetroativa(Integer idCargaRetroativa) {
        this.idCargaRetroativa = idCargaRetroativa;
    }

    public String getQtMarcas() {
        return qtMarcas;
    }

    public void setQtMarcas(String qtMarcas) {
        this.qtMarcas = qtMarcas;
    }

    public String getQtPatentes() {
        return qtPatentes;
    }

    public void setQtPatentes(String qtPatentes) {
        this.qtPatentes = qtPatentes;
    }

    public String getQtDi() {
        return qtDi;
    }

    public void setQtDi(String qtDi) {
        this.qtDi = qtDi;
    }

    @XmlTransient
    public List<TbCargaRetroativaStatus> getTbCargaRetroativaStatusList() {
        return tbCargaRetroativaStatusList;
    }

    public void setTbCargaRetroativaStatusList(List<TbCargaRetroativaStatus> tbCargaRetroativaStatusList) {
        this.tbCargaRetroativaStatusList = tbCargaRetroativaStatusList;
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
        hash += (idCargaRetroativa != null ? idCargaRetroativa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbCargaRetroativa)) {
            return false;
        }
        TbCargaRetroativa other = (TbCargaRetroativa) object;
        if ((this.idCargaRetroativa == null && other.idCargaRetroativa != null) || (this.idCargaRetroativa != null && !this.idCargaRetroativa.equals(other.idCargaRetroativa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.intercarga.beans.TbCargaRetroativa[ idCargaRetroativa=" + idCargaRetroativa + " ]";
    }
    
}
