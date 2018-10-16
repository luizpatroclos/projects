package br.gov.inpi.epec.xml.report.category;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="patent-prior-category-list")
public class PatentPriorCategoryList {
	
    
    private List<PatentPriorCategory> patentPriorCategories;

	
	@XmlElement(name="patent-prior-category")
	public List<PatentPriorCategory> getPatentPriorCategories() {
		return patentPriorCategories;
	}

	public void setPatentPriorCategories(
			List<PatentPriorCategory> patentPriorCategories) {
		this.patentPriorCategories = patentPriorCategories;
	}
	

}
