package br.gov.inpi.epec.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "TBCADTIPOANTERIORIDADE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbcadtipoanterioridade.findAll", query = "SELECT t FROM Tbcadtipoanterioridade t"),
    @NamedQuery(name = "Tbcadtipoanterioridade.findByIdCadtipoanterioridade", query = "SELECT t FROM Tbcadtipoanterioridade t WHERE t.idCadtipoanterioridade = :idCadtipoanterioridade"),
    @NamedQuery(name = "Tbcadtipoanterioridade.findByIOrdem", query = "SELECT t FROM Tbcadtipoanterioridade t WHERE t.iOrdem = :iOrdem"),
    @NamedQuery(name = "Tbcadtipoanterioridade.findByStrOmpi", query = "SELECT t FROM Tbcadtipoanterioridade t WHERE t.strOmpi = :strOmpi"),
    @NamedQuery(name = "Tbcadtipoanterioridade.findByStrPortugues", query = "SELECT t FROM Tbcadtipoanterioridade t WHERE t.strPortugues = :strPortugues"),
    @NamedQuery(name = "Tbcadtipoanterioridade.findByStrIngles", query = "SELECT t FROM Tbcadtipoanterioridade t WHERE t.strIngles = :strIngles"),
    @NamedQuery(name = "Tbcadtipoanterioridade.findByStrEspanhol", query = "SELECT t FROM Tbcadtipoanterioridade t WHERE t.strEspanhol = :strEspanhol"),
    @NamedQuery(name = "Tbcadtipoanterioridade.findByStrAlemao", query = "SELECT t FROM Tbcadtipoanterioridade t WHERE t.strAlemao = :strAlemao"),
    @NamedQuery(name = "Tbcadtipoanterioridade.findByStrFrances", query = "SELECT t FROM Tbcadtipoanterioridade t WHERE t.strFrances = :strFrances"),
    @NamedQuery(name = "Tbcadtipoanterioridade.findByIdAnterioridadenaopatentaria", query = "SELECT t FROM Tbcadtipoanterioridade t WHERE t.idAnterioridadenaopatentaria = :idAnterioridadenaopatentaria")})
public class Tbcadtipoanterioridade implements Serializable {
	
	
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CADTIPOANTERIORIDADE")
    private Integer idCadtipoanterioridade;
    
    
    @Column(name = "I_ORDEM")
    private Integer iOrdem;
    
    @Column(name = "STR_OMPI", length = 10)
    private String strOmpi;
    
  
    @Column(name = "STR_PORTUGUES", length = 500)
    private String strPortugues;
    

    @Column(name = "STR_INGLES", length = 500)
    private String strIngles;
    

    @Column(name = "STR_ESPANHOL", length = 500)
    private String strEspanhol;
    

    @Column(name = "STR_ALEMAO", length = 500)
    private String strAlemao;


    @Column(name = "STR_FRANCES", length = 500)
    private String strFrances;
    

    @Column(name = "ID_ANTERIORIDADENAOPATENTARIA", length = 1)
    private String idAnterioridadenaopatentaria;

    public Tbcadtipoanterioridade() {
    }

    public Tbcadtipoanterioridade(Integer idCadtipoanterioridade) {
        this.idCadtipoanterioridade = idCadtipoanterioridade;
    }

    public Tbcadtipoanterioridade(Integer idCadtipoanterioridade, String strPortugues, String strIngles, String strEspanhol, String strAlemao, String strFrances) {
        this.idCadtipoanterioridade = idCadtipoanterioridade;
        this.strPortugues = strPortugues;
        this.strIngles = strIngles;
        this.strEspanhol = strEspanhol;
        this.strAlemao = strAlemao;
        this.strFrances = strFrances;
    }

    public Integer getIdCadtipoanterioridade() {
        return idCadtipoanterioridade;
    }

    public void setIdCadtipoanterioridade(Integer idCadtipoanterioridade) {
        this.idCadtipoanterioridade = idCadtipoanterioridade;
    }

    public Integer getIOrdem() {
        return iOrdem;
    }

    public void setIOrdem(Integer iOrdem) {
        this.iOrdem = iOrdem;
    }

    public String getStrOmpi() {
        return strOmpi;
    }

    public void setStrOmpi(String strOmpi) {
        this.strOmpi = strOmpi;
    }

    public String getStrPortugues() {
        return strPortugues;
    }

    public void setStrPortugues(String strPortugues) {
        this.strPortugues = strPortugues;
    }

    public String getStrIngles() {
        return strIngles;
    }

    public void setStrIngles(String strIngles) {
        this.strIngles = strIngles;
    }

    public String getStrEspanhol() {
        return strEspanhol;
    }

    public void setStrEspanhol(String strEspanhol) {
        this.strEspanhol = strEspanhol;
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

    public String getIdAnterioridadenaopatentaria() {
        return idAnterioridadenaopatentaria;
    }

    public void setIdAnterioridadenaopatentaria(String idAnterioridadenaopatentaria) {
        this.idAnterioridadenaopatentaria = idAnterioridadenaopatentaria;
    }

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (idCadtipoanterioridade != null ? idCadtipoanterioridade.hashCode() : 0);
        return hash;
    }*/

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tbcadtipoanterioridade)) {
            return false;
        }
        Tbcadtipoanterioridade other = (Tbcadtipoanterioridade) object;
        if ((this.idCadtipoanterioridade == null && other.idCadtipoanterioridade != null) || (this.idCadtipoanterioridade != null && !this.idCadtipoanterioridade.equals(other.idCadtipoanterioridade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbcadtipoanterioridade[ idCadtipoanterioridade=" + idCadtipoanterioridade + " ]";
    }
    
}