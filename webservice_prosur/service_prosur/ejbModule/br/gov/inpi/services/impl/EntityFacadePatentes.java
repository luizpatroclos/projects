package br.gov.inpi.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.jboss.logging.Logger;

import br.gov.inpi.patentes.beans.DesignPatentLocarno;
import br.gov.inpi.patentes.beans.DiClassificacao;
import br.gov.inpi.patentes.beans.DiPedido;
import br.gov.inpi.patentes.beans.PtnClassif;
import br.gov.inpi.patentes.beans.PtnClassifDesc;
import br.gov.inpi.patentes.beans.PtnEscritorio;
import br.gov.inpi.patentes.beans.PtnPaisProsur;
import br.gov.inpi.patentes.beans.PtnPct;
import br.gov.inpi.patentes.beans.PtnPedido;
import br.gov.inpi.patentes.beans.PtnPedidoDivido;
import br.gov.inpi.patentes.beans.PtnPrioridade;
import br.gov.inpi.patentes.beans.PtnProcessoProsur;
import br.gov.inpi.patentes.beans.PtnResumoPedido;
import br.gov.inpi.patentes.beans.PtnSolicitante;
import br.gov.inpi.patentes.beans.PtnTipoDespacho;
import br.gov.inpi.services.EntityInterfacePatentes;



