package br.gov.inpi.epec.mb;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.http.client.ClientProtocolException;
import org.xml.sax.SAXException;

import br.gov.inpi.epec.beans.DiagramaFamilia;
import br.gov.inpi.epec.entities.Familia;
import br.gov.inpi.epec.service.impl.helper.FamiliaHelper;
import br.gov.inpi.epec.util.RelatorioUtil;

/**
 * Classe responsavel por passar os parametros para o relatorio que exibe o 
 * diagrama de familia. Todos os dados do diagrama de familia sao provenientes do XML
 * que e tratado na classe FamiliaHelper. Esta classe apenas separa os dados desse XML
 * em uma lista para posteriormente ser exibido atraves de uma Bean no relatorio. 
 * Cada posicao da lista e referente a um elemento do relatorio, e para os dados do relatorio
 * serem exibidos corretamente e necessario respeitar essa ordem, contendo: imagem padrao com 
 * fundo branco para um pedido que nao contenha pais, estando este elemento sempre na posicao 0,
 * deposito com numero do pedido e data, publicacao contendo numero do pedido e data, 
 * uma flag para verificar se o pedido tem relacao direta com a prioridade em questao e a imagem do pais. 
 * @author ahlucena
 *
 */
@ManagedBean
@ViewScoped
public class DiagramaFamiliaMB extends AbstractMB {

DiagramaFamilia diagramaFamilia;
	
	List<Familia> listaFamilia = new ArrayList<Familia>();
	List<DiagramaFamilia> listaDiagramaFamilia;
	
	
	
	RelatorioUtil relatorioUtil = new RelatorioUtil();
	JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaDiagramaFamilia);
	
	public void carregaDiagramaFamilia(List<Familia> listaFamilia, FamiliaHelper helper) throws ClientProtocolException, IOException, SAXException{
		
			try {
				
				this.getParametrosDiagrama(listaFamilia, helper);
				this.dataSource = new JRBeanCollectionDataSource(this.listaDiagramaFamilia);
				
			} catch (Exception e) {

				if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
					displayErrorMessageToUser("Document Not Found");
				} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
					displayErrorMessageToUser("Document Not Found");
				} else {
					displayErrorMessageToUser("Documento não Encontrado");
				}
			}
			
			relatorioUtil.gerarRelatorioPdf("ReportDiagramaFamilia", "DiagramaFamilia", dataSource);
		
		
		
	}
	
	public List<DiagramaFamilia> getParametrosDiagrama(List<Familia> listaFamilia, FamiliaHelper helper) throws Exception{
		
		FacesContext context = FacesContext.getCurrentInstance();
		DiagramaFamilia diagramaFamilia = new DiagramaFamilia();
		int contador = 0;
		List<Object> listaAuxiliar = new ArrayList<Object>();
		
		this.listaDiagramaFamilia = new ArrayList<DiagramaFamilia>();
		
		for (Familia familia : listaFamilia) {
			
			if (contador == 0) { //resgata a primeira prioridade e a imagem padrao
				
				String fundoBranco = context.getExternalContext().getRealPath("/pages/images/logo_epec.png");
				listaAuxiliar.add(fundoBranco);
				
				listaAuxiliar.add(helper.getDocNumber());
				listaAuxiliar.add(this.formataData(familia.getPatente().getPrioridades().get(0).getDataDeposito()));
			}
			
			listaAuxiliar.add(familia.getPatente().getNumeroAplicacao());
			listaAuxiliar.add(this.formataData(familia.getPatente().getDataDeposito()));
			listaAuxiliar.add(familia.getPatente().getNumeroPublicacaoEpodoc()+familia.getPatente().getKindCodePublicacao());
			listaAuxiliar.add(this.formataData(familia.getPatente().getDataPublicacao()));
			listaAuxiliar.add(familia.isPrincipal());
			
			if (familia.getPatente().getPais() != null) {
			
				String caminhoImagem = context.getExternalContext().getRealPath("/pages/images/_bandeirasP/" + familia.getPatente().getPais()
																			 	+ ".png");
				File imagem = new File(caminhoImagem);
				
				if (imagem.exists() && !imagem.isDirectory()) {	

					listaAuxiliar.add(caminhoImagem);
				} else {
					
					String fundoBranco = context.getExternalContext().getRealPath("/pages/images/logo_epec.png");
					listaAuxiliar.add(fundoBranco);
				}
			}  else {
				
				String fundoBranco = context.getExternalContext().getRealPath("/pages/images/logo_epec.png");
				listaAuxiliar.add(fundoBranco);
			}
			
			contador++;
			
			if (contador == 20 || contador == listaFamilia.size()) { //delimita a quantidade maxima de nos por pagina 
				
				diagramaFamilia.setListaDiagramaFamilia(listaAuxiliar);
				this.listaDiagramaFamilia.add(diagramaFamilia);
				contador = 0;
				diagramaFamilia = new DiagramaFamilia();
				listaAuxiliar = new ArrayList<Object>();
			}
		}
		
		return this.listaDiagramaFamilia;
	}
	
	public String formataData(Date dataFormatada) throws Exception {
		if (dataFormatada == null || dataFormatada.equals(""))
			return null;

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String sData = "";
		try {
			
			sData = dateFormat.format(dataFormatada);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return sData;
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
}
