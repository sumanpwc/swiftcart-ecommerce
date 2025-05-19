package com.swiftcart.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swiftcart.user.config.UserPrincipal;
import com.swiftcart.user.dto.JwtResponse;
import com.swiftcart.user.dto.RegisterRequest;
import com.swiftcart.user.dto.UserRequest;
import com.swiftcart.user.service.JwtTokenUtilService;
import com.swiftcart.user.service.UserDetailsServiceImpl;
import com.swiftcart.user.service.UserService;

@RestController
public class PublicController {
	
	private static final Logger log = LoggerFactory.getLogger(PublicController.class);
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtilService jwtTokenUtilService;
	
	@Autowired
	UserService userService;
	
	// Register New User
	
	@PostMapping("/api/v1/auth/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// Login and Get JWT Token
	
	@PostMapping("/api/v1/auth/login")
	public ResponseEntity<?> generateToken (@RequestBody UserRequest userRequest) {
		log.info("=================Public Controller 1===================" + userRequest.getEmail() + "==================" + userRequest.getPassword());
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));
			
			log.info("=================Public Controller 2===================" + SecurityContextHolder.getContext().getAuthentication().getName());
						
			UserPrincipal userPrincipal = (UserPrincipal) userDetailsServiceImpl.loadUserByUsername(userRequest.getEmail());
			
			log.info("=================Public Controller 3===================" + userRequest.getPassword());
			
			log.info("=================Public Controller 4===================" + userPrincipal.getUsername());
			
			String jwt = jwtTokenUtilService.generateToken(userPrincipal.getUsername());
			String expiresIn = jwtTokenUtilService.getExpirationDurationSeconds() + " Seconds (s)";
			
			return new ResponseEntity<> (new JwtResponse(jwt, expiresIn), HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<> ("Incorrect Email and Password", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// Refresh Token 
	
	@PostMapping("/api/v1/auth/token/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserRequest userRequest){
		try {
			//authenticationManager.authenticate(
				//	new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));
			
			UserPrincipal userPrincipal = (UserPrincipal) userDetailsServiceImpl.loadUserByUsername(userRequest.getEmail());
			String jwt = jwtTokenUtilService.generateToken(userPrincipal.getUsername());
			String expiresIn = jwtTokenUtilService.getExpirationDurationSeconds() + " Seconds (s)";
			
			return new ResponseEntity<>(new JwtResponse(jwt, expiresIn), HttpStatus.OK);			
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<> ("Incorrect Email and Password", HttpStatus.BAD_REQUEST);
		}
	}
	
	// OAuth2 login (Google, GitHub, etc.)
	//@GetMapping("/oauth2/authorize/{provider}")
	
	
}