package br.gov.inpi.epec.xml.report.category;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="category-list")
public class CategoryList {
	
	
	private List<Category> categorias;

    @XmlElement(name="category")
	public List<Category> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Category> categorias) {
		this.categorias = categorias;
	}

	

}
