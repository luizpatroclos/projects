package br.gov.inpi.epec.service.impl.helper;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.gov.inpi.epec.entities.Citacao;
import br.gov.inpi.epec.entities.Familia;
import br.gov.inpi.epec.entities.Patente;
import br.gov.inpi.epec.entities.Prioridade;

public class FamiliaHelper {

	String docNumber;
	Locale localeES = new Locale("es", "");
	Locale localeEN = new Locale("en", "");

	public String getDocNumber() {

		return this.docNumber;
	}

	public void setDocNumber(String docNumber) {

		this.docNumber = docNumber;
	}

	/*
	 * popular a familia para o relatorio de diagrama da familia
	 */
	public List<Familia> obterFamilia(String xml, String tipoConsulta) {

		
		
		List<Familia> familias = new ArrayList<Familia>();

		try {

			Document xmlDoc = loadXMLFrom(xml);
			Element root = xmlDoc.getDocumentElement();

			NodeList patentFamilyPai = root.getElementsByTagName("ops:family-member");

			NodeList nodePublicationReference = root.getElementsByTagName("ops:publication-reference");
			Element publicationReference = (Element) nodePublicationReference.item(0);

			if ("ops:publication-reference".equals(publicationReference.getNodeName())) {

				this.obterPublicationReferencePai(publicationReference);
			}

			for (int i = 0; i < patentFamilyPai.getLength(); i++) {

				Element element = (Element) patentFamilyPai.item(i);
				String nomeElemento = element.getNodeName();
				if ("ops:family-member".equals(nomeElemento)) {
					Familia familia = this.obterFamilia(element, tipoConsulta);
					familias.add(familia);
				}

			}

		} catch (Exception e) {
			FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_WARN, "Erro na solicitação do serviço!", "Erro !");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return familias;
	}

	private FacesMessage createMessage(Severity severity, String mensagemErro, String tipo) {
		return new FacesMessage(severity, tipo, mensagemErro);
	}

	/*
	 * Carrega o XML
	 */
	public static Document loadXMLFrom(String xml) throws Exception {
		Source source = new StreamSource(new StringReader(xml));
		DOMResult result = new DOMResult();
		TransformerFactory.newInstance().newTransformer().transform(source, result);
		return (Document) result.getNode();
	}

	public Familia obterFamilia(Element element, String tipoConsulta) throws Exception {

		Familia familia = new Familia();
		Patente patente = new Patente();

		// Resgata Publicacao
		NodeList nodePublicationReference = element.getElementsByTagName("publication-reference");
		Element publicationReference = (Element) nodePublicationReference.item(0);
		patente = this.obterPublicationReference(publicationReference, patente);

		if ("diagrama".equals(tipoConsulta)) {
			patente = obterDadosParaDiagrama(element, patente, nodePublicationReference.item(0).getNodeType());

			if (!(this.docNumber == null || this.docNumber.isEmpty())) {

				int tamanho = patente.getNumeroPublicacao().length();
				String numeroPublicacao = patente.getPaisPublicacao() + patente.getNumeroPublicacao().substring(0, tamanho);

				if (numeroPublicacao.equals(this.docNumber)) {

					familia.setPrincipal("sim");
				} else {

					familia.setPrincipal("nao");
				}
			}
		}
		// Resgata Referências Citadas
		if ("citacao".equals(tipoConsulta)) {
			NodeList nodeExchangeDocument = element.getElementsByTagName("exchange-document");
			if (nodeExchangeDocument != null && nodeExchangeDocument.getLength() > 0) {
				Element exchangeDocument = (Element) nodeExchangeDocument.item(0);
				patente = this.fazerParseExchangeDocumentParaCitacoes(exchangeDocument, patente);
			}
		}

		familia.setPatente(patente);

		return familia;
	}

