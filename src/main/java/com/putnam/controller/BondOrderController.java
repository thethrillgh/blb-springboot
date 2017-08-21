package com.putnam.controller;

import java.util.ArrayList;
import java.util.Date;

import com.putnam.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.putnam.model.Bond;
import com.putnam.model.BondOrder;
import com.putnam.model.User;
import com.putnam.repository.BondOrderRepository;
import com.putnam.repository.BondRepository;
import com.putnam.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BondOrderController {	
	
	@Autowired
	UserRepository userRepo;

	UserController userCont;
	
	@Autowired
	BondRepository bondRepo;
	
	@Autowired
	BondOrderRepository bondOrderRepo;
	
	@RequestMapping(value="/order/save", method = RequestMethod.GET)
	public void save() {
		Bond bond = bondRepo.findByCusip("912828X88");
		User user = userRepo.findByUserid(1);
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),100000.0,120.050,100120.050,100, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),5000.0,85.090,85.090,50, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),1000000.0,2300.390,1002300.390,1000, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),10000.0,150.430,10150.430,100, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),500000.0,0.000,500000.000,500, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),100000.0,1020.220,101020.220,100, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),10000000.0,0.000,10000000.000,1000, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),50000.0,45.040,50045.040,50, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),100000.0,305.780,100305.780,1000, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),100000.0,120.050,100120.050,100, bond, user));
	}

	@RequestMapping(value = "/order/buy", method = RequestMethod.GET)
	public Response buyBond(@RequestParam("id") long id, @RequestParam("quantity") int quant, HttpServletRequest req){
		//ID in param is the bondID
		Bond bondToBuy = bondRepo.findByBondid(id);

		long userid = (long) req.getSession().getAttribute("user_id");
		User buyer = userRepo.findByUserid(userid);

		if(bondToBuy != null && buyer != null){

			double princ = bondToBuy.getMarketprice();

			double totalPrincipal = princ * quant;

			double interestOnPurchase = computeInterest(bondToBuy.getFacevalue(), bondToBuy.getInterestrate(), bondToBuy.getIssuedate(), new Date());

			double orderTotal = totalPrincipal + interestOnPurchase;

			double newBalance = buyer.getAcctbalance() - orderTotal;

			if(newBalance >= 0.00) {

				buyer.setAcctbalance(newBalance);

				BondOrder order = new BondOrder(new Date(), new Date(), new Date(), totalPrincipal, interestOnPurchase, orderTotal, quant, bondToBuy, buyer);

				bondOrderRepo.save(order);

				return new Response("Success", order);
			}
		}

		return new Response("Fail", bondToBuy);
	}

	@RequestMapping(value = "/order/sell", method = RequestMethod.GET)
	public Response sellBond(@RequestParam("id") long id, @RequestParam("quantity") int quant, HttpServletRequest req){

		//ID in param is the bondID
		Bond bondToSell = bondRepo.findByBondid(id);

		long userid = (long) req.getSession().getAttribute("user_id");

		User buyer = userRepo.findByUserid(userid);

		if(bondToSell != null && buyer != null){
			return new Response("Success", new BondOrder());
		}

		return new Response("Fail", bondToSell);
	}

	public int daysBetween(Date d1, Date d2){
		return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	public double computeInterest(double fvalue, double coupon, Date d1, Date d2){

		int numDays = daysBetween(d1, d2);

		double rate = coupon / 100; //convert 6.5 to .065 for example

		double dailyInt = fvalue * rate;

		return dailyInt*numDays;
	}

}
