package com.example.clothes.services;

import com.example.clothes.dto.request.UpdatePasswordRequest;
import com.example.clothes.dto.request.UpdateUserInfoRequest;
import com.example.clothes.dto.response.UserInfoResponse;
import com.example.clothes.entities.PasswordResetToken;
import com.example.clothes.entities.User;
import com.example.clothes.entities.AccessToken;
import com.example.clothes.enums.Status;
import com.example.clothes.enums.TokenType;
import com.example.clothes.mappers.UserMapper;
import com.example.clothes.repositories.AccessTokenRepository;
import com.example.clothes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AccessTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenService passwordResetTokenService;
    private final UserMapper userMapper;


    public User add(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public void saveUserToken(User user, String jwtToken) {
        var token = AccessToken.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);

    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void resetPassword(User theUser, String newPassword) {
        theUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(theUser);
    }

    public boolean validatePasswordResetToken(String token) {
        return passwordResetTokenService.validatePasswordResetToken(token);
    }

    public User findUserByPasswordToken(String token) {
        return passwordResetTokenService.findUserByPasswordToken(token).get();
    }

    public PasswordResetToken createPasswordResetTokenForUser(User user, String passwordResetToken) {
        return passwordResetTokenService.createPasswordResetTokenForUser(user, passwordResetToken);
    }

    public void updatePassword(User user, UpdatePasswordRequest request) {
        String encodedOldPassword=passwordEncoder.encode(request.getOldPassword());
        if(!encodedOldPassword.equals(user.getPassword())){
            throw new RuntimeException("Old password was entered incorrectly");
        }else{
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }

    }

    public void updateUserInfo(User user, UpdateUserInfoRequest request) {
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPhoneNumber(request.getPhoneNumber());

        userRepository.save(user);
    }

    public UserInfoResponse getUserInfo(User user) {
        return userMapper.userToUserInfoResponse(user);
    }

    public void deleteUser(User user) {
        user.setStatus(Status.DELETED);
        userRepository.save(user);
    }
}
