package com.swiftcart.user.controller;

import com.swiftcart.user.config.UserPrincipal;
import com.swiftcart.user.dto.*;
import com.swiftcart.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")  // Base path for all user-related endpoints
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ User Registration End Point
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        // This method will handle user registration logic
        userService.register(registerRequest);
        return ResponseEntity.ok("User registered successfully.");
    }

    // ✅ User Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        // This method will handle user login and JWT generation
        LoginResponse response = userService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    // ✅ Token Refresh Endpoint
    @PostMapping("/token/refresh")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest refreshRequest) {
        // Handle refresh token to issue new access token
        TokenRefreshResponse response = userService.refreshToken(refreshRequest);
        return ResponseEntity.ok(response);
    }

    // ✅ Logout Endpoint
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody TokenRefreshRequest refreshRequest) {
        // Invalidate the refresh token on logout
        userService.logout(refreshRequest);
        return ResponseEntity.ok("Logged out successfully.");
    }

    // ✅ Get Current User Profile
    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        // This method fetches the profile of the currently authenticated user
        UserDto user = userService.getUserProfile(userPrincipal.getId());
        return ResponseEntity.ok(user);
    }

    // ✅ Update User Profile (except email)
    @PutMapping("/me")
    public ResponseEntity<?> updateUserProfile(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                @Valid @RequestBody UpdateProfileRequest updateProfileRequest) {
        // This method updates the profile information, excluding email
        userService.updateUserProfile(userPrincipal.getId(), updateProfileRequest);
        return ResponseEntity.ok("Profile updated successfully.");
    }

    // ✅ Change Password
    @PostMapping("/me/change-password")
    public ResponseEntity<?> changePassword(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                            @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        // This method will allow the user to change their password
        userService.changePassword(userPrincipal.getId(), changePasswordRequest);
        return ResponseEntity.ok("Password changed successfully.");
    }

    // ✅ Update Email (Requires verification)
    @PostMapping("/me/update-email")
    public ResponseEntity<?> updateEmail(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                         @RequestBody UpdateEmailRequest updateEmailRequest) {
        // This method will initiate email change process and send a verification link
        userService.updateEmail(userPrincipal.getId(), updateEmailRequest);
        return ResponseEntity.ok("Email update initiated. Please verify your new email.");
    }
}
