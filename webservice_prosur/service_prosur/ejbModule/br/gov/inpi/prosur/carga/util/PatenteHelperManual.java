package br.gov.inpi.prosur.carga.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jboss.logging.Logger;
import org.prosur.catalog.ws.DesignPatent;
import org.prosur.catalog.ws.InventionPatent;
import org.prosur.catalog.ws.IpRecordTypeEnum;
import org.prosur.catalog.ws.Priority;

import br.gov.inpi.patentes.beans.Desenho;
import br.gov.inpi.patentes.beans.DiClassificacao;
import br.gov.inpi.patentes.beans.DiPedido;
import br.gov.inpi.patentes.beans.Patente;
import br.gov.inpi.patentes.beans.PtnClassif;
import br.gov.inpi.patentes.beans.PtnClassifDesc;
import br.gov.inpi.patentes.beans.PtnEscritorio;
import br.gov.inpi.patentes.beans.PtnPaisProsur;
import br.gov.inpi.patentes.beans.PtnPct;
import br.gov.inpi.patentes.beans.PtnPedido;
import br.gov.inpi.patentes.beans.PtnPrioridade;
import br.gov.inpi.patentes.beans.PtnResumoPedido;
import br.gov.inpi.patentes.beans.PtnSolicitante;
import br.gov.inpi.patentes.beans.PtnTipoDespacho;
import br.gov.inpi.services.ProcessoPatenteManualInterface;

public class PatenteHelperManual extends ProsurUtils{

private static final Logger LOGGER = Logger.getLogger(PatenteHelperAutomatico.class);
	
	
	private final static String DETAIL_LINK = "http://www.inpi.gov.br/portal/";
	
	
	private ProcessoPatenteManualInterface persistPatentes;

	private String designClassification;
	

	private List<PtnPaisProsur> paisesValidos;

	
	private List<PtnTipoDespacho> despachos;
	
	
	private boolean isPedidoDivido;
	
	
		
	public String getDesignClassification() {
		return designClassification;
	}

	public void setDesignClassification(String designClassification) {
		this.designClassification = designClassification;
	}

	public ProcessoPatenteManualInterface getPersistPatentes() {
		return persistPatentes;
	}
	public void setPersistPatentes(ProcessoPatenteManualInterface persistPatentes) {
		this.persistPatentes = persistPatentes;
	}
	
	private void listarTodosPaises(){
		
		this.paisesValidos = this.persistPatentes.retornarTodosPaisesProsur();
		
	}
	
	
	
