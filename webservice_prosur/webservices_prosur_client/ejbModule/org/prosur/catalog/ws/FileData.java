/**
 * FileData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.prosur.catalog.ws;

public class FileData  implements java.io.Serializable {
    private java.lang.String fileDescription;

    private java.lang.String fileName;

    private java.lang.String fileTitle;

    public FileData() {
    }

    public FileData(
           java.lang.String fileDescription,
           java.lang.String fileName,
           java.lang.String fileTitle) {
           this.fileDescription = fileDescription;
           this.fileName = fileName;
           this.fileTitle = fileTitle;
    }


    /**
     * Gets the fileDescription value for this FileData.
     * 
     * @return fileDescription
     */
    public java.lang.String getFileDescription() {
        return fileDescription;
    }


    /**
     * Sets the fileDescription value for this FileData.
     * 
     * @param fileDescription
     */
    public void setFileDescription(java.lang.String fileDescription) {
        this.fileDescription = fileDescription;
    }


    /**
     * Gets the fileName value for this FileData.
     * 
     * @return fileName
     */
    public java.lang.String getFileName() {
        return fileName;
    }


    /**
     * Sets the fileName value for this FileData.
     * 
     * @param fileName
     */
    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }


    /**
     * Gets the fileTitle value for this FileData.
     * 
     * @return fileTitle
     */
    public java.lang.String getFileTitle() {
        return fileTitle;
    }


    /**
     * Sets the fileTitle value for this FileData.
     * 
     * @param fileTitle
     */
    public void setFileTitle(java.lang.String fileTitle) {
        this.fileTitle = fileTitle;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FileData)) return false;
        FileData other = (FileData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fileDescription==null && other.getFileDescription()==null) || 
             (this.fileDescription!=null &&
              this.fileDescription.equals(other.getFileDescription()))) &&
            ((this.fileName==null && other.getFileName()==null) || 
             (this.fileName!=null &&
              this.fileName.equals(other.getFileName()))) &&
            ((this.fileTitle==null && other.getFileTitle()==null) || 
             (this.fileTitle!=null &&
              this.fileTitle.equals(other.getFileTitle())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getFileDescription() != null) {
            _hashCode += getFileDescription().hashCode();
        }
        if (getFileName() != null) {
            _hashCode += getFileName().hashCode();
        }
        if (getFileTitle() != null) {
            _hashCode += getFileTitle().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FileData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.catalog.prosur.org/", "fileData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileTitle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileTitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
