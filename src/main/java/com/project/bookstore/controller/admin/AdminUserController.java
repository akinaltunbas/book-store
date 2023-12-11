package com.project.bookstore.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookstore.dto.UserCreateRequestDto;
import com.project.bookstore.dto.UserUpdateRequestDto;
import com.project.bookstore.dto.constants.ResponseMessages;

import com.project.bookstore.entities.User;
import com.project.bookstore.service.UserServiceImpl;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
	
	private UserServiceImpl userService;

	public AdminUserController(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<String> createOneUser(@Valid @RequestBody UserCreateRequestDto newUserRequest) {
		userService.createOneUser(newUserRequest);
		return ResponseEntity.ok(ResponseMessages.USER_CREATED.getMessage());
		
	}
	
	@GetMapping("/listUser")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
		
	}
	
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<User> getOneBook(@PathVariable Long userId){
		return new ResponseEntity<>(userService.getOneUserById(userId), HttpStatus.OK);
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<String> updateOneUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDto updateUserReqest) {
		userService.updateOneUserById(userId, updateUserReqest);
		return ResponseEntity.ok(ResponseMessages.USER_UPDATED.getMessage());
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<String> deleteOneBook(@PathVariable  Long userId) {
		userService.deleteOneUserById(userId);
		return ResponseEntity.ok(ResponseMessages.USER_DELETED.getMessage());
	}
	
	

}
