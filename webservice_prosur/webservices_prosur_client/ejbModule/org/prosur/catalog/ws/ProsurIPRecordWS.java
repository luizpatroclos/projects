/**
 * ProsurIPRecordWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.prosur.catalog.ws;

import javax.ejb.Local;

@Local
public interface ProsurIPRecordWS {
    public void removeRecord(org.prosur.catalog.ws.IpRecord ipRecord, java.lang.String user) throws java.rmi.RemoteException;
    public org.prosur.catalog.ws.IpRecord getRecord(org.prosur.catalog.ws.IpRecord ipRecord, java.lang.String user) throws java.rmi.RemoteException;
    public org.prosur.catalog.ws.IpRecord createRecord(org.prosur.catalog.ws.IpRecord ipRecord, java.lang.String user) throws java.rmi.RemoteException;
}
