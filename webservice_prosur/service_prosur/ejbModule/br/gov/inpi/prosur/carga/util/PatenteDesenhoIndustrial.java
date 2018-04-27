package br.gov.inpi.prosur.carga.util;

import java.util.Date;
import java.util.List;

import br.gov.inpi.patentes.beans.DesignPatentLocarno;
import br.gov.inpi.patentes.beans.PtnPct;
import br.gov.inpi.patentes.beans.PtnPrioridade;
import br.gov.inpi.prosur.beans.Prosur;

public class PatenteDesenhoIndustrial extends Prosur{
	
	
	
	private List<String> applicants;	
	
	
	// Propriedades comuns a Patente e Desenho Industrial ----------------------
   /***
	 * Pais de Origem do principal Applicant (Depositante)
	 */
	private String requestCoutryId;
	
	
	/***
	 *  Escritório ou Procurador do pedido.
	 */
	private String representativeName;
	
	
	/***
	 *  Prioridade(s) do Pedido. 
	 */
	private List<PtnPrioridade> prioridades;
	
	
	/***
	 *  
	 *  Número do registro definitivo.  
	 *  O número é o mesmo do ApplicationId (número do pedido).
	 *  
	 *  Desenho Industrial = 39
	 *  Patente 16.1  
	 *  
	 */
	private String registrationNumber;
	
		
	/***
	 * Inventores do pedido.
	 * 	
	 */
	private List<String> inventors;
    // -----------------------------------------------------------------------				

	// Propriedades de Patentes ---------------------------------------------
	
	/**  Resumo do pedido de patente */
	private String patentAbstract;
		
		
	/** Relatório descritivo */    
	private String description;
	
	
	/** 
	 * Tipo de patente :
	 * Invenção, Utilitário, Pedido Divido ou Certificado de Adição.   
	 *  
	 * */
	private String patentTypeId;
	
	
	/***
	 * Classificação Internacional.
	 */
    private List<String> internationalClassification;
	
    
    /***
     * PCT do Pedido de patente.
     */
    private PtnPct ptnPct;
    //------------------------------------------------------------------------
		
	
	// Desenho Industrial ----------------------------------------------------
	/** 
	 * Desenho Industrial - Conclusão do pedido 
	 * 
	 * */
	private String conclusionMethod;
	
	
	
	/**
	 *  Desenho Industrial - Classificação de Locarno 
	 * */
	private List<DesignPatentLocarno> designPatentLocarnos;
	// Fim de Desenho Industrial ---------------------------------------------
	
	
	private String designClassification;
	
	
	
	
	public String getDesignClassification() {
		return designClassification;
	}

	public void setDesignClassification(String designClassification) {
		this.designClassification = designClassification;
	}

	public List<DesignPatentLocarno> getDesignPatents() {
		return designPatentLocarnos;
	}

	public List<String> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<String> applicants) {
		this.applicants = applicants;
	}

	public String getPatentAbstract() {
		return patentAbstract;
	}

	public void setPatentAbstract(String patentAbstract) {
		this.patentAbstract = patentAbstract;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPatentTypeId() {
		return patentTypeId;
	}

	public void setPatentTypeId(String patentTypeId) {
		this.patentTypeId = patentTypeId;
	}

	public List<String> getInternationalClassification() {
		return internationalClassification;
	}

	public void setInternationalClassification(List<String> internationalClassification) {
		this.internationalClassification = internationalClassification;
	}

		
	public PtnPct getPtnPct() {
		return ptnPct;
	}

	public void setPtnPct(PtnPct ptnPct) {
		this.ptnPct = ptnPct;
	}

	public void setDesignPatents(List<DesignPatentLocarno> designPatentLocarnos) {
		this.designPatentLocarnos = designPatentLocarnos;
	}

	public List<String> getInventors() {
		return inventors;
	}

	public void setInventors(List<String> inventors) {
		this.inventors = inventors;
	}

	public String getConclusionMethod() {
		return conclusionMethod;
	}

	public void setConclusionMethod(String conclusionMethod) {
		this.conclusionMethod = conclusionMethod;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public List<PtnPrioridade> getPrioridades() {
		return prioridades;
	}

	public void setPrioridades(List<PtnPrioridade> prioridades) {
		this.prioridades = prioridades;
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public String getRequestCoutryId() {
		return requestCoutryId;
	}

	public void setRequestCoutryId(String requestCoutryId) {
		this.requestCoutryId = requestCoutryId;
	}

	@Override
	public String toString() {
		return "PatenteDesenhoIndustrial [applicants=" + applicants
				+ ", requestCoutryId=" + requestCoutryId
				+ ", representativeName=" + representativeName
				+ ", prioridades=" + prioridades + ", registrationNumber="
				+ registrationNumber + ", inventors=" + inventors
				+ ", patentAbstract=" + patentAbstract + ", description="
				+ description + ", patentTypeId=" + patentTypeId
				+ ", internationalClassification="
				+ internationalClassification + ", ptnPct=" + ptnPct
				+ ", conclusionMethod=" + conclusionMethod + ", designPatentLocarnos="
				+ designPatentLocarnos + ", getIPRecord()=" + getIPRecord()
				+ ", getIpRecordID()=" + getIpRecordID() + ", getOnapId()="
				+ getOnapId() + ", getApplicationId()=" + getApplicationId()
				+ ", getNationalPresentationDate()="
				+ getNationalPresentationDate()
				+ ", getNationalPublishingDate()="
				+ getNationalPublishingDate() + ", getTitle()=" + getTitle()
				+ ", getApplicationName()=" + getApplicationName()
				+ ", getStatusID()=" + getStatusID()
				+ ", getIpRecordDetailLink()=" + getIpRecordDetailLink()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
