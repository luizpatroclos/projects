package br.gov.inpi.epec.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;



@Embeddable
public class TbRelatorioPatentePK  implements Serializable {
	


	private static final long serialVersionUID = 8541090182387917187L;

	@Column(name = "ID_RELATORIO_EC")
	private Long idRelatorio;

	@Column(name = "ID_PATENTE_EC")
	private Long idPatente;
	
	
	public Long getIdRelatorio() {
		return idRelatorio;
	}

	public void setIdRelatorio(Long idRelatorio) {
		this.idRelatorio = idRelatorio;
	}

	public Long getIdPatente() {
		return idPatente;
	}

	public void setIdPatente(Long idPatente) {
		this.idPatente = idPatente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPatente == null) ? 0 : idPatente.hashCode());
		result = prime * result + ((idRelatorio == null) ? 0 : idRelatorio.hashCode());
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
		TbRelatorioPatentePK other = (TbRelatorioPatentePK) obj;
		if (idPatente == null) {
			if (other.idPatente != null)
				return false;
		} else if (!idPatente.equals(other.idPatente))
			return false;
		if (idRelatorio == null) {
			if (other.idRelatorio != null)
				return false;
		} else if (!idRelatorio.equals(other.idRelatorio))
			return false;
		return true;
	}

}
