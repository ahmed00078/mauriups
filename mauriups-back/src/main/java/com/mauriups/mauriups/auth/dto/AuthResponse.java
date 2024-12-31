package com.mauriups.mauriups.auth.dto;

public class AuthResponse {
    private String token;
    private String refreshToken;
    private UserDTO user;

    public AuthResponse(String token, String refreshToken, UserDTO user) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
    }

    public static class AuthResponseBuilder {
        private String token;
        private String refreshToken;
        private UserDTO user;

        public AuthResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthResponseBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public AuthResponseBuilder user(UserDTO user) {
            this.user = user;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(token, refreshToken, user);
        }
    }
}