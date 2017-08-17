package com.putnam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.putnam.model.Bank;
import com.putnam.model.User;
import com.putnam.repository.BankAccountRepository;
import com.putnam.repository.UserRepository;

@RestController
public class BankController {	
	
	@Autowired
	BankAccountRepository bankRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value="/bank/save", method = RequestMethod.GET)
	public String save() {
		User user = userRepo.findByUserid(1);
		User user1 = userRepo.findByUserid(2);
		User user2 = userRepo.findByUserid(3);
		User user3 = userRepo.findByUserid(4);
		User user4 = userRepo.findByUserid(5);
		User user5 = userRepo.findByUserid(6);
		User user6 = userRepo.findByUserid(7);
		User user7 = userRepo.findByUserid(8);
		bankRepo.save(new Bank("123456789876","123456789","Savings", user));
		bankRepo.save(new Bank("394857209345","938398477","Checking", user1));
		bankRepo.save(new Bank("483539845733","457542768","Checking", user2));
		bankRepo.save(new Bank("435874773097","563701938","Checking", user3));
		bankRepo.save(new Bank("989435739489","900745822","Checking", user4));
		bankRepo.save(new Bank("758376245387","234508547","Checking", user5));
		bankRepo.save(new Bank("587483937777","435202345","Savings", user6));
		bankRepo.save(new Bank("458739274857","593048534","Savings", user7));
		return "Done";
		
	}
	
	@RequestMapping(value="/bank/findall", method = RequestMethod.GET)
	public Iterable<Bank> findall() {
		return bankRepo.findAll();
	}
}
