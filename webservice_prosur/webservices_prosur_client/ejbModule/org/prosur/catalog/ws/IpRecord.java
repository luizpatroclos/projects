/**
 * IpRecord.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.prosur.catalog.ws;

public class IpRecord  implements java.io.Serializable {
    private java.lang.String[] applicantName;

    private java.lang.String applicationId;

    private org.prosur.catalog.ws.FileData[] files;

    private java.lang.String ipRecordDetailLink;

    private java.lang.String ipRecordFilesService;

    private java.lang.Long ipRecordId;

    private java.util.Date nationalPresentationDate;

    private java.util.Date nationalPublishingDate;

    private java.lang.String onapiId;

    private org.prosur.catalog.ws.IpRecordTypeEnum recordType;

    private java.lang.String statusId;

    private java.lang.String title;

    public IpRecord() {
    }

    public IpRecord(
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
           java.lang.String title) {
           this.applicantName = applicantName;
           this.applicationId = applicationId;
           this.files = files;
           this.ipRecordDetailLink = ipRecordDetailLink;
           this.ipRecordFilesService = ipRecordFilesService;
           this.ipRecordId = ipRecordId;
           this.nationalPresentationDate = nationalPresentationDate;
           this.nationalPublishingDate = nationalPublishingDate;
           this.onapiId = onapiId;
           this.recordType = recordType;
           this.statusId = statusId;
           this.title = title;
    }


    /**
     * Gets the applicantName value for this IpRecord.
     * 
     * @return applicantName
     */
    public java.lang.String[] getApplicantName() {
        return applicantName;
    }


    /**
     * Sets the applicantName value for this IpRecord.
     * 
     * @param applicantName
     */
    public void setApplicantName(java.lang.String[] applicantName) {
        this.applicantName = applicantName;
    }

    public java.lang.String getApplicantName(int i) {
        return this.applicantName[i];
    }

    public void setApplicantName(int i, java.lang.String _value) {
        this.applicantName[i] = _value;
    }


    /**
     * Gets the applicationId value for this IpRecord.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this IpRecord.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the files value for this IpRecord.
     * 
     * @return files
     */
    public org.prosur.catalog.ws.FileData[] getFiles() {
        return files;
    }


    /**
     * Sets the files value for this IpRecord.
     * 
     * @param files
     */
    public void setFiles(org.prosur.catalog.ws.FileData[] files) {
        this.files = files;
    }

    public org.prosur.catalog.ws.FileData getFiles(int i) {
        return this.files[i];
    }

    public void setFiles(int i, org.prosur.catalog.ws.FileData _value) {
        this.files[i] = _value;
    }


    /**
     * Gets the ipRecordDetailLink value for this IpRecord.
     * 
     * @return ipRecordDetailLink
     */
    public java.lang.String getIpRecordDetailLink() {
        return ipRecordDetailLink;
    }


    /**
     * Sets the ipRecordDetailLink value for this IpRecord.
     * 
     * @param ipRecordDetailLink
     */
    public void setIpRecordDetailLink(java.lang.String ipRecordDetailLink) {
        this.ipRecordDetailLink = ipRecordDetailLink;
    }


    /**
     * Gets the ipRecordFilesService value for this IpRecord.
     * 
     * @return ipRecordFilesService
     */
    public java.lang.String getIpRecordFilesService() {
        return ipRecordFilesService;
    }


    /**
     * Sets the ipRecordFilesService value for this IpRecord.
     * 
     * @param ipRecordFilesService
     */
    public void setIpRecordFilesService(java.lang.String ipRecordFilesService) {
        this.ipRecordFilesService = ipRecordFilesService;
    }


    /**
     * Gets the ipRecordId value for this IpRecord.
     * 
     * @return ipRecordId
     */
    public java.lang.Long getIpRecordId() {
        return ipRecordId;
    }


    /**
     * Sets the ipRecordId value for this IpRecord.
     * 
     * @param ipRecordId
     */
    public void setIpRecordId(java.lang.Long ipRecordId) {
        this.ipRecordId = ipRecordId;
    }


    /**
     * Gets the nationalPresentationDate value for this IpRecord.
     * 
     * @return nationalPresentationDate
     */
    public java.util.Date getNationalPresentationDate() {
        return nationalPresentationDate;
    }


    /**
     * Sets the nationalPresentationDate value for this IpRecord.
     * 
     * @param nationalPresentationDate
     */
    public void setNationalPresentationDate(java.util.Date nationalPresentationDate) {
        this.nationalPresentationDate = nationalPresentationDate;
    }


    /**
     * Gets the nationalPublishingDate value for this IpRecord.
     * 
     * @return nationalPublishingDate
     */
    public java.util.Date getNationalPublishingDate() {
        return nationalPublishingDate;
    }


    /**
     * Sets the nationalPublishingDate value for this IpRecord.
     * 
     * @param nationalPublishingDate
     */
    public void setNationalPublishingDate(java.util.Date nationalPublishingDate) {
        this.nationalPublishingDate = nationalPublishingDate;
    }


    /**
     * Gets the onapiId value for this IpRecord.
     * 
     * @return onapiId
     */
    public java.lang.String getOnapiId() {
        return onapiId;
    }


    /**
     * Sets the onapiId value for this IpRecord.
     * 
     * @param onapiId
     */
    public void setOnapiId(java.lang.String onapiId) {
        this.onapiId = onapiId;
    }


    /**
     * Gets the recordType value for this IpRecord.
     * 
     * @return recordType
     */
    public org.prosur.catalog.ws.IpRecordTypeEnum getRecordType() {
        return recordType;
    }


    /**
     * Sets the recordType value for this IpRecord.
     * 
     * @param recordType
     */
    public void setRecordType(org.prosur.catalog.ws.IpRecordTypeEnum recordType) {
        this.recordType = recordType;
    }


    /**
     * Gets the statusId value for this IpRecord.
     * 
     * @return statusId
     */
    public java.lang.String getStatusId() {
        return statusId;
    }


    /**
     * Sets the statusId value for this IpRecord.
     * 
     * @param statusId
     */
    public void setStatusId(java.lang.String statusId) {
        this.statusId = statusId;
    }


    /**
     * Gets the title value for this IpRecord.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this IpRecord.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IpRecord)) return false;
        IpRecord other = (IpRecord) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicantName==null && other.getApplicantName()==null) || 
             (this.applicantName!=null &&
              java.util.Arrays.equals(this.applicantName, other.getApplicantName()))) &&
            ((this.applicationId==null && other.getApplicationId()==null) || 
             (this.applicationId!=null &&
              this.applicationId.equals(other.getApplicationId()))) &&
            ((this.files==null && other.getFiles()==null) || 
             (this.files!=null &&
              java.util.Arrays.equals(this.files, other.getFiles()))) &&
            ((this.ipRecordDetailLink==null && other.getIpRecordDetailLink()==null) || 
             (this.ipRecordDetailLink!=null &&
              this.ipRecordDetailLink.equals(other.getIpRecordDetailLink()))) &&
            ((this.ipRecordFilesService==null && other.getIpRecordFilesService()==null) || 
             (this.ipRecordFilesService!=null &&
              this.ipRecordFilesService.equals(other.getIpRecordFilesService()))) &&
            ((this.ipRecordId==null && other.getIpRecordId()==null) || 
             (this.ipRecordId!=null &&
              this.ipRecordId.equals(other.getIpRecordId()))) &&
            ((this.nationalPresentationDate==null && other.getNationalPresentationDate()==null) || 
             (this.nationalPresentationDate!=null &&
              this.nationalPresentationDate.equals(other.getNationalPresentationDate()))) &&
            ((this.nationalPublishingDate==null && other.getNationalPublishingDate()==null) || 
             (this.nationalPublishingDate!=null &&
              this.nationalPublishingDate.equals(other.getNationalPublishingDate()))) &&
            ((this.onapiId==null && other.getOnapiId()==null) || 
             (this.onapiId!=null &&
              this.onapiId.equals(other.getOnapiId()))) &&
            ((this.recordType==null && other.getRecordType()==null) || 
             (this.recordType!=null &&
              this.recordType.equals(other.getRecordType()))) &&
            ((this.statusId==null && other.getStatusId()==null) || 
             (this.statusId!=null &&
              this.statusId.equals(other.getStatusId()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle())));
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
        if (getApplicantName() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getApplicantName());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getApplicantName(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getApplicationId() != null) {
            _hashCode += getApplicationId().hashCode();
        }
        if (getFiles() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFiles());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFiles(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIpRecordDetailLink() != null) {
            _hashCode += getIpRecordDetailLink().hashCode();
        }
        if (getIpRecordFilesService() != null) {
            _hashCode += getIpRecordFilesService().hashCode();
        }
        if (getIpRecordId() != null) {
            _hashCode += getIpRecordId().hashCode();
        }
        if (getNationalPresentationDate() != null) {
            _hashCode += getNationalPresentationDate().hashCode();
        }
        if (getNationalPublishingDate() != null) {
            _hashCode += getNationalPublishingDate().hashCode();
        }
        if (getOnapiId() != null) {
            _hashCode += getOnapiId().hashCode();
        }
        if (getRecordType() != null) {
            _hashCode += getRecordType().hashCode();
        }
        if (getStatusId() != null) {
            _hashCode += getStatusId().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IpRecord.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.catalog.prosur.org/", "ipRecord"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicantName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicantName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("files");
        elemField.setXmlName(new javax.xml.namespace.QName("", "files"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.catalog.prosur.org/", "fileData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipRecordDetailLink");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ipRecordDetailLink"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipRecordFilesService");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ipRecordFilesService"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipRecordId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ipRecordId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nationalPresentationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nationalPresentationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nationalPublishingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nationalPublishingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("onapiId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "onapiId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recordType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.catalog.prosur.org/", "ipRecordTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
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
