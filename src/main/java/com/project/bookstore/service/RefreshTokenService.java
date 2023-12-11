package com.project.bookstore.service;

import com.project.bookstore.entities.RefreshToken;
import com.project.bookstore.entities.User;

public interface RefreshTokenService {
	
	String createRefreshToken(User user);
	
	boolean isRefreshExpired(RefreshToken token);
	
	RefreshToken getByUser(Long userId);
	
	

}
