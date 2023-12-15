package com.project.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.bookstore.entities.Role;
import com.project.bookstore.entities.User;
import com.project.bookstore.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {
	
	private UserRepository userRepository;
	
	private UserDetailsServiceImpl userService;
	
	private User user;
	
	@BeforeEach
	public void setup() {
		userRepository = mock(UserRepository.class);
		user = mock(User.class);
		userService = new UserDetailsServiceImpl(userRepository);
	}
	
	@Test
	public void testLoadUserByUsername() {
		
		User user = new User(1L,"Akın","1234",Role.ADMIN);
		user.setUsername("Akın");
		
		when(userRepository.findByUsername("Akın")).thenReturn(user);
		userService.loadUserByUsername("Akın");
		
		assertEquals("Akın",user.getUsername());
	}
	
	@Test
	public void testLoadUserById() {
		
		User user = new User(1L,"Akın","1234",Role.ADMIN);
		user.setId(1L);
		
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		userService.loadUserById(1L);
		
		assertEquals(1L,user.getId());
	}
}
