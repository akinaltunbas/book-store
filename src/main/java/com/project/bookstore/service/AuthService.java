package com.project.bookstore.service;


import com.project.bookstore.dto.AuthResponseDto;
import com.project.bookstore.dto.RefreshTokenRequestDto;
import com.project.bookstore.dto.RegisterResponseDto;
import com.project.bookstore.dto.UserLoginRequestDto;
import com.project.bookstore.dto.UserRegisterRequestDto;


public interface AuthService {
	
	AuthResponseDto loginUser(UserLoginRequestDto loginRequest);
	
	RegisterResponseDto registerUser(UserRegisterRequestDto registerRequest);
	
	AuthResponseDto refreshToken(RefreshTokenRequestDto refreshRequest);


}
