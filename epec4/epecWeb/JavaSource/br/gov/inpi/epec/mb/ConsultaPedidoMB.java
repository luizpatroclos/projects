package br.gov.inpi.epec.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.gov.inpi.epec.beans.Tbcadentidade;
import br.gov.inpi.epec.beans.Tbcadpais;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbfamiliaec;
import br.gov.inpi.epec.beans.Tblogfamilia;
import br.gov.inpi.epec.beans.Tblogpatente;
import br.gov.inpi.epec.beans.Tbpatenteec;
import br.gov.inpi.epec.entities.Familia;
import br.gov.inpi.epec.facade.ConsultaServiceFacade;
import br.gov.inpi.epec.facade.EpecServiceFacade;
import br.gov.inpi.epec.util.EPECUtil;

@SessionScoped
@ManagedBean
public class ConsultaPedidoMB extends AbstractMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7664731014626584300L;

	private String pedido;

	private String tipo;

	private String tipoConsulta;

	private String valorPedido;

	private String valorPrioridade;

	private String valorPublicacao;

	private String urlBuscaOps;
	
	private String usuario;

	private int tabIndex = 0;

	private HttpSession session;

	public Tbcadusuario usuarioLogado;

	Locale localeES = new Locale("es", "");
	Locale localeEN = new Locale("en", "");

	public List<Familia> familias = new ArrayList<Familia>();

	public Tbcadusuario getUsuarioLogado() {
		obterUsuarioSessao();
		return usuarioLogado;
	}

	public void setUsuarioLogado(Tbcadusuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	private void obterUsuarioSessao() {
		FacesContext fc = FacesContext.getCurrentInstance();

		session = (HttpSession) fc.getExternalContext().getSession(true);

		usuarioLogado = (Tbcadusuario) session.getAttribute("usuarioAutenticado");
	}

	public ExternalContext externalContext;

	@EJB
	private ConsultaServiceFacade service;

	@EJB
	private EpecServiceFacade persist;

	private List<Tbpatenteec> patentec;

	@SuppressWarnings("unchecked")
	public void inserirPedido() {

		loadTipo();

		try {
			
			String xmlResult = service.retornaXML(tipo, tipoConsulta, valorPedido);
			
			if(service.verificarXmL(xmlResult)){
			

				if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
					displayErrorMessageToUser("Document Not Found");
				} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
					displayErrorMessageToUser("Document Not Found");
				} else {
					displayErrorMessageToUser("Documento não Encontrado");
				}

				return;
			}
			
			if ("HTTP Status 404".equals(xmlResult) || "not found".equals(xmlResult)) {
				
				

				if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
					displayErrorMessageToUser("Document Not Found");
				} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
					displayErrorMessageToUser("Document Not Found");
				} else {
					displayErrorMessageToUser("Documento não Encontrado");
				}

				return;

			}

				List<Tbcadpais> paises = (List<Tbcadpais>) (Object) persist.listAll(FIND_ALL_PAIS);

				if ("3".equals(tipo)) {

					int contador = 0;
					List<String> pedidosVerificados = service.cerregarPaisesValidos(xmlResult, paises, tipo);
					
					Tbfamiliaec familia = null;
					
					familia = service.verificarFamiliaPedidos(xmlResult, paises, tipo);
						
					if (familia == null) {

					// Passo 0: Associa a uma Familia
					/*--------------------------------*/
					familia = new Tbfamiliaec();

					familia.setDDatahora(new Date());
					familia.setPublico(true);

					persist.save(familia);
					
					salvarLogFamilia(familia.getIdFamiliaEc(), "Inserir", "Pedido", valorPedido, usuario);

					}	

					
					for (String pedidoPrioridade : pedidosVerificados) {
						
						String xmlResultBiblio = service.retornaXML(tipo, PRIORITY, pedidoPrioridade);

						List<String> auxPrioridade = service.cerregarPaisesValidos(xmlResultBiblio, paises, null);

						if (verificarSeExistePedido(auxPrioridade, pedidoPrioridade)) {

							String auxResultBiblio = service.retornaXML(tipo, BIBLIO, auxPrioridade.get(contador));

							Tbpatenteec patente = new Tbpatenteec();

							Tbcadentidade entidade = service.buscaEntidadeByIdPais(paises, auxPrioridade.get(contador), getUsuarioLogado());

							patente.setIdEntidadeEc(entidade);
							patente.setIdFamiliaEc(familia);

							service.convertXMLFamilia(auxResultBiblio, patente);
							
							//aqui inserir log do pedido.
							salvarLogPedido(patente.getIdPatenteEc(), "Inserir", "Pedido", "-", usuario);
							
							//aqui inserir log da familia.
							salvarLogFamilia(familia.getIdFamiliaEc(), "Inserir", "Família", "-", usuario);

							if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
								displaySucessoMessageToUser("Petición de la familia grabado con éxito");
							} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
								displaySucessoMessageToUser("Family request engraved with Success");
							} else {
								displaySucessoMessageToUser(" Familia do pedido gravada com Sucesso !");
							}

							contador++;
						}

						/*if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
							displayErrorMessageToUser("Error en el registro de la solicitud de la familia");
						} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
							displayErrorMessageToUser("Error in recording the family's request");
						} else {
							displaySucessoMessageToUser("Erro, na gravação da familia do pedido");
						}*/

					}
				} else {

					List<String> pedidosVerificados = service.cerregarPaisesValidos(xmlResult, paises, tipo);
					
					Tbfamiliaec familia = null;
	
					familia = service.verificarFamiliaPedidos(xmlResult, paises, tipo);
						
					if (familia == null) {

					// Passo 0: Associa a uma Familia
					/*--------------------------------*/
					familia = new Tbfamiliaec();

					familia.setDDatahora(new Date());
					familia.setPublico(true);

					persist.save(familia);
					
					salvarLogFamilia(familia.getIdFamiliaEc(), "Inserir", "Família", "-", usuario);

					}	
					

						for (String pedidoBiblio : pedidosVerificados) {

							String xmlResultBiblio = service.retornaXML(tipo, BIBLIO, pedidoBiblio);

							Tbpatenteec patente = new Tbpatenteec();

							Tbcadentidade entidade = service.buscaEntidadeByIdPais(paises, pedidoBiblio, getUsuarioLogado());

							patente.setIdEntidadeEc(entidade);
							patente.setIdFamiliaEc(familia);
							patente.setAutomatico(true);
							
							service.convertXMLFamilia(xmlResultBiblio, patente);
							
							//aqui inserir log do pedido.
							salvarLogPedido(patente.getIdPatenteEc(), "Inserir", "Pedido", "Automático", usuario);
							salvarLogFamilia(familia.getIdFamiliaEc(), "Inserir", "Pedido", patente.getNumeroPedido(), usuario);

					
						}
						if(pedidosVerificados.size() == 0){
							if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
								displayInfoMessageToUser("La solicitud de la familia ya está registrado en el sistema!");
							} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
								displayInfoMessageToUser("The family request is already registered in the system");
							} else {
								displayInfoMessageToUser("A família do pedido já está cadastrada no sistema !");
							}
							
						}else{
							//aqui inserir log da familia.
							
							if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
								displayErrorMessageToUser("Petición de la familia grabado con éxito");
							} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
								displayErrorMessageToUser("Family request engraved with Success");
							} else {
								displaySucessoMessageToUser(" Familia do pedido gravada com Sucesso !");
							}
							
							
						
						}
				

				}

		} catch (Throwable e) {
			if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
				displayErrorFatalMessageToUser("Error en la Solicitud de Servicio !");
			} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
				displayErrorFatalMessageToUser("Error in the Request Service !");
			} else {
				displayErrorFatalMessageToUser("Erro na Solicitação do Serviço !");
			}
		} finally {
			flush();
		}
	}
	

	private boolean verificarSeExistePedido(List<String> auxPrioridade, String pedidoPrioridade) {

		for (int i = 0; i < auxPrioridade.size(); i++) {
			if (auxPrioridade.get(i).equals(pedidoPrioridade))
				
			return true;
		}

		return false;
	}

	public void buscarPedido() {

		loadTipo();

		List<Tbpatenteec> pedidosFamilia = new ArrayList<Tbpatenteec>();

		try {
			pedidosFamilia = service.buscaFamiliaPatente(valorPedido, tipo);

			if ("Visitante".equals(usuario)) {

				List<Tbpatenteec> pedidosFamiliaAux = new ArrayList<Tbpatenteec>();

				for (Tbpatenteec patenteAux : pedidosFamilia) {

					if (patenteAux.getIdFamiliaEc().getPublico() == true) {

						pedidosFamiliaAux.add(patenteAux);

					}
				}

				this.setPatentec(pedidosFamiliaAux);

			} else {

				this.setPatentec(pedidosFamilia);
			}

		} catch (Exception e) {}

		tabIndex = 1;
		valorPedido = "";

	}

	public void detalheFamilia() {
		EPECUtil.redirecionar("/epecWeb/pages/manterFamilia.jsf");
		flush();
	}

	public void buscaOps() {
		
		loadTipo();
		String url = null;

		//String url = "http://worldwide.espacenet.com/searchResults?DB=worldwide.espacenet.com&locale=en_EP&query=" + valorPedido;
		
		if("1".equals(tipo)){
			
			url = "http://worldwide.espacenet.com/searchResults?ST=single&locale=en_EP&submitted=true&DB=worldwide.espacenet.com&query=ap="+valorPedido+"&Submit=Search";
			
		}else if("2".equals(tipo)){
			
			url = "http://worldwide.espacenet.com/searchResults?ST=single&locale=en_EP&submitted=true&DB=worldwide.espacenet.com&query=pn="+valorPedido+"&Submit=Search";
			
		}else if("3".equals(tipo)){
			
			url = "http://worldwide.espacenet.com/searchResults?ST=single&locale=en_EP&submitted=true&DB=worldwide.espacenet.com&query=pr="+valorPedido+"&Submit=Search";
		}

		this.setUrlBuscaOps(url);

		flush();
	}

	public String getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(String valorPedido) {

		if (this.valorPedido == null || "".equals(this.valorPedido)) {

			this.valorPedido = valorPedido;

		}

	}
	
	public void salvarLogFamilia(Long familia, String acao, String ativo, String textoAtivo, String usuario) {
		Tblogfamilia tblogfamilia = new Tblogfamilia();
		tblogfamilia.setDatahora(new Date());
		tblogfamilia.setIdFamiliaEc(familia);
		tblogfamilia.setStrAcao(acao);
		tblogfamilia.setStrAtivo(ativo);
		tblogfamilia.setTxAtivo(textoAtivo);
		tblogfamilia.setStrUsuario(usuario);

		persist.save(tblogfamilia);
	}
	
	public void salvarLogPedido(Long patente, String acao, String ativo, String textoAtivo, String usuario) {
		
		Tblogpatente tblogPatente = new Tblogpatente();
		
		tblogPatente.setIdPatenteEc(patente);
		tblogPatente.setStrUsuario(usuario);
		tblogPatente.setdDatahora(new Date());
		tblogPatente.setStrAcao(acao);
		tblogPatente.setStrAtivo(ativo);
		tblogPatente.setTxAtivo(textoAtivo);
		
		persist.save(tblogPatente);
	}

	public void flush() {

		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("consultaPedidoMB");
		this.valorPedido = "";
		this.pedido = "";
		// this.urlBuscaOps = "";
		tabIndex = 0;
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public boolean isValorValido() {
		return tabIndex == 1;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public List<Tbpatenteec> getPatentec() {
		return patentec;
	}

	public void setPatentec(List<Tbpatenteec> patentec) {
		this.patentec = patentec;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getUrlBuscaOps() {
		return urlBuscaOps;
	}

	public void setUrlBuscaOps(String urlBuscaOps) {
		this.urlBuscaOps = urlBuscaOps;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void loadTipo() {

		externalContext = FacesContext.getCurrentInstance().getExternalContext();
		tipo = externalContext.getRequestParameterMap().get("tipo");
		tipoConsulta = externalContext.getRequestParameterMap().get("tipoConsulta");
		session = (HttpSession) externalContext.getSession(true);
		usuario = (String) session.getAttribute("usuario");

	}

	public String getValorPrioridade() {
		return valorPrioridade;
	}

	public void setValorPrioridade(String valorPrioridade) {
		this.valorPrioridade = valorPrioridade;
	}

	public String getValorPublicacao() {
		return valorPublicacao;
	}

	public void setValorPublicacao(String valorPublicacao) {

		if (this.valorPublicacao == null || "".equals(this.valorPublicacao)) {

			this.valorPublicacao = valorPublicacao;

		}

	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
