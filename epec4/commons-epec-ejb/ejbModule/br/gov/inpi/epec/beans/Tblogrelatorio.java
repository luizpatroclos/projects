package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;

@Entity
@Table(name = "TBLOGRELATORIO")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tblogrelatorio.countAll", query = "SELECT COUNT(t) FROM Tblogrelatorio t"),
@NamedQuery(name = "Tblogrelatorio.findByIdLogRelatorio", query = "SELECT t FROM Tblogrelatorio t WHERE t.idRelatorio = :idRelatorio order BY t.datahora DESC") })
public class Tblogrelatorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LOGRELATORIO")
	private long idLog;


	@Column(name = "ID_RELATORIO_EC")
	private long idRelatorio;


	@Column(name = "STR_USUARIO")
	private String strUsuario;


	@Column(name = "D_DATAHORA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datahora;


	@Column(name = "STR_ACAO")
	private String strAcao;


	@Column(name = "STR_ATIVO")
	private String strAtivo;


	@Lob
	@Column(name = "TX_ATIVO")
	private String txAtivo;

	
	public long getIdLog() {
		return idLog;
	}

	public void setIdLog(long idLog) {
		this.idLog = idLog;
	}

	public long getIdRelatorio() {
		return idRelatorio;
	}

	public void setIdRelatorio(long idRelatorio) {
		this.idRelatorio = idRelatorio;
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

	@Transient
    private Tbcadusuario usuario;
    
	
	  
    public Tbcadusuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Tbcadusuario usuario) {
		this.usuario = usuario;
	}



}
