package br.gov.inpi.epec.entities;

import java.util.Date;
import java.util.List;

public class Patente {

	private long idDocumentoPatente;

	private String tipoDocumento;

	private String numero;

	private String titulo;

	private Pessoa procurador;

	private Date dataDeposito;

	private String pais;

	private String kindCode;

	private Date dataPublicacao;

	private String paisPublicacao;

	
	/**
	 * Numero de publicacao DOCDB 
	 */
	private String numeroPublicacao;

	/**
	 * número publicação EPODOC
	 */
	private String numeroPublicacaoEpodoc;

	private String numeroAplicacao;

	private String kindCodePublicacao;

	private List<Prioridade> prioridades;

	private List<ClassificacaoInternacional> classificacoes;

	private List<Pessoa> inventores;

	private List<Pessoa> depositantes;

	private String revindicacao;

	private String inventor;

	private String depositante;

	private String classificacao;

	private String numeroPedidoEpoDoc;

	private String familiaId;

	private String numeroPedidoOriginal;

	private String resumo;

	private List<Citacao> citacoes;

	private String paisPrioridade;

	private String numeroAplicacaoPrioridade;

	private Date dataDepositoPrioridade;

	private String imagem;

	private String imagemPdf;

	private String urlPedido;

	private String urlArquivo;

	public String getImagem() {
		return "images/_bandeirasP/" + paisPublicacao + ".png";
	}

	public String getImagemPdf() {
		return "imagens/botoes/pdf.png";
	}

	public String getPaisPrioridade() {
		return paisPrioridade;
	}

	public void setPaisPrioridade(String paisPrioridade) {
		this.paisPrioridade = paisPrioridade;
	}

	public String getNumeroAplicacaoPrioridade() {
		return numeroAplicacaoPrioridade;
	}

	public void setNumeroAplicacaoPrioridade(String numeroAplicacaoPrioridade) {
		this.numeroAplicacaoPrioridade = numeroAplicacaoPrioridade;
	}

	public Date getDataDepositoPrioridade() {
		return dataDepositoPrioridade;
	}

	public void setDataDepositoPrioridade(Date dataDepositoPrioridade) {
		this.dataDepositoPrioridade = dataDepositoPrioridade;
	}

	public long getIdDocumentoPatente() {
		return idDocumentoPatente;
	}

