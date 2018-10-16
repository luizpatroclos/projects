package br.gov.inpi.epec.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class CategoriaPatentariaEntity {
	
	@Id
	@Column(name="ID_ANTPATENTARIACATRELEC")
	private long id;
	
	@Column(name="ID_CATEGORIA")
	private long idCategoria; 	
	
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
	
	@Column(name="STR_ANTPATENTARIA")
    private String antPatentaria;
	
	
	

	public String getAntPatentaria() {
		return antPatentaria;
	}
	
	public long getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}


	public void setAntPatentaria(String antPatentaria) {
		this.antPatentaria = antPatentaria;
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