package com.example.clothes.controllers;

import com.example.clothes.entities.PasswordResetToken;
import com.example.clothes.entities.User;
import com.example.clothes.services.UserService;
import com.example.clothes.utils.EmailUtility;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
	private final JavaMailSender javaMailSender;

	@PostMapping("/password-reset-request")
	public ResponseEntity<String> resetPasswordRequest(Authentication authentication, final HttpServletRequest servletRequest)
			throws MessagingException, UnsupportedEncodingException {

		String email= authentication.getName();
		log.info("email for password reset: "+email);


		Optional<User> user = userService.findByEmail(email);
		if (user.isPresent()) {
			PasswordResetToken passwordResetToken=userService.createPasswordResetTokenForUser(user.get(), UUID.randomUUID().toString());
			EmailUtility.sendPasswordResetCode(passwordResetToken,javaMailSender, applicationUrl(servletRequest));
		}
		return ResponseEntity.ok().body("We have sent a password reset link to "+email+". Click the link within 10 minutes or you'll need to request a new one. If you don't see the email, check your spam folder");
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestParam(name="new_password") String newPassword,
												@RequestParam("token") String token){
		boolean tokenVerificationResult = userService.validatePasswordResetToken(token);
		if (!tokenVerificationResult) {
			return ResponseEntity.badRequest().body("Invalid token password reset token");
		}
		Optional<User> theUser = Optional.ofNullable(userService.findUserByPasswordToken(token));
		if (theUser.isPresent()) {
			userService.resetPassword(theUser.get(), newPassword);
			return ResponseEntity.ok().body("Password has been reset successfully");
		}
		return ResponseEntity.badRequest().body("Invalid token password reset token");
	}

	private String applicationUrl(HttpServletRequest request) {
		return "http://"+request.getServerName()+":"
				+request.getServerPort()+request.getContextPath();
	}


}
