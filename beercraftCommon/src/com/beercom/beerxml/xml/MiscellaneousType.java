//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.15 at 09:49:56 PM EDT 
//


package com.beercom.beerxml.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MiscellaneousType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MiscellaneousType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:beerxml:miscellaneous:v2}MiscellaneousBase">
 *       &lt;sequence>
 *         &lt;element name="use_for" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="notes" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="inventory" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="amount" type="{urn:beerxml:unit:v2}VolumeType"/>
 *                   &lt;element name="amount_as_weight" type="{urn:beerxml:unit:v2}MassType"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MiscellaneousType", namespace = "urn:beerxml:miscellaneous:v2", propOrder = {
    "useFor",
    "notes",
    "inventory"
})
public class MiscellaneousType
    extends MiscellaneousBase
{

    @XmlElement(name = "use_for")
    protected String useFor;
    protected String notes;
    protected MiscellaneousType.Inventory inventory;

    /**
     * Gets the value of the useFor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseFor() {
        return useFor;
    }

    /**
     * Sets the value of the useFor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseFor(String value) {
        this.useFor = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

    /**
     * Gets the value of the inventory property.
     * 
     * @return
     *     possible object is
     *     {@link MiscellaneousType.Inventory }
     *     
     */
    public MiscellaneousType.Inventory getInventory() {
        return inventory;
    }

    /**
     * Sets the value of the inventory property.
     * 
     * @param value
     *     allowed object is
     *     {@link MiscellaneousType.Inventory }
     *     
     */
    public void setInventory(MiscellaneousType.Inventory value) {
        this.inventory = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element name="amount" type="{urn:beerxml:unit:v2}VolumeType"/>
     *         &lt;element name="amount_as_weight" type="{urn:beerxml:unit:v2}MassType"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "amount",
        "amountAsWeight"
    })
    public static class Inventory {

        @XmlElement(namespace = "urn:beerxml:miscellaneous:v2")
        protected VolumeType amount;
        @XmlElement(name = "amount_as_weight", namespace = "urn:beerxml:miscellaneous:v2")
        protected MassType amountAsWeight;

        /**
         * Gets the value of the amount property.
         * 
         * @return
         *     possible object is
         *     {@link VolumeType }
         *     
         */
        public VolumeType getAmount() {
            return amount;
        }

        /**
         * Sets the value of the amount property.
         * 
         * @param value
         *     allowed object is
         *     {@link VolumeType }
         *     
         */
        public void setAmount(VolumeType value) {
            this.amount = value;
        }

        /**
         * Gets the value of the amountAsWeight property.
         * 
         * @return
         *     possible object is
         *     {@link MassType }
         *     
         */
        public MassType getAmountAsWeight() {
            return amountAsWeight;
        }

        /**
         * Sets the value of the amountAsWeight property.
         * 
         * @param value
         *     allowed object is
         *     {@link MassType }
         *     
         */
        public void setAmountAsWeight(MassType value) {
            this.amountAsWeight = value;
        }

    }

}
