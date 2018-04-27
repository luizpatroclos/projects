/**
 * InventionPatent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.prosur.catalog.ws;

import java.util.Arrays;

public class InventionPatent  extends org.prosur.catalog.ws.Patent  implements java.io.Serializable {
    private java.lang.String[] internationalClassification;

    private java.lang.String patentTypeId;

    private java.util.Date pctApplicationDate;

    private java.lang.String pctApplicationNumber;

    public InventionPatent() {
    }

    public InventionPatent(
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
           java.lang.String[] internationalClassification,
           java.lang.String patentTypeId,
           java.util.Date pctApplicationDate,
           java.lang.String pctApplicationNumber) {
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
        this.internationalClassification = internationalClassification;
        this.patentTypeId = patentTypeId;
        this.pctApplicationDate = pctApplicationDate;
        this.pctApplicationNumber = pctApplicationNumber;
    }


    /**
     * Gets the internationalClassification value for this InventionPatent.
     * 
     * @return internationalClassification
     */
    public java.lang.String[] getInternationalClassification() {
        return internationalClassification;
    }


    /**
     * Sets the internationalClassification value for this InventionPatent.
     * 
     * @param internationalClassification
     */
    public void setInternationalClassification(java.lang.String[] internationalClassification) {
        this.internationalClassification = internationalClassification;
    }

    public java.lang.String getInternationalClassification(int i) {
        return this.internationalClassification[i];
    }

    public void setInternationalClassification(int i, java.lang.String _value) {
        this.internationalClassification[i] = _value;
    }


    /**
     * Gets the patentTypeId value for this InventionPatent.
     * 
     * @return patentTypeId
     */
    public java.lang.String getPatentTypeId() {
        return patentTypeId;
    }


    /**
     * Sets the patentTypeId value for this InventionPatent.
     * 
     * @param patentTypeId
     */
    public void setPatentTypeId(java.lang.String patentTypeId) {
        this.patentTypeId = patentTypeId;
    }


    /**
     * Gets the pctApplicationDate value for this InventionPatent.
     * 
     * @return pctApplicationDate
     */
    public java.util.Date getPctApplicationDate() {
        return pctApplicationDate;
    }


    /**
     * Sets the pctApplicationDate value for this InventionPatent.
     * 
     * @param pctApplicationDate
     */
    public void setPctApplicationDate(java.util.Date pctApplicationDate) {
        this.pctApplicationDate = pctApplicationDate;
    }


    /**
     * Gets the pctApplicationNumber value for this InventionPatent.
     * 
     * @return pctApplicationNumber
     */
    public java.lang.String getPctApplicationNumber() {
        return pctApplicationNumber;
    }


    /**
     * Sets the pctApplicationNumber value for this InventionPatent.
     * 
     * @param pctApplicationNumber
     */
    public void setPctApplicationNumber(java.lang.String pctApplicationNumber) {
        this.pctApplicationNumber = pctApplicationNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InventionPatent)) return false;
        InventionPatent other = (InventionPatent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.internationalClassification==null && other.getInternationalClassification()==null) || 
             (this.internationalClassification!=null &&
              java.util.Arrays.equals(this.internationalClassification, other.getInternationalClassification()))) &&
            ((this.patentTypeId==null && other.getPatentTypeId()==null) || 
             (this.patentTypeId!=null &&
              this.patentTypeId.equals(other.getPatentTypeId()))) &&
            ((this.pctApplicationDate==null && other.getPctApplicationDate()==null) || 
             (this.pctApplicationDate!=null &&
              this.pctApplicationDate.equals(other.getPctApplicationDate()))) &&
            ((this.pctApplicationNumber==null && other.getPctApplicationNumber()==null) || 
             (this.pctApplicationNumber!=null &&
              this.pctApplicationNumber.equals(other.getPctApplicationNumber())));
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
        if (getInternationalClassification() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInternationalClassification());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInternationalClassification(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPatentTypeId() != null) {
            _hashCode += getPatentTypeId().hashCode();
        }
        if (getPctApplicationDate() != null) {
            _hashCode += getPctApplicationDate().hashCode();
        }
        if (getPctApplicationNumber() != null) {
            _hashCode += getPctApplicationNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InventionPatent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.catalog.prosur.org/", "inventionPatent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("internationalClassification");
        elemField.setXmlName(new javax.xml.namespace.QName("", "internationalClassification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("patentTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "patentTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pctApplicationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pctApplicationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pctApplicationNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pctApplicationNumber"));
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
		return "InventionPatent [getInternationalClassification()="
				+ Arrays.toString(getInternationalClassification())
				+ ", getPatentTypeId()=" + getPatentTypeId()
				+ ", getPctApplicationDate()=" + getPctApplicationDate()
				+ ", getPctApplicationNumber()=" + getPctApplicationNumber()
				+ ", hashCode()=" + hashCode() + ", getConclusionMethod()="
				+ getConclusionMethod() + ", getDescription()="
				+ getDescription() + ", getInventors()="
				+ Arrays.toString(getInventors()) + ", getPatentAbstract()="
				+ getPatentAbstract() + ", getPriorities()="
				+ Arrays.toString(getPriorities())
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
