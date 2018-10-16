/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBFAMILIAEC")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbfamiliaec.findAll", query = "SELECT t FROM Tbfamiliaec t"), @NamedQuery(name = "Tbfamiliaec.countAll", query = "SELECT COUNT(t) from Tbfamiliaec t"),
		@NamedQuery(name = "Tbfamiliaec.findByIdFamiliaEc", query = "SELECT t FROM Tbfamiliaec t WHERE t.idFamiliaEc = :idFamiliaEc"),
		@NamedQuery(name = "Tbfamiliaec.findByBPublico", query = "SELECT t FROM Tbfamiliaec t WHERE t.publico = :publico"),
		@NamedQuery(name = "Tbfamiliaec.findByDDatahora", query = "SELECT t FROM Tbfamiliaec t WHERE t.dDatahora = :dDatahora") })
public class Tbfamiliaec implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FAMILIA_EC")
    private Long idFamiliaEc;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_PUBLICO", nullable = false)
    private boolean publico;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "D_DATAHORA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDatahora;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFamiliaEc")
    private List<Tbarquivofamilia> tbarquivofamiliaList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFamiliaEc", fetch = FetchType.EAGER)
    private List<Tbpatenteec> tbpatenteecList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFamiliaEc")
    private List<Tbcomentariosfamiliaec> tbcomentariosfamiliaecList;

    @Transient
    private String status;
    
    public String getStatus() {
    	if(getPublico()){
    		return "Público";
    	}
    	return "Privado";
 	}

 	public void setStatus(String status) {
 		this.status = status;
 	}

    
    
    public Tbfamiliaec() {
    }

    public Tbfamiliaec(Long idFamiliaEc) {
        this.idFamiliaEc = idFamiliaEc;
    }

    public Tbfamiliaec(Long idFamiliaEc, boolean publico, Date dDatahora) {
        this.idFamiliaEc = idFamiliaEc;
        this.publico = publico;
        this.dDatahora = dDatahora;
    }

    public Long getIdFamiliaEc() {
        return idFamiliaEc;
    }

    public void setIdFamiliaEc(Long idFamiliaEc) {
        this.idFamiliaEc = idFamiliaEc;
    }

    public boolean getPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public Date getDDatahora() {
        return dDatahora;
    }

    public void setDDatahora(Date dDatahora) {
        this.dDatahora = dDatahora;
    }

    @XmlTransient
    public List<Tbarquivofamilia> getTbarquivofamiliaList() {
        return tbarquivofamiliaList;
    }

    public void setTbarquivofamiliaList(List<Tbarquivofamilia> tbarquivofamiliaList) {
        this.tbarquivofamiliaList = tbarquivofamiliaList;
    }

    @XmlTransient
    public List<Tbpatenteec> getTbpatenteecList() {
        return tbpatenteecList;
    }

    public void setTbpatenteecList(List<Tbpatenteec> tbpatenteecList) {
        this.tbpatenteecList = tbpatenteecList;
    }

    @XmlTransient
    public List<Tbcomentariosfamiliaec> getTbcomentariosfamiliaecList() {
        return tbcomentariosfamiliaecList;
    }

    public void setTbcomentariosfamiliaecList(List<Tbcomentariosfamiliaec> tbcomentariosfamiliaecList) {
        this.tbcomentariosfamiliaecList = tbcomentariosfamiliaecList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamiliaEc != null ? idFamiliaEc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbfamiliaec)) {
            return false;
        }
        Tbfamiliaec other = (Tbfamiliaec) object;
        if ((this.idFamiliaEc == null && other.idFamiliaEc != null) || (this.idFamiliaEc != null && !this.idFamiliaEc.equals(other.idFamiliaEc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbfamiliaec[ idFamiliaEc=" + idFamiliaEc + " ]";
    }
    
}
