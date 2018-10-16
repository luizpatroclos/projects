/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBCATRELATORIOEC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbcatrelatorioec.findAll", query = "SELECT t FROM Tbcatrelatorioec t"),
    @NamedQuery(name = "Tbcatrelatorioec.findByIdCatrelatorioec", query = "SELECT t FROM Tbcatrelatorioec t WHERE t.idCatrelatorioec = :idCatrelatorioec"),
    @NamedQuery(name = "Tbcatrelatorioec.findByRelatorioEc", query = "SELECT t FROM Tbcatrelatorioec t WHERE t.idRelatorioEc.idRelatorioEc = :idRelatorioEc")})
public class Tbcatrelatorioec implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Lob
    @Column(name = "TX_RESUMO")
    private String txResumo;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATRELATORIOEC")
    private Long idCatrelatorioec;
    
    @JoinColumn(name = "ID_RELATORIO_EC", referencedColumnName = "ID_RELATORIO_EC")
    @ManyToOne(optional = false)
    private Tbrelatorioec idRelatorioEc;
    
    @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA")
    @ManyToOne(optional = false)
    private Tbcadcategoria idCategoria;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCatrelatorioec")
    private List<Tbantpatentariacatrelec> tbantpatentariacatrelecList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCatrelatorioec")
    private List<Tbantnaopatentariacatrelec> tbantnaopatentariacatrelecList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCatrelatorioec")
    private List<Tbcaraccatrelatorioec> tbcaraccatrelatorioecList;

    public Tbcatrelatorioec() {
    }

    public Tbcatrelatorioec(Long idCatrelatorioec) {
        this.idCatrelatorioec = idCatrelatorioec;
    }

    public String getTxResumo() {
        return txResumo;
    }

    public void setTxResumo(String txResumo) {
        this.txResumo = txResumo;
    }

    public Long getIdCatrelatorioec() {
        return idCatrelatorioec;
    }

    public void setIdCatrelatorioec(Long idCatrelatorioec) {
        this.idCatrelatorioec = idCatrelatorioec;
    }

    public Tbrelatorioec getIdRelatorioEc() {
        return idRelatorioEc;
    }

    public void setIdRelatorioEc(Tbrelatorioec idRelatorioEc) {
        this.idRelatorioEc = idRelatorioEc;
    }

    public Tbcadcategoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Tbcadcategoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    @XmlTransient
    public List<Tbantpatentariacatrelec> getTbantpatentariacatrelecList() {
        return tbantpatentariacatrelecList;
    }

    public void setTbantpatentariacatrelecList(List<Tbantpatentariacatrelec> tbantpatentariacatrelecList) {
        this.tbantpatentariacatrelecList = tbantpatentariacatrelecList;
    }

    @XmlTransient
    public List<Tbantnaopatentariacatrelec> getTbantnaopatentariacatrelecList() {
        return tbantnaopatentariacatrelecList;
    }

    public void setTbantnaopatentariacatrelecList(List<Tbantnaopatentariacatrelec> tbantnaopatentariacatrelecList) {
        this.tbantnaopatentariacatrelecList = tbantnaopatentariacatrelecList;
    }

    @XmlTransient
    public List<Tbcaraccatrelatorioec> getTbcaraccatrelatorioecList() {
        return tbcaraccatrelatorioecList;
    }

    public void setTbcaraccatrelatorioecList(List<Tbcaraccatrelatorioec> tbcaraccatrelatorioecList) {
        this.tbcaraccatrelatorioecList = tbcaraccatrelatorioecList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatrelatorioec != null ? idCatrelatorioec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbcatrelatorioec)) {
            return false;
        }
        Tbcatrelatorioec other = (Tbcatrelatorioec) object;
        if ((this.idCatrelatorioec == null && other.idCatrelatorioec != null) || (this.idCatrelatorioec != null && !this.idCatrelatorioec.equals(other.idCatrelatorioec))) {
            return false;
        }
        return true;
    }

    @Override
	public String toString() {
		return "Tbcatrelatorioec [txResumo=" + txResumo + ", idCatrelatorioec="
				+ idCatrelatorioec + ", idRelatorioEc=" + idRelatorioEc
				+ ", idCategoria=" + idCategoria
				+ ", tbantpatentariacatrelecList="
				+ tbantpatentariacatrelecList
				+ ", tbantnaopatentariacatrelecList="
				+ tbantnaopatentariacatrelecList
				+ ", tbcaraccatrelatorioecList=" + tbcaraccatrelatorioecList
				+ "]";
	}
    
}
