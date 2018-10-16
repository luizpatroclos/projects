package br.gov.inpi.epec.xml.report.reportdata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.gov.inpi.epec.xml.report.category.CategoryList;



@XmlRootElement(name="report-data")
@XmlType(propOrder={"reportNumber",
		            "reportStatus",
		            "situation",
		            "sumary",
		            "conclusion",
		            "listaAnteoridades",
		            "antioridadesNaoPatentarias",
		            "categoriasDoRelatorio"})
public class ReportData {
	
	
   private String sequence;
	
	
	private String reportNumber;
	
	
	private String reportStatus;
	
	
	private String situation;
	
		
	private String sumary;
	
	
	private String conclusion;
	
	
	private PatentPriorList listaAnteoridades;
	
		
	private  NonPatentPriorList antioridadesNaoPatentarias;
	
	
	private CategoryList categoriasDoRelatorio;


	/**
	 * @return the reportNumber
	 */
	@XmlElement(name="report-number")
	public String getReportNumber() {
		return reportNumber;
	}


	/**
	 * @param reportNumber the reportNumber to set
	 */
	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}


	/**
	 * @return the reportStatus
	 */
	@XmlElement(name="report-status")
	public String getReportStatus() {
		return reportStatus;
	}


	/**
	 * @param reportStatus the reportStatus to set
	 */
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}


	/**
	 * @return the situation
	 */
	@XmlElement(name="situation")
	public String getSituation() {
		return situation;
	}


	/**
	 * @param situation the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}


	

	/**
	 * @return the sumary
	 */
	@XmlElement(name="summary")
	public String getSumary() {
		return sumary;
	}


	/**
	 * @param sumary the sumary to set
	 */
	public void setSumary(String sumary) {
		this.sumary = sumary;
	}

    
	


	/**
	 * @return the sequence
	 */
	@XmlAttribute(name="sequence")
	public String getSequence() {
		return sequence;
	}


	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

    @XmlElement(name="non-patent-prior-list")
	public NonPatentPriorList getAntioridadesNaoPatentarias() {
		return antioridadesNaoPatentarias;
	}


	public void setAntioridadesNaoPatentarias(
			NonPatentPriorList antioridadesNaoPatentarias) {
		this.antioridadesNaoPatentarias = antioridadesNaoPatentarias;
	}


	


	@XmlElement(name="patent-prior-list")
	public PatentPriorList getListaAnteoridades() {
		return listaAnteoridades;
	}


	public void setListaAnteoridades(PatentPriorList listaAnteoridades) {
		this.listaAnteoridades = listaAnteoridades;
	}


	@XmlElement(name="category-list")
	public CategoryList getCategoriasDoRelatorio() {
		return categoriasDoRelatorio;
	}


	public void setCategoriasDoRelatorio(CategoryList categoriasDoRelatorio) {
		this.categoriasDoRelatorio = categoriasDoRelatorio;
	}


	@XmlElement(name="conclusion")
	public String getConclusion() {
		return conclusion;
	}


	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}


	@Override
	public String toString() {
		return "ReportData [sequence=" + sequence + ", reportNumber="
				+ reportNumber + ", reportStatus=" + reportStatus
				+ ", situation=" + situation + ", sumary=" + sumary
				+ ", conclusion=" + conclusion + ", listaAnteoridades="
				+ listaAnteoridades + ", antioridadesNaoPatentarias="
				+ antioridadesNaoPatentarias + ", categoriasDoRelatorio="
				+ categoriasDoRelatorio + "]";
	}

	
	
	
}
