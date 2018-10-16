/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author lasilva
 */

@Entity
@Table(name = "TBRELATORIOCOLABORACAO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tbrelatoriocolaboracao.findByIdRelatorioColaboracao_1", query = "SELECT t FROM Tbrelatoriocolaboracao t WHERE t.colaboracaoPK.idRelatorio = :idRelatorio"),
		@NamedQuery(name = "Tbrelatoriocolaboracao.findByIdRelatorioColaboracao_2", query = "SELECT t FROM Tbrelatoriocolaboracao t WHERE t.colaboracaoPK.idColaboracao= :idColaboracao") })
public class Tbrelatoriocolaboracao implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	private TbRelatorioColaboracaoPK colaboracaoPK;

	
	public TbRelatorioColaboracaoPK getColaboracaoPK() {
		return colaboracaoPK;
	}



	public void setColaboracaoPK(TbRelatorioColaboracaoPK colaboracaoPK) {
		this.colaboracaoPK = colaboracaoPK;
	}



	
}
