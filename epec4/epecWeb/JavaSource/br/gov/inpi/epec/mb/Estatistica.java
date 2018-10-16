package br.gov.inpi.epec.mb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.gov.inpi.epec.beans.Tbcadentidade;
import br.gov.inpi.epec.facade.EstatisticaFacade;

@ManagedBean
@RequestScoped
public class Estatistica {

	@EJB
	EstatisticaFacade service;

	private List<Estatistica> estatisticaGeral = new ArrayList<Estatistica>();
	private List<Estatistica> estatisticaEntidade = new ArrayList<Estatistica>();
	private String parametro;
	private int valor; // TODO trocar para valor bd
	private String logoEntidade;
	private int usuarios;
	private int patentes;
	private int prioridades;
	private int relatorios;
	private int anterioridadesPatentarias;
	private int anterioridadesNaoPatentarias;
	private int comentarios;
	private int logs;
	
	
	private int entidadesTotal;
	private int familiaPedidoTotal;	
	private int usuariosTotais;
	private int pedidoPatenteTotal;
	private int prioridadeTotal;
	private int relatorioTecnicoTotal;
	private int anterioridadesPatentariasTotal;
	private int anterioridadesNaoPatentariasTotal;
	private int comentariosTotal;
	private int logsTotal;
	
	



	private String nomeEntidade;

	public Estatistica() {
	}

	public Estatistica(String parametro, int valor) { // geral

		this.parametro = parametro;
		this.valor = valor;
	}

	public Estatistica(String logoEntidade, String nomeEntidade, int usuarios, int patentes, int prioridades, int relatorios, int anterioridadesPatentarias, int anterioridadesNaoPatentarias,
			int comentarios, int logs) { // entidade
		this.logoEntidade = logoEntidade;
		this.nomeEntidade = nomeEntidade;
		this.usuarios = usuarios;
		this.patentes = patentes;
		this.prioridades = prioridades;
		this.relatorios = relatorios;
		this.anterioridadesPatentarias = anterioridadesPatentarias;
		this.anterioridadesNaoPatentarias = anterioridadesNaoPatentarias;
		this.comentarios = comentarios;
		this.logs = logs;
	}

	public List<Estatistica> getEstatisticaGeral() {

		this.estatisticaGeral.add(new Estatistica("Entidades", this.service.countEntidades()));
		this.estatisticaGeral.add(new Estatistica("Família de pedidos de patentes", this.service.countFamilias()));
		this.estatisticaGeral.add(new Estatistica("Usuários", this.service.countUsuarios(null)));
		this.estatisticaGeral.add(new Estatistica("Pedidos de patente", this.service.countPedidosPatentes(null)));
		this.estatisticaGeral.add(new Estatistica("Prioridades associadas aos pedidos do país", this.service.countPrioridades(null)));
		this.estatisticaGeral.add(new Estatistica("Relatórios Técnicos", this.service.countRelatoriosTecnicos(null)));
		this.estatisticaGeral.add(new Estatistica("Anterioridades patentárias citadas nos relatórios técnicos", this.service.countAnterioridadesPatentarias(null)));
		this.estatisticaGeral.add(new Estatistica("Anterioridades não patentórias citadas nos relatórios técnicos", this.service.countAnterioridadesNaoPatentarias(null)));
		this.estatisticaGeral.add(new Estatistica("Comentários", this.service.countComentarios(null)));
		this.estatisticaGeral.add(new Estatistica("Log", this.service.countLogs(null)));

		return estatisticaGeral;
	}

