package com.swiftcart.user.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import com.swiftcart.user.model.AuthProvider;

public class UserResponse {
	
    private String email;

    private String phoneNumber;

    private String fullName;

    private Boolean isEmailVerified;

    private Boolean isPhoneVerified;

    private Boolean isActive;

    private AuthProvider authProvider;

    private String profilePicture;

    private Map<String, Object> metadata;
    
    private OffsetDateTime lastLogin;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    private List<String> roles;

    public UserResponse() {
		super();
	}

	public UserResponse(String email, String phoneNumber, String fullName, Boolean isEmailVerified,
			Boolean isPhoneVerified, Boolean isActive, AuthProvider authProvider, String profilePicture,
			Map<String, Object> metadata, OffsetDateTime lastLogin, OffsetDateTime createdAt, OffsetDateTime updatedAt,
			List<String> roles) {
		super();
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.fullName = fullName;
		this.isEmailVerified = isEmailVerified;
		this.isPhoneVerified = isPhoneVerified;
		this.isActive = isActive;
		this.authProvider = authProvider;
		this.profilePicture = profilePicture;
		this.metadata = metadata;
		this.lastLogin = lastLogin;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public Boolean getIsPhoneVerified() {
		return isPhoneVerified;
	}

	public void setIsPhoneVerified(Boolean isPhoneVerified) {
		this.isPhoneVerified = isPhoneVerified;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public AuthProvider getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(AuthProvider authProvider) {
		this.authProvider = authProvider;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}

	public OffsetDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(OffsetDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
