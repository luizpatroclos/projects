package br.gov.inpi.epec.xml.parties.inventors;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Inventor {

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

	@XmlElement(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Inventor [sequence=" + sequence + ", dataFormat=" + dataFormat
				+ ", name=" + name + "]";
	}

	
	
	
	
}
