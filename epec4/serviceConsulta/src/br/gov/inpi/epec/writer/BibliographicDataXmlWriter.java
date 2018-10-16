package br.gov.inpi.epec.writer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.gov.inpi.epec.entities.ClassificacaoInternacional;
import br.gov.inpi.epec.entities.Patente;
import br.gov.inpi.epec.entities.Pessoa;
import br.gov.inpi.epec.entities.Prioridade;
import br.gov.inpi.epec.utils.Utils;
import br.gov.inpi.epec.writer.helper.ApplicationReferenceHelper;
import br.gov.inpi.epec.writer.helper.PartiesHelper;
import br.gov.inpi.epec.writer.helper.PublicationReferenceHelper;
import br.gov.inpi.epec.xml.abstractdoc.AbstractDoc;
import br.gov.inpi.epec.xml.bibliographicdata.BibliographicData;
import br.gov.inpi.epec.xml.ipc.Classification;
import br.gov.inpi.epec.xml.ipc.ClassificationList;
import br.gov.inpi.epec.xml.parties.Parties;
import br.gov.inpi.epec.xml.priority.PriorityClaim;
import br.gov.inpi.epec.xml.priority.PriorityClaims;
import br.gov.inpi.epec.xml.reference.ApplicationReference;
import br.gov.inpi.epec.xml.reference.PublicationReference;

/**
 * 
 * Classe responsável em criar o Xml referente aos dados
 * bibliograficos de uma patente.
 * 
 * @author allanlq
 *
 */
public class BibliographicDataXmlWriter {
	  
        private Utils utils = new Utils();
	
			
	    private String typeDocumentFormat;
		
	    

		public String getTypeDocumentFormat() {
			return typeDocumentFormat;
		}


		public void setTypeDocumentFormat(String typeDocumentFormat) {
			this.typeDocumentFormat = typeDocumentFormat;
		} 
	
		
		
		public BibliographicData converterPatenteDocDbParaXml(Patente patente){
	    	this.setTypeDocumentFormat("docdb");
			BibliographicData biblio = this.converterPatenteParaXml(patente);
			return biblio;
		}
		
		
		
		
		public BibliographicData converterPatenteEpodocParaXml(Patente patente) {
            this.setTypeDocumentFormat("epodoc");
	        BibliographicData biblio = this.converterPatenteParaXml(patente);
			return biblio;
		}
		
		
		
		public BibliographicData converterPatenteOriginalParaXml(Patente patente){
	    	this.setTypeDocumentFormat("original");
			BibliographicData biblio = this.converterPatenteParaXml(patente);
			return biblio;
		}
		
		
		
		private BibliographicData converterPatenteParaXml(Patente patente){
			
			BibliographicData biblio = new BibliographicData();
		    this.setTypeDocumentFormat(patente.getTipoDocumento());
		    
			biblio.setNumber(patente.getNumero());
			String title = patente.getTitulo();
			if(title != null && !"".equals(title.trim())){
				biblio.setInventionTitle(title.trim());
			}
			
			String revindicacao = patente.getRevindicacao();
			if(revindicacao != null && !"".equals(revindicacao)){
				biblio.setClaimsNumber(revindicacao.trim());
			}else{
				biblio.setClaimsNumber("");
			}
				
			String nomeProcurador = this.retornarProcurador(patente.getProcurador());
			if(nomeProcurador != null){
				biblio.setRepresentative(nomeProcurador.trim());
			}
		
			ClassificationList classificationList = this.retornarClassificacoes(patente.getClassificacoes());
			if(classificationList != null){
			   biblio.setClassification(classificationList);
			}
			
			ApplicationReference applicationReference = this.obterApplicationReference(patente);
			biblio.setApplicationReference(applicationReference);

			PublicationReference publication = this.obterPublicationReferenceDocDb(patente);
		    biblio.setPublicationReference(publication);
			
		    PriorityClaims priorityClaims = this.retornarPrioridade(patente.getPrioridades());
		    if(priorityClaims != null){
		       biblio.setPriorityClaims(priorityClaims);
		    }
		    	    
		    AbstractDoc abstractDoc = this.obterResumoDoPedido(patente.getResumo(),patente.getPais());
		    biblio.setAbstractDoc(abstractDoc);
		    
		    Parties parties = PartiesHelper.getInstance().retornarInteressados(patente);
		    biblio.setParties(parties);
			    
			return biblio;
			
		}
		

		private AbstractDoc obterResumoDoPedido(String resumo, String paisOrigem){
			
			AbstractDoc abstractDoc = new AbstractDoc();
			if(resumo != null && !"".equals(resumo.trim())){
				
				abstractDoc.setLang(paisOrigem.trim());
				abstractDoc.setAbstractDoc(resumo.trim());
				
			}else{
				abstractDoc.setAbstractDoc("");
			}
				
			return abstractDoc;
			
		}
		
		
	   
	   private String retornarProcurador(Pessoa procurador){
		   String nome = procurador.getNome();
		   if(nome != null && !"".equals(nome.trim()) && !"-".equals(nome.trim())){
			   return nome.trim();		   		  
		   }else{
			   return "";
		   }
		   
	   }
	   
	   
	   
	   private ClassificationList retornarClassificacoes(List<ClassificacaoInternacional> classificacoes){
		   
		   
		   ClassificationList classificationsList = new ClassificationList();
		   List<Classification> classifications = null;
		   if(classificacoes != null && !classificacoes.isEmpty()){
			  		  
			   classifications = new ArrayList<Classification>();
			   for(int i = 0; i < classificacoes.size(); i++){
			        
				   ClassificacaoInternacional cip = classificacoes.get(i);
				   Classification classification = new Classification();
				   classification.setSequence(cip.getOrdem());
				   classification.setClassificacaoInternacional(cip.getClassificacaoInternacional());
				   classifications.add(classification);
				   
			   }
			   
		   }
		   classificationsList.setClassifications(classifications);
		   return classificationsList;
		   	   
	   }
	   
		
	   
	    // Publication Reference ----------------------------------------------------------------------
		private PublicationReference obterPublicationReferenceDocDb(Patente patente){
			
			PublicationReference publication = new PublicationReference();
			publication = PublicationReferenceHelper.getInstance().obterPublicationReferenceDocDb(patente);
			return publication;
			
		}
		//--------------------------------------------------------------------------------------
		
		
		
		// Application Reference ---------------------------------------------------------------
		private ApplicationReference obterApplicationReference(Patente patente){
			
			ApplicationReference application = new ApplicationReference();
			application = ApplicationReferenceHelper.getInstance().obterApplicationReference(patente);
			return application;
			
	   }
	  // Fim ApplicationReference ----------------------------------------------------------------
		
		
		
		
	   private PriorityClaims retornarPrioridade(List<Prioridade> prioridades){
			
			PriorityClaims priorityClaimsRoot = new PriorityClaims();
			
			if(prioridades != null && !prioridades.isEmpty()){
				
				int i = 1;
				List<PriorityClaim> prioritiesClaims = new ArrayList<PriorityClaim>();
				Iterator<Prioridade> it = prioridades.iterator();
				while(it.hasNext()){
					
					Prioridade prioridade = it.next();
					
					PriorityClaim claim = new PriorityClaim();
					claim.setDate(this.utils.converterData(prioridade.getDataDeposito()));
					claim.setDocNumber(prioridade.getNumero());
		            claim.setSequence(Integer.toString(i));
					prioritiesClaims.add(claim);
		            i++;
		            
				}
				priorityClaimsRoot.setPriorityClaims(prioritiesClaims);
			}
			return priorityClaimsRoot;
			
		}
	
	
}
