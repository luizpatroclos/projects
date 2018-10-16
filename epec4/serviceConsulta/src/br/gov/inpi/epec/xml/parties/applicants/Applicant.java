package br.gov.inpi.epec.xml.parties.applicants;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="applicant")
public class Applicant {
	
    private String sequence;
	
	private String dataFormat;
	
	private String name;

	
	@XmlAttribute(name="sequence")
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	
    @XmlAttribute(name="data-format")
	public String getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
