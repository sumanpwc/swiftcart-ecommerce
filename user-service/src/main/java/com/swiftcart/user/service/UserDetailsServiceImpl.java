package com.swiftcart.user.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.swiftcart.user.config.UserPrincipal;
import com.swiftcart.user.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("🔍 Attempting to load user by email: {}", email);
		
		return userRepository.findByEmail(email)
                .map(user -> {
                    log.info("✅ User found: {}", user.getEmail());
                    return new UserPrincipal(user);
                })
                .orElseThrow(() -> {
                    log.warn("❌ User not found with email: {}", email);
                    return new UsernameNotFoundException("User not found with email: " + email);
                });
	}
}