package com.spring.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.spring.security.domain.User;
import com.spring.security.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Cacheable(cacheNames = "User", key="#root.method.name")
	public List<User> findAll() {
		
		return userRepository.findAll();
	}
	
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	@CacheEvict(cacheNames = "User", allEntries = true)
	public void create(User user) {
		userRepository.save(user);
	}
	
	@CachePut(cacheNames = "User", key="#user.getIdentifier()")
	public void update(Long id, User user) {
		userRepository.save(user);
	}

}
