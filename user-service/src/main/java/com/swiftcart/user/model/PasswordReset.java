package com.swiftcart.user.model;
import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "password_resets", schema = "user_schema")
public class PasswordReset {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String resetToken;

    private OffsetDateTime tokenExpiry;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    private boolean used = false;
    
    public PasswordReset() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public OffsetDateTime getTokenExpiry() {
		return tokenExpiry;
	}

	public void setTokenExpiry(OffsetDateTime tokenExpiry) {
		this.tokenExpiry = tokenExpiry;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
}
