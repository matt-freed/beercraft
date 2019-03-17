package com.beercom.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A single step in the mashing process
 */
@Entity
@Table(name="mash_step")
@AttributeOverrides({@AttributeOverride(name="id", column=@Column(name="mash_step_id"))})
public class MashStep extends CustomizableEntity{
	
	@NotNull @Min(0)
	@Column(name="step_number", nullable=false)
	private Integer stepNumber;
	
	@Size(min=0, max=45)
	@Column(name="name", length=45)
	private String name;
	
	@NotNull
	@Column(name="type", length=45)
	@Enumerated(EnumType.STRING)
	private MashStepType type;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="mash_process_id", nullable=false)
	private MashProcess mashProcess;
	
	/**
	 * Volume in gallons
	 */
	@Min(0)
	@Column(name="infuse_amount_gal")
	private Double infuseAmountGal;
	
	@Min(0)
	@Column(name="temperature_f")
	private Double temperatureF;
	
	@Min(0)
	@Column(name="time_min")
	private Integer timeMin;
	
	@Min(0)
	@Column(name="ramp_time_min")
	private Integer rampTimeMin;
	
	@Min(0)
	@Column(name="end_temperature_F")
	private Double endTemperatureF;
	
	@Size(min=0, max=500)
	@Column(name="description", length=500)
	private String description;
	
	@Min(0)
	@Column(name="water_grain_ratio")
	private Double waterGrainRatio;
	
	/**
	 * Volume in gallons
	 */
	@Min(0)
	@Column(name="decoction_amount_gal")
	private Double decoctionAmountGal;
	
	@Min(0)
	@Column(name="infuse_temperature_f")
	private Double infuseTemperatureF;
	
	public enum MashStepType {
		INFUSION("Infusion"),
		DECOCTION("Decoction"),
		TEMPERATURE("Temperature"),
		SPARGE("Sparge");
		
		private String label;
		
		private MashStepType(String label){
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

	}

	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof MashStep)){
			return false;
		}
		if(obj == this){
			return true;
		}
		
		MashStep other = (MashStep) obj;
		
		return new EqualsBuilder().
			append(name, other.getName()).
			append(mashProcess, other.getMashProcess()).
			isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder(17, 31).
			append(name).
			append(mashProcess).
			toHashCode();
	}

	public Integer getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(Integer stepNumber) {
		this.stepNumber = stepNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getWaterGrainRatio() {
		return waterGrainRatio;
	}

	public void setWaterGrainRatio(Double waterGrainRatio) {
		this.waterGrainRatio = waterGrainRatio;
	}

	public MashProcess getMashProcess() {
		return mashProcess;
	}

	public void setMashProcess(MashProcess mashProcess) {
		this.mashProcess = mashProcess;
	}

	public MashStepType getType() {
		return type;
	}

	public void setType(MashStepType type) {
		this.type = type;
	}

	public Double getInfuseAmountGal() {
		return infuseAmountGal;
	}

	public void setInfuseAmountGal(Double infuseAmountGal) {
		this.infuseAmountGal = infuseAmountGal;
	}

	public Double getTemperatureF() {
		return temperatureF;
	}

	public void setTemperatureF(Double temperatureF) {
		this.temperatureF = temperatureF;
	}

	public Integer getTimeMin() {
		return timeMin;
	}

	public void setTimeMin(Integer timeMin) {
		this.timeMin = timeMin;
	}

	public Integer getRampTimeMin() {
		return rampTimeMin;
	}

	public void setRampTimeMin(Integer rampTimeMin) {
		this.rampTimeMin = rampTimeMin;
	}

	public Double getEndTemperatureF() {
		return endTemperatureF;
	}

	public void setEndTemperatureF(Double endTemperatureF) {
		this.endTemperatureF = endTemperatureF;
	}

	public Double getDecoctionAmountGal() {
		return decoctionAmountGal;
	}

	public void setDecoctionAmountGal(Double decoctionAmountGal) {
		this.decoctionAmountGal = decoctionAmountGal;
	}

	public Double getInfuseTemperatureF() {
		return infuseTemperatureF;
	}

	public void setInfuseTemperatureF(Double infuseTemperatureF) {
		this.infuseTemperatureF = infuseTemperatureF;
	}
}
