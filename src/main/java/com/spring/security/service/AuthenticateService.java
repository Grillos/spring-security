package com.spring.security.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.domain.User;
import com.spring.security.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthenticateService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Value("${jwt.expiration}")
	private Long JWT_EXPIRATION;
	
	@Value("${jwt.secret}")
	private String JWT_SECRET;
	
	@Value("${jwt.issuer}")
	private String JWT_ISSUER;
	
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent())
			return user.get();
		throw new UsernameNotFoundException("User not found");
	}

	public String getToken(Authentication authentication) {
		
		User user = (User) authentication.getPrincipal();
		
		return Jwts.builder()
				.setIssuer(JWT_ISSUER)
				.setSubject(user.getId().toString())
				.setIssuedAt(new Date())
				.setIssuedAt(new Date(new Date().getTime() + JWT_EXPIRATION))
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET)
				.compact();
				
	}
	
	public String refreshToken(String token) {
		
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		
		User user = userRepository.findById(Long.parseLong(claims.getSubject())).orElseThrow();
		
		return Jwts.builder()
				.setIssuer(JWT_ISSUER)
				.setSubject(user.getId().toString())
				.setIssuedAt(new Date())
				.setIssuedAt(new Date(new Date().getTime() + JWT_EXPIRATION))
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET)
				.compact();
				
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}		
	}
	
	public User getUserByToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		return userRepository.findById(Long.parseLong(claims.getSubject())).orElseThrow();
		
	}

}
