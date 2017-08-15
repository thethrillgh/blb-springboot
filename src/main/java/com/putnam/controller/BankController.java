package com.putnam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.putnam.model.Bank;
import com.putnam.model.User;
import com.putnam.repository.BankAccountRepository;
import com.putnam.repository.UserRepository;
import com.putnam.response.Response;

@RestController
public class BankController {	
	
	@Autowired
	BankAccountRepository bankRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value="/bank/save", method = RequestMethod.GET)
	public Response save() {
		User user = userRepo.findByAcctemailAndSsnlastfour("yourefired@putin.com", "6789");
		Bank result = bankRepo.save(new Bank("1234", "000", "Debit", user));
		return new Response("Done", result);
		
	}
}
