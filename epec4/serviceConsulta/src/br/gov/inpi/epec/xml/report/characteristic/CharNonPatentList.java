package br.gov.inpi.epec.xml.report.characteristic;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="non-patent-prior-list")
public class CharNonPatentList {
	
	
    private List<NonPatentPriorCharacteristics> nonPatentPriorCharacteristics;

	
	@XmlElement(name="non-patent-prior-characteristics")
	public List<NonPatentPriorCharacteristics> getNonPatentPriorCharacteristics() {
		return nonPatentPriorCharacteristics;
	}

	public void setNonPatentPriorCharacteristics(List<NonPatentPriorCharacteristics> nonPatentPriorCharacteristics) {
		this.nonPatentPriorCharacteristics = nonPatentPriorCharacteristics;
	}

}
