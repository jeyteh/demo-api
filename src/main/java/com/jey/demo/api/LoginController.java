package com.jey.demo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jey.demo.dto.AuthRequest;
import com.jey.demo.dto.AuthResponse;
import com.jey.demo.security.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(LoginController.URL)
@RequiredArgsConstructor
public class LoginController {

	public static final String URL = "/api/login";
	
	private final AuthenticationService authenticationService;

	@PostMapping
	public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) throws Exception {
		String jwt;
		try {
			jwt = authenticationService.authenticate(authRequest);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		return ResponseEntity.ok(new AuthResponse(jwt));
	}
}
