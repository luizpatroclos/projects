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
@Table(name = "TBCADTIPOARQUIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbcadtipoarquivo.findAll", query = "SELECT t FROM Tbcadtipoarquivo t"),
    @NamedQuery(name = "Tbcadtipoarquivo.findByIdCadtipoarquivo", query = "SELECT t FROM Tbcadtipoarquivo t WHERE t.idCadtipoarquivo = :idCadtipoarquivo"),
    @NamedQuery(name = "Tbcadtipoarquivo.findByStrTipo", query = "SELECT t FROM Tbcadtipoarquivo t WHERE t.strTipo = :strTipo"),
    @NamedQuery(name = "Tbcadtipoarquivo.findByStrExtensao", query = "SELECT t FROM Tbcadtipoarquivo t WHERE t.strExtensao = :strExtensao")})
public class Tbcadtipoarquivo implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CADTIPOARQUIVO", nullable = false)
    private Integer idCadtipoarquivo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STR_TIPO", nullable = false, length = 100)
    private String strTipo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "STR_EXTENSAO", nullable = false, length = 10)
    private String strExtensao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCadtipoarquivo")
    private List<Tbarquivofamilia> tbarquivofamiliaList;

    public Tbcadtipoarquivo() {
    }

    public Tbcadtipoarquivo(Integer idCadtipoarquivo) {
        this.idCadtipoarquivo = idCadtipoarquivo;
    }

    public Tbcadtipoarquivo(Integer idCadtipoarquivo, String strTipo, String strExtensao) {
        this.idCadtipoarquivo = idCadtipoarquivo;
        this.strTipo = strTipo;
        this.strExtensao = strExtensao;
    }

    public Integer getIdCadtipoarquivo() {
        return idCadtipoarquivo;
    }

    public void setIdCadtipoarquivo(Integer idCadtipoarquivo) {
        this.idCadtipoarquivo = idCadtipoarquivo;
    }

    public String getStrTipo() {
        return strTipo;
    }

    public void setStrTipo(String strTipo) {
        this.strTipo = strTipo;
    }

    public String getStrExtensao() {
        return strExtensao;
    }

    public void setStrExtensao(String strExtensao) {
        this.strExtensao = strExtensao;
    }

    @XmlTransient
    public List<Tbarquivofamilia> getTbarquivofamiliaList() {
        return tbarquivofamiliaList;
    }

    public void setTbarquivofamiliaList(List<Tbarquivofamilia> tbarquivofamiliaList) {
        this.tbarquivofamiliaList = tbarquivofamiliaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCadtipoarquivo != null ? idCadtipoarquivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbcadtipoarquivo)) {
            return false;
        }
        Tbcadtipoarquivo other = (Tbcadtipoarquivo) object;
        if ((this.idCadtipoarquivo == null && other.idCadtipoarquivo != null) || (this.idCadtipoarquivo != null && !this.idCadtipoarquivo.equals(other.idCadtipoarquivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbcadtipoarquivo[ idCadtipoarquivo=" + idCadtipoarquivo + " ]";
    }
    
}