	private Patente obterDadosParaDiagrama(Element element, Patente patente, Short node) {
		try {
			// Resgata Prioridade
			NodeList nodePriorityClaim = element.getElementsByTagName("priority-claim");
			Element priorityClaim = (Element) nodePriorityClaim.item(0);

			// Resgata Deposito
			NodeList nodeApplicationReference = element.getElementsByTagName("application-reference");
			Element applicationReference = (Element) nodeApplicationReference.item(0);

			short n1 = nodePriorityClaim.item(0).getNodeType();
			short n2 = node;
			short n3 = nodeApplicationReference.item(0).getNodeType();

			if (n1 == Node.ELEMENT_NODE && n2 == Node.ELEMENT_NODE && n3 == Node.ELEMENT_NODE) {
				patente = this.obterPriorityClaim(priorityClaim, patente);
				patente = this.obterApplicationReference(applicationReference, patente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patente;
	}

	/**
	 * Resgata os dados do publication-reference mais alto a fim de identificar
	 * qual n� faz referencia direta com o pedido analisado
	 */
	private void obterPublicationReferencePai(Element e) {

		NodeList nodeDocumentId = e.getElementsByTagName("document-id");
		Element documentId = (Element) nodeDocumentId.item(0);

		if ("document-id".equals(documentId.getNodeName())) {

			NodeList nodeFilhoDocumentId = documentId.getChildNodes();

			if (nodeFilhoDocumentId.item(1).getNodeType() == Node.ELEMENT_NODE) {

				Element filhoDocumentId = (Element) nodeFilhoDocumentId.item(1);
				this.setDocNumber(filhoDocumentId.getTextContent());
			}
		}
	}

	private Patente obterPublicationReference(Element e, Patente patente) throws Exception {
		NodeList documentsId = e.getElementsByTagName("document-id");

		String kind = "";
		String country = "";
		String numeroPublicacao = "";

		for (int y = 0; y < documentsId.getLength(); y++) {

			Element documentId = (Element) documentsId.item(y);
			if ("document-id".equals(documentId.getNodeName())) {

				String atribute = documentId.getAttribute("document-id-type");
				if ("docdb".equals(atribute)) {

					NodeList documentsId2 = documentId.getChildNodes();
					for (int z = 0; z < documentsId2.getLength(); z++) {

						if (documentsId2.item(z).getNodeType() == Node.ELEMENT_NODE) {

							Element elementDocId = (Element) documentsId2.item(z);
							String name = elementDocId.getNodeName();
							if ("country".equals(name)) {
								patente.setPaisPublicacao(elementDocId.getTextContent());								
								patente.setPais(elementDocId.getTextContent());
								country = elementDocId.getTextContent();
							}

							if ("doc-number".equals(name)) {
								numeroPublicacao = elementDocId.getTextContent();
							}

							if ("kind".equals(name)) {
								patente.setKindCodePublicacao(elementDocId.getTextContent());
								kind = elementDocId.getTextContent();
							}

							if ("date".equals(name)) {
								patente.setDataPublicacao(this.formataData(elementDocId.getTextContent()));
							}
						}
					}
				}
			}
		}
		patente.setNumeroPublicacao(numeroPublicacao);
		patente.setNumeroPublicacaoEpodoc((country == null || country.isEmpty() ? "" : country) + numeroPublicacao);
		return patente;
	}

	private Patente obterApplicationReference(Element applicationReference, Patente patente) throws Exception {

		NodeList filhosApplicationReference = applicationReference.getElementsByTagName("document-id");
		Element documentId = (Element) filhosApplicationReference.item(0);

		String kind = "";
		String country = "";
		String numeroAplicacao = "";

		if ("document-id".equals(documentId.getNodeName())) {

			NodeList filhosDocumentId = documentId.getChildNodes();
			for (int i = 0; i < filhosDocumentId.getLength(); i++) {

				if (filhosDocumentId.item(i).getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) filhosDocumentId.item(i);
					String name = element.getNodeName();
					if ("country".equals(name)) {
						//patente.setPais(element.getTextContent());
						country = element.getTextContent();
					}

					if ("doc-number".equals(name)) {
						numeroAplicacao = element.getTextContent();
					}

					if ("kind".equals(name)) {
						patente.setKindCode(element.getTextContent());
						kind = element.getTextContent();
					}

					if ("date".equals(name)) {
						patente.setDataDeposito(this.formataData(element.getTextContent()));
					}
				}
			}
		}
		patente.setNumeroAplicacao((country == null || country.isEmpty() ? "" : country) + numeroAplicacao + (kind == null || kind.isEmpty() ? "" : kind));
		return patente;

	}

	private Patente obterPriorityClaim(Element priorityClaim, Patente patente) throws Exception {

		NodeList filhosPriorityClaim = priorityClaim.getElementsByTagName("document-id");
		Element documentId = (Element) filhosPriorityClaim.item(0);

		Prioridade prioridade = new Prioridade();
		List<Prioridade> listaPrioridade = new ArrayList<Prioridade>();

		String country = "";
		String sequence = priorityClaim.getAttribute("sequence");

		if ("document-id".equals(documentId.getNodeName())) {

			NodeList filhosDocumentId = documentId.getChildNodes();
			prioridade.setSequence(sequence);

			for (int i = 0; i < filhosDocumentId.getLength(); i++) {

				if (filhosDocumentId.item(i).getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) filhosDocumentId.item(i);
					String name = element.getNodeName();

					switch (name) {

					case "country":
						prioridade.setPais(element.getTextContent());
						country = element.getTextContent();
						break;
					case "doc-number":
						prioridade.setNumero((country == null || country.isEmpty() ? "" : country) + element.getTextContent());
						break;
					case "date":
						prioridade.setDataDeposito(this.formataData(element.getTextContent()));
						break;

					}
				}
			}
		}

		listaPrioridade.add(prioridade);
		patente.setPrioridades(listaPrioridade);
		return patente;
	}

	private Patente fazerParseExchangeDocumentParaCitacoes(Element exchangeDocument, Patente patente) {
		try {
			NodeList referencesCitedNodeList = exchangeDocument.getElementsByTagName("references-cited");
			if (referencesCitedNodeList != null && referencesCitedNodeList.getLength() >= 1) {

				Element referencesCited = (Element) referencesCitedNodeList.item(0).getChildNodes();

				NodeList citationsNodeList = referencesCited.getElementsByTagName("citation");
				if (citationsNodeList != null && citationsNodeList.getLength() >= 1) {
					List<Citacao> listaCitacoes = new ArrayList<Citacao>();
					listaCitacoes = obterCitacoes(citationsNodeList);
					patente.setCitacoes(listaCitacoes);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return patente;

	}

	private List<Citacao> obterCitacoes(NodeList nodeList) {
		List<Citacao> listaCitacoes = new ArrayList<Citacao>();
		NodeList filhosCategoria = null;
		NodeList filhosPatcit = null;
		NodeList filhosNplcit = null;

		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nodeList.item(i);
				/*String name = element.getAttribute("cited-by");
				if ("examiner".equals(name)) {*/

					Citacao citacao = new Citacao();

					filhosPatcit = element.getElementsByTagName("patcit");
					filhosNplcit = element.getElementsByTagName("nplcit");
					filhosCategoria = element.getElementsByTagName("category");

					getDocNumberKindCitacao(listaCitacoes, citacao, filhosPatcit, filhosNplcit, filhosCategoria);
				//}
			}
		}
		return listaCitacoes;

	}

	public void getDocNumberKindCitacao(List<Citacao> listaCitacoes, Citacao citacao, NodeList filhosPatcit, NodeList filhosNplcit, NodeList filhosCategoria) {

		if (filhosNplcit != null && filhosNplcit.getLength() >= 1) {
			for (int i = 0; i < filhosNplcit.getLength(); i++) {

				Element element = (Element) filhosNplcit.item(i);
				NodeList texto = element.getElementsByTagName("text");

				for (int p = 0; p < texto.getLength(); p++) {
					Element element2 = (Element) texto.item(p);

					citacao.setText(element2.getTextContent());
				}
			}

		}

		if (filhosCategoria != null && filhosCategoria.getLength() >= 1) {

			Element categoriaElement = (Element) filhosCategoria.item(0);

			String categoria = categoriaElement.getTextContent().toString();

			if (isText(categoria)) {

				citacao.setCategoria(categoriaElement.getTextContent());

				if (filhosPatcit != null && filhosPatcit.getLength() >= 1) {

					for (int i = 0; i < filhosPatcit.getLength(); i++) {

						Element element = (Element) filhosPatcit.item(i);
						NodeList documento = element.getElementsByTagName("document-id");

						for (int p = 0; p < documento.getLength(); p++) {
							Element element2 = (Element) documento.item(p);

							String name = element2.getAttribute("document-id-type");
							if ("docdb".equals(name)) {

								NodeList pais = element.getElementsByTagName("country");
								for (int a = 0; a < pais.getLength(); a++) {
									Element ele = (Element) pais.item(a);
									String paisTexto = ele.getTextContent();

									if (!(paisTexto.trim().isEmpty())) {
										citacao.setPais(ele.getTextContent());
									}

								}

								NodeList doc = element.getElementsByTagName("doc-number");
								for (int a = 0; a < doc.getLength(); a++) {
									Element ele = (Element) doc.item(a);
									citacao.setDocNumber(ele.getTextContent());
								}

								NodeList kind = element.getElementsByTagName("kind");
								for (int a = 0; a < kind.getLength(); a++) {
									Element ele = (Element) kind.item(a);
									citacao.setKind(ele.getTextContent());
								}

							}

						}

					}
				}

			}

		}

		listaCitacoes.add(citacao);
	}

	public Date formataData(String data) throws Exception {
		if (data == null || data.equals(""))
			return null;

		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date d = null;
		try {
			d = sdf.parse(data);

		} catch (Throwable e) {
			e.printStackTrace();
		}

		return d;
	}

	private boolean isText(String texto) {

		String regex = "^[a-zA-Z]+$";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(texto);

		return matcher.matches();

	}

}
