package com.swiftcart.user.service;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swiftcart.commom.dto.ApiResponse;
import com.swiftcart.commom.dto.ResponseFactory;
import com.swiftcart.user.dto.FilteredUserListApiResponse;
import com.swiftcart.user.dto.UserResponse;
import com.swiftcart.user.model.User;
import com.swiftcart.user.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class AdminService {

	@Autowired
	private UserRepository userRepository;
	
	// Service To Get All Filtered User
	
	public FilteredUserListApiResponse getFilteredUsers(String role, Boolean isEmailVerified, Boolean isPhoneVerified,
			Boolean isActive) {
		
		// Fetching records from user repository
		Optional<List<User>> optionUsers = userRepository.findFilteredUsers(role, isEmailVerified, isPhoneVerified, isActive);
		List<User> users = optionUsers.orElse(Collections.emptyList());
		
		List<UserResponse> userResponses = users.stream()
	        .map(this::convertToUserResponseDTO)
	        .collect(Collectors.toList());
		
		// Build the response
		FilteredUserListApiResponse response = new FilteredUserListApiResponse();
        response.setStatus("success");
        response.setCode(200);
        response.setMessage("Filtered user list retrieved successfully.");
        response.setTimestamp(OffsetDateTime.now().toString());
        response.setRequestId(UUID.randomUUID().toString());

        FilteredUserListApiResponse.FilterParams filterParams = new FilteredUserListApiResponse.FilterParams();
        filterParams.setRole(role);
        filterParams.setIsEmailVerified(isEmailVerified);
        filterParams.setIsPhoneVerified(isPhoneVerified);
        filterParams.setIsActive(isActive);
        response.setFilters(filterParams);

        FilteredUserListApiResponse.Data data = new FilteredUserListApiResponse.Data();
        data.setPayload(userResponses);
        data.setCount(userResponses.size());
        response.setData(data);
        
        return response;
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

	// Service To Get User By Id
	
	public ApiResponse<UserResponse> getUserById(UUID id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found."));
		
		UserResponse userResponse = convertToUserResponseDTO(user);
		
		// Build the response
		return ResponseFactory.success(
				"User retrieved successfully.", 
				userResponse, 
				1, 
				UUID.randomUUID().toString());
	}
	
	// Service To Delete User By Id
	
	public void deleteUser(UUID id) {
	    if (!userRepository.existsById(id)) {
	        throw new EntityNotFoundException("User with ID " + id + " does not exist.");
	    }
	    userRepository.deleteById(id);
	}
	
	// Service To Change User Role
}
