package com.mauriups.mauriups.auth.controller;

import com.mauriups.mauriups.auth.dto.AuthResponse;
import com.mauriups.mauriups.auth.dto.LoginRequest;
import com.mauriups.mauriups.auth.dto.RegisterRequest;
import com.mauriups.mauriups.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mauriups.mauriups.auth.dto.RefreshTokenRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        System.out.println("\n\n\n\n =========== Login request in AuthController: " + request + "\n\n\n\n");
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        System.out.println("\n\n\n\n =========== Register request in AuthController: " + request + "\n\n\n\n");
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) throws Exception {
        System.out.println("Received refresh token request with token: " + request.getRefreshToken());

        if (request.getRefreshToken() == null || request.getRefreshToken().isEmpty()) {
            throw new IllegalArgumentException("Refresh token is null or empty");
        }

        System.out.println("\n\n\n\n =========== Refresh token request in AuthController: " + request + "\n\n\n\n");
        return ResponseEntity.ok(authService.refreshToken(request.getRefreshToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        System.out.println("\n\n\n\n =========== Logout request in AuthController: " + token + "\n\n\n\n");
        authService.logout(token);
        return ResponseEntity.ok().build();
    }
}