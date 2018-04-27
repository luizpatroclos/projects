package br.gov.inpi.services;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Local;

import org.prosur.catalog.ws.IpRecord;

/**
 *
 * @author luizAlbuquerque
 */
@Local
public interface ServicosInterface {

	public void removeProsur(List<IpRecord> iprecord, IpRecord iprecordUnit);

	public IpRecord createProsur(IpRecord iprecordUnit) throws RemoteException;

}
