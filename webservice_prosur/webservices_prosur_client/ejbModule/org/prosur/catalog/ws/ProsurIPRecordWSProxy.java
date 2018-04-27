package org.prosur.catalog.ws;

import javax.ejb.Stateless;


@Stateless(name = "ProsurIPRecordWSProxy")
public class ProsurIPRecordWSProxy implements org.prosur.catalog.ws.ProsurIPRecordWS {
  private String _endpoint = null;
  private org.prosur.catalog.ws.ProsurIPRecordWS prosurIPRecordWS = null;
  
  public ProsurIPRecordWSProxy() {
    _initProsurIPRecordWSProxy();
  }
  
  public ProsurIPRecordWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initProsurIPRecordWSProxy();
  }
  
  private void _initProsurIPRecordWSProxy() {
    try {
      prosurIPRecordWS = (new org.prosur.catalog.ws.ProsurIPRecordWSServiceLocator()).getProsurIPRecordWSPort();
      if (prosurIPRecordWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)prosurIPRecordWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)prosurIPRecordWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (prosurIPRecordWS != null)
      ((javax.xml.rpc.Stub)prosurIPRecordWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.prosur.catalog.ws.ProsurIPRecordWS getProsurIPRecordWS() {
    if (prosurIPRecordWS == null)
      _initProsurIPRecordWSProxy();
    return prosurIPRecordWS;
  }
  
  public void removeRecord(org.prosur.catalog.ws.IpRecord ipRecord, java.lang.String user) throws java.rmi.RemoteException{
    if (prosurIPRecordWS == null)
      _initProsurIPRecordWSProxy();
    prosurIPRecordWS.removeRecord(ipRecord, user);
  }
  
  public org.prosur.catalog.ws.IpRecord getRecord(org.prosur.catalog.ws.IpRecord ipRecord, java.lang.String user) throws java.rmi.RemoteException{
    if (prosurIPRecordWS == null)
      _initProsurIPRecordWSProxy();
    return prosurIPRecordWS.getRecord(ipRecord, user);
  }
  
  public org.prosur.catalog.ws.IpRecord createRecord(org.prosur.catalog.ws.IpRecord ipRecord, java.lang.String user) throws java.rmi.RemoteException{
    if (prosurIPRecordWS == null)
      _initProsurIPRecordWSProxy();
    return prosurIPRecordWS.createRecord(ipRecord, user);
  }
  
  
}