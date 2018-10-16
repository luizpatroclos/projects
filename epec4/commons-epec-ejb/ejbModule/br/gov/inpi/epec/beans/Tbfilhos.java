/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author lasilva
 */
@Entity
@Table(name = "TBFILHOS")
@XmlRootElement
public class Tbfilhos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_PAI")
	private Long idPai;

	@Id
	@Column(name = "ID_FILHO")
	private Long idFilho;

	public Long getIdPai() {
		return idPai;
	}

	public void setIdPai(Long idPai) {
		this.idPai = idPai;
	}

	public Long getIdFilho() {
		return idFilho;
	}

	public void setIdFilho(Long idFilho) {
		this.idFilho = idFilho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFilho == null) ? 0 : idFilho.hashCode());
		result = prime * result + ((idPai == null) ? 0 : idPai.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tbfilhos other = (Tbfilhos) obj;
		if (idFilho == null) {
			if (other.idFilho != null)
				return false;
		} else if (!idFilho.equals(other.idFilho))
			return false;
		if (idPai == null) {
			if (other.idPai != null)
				return false;
		} else if (!idPai.equals(other.idPai))
			return false;
		return true;
	}

	
}