	// Desenho Industrial --------------------------------------------------------------------
    public Desenho retornarDesenho(DiPedido diPedido){
    	
    	Desenho desenho = new Desenho();
		
		try{
			
			this.listarTodosPaises();
				boolean isSigilo = this.isPedidoSigilo(diPedido.getNumPedido().trim());
				
				if(!isSigilo){
					DesignPatent design = this.conveterDiPedidoParaDesignPatent(diPedido);
					desenho.setDesignPatent(design);
					desenho.setCodigoIdentificador(diPedido.getCodPedido());
				}
		
		}catch(Exception e){
			LOGGER.error(" :: ERRO ao converter objects em PatenteDesenhoIndustrial ::" + e.getMessage());
		}
		return desenho;
		
	}
	
	
		
     
    /***
     * Desenho Industrial
     * Converte os pedidos de Desenho Industrial para o padrão do Prosur. 
     * 
     * @param diPedido
     * @return
     */
    private DesignPatent conveterDiPedidoParaDesignPatent(DiPedido diPedido){
    	
    	DesignPatent designPatent = new DesignPatent();
    	
    	try{
    		
    		int codigoPedido = diPedido.getCodPedido();
			this.retornarTodosDespachosDoPedido(codigoPedido);
			
			String numeroPedido = super.formatarNumeroPedido(diPedido.getNumPedido().trim());
			 
		    boolean isConcedido = this.verificarPedidoComConcessao();
	        if(isConcedido){
	          	 designPatent.setRegistrationNumber(numeroPedido);
	        }
				
    		designPatent.setOnapiId("BR");
    		designPatent.setApplicationId(numeroPedido);
    		designPatent.setNationalPresentationDate(diPedido.getDtDeposito());
    		
    		Date national = this.retornarPublshingDateDI(codigoPedido);
    		if(national != null){
    		    designPatent.setNationalPublishingDate(national);		 		
    		}
    		
    		String titulo =this.formatarTitulo(diPedido.getTitulo());
    		if(titulo != null){
    	       designPatent.setTitle(titulo.trim());
    		}
    		
    	    designPatent.setStatusId(this.retornarStatusId(codigoPedido));
    	    designPatent.setIpRecordDetailLink(DETAIL_LINK);
    	    designPatent.setRepresentativeName(this.retornarEscritorio(codigoPedido));
    	    designPatent.setConclusionMethod(this.retornarConclusionMethodParaPedidoDesenhoIndustrial());
      	  
    		designPatent.setPriorities(this.converterListaDePtnPrioridadesArray(codigoPedido));
    		designPatent.setInventors(this.obterAutores(codigoPedido));
            designPatent.setRecordType(IpRecordTypeEnum.DESIGN_PATENT);     
         
            designPatent = this.retornarApplicantNameRequestContryId(designPatent,codigoPedido);
            designPatent = this.retornarClassificacoesDesignPatent(designPatent,codigoPedido); 
            this.despachos = null;
            
    	}catch(Exception e){
			LOGGER.error(" :: ERRO ao converter objects em PatenteDesenhoIndustrial ::" + e.getMessage());
		}
    	
    	return designPatent;   	
    }
		
    
    /***
     * Desenho Industrial
     * Retorna as classificações de Locarno.
     * 
     * @param designPatent
     * @param codigoPedido
     * @return
     */
    private DesignPatent retornarClassificacoesDesignPatent(DesignPatent designPatent, int codigoPedido){
    	
    	List<DiClassificacao> classificacoes = this.persistPatentes.pesquisarClassificacoesPedidoDI(codigoPedido);
    	    	
    	if(classificacoes != null){
    	   
    		String[] classificacoesLocarno = new String[classificacoes.size()];
    	    
    		List<Integer> codigosClassificacao = new ArrayList<Integer>();
    		Iterator<DiClassificacao> it = classificacoes.iterator();
    		int i = 0;
            while(it.hasNext()){
            	
            	DiClassificacao classif = it.next();
            	
            	StringBuilder strBuilder = new StringBuilder();
            	strBuilder.append(classif.getCodInternacional());
            	if(it.hasNext()){
            		strBuilder.append(";");
            	}
            	
            	classificacoesLocarno[i] = strBuilder.toString();
            	i++;
            }
            
            designPatent.setLocarnoClassification(classificacoesLocarno);    	       	    
   	
    	}
    	return designPatent;
    }
    //-----------------------------------------------------------------------------------
    
    
    
    /***
     * Desenho Industrial
     * Retorna as classificações de Locarno existentes para um determinado pedido.
     * 
     * @param codigoPedido
     * @return
     */
    private List<PtnClassif> retornarDesignPatentPedidoDI(Integer codigoPedido){
    	
    	List<PtnClassif> classificacoes = this.persistPatentes.pesquisarClassificacoesPedido(codigoPedido);
    	if(classificacoes != null){
    		return classificacoes;
    	}
    	return null;
    }
    
    
    
 /*   *//**
     * Desenho Industrial
     * Retorna a descrição das classificações de Locarno.
     * 
     * @param codigosClassificacao
     * @return
     */
    private String retornarDesignClassification(List<Integer> codigosClassificacao){
        
    	String descricao = null;
    	if(codigosClassificacao != null && !codigosClassificacao.isEmpty()){
    	    
    		StringBuilder strBuilder = new StringBuilder();
    		Iterator<Integer> it = codigosClassificacao.iterator();
    		while(it.hasNext()){
    			
    			Integer codigo = it.next();
    			PtnClassifDesc classifDesc = this.persistPatentes.retornarDescricaoDosProjetosDI(codigo);
    			strBuilder.append(classifDesc.getDesClassificacao());
    		    if(it.hasNext()){
    		       	strBuilder.append(";");
    		    }
    		}
    		
    		String aux = strBuilder.toString();
    		int totalCaracteres = aux.trim().length();
    		if(totalCaracteres <= 255){
    			descricao = aux;
    		}else{
    			descricao = aux.substring(0,250);
    		}
    		
    	}
    	
    	return descricao;
    	
    }
    
    
    
