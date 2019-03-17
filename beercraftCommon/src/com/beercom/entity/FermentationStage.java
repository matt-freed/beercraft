package com.beercom.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

/**
 * A single step in the fermentation process
 */
@Embeddable
public class FermentationStage{
	
	/**
	 * Time in days
	 */
	@Min(0)
	public Integer agingDays;
	
	/**
	 * Temperature at which fermentation is held for duration
	 */
	public Double temperatureF;

	public Integer getAgingDays() {
		return agingDays;
	}

	public void setAgingDays(Integer agingDays) {
		this.agingDays = agingDays;
	}

	public Double getTemperatureF() {
		return temperatureF;
	}

	public void setTemperatureF(Double temperatureF) {
		this.temperatureF = temperatureF;
	}


}
