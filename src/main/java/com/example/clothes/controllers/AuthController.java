package com.example.clothes.controllers;

import com.example.clothes.dto.request.AuthenticationRequest;
import com.example.clothes.dto.request.RegisterRequest;
import com.example.clothes.dto.response.AuthenticationResponse;
import com.example.clothes.entities.ConfirmationToken;
import com.example.clothes.entities.User;
import com.example.clothes.repositories.ConfirmationTokenRepository;
import com.example.clothes.services.AuthenticationService;
import com.example.clothes.services.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
		if(userService.existsByEmail(registerDto.getEmail())){
			return ResponseEntity.badRequest().body("This email address is already used");
		}

		ConfirmationToken confirmationToken=authService.register(registerDto);



		
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
	public ResponseEntity<Void> refreshToken(
			HttpServletRequest request,
			HttpServletResponse response
	) throws IOException {
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


	private String applicationUrl(HttpServletRequest request) {
		return "http://"+request.getServerName()+":"
				+request.getServerPort()+request.getContextPath();
	}
	
	
}