    /**
     * Desenho Industrial
     * Obtem os autores do Pedido de Desenho Industrial. 
     *  
     * @param codigoPedido
     * @return
     */
    private String[] obterAutores(Integer codigoPedido){
    	
    	List<String> autores = new ArrayList<String>();
    	autores = this.persistPatentes.pesquisarAutoresPorCodigoDoPedidoDI(codigoPedido);
    	if(autores != null && !autores.isEmpty()){
 		  return super.converterListaParaArray(autores);
    	}else{
    		
    		String leiPropriedade = "Sigilo do Autor - Art. 6º parágrafo 4º da LPI";
    		autores.add(leiPropriedade);
    		return super.converterListaParaArray(autores);
    		
    	}
    	
 	}
    
    
    
    /***
	 * Desenho Industrial
	 * Retorna o conclusion method.
	 * 
	 * @return
	 */
	private String retornarConclusionMethodParaPedidoDesenhoIndustrial(){
		
	   String conclusionMethod = null;
	   Iterator<PtnTipoDespacho> it = this.despachos.iterator();
	   while(it.hasNext()){
		   
		   PtnTipoDespacho ptnTipoDespacho = it.next();
		   int codigoDespacho = ptnTipoDespacho.getCodigoTipoDespacho();
		   if(super.isPedidoConcedido(codigoDespacho)){
			   conclusionMethod = "CONCEDIDO"; 
		   }else if(super.isPedidoDIExtinto(codigoDespacho)){
			   conclusionMethod = "EXTINTO";
		   }else if(ptnTipoDespacho.getCodDespachoRpi().trim().equals("35")){
			   conclusionMethod = "ARQUIVADO";  
		   }else if(ptnTipoDespacho.getCodDespachoRpi().trim().equals("36")){
			   conclusionMethod = "INDEFERIDO";
		   }else if(ptnTipoDespacho.getCodDespachoRpi().trim().equals("65")){
			   conclusionMethod = "DESISTENCIA";
		   }
	   }
	   return conclusionMethod;
	}
    // Fim de  Desenho Industrial --------------------------------------------------------------- 
  
	 // Patentes ---------------------------------------------------------------------------------
		public Patente retornarPedidoPatente(PtnPedido pedido){
			
			Patente patente = new Patente();
			
			try{
				
				this.listarTodosPaises();
				
					boolean isSigilo = this.isPedidoSigilo(pedido.getNumPedido().trim());
					if(!isSigilo){
						
						InventionPatent inventionPatent = this.retornarPatent(pedido);
				        patente.setInventionPatent(inventionPatent);
				        patente.setCodigoPedido(pedido.getCodPedido());
					}
					
			
			}catch(Exception e){
				LOGGER.error(" :: ERRO ao converter os objetos em InventionPatent : " + e.getMessage());
			}
			return patente;
		}
	    //--------------------------------------------------------------------------------------
	
   
    
    private boolean isPedidoSigilo(String numeroPedido){
    	boolean isPedidoEmSigilo = Boolean.FALSE;
    	isPedidoEmSigilo = this.persistPatentes.isPedidoEmSigilo(numeroPedido.trim());
    	return isPedidoEmSigilo;
    }
    
    
    
