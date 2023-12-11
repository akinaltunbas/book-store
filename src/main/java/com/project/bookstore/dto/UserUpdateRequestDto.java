package com.project.bookstore.dto;



import com.project.bookstore.entities.Role;
import com.project.bookstore.entities.User;

import lombok.Data;

@Data
public class UserUpdateRequestDto {

	private String username;
	private String password;
	private Role role;
	
	public void mapUserUpdateRequestDto(User user) {
		user.setUsername(this.getPassword());
		user.setPassword(this.getPassword());
		user.setRole(this.getRole());
	}
}
