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
import com.putnam.response.Failed;
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
		if(result != null) {
			if(User.isMatchingPassword(user.getAcctpass(), result.getPasssalt())) {
				setUserInSession(session, result);
				return new Response("Done", result);
			}
			else {
				return new Response("Failed", new Failed("Wrong Password"));
			}
		}
		return new Response("Failed", new Failed("Entered wrong email or SSN"));
	}

	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public Response signup(@RequestBody User user) {
		User prev = userRepo.findByAcctemail(user.getAcctemail());
		if(prev != null) {
			if(userRepo.exists(prev.getUserid())) {
				return new Response("Fail", new Failed("Email account already exists"));

			}
		}
		User newUser = new User(user);
		newUser.setPasssalt(newUser.getAcctpass());
		userRepo.save(newUser);
		return new Response("Done", newUser);
	}


	@RequestMapping(value="/user/findall", method = RequestMethod.GET)
	public Response findall() {
		Iterable<User> result = userRepo.findAll();
		return new Response("Done", result);

	}

	@RequestMapping(value="/hash", method = RequestMethod.GET)
	public String pass() {
		User result = userRepo.findByUserid(1);
		result.setPasssalt("happy12345");
		userRepo.save(result);
		return "Done";
	}


	@RequestMapping(value="/user", method = RequestMethod.GET)
	public Response user(HttpServletRequest request, @RequestParam("id") long id) {
			User user = null;
			if(id != 0) {
				user = userRepo.findByUserid(id);
			}
			else {
				return new Response("Failed",  new Failed("Not logged in"));
			}
			return new Response("Done", user);
	}

	@RequestMapping(value="/userbonds", method = RequestMethod.GET)
	public Response userBonds(HttpServletRequest request) {
			User user = userRepo.findByUserid((long) request.getSession().getAttribute(userSessionKey));
			if(user == null) {
				return new Response("Failed",  new Failed("Unable to find user"));
			}
			return new Response("Done", user.getOrders().get(0));
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request){
        request.getSession().invalidate();
	}

	@RequestMapping(value = "/session", method = RequestMethod.GET)
	public Object session(HttpServletRequest request){
		return request.getSession().getAttribute(userSessionKey);
	}

	@RequestMapping(value = "/resetpass", method = RequestMethod.GET)
	public Response resetUserPassword(@RequestParam("old") String oldPass, @RequestParam("new") String newPass, HttpServletRequest req){

		Long userid = (Long) req.getSession().getAttribute("user_id");

		if(userid != null){

			User user = userRepo.findByUserid(userid);

			if(user != null){

				if(User.isMatchingPassword(oldPass, user.getPasssalt())){

					user.setPasssalt(newPass);

					userRepo.save(user);

					return new Response("Success", "Password Reset Successful");
				}
				return new Response("Fail", new Failed("Wrong credentials, reset failed"));
			}
		}
		return new Response("Fail", new Failed("Cannot locate user"));
	}
	
}
