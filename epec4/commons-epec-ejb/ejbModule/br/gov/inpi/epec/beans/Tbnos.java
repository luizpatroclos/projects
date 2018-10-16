/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBNOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbnos.findAll", query = "SELECT t FROM Tbnos t"),
    @NamedQuery(name = "Tbnos.findByIdNo", query = "SELECT t FROM Tbnos t WHERE t.idNo = :idNo"),
    @NamedQuery(name = "Tbnos.findByClasulaTipo", query = "SELECT t FROM Tbnos t WHERE t.idClausulaTipo.idClausulaTipo = :idClausulaTipo"),
    @NamedQuery(name = "Tbnos.findByEntidade", query = "SELECT t FROM Tbnos t WHERE t.idEntidadeEc.idEntidadeEc = :idEntidadeEc")})
public class Tbnos implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NO")
    private Long idNo;
    
    @Column(name = "TX_TITULO_PORTUGUES")
    private String txTituloPortugues;
    
    
    @Column(name = "TX_TITULO_INGLES")
    private String txTituloIngles;
    
 
    @Column(name = "TX_TITULO_ESPANHOL")
    private String txTituloEspanhol;
    
 

	@JoinTable(name = "TBFILHOS", joinColumns = {
    	@JoinColumn(name = "ID_PAI", referencedColumnName = "ID_NO")}, inverseJoinColumns = {  
    	@JoinColumn(name = "ID_FILHO", referencedColumnName = "ID_NO")})  
    @OneToMany(fetch = FetchType.EAGER )
    @Fetch(FetchMode.SELECT)
    private List<Tbnos> tbnosList;
        
    
    @JoinTable(name = "TBFILHOS", joinColumns = {
            @JoinColumn(name = "ID_FILHO", referencedColumnName = "ID_NO")}, inverseJoinColumns = {
            @JoinColumn(name = "ID_PAI", referencedColumnName = "ID_NO")})
        @ManyToOne(fetch = FetchType.EAGER )
        @Fetch(FetchMode.SELECT)
        private Tbnos idTbnoPai;   
    
    
    
    @JoinColumn(name = "ID_CLAUSULA_TIPO", referencedColumnName = "ID_CLAUSULA_TIPO")
    @ManyToOne
    private Tbclausulatipo idClausulaTipo;
    
    @JoinColumn(name = "ID_ENTIDADE_EC", referencedColumnName = "ID_ENTIDADE_EC")
    @ManyToOne
    private Tbcadentidade idEntidadeEc;

    public Tbnos() {
    }

    public Tbnos(Long idNo) {
        this.idNo = idNo;
    }

    public Tbnos(Long idNo, String txTituloPortugues, String txTituloIngles, String txTituloEspanhol) {
        this.idNo = idNo;
        this.txTituloPortugues = txTituloPortugues;
        this.txTituloIngles = txTituloIngles;
        this.txTituloEspanhol = txTituloEspanhol;
    }

    public Long getIdNo() {
        return idNo;
    }

    public void setIdNo(Long idNo) {
        this.idNo = idNo;
    }

    public String getTxTituloPortugues() {
    	if(txTituloPortugues != null){
    		this.txTituloPortugues.toUpperCase();
    	}
        return txTituloPortugues;
    }

    public void setTxTituloPortugues(String txTituloPortugues) {
        this.txTituloPortugues = txTituloPortugues;
    }

    public String getTxTituloIngles() {
    	if(txTituloIngles != null){
    		this.txTituloIngles.toUpperCase();
    	}
        return txTituloIngles;
    }

    public void setTxTituloIngles(String txTituloIngles) {
        this.txTituloIngles = txTituloIngles;
    }

    public String getTxTituloEspanhol() {
    	if(txTituloEspanhol != null){
    		this.txTituloEspanhol.toUpperCase();
    	}

        return txTituloEspanhol;
    }

    public void setTxTituloEspanhol(String txTituloEspanhol) {
        this.txTituloEspanhol = txTituloEspanhol;
    }

    @XmlTransient
    public List<Tbnos> getTbnosList() {
        return tbnosList;
    }

    public void setTbnosList(List<Tbnos> tbnosList) {
        this.tbnosList = tbnosList;
    }


    public Tbclausulatipo getIdClausulaTipo() {
    	
        return idClausulaTipo;
    }

    public void setIdClausulaTipo(Tbclausulatipo idClausulaTipo) {
        this.idClausulaTipo = idClausulaTipo;
    }
    
    public Tbcadentidade getIdEntidadeEc() {
        return idEntidadeEc;
    }

    public void setIdEntidadeEc(Tbcadentidade idEntidadeEc) {
        this.idEntidadeEc = idEntidadeEc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNo != null ? idNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbnos)) {
            return false;
        }
        Tbnos other = (Tbnos) object;
        if ((this.idNo == null && other.idNo != null) || (this.idNo != null && !this.idNo.equals(other.idNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.inpi.epec.beans.Tbnos[ idNo=" + idNo + " ]";
    }

    @XmlTransient
	public Tbnos getIdTbnoPai() {
		return idTbnoPai;
	}

	public void setIdTbnoPai(Tbnos idTbnoPai) {
		this.idTbnoPai = idTbnoPai;
	}

    
}
