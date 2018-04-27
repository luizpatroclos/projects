package br.gov.inpi.prosur.carga.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;
import org.prosur.catalog.ws.DistinctiveSign;
import org.prosur.catalog.ws.FileData;
import org.prosur.catalog.ws.IpRecordTypeEnum;
import org.prosur.catalog.ws.NiceClass;

import br.gov.inpi.marcas.beans.Marca;
import br.gov.inpi.marcas.beans.MrcClasNice;
import br.gov.inpi.marcas.beans.MrcClasVienna;
import br.gov.inpi.marcas.beans.MrcClassifVienna;
import br.gov.inpi.marcas.beans.MrcEspecificacao;
import br.gov.inpi.marcas.beans.MrcFiguraProcess;
import br.gov.inpi.prosur.beans.NiceClassAux;
import br.gov.inpi.services.ProcessoMarcasAutomaticoInterface;
import br.gov.inpi.services.ProcessoMarcasManualInterface;


public class MarcasHelperAutoManual extends ProsurUtils{

	private static final Logger LOGGER = Logger.getLogger(MarcasHelperAutoManual.class);
	
	
	private String IPRECORD_DETAIL_LINK = "http://www.inpi.gov.br/portal/"; 
	private String IP = null;
	private boolean isExigenciaFormal;
	private String IPRECORD_FILES_SERVICE; 
	
	
	public boolean isExigenciaFormal() {
		return isExigenciaFormal;
	}


