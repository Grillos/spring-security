package com.spring.security.dto;

import com.spring.security.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
	
	public UserDto() {}
	
	public UserDto(User user) {
		this.id = user.getId();
		this.username = user.getPassword();
		this.password = user.getPassword();
	}
	
    private Long id;
	
	private String username;
	
	private String password;
	
}
