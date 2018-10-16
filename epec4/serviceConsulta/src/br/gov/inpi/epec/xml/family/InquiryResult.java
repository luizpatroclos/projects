package br.gov.inpi.epec.xml.family;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.gov.inpi.epec.xml.reference.ApplicationReference;


@XmlRootElement(name="inquiry-result")
public class InquiryResult {
	
	
    private List<ApplicationReference> references;
	
	
	@XmlElement(name="application-reference")
	public List<ApplicationReference> getReferences() {
		return references;
	}

	public void setReferences(List<ApplicationReference> references) {
		this.references = references;
	}

}
