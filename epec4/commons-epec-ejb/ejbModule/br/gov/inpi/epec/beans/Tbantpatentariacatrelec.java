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
@Table(name = "TBANTPATENTARIACATRELEC")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbantpatentariarelec.findAll", query = "SELECT t FROM Tbantpatentariarelec t"),
		@NamedQuery(name = "Tbantpatentariarelec.countAll", query = "SELECT COUNT(t) FROM Tbantpatentariarelec t"),
		@NamedQuery(name = "Tbantpatentariarelec.findByIdAntpatentariarelEc", query = "SELECT t FROM Tbantpatentariarelec t WHERE t.idAntpatentariarelEc = :idAntpatentariarelEc"),
		@NamedQuery(name = "Tbantpatentariarelec.findByStrAntpatentaria", query = "SELECT t FROM Tbantpatentariarelec t WHERE t.strAntpatentaria = :strAntpatentaria"),
		@NamedQuery(name = "Tbantpatentariarelec.findByIdRelatorio", query = "SELECT t FROM Tbantpatentariarelec t WHERE t.idRelatorioEc.idRelatorioEc = :idRelatorioEc") })
public class Tbantpatentariacatrelec implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANTPATENTARIACATRELEC")
    private Long idAntPatentariaCatRelec;
    
    @Column(name = "STR_ANTPATENTARIA")
    private String strAntpatentaria;
    
    @Lob
    @Column(name = "TX_RELACAO")
    private String txRelacao;
    
    @Column(name = "TX_REIVINDICACAO")
    private String txReivindicacao;
    
    @JoinColumn(name = "ID_CADTIPOANTERIORIDADE", referencedColumnName = "ID_CADTIPOANTERIORIDADE")
    @ManyToOne
    private Tbcadtipoanterioridade idCadtipoanterioridade;
    
    @JoinColumn(name = "ID_CATRELATORIOEC", referencedColumnName = "ID_CATRELATORIOEC")
    @ManyToOne
    private Tbcatrelatorioec idCatrelatorioec;
    
    @Transient
    private String cadTipoAnteriodadePatentaria;

    public String getCadTipoAnteriodadePatentaria() {
		return cadTipoAnteriodadePatentaria;
	}

	public void setCadTipoAnteriodadePatentaria(String cadTipoAnteriodadePatentaria) {
		this.cadTipoAnteriodadePatentaria = cadTipoAnteriodadePatentaria;
	}

	public Tbantpatentariacatrelec() {
    }

    public Tbantpatentariacatrelec(Long iDANTPATENTARIACATRELEl) {
        this.idAntPatentariaCatRelec = iDANTPATENTARIACATRELEl;
    }

    public Tbantpatentariacatrelec(Long iDANTPATENTARIACATRELEl, String strAntpatentaria) {
        this.idAntPatentariaCatRelec = iDANTPATENTARIACATRELEl;
        this.strAntpatentaria = strAntpatentaria;
    }

    public Long getIdAntPatentariaCatRelec() {
        return idAntPatentariaCatRelec;
    }

    public void setIdAntPatentariaCatRelec(Long idAntPatentariaCatRelec) {
        this.idAntPatentariaCatRelec = idAntPatentariaCatRelec;
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

    public Tbcatrelatorioec getIdCatrelatorioec() {
        return idCatrelatorioec;
    }

    public void setIdCatrelatorioec(Tbcatrelatorioec idCatrelatorioec) {
        this.idCatrelatorioec = idCatrelatorioec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAntPatentariaCatRelec != null ? idAntPatentariaCatRelec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbantpatentariacatrelec)) {
            return false;
        }
        Tbantpatentariacatrelec other = (Tbantpatentariacatrelec) object;
        if ((this.idAntPatentariaCatRelec == null && other.idAntPatentariaCatRelec != null) || (this.idAntPatentariaCatRelec != null && !this.idAntPatentariaCatRelec.equals(other.idAntPatentariaCatRelec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbantpatentariacatrelec[ iDANTPATENTARIACATRELEl=" + idAntPatentariaCatRelec + " ]";
    }
    
}
