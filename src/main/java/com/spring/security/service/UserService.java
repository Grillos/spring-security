package com.spring.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.domain.User;
import com.spring.security.dto.UserDto;
import com.spring.security.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDto> findAll() {		
		List<User> usersTmp = userRepository.findAll();
		List<UserDto> usersDto = usersTmp
				.stream()
				.map(UserDto::new)
				.collect(Collectors.toList());
		
		return usersDto;
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