    private InventionPatent retornarPatent(PtnPedido pedido){
		
		InventionPatent inventionPatent = new InventionPatent();
		
		try{
			
			int codigoPedido = pedido.getCodPedido();
			this.retornarTodosDespachosDoPedido(codigoPedido);
			
			
			String numeroPedidoFormatado = super.formatarNumeroPedido(pedido.getNumPedido().trim());
			inventionPatent.setApplicationId(numeroPedidoFormatado);
			inventionPatent.setNationalPresentationDate(pedido.getDtDeposito());
			inventionPatent.setOnapiId("BR");
			

            boolean isConcedido = this.verificarPedidoComConcessao();
            if(isConcedido){
                inventionPatent.setRegistrationNumber(numeroPedidoFormatado);
            }
			
			String titulo = this.formatarTitulo(pedido.getTitulo());
			if(titulo != null){
			   inventionPatent.setTitle(titulo.trim());
			}
			
			this.verificarPedidoDivido();
		 	if(this.isPedidoDivido){
		 		
		 		try {
		 			inventionPatent.setNationalPublishingDate(this.retornarPublishingDateDoPedidoOriginal(codigoPedido));
				} catch (Exception ignore) {}
		 	}else{
		 		inventionPatent.setNationalPublishingDate(this.retornarPublshingDatePT(codigoPedido));		 		
		 	}
		
		    inventionPatent.setStatusId(this.retornarStatusId(codigoPedido));
			
		    inventionPatent.setPatentAbstract(this.retornarResumo(codigoPedido));
			
		    inventionPatent.setDescription(this.retornarRelatorioDescritivo(codigoPedido));
			
  		   	inventionPatent.setIpRecordDetailLink(DETAIL_LINK);
  		   	
  		  try {
  			  inventionPatent.setRepresentativeName(this.retornarEscritorio(codigoPedido));
			} catch (Exception ignore) {}

            inventionPatent.setPriorities(this.converterListaDePtnPrioridadesArray(codigoPedido));
            inventionPatent.setPatentTypeId(this.retornarPatentTypeId(pedido.getNumPedido()));
			
     		String[] inventores = this.obterInventores(codigoPedido);
     		if(inventores != null){
     		   inventionPatent.setInventors(inventores);
     		}
     		
     		inventionPatent.setRecordType(IpRecordTypeEnum.INVENTION_PATENT);
     		     		
     		List<String> classificacoes = this.retornarClassificacoes(codigoPedido);
     		if(classificacoes != null){
     		    inventionPatent.setInternationalClassification(super.converterListaParaArray(classificacoes));
     		}
     	          
		 	inventionPatent = this.retornarApplicantNameRequestContryId(inventionPatent,codigoPedido);
	        
		 	PtnPct pct = this.retornarPct(codigoPedido);
		 	if(pct.getCdOmpi() != null){
			 	inventionPatent.setPctApplicationDate(pct.getDtPct());
			 	inventionPatent.setPctApplicationNumber(pct.getPtnPctPK().getNumPct());
		 	}	 	
	        
		 	this.despachos.clear();
		}catch(Exception e){
			LOGGER.error(" >> ERRO :: ao converter patente para o bean Prosur :: " + pedido.getNumPedido());
		}
				
		return inventionPatent;
		
	}
    // Fim de Patentes -------------------------------------------------------------------------
      
  

	private Priority[] converterListaDePtnPrioridadesArray(Integer codigoPedido){
    	
    	List<PtnPrioridade> prioridades = this.retornarPrioridades(codigoPedido);
    	if(prioridades != null && !prioridades.isEmpty()){
    		return this.retornarPrioridadesPedido(prioridades);
    	}
    	return null;
 	}
	
    
	
	 /***
	    * 
	    * Método responsável em obter um array de prioridades.
	    * 
	    */
	   private Priority[] retornarPrioridadesPedido(List<PtnPrioridade> prioridades){
	    	
	    	Priority[] priorities = null;
	    	if(prioridades != null && !prioridades.isEmpty()){
	    		    		
	    		priorities = new Priority[prioridades.size()];
	    		int total = 0;
	    		for(int i = 0; i < prioridades.size(); i++){
	    		    		
	    			PtnPrioridade ptnPrioridade = prioridades.get(i);
	    			Priority priority = new Priority();
	    			priority.setPriorityNumber(ptnPrioridade.getNumeroPrioridade().trim());
	    			priority.setPriorityDate(ptnPrioridade.getDataPrioridade());
	    			
	    			String codPais = ptnPrioridade.getPais();
	    			boolean isPaisValido = this.validarPais(codPais);
	    			if(isPaisValido){
	    				priority.setPriorityCountryId(codPais);
	    			}
	    			
	    			priorities[total] = priority; 
	    			total++;
	    			
	    		}
	    		
	    	}
	    	return priorities;
	    }
	
	
	
    
	/***
	 * Retorna as prioridades que podem existentir para um pedido. 
	 * @param codigoPedido
	 * @return
	 */
	private List<PtnPrioridade> retornarPrioridades(Integer codigoPedido){
	   List<PtnPrioridade> prioridades = this.persistPatentes.pesquisarPrioridades(codigoPedido);
	   if(prioridades == null){
		   LOGGER.info(" Sem prioridades ");
	   }
	   return prioridades;
	}
	
	
	
	
    private String[] obterInventores(Integer codigoPedido){
    	
    	List<String> inventores = new ArrayList<String>();
    	inventores = this.retornarInventors(codigoPedido);
    	if(inventores != null && !inventores.isEmpty()){
 		  return super.converterListaParaArray(inventores);
    	}
    	return null;
 	}
    
    
    
    

