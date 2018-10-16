/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBANTPATENTARIARELEC")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbantpatentariacatrelec.findAll", query = "SELECT t FROM Tbantpatentariacatrelec t"),
		@NamedQuery(name = "Tbantpatentariacatrelec.countAll", query = "SELECT COUNT(t) FROM Tbantpatentariacatrelec t"),
		@NamedQuery(name = "Tbantpatentariacatrelec.findByIDANTPATENTARIACATRELEl", query = "SELECT t FROM Tbantpatentariacatrelec t WHERE t.idAntPatentariaCatRelec = :idAntPatentariaCatRelec"),
		@NamedQuery(name = "Tbantpatentariacatrelec.findByStrAntpatentaria", query = "SELECT t FROM Tbantpatentariacatrelec t WHERE t.strAntpatentaria = :strAntpatentaria"),
		@NamedQuery(name = "Tbantpatentariacatrelec.findByIdCatrelatorioEca", query = "SELECT t FROM Tbantpatentariacatrelec t WHERE t.idCatrelatorioec.idCatrelatorioec = :idCatrelatorioec") })
public class Tbantpatentariarelec implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANTPATENTARIAREL_EC")
    private Long idAntpatentariarelEc;
    
    @NotNull
    @Column(name = "STR_ANTPATENTARIA")
    private String strAntpatentaria;

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

    public Tbantpatentariarelec() {
    }

    public Tbantpatentariarelec(Long idAntpatentariarelEc) {
        this.idAntpatentariarelEc = idAntpatentariarelEc;
    }

    public Tbantpatentariarelec(Long idAntpatentariarelEc, String strAntpatentaria) {
        this.idAntpatentariarelEc = idAntpatentariarelEc;
        this.strAntpatentaria = strAntpatentaria;
    }

    public Long getIdAntpatentariarelEc() {
        return idAntpatentariarelEc;
    }

    public void setIdAntpatentariarelEc(Long idAntpatentariarelEc) {
        this.idAntpatentariarelEc = idAntpatentariarelEc;
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

    public Tbrelatorioec getIdRelatorioEc() {
        return idRelatorioEc;
    }

    public void setIdRelatorioEc(Tbrelatorioec idRelatorioEc) {
        this.idRelatorioEc = idRelatorioEc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAntpatentariarelEc != null ? idAntpatentariarelEc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbantpatentariarelec)) {
            return false;
        }
        Tbantpatentariarelec other = (Tbantpatentariarelec) object;
        if ((this.idAntpatentariarelEc == null && other.idAntpatentariarelEc != null) || (this.idAntpatentariarelEc != null && !this.idAntpatentariarelEc.equals(other.idAntpatentariarelEc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbantpatentariarelec[ idAntpatentariarelEc=" + idAntpatentariarelEc + " ]";
    }

	public String getCadTipoAnteriodadePatentaria() {
		return cadTipoAnteriodadePatentaria;
	}

	public void setCadTipoAnteriodadePatentaria(String cadTipoAnteriodadePatentaria) {
		this.cadTipoAnteriodadePatentaria = cadTipoAnteriodadePatentaria;
	}
    
}
