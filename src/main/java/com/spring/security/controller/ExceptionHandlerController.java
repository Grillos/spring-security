package com.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.security.domain.ErrorResponseException;
import com.spring.security.domain.Response;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(ErrorResponseException.class)
	public ResponseEntity<Response> errorResponseExceptionHandler(ErrorResponseException ex) {
		return new ResponseEntity<>(ex.getResponse(), ex.getStatus());
	}

}
