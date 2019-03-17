package com.beercom.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;

import com.beercom.entity.unit.VolumeUnit;

@Entity
@Table(name="recipe")
@AttributeOverrides({@AttributeOverride(name="id", column=@Column(name="recipe_id"))})
public class Recipe extends CustomizableEntity{
	
	@NotNull @Size(min=0, max=45)
	@Column(name="name", length=45, nullable=false)
	private String name;
	
	@Column(name="entered_date")
	@Temporal(TemporalType.DATE)
	private Date enteredDate;
	
	@NotNull
	@Column(name="type", nullable=false)
	@Enumerated(EnumType.STRING)
	private RecipeType type;
	
	@Column(name="author", length=150)
	private String author;
	
	@NotNull @Min(0)
	@Column(name="batch_size_gal", nullable=false)
	private Double batchSizeGallons;
	
	@Min(0)
	@Column(name="boil_time_min")
	private Double boilMinutes;
	
	@Min(0)
	@Column(name="boil_size_gal")
	private Double preBoilVolGal;
	
	@Min(0)
	@Column(name="boil_loss_gph")
	private Double boilLossGPH;
	
	@Min(0)
	@Column(name="trub_loss_g")
	private Double trubLossGal;
	
	@Min(0)
	@Column(name="pre_boil_sg")
	private Double preBoilSG;

	//This is the unit the user wants to display, we will still store in db as lbs
	@Column(name="volume_display_unit")
	@Enumerated(EnumType.STRING)
	private VolumeUnit volumeDisplayUnit;
	
	@NotNull @Min(0) @Max(100) 
	@Column(name="efficiency")
	private Double efficiency;
	
	//Since these are shared styles, we don't want to persist any changes (also no cascading)
	@Immutable
	@ManyToOne
	//Specify join column because this entity owns relationship (has FK column)
	@JoinColumn(name="style_type")
	private Style styleType;
	
	//Cascade deletes. orphanRemoval will delete child when parent no longer has reference to child
	@Valid
	@OneToMany(mappedBy="recipe", cascade={CascadeType.ALL}, fetch=FetchType.EAGER, orphanRemoval=true)
	@Fetch(value=FetchMode.SUBSELECT)//This is needed so hibernate can fetch multiple bags
	private List<FermentableAddition> fermentableAdditions = new ArrayList<FermentableAddition>();
	
	@Valid
	@OneToMany(mappedBy="recipe", cascade={CascadeType.ALL}, fetch=FetchType.EAGER, orphanRemoval=true)
	@Fetch(value=FetchMode.SUBSELECT)
	private List<HopAddition> hopAdditions = new ArrayList<HopAddition>();
	
	@Valid
	@OneToMany(mappedBy="recipe", cascade={CascadeType.ALL}, fetch=FetchType.EAGER, orphanRemoval=true)
	@Fetch(value=FetchMode.SUBSELECT)
	private List<YeastAddition> yeastAdditions = new ArrayList<YeastAddition>();
	
	@Valid
	@OneToMany(mappedBy="recipe", cascade={CascadeType.ALL}, fetch=FetchType.EAGER, orphanRemoval=true)
	@Fetch(value=FetchMode.SUBSELECT)
	private List<MiscIngredientAddition> miscAdditions = new ArrayList<MiscIngredientAddition>();
	
