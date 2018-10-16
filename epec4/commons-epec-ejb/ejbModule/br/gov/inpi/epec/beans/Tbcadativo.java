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
@Table(name = "TBCADATIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbcadativo.findAll", query = "SELECT t FROM Tbcadativo t"),
    @NamedQuery(name = "Tbcadativo.findByIdAtivo", query = "SELECT t FROM Tbcadativo t WHERE t.idAtivo = :idAtivo"),
    @NamedQuery(name = "Tbcadativo.findByStrAtivo", query = "SELECT t FROM Tbcadativo t WHERE t.strAtivo = :strAtivo")})
public class Tbcadativo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_ATIVO")
    private Integer idAtivo;
    

    @Size(min = 1, max = 45)
    @Column(name = "STR_ATIVO")
    private String strAtivo;
    

    public Tbcadativo() {
    }

    public Tbcadativo(Integer idAtivo) {
        this.idAtivo = idAtivo;
    }

    public Tbcadativo(Integer idAtivo, String strAtivo) {
        this.idAtivo = idAtivo;
        this.strAtivo = strAtivo;
    }

    public Integer getIdAtivo() {
        return idAtivo;
    }

    public void setIdAtivo(Integer idAtivo) {
        this.idAtivo = idAtivo;
    }

    public String getStrAtivo() {
        return strAtivo;
    }

    public void setStrAtivo(String strAtivo) {
        this.strAtivo = strAtivo;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtivo != null ? idAtivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbcadativo)) {
            return false;
        }
        Tbcadativo other = (Tbcadativo) object;
        if ((this.idAtivo == null && other.idAtivo != null) || (this.idAtivo != null && !this.idAtivo.equals(other.idAtivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.inpi.epec.beans.Tbcadativo[ idAtivo=" + idAtivo + " ]";
    }
    
}
