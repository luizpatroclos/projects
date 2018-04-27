/*package br.gov.inpi.prosur.carga.util;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import org.jboss.logging.Logger;
import br.gov.inpi.patentes.beans.PtnProcessoProsur;
import br.gov.inpi.services.EntityInterfacePatentes;
import br.gov.inpi.services.ServicosInterface;
public class PatenteDesenhoIndustrialBase {
	
	
	private static final Logger LOGGER = Logger.getLogger(PatenteDesenhoIndustrialBase.class);
	
	
    private Set<PtnProcessoProsur> processos = new HashSet<>();
	
	@EJB
	protected EntityInterfacePatentes persistPatentes;
	
	@EJB
	protected ServicosInterface servicosProsur;

	private ProsurUtils prosurUtils;
	
	
	private Date dataDeEnvio;
			
	
	
	public Date getDataDeEnvio() {
		return dataDeEnvio;
	}



	public void setDataDeEnvio(Date dataDeEnvio) {
		this.dataDeEnvio = dataDeEnvio;
	}



	public Set<PtnProcessoProsur> getProcessos() {
		return processos;
	}



	public void setProcessos(Set<PtnProcessoProsur> processos) {
		this.processos = processos;
	}



	public EntityInterfacePatentes getPersistPatentes() {
		return persistPatentes;
	}



	public void setPersistPatentes(EntityInterfacePatentes persistPatentes) {
		this.persistPatentes = persistPatentes;
	}


	public ProsurUtils getProsurUtils() {
		return prosurUtils;
	}



	public void setProsurUtils(ProsurUtils prosurUtils) {
		this.prosurUtils = prosurUtils;
	}
	
	public ServicosInterface getServicosProsur() {
		return servicosProsur;
	}



	public void setServicosProsur(ServicosInterface servicosProsur) {
		this.servicosProsur = servicosProsur;
	}

	

	//  Prepara a lista de objetos para serem persistidos ------------------------------------------
	protected void enviarParaLog(String numeroPedido){
		 
		    PtnProcessoProsur prosur = new PtnProcessoProsur();
			prosur.setNumeroProcesso(numeroPedido);
			prosur.setDataEnvio(this.getDataDeEnvio());
			this.getProcessos().add(prosur);
		   
	}
	 
	protected  void gravarPedidoPatenetDesenhoIndustrial(){
	    
		try{
		   this.getPersistPatentes().salvar(this.getProcessos());		   
		}catch(Exception e){
		    LOGGER.error(" >> ERRO : ao inserir o log de PatentesDesenhoIndustrial" + e.getMessage());
		}
		
	}
	

}
*/