	@Valid
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="mash_proc_id")
	private MashProcess mashProcess;
	
	//This cascade is needed so a save will save fermentation first
	@Valid
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="fermentation_proc_id")
	private FermentationProcess fermentation;
	
	//OneToOne mapped by recipe column in batch
	@Valid
	@OneToOne(mappedBy="recipe", cascade={CascadeType.ALL})
	private Batch batch;
	
	@Min(0)
	@Column(name="grain_bill_lbs")
	private Double grainBillLbs;
	
	@Min(0)
	@Column(name="max_potential")
	private Double maxPotential;
	
	@Min(0)
	@Column(name="hop_bill_oz")
	private Double hopBillOz;
	
	@Min(0)
	@Column(name="original_gravity")
	private Double originalGravity;
	
	@Min(0)
	@Column(name="final_gravity")
	private Double finalGravity;
	
	@Min(0)
	@Column(name="ibu_fg_ratio")
	private Double ibuFgRatio;
	
	@Min(0) @Max(100)
	@Column(name="attenuation")
	private Double attenuation;
	
	@Min(0)
	@Column(name="abv")
	private Double abv;
	
	@Min(0)
	@Column(name="ibu")
	private Double ibu;
	
	@Min(0)
	@Column(name="color")
	private Double color;
	
	@Min(0)
	@Column(name="carbonation")
	private Double carbonation;
	
	@Size(min=0, max=2000)
	@Column(name="notes", length=2000)
	private String notes;
	
	@Size(min=0, max=2000)
	@Column(name="tasting_notes", length=2000)
	private String tastingNotes;
	
	//TODO: Change this in DB
	@Min(0) @Max(10)
	@Column(name="rating")
	private Integer rating;
	
	@Min(0)
	@Column(name="calories")
	private Integer calories;
	
	public enum RecipeType {
		EXTRACT("Extract"),
		ALLGRAIN("All Grain"),
		PARTIALMASH("Partial Mash");
		
		private String description;
		
		private RecipeType(String description){
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Recipe)){
			return false;
		}
		if(obj == this){
			return true;
		}
		
		Recipe other = (Recipe) obj;
		
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

	public RecipeType getType() {
		return type;
	}

	public void setType(RecipeType type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(Double efficiency) {
		this.efficiency = efficiency;
	}

	public Style getStyleType() {
		return styleType;
	}

	public void setStyleType(Style styleType) {
		this.styleType = styleType;
	}

	public MashProcess getMashProcess() {
		return mashProcess;
	}

	public void setMashProcess(MashProcess mashProcess) {
		this.mashProcess = mashProcess;
	}

	public FermentationProcess getFermentation() {
		return fermentation;
	}

	public void setFermentation(FermentationProcess fermentation) {
		this.fermentation = fermentation;
	}

	public Double getOriginalGravity() {
		return originalGravity;
	}

	public void setOriginalGravity(Double originalGravity) {
		this.originalGravity = originalGravity;
	}

	public Double getGrainBillLbs() {
		return grainBillLbs;
	}

	public void setGrainBillLbs(Double grainBillLbs) {
		this.grainBillLbs = grainBillLbs;
	}

	public Double getFinalGravity() {
		return finalGravity;
	}

	public void setFinalGravity(Double finalGravity) {
		this.finalGravity = finalGravity;
	}

	public Double getIbuFgRatio() {
		return ibuFgRatio;
	}

	public void setIbuFgRatio(Double ibuFgRatio) {
		this.ibuFgRatio = ibuFgRatio;
	}

	public Double getAbv() {
		return abv;
	}

	public void setAbv(Double abv) {
		this.abv = abv;
	}

	public Double getIbu() {
		return ibu;
	}

	public void setIbu(Double ibu) {
		this.ibu = ibu;
	}

	public Double getColor() {
		return color;
	}

	public void setColor(Double color) {
		this.color = color;
	}

	public Double getCarbonation() {
		return carbonation;
	}

	public void setCarbonation(Double carbonation) {
		this.carbonation = carbonation;
	}

	public Double getBatchSizeGallons() {
		return batchSizeGallons;
	}

	public void setBatchSizeGallons(Double batchSizeGallons) {
		this.batchSizeGallons = batchSizeGallons;
	}

	public Double getBoilMinutes() {
		return boilMinutes;
	}

	public void setBoilMinutes(Double boilMinutes) {
		this.boilMinutes = boilMinutes;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public List<FermentableAddition> getFermentableAdditions() {
		return fermentableAdditions;
	}

	public List<HopAddition> getHopAdditions() {
		return hopAdditions;
	}

	public List<YeastAddition> getYeastAdditions() {
		return yeastAdditions;
	}

	public List<MiscIngredientAddition> getMiscAdditions() {
		return miscAdditions;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public void setFermentableAdditions(
			List<FermentableAddition> fermentableAdditions) {
		this.fermentableAdditions = fermentableAdditions;
	}

	public void setHopAdditions(List<HopAddition> hopAdditions) {
		this.hopAdditions = hopAdditions;
	}

	public void setYeastAdditions(List<YeastAddition> yeastAdditions) {
		this.yeastAdditions = yeastAdditions;
	}

	public void setMiscAdditions(List<MiscIngredientAddition> miscAdditions) {
		this.miscAdditions = miscAdditions;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public VolumeUnit getVolumeDisplayUnit() {
		return volumeDisplayUnit;
	}

	public void setVolumeDisplayUnit(VolumeUnit volumeDisplayUnit) {
		this.volumeDisplayUnit = volumeDisplayUnit;
	}

	public Double getHopBillOz() {
		return hopBillOz;
	}

	public void setHopBillOz(Double hopBillOz) {
		this.hopBillOz = hopBillOz;
	}

	public Double getBoilLossGPH() {
		return boilLossGPH;
	}

	public void setBoilLossGPH(Double boilLossGPH) {
		this.boilLossGPH = boilLossGPH;
	}

	public Double getPreBoilSG() {
		return preBoilSG;
	}

	public void setPreBoilSG(Double preBoilSG) {
		this.preBoilSG = preBoilSG;
	}

	public Double getPreBoilVolGal() {
		return preBoilVolGal;
	}

	public void setPreBoilVolGal(Double preBoilVolGal) {
		this.preBoilVolGal = preBoilVolGal;
	}

	public Double getTrubLossGal() {
		return trubLossGal;
	}

	public void setTrubLossGal(Double trubLossGal) {
		this.trubLossGal = trubLossGal;
	}

	public Double getMaxPotential() {
		return maxPotential;
	}

	public void setMaxPotential(Double maxPotential) {
		this.maxPotential = maxPotential;
	}

	public Double getAttenuation() {
		return attenuation;
	}

	public void setAttenuation(Double attenuation) {
		this.attenuation = attenuation;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public String getTastingNotes() {
		return tastingNotes;
	}

	public void setTastingNotes(String tastingNotes) {
		this.tastingNotes = tastingNotes;
	}
}
