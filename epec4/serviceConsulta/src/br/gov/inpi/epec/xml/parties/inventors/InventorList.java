package br.gov.inpi.epec.xml.parties.inventors;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="inventors")
public class InventorList {
	
	
    private List<Inventor> inventor;

	
	@XmlElement(name="inventor")
	public List<Inventor> getInventor() {
		return inventor;
	}

	public void setInventor(List<Inventor> inventor) {
		this.inventor = inventor;
	}

	@Override
	public String toString() {
		return "Inventors [inventor=" + inventor + "]";
	}


}
