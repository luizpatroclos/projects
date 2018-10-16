package br.gov.inpi.epec.xml.family;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="inquiry-list")
public class InquiryResultList {

    private InquiryResult inquiryResult;

	
	@XmlElement(name="inquiry-result")
	public InquiryResult getInquiryResult() {
		return inquiryResult;
	}

	public void setInquiryResult(InquiryResult inquiryResult) {
		this.inquiryResult = inquiryResult;
	}
	
	
}
