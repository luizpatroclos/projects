package br.gov.inpi.epec.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.gov.inpi.epec.beans.Tbcadentidade;
import br.gov.inpi.epec.beans.Tbcadpais;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbclassificacao;
import br.gov.inpi.epec.beans.Tbclassificacaopatente;
import br.gov.inpi.epec.beans.TbclassificacaopatentePK;
import br.gov.inpi.epec.beans.Tbdepositante;
import br.gov.inpi.epec.beans.Tbdepositantepatente;
import br.gov.inpi.epec.beans.Tbfamiliaec;
import br.gov.inpi.epec.beans.Tbinventor;
import br.gov.inpi.epec.beans.Tbinventorpatente;
import br.gov.inpi.epec.beans.Tbpatenteec;
import br.gov.inpi.epec.beans.Tbprioridadeec;
import br.gov.inpi.epec.facade.ConsultaServiceFacade;
import br.gov.inpi.epec.facade.EpecServiceFacade;

@Stateless(name = "ConsultaPedidosServiceImpl")
public class ConsultaPedidosServiceImpl implements ConsultaServiceFacade {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ConsultaPedidosServiceImpl.class);

	@PersistenceContext(unitName = "epec_mysql")
	protected EntityManager entityManager;

	@EJB
	public EpecServiceFacade persist;

	private HttpSession session;

	public Tbcadusuario usuarioLogado;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String retornaXML(String tipo, String valor, String codPedido) throws ClientProtocolException, IOException {

		String url = null;
		String erro1 = "HTTP Status 404";
		String erro2 = "not found";
		// "http://javaproducao.inpi.gov.br:8080/epows/"
		// "http://javadesenvolvimento.inpi.gov.br:8080/EpoWs/"
		String servidor = "http://javadesenvolvimento.inpi.gov.br:8080/EpoWs/";

		// Solicitação Busca por Pedido
		if ("1".equals(tipo)) {

			if ("epodoc".equals(valor)) {

				url = servidor + "pesquisa/ops/?uri=/published-data/application/epodoc/" + codPedido + "/equivalents";

			} else {

				url = servidor + "pesquisa/ops/?uri=/published-data/publication/epodoc/" + codPedido + "/biblio";

			}

		}

		// Solicitacao Busca por Publicacao
		if ("2".equals(tipo)) {

			// Solicitação Busca por Publicação
			// if ("2".equals(tipo) & "epodoc".equals(valor)) {
			if ("2".equals(tipo)) {

				if ("epodoc".equals(valor)) {

					url = servidor + "pesquisa/ops/?uri=/published-data/publication/epodoc/" + codPedido + "/equivalents";

				} else if ("docdb".equals(valor)) {

					url = servidor + "pesquisa/ops/?uri=/published-data/publication/docdb/" + codPedido + "/equivalents";

				} else {

					url = servidor + "pesquisa/ops/?uri=/published-data/publication/epodoc/" + codPedido + "/biblio";

				}

			}

		}

		// Solicitacao Busca por Prioridade
		if ("3".equals(tipo)) {

			if ("epodoc".equals(valor)) {

				url = servidor + "pesquisa/ops/?uri=/published-data/search/?q=pr%3D" + codPedido;

			} else if ("priority".equals(valor)) {

				url = servidor + "pesquisa/ops/?uri=/published-data/publication/docdb/" + codPedido + "/equivalents";

			} else {

				url = servidor + "pesquisa/ops/?uri=/published-data/publication/epodoc/" + codPedido + "/biblio";
			}

		}
		// Solicitação dados completos da família

		if ("4".equals(tipo)) {

			url = servidor + "pesquisa/ops/?uri=family/publication/epodoc/" + codPedido + "/biblio";

		}

		String xmlResult = null;

		HttpClient client = new DefaultHttpClient();

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		xmlResult = rd.readLine();
		
		if(xmlResult == null){
			xmlResult = erro1;
		}else if (xmlResult.toLowerCase().contains(erro1.toLowerCase()) || xmlResult.toLowerCase().contains(erro2.toLowerCase())) {
			xmlResult = erro1;
		}

		return xmlResult;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<String> cerregarPaisesValidos(String valor, List<Tbcadpais> paises, String tipo) throws IOException {

		List<String> pedidosValidos = null;

		try {

			Document doc = loadXMLFrom(valor);

			// Passo 1: obter o elemento raiz
			Element raiz = doc.getDocumentElement();

			List<String> auxPaises = new ArrayList<String>();

			for (Tbcadpais pais : paises) {

				auxPaises.add(pais.getStrCodPais());

			}

			// Passo 2: localizar os elementos filhos do xml
			NodeList listDoc = null;
			NodeList listDoc1 = null;

			pedidosValidos = new ArrayList<String>();

			if ("3".equals(tipo)) {

				listDoc = raiz.getElementsByTagName("country");

				listDoc1 = raiz.getElementsByTagName("doc-number");

				for (int i = 0; i < listDoc.getLength(); i++) {

					Element auxPais = (Element) listDoc.item(i);
					Element auxPed = (Element) listDoc1.item(i);

					String pais = auxPais.getTextContent();
					String ped = auxPed.getTextContent();

					String mainPedido = pais + ped;

					if ((auxPaises.contains(pais)) && (!verificaPedido(mainPedido, "3"))) {

						pedidosValidos.add(mainPedido);

					} else {

						System.out.println(" Pais não correspondente do EPEC ou Familia já registrada = " + mainPedido);

					}
				}

			} else {

				listDoc = raiz.getElementsByTagName("doc-number");

				for (int i = 1; i < listDoc.getLength(); i++) {

					Element docAux = (Element) listDoc.item(i);

					String valor1 = docAux.getTextContent();

					String pais = valor1.substring(0, 2);

					if ((auxPaises.contains(pais)) && (!verificaPedido(valor1, tipo))) {

						pedidosValidos.add(valor1);

					} else {

						System.out.println(" Pais não correspondente do EPEC ou Familia já registrada = " + valor1);

					}

				}

			}

			System.out.println("Paises Válidos para o EPEC " + pedidosValidos);

		} catch (SAXException e) {
			e.printStackTrace();
		}

		return pedidosValidos;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Tbfamiliaec verificarFamiliaPedidos(String valor, List<Tbcadpais> paises, String tipo) throws IOException {

		List<String> pedidosValidos = null;

		Tbfamiliaec familia = null;

		try {

			Document doc = loadXMLFrom(valor);

			// Passo 1: obter o elemento raiz
			Element raiz = doc.getDocumentElement();

			List<String> auxPaises = new ArrayList<String>();

			for (Tbcadpais pais : paises) {

				auxPaises.add(pais.getStrCodPais());

			}

			// Passo 2: localizar os elementos filhos do xml
			NodeList listDoc = null;
			NodeList listDoc1 = null;

			pedidosValidos = new ArrayList<String>();

			if ("3".equals(tipo)) {

				listDoc = raiz.getElementsByTagName("country");

				listDoc1 = raiz.getElementsByTagName("doc-number");

				for (int i = 0; i < listDoc.getLength(); i++) {

					Element auxPais = (Element) listDoc.item(i);
					Element auxPed = (Element) listDoc1.item(i);

					String pais = auxPais.getTextContent();
					String ped = auxPed.getTextContent();

					if (pais != null) {

						pedidosValidos.add(pais + ped);

					} else {

						System.out.println(" Pais não correspondente do EPEC = " + pais);

					}
				}

			} else {

				listDoc = raiz.getElementsByTagName("doc-number");

				for (int i = 1; i < listDoc.getLength(); i++) {

					Element docAux = (Element) listDoc.item(i);

					String valor1 = docAux.getTextContent();

					String pais = valor1.substring(0, 2);

					if ((auxPaises.contains(pais)) && (verificaPedido(valor1, "2"))) {

						String pedidoTratado = null;

						Tbpatenteec patentes = null;
						
					
						pedidoTratado = valor1.substring(2);
						patentes = persist.buscaPatentePorPublicacao(pedidoTratado);
						
						
						if(patentes == null){
							patentes = persist.findPatentePorEpoDoc(pedidoTratado);
						}

						return familia = patentes.getIdFamiliaEc();

					} else {

						System.out.println(" Pais não correspondente do EPEC ou Familia já registrada = " + valor1);

					}

				}

			}

		} catch (SAXException e) {
			e.printStackTrace();
		}

		return familia;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void convertXMLFamilia(String xml, Tbpatenteec patente) throws Exception {

		Document doc = loadXMLFrom(xml);

		// Passo 1: obter o elemento raiz
		Element raiz1 = doc.getDocumentElement();

		NodeList nzero = raiz1.getElementsByTagName("exchange-documents");

		Element ezero = (Element) nzero.item(0);

		NodeList documentzero = ezero.getElementsByTagName("exchange-document");

		Element raiz = null;

		if (documentzero != null && documentzero.getLength() > 0) {

			// respons�vel por trazer sempre o �ltimo
			for (int i = 0; i < documentzero.getLength(); i++) {

				raiz = (Element) documentzero.item(i);

				String typezero = raiz.getAttribute("kind");

			}
		}

		// Passo 2: Pega Tag Publication
		/*--------------------------------*/
		NodeList n0 = raiz.getElementsByTagName("publication-reference");

		Element e0 = (Element) n0.item(0);

		NodeList documentx = e0.getElementsByTagName("document-id");

		if (documentx != null && documentx.getLength() > 0) {
			for (int i = 0; i < documentx.getLength(); i++) {

				Element documentId = (Element) documentx.item(i);

				String type = documentId.getAttribute("document-id-type");

				if ("docdb".equals(type)) {

					String countryDb = getTextValue(documentId, "country");

					if (!countryDb.isEmpty())
						patente.setStrPubPaisDocdb(countryDb);

					String docNumber = getTextValue(documentId, "doc-number");

					if (!docNumber.isEmpty())
						patente.setStrPubNumDocdb(docNumber);

					String kind = getTextValue(documentId, "kind");

					if (!kind.isEmpty())
						patente.setStrPubKindDocdb(kind);

					String date = getTextValue(documentId, "date");

					if (!date.isEmpty())
						patente.setDtPublicacao(formataData(date));

				}

			}
		}

		// Passo 3: Pega Tag Application
		/*--------------------------------*/
		NodeList n1 = raiz.getElementsByTagName("application-reference");

		Element e1 = (Element) n1.item(0);

		NodeList document = e1.getElementsByTagName("document-id");

		if (document != null && document.getLength() > 0) {
			for (int i = 0; i < document.getLength(); i++) {

				Element documentId = (Element) document.item(i);

				String type = documentId.getAttribute("document-id-type");

				if ("docdb".equals(type)) {

					String countryDb = getTextValue(documentId, "country");

					if (!countryDb.isEmpty())
						patente.setStrPedPaisDocdb(countryDb);

					String docNumber = getTextValue(documentId, "doc-number");

					if (!docNumber.isEmpty())
						patente.setStrPedNumDocdb(docNumber);

					String kind = getTextValue(documentId, "kind");

					if (!kind.isEmpty())
						patente.setStrPedKindDocdb(kind);

				} else if ("epodoc".equals(type)) {

					String epodocDocNum = getTextValue(documentId, "doc-number");

					if (!epodocDocNum.isEmpty())
						patente.setStrPedNumEpodoc(epodocDocNum);

					String epodocDate = getTextValue(documentId, "date");

					if (!epodocDate.isEmpty())
						patente.setDtDeposito(formataData(epodocDate));

				} else if ("original".equals(type)) {

					String originalDocNum1 = getTextValue(documentId, "doc-number");

					if (!originalDocNum1.isEmpty())
						patente.setStrPedNumOriginal(originalDocNum1);

				}

			}
		}

		// Passo 4: Pega Tag Título
		/*--------------------------------*/

		String title = getTextValue(raiz, "invention-title");

		if (!title.isEmpty())
			patente.setTxTitulo(title);

		// Passo 5: Pega Tag Resumo
		/*--------------------------------*/
		NodeList n9 = raiz.getElementsByTagName("abstract");

		Element e9 = (Element) n9.item(0);
		
		String resumo = "";
		
		if (e9 != null) {

			resumo = resumo + getTodosValores(e9, "p");

			if (!resumo.isEmpty())
				patente.setTxResumo(resumo);

			
			}
	

		persist.save(patente);

		// Passo 6: Pega Tag Prioridade
		/*--------------------------------*/
		NodeList n2 = raiz.getElementsByTagName("priority-claims");

		Element e2 = (Element) n2.item(0);

		NodeList documenty = e2.getElementsByTagName("document-id");

		List<Tbprioridadeec> prioridades = new ArrayList<Tbprioridadeec>();

		Tbprioridadeec prioridade = null;

		if (documenty != null && documenty.getLength() > 0) {

			for (int i = 0; i < documenty.getLength(); i++) {

				Element documentId = (Element) documenty.item(i);

				prioridade = new Tbprioridadeec();

				String type = documentId.getAttribute("document-id-type");

				if ("epodoc".equals(type)) {

					String epodocDocNum = getTextValue(documentId, "doc-number");

					if (!epodocDocNum.isEmpty())
						prioridade.setStrPrioridade(epodocDocNum);

					String epodocDate = getTextValue(documentId, "date");

					if (!epodocDate.isEmpty())
						prioridade.setDtDeposito(formataData(epodocDate));

					prioridade.setIdPatenteEc(patente);

					persist.save(prioridade);

					prioridades.add(prioridade);

				}
			}

			patente.setTbprioridadeecList(prioridades);
		}

		// Passo 7: Pega Tag Depositante
		/*--------------------------------*/
		NodeList n3 = raiz.getElementsByTagName("applicants");
		NodeList documentz = null;

		Element e3 = (Element) n3.item(0);

		if (e3 != null) {

			documentz = e3.getElementsByTagName("applicant");

		}

		Tbdepositante depositante = null;
		Tbdepositantepatente depositantePatente = null;

		if (documentz != null && documentz.getLength() > 0) {

			for (int i = 0; i < documentz.getLength(); i++) {

				Element documentId = (Element) documentz.item(i);

				String type = documentId.getAttribute("data-format");

				if ("original".equals(type)) {

					String name = getTextValue(documentId, "name");

					Tbdepositante depositanteAux = persist.findDepositantePorNome(name);

					if (depositanteAux != null) {

						depositantePatente = new Tbdepositantepatente();

						depositantePatente.setIdDepositante(depositanteAux.getIdDepositante());
						depositantePatente.setIdPatenteEc(patente.getIdPatenteEc());

						persist.save(depositantePatente);

					} else {

						depositante = new Tbdepositante();
						depositantePatente = new Tbdepositantepatente();

						if (!name.isEmpty())
							depositante.setTxDepositante(name);

						persist.save(depositante);

						depositantePatente.setIdDepositante(depositante.getIdDepositante());
						depositantePatente.setIdPatenteEc(patente.getIdPatenteEc());

						persist.save(depositantePatente);

					}
				}
			}

		}

		// Passo 8: Pega Tag Inventores'
		/*--------------------------------*/
		NodeList n4 = raiz.getElementsByTagName("inventors");

		Element e4 = (Element) n4.item(0);

		if(e4 != null){
			NodeList documentw = e4.getElementsByTagName("inventor");

			Tbinventor inventor = null;

			Tbinventorpatente inventorPatente = null;
			
			if (documentw != null && documentw.getLength() > 0) {

				for (int i = 0; i < documentw.getLength(); i++) {

					Element documentId = (Element) documentw.item(i);

					String type = documentId.getAttribute("data-format");

					if ("epodoc".equals(type)) {

						String name = getTextValue(documentId, "name");

						Tbinventor inventorAux = persist.findInventorPorNome(name.trim());

						if (inventorAux != null) {

							inventorPatente = new Tbinventorpatente();

							inventorPatente.setIdInventor(inventorAux.getIdInventor());
							inventorPatente.setIdPatenteEc(patente.getIdPatenteEc());

							persist.save(inventorPatente);

						} else {

							inventor = new Tbinventor();
							inventorPatente = new Tbinventorpatente();

							if (!name.isEmpty())
								inventor.setTxInventor(name.trim());

							persist.save(inventor);

							inventorPatente.setIdInventor(inventor.getIdInventor());
							inventorPatente.setIdPatenteEc(patente.getIdPatenteEc());

							persist.save(inventorPatente);

						}
					}
				}

			}
		}
		
		



		// Passo 9: Pega Tag Classificação
		/*--------------------------------*/
		NodeList listDoc = raiz.getElementsByTagName("classification-ipcr");

		Tbclassificacao classificacao = null;

		Tbclassificacaopatente classificacaoPatenteBean = null;

		TbclassificacaopatentePK classificacaoPatentePK = null;

		for (int i = 0; i < listDoc.getLength(); i++) {

			Element docAux = (Element) listDoc.item(i);

			String sequence = docAux.getAttribute("sequence");

			String classificacaoXML = docAux.getTextContent();

			Tbclassificacao classificacaoAux = persist.findClassificacaoPorNome(classificacaoXML);

			if (classificacaoAux != null) {

				classificacaoPatenteBean = new Tbclassificacaopatente();

				classificacaoPatentePK = new TbclassificacaopatentePK();

				classificacaoPatentePK.setIdClassificacao(classificacaoAux.getIdClassificacao());
				classificacaoPatentePK.setIdPatenteEc(patente.getIdPatenteEc());

				classificacaoPatenteBean.setIdOrdem(Long.parseLong(sequence));

				classificacaoPatenteBean.setTbclassificacaopatentePK(classificacaoPatentePK);

				persist.save(classificacaoPatenteBean);

			} else {

				classificacao = new Tbclassificacao();

				classificacaoPatenteBean = new Tbclassificacaopatente();

				classificacaoPatentePK = new TbclassificacaopatentePK();

				classificacao.setTxClassificacao(classificacaoXML);

				persist.save(classificacao);

				classificacaoPatentePK.setIdClassificacao(classificacao.getIdClassificacao());
				classificacaoPatentePK.setIdPatenteEc(patente.getIdPatenteEc());

				classificacaoPatenteBean.setIdOrdem(Long.parseLong(sequence));

				classificacaoPatenteBean.setTbclassificacaopatentePK(classificacaoPatentePK);

				persist.save(classificacaoPatenteBean);

			}

		}

	}

	public static org.w3c.dom.Document loadXMLFrom(String xml) throws org.xml.sax.SAXException, java.io.IOException {
		return loadXMLFrom(new java.io.ByteArrayInputStream(xml.trim().getBytes("UTF8")));
	}

	public static org.w3c.dom.Document loadXMLFrom(java.io.InputStream is) throws org.xml.sax.SAXException, java.io.IOException {
		javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		javax.xml.parsers.DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (javax.xml.parsers.ParserConfigurationException ex) {
		}
		org.w3c.dom.Document doc = builder.parse(is);
		is.close();
		return doc;
	}

	private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
	
	private static String getTodosValores(Element ele, String tagName){
		String textVal = "";
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			for(int i = 0; i< nl.getLength(); i++){
				Element el = (Element) nl.item(i);
				textVal = textVal + el.getFirstChild().getNodeValue();
			}
			
			
		}

		return textVal;
	}
	

	public static Date formataData(String data) throws Exception {
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

	public Tbcadentidade buscaEntidadeByIdPais(List<Tbcadpais> paises, String pedido, Tbcadusuario tbcadusuario) {

		String paisPedido = pedido.substring(0, 2);

		Tbcadentidade entidade = null;

		for (Tbcadpais pais : paises) {

			if (paisPedido.equals(pais.getStrCodPais())) {
				entidade = persist.findEntidadePorIdPais(pais.getIdPais(), tbcadusuario);

				return entidade;
			}

		}

		return null;
	}

	@Override
	public boolean verificaPedido(String pedido, String tipo) {

		String pedidoTratado = null;
		boolean ok = false;
		Tbpatenteec patente = null;
		
		try {
		if ("2".equals(tipo)) {
			pedidoTratado = pedido.substring(2);
			 patente= persist.buscaPatentePorPublicacao(pedidoTratado);

		}else if("1".equals(tipo)){
			pedidoTratado = pedido.substring(2);
			patente= persist.buscaPatentePorPublicacao(pedidoTratado);
		} else {
			pedidoTratado = pedido;
			 patente= persist.buscaPatentePorEpodoc(pedidoTratado);

		}
			if(patente == null){
				ok = false;
			}else{
				ok= true;
			}


		} catch (Exception e) {
		}

		return ok;
	}

	public List<Tbpatenteec> buscaFamiliaPatente(String pedido, String tipo) {

		List<Tbpatenteec> patentes = new ArrayList<Tbpatenteec>();

		String pedidoTratado = null;
		String pais = null; 
		if (("2".equals(tipo))) {
			pedidoTratado = pedido.substring(2);
			pais = pedido.substring(0, 2);
			
		} else {
			pedidoTratado = pedido;
		}

		try {
			if (tipo.equals("3")) {
				patentes = persist.buscaPatentesPrioridade(pedidoTratado);
			}else  if(tipo.equals("1")){
				patentes = persist.buscaPatentesPorPedido(pedidoTratado);
				
			} else if(tipo.equals("2")){
				patentes = persist.buscaPatentesPorPublicacao(pedidoTratado, pais );
			}else {
				patentes = persist.buscaPatentes(pedidoTratado);
			}

		} catch (Exception e) {
		}

		return patentes;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterarPedidoXML(String xml, Tbpatenteec patente) throws Exception {
		Long id = patente.getIdPatenteEc();
		patente.setIdPatenteEc(id);
		Document doc = loadXMLFrom(xml);

		// TODO
		// Passo 1: obter o elemento raiz
		// Element raiz = doc.getDocumentElement();

		Element raiz1 = doc.getDocumentElement();

		NodeList nzero = raiz1.getElementsByTagName("exchange-documents");

		Element ezero = (Element) nzero.item(0);

		NodeList documentzero = ezero.getElementsByTagName("exchange-document");

		Element raiz = null;

		if (documentzero != null && documentzero.getLength() > 0) {

			// respons�vel por trazer sempre o �ltimo
			for (int i = 0; i < documentzero.getLength(); i++) {

				raiz = (Element) documentzero.item(i);

				String typezero = raiz.getAttribute("kind");

			}
		}

		// Passo 2: Pega Tag Publication
		/*--------------------------------*/
		NodeList n0 = raiz.getElementsByTagName("publication-reference");

		Element e0 = (Element) n0.item(0);

		NodeList documentx = e0.getElementsByTagName("document-id");

		if (documentx != null && documentx.getLength() > 0) {
			for (int i = 0; i < documentx.getLength(); i++) {

				Element documentId = (Element) documentx.item(i);

				String type = documentId.getAttribute("document-id-type");

				if ("docdb".equals(type)) {

					String countryDb = getTextValue(documentId, "country");

					if (!countryDb.isEmpty())
						patente.setStrPubPaisDocdb(countryDb);

					String docNumber = getTextValue(documentId, "doc-number");

					if (!docNumber.isEmpty())
						patente.setStrPubNumDocdb(docNumber);

					String kind = getTextValue(documentId, "kind");

					if (!kind.isEmpty())
						patente.setStrPubKindDocdb(kind);

					String date = getTextValue(documentId, "date");

					if (date != null) {
						if (!date.isEmpty())
							patente.setDtPublicacao(formataData(date));
					}

				}

			}
		}

		// Passo 3: Pega Tag Application
		/*--------------------------------*/
		NodeList n1 = raiz.getElementsByTagName("application-reference");

		Element e1 = (Element) n1.item(0);

		NodeList document = e1.getElementsByTagName("document-id");

		if (document != null && document.getLength() > 0) {
			for (int i = 0; i < document.getLength(); i++) {

				Element documentId = (Element) document.item(i);

				String type = documentId.getAttribute("document-id-type");

				if ("docdb".equals(type)) {

					String countryDb = getTextValue(documentId, "country");

					if (!countryDb.isEmpty())
						patente.setStrPedPaisDocdb(countryDb);

					String docNumber = getTextValue(documentId, "doc-number");

					if (!docNumber.isEmpty())
						patente.setStrPedNumDocdb(docNumber);

					String kind = getTextValue(documentId, "kind");

					if (!kind.isEmpty())
						patente.setStrPedKindDocdb(kind);

				} else if ("epodoc".equals(type)) {

					String epodocDocNum = getTextValue(documentId, "doc-number");

					if (!epodocDocNum.isEmpty())
						patente.setStrPedNumEpodoc(epodocDocNum);

					String epodocDate = getTextValue(documentId, "date");

					if (!epodocDate.isEmpty())
						patente.setDtDeposito(formataData(epodocDate));

				} else if ("original".equals(type)) {

					String originalDocNum1 = getTextValue(documentId, "doc-number");

					if (!originalDocNum1.isEmpty())
						patente.setStrPedNumOriginal(originalDocNum1);

				}

			}
		}

		// Passo 4: Pega Tag Título
		/*--------------------------------*/

		String title = getTextValue(raiz, "invention-title");

		if (!title.isEmpty())
			patente.setTxTitulo(title);

		// Passo 5: Pega Tag Resumo
		/*--------------------------------*/
		NodeList n9 = raiz.getElementsByTagName("abstract");
		String resumo = "";	

		Element e9 = (Element) n9.item(0);

		if (e9 != null) {

		resumo = resumo + getTodosValores(e9, "p");

		if (!resumo.isEmpty())
			patente.setTxResumo(resumo);

		
		}

		persist.merge(patente);

		// Passo 6: Pega Tag Prioridade
		/*--------------------------------*/
		NodeList n2 = raiz.getElementsByTagName("priority-claims");

		Element e2 = (Element) n2.item(0);

		if (e2 != null) {
			NodeList documenty = e2.getElementsByTagName("document-id");

			List<Tbprioridadeec> prioridades = new ArrayList<Tbprioridadeec>();

			Tbprioridadeec prioridade = null;

			if (documenty != null && documenty.getLength() > 0) {

				for (int i = 0; i < documenty.getLength(); i++) {

					Element documentId = (Element) documenty.item(i);

					prioridade = new Tbprioridadeec();

					String type = documentId.getAttribute("document-id-type");

					if ("epodoc".equals(type)) {

						String epodocDocNum = getTextValue(documentId, "doc-number");

						if (!epodocDocNum.isEmpty())
							prioridade.setStrPrioridade(epodocDocNum);

						String epodocDate = getTextValue(documentId, "date");

						if (!epodocDate.isEmpty())
							prioridade.setDtDeposito(formataData(epodocDate));

						prioridade.setIdPatenteEc(patente);
						persist.merge(prioridade);

						prioridades.add(prioridade);

					}
				}

				patente.setTbprioridadeecList(prioridades);
			}

		}

		// Passo 7: Pega Tag Depositante
		/*--------------------------------*/
		NodeList n3 = raiz.getElementsByTagName("applicants");
		Element e3 = (Element) n3.item(0);

		if (e3 != null) {
			NodeList documentz = e3.getElementsByTagName("applicant");
			Tbdepositante depositante = null;
			Tbdepositantepatente depositantePatente = null;

			if (documentz != null && documentz.getLength() > 0) {
				persist.deleteDepositantePatenteTodos(id);

				for (int i = 0; i < documentz.getLength(); i++) {
					Element documentId = (Element) documentz.item(i);
					String type = documentId.getAttribute("data-format");

					if ("original".equals(type)) {
						String name = getTextValue(documentId, "name");
						String depositanteXML = documentId.getTextContent();
						Tbdepositante depositanteAux = persist.findDepositantePorNome(name);

						if (depositanteAux != null) {
							depositantePatente = new Tbdepositantepatente();
							depositantePatente.setIdDepositante(depositanteAux.getIdDepositante());
							depositantePatente.setIdPatenteEc(id);
							persist.save(depositantePatente);

						} else {
							depositante = new Tbdepositante();
							depositantePatente = new Tbdepositantepatente();
							if (!name.isEmpty())
								depositante.setTxDepositante(name);
							persist.save(depositante);

							depositantePatente.setIdDepositante(depositante.getIdDepositante());
							depositantePatente.setIdPatenteEc(id);
							persist.save(depositantePatente);

						}
					}
				}

			}
		}

		// Passo 8: Pega Tag Inventores
		/*--------------------------------*/
		NodeList n4 = raiz.getElementsByTagName("inventors");
		Element e4 = (Element) n4.item(0);
		if (e4 != null) {
			NodeList documentw = e4.getElementsByTagName("inventor");
			Tbinventor inventor = null;
			Tbinventorpatente inventorPatente = null;

			if (documentw != null && documentw.getLength() > 0) {
				persist.deleteInventorPatenteTodos(id);

				for (int i = 0; i < documentw.getLength(); i++) {
					Element documentId = (Element) documentw.item(i);
					String type = documentId.getAttribute("data-format");
					if ("epodoc".equals(type)) {
						String inventorXML = documentId.getTextContent();
						Tbinventor inventorAux = persist.findInventorPorNome(inventorXML.trim());
						String name = getTextValue(documentId, "name");

						if (inventorAux != null) {
							inventorPatente = new Tbinventorpatente();
							inventorPatente.setIdInventor(inventorAux.getIdInventor());
							inventorPatente.setIdPatenteEc(id);
							persist.save(inventorPatente);

						} else {
							inventor = new Tbinventor();
							inventorPatente = new Tbinventorpatente();
							if (!name.isEmpty()) {
								inventor.setTxInventor(name.trim());
								persist.save(inventor);
							}

							inventorPatente.setIdInventor(inventor.getIdInventor());
							inventorPatente.setIdPatenteEc(id);
							persist.save(inventorPatente);

						}
					}
				}

			}
		}

		// Passo 9: Pega Tag Classificação
		/*--------------------------------*/
		NodeList listDoc = raiz.getElementsByTagName("classification-ipcr");
		Tbclassificacao classificacao = null;
		Tbclassificacaopatente classificacaoPatenteBean = null;
		TbclassificacaopatentePK classificacaoPatentePK = null;

		for (int i = 0; i < listDoc.getLength(); i++) {
			Element docAux = (Element) listDoc.item(i);

			String sequence = docAux.getAttribute("sequence");

			String classificacaoXML = docAux.getTextContent();
			Tbclassificacao classificacaoAux = persist.findClassificacaoPorNome(classificacaoXML);

			if (classificacaoAux != null) {

				persist.delete(classificacaoAux);
			}

			classificacao = new Tbclassificacao();
			classificacaoPatenteBean = new Tbclassificacaopatente();
			classificacaoPatentePK = new TbclassificacaopatentePK();
			classificacao.setTxClassificacao(classificacaoXML);
			persist.save(classificacao);

			classificacaoPatentePK.setIdClassificacao(classificacao.getIdClassificacao());
			classificacaoPatentePK.setIdPatenteEc(id);
			classificacaoPatenteBean.setIdOrdem(Long.parseLong(sequence));
			classificacaoPatenteBean.setTbclassificacaopatentePK(classificacaoPatentePK);
			persist.save(classificacaoPatenteBean);
		}

	}

	@Override
	public boolean verificarXmL(String xml) throws Exception {
		Document doc = loadXMLFrom(xml);

		Element raiz1 = doc.getDocumentElement();

		
		if(raiz1.getLocalName().equals("fault")){
			return true;
		}
		
		return false;
	}
}
