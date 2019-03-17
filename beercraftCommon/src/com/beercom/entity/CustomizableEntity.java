package com.beercom.entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;


@MappedSuperclass
public abstract class CustomizableEntity extends BaseEntity{
	
	@XmlTransient
	@Column(name="is_builtin", nullable=false)
	private Boolean isBuiltIn;
	
	@XmlTransient
	@Column(name="owner_id", length=45, nullable=false)
	private String owner;
	
	@Column(name="derived_from")
	private Long derivedFrom;

	@Transient
	@XmlTransient
	private final List<String> excludedCompFields = Arrays.asList("id", "userId", "createdDate", "maintDate", "maintId", "derivedFrom");

	public Long getDerivedFrom() {
		return derivedFrom;
	}

	public void setDerivedFrom(Long derivedFrom) {
		this.derivedFrom = derivedFrom;
	}

	public List<String> getExcludedCompFields() {
		return excludedCompFields;
	}

	public Boolean getIsBuiltIn() {
		return isBuiltIn;
	}

	public void setIsBuiltIn(Boolean isBuiltIn) {
		this.isBuiltIn = isBuiltIn;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
