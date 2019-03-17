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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Hop Variety Record Information
 * 
 * <p>Java class for VarietyInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VarietyInformation">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:beerxml:hop:v2}HopVarietyBase">
 *       &lt;sequence>
 *         &lt;element name="type" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="aroma"/>
 *               &lt;enumeration value="bittering"/>
 *               &lt;enumeration value="flavor"/>
 *               &lt;enumeration value="aroma/bittering"/>
 *               &lt;enumeration value="bittering/flavor"/>
 *               &lt;enumeration value="aroma/flavor"/>
 *               &lt;enumeration value="aroma/bittering/flavor"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="notes" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="percent_lost" type="{urn:beerxml:unit:v2}PercentType" minOccurs="0"/>
 *         &lt;element name="substitutes" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="humulene" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="caryophyllene" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cohumulone" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="myrcene" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="farnesene" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="inventory" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="leaf" type="{urn:beerxml:unit:v2}MassType" minOccurs="0"/>
 *                   &lt;element name="pellet" type="{urn:beerxml:unit:v2}MassType" minOccurs="0"/>
 *                   &lt;element name="plug" type="{urn:beerxml:unit:v2}MassType" minOccurs="0"/>
 *                 &lt;/sequence>
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
@XmlType(name = "VarietyInformation", namespace = "urn:beerxml:hop:v2", propOrder = {
    "type",
    "notes",
    "percentLost",
    "substitutes",
    "humulene",
    "caryophyllene",
    "cohumulone",
    "myrcene",
    "farnesene",
    "inventory"
})
public class VarietyInformation
    extends HopVarietyBase
{

    protected String type;
    protected String notes;
    @XmlElement(name = "percent_lost")
    protected BigDecimal percentLost;
    protected String substitutes;
    protected BigDecimal humulene;
    protected BigDecimal caryophyllene;
    protected BigDecimal cohumulone;
    protected BigDecimal myrcene;
    protected BigDecimal farnesene;
    protected VarietyInformation.Inventory inventory;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
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
     * Gets the value of the percentLost property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPercentLost() {
        return percentLost;
    }

    /**
     * Sets the value of the percentLost property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPercentLost(BigDecimal value) {
        this.percentLost = value;
    }

    /**
     * Gets the value of the substitutes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubstitutes() {
        return substitutes;
    }

    /**
     * Sets the value of the substitutes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubstitutes(String value) {
        this.substitutes = value;
    }

    /**
     * Gets the value of the humulene property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHumulene() {
        return humulene;
    }

    /**
     * Sets the value of the humulene property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHumulene(BigDecimal value) {
        this.humulene = value;
    }

    /**
     * Gets the value of the caryophyllene property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCaryophyllene() {
        return caryophyllene;
    }

    /**
     * Sets the value of the caryophyllene property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCaryophyllene(BigDecimal value) {
        this.caryophyllene = value;
    }

    /**
     * Gets the value of the cohumulone property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCohumulone() {
        return cohumulone;
    }

    /**
     * Sets the value of the cohumulone property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCohumulone(BigDecimal value) {
        this.cohumulone = value;
    }

    /**
     * Gets the value of the myrcene property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMyrcene() {
        return myrcene;
    }

    /**
     * Sets the value of the myrcene property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMyrcene(BigDecimal value) {
        this.myrcene = value;
    }

    /**
     * Gets the value of the farnesene property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFarnesene() {
        return farnesene;
    }

    /**
     * Sets the value of the farnesene property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFarnesene(BigDecimal value) {
        this.farnesene = value;
    }

    /**
     * Gets the value of the inventory property.
     * 
     * @return
     *     possible object is
     *     {@link VarietyInformation.Inventory }
     *     
     */
    public VarietyInformation.Inventory getInventory() {
        return inventory;
    }

    /**
     * Sets the value of the inventory property.
     * 
     * @param value
     *     allowed object is
     *     {@link VarietyInformation.Inventory }
     *     
     */
    public void setInventory(VarietyInformation.Inventory value) {
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
     *       &lt;sequence>
     *         &lt;element name="leaf" type="{urn:beerxml:unit:v2}MassType" minOccurs="0"/>
     *         &lt;element name="pellet" type="{urn:beerxml:unit:v2}MassType" minOccurs="0"/>
     *         &lt;element name="plug" type="{urn:beerxml:unit:v2}MassType" minOccurs="0"/>
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
        "leaf",
        "pellet",
        "plug"
    })
    public static class Inventory {

        @XmlElement(namespace = "urn:beerxml:hop:v2")
        protected MassType leaf;
        @XmlElement(namespace = "urn:beerxml:hop:v2")
        protected MassType pellet;
        @XmlElement(namespace = "urn:beerxml:hop:v2")
        protected MassType plug;

        /**
         * Gets the value of the leaf property.
         * 
         * @return
         *     possible object is
         *     {@link MassType }
         *     
         */
        public MassType getLeaf() {
            return leaf;
        }

        /**
         * Sets the value of the leaf property.
         * 
         * @param value
         *     allowed object is
         *     {@link MassType }
         *     
         */
        public void setLeaf(MassType value) {
            this.leaf = value;
        }

        /**
         * Gets the value of the pellet property.
         * 
         * @return
         *     possible object is
         *     {@link MassType }
         *     
         */
        public MassType getPellet() {
            return pellet;
        }

        /**
         * Sets the value of the pellet property.
         * 
         * @param value
         *     allowed object is
         *     {@link MassType }
         *     
         */
        public void setPellet(MassType value) {
            this.pellet = value;
        }

        /**
         * Gets the value of the plug property.
         * 
         * @return
         *     possible object is
         *     {@link MassType }
         *     
         */
        public MassType getPlug() {
            return plug;
        }

        /**
         * Sets the value of the plug property.
         * 
         * @param value
         *     allowed object is
         *     {@link MassType }
         *     
         */
        public void setPlug(MassType value) {
            this.plug = value;
        }

    }

}
