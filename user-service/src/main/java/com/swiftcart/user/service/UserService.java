package com.swiftcart.user.service;

import org.springframework.stereotype.Component;

import com.swiftcart.user.dto.ChangePasswordRequest;
import com.swiftcart.user.dto.LoginRequest;
import com.swiftcart.user.dto.LoginResponse;
import com.swiftcart.user.dto.RegisterRequest;
import com.swiftcart.user.dto.TokenRefreshRequest;
import com.swiftcart.user.dto.TokenRefreshResponse;
import com.swiftcart.user.dto.UpdateEmailRequest;
import com.swiftcart.user.dto.UpdateProfileRequest;
import com.swiftcart.user.dto.UserDto;

import jakarta.validation.Valid;

@Component
public class UserService {

	public void register(@Valid RegisterRequest registerRequest) {
		
	}
	
	public LoginResponse login(@Valid LoginRequest loginRequest) {
		return null;
		
	}

	public void logout(TokenRefreshRequest refreshRequest) {
		// TODO Auto-generated method stub
		
	}

	public TokenRefreshResponse refreshToken(TokenRefreshRequest refreshRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateUserProfile(Long id, @Valid UpdateProfileRequest updateProfileRequest) {
		// TODO Auto-generated method stub
		
	}

	public UserDto getUserProfile(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void changePassword(Long id, @Valid ChangePasswordRequest changePasswordRequest) {
		// TODO Auto-generated method stub
		
	}

	public void updateEmail(Long id, UpdateEmailRequest updateEmailRequest) {
		// TODO Auto-generated method stub
		
	}
}
