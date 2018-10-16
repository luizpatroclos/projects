/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author lasilva
 */
@Entity
@Table(name = "TBRELATORIOPATENTE")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbrelatoriopatente.findByIdRelatorioPatente_1", query = "SELECT t FROM Tbrelatoriopatente t WHERE t.tbRelatorioPatentePK.idRelatorio = :idRelatorio"),
	 @NamedQuery(name = "Tbrelatoriopatente.findByIdRelatorioPatente_3", query = "SELECT t FROM Tbrelatoriopatente t WHERE t.tbRelatorioPatentePK.idPatente = :idPatente ORDER BY t.tbRelatorioPatentePK.idRelatorio DESC "), 
     @NamedQuery(name = "Tbrelatoriopatente.findByIdRelatorioPatente_2", query = "SELECT t FROM Tbrelatoriopatente t WHERE t.tbRelatorioPatentePK.idPatente = :idPatente") })
public class Tbrelatoriopatente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 	@EmbeddedId
	    protected TbRelatorioPatentePK tbRelatorioPatentePK;	

	    public Tbrelatoriopatente() {
	    }

	    public void setTbRelatorioPatentePK(TbRelatorioPatentePK tbRelatorioPatentePK) {
			this.tbRelatorioPatentePK = tbRelatorioPatentePK;
		}

		public Tbrelatoriopatente(TbRelatorioPatentePK tbRelatorioPatentePK) {
	        this.tbRelatorioPatentePK = tbRelatorioPatentePK;
	    }


	    public Tbrelatoriopatente(long idRelatorio, long idPatenteEc) {
	        this.tbRelatorioPatentePK = new TbRelatorioPatentePK();
	    }

	    public TbRelatorioPatentePK getTbRelatorioPatentePK() {
	        return tbRelatorioPatentePK;
	    }

	    public void TbRelatorioPatentePK(TbRelatorioPatentePK tbRelatorioPatentePK) {
	        this.tbRelatorioPatentePK = tbRelatorioPatentePK;
	    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tbRelatorioPatentePK.getIdPatente() == null) ? 0 : tbRelatorioPatentePK.getIdPatente().hashCode());
		result = prime * result + ((tbRelatorioPatentePK.getIdRelatorio() == null) ? 0 : tbRelatorioPatentePK.getIdRelatorio().hashCode());
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
		Tbrelatoriopatente other = (Tbrelatoriopatente) obj;
		if (tbRelatorioPatentePK.getIdPatente() == null) {
			if (other.tbRelatorioPatentePK.getIdPatente() != null)
				return false;
		} else if (!tbRelatorioPatentePK.getIdPatente().equals(other.tbRelatorioPatentePK.getIdPatente()))
			return false;
		if (tbRelatorioPatentePK.getIdRelatorio() == null) {
			if (other.tbRelatorioPatentePK.getIdRelatorio() != null)
				return false;
		} else if (!tbRelatorioPatentePK.getIdRelatorio().equals(other.tbRelatorioPatentePK.getIdRelatorio()))
			return false;
		return true;
	}

}
