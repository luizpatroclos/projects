/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author lasilva
 */
@Embeddable
public class TbclassificacaopatentePK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5382844482551750813L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name = "ID_CLASSIFICACAO")
    private long idClassificacao;
	
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PATENTE_EC")
    private long idPatenteEc;

    public TbclassificacaopatentePK() {
    }

    public TbclassificacaopatentePK(long idClassificacao, long idPatenteEc) {
        this.idClassificacao = idClassificacao;
        this.idPatenteEc = idPatenteEc;
    }

    public long getIdClassificacao() {
        return idClassificacao;
    }

    public void setIdClassificacao(long idClassificacao) {
        this.idClassificacao = idClassificacao;
    }

    public long getIdPatenteEc() {
        return idPatenteEc;
    }

    public void setIdPatenteEc(long idPatenteEc) {
        this.idPatenteEc = idPatenteEc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idClassificacao;
        hash += (int) idPatenteEc;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbclassificacaopatentePK)) {
            return false;
        }
        TbclassificacaopatentePK other = (TbclassificacaopatentePK) object;
        if (this.idClassificacao != other.idClassificacao) {
            return false;
        }
        if (this.idPatenteEc != other.idPatenteEc) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.TbclassificacaopatentePK[ idClassificacao=" + idClassificacao + ", idPatenteEc=" + idPatenteEc + " ]";
    }
    
}
