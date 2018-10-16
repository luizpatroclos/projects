package br.gov.inpi.epec.xml.bibliographicdata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.gov.inpi.epec.xml.abstractdoc.AbstractDoc;
import br.gov.inpi.epec.xml.ipc.ClassificationList;
import br.gov.inpi.epec.xml.parties.Parties;
import br.gov.inpi.epec.xml.priority.PriorityClaims;
import br.gov.inpi.epec.xml.reference.ApplicationReference;
import br.gov.inpi.epec.xml.reference.PublicationReference;




/***
 * 
 * Classe responsável em encaspsular os dados do Xml reference aos
 * dados bibliograficos - 
 * 
 * 
 * @author allanlq
 *
 */

@XmlRootElement(name="bibliographic-data")
@XmlType(propOrder={"number",
					"inventionTitle",             
					"claimsNumber",
		            "representative",
		            "applicationReference",
		            "publicationReference",
		            "classification",
		            "priorityClaims",
		            "parties",
		            "abstractDoc"})
public class BibliographicData {
	
	
    private String identificador;
	
	private String number;
	
	private String claimsNumber;
	
	private String representative;
				
	private PublicationReference publicationReference;
		
	private ApplicationReference applicationReference;

	private Parties parties;
		
	private String inventionTitle;
	
	private AbstractDoc abstractDoc;
	
	private PriorityClaims priorityClaims;
		
	private ClassificationList classification;
		
	//private XmlError xmlError;
	
	


	@XmlAttribute(name="id")
	public String getIdentificador() {
		return identificador;
	}


	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}


	@XmlElement(name="publication-reference")
	public PublicationReference getPublicationReference() {
		return publicationReference;
	}
	

	public void setPublicationReference(PublicationReference publicationReference) {
		this.publicationReference = publicationReference;
	}

	
	@XmlElement(name="application-reference")
	public ApplicationReference getApplicationReference() {
		return applicationReference;
	}

	
	public void setApplicationReference(ApplicationReference applicationReference) {
		this.applicationReference = applicationReference;
	}

    
	@XmlElement(name="parties")
	public Parties getParties() {
		return parties;
	}


	public void setParties(Parties parties) {
		this.parties = parties;
	}


	@XmlElement(name="title")
	public String getInventionTitle() {
		return inventionTitle;
	}


	public void setInventionTitle(String inventionTitle) {
		this.inventionTitle = inventionTitle;
	}

	
	@XmlElement(name="abstract")
	public AbstractDoc getAbstractDoc() {
		return abstractDoc;
	}


	public void setAbstractDoc(AbstractDoc abstractDoc) {
		this.abstractDoc = abstractDoc;
	}


	/**
	 * @return the priorityClaims
	 */
	@XmlElement(name="priority-claims")
	public PriorityClaims getPriorityClaims() {
		return priorityClaims;
	}


	/**
	 * @param priorityClaims the priorityClaims to set
	 */
	public void setPriorityClaims(PriorityClaims priorityClaims) {
		this.priorityClaims = priorityClaims;
	}
	

	/**
	 * @return the number
	 */
	@XmlElement(name="number")
	public String getNumber() {
		return number;
	}


	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}


	@XmlElement(name="representative")
	public String getRepresentative() {
		return representative;
	}


	/**
	 * @param representative the representative to set
	 */
	public void setRepresentative(String representative) {
		this.representative = representative;
	}


	/**
	 * @return the classification
	 */
	@XmlElement(name="classification-list")
	public ClassificationList getClassification() {
		return classification;
	}


	/**
	 * @param classification the classification to set
	 */
	public void setClassification(ClassificationList classification) {
		this.classification = classification;
	}


	/**
	 * @return the claimsNumber
	 */
	 @XmlElement(name="claims-number")
	public String getClaimsNumber() {
		return claimsNumber;
	}


	/**
	 * @param claimsNumber the claimsNumber to set
	 */
	public void setClaimsNumber(String claimsNumber) {
		this.claimsNumber = claimsNumber;
	}


/*	*//**
	 * @return the xmlErro
	 *//*
	@XmlElement(name="xmlErro")
	public XmlError getXmlError() {
		return xmlError;
	}


	*//**
	 * @param xmlErro the xmlErro to set
	 *//*
	public void setXmlError(XmlError xmlError) {
		this.xmlError = xmlError;
	}
*/

	

	
	
	

}
