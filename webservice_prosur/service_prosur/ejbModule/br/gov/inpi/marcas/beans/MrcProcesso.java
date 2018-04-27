/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.marcas.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author tgouvea
 */
@Entity
@Table(name = "mrc_processo")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "MrcProcesso.findAll", query = "SELECT m FROM MrcProcesso m"),
		@NamedQuery(name = "MrcProcesso.findByCdProcess", query = "SELECT m FROM MrcProcesso m WHERE m.cdProcess = :cdProcess"),
		@NamedQuery(name = "MrcProcesso.findByNoProcess", query = "SELECT m FROM MrcProcesso m WHERE m.noProcess = :noProcess"),
		@NamedQuery(name = "MrcProcesso.findByDhPrioridProcess", query = "SELECT m FROM MrcProcesso m WHERE m.dhPrioridProcess = :dhPrioridProcess"),
		@NamedQuery(name = "MrcProcesso.findByNmMarcaProcess", query = "SELECT m FROM MrcProcesso m WHERE m.nmMarcaProcess = :nmMarcaProcess"),
		@NamedQuery(name = "MrcProcesso.findByCdPfpjProcura", query = "SELECT m FROM MrcProcesso m WHERE m.cdPfpjProcura = :cdPfpjProcura"),
		@NamedQuery(name = "MrcProcesso.findByDtDepositProcess", query = "SELECT m FROM MrcProcesso m WHERE m.dtDepositProcess = :dtDepositProcess"),
		@NamedQuery(name = "MrcProcesso.findByDtConcessMarca", query = "SELECT m FROM MrcProcesso m WHERE m.dtConcessMarca = :dtConcessMarca"),
		@NamedQuery(name = "MrcProcesso.findByDtVigenciaMarca", query = "SELECT m FROM MrcProcesso m WHERE m.dtVigenciaMarca = :dtVigenciaMarca"),
		@NamedQuery(name = "MrcProcesso.findByDtCaducidMarca", query = "SELECT m FROM MrcProcesso m WHERE m.dtCaducidMarca = :dtCaducidMarca"),
		@NamedQuery(name = "MrcProcesso.findByCdClassificacao", query = "SELECT m FROM MrcProcesso m WHERE m.cdClassificacao = :cdClassificacao"),
		@NamedQuery(name = "MrcProcesso.findByCdNice", query = "SELECT m FROM MrcProcesso m WHERE m.cdNice = :cdNice"),
		@NamedQuery(name = "MrcProcesso.findByDtAtualizacaoIpas", query = "SELECT m FROM MrcProcesso m WHERE m.dtAtualizacaoIpas = :dtAtualizacaoIpas"),
		@NamedQuery(name = "MrcProcesso.findByTraducaoMarcaProcess", query = "SELECT m FROM MrcProcesso m WHERE m.traducaoMarcaProcess = :traducaoMarcaProcess"),
		@NamedQuery(name = "MrcProcesso.findByDtPublicacao", query = "SELECT m FROM MrcProcesso m WHERE m.dtPublicacao = :dtPublicacao"),
		@NamedQuery(name = "MrcProcesso.findByDtSituacaPosPrazo", query = "SELECT m FROM MrcProcesso m WHERE m.dtSituacaPosPrazo = :dtSituacaPosPrazo") })