    /***
     * 
     * Retorna o nome dos inventores de um pedido, que não estão com
     * condição de anonimato para o pedido.
     * 
     * @param codigoPedido
     * @return
     */
    private List<String> retornarInventors(Integer codigoPedido){
    	List<String> inventors = this.persistPatentes.pesquisarInventoresPorCodigoDoPedido(codigoPedido);
    	return inventors;
    }
	
  
    
    
    
	
   /***
	 * Retorna todos os despachos publicados de um pedido.
	 * @param codigoPedido
	 */
	private void retornarTodosDespachosDoPedido(Integer codigoPedido){
		this.despachos = this.getPersistPatentes().pesquisarTodosTipoDespachos(codigoPedido);
	}	
	
	
	
	

	/**
	 * 
	 * Retorna a data da publicação do Pedido de Patente
	 * despachos nos despachos :
	 * Patentes : '3.1', '3.2','3.6','1.3' 
	 *
	 * @param codigoDespacho
	 * @return
	 */
	private Date retornarPublshingDatePT(int codigoPedido){
	    
		 Date publishingDate = this.persistPatentes.pesquisarDataDePublicacaoPT(codigoPedido);
	 	 if(publishingDate != null){
	        return publishingDate; 
	 	 }else{
	 	    return null;
	 	 }
	 	 
    }
	
	
	/**
	 * 
	 * Retorna a data da publicação do Pedido de Patente
	 * despachos nos despachos :
	 * Desenho Industrial : '35','36','39' 
	 *
	 * @param codigoDespacho
	 * @return
	 */
	private Date retornarPublshingDateDI(int codigoPedido){
	    
		 Date publishingDate = this.persistPatentes.pesquisarDataDePublicacaoDI(codigoPedido);
	 	 if(publishingDate != null){
	        return publishingDate; 
	 	 }else{
	 	    return null;
	 	 }
	 	 
    }
	
	
	
	
	private Date retornarPublishingDateDoPedidoOriginal(int codigoPedidoDerivado){
		
		Date publishingDate = null;
		int codigoPedidoOriginal = this.persistPatentes.verificarCodigoOrginalPedidoDivido(codigoPedidoDerivado);
		if(codigoPedidoOriginal > 0){
			publishingDate = this.retornarPublshingDatePT(codigoPedidoOriginal);
		}
		return publishingDate;
		
	}
	
	
	
	
	
	
	
	
	
	/***
	 * 
	 * Retorna os dados do representativeName (procurador ou escritório) responsável pelo
	 * pedido.
	 * 
	 * @param codigoPedido
	 * @return
	 */
	private String retornarEscritorio(int codigoPedido){
		
		PtnEscritorio escritorio = this.persistPatentes.pesquisarEscritorio(codigoPedido);
		if(escritorio.getCodigoProcurador() != null){
	        
			String nome = escritorio.getNomeCompleto().trim();
			int totalCaracteres = nome.length();
			int totalMaximoPermitido = Integer.parseInt("255");
			if(totalCaracteres <= totalMaximoPermitido){
				return nome;
			}else{
     		    return nome.substring(0,totalMaximoPermitido);	
			}
			
		
		} 
		return null;
	}

	

	
	
	
	
