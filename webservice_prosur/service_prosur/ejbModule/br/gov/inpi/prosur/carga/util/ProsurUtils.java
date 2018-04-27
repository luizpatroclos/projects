package br.gov.inpi.prosur.carga.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;
import org.prosur.catalog.ws.Priority;

import br.gov.inpi.patentes.beans.PtnPrioridade;

public class ProsurUtils {
	
	
	private static final Logger LOGGER = Logger.getLogger(ProsurUtils.class);
	
	
    /***
     * 
     * Natureza nova dos pedidos de Patente e Desenho Industrial adotado 
     * pelo INPI 01/01/2012.
     *  
     * 
     */
    private final static String NATUREZA_NOVA_PATENTE_DESENHOINDUSTRIAL = "^(10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30)$";
	
	
	public String objetoString(Object valor) {

		try {

			if (valor != null && !"".equals(valor)) {
				return valor.toString().trim();
			} else {
				return "";
			}

		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			return "";
		}

	}
	
	
	
	public Date objetoDate(Object valor) {

		try {

			if (valor != null && !"".equals(valor)) {
			     return (Date) valor;
			} else {
				return null;
			}

		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			return null;
		}

	}
	
	
	protected Integer objetoInteger(Object valor) {

		try {

			if (valor != null && !"".equals(valor)) {

				return Integer.valueOf(valor.toString());

			} else {
				return null;
			}

		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			return null;
		}

	}
	
	
	
	 
	   /**
	    * 
	    * Método responsável em transformar uma lista de objetos 
	    * em um array de Strings.
	    * 	
	    * @param objetos
	    * @return
	    */
	  public String[] converterListaParaArray(List<String> objetos){
		  
		   String[] arrayObjetos = null;
		   if(objetos != null && !objetos.isEmpty()){
		      arrayObjetos = objetos.toArray(new String[(objetos.size())]);
		   }
		  return arrayObjetos;
	   }
	
	
	  

	  /***
	    * 
	    * Método responsável em obter um array de prioridades.
	    * 
	    */
	   public Priority[] retornarListaPrioridadesParaPriorities(List<PtnPrioridade> prioridades){
	    	
	    	Priority[] priorities = null;
	    	if(prioridades != null && !prioridades.isEmpty()){
	    		    		
	    		priorities = new Priority[prioridades.size()];
	    		int total = 0;
	    		for(int i = 0; i < prioridades.size(); i++){
	    		    		
	    			PtnPrioridade ptnPrioridade = prioridades.get(i);
	    			Priority priority = new Priority();
	    			priority.setPriorityNumber(ptnPrioridade.getNumeroPrioridade().trim());
	    			priority.setPriorityDate(ptnPrioridade.getDataPrioridade());
	    			
	    			if("WO".equals(ptnPrioridade.getPais()) || "EP".equals(ptnPrioridade.getPais())){
	    				priority.setPriorityCountryId("");
	    			}else{
	    				priority.setPriorityCountryId(ptnPrioridade.getPais());
	    			}
	    		    
	    			priorities[total] = priority; 
	    			total++;
	    			
	    		}
	    		
	    	}
	    	return priorities;
	    }
	  
	    
	    
	       
	   
	    
	        
	    
	   /**
	    *  
	    *  Verifica se um numero de pedido possuí uma natureza nova.
	    *  
	    * @param natureza
	    * @return
	    */
	   public boolean verificarNaturezaNova(String natureza){
		   boolean isPedidoNovo = Boolean.FALSE;
		   Pattern pattern = Pattern.compile(NATUREZA_NOVA_PATENTE_DESENHOINDUSTRIAL);
		   Matcher matches = pattern.matcher(natureza);
		   isPedidoNovo = matches.find();
		   return  isPedidoNovo;
	  }
	   
	   
	   
	
	   
	   
	   
