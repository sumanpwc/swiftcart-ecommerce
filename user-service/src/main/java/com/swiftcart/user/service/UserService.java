package com.swiftcart.user.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.swiftcart.commom.dto.ApiResponse;
import com.swiftcart.commom.dto.ResponseFactory;
import com.swiftcart.user.dto.UserResponse;
import com.swiftcart.user.model.User;
import com.swiftcart.user.repository.UserRepository;

@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	// Find a user by Email
	
	public UserResponse findUserByEmail(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Exist"));
				
		return convertToUserResponseDTO(user);
	}
		
	// Convert the response to the UserResponseDTO
		
	public UserResponse convertToUserResponseDTO(User user) {
	    List<String> roleNames = user.getUserRoles().stream()
	        .map(userRole -> userRole.getRole().getName())
	        .collect(Collectors.toList());

	    return new UserResponse(
	    		user.getEmail(), 
	    		user.getPhoneNumber(),
	    		user.getFullName(), 
	    		user.getIsEmailVerified(),
	    		user.getIsPhoneVerified(),
	    		user.getIsActive(),
	    		user.getAuthProvider(),
	    		user.getProfilePicture(),
	    		user.getMetadata(),
	    		user.getLastLogin(),
	    		user.getCreatedAt(),
	    		user.getUpdatedAt(),
	    		roleNames);
	}
	
	// View Profile
	
	public ApiResponse<UserResponse> viewProfile(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Exist"));
				
		UserResponse userResponse = convertToUserResponseDTO(user);
		
		// Build the response
		return ResponseFactory.success(
				"User retrieved successfully.", 
				userResponse, 
				1, 
				UUID.randomUUID().toString());	
	}

}