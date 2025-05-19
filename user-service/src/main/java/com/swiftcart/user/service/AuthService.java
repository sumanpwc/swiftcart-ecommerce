package com.swiftcart.user.service;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.swiftcart.user.dto.RegisterRequest;
import com.swiftcart.user.dto.RegisterResponse;
import com.swiftcart.user.model.AuthProvider;
import com.swiftcart.user.model.Role;
import com.swiftcart.user.model.User;
import com.swiftcart.user.model.UserRole;
import com.swiftcart.user.model.UserRoleId;
import com.swiftcart.user.repository.RoleRepository;
import com.swiftcart.user.repository.UserRepository;

@Component
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public RegisterResponse registerUser (RegisterRequest request) {
		if(userRepository.existsByEmail(request.getEmail())){
			 throw new IllegalArgumentException("Email already in use.");
		}
		
		 // 1. Generate UUID for user BEFORE saving
	    final UUID id = UUID.randomUUID();
	    
		// 2. Create and populate User entity
        User user = new User();
        
        user.setId(id);
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setProfilePicture(request.getProfilePicture());
        user.setMetadata(request.getMetadata());
        user.setAuthProvider(AuthProvider.LOCAL);
        user.setIsActive(true);
        user.setIsEmailVerified(false);
        user.setIsPhoneVerified(false);
        user.setCreatedAt(OffsetDateTime.now());
        user.setUpdatedAt(OffsetDateTime.now());

        // Assign default role assignment
        
        Role role = roleRepository.findByName("ROLE_USER")
        		.orElseThrow(() -> new RuntimeException("ROLE_USER not found"));
        
        // Assigning UserRole
        
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        userRole.setId(new UserRoleId(id, role.getId()));
        
        userRepository.save(user);
        
        return new RegisterResponse(
                user.getEmail(),
                user.getFullName(),
                Collections.singletonList(role.getName()),
                user.getIsActive(),
                user.getCreatedAt()
        		);
	}	
}