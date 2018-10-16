package br.gov.inpi.epec.entities;

public class RelatorioPatente {

	private String namePrior;

	private Long idCategoria;

	private String documentCategory;

	private String relevance;

	private String relations;

	private String relationsClaims;

	private String tipoAnterioridade;
	
	public RelatorioPatente() {

	}

	/**
	 * @return the relationsClaims
	 */
	public String getRelationsClaims() {
		return relationsClaims;
	}

	/**
	 * @param relationsClaims
	 *            the relationsClaims to set
	 */
	public void setRelationsClaims(String relationsClaims) {
		this.relationsClaims = relationsClaims;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return the namePrior
	 */
	public String getNamePrior() {
		return namePrior;
	}

	/**
	 * @param namePrior
	 *            the namePrior to set
	 */
	public void setNamePrior(String namePrior) {
		this.namePrior = namePrior;
	}

	/**
	 * @return the documentCategory
	 */
	public String getDocumentCategory() {
		return documentCategory;
	}

	/**
	 * @param documentCategory
	 *            the documentCategory to set
	 */
	public void setDocumentCategory(String documentCategory) {
		this.documentCategory = documentCategory;
	}

	/**
	 * @return the relevance
	 */
	public String getRelevance() {
		return relevance;
	}

	/**
	 * @param relevance
	 *            the relevance to set
	 */
	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	/**
	 * @return the relations
	 */
	public String getRelations() {
		return relations;
	}

	/**
	 * @param relations
	 *            the relations to set
	 */
	public void setRelations(String relations) {
		this.relations = relations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RelatorioPatente [namePrior=" + namePrior + ", documentCategory=" + documentCategory + ", relevance=" + relevance + ", relations=" + relations + "]";
	}

	public String getTipoAnterioridade() {
		return tipoAnterioridade;
	}

	public void setTipoAnterioridade(String tipoAnterioridade) {
		this.tipoAnterioridade = tipoAnterioridade;
	}

}
