/**
 * DistinctiveSign.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.prosur.catalog.ws;

public class DistinctiveSign  extends org.prosur.catalog.ws.IpRecord  implements java.io.Serializable {
    private java.lang.String distinctiveSignType;

    private java.util.Date expiration;

    private java.lang.String logoDescription;

    private org.prosur.catalog.ws.NiceClass[] niceClasses;

    private java.lang.String presentationType;

    private java.util.Date registrationDate;

    public DistinctiveSign() {
    }

    public DistinctiveSign(
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
           java.lang.String distinctiveSignType,
           java.util.Date expiration,
           java.lang.String logoDescription,
           org.prosur.catalog.ws.NiceClass[] niceClasses,
           java.lang.String presentationType,
           java.util.Date registrationDate) {
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
        this.distinctiveSignType = distinctiveSignType;
        this.expiration = expiration;
        this.logoDescription = logoDescription;
        this.niceClasses = niceClasses;
        this.presentationType = presentationType;
        this.registrationDate = registrationDate;
    }


    /**
     * Gets the distinctiveSignType value for this DistinctiveSign.
     * 
     * @return distinctiveSignType
     */
    public java.lang.String getDistinctiveSignType() {
        return distinctiveSignType;
    }


    /**
     * Sets the distinctiveSignType value for this DistinctiveSign.
     * 
     * @param distinctiveSignType
     */
    public void setDistinctiveSignType(java.lang.String distinctiveSignType) {
        this.distinctiveSignType = distinctiveSignType;
    }


    /**
     * Gets the expiration value for this DistinctiveSign.
     * 
     * @return expiration
     */
    public java.util.Date getExpiration() {
        return expiration;
    }


    /**
     * Sets the expiration value for this DistinctiveSign.
     * 
     * @param expiration
     */
    public void setExpiration(java.util.Date expiration) {
        this.expiration = expiration;
    }


    /**
     * Gets the logoDescription value for this DistinctiveSign.
     * 
     * @return logoDescription
     */
    public java.lang.String getLogoDescription() {
        return logoDescription;
    }


    /**
     * Sets the logoDescription value for this DistinctiveSign.
     * 
     * @param logoDescription
     */
    public void setLogoDescription(java.lang.String logoDescription) {
        this.logoDescription = logoDescription;
    }


    /**
     * Gets the niceClasses value for this DistinctiveSign.
     * 
     * @return niceClasses
     */
    public org.prosur.catalog.ws.NiceClass[] getNiceClasses() {
        return niceClasses;
    }


    /**
     * Sets the niceClasses value for this DistinctiveSign.
     * 
     * @param niceClasses
     */
    public void setNiceClasses(org.prosur.catalog.ws.NiceClass[] niceClasses) {
        this.niceClasses = niceClasses;
    }

    public org.prosur.catalog.ws.NiceClass getNiceClasses(int i) {
        return this.niceClasses[i];
    }

    public void setNiceClasses(int i, org.prosur.catalog.ws.NiceClass _value) {
        this.niceClasses[i] = _value;
    }


    /**
     * Gets the presentationType value for this DistinctiveSign.
     * 
     * @return presentationType
     */
    public java.lang.String getPresentationType() {
        return presentationType;
    }


    /**
     * Sets the presentationType value for this DistinctiveSign.
     * 
     * @param presentationType
     */
    public void setPresentationType(java.lang.String presentationType) {
        this.presentationType = presentationType;
    }


    /**
     * Gets the registrationDate value for this DistinctiveSign.
     * 
     * @return registrationDate
     */
    public java.util.Date getRegistrationDate() {
        return registrationDate;
    }


    /**
     * Sets the registrationDate value for this DistinctiveSign.
     * 
     * @param registrationDate
     */
    public void setRegistrationDate(java.util.Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DistinctiveSign)) return false;
        DistinctiveSign other = (DistinctiveSign) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.distinctiveSignType==null && other.getDistinctiveSignType()==null) || 
             (this.distinctiveSignType!=null &&
              this.distinctiveSignType.equals(other.getDistinctiveSignType()))) &&
            ((this.expiration==null && other.getExpiration()==null) || 
             (this.expiration!=null &&
              this.expiration.equals(other.getExpiration()))) &&
            ((this.logoDescription==null && other.getLogoDescription()==null) || 
             (this.logoDescription!=null &&
              this.logoDescription.equals(other.getLogoDescription()))) &&
            ((this.niceClasses==null && other.getNiceClasses()==null) || 
             (this.niceClasses!=null &&
              java.util.Arrays.equals(this.niceClasses, other.getNiceClasses()))) &&
            ((this.presentationType==null && other.getPresentationType()==null) || 
             (this.presentationType!=null &&
              this.presentationType.equals(other.getPresentationType()))) &&
            ((this.registrationDate==null && other.getRegistrationDate()==null) || 
             (this.registrationDate!=null &&
              this.registrationDate.equals(other.getRegistrationDate())));
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
        if (getDistinctiveSignType() != null) {
            _hashCode += getDistinctiveSignType().hashCode();
        }
        if (getExpiration() != null) {
            _hashCode += getExpiration().hashCode();
        }
        if (getLogoDescription() != null) {
            _hashCode += getLogoDescription().hashCode();
        }
        if (getNiceClasses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNiceClasses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNiceClasses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPresentationType() != null) {
            _hashCode += getPresentationType().hashCode();
        }
        if (getRegistrationDate() != null) {
            _hashCode += getRegistrationDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DistinctiveSign.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.catalog.prosur.org/", "distinctiveSign"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("distinctiveSignType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "distinctiveSignType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "expiration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logoDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logoDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("niceClasses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "niceClasses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.catalog.prosur.org/", "niceClass"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("presentationType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "presentationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "registrationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
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
