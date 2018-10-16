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
@Table(name = "TBCADPAIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbcadpais.findAll", query = "SELECT t FROM Tbcadpais t"),
    @NamedQuery(name = "Tbcadpais.findByIdPais", query = "SELECT t FROM Tbcadpais t WHERE t.idPais = :idPais"),
    @NamedQuery(name = "Tbcadpais.findByStrCodPais", query = "SELECT t FROM Tbcadpais t WHERE t.strCodPais = :strCodPais"),
    @NamedQuery(name = "Tbcadpais.findByStrNomePais", query = "SELECT t FROM Tbcadpais t WHERE t.strNomePais = :strNomePais")})
public class Tbcadpais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAIS")
    private Long idPais;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "STR_COD_PAIS")
    private String strCodPais;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "STR_NOME_PAIS")
    private String strNomePais;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPais")
    private List<Tbcadentidade> tbcadentidadeList;
    

    public Tbcadpais() {
    }

    public Tbcadpais(Long idPais) {
        this.idPais = idPais;
    }

    public Tbcadpais(Long idPais, String strCodPais, String strNomePais) {
        this.idPais = idPais;
        this.strCodPais = strCodPais;
        this.strNomePais = strNomePais;
    }

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public String getStrCodPais() {
        return strCodPais;
    }

    public void setStrCodPais(String strCodPais) {
        this.strCodPais = strCodPais;
    }

    public String getStrNomePais() {
        return strNomePais;
    }

    public void setStrNomePais(String strNomePais) {
        this.strNomePais = strNomePais;
    }

    @XmlTransient
    public List<Tbcadentidade> getTbcadentidadeList() {
        return tbcadentidadeList;
    }

    public void setTbcadentidadeList(List<Tbcadentidade> tbcadentidadeList) {
        this.tbcadentidadeList = tbcadentidadeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPais != null ? idPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbcadpais)) {
            return false;
        }
        Tbcadpais other = (Tbcadpais) object;
        if ((this.idPais == null && other.idPais != null) || (this.idPais != null && !this.idPais.equals(other.idPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbcadpais[ idPais=" + idPais + " ]";
    }
    
    @Transient
    private String imagem;


	public String getImagem() {
		return  "images/_bandeirasP/" + strCodPais + ".png";
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
}
