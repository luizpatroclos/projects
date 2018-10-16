package br.gov.inpi.epec.xml.ipc;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="classification-list")
public class ClassificationList {

	
	private List<Classification> classifications;

	
	@XmlElement(name="classification")
	public List<Classification> getClassifications() {
		return classifications;
	}


	public void setClassifications(List<Classification> classifications) {
		this.classifications = classifications;
	}


	
	@Override
	public String toString() {
		return "ClassificationList [classifications=" + classifications + "]";
	}
	
	
	
	
}

