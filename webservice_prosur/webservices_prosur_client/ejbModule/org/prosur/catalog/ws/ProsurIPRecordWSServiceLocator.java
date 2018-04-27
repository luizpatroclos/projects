/**
 * ProsurIPRecordWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.prosur.catalog.ws;

import java.rmi.Remote;

public class ProsurIPRecordWSServiceLocator extends org.apache.axis.client.Service implements org.prosur.catalog.ws.ProsurIPRecordWSService {

    public ProsurIPRecordWSServiceLocator() {
    }


    public ProsurIPRecordWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ProsurIPRecordWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ProsurIPRecordWSPort
    // BR private java.lang.String ProsurIPRecordWSPort_address = "https://172.20.4.5:8443/ProsurCatalog/ProsurIPRecordWS";

    // AR private java.lang.String ProsurIPRecordWSPort_address = "https://10.5.2.11:8443/ProsurCatalog/ProsurIPRecordWS";
    
    private java.lang.String ProsurIPRecordWSPort_address = "https://172.20.4.33:8443/ProsurCatalog/ProsurIPRecordWS";
    
    //private java.lang.String ProsurIPRecordWSPort_address = "https://10.5.2.11:8443/ProsurCatalog/ProsurIPRecordWS";
    
    public java.lang.String getProsurIPRecordWSPortAddress() {
        return ProsurIPRecordWSPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ProsurIPRecordWSPortWSDDServiceName = "ProsurIPRecordWSPort";

    public java.lang.String getProsurIPRecordWSPortWSDDServiceName() {
        return ProsurIPRecordWSPortWSDDServiceName;
    }

    public void setProsurIPRecordWSPortWSDDServiceName(java.lang.String name) {
        ProsurIPRecordWSPortWSDDServiceName = name;
    }

    public org.prosur.catalog.ws.ProsurIPRecordWS getProsurIPRecordWSPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ProsurIPRecordWSPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getProsurIPRecordWSPort(endpoint);
    }

    public org.prosur.catalog.ws.ProsurIPRecordWS getProsurIPRecordWSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.prosur.catalog.ws.ProsurIPRecordWSServiceSoapBindingStub _stub = new org.prosur.catalog.ws.ProsurIPRecordWSServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getProsurIPRecordWSPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setProsurIPRecordWSPortEndpointAddress(java.lang.String address) {
        ProsurIPRecordWSPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.prosur.catalog.ws.ProsurIPRecordWS.class.isAssignableFrom(serviceEndpointInterface)) {
                org.prosur.catalog.ws.ProsurIPRecordWSServiceSoapBindingStub _stub = new org.prosur.catalog.ws.ProsurIPRecordWSServiceSoapBindingStub(new java.net.URL(ProsurIPRecordWSPort_address), this);
                _stub.setPortName(getProsurIPRecordWSPortWSDDServiceName());
                return (Remote) _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ProsurIPRecordWSPort".equals(inputPortName)) {
            return (Remote) getProsurIPRecordWSPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.catalog.prosur.org/", "ProsurIPRecordWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.catalog.prosur.org/", "ProsurIPRecordWSPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ProsurIPRecordWSPort".equals(portName)) {
            setProsurIPRecordWSPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
