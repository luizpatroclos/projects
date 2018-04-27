//package br.gov.inpi.services.impl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//
//import org.jboss.logging.Logger;
//import org.prosur.catalog.ws.IpRecord;
//
//import br.gov.inpi.patentes.beans.Desenho;
//import br.gov.inpi.patentes.beans.DiPedido;
//import br.gov.inpi.patentes.beans.PtnProcessoProsur;
//import br.gov.inpi.prosur.carga.util.PatenteHelperAutomatico;
//import br.gov.inpi.services.CargaDesenhosInterface;
//import br.gov.inpi.services.EntityInterfacePatentes;
//import br.gov.inpi.services.ServicosInterface;
//
///**
// *
// * @author luizAlbuquerque
// */
//@Stateless(name = "CargaDesenhosFacade")
//public class CargaDesenhosFacade implements CargaDesenhosInterface {
//
//	private static final Logger LOGGER = Logger.getLogger(CargaDesenhosFacade.class);
//
//	@EJB
//	private EntityInterfacePatentes persistPatentes;
//
//	@EJB
//	private ServicosInterface servicosProsur;
//
//	public CargaDesenhosFacade() {
//
//	}
//
//	/**
//	 * 
//	 * M�todo respons�vel em enviar os processos de desenho(DI) para o Prosur.
//	 * 
//	 * @throws Throwable
//	 * 
//	 */
//	/*
//	 * @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//	 * 
//	 * @TransactionTimeout(value=1 , unit= TimeUnit.HOURS) public void enviarPedidosDesenhos(String tipoEnvio) {
//	 * 
//	 * List<DiPedido> diPedidos = new ArrayList<DiPedido>();
//	 * 
//	 * if("cargaSemanal".equals(tipoEnvio)){
//	 * 
//	 * diPedidos = persistPatentes.pesquisarPedidosDiPorSemana();
//	 * 
//	 * }else if("cargaRetroativa".equals(tipoEnvio)){
//	 * 
//	 * diPedidos = persistPatentes.pesquisarPedidosDiRetroativa();
//	 * 
//	 * }else if("teste".equals(tipoEnvio)){
//	 * 
//	 * Iterator<String> it = this.criarMockDeNumerosProcessos().iterator(); while(it.hasNext()){
//	 * 
//	 * String numeroPedido = it.next(); LOGGER.info("numero " + numeroPedido); DiPedido diPedido =
//	 * this.persistPatentes.pesquisarPorNumeroDePedidoDesenhoIndustrial(numeroPedido); diPedidos.add(diPedido); }
//	 * 
//	 * }
//	 * 
//	 * this.prepararPedidos(diPedidos); LOGGER.info(" Fim da carga de Desenho Industrial.............. ");
//	 * 
//	 * }
//	 */
//
//	private String enviarProsur(Desenho desenho) throws Exception {
//
//		String idProsur = null;
//
//		try {
//
//			IpRecord iprecordRetorno = servicosProsur.createProsur(desenho.getDesignPatent());
//
//			if (iprecordRetorno != null) {
//
//				PtnProcessoProsur prosur = new PtnProcessoProsur();
//				prosur.setIdProsur(iprecordRetorno.getIpRecordId());
//				prosur.setDataEnvio(new Date());
//				prosur.setCodigoPedidoInpi(desenho.getCodigoIdentificador());
//				idProsur = iprecordRetorno.getIpRecordId().toString();
//				gravarLog(prosur);
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(" >> ERRO :: enviar pedido de Patente para o servi�o Prosur : " + e.getMessage());
//			throw e;
//
//		}
//		return idProsur;
//
//	}
//
//	private void gravarLog(PtnProcessoProsur prosur) {
//
//		try {
//			persistPatentes.save(prosur);
//
//		} catch (Exception e) {
//			LOGGER.error(" Erro ao gravar o log " + e.getMessage());
//		}
//
//	}
//
//	private List<String> criarMockDeNumerosProcessos() {
//
//		List<String> processos = new ArrayList<String>();
//
//		processos.add("DI7100688");
//		processos.add("DI7100746");
//		processos.add("DI7101740");
//		processos.add("DI7101033");
//		processos.add("DI7101042");
//		processos.add("DI7100930");
//		processos.add("DI7100979");
//		processos.add("DI7101206");
//		processos.add("DI7100679");
//		processos.add("DI7100934");
//
//		return processos;
//
//	}
//
//	public List<DiPedido> buscarPedidosDesenho(String tipoEnvio) {
//
//		List<DiPedido> diPedidos = new ArrayList<DiPedido>();
//
//		// tipoEnvio = "teste";
//
//		LOGGER.info(" Inicio da carga de Desenho Industrial...........");
//
//		if ("cargaSemanal".equals(tipoEnvio)) {
//
//			diPedidos = persistPatentes.pesquisarPedidosDiPorSemana();
//
//		} else if ("cargaRetroativa".equals(tipoEnvio)) {
//
//			diPedidos = persistPatentes.pesquisarPedidosDiRetroativa();
//
//		} else if ("teste".equals(tipoEnvio)) {
//
//			Iterator<String> it = this.criarMockDeNumerosProcessos().iterator();
//			while (it.hasNext()) {
//
//				String numeroPedido = it.next();
//				LOGGER.info("numero " + numeroPedido);
//				DiPedido diPedido = this.persistPatentes.pesquisarPorNumeroDePedidoDesenhoIndustrial(numeroPedido);
//				diPedidos.add(diPedido);
//			}
//		}
//
//		return diPedidos;
//	}
//
//	/***
//	 * 
//	 * M�todo respons�vel em converter os pedidos de Desenho Industrial para o padr�o utilizado pelo Prosur.
//	 * 
//	 * @param diPedidos
//	 * @throws Exception
//	 */
//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//	public String executarPedidoDesenho(DiPedido diPedido) throws Exception {
//
//		String idProsur = null;
//
//		try {
//
//			PatenteHelperAutomatico helper = new PatenteHelperAutomatico();
//			helper.setPersistPatentes(this.persistPatentes);
//			Desenho desenho = helper.retornarDesenho(diPedido);
//
//			if (desenho != null && !"".equals(desenho)) {
//
//				idProsur = this.enviarProsur(desenho);
//			}
//
//		} catch (Exception e) {
//			LOGGER.info(" Nenhum pedido de Desenho Industrial encontrado " + e.getMessage());
//			throw e;
//
//		}
//
//		return idProsur;
//
//	}
//
//	/*
//	 * private void prepararPedidos(List<DiPedido> diPedidos){
//	 * 
//	 * if(diPedidos != null && !diPedidos.isEmpty()){
//	 * 
//	 * PatenteHelperAutomatico helper = new PatenteHelperAutomatico(); helper.setPersistPatentes(this.persistPatentes);
//	 * 
//	 * List<Desenho> desenhos = helper.retornarDesenho(diPedidos); if(desenhos != null && !desenhos.isEmpty()){
//	 * 
//	 * this.enviarProsur(desenhos); }
//	 * 
//	 * }else{ LOGGER.info(" Nenhum pedido de Desenho Industrial encontrado. "); }
//	 * 
//	 * }
//	 */
//
//}