	/***
	 * Verifica se um pedido tem um despacho de concessão, para 
	 * atribuir o Registration Number que vem a ser o mesmo número
	 * do ApplicationId (Número do pedido).
	 * 
	 * Desenho Industrial = 39.
	 *
	 * @return
	 */
	private DesignPatent verificarConcessaoResgistro(DesignPatent designPatent){ 
		
		boolean isPedidoConcedido = this.verificarPedidoComConcessao();
		if(isPedidoConcedido){
			designPatent.setRegistrationNumber(designPatent.getApplicationId());
		}
		return designPatent;
	}
	
	
	
	
	
	
	/***
	 * Verifica se um pedido tem um despacho de concessão, para 
	 * atribuir o Registration Number que vem a ser o mesmo número
	 * do ApplicationId (Número do pedido).
	 *
	 * @return
	 */
	@SuppressWarnings("unused")
	private InventionPatent verificarConcessaoResgistro(InventionPatent inventionPatent){ 
		
		boolean isPedidoConcedido = this.verificarPedidoComConcessao();
		if(isPedidoConcedido){
			 inventionPatent.setRegistrationNumber(inventionPatent.getApplicationId());
		}
		return  inventionPatent;
	}
	
	
	
	
	

	private boolean verificarPedidoComConcessao(){
    	
    	boolean isConcedido = Boolean.FALSE;
		for(int i = 0; i < this.despachos.size(); i++){
		
			PtnTipoDespacho despacho = this.despachos.get(i);
			if(super.isPedidoConcedido(despacho.getCodigoTipoDespacho())){
		    	isConcedido = Boolean.TRUE;
			    break;
			}
			
		}
		return isConcedido;
	
    }
	

	
	
	
	/***
	 * 
	 * Retorna o(s)  no nome do(s) applicant(s) (depositante) e o país de origem do
	 * principal applicant ( o de menor ordem ) de um pedido de Desenho Industrial.
	 * 
	 * @param processo
	 * @param codigoPedido
	 * @return
	 */
	private DesignPatent retornarApplicantNameRequestContryId(DesignPatent designPatent,int codigoPedido){
		
		DesignPatent designPatentAux  = designPatent;
		List<PtnSolicitante> solicitantes = this.retornarSolicitanteDoPedido(codigoPedido);
		
		if(solicitantes != null && !solicitantes.isEmpty()){
		    
			List<String> applicants = new ArrayList<String>();

			int i=0;
			Iterator<PtnSolicitante> it = solicitantes.iterator();
			while(it.hasNext()){
				
				i++;
				PtnSolicitante solicitante = it.next();
				if(i == 1){
					String pais = solicitante.getPaisOrigem();
					boolean isValido = this.validarPais(pais);
					if(isValido){
					   designPatentAux.setRequestCountryId(solicitante.getPaisOrigem());
					}
				}
			  	applicants.add(solicitante.getNome());
			}
			
			String depositantes[] = super.converterListaParaArray(applicants);
			designPatentAux.setApplicantName(depositantes);
		}
		return designPatentAux;
	}
	
	
	/***
	 * 
	 * Retorna o(s)  no nome do(s) applicant(s) (depositante) e o país de origem do
	 * principal applicant ( o de menor ordem ) para um pedido de Patente .
	 * 
	 * @param processo
	 * @param codigoPedido
	 * @return
	 */
	private InventionPatent retornarApplicantNameRequestContryId(InventionPatent inventionPatent,
			int codigoPedido){

		List<PtnSolicitante> solicitantes = this.retornarSolicitanteDoPedido(codigoPedido);
		if(solicitantes != null && !solicitantes.isEmpty()){
		    
			List<String> applicants = new ArrayList<String>();

			int i=0;
			Iterator<PtnSolicitante> it = solicitantes.iterator();
			while(it.hasNext()){
				
				i++;
				PtnSolicitante solicitante = it.next();
				if(i == 1){
					
					String pais = solicitante.getPaisOrigem();
					boolean isValido = this.validarPais(pais);
					if(isValido){
						inventionPatent.setRequestCountryId(solicitante.getPaisOrigem());
					}
					
				}
				applicants.add(solicitante.getNome());
			}
			
			String depositantes[] = super.converterListaParaArray(applicants);
			inventionPatent.setApplicantName(depositantes);
						
		}
		return inventionPatent;
	}
	
	
	private List<PtnSolicitante> retornarSolicitanteDoPedido(int codigoPedido){
		List<PtnSolicitante> ptnSolicitantes = this.persistPatentes.pesquisarSolicitantePorCodigoPedido(codigoPedido);
		return ptnSolicitantes;
	}
	
	
	
