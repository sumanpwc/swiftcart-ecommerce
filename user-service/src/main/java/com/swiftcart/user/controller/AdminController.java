package com.swiftcart.user.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiftcart.user.dto.FilteredUserListApiResponse;
import com.swiftcart.user.dto.UserApiResponse;
import com.swiftcart.user.service.AdminService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	// List All Filtered Users 
	
	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllUsers(
			@PathVariable(value = "role", required = false) String role,
			@PathVariable(value = "isEmailVerified", required = false) Boolean isEmailVerified,
			@PathVariable(value = "isPhoneVerified", required = false) Boolean isPhoneVerified,
			@PathVariable(value = "isActive", required = false) Boolean isActive){
		
		FilteredUserListApiResponse response = adminService.getFilteredUsers(role, isEmailVerified, isPhoneVerified, isActive);
		return new ResponseEntity<> (response, HttpStatus.OK);
	}
	
	// Get User By Id
	
	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUserByID(@PathVariable("id") UUID id){
		UserApiResponse userApiResponse = adminService.getUserById(id);
		return new ResponseEntity<> (userApiResponse, HttpStatus.OK); 
	}
	
	// Delete User By Id
	
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") UUID id){
		adminService.deleteUser(id);
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	// Change User Role [Optional]
	
	@PutMapping("/api/v1/admin/users/{id}/role")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> changeUserRole(@PathVariable("id") UUID id){
		
		return new ResponseEntity<> (HttpStatus.OK);
	}
}