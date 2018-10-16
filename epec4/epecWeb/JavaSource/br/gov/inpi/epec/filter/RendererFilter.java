package br.gov.inpi.epec.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.charset.Charset;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.StreamedContent;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.gov.inpi.epec.util.HtmlEntities;

import com.lowagie.text.DocumentException;

public class RendererFilter implements Filter {

	FilterConfig config;

	private DocumentBuilder documentBuilder;

	private StreamedContent file;

	public void init(FilterConfig config) throws ServletException {
		try {
			this.config = config;
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			documentBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new ServletException(e);
		}
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String renderType = request.getParameter("RenderOutputType");

		if (renderType != null) {
			// Capture the content for this request
			ContentCaptureServletResponse capContent = new ContentCaptureServletResponse(response);

			filterChain.doFilter(request, capContent);

			try {
				// Parse the XHTML content to a document that is readable by the
				// XHTML renderer.
				String contentPage = HtmlEntities.decode(capContent.getContent());

				StringReader contentReader = new StringReader(contentPage);
				InputSource source = new InputSource(contentReader);
				source.setEncoding("utf-8");

				Document xhtmlContent = documentBuilder.parse(source);

				if (renderType.equals("pdf")) {
					ITextRenderer renderer = new ITextRenderer();
					renderer.setDocument(xhtmlContent, "RelatorioTecnicoCompleto.pdf");
					renderer.layout();

					response.setContentType("application/pdf");
					OutputStream browserStream = response.getOutputStream();
					renderer.createPDF(browserStream);
					return;
				}

				if (renderType.equals("odt")) {
					int posicaoFinal = contentPage.indexOf("</html>") + 7;
					System.out.println(posicaoFinal);

					String contentPageProcessed = contentPage.substring(0, posicaoFinal);

					File file = new File("/tmp/RelatorioTecnico.odt");
					FileUtils.writeStringToFile(file, contentPageProcessed, "UTF-8");
					FileInputStream fileIn = new FileInputStream(file);

					response.setHeader("Content-Disposition", "attachment;filename=RelatorioTecnico.odt");
					ServletOutputStream out = response.getOutputStream();
					 byte[] b = contentPageProcessed.getBytes(Charset.forName("UTF-8"));
					 out.write(b);

					return;
				}
			} catch (SAXException e) {
				throw new ServletException(e);
			} catch (DocumentException e) {
				throw new ServletException(e);
			}
		} else {
			// Normal processing
			filterChain.doFilter(request, response);
		}
	}

	public void destroy() {
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

}
