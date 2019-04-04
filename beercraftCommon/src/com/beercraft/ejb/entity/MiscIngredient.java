package com.beercraft.ejb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Miscellaneous ingredient type or adjunct to add to a recipe
 * that does not fit into other categories
 */
@Entity
@Table(name="misc_ingredient")
public class MiscIngredient extends CustomizableEntity{
	
	@NotNull @Size(min=0, max=150)
	@Column(name="name", nullable=false, length=150)
	private String name;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private MiscType type;
	
	@Column(name="ingredient_use")
	@Enumerated(EnumType.STRING)
	private MiscUse use;
	
	@Size(min=0, max=150)
	@Column(name="use_for", length=150)
	private String useFor;
	
	@Size(min=0, max=2000)
	@Column(name="notes", length=2000)
	private String notes;
	
	public enum MiscType{
		SPICE("Spice"),
		FINING("Fining"),
		WATERAGENT("Water Agent"),
		HERB("Herb"),
		FRUIT("Fruit"),
		FLAVOR("Flavor"),
		OTHER("OTHER");
		
		private String description;
		
		private MiscType(String description){
			this.description = description;
		}
		
		public String getDescription(){
			return description;
		}
	}
	
	public enum MiscUse{
		BOIL("Boil"),
		MASH("Mash"),
		PRIMARY("Primary"),
		SECONDARY("Secondary"),
		BOTTLING("Bottling");
		
		private String description;
		
		private MiscUse(String description){
			this.description = description;
		}
		
		public String getDescription(){
			return description;
		}
	}

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof MiscIngredient)){
			return false;
		}
		if(obj == this){
			return true;
		}
		
		MiscIngredient other = (MiscIngredient) obj;
		
		return EqualsBuilder.reflectionEquals(this, other, getExcludedCompFields());
		
	}
	
	@Override
	public int hashCode(){
		return HashCodeBuilder.reflectionHashCode(this, getExcludedCompFields());
	}

	public MiscType getType() {
		return type;
	}

	public void setType(MiscType type) {
		this.type = type;
	}

	public MiscUse getUse() {
		return use;
	}

	public void setUse(MiscUse use) {
		this.use = use;
	}

	public String getUseFor() {
		return useFor;
	}

	public void setUseFor(String useFor) {
		this.useFor = useFor;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
