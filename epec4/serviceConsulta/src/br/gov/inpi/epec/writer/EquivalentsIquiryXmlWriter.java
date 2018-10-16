package br.gov.inpi.epec.writer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.gov.inpi.epec.entities.Familia;
import br.gov.inpi.epec.entities.Patente;
import br.gov.inpi.epec.utils.Utils;
import br.gov.inpi.epec.xml.document.DocumentId;
import br.gov.inpi.epec.xml.family.EquivalentsInquiry;
import br.gov.inpi.epec.xml.family.InquiryResult;
import br.gov.inpi.epec.xml.family.InquiryResultList;
import br.gov.inpi.epec.xml.reference.ApplicationReference;

/***
 * 
 * Classe respons�vel em escrever o Xml referente a Fam�lia de 
 * Documentos de Patente.
 * 
 * @author allanlq
 *
 */
public class EquivalentsIquiryXmlWriter {
	
	
	private Utils utils = new Utils();
	
	private String tipoPesquisa; 
	
	
	private String tipoDocType;
	
		
	public String getTipoDocType() {
		return tipoDocType;
	}


	public void setTipoDocType(String tipoDocType) {
		this.tipoDocType = tipoDocType;
	}
	
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}


	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}


	public EquivalentsInquiry converterFamilia(List<Familia> familiaDocumentos, String numeroPedidoBase){
		
		EquivalentsInquiry equivalentsInquiry = null;
	 	for(int i = 0; i < familiaDocumentos.size(); i++){
			
	 		equivalentsInquiry = new EquivalentsInquiry();
			Familia familia = familiaDocumentos.get(i);
			Patente patente = familia.getPatente();
		
			if("epodoc".equals(this.getTipoDocType())){
				
				if("application".equals(this.getTipoPesquisa())){
				    
					if(numeroPedidoBase.equals(patente.getNumeroPedidoEpoDoc())){
						ApplicationReference applicationReference = this.obterApplicationReference(familia,true);
					    equivalentsInquiry.setApplicationReference(applicationReference);
					}
				}else if("publication".equals(this.getTipoPesquisa())){
					
					String numeroPublicacao = patente.getPaisPublicacao()+patente.getNumeroPublicacao();
					if(numeroPedidoBase.equals(numeroPublicacao)){
						ApplicationReference applicationReference = this.obterApplicationReference(familia,true);
					    equivalentsInquiry.setApplicationReference(applicationReference);
					}
					
				}
						
				
			}else if("original".equals(this.getTipoDocType())){
				
				String numOriginal = patente.getNumeroPedidoOriginal();
				if(numOriginal != null && !"".equals(numOriginal)){
				if(numeroPedidoBase.equals(numOriginal.trim())){
				    ApplicationReference applicationReference = this.obterApplicationReference(familia,true);
					equivalentsInquiry.setApplicationReference(applicationReference);
				}
				} 
				
            }else if("docdb".equals(this.getTipoDocType())){
            	
            	if(numeroPedidoBase.equals(patente.getNumeroPublicacao())){
				    ApplicationReference applicationReference = this.obterApplicationReference(familia,true);
					equivalentsInquiry.setApplicationReference(applicationReference);
				}

		   		    
		    }
			 	
	 	}
	 	
	 	InquiryResultList inquiryResultList = this.obterInquiryResultList(familiaDocumentos);
	    equivalentsInquiry.setInquiryResultList(inquiryResultList);
	 	return equivalentsInquiry;
	 	
	}
	 	
	 	
	 	public EquivalentsInquiry converterFamiliaPublicationEpoDoc(List<Familia> familiaDocumentos, String numeroBase){
			
			EquivalentsInquiry equivalentsInquiry = new EquivalentsInquiry();
		 	for(int i = 0; i < familiaDocumentos.size(); i++){
				
				Familia familia = familiaDocumentos.get(i);
				Patente patente = familia.getPatente();

				String publicacao = patente.getPaisPublicacao().trim()+patente.getNumeroPublicacao().trim();
                
				if(numeroBase.equals(publicacao.trim())){
				  ApplicationReference applicationReference = this.obterApplicationReference(familia,true);
				  equivalentsInquiry.setApplicationReference(applicationReference);
				}
					    
		   }
	 		
	       InquiryResultList inquiryResultList = this.obterInquiryResultList(familiaDocumentos);
	       equivalentsInquiry.setInquiryResultList(inquiryResultList);
	   
	   return equivalentsInquiry;

	}
	
    
	private InquiryResultList obterInquiryResultList(List<Familia> familiaDocumentos){
    	
    	InquiryResultList inquiryResultList = new InquiryResultList();
    	InquiryResult inquiryResult = this.obterFamiliaDocumentoBase(familiaDocumentos);
    	
    	if(inquiryResult != null){
    		 inquiryResultList.setInquiryResult(inquiryResult);
    	}
    	
    	return inquiryResultList;
    	
    }
	
	
	
	private InquiryResult obterFamiliaDocumentoBase(List<Familia> familiaDocumentos){
		
		InquiryResult inquiryResult = new InquiryResult();
		List<ApplicationReference> references = new ArrayList<ApplicationReference>();
		for(int i = 0; i < familiaDocumentos.size(); i++){
			
			Familia familia = familiaDocumentos.get(i);
		    ApplicationReference applicationReference = this.obterApplicationReference(familia,false);
			references.add(applicationReference);
			
		}
		
		inquiryResult.setReferences(references);
		return inquiryResult;
				
	}
	
	
	
	
	private ApplicationReference obterApplicationReference(Familia familia, boolean isNumeroBase){
		
		 ApplicationReference applicationReference = new ApplicationReference();
		 DocumentId documentId  = new DocumentId();
		 documentId.setType("epodoc"); 
		 
		 if(isNumeroBase){
    		documentId.setStatus(familia.getPublico());
			documentId.setIdFamily(familia.getId());
		 }else{
			 documentId.setNumber(familia.getPatente().getNumeroPedidoEpoDoc());
		     documentId.setInventionTitle(familia.getPatente().getTitulo());
		     documentId.setApplicationDate(this.converterData(familia.getPatente().getDataDeposito()));
		     documentId.setPublicationDate(this.converterData(familia.getPatente().getDataPublicacao()));
	
		 }
		 
		 applicationReference.setDocumentId(documentId);
		 return applicationReference;
		
	}

	
	private String converterData(Date date){
		String dataFormatada = this.utils.converterData(date);
		return dataFormatada;
	}
	
	

}
