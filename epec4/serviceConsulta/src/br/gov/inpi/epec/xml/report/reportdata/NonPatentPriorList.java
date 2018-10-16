package br.gov.inpi.epec.xml.report.reportdata;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="non-patent-prior-list")
public class NonPatentPriorList {
	
	private List<NonPatentPrior> listaAnterioridadesNaoPatentarias;

	@XmlElement(name="non-patent-prior")
	public List<NonPatentPrior> getListaAnterioridadesNaoPatentarias() {
		return listaAnterioridadesNaoPatentarias;
	}

	public void setListaAnterioridadesNaoPatentarias(List<NonPatentPrior> listaAnterioridadesNaoPatentarias) {
		this.listaAnterioridadesNaoPatentarias = listaAnterioridadesNaoPatentarias;
	}

	

}
