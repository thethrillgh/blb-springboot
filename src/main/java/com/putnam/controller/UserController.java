package com.putnam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.putnam.model.User;
import com.putnam.repository.UserRepository;
import com.putnam.response.Response;

@RestController
public class UserController {	
	
	@Autowired
	UserRepository userRepo;
	
	public static final String userSessionKey = "user_id";
	
	protected User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        return userId == null ? null : userRepo.findByUserid(userId);
    }
	
	protected void setUserInSession(HttpSession session, User user) {
    	session.setAttribute(userSessionKey, user.getUserid());
    }
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public Response login(@RequestBody User user, HttpSession session) {
		User result = userRepo.findByAcctemailAndSsnlastfour(user.getAcctemail(), user.getSsnlastfour());
		if(User.isMatchingPassword(user.getAcctpass(), result.getPasssalt())) {
			setUserInSession(session, result);
			return new Response("Done", result);
		}
		else {
			return new Response("Failed", user);
		}		
	}
	
	@RequestMapping(value="/user/findall", method = RequestMethod.GET)
	public Response findall() {
		Iterable<User> result = userRepo.findAll();
		return new Response("Done", result);
		
	}
	
	@RequestMapping(value="/hash", method = RequestMethod.GET)
	public String pass() {
		User result = userRepo.findByUserid(2);
		result.setPasssalt("happy12345");
		userRepo.save(result);
		return "Done";
	}
	

	@RequestMapping(value="/user", method = RequestMethod.GET)
	public User user(@RequestParam("id") long id, HttpServletRequest request) {
//		if(id == (long) request.getSession().getAttribute(userSessionKey)) {
			User user = userRepo.findByUserid(id);
			return user;
//		}
//		return null;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/";
	}
}
