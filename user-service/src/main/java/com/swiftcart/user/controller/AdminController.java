package com.swiftcart.user.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swiftcart.user.dto.FilteredUserListResponse;
import com.swiftcart.user.service.UserService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	// List All Filtered Users 
	
	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllUsers(
			@RequestParam(value = "role", required = false) String role,
			@RequestParam(value = "isEmailVerified", required = false) Boolean isEmailVerified,
			@RequestParam(value = "isPhoneVerified", required = false) Boolean isPhoneVerified,
			@RequestParam(value = "isActive", required = false) Boolean isActive){
		
		FilteredUserListResponse response = userService.getFilteredUsers(role, isEmailVerified, isPhoneVerified, isActive);
		return new ResponseEntity<> (response, HttpStatus.OK);
	}
	
	// Get User By ID
	
	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUserByID(@RequestParam("id") UUID id){
		
		return new ResponseEntity<> (HttpStatus.OK); 
	}
	
	// Delete User By Id
	
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUserById(@RequestParam("id") UUID id){
		
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	// Change User Role [Optional]
	
	@PutMapping("/api/v1/admin/users/{id}/role")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> changeUserRole(@RequestParam("id") UUID id){
		
		return new ResponseEntity<> (HttpStatus.OK);
	}
}