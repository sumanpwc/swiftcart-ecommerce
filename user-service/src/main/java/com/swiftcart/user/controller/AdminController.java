package com.swiftcart.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiftcart.user.dto.UserResponse;
import com.swiftcart.user.model.User;
import com.swiftcart.user.service.UserService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers(){
		List<User> users = userService.getAllUsers();
		List<UserResponse> userResponses = new ArrayList<>();
		
		for (User user : users) {
			UserResponse userResponse = userService.convertToUserResponseDTO(user);
			userResponses.add(userResponse);
		}
		
		return new ResponseEntity<> (userResponses, HttpStatus.FOUND);
	}
}
