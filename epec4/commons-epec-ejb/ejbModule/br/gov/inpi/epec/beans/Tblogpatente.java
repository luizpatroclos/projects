package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TBLOGPATENTE")
@XmlRootElement
@NamedQueries({ @javax.persistence.NamedQuery(name = "Tblogpatente.countAll", query = "SELECT COUNT(t) FROM Tblogpatente t"),
	@NamedQuery(name = "Tblogpatente.findByIdPatenteEc", query = "SELECT t FROM Tblogpatente t WHERE t.idPatenteEc = :idPatenteEc order BY t.dDatahora DESC")})
public class Tblogpatente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_LOGPATENTE")
	private long idLog;
	
	@Basic(optional = false)
	@Column(name = "ID_PATENTE_EC")
	private Long idPatenteEc;

	@Basic(optional = false)
	@Column(name = "STR_USUARIO")
	private String strUsuario;
	
	@Basic(optional = false)
	@Column(name = "D_DATAHORA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dDatahora;

	@Basic(optional = false)
	@Column(name = "STR_ACAO")
	private String strAcao;

	@Basic(optional = false)
	@Column(name = "STR_ATIVO")
	private String strAtivo;

	@Basic(optional = false)
	@Lob
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

	public Tblogpatente() {
	}

	public Tblogpatente(long idLog) {
		this.idLog = idLog;
	}

	public String getStrUsuario() {
		return strUsuario;
	}

	public long getIdLog() {
		return idLog;
	}

	public void setIdLog(long idLog) {
		this.idLog = idLog;
	}

	public Long getIdPatenteEc() {
		return idPatenteEc;
	}

	public void setIdPatenteEc(Long idPatenteEc) {
		this.idPatenteEc = idPatenteEc;
	}

	public Date getdDatahora() {
		return dDatahora;
	}

	public void setdDatahora(Date dDatahora) {
		this.dDatahora = dDatahora;
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

	public void setStrUsuario(String strUsuario) {
		this.strUsuario = strUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dDatahora == null) ? 0 : dDatahora.hashCode());
		result = prime * result + (int) (idLog ^ (idLog >>> 32));
		result = prime * result + ((idPatenteEc == null) ? 0 : idPatenteEc.hashCode());
		result = prime * result + ((strAcao == null) ? 0 : strAcao.hashCode());
		result = prime * result + ((strAtivo == null) ? 0 : strAtivo.hashCode());
		result = prime * result + ((strUsuario == null) ? 0 : strUsuario.hashCode());
		result = prime * result + ((txAtivo == null) ? 0 : txAtivo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tblogpatente other = (Tblogpatente) obj;
		if (dDatahora == null) {
			if (other.dDatahora != null)
				return false;
		} else if (!dDatahora.equals(other.dDatahora))
			return false;
		if (idLog != other.idLog)
			return false;
		if (idPatenteEc == null) {
			if (other.idPatenteEc != null)
				return false;
		} else if (!idPatenteEc.equals(other.idPatenteEc))
			return false;
		if (strAcao == null) {
			if (other.strAcao != null)
				return false;
		} else if (!strAcao.equals(other.strAcao))
			return false;
		if (strAtivo == null) {
			if (other.strAtivo != null)
				return false;
		} else if (!strAtivo.equals(other.strAtivo))
			return false;
		if (strUsuario == null) {
			if (other.strUsuario != null)
				return false;
		} else if (!strUsuario.equals(other.strUsuario))
			return false;
		if (txAtivo == null) {
			if (other.txAtivo != null)
				return false;
		} else if (!txAtivo.equals(other.txAtivo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tblogpatente [idLog=" + idLog + ", idPatenteEc=" + idPatenteEc + ", strUsuario=" + strUsuario + ", dDatahora=" + dDatahora + ", strAcao=" + strAcao + ", strAtivo=" + strAtivo
				+ ", txAtivo=" + txAtivo + "]";
	}

	

}
