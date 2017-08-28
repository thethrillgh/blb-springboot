package com.putnam.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.putnam.model.Bank;
import com.putnam.model.User;
import com.putnam.repository.BankAccountRepository;
import com.putnam.repository.UserRepository;
import com.putnam.response.Failed;
import com.putnam.response.Response;

@RestController
public class BankController {	
	
	@Autowired
	BankAccountRepository bankRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value="/bank/save", method = RequestMethod.GET)
	public Response save(HttpServletRequest request, @RequestBody Bank bank) {
		User user = userRepo.findByUserid((long) request.getSession().getAttribute("user_id"));
		if(user != null) {
			bankRepo.save(new Bank(bank.getAcctnum(), bank.getRoutingnum(), bank.getAccttype(), user));
			return new Response("Done", user);
		}
		return new Response("Failed", new Failed("Not logged in"));
	}
	
	@RequestMapping(value="/bank/findall", method = RequestMethod.GET)
	public Iterable<Bank> findall() {
		return bankRepo.findAll();
	}
}