public class MrcProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "cd_process")
	private Integer cdProcess;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 9)
	@Column(name = "no_process")
	private String noProcess;

	@Basic(optional = false)
	@NotNull
	@Column(name = "dh_priorid_process")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dhPrioridProcess;

	@Size(max = 240)
	@Column(name = "nm_marca_process")
	private String nmMarcaProcess;

	@Column(name = "cd_pfpj_procura")
	private Integer cdPfpjProcura;

	@Column(name = "dt_deposit_process")
	@Temporal(TemporalType.DATE)
	private Date dtDepositProcess;

	@Column(name = "dt_concess_marca")
	@Temporal(TemporalType.DATE)
	private Date dtConcessMarca;

	@Column(name = "dt_vigencia_marca")
	@Temporal(TemporalType.DATE)
	private Date dtVigenciaMarca;

	@Column(name = "dt_caducid_marca")
	@Temporal(TemporalType.DATE)
	private Date dtCaducidMarca;

	@Size(max = 8)
	@Column(name = "cd_classificacao")
	private String cdClassificacao;

	@Column(name = "cd_nice")
	private Integer cdNice;

	@Column(name = "dt_atualizacao_ipas")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtAtualizacaoIpas;

	@Size(max = 240)
	@Column(name = "traducao_marca_process")
	private String traducaoMarcaProcess;

	@Column(name = "dt_publicacao")
	@Temporal(TemporalType.DATE)
	private Date dtPublicacao;

	@Column(name = "dt_situaca_pos_prazo")
	@Temporal(TemporalType.DATE)
	private Date dtSituacaPosPrazo;

	@Column(name = "cd_situaca_process")
	private String cdSituacaProcess;

	@Column(name = "cd_naturez_marca")
	private String cdNaturezMarca;

	@Column(name = "cd_apresen_marca")
	private String cdApresenMarca;

	@Column(name = "cd_pfpj_titular")
	private Integer cdPfpjTitular;

	public MrcProcesso() {
	}

	public MrcProcesso(Integer cdProcess) {
		this.cdProcess = cdProcess;
	}

	public MrcProcesso(Integer cdProcess, String noProcess,
			Date dhPrioridProcess) {
		this.cdProcess = cdProcess;
		this.noProcess = noProcess;
		this.dhPrioridProcess = dhPrioridProcess;
	}

	public Integer getCdProcess() {
		return cdProcess;
	}

	public void setCdProcess(Integer cdProcess) {
		this.cdProcess = cdProcess;
	}

	public String getNoProcess() {
		return noProcess;
	}

	public void setNoProcess(String noProcess) {
		this.noProcess = noProcess;
	}

	public Date getDhPrioridProcess() {
		return dhPrioridProcess;
	}

	public void setDhPrioridProcess(Date dhPrioridProcess) {
		this.dhPrioridProcess = dhPrioridProcess;
	}

	public String getNmMarcaProcess() {
		return nmMarcaProcess;
	}

	public void setNmMarcaProcess(String nmMarcaProcess) {
		this.nmMarcaProcess = nmMarcaProcess;
	}

	public Integer getCdPfpjProcura() {
		return cdPfpjProcura;
	}

	public void setCdPfpjProcura(Integer cdPfpjProcura) {
		this.cdPfpjProcura = cdPfpjProcura;
	}

	public Date getDtDepositProcess() {
		return dtDepositProcess;
	}

	public void setDtDepositProcess(Date dtDepositProcess) {
		this.dtDepositProcess = dtDepositProcess;
	}

	public Date getDtConcessMarca() {
		return dtConcessMarca;
	}

	public void setDtConcessMarca(Date dtConcessMarca) {
		this.dtConcessMarca = dtConcessMarca;
	}

	public Date getDtVigenciaMarca() {
		return dtVigenciaMarca;
	}

	public void setDtVigenciaMarca(Date dtVigenciaMarca) {
		this.dtVigenciaMarca = dtVigenciaMarca;
	}

	public Date getDtCaducidMarca() {
		return dtCaducidMarca;
	}

	public void setDtCaducidMarca(Date dtCaducidMarca) {
		this.dtCaducidMarca = dtCaducidMarca;
	}

	public String getCdClassificacao() {
		return cdClassificacao;
	}

	public void setCdClassificacao(String cdClassificacao) {
		this.cdClassificacao = cdClassificacao;
	}

	public Integer getCdNice() {
		return cdNice;
	}

	public void setCdNice(Integer cdNice) {
		this.cdNice = cdNice;
	}

	public Date getDtAtualizacaoIpas() {
		return dtAtualizacaoIpas;
	}

	public void setDtAtualizacaoIpas(Date dtAtualizacaoIpas) {
		this.dtAtualizacaoIpas = dtAtualizacaoIpas;
	}

	public String getTraducaoMarcaProcess() {
		return traducaoMarcaProcess;
	}

	public void setTraducaoMarcaProcess(String traducaoMarcaProcess) {
		this.traducaoMarcaProcess = traducaoMarcaProcess;
	}

	public Date getDtPublicacao() {
		return dtPublicacao;
	}

	public void setDtPublicacao(Date dtPublicacao) {
		this.dtPublicacao = dtPublicacao;
	}

	public Date getDtSituacaPosPrazo() {
		return dtSituacaPosPrazo;
	}

	public void setDtSituacaPosPrazo(Date dtSituacaPosPrazo) {
		this.dtSituacaPosPrazo = dtSituacaPosPrazo;
	}
	
	/**
	 * @return the cdSituacaProcess
	 */
	public String getCdSituacaProcess() {
		return cdSituacaProcess;
	}

	/**
	 * @param cdSituacaProcess the cdSituacaProcess to set
	 */
	public void setCdSituacaProcess(String cdSituacaProcess) {
		this.cdSituacaProcess = cdSituacaProcess;
	}

	/**
	 * @return the cdNaturezMarca
	 */
	public String getCdNaturezMarca() {
		return cdNaturezMarca;
	}

	/**
	 * @param cdNaturezMarca the cdNaturezMarca to set
	 */
	public void setCdNaturezMarca(String cdNaturezMarca) {
		this.cdNaturezMarca = cdNaturezMarca;
	}

	/**
	 * @return the cdApresenMarca
	 */
	public String getCdApresenMarca() {
		return cdApresenMarca;
	}

	/**
	 * @param cdApresenMarca the cdApresenMarca to set
	 */
	public void setCdApresenMarca(String cdApresenMarca) {
		this.cdApresenMarca = cdApresenMarca;
	}

	/**
	 * @return the cdPfpjTitular
	 */
	public Integer getCdPfpjTitular() {
		return cdPfpjTitular;
	}

	/**
	 * @param cdPfpjTitular the cdPfpjTitular to set
	 */
	public void setCdPfpjTitular(Integer cdPfpjTitular) {
		this.cdPfpjTitular = cdPfpjTitular;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cdProcess != null ? cdProcess.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof MrcProcesso)) {
			return false;
		}
		MrcProcesso other = (MrcProcesso) object;
		if ((this.cdProcess == null && other.cdProcess != null)
				|| (this.cdProcess != null && !this.cdProcess
						.equals(other.cdProcess))) {
			return false;
		}
		return true;
	}

	

}
