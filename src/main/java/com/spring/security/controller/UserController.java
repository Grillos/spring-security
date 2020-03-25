package com.spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.domain.User;
import com.spring.security.service.UserService;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	 
    @GetMapping
    public List<User> findAll() {
    	return userService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
    	User user = userService.findById(id).orElse(null);
    	return (user != null) ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody User user) {
    	userService.create(user);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public void send(@PathVariable Long id, @RequestBody User user) {
    	userService.update(id, user);
    }
    
}
