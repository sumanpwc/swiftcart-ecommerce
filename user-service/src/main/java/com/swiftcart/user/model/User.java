package com.swiftcart.user.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String phoneNumber;

    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.CUSTOMER;

    private Boolean emailVerified = false;

    private Boolean phoneVerified = false;

    private Boolean isActive = true;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider = AuthProvider.LOCAL;

    private String profilePicture;

    
    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;

    private LocalDateTime lastLogin;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
