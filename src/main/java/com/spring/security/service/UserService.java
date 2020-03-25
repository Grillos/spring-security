package com.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.spring.security.domain.User;
import com.spring.security.dto.UserDto;
import com.spring.security.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<UserDto> findAll(int page, int size) {		
		Page<User> users = userRepository.findAll(PageRequest.of(page, size));
		return users.map(UserDto::new);
	}
	
	public UserDto findById(Long id) {
		return new UserDto(userRepository.getOne(id));
	}
	
	public UserDto create(User user) {
		return new UserDto(userRepository.save(user));
	}
	
	public void update(Long id, User user) {
		userRepository.save(user);
	}

}
