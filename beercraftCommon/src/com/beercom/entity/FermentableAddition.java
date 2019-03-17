package com.beercom.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.beercom.entity.unit.WeightUnit;

/**
 * The addition of a fermentable to a recipe for a specific 
 * fermentable type
 */
@Entity
@Table(name="fermentable_addition")
@AttributeOverrides({@AttributeOverride(name="id", column=@Column(name="fermentable_addition_id"))})
public class FermentableAddition extends CustomizableEntity{

	@Valid @NotNull
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="fermentable_type", nullable=false)
	private Fermentable fermentable;
	
	@Min(0) @NotNull
	@Column(name="weight_lbs")
	private Double weightLbs;
	
	//This is the unit the user wants to display, we will still store in db as lbs
	@Column(name="weight_display_unit")
	@Enumerated(EnumType.STRING)
	private WeightUnit weightDisplayUnit;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="recipe_id", nullable=false)
	private Recipe recipe;

	@NotNull
	@Column(name="fermentable_use")
	@Enumerated(EnumType.STRING)
	private FermentableUse use;
	
	public enum FermentableUse {
		MASH("Mash"),
		BOIL("Boil"),
		LATEBOIL("Late Boil"),
		STEEP("Steep");
		
		private String description;
		
		private FermentableUse(String description){
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof FermentableAddition)){
			return false;
		}
		if(obj == this){
			return true;
		}
		
		FermentableAddition other = (FermentableAddition) obj;
		
		return new EqualsBuilder().
			append(fermentable, other.getFermentable()).
			append(weightLbs, other.getMass()).
			append(use, other.getUse()).
			append(recipe, other.getRecipe()).
			isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder(17, 31).
				append(fermentable).
				append(weightLbs).
				append(use).
				append(recipe).
				toHashCode();
	}
	public Fermentable getFermentable() {
		return fermentable;
	}
	
	public void setFermentable(Fermentable fermentable) {
		this.fermentable = fermentable;
	}

	public Double getMass() {
		return weightLbs;
	}

	public void setMass(Double mass) {
		this.weightLbs = mass;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Double getWeightLbs() {
		return weightLbs;
	}

	public void setWeightLbs(Double weightLbs) {
		this.weightLbs = weightLbs;
	}

	public WeightUnit getWeightDisplayUnit() {
		return weightDisplayUnit;
	}

	public void setWeightDisplayUnit(WeightUnit weightDisplayUnit) {
		this.weightDisplayUnit = weightDisplayUnit;
	}

	public FermentableUse getUse() {
		return use;
	}

	public void setUse(FermentableUse use) {
		this.use = use;
	}

}
