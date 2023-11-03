package com.example.clothes.config;

import com.example.clothes.dto.response.AuthenticationResponse;
import com.example.clothes.entities.User;
import com.example.clothes.repositories.UserRepository;
import com.example.clothes.services.JwtService;
import com.example.clothes.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User= (OAuth2User) authentication.getPrincipal();
        String email=oAuth2User.getAttribute("email");

        log.info("User's email: "+email);

        if(!userRepository.existsByEmail(email) ){
            User newUser=User.builder()
                    .firstname(oAuth2User.getAttribute("given_name"))
                    .lastname(oAuth2User.getAttribute("family_name"))
                    .email(email)
                    .enable(true)
                    .build();
            userRepository.save(newUser);
        }

        User user=userRepository.findByEmail(email).orElseThrow();


        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        userService.revokeAllUserTokens(user);
        userService.saveUserToken(user, jwtToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
    }
}
