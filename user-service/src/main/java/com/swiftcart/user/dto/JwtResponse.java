package com.swiftcart.user.dto;

public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private long expiresIn; // in seconds

    // Constructors
    
    public JwtResponse(String accessToken, String refreshToken, long expiresIn) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expiresIn = expiresIn;
	}

    // Getters and Setters
    
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}