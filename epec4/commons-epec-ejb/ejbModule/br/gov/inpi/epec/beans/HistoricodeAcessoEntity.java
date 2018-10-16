package br.gov.inpi.epec.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class HistoricodeAcessoEntity {

	@Id
	@Column(name = "STR_USUARIO")
	private String strUsuario;

	@Column(name = "STR_NOME_PAIS")
	private String strNomePais;

	@Column(name = "TX_ORGANIZACAO")
	private String txOrganizacao;

	@Column(name = "B_LOGADO")
	private Boolean bLogado;

	@Column(name = "DT_REGISTRO")
	private Date dtRegistro;

	@Column(name = "STR_IP")
	private String strIp;

	@Transient
	private String imagemLogado;
	
	@Transient
	private Tbcadusuario tbcadusuario;

	

	public String getImagemLogado() {

		if (isbLogado()) {
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

	public String getStrUsuario() {
		return strUsuario;
	}

	public void setStrUsuario(String strUsuario) {
		this.strUsuario = strUsuario;
	}

	public String getStrNomePais() {
		return strNomePais;
	}

	public void setStrNomePais(String strNomePais) {
		this.strNomePais = strNomePais;
	}

	public String getTxOrganizacao() {
		return txOrganizacao;
	}

	public void setTxOrganizacao(String txOrganizacao) {
		this.txOrganizacao = txOrganizacao;
	}

	public Boolean getbLogado() {
		return bLogado;
	}

	public void setbLogado(Boolean bLogado) {
		this.bLogado = bLogado;
	}

	public Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public String getStrIp() {
		return strIp;
	}

	public void setStrIp(String strIp) {
		this.strIp = strIp;
	}
	
	public Tbcadusuario getTbcadusuario() {
		return tbcadusuario;
	}

	public void setTbcadusuario(Tbcadusuario tbcadusuario) {
		this.tbcadusuario = tbcadusuario;
	}

	@Override
	public String toString() {
		return "HistoricodeAcessoEntity [strUsuario=" + strUsuario + ", strNomePais=" + strNomePais + ", txOrganizacao=" + txOrganizacao + ", bLogado=" + bLogado + ", dtRegistro=" + dtRegistro
				+ ", strIp=" + strIp + ", imagemLogado=" + imagemLogado + "]";
	}
	
	


}