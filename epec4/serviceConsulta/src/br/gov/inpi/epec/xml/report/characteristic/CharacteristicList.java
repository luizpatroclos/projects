package br.gov.inpi.epec.xml.report.characteristic;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="characteristics-list")
public class CharacteristicList {
	
	
	private List<CharacteristicsRelatedPrior> characteristicsRelatedPrior;

	@XmlElement(name="characteristic")
	public List<CharacteristicsRelatedPrior> getCharacteristicsRelatedPrior() {
		return characteristicsRelatedPrior;
	}

	public void setCharacteristicsRelatedPrior(List<CharacteristicsRelatedPrior> characteristicsRelatedPrior) {
		this.characteristicsRelatedPrior = characteristicsRelatedPrior;
	}

}
