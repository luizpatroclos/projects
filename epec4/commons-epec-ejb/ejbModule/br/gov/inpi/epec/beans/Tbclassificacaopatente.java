/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBCLASSIFICACAOPATENTE")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbclassificacaopatente.findAll", query = "SELECT t FROM Tbclassificacaopatente t"),
		@NamedQuery(name = "Tbclassificacaopatente.findByIdClassificacao", query = "SELECT t FROM Tbclassificacaopatente t WHERE t.tbclassificacaopatentePK.idClassificacao = :idClassificacao"),
		@NamedQuery(name = "Tbclassificacaopatente.findByIdPatenteEc", query = "SELECT t FROM Tbclassificacaopatente t WHERE t.tbclassificacaopatentePK.idPatenteEc = :idPatenteEc"),
		@NamedQuery(name = "Tbclassificacaopatente.findByClaasificacaoandPatente", query = "SELECT t FROM Tbclassificacaopatente t WHERE t.tbclassificacaopatentePK.idPatenteEc = :idPatenteEc AND t.tbclassificacaopatentePK.idClassificacao = :idClassificacao"),
		@NamedQuery(name = "Tbclassificacaopatente.findByIdOrdem", query = "SELECT t FROM Tbclassificacaopatente t WHERE t.idOrdem = :idOrdem") })
public class Tbclassificacaopatente implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected TbclassificacaopatentePK tbclassificacaopatentePK;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ID_ORDEM")
	private long idOrdem;

	@JoinColumn(name = "ID_PATENTE_EC", referencedColumnName = "ID_PATENTE_EC", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Tbpatenteec tbpatenteec;

	@JoinColumn(name = "ID_CLASSIFICACAO", referencedColumnName = "ID_CLASSIFICACAO", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Tbclassificacao tbclassificacao;

	public Tbclassificacaopatente() {
	}

	public Tbclassificacaopatente(TbclassificacaopatentePK tbclassificacaopatentePK) {
		this.tbclassificacaopatentePK = tbclassificacaopatentePK;
	}

	public Tbclassificacaopatente(TbclassificacaopatentePK tbclassificacaopatentePK, long idOrdem) {
		this.tbclassificacaopatentePK = tbclassificacaopatentePK;
		this.idOrdem = idOrdem;
	}

	public Tbclassificacaopatente(long idClassificacao, long idPatenteEc) {
		this.tbclassificacaopatentePK = new TbclassificacaopatentePK(idClassificacao, idPatenteEc);
	}

	public TbclassificacaopatentePK getTbclassificacaopatentePK() {
		return tbclassificacaopatentePK;
	}

	public void setTbclassificacaopatentePK(TbclassificacaopatentePK tbclassificacaopatentePK) {
		this.tbclassificacaopatentePK = tbclassificacaopatentePK;
	}

	public long getIdOrdem() {
		return idOrdem;
	}

	public void setIdOrdem(long idOrdem) {
		this.idOrdem = idOrdem;
	}

	public Tbpatenteec getTbpatenteec() {
		return tbpatenteec;
	}

	public void setTbpatenteec(Tbpatenteec tbpatenteec) {
		this.tbpatenteec = tbpatenteec;
	}

	public Tbclassificacao getTbclassificacao() {
		return tbclassificacao;
	}

	public void setTbclassificacao(Tbclassificacao tbclassificacao) {
		this.tbclassificacao = tbclassificacao;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tbclassificacaopatentePK != null ? tbclassificacaopatentePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Tbclassificacaopatente)) {
			return false;
		}
		Tbclassificacaopatente other = (Tbclassificacaopatente) object;
		if ((this.tbclassificacaopatentePK == null && other.tbclassificacaopatentePK != null)
				|| (this.tbclassificacaopatentePK != null && !this.tbclassificacaopatentePK.equals(other.tbclassificacaopatentePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.gov.inpi.epec.beans.Tbclassificacaopatente[ tbclassificacaopatentePK=" + tbclassificacaopatentePK + " ]";
	}

}
