package com.sage.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sage.cnfig.JwtProvider;
import com.sage.model.User;
import com.sage.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> opt = userRepository.findById(userId);
		if(opt.isPresent())
		{
			return opt.get();
		}
		
		throw new Exception("User not found with id "+userId);
	}

	@Override
	public User findUserByJwt(String jwt) throws Exception {
	
		String email=jwtProvider.getEmailFromJwtToken(jwt);
		
		if(email==null) {
			throw new Exception("Provide valid jwt token...");
		}
		
		User user=userRepository.findByEmail(email);
		
		if(user==null) {
			throw new Exception("User not found with email "+email);
		}
		
		return user;
	}
}
