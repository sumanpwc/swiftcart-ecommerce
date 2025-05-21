package com.swiftcart.user.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtilService {

	private static final Logger log = LoggerFactory.getLogger(JwtTokenUtilService.class);
	
	@Value("${jwt.secret}")
    private String secretKey;
	
	@Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;
    
	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}
	
	// Create a JWT token with specified claims and subject (user name)
    private String createToken(Map<String, Object> claims, String userName) {
    	
    	log.info("==========Jwt Expiration ================" +jwtExpirationMs);
    	
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuer("swiftcart")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Token valid for 30 minutes
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    // Get the signing key for JWT token
    private Key getSignKey() {
    	log.info("==========Secrect Key================" +secretKey);
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    // Extract the user name from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    
    // Extract a claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    // Extract the expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    // Check if the token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate the token against user details and expiration
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    // Public method to get expiration duration (in seconds) for the API response
    public long getExpirationDurationSeconds() {
        return jwtExpirationMs / 1000;
    }
}