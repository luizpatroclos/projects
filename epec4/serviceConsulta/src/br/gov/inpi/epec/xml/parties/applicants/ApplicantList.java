package br.gov.inpi.epec.xml.parties.applicants;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="applicants")
public class ApplicantList {

	
	private List<Applicant> applicants;

	@XmlElement(name="applicant")
	public List<Applicant> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<Applicant> applicants) {
		this.applicants = applicants;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Applicants [applicants=" + applicants + "]";
	}
	
	
}
