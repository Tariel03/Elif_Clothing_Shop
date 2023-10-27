package com.example.clothes.services;

import com.example.clothes.dto.request.RegisterRequest;
import com.example.clothes.dto.request.AuthenticationRequest;
import com.example.clothes.dto.response.AuthenticationResponse;
import com.example.clothes.entities.ConfirmationToken;
import com.example.clothes.entities.User;
import com.example.clothes.enums.Role;
import com.example.clothes.exceptions.NotFoundException;
import com.example.clothes.repositories.AccessTokenRepository;
import com.example.clothes.repositories.ConfirmationTokenRepository;
import com.example.clothes.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final AccessTokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final ConfirmationTokenRepository confirmationTokenRepository;
  private final UserService userService;

  public ConfirmationToken register(RegisterRequest request) {
    User user = User.builder()
            .firstname(request.getFirstName())
            .lastname(request.getLastName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
    User savedUser = repository.save(user);

    ConfirmationToken confirmationToken = new ConfirmationToken(savedUser, UUID.randomUUID().toString());
    return confirmationTokenRepository.save(confirmationToken);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    var user = repository.findByEmail(request.getEmail())
            .orElseThrow(() -> new NotFoundException("User with email " + request.getEmail() + " not found"));
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    userService.revokeAllUserTokens(user);
    userService.saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow(() -> new NotFoundException("User with email " + userEmail + " not found"));
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        userService.revokeAllUserTokens(user);
        userService.saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
