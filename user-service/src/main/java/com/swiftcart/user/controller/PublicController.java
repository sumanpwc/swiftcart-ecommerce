package com.swiftcart.user.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiftcart.user.config.UserPrincipal;
import com.swiftcart.user.dto.JwtResponse;
import com.swiftcart.user.dto.UserRequest;
import com.swiftcart.user.dto.UserResponse;
import com.swiftcart.user.model.User;
import com.swiftcart.user.repository.UserRepository;
import com.swiftcart.user.service.JwtTokenUtilService;
import com.swiftcart.user.service.UserDetailsServiceImpl;
import com.swiftcart.user.service.UserService;



@RestController
@RequestMapping("/api/v1/public")
public class PublicController {
	
	private static final Logger log = LoggerFactory.getLogger(PublicController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtilService jwtTokenUtilService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/generateToken")
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
	
	@GetMapping("/find")
	public ResponseEntity<?> getUser(@RequestBody UserRequest userRequest) {
	    Optional<User> user = userRepository.findByEmail(userRequest.getEmail());
	    UserResponse userResponse = userService.convertToUserResponseDTO(user.get());
	    
	    return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
}