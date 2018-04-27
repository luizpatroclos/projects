/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.intercarga.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "tb_acesso_carga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbAcessoCarga.findAll", query = "SELECT t FROM TbAcessoCarga t"),
    @NamedQuery(name = "TbAcessoCarga.findByIdLogin", query = "SELECT t FROM TbAcessoCarga t WHERE t.idLogin = :idLogin"),
    @NamedQuery(name = "TbAcessoCarga.findByNmLogin", query = "SELECT t FROM TbAcessoCarga t WHERE t.nmLogin = :nmLogin")})
public class TbAcessoCarga implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "id_login", nullable = false)
    @Id
    private Integer idLogin;
    
    @Size(max = 15)
    @Column(name = "nm_login")
    private String nmLogin;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLogin")
    private List<TbAgendaCarga> tbAgendaCargaList;
    
    /*@OneToMany(mappedBy = "idLogin")
    private List<TbAgendaCarga> tbAgendaCargaList;*/

    public TbAcessoCarga() {
    }

    public TbAcessoCarga(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public TbAcessoCarga(Integer idLogin, String nmLogin) {
        this.idLogin = idLogin;
        this.nmLogin = nmLogin;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public String getNmLogin() {
        return nmLogin;
    }

    public void setNmLogin(String nmLogin) {
        this.nmLogin = nmLogin;
    }

    @XmlTransient
    public List<TbAgendaCarga> getTbAgendaCargaList() {
        return tbAgendaCargaList;
    }

    public void setTbAgendaCargaList(List<TbAgendaCarga> tbAgendaCargaList) {
        this.tbAgendaCargaList = tbAgendaCargaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogin != null ? idLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbAcessoCarga)) {
            return false;
        }
        TbAcessoCarga other = (TbAcessoCarga) object;
        if ((this.idLogin == null && other.idLogin != null) || (this.idLogin != null && !this.idLogin.equals(other.idLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.intercarga.beans.TbAcessoCarga[ idLogin=" + idLogin + " ]";
    }
    
}