	@SuppressWarnings("unchecked")
	public List<Estatistica> getEstatisticaEntidade() {

		List<Tbcadentidade> entidades = (List<Tbcadentidade>) (Object) this.service.listAll();

		for (Tbcadentidade tbcadentidade : entidades) {
			this.estatisticaEntidade.add(new Estatistica(tbcadentidade.getImagem(), tbcadentidade.getTxOrganizacao(), this.service.countUsuarios(tbcadentidade.getIdEntidadeEc()), this.service
					.countPedidosPatentes(tbcadentidade.getIdEntidadeEc()), this.service.countPrioridades(tbcadentidade.getIdEntidadeEc()), this.service.countRelatoriosTecnicos(tbcadentidade
					.getIdEntidadeEc()), this.service.countAnterioridadesPatentarias(tbcadentidade.getIdEntidadeEc()), this.service.countAnterioridadesNaoPatentarias(tbcadentidade.getIdEntidadeEc()),
					this.service.countComentarios(tbcadentidade.getIdEntidadeEc()), this.service.countLogs(tbcadentidade.getIdEntidadeEc())));

		}

		return estatisticaEntidade;
	}

	public String getParametro() {
		return parametro;
	}

	public int getValor() {
		return valor;
	}

	public String getLogoEntidade() {
		return logoEntidade;
	}

	public EstatisticaFacade getService() {
		return service;
	}

	public int getUsuarios() {
		return usuarios;
	}

	public int getPatentes() {
		return patentes;
	}

	public int getPrioridades() {
		return prioridades;
	}

	public int getRelatorios() {
		return relatorios;
	}

	public int getAnterioridadePatentarias() {
		return anterioridadesPatentarias;
	}

	public int getAnterioridadeNaoPatentarias() {
		return anterioridadesNaoPatentarias;
	}

	public int getComentarios() {
		return comentarios;
	}

	public int getLogs() {
		return logs;
	}

	public String getNomeEntidade() {
		return nomeEntidade;
	}

	public int getEntidadesTotal() {
		entidadesTotal = this.service.countEntidades();
		return entidadesTotal;
	}

	public void setEntidadesTotal(int entidadesTotal) {
		this.entidadesTotal = entidadesTotal;
	}
	
	public int getFamiliaPedidoTotal() {
		return this.service.countFamilias();
	}

	public void setFamiliaPedidoTotal(int familiaPedidoTotal) {
		this.familiaPedidoTotal = familiaPedidoTotal;
	}

	public int getUsuariosTotais() {
		usuariosTotais = this.service.countUsuarios(null);
		return usuariosTotais;
	}

	public void setUsuariosTotais(int usuariosTotais) {
		this.usuariosTotais = usuariosTotais;
	}

	public int getPedidoPatenteTotal() {
		return this.service.countPedidosPatentes(null);
	}

	public void setPedidoPatenteTotal(int pedidoPatenteTotal) {
		this.pedidoPatenteTotal = pedidoPatenteTotal;
	}

	public int getPrioridadeTotal() {
		return  this.service.countPrioridades(null);
	}

	public void setPrioridadeTotal(int prioridadeTotal) {
		this.prioridadeTotal = prioridadeTotal;
	}

	public int getRelatorioTecnicoTotal() {
		return this.service.countRelatoriosTecnicos(null);
	}

	public void setRelatorioTecnicoTotal(int relatorioTecnicoTotal) {
		this.relatorioTecnicoTotal = relatorioTecnicoTotal;
	}

	public int getAnterioridadesPatentariasTotal() {
		return this.service.countAnterioridadesPatentarias(null);
	}

	public void setAnterioridadesPatentariasTotal(int anterioridadesPatentariasTotal) {
		this.anterioridadesPatentariasTotal = anterioridadesPatentariasTotal;
	}

	public int getAnterioridadesNaoPatentariasTotal() {
		return this.service.countAnterioridadesNaoPatentarias(null);
	}

	public void setAnterioridadesNaoPatentariasTotal(int anterioridadesNaoPatentariasTotal) {
		this.anterioridadesNaoPatentariasTotal = anterioridadesNaoPatentariasTotal;
	}

	public int getComentariosTotal() {
		return this.service.countComentarios(null);
	}

	public void setComentariosTotal(int comentariosTotal) {
		this.comentariosTotal = comentariosTotal;
	}

	public int getLogsTotal() {
		return this.service.countLogs(null);
	}

	public void setLogsTotal(int logsTotal) {
		this.logsTotal = logsTotal;
	}
}
