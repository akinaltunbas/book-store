package com.project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bookstore.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	 User findByUsername(String username);
}
