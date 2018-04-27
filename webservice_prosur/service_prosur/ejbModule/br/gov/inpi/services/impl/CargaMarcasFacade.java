//package br.gov.inpi.services.impl;
//
//import java.util.ArrayList;
//import java.util.Date;
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
//import br.gov.inpi.mail.Estatistica;
//import br.gov.inpi.marcas.beans.Marca;
//import br.gov.inpi.marcas.beans.MrcProsur;
//import br.gov.inpi.prosur.carga.util.MarcasHelperAutoManual;
//import br.gov.inpi.services.CargaMarcasInterface;
//import br.gov.inpi.services.EntityInterfaceMarcas;
//import br.gov.inpi.services.ServicosInterface;
//
///**
// *
// * @author luizAlbuquerque
// */
//@Stateless(name = "CargaMarcasFacade")
//public class CargaMarcasFacade implements CargaMarcasInterface {
//
//	private static final Logger LOGGER = Logger.getLogger(CargaMarcasFacade.class);
//
//	@EJB
//	private EntityInterfaceMarcas persistMarcas;
//
//	@EJB
//	private ServicosInterface servicosProsur;
//
//	public CargaMarcasFacade() {
//
//	}
//
//	public EntityInterfaceMarcas getPersistMarcas() {
//		return persistMarcas;
//	}
//
//	public void setPersistMarcas(EntityInterfaceMarcas persistMarcas) {
//		this.persistMarcas = persistMarcas;
//	}
//
//	/**
//	 * 
//	 * M�todo respons�vel em enviar os processos de marcas para o Prosur.
//	 * 
//	 */
//	public List<Object[]> buscarPedidosMarcas(String tipoEnvio) {
//
//		List<Object[]> objectProcessosMarcas = new ArrayList<>();
//
////		tipoEnvio = "teste";
//
//		if ("cargaRetroativa".equals(tipoEnvio)) {
//
//			objectProcessosMarcas = persistMarcas.listarPedidosMarcasRetroativo();
//
//		} else if ("cargaSemanal".equals(tipoEnvio)) {
//
//			objectProcessosMarcas = persistMarcas.listarPedidosMarcasRetroativoPorRPI();
//
//		} else if ("teste".equals(tipoEnvio)) {
//
//			String processo = this.criarMockPedidoMarcas();
//			objectProcessosMarcas = this.persistMarcas.listarPedidosMarcasRetroativoPorProcesso(processo);
//		}
//
//		Estatistica.addString("numero_rpi", persistMarcas.recuperarNumeroRPICorrente());
//
//		return objectProcessosMarcas;
//	}
//
//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//	public String executarPedidoMarca(Object[] marca) throws Exception {
//
//		String idProsur = null;
//
//		try {
//
//			Marca pedidoMarca = new Marca();
//			MarcasHelperAutoManual helper = new MarcasHelperAutoManual();
//			pedidoMarca = helper.retornarPedidosMarcas(marca, persistMarcas);
//
//			if (pedidoMarca != null & !"".equals(pedidoMarca)) {
//
//				idProsur = this.enviarProsur(pedidoMarca);
//			}
//
//		} catch (Exception e) {
//			LOGGER.info(" Erro ao executar o pedido de Marcas " + e.getMessage());
//			throw e;
//
//		}
//
//		return idProsur;
//
//	}
//
//	private String enviarProsur(Marca pedidoMarca) throws Exception {
//
//		String idProsur = null;
//
//		try {
//
//			IpRecord iprecordRetorno = servicosProsur.createProsur(pedidoMarca.getDistinctiveSign());
//
//			if (iprecordRetorno != null) {
//
//				MrcProsur prosur = new MrcProsur();
//				prosur.setDataEnvio(new Date());
//				prosur.setIdProsur(iprecordRetorno.getIpRecordId());
//				prosur.setCodigoPedidoMarcaInpi(pedidoMarca.getCodigoProcesso());
//				idProsur = iprecordRetorno.getIpRecordId().toString();
//				gravarLog(prosur);
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(" >> ERRO :: enviar pedido de Patente para o servi�o Prosur : " + e.getMessage());
//			throw e;
//		}
//
//		return idProsur;
//	}
//
//	private void gravarLog(MrcProsur prosur) {
//
//		try {
//			persistMarcas.save(prosur);
//
//		} catch (Exception e) {
//			LOGGER.error(" Erro ao gravar o log " + e.getMessage());
//		}
//
//	}
//
//	private String criarMockPedidoMarcas() {
//
//		StringBuilder strBuilder = new StringBuilder();
//
//		strBuilder.append("(");
//		strBuilder.append("'902988816'");
//		strBuilder.append("'904583589'");
//		strBuilder.append("'904569764',");
//		strBuilder.append("'904570304',");
//		strBuilder.append("'904570339',");
//		strBuilder.append("'840040865',");
//		strBuilder.append("'904579115',");
//		strBuilder.append("'904576248',");
//		strBuilder.append("'904594033',");
//		strBuilder.append("'904578232'");
//		strBuilder.append(")");
//		return strBuilder.toString();
//
//	}
//
//}
