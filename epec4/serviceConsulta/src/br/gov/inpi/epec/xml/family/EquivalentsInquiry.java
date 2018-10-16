package br.gov.inpi.epec.xml.family;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.gov.inpi.epec.xml.reference.ApplicationReference;

@XmlRootElement(name="equivalents-inquiry")
public class EquivalentsInquiry {
	
	
    private ApplicationReference applicationReference; 

	
	private InquiryResultList inquiryResultList;


	@XmlElement(name="application-reference")
	public ApplicationReference getApplicationReference() {
		return applicationReference;
	}



	public void setApplicationReference(ApplicationReference applicationReference) {
		this.applicationReference = applicationReference;
	}

	@XmlElement(name="inquiry-list")
	public InquiryResultList getInquiryResultList() {
		return inquiryResultList;
	}


	public void setInquiryResultList(InquiryResultList inquiryResultList) {
		this.inquiryResultList = inquiryResultList;
	}


}
