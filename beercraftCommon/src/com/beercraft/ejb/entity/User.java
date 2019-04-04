package com.beercraft.ejb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
public class User{
	
	@Id
	@NotNull @Size(min=0, max=45)
	@Column(name="auth_id", length=45, nullable=false)
	private String authId;
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Version
	@Column(name="maint_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date maintDate;
	
	@Size(min=0, max=45)
	@Column(name="maint_id", length=45, nullable = false)
	private String maintId;
	
	@NotNull @Size(min=0, max=45)
	@Column(name="email", length=45, nullable=false)
	private String email;
	
	@NotNull @Size(min=0, max=45)
	@Column(name="name", length=45, nullable=false)
	private String name;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

}
