package com.swiftcart.user.dto;

public class TokenRefreshResponse {

	private String refreshToken;
	
	TokenRefreshResponse() {
		super();
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
