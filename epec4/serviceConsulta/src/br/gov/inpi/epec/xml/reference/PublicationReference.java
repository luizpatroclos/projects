package br.gov.inpi.epec.xml.reference;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.gov.inpi.epec.xml.document.DocumentId;




@XmlRootElement(name="publication-reference")
@XmlType(propOrder={"dataPublicacao","documentsId"})
public class PublicationReference {

    private String dataPublicacao;
	
	private List<DocumentId> documentsId;

	@XmlElement(name="document-id")
	public List<DocumentId> getDocumentsId() {
		return documentsId;
	}

	public void setDocumentsId(List<DocumentId> documentsId) {
		this.documentsId = documentsId;
	}

	
	@XmlElement(name="date")
	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	
	
}
