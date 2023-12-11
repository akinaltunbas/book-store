package com.project.bookstore.service;

import java.util.List;


import com.project.bookstore.dto.UserCreateRequestDto;
import com.project.bookstore.dto.UserUpdateProfileRequestDto;
import com.project.bookstore.dto.UserUpdateRequestDto;
import com.project.bookstore.entities.User;

public interface UserService {
	
	List<User> getAllUsers();
	
	User createOneUser(UserCreateRequestDto newUserRequest);
	
	void deleteOneUserById(Long userId);
	
	User getOneUserById(Long userId);
	
	User updateOneUserById(Long userId, UserUpdateRequestDto updateUserRequest);
	
	User getOneUserByUserName(String username);
	
	User saveOneUser(User user);

	User updateProfile(Long userId, UserUpdateProfileRequestDto updateProfile);
	
}
