package com.project.bookstore.dto;

import javax.validation.constraints.NotBlank;

import com.project.bookstore.entities.Role;
import com.project.bookstore.entities.User;

import lombok.Data;

@Data
public class UserRegisterRequestDto {
	
	@NotBlank(message="Name cannot be empty")
	private String username;
	
	@NotBlank(message="Password cannot be empty")
	private String password;
	
	@NotBlank(message="Role cannot be empty")
	private Role role;
	
	
	public void mapUserRegisterRequestDto(User user) {
		
		user.setUsername(this.getUsername());
		user.setPassword(this.getPassword());
		user.setRole(this.getRole());
		
	}

}
