package com.swiftcart.user.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.vladmihalcea.hibernate.type.json.JsonType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "users", schema = "user_schema")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    private String fullName;

    @Column(name = "is_email_verified", nullable = false)
    private Boolean isEmailVerified = false;

    @Column(name = "is_phone_verified", nullable = false)
    private Boolean isPhoneVerified = false;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider authProvider = AuthProvider.LOCAL;

    private String profilePicture;

    @Type(JsonType.class) 
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metadata;
    
    private OffsetDateTime lastLogin;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime updatedAt;

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles;

    public User(){
    	super();
    }

	public User(UUID id, String email, String password, String phoneNumber,
			String fullName, Boolean isEmailVerified, Boolean isPhoneVerified, Boolean isActive,
			AuthProvider authProvider, String profilePicture, Map<String, Object> metadata, OffsetDateTime lastLogin,
			OffsetDateTime createdAt, OffsetDateTime updatedAt, List<UserRole> userRoles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
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
		this.userRoles = userRoles;
	}

	public UUID getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public void OffsetDateTime(OffsetDateTime lastLogin) {
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

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
}