package com.project.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookstore.dto.AuthResponseDto;
import com.project.bookstore.dto.RefreshTokenRequestDto;
import com.project.bookstore.dto.RegisterResponseDto;
import com.project.bookstore.dto.UserLoginRequestDto;
import com.project.bookstore.dto.UserRegisterRequestDto;
import com.project.bookstore.service.AuthServiceImpl;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private AuthServiceImpl authService;
	
	
	public AuthController(AuthServiceImpl authService) {

		this.authService = authService;
	}
	
	@PostMapping("/register") 
		public ResponseEntity<RegisterResponseDto> register(@RequestBody UserRegisterRequestDto registerRequest) {
			return new ResponseEntity<>(authService.registerUser(registerRequest), HttpStatus.CREATED);
		}
	@PostMapping("/login")
	public ResponseEntity<AuthResponseDto> login(@RequestBody UserLoginRequestDto loginRequest) {
		return new ResponseEntity<>(authService.loginUser(loginRequest), HttpStatus.OK);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<AuthResponseDto> refresh(@RequestBody RefreshTokenRequestDto refreshRequest) {
			return new ResponseEntity<>(authService.refreshToken(refreshRequest), HttpStatus.OK);		
	} 	
	

}
