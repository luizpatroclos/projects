/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBCADUSUARIO")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbcadusuario.findAll", query = "SELECT t FROM Tbcadusuario t"), @NamedQuery(name = "Tbcadusuario.countAll", query = "SELECT COUNT(t) from Tbcadusuario t"),
		@NamedQuery(name = "Tbcadusuario.findByIdUsuario", query = "SELECT t FROM Tbcadusuario t WHERE t.idUsuario = :idUsuario"),
		@NamedQuery(name = "Tbcadusuario.findByStrUsuario", query = "SELECT t FROM Tbcadusuario t WHERE t.strUsuario = :strUsuario"),
		@NamedQuery(name = "Tbcadusuario.findByStrSenha", query = "SELECT t FROM Tbcadusuario t WHERE t.strSenha = :strSenha"),
		@NamedQuery(name = "Tbcadusuario.findByStrEmail", query = "SELECT t FROM Tbcadusuario t WHERE t.strEmail = :strEmail"),
		@NamedQuery(name = "Tbcadusuario.findByStrTelefone", query = "SELECT t FROM Tbcadusuario t WHERE t.strTelefone = :strTelefone"),
		@NamedQuery(name = "Tbcadusuario.findByBPublico", query = "SELECT t FROM Tbcadusuario t WHERE t.publico = :publico"),
		@NamedQuery(name = "Tbcadusuario.findByBLogado", query = "SELECT t FROM Tbcadusuario t WHERE t.bLogado = :bLogado"),
		@NamedQuery(name = "Tbcadusuario.findByIdEntidade", query = "SELECT t FROM Tbcadusuario t WHERE t.idEntidadeEc.idEntidadeEc = :idEntidadeEc"),
		@NamedQuery(name = "Tbcadusuario.countAllByIdEntidadeEc", query = "SELECT COUNT(t) FROM Tbcadusuario t WHERE t.idEntidadeEc.idEntidadeEc = :idEntidadeEc"),
		@NamedQuery(name = "Tbcadusuario.findByIdAtivo", query = "SELECT t FROM Tbcadusuario t WHERE t.idAtivo = :idAtivo"),
		@NamedQuery(name = "Tbcadusuario.findByIdPais", query = "SELECT t FROM Tbcadusuario t WHERE t.idEntidadeEc.idPais.idPais = :idPais")})
