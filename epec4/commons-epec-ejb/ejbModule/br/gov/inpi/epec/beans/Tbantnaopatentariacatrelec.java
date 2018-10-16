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
@Table(name = "TBANTNAOPATENTARIACATRELEC")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbantnaopatentariarelec.findAll", query = "SELECT t FROM Tbantnaopatentariarelec t"),
		@NamedQuery(name = "Tbantnaopatentariarelec.countAll", query = "SELECT COUNT(t) FROM Tbantnaopatentariarelec t"),
		@NamedQuery(name = "Tbantnaopatentariarelec.findByIdAntnaopatentariarelec", query = "SELECT t FROM Tbantnaopatentariarelec t WHERE t.idAntnaopatentariarelec = :idAntnaopatentariarelec"),
		@NamedQuery(name = "Tbantnaopatentariarelec.findByIdRelatorio", query = "SELECT t FROM Tbantnaopatentariarelec t WHERE t.idRelatorioEc.idRelatorioEc = :idRelatorioEc") })
public class Tbantnaopatentariacatrelec implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANTNAOPATENTARIACATRELEC")
    private Long idAntnaopatentariacatrelec;
    

    @Lob
    @Column(name = "TX_TITULO")
    private String txTitulo;
    
    @Lob
    @Column(name = "TX_RELACAO")
    private String txRelacao;
    
    @Lob
    @Column(name = "TX_REIVINDICACAO")
    private String txReivindicacao;
    
    @JoinColumn(name = "ID_CADTIPOANTERIORIDADE", referencedColumnName = "ID_CADTIPOANTERIORIDADE")
    @ManyToOne(optional = false)
    private Tbcadtipoanterioridade idCadtipoanterioridade;
    
    @JoinColumn(name = "ID_CATRELATORIOEC", referencedColumnName = "ID_CATRELATORIOEC")
    @ManyToOne(optional = false)
    private Tbcatrelatorioec idCatrelatorioec;
    
    @Transient
    private String cadTipoAnteriodadePatentaria;

    public String getCadTipoAnteriodadePatentaria() {
		return cadTipoAnteriodadePatentaria;
	}

	public void setCadTipoAnteriodadePatentaria(String cadTipoAnteriodadePatentaria) {
		this.cadTipoAnteriodadePatentaria = cadTipoAnteriodadePatentaria;
	}

    public Tbantnaopatentariacatrelec() {
    }

    public Tbantnaopatentariacatrelec(Long idAntnaopatentariacatrelec) {
        this.idAntnaopatentariacatrelec = idAntnaopatentariacatrelec;
    }

    public Tbantnaopatentariacatrelec(Long idAntnaopatentariacatrelec, String txTitulo) {
        this.idAntnaopatentariacatrelec = idAntnaopatentariacatrelec;
        this.txTitulo = txTitulo;
    }

    public Long getIdAntnaopatentariacatrelec() {
        return idAntnaopatentariacatrelec;
    }

    public void setIdAntnaopatentariacatrelec(Long idAntnaopatentariacatrelec) {
        this.idAntnaopatentariacatrelec = idAntnaopatentariacatrelec;
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

    public Tbcatrelatorioec getIdCatrelatorioec() {
        return idCatrelatorioec;
    }

    public void setIdCatrelatorioec(Tbcatrelatorioec idCatrelatorioec) {
        this.idCatrelatorioec = idCatrelatorioec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAntnaopatentariacatrelec != null ? idAntnaopatentariacatrelec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbantnaopatentariacatrelec)) {
            return false;
        }
        Tbantnaopatentariacatrelec other = (Tbantnaopatentariacatrelec) object;
        if ((this.idAntnaopatentariacatrelec == null && other.idAntnaopatentariacatrelec != null) || (this.idAntnaopatentariacatrelec != null && !this.idAntnaopatentariacatrelec.equals(other.idAntnaopatentariacatrelec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbantnaopatentariacatrelec[ idAntnaopatentariacatrelec=" + idAntnaopatentariacatrelec + " ]";
    }
    
}
