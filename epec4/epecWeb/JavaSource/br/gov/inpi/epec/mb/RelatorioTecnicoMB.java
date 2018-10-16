package br.gov.inpi.epec.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.gov.inpi.epec.entities.RelatorioTecnico;
import br.gov.inpi.epec.facade.PesquisaServiceFacade;

@SessionScoped
@ManagedBean
public class RelatorioTecnicoMB implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = -4409937712994959602L;

	private RelatorioTecnico relatorioTecnico;

	private String numeroPedido;

	@EJB
	private PesquisaServiceFacade pesquisaService;

	boolean postBack;

	public RelatorioTecnico getRelatorioTecnico() {

		return relatorioTecnico;
	}

	public void setRelatorioTecnico(RelatorioTecnico relatorioTecnico) {
		this.relatorioTecnico = relatorioTecnico;
	}

	public RelatorioTecnico getObterRelatorioTecnico() {
		if (postBack) {
			relatorioTecnico = this.pesquisaService.pesquisarRelatorioOdt(this.numeroPedido);
			postBack = false;
			return relatorioTecnico;
		} else {
			return relatorioTecnico;
		}
	}

	public void redirecionar() {
		try {

			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("/RenderOutputType=pdf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		postBack = true;

		this.numeroPedido = numeroPedido;
	}
}
