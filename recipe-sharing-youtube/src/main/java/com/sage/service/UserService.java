package com.sage.service;

import org.springframework.context.annotation.ComponentScan;

import com.sage.model.User;
@ComponentScan
public interface UserService {

	public User findUserById(Long userId)throws Exception;
	
	public User findUserByJwt(String jwt)throws Exception;
}
