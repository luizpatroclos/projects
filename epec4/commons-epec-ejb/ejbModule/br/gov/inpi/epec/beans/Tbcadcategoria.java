/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBCADCATEGORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbcadcategoria.findAll", query = "SELECT t FROM Tbcadcategoria t"),
    @NamedQuery(name = "Tbcadcategoria.findByIdCategoria", query = "SELECT t FROM Tbcadcategoria t WHERE t.idCategoria = :idCategoria"),
    @NamedQuery(name = "Tbcadcategoria.findByStrPortugues", query = "SELECT t FROM Tbcadcategoria t WHERE t.strPortugues = :strPortugues"),
    @NamedQuery(name = "Tbcadcategoria.findByStrEspanhol", query = "SELECT t FROM Tbcadcategoria t WHERE t.strEspanhol = :strEspanhol"),
    @NamedQuery(name = "Tbcadcategoria.findByStrIngles", query = "SELECT t FROM Tbcadcategoria t WHERE t.strIngles = :strIngles"),
    @NamedQuery(name = "Tbcadcategoria.findByStrAlemao", query = "SELECT t FROM Tbcadcategoria t WHERE t.strAlemao = :strAlemao"),
    @NamedQuery(name = "Tbcadcategoria.findByStrFrances", query = "SELECT t FROM Tbcadcategoria t WHERE t.strFrances = :strFrances")})
public class Tbcadcategoria implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA")
    private Integer idCategoria;
    
    @Column(name = "STR_PORTUGUES")
    private String strPortugues;
    
    @Column(name = "STR_ESPANHOL")
    private String strEspanhol;
    

    @Column(name = "STR_INGLES")
    private String strIngles;
    

    @Column(name = "STR_ALEMAO")
    private String strAlemao;
    
    @Column(name = "STR_FRANCES")
    private String strFrances;
    
    @Transient
    private String label;
    
    public String getLabel() {    	
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
    private List<Tbcatrelatorioec> tbcatrelatorioecList;

    public Tbcadcategoria() {
    }

    public Tbcadcategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Tbcadcategoria(Integer idCategoria, String strPortugues, String strEspanhol, String strIngles, String strAlemao, String strFrances) {
        this.idCategoria = idCategoria;
        this.strPortugues = strPortugues;
        this.strEspanhol = strEspanhol;
        this.strIngles = strIngles;
        this.strAlemao = strAlemao;
        this.strFrances = strFrances;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getStrPortugues() {
        return strPortugues;
    }

    public void setStrPortugues(String strPortugues) {
        this.strPortugues = strPortugues;
    }

    public String getStrEspanhol() {
        return strEspanhol;
    }

    public void setStrEspanhol(String strEspanhol) {
        this.strEspanhol = strEspanhol;
    }

    public String getStrIngles() {
        return strIngles;
    }

    public void setStrIngles(String strIngles) {
        this.strIngles = strIngles;
    }

    public String getStrAlemao() {
        return strAlemao;
    }

    public void setStrAlemao(String strAlemao) {
        this.strAlemao = strAlemao;
    }

    public String getStrFrances() {
        return strFrances;
    }

    public void setStrFrances(String strFrances) {
        this.strFrances = strFrances;
    }

    @XmlTransient
    public List<Tbcatrelatorioec> getTbcatrelatorioecList() {
        return tbcatrelatorioecList;
    }

    public void setTbcatrelatorioecList(List<Tbcatrelatorioec> tbcatrelatorioecList) {
        this.tbcatrelatorioecList = tbcatrelatorioecList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbcadcategoria)) {
            return false;
        }
        Tbcadcategoria other = (Tbcadcategoria) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbcadcategoria[ idCategoria=" + idCategoria + " ]";
    }
    
}
