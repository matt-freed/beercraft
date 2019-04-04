package com.beercraft.ejb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Represents the mashing process for a recipe
 * consisting of several steps
 */
@Entity
@Table(name="mash_process")
@AttributeOverrides({@AttributeOverride(name="id", column=@Column(name="mash_proc_id"))})
public class MashProcess extends CustomizableEntity{
	
	@Size(min=0, max=45)
	@Column(name="name", length=45)
	private String name;
	
	@Min(0)
	@Column(name="grain_temp_f")
	private Double grainTemperatureF;
	
	@Min(0)
	@Column(name="sparge_temp_f")
	private Double spargeTemperatureF;
	
	@Min(0) @Max(14)
	@Column(name="ph")
	private Double ph;
	
	@Size(min=0, max=500)
	@Column(name="notes", length=500)
	private String notes;
	
	@Valid
	@OneToMany(mappedBy="mashProcess", cascade={CascadeType.ALL}, fetch=FetchType.EAGER, orphanRemoval=true)
	@Fetch(value=FetchMode.SUBSELECT)
	private List<MashStep> mashSteps = new ArrayList<MashStep>();

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof MashProcess)){
			return false;
		}
		if(obj == this){
			return true;
		}
		
		MashProcess other = (MashProcess) obj;
		
		return new EqualsBuilder().
			append(name, other.getName()).
			isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder(17, 31).
				append(name).toHashCode();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPh() {
		return ph;
	}

	public void setPh(Double ph) {
		this.ph = ph;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<MashStep> getMashSteps() {
		return mashSteps;
	}

	public void setMashSteps(List<MashStep> mashSteps) {
		this.mashSteps = mashSteps;
	}

	public Double getGrainTemperatureF() {
		return grainTemperatureF;
	}

	public void setGrainTemperatureF(Double grainTemperatureF) {
		this.grainTemperatureF = grainTemperatureF;
	}

	public Double getSpargeTemperatureF() {
		return spargeTemperatureF;
	}

	public void setSpargeTemperatureF(Double spargeTemperatureF) {
		this.spargeTemperatureF = spargeTemperatureF;
	}
}