	/**
	 * 
	 * Verifica a conclusão do pedido de Desenho Industrial.
	 * 
	 * @param processo
	 * @return
	 */
	private DesignPatent conclusionMethod(DesignPatent designPatent){
		
		String conclusionMethod = this.retornarConclusionMethodParaPedidoDesenhoIndustrial();
		designPatent.setConclusionMethod(conclusionMethod);
		return designPatent;
		
	}
	
	
	    
    /***
	 * 
	 * Método responsável em retornar o resumo do pedido;
	 * 
	 * Obs : Limite máximo do é de 5.000 caracteres.
	 * 
	 * @param codigoPedido
	 * @return
	 */
	private String retornarResumo(Integer codigoPedido){
		
		PtnResumoPedido ptnResumo = this.persistPatentes.pesquisarResumoDoPedido(codigoPedido);
		if(ptnResumo != null ) {
			
			if(ptnResumo.getResumo() != null && !"".equals(ptnResumo.getResumo())){
				
				String resumo = ptnResumo.getResumo().trim();
				int total = resumo.length();
				int totalMaximo = Integer.parseInt("5000"); 			
				if(total <= totalMaximo){
				     return resumo;
				}else{
					String aux = resumo.substring(0,totalMaximo);
					return aux;
				}
			}else{
				return null;
			}
		    
	    }
		
		return null;
		
	}
    
	
	private String retornarRelatorioDescritivo(Integer codigoPedido){
		
		
		PtnResumoPedido ptnResumo = this.persistPatentes.pesquisarResumoDoPedido(codigoPedido);
		if(ptnResumo != null ) {
			
			if(ptnResumo.getTxRelatorDescrit() != null && !"".equals(ptnResumo.getTxRelatorDescrit())){
				
				String relatorioDescritivo = ptnResumo.getTxRelatorDescrit().trim();
				int total = relatorioDescritivo.length();
				int totalMaximo = Integer.parseInt("5000"); 			
				if(total <= totalMaximo){
				     return relatorioDescritivo;
				}else{
					String aux = relatorioDescritivo.substring(0,totalMaximo);
					return aux;
				}
			}else{
				return null;
			}
		    
	    }
		
		return null;
		
	}
	
    
    
    
	/***
	 * 
	 * Identifica se uma patente é uma invenção, utilitário, 
	 * certificado adicional e pedido divido.
	 *  
	 * @param numeroPedido
	 * @param despachoRpiAtual
	 * @return
	 */
	private String retornarPatentTypeId(String numeroPedido){
		
		String retornarTypeId = null;
	   
		if(this.isPedidoDivido){
			retornarTypeId = "DIVISIONAL_PATENT";
		}else{
			String natureza = numeroPedido.substring(0,2);
			retornarTypeId = super.retornarNaturezaPedido(natureza);
		}
	    return retornarTypeId;
	
	}
   
	
	
    
   /**
    * 
    *  Verifica se um determinado pedido possui um despacho '2.4' 
    *  tornando um pedido de patente e uma patente divida.
    *  
    * @return
    */
   private void verificarPedidoDivido(){
	   
	   this.isPedidoDivido = Boolean.FALSE;
	   for(int i = 0; i < this.despachos.size(); i++){
			
			PtnTipoDespacho despacho = this.despachos.get(i);
			if("2.4".equals(despacho.getCodDespachoRpi().trim())){
				this.isPedidoDivido = Boolean.TRUE;
				break;
			}
		}

   }

      
   
   
   /***
    * 
    * Retorna as classificaçoes do pedido na segunda formação.
    * 
    * NumeroPedido.CodigoClassificacao.VersionIndicator;
    * 
    * @param codigoPedido
    * @param numeroPedido
    * @return
    */
   private List<String> retornarClassificacoes(Integer codigoPedido){
	   
	   List<String> classificacoesFormatadas = new ArrayList<String>();
	  
	   List<PtnClassif> classificacoes = this.persistPatentes.pesquisarClassificacoesPedido(codigoPedido);
	   if(classificacoes !=null && !classificacoes.isEmpty()){
		   
		   for (int i = 0; i < classificacoes.size(); i++){
			   
			   StringBuilder  strBuilder =  new StringBuilder();
			   PtnClassif ptnClassif = classificacoes.get(i);
		  	  		  	   
		  	   String codInternacional = ptnClassif.getCodInternacional();
		  	   if(codInternacional != null){
		           strBuilder.append(ptnClassif.getCodInternacional().trim());
		  	   }
		  	   
		  	   String versionIndicator = ptnClassif.getVersionIndicator(); 
			   if(versionIndicator != null){
				   strBuilder.append(" (");
				   strBuilder.append(versionIndicator.trim());
				   strBuilder.append(")");
			   }
			   classificacoesFormatadas.add(strBuilder.toString());
		   }
		   
	   }
	   
	   return classificacoesFormatadas;
	   
    } 
    
    
	/***
	 * 
	 * Retorna o PCT de um pedido de patente, caso exista.
	 * 
	 * @param codigoPedido
	 * @return
	 */
	
