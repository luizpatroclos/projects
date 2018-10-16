/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBLOGFAMILIA")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tblogfamilia.findAll", query = "SELECT t FROM Tblogfamilia t"), @NamedQuery(name = "Tblogfamilia.countAll", query = "SELECT COUNT(t) FROM Tblogfamilia t"),
		@NamedQuery(name = "Tblogfamilia.findByIdLogfamilia", query = "SELECT t FROM Tblogfamilia t WHERE t.idLogfamilia = :idLogfamilia"),
		@NamedQuery(name = "Tblogfamilia.findByIdFamiliaEc", query = "SELECT t FROM Tblogfamilia t WHERE t.idFamiliaEc = :idFamiliaEc order BY t.datahora DESC"),
		@NamedQuery(name = "Tblogfamilia.findByStrUsuario", query = "SELECT t FROM Tblogfamilia t WHERE t.strUsuario = :strUsuario"),
		@NamedQuery(name = "Tblogfamilia.findByDDatahora", query = "SELECT t FROM Tblogfamilia t WHERE t.datahora = :datahora"),
		@NamedQuery(name = "Tblogfamilia.findByStrAcao", query = "SELECT t FROM Tblogfamilia t WHERE t.strAcao = :strAcao"),
		@NamedQuery(name = "Tblogfamilia.findByStrAtivo", query = "SELECT t FROM Tblogfamilia t WHERE t.strAtivo = :strAtivo") })
public class Tblogfamilia implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOGFAMILIA")
    private Long idLogfamilia;
    

    @Column(name = "ID_FAMILIA_EC")
    private long idFamiliaEc;
    
  

    @Column(name = "STR_USUARIO")
    private String strUsuario;
    

    @Column(name = "D_DATAHORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datahora;
    


    @Column(name = "STR_ACAO")
    private String strAcao;
    

    @Column(name = "STR_ATIVO")
    private String strAtivo;
    

    @Column(name = "TX_ATIVO")
    private String txAtivo;
  
	@Transient
    private Tbcadusuario usuario;
    
	
	  
    public Tbcadusuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Tbcadusuario usuario) {
		this.usuario = usuario;
	}

	

    public Tblogfamilia() {
    }

    public Tblogfamilia(Long idLogfamilia) {
        this.idLogfamilia = idLogfamilia;
    }

    public Tblogfamilia(Long idLogfamilia, long idFamiliaEc, String strUsuario, Date datahora, String strAcao, String strAtivo, String txAtivo) {
        this.idLogfamilia = idLogfamilia;
        this.idFamiliaEc = idFamiliaEc;
        this.strUsuario = strUsuario;
        this.datahora = datahora;
        this.strAcao = strAcao;
        this.strAtivo = strAtivo;
        this.txAtivo = txAtivo;
    }

    public Long getIdLogfamilia() {
        return idLogfamilia;
    }

    public void setIdLogfamilia(Long idLogfamilia) {
        this.idLogfamilia = idLogfamilia;
    }

    public long getIdFamiliaEc() {
        return idFamiliaEc;
    }

    public void setIdFamiliaEc(long idFamiliaEc) {
        this.idFamiliaEc = idFamiliaEc;
    }

    public String getStrUsuario() {
        return strUsuario;
    }

    public void setStrUsuario(String strUsuario) {
        this.strUsuario = strUsuario;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public String getStrAcao() {
        return strAcao;
    }

    public void setStrAcao(String strAcao) {
        this.strAcao = strAcao;
    }

    public String getStrAtivo() {
        return strAtivo;
    }

    public void setStrAtivo(String strAtivo) {
        this.strAtivo = strAtivo;
    }

    public String getTxAtivo() {
        return txAtivo;
    }

    public void setTxAtivo(String txAtivo) {
        this.txAtivo = txAtivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogfamilia != null ? idLogfamilia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblogfamilia)) {
            return false;
        }
        Tblogfamilia other = (Tblogfamilia) object;
        if ((this.idLogfamilia == null && other.idLogfamilia != null) || (this.idLogfamilia != null && !this.idLogfamilia.equals(other.idLogfamilia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tblogfamilia[ idLogfamilia=" + idLogfamilia + " ]";
    }
    
}
