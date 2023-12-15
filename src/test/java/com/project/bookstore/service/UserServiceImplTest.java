package com.project.bookstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.bookstore.dto.UserCreateRequestDto;
import com.project.bookstore.dto.UserUpdateProfileRequestDto;
import com.project.bookstore.dto.UserUpdateRequestDto;
import com.project.bookstore.entities.Role;
import com.project.bookstore.entities.User;
import com.project.bookstore.exception.UserNotFoundException;
import com.project.bookstore.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	private UserRepository userRepository;
	
	private UserServiceImpl userService;
	
	private User user;
	
	@BeforeEach
	public void setup() {
		
		userRepository = mock(UserRepository.class);
		user = mock(User.class);
		userService = new UserServiceImpl(userRepository);
		
	}
	
	@Test
	public void testGetAllUsers_itShouldReturnUsersList() {
		
		User user = new User(1L,"Akın","1234",Role.ADMIN);
		
		List<User> userList = Collections.singletonList(user);
		
		when(userRepository.findAll()).thenReturn(userList);
		
		List<User> result = userService.getAllUsers();
		
		assertEquals(userList,result);
	}
	
	@Test
	public void testGetUserById_whenUserIdExist_İtShouldReturnUser() {
		
		Long userId = 1L;
		
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		
		User result = userService.getOneUserById(userId);
		
		assertEquals(user,result);
	}
	
	@Test
	public void testGetUserById_whenUserDoesNotIdExist_itShouldThrowUserNotFoundException() {
		
		Long userId = 1L;
		
		when(userRepository.findById(userId)).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class, () -> userService.getOneUserById(userId));
	}
	
	@Test
	public void testDeleteUserById_whenUserByIdExist_itShouldDeleteUser() {
		
		User user = new User(1L,"Akın","1234",Role.ADMIN);
		
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		
		userService.deleteOneUserById(1L);
	}
	
	@Test
	public void testDeleteUserById_whenUserDoesNotIdExist_itShouldThrowUserNotFoundException() {
		
		Long userId = 1L;
		
		when(userRepository.findById(userId)).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class, () -> userService.deleteOneUserById(userId));
	}
	
	@Test
	public void testCreateUser_itShouldReturnCreadetUserRequestDto() {
		
		UserCreateRequestDto userCreateDto = new UserCreateRequestDto();
		userCreateDto.setUsername("Akın");
		userCreateDto.setPassword("1234");
		userCreateDto.setRole(Role.ADMIN);
		
		User savedUser = new User(1L,"Akın","1234",Role.ADMIN);
		when(userRepository.save(any())).thenReturn(savedUser);
		
		User saveUser = userService.createOneUser(userCreateDto);
		
		assertEquals(saveUser.getId(),1L);
	}
	
	@Test
	public void testgetOneUserByUserName_itShouldReturnUser() {
		
		String username = "Akın";
		
		when(userRepository.findByUsername(username)).thenReturn(user);
		
		User result = userService.getOneUserByUserName(username);
		
		assertEquals(user,result);
		
	}
	
	@Test
	public void testSaveOneUser_itShouldReturnUser() {
		
		when(userRepository.save(user)).thenReturn(user);
		
		User result = userService.saveOneUser(user);
		
		assertEquals(user,result);
	}
	
	@Test
	public void testUpdateUser_itShouldReturnUpdateUserRequestDto() {
		
		User user = new User(1L,"Akın","1234",Role.ADMIN);
		UserUpdateRequestDto updateUserRequest = new UserUpdateRequestDto();
		Long userId = 1L;
		
		given(userRepository.findById(1L)).willReturn(Optional.of(user));
		given(userRepository.save(user)).willReturn(user);
		
		updateUserRequest.setUsername("Akito");
		updateUserRequest.setPassword("1234");
		updateUserRequest.setRole(Role.ADMIN);
		
		User updateUser = userService.updateOneUserById(userId, updateUserRequest);
		
		assertThat(updateUser.getUsername()).isEqualTo("Akito");
		assertThat(updateUser.getPassword()).isEqualTo("abcd");
		assertThat(updateUser.getRole()).isEqualTo(Role.ADMIN);
		
	}
	
	@Test
	public void updateUser_whenUserDoesNotExist_itShouldThrowuserNotFoundException() {
		
		Long userId= 1L;
		UserUpdateRequestDto updateUserRequest = new UserUpdateRequestDto();
		
		when(userRepository.findById(userId)).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class, () -> userService.updateOneUserById(userId, updateUserRequest));
	}
	
	@Test
	public void testUpdateProfile_itShouldReturnUserUpdateProfileRequestDto() {
		
		User user = new User(1L,"Akın","1234",Role.ADMIN);
		UserUpdateProfileRequestDto updateProfileRequest = new UserUpdateProfileRequestDto();
		Long userId = 1L;
		
		given(userRepository.findById(1L)).willReturn(Optional.of(user));
		given(userRepository.save(user)).willReturn(user);
		
		updateProfileRequest.setUsername("Akito");
		updateProfileRequest.setPassword("1234");
		
		User updateProfile = userService.updateProfile(userId, updateProfileRequest);
		
		assertThat(updateProfile.getUsername()).isEqualTo("Akito");
		assertThat(updateProfile.getPassword()).isEqualTo("abcd");
		
		
	}
	
	@Test
	public void updateProfile_whenUserDoesNotExist_itShouldThrowuserNotFoundException() {
		
		Long userId= 1L;
		UserUpdateProfileRequestDto updateProfileRequest = new UserUpdateProfileRequestDto();
		
		when(userRepository.findById(userId)).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class, () -> userService.updateProfile(userId, updateProfileRequest));
	}
}