public class Tbcadusuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USUARIO")
	private Long idUsuario;

	@Column(name = "STR_USUARIO", length = 45)
	private String strUsuario;

	@Column(name = "STR_SENHA", length = 100)
	private String strSenha;

	@Column(name = "TX_NOME", length = 65535)
	private String txNome;

	@Column(name = "TX_DESIGNACAO", length = 65535)
	private String txDesignacao;

	@Column(name = "TX_ORGANIZACAO", length = 65535)
	private String txOrganizacao;

	@Column(name = "STR_EMAIL", length = 100)
	private String strEmail;

	@Column(name = "STR_TELEFONE", length = 30)
	private String strTelefone;

	@Column(name = "B_PUBLICO", nullable = false)
	private boolean publico;

	@Column(name = "B_LOGADO", nullable = false)
	private boolean bLogado;

	@Column(name = "ID_ATIVO", nullable = false)
	private short idAtivo;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private List<Tbarquivofamilia> tbarquivofamiliaList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private List<Tbcomentariosfamiliaec> tbcomentariosfamiliaecList;

	@JoinColumn(name = "ID_PERFILUSUARIO", referencedColumnName = "ID_PERFILUSUARIO", nullable = false)
	@ManyToOne
	private Tbperfilusuario idPerfilusuario;
	
	@OneToMany(mappedBy = "idUsuario")
    private List<Tbpatenteec> tbpatenteecList;

	@JoinColumn(name = "ID_ENTIDADE_EC", referencedColumnName = "ID_ENTIDADE_EC", nullable = false)
	@ManyToOne
	private Tbcadentidade idEntidadeEc;

	public Tbcadusuario() {
	}

	public Tbcadusuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Tbcadusuario(Long idUsuario, String strUsuario, String strSenha, String txNome, String txDesignacao, String txOrganizacao, String strEmail, boolean publico, boolean bLogado, short idAtivo) {
		this.idUsuario = idUsuario;
		this.strUsuario = strUsuario;
		this.strSenha = strSenha;
		this.txNome = txNome;
		this.txDesignacao = txDesignacao;
		this.txOrganizacao = txOrganizacao;
		this.strEmail = strEmail;
		this.publico = publico;
		this.bLogado = bLogado;
		this.idAtivo = idAtivo;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getStrUsuario() {
		return strUsuario;
	}

	public void setStrUsuario(String strUsuario) {
		this.strUsuario = strUsuario;
	}

	public String getStrSenha() {
		return strSenha;
	}

	public void setStrSenha(String strSenha) {
		this.strSenha = strSenha;
	}

	public String getTxNome() {
		return txNome;
	}

	public void setTxNome(String txNome) {
		this.txNome = txNome;
	}

	public String getTxDesignacao() {
		return txDesignacao;
	}

	public void setTxDesignacao(String txDesignacao) {
		this.txDesignacao = txDesignacao;
	}

	public String getTxOrganizacao() {
		return txOrganizacao;
	}

	public void setTxOrganizacao(String txOrganizacao) {
		this.txOrganizacao = txOrganizacao;
	}

	public String getStrEmail() {
		return strEmail;
	}

	public void setStrEmail(String strEmail) {
		this.strEmail = strEmail;
	}

	public String getStrTelefone() {
		return strTelefone;
	}

	public void setStrTelefone(String strTelefone) {
		this.strTelefone = strTelefone;
	}

	public boolean getPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}

	public boolean getBLogado() {
		return bLogado;
	}

	public void setBLogado(boolean bLogado) {
		this.bLogado = bLogado;
	}

	public short getIdAtivo() {
		return idAtivo;
	}

	public void setIdAtivo(short idAtivo) {
		this.idAtivo = idAtivo;
	}

	@XmlTransient
	public List<Tbarquivofamilia> getTbarquivofamiliaList() {
		return tbarquivofamiliaList;
	}

	public void setTbarquivofamiliaList(List<Tbarquivofamilia> tbarquivofamiliaList) {
		this.tbarquivofamiliaList = tbarquivofamiliaList;
	}

	@XmlTransient
	public List<Tbcomentariosfamiliaec> getTbcomentariosfamiliaecList() {
		return tbcomentariosfamiliaecList;
	}

	public void setTbcomentariosfamiliaecList(List<Tbcomentariosfamiliaec> tbcomentariosfamiliaecList) {
		this.tbcomentariosfamiliaecList = tbcomentariosfamiliaecList;
	}
	
	 @XmlTransient
	    public List<Tbpatenteec> getTbpatenteecList() {
	        return tbpatenteecList;
	    }

	    public void setTbpatenteecList(List<Tbpatenteec> tbpatenteecList) {
	        this.tbpatenteecList = tbpatenteecList;
	    }

	public Tbperfilusuario getIdPerfilusuario() {
		return idPerfilusuario;
	}

	public void setIdPerfilusuario(Tbperfilusuario idPerfilusuario) {
		this.idPerfilusuario = idPerfilusuario;
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
		hash += (idUsuario != null ? idUsuario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Tbcadusuario)) {
			return false;
		}
		Tbcadusuario other = (Tbcadusuario) object;
		if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.gov.inpi.epec.beans.Tbcadusuario[ idUsuario=" + idUsuario + " ]";
	}

	@Transient
	private String imagem;

	@Transient
	private String imagemLogado;

	public String getImagemLogado() {

		if (isbLogado()) {
			return "images/online.png";
		} else {
			return "images/offline.png";
		}

	}
	
	@Transient
	private String imagemAtivo;

	public String getImagemAtivo() {

		if (getIdAtivo() == 1) {
			return "images/online.png";
		} else {
			return "images/offline.png";
		}

	}
	

	public void setImagemLogado(String imagemLogado) {
		this.imagemLogado = imagemLogado;
	}

	public boolean isbLogado() {
		return bLogado;
	}

	public void setbLogado(boolean bLogado) {
		this.bLogado = bLogado;
	}

	public String getImagem() {
		return "images/_bandeirasP/" + idEntidadeEc.getIdPais().getStrCodPais() + ".png";
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
}
