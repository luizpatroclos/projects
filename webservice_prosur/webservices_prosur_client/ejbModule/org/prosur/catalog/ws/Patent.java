/**
 * Patent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.prosur.catalog.ws;

public class Patent  extends org.prosur.catalog.ws.IpRecord  implements java.io.Serializable {
    private java.lang.String conclusionMethod;

    private java.lang.String description;

    private java.lang.String[] inventors;

    private java.lang.String patentAbstract;

    private org.prosur.catalog.ws.Priority[] priorities;

    private java.lang.String registrationNumber;

    private java.lang.String representativeName;

    private java.lang.String requestCountryId;

    private java.lang.String technicalReportDetail;

    public Patent() {
    }

    public Patent(
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
           java.lang.String technicalReportDetail) {
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
            title);
        this.conclusionMethod = conclusionMethod;
        this.description = description;
        this.inventors = inventors;
        this.patentAbstract = patentAbstract;
        this.priorities = priorities;
        this.registrationNumber = registrationNumber;
        this.representativeName = representativeName;
        this.requestCountryId = requestCountryId;
        this.technicalReportDetail = technicalReportDetail;
    }


    /**
     * Gets the conclusionMethod value for this Patent.
     * 
     * @return conclusionMethod
     */
    public java.lang.String getConclusionMethod() {
        return conclusionMethod;
    }


    /**
     * Sets the conclusionMethod value for this Patent.
     * 
     * @param conclusionMethod
     */
    public void setConclusionMethod(java.lang.String conclusionMethod) {
        this.conclusionMethod = conclusionMethod;
    }


    /**
     * Gets the description value for this Patent.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Patent.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the inventors value for this Patent.
     * 
     * @return inventors
     */
    public java.lang.String[] getInventors() {
        return inventors;
    }


    /**
     * Sets the inventors value for this Patent.
     * 
     * @param inventors
     */
    public void setInventors(java.lang.String[] inventors) {
        this.inventors = inventors;
    }

    public java.lang.String getInventors(int i) {
        return this.inventors[i];
    }

    public void setInventors(int i, java.lang.String _value) {
        this.inventors[i] = _value;
    }


    /**
     * Gets the patentAbstract value for this Patent.
     * 
     * @return patentAbstract
     */
    public java.lang.String getPatentAbstract() {
        return patentAbstract;
    }


    /**
     * Sets the patentAbstract value for this Patent.
     * 
     * @param patentAbstract
     */
    public void setPatentAbstract(java.lang.String patentAbstract) {
        this.patentAbstract = patentAbstract;
    }


    /**
     * Gets the priorities value for this Patent.
     * 
     * @return priorities
     */
    public org.prosur.catalog.ws.Priority[] getPriorities() {
        return priorities;
    }


    /**
     * Sets the priorities value for this Patent.
     * 
     * @param priorities
     */
    public void setPriorities(org.prosur.catalog.ws.Priority[] priorities) {
        this.priorities = priorities;
    }

    public org.prosur.catalog.ws.Priority getPriorities(int i) {
        return this.priorities[i];
    }

    public void setPriorities(int i, org.prosur.catalog.ws.Priority _value) {
        this.priorities[i] = _value;
    }


    /**
     * Gets the registrationNumber value for this Patent.
     * 
     * @return registrationNumber
     */
    public java.lang.String getRegistrationNumber() {
        return registrationNumber;
    }


    /**
     * Sets the registrationNumber value for this Patent.
     * 
     * @param registrationNumber
     */
    public void setRegistrationNumber(java.lang.String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }


    /**
     * Gets the representativeName value for this Patent.
     * 
     * @return representativeName
     */
    public java.lang.String getRepresentativeName() {
        return representativeName;
    }


    /**
     * Sets the representativeName value for this Patent.
     * 
     * @param representativeName
     */
    public void setRepresentativeName(java.lang.String representativeName) {
        this.representativeName = representativeName;
    }


    /**
     * Gets the requestCountryId value for this Patent.
     * 
     * @return requestCountryId
     */
    public java.lang.String getRequestCountryId() {
        return requestCountryId;
    }


    /**
     * Sets the requestCountryId value for this Patent.
     * 
     * @param requestCountryId
     */
    public void setRequestCountryId(java.lang.String requestCountryId) {
        this.requestCountryId = requestCountryId;
    }


    /**
     * Gets the technicalReportDetail value for this Patent.
     * 
     * @return technicalReportDetail
     */
    public java.lang.String getTechnicalReportDetail() {
        return technicalReportDetail;
    }


    /**
     * Sets the technicalReportDetail value for this Patent.
     * 
     * @param technicalReportDetail
     */
    public void setTechnicalReportDetail(java.lang.String technicalReportDetail) {
        this.technicalReportDetail = technicalReportDetail;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Patent)) return false;
        Patent other = (Patent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.conclusionMethod==null && other.getConclusionMethod()==null) || 
             (this.conclusionMethod!=null &&
              this.conclusionMethod.equals(other.getConclusionMethod()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.inventors==null && other.getInventors()==null) || 
             (this.inventors!=null &&
              java.util.Arrays.equals(this.inventors, other.getInventors()))) &&
            ((this.patentAbstract==null && other.getPatentAbstract()==null) || 
             (this.patentAbstract!=null &&
              this.patentAbstract.equals(other.getPatentAbstract()))) &&
            ((this.priorities==null && other.getPriorities()==null) || 
             (this.priorities!=null &&
              java.util.Arrays.equals(this.priorities, other.getPriorities()))) &&
            ((this.registrationNumber==null && other.getRegistrationNumber()==null) || 
             (this.registrationNumber!=null &&
              this.registrationNumber.equals(other.getRegistrationNumber()))) &&
            ((this.representativeName==null && other.getRepresentativeName()==null) || 
             (this.representativeName!=null &&
              this.representativeName.equals(other.getRepresentativeName()))) &&
            ((this.requestCountryId==null && other.getRequestCountryId()==null) || 
             (this.requestCountryId!=null &&
              this.requestCountryId.equals(other.getRequestCountryId()))) &&
            ((this.technicalReportDetail==null && other.getTechnicalReportDetail()==null) || 
             (this.technicalReportDetail!=null &&
              this.technicalReportDetail.equals(other.getTechnicalReportDetail())));
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
        if (getConclusionMethod() != null) {
            _hashCode += getConclusionMethod().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getInventors() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInventors());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInventors(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPatentAbstract() != null) {
            _hashCode += getPatentAbstract().hashCode();
        }
        if (getPriorities() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPriorities());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPriorities(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRegistrationNumber() != null) {
            _hashCode += getRegistrationNumber().hashCode();
        }
        if (getRepresentativeName() != null) {
            _hashCode += getRepresentativeName().hashCode();
        }
        if (getRequestCountryId() != null) {
            _hashCode += getRequestCountryId().hashCode();
        }
        if (getTechnicalReportDetail() != null) {
            _hashCode += getTechnicalReportDetail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Patent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.catalog.prosur.org/", "patent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conclusionMethod");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conclusionMethod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inventors");
        elemField.setXmlName(new javax.xml.namespace.QName("", "inventors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("patentAbstract");
        elemField.setXmlName(new javax.xml.namespace.QName("", "patentAbstract"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priorities");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priorities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.catalog.prosur.org/", "priority"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrationNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "registrationNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("representativeName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "representativeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestCountryId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestCountryId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("technicalReportDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("", "technicalReportDetail"));
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

}
