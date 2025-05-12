package com.swiftcart.user.dto;

public class TokenRefreshRequest {
	private String refreshToken;
	
	TokenRefreshRequest() {
		super();
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
