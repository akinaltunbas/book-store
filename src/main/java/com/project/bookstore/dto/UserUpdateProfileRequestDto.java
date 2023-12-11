package com.project.bookstore.dto;

import com.project.bookstore.entities.User;

import lombok.Data;

@Data
public class UserUpdateProfileRequestDto {
	
	private String username;
	private String password;
	
	public void mapUserUpdateProfileRequestDto(User user) {
		
		user.setUsername(this.getPassword());
		user.setPassword(this.getPassword());
	}

}
