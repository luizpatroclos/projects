package br.gov.inpi.epec.xml.priority;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="priority-claim")
public class PriorityClaim {

	
    private String sequence;
	
	private String docNumber;
	
	private String date;

	
	@XmlAttribute(name="sequence")
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}


	@XmlElement(name="doc-number")
	public String getDocNumber() {
		return docNumber;
	}


	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	
	@XmlElement(name="date")
	public String getDate() {
		return date;
	}

	
	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "PriorityClaim [sequence=" + sequence + ", docNumber="
				+ docNumber + ", date=" + date + "]";
	}

	
	
	
}
