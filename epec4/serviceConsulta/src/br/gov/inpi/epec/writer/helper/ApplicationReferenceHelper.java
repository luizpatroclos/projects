package br.gov.inpi.epec.writer.helper;

import java.util.ArrayList;
import java.util.List;

import br.gov.inpi.epec.entities.Patente;
import br.gov.inpi.epec.utils.Utils;
import br.gov.inpi.epec.xml.document.DocumentId;
import br.gov.inpi.epec.xml.reference.ApplicationReference;

public final class ApplicationReferenceHelper {
	
    private Utils utils = new Utils();
	
	private static final ApplicationReferenceHelper instance;
	  
	
	static{
	  instance = new ApplicationReferenceHelper();
	}
	
	public static ApplicationReferenceHelper getInstance() {
		return instance;
	}


    public ApplicationReference obterApplicationReference(Patente patente){
		
		ApplicationReference application = new ApplicationReference();
		
		String data = this.utils.converterData(patente.getDataDeposito());
		application.setDataDeposito(data);
		
		List<DocumentId> documentsId = this.obterDadosDoPedido(patente);
		application.setDocumentsId(documentsId);
		
		return application;
		
   }


	
	private List<DocumentId> obterDadosDoPedido(Patente patente){
		
		List<DocumentId> documentsId = new ArrayList<DocumentId>();
		DocumentId documentId = new DocumentId();
		documentId = this.obterApplicationReference(patente,"docdb");
		documentsId.add(documentId);
		
		documentId  = new DocumentId();
		documentId = this.obterApplicationReference(patente,"epodoc");
		documentsId.add(documentId);
		
		documentId  = new DocumentId();
		documentId = this.obterApplicationReference(patente,"original");
		documentsId.add(documentId);
				
		return documentsId;
		
	}
	
	
	private DocumentId obterApplicationReference(Patente patente, String docType){
		
		DocumentId documentId = new DocumentId();
		String numeroPedido = null;
  		
		if("docdb".equals(docType)){
			numeroPedido = this.formatarNumeroDoPedidoDocDb(patente);
			documentId.setNumber(numeroPedido);
			documentId.setType("docdb");
		}else if("epodoc".equals(docType)){
			documentId.setNumber(patente.getNumeroPedidoEpoDoc().trim());
			documentId.setType("epodoc");
	    }else if("original".equals(docType)){
	    	documentId.setNumber(patente.getNumeroPedidoOriginal());
	    	documentId.setType("original");
	    }
	
		return documentId;
	
	}
		
	
	private String formatarNumeroDoPedidoDocDb(Patente patente){
		
		StringBuilder  strBuilder = new StringBuilder();
		strBuilder.append(patente.getPais());
		strBuilder.append(patente.getNumeroAplicacao());
		strBuilder.append(patente.getKindCode());
		
		return strBuilder.toString().trim();
		
	}

}
