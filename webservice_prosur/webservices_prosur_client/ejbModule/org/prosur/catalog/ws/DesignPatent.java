/**
 * DesignPatent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.prosur.catalog.ws;

import java.util.Arrays;

public class DesignPatent  extends org.prosur.catalog.ws.Patent  implements java.io.Serializable {
    private java.lang.String designClassification;

    private java.lang.String[] locarnoClassification;

    private java.lang.String mainDesignImage;

    public DesignPatent() {
    }

    public DesignPatent(
           java.lang.String[] applicantName,
           java.lang.String applicationId,
           org.prosur.catalog.ws.FileData[] files,
           java.lang.String ipRecordDetailLink,
           java.lang.String ipRecordFilesService,
           java.lang.Long ipRecordId,
           java.util.Date nationalPresentationDate,
           java.util.Date nationalPublishingDate,
           java.lang.String onapiId,
           org.prosur.catalog.ws.IpRecordTypeEnum recordType,
           java.lang.String statusId,
           java.lang.String title,
           java.lang.String conclusionMethod,
           java.lang.String description,
           java.lang.String[] inventors,
           java.lang.String patentAbstract,
           org.prosur.catalog.ws.Priority[] priorities,
           java.lang.String registrationNumber,
           java.lang.String representativeName,
           java.lang.String requestCountryId,
           java.lang.String technicalReportDetail,
           java.lang.String designClassification,
           java.lang.String[] locarnoClassification,
           java.lang.String mainDesignImage) {
        super(
            applicantName,
            applicationId,
            files,
            ipRecordDetailLink,
            ipRecordFilesService,
            ipRecordId,
            nationalPresentationDate,
            nationalPublishingDate,
            onapiId,
            recordType,
            statusId,
            title,
            conclusionMethod,
            description,
            inventors,
            patentAbstract,
            priorities,
            registrationNumber,
            representativeName,
            requestCountryId,
            technicalReportDetail);
        this.designClassification = designClassification;
        this.locarnoClassification = locarnoClassification;
        this.mainDesignImage = mainDesignImage;
    }


    /**
     * Gets the designClassification value for this DesignPatent.
     * 
     * @return designClassification
     */
    public java.lang.String getDesignClassification() {
        return designClassification;
    }


    /**
     * Sets the designClassification value for this DesignPatent.
     * 
     * @param designClassification
     */
    public void setDesignClassification(java.lang.String designClassification) {
        this.designClassification = designClassification;
    }


    /**
     * Gets the locarnoClassification value for this DesignPatent.
     * 
     * @return locarnoClassification
     */
    public java.lang.String[] getLocarnoClassification() {
        return locarnoClassification;
    }


    /**
     * Sets the locarnoClassification value for this DesignPatent.
     * 
     * @param locarnoClassification
     */
    public void setLocarnoClassification(java.lang.String[] locarnoClassification) {
        this.locarnoClassification = locarnoClassification;
    }

    public java.lang.String getLocarnoClassification(int i) {
        return this.locarnoClassification[i];
    }

    public void setLocarnoClassification(int i, java.lang.String _value) {
        this.locarnoClassification[i] = _value;
    }


    /**
     * Gets the mainDesignImage value for this DesignPatent.
     * 
     * @return mainDesignImage
     */
    public java.lang.String getMainDesignImage() {
        return mainDesignImage;
    }


    /**
     * Sets the mainDesignImage value for this DesignPatent.
     * 
     * @param mainDesignImage
     */
    public void setMainDesignImage(java.lang.String mainDesignImage) {
        this.mainDesignImage = mainDesignImage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DesignPatent)) return false;
        DesignPatent other = (DesignPatent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.designClassification==null && other.getDesignClassification()==null) || 
             (this.designClassification!=null &&
              this.designClassification.equals(other.getDesignClassification()))) &&
            ((this.locarnoClassification==null && other.getLocarnoClassification()==null) || 
             (this.locarnoClassification!=null &&
              java.util.Arrays.equals(this.locarnoClassification, other.getLocarnoClassification()))) &&
            ((this.mainDesignImage==null && other.getMainDesignImage()==null) || 
             (this.mainDesignImage!=null &&
              this.mainDesignImage.equals(other.getMainDesignImage())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getDesignClassification() != null) {
            _hashCode += getDesignClassification().hashCode();
        }
        if (getLocarnoClassification() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLocarnoClassification());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLocarnoClassification(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMainDesignImage() != null) {
            _hashCode += getMainDesignImage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DesignPatent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.catalog.prosur.org/", "designPatent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("designClassification");
        elemField.setXmlName(new javax.xml.namespace.QName("", "designClassification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locarnoClassification");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locarnoClassification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mainDesignImage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mainDesignImage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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

	@Override
	public String toString() {
		return "DesignPatent [getDesignClassification()="
				+ getDesignClassification() + ", getLocarnoClassification()="
				+ Arrays.toString(getLocarnoClassification())
				+ ", getMainDesignImage()=" + getMainDesignImage()
				+ ", getConclusionMethod()=" + getConclusionMethod()
				+ ", getDescription()=" + getDescription()
				+ ", getInventors()=" + Arrays.toString(getInventors())
				+ ", getPatentAbstract()=" + getPatentAbstract()
				+ ", getPriorities()=" + Arrays.toString(getPriorities())
				+ ", getRegistrationNumber()=" + getRegistrationNumber()
				+ ", getRepresentativeName()=" + getRepresentativeName()
				+ ", getRequestCountryId()=" + getRequestCountryId()
				+ ", getTechnicalReportDetail()=" + getTechnicalReportDetail()
				+ ", getApplicantName()=" + Arrays.toString(getApplicantName())
				+ ", getApplicationId()=" + getApplicationId()
				+ ", getFiles()=" + Arrays.toString(getFiles())
				+ ", getIpRecordDetailLink()=" + getIpRecordDetailLink()
				+ ", getIpRecordFilesService()=" + getIpRecordFilesService()
				+ ", getIpRecordId()=" + getIpRecordId()
				+ ", getNationalPresentationDate()="
				+ getNationalPresentationDate()
				+ ", getNationalPublishingDate()="
				+ getNationalPublishingDate() + ", getOnapiId()="
				+ getOnapiId() + ", getRecordType()=" + getRecordType()
				+ ", getStatusId()=" + getStatusId() + ", getTitle()="
				+ getTitle() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
    
    
    

}
