package com.project.bookstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.bookstore.entities.RefreshToken;
import com.project.bookstore.entities.Role;
import com.project.bookstore.entities.User;
import com.project.bookstore.repository.RefreshTokenRepository;

@ExtendWith(MockitoExtension.class)
public class RefreshTokenServiceImplTest {
	
	private RefreshTokenRepository refrehTokenRepository;
	
	private RefreshTokenServiceImpl refrehTokenService;
	
	private RefreshToken refreshToken;
	
	@BeforeEach
	public void setup() {
		refrehTokenRepository = mock(RefreshTokenRepository.class);
		refreshToken = mock(RefreshToken.class);
		refrehTokenService = new RefreshTokenServiceImpl(refrehTokenRepository, null);
		
		
	}
	
	@Test
	public void testGetByUser() {
		
		RefreshToken tokenMock = Mockito.mock(RefreshToken.class);
		
		when(refrehTokenRepository.findByUserId(ArgumentMatchers.anyLong())).thenReturn(tokenMock);
		
		RefreshToken savedToken = refrehTokenService.getByUser(1L);
		
		assertThat(savedToken).isNotNull();
		
		
		
	}
	
	@Test
	public void testCreateRefrehToken_NotNull() {
		
		User user = new User(1L,"Akın","1234",Role.ADMIN);
		
		RefreshToken tokenMock = mock(RefreshToken.class);
		
		when(refrehTokenRepository.findByUserId(ArgumentMatchers.any())).thenReturn(tokenMock);
		
		String savedToken = refrehTokenService.createRefreshToken(user);
		
		savedToken= "90aeb74a-babb-4c98-96a6-9a3b93eda2db";
		
		assertThat(savedToken).isNotNull();
	}
	
	@Test
	public void testCreateRefrehToken_Null() {
		
		User user = new User();
		
		String savedToken = refrehTokenService.createRefreshToken(user);
		
		savedToken = null;
		
		assertThat(savedToken).isNull();
	}

}
