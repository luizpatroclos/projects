/**
 * Priority.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.prosur.catalog.ws;

public class Priority  implements java.io.Serializable {
    private java.lang.String priorityCountryId;

    private java.util.Date priorityDate;

    private java.lang.String priorityNumber;

    public Priority() {
    }

    public Priority(
           java.lang.String priorityCountryId,
           java.util.Date priorityDate,
           java.lang.String priorityNumber) {
           this.priorityCountryId = priorityCountryId;
           this.priorityDate = priorityDate;
           this.priorityNumber = priorityNumber;
    }


    /**
     * Gets the priorityCountryId value for this Priority.
     * 
     * @return priorityCountryId
     */
    public java.lang.String getPriorityCountryId() {
        return priorityCountryId;
    }


    /**
     * Sets the priorityCountryId value for this Priority.
     * 
     * @param priorityCountryId
     */
    public void setPriorityCountryId(java.lang.String priorityCountryId) {
        this.priorityCountryId = priorityCountryId;
    }


    /**
     * Gets the priorityDate value for this Priority.
     * 
     * @return priorityDate
     */
    public java.util.Date getPriorityDate() {
        return priorityDate;
    }


    /**
     * Sets the priorityDate value for this Priority.
     * 
     * @param priorityDate
     */
    public void setPriorityDate(java.util.Date priorityDate) {
        this.priorityDate = priorityDate;
    }


    /**
     * Gets the priorityNumber value for this Priority.
     * 
     * @return priorityNumber
     */
    public java.lang.String getPriorityNumber() {
        return priorityNumber;
    }


    /**
     * Sets the priorityNumber value for this Priority.
     * 
     * @param priorityNumber
     */
    public void setPriorityNumber(java.lang.String priorityNumber) {
        this.priorityNumber = priorityNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Priority)) return false;
        Priority other = (Priority) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.priorityCountryId==null && other.getPriorityCountryId()==null) || 
             (this.priorityCountryId!=null &&
              this.priorityCountryId.equals(other.getPriorityCountryId()))) &&
            ((this.priorityDate==null && other.getPriorityDate()==null) || 
             (this.priorityDate!=null &&
              this.priorityDate.equals(other.getPriorityDate()))) &&
            ((this.priorityNumber==null && other.getPriorityNumber()==null) || 
             (this.priorityNumber!=null &&
              this.priorityNumber.equals(other.getPriorityNumber())));
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
        if (getPriorityCountryId() != null) {
            _hashCode += getPriorityCountryId().hashCode();
        }
        if (getPriorityDate() != null) {
            _hashCode += getPriorityDate().hashCode();
        }
        if (getPriorityNumber() != null) {
            _hashCode += getPriorityNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Priority.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.catalog.prosur.org/", "priority"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priorityCountryId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priorityCountryId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priorityDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priorityDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priorityNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priorityNumber"));
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
		return "Priority [getPriorityCountryId()=" + getPriorityCountryId()
				+ ", getPriorityDate()=" + getPriorityDate()
				+ ", getPriorityNumber()=" + getPriorityNumber() + "]";
	}
    
    
    

}
