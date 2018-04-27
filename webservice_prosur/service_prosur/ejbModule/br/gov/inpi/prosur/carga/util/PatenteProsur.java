/*package br.gov.inpi.prosur.carga.util;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;
import org.prosur.catalog.ws.InventionPatent;
import org.prosur.catalog.ws.IpRecord;
import org.prosur.catalog.ws.ProsurIPRecordWS;
import org.prosur.catalog.ws.ProsurIPRecordWSProxy;

import br.gov.inpi.patentes.beans.PtnPedido;




public class PatenteProsur extends PatenteDesenhoIndustrialBase {
	
	
	private static final Logger LOGGER = Logger.getLogger(PatenteProsur.class);
	
	
	public void processarPatente(String tipoEnvio){
		
		
		this.pesquisarPedidos(tipoEnvio);
		this.gravarPedidoPatenetDesenhoIndustrial();
	}
		
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private void pesquisarPedidos(String tipoEnvio){
		
		LOGGER.info(" Inicio da carga de Patentes ..........  ");
		List<PtnPedido> pedidos = new ArrayList<PtnPedido>();
		if("cargaRetroativa".equals(tipoEnvio)){
			 
			pedidos = this.getPersistPatentes().pesquisarPedidosDePatenteRetroativa();
			
		}else if("cargaSemanal".equals(tipoEnvio)){
			
			pedidos = this.getPersistPatentes().pesquisarPedidosDePatentePorSemana();
		
		}
	 	this.prepararPedidos(pedidos);
	    LOGGER.info(" Fim da carga de Patentes............... ");
		
	}
	
	
	private void prepararPedidos(List<PtnPedido> pedidos){
		
		
		if(pedidos != null && !pedidos.isEmpty()){
			
			PatenteHelperAutomatico helper = new PatenteHelperAutomatico();
			helper.setPersistPatentes(this.getPersistPatentes());
			helper.setProsurUtils(this.getProsurUtils());
			
			List<InventionPatent> inventionPatents = helper.retornarPedidosPatent(pedidos);
			
			if(inventionPatents != null && !inventionPatents.isEmpty()){
			     this.enviar(inventionPatents);
			}   
											
		}
		
	}
	
	
	private void enviar( List<InventionPatent> inventionPatents){
		
		try {
			
			if(inventionPatents != null && !inventionPatents.isEmpty()){
				
				autenticUser();
				
				for(int i = 0; i < inventionPatents.size(); i++){
							
					
					ProsurIPRecordWS valor1 = new ProsurIPRecordWSProxy();
					InventionPatent invention = inventionPatents.get(i);
					IpRecord iprecordRetorno = valor1.createRecord(invention,"inpi.org.br");
					 
				    LOGGER.info(" valores do bean :: " +iprecordRetorno.getIpRecordId());
					if(iprecordRetorno != null){
					    this.enviarParaLog(invention.getApplicationId());
					}	
				}
			}
			
		} catch (RemoteException e) {
		     LOGGER.error(" >> ERRO :: enviar pedido de Patente para o serviço Prosur : " + e.getMessage());
		}
		
	}
	
	
private void autenticUser(){
		
		URL realData = getClass().getClassLoader().getResource("clienteBR.jks");
		String path = realData.getPath();
		
				System.setProperty("javax.net.ssl.keyStoreType", "JKS"); 
		    	System.setProperty("javax.net.ssl.keyStore",path); 
		    	System.setProperty("javax.net.ssl.keyStorePassword", "Be7@5U");
		
	}

}
*/