package com.messaging.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messaging.main.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsernameAndPassword(String username, String password);
	public Optional<User> findByUsername(String username);
}
