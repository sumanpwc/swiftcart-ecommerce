package com.swiftcart.user.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class RegisterResponse {

	private String email;
	private String fullName;
	private List<String> roles;
	private Boolean isActive;
	private OffsetDateTime createdAt;
	
	public RegisterResponse() {
		super();
	}

	public RegisterResponse(String email, String fullName, List<String> roles, Boolean isActive,
			OffsetDateTime createdAt) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.roles = roles;
		this.isActive = isActive;
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
