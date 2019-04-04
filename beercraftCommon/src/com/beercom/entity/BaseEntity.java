package com.beercom.entity;

import java.util.Date;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@MappedSuperclass
public class BaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	//We don't want the user to be able to modify this
	//Automatically set on initial save to db
	@JsonbTransient
	@Column(name="create_date", updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	//Automatically set in the DB on update
	@JsonbTransient
	@Column(name="maint_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date maintDate;
	
	@JsonbTransient
	@Size(min=0, max=45)
	@Column(name="maint_id", length=45, nullable = false)
	private String maintId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getMaintId() {
		return maintId;
	}

	public void setMaintId(String maintId) {
		this.maintId = maintId;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getMaintDate() {
		return maintDate;
	}

	public void setMaintDate(Date maintDate) {
		this.maintDate = maintDate;
	}
}
