package com.putnam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.putnam.model.User;
import com.putnam.repository.UserRepository;
import com.putnam.response.Response;

@RestController
public class UserController {	
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public Response login(@RequestBody User user) {
		User result = userRepo.findByAcctemailAndSsnlastfour(user.getAcctemail(), user.getSsnlastfour());
		return new Response("Done", result);
		
	}
	
	@RequestMapping(value="/user/findall", method = RequestMethod.GET)
	public Response findall() {
		Iterable<User> result = userRepo.findAll();
		return new Response("Done", result);
		
	}
}
