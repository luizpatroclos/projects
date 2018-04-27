package br.gov.inpi.prosur.beans;

import java.util.Date;

/***
 * 
 * Classe responsável em encapsular informações exclusivas do Prosur.
 * 
 * @author tgouvea
 *
 */
public class Prosur {
	
	
	
	/** Elemento base para o registro de Propriedade Industrial. */
	private String IPRecord;
	
	
	/** Identificador do registro no Banco de dados PROSUR. */
	private String ipRecordID;
	
	/** Identificador do banco de dados do PROSUR. 
	 * O atributo é fixo cujo valor é 'BR'.  */
	private String onapId;
		
	
	/**Número do Pedido. */
	private String applicationId;
	
	
	/** Data de Depósito */
	private Date nationalPresentationDate;
	
	
	/** Data de Publicação Nacional. */	
	private Date nationalPublishingDate;
	
	
	/** Nome da Marca. */
	private String title;
	
	/** Solicitante */
	private String applicationName;
	
	
	/** Status do pedido no PROSUR */
	private String statusID;
	
	
	/** Link para Onapi no caso sítio do INPI. */
	private String ipRecordDetailLink;
	
	public Prosur() {
		super();
	}

	/**
	 * @return the iPRecord
	 */
	public String getIPRecord() {
		return IPRecord;
	}


	/**
	 * @param iPRecord the iPRecord to set
	 */
	public void setIPRecord(String iPRecord) {
		IPRecord = iPRecord;
	}


	/**
	 * @return the ipRecordID
	 */
	public String getIpRecordID() {
		return ipRecordID;
	}


	/**
	 * @param ipRecordID the ipRecordID to set
	 */
	public void setIpRecordID(String ipRecordID) {
		this.ipRecordID = ipRecordID;
	}


	/**
	 * @return the onapId
	 */
	public String getOnapId() {
		return onapId;
	}


	/**
	 * @param onapId the onapId to set
	 */
	public void setOnapId(String onapId) {
		this.onapId = onapId;
	}


	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}


	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}


	/**
	 * @return the nationalPresentationDate
	 */
	public Date getNationalPresentationDate() {
		return nationalPresentationDate;
	}


	/**
	 * @param nationalPresentationDate the nationalPresentationDate to set
	 */
	public void setNationalPresentationDate(Date nationalPresentationDate) {
		this.nationalPresentationDate = nationalPresentationDate;
	}


	/**
	 * @return the nationalPublishingDate
	 */
	public Date getNationalPublishingDate() {
		return nationalPublishingDate;
	}


	/**
	 * @param nationalPublishingDate the nationalPublishingDate to set
	 */
	public void setNationalPublishingDate(Date nationalPublishingDate) {
		this.nationalPublishingDate = nationalPublishingDate;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}


	/**
	 * @param applicationName the applicationName to set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}


	/**
	 * @return the statusID
	 */
	public String getStatusID() {
		return statusID;
	}


	/**
	 * @param statusID the statusID to set
	 */
	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}


	/**
	 * @return the ipRecordDetailLink
	 */
	public String getIpRecordDetailLink() {
		return ipRecordDetailLink;
	}


	/**
	 * @param ipRecordDetailLink the ipRecordDetailLink to set
	 */
	public void setIpRecordDetailLink(String ipRecordDetailLink) {
		this.ipRecordDetailLink = ipRecordDetailLink;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Prosur [IPRecord=" + IPRecord + ", ipRecordID=" + ipRecordID
				+ ", onapId=" + onapId + ", applicationId=" + applicationId
				+ ", nationalPresentationDate=" + nationalPresentationDate
				+ ", nationalPublishingDate=" + nationalPublishingDate
				+ ", title=" + title + ", applicationName=" + applicationName
				+ ", statusID=" + statusID + ", ipRecordDetailLink="
				+ ipRecordDetailLink + "]";
	}


	
	
	
	
	
	
}
