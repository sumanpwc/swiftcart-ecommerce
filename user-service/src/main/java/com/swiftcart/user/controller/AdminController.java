package com.swiftcart.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swiftcart.user.dto.UserResponse;
import com.swiftcart.user.service.UserService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers(
			@RequestParam(value = "role", required = false) String role,
			@RequestParam(value = "isEmailVerified", required = false) Boolean isEmailVerified,
			@RequestParam(value = "isPhoneVerified", required = false) Boolean isPhoneVerified,
			@RequestParam(value = "isActive", required = false) Boolean isActive){

		List<UserResponse> filteredUserResponses = userService.getFilteredUsers(role, isEmailVerified, isPhoneVerified, isActive);
		return new ResponseEntity<> (filteredUserResponses, HttpStatus.OK);
	}
}