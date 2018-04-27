
package org.prosur.office.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.prosur.office.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetIPRecordFileResponse_QNAME = new QName("http://ws.office.prosur.org/", "getIPRecordFileResponse");
    private final static QName _GetIPRecordFile_QNAME = new QName("http://ws.office.prosur.org/", "getIPRecordFile");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.prosur.office.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetIPRecordFile }
     * 
     */
    public GetIPRecordFile createGetIPRecordFile() {
        return new GetIPRecordFile();
    }

    /**
     * Create an instance of {@link GetIPRecordFileResponse }
     * 
     */
    public GetIPRecordFileResponse createGetIPRecordFileResponse() {
        return new GetIPRecordFileResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIPRecordFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.office.prosur.org/", name = "getIPRecordFileResponse")
    public JAXBElement<GetIPRecordFileResponse> createGetIPRecordFileResponse(GetIPRecordFileResponse value) {
        return new JAXBElement<GetIPRecordFileResponse>(_GetIPRecordFileResponse_QNAME, GetIPRecordFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIPRecordFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.office.prosur.org/", name = "getIPRecordFile")
    public JAXBElement<GetIPRecordFile> createGetIPRecordFile(GetIPRecordFile value) {
        return new JAXBElement<GetIPRecordFile>(_GetIPRecordFile_QNAME, GetIPRecordFile.class, null, value);
    }

}
