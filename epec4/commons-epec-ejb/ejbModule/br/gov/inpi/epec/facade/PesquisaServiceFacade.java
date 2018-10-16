package br.gov.inpi.epec.facade;

import javax.ejb.Local;

import br.gov.inpi.epec.entities.RelatorioTecnico;
import br.gov.inpi.epec.xml.bibliographicdata.BibliographicData;
import br.gov.inpi.epec.xml.family.EquivalentsInquiry;
import br.gov.inpi.epec.xml.report.ReportApplicationReference;



/**
 * 
 * Interface implementação do serviço de pesquisa de documentos de patente para o Prosur.
 * 
 * @author allanlq
 *
 */
@Local
public interface PesquisaServiceFacade {

	
	/**
	 * 
	 * M�todo respons�vel pesquisar dados bibliogr�ficos de documentos de patente pela seu
	 * n�mero de publica��o docdb e converter em xml. 
	 * 
	 * @param numeroPedido
	 * @return
	 */
	public BibliographicData pesquisarPatentePorPublicacaoNumeroDocDb(String numeroPedido);
		
	/**
	 * 
	 * M�todo respons�vel em pesquisar dados bibliogr�ficos de documentos de patentes pelo seu
	 * n�mero de aplica��o docdb e converter para o formato xml
	 * 
	 * @param numeroPedido
	 * @return
	 */
	public BibliographicData pesquisarPatentePorAplicacaoNumeroDocDb(String numeroPedido);

	/***
	 * 
	 * M�todo respons�vel em pesquisar dados bibliogr�ficos de patentes pelo seu 
	 * n�mero de aplica��o epodoc e converter para xml.
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	public BibliographicData pesquisarAplicacaoPatentePorNumeroEpoDoc(String numeroEpoDoc);

	
	/***
	 * 
	 * M�todo respons�vel em pesquisar dados bibliogr�ficos de patentes pelo seu
	 * n�mero de publica��o epodoc e converter para xml. 
	 * 
	 * @param numeroPedido
	 * @return
	 */
	public BibliographicData pesquisarPublicacaoPatentePorNumeroEpoDoc(String numeroPedido);
	
    
	/***
	 * 
	 * M�todo respons�vel em pesquisar dados bibliogr�ficos de patentes pelo seu 
	 * n�mero de patente original e converter para xml.
	 * 
	 * @param numeroOriginal
	 * @return
	 */
	public BibliographicData pesquisarPatentePorNumeroOriginal(String numeroOriginal);
	
	
	/***
	 * 
	 * M�todo respons�vel em pesquisar dados bibliogr�ficos de patentes pelo seu n�mero
	 * de aplica��o epodoc pela entidade de origem e converter para xml.
	 * 
	 * @param numeroEpoDoc
	 * @param entidade
	 * @return
	 */
	public BibliographicData pesquisarAplicacaoPatentePorNumeroEpoDocEEntidadeConverterXml(String numeroEpoDoc, String entidade);


	
	/***
	 * 
	 * M�todo respons�vel em pesquisar familia de patentes por
	 * numero de aplica��o epodoc e converter para xml. 
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	public EquivalentsInquiry pesquisarFamiliaAplicacaoPorNumeroEpoDoc(String numeroEpoDoc);
	
	
	/***
	 * 
	 * M�todo respons�vel em pesquisar familia de patentes  por 
	 * n�mero de publica��o epodoc e converter para xml.
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	public EquivalentsInquiry pesquisarFamiliaPublicacaoPorNumeroEpoDoc(String numeroEpoDoc);
	
	
	/**
	 * 
	 * M�todo respos�vel em pesquisar familia de patentes por n�mero de aplica��o
	 * docdb e converter para xml.
	 * 
	 * @param numeroPedido
	 * @return
	 */
	public EquivalentsInquiry pesquisarFamiliaAplicacaoDocDb(String numeroPedido);
	
	
	/***
	 * 
	 * M�todo respons�vel em pesquisar familia de patentes pelo n�mero de 
	 * publica��o docdb e converter para xml.
	 * 
	 * @param numeroPedido
	 * @return
	 */
	public EquivalentsInquiry pesquisarFamiliaPublicationDocDb(String numeroPedido);
		
	
	/***
	 * 
	 * M�todo respons�vel em pesquisar familia de patentes pelo n�mero original
	 * e converter para xml.
	 * 
	 * @param numeroOriginal
	 * @return
	 */
	public EquivalentsInquiry pesquisarFamiliaNumeroOriginal(String numeroOriginal);
 
	
	/**
	 * 
	 * M�todo respons�vel em pesquisar relatorios t�cnicos pelo n�mero de aplica��o
	 * epodoc e converter para xml.
	 * 
	 * @param numeroPedido
	 * @param entidade
	 * @return
	 */
	public ReportApplicationReference pesquisarRelatorioTecnicoAplicacaoPorNumeroEpoDoc(String numeroPedido, String entidade);
		
	
	//public ReportApplicationReference pesquisarRelatorioTecnicoPublicacaoPorNumeroEpoDoc(String numeroPedido);
	

	//public ReportApplicationReference pesquisarRelatorioTecnicoAplicacaoPorNumeroDocDb(String numeroPedido);
	
		
	//public ReportApplicationReference pesquisarRelatorioTecnicoPublicacaoPorNumeroDocDb(String numeroPedido);

		
	//public ReportApplicationReference pesquisarRelatorioTecnicoPorNumeroOriginal(String numeroOriginal);


	
    /***
     * 
     * M�todo respons�vel em pesquisar relat�rios  t�cnicos pelo n�mero de aplica��o
     * docdb pela entidade de origem e converter para xml.
     * 
     * @param numeroPedido
     * @param entidade
     * @return
     */
	public ReportApplicationReference pesquisarRelatorioTecnicoAplicacaoPorNumeroDocDbEEntidade(String numeroPedido, String entidade);

    
	/**
	 * 
	 * M�todo respons�vel em pesquisar relat�rios t�cnicos pelo n�mero de publica��o 
	 * epodoc pela entidade de origem e converter para xml.
	 * 
	 * @param numeroPedido
	 * @param entidade
	 * @return
	 */
	public ReportApplicationReference pesquisarRelatorioTecnicoPublicacaoPorNumeroEpoDocEEntidade(String numeroPedido, String entidade);


	/***
	 * 
	 * M�todo respons�vel em pesquisar relat�rios t�cnicos pelo n�mero de orginal pela sua
	 * entidade de origem e converter para xml.
	 * 
	 * @param numeroOriginal
	 * @param entidade
	 * @return
	 */
	public ReportApplicationReference pesquisarRelatorioTecnicoPorNumeroOriginalEEntidade(String numeroOriginal, String entidade);


	/***
	 * 
	 * M�todo respons�vel em pesquisar rel�torios t�cnicos pelo n�mero de publica��o docdv e sua
	 * entidade de origem e converter para xml. 
	 * 
	 * @param numeroPedido
	 * @param entidade
	 * @return
	 */
	public ReportApplicationReference pesquisarRelatorioTecnicoPublicacaoPorNumeroDocDb(String numeroPedido, String entidade);
	
	
	public RelatorioTecnico pesquisarRelatorioOdt(String numeroPedido);

	
}
