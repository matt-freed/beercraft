package com.beercom.entity;

import java.util.Arrays;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


@MappedSuperclass
public abstract class CustomizableEntity extends BaseEntity{
	
	@JsonbTransient
	@Column(name="is_builtin", nullable=false)
	private Boolean isBuiltIn;
	
	@JsonbTransient
	@Column(name="owner_id", length=45, nullable=false)
	private String owner;
	
	@Column(name="derived_from")
	private Long derivedFrom;

	@Transient
	@JsonbTransient
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
