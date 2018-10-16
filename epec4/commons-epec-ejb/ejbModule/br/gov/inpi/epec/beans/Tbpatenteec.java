/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 
 * @author lasilva
 */
@Entity
@Table(name = "TBPATENTEEC")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tbpatenteec.findAll", query = "SELECT t FROM Tbpatenteec t"),
		@NamedQuery(name = "Tbpatenteec.countAll", query = "SELECT COUNT(t) FROM Tbpatenteec t"),
		@NamedQuery(name = "Tbpatenteec.countAllByIdEntidadeEc", query = "SELECT COUNT(t) FROM Tbpatenteec t WHERE t.idEntidadeEc.idEntidadeEc = :idEntidadeEc"),
		@NamedQuery(name = "Tbpatenteec.findByIdPatenteEc", query = "SELECT t FROM Tbpatenteec t WHERE t.idPatenteEc = :idPatenteEc"),
		@NamedQuery(name = "Tbpatenteec.findByStrPubPaisDocdb", query = "SELECT t FROM Tbpatenteec t WHERE t.strPubPaisDocdb = :strPubPaisDocdb"),
		@NamedQuery(name = "Tbpatenteec.findByStrPublicacaoPedidoEpoDoc", query = "SELECT t FROM Tbpatenteec t WHERE t.strPubPaisDocdb = :strPubPaisDocdb AND  t.strPubNumDocdb = :strPubNumDocdb "),
		@NamedQuery(name = "Tbpatenteec.findByStrPublicacaoPedidoEpoDocAndEntidade", query = "SELECT t FROM Tbpatenteec t WHERE t.strPubPaisDocdb = :strPubPaisDocdb AND  t.strPubNumDocdb = :strPubNumDocdb  AND t.idEntidadeEc.txOrganizacao = :txOrganizacao"),
		@NamedQuery(name = "Tbpatenteec.findByStrPublicacaoPedidoDocDb", query = " SELECT t FROM Tbpatenteec t WHERE t.strPubPaisDocdb = :strPubPaisDocdb AND  t.strPubNumDocdb = :strPubNumDocdb  AND  t.strPubKindDocdb = :strPubKindDocdb "),
		@NamedQuery(name = "Tbpatenteec.findByStrPublicacaoPedidoDocDbAndEntidade", query = " SELECT t FROM Tbpatenteec t WHERE t.strPubPaisDocdb = :strPubPaisDocdb AND  t.strPubNumDocdb = :strPubNumDocdb  AND  t.strPubKindDocdb = :strPubKindDocdb  AND t.idEntidadeEc.txOrganizacao = :txOrganizacao"),
		@NamedQuery(name = "Tbpatenteec.findByStrAplicacaoPedidoDocDb", query = " SELECT t FROM Tbpatenteec t WHERE t.strPedPaisDocdb = :strPedPaisDocdb AND  t.strPedNumDocdb = :strPedNumDocdb  AND  t.strPedKindDocdb = :strPedKindDocdb "),
		@NamedQuery(name = "Tbpatenteec.findByStrAplicacaoPedidoDocDbAndEntidade", query = " SELECT t FROM Tbpatenteec t WHERE t.strPedPaisDocdb = :strPedPaisDocdb AND  t.strPedNumDocdb = :strPedNumDocdb  AND  t.strPedKindDocdb = :strPedKindDocdb  AND t.idEntidadeEc.txOrganizacao = :txOrganizacao"),
		@NamedQuery(name = "Tbpatenteec.findByStrPubNumDocdb", query = "SELECT t FROM Tbpatenteec t WHERE t.strPubNumDocdb = :strPubNumDocdb"),
		@NamedQuery(name = "Tbpatenteec.findByStrPubKindDocdb", query = "SELECT t FROM Tbpatenteec t WHERE t.strPubKindDocdb = :strPubKindDocdb"),
		@NamedQuery(name = "Tbpatenteec.findByStrPedPaisDocdb", query = "SELECT t FROM Tbpatenteec t WHERE t.strPedPaisDocdb = :strPedPaisDocdb"),
		@NamedQuery(name = "Tbpatenteec.findByStrPedNumDocdb", query = "SELECT t FROM Tbpatenteec t WHERE t.strPedNumDocdb = :strPedNumDocdb"),
		@NamedQuery(name = "Tbpatenteec.findByStrPedKindDocdb", query = "SELECT t FROM Tbpatenteec t WHERE t.strPedKindDocdb = :strPedKindDocdb"),
		@NamedQuery(name = "Tbpatenteec.findByStrPedNumOriginal", query = "SELECT t FROM Tbpatenteec t WHERE t.strPedNumOriginal = :strPedNumOriginal"),
		@NamedQuery(name = "Tbpatenteec.findByStrPedNumOriginalAndEntidade", query = "SELECT t FROM Tbpatenteec t WHERE t.strPedNumOriginal = :strPedNumOriginal AND t.idEntidadeEc.txOrganizacao = :txOrganizacao"),
		@NamedQuery(name = "Tbpatenteec.findByStrPedNumEpodoc", query = "SELECT t FROM Tbpatenteec t WHERE t.strPedNumEpodoc = :strPedNumEpodoc"),
		@NamedQuery(name = "Tbpatenteec.findByStrPedNumEpodocAndEntidade", query = "SELECT t FROM Tbpatenteec t WHERE t.strPedNumEpodoc = :strPedNumEpodoc AND t.idEntidadeEc.txOrganizacao = :txOrganizacao"),
		@NamedQuery(name = "Tbpatenteec.findByDtDeposito", query = "SELECT t FROM Tbpatenteec t WHERE t.dtDeposito = :dtDeposito"),
		@NamedQuery(name = "Tbpatenteec.findByDtPublicacao", query = "SELECT t FROM Tbpatenteec t WHERE t.dtPublicacao = :dtPublicacao"),
		@NamedQuery(name = "Tbpatenteec.findByBAutomatico", query = "SELECT t FROM Tbpatenteec t WHERE t.automatico = :automatico"),
		@NamedQuery(name = "Tbpatenteec.findByStrReivindicacao", query = "SELECT t FROM Tbpatenteec t WHERE t.strReivindicacao = :strReivindicacao"),
		@NamedQuery(name = "Tbpatenteec.findIdFamilyByStrNumOriginal", query = "SELECT t.idFamiliaEc FROM Tbpatenteec t WHERE t.strPedNumOriginal = :strPedNumOriginal"),
		@NamedQuery(name = "Tbpatenteec.findIdFamilyByStrNumEpodoc", query = "SELECT t.idFamiliaEc FROM Tbpatenteec t WHERE t.strPedNumEpodoc = :strPedNumEpodoc"),
		@NamedQuery(name = "Tbpatenteec.findIdFamilyByStrNumDocDb", query = "SELECT t.idFamiliaEc FROM Tbpatenteec t WHERE t.strPubNumDocdb = :strPubNumDocdb"),
		@NamedQuery(name = "Tbpatenteec.findIdFamilyByPublicacaoEpoDoc", query = "  SELECT t.idFamiliaEc FROM Tbpatenteec t  WHERE t.strPubNumDocdb = :strPubNumDocdb  AND t.strPubPaisDocdb = :strPubPaisDocdb "),
		// @NamedQuery(name = "Tbpatenteec.findIdFamilyByPublicacaoEpoDoc",
		// query =
		// "  SELECT t.idFamiliaEc FROM Tbpatenteec t  WHERE t.strPedNumDocdb = :strPedNumDocdb  AND t.strPedPaisDocdb = :strPedPaisDocdb "),
		@NamedQuery(name = "Tbpatenteec.findByStrAplicacaoFamiliaDocDb", query = "SELECT t.idFamiliaEc FROM Tbpatenteec t WHERE t.strPedPaisDocdb = :strPedPaisDocdb  AND  t.strPedNumDocdb = :strPedNumDocdb  AND  t.strPedKindDocdb = :strPedKindDocdb "),
		@NamedQuery(name = "Tbpatenteec.findByStrPublicacaoFamiliaDocDb", query = " SELECT t.idFamiliaEc FROM Tbpatenteec t WHERE t.strPubPaisDocdb = :strPubPaisDocdb AND  t.strPubNumDocdb = :strPubNumDocdb  AND  t.strPubKindDocdb = :strPubKindDocdb "),
		@NamedQuery(name = "Tbpatenteec.findIdRelatorioTecnicoByNumOriginal", query = "SELECT t FROM Tbpatenteec t WHERE t.strPedNumOriginal = :strPedNumOriginal"),
		@NamedQuery(name = "Tbpatenteec.findIdRelatorioTecnicoByStrNumEpodoc", query = "SELECT t FROM Tbpatenteec t WHERE t.strPedNumEpodoc = :strPedNumEpodoc"),
		@NamedQuery(name = "Tbpatenteec.findIdRelatorioTecnicoByStrNumDocDb", query = "SELECT t FROM Tbpatenteec t WHERE  t.idEntidadeEc.txOrganizacao = :txOrganizacao"),
		@NamedQuery(name = "Tbpatenteec.findEntidadeEC", query = "SELECT t FROM Tbpatenteec t WHERE  t.idEntidadeEc.idPais.idPais = :idPais"),
		@NamedQuery(name = "Tbpatenteec.findByEntidadeEC", query = "SELECT t FROM Tbpatenteec t WHERE  t.idEntidadeEc.idEntidadeEc = :idEntidadeEc") })
