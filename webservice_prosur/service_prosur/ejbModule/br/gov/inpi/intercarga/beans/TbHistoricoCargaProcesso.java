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
@Table(name = "tb_historico_carga_processo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbHistoricoCargaProcesso.findAll", query = "SELECT t FROM TbHistoricoCargaProcesso t"),
    @NamedQuery(name = "TbHistoricoCargaProcesso.findByIdHistoricoCarga", query = "SELECT t FROM TbHistoricoCargaProcesso t WHERE t.idHistoricoCarga = :idHistoricoCarga"),
    @NamedQuery(name = "TbHistoricoCargaProcesso.findByIdHistoricoCargaProcesso", query = "SELECT t FROM TbHistoricoCargaProcesso t WHERE t.idHistoricoCargaProcesso = :idHistoricoCargaProcesso"),
    @NamedQuery(name = "TbHistoricoCargaProcesso.findByNumProcesso", query = "SELECT t FROM TbHistoricoCargaProcesso t WHERE t.numProcesso = :numProcesso"),
    @NamedQuery(name = "TbHistoricoCargaProcesso.findByCdSituacao", query = "SELECT t FROM TbHistoricoCargaProcesso t WHERE t.cdSituacao = :cdSituacao"),
    @NamedQuery(name = "TbHistoricoCargaProcesso.findByCdTipo", query = "SELECT t FROM TbHistoricoCargaProcesso t WHERE t.cdTipo = :cdTipo")})
public class TbHistoricoCargaProcesso implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico_carga_processo")
    private Integer idHistoricoCargaProcesso;
    @Size(max = 20)
    @Column(name = "num_processo")
    private String numProcesso;
    @Column(name = "cd_situacao")
    private short cdSituacao;
    @Size(max = 1)
    @Column(name = "cd_tipo")
    private String cdTipo;
    @JoinColumn(name = "id_historico_carga", referencedColumnName = "id_historico_carga")
    @ManyToOne(optional = false)
    private TbHistoricoCarga idHistoricoCarga;

    public TbHistoricoCargaProcesso() {
    }

    public TbHistoricoCargaProcesso(Integer idHistoricoCargaProcesso) {
        this.idHistoricoCargaProcesso = idHistoricoCargaProcesso;
    }
    
    public TbHistoricoCargaProcesso(Integer idHistoricoCargaProcesso, String numProcesso, short cdSituacao, String cdTipo) {
        this.idHistoricoCargaProcesso = idHistoricoCargaProcesso;
        this.numProcesso = numProcesso;
        this.cdSituacao = cdSituacao;
        this.cdTipo = cdTipo;
    }

    public Integer getIdHistoricoCargaProcesso() {
        return idHistoricoCargaProcesso;
    }

    public void setIdHistoricoCargaProcesso(Integer idHistoricoCargaProcesso) {
        this.idHistoricoCargaProcesso = idHistoricoCargaProcesso;
    }

    public String getNumProcesso() {
        return numProcesso;
    }

    public void setNumProcesso(String numProcesso) {
        this.numProcesso = numProcesso;
    }

    public short getCdSituacao() {
        return cdSituacao;
    }

    public void setCdSituacao(short cdSituacao) {
        this.cdSituacao = cdSituacao;
    }

    public String getCdTipo() {
        return cdTipo;
    }

    public void setCdTipo(String cdTipo) {
        this.cdTipo = cdTipo;
    }

    public TbHistoricoCarga getIdHistoricoCarga() {
        return idHistoricoCarga;
    }

    public void setIdHistoricoCarga(TbHistoricoCarga idHistoricoCarga) {
        this.idHistoricoCarga = idHistoricoCarga;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoricoCargaProcesso != null ? idHistoricoCargaProcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbHistoricoCargaProcesso)) {
            return false;
        }
        TbHistoricoCargaProcesso other = (TbHistoricoCargaProcesso) object;
        if ((this.idHistoricoCargaProcesso == null && other.idHistoricoCargaProcesso != null) || (this.idHistoricoCargaProcesso != null && !this.idHistoricoCargaProcesso.equals(other.idHistoricoCargaProcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso[ idHistoricoCargaProcesso=" + idHistoricoCargaProcesso + " ]";
    }
    
}
