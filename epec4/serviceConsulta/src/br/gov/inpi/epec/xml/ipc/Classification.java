package br.gov.inpi.epec.xml.ipc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="classification")
public class Classification {
		
	
	private long sequence;
		
	
	private String classificacaoInternacional;

	
	/**
	 * @return the sequence
	 */
	@XmlAttribute(name="sequence")
	public long getSequence() {
		return sequence;
	}


	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}


	/**
	 * @return the classificacaoInternacional
	 */
	@XmlElement(name="classification-name")
	public String getClassificacaoInternacional() {
		return classificacaoInternacional;
	}


	/**
	 * @param classificacaoInternacional the classificacaoInternacional to set
	 */
	public void setClassificacaoInternacional(String classificacaoInternacional) {
		this.classificacaoInternacional = classificacaoInternacional;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Classification [sequence=" + sequence
				+ ", classificacaoInternacional=" + classificacaoInternacional
				+ "]";
	}
	
	
	

}

