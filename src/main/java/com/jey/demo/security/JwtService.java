package com.jey.demo.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	private String SECRET = "thales_demo";

	@Autowired
	private MyUserDetailService myUserDetailService;

	public String generateToken(UserDetails userDetails) {
		LocalDateTime expiration = LocalDateTime.now().plusMinutes(30);
		Date expirationDate = Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant());

		return Jwts.builder().setSubject(userDetails.getUsername()).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
	}

	public Boolean isValidToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}

	public UsernamePasswordAuthenticationToken validateJwt(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader("Authorization");
		token = token.replace("Bearer ", "");
		String username = extractUsername(token);

		if (username == null)
			return null;

		UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
		if (isValidToken(token, userDetails)) {
			return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		}
		
		return null;
	}
}
