package com.mauriups.mauriups.auth.service;

import com.mauriups.mauriups.auth.dto.AuthResponse;
import com.mauriups.mauriups.auth.dto.LoginRequest;
import com.mauriups.mauriups.auth.dto.RegisterRequest;
import com.mauriups.mauriups.auth.dto.UserDTO;
import com.mauriups.mauriups.entity.User;
import com.mauriups.mauriups.entity.UserRole;
import com.mauriups.mauriups.entity.UserStatus;
import com.mauriups.mauriups.exception.ResourceNotFoundException;
import com.mauriups.mauriups.exception.UnauthorizedException;
import com.mauriups.mauriups.exception.UserAlreadyExistsException;
import com.mauriups.mauriups.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse login(LoginRequest request) {
        try {
            System.out.println("\n\n\n\n =========== Login request in AuthService: " + request.getEmail() + request.getPassword() + "\n\n\n\n");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            System.out.println("\n\n\n\n =========== Authentication in AuthService: " + authentication.getName() + "\n\n\n\n");

            User user = (User) authentication.getPrincipal();
            String token = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            System.out.println("\n\n\n\n =========== User in AuthService: " + user + "\n\n\n\n");
            System.out.println("\n\n\n\n =========== Token in AuthService: " + token + "\n\n\n\n");

            return AuthResponse.builder()
                    .token(token)
                    .refreshToken(refreshToken)
                    .user(UserDTO.fromUser(user))
                    .build();
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("Invalid email or password");
        }
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return AuthResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .user(UserDTO.fromUser(user))
                .build();
    }

    public AuthResponse refreshToken(String refreshToken) throws Exception {
        String email = jwtService.validateRefreshTokenAndGetEmail(refreshToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String newToken = jwtService.generateToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);

        return AuthResponse.builder()
                .token(newToken)
                .refreshToken(newRefreshToken)
                .user(UserDTO.fromUser(user))
                .build();
    }

    public void logout(String token) {
        // Optionally implement token blacklisting here
        // For now, client-side token removal is sufficient
    }
}