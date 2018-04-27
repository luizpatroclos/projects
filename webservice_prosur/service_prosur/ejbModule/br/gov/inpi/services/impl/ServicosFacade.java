package br.gov.inpi.services.impl;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;
import org.prosur.catalog.ws.IpRecord;
import org.prosur.catalog.ws.ProsurIPRecordWS;
import org.prosur.catalog.ws.ProsurIPRecordWSProxy;

import br.gov.inpi.services.ServicosInterface;

/**
 *
 * @author luizAlbuquerque
 */
@Stateless(name = "ServicosFacade")
public class ServicosFacade implements ServicosInterface {

	private static final Logger LOGGER = Logger.getLogger(ServicosFacade.class);

	// ProsurIPRecordWS send = null;
	@EJB
	private ProsurIPRecordWS send;

	public ServicosFacade() {
		// send = new ProsurIPRecordWSProxy();
		// autenticUser();

	}

	@SuppressWarnings("unused")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void removeProsur(List<IpRecord> iprecord, IpRecord iprecordUnit) {

		ProsurIPRecordWS send = new ProsurIPRecordWSProxy();

		if (!"".equalsIgnoreCase(iprecordUnit.toString()) || iprecordUnit != null) {

			try {
				send.removeRecord(iprecordUnit, "inpi.gov.br");
			} catch (RemoteException e) {
				LOGGER.info("Erro no envio de Remove ! - ID = " + iprecordUnit.getIpRecordId());
			}
			LOGGER.info(" IpRecor removido com Sucesso ! - ID = " + iprecordUnit.getIpRecordId());

		} else {

			for (IpRecord iprecordEnvio : iprecord) {

				try {
					send.removeRecord(iprecordEnvio, "inpi.gov.br");
				} catch (RemoteException e) {
					LOGGER.info("Erro no envio de Remove ! - ID = " + iprecordEnvio.getIpRecordId());
				}
				LOGGER.info(" IpRecor removido com Sucesso ! - ID = " + iprecordEnvio.getIpRecordId());

			}

		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public IpRecord createProsur(IpRecord iprecordUnit) throws RemoteException {

		IpRecord retorno = null;
		try {
			retorno = send.createRecord(iprecordUnit, "inpi.gov.br");
		} catch (RemoteException e) {
			LOGGER.info("Erro no envio do create ! - Processo = " + iprecordUnit.getApplicationId());
			throw e;
		}
		return retorno;

	}

	private void autenticUser() {

		/*
		 * URL realData = getClass().getClassLoader().getResource("clienteBR.jks"); String path = realData.getPath();
		 * 
		 * System.setProperty("javax.net.ssl.keyStoreType", "JKS"); System.setProperty("javax.net.ssl.keyStore", path);
		 * System.setProperty("javax.net.ssl.keyStorePassword", "Be7@5U");
		 */

	}

}
