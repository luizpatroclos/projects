/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBCADSTATUSRELATORIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbcadstatusrelatorio.findAll", query = "SELECT t FROM Tbcadstatusrelatorio t"),
    @NamedQuery(name = "Tbcadstatusrelatorio.findByIdStatus", query = "SELECT t FROM Tbcadstatusrelatorio t WHERE t.idStatus = :idStatus"),
    @NamedQuery(name = "Tbcadstatusrelatorio.findByStrStatus", query = "SELECT t FROM Tbcadstatusrelatorio t WHERE t.strStatus = :strStatus")})
public class Tbcadstatusrelatorio implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_STATUS")
    private Long idStatus;
    

    @Column(name = "STR_STATUS")
    private String strStatus;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStatus")
    private List<Tbrelatorioec> tbrelatorioecList;

    public Tbcadstatusrelatorio() {
    }

    public Tbcadstatusrelatorio(Long idStatus) {
        this.idStatus = idStatus;
    }

    public Tbcadstatusrelatorio(Long idStatus, String strStatus) {
        this.idStatus = idStatus;
        this.strStatus = strStatus;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    @XmlTransient
    public List<Tbrelatorioec> getTbrelatorioecList() {
        return tbrelatorioecList;
    }

    public void setTbrelatorioecList(List<Tbrelatorioec> tbrelatorioecList) {
        this.tbrelatorioecList = tbrelatorioecList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatus != null ? idStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbcadstatusrelatorio)) {
            return false;
        }
        Tbcadstatusrelatorio other = (Tbcadstatusrelatorio) object;
        if ((this.idStatus == null && other.idStatus != null) || (this.idStatus != null && !this.idStatus.equals(other.idStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbcadstatusrelatorio[ idStatus=" + idStatus + " ]";
    }
    
}