public class Tbpatenteec implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PATENTE_EC")
	private Long idPatenteEc;

	@Column(name = "STR_PUB_PAIS_DOCDB")
	private String strPubPaisDocdb;

	@Column(name = "STR_PUB_NUM_DOCDB")
	private String strPubNumDocdb;

	@Column(name = "STR_PUB_KIND_DOCDB")
	private String strPubKindDocdb;

	@Column(name = "STR_PED_PAIS_DOCDB")
	private String strPedPaisDocdb;

	@Column(name = "STR_PED_NUM_DOCDB")
	private String strPedNumDocdb;

	@Column(name = "STR_PED_KIND_DOCDB")
	private String strPedKindDocdb;

	@Column(name = "STR_PED_NUM_ORIGINAL")
	private String strPedNumOriginal;

	@Column(name = "STR_PED_NUM_EPODOC")
	private String strPedNumEpodoc;

	@Lob
	@Column(name = "TX_TITULO")
	private String txTitulo;

	@Column(name = "DT_DEPOSITO")
	@Temporal(TemporalType.DATE)
	private Date dtDeposito;

	@Column(name = "DT_PUBLICACAO")
	@Temporal(TemporalType.DATE)
	private Date dtPublicacao;

	@Column(name = "B_AUTOMATICO")
	private boolean automatico;

	@Lob
	@Column(name = "TX_PROCURADOR")
	private String txProcurador;

	@Column(name = "STR_REIVINDICACAO")
	private String strReivindicacao;

	@Lob
	@Column(name = "TX_RESUMO")
	private String txResumo;

	@JoinTable(name = "TBINVENTORPATENTE", joinColumns = @JoinColumn(name = "ID_PATENTE_EC"), inverseJoinColumns =  @JoinColumn(name = "ID_INVENTOR"))
	@ManyToMany
	@Fetch(FetchMode.SELECT)
	private List<Tbinventor> tbinventorList;

	@JoinTable(name = "TBRELATORIOPATENTE", joinColumns = { @JoinColumn(name = "ID_PATENTE_EC", referencedColumnName = "ID_PATENTE_EC") }, inverseJoinColumns = { @JoinColumn(name = "ID_RELATORIO_EC", referencedColumnName = "ID_RELATORIO_EC") })
	@ManyToMany
	private List<Tbrelatorioec> tbrelatorioecList;

	@JoinTable(name = "TBDEPOSITANTEPATENTE", joinColumns = { @JoinColumn(name = "ID_PATENTE_EC", referencedColumnName = "ID_PATENTE_EC") }, inverseJoinColumns = { @JoinColumn(name = "ID_DEPOSITANTE", referencedColumnName = "ID_DEPOSITANTE") })
	@ManyToMany
	@Fetch(FetchMode.SELECT)
	private List<Tbdepositante> tbdepositanteList;

	@JoinTable(name = "TBCLASSIFICACAOPATENTE", joinColumns = { @JoinColumn(name = "ID_PATENTE_EC", referencedColumnName = "ID_PATENTE_EC") }, inverseJoinColumns = { @JoinColumn(name = "ID_CLASSIFICACAO", referencedColumnName = "ID_CLASSIFICACAO") })
	@ManyToMany
	@Fetch(FetchMode.SELECT)
	private List<Tbclassificacaopatente> tbclassificacaopatenteList;

	@JoinColumn(name = "ID_FAMILIA_EC", referencedColumnName = "ID_FAMILIA_EC")
	@ManyToOne
	private Tbfamiliaec idFamiliaEc;

	@JoinColumn(name = "ID_ENTIDADE_EC", referencedColumnName = "ID_ENTIDADE_EC")
	@ManyToOne
	private Tbcadentidade idEntidadeEc;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatenteEc", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<Tbprioridadeec> tbprioridadeecList;

	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
	@ManyToOne
	private Tbcadusuario idUsuario;

	@Transient
	private List<String> inventor;

	@Transient
	private List<String> depositante;

	@Transient
	private List<String> classificacao;

	@Transient
	private String numeroTitulo;

	@Transient
	private String emColaboracao;

	@Transient
	private String patenteManual;

	@Transient
	private String patenteManual2;

	public String getPatenteManual() {
		return patenteManual;
	}

	public void setPatenteManual(String patenteManual) {
		this.patenteManual = patenteManual;
	}

	public String getEmColaboracao() {
		return emColaboracao;
	}

	public void setEmColaboracao(String emColaboracao) {
		this.emColaboracao = emColaboracao;
	}

	public String getNumeroTitulo() {
		return strPubPaisDocdb + strPubNumDocdb + " - " + txTitulo;
	}

	public void setNumeroTitulo(String numeroTitulo) {
		this.numeroTitulo = numeroTitulo;
	}

	public List<String> getInventor() {
		return inventor;
	}

	public void setInventor(List<String> inventor) {
		this.inventor = inventor;
	}

	public List<String> getDepositante() {
		return depositante;
	}

	public void setDepositante(List<String> depositante) {
		this.depositante = depositante;
	}

	public List<String> getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(List<String> classificacao) {
		this.classificacao = classificacao;
	}

	public Tbpatenteec() {
	}

	public Tbpatenteec(Long idPatenteEc) {
		this.idPatenteEc = idPatenteEc;
	}

	public Tbpatenteec(Long idPatenteEc, String strPubPaisDocdb, String strPubNumDocdb, String strPubKindDocdb, String strPedPaisDocdb, String strPedNumDocdb, String strPedKindDocdb,
			String strPedNumOriginal, String strPedNumEpodoc, boolean bAutomatico, String txResumo) {
		this.idPatenteEc = idPatenteEc;
		this.strPubPaisDocdb = strPubPaisDocdb;
		this.strPubNumDocdb = strPubNumDocdb;
		this.strPubKindDocdb = strPubKindDocdb;
		this.strPedPaisDocdb = strPedPaisDocdb;
		this.strPedNumDocdb = strPedNumDocdb;
		this.strPedKindDocdb = strPedKindDocdb;
		this.strPedNumOriginal = strPedNumOriginal;
		this.strPedNumEpodoc = strPedNumEpodoc;
		this.automatico = automatico;
		this.txResumo = txResumo;
	}

	public Long getIdPatenteEc() {
		return idPatenteEc;
	}

	public void setIdPatenteEc(Long idPatenteEc) {
		this.idPatenteEc = idPatenteEc;
	}

	public String getStrPubPaisDocdb() {
		return strPubPaisDocdb;
	}

	public void setStrPubPaisDocdb(String strPubPaisDocdb) {
		this.strPubPaisDocdb = strPubPaisDocdb;
	}

	public String getStrPubNumDocdb() {
		return strPubNumDocdb;
	}

	public Tbcadusuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Tbcadusuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setStrPubNumDocdb(String strPubNumDocdb) {
		this.strPubNumDocdb = strPubNumDocdb;
	}

	public String getStrPubKindDocdb() {
		return strPubKindDocdb;
	}

	public void setStrPubKindDocdb(String strPubKindDocdb) {
		this.strPubKindDocdb = strPubKindDocdb;
	}

	public String getStrPedPaisDocdb() {
		return strPedPaisDocdb;
	}

	public void setStrPedPaisDocdb(String strPedPaisDocdb) {
		this.strPedPaisDocdb = strPedPaisDocdb;
	}

	public String getStrPedNumDocdb() {
		return strPedNumDocdb;
	}

	public void setStrPedNumDocdb(String strPedNumDocdb) {
		this.strPedNumDocdb = strPedNumDocdb;
	}

	public String getStrPedKindDocdb() {
		return strPedKindDocdb;
	}

	public void setStrPedKindDocdb(String strPedKindDocdb) {
		this.strPedKindDocdb = strPedKindDocdb;
	}

	public String getStrPedNumOriginal() {
		return strPedNumOriginal;
	}

	public void setStrPedNumOriginal(String strPedNumOriginal) {
		this.strPedNumOriginal = strPedNumOriginal;
	}

	public String getStrPedNumEpodoc() {
		return strPedNumEpodoc;
	}

	public void setStrPedNumEpodoc(String strPedNumEpodoc) {
		this.strPedNumEpodoc = strPedNumEpodoc;
	}

	public String getTxTitulo() {
		return txTitulo;
	}

	public void setTxTitulo(String txTitulo) {
		this.txTitulo = txTitulo;
	}

	public Date getDtDeposito() {
		return dtDeposito;
	}

	public void setDtDeposito(Date dtDeposito) {
		this.dtDeposito = dtDeposito;
	}

	public Date getDtPublicacao() {
		return dtPublicacao;
	}

	public void setDtPublicacao(Date dtPublicacao) {
		this.dtPublicacao = dtPublicacao;
	}

	public boolean getAutomatico() {
		return automatico;
	}

	public void setAutomatico(boolean automatico) {
		this.automatico = automatico;
	}

	public String getTxProcurador() {
		return txProcurador;
	}

	public void setTxProcurador(String txProcurador) {
		this.txProcurador = txProcurador;
	}

	public String getStrReivindicacao() {
		return strReivindicacao;
	}

	public void setStrReivindicacao(String strReivindicacao) {
		this.strReivindicacao = strReivindicacao;
	}

	public String getTxResumo() {
		return txResumo;
	}

	public void setTxResumo(String txResumo) {
		this.txResumo = txResumo;
	}

	@XmlTransient
	public List<Tbinventor> getTbinventorList() {
		return tbinventorList;
	}

	public void setTbinventorList(List<Tbinventor> tbinventorList) {
		this.tbinventorList = tbinventorList;
	}

	@XmlTransient
	public List<Tbrelatorioec> getTbrelatorioecList() {
		return tbrelatorioecList;
	}

	public void setTbrelatorioecList(List<Tbrelatorioec> tbrelatorioecList) {
		this.tbrelatorioecList = tbrelatorioecList;
	}

	@XmlTransient
	public List<Tbdepositante> getTbdepositanteList() {
		return tbdepositanteList;
	}

	public void setTbdepositanteList(List<Tbdepositante> tbdepositanteList) {
		this.tbdepositanteList = tbdepositanteList;
	}

	@XmlTransient
	public List<Tbclassificacaopatente> getTbclassificacaopatenteList() {
		return tbclassificacaopatenteList;
	}

	public void setTbclassificacaopatenteList(List<Tbclassificacaopatente> tbclassificacaopatenteList) {
		this.tbclassificacaopatenteList = tbclassificacaopatenteList;
	}

	public Tbfamiliaec getIdFamiliaEc() {
		return idFamiliaEc;
	}

	public void setIdFamiliaEc(Tbfamiliaec idFamiliaEc) {
		this.idFamiliaEc = idFamiliaEc;
	}

	public Tbcadentidade getIdEntidadeEc() {
		return idEntidadeEc;
	}

	public void setIdEntidadeEc(Tbcadentidade idEntidadeEc) {
		this.idEntidadeEc = idEntidadeEc;
	}

	@XmlTransient
	public List<Tbprioridadeec> getTbprioridadeecList() {
		return tbprioridadeecList;
	}

	public void setTbprioridadeecList(List<Tbprioridadeec> tbprioridadeecList) {
		this.tbprioridadeecList = tbprioridadeecList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idPatenteEc != null ? idPatenteEc.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Tbpatenteec)) {
			return false;
		}
		Tbpatenteec other = (Tbpatenteec) object;
		if ((this.idPatenteEc == null && other.idPatenteEc != null) || (this.idPatenteEc != null && !this.idPatenteEc.equals(other.idPatenteEc))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.gov.inpi.epec.beans.Tbpatenteec[ idPatenteEc=" + idPatenteEc + " ]";

	}

	@Transient
	private String numeroPedido;

	@Transient
	private String numeroPedidoDOCDB;

	@Transient
	private String numeroPublicacaoDOCDB;

	@Transient
	private String numeroPublicacaEPODOC;

	@Transient
	private String relatorio;

	@Transient
	private String insercao;

	@Transient
	private String images;

	@Transient
	private String imagesRelatorio;

	@Transient
	private String html;

	public String getImagesRelatorio() {
		if (tbrelatorioec != null) {
			return "images/_bandeirasP/" + strPubPaisDocdb + ".png";
		} else {
			return "";
		}

	}

	public void setImagesRelatorio(String imagesRelatorio) {
		this.imagesRelatorio = imagesRelatorio;
	}

	@Transient
	private Tbrelatorioec tbrelatorioec;

	public Tbrelatorioec getTbrelatorioec() {
		return tbrelatorioec;
	}

	public void setTbrelatorioec(Tbrelatorioec tbrelatorioec) {
		this.tbrelatorioec = tbrelatorioec;
	}

	public String getNumeroPedido() {
		return strPubPaisDocdb + strPubNumDocdb;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getRelatorio() {
		if (tbrelatorioec != null) {
			return strPubPaisDocdb + strPubNumDocdb;
		} else {
			return "";
		}

	}

	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}

	public String getNumeroPedidoDOCDB() {
		return strPedPaisDocdb + strPedNumDocdb + strPedKindDocdb;
	}

	public void setNumeroPedidoDOCDB(String numeroPedidoDOCDB) {
		this.numeroPedidoDOCDB = numeroPedidoDOCDB;
	}

	public String getNumeroPublicacaoDOCDB() {
		return strPubPaisDocdb + strPubNumDocdb + strPubKindDocdb;
	}

	public void setNumeroPublicacaoDOCDB(String numeroPublicacaoDOCDB) {
		this.numeroPublicacaoDOCDB = numeroPublicacaoDOCDB;
	}

	public String getNumeroPublicacaEPODOC() {
		return strPubPaisDocdb + strPubNumDocdb;
	}

	public void setNumeroPublicacaEPODOC(String numeroPublicacaEPODOC) {
		this.numeroPublicacaEPODOC = numeroPublicacaEPODOC;
	}

	public String getInsercao() {

		if (automatico) {
			return "Automático";
		} else {
			return "Manual";

		}
	}

	public void setInsercao(String insercao) {
		this.insercao = insercao;
	}

	public String getImages() {

		return "images/_bandeirasP/" + strPubPaisDocdb + ".png";
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getHtml() {
		if (tbrelatorioec != null) {
			return "HTML";
		} else {
			return "";
		}
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getPatenteManual2() {
		return patenteManual2;
	}

	public void setPatenteManual2(String patenteManual2) {
		this.patenteManual2 = patenteManual2;
	}

}