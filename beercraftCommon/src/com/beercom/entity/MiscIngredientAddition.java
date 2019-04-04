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
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.beercom.entity.MiscIngredient.MiscUse;

/**
 * An addition of a miscellaneous ingredient to a recipe
 */
@Entity
@Table(name="misc_ingredient_addition")
@AttributeOverrides({@AttributeOverride(name="id", column=@Column(name="misc_ingredient_addition_id"))})
public class MiscIngredientAddition extends CustomizableEntity{
	
	@NotNull @Valid
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="misc_ingredient_id", nullable=false)
	private MiscIngredient miscIngredient;
	
	@Column(name="misc_use")
	@Enumerated(EnumType.STRING)
	private MiscUse miscUse;
	
	/**
	 * Unit must be described in string,
	 * as various measurements types could be used
	 */
	@Size(min=0, max=45)
	@Column(name="amount", length=45)
	private String amount;
	
	/**
	 * Time in boil if needed
	 */
	@Min(0)
	@Column(name="time")
	private Integer time;
	
	@JsonbTransient
	@ManyToOne(fetch = FetchType.LAZY)	//No need to load this if you are just loading the addition
	@JoinColumn(name="recipe_id")
	private Recipe recipe;

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof MiscIngredientAddition)){
			return false;
		}
		if(obj == this){
			return true;
		}
		
		MiscIngredientAddition other = (MiscIngredientAddition) obj;
		
		return new EqualsBuilder().
			append(amount, other.getAmount()).
			append(time, other.getTime()).
			append(miscIngredient, other.getMiscIngredient()).
			append(recipe, other.getRecipe()).
			isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder(17, 31).
				append(amount).
				append(time).
				append(miscIngredient).
				append(recipe).
				toHashCode();
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public MiscIngredient getMiscIngredient() {
		return miscIngredient;
	}

	public void setMiscIngredient(MiscIngredient miscIngredient) {
		this.miscIngredient = miscIngredient;
	}

	public MiscUse getMiscUse() {
		return miscUse;
	}

	public void setMiscUse(MiscUse miscUse) {
		this.miscUse = miscUse;
	}
}
