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
 * Style Type Record Information
 * 
 * <p>Java class for StyleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StyleType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:beerxml:style:v2}StyleBase">
 *       &lt;sequence>
 *         &lt;element name="original_gravity" type="{urn:beerxml:unit:v2}DensityRangeType"/>
 *         &lt;element name="final_gravity" type="{urn:beerxml:unit:v2}DensityRangeType"/>
 *         &lt;element name="international_bitterness_units" type="{urn:beerxml:unit:v2}QuantitativeRangeType"/>
 *         &lt;element name="color" type="{urn:beerxml:unit:v2}ColorRangeType"/>
 *         &lt;element name="carbonation" type="{urn:beerxml:unit:v2}QuantitativeRangeType" minOccurs="0"/>
 *         &lt;element name="alcohol_by_volume" type="{urn:beerxml:unit:v2}PercentRangeType" minOccurs="0"/>
 *         &lt;element name="notes" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="profile" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ingredients" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="examples" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
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
@XmlType(name = "StyleType", namespace = "urn:beerxml:style:v2", propOrder = {
    "originalGravity",
    "finalGravity",
    "internationalBitternessUnits",
    "color",
    "carbonation",
    "alcoholByVolume",
    "notes",
    "profile",
    "ingredients",
    "examples"
})
public class StyleType
    extends StyleBase
{

    @XmlElement(name = "original_gravity", required = true)
    protected DensityRangeType originalGravity;
    @XmlElement(name = "final_gravity", required = true)
    protected DensityRangeType finalGravity;
    @XmlElement(name = "international_bitterness_units", required = true)
    protected QuantitativeRangeType internationalBitternessUnits;
    @XmlElement(required = true)
    protected ColorRangeType color;
    protected QuantitativeRangeType carbonation;
    @XmlElement(name = "alcohol_by_volume")
    protected PercentRangeType alcoholByVolume;
    protected String notes;
    protected String profile;
    protected String ingredients;
    protected String examples;

    /**
     * Gets the value of the originalGravity property.
     * 
     * @return
     *     possible object is
     *     {@link DensityRangeType }
     *     
     */
    public DensityRangeType getOriginalGravity() {
        return originalGravity;
    }

    /**
     * Sets the value of the originalGravity property.
     * 
     * @param value
     *     allowed object is
     *     {@link DensityRangeType }
     *     
     */
    public void setOriginalGravity(DensityRangeType value) {
        this.originalGravity = value;
    }

    /**
     * Gets the value of the finalGravity property.
     * 
     * @return
     *     possible object is
     *     {@link DensityRangeType }
     *     
     */
    public DensityRangeType getFinalGravity() {
        return finalGravity;
    }

    /**
     * Sets the value of the finalGravity property.
     * 
     * @param value
     *     allowed object is
     *     {@link DensityRangeType }
     *     
     */
    public void setFinalGravity(DensityRangeType value) {
        this.finalGravity = value;
    }

    /**
     * Gets the value of the internationalBitternessUnits property.
     * 
     * @return
     *     possible object is
     *     {@link QuantitativeRangeType }
     *     
     */
    public QuantitativeRangeType getInternationalBitternessUnits() {
        return internationalBitternessUnits;
    }

    /**
     * Sets the value of the internationalBitternessUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantitativeRangeType }
     *     
     */
    public void setInternationalBitternessUnits(QuantitativeRangeType value) {
        this.internationalBitternessUnits = value;
    }

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link ColorRangeType }
     *     
     */
    public ColorRangeType getColor() {
        return color;
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link ColorRangeType }
     *     
     */
    public void setColor(ColorRangeType value) {
        this.color = value;
    }

    /**
     * Gets the value of the carbonation property.
     * 
     * @return
     *     possible object is
     *     {@link QuantitativeRangeType }
     *     
     */
    public QuantitativeRangeType getCarbonation() {
        return carbonation;
    }

    /**
     * Sets the value of the carbonation property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantitativeRangeType }
     *     
     */
    public void setCarbonation(QuantitativeRangeType value) {
        this.carbonation = value;
    }

    /**
     * Gets the value of the alcoholByVolume property.
     * 
     * @return
     *     possible object is
     *     {@link PercentRangeType }
     *     
     */
    public PercentRangeType getAlcoholByVolume() {
        return alcoholByVolume;
    }

    /**
     * Sets the value of the alcoholByVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link PercentRangeType }
     *     
     */
    public void setAlcoholByVolume(PercentRangeType value) {
        this.alcoholByVolume = value;
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
     * Gets the value of the profile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Sets the value of the profile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfile(String value) {
        this.profile = value;
    }

    /**
     * Gets the value of the ingredients property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIngredients() {
        return ingredients;
    }

    /**
     * Sets the value of the ingredients property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIngredients(String value) {
        this.ingredients = value;
    }

    /**
     * Gets the value of the examples property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExamples() {
        return examples;
    }

    /**
     * Sets the value of the examples property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExamples(String value) {
        this.examples = value;
    }

}
