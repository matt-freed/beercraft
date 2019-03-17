//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.15 at 09:49:56 PM EDT 
//


package com.beercom.beerxml.xml;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for TemperatureType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TemperatureType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>decimal">
 *       &lt;attribute name="degrees" use="required" type="{urn:beerxml:unit:v2}TemperatureUnitType" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemperatureType", propOrder = {
    "value"
})
public class TemperatureType {

    @XmlValue
    protected BigDecimal value;
    @XmlAttribute(name = "degrees", required = true)
    protected TemperatureUnitType degrees;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Gets the value of the degrees property.
     * 
     * @return
     *     possible object is
     *     {@link TemperatureUnitType }
     *     
     */
    public TemperatureUnitType getDegrees() {
        return degrees;
    }

    /**
     * Sets the value of the degrees property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemperatureUnitType }
     *     
     */
    public void setDegrees(TemperatureUnitType value) {
        this.degrees = value;
    }

}
