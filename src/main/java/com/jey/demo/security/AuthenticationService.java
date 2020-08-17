package com.jey.demo.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jey.demo.dto.AuthRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final JwtService jwtService;
//	private final AuthenticationManager authenticationManager;
	private final MyUserDetailService myUserDetailService;

	public String authenticate(AuthRequest authRequest) throws BadCredentialsException {

//		authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		
		final UserDetails user = myUserDetailService.loadUserByUsername(authRequest.getUsername());
	
		if (user == null) {
			throw new BadCredentialsException("No user found");
		}
		
		return jwtService.generateToken(user);
	}
}
