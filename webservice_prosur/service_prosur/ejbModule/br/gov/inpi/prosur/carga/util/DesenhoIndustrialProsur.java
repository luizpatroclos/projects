/*package br.gov.inpi.prosur.carga.util;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.prosur.catalog.ws.DesignPatent;
import org.prosur.catalog.ws.IpRecord;

import br.gov.inpi.patentes.beans.DiPedido;


public class DesenhoIndustrialProsur extends PatenteDesenhoIndustrialBase {
	
		
	private static final Logger LOGGER = Logger.getLogger(DesenhoIndustrialProsur.class);
	
		
	public void processarDesenhoIndustrial(String tipoEnvio){
		
		this.pesquisarPedidosDesenhoIndustrial(tipoEnvio);
		this.gravarPedidoPatenetDesenhoIndustrial();
		
	}

	*//***
	 * 
	 * Método responsável em pesquisar os pedidos de Desenho Industrial
	 * para serem enviados ao serviço Prosur.
	 * 
	 * @param tipoEnvio
	 *//*
	private void pesquisarPedidosDesenhoIndustrial(String tipoEnvio){
			
			List<DiPedido> diPedidos = new ArrayList<DiPedido>();
			
			LOGGER.info(" Inicio da carga de Desenho Industrial..........."); 
			
			if("cargaSemanal".equals(tipoEnvio)){
				diPedidos = this.persistPatentes.pesquisarPedidosDiPorSemana();
	    	}else if("cargaRetroativa".equals(tipoEnvio)){
				diPedidos = this.persistPatentes.pesquisarPedidosDiRetroativa();
			}
			
			this.prepararPedidos(diPedidos);
			LOGGER.info(" Fim da carga de Desenho Industrial.............. ");
	}
	
	
		   
	*//***
	 * 
	 * Método responsável em converter os pedidos de Desenho Industrial
	 * para o padrão utilizado pelo Prosur.
	 *     
	 * @param diPedidos
	 *//*
	private void prepararPedidos(List<DiPedido> diPedidos){
	    	
		if(diPedidos != null && !diPedidos.isEmpty()){
		   	
			PatenteHelperAutomatico helper = new PatenteHelperAutomatico();
			helper.setPersistPatentes(this.getPersistPatentes());
			helper.setProsurUtils(this.getProsurUtils());
			List<DesignPatent> designPatents = helper.retornarDesignPatent(diPedidos);
				
			if(designPatents != null && !designPatents.isEmpty()){
				this.enviar(designPatents);
			    
		     }
				
		}else{
			LOGGER.info(" Nenhum pedido de Desenho Industrial encontrado. ");
		}
	    
	}

	   
	private void enviar(List<DesignPatent> designPatents){
			
		if(designPatents != null && !designPatents.isEmpty()){
		
				for(int i = 0; i < designPatents.size(); i++){
					
					DesignPatent designPatent = designPatents.get(i);
				    IpRecord iprecordRetorno = servicosProsur.createProsur(designPatent);
				    
				    LOGGER.info(" valores do bean :: " +iprecordRetorno.getIpRecordId());
					if(iprecordRetorno != null){
					    this.enviarParaLog(designPatent.getApplicationId());						
					}
										
				}
			
			}else{
				LOGGER.info(" Nenhum pedido de Desenho Industrial encontrado. ");
			}
		
	}

}
*/