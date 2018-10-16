package br.gov.inpi.epec.xml.parties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.gov.inpi.epec.xml.parties.applicants.ApplicantList;
import br.gov.inpi.epec.xml.parties.inventors.InventorList;



@XmlRootElement(name="parties")
@XmlType(propOrder={"applicantList",
                    "inventorList"})
public class Parties {
	
	
	private InventorList inventorList;
	
	
	private ApplicantList applicantList;


	@XmlElement(name="inventors")
	public InventorList getInventorList() {
		return inventorList;
	}


	public void setInventorList(InventorList inventorList) {
		this.inventorList = inventorList;
	}

	@XmlElement(name="applicants")
	public ApplicantList getApplicantList() {
		return applicantList;
	}


	public void setApplicantList(ApplicantList applicantList) {
		this.applicantList = applicantList;
	}


	@Override
	public String toString() {
		return "Parties [inventorList=" + inventorList + ", applicantList="
				+ applicantList + "]";
	}
	
	
	
	
	
	

}
