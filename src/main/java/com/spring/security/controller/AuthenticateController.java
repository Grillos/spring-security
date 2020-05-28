package com.spring.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.domain.Login;
import com.spring.security.domain.TokenDto;
import com.spring.security.service.AuthenticateService;

@RestController
@RequestMapping(value = "/v1/auth")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private AuthenticateService authenticateService;
    
    @PostMapping("/token")
    public ResponseEntity<TokenDto> auth(@RequestBody @Valid Login login) {
    	try {
    		UsernamePasswordAuthenticationToken user = login.converter();
    		String token = authenticateService.getToken(authenticationManager.authenticate(user));
    		return ResponseEntity.ok(TokenDto.builder().token(token).tipo("Bearer").build());
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
    }
    
    @GetMapping("/refresh/token")
    public ResponseEntity<TokenDto> refresh(String token) {
    	try {
    		
    		return ResponseEntity.ok(TokenDto.builder().token(authenticateService.refreshToken(token)).tipo("Bearer").build());
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
    }
}
