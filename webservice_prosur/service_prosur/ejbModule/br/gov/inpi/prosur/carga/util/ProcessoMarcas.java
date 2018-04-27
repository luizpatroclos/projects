package br.gov.inpi.prosur.carga.util;

import java.util.Date;

import br.gov.inpi.prosur.beans.Prosur;



/***
 * 
 * POJO - Encapsula os atributos referentes a um processo de Marcas,
 * existente no INPI.
 * 
 * @author tgouvea
 *
 */
public class ProcessoMarcas extends Prosur {
		

	
	/** 
	 * 
	 * URL do serviço para arquivos fornecidos IPOfficeWS 
	 * fornecidos pelo escritorio PI. Neste caso exclusivo de
	 * Marcas.
	 * 
	 * */
	private String ipRecordFilesService;
	
	/**
	 * Lista de arquivos que será acessado através da URL ipRecordFilesService.
	 */
	private String files;
	

	/** 
	 *   
	 *  Nome do arquivo referente a figura do processo de Marcas.
	 *  Exemplo : 830034200.jpg
	 *  
	 *  */	
	private String fileName;
	
	
	/***
	 *  Titulo do arquivo. 
	 *  Exemplo : CONEXWELD
	 *  
	 */
	private String fileTitle;
		
	/***
	 *  O tipo de sinal distintivo.
	 */
	private String distinctiveSign;
	
	/**
	 * Tipo de apresentação da figura do processo de marcas.
	 */
	private String presentationType;
	
	
	/***
	 * Data do registro
	 */
	private Date registrationDate;
	
	
	/***
	 * Data de vencimento
	 */
	private Date expiration;
	
	/**
	 * Classificação de Nice.
	 * 
	 * " .. Classificação Internacional de Produtos e Serviços de Nice, 
	 * possui uma lista de 45 classes com informação sobre os diversos tipos
	 *  de produtos e serviços e o que pertence a cada classe... " 
	 *  
	 *  Fonte : INPI
	 *  Link : http://www.inpi.gov.br/portal/artigo/classificacao_marcas
	 *  Acessado em : 27/05/2014 as 16:44
	 *  
	 */
	private String niceClasses;
	
	/***
	 * Descrição do logo.
	 */
	private String logoDescription;
		
	/**
	 * Número da classe Nice.
	 */
	private String classNumber;
	
	
	/***
	 * O produto, serviço ou atividade.
	 */
	private String activy;
	//-------------------------------------------------------------------------------------------
	
	
	public ProcessoMarcas(){}
		
		
	/*

	public ProcessoMarcas(String iPRecord, String ipRecordID,
				String onapId, String applicationId,
				Date nationalPresentationDate, Date nationalPublishingDate,
				String title, String applicationName, String statusID,
				String ipRecordDetailLink, String files,
				String ipRecordFilesService, String fileName, String fileTitle,
				String distinctiveSign, String presentationType,
				Date registrationDate, Date expiration, String niceClasses,
				String logoDescription, String classNumber, String activy) {
			
		    super.setIPRecord(iPRecord);
			super.setIPRecord(ipRecordID);
			super.setOnapId(onapId);
			super.setOnapId(applicationId);
			super.setNationalPresentationDate(nationalPresentationDate);
			super.setNationalPublishingDate(nationalPublishingDate);
			super.setTitle(title);
			super.setApplicationName(applicationName);
			super.setStatusID(statusID);
			super.setIpRecordDetailLink(ipRecordDetailLink);
			this.files = files;
			this.ipRecordFilesService = ipRecordFilesService;
			this.fileName = fileName;
			this.fileTitle = fileTitle;
			this.distinctiveSign = distinctiveSign;
			this.presentationType = presentationType;
			this.registrationDate = registrationDate;
			this.expiration = expiration;
			this.niceClasses = niceClasses;
			this.logoDescription = logoDescription;
			this.classNumber = classNumber;
			this.activy = activy;
		
	}*/




	/**
	 * @return the ipRecordFilesService
	 */
	public String getIpRecordFilesService() {
		return ipRecordFilesService;
	}




	/**
	 * @param ipRecordFilesService the ipRecordFilesService to set
	 */
	public void setIpRecordFilesService(String ipRecordFilesService) {
		this.ipRecordFilesService = ipRecordFilesService;
	}




	/**
	 * @return the files
	 */
	public String getFiles() {
		return files;
	}




	/**
	 * @param files the files to set
	 */
	public void setFiles(String files) {
		this.files = files;
	}




	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}




	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}




	/**
	 * @return the fileTitle
	 */
	public String getFileTitle() {
		return fileTitle;
	}




	/**
	 * @param fileTitle the fileTitle to set
	 */
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}




	/**
	 * @return the distinctiveSign
	 */
	public String getDistinctiveSign() {
		return distinctiveSign;
	}




	/**
	 * @param distinctiveSign the distinctiveSign to set
	 */
	public void setDistinctiveSign(String distinctiveSign) {
		this.distinctiveSign = distinctiveSign;
	}




	/**
	 * @return the presentationType
	 */
	public String getPresentationType() {
		return presentationType;
	}




	/**
	 * @param presentationType the presentationType to set
	 */
	public void setPresentationType(String presentationType) {
		this.presentationType = presentationType;
	}




	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}




	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}




	/**
	 * @return the expiration
	 */
	public Date getExpiration() {
		return expiration;
	}




	/**
	 * @param expiration the expiration to set
	 */
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}




	/**
	 * @return the niceClasses
	 */
	public String getNiceClasses() {
		return niceClasses;
	}




	/**
	 * @param niceClasses the niceClasses to set
	 */
	public void setNiceClasses(String niceClasses) {
		this.niceClasses = niceClasses;
	}




	/**
	 * @return the logoDescription
	 */
	public String getLogoDescription() {
		return logoDescription;
	}




	/**
	 * @param logoDescription the logoDescription to set
	 */
	public void setLogoDescription(String logoDescription) {
		this.logoDescription = logoDescription;
	}




	/**
	 * @return the classNumber
	 */
	public String getClassNumber() {
		return classNumber;
	}




	/**
	 * @param classNumber the classNumber to set
	 */
	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}




	/**
	 * @return the activy
	 */
	public String getActivy() {
		return activy;
	}




	/**
	 * @param activy the activy to set
	 */
	public void setActivy(String activy) {
		this.activy = activy;
	}


	@Override
	public String toString() {
		return "Marca [ipRecordFilesService=" + ipRecordFilesService
				+ ", files=" + files + ", fileName=" + fileName
				+ ", fileTitle=" + fileTitle + ", distinctiveSign="
				+ distinctiveSign + ", presentationType=" + presentationType
				+ ", registrationDate=" + registrationDate + ", expiration="
				+ expiration + ", niceClasses=" + niceClasses
				+ ", logoDescription=" + logoDescription + ", classNumber="
				+ classNumber + ", activy=" + activy + ", getIPRecord()="
				+ getIPRecord() + ", getIpRecordID()=" + getIpRecordID()
				+ ", getOnapId()=" + getOnapId() + ", getApplicationId()="
				+ getApplicationId() + ", getNationalPresentationDate()="
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
