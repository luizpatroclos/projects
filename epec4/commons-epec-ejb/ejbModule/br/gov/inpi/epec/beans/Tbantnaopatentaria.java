/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBANTNAOPATENTARIA")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbantnaopatentaria.findAll", query = "SELECT t FROM Tbantnaopatentaria t"),
		@NamedQuery(name = "Tbantnaopatentaria.countAll", query = "SELECT COUNT(t) FROM Tbantnaopatentaria t"),
		@NamedQuery(name = "Tbantnaopatentaria.findByIdAntnaopatentaria", query = "SELECT t FROM Tbantnaopatentaria t WHERE t.idAntnaopatentaria = :idAntnaopatentaria"),
		@NamedQuery(name = "Tbantnaopatentaria.findByIdCaraccatrelatorio", query = "SELECT t FROM Tbantnaopatentaria t WHERE t.idCaraccatrelatorio.idCaraccatrelatorio = :idCaraccatrelatorio") })
public class Tbantnaopatentaria implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANTNAOPATENTARIA")
    private Long idAntnaopatentaria;
    

    @Column(name = "TX_TITULO")
    private String txTitulo;
   
    @Column(name = "TX_RELACAO")
    private String txRelacao;
    

    @Column(name = "TX_REIVINDICACAO")
    private String txReivindicacao;
    
    @JoinColumn(name = "ID_CARACCATRELATORIO", referencedColumnName = "ID_CARACCATRELATORIO")
    @ManyToOne
    private Tbcaraccatrelatorioec idCaraccatrelatorio;
    
    @JoinColumn(name = "ID_CADTIPOANTERIORIDADE", referencedColumnName = "ID_CADTIPOANTERIORIDADE")
    @ManyToOne
    private Tbcadtipoanterioridade idCadtipoanterioridade;
    
    @Transient
    private String cadTipoAnteriodadePatentaria;

    public Tbantnaopatentaria() {
    }

    public Tbantnaopatentaria(Long idAntnaopatentaria) {
        this.idAntnaopatentaria = idAntnaopatentaria;
    }

    public Tbantnaopatentaria(Long idAntnaopatentaria, String txTitulo) {
        this.idAntnaopatentaria = idAntnaopatentaria;
        this.txTitulo = txTitulo;
    }

    public Long getIdAntnaopatentaria() {
        return idAntnaopatentaria;
    }

    public void setIdAntnaopatentaria(Long idAntnaopatentaria) {
        this.idAntnaopatentaria = idAntnaopatentaria;
    }

    public String getTxTitulo() {
        return txTitulo;
    }

    public void setTxTitulo(String txTitulo) {
        this.txTitulo = txTitulo;
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

    public Tbcaraccatrelatorioec getIdCaraccatrelatorio() {
        return idCaraccatrelatorio;
    }

    public void setIdCaraccatrelatorio(Tbcaraccatrelatorioec idCaraccatrelatorio) {
        this.idCaraccatrelatorio = idCaraccatrelatorio;
    }

    public Tbcadtipoanterioridade getIdCadtipoanterioridade() {
        return idCadtipoanterioridade;
    }

    public void setIdCadtipoanterioridade(Tbcadtipoanterioridade idCadtipoanterioridade) {
        this.idCadtipoanterioridade = idCadtipoanterioridade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAntnaopatentaria != null ? idAntnaopatentaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbantnaopatentaria)) {
            return false;
        }
        Tbantnaopatentaria other = (Tbantnaopatentaria) object;
        if ((this.idAntnaopatentaria == null && other.idAntnaopatentaria != null) || (this.idAntnaopatentaria != null && !this.idAntnaopatentaria.equals(other.idAntnaopatentaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbantnaopatentaria[ idAntnaopatentaria=" + idAntnaopatentaria + " ]";
    }

	public String getCadTipoAnteriodadePatentaria() {
		return cadTipoAnteriodadePatentaria;
	}

	public void setCadTipoAnteriodadePatentaria(String cadTipoAnteriodadePatentaria) {
		this.cadTipoAnteriodadePatentaria = cadTipoAnteriodadePatentaria;
	}
    
}
