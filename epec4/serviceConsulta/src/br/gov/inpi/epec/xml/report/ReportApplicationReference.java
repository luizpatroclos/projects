package br.gov.inpi.epec.xml.report;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.gov.inpi.epec.xml.document.DocumentId;
import br.gov.inpi.epec.xml.report.reportdata.ReportData;



@XmlRootElement(name="application-reference")
@XmlType(propOrder={"documentId","reportarData"})
public class ReportApplicationReference {
	
		 
	 private DocumentId documentId;
	
	 
	 private ReportData reportarData;
 	  
	 
	
	/**
	 * @return the documentId
	 */
	 @XmlElement(name="document-id")
	public DocumentId getDocumentId() {
		return documentId;
	}

	/**
	 * @param documentId the documentId to set
	 */
	public void setDocumentId(DocumentId documentId) {
		this.documentId = documentId;
	}

	/**
	 * @return the reportarData
	 */
	@XmlElement(name="report-data")
	public ReportData getReportarData() {
		return reportarData;
	}

	/**
	 * @param reportarData the reportarData to set
	 */
	public void setReportarData(ReportData reportarData) {
		this.reportarData = reportarData;
	}

	@Override
	public String toString() {
		return "ReportApplicationReference [documentId=" + documentId
				+ ", reportarData=" + reportarData + "]";
	}

	
}


