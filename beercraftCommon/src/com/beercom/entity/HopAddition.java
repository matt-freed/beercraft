package com.beercom.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.beercom.entity.unit.WeightUnit;

/**
 * The addition of a hop to a recipe for a specific
 * hop type
 */
@Entity
@Table(name="hop_addition")
@AttributeOverrides({@AttributeOverride(name="id", column=@Column(name="hop_addition_id"))})
public class HopAddition extends CustomizableEntity{

	@NotNull @Valid
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="hop_type", nullable=false)
	private Hop hop;
	
	@Column(name="hop_form")
	@Enumerated(EnumType.STRING)
	private HopForm form;
	
	@Column(name="hop_use")
	@Enumerated(EnumType.STRING)
	private HopUse use;

	@Min(0)
	@Column(name="weight_oz")
	private Double weightOz;
	
	@Column(name="weight_display_unit")
	@Enumerated(EnumType.STRING)
	private WeightUnit weightDisplayUnit;
	
	@Min(0)
	@Column(name="boil_time_min")
	private Integer boilTimeMin;
	
	@Min(0)
	@Column(name="ibu_contribution")
	private Double ibuContribution;
	
	@JsonbTransient
	@ManyToOne(fetch = FetchType.LAZY)	//No need to load this if you are just loading the addition
	@JoinColumn(name="recipe_id", nullable=false)
	private Recipe recipe;

	/**
	 * The form that the hop will be added in the recipe
	 */
	public enum HopForm{
		LEAF("Leaf"),
		PELLET("Pellet"),
		PLUG("Plug");
		
		private String description;
		
		private HopForm(String description){
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}
	
	/**
	 * How the hop will be used (eg, at which stage)
	 */
	public enum HopUse{
		BOIL("Boil"),
		DRYHOP("Dry Hop"),
		FIRSTWORT("First Wort"),
		HOPBACK("Hop Back"),
		MASH("Mash"),
		CONTINUOUSBOIL("Continuous Boil Addition");
		
		private String description;
		
		private HopUse(String description){
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof HopAddition)){
			return false;
		}
		if(obj == this){
			return true;
		}
		
		HopAddition other = (HopAddition) obj;
		
		return new EqualsBuilder().
			append(hop, other.getHop()).
			append(form, other.getForm()).
			append(use, other.getUse()).
			append(weightOz, other.getWeightOz()).
			append(boilTimeMin, other.getBoilTimeMin()).
			append(recipe, other.getRecipe()).
			isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder(17, 31).
				append(hop).
				append(form).
				append(use).
				append(weightOz).
				append(boilTimeMin).
				append(recipe).
				toHashCode();
	}

	public Hop getHop() {
		return hop;
	}

	public void setHop(Hop hop) {
		this.hop = hop;
	}

	public HopForm getForm() {
		return form;
	}

	public void setForm(HopForm form) {
		this.form = form;
	}

	public HopUse getUse() {
		return use;
	}

	public void setUse(HopUse use) {
		this.use = use;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Double getWeightOz() {
		return weightOz;
	}

	public void setWeightOz(Double weightOz) {
		this.weightOz = weightOz;
	}

	public WeightUnit getWeightDisplayUnit() {
		return weightDisplayUnit;
	}

	public void setWeightDisplayUnit(WeightUnit weightDisplayUnit) {
		this.weightDisplayUnit = weightDisplayUnit;
	}

	public Integer getBoilTimeMin() {
		return boilTimeMin;
	}

	public void setBoilTimeMin(Integer boilTimeMin) {
		this.boilTimeMin = boilTimeMin;
	}

	public Double getIbuContribution() {
		return ibuContribution;
	}

	public void setIbuContribution(Double ibuContribution) {
		this.ibuContribution = ibuContribution;
	}
}
