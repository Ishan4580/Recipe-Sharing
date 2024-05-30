package com.sage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sage.model.User;
import com.sage.service.UserService;

@RestController
@ResponseBody
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user=userService.findUserByJwt(jwt);
		
		return user;
		
	}

	
	

}
