package com.spring.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.security.domain.User;
import com.spring.security.service.AuthenticateService;

public class AuthenticateFilter extends OncePerRequestFilter {
	
	AuthenticateService authenticateService;
	
	public AuthenticateFilter(AuthenticateService authenticateService) {
		this.authenticateService = authenticateService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
		boolean isTokenValid = authenticateService.isTokenValid(token);
		if(isTokenValid)
			authClient(token);
		
		filterChain.doFilter(request, response);
	}
	
	private void authClient(String token) {
		User user = authenticateService.getUserByToken(token);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || (!token.startsWith("Bearer")))
			return null;
		
		return token.replaceAll("Bearer", "").trim();
		
	}

}
