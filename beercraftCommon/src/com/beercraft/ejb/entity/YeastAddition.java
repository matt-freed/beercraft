package com.beercraft.ejb.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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


/**
 * The addition of yeast to a recipe
 */
@Entity
@Table(name="yeast_addition")
@AttributeOverrides({@AttributeOverride(name="id", column=@Column(name="yeast_addition_id"))})
public class YeastAddition extends CustomizableEntity{
	
	@NotNull @Valid
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="yeast_type", nullable=false)
	private Yeast yeast;
	
	@Size(min=0, max=45)
	@Column(name="amount", length=45)
	private String amount;
	
	@Min(0)
	@Column(name="weight")
	private Double weight;
	
	@Min(0)
	@Column(name="times_cultured")
	private Integer timesCultured;
	
	@Column(name="add_to_secondary")
	private Boolean addToSecondary;
	
	@JsonbTransient
	@ManyToOne(fetch = FetchType.LAZY)	//No need to load this if you are just loading the addition
	@JoinColumn(name="recipe_id", nullable=false)
	private Recipe recipe;
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof YeastAddition)){
			return false;
		}
		if(obj == this){
			return true;
		}
		
		YeastAddition other = (YeastAddition) obj;
		
		return new EqualsBuilder().
			append(yeast, other.getYeast()).
			append(amount, other.getAmount()).
			append(weight, other.getWeight()).
			append(timesCultured, other.getTimesCultured()).
			append(addToSecondary, other.getAddToSecondary()).
			append(recipe, other.getRecipe()).
			isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder(17, 31).
				append(yeast).
				append(amount).
				append(weight).
				append(timesCultured).
				append(addToSecondary).
				append(recipe).
				toHashCode();
	}
	public Yeast getYeast() {
		return yeast;
	}
	
	public void setYeast(Yeast yeast) {
		this.yeast = yeast;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public Double getWeight() {
		return weight;
	}
	
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public Integer getTimesCultured() {
		return timesCultured;
	}
	
	public void setTimesCultured(Integer timesCultured) {
		this.timesCultured = timesCultured;
	}
	
	public Boolean getAddToSecondary() {
		return addToSecondary;
	}
	
	public void setAddToSecondary(Boolean addToSecondary) {
		this.addToSecondary = addToSecondary;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
}
