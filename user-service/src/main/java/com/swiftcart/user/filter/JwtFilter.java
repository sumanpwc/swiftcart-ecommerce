package com.swiftcart.user.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.swiftcart.user.service.JwtTokenUtilService;
import com.swiftcart.user.service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	JwtTokenUtilService jwtTokenUtilService;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;
		
		// Check if the header starts with "Bearer "
		if (authHeader != null && authHeader.startsWith("Bearer")) {
			token = authHeader.substring(7);
			username = jwtTokenUtilService.extractUsername(token);
		}
		
		 // If the token is valid and no authentication is set in the context
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
		
			// Validate token and set authentication
			if(jwtTokenUtilService.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	                    userDetails,
	                    null,
	                    userDetails.getAuthorities()
	                );
	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Continue the filter chain
        filterChain.doFilter(request, response);
	}
}