package com.swiftcart.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swiftcart.user.dto.UserResponse;
import com.swiftcart.user.model.User;
import com.swiftcart.user.repository.UserRepository;

@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	// Fetch all users records
	
	public List<UserResponse> getAllUsers(){
		List<User> users = userRepository.findAll();
		if(users != null) {
			List<UserResponse> userResponses = new ArrayList<>();
			
			for (User user : users) {
				UserResponse userResponse = convertToUserResponseDTO(user);
				userResponses.add(userResponse);
			}
			return userResponses;
		}
		return null;
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

	public List<UserResponse> getFilteredUsers(String role, Boolean isEmailVerified, Boolean isPhoneVerified,
			Boolean isActive) {
		// TODO Auto-generated method stub
		return null;
	}
}