package br.gov.inpi.epec.xml.document;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="document-id")
@XmlType(propOrder={"country",
		"number",
        "kind",
        "date",
        "idFamily",
        "status",
        "inventionTitle",
        "applicationDate",
        "publicationDate"})
public class DocumentId {
	
	 
	private String type;
		
		
	private String number;
		
	
	private String country;
	
	
	private String date;

	
	private String kind;
	
		
    private String idFamily;
	

	private String status;
	
	
	private String inventionTitle;
	
	/**
	 * Objeto utilizado nos xml's de Familia.
	 * 
	 */
	private String applicationDate;
	
	/**
	 * Objeto utilizado nos xml's de Familia.
	 * 
	 */
	private String publicationDate;
	
	
	@XmlElement(name="application-date")
	public String getApplicationDate() {
		return applicationDate;
	}


	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}


	@XmlElement(name="publication-date")
	public String getPublicationDate() {
		return publicationDate;
	}


	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
    
	@XmlAttribute(name="document-id-type")
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

	
    @XmlElement(name="country")
	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	@XmlElement(name="date")
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	@XmlElement(name="kind")
	public String getKind() {
		return kind;
	}


	public void setKind(String kind) {
		this.kind = kind;
	}


	/**
	 * @return the idFamily
	 */
	@XmlElement(name="id")
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



	
	
	
	

}

