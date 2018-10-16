package br.gov.inpi.epec.xml.abstractdoc;

import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlAttribute;   

public class AbstractDoc {
	
	
    private String abstractDoc;
	
	private String lang;
	
	@XmlValue
	public String getAbstractDoc() {
		return abstractDoc;
	}

	public void setAbstractDoc(String abstractDoc) {
		this.abstractDoc = abstractDoc;
	}

	@XmlAttribute(name="lang")
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}


}
