package br.gov.inpi.epec.xml.report.category;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.gov.inpi.epec.xml.report.characteristic.CharacteristicList;


@XmlRootElement(name="category")
@XmlType(propOrder={"categoryName",
		            "categorySumary",
		            "patentPriorCategoryList",
		            "nonPatentPriorCategoryList",
		            "characteristics"})
public class Category {
	
	
    private String categoryName;
	
	
	private String categorySumary;
	
	
	private PatentPriorCategoryList patentPriorCategoryList;
	
	
	private NonPatentPriorCategoryList nonPatentPriorCategoryList;
	
	
	private CharacteristicList characteristics;
	
	
	private int sequence;
	
	
	
	/**
	 * @return the categoryName
	 */
	@XmlElement(name="category-name")
	public String getCategoryName() {
		return categoryName;
	}


	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	/**
	 * @return the categorySumary
	 */
	@XmlElement(name="category-summary")
	public String getCategorySumary() {
		return categorySumary;
	}


	/**
	 * @param categorySumary the categorySumary to set
	 */
	public void setCategorySumary(String categorySumary) {
		this.categorySumary = categorySumary;
	}


	
    @XmlElement(name="patent-prior-category-list")
	public PatentPriorCategoryList getPatentPriorCategoryList() {
		return patentPriorCategoryList;
	}


	public void setPatentPriorCategoryList(
			PatentPriorCategoryList patentPriorCategoryList) {
		this.patentPriorCategoryList = patentPriorCategoryList;
	}


	@XmlElement(name="non-patent-category-prior-list")
	public NonPatentPriorCategoryList getNonPatentPriorCategoryList() {
		return nonPatentPriorCategoryList;
	}


	public void setNonPatentPriorCategoryList(
			NonPatentPriorCategoryList nonPatentPriorCategoryList) {
		this.nonPatentPriorCategoryList = nonPatentPriorCategoryList;
	}


	@XmlAttribute(name="sequence")
	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}


	@XmlElement(name="characteristic-list")
	public CharacteristicList getCharacteristics() {
		return characteristics;
	}


	public void setCharacteristics(CharacteristicList characteristics) {
		this.characteristics = characteristics;
	}


}
