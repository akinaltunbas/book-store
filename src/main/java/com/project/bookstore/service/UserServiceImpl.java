package com.project.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.bookstore.dto.UserCreateRequestDto;
import com.project.bookstore.dto.UserUpdateProfileRequestDto;
import com.project.bookstore.dto.UserUpdateRequestDto;
import com.project.bookstore.entities.User;
import com.project.bookstore.exception.UserNotFoundException;
import com.project.bookstore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User createOneUser(UserCreateRequestDto newUserRequest) {
		User user = new User();
		newUserRequest.mapUserCreateRequestDto(user);;
		return userRepository.save(user);
	}

	@Override
	public void deleteOneUserById(Long userId) {
		userRepository.deleteById(userId);
		
	}

	@Override
	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}

	@Override
	public User updateOneUserById(Long userId, UserUpdateRequestDto updateUserRequest) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User user1 = user.get();
			updateUserRequest.mapUserUpdateRequestDto(user1);
			userRepository.save(user1);
			
			return user1;
		}
		return null;
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getOneUserByUserName(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User saveOneUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateProfile(Long userId, UserUpdateProfileRequestDto updateProfile) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User user1 = user.get();
			updateProfile.mapUserUpdateProfileRequestDto(user1);
			userRepository.save(user1);
			
			return user1;
		}
		return null;
	}

		
	

}
