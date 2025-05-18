package com.swiftcart.user.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swiftcart.user.dto.FilteredUserListResponse;
import com.swiftcart.user.dto.UserResponse;
import com.swiftcart.user.model.User;
import com.swiftcart.user.repository.UserRepository;

@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	// Find a user by Email
	
	public UserResponse findUserByEmail(String email) {
		Optional<User> optionUser = userRepository.findByEmail(email);
		User user = optionUser.get();
		
		return convertToUserResponseDTO(user);	
	}

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
		return Collections.emptyList();
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

	// Fetch users based on role, isEmailVerified, isPhoneVerified etc.
	
	public FilteredUserListResponse getFilteredUsers(String role, Boolean isEmailVerified, Boolean isPhoneVerified,
			Boolean isActive) {
		
		// Fetching records from user repository
		Optional<List<User>> optionUsers = userRepository.findFilteredUsers(role, isEmailVerified, isPhoneVerified, isActive);
		List<User> users = optionUsers.orElse(Collections.emptyList());
		
		List<UserResponse> userResponses = users.stream()
	        .map(this::convertToUserResponseDTO)
	        .collect(Collectors.toList());
		
		// Build the response
        FilteredUserListResponse response = new FilteredUserListResponse();
        response.setStatus("success");
        response.setMessage("Filtered user list retrieved successfully.");
        response.setTimestamp(OffsetDateTime.now().toString());
        response.setRequestId(UUID.randomUUID().toString());

        FilteredUserListResponse.FilterParams filterParams = new FilteredUserListResponse.FilterParams();
        filterParams.setRole(role);
        filterParams.setIsEmailVerified(isEmailVerified);
        filterParams.setIsPhoneVerified(isPhoneVerified);
        filterParams.setIsActive(isActive);
        response.setFilters(filterParams);

        FilteredUserListResponse.Data data = new FilteredUserListResponse.Data();
        data.setUsers(userResponses);
        data.setTotal(userResponses.size());
        response.setData(data);
        
        return response;
	}
	
	
}