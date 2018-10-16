package br.gov.inpi.epec.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.gov.inpi.epec.mb.TbPatenteEcMB;

@WebServlet("/ReportDiagramaFamilia.pdf")
public class PdfReportServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1385966562542676197L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
        byte[] content = (byte[]) request.getSession().getAttribute("reportBytes");
        
        if (content != null) {
		
        	response.setContentType("application/pdf");
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
		}
        
        
    }
	


}
