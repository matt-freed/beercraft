package com.beercom.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A style of beer, 
 * (based on BeerXML spec, for storing BJCP)
 *
 */
@Entity
@Table(name="style")
@AttributeOverrides({@AttributeOverride(name="id", column=@Column(name="style_id"))})
public class Style extends BaseEntity{
	
	@Column(name="name", length=2000, nullable=false)
	private String name;
	
	@Column(name="type", nullable=false)
	@Enumerated(EnumType.STRING)
	private StyleType type;
	
	@Column(name="category", length=45)
	private String category;
	
	@Column(name="category_number")
	private Integer categoryNumber;
	
	@Column(name="style_letter")
	private Character styleLetter;
	
	@Column(name="style_guide", length=45)
	private String styleGuide;
	
	@Column(name="og_min")
	private Double ogMin;
	
	@Column(name="og_max")
	private Double ogMax;
	
	@Column(name="fg_min")
	private Double fgMin;
	
	@Column(name="fg_max")
	private Double fgMax;
	
	@Column(name="ibu_min")
	private Double ibuMin;
	
	@Column(name="ibu_max")
	private Double ibuMax;
	
	@Column(name="color_min")
	private Double colorMin;
	
	@Column(name="color_max")
	private Double colorMax;
	
	@Column(name="abv_min")
	private Double abvMin;
	
	@Column(name="abv_max")
	private Double abvMax;
	
	@Column(name="carbonation", length=45)
	private String carbonation;
	
	@Column(name="notes", length=5000)
	private String notes;
	
	@Column(name="profile", length=5000)
	private String profile;
	
	@Column(name="ingredients", length=5000)
	private String ingredients;
	
	@Column(name="examples", length=5000)
	private String examples;
	
	public enum StyleType{
		LAGER("Lager"),
		ALE("Ale"),
		MEAD("Mead"),
		WHEAT("Wheat"),
		MIXED("Mixed"),
		CIDER("Cider");
		
		private String description;
		
		private StyleType(String description){
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Style)){
			return false;
		}
		if(obj == this){
			return true;
		}
		
		Style other = (Style) obj;
		
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

	public StyleType getType() {
		return type;
	}

	public void setType(StyleType type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCategoryNumber() {
		return categoryNumber;
	}

	public void setCategoryNumber(int categoryNumber) {
		this.categoryNumber = categoryNumber;
	}

	public char getStyleLetter() {
		return styleLetter;
	}

	public void setStyleLetter(char styleLetter) {
		this.styleLetter = styleLetter;
	}

	public String getStyleGuide() {
		return styleGuide;
	}

	public void setStyleGuide(String styleGuide) {
		this.styleGuide = styleGuide;
	}

	public double getOgMin() {
		return ogMin;
	}

	public void setOgMin(double ogMin) {
		this.ogMin = ogMin;
	}

	public double getOgMax() {
		return ogMax;
	}

	public void setOgMax(double ogMax) {
		this.ogMax = ogMax;
	}

	public double getFgMin() {
		return fgMin;
	}

	public void setFgMin(double fgMin) {
		this.fgMin = fgMin;
	}

	public double getFgMax() {
		return fgMax;
	}

	public void setFgMax(double fgMax) {
		this.fgMax = fgMax;
	}

	public double getIbuMin() {
		return ibuMin;
	}

	public void setIbuMin(double ibuMin) {
		this.ibuMin = ibuMin;
	}

	public double getIbuMax() {
		return ibuMax;
	}

	public void setIbuMax(double ibuMax) {
		this.ibuMax = ibuMax;
	}

	public double getColorMin() {
		return colorMin;
	}

	public void setColorMin(double colorMin) {
		this.colorMin = colorMin;
	}

	public double getColorMax() {
		return colorMax;
	}

	public void setColorMax(double colorMax) {
		this.colorMax = colorMax;
	}

	public double getAbvMin() {
		return abvMin;
	}

	public void setAbvMin(double abvMin) {
		this.abvMin = abvMin;
	}

	public double getAbvMax() {
		return abvMax;
	}

	public void setAbvMax(double abvMax) {
		this.abvMax = abvMax;
	}

	public String getCarbonation() {
		return carbonation;
	}

	public void setCarbonation(String carbonation) {
		this.carbonation = carbonation;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getExamples() {
		return examples;
	}

	public void setExamples(String examples) {
		this.examples = examples;
	}

	public void setCategoryNumber(Integer categoryNumber) {
		this.categoryNumber = categoryNumber;
	}

	public void setStyleLetter(Character styleLetter) {
		this.styleLetter = styleLetter;
	}

	public void setOgMin(Double ogMin) {
		this.ogMin = ogMin;
	}

	public void setOgMax(Double ogMax) {
		this.ogMax = ogMax;
	}

	public void setFgMin(Double fgMin) {
		this.fgMin = fgMin;
	}

	public void setFgMax(Double fgMax) {
		this.fgMax = fgMax;
	}

	public void setIbuMin(Double ibuMin) {
		this.ibuMin = ibuMin;
	}

	public void setIbuMax(Double ibuMax) {
		this.ibuMax = ibuMax;
	}

	public void setColorMin(Double colorMin) {
		this.colorMin = colorMin;
	}

	public void setColorMax(Double colorMax) {
		this.colorMax = colorMax;
	}

	public void setAbvMin(Double abvMin) {
		this.abvMin = abvMin;
	}

	public void setAbvMax(Double abvMax) {
		this.abvMax = abvMax;
	}
}
