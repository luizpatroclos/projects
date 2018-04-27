package br.com.inpi.prosur.managedBean;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.inpi.prosur.util.FacesMessageUtil;
import br.com.inpi.prosur.util.Mensagem;
import br.com.inpi.prosur.validacoes.ProsurUtil;
import br.com.inpi.prosur.validacoes.Validador;
import br.gov.inpi.intercarga.beans.TbAcessoCarga;
import br.gov.inpi.services.EntityInterfaceIntercarga;

@ManagedBean(name = "usuarioProsurMB")
@ViewScoped
public class UsuarioProsurMB {
	
	TbAcessoCarga acessoCarga;
	
	private String nomeUsuario;
	private String nomeUsuarioExclusao;
	private List<TbAcessoCarga> listaAcessoCarga;
	private HashMap<String, Integer> mapParameters = new HashMap<String, Integer>();
	
	public String getNomeUsuario(){
		
		return nomeUsuario;
	}
	
	public void setNomeUsuario(String nomeUsuario){
		
		this.nomeUsuario = nomeUsuario;
	}
	
	public String getNomeUsuarioExclusao() {
		return nomeUsuarioExclusao;
	}

	public void setNomeUsuarioExclusao(String nomeUsuarioExclusao) {
		this.nomeUsuarioExclusao = nomeUsuarioExclusao;
	}

	@EJB
	EntityInterfaceIntercarga service;
	
	public void salvarNovoUsuario(){
		
		acessoCarga = new TbAcessoCarga();
		
		try {
		
			if (!Validador.vazio(this.nomeUsuario)) {
				
				this.acessoCarga.setNmLogin(this.nomeUsuario);
				service.save(this.acessoCarga);
				
				new FacesMessageUtil(true).mensagemSucesso("operacao_sucesso");
				ProsurUtil.redirecionar("acesso.jsf?i=3");
			} else {
				
				new FacesMessageUtil().mensagemAviso("login_vazio");
			}
		} catch (Exception e) {
			
			new FacesMessageUtil().mensagemErro("erro_sistema");
		}
		
	}
	
	public void excluirUsuarioNovo(){
		
		try {
			
			mapParameters.put("idLogin", this.acessoCarga.getIdLogin());
			this.acessoCarga = (TbAcessoCarga)service.findOneResult("TbAcessoCarga.findByIdLogin", mapParameters);
			
			System.out.println(this.acessoCarga);
			
			service.deleteUsuarioProsur(this.acessoCarga);
			
			new FacesMessageUtil(true).mensagemSucesso("operacao_sucesso");
			ProsurUtil.redirecionar("acesso.jsf?i=3");
		} catch (Exception e) {

			new FacesMessageUtil().mensagemErro("erro_sistema");
		}
	}
	
	public List<TbAcessoCarga> getListaAcessoCarga(){
			
		this.listaAcessoCarga = service.listaUsuarioProsur();
		
		return this.listaAcessoCarga;
	}
	public void setListaAcessoCarga(List<TbAcessoCarga> listaUsuarios) {
		this.listaAcessoCarga = listaUsuarios;
	}

	public TbAcessoCarga getAcessoCarga() {
		return acessoCarga;
	}
	public void setAcessoCarga(TbAcessoCarga acessoCarga) {
		this.acessoCarga = acessoCarga;
	}
}
