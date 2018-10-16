package br.gov.inpi.epec.xml.reference;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.gov.inpi.epec.xml.document.DocumentId;


@XmlRootElement(name="application-reference")
@XmlType(propOrder={"dataDeposito",
		            "documentsId",
		            "documentId"})
public class ApplicationReference {

	

	private String dataDeposito;
	
	
	private List<DocumentId> documentsId;
	
	
	private DocumentId documentId;
	

	@XmlElement(name="document-id")
	public List<DocumentId> getDocumentsId() {
		return documentsId;
	}

	
	public void setDocumentsId(List<DocumentId> documentsId) {
		this.documentsId = documentsId;
	}

	@XmlElement(name="date")
	public String getDataDeposito() {
		return dataDeposito;
	}


	public void setDataDeposito(String dataDeposito) {
		this.dataDeposito = dataDeposito;
	}

	
	public void setDocumentId(DocumentId documentId) {
		this.documentId = documentId;
	}


	@XmlElement(name="document-id")
	public DocumentId getDocumentId() {
		return documentId;
	}

	
}
