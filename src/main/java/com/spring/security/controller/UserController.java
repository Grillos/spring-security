package com.spring.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.security.domain.User;
import com.spring.security.dto.UserDto;
import com.spring.security.service.UserService;
import com.sun.istack.NotNull;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	 
    @GetMapping
    public Page<UserDto> findAll(@RequestParam(required = true) int page, 
    							@RequestParam(required = true) int size) {
    	return userService.findAll(page, size);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable @NotNull Long id) {
    	UserDto userDto = userService.findById(id);
    	return (userDto != null) ? new ResponseEntity<>(userDto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid User user, UriComponentsBuilder uri) {
    	UserDto userDto = userService.create(user);
    	
    	return ResponseEntity.created(
    			uri.path("/users/{id}").buildAndExpand(user.getId()).toUri()).body(userDto);
    }
    
    @PutMapping("/{id}")
    public void send(@PathVariable Long id, @RequestBody @Valid User user) {
    	userService.update(id, user);
    }
    
}
