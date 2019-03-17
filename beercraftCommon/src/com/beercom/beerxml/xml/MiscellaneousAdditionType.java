//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.15 at 09:49:56 PM EDT 
//


package com.beercom.beerxml.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MiscellaneousAdditionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MiscellaneousAdditionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addition" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{urn:beerxml:miscellaneous:v2}MiscellaneousBase">
 *                 &lt;sequence>
 *                   &lt;choice>
 *                     &lt;element name="amount" type="{urn:beerxml:unit:v2}VolumeType"/>
 *                     &lt;element name="amount_as_weight" type="{urn:beerxml:unit:v2}MassType"/>
 *                   &lt;/choice>
 *                   &lt;element name="time" type="{urn:beerxml:unit:v2}TimeType"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MiscellaneousAdditionType", namespace = "urn:beerxml:miscellaneous:v2", propOrder = {
    "addition"
})
public class MiscellaneousAdditionType {

    @XmlElement(required = true)
    protected List<MiscellaneousAdditionType.Addition> addition;

    /**
     * Gets the value of the addition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MiscellaneousAdditionType.Addition }
     * 
     * 
     */
    public List<MiscellaneousAdditionType.Addition> getAddition() {
        if (addition == null) {
            addition = new ArrayList<MiscellaneousAdditionType.Addition>();
        }
        return this.addition;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{urn:beerxml:miscellaneous:v2}MiscellaneousBase">
     *       &lt;sequence>
     *         &lt;choice>
     *           &lt;element name="amount" type="{urn:beerxml:unit:v2}VolumeType"/>
     *           &lt;element name="amount_as_weight" type="{urn:beerxml:unit:v2}MassType"/>
     *         &lt;/choice>
     *         &lt;element name="time" type="{urn:beerxml:unit:v2}TimeType"/>
     *       &lt;/sequence>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "amount",
        "amountAsWeight",
        "time"
    })
    public static class Addition
        extends MiscellaneousBase
    {

        @XmlElement(namespace = "urn:beerxml:miscellaneous:v2")
        protected VolumeType amount;
        @XmlElement(name = "amount_as_weight", namespace = "urn:beerxml:miscellaneous:v2")
        protected MassType amountAsWeight;
        @XmlElement(namespace = "urn:beerxml:miscellaneous:v2", required = true)
        protected TimeType time;

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

        /**
         * Gets the value of the time property.
         * 
         * @return
         *     possible object is
         *     {@link TimeType }
         *     
         */
        public TimeType getTime() {
            return time;
        }

        /**
         * Sets the value of the time property.
         * 
         * @param value
         *     allowed object is
         *     {@link TimeType }
         *     
         */
        public void setTime(TimeType value) {
            this.time = value;
        }

    }

}
