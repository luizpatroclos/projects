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
//import br.gov.inpi.patentes.beans.Patente;
//import br.gov.inpi.patentes.beans.PtnPedido;
//import br.gov.inpi.patentes.beans.PtnProcessoProsur;
//import br.gov.inpi.prosur.carga.util.PatenteHelperAutomatico;
//import br.gov.inpi.prosur.carga.util.ProsurUtils;
//import br.gov.inpi.services.CargaPatentesInterface;
//import br.gov.inpi.services.EntityInterfacePatentes;
//import br.gov.inpi.services.ServicosInterface;
//
///**
// *
// * @author luizAlbuquerque
// */
//@Stateless(name = "CargaPatentesFacade")
//public class CargaPatentesFacade implements CargaPatentesInterface {
//
//	private static final Logger LOGGER = Logger.getLogger(CargaPatentesFacade.class);
//
//	public ProsurUtils utils;
//
//	@EJB
//	private EntityInterfacePatentes persistPatentes;
//
//	@EJB
//	private ServicosInterface servicosProsur;
//
//	public CargaPatentesFacade() {
//
//	}
//
//	private List<String> criarMockDeNumerosProcessos() {
//
//		List<String> processos = new ArrayList<String>();
//
//		// processos.add("PI0113929");
//
//		processos.add("PI9610172");
//
//		processos.add("PI0216112");
//		processos.add("PI0216128");
//		processos.add("122012016624");
//		processos.add("122012020772");
//		processos.add("PI8807234");
//		processos.add("MU7702238");
//		processos.add("PI8703081");
//		processos.add("MU7702238");
//		processos.add("102013000118");
//		processos.add("PI0017507");
//
//		return processos;
//
//		/*
//		 * --- Desenho industrial - DI DI6103667-6 BR 30 2012 000162-5 DI6802553 DI5500698 DI6301079 DI6703784 DI6500833
//		 * DI6200422 DI7101962
//		 * 
//		 * 
//		 * --- Patente PI9702959 PI8807234 MU6702117 PI8703081 MU7702238 MU 7702238 BR 10 2013 000118 0 PI0017507
//		 * 
//		 * -- Marcas 906881170 906997712 905873602 907457347 907578772 902999524 820436089 907360785 905039971
//		 */
//
//	}
//
//	/**
//	 * 
//	 * M�todo respons�vel em enviar os processos de Patentes para o Prosur.
//	 * 
//	 */
//	public List<PtnPedido> buscarPedidosPatentes(String tipoEnvio) {
//
//		List<PtnPedido> pedidos = new ArrayList<PtnPedido>();
//
//		tipoEnvio = "teste";
//
//		if ("cargaRetroativa".equals(tipoEnvio)) {
//
//			pedidos = persistPatentes.pesquisarPedidosDePatenteRetroativa();
//
//		} else if ("cargaSemanal".equals(tipoEnvio)) {
//
//			pedidos = persistPatentes.pesquisarPedidosDePatentePorSemana();
//
//		} else if ("teste".equals(tipoEnvio)) {
//
//			Iterator<String> it = this.criarMockDeNumerosProcessos().iterator();
//			while (it.hasNext()) {
//
//				String numeroPedido = it.next();
//				LOGGER.info("numero " + numeroPedido);
//				List<PtnPedido> pedido = this.persistPatentes.pesquisarPedidosDePatentePorNumeroPedido(numeroPedido);
//				pedidos.add(pedido.get(0));
//			}
//
//		}
//
//		return pedidos;
//	}
//
//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//	public String executarPedidoPatente(PtnPedido pedido) throws Exception {
//
//		String idProsur = null;
//
//		try {
//
//			PatenteHelperAutomatico helper = new PatenteHelperAutomatico();
//			helper.setPersistPatentes(this.persistPatentes);
//			Patente patente = helper.retornarPedidoPatente(pedido);
//
//			if (patente.getCodigoPedido() != null && !"".equals(patente.getCodigoPedido())) {
//
//				idProsur = this.enviarProsur(patente);
//			}
//
//		} catch (Exception e) {
//			LOGGER.info(" Erro ao executar o pedido de Patente " + e.getMessage());
//			throw e;
//		}
//
//		return idProsur;
//	}
//
//	private String enviarProsur(Patente patente) throws Exception {
//
//		String idProsur = null;
//
//		try {
//
//			IpRecord iprecordRetorno = servicosProsur.createProsur(patente.getInventionPatent());
//
//			if (iprecordRetorno != null) {
//
//				PtnProcessoProsur prosur = new PtnProcessoProsur();
//				prosur.setIdProsur(iprecordRetorno.getIpRecordId());
//				prosur.setDataEnvio(new Date());
//				prosur.setCodigoPedidoInpi(patente.getCodigoPedido());
//				idProsur = iprecordRetorno.getIpRecordId().toString();
//				gravarLog(prosur);
//			}
//
//		} catch (Throwable e) {
//			LOGGER.error(" >> ERRO :: enviar pedido de Patente para o servi�o Prosur : " + e.getMessage());
//			throw e;
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
//}
