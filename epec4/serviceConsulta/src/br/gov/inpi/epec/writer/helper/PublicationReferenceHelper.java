package br.gov.inpi.epec.writer.helper;

import java.util.ArrayList;
import java.util.List;

import br.gov.inpi.epec.entities.Patente;
import br.gov.inpi.epec.utils.Utils;
import br.gov.inpi.epec.xml.document.DocumentId;
import br.gov.inpi.epec.xml.reference.PublicationReference;



/***
 * 
 * Classe Helper responsável em montar o xml referente ao elemento PublicationReference 
 * 
 * @author allanlq
 *
 */
public final class PublicationReferenceHelper {
	
   private Utils utils = new Utils();
	
   private static final PublicationReferenceHelper instance;
   
   
   static{
	   instance = new PublicationReferenceHelper();
   }
	

	public static PublicationReferenceHelper getInstance() {
		return instance;
	}



   public PublicationReference obterPublicationReferenceDocDb(Patente patente){
		
		PublicationReference publication = new PublicationReference();
		
		String dataFormatada = utils.converterData(patente.getDataPublicacao());
		publication.setDataPublicacao(dataFormatada);
		
		List<DocumentId> documentsId = this.obterDocumentosPublicacao(patente);
		publication.setDocumentsId(documentsId);

		return publication;
		
	}
	
	
	
	private List<DocumentId> obterDocumentosPublicacao(Patente patente){
		
		List<DocumentId> documentsId = new ArrayList<DocumentId>();
		DocumentId documentId = new DocumentId();
		documentId = this.obterDocumentId(patente,"docdb");
		documentsId.add(documentId);
		
		documentId  = new DocumentId();
		documentId = this.obterDocumentId(patente,"epodoc");
		documentsId.add(documentId);
		
		return documentsId;
		
	}
		
	
	private DocumentId obterDocumentId(Patente patente, String docType){
		
		DocumentId documentId = new DocumentId();
		String numeroPedido = null;
		if("docdb".equals(docType)){
			numeroPedido =  this.obterDocDb(patente);
			documentId.setNumber(numeroPedido);
			documentId.setType("docdb");
		}else if("epodoc".equals(docType)){
		    numeroPedido = this.obterEpoDoc(patente);
		    documentId.setNumber(numeroPedido);
		    documentId.setType("epodoc");
		}
		
		return documentId;
		
	}
		
	
	private String obterDocDb(Patente patente){
		
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(patente.getPaisPublicacao());
		strBuilder.append(patente.getNumeroPublicacao());
		strBuilder.append(patente.getKindCodePublicacao());
		
		return strBuilder.toString().trim();
		
	}
	
	
	
	private String obterEpoDoc(Patente patente){
		
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(patente.getPaisPublicacao());
		strBuilder.append(patente.getNumeroPublicacao());
		
		return strBuilder.toString().trim();
	
	}

}
