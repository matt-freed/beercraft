package com.beercom.entity;

import java.util.Date;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="batch")
public class Batch extends CustomizableEntity{

	@Immutable
	@OneToOne
	@JoinColumn(name="recipe_id")
	@JsonbTransient
	private Recipe recipe;
	
	@Column(name="brew_date")
	@Temporal(TemporalType.DATE)
	private Date brewDate;
	
	@Column(name="secondary_date")
	@Temporal(TemporalType.DATE)
	private Date toSecondaryDate;
	
	@Column(name="tertiary_date")
	@Temporal(TemporalType.DATE)
	private Date toTertiaryDate;
	
	@Column(name="bottlekeg_date")
	@Temporal(TemporalType.DATE)
	private Date toBottleKegDate;
	
	@Column(name="ready_date")
	@Temporal(TemporalType.DATE)
	private Date readyDate;
	
	@Min(0)
	@Column(name="cost")
	private Double cost;
	
	@Min(0)
	@Column(name="actual_og")
	private Double actualOg;
	
	@Min(0)
	@Column(name="actual_fg")
	private Double actualFg;
	
	@Min(0)
	@Column(name="actual_abv")
	private Double actualAbv;
	
	@Min(0)
	@Column(name="actual_ibu")
	private Double actualIbu;
	
	@Min(0)
	@Column(name="actual_pre_boil_vol_gal")
	private Double actualPreBoilVolGal;
	
	@Min(0)
	@Column(name="actual_pre_boil_sg")
	private Double actualPreBoilSg;
	
	@Min(0)
	@Column(name="actual_vol_gal")
	private Double actualVolGal;
	
	@Min(0) @Max(100)
	@Column(name="actual_mash_efficiency")
	private Double actualMashEfficiency;
	
	@Min(0)
	@Column(name="actual_efficiency")
	private Double actualEfficiency;
	
	@Min(0)
	@Column(name="actual_attenuation")
	private Double actualAttenuation;
	
	@Min(0)
	@Column(name="og_error")
	private Double ogError;
	
	@Min(0)
	@Column(name="fg_error")
	private Double fgError;
	
	@Min(0)
	@Column(name="ibu_error")
	private Double ibuError;
	
	@Min(0)
	@Column(name="abv_error")
	private Double abvError;
	
	@Column(name="process_notes")
	@Size(min=0, max=2000)
	private String processNotes;

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Date getBrewDate() {
		return brewDate;
	}

	public void setBrewDate(Date brewDate) {
		this.brewDate = brewDate;
	}

	public Date getReadyDate() {
		return readyDate;
	}

	public void setReadyDate(Date readyDate) {
		this.readyDate = readyDate;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getActualOg() {
		return actualOg;
	}

	public void setActualOg(Double actualOg) {
		this.actualOg = actualOg;
	}

	public Double getActualFg() {
		return actualFg;
	}

	public void setActualFg(Double actualFg) {
		this.actualFg = actualFg;
	}

	public Double getActualAbv() {
		return actualAbv;
	}

	public void setActualAbv(Double actualAbv) {
		this.actualAbv = actualAbv;
	}

	public Double getActualPreBoilVolGal() {
		return actualPreBoilVolGal;
	}

	public void setActualPreBoilVolGal(Double actualPreBoilVolGal) {
		this.actualPreBoilVolGal = actualPreBoilVolGal;
	}

	public Double getActualPreBoilSg() {
		return actualPreBoilSg;
	}

	public void setActualPreBoilSg(Double actualPreBoilSg) {
		this.actualPreBoilSg = actualPreBoilSg;
	}

	public Double getActualMashEfficiency() {
		return actualMashEfficiency;
	}

	public void setActualMashEfficiency(Double actualMashEfficiency) {
		this.actualMashEfficiency = actualMashEfficiency;
	}

	public Double getActualEfficiency() {
		return actualEfficiency;
	}

	public void setActualEfficiency(Double actualEfficiency) {
		this.actualEfficiency = actualEfficiency;
	}

	public String getProcessNotes() {
		return processNotes;
	}

	public void setProcessNotes(String processNotes) {
		this.processNotes = processNotes;
	}

	public Double getActualIbu() {
		return actualIbu;
	}

	public void setActualIbu(Double actualIbu) {
		this.actualIbu = actualIbu;
	}

	public Double getOgError() {
		return ogError;
	}

	public void setOgError(Double ogError) {
		this.ogError = ogError;
	}

	public Double getFgError() {
		return fgError;
	}

	public void setFgError(Double fgError) {
		this.fgError = fgError;
	}

	public Double getIbuError() {
		return ibuError;
	}

	public void setIbuError(Double ibuError) {
		this.ibuError = ibuError;
	}

	public Double getAbvError() {
		return abvError;
	}

	public void setAbvError(Double abvError) {
		this.abvError = abvError;
	}

	public Double getActualAttenuation() {
		return actualAttenuation;
	}

	public void setActualAttenuation(Double actualAttenuation) {
		this.actualAttenuation = actualAttenuation;
	}

	public Date getToSecondaryDate() {
		return toSecondaryDate;
	}

	public void setToSecondaryDate(Date toSecondaryDate) {
		this.toSecondaryDate = toSecondaryDate;
	}

	public Date getToTertiaryDate() {
		return toTertiaryDate;
	}

	public void setToTertiaryDate(Date toTertiaryDate) {
		this.toTertiaryDate = toTertiaryDate;
	}

	public Date getToBottleKegDate() {
		return toBottleKegDate;
	}

	public void setToBottleKegDate(Date toBottleKegDate) {
		this.toBottleKegDate = toBottleKegDate;
	}

	public Double getActualVolGal() {
		return actualVolGal;
	}

	public void setActualVolGal(Double actualVolGal) {
		this.actualVolGal = actualVolGal;
	}

}
