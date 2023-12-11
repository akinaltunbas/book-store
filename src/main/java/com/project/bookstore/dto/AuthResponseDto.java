package com.project.bookstore.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
	
	private String message;
	private Long userId;
	private String accessToken;
	private String refreshToken;

}
