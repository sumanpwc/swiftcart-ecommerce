package com.swiftcart.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swiftcart.commom.dto.ApiResponse;
import com.swiftcart.user.dto.JwtResponse;
import com.swiftcart.user.dto.LoginRequest;
import com.swiftcart.user.dto.RegisterRequest;
import com.swiftcart.user.dto.RegisterResponse;
import com.swiftcart.user.service.AuthService;

@RestController
public class PublicController {
	
	private static final Logger log = LoggerFactory.getLogger(PublicController.class);
	
	@Autowired
	private AuthService authService;
	
	// Register new User
	
	@PostMapping("/api/v1/auth/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
		ApiResponse<RegisterResponse> response = authService.registerUser(registerRequest);
		log.info("=====================================" +response.toString());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	// Login and Get JWT Token
	
	@PostMapping("/api/v1/auth/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		ApiResponse<JwtResponse> response = authService.login(loginRequest);
		return new ResponseEntity<> (response, HttpStatus.OK);
	}

	// Refresh Token
	
	@PostMapping("/api/v1/refresh/token/{refreshHeader}")
	public ResponseEntity<?> refreshToken(@PathVariable String refreshHeader){
		ApiResponse<JwtResponse> response = authService.refreshToken(refreshHeader);
		return new ResponseEntity<> (response, HttpStatus.OK);
	}
	
	// OAuth2 login (Google, GitHub, etc.)
	//@GetMapping("/oauth2/authorize/{provider}")
	
}