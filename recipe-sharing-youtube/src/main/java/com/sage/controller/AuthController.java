package com.sage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sage.cnfig.JwtProvider;
import com.sage.model.User;
import com.sage.repository.UserRepository;
import com.sage.request.LoginRequest;
import com.sage.response.AuthRespose;
import com.sage.service.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController { 

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomUserDetailsService customUserDetails;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/signup")
	public AuthRespose createUser(@RequestBody User user)throws Exception{
		
		String email=user.getEmail();
		String password=user.getPassword();
		String fullNmae=user.getFullName();
		
		User isExistEmail=userRepository.findByEmail(email);
		
		if(isExistEmail!=null) {
			throw new Exception("Email is already used with another account");
		}
		
		User creatUser=new User();
		creatUser.setEmail(email);
		creatUser.setPassword(passwordEncoder.encode(password));
		creatUser.setFullName(fullNmae);
		
		User savedUser=userRepository.save(creatUser);
		Authentication authentication=new UsernamePasswordAuthenticationToken(email, password);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtProvider.generateToken(authentication);
		
		AuthRespose res=new AuthRespose();
		
		res.setJwt(token);
		res.setMessage("signup success");
		
		return res;
	}
	
	@PostMapping("/signin")
	public AuthRespose signingHandler(@RequestBody LoginRequest loginRequest) {
		
		String username=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		
		
		Authentication authentication=authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtProvider.generateToken(authentication);
		AuthRespose res=new AuthRespose();
		
		res.setJwt(token);
		res.setMessage("signin success");
		return res;
	}

	
	private Authentication authenticate(String username, String password) {
		
		UserDetails userDetails=customUserDetails.loadUserByUsername(username);
		
		if(userDetails==null) {
			throw new BadCredentialsException("User not found");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("invalid password");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
	
