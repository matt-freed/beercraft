//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.15 at 09:49:56 PM EDT 
//


package com.beercom.beerxml.xml;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MashProcedureType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MashProcedureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="grain_temperature" type="{urn:beerxml:unit:v2}TemperatureType"/>
 *         &lt;element name="sparge_temperature" type="{urn:beerxml:unit:v2}TemperatureType" minOccurs="0"/>
 *         &lt;element name="pH" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="notes" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="mash_steps">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="step" type="{urn:beerxml:mash:step:v2}MashStepType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
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
@XmlType(name = "MashProcedureType", namespace = "urn:beerxml:mash:v2", propOrder = {
    "name",
    "grainTemperature",
    "spargeTemperature",
    "ph",
    "notes",
    "mashSteps"
})
public class MashProcedureType {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(name = "grain_temperature", required = true)
    protected TemperatureType grainTemperature;
    @XmlElement(name = "sparge_temperature")
    protected TemperatureType spargeTemperature;
    @XmlElement(name = "pH")
    protected BigDecimal ph;
    protected String notes;
    @XmlElement(name = "mash_steps", required = true)
    protected MashProcedureType.MashSteps mashSteps;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the grainTemperature property.
     * 
     * @return
     *     possible object is
     *     {@link TemperatureType }
     *     
     */
    public TemperatureType getGrainTemperature() {
        return grainTemperature;
    }

    /**
     * Sets the value of the grainTemperature property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemperatureType }
     *     
     */
    public void setGrainTemperature(TemperatureType value) {
        this.grainTemperature = value;
    }

    /**
     * Gets the value of the spargeTemperature property.
     * 
     * @return
     *     possible object is
     *     {@link TemperatureType }
     *     
     */
    public TemperatureType getSpargeTemperature() {
        return spargeTemperature;
    }

    /**
     * Sets the value of the spargeTemperature property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemperatureType }
     *     
     */
    public void setSpargeTemperature(TemperatureType value) {
        this.spargeTemperature = value;
    }

    /**
     * Gets the value of the ph property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPH() {
        return ph;
    }

    /**
     * Sets the value of the ph property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPH(BigDecimal value) {
        this.ph = value;
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
     * Gets the value of the mashSteps property.
     * 
     * @return
     *     possible object is
     *     {@link MashProcedureType.MashSteps }
     *     
     */
    public MashProcedureType.MashSteps getMashSteps() {
        return mashSteps;
    }

    /**
     * Sets the value of the mashSteps property.
     * 
     * @param value
     *     allowed object is
     *     {@link MashProcedureType.MashSteps }
     *     
     */
    public void setMashSteps(MashProcedureType.MashSteps value) {
        this.mashSteps = value;
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
     *       &lt;sequence>
     *         &lt;element name="step" type="{urn:beerxml:mash:step:v2}MashStepType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "step"
    })
    public static class MashSteps {

        @XmlElement(namespace = "urn:beerxml:mash:v2", required = true)
        protected List<MashStepType> step;

        /**
         * Gets the value of the step property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the step property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStep().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MashStepType }
         * 
         * 
         */
        public List<MashStepType> getStep() {
            if (step == null) {
                step = new ArrayList<MashStepType>();
            }
            return this.step;
        }

    }

}
