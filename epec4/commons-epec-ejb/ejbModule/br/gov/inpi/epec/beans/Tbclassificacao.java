/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBCLASSIFICACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbclassificacao.findAll", query = "SELECT t FROM Tbclassificacao t order by t.txClassificacao"),
    @NamedQuery(name = "Tbclassificacao.findByIdClassificacao", query = "SELECT t FROM Tbclassificacao t WHERE t.idClassificacao = :idClassificacao"),
    @NamedQuery(name = "Tbclassificacao.findByTxClassificacao", query = "SELECT t FROM Tbclassificacao t WHERE t.txClassificacao = :txClassificacao")})
public class Tbclassificacao implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLASSIFICACAO")
    private Long idClassificacao;
    
    @Column(name = "TX_CLASSIFICACAO")
    private String txClassificacao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbclassificacao")
    private List<Tbclassificacaopatente> tbclassificacaopatenteList;
    
    @Transient
    private long ordem;

    public Tbclassificacao() {
    }

    public Tbclassificacao(Long idClassificacao) {
        this.idClassificacao = idClassificacao;
    }

    public Long getIdClassificacao() {
        return idClassificacao;
    }

    public void setIdClassificacao(Long idClassificacao) {
        this.idClassificacao = idClassificacao;
    }

    public String getTxClassificacao() {
        return txClassificacao;
    }

    public void setTxClassificacao(String txClassificacao) {
        this.txClassificacao = txClassificacao;
    }

    @XmlTransient
    public List<Tbclassificacaopatente> getTbclassificacaopatenteList() {
        return tbclassificacaopatenteList;
    }

    public void setTbclassificacaopatenteList(List<Tbclassificacaopatente> tbclassificacaopatenteList) {
        this.tbclassificacaopatenteList = tbclassificacaopatenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClassificacao != null ? idClassificacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbclassificacao)) {
            return false;
        }
        Tbclassificacao other = (Tbclassificacao) object;
        if ((this.idClassificacao == null && other.idClassificacao != null) || (this.idClassificacao != null && !this.idClassificacao.equals(other.idClassificacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbclassificacao[ idClassificacao=" + idClassificacao + " ]";
    }

	public long getOrdem() {
		return ordem;
	}

	public void setOrdem(long ordem) {
		this.ordem = ordem;
	}
    
}