	public void setExigenciaFormal(boolean isExigenciaFormal) {
		this.isExigenciaFormal = isExigenciaFormal;
	}
	
	
	public MarcasHelperAutoManual() {

		try {
			IP = InetAddress.getLocalHost().getHostAddress();
			IPRECORD_FILES_SERVICE = "http://"+IP+":8080/Office/IPOfficeWS";
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	//------------------------------------------------------------------------------------
	public Marca retornarPedidosMarcas(Object[] marcas, ProcessoMarcasAutomaticoInterface persistMarcas){
		
		Marca  pedidoMarca = null;
		
		try{
		
				DistinctiveSign distinctiveSign = this.converterMarcasParaDistinctiveSign(marcas, persistMarcas);
				if(distinctiveSign != null){
					
					pedidoMarca = new Marca();
					pedidoMarca.setDistinctiveSign(distinctiveSign);
					pedidoMarca.setCodigoProcesso(this.objetoInteger(marcas[0]));
					
				}
	
		}catch(Exception e){
			LOGGER.error(" Não foi possível converter os processos " + e.getMessage());
		}
		
		return pedidoMarca;
	}
	
	public Marca retornarPedidosMarcas(Object[] marcas, ProcessoMarcasManualInterface persistMarcas){
		
		Marca  pedidoMarca = null;
		
		try{
		
				DistinctiveSign distinctiveSign = this.converterMarcasParaDistinctiveSign(marcas, persistMarcas);
				if(distinctiveSign != null){
					
					pedidoMarca = new Marca();
					pedidoMarca.setDistinctiveSign(distinctiveSign);
					pedidoMarca.setCodigoProcesso(this.objetoInteger(marcas[0]));
					
				}
	
		}catch(Exception e){
			LOGGER.error(" Não foi possível converter os processos " + e.getMessage());
		}
		
		return pedidoMarca;
	}
	//---------------------------------------------------------------------------------
	
	//AUTOMATICO
	private DistinctiveSign converterMarcasParaDistinctiveSign(Object[] objects, ProcessoMarcasAutomaticoInterface persistMarcas){
		
		
		DistinctiveSign distinctiveSign = new DistinctiveSign();
    	
		try{
			
			
    		distinctiveSign.setOnapiId("BR");
    		distinctiveSign.setRecordType(IpRecordTypeEnum.DISTINCTIVE_SIGN);
    	    distinctiveSign.setIpRecordDetailLink(this.IPRECORD_DETAIL_LINK);
    	    		    	      
    	    int codigoPedido = this.objetoInteger(objects[0]);
    		String numeroProcesso = this.objetoString(objects[1]);
    		distinctiveSign.setApplicationId(numeroProcesso);
    		
    		distinctiveSign.setNationalPresentationDate(this.objetoDate(objects[2]));
			
    		distinctiveSign.setNationalPublishingDate(this.objetoDate(objects[3]));	
    		
			// Solicitante 
			String[] solicitantes = this.retornarSolicitantes(this.objetoString(objects[4]));
			distinctiveSign.setApplicantName(solicitantes);
		        	
			String descricaoApresentacaoMarca = this.objetoString(objects[6]).toUpperCase();     
	        distinctiveSign.setDistinctiveSignType(descricaoApresentacaoMarca);
	       	distinctiveSign.setPresentationType(descricaoApresentacaoMarca);
	       	
	       	Date dataDoRegistro = this.objetoDate(objects[7]);
       		if(dataDoRegistro != null){
       			distinctiveSign.setRegistrationDate(dataDoRegistro);
       		}
			
       		Date dataDeVencimento = this.objetoDate(objects[8]);
     		Date dataAtualizacaoIpas = this.objetoDate(objects[9]);
    	    
    		Date dataExpiracao = this.retornarDataExpiracaoMarca(dataDeVencimento,dataAtualizacaoIpas);
       		if(dataExpiracao != null){
    	        distinctiveSign.setExpiration(dataExpiracao);	
       		} 
           		     	   
       		String codigoSituacao = this.objetoString(objects[10]);
			this.verificarExigenciaDoPedidoPorCodigoDeSituacao(codigoSituacao);
    	    if(!this.isExigenciaFormal){
       		  
          	    String titulo = this.objetoString(objects[5]);
          	    distinctiveSign.setTitle(titulo);
               
          	    String tipoFigura = this.retornarExtencaoFiguraDoPedido(codigoPedido,persistMarcas);
          	    if(tipoFigura != null){
          	        
          	    	String nomeDoArquivo = numeroProcesso.trim().concat(".".concat(tipoFigura));
          	    	
          	    	FileData[] fileData = this.retornarFileData(nomeDoArquivo,titulo);
                    distinctiveSign.setFiles(fileData);
               	  	distinctiveSign.setIpRecordFilesService(this.IPRECORD_FILES_SERVICE);
           	        
               	    String descricaoVienna = this.retornarDescricaoDeVienna(codigoPedido, persistMarcas);
             		if(descricaoVienna != null && !descricaoVienna.isEmpty()){
             		  distinctiveSign.setLogoDescription(descricaoVienna.trim());
             		}
             		
          	    }
          	                    
             
       	    }
		    //--------------------------------------------------------------------------------
    	    
		    // Status do Pedido p/ o PROSUR 
			distinctiveSign.setStatusId(this.objetoString(objects[11]));
			
			
			// Classificação de Nice.
    		String activity = this.retornarEspecificacaoPedido(codigoPedido, persistMarcas);
       		NiceClass[] classesNice = this.pesquisarClassificacaoDeNice(codigoPedido,activity, persistMarcas);
       	    distinctiveSign.setNiceClasses(classesNice);
       	    
       		
		}catch(Exception e){
			LOGGER.error(" Não foi possível converter os processo ");
		}
		return distinctiveSign;
	
	}
	
	//MANUAL
	private DistinctiveSign converterMarcasParaDistinctiveSign(Object[] objects, ProcessoMarcasManualInterface persistMarcas){
		
		
		DistinctiveSign distinctiveSign = new DistinctiveSign();
    	
		try{
			
			
    		distinctiveSign.setOnapiId("BR");
    		distinctiveSign.setRecordType(IpRecordTypeEnum.DISTINCTIVE_SIGN);
    	    distinctiveSign.setIpRecordDetailLink(this.IPRECORD_DETAIL_LINK);
    	    		    	      
    	    int codigoPedido = this.objetoInteger(objects[0]);
    		String numeroProcesso = this.objetoString(objects[1]);
    		distinctiveSign.setApplicationId(numeroProcesso);
    		
    		distinctiveSign.setNationalPresentationDate(this.objetoDate(objects[2]));
			
    		distinctiveSign.setNationalPublishingDate(this.objetoDate(objects[3]));	
    		
			// Solicitante 
			String[] solicitantes = this.retornarSolicitantes(this.objetoString(objects[4]));
			distinctiveSign.setApplicantName(solicitantes);
		        	
			String descricaoApresentacaoMarca = this.objetoString(objects[6]).toUpperCase();     
	        distinctiveSign.setDistinctiveSignType(descricaoApresentacaoMarca);
	       	distinctiveSign.setPresentationType(descricaoApresentacaoMarca);
	       	
	       	Date dataDoRegistro = this.objetoDate(objects[7]);
       		if(dataDoRegistro != null){
       			distinctiveSign.setRegistrationDate(dataDoRegistro);
       		}
			
       		Date dataDeVencimento = this.objetoDate(objects[8]);
     		Date dataAtualizacaoIpas = this.objetoDate(objects[9]);
    	    
    		Date dataExpiracao = this.retornarDataExpiracaoMarca(dataDeVencimento,dataAtualizacaoIpas);
       		if(dataExpiracao != null){
    	        distinctiveSign.setExpiration(dataExpiracao);	
       		} 
           		     	   
       		String codigoSituacao = this.objetoString(objects[10]);
			this.verificarExigenciaDoPedidoPorCodigoDeSituacao(codigoSituacao);
    	    if(!this.isExigenciaFormal){
       		  
          	    String titulo = this.objetoString(objects[5]);
          	    distinctiveSign.setTitle(titulo);
               
          	    String tipoFigura = this.retornarExtencaoFiguraDoPedido(codigoPedido,persistMarcas);
          	    if(tipoFigura != null){
          	        
          	    	String nomeDoArquivo = numeroProcesso.trim().concat(".".concat(tipoFigura));
          	    	
          	    	FileData[] fileData = this.retornarFileData(nomeDoArquivo,titulo);
                    distinctiveSign.setFiles(fileData);
               	  	distinctiveSign.setIpRecordFilesService(this.IPRECORD_FILES_SERVICE);
           	        
               	    String descricaoVienna = this.retornarDescricaoDeVienna(codigoPedido, persistMarcas);
             		if(descricaoVienna != null && !descricaoVienna.isEmpty()){
             		  distinctiveSign.setLogoDescription(descricaoVienna.trim());
             		}
             		
          	    }
          	                    
             
       	    }
		    //--------------------------------------------------------------------------------
    	    
		    // Status do Pedido p/ o PROSUR 
			distinctiveSign.setStatusId(this.objetoString(objects[11]));
			
			
			// Classificação de Nice.
    		String activity = this.retornarEspecificacaoPedido(codigoPedido, persistMarcas); 
       		NiceClass[] classesNice = this.pesquisarClassificacaoDeNice(codigoPedido,activity, persistMarcas); 
       	    distinctiveSign.setNiceClasses(classesNice);
       	    
       		
		}catch(Exception e){
			LOGGER.error(" Não foi possível converter os processo ");
		}
		return distinctiveSign;
	
	}
	
    /**
     * Regra : Para pedido inexiste e em exigência formal não mostrar o 
	 * "nome da marca" e o logo.  
	 * 
     * @param numeroProcesso
     * @param codigoSituacao
     */
	private void verificarExigenciaDoPedidoPorCodigoDeSituacao(String codigoSituacao){
			
		this.isExigenciaFormal = Boolean.FALSE;
		if(codigoSituacao != null){
		    this.isExigenciaFormal = this.isTituloMarcaVisivel(codigoSituacao.trim());  	
		}
				
	}
	
	
	/***
	 * 
	 * Rertorna um array de string contendo os solicitate(s) da Marca.
	 * 
	 * @param solicitante
	 * @return
	 */
	private String[] retornarSolicitantes(String solicitante){
		
	   String[] arraySolicitantes = new String[1];
	   arraySolicitantes[0] = solicitante;
	   return arraySolicitantes; 		
		
	}
	
	
    /**
     * 
     * Método responsável em retornar os dados dos arquivos do processo de
     * marcas.
     * 
     * @param fileName
     * @param fileTitle
     * @param filesDescription
     * @return
     **/
    private FileData[] retornarFileData(String nomeArquivo, String filesDescription){
    
    	FileData file = new FileData();
    	file.setFileName(nomeArquivo);
    	file.setFileTitle(nomeArquivo);
    	file.setFileDescription(filesDescription);
       	
    	FileData[] lista = {file};
    	return lista;
    	
    }
    
    
    
    
    private java.sql.Date retornarDataExpiracaoMarca(Date dataVencimento, Date dataAtualizacaoIpas){
    	
    	java.sql.Date dataExpiracao = null;
		
    	if( (dataVencimento != null && !"".equals(dataVencimento)) || 
    			  (dataAtualizacaoIpas != null && !"".equals(dataAtualizacaoIpas))){
    				
    	    try {
    	    		    
    		   if(dataAtualizacaoIpas != null){
    			   dataExpiracao = (java.sql.Date) dataVencimento;
    		   }else{
    			  
    			   int anosVigencia = 10;
    	    	  Calendar dataExpiracaoAux = Calendar.getInstance();
    		      dataExpiracaoAux.setTime(dataVencimento);
    		      dataExpiracaoAux.add(Calendar.YEAR,anosVigencia); 
    		      DateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");
    		      String obj = formatter.format(dataExpiracaoAux.getTime());
    		      dataExpiracao = new java.sql.Date( ((java.util.Date)formatter.parse(obj)).getTime() );  
    	       
    		   }
    		   
    	    }catch(Exception e){
    	    	LOGGER.error(" ERRO ao converter datas ");
    	    }
    		  		
    	}
    	  return dataExpiracao;
    }   
    
	
    /***
     * 
     * Pesquisa a Classificação de Nice vinculado ao pedido Automatico.
     * 
     * Se o pedido não tiver a Classificação de Nice, deve retornar 
     * a classificação correlata da Classificação Nacional.
     * 
     * @param codigoProcesso
     * @param activity
     * @return
     */
	private NiceClass[] pesquisarClassificacaoDeNice(int codigoProcesso, String activity, ProcessoMarcasAutomaticoInterface persistMarcas){
		
		NiceClass[] niceClassArray = null;
		
		List<MrcClasNice> classificacoesNice = persistMarcas.pesquisarClasseNicePorId(codigoProcesso);
		if(classificacoesNice != null && !classificacoesNice.isEmpty()){
			
			classificacoesNice = this.retornarClassificacoesNice(codigoProcesso, persistMarcas);
			
		}else{
			
			classificacoesNice = this.retornarClasssificacaoNiceRelacaoNacional(codigoProcesso,persistMarcas );
		
		}
		
		niceClassArray = this.retornarNiceClass(classificacoesNice,activity);
	    return niceClassArray;
		
	}
	
	/***
     * 
     * Pesquisa a Classificação de Nice vinculado ao pedido Manual.
     * 
     * Se o pedido não tiver a Classificação de Nice, deve retornar 
     * a classificação correlata da Classificação Nacional.
     * 
     * @param codigoProcesso
     * @param activity
     * @return
     */
	private NiceClass[] pesquisarClassificacaoDeNice(int codigoProcesso, String activity, ProcessoMarcasManualInterface persistMarcas){
		
		NiceClass[] niceClassArray = null;
		
		List<MrcClasNice> classificacoesNice = persistMarcas.pesquisarClasseNicePorId(codigoProcesso);
		if(classificacoesNice != null && !classificacoesNice.isEmpty()){
			
			classificacoesNice = this.retornarClassificacoesNice(codigoProcesso, persistMarcas);
			
		}else{
			
			classificacoesNice = this.retornarClasssificacaoNiceRelacaoNacional(codigoProcesso,persistMarcas );
		
		}
		
		niceClassArray = this.retornarNiceClass(classificacoesNice,activity);
	    return niceClassArray;
		
	}
	
	
    /***
     * 
     * Retorna as classificações de Nice de um determinado pedido Automatico.
     * 
     * @param codigoProcesso
     * @return
     */
	private List<MrcClasNice> retornarClassificacoesNice(int codigoProcesso, ProcessoMarcasAutomaticoInterface persistMarcas){
     	List<MrcClasNice> classificacoes = persistMarcas.pesquisarClasseNicePorId(codigoProcesso);
		return classificacoes;
	}
	
	/***
     * 
     * Retorna as classificações de Nice de um determinado pedido Manual.
     * 
     * @param codigoProcesso
     * @return
     */
	private List<MrcClasNice> retornarClassificacoesNice(int codigoProcesso, ProcessoMarcasManualInterface persistMarcas){
     	List<MrcClasNice> classificacoes = persistMarcas.pesquisarClasseNicePorId(codigoProcesso);
		return classificacoes;
	}
	
	
	/**
	 * 
	 * Retorna as classificações de Nice em relação a classificação Nacional Automatico.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	private List<MrcClasNice> retornarClasssificacaoNiceRelacaoNacional(int codigoProcesso, ProcessoMarcasAutomaticoInterface persistMarcas){
		
		List<MrcClasNice> classificacoes = new ArrayList<MrcClasNice>();
		List<NiceClassAux> nicesAux = persistMarcas.buscaClasseNiceAuxPorId(codigoProcesso);
		if(nicesAux != null && !nicesAux.isEmpty()){
		    
			Iterator<NiceClassAux> it = nicesAux.iterator();
			while(it.hasNext()){
				
				NiceClassAux niceClass = it.next();
				MrcClasNice mrcClasNice = new MrcClasNice(niceClass.getCd_classe());
				classificacoes.add(mrcClasNice);
				
			}
			
		}		
		return classificacoes;
	}
	
	/**
	 * 
	 * Retorna as classificações de Nice em relação a classificação Nacional Manual.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	private List<MrcClasNice> retornarClasssificacaoNiceRelacaoNacional(int codigoProcesso, ProcessoMarcasManualInterface persistMarcas){
		
		List<MrcClasNice> classificacoes = new ArrayList<MrcClasNice>();
		List<NiceClassAux> nicesAux = persistMarcas.buscaClasseNiceAuxPorId(codigoProcesso);
		if(nicesAux != null && !nicesAux.isEmpty()){
		    
			Iterator<NiceClassAux> it = nicesAux.iterator();
			while(it.hasNext()){
				
				NiceClassAux niceClass = it.next();
				MrcClasNice mrcClasNice = new MrcClasNice(niceClass.getCd_classe());
				classificacoes.add(mrcClasNice);
				
			}
			
		}		
		return classificacoes;
	}
	
	/***
	 * 
	 * Regra : Para pedido inexiste e em exigência formal não mostrar o 
	 * "nome da marca" e o logo.  
	 * 
	 * Selecionar o nome da marca do processo se a situação for diferente 
	 * de : "10", "19", "51", "IPAS001", "IPAS002", "IPAS003", "IPAS004", 
	 *      "IPAS037", "IPAS059", "IPAS071", "IPAS072", "IPAS080", "IPAS081", 
	 *      "IPAS082", "IPAS083", "IPAS086", "IPAS087", "IPAS129", "IPAS202", 
	 *      "IPAS204", "IPAS325", "IPAS331", "IPAS356", "IPAS368", "IPAS426"
	 * 
	 * 
	 * @param nmMarcaProcess
	 * @param situacao
	 * @return
	 */ 
     private boolean isTituloMarcaVisivel(String codigoSituacao){
	   
	   StringBuilder codigosSituacao = new StringBuilder();
	   codigosSituacao.append("^(10|19|51|IPAS001|IPAS002|IPAS003|IPAS004|");
	   codigosSituacao.append("IPAS037|IPAS059|IPAS071|IPAS072|IPAS080|IPAS081|");
	   codigosSituacao.append("IPAS082|IPAS083|IPAS086|IPAS087|IPAS129|IPAS202|");
	   codigosSituacao.append("IPAS204|IPAS325|IPAS331|IPAS356|IPAS368|IPAS426)$");
	  
	   Pattern pattern = Pattern.compile(codigosSituacao.toString());
       Matcher matcher = pattern.matcher(codigoSituacao);
       boolean isPedidoMarcaExigencia = matcher.find();
       if(isPedidoMarcaExigencia){
    		return Boolean.TRUE;
    	}
    	return Boolean.FALSE;
  }
   
  /***
   * 
   *  Converte as classificações de Nice para o padrão do Prosur 'NiceClass'
   *  
   * @param classificacoesNice
   * @param activity
   * @return
   */
  private NiceClass[] retornarNiceClass(List<MrcClasNice> classificacoesNice,String activity){
		
		NiceClass[] niceClassArray = null;
		if(classificacoesNice != null && !classificacoesNice.isEmpty()){
			
			niceClassArray = new NiceClass[classificacoesNice.size()];
			for(int i =0; i < classificacoesNice.size(); i++){
				
				MrcClasNice mrcNice = classificacoesNice.get(i);    	
			    int cdClasse = Integer.parseInt(mrcNice.getCdClasse());
			    NiceClass nice = new NiceClass(activity,cdClasse);
			    niceClassArray[i] = nice;
			    
			}
		}
		return niceClassArray;
		
	}
	
    /***
     * 
     * Retorna a especificação do pedido Automatico
     * 
     * @param codigoProcesso
     * @return
     */
	private String retornarEspecificacaoPedido(int codigoProcesso, ProcessoMarcasAutomaticoInterface persistMarcas){
		
		String especificacao = null;
		
		List<MrcEspecificacao> especificacoes = persistMarcas.pesquisarEspecificacaoPorId(codigoProcesso);
		
		if(especificacoes != null && !especificacoes.isEmpty()){
			
			StringBuilder strBuilder = new StringBuilder();
			Iterator<MrcEspecificacao> it = especificacoes.iterator();
			while(it.hasNext()){
				
				String especificacaoAux = it.next().getTxEspecif();
				strBuilder.append(especificacaoAux);
				if(it.hasNext()){
					strBuilder.append(";");
				}
			}
			
			String aux = strBuilder.toString();
    		int totalCaracteres = aux.trim().length();
    		if(totalCaracteres <= 255){
    			especificacao = aux;
    		}else{
    			especificacao = aux.substring(0,250);
    		}
			
			
		}
		return  especificacao;
	}
	
	/***
     * 
     * Retorna a especificação do pedido Manual
     * 
     * @param codigoProcesso
     * @return
     */
	private String retornarEspecificacaoPedido(int codigoProcesso, ProcessoMarcasManualInterface persistMarcas){
		
		String especificacao = null;
		
		List<MrcEspecificacao> especificacoes = persistMarcas.pesquisarEspecificacaoPorId(codigoProcesso);
		
		if(especificacoes != null && !especificacoes.isEmpty()){
			
			StringBuilder strBuilder = new StringBuilder();
			Iterator<MrcEspecificacao> it = especificacoes.iterator();
			while(it.hasNext()){
				
				String especificacaoAux = it.next().getTxEspecif();
				strBuilder.append(especificacaoAux);
				if(it.hasNext()){
					strBuilder.append(";");
				}
			}
			
			String aux = strBuilder.toString();
    		int totalCaracteres = aux.trim().length();
    		if(totalCaracteres <= 255){
    			especificacao = aux;
    		}else{
    			especificacao = aux.substring(0,250);
    		}
			
			
		}
		return  especificacao;
	}
	
	/***
	 * 
	 * Retorna a extenção da figura do pedido Automatico.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	private String retornarExtencaoFiguraDoPedido(int codigoProcesso, ProcessoMarcasAutomaticoInterface persistMarcas){
		
		String extencaoFigura = null;
		MrcFiguraProcess figura = persistMarcas.pesquisarFiguraPorId(codigoProcesso );
		if(figura != null){
			extencaoFigura = figura.getTipoFigura();
     	}
       return extencaoFigura;
		
	}
	
	/***
	 * 
	 * Retorna a extenção da figura do pedido Manual.
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	private String retornarExtencaoFiguraDoPedido(int codigoProcesso, ProcessoMarcasManualInterface persistMarcas){
		
		String extencaoFigura = null;
		MrcFiguraProcess figura = persistMarcas.pesquisarFiguraPorId(codigoProcesso );
		if(figura != null){
			extencaoFigura = figura.getTipoFigura();
     	}
       return extencaoFigura;
		
	}
	
	/***
	 * 
	 * Retorna as descrições das Classificações de Vienna para um determinado pedido Automatico
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	private String retornarDescricaoDeVienna(int codigoProcesso, ProcessoMarcasAutomaticoInterface persistMarcas){
		
		String descricao = null;
		List<MrcClassifVienna> classificacoes = persistMarcas.pesquisarClassificacaoViennaPorId(codigoProcesso);
	    if(classificacoes != null && !classificacoes.isEmpty()){
	    	
	    	StringBuilder strBuilder = new StringBuilder(); 
	    	Iterator<MrcClassifVienna> it = classificacoes.iterator();
	    	while(it.hasNext()){
	    	   
	    		MrcClasVienna  classeVienna = it.next().getMrcClasVienna();
	    		String descricaoAux = classeVienna.getDsVienna();
	    		strBuilder.append(descricaoAux);
	    		if(it.hasNext()){
	    			strBuilder.append(";");
	    		}
	    		
	    	}
	    	descricao = strBuilder.toString();	    	
	    }
	  	return descricao;
	}
	
	/***
	 * 
	 * Retorna as descrições das Classificações de Vienna para um determinado pedido Manual
	 * 
	 * @param codigoProcesso
	 * @return
	 */
	private String retornarDescricaoDeVienna(int codigoProcesso, ProcessoMarcasManualInterface persistMarcas){
		
		String descricao = null;
		List<MrcClassifVienna> classificacoes = persistMarcas.pesquisarClassificacaoViennaPorId(codigoProcesso);
	    if(classificacoes != null && !classificacoes.isEmpty()){
	    	
	    	StringBuilder strBuilder = new StringBuilder(); 
	    	Iterator<MrcClassifVienna> it = classificacoes.iterator();
	    	while(it.hasNext()){
	    	   
	    		MrcClasVienna  classeVienna = it.next().getMrcClasVienna();
	    		String descricaoAux = classeVienna.getDsVienna();
	    		strBuilder.append(descricaoAux);
	    		if(it.hasNext()){
	    			strBuilder.append(";");
	    		}
	    		
	    	}
	    	descricao = strBuilder.toString();	    	
	    }
	  	return descricao;
	}
	
}