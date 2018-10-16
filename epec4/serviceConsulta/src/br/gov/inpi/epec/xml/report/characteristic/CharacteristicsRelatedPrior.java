package br.gov.inpi.epec.xml.report.characteristic;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlRootElement(name="characteristic")
@XmlType(propOrder={"characteristicsName",
		            "patentPriorList",
		            "charNonPatentList"}) 
public class CharacteristicsRelatedPrior {
	
    private int sequence;
	
	private String characteristicsName;
	
	
	private CharPatentPriorList patentPriorList;
	

	private CharNonPatentList charNonPatentList;

	/**
	 * @return the characteristicsName
	 */
	@XmlElement(name="characteristic-name")
	public String getCharacteristicsName() {
		return characteristicsName;
	}

	/**
	 * @param characteristicsName the characteristicsName to set
	 */
	public void setCharacteristicsName(String characteristicsName) {
		this.characteristicsName = characteristicsName;
	}



	@XmlAttribute(name="sequence")
	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	
	@XmlElement(name="patent-prior-list")
	public CharPatentPriorList getPatentPriorList() {
		return patentPriorList;
	}

	public void setPatentPriorList(CharPatentPriorList patentPriorList) {
		this.patentPriorList = patentPriorList;
	}
	
	
    @XmlElement(name="non-patent-prior-list")
	public CharNonPatentList getCharNonPatentList() {
		return charNonPatentList;
	}

	public void setCharNonPatentList(CharNonPatentList charNonPatentList) {
		this.charNonPatentList = charNonPatentList;
	}

}
