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
@Table(name = "TBCARACCATRELATORIOEC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbcaraccatrelatorioec.findAll", query = "SELECT t FROM Tbcaraccatrelatorioec t"),
    @NamedQuery(name = "Tbcaraccatrelatorioec.findByIdCaraccatrelatorio", query = "SELECT t FROM Tbcaraccatrelatorioec t WHERE t.idCaraccatrelatorio = :idCaraccatrelatorio"),
    @NamedQuery(name = "Tbcaraccatrelatorioec.findByIdCatrelatorioec", query = "SELECT t FROM Tbcaraccatrelatorioec t WHERE t.idCatrelatorioec.idCatrelatorioec = :idCatrelatorioec")})
public class Tbcaraccatrelatorioec implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARACCATRELATORIO")
    private Long idCaraccatrelatorio;
    
    @Column(name = "TX_CARACTERISTICA")
    private String txCaracteristica;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCaraccatrelatorio")
    private List<Tbantpatentaria> tbantpatentariaList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCaraccatrelatorio")
    private List<Tbantnaopatentaria> tbantnaopatentariaList;
    
    @JoinColumn(name = "ID_CATRELATORIOEC", referencedColumnName = "ID_CATRELATORIOEC")
    @ManyToOne
    private Tbcatrelatorioec idCatrelatorioec;

    public Tbcaraccatrelatorioec() {
    }

    public Tbcaraccatrelatorioec(Long idCaraccatrelatorio) {
        this.idCaraccatrelatorio = idCaraccatrelatorio;
    }

    public Tbcaraccatrelatorioec(Long idCaraccatrelatorio, String txCaracteristica) {
        this.idCaraccatrelatorio = idCaraccatrelatorio;
        this.txCaracteristica = txCaracteristica;
    }

    public Long getIdCaraccatrelatorio() {
        return idCaraccatrelatorio;
    }

    public void setIdCaraccatrelatorio(Long idCaraccatrelatorio) {
        this.idCaraccatrelatorio = idCaraccatrelatorio;
    }

    public String getTxCaracteristica() {
        return txCaracteristica;
    }

    public void setTxCaracteristica(String txCaracteristica) {
        this.txCaracteristica = txCaracteristica;
    }

    @XmlTransient
    public List<Tbantpatentaria> getTbantpatentariaList() {
        return tbantpatentariaList;
    }

    public void setTbantpatentariaList(List<Tbantpatentaria> tbantpatentariaList) {
        this.tbantpatentariaList = tbantpatentariaList;
    }

    @XmlTransient
    public List<Tbantnaopatentaria> getTbantnaopatentariaList() {
        return tbantnaopatentariaList;
    }

    public void setTbantnaopatentariaList(List<Tbantnaopatentaria> tbantnaopatentariaList) {
        this.tbantnaopatentariaList = tbantnaopatentariaList;
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
        hash += (idCaraccatrelatorio != null ? idCaraccatrelatorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbcaraccatrelatorioec)) {
            return false;
        }
        Tbcaraccatrelatorioec other = (Tbcaraccatrelatorioec) object;
        if ((this.idCaraccatrelatorio == null && other.idCaraccatrelatorio != null) || (this.idCaraccatrelatorio != null && !this.idCaraccatrelatorio.equals(other.idCaraccatrelatorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbcaraccatrelatorioec[ idCaraccatrelatorio=" + idCaraccatrelatorio + " ]";
    }
    
}