	 public String formatarNumeroPedido(String numeroPedido){
	    	
	    	int tamanho = numeroPedido.trim().length();
	    	StringBuilder strBuilder = new StringBuilder();
	    	
	    	String digitoVerificador = null;
			String codigo = null;
	   		String natureza = numeroPedido.substring(0,2);
		    
	   		boolean isNovo = this.verificarNaturezaNova(natureza);
			if(isNovo){
				
				codigo = numeroPedido;
				digitoVerificador = getMOD11(codigo,0);
				
				strBuilder.append("BR ");
				strBuilder.append(natureza);
				String anoDeposito = numeroPedido.substring(2,6);
				strBuilder.append(" ");
				strBuilder.append(anoDeposito);
				String codigoPedido = numeroPedido.substring(6,tamanho);
				strBuilder.append(" ");
				strBuilder.append(codigoPedido);
				strBuilder.append(" ");
				strBuilder.append(digitoVerificador);
				
			    
		   }else{
				codigo = numeroPedido.substring(2,tamanho);
				digitoVerificador ="-".concat(getMOD11(codigo,0));
				
				strBuilder.append(natureza);
				strBuilder.append(" ");
				strBuilder.append(codigo);
				strBuilder.append(digitoVerificador.trim());
			}	
     		
			return strBuilder.toString();
	    
	 }
	   
	    
	   
	   /**
	     * 
	     * Retorna o Dígito Verificador de Patentes e Desenho Industrial.
	     * 
	     * @param Codigo
	     * @param Incremento
	     * @return
	     */
	    public String getMOD11(String Codigo, int Incremento) {
	    	
			int Soma = 0;
			int DV = 0;
			int fator = 0;
			int multi =9;
			
			try {
				
				for(int I = 1; I < Codigo.length() + 1; I++) {
					fator = multi * Integer.parseInt(Codigo.substring(Codigo.length() - I, Codigo.length() - (I - 1)));
					if(multi == 2)
						multi = 9;
					else
						multi--;
					Soma += fator;
				}
				
				DV = Soma % 11;
				
				if(DV == 10) {
					String s = "0";
					return s;
				} else {
					String s1 = Integer.toString(DV);
					return s1;
				}
			
			}catch(Exception exception) {
				String s2 = "ERRO";
				return s2;
			}
		
	    }
	     
	    
	    
	    /**
	     * 
	     * Despachos de concessão. Patente - 16.1
	     *                         Desenho Industrial - 39
	     *                          
	     * @param codDespacho
	     * @return
	     */
	    public boolean isPedidoConcedido(int codigoDespacho){
	       	
	    	String despachosConcecao ="^(861|606|700|976)$";
	     	return this.matcher(despachosConcecao, codigoDespacho);
		
	     }
		
	    /**
		 * 
		 * Verifica se um pedido de Desenho Industrial está extinto 
		 * através dos despachos 42,43,44,45.
		 * 
		 * @param codigoDespacho
		 * @return
		 */
		public boolean isPedidoDIExtinto(int codigoDespacho){
			String despachosExtintos = "^(703|979|704|980|705|981|706|982)$";
			return this.matcher(despachosExtintos,codigoDespacho);
		}
		
		
	    /**
	     * 
	     * Despachos de Negação : Patente : 9.2,15.21,1.2,
	     *                                  11.1.1,11.2,11.4,11.6,11.11,8.11, 8.12 
	     *                                  10.1 
	     *                        Desenho Industrial : 35,36
	     * 
	     * @param codDespacho
	     * @return
	     */
	    public boolean isPedidoNegada(int codigoDespacho){
	      	String despachosNegagacao ="^(838|535|591|856|481|817|622|552|1034|623|554|625|555|547|1034|1039|787|843|697|808|972|973|1093)$";
	     	return this.matcher(despachosNegagacao, codigoDespacho);
        }
		
		
		/**
		 * 
		 * Despachos de Pedidos não vigênte   Patente            : 21.1,21.2,21.6,21.7,24.10;
		 *                                    Desenho Industrial : 44,45;  
		 * 
		 * @param codDespacho
		 * @return
		 */
		public boolean isPedidoNoVigente(int codigoDespacho){
			String despachosVigente ="^(1040|875|646|876|652|877|1094|705|706|981|982)$";
	     	return this.matcher(despachosVigente, codigoDespacho);
		}
	  
		
		
