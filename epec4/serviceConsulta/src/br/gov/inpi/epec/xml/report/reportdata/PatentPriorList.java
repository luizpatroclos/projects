package br.gov.inpi.epec.xml.report.reportdata;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="patent-prior-list")
public class PatentPriorList {
	
	
	private List<PatentPrior> anterioridades;

	@XmlElement(name="patent-prior")
	public List<PatentPrior> getAnterioridades() {
		return anterioridades;
	}

	public void setAnterioridades(List<PatentPrior> anterioridades) {
		this.anterioridades = anterioridades;
	}


}
