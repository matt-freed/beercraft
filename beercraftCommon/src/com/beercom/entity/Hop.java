package com.beercom.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


/**
 * Represents a type of hop (Fields align with Beerxml spec
 * for Hop Variety)
 *
 */
@Entity
@Table(name="hop")
public class Hop extends CustomizableEntity {
	
	@NotNull @Size(min=0, max=150)
	@Column(name="name", length=150, nullable=false)
	private String name;
	
	@Size(min=0, max=45)
	@Column(name="origin", length=45)
	private String origin;
	
	@Min(0) @Max(100) @NotNull
	@Column(name="alpha_acid", nullable=false)
	private Double alphaAcid;
	
	@Min(0) @Max(100) 
	@Column(name="beta_acid")
	private Double betaAcid;
	
	@Size(min=0, max=2000)
	@Column(name="notes", length=2000)
	private String notes;
	
	//TODO: add constraint to database
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private HopType type;
	
	@Min(0) @Max(100) 
	@Column(name="percentLost")
	private Double percentLost;
	
	@Size(min=0, max=500)
	@Column(name="substitutes", length=500)
	private String substitutes;
	
	@Min(0) @Max(100) 
	@Column(name="humulene")
	private Double humulene;
	
	@Min(0) @Max(100) 
	@Column(name="caryophyllene")
	private Double caryophyllene;
	
	@Min(0) @Max(100) 
	@Column(name="cohumulone")
	private Double cohumulone;
	
	@Min(0) @Max(100) 
	@Column(name="myrcene")
	private Double myrcene;
	
	@Min(0) @Max(100) 
	@Column(name="farnesene")
	private Double farnesene;

	/**
	 * A specific type of hop, or its purpose
	 */
	public enum HopType {
		
		AROMA("Aroma"),
		BITTERING("Bittering"),
		FLAVOR("Flavor"),
		BOTH("Aroma/Bittering"),
		BITTERINGFLAVOR("Bittering/Flavor"),
		AROMAFLAVOR("Aroma/Flavor"),
		AROMABITTERINGFLAVOR("Aroma/Bittering/Flavor"),
		NONE("None");

		private String description;
		
		private HopType(String description){
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}
	

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Hop)){
			return false;
		}
		if(obj == this){
			return true;
		}
		
		Hop other = (Hop) obj;
		
		return EqualsBuilder.reflectionEquals(this, other, getExcludedCompFields());
	}
	
	@Override
	public int hashCode(){
		return HashCodeBuilder.reflectionHashCode(this, getExcludedCompFields());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public HopType getType() {
		return type;
	}

	public void setType(HopType type) {
		this.type = type;
	}

	public Double getPercentLost() {
		return percentLost;
	}

	public void setPercentLost(Double percentLost) {
		this.percentLost = percentLost;
	}

	public Double getHumulene() {
		return humulene;
	}

	public void setHumulene(Double humulene) {
		this.humulene = humulene;
	}

	public Double getCaryophyllene() {
		return caryophyllene;
	}

	public void setCaryophyllene(Double caryophyllene) {
		this.caryophyllene = caryophyllene;
	}

	public Double getCohumulone() {
		return cohumulone;
	}

	public void setCohumulone(Double cohumulone) {
		this.cohumulone = cohumulone;
	}

	public Double getMyrcene() {
		return myrcene;
	}

	public void setMyrcene(Double myrcene) {
		this.myrcene = myrcene;
	}

	public Double getFarnesene() {
		return farnesene;
	}

	public void setFarnesene(Double farnesene) {
		this.farnesene = farnesene;
	}

	public String getSubstitutes() {
		return substitutes;
	}

	public void setSubstitutes(String substitutes) {
		this.substitutes = substitutes;
	}

	public Double getAlphaAcid() {
		return alphaAcid;
	}

	public void setAlphaAcid(Double alphaAcid) {
		this.alphaAcid = alphaAcid;
	}

	public Double getBetaAcid() {
		return betaAcid;
	}

	public void setBetaAcid(Double betaAcid) {
		this.betaAcid = betaAcid;
	}

}
