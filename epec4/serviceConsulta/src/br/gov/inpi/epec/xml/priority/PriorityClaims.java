package br.gov.inpi.epec.xml.priority;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="priority-claims")
public class PriorityClaims {
	
	
    private List<PriorityClaim> priorityClaims;

	
	@XmlElement(name="priority-claim")
	public List<PriorityClaim> getPriorityClaims() {
		return priorityClaims;
	}

	public void setPriorityClaims(List<PriorityClaim> priorityClaims) {
		this.priorityClaims = priorityClaims;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PriorityClaims [priorityClaims=" + priorityClaims + "]";
	}


}
