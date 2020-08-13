package com.messaging.main.Service.ServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.messaging.main.Service.UserService;
import com.messaging.main.model.User;
import com.messaging.main.repository.RoleRepository;
import com.messaging.main.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<User> getUser() {
		return userRepository.findAll();
	}

	@Override
	public User login(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		return user;
	}

	@Override
	public User signup(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
}