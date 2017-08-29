package com.putnam.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	
	@RequestMapping(value="/bank/save", method = RequestMethod.POST)
	public Response save(HttpServletRequest request, @RequestBody Bank bank) {
		Object userid = request.getSession().getAttribute("user_id");
		if(userid != null) {
			User user = userRepo.findByUserid((long) userid);
			if(user != null) {
				bankRepo.save(new Bank(bank.getAcctnum(), bank.getRoutingnum(), bank.getAccttype(), user));
				return new Response("Done", user);
			}
		}
		return new Response("Failed", new Failed("Not logged in"));
	}
	
	@RequestMapping(value="/bank/findall", method = RequestMethod.GET)
	public Iterable<Bank> findall() {
		return bankRepo.findAll();
	}

	@RequestMapping(value = "/bank/deposit", method = RequestMethod.GET)
	public Response addFundsToAccount(@RequestParam("amt") double amount, HttpServletRequest req){

		Long userid = (Long) req.getSession().getAttribute("user_id");

		if(userid != null){

			User user = userRepo.findByUserid(userid);

			if(user != null){
				double oldBal = user.getAcctbalance();

				user.setAcctbalance(oldBal+amount);
				
				if(user.getTotalinvested() == null) {
					user.setTotalinvested(0.0);
					user.setTotalinvested(user.getTotalinvested()+amount);
				}
				user.setTotalinvested(user.getTotalinvested()+amount);
				userRepo.save(user);

				return new Response("Success", "Account balance updated");
			}
		}
		return new Response("Fail", new Failed("Cannot locate user"));
	}

	@RequestMapping(value = "/bank/withdraw", method = RequestMethod.GET)
	public Response removeFundsFromAccount(@RequestParam("amt") double amount, HttpServletRequest req){
		Long userid = (Long) req.getSession().getAttribute("user_id");

		if(userid != null){

			User user = userRepo.findByUserid(userid);

			if(user != null){
				double oldBal = user.getAcctbalance();

				if(amount <= oldBal) {
					user.setAcctbalance(oldBal - amount);

					user.setTotalinvested(user.getTotalinvested() - amount);

					userRepo.save(user);

					return new Response("Success", "Account balance updated");
				}
				return new Response("Fail", "Cannot withdraw more funds than you have");
			}
		}
		return new Response("Fail", new Failed("Cannot locate user"));
	}

}