		/**
		 * 
		 * Verifica se um pedido de Desenho Industrial foi extinto.
		 * 
		 * 
		 * @param despacho
		 * @return
		 */
		public boolean isPedidoDesenhoIndustrialExtinto(String despacho){
		 	String despachosExtincao ="^(42|43|44|45)$";
		   	Pattern pattern = Pattern.compile(despachosExtincao);
		   	Matcher seTem = pattern.matcher(despacho);
		   	boolean isNegado = seTem.matches();
		   	if(isNegado){
		     		return Boolean.TRUE;
		     	}
		     	return Boolean.FALSE;
		}
        
		
		
	    
	    
		
	    private boolean matcher(String despachos, int codigoDespacho){
	       	Pattern pattern = Pattern.compile(despachos);
	     	Matcher seTem = pattern.matcher(Integer.toString(codigoDespacho));
	     	boolean existeDespacho = seTem.find();
	     	if(existeDespacho){
	     		return Boolean.TRUE;
	     	}
	     	return Boolean.FALSE;
	    }
	    
	    
	    
	    
	    /**
	     * 
	     * Retorna o tipo de patente (patentTypeId) que não seja de pedido divido.
	     * 
	     * @param natureza
	     * @return
	     */
	    public String retornarNaturezaPedido(String natureza){
	    	
	    	String patentTypeId = null;
	       	
	    	boolean isInvention = this.isInvention(natureza);
	    	if(isInvention){
	    	   return patentTypeId = "INVENTION";  	
	    	}
	    	
	    	boolean isUtilityModel = this.isUtilityModel(natureza);
	    	if(isUtilityModel){
	    		return patentTypeId = "UTILITY_MODEL";
	    	}
	    	
	    	boolean isCertificateAddition = this.isCertificateAddition(natureza);
	    	if(isCertificateAddition){
	    		return patentTypeId = "CERTIFICATE_ADDITION";
	    	}
	    	
	    	return patentTypeId;
	    	
	    }
	    
	    
	    
	    /***
	     * 
	     * Verifica se um pedido de patente de invenção (Invention).
	     * 
	     * @param natureza
	     * @return
	     */
	    private boolean isInvention(String natureza){
	    	
	    	String naturezaInvention = "^(PI|PP|10|11)$";
	    	Pattern pattern = Pattern.compile(naturezaInvention);
	    	Matcher matcher = pattern.matcher(natureza);
	    	boolean isInvention = matcher.find();
	    	if(isInvention){
	    		return Boolean.TRUE;
	    	}
	    	return Boolean.FALSE;
	    	
	    }
	    
	    /***
	     * 
	     * Verifica se uma patente é um Modelo de Utilidade (Utility Model)
	     * 
	     * @param natureza
	     * @return
	     */
	    private boolean isUtilityModel(String natureza){
	    	
	    	String utilityModel = "^(MU|20|21)$";
	    	Pattern pattern = Pattern.compile(utilityModel);
	    	Matcher matcher = pattern.matcher(natureza);
	    	boolean isUtilityModel = matcher.find();
	    	if(isUtilityModel){
	    		return Boolean.TRUE;
	    	}
	    	return Boolean.FALSE;
	    	
	    }
	    
	    
	    /**
	     * 
	     * Verifica se um pedido de Patente é um Certificado de Adição (Certificate Addition)
	     * @param natureza
	     * @return
	     */
	    private boolean isCertificateAddition(String natureza){
	    	String certificateAddition = "^(C0|C1|C2|C3|C4|C5|C6|C7|C8|C9|20|21)$";
	    	Pattern pattern = Pattern.compile(certificateAddition);
	    	Matcher matcher = pattern.matcher(natureza);
	    	boolean isCertificateAddition = matcher.find();
	    	if(isCertificateAddition){
	    		return Boolean.TRUE;
	    	}
	    	return Boolean.FALSE;
	    }
		    
	   	    
}
