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
@Table(name = "TBPERFILUSUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbperfilusuario.findAll", query = "SELECT t FROM Tbperfilusuario t"),
    @NamedQuery(name = "Tbperfilusuario.findByIdPerfilusuario", query = "SELECT t FROM Tbperfilusuario t WHERE t.idPerfilusuario = :idPerfilusuario"),
    @NamedQuery(name = "Tbperfilusuario.findByStrPerfil", query = "SELECT t FROM Tbperfilusuario t WHERE t.strPerfil = :strPerfil")})
public class Tbperfilusuario implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERFILUSUARIO", nullable = false)
    private Long idPerfilusuario;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STR_PERFIL", nullable = false, length = 100)
    private String strPerfil;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerfilusuario")
    private List<Tbcadusuario> tbcadusuarioList;
    
    @Transient
    private boolean ehAdmEntidade;
    
    @Transient
    private boolean NaoEhExaminador;
    
    @Transient
    private boolean ehAdmSistema;
    
    
    public boolean isNaoEhExaminador() {
    	if(this.idPerfilusuario == 1L){
    		return false;
    	}
		return true;
	}

	public void setNaoEhExaminador(boolean naoEhExaminador) {
		NaoEhExaminador = naoEhExaminador;
	}

	public boolean isEhAdmEntidade() {
    	if(this.idPerfilusuario == 2L){
    		return true;
    	}
		return false;
	}

	public void setEhAdmEntidade(boolean ehAdmEntidade) {
		this.ehAdmEntidade = ehAdmEntidade;
	}

	public Tbperfilusuario() {
    }

    public Tbperfilusuario(Long idPerfilusuario) {
        this.idPerfilusuario = idPerfilusuario;
    }

    public Tbperfilusuario(Long idPerfilusuario, String strPerfil) {
        this.idPerfilusuario = idPerfilusuario;
        this.strPerfil = strPerfil;
    }

    public Long getIdPerfilusuario() {
        return idPerfilusuario;
    }

    public void setIdPerfilusuario(Long idPerfilusuario) {
        this.idPerfilusuario = idPerfilusuario;
    }

    public String getStrPerfil() {
        return strPerfil;
    }

    public void setStrPerfil(String strPerfil) {
        this.strPerfil = strPerfil;
    }

    @XmlTransient
    public List<Tbcadusuario> getTbcadusuarioList() {
        return tbcadusuarioList;
    }

    public void setTbcadusuarioList(List<Tbcadusuario> tbcadusuarioList) {
        this.tbcadusuarioList = tbcadusuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfilusuario != null ? idPerfilusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbperfilusuario)) {
            return false;
        }
        Tbperfilusuario other = (Tbperfilusuario) object;
        if ((this.idPerfilusuario == null && other.idPerfilusuario != null) || (this.idPerfilusuario != null && !this.idPerfilusuario.equals(other.idPerfilusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbperfilusuario[ idPerfilusuario=" + idPerfilusuario + " ]";
    }

	public boolean isEhAdmSistema() {
		if(this.idPerfilusuario == 3L){
    		return true;
    	}
		return false;
	}

	public void setEhAdmSistema(boolean ehAdmSistema) {
		this.ehAdmSistema = ehAdmSistema;
	}
    
}
