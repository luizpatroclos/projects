package br.gov.inpi.epec.facade;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;

import org.apache.http.client.ClientProtocolException;

import br.gov.inpi.epec.beans.Tbcadentidade;
import br.gov.inpi.epec.beans.Tbcadpais;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbfamiliaec;
import br.gov.inpi.epec.beans.Tbpatenteec;

@Local
public interface ConsultaServiceFacade {

	public String retornaXML(String tipo, String valor, String codPedido) throws ClientProtocolException, IOException;

	public List<String> cerregarPaisesValidos(String valor, List<Tbcadpais> paises, String tipo) throws IOException;

	public void convertXMLFamilia(String xml, Tbpatenteec patente) throws Exception;

	public void alterarPedidoXML(String xml, Tbpatenteec patente) throws Exception;

	public Tbcadentidade buscaEntidadeByIdPais(List<Tbcadpais> paises, String pedido, Tbcadusuario usuarioLogado);

	public boolean verificaPedido(String pedido, String tipo);

	public List<Tbpatenteec> buscaFamiliaPatente(String pedido, String tipo);
	
	public Tbfamiliaec verificarFamiliaPedidos(String valor, List<Tbcadpais> paises, String tipo)throws IOException ;
	
	public boolean verificarXmL(String xml) throws Exception;

}