@Stateless(name = "EntityFacadePatentes")
public class EntityFacadePatentes implements EntityInterfacePatentes {

		
private static final Logger LOGGER = Logger.getLogger(EntityFacadePatentes.class);
	
	
	@PersistenceContext(unitName = "prosur_patente")
	protected EntityManager entityManager;
	
	
	protected static EntityManagerFactory entityManagerFactory;
	
	
	//private String persistenceUnitName = "prosur_patente";

	
	public EntityFacadePatentes() {
		
		/*if (entityManagerFactory == null)
			entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		
		entityManager = entityManagerFactory.createEntityManager();*/

	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void save(Object object) {
		
		if(object != null){
			this.entityManager.persist(object);
		    this.entityManager.flush();
	    }
	}

	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Object object) {
		entityManager.merge(object);
	}

	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Object object) {
		entityManager.remove(object);
	}

	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Object undo(Object object) {

		entityManager.refresh(object);
		return object;

	}

	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Object undo(Class<Object> klass, Object id) {
		return entityManager.find(klass, id);
	}

	
	
	
	@SuppressWarnings("rawtypes")
	public List list(String namedQuery) {
		
		return this.entityManager.createNamedQuery(namedQuery).getResultList();
	
	}
	
	
	
	// Desenho Industrial ----------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public List<DiPedido> pesquisarPedidosDiPorSemana() {
		
	   List<DiPedido> pedidos = new ArrayList<DiPedido>();
	   try{
		    
		   StringBuilder strBuilder = new StringBuilder();
		   strBuilder.append(" SELECT DISTINCT di.cod_pedido, di.cod_situacao, di.num_pedido, ");
		   strBuilder.append(" di.dt_deposito, di.cod_natureza, di.cd_tipo_pedido, di.titulo,  ");
		   strBuilder.append(" di.dt_fase_nacional  ");
		   strBuilder.append(" FROM di_pedido di, crp_rpi crp, ptn_despacho des,ptn_tipo_despacho tp ");
		   // Linha real para carga Semanal 
		   //strBuilder.append(" WHERE crp.num_rpi = (SELECT MAX(num_rpi) FROM crp_rpi) ");
		   strBuilder.append(" WHERE crp.num_rpi = '2274'");
		   strBuilder.append(" AND crp.num_rpi = des.num_rpi AND des.cod_pedido = di.cod_pedido ");
		   strBuilder.append(" AND des.cd_tipo_despacho = tp.cd_tipo_despacho ");
		  
		   Query query = this.entityManager.createNativeQuery(strBuilder.toString(), DiPedido.class);
		   pedidos = (List<DiPedido>) query.getResultList();
		  
	   }catch(NoResultException nre){
		   LOGGER.info("Nenhum pedido encontrado. ");
	   }
	   
	   return pedidos;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<DiPedido> pesquisarPedidosDiRetroativa() {
		   
		   List<DiPedido> pedidos = new ArrayList<DiPedido>();
		   
		   try{
			    
			   StringBuilder strBuilder = new StringBuilder();
			   strBuilder.append(" SELECT FIRST 2000 DISTINCT di.cod_pedido, di.cod_situacao, di.num_pedido, ");
			   strBuilder.append(" di.dt_deposito, di.cod_natureza, di.cd_tipo_pedido, di.titulo,  ");
			   strBuilder.append(" di.dt_fase_nacional  ");
			   strBuilder.append(" FROM di_pedido di, ptn_despacho des");
			   strBuilder.append(" WHERE di.num_pedido [1,2] in ('DI', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', 'MI')");
			   strBuilder.append(" AND des.cod_pedido = di.cod_pedido ");
			   strBuilder.append(" AND des.cod_pedido NOT IN ");
			   strBuilder.append(" (SELECT cod_pedido FROM  ptn_processo_prosur )  ");
			   strBuilder.append(" AND di.cod_pedido  NOT IN (SELECT  DISTINCT t20.cod_pedido FROM di_pedido t20 WHERE t20.dt_deposito < to_char(dt_deposito,'1997-05-14')  AND t20.cod_pedido NOT IN (SELECT DISTINCT  t22.cod_pedido  FROM ptn_despacho t22 WHERE t22.cd_tipo_despacho  IN  ('697', '700', '808', '972', '973', '976')))");
			 
			   Query query = this.entityManager.createNativeQuery(strBuilder.toString(), DiPedido.class);
			   pedidos = (List<DiPedido>) query.getResultList();
			   	   
		   }catch(NoResultException nre){
			   LOGGER.info("Nenhum pedido encontrado.");
		   }
		   
		   return pedidos;
	
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DesignPatentLocarno> pesquisarDescricaoLocarno(Integer codigoPedido) {
		
		List<DesignPatentLocarno> classificacoes = new ArrayList<DesignPatentLocarno>();
		try{
			
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT di.num_pedido, di.cod_pedido, ");
			strBuilder.append(" clas.cod_internacional, des.des_classificacao");
    		strBuilder.append(" FROM ptn_classif_pedido pcla, ptn_classif clas, ");
    		strBuilder.append(" ptn_classif_desc des,di_pedido di ");
			strBuilder.append(" WHERE di.cod_pedido = ");
			strBuilder.append(codigoPedido);
			strBuilder.append(" AND di.cod_pedido = pcla.cod_pedido ");
			strBuilder.append(" AND pcla.cod_classificacao = clas.cod_classificacao  ");
			strBuilder.append(" AND clas.cod_classificacao = des.cod_classificacao ");
			strBuilder.append(" AND clas.cod_internacional <= '6' ");
			strBuilder.append(" ORDER BY  pcla.ordem_pedido ASC ");
	
			Query query = this.entityManager.createNativeQuery(strBuilder.toString(),DesignPatentLocarno.class);
			classificacoes = (List<DesignPatentLocarno>) query.getResultList();
			
		}catch(NoResultException nre){
			LOGGER.info("Nenhuma classificação de Locarno encontrada. ");
		}
		
		return classificacoes;
	}
	// Fim Desenho Industrial ----------------------------------------------------------
	
	
	
	// Patentes ------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public List<PtnPedido> pesquisarPedidosDePatentePorSemana() {
	
		List<PtnPedido> pedidos = new ArrayList<PtnPedido>();
		
		try{
			
			StringBuilder strBuilder = new StringBuilder();
    		strBuilder.append(" SELECT ped.cod_pedido, ped.cod_situacao, ped.num_pedido, ");
    		strBuilder.append(" ped.dt_deposito ,ped.cod_natureza, ");
    		strBuilder.append("  ped.cd_tipo_pedido, ped.titulo, ped.dt_fase_nacional");
    		strBuilder.append(" FROM ptn_pedido ped, crp_rpi crp, ptn_despacho ");
    		strBuilder.append(" des,ptn_tipo_despacho tp ");
    		 // Linha real para carga Semanal
			//strBuilder.append(" WHERE crp.num_rpi = (SELECT MAX(num_rpi) FROM crp_rpi)  ");
			strBuilder.append(" WHERE crp.num_rpi = '2274'");
			strBuilder.append(" AND ped.num_pedido [1,2] NOT IN ('DI', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', 'MI', 'PP') ");
			strBuilder.append(" AND ped.cod_situacao <> 'RS' ");
			strBuilder.append(" AND crp.num_rpi = des.num_rpi  ");
			strBuilder.append(" AND des.cod_pedido = ped.cod_pedido   ");
			strBuilder.append(" AND des.cd_tipo_despacho = tp.cd_tipo_despacho ");
			strBuilder.append(" AND ped.num_pedido IS NOT NULL ");
			strBuilder.append(" AND ped.num_pedido <> '' ");
		 	
			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), PtnPedido.class);
			pedidos = (List<PtnPedido>) query.getResultList();
			
		}catch(NoResultException nre){
    		LOGGER.info("Nenhum inventor encontrado para o pedido.");
    	}
		
		return pedidos;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PtnPedido> pesquisarPedidosDePatenteRetroativa() {
         
		List<PtnPedido> pedidos = new ArrayList<PtnPedido>();
		
		try{
			
			StringBuilder strBuilder = new StringBuilder();
    		strBuilder.append(" SELECT FIRST 13000 DISTINCT ped.cod_pedido, ped.cod_situacao, ped.num_pedido, ");
    		strBuilder.append(" ped.dt_deposito ,ped.cod_natureza, ");
    		strBuilder.append("  ped.cd_tipo_pedido, ped.titulo, ped.dt_fase_nacional");
    		strBuilder.append(" FROM ptn_pedido ped, ptn_despacho des ");
			strBuilder.append(" WHERE ped.num_pedido [1,2] NOT IN ('DI', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', 'MI', 'PP') ");
			strBuilder.append(" AND ped.cod_situacao <> 'RS' ");
      		strBuilder.append(" AND des.cod_pedido = ped.cod_pedido ");
			strBuilder.append(" AND ped.cod_pedido NOT IN ");
			strBuilder.append(" ( SELECT cod_pedido FROM  ptn_processo_prosur )  ");
			
			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), PtnPedido.class);
			pedidos = (List<PtnPedido>) query.getResultList();
			
		}catch(NoResultException nre){
    		LOGGER.info("Nenhum inventor encontrado para o pedido.");
    	}
		
		return pedidos;
	}	
    // Fin de Patentes -----------------------------------------------------------------	
	
	

	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PtnPrioridade> pesquisarPrioridades(Integer codigoPedido) {
		
		List<PtnPrioridade> prioridades = new ArrayList<PtnPrioridade>();
		try{
			
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT cod_pedido, num_pedido_origem, dt_pedido_origem, ");
			strBuilder.append(" cod_pais FROM ptn_prioridade ");
			strBuilder.append(" WHERE cod_pedido = "+codigoPedido);
			strBuilder.append(" ORDER BY  dt_pedido_origem DESC ");
			
			Query query = this.entityManager.createNativeQuery(strBuilder.toString(),PtnPrioridade.class);
	        prioridades = query.getResultList();
			if(prioridades == null){
				LOGGER.info(" Sem prioridades para o processo " + codigoPedido);
				return null;
			}
			
		}catch(NoResultException nre){
			LOGGER.info("Pedido sem prioridade(s). ");
		}
	    return prioridades;
	}
	
	
	
	
	
	@Override
	public PtnPedido pesquisarPedidoPatente(Integer codigoPedido) {
	
		PtnPedido ptnPedido = null;
		try{
			
			Query query = this.entityManager.createNamedQuery("PtnPedido.findByCodPedido");
			query.setParameter("codPedido",codigoPedido);
			ptnPedido = (PtnPedido) query.getSingleResult();
			
		}catch(NoResultException e){
			LOGGER.info("Pedido não encontrado.");
		}
		return ptnPedido;
	}

	
	
	

	@Override
	public PtnPct pesquisarPctPedidoPatente(Integer codigoPedido) {
		
		PtnPct pct = new PtnPct();
		try{
		    Query query = this.entityManager.createNamedQuery("PtnPct.findByCodPedido"); 
		    query.setParameter("codPedido",codigoPedido);
		    pct = (PtnPct)query.getSingleResult();
		}catch(NoResultException e){
			LOGGER.info("Pedido sem PCT.");
		}
		return pct;
	}


	
	
	@SuppressWarnings("unchecked")
	@Override
    public List<PtnSolicitante> pesquisarSolicitantePorCodigoPedido(Integer codigoPedido) {
	
		List<PtnSolicitante> ptnSolicitantes = new ArrayList<PtnSolicitante>();
		
		try{
			 StringBuilder strBuilder = new StringBuilder();
			 strBuilder.append(" SELECT pes.cod_pessoa, pes.nome_completo, ");
			 strBuilder.append(" ende.cod_pais,req.ordem_importancia ");
			 strBuilder.append(" FROM crp_pes_requerente pes, ptn_requerente req, ");
			 strBuilder.append(" crp_endereco ende ");
			 strBuilder.append(" WHERE req.cod_pedido = ");
			 strBuilder.append(codigoPedido);
     		 strBuilder.append(" AND req.cod_requerente = pes.cod_pessoa ");
			 strBuilder.append(" AND pes.cod_pessoa = ende.cod_pessoa ");
			 strBuilder.append(" AND req.dt_termino is NULL ");
			 strBuilder.append(" ORDER BY req.ordem_importancia ASC ");
			 
			 Query query = this.entityManager.createNativeQuery(strBuilder.toString(),PtnSolicitante.class);
			 ptnSolicitantes = (List<PtnSolicitante>)query.getResultList();
							 
		}catch(NoResultException e){
			LOGGER.info(" Solicitante não encontrado.");
		}
			
		return ptnSolicitantes;
		
	}

	
	

	@Override
	public PtnEscritorio pesquisarEscritorio(Integer codigoPedido) {
		
        PtnEscritorio escritorio = new PtnEscritorio(); 
        
        try{
        	StringBuilder strBuilder = new StringBuilder();
        	strBuilder.append(" SELECT FIRST 1 prc.cod_procurador, ppro.nome_completo ");
        	strBuilder.append(" FROM crp_pes_procurador ppro, ptn_procurador prc ");
        	strBuilder.append(" WHERE prc.cod_pedido = ");
        	strBuilder.append(codigoPedido);
        	strBuilder.append(" AND prc.cod_procurador = ppro.cod_pessoa ");
      	
        	Query query = this.entityManager.createNativeQuery(strBuilder.toString(),
        			                                           PtnEscritorio.class);
        	escritorio = (PtnEscritorio) query.getSingleResult();
        	
        }catch(NoResultException e){
			LOGGER.info("Pedido escritorio não encontrado.");
		}
		return escritorio;
	}

	
	
	

	@Override
	public Date pesquisarDataDePublicacaoPT(Integer codigoPedido) {
		
		Date dataPublicacao = null;
		
		try{
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT FIRST 1 cr.dt_publica_ptn ");
			strBuilder.append(" FROM ptn_despacho des, ptn_tipo_despacho tp, crp_rpi cr ");
			strBuilder.append(" WHERE des.cod_pedido = ");
			strBuilder.append(codigoPedido);
			strBuilder.append(" AND des.num_rpi = cr.num_rpi ");
			strBuilder.append(" AND des.cd_tipo_despacho = tp.cd_tipo_despacho ");
			strBuilder.append(" AND tp.cd_tipo_despacho IN ");
			strBuilder.append(" (492,813,824,825,827,859) ");
			strBuilder.append(" ORDER BY cr.dt_publica_ptn ASC ");
			
			Query query = this.entityManager.createNativeQuery(strBuilder.toString());
			dataPublicacao = (Date)query.getSingleResult();
			
		}catch(NoResultException nre){
			LOGGER.info(" Data de publicação não encontrado. ");
		}
		return dataPublicacao;
	}


	
	@Override
	public Date pesquisarDataDePublicacaoDI(Integer codigoPedido) {
		
		Date dataPublicacao = null;
		
		try{
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT FIRST 1 cr.dt_publica_ptn ");
			strBuilder.append(" FROM ptn_despacho des, ptn_tipo_despacho tp, crp_rpi cr ");
			strBuilder.append(" WHERE des.cod_pedido = ");
			strBuilder.append(codigoPedido);
			strBuilder.append(" AND des.num_rpi = cr.num_rpi ");
			strBuilder.append(" AND des.cd_tipo_despacho = tp.cd_tipo_despacho ");
			strBuilder.append(" AND tp.cd_tipo_despacho IN ");
			strBuilder.append(" (697,700,808,972,973,976) ");
			strBuilder.append(" ORDER BY cr.dt_publica_ptn ASC ");
			
			Query query = this.entityManager.createNativeQuery(strBuilder.toString());
			dataPublicacao = (Date)query.getSingleResult();
			
		}catch(NoResultException nre){
			LOGGER.info(" Data de publicação não encontrado. ");
		}
		return dataPublicacao;
	}
	
	
	
	@Override
	public PtnTipoDespacho pesquisarTipoDespacho(Integer codigoPedido) {
		
		PtnTipoDespacho ptnTipoDespacho = null;
		
		try{
	    	StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT FIRST 1 tp.cod_despacho_rpi,des.cd_tipo_despacho ");
			strBuilder.append(" , tp.des_tipo_despacho,tp.num_lei ");
			strBuilder.append(" FROM ptn_despacho des, ptn_tipo_despacho tp  ");
			strBuilder.append(" WHERE des.cod_pedido = " + codigoPedido);
			strBuilder.append(" AND des.cd_tipo_despacho = tp.cd_tipo_despacho ");
			strBuilder.append(" ORDER BY num_rpi DESC ");
			
			Query query = this.entityManager.createNativeQuery(strBuilder.toString(), 
					                                           PtnTipoDespacho.class);
			
			ptnTipoDespacho = (PtnTipoDespacho)query.getSingleResult();
			
		}catch(NoResultException nre){
			LOGGER.info("Despacho não encontrado para o pedido." + codigoPedido);
		}
		return ptnTipoDespacho;
	
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PtnTipoDespacho> pesquisarTodosTipoDespachos(Integer codigoPedido) {
	
		List<PtnTipoDespacho> despachos = new ArrayList<PtnTipoDespacho>();
		try{
			
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT tp.cod_despacho_rpi,des.cd_tipo_despacho ");
			strBuilder.append(" ,tp.des_tipo_despacho,tp.num_lei ");
			strBuilder.append(" FROM ptn_despacho des, ptn_tipo_despacho tp  ");
			strBuilder.append(" WHERE des.cod_pedido = " + codigoPedido);
			strBuilder.append(" AND des.cd_tipo_despacho = tp.cd_tipo_despacho ");
			strBuilder.append(" ORDER BY num_rpi ASC ");
			
			Query query = this.entityManager.createNativeQuery(strBuilder.toString(),PtnTipoDespacho.class);
			despachos = (List<PtnTipoDespacho>) query.getResultList();
						
		}catch(NoResultException nre){
			LOGGER.info(" Despachos não encontrados para o pedido.");
		}
		
		return despachos;
	}
	
	
	
	
	
    @SuppressWarnings("unchecked")
	@Override
	public List<String> pesquisarInventoresPorCodigoDoPedido(Integer codigoPedido) {
		
    	List<String> inventores = new ArrayList<String>();
    	
    	try{
    		
        	StringBuilder strBuilder = new StringBuilder();
    		strBuilder.append(" SELECT crp.nome_completo FROM crp_pes_inventor crp, ");
    		strBuilder.append(" ptn_inventor inv WHERE inv.cod_pedido = ");
    		strBuilder.append(codigoPedido);
    		strBuilder.append(" AND inv.cod_inventor = cod_pessoa ");
    		    		
    		Query query = this.entityManager.createNativeQuery(strBuilder.toString());
    		inventores = (List<String>)query.getResultList();
    		
    	}catch(NoResultException nre){
    		LOGGER.info(" Nenhum inventor encontrado para um pedido. ");
    	}
    	return inventores;
	
    }


	


	@SuppressWarnings("unchecked")
	@Override
	public List<PtnClassif> pesquisarClassificacoesPedido(Integer codigoPedido) {
		
		List<PtnClassif> classificacoes = new ArrayList<PtnClassif>();
		
		try{
			
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT clas.cod_internacional, clas.version_indicator, ");
			strBuilder.append(" clas.num_versao, clas.cd_sistema_classif, clas.cod_classificacao");
    		strBuilder.append(" FROM ptn_classif_pedido pcla, ptn_classif clas ");
			strBuilder.append(" WHERE pcla.cod_pedido =" + codigoPedido);
			strBuilder.append(" AND pcla.cod_classificacao = clas.cod_classificacao  ");
			strBuilder.append(" ORDER BY  pcla.ordem_pedido ASC ");
			
			Query query = this.entityManager.createNativeQuery(strBuilder.toString(),PtnClassif.class);
			classificacoes = (List<PtnClassif>) query.getResultList();
			
		}catch(NoResultException nre){
			LOGGER.info(" Nenhuma classificação encontrada para o pedido.");
		}
		
		return classificacoes;
	}
	
		
	
	
	
	
	@SuppressWarnings("rawtypes")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List list(String namedQuery, HashMap mapParameters) {
		
		Query query = entityManager.createNamedQuery(namedQuery);
     	if (!mapParameters.isEmpty()) {
			
     		@SuppressWarnings("unchecked")
			Set<String> chaves = mapParameters.keySet();
			for (String chave : chaves) {
				if (chave != null)
					query.setParameter(chave, mapParameters.get(chave));
			}
		}
	
     	return query.getResultList();
	
	}

	
	

	@SuppressWarnings("rawtypes")
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List pesquisarTodosObject(String pesquisa) {
		
		Query query = this.entityManager.createNativeQuery(pesquisa);
		List processos = query.getResultList(); 
		return processos;
		
	}

	

	
	
	@SuppressWarnings("unchecked")
	@Override
	public Object pesquisarPorCodigoPedido(Object object, Integer codigoPedido) {
	    return this.entityManager.find((Class<Object>) object,codigoPedido);
	}

	
	

	@Override
	@SuppressWarnings("unchecked")
	public List<String> pesquisarTodos(String pesquisa) {
	   Query query = this.entityManager.createNativeQuery(pesquisa);
       List<String> resultado = query.getResultList();
	   return resultado;
	}

	
	

	@Override
	public Object pesquisar(String pesquisa) {
	    
		Object obj = null;
		try{
			Query query = this.entityManager.createNativeQuery(pesquisa);
			obj = query.getSingleResult();	
		}catch(NoResultException nre){
		    LOGGER.info(" Nenhum registro encontrado :: "+ nre.getMessage());
		}
		return obj;
	}
	
	
    @Override
	public void salvar(Set<PtnProcessoProsur> processos) {
	
		Iterator<PtnProcessoProsur> it = processos.iterator();
		while(it.hasNext()){
			
			PtnProcessoProsur processo = it.next();
		    this.entityManager.persist(processo);
		    this.entityManager.flush();
		    
		}
		
	}


	@Override
	public String pesquisarPedidoRenumerado(String numeroPedido) {
		// TODO Auto-generated method stub
		return null;
	}



	


	@Override
	public List<PtnPedido> pesquisarPedidosDePatentePorNumeroPedido(String numeroPedido) {
        
		List<PtnPedido> pedidos = new ArrayList<PtnPedido>();
		
		try{
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT DISTINCT ped.cod_pedido, ped.cod_situacao, ped.num_pedido, ");
    		strBuilder.append(" ped.dt_deposito ,ped.cod_natureza, ");
    		strBuilder.append("  ped.cd_tipo_pedido, ped.titulo, ped.dt_fase_nacional");
    		strBuilder.append(" FROM ptn_pedido ped, crp_rpi crp, ptn_despacho ");
    		strBuilder.append(" des,ptn_tipo_despacho tp ");
			strBuilder.append(" WHERE ped.num_pedido = '"+numeroPedido+"'");
			strBuilder.append(" AND ped.num_pedido [1,2] NOT IN ('DI', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', 'MI', 'PP') ");
			strBuilder.append(" AND ped.cod_situacao <> 'RS' ");
			strBuilder.append(" AND crp.num_rpi = des.num_rpi  ");
			strBuilder.append(" AND des.cod_pedido = ped.cod_pedido   ");
			strBuilder.append(" AND des.cd_tipo_despacho = tp.cd_tipo_despacho ");
			strBuilder.append(" AND ped.num_pedido IS NOT NULL ");
			strBuilder.append(" AND ped.num_pedido <> '' ");
			
			
			
			/*SELECT distinct ped.cod_pedido, ped.cod_situacao, ped.num_pedido,  ped.dt_deposito ,ped.cod_natureza,   ped.cd_tipo_pedido, ped.titulo, ped.dt_fase_nacional
			 FROM ptn_pedido ped, crp_rpi crp, ptn_despacho  des,ptn_tipo_despacho tp  
			WHERE  ped.num_pedido = 'MU7702238'
			 AND ped.num_pedido [1,2] NOT IN ('DI', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', 'MI', 'PP')  
			AND ped.cod_situacao <> 'RS'  AND crp.num_rpi = des.num_rpi   AND des.cod_pedido = ped.cod_pedido    
			AND des.cd_tipo_despacho = tp.cd_tipo_despacho  
			AND ped.num_pedido IS NOT NULL  
			AND ped.num_pedido <> '' */
			
			
			//88888888888888888888888888888888888888888
			
			/*StringBuilder strBuilder = new StringBuilder();
    		strBuilder.append(" SELECT  DISTINCT ped.cod_pedido, ped.cod_situacao, ped.num_pedido, ");
    		strBuilder.append(" ped.dt_deposito ,ped.cod_natureza, ");
    		strBuilder.append(" ped.cd_tipo_pedido, ped.titulo, ped.dt_fase_nacional");
    		strBuilder.append(" FROM ptn_pedido ped, crp_rpi crp, ptn_despacho ");
    		strBuilder.append(" des,ptn_tipo_despacho tp ");
			strBuilder.append(" WHERE ped.num_pedido = '" + numeroPedido+"'");
			strBuilder.append(" AND crp.num_rpi = des.num_rpi  ");
			strBuilder.append(" AND des.cod_pedido = ped.cod_pedido   ");
			strBuilder.append(" AND des.cd_tipo_despacho = tp.cd_tipo_despacho ");
			strBuilder.append(" AND ped.num_pedido IS NOT NULL ");
			strBuilder.append(" AND ped.num_pedido <> '' ");*/
		   /* strBuilder.append(" AND ped.num_pedido NOT IN ");
			strBuilder.append(" ( SELECT no_processo FROM  ptn_processo_prosur )  ");
			*/
			Query query = this.entityManager.createNativeQuery(strBuilder.toString(),PtnPedido.class);
			pedidos = (List<PtnPedido>) query.getResultList();
			
		}catch(NoResultException nre){
    		LOGGER.info("Nenhum inventor encontrado para o pedido.");
    	}
		
		return pedidos;
	}


	

	@Override
	public PtnResumoPedido pesquisarResumoDoPedido(long codigoPedido) {
		
		PtnResumoPedido resumo = new PtnResumoPedido();
		try{
			
			Query query = this.entityManager.createNamedQuery("PtnResumoPedido.findByCodPedido");
			query.setParameter("codPedido",codigoPedido);
			resumo = (PtnResumoPedido) query.getSingleResult();
			
		}catch(NoResultException nre){
			LOGGER.info(" Pedido sem resumo. ");
		}
		
		return resumo;
	}

	
	

	@Override
	public Integer verificarCodigoOrginalPedidoDivido(int codigoPedido) {
        
		Integer codigoPedidoOrigem = null;
		
		try{
			
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT cd_pedido_origem,cd_pedido_derivad,dt_vinculo,cd_vinculo ");
			strBuilder.append(" FROM ptn_vinculo_pedido WHERE cd_pedido_derivad = "+ codigoPedido);
			Query query = this.entityManager.createNativeQuery(strBuilder.toString(),PtnPedidoDivido.class);
			PtnPedidoDivido ptnPedidoDerivado = (PtnPedidoDivido) query.getSingleResult();
			if(ptnPedidoDerivado != null){
			    codigoPedidoOrigem = ptnPedidoDerivado.getCdPedidoOrigem();
			    LOGGER.info(" cd " +codigoPedidoOrigem );
			    return codigoPedidoOrigem;
			}
						
		}catch(NoResultException nre){
			LOGGER.info(" Pedido sem divisão. ");
		}
		
		return null;
	}


	@Override
	public DiPedido pesquisarPorNumeroDePedidoDesenhoIndustrial(String numeroPedido) {
	
		DiPedido pedido = new DiPedido();
		
		try{
			
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT DISTINCT di.cod_pedido, di.cod_situacao, di.num_pedido, ");
			strBuilder.append(" di.dt_deposito, di.cod_natureza, di.cd_tipo_pedido, di.titulo, ");
			strBuilder.append(" di.dt_fase_nacional  ");
			strBuilder.append(" FROM di_pedido di, crp_rpi crp, ptn_despacho des,ptn_tipo_despacho tp ");
			strBuilder.append(" WHERE di.num_pedido = '");
			strBuilder.append(numeroPedido);
			strBuilder.append("'AND crp.num_rpi = des.num_rpi AND des.cod_pedido = di.cod_pedido ");
			strBuilder.append(" AND des.cd_tipo_despacho = tp.cd_tipo_despacho  ");
			strBuilder.append(" AND di.num_pedido IS NOT NULL AND di.num_pedido <>'' ");
			
			Query query = this.entityManager.createNativeQuery(strBuilder.toString(),DiPedido.class);
			pedido = (DiPedido)query.getSingleResult();
						
		}catch(NoResultException nre){
    		LOGGER.info("Pedido não encontrado.");
    	}
		return pedido;
	}


	
	
	
	@Override
	public PtnClassifDesc retornarDescricaoDosProjetosDI(Integer codigoPedido) {
		
		PtnClassifDesc ptnClassifDesc = null;
		try{
			
			Query query = this.entityManager.createNamedQuery("PtnClassifDesc.findByCodClassificacao");
			query.setParameter("codClassificacao",codigoPedido);
			ptnClassifDesc = (PtnClassifDesc) query.getSingleResult();
			
		}catch(NoResultException nre){
			LOGGER.info(" Descrição de projeto Desenho Industrial não encontrado");
		}
		return ptnClassifDesc;
	}


	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> pesquisarAutoresPorCodigoDoPedidoDI(Integer codigoPedido) {
        
		List<String> inventores = new ArrayList<String>();
    	
    	try{
    		
        	StringBuilder strBuilder = new StringBuilder();
    		strBuilder.append(" SELECT crp.nome_completo FROM crp_pes_inventor crp, ");
    		strBuilder.append(" ptn_inventor inv WHERE inv.cod_pedido = ");
    		strBuilder.append(codigoPedido);
    		strBuilder.append(" AND inv.cod_inventor = cod_pessoa ");
    		strBuilder.append(" AND inv.anonimato = 'N' ");
    		    		
    		Query query = this.entityManager.createNativeQuery(strBuilder.toString());
    		inventores = (List<String>)query.getResultList();
    		
    	}catch(NoResultException nre){
    		LOGGER.info(" Nenhum inventor encontrado para um pedido. ");
    	}
    	return inventores;
	}

	
	
	

	@Override
	public List<DiClassificacao> pesquisarClassificacoesPedidoDI(Integer codigoPedido) {
		
		List<DiClassificacao> classificacoes = new ArrayList<DiClassificacao>();
		
		try{
			
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" SELECT distinct 'LOC (7) CL. '|| ");
			strBuilder.append(" substr(t3.cod_internacional,1,5) as locarno ");
			strBuilder.append(" FROM di_pedido t1, ptn_classif_pedido t2, ptn_classif t3 ");
			strBuilder.append(" WHERE t1.cod_pedido = t2.cod_pedido ");
			strBuilder.append(" AND t2.cod_classificacao = t3.cod_classificacao   ");
			strBuilder.append(" AND T1.dt_deposito >='2001-01-01' ");
			strBuilder.append(" AND T1.cod_pedido= " + codigoPedido);
			
			Query query = this.entityManager.createNativeQuery(strBuilder.toString(),DiClassificacao.class);
			classificacoes = (List<DiClassificacao>) query.getResultList();
				
		}catch(NoResultException nre){
    		LOGGER.info(" Nenhuma classificação encontrada para o pedido." + codigoPedido);
    	}
		
		return classificacoes;
	}

	
	
	
	

	@Override
	public List<PtnPaisProsur> retornarTodosPaisesProsur() {
		
		List<PtnPaisProsur> paises = new ArrayList<PtnPaisProsur>();
		Query query = this.entityManager.createNamedQuery("PtnPaisProsur.findAll");
		paises = query.getResultList();
		return paises;
	
	}


	@Override
	public boolean isPedidoEmSigilo(String numeroPedido) {
		
		boolean isPedidoSigilo = Boolean.FALSE;
		
	    try{
		    	    	
	    	StringBuilder strBuilder = new StringBuilder();
	    	strBuilder.append(" SELECT ");
			strBuilder.append(" CASE WHEN T1.no_pedido[1,2] NOT IN ('PI', 'PP', '10', '11', '12', '14', '15', '16', '17', '18', '19', ");
			strBuilder.append(" 'MU', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', ");
			strBuilder.append(" 'DI', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', ");
			strBuilder.append(" 'MI', 'C1', 'C2', 'C3', 'C4', 'C5', 'C6', 'C7', 'C8', 'C9', '13') THEN 'F' ");
			strBuilder.append(" WHEN (SELECT COUNT(t22.cd_pedido) ");
			strBuilder.append("      FROM PTNBASE@JERRY_SOC:ptn_pedido t11, PTNBASE@JERRY_SOC:ptn_despacho t22 ");
			strBuilder.append("      WHERE t11.cd_pedido         = t22.cd_pedido ");
			strBuilder.append(" AND t11.cd_pedido         = t1.cd_pedido ");
			strBuilder.append(" AND t11.cd_defesa_naciona = 'N' ");
			strBuilder.append(" AND t11.no_pedido[1,2]    NOT IN ('DI', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39') ");
			strBuilder.append(" AND DATE(t11.dt_deposit)  > DATE('1997-05-31') ");
			strBuilder.append(" AND ((t11.no_pct = '')    OR (t11.no_pct IS NULL)) ");
			strBuilder.append(" AND t22.no_rpi <= (SELECT max(no_rpi)-1 AS rpi FROM PTNBASE@JERRY_SOC:crp_rpi_sistema) ");
			strBuilder.append(" AND t22.cd_tipo_despach IN (484, 813, 824, 859, 492, 825, 495, 827, 497, 670, 884, 1016, 674, 882)) > 0 THEN 'F' ");
			strBuilder.append("	WHEN  (SELECT COUNT(t44.cd_pedido) ");
			strBuilder.append("	FROM  PTNBASE@JERRY_SOC:ptn_pedido t44,  PTNBASE@JERRY_SOC:ptn_pct t55 ");
			strBuilder.append(" WHERE t44.no_pct            = t55.no_pct ");
			strBuilder.append(" AND t44.cd_pedido         = t1.cd_pedido ");
		    strBuilder.append(" AND t44.cd_defesa_naciona = 'N' ");
	        strBuilder.append(" AND t44.no_pedido[1,2]    NOT IN ('DI', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39') ");
	        strBuilder.append(" AND t55.cd_ompi           IS NOT NULL ");
	        strBuilder.append(" AND LENGTH(t55.cd_ompi)   > 1  ) > 0 THEN 'F' ");
	        strBuilder.append(" WHEN (SELECT COUNT(t123.cd_pedido) ");
	       	strBuilder.append(" FROM  PTNBASE@JERRY_SOC:ptn_pedido t123 ");
	       	strBuilder.append(" WHERE t123.cd_pedido         = t1.cd_pedido ");
	        strBuilder.append(" AND t123.cd_defesa_naciona = 'S') > 0 THEN 'T' ");
	        strBuilder.append(" WHEN (SELECT COUNT(t222.cd_pedido) ");
	        strBuilder.append(" FROM  PTNBASE@JERRY_SOC:ptn_pedido t111,  PTNBASE@JERRY_SOC:ptn_despacho t222 ");
	        strBuilder.append(" WHERE t111.cd_pedido        = t222.cd_pedido ");
	        strBuilder.append(" AND t111.cd_pedido        = t1.cd_pedido ");
	        strBuilder.append(" AND t111.no_pedido[1,2]   NOT IN ('DI', '30', '31', '32', '33', ");
	        strBuilder.append(" '34', '35', '36', '37', '38', '39') "); 
	        strBuilder.append(" AND DATE(t111.dt_deposit) > DATE('1997-05-31') ");
	        strBuilder.append(" AND ((t111.no_pct = '')   OR (t111.no_pct IS NULL)) ");
	       	strBuilder.append(" AND t111.cd_pedido NOT IN(SELECT tn.cd_pedido ");
	      	strBuilder.append(" FROM  PTNBASE@JERRY_SOC:ptn_despacho tn ");
	        strBuilder.append(" WHERE tn.cd_tipo_despach in(484,813,824,859,492,825,495,827,497,670,884,1016,674,882) ");
	        strBuilder.append(" AND t111.cd_pedido     = tn.cd_pedido ");
	        strBuilder.append(" AND tn.no_rpi  <= (SELECT MAX(no_rpi)-1 AS rpi FROM  PTNBASE@JERRY_SOC:crp_rpi_sistema) ) ) > 0 THEN 'T' ");
	      	strBuilder.append(" WHEN (SELECT COUNT(t77.cd_pedido) ");
	      	strBuilder.append(" FROM  PTNBASE@JERRY_SOC:ptn_pedido t77,  PTNBASE@JERRY_SOC:ptn_despacho t88 ");
	      	strBuilder.append(" WHERE DATE(t77.dt_deposit) <= DATE('1997-05-31') ");
	      	strBuilder.append(" AND t77.cd_pedido        = t1.cd_pedido ");
	      	strBuilder.append(" AND t77.no_pedido[1,2]   NOT IN ('DI', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39') ");
	        strBuilder.append(" AND t77.cd_pedido        = t88.cd_pedido ");
	       	strBuilder.append(" AND t88.cd_tipo_despach  IN (787, 843, 584, 630, 591, 856) ");
	       	strBuilder.append("  AND t1.cd_pedido         NOT IN(SELECT tn.cd_pedido ");
	        strBuilder.append(" FROM  PTNBASE@JERRY_SOC:ptn_despacho tn ");
	        strBuilder.append(" WHERE tn.cd_tipo_despach IN(484,813,824,859,492,825,495,827,497,670,884,1016,674,882) ");
	        strBuilder.append(" AND t77.cd_pedido      = tn.cd_pedido ");
	        strBuilder.append(" AND tn.no_rpi          <= (SELECT max(no_rpi)-1 AS rpi FROM  PTNBASE@JERRY_SOC:crp_rpi_sistema) )) > 0 THEN 'T' ");
	        strBuilder.append(" WHEN (SELECT COUNT(W1.cd_pedido) ");
	        strBuilder.append(" FROM  PTNBASE@JERRY_SOC:ptn_pedido w1 ");
	        strBuilder.append(" WHERE w1.cd_pedido        =  t1.cd_pedido ");
	        strBuilder.append(" AND w1.no_pedido[1,2]   NOT IN ('DI', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39') ");
	        strBuilder.append(" AND DATE(w1.dt_deposit) > DATE('1997-05-31') ");
	        strBuilder.append(" AND ((w1.no_pct = '')   OR (w1.no_pct IS NULL)) ");
	        strBuilder.append(" AND w1.cd_pedido        NOT IN (SELECT tn.cd_pedido FROM  PTNBASE@JERRY_SOC:ptn_despacho tn ");
	        strBuilder.append(" WHERE w1.cd_pedido = tn.cd_pedido)) > 0 THEN 'T' ");
	        strBuilder.append(" WHEN (SELECT COUNT(DISTINCT PAG.numnossonumero) ");
	        strBuilder.append(" FROM  PTNBASE@JERRY_SOC:pag_view_inpi PAG,  PTNBASE@JERRY_SOC:vw_protocodigita PRO,");
	        strBuilder.append(" PTNBASE@JERRY_SOC:vw_protocodigita subPRO ");
	        strBuilder.append("  WHERE PAG.numnossonumero    = PRO.numnossonumero ");
	        strBuilder.append("               AND PRO.no_protoco        = subPRO.no_protoco  ");
	        strBuilder.append("               AND PAG.coddiretoria      = 100  ");
	      	strBuilder.append("                 AND PAG.codservico        IN (100, 102)  ");
	        strBuilder.append("           AND (  ");
	       	strBuilder.append("                           (PAG.dat_gravacao  IS NOT NULL)  ");
	        strBuilder.append("                       OR  ");
	        strBuilder.append("                        ((PAG.dat_gravacao IS NULL) AND (PAG.datconciliacao IS NOT NULL))  ");
	     	strBuilder.append("                   )  ");
		    strBuilder.append("              AND (DATE(CURRENT) - DATE(PRO.dh_entrada_protoco)) <= 180  ");
	   		strBuilder.append("                    AND UPPER(SUBSTR(REPLACE(PAG.numprocesso, ' ', ''), 1, 12)) = T1.no_pedido) >= 2 THEN 'T'  ");
	    	strBuilder.append("       WHEN (SELECT COUNT(P.cd_pedido)  ");
		    strBuilder.append("                    FROM  PTNBASE@JERRY_SOC:ptn_pedido P  ");
			strBuilder.append(" WHERE P.cd_pedido = t1.cd_pedido  ");
			strBuilder.append(" AND P.no_pedido[1,2] IN ('DI', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39')  ");
			strBuilder.append(" AND P.cd_situaca_pedido = 'SI') > 0 THEN 'T'  ");
			strBuilder.append("     ELSE 'F'  ");
			strBuilder.append("	     END AS SIGILO  ");
			strBuilder.append("		FROM PTNBASE@JERRY_SOC:PTN_PEDIDO T1  ");
			strBuilder.append("	WHERE T1.no_pedido = '");
			strBuilder.append(numeroPedido);
			strBuilder.append("'");
	    	
			Session session = this.entityManager.unwrap(Session.class);
			SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
			ConnectionProvider cp = sfi.getConnectionProvider();
			Connection conn = cp.getConnection();
			
			PreparedStatement pstm = conn.prepareStatement(strBuilder.toString());
			ResultSet res = pstm.executeQuery();
			if(res.next()){
				
				String statusSigilo = res.getString("sigilo");
				if("T".equals(statusSigilo)){
					isPedidoSigilo = Boolean.TRUE;
				}
				
			}
			
			res.close();
			pstm.close();
			conn.close();
	    
	    }catch(Exception nre){
    		LOGGER.info(" Pedido não encontrado. ");
    	}
		
		return isPedidoSigilo;
	
	}


	public void salvarPedidosEnviados(List<PtnProcessoProsur> processosProsur) {
		
		try{
			for(int i = 0; i < processosProsur.size(); i++){
					
					PtnProcessoProsur prosur = processosProsur.get(i);
					this.entityManager.persist(prosur);
				    this.entityManager.flush();
			}
			
		}catch(Exception nre){
    		LOGGER.info(" Erro ao gravar o pedido." + nre.getMessage());
    	}
		
	}

	
	

}
