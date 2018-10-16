package br.gov.inpi.epecws.frontcontroller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.gov.inpi.epec.facade.CertificadoServiceFacade;
import br.gov.inpi.epec.facade.PesquisaServiceFacade;
import br.gov.inpi.epec.xml.bibliographicdata.BibliographicData;
import br.gov.inpi.epec.xml.family.EquivalentsInquiry;
import br.gov.inpi.epec.xml.report.ReportApplicationReference;

/***
 * 
 * Classe respons�vel em prover o servi�o Prosur.
 * 
 * @author allanlq
 *
 */
@Path("/search")
@Stateless
public class EPecWs {

/** 
	 * 
	 * Objeto utilizado para obter a entidade no qual está sendo feita a 
	 * requisição de um relatório técnico.
	 * 
	 * Regra de Negócio : As entidades somente podem ter acesso aos seus
	 * próprios relatórios técnicos nunca de outra entidade.
	 * 
	 * */	
	private String entidade;

	private static final String SEARCH_PROPERTY = "OU";

	@EJB
	private PesquisaServiceFacade pesquisaService;

	@EJB
	private CertificadoServiceFacade certificadoService;

	
	
	
	
	// Dados bibliográficos --------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	/**
	 * 
	 * Método responsável em realizar pesquisas de 
	 * documentos de patente pelo seu número original.
	 * 
	 * 
	 * */
	@GET
	@Path("/application/original/{numeroOriginal}/biblio")
	@Produces(MediaType.TEXT_XML)
	public Response getDadosBibliograficosPorNumeroOriginal(@PathParam("numeroOriginal") String numeroOriginal) {

		try{
			
			if (this.pesquisaService != null) {

				BibliographicData biblio = this.pesquisaService.pesquisarPatentePorNumeroOriginal(numeroOriginal);
				if (biblio == null) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(biblio).build();

			}
			
		}catch(Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).build();
		}
		return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
	}
	
	

	/***
	 * 
	 * Método responsável em realizar pesquisas de dados bibliográficos de documentos
	 * de patente pela sua aplicação e por número EpoDoc.
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	@GET
	@Path("/application/epodoc/{numeroEpoDoc}/biblio")
	@Produces(MediaType.TEXT_XML)
	public Response getPedidoEpodoc(@PathParam("numeroEpoDoc") String numeroEpoDoc) {

		try {

			if (this.pesquisaService != null) {
			    
				BibliographicData biblio = this.pesquisaService.pesquisarAplicacaoPatentePorNumeroEpoDoc(numeroEpoDoc);
				if (biblio == null) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(biblio).build();
			
			}

		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).build();
		}
		return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
	}
	
	
	
	

	/***
	 * 
	 * Método responsável em realizar pesquisas de dados bibliográficos de patente 
	 * pela sua publicação e pelo número EpoDoc.
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	@GET
	@Path("/publication/epodoc/{numeroEpoDoc}/biblio")
	@Produces(MediaType.TEXT_XML)
	public Response getPedidoPublicacaoPatenteEpodoc(@PathParam("numeroEpoDoc") String numeroEpoDoc) {

		
		try{
			
			if (this.pesquisaService != null) {
			   
				BibliographicData biblio = this.pesquisaService.pesquisarPublicacaoPatentePorNumeroEpoDoc(numeroEpoDoc);
				if (biblio == null) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(biblio).build();
			}
			
		}catch(Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).build();
		}
		return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
	}

	
	
	
	
	
	/***
	 * 
	 * Método responsável em realizar as pesquisa de dados bibliográficos de patentes
	 * pela publicação e por número DocDb.
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	@GET
	@Path("/publication/docdb/{numeroDocDb}/biblio")
	@Produces(MediaType.TEXT_XML)
	public Response getPedidoPublicacaoPatenteDocDb(@PathParam("numeroDocDb") String numeroDocDb) {

		try{
		    
			if (this.pesquisaService != null) {
				BibliographicData biblio = this.pesquisaService.pesquisarPatentePorPublicacaoNumeroDocDb(numeroDocDb);
				if (biblio == null) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(biblio).build();
			}
			
		}catch(Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).build();
		}
		return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
	}
	

	
	

	/***
	 * 
	 * Método responsável em realizar as pesquisas de dados bibliográficos de patente pela aplicação
	 * e pelo número de DocDb 
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	@GET
	@Path("/application/docdb/{numeroDocDb}/biblio")
	@Produces(MediaType.TEXT_XML)
	public Response getPedidoAplicacaoPatenteDocDb(@PathParam("numeroDocDb") String numeroDocDb) {

	      try{
		    
	       	  
			if (this.pesquisaService != null) {
				
				BibliographicData biblio = this.pesquisaService.pesquisarPatentePorAplicacaoNumeroDocDb(numeroDocDb);
				if (biblio == null) {
			     	return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(biblio).build();
			
			}
		
		}catch(Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).build();
		}
	    return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
	}
    // Fim Dados Bibliográficos ---------------------------------------------------------------------------
	
	
	
	
	
	// Familia -------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------
	/***
	 * 
	 * Método responsável em realizar pesquisa de Familia de documentos de Patente pela sua
	 * aplicação e por número EpoDoc.
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	@GET
	@Path("/application/epodoc/{numeroEpoDoc}/family")
	@Produces(MediaType.TEXT_XML)
	public Response getFamiliaApplicationEpoDoc(@PathParam("numeroEpoDoc") String numeroEpoDoc) {

		try{
			
			if (this.pesquisaService != null) {
				
				EquivalentsInquiry equivalentsInquiry = this.pesquisaService.pesquisarFamiliaAplicacaoPorNumeroEpoDoc(numeroEpoDoc);
				if (equivalentsInquiry == null) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(equivalentsInquiry).build();
			
			}
			
		}catch(Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).build();
		}
	    return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
	}
	
	
	

	/***
	 * 
	 * Método responsável em realizar pesquisa de Familia de documentos de Patente pela publicação
	 * e pelo seu número EpoDoc.
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	@GET
	@Path("/publication/epodoc/{numeroPedido}/family")
	@Produces(MediaType.TEXT_XML)
	public Response getFamiliaPublicationEpoDoc(@PathParam("numeroPedido") String numeroPedido) {

		
		try{
		    
			if (this.pesquisaService != null) {

				EquivalentsInquiry equivalentsInquiry = this.pesquisaService.pesquisarFamiliaPublicacaoPorNumeroEpoDoc(numeroPedido);
				if (equivalentsInquiry == null) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(equivalentsInquiry).build();
			
			}
			
		}catch(Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).build();
		}
	    return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
	}

	
	
	// original ----
	/***
	 * 
	 * Método responsável em realizar pesquisa de Familia de documentos  
	 * Patente pela sua aplicação e por número original.
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	@GET
	@Path("/application/original/{numeroOriginal}/family")
	@Produces(MediaType.TEXT_XML)
	public Response getFamiliaPorNumeroOriginal(@PathParam("numeroOriginal") String numeroOriginal) {

		
		try{
			
			if (this.pesquisaService != null) {
				
				EquivalentsInquiry equivalentsInquiry = this.pesquisaService.pesquisarFamiliaNumeroOriginal(numeroOriginal);
				if (equivalentsInquiry == null) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(equivalentsInquiry).build();
				
			}
			
		}catch(Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).build();
		}
	    return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
	}

	
	
	
	
	/***
	 * 
	 * Método responsável em realizar pesquisa de Familia de documentos de Patente pela sua Aplicação
	 * e por número DocDb.
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	@GET
	@Path("/application/docdb/{numeroDocDb}/family")
	@Produces(MediaType.TEXT_XML)
	public Response getFamiliaAplicacaoDocDb(@PathParam("numeroDocDb") String numeroDocDb) {

		
		try{
			
			if (this.pesquisaService != null) {
				
				EquivalentsInquiry equivalentsInquiry = this.pesquisaService.pesquisarFamiliaAplicacaoDocDb(numeroDocDb);
				if (equivalentsInquiry == null) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(equivalentsInquiry).build();
			}
			
		}catch(Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).build();
		}
	    return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();

	}
	
	
	

	/***
	 * 
	 * Método responsável em realizar pesquisa de Familia de documentos de Patente pela sua
	 * publicação e pelo número docdb.
	 * 
	 * @param numeroEpoDoc
	 * @return
	 */
	@GET
	@Path("/publication/docdb/{numeroDocDb}/family")
	@Produces(MediaType.TEXT_XML)
	public Response getFamiliaPublicationDocDb(@PathParam("numeroDocDb") String numeroDocDb) {

		try{
			
			if (this.pesquisaService != null) {
			    
				EquivalentsInquiry equivalentsInquiry = this.pesquisaService.pesquisarFamiliaPublicationDocDb(numeroDocDb);
				if (equivalentsInquiry == null) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(equivalentsInquiry).build();
				
			}
			
		}catch(Exception e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_XML).build();
		}
		return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
	
	}
	// ------------------------------------------------------------------------------------------------


	
	// Relatórios Técnicos -----------------------------------------------------------------------------
	/***
	 * 
	 * Método responsável em retornar o Relatório Tecnico de uma patente pelo número de EpoDoc.
	 * 
	 * @param numeroDocDb
	 * @return
	 */
	@GET
	@Path("/application/epodoc/{numeroPedido}/report")
	@Produces(MediaType.TEXT_XML)
	public Response getRelatorioTecnicoAplicacaoEpoDoc(@PathParam("numeroPedido") String numeroPedido, @Context HttpServletRequest request) {

		try {

//			this.entidade = recuperarValorPropriedadeNoCertificado(request);
			this.entidade = "INPI-BR";
			if(this.entidade != null){
			    
				if (this.pesquisaService == null) {
					return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
				}
				
				ReportApplicationReference report = this.pesquisaService.pesquisarRelatorioTecnicoAplicacaoPorNumeroEpoDoc(numeroPedido, entidade);
				if (report == null) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(report).build();	
			 	
			}
	
		} catch (Exception e) {
			return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
		}
		return Response.status(Status.FORBIDDEN).type(MediaType.APPLICATION_XML).build();
		
	}

	
	
	
	
	/***
	 * 
	 * M�todo respons�vel em retornar o Relat�rio Tecnico de uma patente pelo n�mero de EpoDoc.
	 * 
	 * @param numeroDocDb
	 * @return
	 */
	@GET
	@Path("/application/docdb/{numeroPedido}/report")
	@Produces(MediaType.TEXT_XML)
	public Response getRelatorioTecnicoAplicacaoDocDb(@PathParam("numeroPedido") String numeroPedido, @Context HttpServletRequest request) {

		try {

			this.entidade = recuperarValorPropriedadeNoCertificado(request);
			if(this.entidade != null){
				
				if (this.pesquisaService == null) {
					return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
				}

				ReportApplicationReference report = this.pesquisaService.pesquisarRelatorioTecnicoAplicacaoPorNumeroDocDbEEntidade(numeroPedido, entidade);
				if (report == null) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(report).build();	
				
			}
			
		} catch (Exception e) {
			return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
		}
		return Response.status(Status.FORBIDDEN).type(MediaType.APPLICATION_XML).build();
	}

	/***
	 * 
	 * M�todo respons�vel em retornar o Relat�rio Tecnico de uma patente pelo n�mero de DocDb.
	 * 
	 * @param numeroDocDb
	 * @return
	 */
	@GET
	@Path("/publication/docdb/{numeroDocDb}/report")
	@Produces(MediaType.TEXT_XML)
	public Response getRelatorioTecnicoDocDb(@PathParam("numeroDocDb") String numeroDocDb, @Context HttpServletRequest request) {

		try {

			this.entidade = recuperarValorPropriedadeNoCertificado(request);
			if(this.entidade != null){
			    
				if (this.pesquisaService == null) {
					return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
				}

				ReportApplicationReference report = this.pesquisaService.pesquisarRelatorioTecnicoPublicacaoPorNumeroDocDb(numeroDocDb, entidade);
				if (report == null) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(report).build();

				
            }
			
		} catch (Exception e) {
			return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
		}
		return Response.status(Status.FORBIDDEN).type(MediaType.APPLICATION_XML).build();

	}

	/***
	 * 
	 * M�todo respons�vel em retornar o Relat�rio Tecnico de uma patente pelo n�mero de DocDb.
	 * 
	 * @param numeroDocDb
	 * @return
	 */
	@GET
	@Path("/publication/epodoc/{numeroDocDb}/report")
	@Produces(MediaType.TEXT_XML)
	public Response getRelatorioTecnicoPublicationEpoDoc(@PathParam("numeroDocDb") String numeroDocDb, @Context HttpServletRequest request) {

		try {

			this.entidade = this.recuperarValorPropriedadeNoCertificado(request);
			if(!"".equals(this.entidade.trim())){
				
				if (this.pesquisaService == null) {
					return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
				}else{
					
					ReportApplicationReference report = this.pesquisaService.pesquisarRelatorioTecnicoPublicacaoPorNumeroEpoDocEEntidade(numeroDocDb,entidade);
					if (report == null) {
						return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
					}
					return Response.ok().type(MediaType.TEXT_XML).entity(report).build();	
				}

			}
					
		} catch (Exception e) {
			return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
		}
		return Response.status(Status.FORBIDDEN).type(MediaType.APPLICATION_XML).build();
	}
	
	
	/***
	 * 
	 * M�todo respons�vel em retornar o Relat�rio Tecnico de uma patente pelo n�mero de DocDb.
	 * 
	 * @param numeroDocDb
	 * @return
	 */
	@GET
	@Path("/application/original/{numeroOriginal}/report")
	@Produces(MediaType.TEXT_XML)
	public Response getRelatorioTecnicoPorNumeroOriginal(@PathParam("numeroOriginal") String numeroOriginal, @Context HttpServletRequest request) {

		try {

			this.entidade = recuperarValorPropriedadeNoCertificado(request);
			if(this.entidade != null){
				
				if (this.pesquisaService == null) {
					return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
				}
				ReportApplicationReference report = this.pesquisaService.pesquisarRelatorioTecnicoPorNumeroOriginalEEntidade(numeroOriginal, entidade);
				if (report == null) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_XML).build();
				}
				return Response.ok().type(MediaType.TEXT_XML).entity(report).build();	
				
			}
			
		} catch (Exception e){
			return Response.status(Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_XML).build();
		}
		return Response.status(Status.FORBIDDEN).type(MediaType.APPLICATION_XML).build();

	}

	private String recuperarValorPropriedadeNoCertificado(HttpServletRequest request) throws Exception {
		return certificadoService.recuperarPropriedadesCertificado(request, SEARCH_PROPERTY);
	}

}
