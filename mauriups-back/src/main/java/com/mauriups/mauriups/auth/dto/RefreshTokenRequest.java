package com.mauriups.mauriups.auth.dto;

import lombok.Getter;

@Getter
public class RefreshTokenRequest {
    private String refreshToken;

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}