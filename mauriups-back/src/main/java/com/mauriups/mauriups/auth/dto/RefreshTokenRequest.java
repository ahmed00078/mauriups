package com.mauriups.mauriups.auth.dto;

public class RefreshTokenRequest {
    private String refreshToken;

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}