/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.intercarga.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "tb_carga_semanal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbCargaSemanal.findAll", query = "SELECT t FROM TbCargaSemanal t"),
    @NamedQuery(name = "TbCargaSemanal.findByIdCargaSemanal", query = "SELECT t FROM TbCargaSemanal t WHERE t.idCargaSemanal = :idCargaSemanal"),
    @NamedQuery(name = "TbCargaSemanal.findByNoRpi", query = "SELECT t FROM TbCargaSemanal t WHERE t.noRpi = :noRpi")})
public class TbCargaSemanal implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "id_carga_semanal", nullable = false)
    private Integer idCargaSemanal;
   
    @Size(max = 4)
    @Column(name = "no_rpi")
    private String noRpi;
    
    @JoinColumn(name = "id_agenda", referencedColumnName = "id_agenda")
    @ManyToOne(optional = false)
    private TbAgendaCarga idAgenda;

    public TbCargaSemanal() {
    }

    public TbCargaSemanal(Integer idCargaSemanal) {
        this.idCargaSemanal = idCargaSemanal;
    }

    public TbCargaSemanal(Integer idCargaSemanal, String noRpi) {
        this.idCargaSemanal = idCargaSemanal;
        this.noRpi = noRpi;
    }

    public Integer getIdCargaSemanal() {
        return idCargaSemanal;
    }

    public void setIdCargaSemanal(Integer idCargaSemanal) {
        this.idCargaSemanal = idCargaSemanal;
    }

    public String getNoRpi() {
        return noRpi;
    }

    public void setNoRpi(String noRpi) {
        this.noRpi = noRpi;
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
        hash += (idCargaSemanal != null ? idCargaSemanal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbCargaSemanal)) {
            return false;
        }
        TbCargaSemanal other = (TbCargaSemanal) object;
        if ((this.idCargaSemanal == null && other.idCargaSemanal != null) || (this.idCargaSemanal != null && !this.idCargaSemanal.equals(other.idCargaSemanal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.intercarga.beans.TbCargaSemanal[ idCargaSemanal=" + idCargaSemanal + " ]";
    }
    
}
