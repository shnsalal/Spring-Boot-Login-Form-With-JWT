package com.messaging.main.Service.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.messaging.main.model.MyUserDetails;
import com.messaging.main.model.User;
import com.messaging.main.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		
		user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + username));
		
		return user.map(MyUserDetails::new).get();
		
//		return new User("foo", "foo", new ArrayList<>());
	}

}
