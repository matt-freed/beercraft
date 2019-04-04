package com.beercraft.ejb.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The fermentation process, or a set of steps to follow
 */
@Entity
@Table(name="fermentation_process")
@AttributeOverrides({@AttributeOverride(name="id", column=@Column(name="fermentation_proc_id"))})
public class FermentationProcess extends CustomizableEntity{
	
	@Size(min=0, max=45)
	@Column(name="name", length=45)
	private String name;
	
	/**
	 * Primary fermentation (where most of attenuation occurs
	 */
	@Valid
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="agingDays", column=@Column(name="ferm_prim_aging_days")),
		@AttributeOverride(name="temperatureF", column=@Column(name="ferm_prim_temperature_f"))
	})
	private FermentationStage primary;
	
	/**
	 * Secondary fermentation (usually done to separate wort from trub)
	 */
	@Valid
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="agingDays", column=@Column(name="ferm_sec_aging_days")),
		@AttributeOverride(name="temperatureF", column=@Column(name="ferm_sec_temperature_f"))
	})
	private FermentationStage secondary;
	
	/**
	 * Tertiary fermentation (not as common)
	 */
	@Valid
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="agingDays", column=@Column(name="ferm_tert_aging_days")),
		@AttributeOverride(name="temperatureF", column=@Column(name="ferm_tert_temperature_f"))
	})
	private FermentationStage tertiary;
	
	/**
	 * Conditioning or aging the beer
	 */
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="agingDays", column=@Column(name="ferm_cond_aging_days")),
		@AttributeOverride(name="temperatureF", column=@Column(name="ferm_cond_temperature_f"))
	})
	private FermentationStage conditioning;

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof FermentationProcess)){
			return false;
		}
		if(obj == this){
			return true;
		}
		
		FermentationProcess other = (FermentationProcess) obj;
		
		return new EqualsBuilder().
			append(name, other.getName()).
			isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder(17, 31).
				append(name).toHashCode();
	}
	
	public FermentationStage getPrimary() {
		return primary;
	}

	public void setPrimary(FermentationStage primary) {
		this.primary = primary;
	}

	public FermentationStage getSecondary() {
		return secondary;
	}

	public void setSecondary(FermentationStage secondary) {
		this.secondary = secondary;
	}

	public FermentationStage getTertiary() {
		return tertiary;
	}

	public void setTertiary(FermentationStage tertiary) {
		this.tertiary = tertiary;
	}

	public FermentationStage getConditioning() {
		return conditioning;
	}

	public void setConditioning(FermentationStage conditioning) {
		this.conditioning = conditioning;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
