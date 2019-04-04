package com.beercraft.ejb.entity;

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
import org.hibernate.annotations.Type;

/**
 * A type of fermentable
 * (Corresponds to BeerXML fermentableType
 *
 */
@Entity
@Table(name="fermentable")
public class Fermentable extends CustomizableEntity {
	
	@NotNull @Size(min=0, max=150)
	@Column(name="name", length=150, nullable=false)
	private String name;
	
	@NotNull
	@Column(name="type", length=45, nullable=false)
	@Enumerated(EnumType.STRING)
	private FermentableType type;
	
	/**
	 * In SRM's
	 */
	@Min(0)
	@Column(name="color")
	private Double color;
	
	@Size(min=0, max=45)
	@Column(name="origin", length=45)
	private String origin;
	
	@Size(min=0, max=45)
	@Column(name="supplier", length=45)
	private String supplier;
	
	@Min(0)
	@Column(name="fine_grind")
	private Double fineGrind;
	
	@Min(0) @Max(100)
	@Column(name="coarse_grind")
	private Double coarseGrind;
	
	@Min(0)
	@Column(name="fine_course_diff")
	private Double fineCourseDiff;
	
	@Size(min=0, max=2000)
	@Column(name="notes", length=2000)
	private String notes;
	
	@Min(0) @Max(100)
	@Column(name="moisture")
	private Double moisture;
	
	@Min(0)
	@Column(name="diastic_power")
	private Double diasticPower;
	
	@Min(0)
	@Column(name="protein")
	private Double protein;
	
	@Min(0)
	@Column(name="soluble_nitrogen_ratio")
	private Double solubleNitrogenRatio;
	
	@Min(0) @Max(100) 
	@Column(name="max_in_batch")
	private Double maxInBatch;
	
	@Column(name="recommend_mash")
	@Type(type="yes_no")
	private Boolean recommendMash;
	
	@Min(0) @Max(100) 
	@Column(name="yield")
	private Double yield;
	
	@Min(0)
	@Column(name="ppg")
	private Double ppg;
	
	public enum FermentableType {
		ADJUNCT("Adjunct"),
		DRYEXTRACT("Dry Extract"),
		EXTRACT("Extract"),
		GRAIN("Grain"),
		SUGAR("Sugar"),
		OTHER("Other");
		
		private String description;
		
		private FermentableType(String description){
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Fermentable)){
			return false;
		}
		if(obj == this){
			return true;
		}
		
		Fermentable other = (Fermentable) obj;
		
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

	public FermentableType getType() {
		return type;
	}

	public void setType(FermentableType type) {
		this.type = type;
	}

	public Double getColor() {
		return color;
	}

	public void setColor(Double color) {
		this.color = color;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Double getFineGrind() {
		return fineGrind;
	}

	public void setFineGrind(Double fineGrind) {
		this.fineGrind = fineGrind;
	}

	public Double getCoarseGrind() {
		return coarseGrind;
	}

	public void setCoarseGrind(Double coarseGrind) {
		this.coarseGrind = coarseGrind;
	}

	public Double getFineCourseDiff() {
		return fineCourseDiff;
	}

	public void setFineCourseDiff(Double fineCourseDiff) {
		this.fineCourseDiff = fineCourseDiff;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Double getMoisture() {
		return moisture;
	}

	public void setMoisture(Double moisture) {
		this.moisture = moisture;
	}

	public Double getDiasticPower() {
		return diasticPower;
	}

	public void setDiasticPower(Double diasticPower) {
		this.diasticPower = diasticPower;
	}

	public Double getProtein() {
		return protein;
	}

	public void setProtein(Double protein) {
		this.protein = protein;
	}

	public Double getSolubleNitrogenRatio() {
		return solubleNitrogenRatio;
	}

	public void setSolubleNitrogenRatio(Double solubleNitrogenRatio) {
		this.solubleNitrogenRatio = solubleNitrogenRatio;
	}

	public Double getMaxInBatch() {
		return maxInBatch;
	}

	public void setMaxInBatch(Double maxInBatch) {
		this.maxInBatch = maxInBatch;
	}

	public Boolean getRecommendMash() {
		return recommendMash;
	}

	public void setRecommendMash(Boolean recommendMash) {
		this.recommendMash = recommendMash;
	}

	public Double getYield() {
		return yield;
	}

	public void setYield(Double yield) {
		this.yield = yield;
	}

	public Double getPpg() {
		return ppg;
	}

	public void setPpg(Double ppg) {
		this.ppg = ppg;
	}
}
