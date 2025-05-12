package com.swiftcart.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.swiftcart.user.filter.JwtFilter;
import com.swiftcart.user.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorize ->
						authorize.requestMatchers("/admin/***").hasRole("ADMIN")
						.requestMatchers("/user/***").authenticated()
						.anyRequest().permitAll()
						)
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No sessions
                    )
                    .authenticationManager(authenticationManager(passwordEncoder(), userDetailsServiceImpl)) // Custom authentication provider
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(new Http403ForbiddenEntryPoint())); // Handle forbidden access for unauthorized users

                return http.build(); // Explicitly calling build here instead of returning directly
	}
	
	@Bean
	AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsServiceImpl ) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}