/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

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
@Table(name = "TBANTPATENTARIA")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbantpatentaria.findAll", query = "SELECT t FROM Tbantpatentaria t"),
		@NamedQuery(name = "Tbantpatentaria.countAll", query = "SELECT COUNT(t) FROM Tbantpatentaria t"),
		@NamedQuery(name = "Tbantpatentaria.findByIdAntpatentaria", query = "SELECT t FROM Tbantpatentaria t WHERE t.idAntpatentaria = :idAntpatentaria"),
		@NamedQuery(name = "Tbantpatentaria.findByStrAntpatentaria", query = "SELECT t FROM Tbantpatentaria t WHERE t.strAntpatentaria = :strAntpatentaria"),
		@NamedQuery(name = "Tbantpatentaria.findByIdCaraccatrelatorio", query = "SELECT t FROM Tbantpatentaria t WHERE t.idCaraccatrelatorio.idCaraccatrelatorio = :idCaraccatrelatorio") })
public class Tbantpatentaria implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANTPATENTARIA")
    private Long idAntpatentaria;   


    @Column(name = "STR_ANTPATENTARIA")
    private String strAntpatentaria;
    
    @Column(name = "TX_RELACAO")
    private String txRelacao;
    
    @Column(name = "TX_REIVINDICACAO")
    private String txReivindicacao;
    
    @JoinColumn(name = "ID_CADTIPOANTERIORIDADE", referencedColumnName = "ID_CADTIPOANTERIORIDADE")
    @ManyToOne
    private Tbcadtipoanterioridade idCadtipoanterioridade;
    
    @JoinColumn(name = "ID_CARACCATRELATORIO", referencedColumnName = "ID_CARACCATRELATORIO")
    @ManyToOne
    private Tbcaraccatrelatorioec idCaraccatrelatorio;
    
    @Transient
    private String cadTipoAnteriodadePatentaria;

    public Tbantpatentaria() {
    }

    public Tbantpatentaria(Long idAntpatentaria) {
        this.idAntpatentaria = idAntpatentaria;
    }

    public Tbantpatentaria(Long idAntpatentaria, String strAntpatentaria) {
        this.idAntpatentaria = idAntpatentaria;
        this.strAntpatentaria = strAntpatentaria;
    }

    public Long getIdAntpatentaria() {
        return idAntpatentaria;
    }

    public void setIdAntpatentaria(Long idAntpatentaria) {
        this.idAntpatentaria = idAntpatentaria;
    }

    public String getStrAntpatentaria() {
        return strAntpatentaria;
    }

    public void setStrAntpatentaria(String strAntpatentaria) {
        this.strAntpatentaria = strAntpatentaria;
    }

    public String getTxRelacao() {
        return txRelacao;
    }

    public void setTxRelacao(String txRelacao) {
        this.txRelacao = txRelacao;
    }

    public String getTxReivindicacao() {
        return txReivindicacao;
    }

    public void setTxReivindicacao(String txReivindicacao) {
        this.txReivindicacao = txReivindicacao;
    }

    public Tbcadtipoanterioridade getIdCadtipoanterioridade() {
        return idCadtipoanterioridade;
    }

    public void setIdCadtipoanterioridade(Tbcadtipoanterioridade idCadtipoanterioridade) {
        this.idCadtipoanterioridade = idCadtipoanterioridade;
    }

    public Tbcaraccatrelatorioec getIdCaraccatrelatorio() {
        return idCaraccatrelatorio;
    }

    public void setIdCaraccatrelatorio(Tbcaraccatrelatorioec idCaraccatrelatorio) {
        this.idCaraccatrelatorio = idCaraccatrelatorio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAntpatentaria != null ? idAntpatentaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbantpatentaria)) {
            return false;
        }
        Tbantpatentaria other = (Tbantpatentaria) object;
        if ((this.idAntpatentaria == null && other.idAntpatentaria != null) || (this.idAntpatentaria != null && !this.idAntpatentaria.equals(other.idAntpatentaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbantpatentaria[ idAntpatentaria=" + idAntpatentaria + " ]";
    }

	public String getCadTipoAnteriodadePatentaria() {
		return cadTipoAnteriodadePatentaria;
	}

	public void setCadTipoAnteriodadePatentaria(String cadTipoAnteriodadePatentaria) {
		this.cadTipoAnteriodadePatentaria = cadTipoAnteriodadePatentaria;
	}
    
}
