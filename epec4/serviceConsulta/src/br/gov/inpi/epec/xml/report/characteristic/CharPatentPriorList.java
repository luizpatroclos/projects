package br.gov.inpi.epec.xml.report.characteristic;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="patent-prior-list-characteristic")
public class CharPatentPriorList {
	
	
    private List<PatentPriorCharacteristics> patentPriorCharacteristics;

	
	@XmlElement(name="patent-prior-characteristics")
	public List<PatentPriorCharacteristics> getPatentPriorCharacteristics() {
		return patentPriorCharacteristics;
	}

	public void setPatentPriorCharacteristics(
			List<PatentPriorCharacteristics> patentPriorCharacteristics) {
		this.patentPriorCharacteristics = patentPriorCharacteristics;
	}
	

}
