package br.gov.inpi.epec.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class CategoriaNaoPatentetariaEntity {
	
	
	@Id
	@Column(name="ID_ANTNAOPATENTARIACATRELEC")
	private long id;	
	
	@Column(name="STR_RELATORIO")
    private String namePrior;
	
	@Column(name="STR_OMPI")
	private String documentCategory;
	
	@Column(name="STR_PORTUGUES")
	private String relevance;
	
	@Column(name="TX_RELACAO")
	private String relations;
	
	@Column(name="TX_REIVINDICACAO")
	private String relationsClaims;
	
	
	@Column(name="TX_TITULO")
    private String txTitulo;
	
	
	
	

	public String getTxTitulo() {
		return txTitulo;
	}


	public void setTxTitulo(String txTitulo) {
		this.txTitulo = txTitulo;
	}


	public String getNamePrior() {
		return namePrior;
	}


	public void setNamePrior(String namePrior) {
		this.namePrior = namePrior;
	}


	public String getDocumentCategory() {
		return documentCategory;
	}


	public void setDocumentCategory(String documentCategory) {
		this.documentCategory = documentCategory;
	}


	public String getRelevance() {
		return relevance;
	}


	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}


	public String getRelations() {
		return relations;
	}


	public void setRelations(String relations) {
		this.relations = relations;
	}


	public String getRelationsClaims() {
		return relationsClaims;
	}


	public void setRelationsClaims(String relationsClaims) {
		this.relationsClaims = relationsClaims;
	}


}