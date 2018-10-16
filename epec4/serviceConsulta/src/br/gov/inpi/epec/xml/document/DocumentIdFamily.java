package br.gov.inpi.epec.xml.document;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="document-id")
@XmlType(propOrder={"idFamily",
		            "status",
		            "number",
		            "inventionTitle"})
public class DocumentIdFamily {
	
	
	private String idFamily;
	
	private String status;
	    	
	private String inventionTitle;

	private String documentIdType;
	
	private String type;
		
	private String number;
		
			
    
	@XmlAttribute(name="document-type-id")
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@XmlElement(name="doc-number")
	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}

	
  	
	/**
	 * @return the documentIdType
	 */
	@XmlAttribute(name="document-id-type")
	public String getDocumentIdType() {
		return documentIdType;
	}


	/**
	 * @param documentIdType the documentIdType to set
	 */
	public void setDocumentIdType(String documentIdType) {
		this.documentIdType = documentIdType;
	}

	

	/**
	 * @return the inventionTitle
	 */
	@XmlElement(name="invention-title")
	public String getInventionTitle() {
		return inventionTitle;
	}


	/**
	 * @param inventionTitle the inventionTitle to set
	 */
	public void setInventionTitle(String inventionTitle) {
		this.inventionTitle = inventionTitle;
	}


	/**
	 * @return the idFamily
	 */
	@XmlElement(name="family-id")
	public String getIdFamily() {
		return idFamily;
	}


	/**
	 * @param idFamily the idFamily to set
	 */
	public void setIdFamily(String idFamily) {
		this.idFamily = idFamily;
	}


	/**
	 * @return the status
	 */
	@XmlElement(name="status")
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	
	
	

}
