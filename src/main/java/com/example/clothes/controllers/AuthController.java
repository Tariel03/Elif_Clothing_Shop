package com.example.clothes.controllers;

import com.example.clothes.dto.request.AuthenticationRequest;
import com.example.clothes.dto.request.RegisterRequest;
import com.example.clothes.dto.response.AuthenticationResponse;
import com.example.clothes.entities.ConfirmationToken;
import com.example.clothes.entities.PasswordResetToken;
import com.example.clothes.entities.User;
import com.example.clothes.repositories.ConfirmationTokenRepository;
import com.example.clothes.services.AuthenticationService;
import com.example.clothes.services.UserService;
import com.example.clothes.utils.EmailUtility;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private final ConfirmationTokenRepository confirmationTokenRepository;
	private final UserService userService;
	private final AuthenticationService authService;
	private final JavaMailSender javaMailSender;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest registerDto, final HttpServletRequest servletRequest) throws MessagingException, UnsupportedEncodingException {
		if(!registerDto.getPassword().equals(registerDto.getPasswordConfirmation())){
			return ResponseEntity.badRequest().body("Passwords do not match, please retype");
		}

		if(userService.existsByEmail(registerDto.getEmail())){
			return ResponseEntity.badRequest().body("This email address is already used");
		}

		ConfirmationToken confirmationToken=authService.register(registerDto);

		EmailUtility.sendVerificationEmail(confirmationToken,javaMailSender,applicationUrl(servletRequest));
		
		return ResponseEntity.ok().body("User registered successfully!"
				+ "We have sent an email with a confirmation link to your email address. In order to complete the sign-up process, please click the confirmation link.\r\n"
				+ "\r\n"
				+ "If you do not receive a confirmation email, please check your spam folder. Also, please verify that you entered a valid email address in our sign-up form.\r\n"
				+ "\r\n"
				+ "If you need assistance, please contact us");
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest loginRequest) {
		return ResponseEntity.ok(authService.authenticate(loginRequest));
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<Void> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		authService.refreshToken(request, response);
		return ResponseEntity.ok().build();
	}

	
	@GetMapping(value = "/confirm-account/{confirmationToken}")
	public ResponseEntity<String> confirmUserAccount(@PathVariable String confirmationToken){
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		if(token != null) {
			User user = token.getUser();
			user.setEnable(true);
			userService.save(user);
			return new ResponseEntity<>("Account verified successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("The link is invalid or broken!", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/password-reset-request")
	public ResponseEntity<String> resetPasswordRequest(@RequestParam(name="email")String email, final HttpServletRequest servletRequest)
			throws MessagingException, UnsupportedEncodingException {

		Optional<User> user = userService.findByEmail(email);
		if (user.isPresent()) {
			PasswordResetToken passwordResetToken=userService.createPasswordResetTokenForUser(user.get(), UUID.randomUUID().toString());
			EmailUtility.sendPasswordResetCode(passwordResetToken,javaMailSender, applicationUrl(servletRequest));
			return ResponseEntity.ok().body("We have sent a password reset link to "+email+". Click the link within 10 minutes or you'll need to request a new one. If you don't see the email, check your spam folder");
		}else{
			return ResponseEntity.badRequest().body(String.format("User with email '%s' was not found",email));
		}

	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestParam(name="new_password") String newPassword,
												@RequestParam(name="confirm_new_password") String confirmNewPassword,
												@RequestParam("token") String token){
		if(!newPassword.equals(confirmNewPassword)){
			return ResponseEntity.badRequest().body("Passwords do not match, please retype");
		}

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
