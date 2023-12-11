package com.project.bookstore.service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.bookstore.entities.RefreshToken;
import com.project.bookstore.entities.User;
import com.project.bookstore.repository.RefreshTokenRepository;
import com.project.bookstore.repository.UserRepository;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{
	
	@Value("${refresh.token.expires.in}")
	Long expireSeconds = 604800L;
	
	private final RefreshTokenRepository refreshTokenRepository;
	
	private final UserRepository userRepository;
	
	public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
		this.userRepository = userRepository;
	}

	@Override
	public String createRefreshToken(User user) {
		RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
		if(token == null) {
			token =	new RefreshToken();
			token.setUser(user);
		}
		token.setToken(UUID.randomUUID().toString());
		token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
		refreshTokenRepository.save(token);
		return token.getToken();
	}

	@Override
	public boolean isRefreshExpired(RefreshToken token) {
		return token.getExpiryDate().before(new Date());
	}
	
	@Override
	public RefreshToken getByUser(Long userId) {
		return refreshTokenRepository.findByUserId(userId);
	}
	
	

}