	private PtnPct retornarPct(int codigoPedido){
		PtnPct pct = this.persistPatentes.pesquisarPctPedidoPatente(codigoPedido);
	    return pct;
	}
	
	
	
	/**
	 * 
	 * Retorna o status do pedido.
	 * 	
	 * @param codigoPedido
	 * @param tipoPedido
	 * @return
	 */
	private String retornarStatusId(int codigoPedido){
	     
         String statusId = null;
         boolean isNoVigente = Boolean.FALSE;
         
         try{
        	 
        	 Iterator<PtnTipoDespacho> iterator = this.despachos.iterator();
        	 while(iterator.hasNext()){
        		 
        		 PtnTipoDespacho ptnTipoDespacho = iterator.next();
        		 int codigoDespacho = ptnTipoDespacho.getCodigoTipoDespacho();
        		 if(super.isPedidoConcedido(codigoDespacho)){
    	        	 
        			 if(iterator.hasNext()){
        				
        				while(iterator.hasNext()){ 
        					
        					ptnTipoDespacho = iterator.next();
	        				codigoDespacho = ptnTipoDespacho.getCodigoTipoDespacho();
	       					isNoVigente = super.isPedidoNoVigente(codigoDespacho);
	       					if(isNoVigente){
	       						 return statusId = "NO_VIGENTE";
	      					}
	       					
        				}
        				
        				if(!isNoVigente){
        					return statusId = "CONCEDIDA"; 
        				}
        			        				
        			 }else{
        				return statusId = "CONCEDIDA"; 
        			 }
        			    			 
        		 }else if(super.isPedidoNegada(codigoDespacho)){
    		       	return statusId = "NEGADA";        	         	 
        		 }else{
    		      	statusId = "EN_TRAMITE";
    		     }
        		 
        	 }
	             	 
        
         }catch(Exception e){
        	 LOGGER.error(" >> ERRO ao restornar statusId" + e.getMessage());
         }
		 return statusId;
	
	}
	
	private String retornarDespachosDoPedido(int codigoPedido){
	   	PtnTipoDespacho ptnTipoDespacho = this.persistPatentes.pesquisarTipoDespacho(codigoPedido);
		return ptnTipoDespacho.getCodDespachoRpi();
	}
	
	
	
	
   
	private boolean validarPais(String codigoPais){
		
		boolean isValido = Boolean.FALSE;
		for(int i = 0; i < this.paisesValidos.size(); i++){
			
			PtnPaisProsur prosur = this.paisesValidos.get(i);
			if(prosur.getCodPais().equals(codigoPais)){
			    isValido = Boolean.TRUE;
			    break;
			}
			
		}
		return isValido;
	}
	
	
	
	
	
	
	private String formatarTitulo(String titulo){
		
		if(titulo != null && !"".equals(titulo)){
			int totalMaximo = Integer.parseInt("500"); 			
			if(titulo.length() <= totalMaximo){
			     return titulo;
			}else{
				String aux = titulo.substring(0,totalMaximo);
				return aux;
				
			}
		}else{
			return null;
		}
		
	}
		


}
