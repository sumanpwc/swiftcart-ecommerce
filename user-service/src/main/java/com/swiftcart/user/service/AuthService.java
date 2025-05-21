package com.swiftcart.user.service;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.swiftcart.commom.dto.ApiResponse;
import com.swiftcart.commom.dto.ResponseFactory;
import com.swiftcart.user.dto.JwtResponse;
import com.swiftcart.user.dto.LoginRequest;
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
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtilService jwtTokenUtilService;
	
	// Register User
	
	public ApiResponse<RegisterResponse> registerUser (RegisterRequest request) {
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
        
        // Creating response object
        RegisterResponse registerResponse = new RegisterResponse(
                user.getEmail(),
                user.getFullName(),
                Collections.singletonList(role.getName()),
                user.getIsActive(),
                user.getCreatedAt()
        		);
        
        // Building response
        return ResponseFactory.success(
        		"User has been registered successfully", 
        		registerResponse, 
        		1, 
        		UUID.randomUUID().toString());
	}	
	
	// Login and Get JWT Token
	
	public ApiResponse<JwtResponse> login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(		
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		// Access Token
		String accessToken = jwtTokenUtilService.generateToken(userDetails.getUsername());
		
		// Refresh Token
		String refreshToken = jwtTokenUtilService.generateToken(userDetails.getUsername());
		
		JwtResponse jwtResponse = new JwtResponse(
				accessToken,
				refreshToken,
				jwtTokenUtilService.getExpirationDurationSeconds()
				);
		
		// Building the response
		
		return ResponseFactory.success(
				"You have logged in sucessfully", 
				jwtResponse, 
				1, 
				UUID.randomUUID().toString());
	}
		
	// Refresh Token 
	
	 public ApiResponse<JwtResponse> refreshToken(String refreshHeader) {
	        String token = refreshHeader.startsWith("Bearer ") ? refreshHeader.substring(7) : refreshHeader;
	        String username = jwtTokenUtilService.extractUsername(token);

	        UserDetails userDetails = (UserDetails) userDetailsServiceImpl.loadUserByUsername(username);
	        
	        if (!jwtTokenUtilService.validateToken(token, userDetails)) {
	            throw new RuntimeException("Invalid refresh token");
	        }

	        String newAccessToken = jwtTokenUtilService.generateToken(username);
	        String newRefreshToken = jwtTokenUtilService.generateToken(username);

			JwtResponse jwtResponse = new JwtResponse(
					newAccessToken,
					newRefreshToken,
					jwtTokenUtilService.getExpirationDurationSeconds()
					);
			
			// Building the response
			
			return ResponseFactory.success(
					"You have logged in sucessfully", 
					jwtResponse, 
					1, 
					UUID.randomUUID().toString());		
	}
	
	// OAuth2 login (Google, GitHub, etc.)
	
	
}