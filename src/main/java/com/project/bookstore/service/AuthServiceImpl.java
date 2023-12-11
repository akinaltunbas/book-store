package com.project.bookstore.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.bookstore.dto.AuthResponseDto;
import com.project.bookstore.dto.RefreshTokenRequestDto;
import com.project.bookstore.dto.RegisterResponseDto;
import com.project.bookstore.dto.UserLoginRequestDto;
import com.project.bookstore.dto.UserRegisterRequestDto;
import com.project.bookstore.dto.constants.ResponseMessages;
import com.project.bookstore.entities.RefreshToken;
import com.project.bookstore.entities.User;
import com.project.bookstore.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService{

	private AuthenticationManager authenticationManager;
	
	private JwtTokenProvider jwtTokenProvider;
	
	private UserServiceImpl userService;
	
	private PasswordEncoder passwordEncoder;
	
	private RefreshTokenServiceImpl refreshTokenService;
	
	
	


	public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
			UserServiceImpl userService, PasswordEncoder passwordEncoder, RefreshTokenServiceImpl refreshTokenService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.refreshTokenService = refreshTokenService;
	}

	@Override
	public AuthResponseDto loginUser(UserLoginRequestDto loginRequest) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		User user = userService.getOneUserByUserName(loginRequest.getUsername());
		AuthResponseDto authResponse = new AuthResponseDto();
		authResponse.setMessage(ResponseMessages.LOGIN_SUCCESS.getMessage());
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
		authResponse.setUserId(user.getId());
		return authResponse;
		
	}

	@Override
	public RegisterResponseDto registerUser(UserRegisterRequestDto registerRequest) {
		RegisterResponseDto authResponse = new RegisterResponseDto();
		if(userService.getOneUserByUserName(registerRequest.getUsername()) != null) {
			authResponse.setMessage(ResponseMessages.USER_IN_USE.getMessage());
			return authResponse;
		}
		
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setRole(registerRequest.getRole());
		userService.saveOneUser(user);
		
		authResponse.setMessage(ResponseMessages.REGISTERED.getMessage());
		authResponse.setUserId(user.getId());
		return authResponse;		
	}

	@Override
	public AuthResponseDto refreshToken(RefreshTokenRequestDto refreshRequest) {
		AuthResponseDto response = new AuthResponseDto();
		RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
		if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
				!refreshTokenService.isRefreshExpired(token)) {

			User user = token.getUser();
			String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
			response.setMessage(ResponseMessages.TOKEN_REFRESHED.getMessage());
			response.setAccessToken("Bearer " + jwtToken);
			response.setUserId(user.getId());
				
		} else {
			response.setMessage(ResponseMessages.TOKEN_NOT_VALID.getMessage());
		}
		return response;
		
	}

}
