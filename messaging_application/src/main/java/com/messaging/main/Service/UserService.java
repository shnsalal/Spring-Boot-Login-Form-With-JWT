package com.messaging.main.Service;

import java.util.List;
import java.util.Optional;

import com.messaging.main.model.User;

public interface UserService {
	public List<User> getUser();
	public User login(String username, String password);
	public User signup(User user);
	public Optional<User> getUserByUsername(String username);
}
