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
 * A type of yeast
 * (Corresponds to yeast type in BeerXML)
 *
 */
@Entity
@Table(name="yeast")
public class Yeast extends CustomizableEntity {
	
	@NotNull @Size(min=0, max=150)
	@Column(name="name", length=150, nullable=false)
	private String name;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private YeastType type;
	
	@Column(name="form")
	@Enumerated(EnumType.STRING)
	private YeastForm form;
	
	@Size(min=0, max=45)
	@Column(name="laboratory", length=45)
	private String laboratory;
	
	@Size(min=0, max=45)
	@Column(name="product_id", length=45)
	private String productId;
	
	@Column(name="temperature_min_c")
	private Double temperatureMinC;
	
	@Column(name="temperature_max_c")
	private Double temperatureMaxC;
	
	@Size(min=0, max=45)
	@Column(name="flocculation", length=45)
	private String flocculation;
	
	@NotNull @Min(0) @Max(100) 
	@Column(name="attenuation", nullable=false)
	private Double attenuation;
	
	@Min(0) 
	@Column(name="alcohol_tol_min")
	private Double alcoholToleranceMin;
	
	@Min(0)
	@Column(name="alcohol_tol_max")
	private Double alcoholToleranceMax;
	
	@Size(min=0, max=2000)
	@Column(name="notes", length=2000)
	private String notes;
	
	@Size(min=0, max=2000)
	@Column(name="best_for", length=2000)
	private String bestFor;
	
	@Min(0)
	@Column(name="max_reuse")
	private Integer maxReuse;
	
	public enum YeastType{
		ALE("Ale"),
		LAGER("Lager"),
		WHEAT("Wheat"),
		WINE("Wine"),
		CHAMPAGNE("Champagne");
		
		private String description;
		
		private YeastType(String description){
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}
	
	public enum YeastForm{
		LIQUID("Liquid"),
		DRY("Dry"),
		SLANT("SLang"),
		CULTURE("Culture");
		
		private String description;
		
		private YeastForm(String description){
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Yeast)){
			return false;
		}
		if(obj == this){
			return true;
		}
		
		Yeast other = (Yeast) obj;
		
		return EqualsBuilder.reflectionEquals(this, other, getExcludedCompFields());
	}
	
	@Override
	public int hashCode(){
		return HashCodeBuilder.reflectionHashCode(this,getExcludedCompFields());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public YeastType getType() {
		return type;
	}

	public void setType(YeastType type) {
		this.type = type;
	}

	public YeastForm getForm() {
		return form;
	}

	public void setForm(YeastForm form) {
		this.form = form;
	}

	public String getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(String laboratory) {
		this.laboratory = laboratory;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getFlocculation() {
		return flocculation;
	}

	public void setFlocculation(String flocculation) {
		this.flocculation = flocculation;
	}

	public Double getAttenuation() {
		return attenuation;
	}

	public void setAttenuation(Double attenuation) {
		this.attenuation = attenuation;
	}

	public Double getAlcoholToleranceMin() {
		return alcoholToleranceMin;
	}

	public void setAlcoholToleranceMin(Double alcoholToleranceMin) {
		this.alcoholToleranceMin = alcoholToleranceMin;
	}

	public Double getAlcoholToleranceMax() {
		return alcoholToleranceMax;
	}

	public void setAlcoholToleranceMax(Double alcoholToleranceMax) {
		this.alcoholToleranceMax = alcoholToleranceMax;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getBestFor() {
		return bestFor;
	}

	public void setBestFor(String bestFor) {
		this.bestFor = bestFor;
	}

	public Integer getMaxReuse() {
		return maxReuse;
	}

	public void setMaxReuse(Integer maxReuse) {
		this.maxReuse = maxReuse;
	}

	public Double getTemperatureMinC() {
		return temperatureMinC;
	}

	public void setTemperatureMinC(Double temperatureMinC) {
		this.temperatureMinC = temperatureMinC;
	}

	public Double getTemperatureMaxC() {
		return temperatureMaxC;
	}

	public void setTemperatureMaxC(Double temperatureMaxC) {
		this.temperatureMaxC = temperatureMaxC;
	}


}
