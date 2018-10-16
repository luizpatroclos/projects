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
@Table(name = "TBANTNAOPATENTARIARELEC")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tbantnaopatentariacatrelec.findAll", query = "SELECT t FROM Tbantnaopatentariacatrelec t"),
		@NamedQuery(name = "Tbantnaopatentariacatrelec.countAll", query = "SELECT COUNT(t) FROM Tbantnaopatentariacatrelec t"),
		@NamedQuery(name = "Tbantnaopatentariacatrelec.findByIdAntnaopatentariacatrelec", query = "SELECT t FROM Tbantnaopatentariacatrelec t WHERE t.idAntnaopatentariacatrelec = :idAntnaopatentariacatrelec"),
		@NamedQuery(name = "Tbantnaopatentariacatrelec.findByidCatrelatorioec", query = "SELECT t FROM Tbantnaopatentariacatrelec t WHERE t.idCatrelatorioec.idCatrelatorioec = :idCatrelatorioec") })
public class Tbantnaopatentariarelec implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANTNAOPATENTARIARELEC")
    private Long idAntnaopatentariarelec;
    

    @Column(name = "TX_TITULO")
    private String txTitulo;
    

    @Column(name = "TX_RELACAO")
    private String txRelacao;

    @Column(name = "TX_REIVINDICACAO")
    private String txReivindicacao;
    
    @JoinColumn(name = "ID_CADTIPOANTERIORIDADE", referencedColumnName = "ID_CADTIPOANTERIORIDADE")
    @ManyToOne
    private Tbcadtipoanterioridade idCadtipoanterioridade;
    
    @JoinColumn(name = "ID_RELATORIO_EC", referencedColumnName = "ID_RELATORIO_EC")
    @ManyToOne
    private Tbrelatorioec idRelatorioEc;
    
    @Transient
    private String cadTipoAnteriodadePatentaria;

    public String getCadTipoAnteriodadePatentaria() {
		return cadTipoAnteriodadePatentaria;
	}

	public void setCadTipoAnteriodadePatentaria(String cadTipoAnteriodadePatentaria) {
		this.cadTipoAnteriodadePatentaria = cadTipoAnteriodadePatentaria;
	}

	public Tbantnaopatentariarelec() {
    }

    public Tbantnaopatentariarelec(Long idAntnaopatentariarelec) {
        this.idAntnaopatentariarelec = idAntnaopatentariarelec;
    }

    public Tbantnaopatentariarelec(Long idAntnaopatentariarelec, String txTitulo, String txRelacao, String txReivindicacao) {
        this.idAntnaopatentariarelec = idAntnaopatentariarelec;
        this.txTitulo = txTitulo;
        this.txRelacao = txRelacao;
        this.txReivindicacao = txReivindicacao;
    }

    public Long getIdAntnaopatentariarelec() {
        return idAntnaopatentariarelec;
    }

    public void setIdAntnaopatentariarelec(Long idAntnaopatentariarelec) {
        this.idAntnaopatentariarelec = idAntnaopatentariarelec;
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

    public Tbcadtipoanterioridade getIdCadtipoanterioridade() {
        return idCadtipoanterioridade;
    }

    public void setIdCadtipoanterioridade(Tbcadtipoanterioridade idCadtipoanterioridade) {
        this.idCadtipoanterioridade = idCadtipoanterioridade;
    }

    public Tbrelatorioec getIdRelatorioEc() {
        return idRelatorioEc;
    }

    public void setIdRelatorioEc(Tbrelatorioec idRelatorioEc) {
        this.idRelatorioEc = idRelatorioEc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAntnaopatentariarelec != null ? idAntnaopatentariarelec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbantnaopatentariarelec)) {
            return false;
        }
        Tbantnaopatentariarelec other = (Tbantnaopatentariarelec) object;
        if ((this.idAntnaopatentariarelec == null && other.idAntnaopatentariarelec != null) || (this.idAntnaopatentariarelec != null && !this.idAntnaopatentariarelec.equals(other.idAntnaopatentariarelec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbantnaopatentariarelec[ idAntnaopatentariarelec=" + idAntnaopatentariarelec + " ]";
    }
    
}
