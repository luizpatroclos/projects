package br.gov.inpi.epec;

import java.util.List;

import br.gov.inpi.epec.writer.BibliographicDataXmlWriter;
import br.gov.inpi.epec.writer.EquivalentsIquiryXmlWriter;
import br.gov.inpi.epec.writer.ReportDataXmlWriter;
import br.gov.inpi.epec.xml.bibliographicdata.BibliographicData;
import br.gov.inpi.epec.xml.family.EquivalentsInquiry;
import br.gov.inpi.epec.xml.report.ReportApplicationReference;

import br.gov.inpi.epec.entities.Familia;
import br.gov.inpi.epec.entities.Patente;
import br.gov.inpi.epec.entities.RelatorioTecnico;

/****
 * 
 * Classe Responsável em realizar a conversão para o XML utilizado pelo ePec.
 * 
 * 
 * @author allanlq
 *
 */
public class EpecXmlWriter {

	/**
	 * 
	 * Converte uma patente para o formato Bibliográfico.
	 * 
	 * @param patente
	 * @param tipoConsulta
	 * @return
	 */
	public BibliographicData converterPatenteParaXml(Patente patente, String tipoConsulta) {

		BibliographicData biblioGraphic = new BibliographicData();

		BibliographicDataXmlWriter biblio = new BibliographicDataXmlWriter();
		if ("epodoc".equalsIgnoreCase(tipoConsulta)) {
			biblioGraphic = biblio.converterPatenteEpodocParaXml(patente);
		}else if ("docDb".equalsIgnoreCase(tipoConsulta)) {
			biblioGraphic = biblio.converterPatenteDocDbParaXml(patente);
		}else if ("original".equalsIgnoreCase(tipoConsulta)) {
			biblioGraphic = biblio.converterPatenteOriginalParaXml(patente);
		}

		return biblioGraphic;

	}

	public EquivalentsInquiry conveterFamiliaParaXml(List<Familia> familiaDocumentos, String numeroPedidoBase) {
		EquivalentsInquiry equivalentsInquiry = new EquivalentsInquiry();
		EquivalentsIquiryXmlWriter writer = new EquivalentsIquiryXmlWriter();
		equivalentsInquiry = writer.converterFamilia(familiaDocumentos, numeroPedidoBase);
		return equivalentsInquiry;
	}
	
	
	/*public EquivalentsInquiry conveterFamiliaParaXmlPublicationEpo(List<Familia> familiaDocumentos, String numeroPedidoBase) {
		EquivalentsInquiry equivalentsInquiry = new EquivalentsInquiry();
		EquivalentsIquiryXmlWriter writer = new EquivalentsIquiryXmlWriter();
		equivalentsInquiry = writer.converterFamiliaPublicationEpoDoc(familiaDocumentos, numeroPedidoBase);
		return equivalentsInquiry;
	}*/
	
	
	

	public ReportApplicationReference conveterRelatorioTecnico(RelatorioTecnico relatorioTecnico) {
		ReportApplicationReference report = new ReportApplicationReference();
		ReportDataXmlWriter writer = new ReportDataXmlWriter();
		report = writer.converterRelatorioTecnico(relatorioTecnico);
		return report;
	}

}
