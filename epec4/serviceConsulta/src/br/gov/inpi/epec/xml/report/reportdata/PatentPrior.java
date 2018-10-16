package br.gov.inpi.epec.xml.report.reportdata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlRootElement(name="patent-prior")
@XmlType(propOrder={"namePrior",
		            "documentCategory",
		            "relevance",
		            "relations",
		            "relationsClaims"})
public class PatentPrior {
	
	
    private String namePrior;
	
	private String documentCategory;
	
	private String relevance;
	
	private String relations;
	
	private String relationsClaims;
	
	private int sequence;

	/**
	 * @return the namePrior
	 */
	@XmlElement(name="name-prior")
	public String getNamePrior() {
		return namePrior;
	}

	/**
	 * @param namePrior the namePrior to set
	 */
	public void setNamePrior(String namePrior) {
		this.namePrior = namePrior;
	}

	/**
	 * @return the documentCategory
	 */
	@XmlElement(name="document-category")
	public String getDocumentCategory() {
		return documentCategory;
	}

	/**
	 * @param documentCategory the documentCategory to set
	 */
	public void setDocumentCategory(String documentCategory) {
		this.documentCategory = documentCategory;
	}

	/**
	 * @return the relevance
	 */
	@XmlElement(name="relevance")
	public String getRelevance() {
		return relevance;
	}

	/**
	 * @param relevance the relevance to set
	 */
	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	/**
	 * @return the relations
	 */
	@XmlElement(name="relations")
	public String getRelations() {
		return relations;
	}

	/**
	 * @param relations the relations to set
	 */
	public void setRelations(String relations) {
		this.relations = relations;
	}

	/**
	 * @return the relationsClaims
	 */
	@XmlElement(name="relations-claims")
	public String getRelationsClaims() {
		return relationsClaims;
	}

	/**
	 * @param relationsClaims the relationsClaims to set
	 */
	public void setRelationsClaims(String relationsClaims) {
		this.relationsClaims = relationsClaims;
	}

	@XmlAttribute(name="sequence")
	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}
