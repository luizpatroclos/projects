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
@Table(name = "TBCLAUSULATIPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbclausulatipo.findAll", query = "SELECT t FROM Tbclausulatipo t"),
    @NamedQuery(name = "Tbclausulatipo.findByIdClausulaTipo", query = "SELECT t FROM Tbclausulatipo t WHERE t.idClausulaTipo = :idClausulaTipo")})
public class Tbclausulatipo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLAUSULA_TIPO")
    private Long idClausulaTipo;
   
    @Column(name = "TX_PORTUGUES")
    private String txPortugues;   
 
    @Column(name = "TX_INGLES")
    private String txIngles;
    
    @Column(name = "TX_ESPANHOL")
    private String txEspanhol;
    
    
    @Transient
    private String name;
    
    public String getName() {
    	return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    @OneToMany(mappedBy = "idClausulaTipo")
    private List<Tbnos> tbnosList;

    public Tbclausulatipo() {
    }

    public Tbclausulatipo(Long idClausulaTipo) {
        this.idClausulaTipo = idClausulaTipo;
    }

    public Tbclausulatipo(Long idClausulaTipo, String txPortugues, String txIngles, String txEspanhol) {
        this.idClausulaTipo = idClausulaTipo;
        this.txPortugues = txPortugues;
        this.txIngles = txIngles;
        this.txEspanhol = txEspanhol;
    }

    public Long getIdClausulaTipo() {
        return idClausulaTipo;
    }

    public void setIdClausulaTipo(Long idClausulaTipo) {
        this.idClausulaTipo = idClausulaTipo;
    }

    public String getTxPortugues() {
        return txPortugues;
    }

    public void setTxPortugues(String txPortugues) {
        this.txPortugues = txPortugues;
    }

    public String getTxIngles() {
        return txIngles;
    }

    public void setTxIngles(String txIngles) {
        this.txIngles = txIngles;
    }

    public String getTxEspanhol() {
        return txEspanhol;
    }

    public void setTxEspanhol(String txEspanhol) {
        this.txEspanhol = txEspanhol;
    }

  
    @XmlTransient
    public List<Tbnos> getTbnosList() {
        return tbnosList;
    }

    public void setTbnosList(List<Tbnos> tbnosList) {
        this.tbnosList = tbnosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClausulaTipo != null ? idClausulaTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbclausulatipo)) {
            return false;
        }
        Tbclausulatipo other = (Tbclausulatipo) object;
        if ((this.idClausulaTipo == null && other.idClausulaTipo != null) || (this.idClausulaTipo != null && !this.idClausulaTipo.equals(other.idClausulaTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.inpi.epec.beans.Tbclausulatipo[ idClausulaTipo=" + idClausulaTipo + " ]";
    }
    
}
