package com.darkroom.main.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.darkroom.main.Service.UserService;
import com.darkroom.main.exception.CustomException;
import com.darkroom.main.exception.ErrorResponse;
import com.darkroom.main.model.User;
import com.darkroom.main.util.AuthenticationRequest;
import com.darkroom.main.util.AuthenticationResponse;
import com.darkroom.main.util.JwtUtil;



@RequestMapping("/api")
@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@GetMapping("/get")
	public List<User> get() {
		return userService.getUser();
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> CreateAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
	
	@GetMapping("/getUsername/{username}")
	public Optional<User> getId(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}
	 
	 @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody User user) throws CustomException {
	        
	        String username = user.getUsername();
	        String password = user.getPassword();
	        
	        User u = userService.login(username, password);
	        if(u == null) {
	        	throw new CustomException("The username/password specified is not valid.");
	        }
	        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.login(username, password));
	    }
	 

	    @PostMapping("/register")
	    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result) throws CustomException {
	       
	    	if(result.hasErrors()) {
	    		List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
	            return ResponseEntity.badRequest().body(new ErrorResponse("400", "Validation failure", errors));
	        }
	        
	        Optional<User> u = userService.getUserByUsername(user.getUsername());
	        
	        if(u.isPresent()) {
	        	return ResponseEntity.badRequest().body(new ErrorResponse("400", "Validation failure", "The username " + u.get().getUsername() + " already exists. Please use a different username."));
	        } else {
	        	return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(user));
	        }
	        
	        
	    }
}