	public void setIdDocumentoPatente(long idDocumentoPatente) {
		this.idDocumentoPatente = idDocumentoPatente;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getInventor() {
		return inventor;
	}

	public void setInventor(String inventor) {
		this.inventor = inventor;
	}

	public String getDepositante() {
		return depositante;
	}

	public void setDepositante(String depositante) {
		this.depositante = depositante;
	}

	public String getNumeroAplicacao() {
		return numeroAplicacao;
	}

	public void setNumeroAplicacao(String numeroAplicacao) {
		this.numeroAplicacao = numeroAplicacao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Pessoa getProcurador() {
		return procurador;
	}

	public void setProcurador(Pessoa procurador) {
		this.procurador = procurador;
	}

	public Date getDataDeposito() {
		return dataDeposito;
	}

	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getKindCode() {
		return kindCode;
	}

	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public String getPaisPublicacao() {
		return paisPublicacao;
	}

	public void setPaisPublicacao(String paisPublicacao) {
		this.paisPublicacao = paisPublicacao;
	}

	public String getNumeroPublicacao() {
		return numeroPublicacao;
	}

	public void setNumeroPublicacao(String numeroPublicacao) {
		this.numeroPublicacao = numeroPublicacao;
	}

	public String getNumeroPublicacaoEpodoc() {
		return numeroPublicacaoEpodoc;
	}

	public void setNumeroPublicacaoEpodoc(String numeroPublicacaoEpodoc) {
		this.numeroPublicacaoEpodoc = numeroPublicacaoEpodoc;
	}

	public String getKindCodePublicacao() {
		return kindCodePublicacao;
	}

	public void setKindCodePublicacao(String kindCodePublicacao) {
		this.kindCodePublicacao = kindCodePublicacao;
	}

	public List<Prioridade> getPrioridades() {
		return prioridades;
	}

	public void setPrioridades(List<Prioridade> prioridades) {
		this.prioridades = prioridades;
	}

	public List<ClassificacaoInternacional> getClassificacoes() {
		return classificacoes;
	}

	public void setClassificacoes(List<ClassificacaoInternacional> classificacoes) {
		this.classificacoes = classificacoes;
	}

	public List<Pessoa> getInventores() {
		return inventores;
	}

	public void setInventores(List<Pessoa> inventores) {
		this.inventores = inventores;
	}

	public List<Pessoa> getDepositantes() {
		return depositantes;
	}

	public void setDepositantes(List<Pessoa> depositantes) {
		this.depositantes = depositantes;
	}

	public String getRevindicacao() {
		return revindicacao;
	}

	public void setRevindicacao(String revindicacao) {
		this.revindicacao = revindicacao;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroPedidoEpoDoc() {
		return numeroPedidoEpoDoc;
	}

	public void setNumeroPedidoEpoDoc(String numeroPedidoEpoDoc) {
		this.numeroPedidoEpoDoc = numeroPedidoEpoDoc;
	}

	public String getFamiliaId() {
		return familiaId;
	}

	public void setFamiliaId(String familiaId) {
		this.familiaId = familiaId;
	}

	public String getNumeroPedidoOriginal() {
		return numeroPedidoOriginal;
	}

	public void setNumeroPedidoOriginal(String numeroPedidoOriginal) {
		this.numeroPedidoOriginal = numeroPedidoOriginal;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public List<Citacao> getCitacoes() {
		return citacoes;
	}

	public void setCitacoes(List<Citacao> citacoes) {
		this.citacoes = citacoes;

	}

	public String UrlPedido() {
		return "http://worldwide.espacenet.com/publicationDetails/biblio?CC=" + this.paisPublicacao + "&NR=" + this.numeroPublicacao+"&KC=A";
	}

	public String UrlArquivo() {
		return "http://worldwide.espacenet.com/espacenetDocument.pdf?CC=" + this.paisPublicacao + "&FT=D&NR=" + this.numeroPublicacao
				+ this.kindCodePublicacao + "&locale=en_EP&flavour=plainPage&pageNumber=1";

	}
	
	public String UrlINPADOC() {
		return "http://worldwide.espacenet.com/publicationDetails/inpadocPatentFamily?CC="+ this.paisPublicacao +"&NR="+ this.numeroPublicacao + this.kindCodePublicacao +"&FT=D&ND=&DB=&&locale=en_EP";

	}

	@Override
	public String toString() {
		return "Patente [idDocumentoPatente=" + idDocumentoPatente + ", tipoDocumento=" + tipoDocumento + ", numero=" + numero + ", titulo=" + titulo
				+ ", procurador=" + procurador + ", dataDeposito=" + dataDeposito + ", pais=" + pais + ", kindCode=" + kindCode + ", dataPublicacao="
				+ dataPublicacao + ", paisPublicacao=" + paisPublicacao + ", numeroPublicacao=" + numeroPublicacao + ", numeroPublicacaoEpodoc="
				+ numeroPublicacaoEpodoc + ", numeroAplicacao=" + numeroAplicacao + ", kindCodePublicacao=" + kindCodePublicacao + ", prioridades="
				+ prioridades + ", classificacoes=" + classificacoes + ", inventores=" + inventores + ", depositantes=" + depositantes
				+ ", revindicacao=" + revindicacao + ", inventor=" + inventor + ", depositante=" + depositante + ", classificacao=" + classificacao
				+ ", numeroPedidoEpoDoc=" + numeroPedidoEpoDoc + ", familiaId=" + familiaId + ", numeroPedidoOriginal=" + numeroPedidoOriginal
				+ ", resumo=" + resumo + ", citacoes=" + citacoes + ", paisPrioridade=" + paisPrioridade + ", numeroAplicacaoPrioridade="
				+ numeroAplicacaoPrioridade + ", dataDepositoPrioridade=" + dataDepositoPrioridade + ", imagem=" + imagem + ", imagemPdf="
				+ imagemPdf + ", urlPedido=" + urlPedido + ", urlArquivo=" + urlArquivo + "]";
	}



}
