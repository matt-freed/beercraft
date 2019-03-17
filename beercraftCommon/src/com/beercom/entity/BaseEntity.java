package com.beercom.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAccessType;

//Xml annotations needed by Moxy JSON serialization in order for @XmlTransient to work
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@MappedSuperclass
public class BaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	//We don't want the user to be able to modify this
	//Automatically set on initial save to db
	@XmlTransient
	@Column(name="create_date", updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	//Automatically set in the DB on update
	@XmlTransient
	@Column(name="maint_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date maintDate;
	
	@XmlTransient
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
