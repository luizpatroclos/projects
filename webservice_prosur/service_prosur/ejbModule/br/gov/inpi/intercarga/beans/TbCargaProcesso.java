/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.intercarga.beans;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "tb_carga_processo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbCargaProcesso.findAll", query = "SELECT t FROM TbCargaProcesso t"),
    @NamedQuery(name = "TbCargaProcesso.findByIdCargaProcesso", query = "SELECT t FROM TbCargaProcesso t WHERE t.idCargaProcesso = :idCargaProcesso"),
    @NamedQuery(name = "TbCargaProcesso.findByNumProcesso", query = "SELECT t FROM TbCargaProcesso t WHERE t.numProcesso = :numProcesso")})
public class TbCargaProcesso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carga_processo", nullable = false)
    private Integer idCargaProcesso;
    @Size(max = 20)
    @Column(name = "num_processo")
    private String numProcesso;
    @JoinColumn(name = "id_agenda", referencedColumnName = "id_agenda")
    @ManyToOne(optional = false)
    private TbAgendaCarga idAgenda;

    public TbCargaProcesso() {
    }

    public TbCargaProcesso(Integer idCargaProcesso) {
        this.idCargaProcesso = idCargaProcesso;
    }

    public TbCargaProcesso(Integer idCargaProcesso, String numProcesso) {
        this.idCargaProcesso = idCargaProcesso;
        this.numProcesso = numProcesso;
    }

    public void setIdCargaProcesso(Integer idCargaProcesso) {
        this.idCargaProcesso = idCargaProcesso;
    }

    public String getNumProcesso() {
        return numProcesso;
    }

    public void setNumProcesso(String numProcesso) {
        this.numProcesso = numProcesso;
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
        hash += (idCargaProcesso != null ? idCargaProcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbCargaProcesso)) {
            return false;
        }
        TbCargaProcesso other = (TbCargaProcesso) object;
        if ((this.idCargaProcesso == null && other.idCargaProcesso != null) || (this.idCargaProcesso != null && !this.idCargaProcesso.equals(other.idCargaProcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.intercarga.beans.TbCargaProcesso[ idCargaProcesso=" + idCargaProcesso + " ]";
    }
    
}
