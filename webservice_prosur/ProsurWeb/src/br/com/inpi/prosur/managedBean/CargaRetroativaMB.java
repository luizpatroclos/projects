package br.com.inpi.prosur.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.inpi.prosur.validacoes.Validador;
import br.gov.inpi.services.EntityInterfaceIntercarga;

@ManagedBean(name = "cargaRetroativaMB")
@ViewScoped
public class CargaRetroativaMB {

	@EJB
	EntityInterfaceIntercarga service;
	
	private String qtdMarcasRetroExecutadas; //qtd de cargas retroativas marcas executadas
	private String qtdMarcasRetroExecutar; //qtd total de cargas retroativas marcas que faltam
	private String qtdDiRetroExecutado;
	private String qtdDiRetroExecutar;
	private String qtdPatenteRetroExecutado;
	private String qtdPatenteRetroExecutar;
	
	//MARCAS RETROATIVO
	public String getQtdMarcasRetroExecutadas(){ //qtd carga
		
		List<String> listaAux = new ArrayList<>();		
		listaAux = service.retornarQtdCargaMarcasRetro();		
		this.qtdMarcasRetroExecutadas = listaAux.size() >= 1 ? listaAux.get(0) : " - ";
		
		return Validador.converteNumeroDecimal(this.qtdMarcasRetroExecutadas);
	}
	public void setQtdMarcasRetroExecutadas(String qtdMarcasRetroExecutadas){ 
		
		this.qtdMarcasRetroExecutadas = qtdMarcasRetroExecutadas;
	}
	public String getQtdMarcasRetroExecutar(){ //qtd restante
		
		List<String> listaAux = new ArrayList<>();		
		listaAux = service.retornarQtdCargaMarcasRetro();		
		this.qtdMarcasRetroExecutar = listaAux.size() >= 1 ? listaAux.get(1) : " - ";
		
		return Validador.converteNumeroDecimal(this.qtdMarcasRetroExecutar);
	}
	public void setQtdMarcasRetroExecutar(String qtdMarcasRetroExecutar){
		
		this.qtdMarcasRetroExecutar = qtdMarcasRetroExecutar;
	}
	//FIM MARCAS RETROATIVO
	
	//DI RETROATIVO
	public String getQtdDiRetroExecutado(){ //qtd carga
		
		List<String> listaAux = new ArrayList<>();		
		listaAux = service.retornarQtdCargaDiRetro();		
		this.qtdDiRetroExecutado = listaAux.size() >= 1 ? listaAux.get(0) : " - ";
		
		return Validador.converteNumeroDecimal(this.qtdDiRetroExecutado);
	}
	public void setQtdDiRetroExecutado(String qtdDiRetroExecutado){ 
		
		this.qtdDiRetroExecutado = qtdDiRetroExecutado;
	}
	public String getQtdDiRetroExecutar(){ //qtd restante
		
		List<String> listaAux = new ArrayList<>();		
		listaAux = service.retornarQtdCargaDiRetro();		
		this.qtdDiRetroExecutar = listaAux.size() >= 1 ? listaAux.get(1) : " - ";
		
		return Validador.converteNumeroDecimal(this.qtdDiRetroExecutar);
	}
	public void setQtdDiRetroExecutar(String qtdDiRetroExecutar){
		
		this.qtdDiRetroExecutar = qtdDiRetroExecutar;
	}
	//FIM DI RETROATIVO
	
	//PATENTE RETROATIVO
	public String getQtdPatenteRetroExecutado(){ //qtd carga
		
		List<String> listaAux = new ArrayList<>();		
		listaAux = service.retornarQtdCargaPatenteRetro();		
		this.qtdPatenteRetroExecutado = listaAux.size() >= 1 ? listaAux.get(0) : " - ";
		
		return Validador.converteNumeroDecimal(this.qtdPatenteRetroExecutado);
	}
	public void setQtdPatenteRetroExecutado(String qtdPatenteRetroExecutado){ 
		
		this.qtdPatenteRetroExecutado = qtdPatenteRetroExecutado;
	}
	public String getQtdPatenteRetroExecutar(){ //qtd restante
		
		List<String> listaAux = new ArrayList<>();		
		listaAux = service.retornarQtdCargaPatenteRetro();		
		this.qtdPatenteRetroExecutar = listaAux.size() >= 1 ? listaAux.get(1) : " - ";
		
		return Validador.converteNumeroDecimal(this.qtdPatenteRetroExecutar);
	}
	public void setQtdPatenteRetroExecutar(String qtdPatenteRetroExecutar){
		
		this.qtdPatenteRetroExecutar = qtdPatenteRetroExecutar;
	}
	//FIM PATENTE RETROATIVO
}
