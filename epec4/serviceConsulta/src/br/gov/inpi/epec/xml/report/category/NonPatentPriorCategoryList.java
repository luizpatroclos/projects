package br.gov.inpi.epec.xml.report.category;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="non-patent-prior-category-list")
public class NonPatentPriorCategoryList {
	
    private List<NonPatentPriorCategory> nonPatentPriorCategories;

	
	@XmlElement(name="non-patent-prior-category")
	public List<NonPatentPriorCategory> getNonPatentPriorCategories() {
		return nonPatentPriorCategories;
	}

	public void setNonPatentPriorCategories(
			List<NonPatentPriorCategory> nonPatentPriorCategories) {
		this.nonPatentPriorCategories = nonPatentPriorCategories;
	}

}
