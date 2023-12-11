package com.project.bookstore.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookstore.dto.UserUpdateProfileRequestDto;
import com.project.bookstore.dto.constants.ResponseMessages;
import com.project.bookstore.service.UserServiceImpl;

@RestController
@RequestMapping("/user/profile")
public class UserProfileController {

	private UserServiceImpl userService;

	public UserProfileController(UserServiceImpl userService) {
		this.userService = userService;
	}
	

	@PutMapping("/updateProfile/{userId}")
	public ResponseEntity<String> updateProfileUser(@PathVariable Long userId, @RequestBody UserUpdateProfileRequestDto updateUserProfileReqest) {
		userService.updateProfile(userId, updateUserProfileReqest);
		return ResponseEntity.ok(ResponseMessages.PROFİLE_UPDATED.getMessage());
	}
	
	
	
}
