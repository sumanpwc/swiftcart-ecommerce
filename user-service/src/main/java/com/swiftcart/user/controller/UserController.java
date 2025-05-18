package com.swiftcart.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiftcart.user.dto.UserApiResponse;
import com.swiftcart.user.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	// Greetings User
	
    @GetMapping("/hello")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String greetings() {   	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String email = authentication.getName();
    	String username = userService.findUserByEmail(email).getFullName();
    	return "Welcome " + username;
    }
    
    // View Profile 
    
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> viewProfile(){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String email = authentication.getName();
    	UserApiResponse userApiResponse = userService.viewProfile(email);
    	return new ResponseEntity<> (userApiResponse, HttpStatus.OK);
    }
    
    // Update Profile
    
    @PutMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateProfile(){
    	
    	return new ResponseEntity<> (HttpStatus.OK);
    }
    
    // Change Password
    
    @PostMapping("me/password")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> changePassword(){
    	
    	return new ResponseEntity<> (HttpStatus.OK);
    }
    
    // Logout
    
    @PostMapping("/logout")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> logout(){
    	
    	return new ResponseEntity<> (HttpStatus.OK);
    }
}
