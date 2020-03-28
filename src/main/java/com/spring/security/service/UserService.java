package com.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.domain.User;
import com.spring.security.dto.UserDto;
import com.spring.security.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<UserDto> findAll(Pageable pageable) {		
		Page<User> users = userRepository.findAll(pageable);
		return users.map(UserDto::new);
	}
	
	public UserDto findById(Long id) {
		return new UserDto(userRepository.getOne(id));
	}
	
	public UserDto create(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return new UserDto(userRepository.save(user));
	}
	
	public void update(Long id, User user) {
		userRepository.save(user);
	}

}
