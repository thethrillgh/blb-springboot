package com.putnam.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	
	@Autowired
	BondRepository bondRepo;
	
	@Autowired
	BondOrderRepository bondOrderRepo;
	
	@RequestMapping(value="/order/save", method = RequestMethod.GET)
	public void save() {
		Bond bond = bondRepo.findByCusip("912828X88");
		User user = userRepo.findByUserid(1);
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),100000.0,120.050,100120.050,100, BondOrder.BUY, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),5000.0,85.090,85.090,50, BondOrder.BUY, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),1000000.0,2300.390,1002300.390,1000, BondOrder.BUY, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),10000.0,150.430,10150.430,100, BondOrder.SELL, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),500000.0,0.000,500000.000,500, BondOrder.BUY, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),100000.0,1020.220,101020.220,100, BondOrder.SELL, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),10000000.0,0.000,10000000.000,1000, BondOrder.BUY, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),50000.0,45.040,50045.040,50, BondOrder.SELL, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),100000.0,305.780,100305.780,1000, BondOrder.BUY, bond, user));
		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),100000.0,120.050,100120.050,100, BondOrder.SELL, bond, user));
	}
	
	@RequestMapping(value="/order/all", method = RequestMethod.GET)
	public String findAll() {
		List<BondOrder> orders = (List<BondOrder>) bondOrderRepo.findAll();
		String order = ((BondOrder) orders.get(0)).getBond().getCusip();
		return order;
	}

	@RequestMapping(value = "/order/buy", method = RequestMethod.GET)
	public Response buyBond(@RequestParam("id") long id, @RequestParam("quantity") int quant, HttpServletRequest req){
		//ID in param is the bondID
		Bond bondToBuy = bondRepo.findByBondid(id);

		long userid = (long) req.getSession().getAttribute("user_id");
		User buyer = userRepo.findByUserid(userid);

		if(bondToBuy != null && buyer != null){

			Date td = new Date();
			Date sd = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(sd);
			if(c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
				c.add(Calendar.DATE, 3);
			}
			else{
				c.add(Calendar.DATE, 1); //US Treasury Bonds have a settlement date of +1 Business day
			}
			sd = c.getTime();

			double princ = bondToBuy.getMarketprice();

			double totalPrincipal = princ * quant;

			double interestOnPurchase = computeInterest(bondToBuy.getFacevalue(), bondToBuy.getInterestrate(), bondToBuy.getIssuedate(), sd);

			double orderTotal = totalPrincipal + interestOnPurchase;

			double newBalance = buyer.getAcctbalance() - orderTotal;

			if(newBalance >= 0.00) {

				buyer.setAcctbalance(newBalance);

				BondOrder order = new BondOrder(td, td, sd, totalPrincipal, interestOnPurchase, orderTotal, quant, BondOrder.BUY, bondToBuy, buyer);

				bondOrderRepo.save(order);

				return new Response("Success", order);
			}
		}
		return new Response("Fail", bondToBuy);
	}

	@RequestMapping(value = "/order/sell", method = RequestMethod.GET)
	public Response sellBond(@RequestParam("id") long id, @RequestParam("quantity") int quant, HttpServletRequest req){
		/**
		 * Assumes sell functionality only exists on bonds in the user's portfolio with quantities
		 */
		//ID in param is the bondID
		Bond bondToSell = bondRepo.findByBondid(id);

		long userid = (long) req.getSession().getAttribute("user_id");

		User seller = userRepo.findByUserid(userid);

		if(bondToSell != null && seller != null){

			ArrayList<BondOrder> orders = bondOrderRepo.findByBondAndUser(bondToSell, seller);

			if(orders != null && !orders.isEmpty()){ //could get around double check with instantiation?

				if(computeQuantityOwned(orders) >= quant) {

					Date td = new Date();
					Date sd = new Date();
					Calendar c = Calendar.getInstance();
					c.setTime(sd);
					if(c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
						c.add(Calendar.DATE, 3);
					}
					else{
						c.add(Calendar.DATE, 1); //US Treasury Bonds have a settlement date of +1 << Business >> day
					}
					sd = c.getTime();

					double princ = bondToSell.getMarketprice();

					double totalPrincipal = princ * quant;

					double interestOnPurchase = computeInterest(bondToSell.getFacevalue(), bondToSell.getInterestrate(), bondToSell.getIssuedate(), sd);

					double orderTotal = totalPrincipal + interestOnPurchase;

					double newBalance = seller.getAcctbalance() + orderTotal;

					BondOrder order = new BondOrder(td, td, sd, totalPrincipal, interestOnPurchase, orderTotal, quant, BondOrder.SELL, bondToSell, seller);

					bondOrderRepo.save(order);

					seller.setAcctbalance(newBalance);

					return new Response("Success", order);
				}
			}
		}
		return new Response("Fail", bondToSell);
	}

	public int daysBetween(Date d1, Date d2){
		return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	/**
	 *  d1 should be issue date and d2 should be the settlement date NOT buy date
	 * @param fvalue
	 * @param coupon
	 * @param d1
	 * @param d2
	 * @return
	 */
	public double computeInterest(double fvalue, double coupon, Date d1, Date d2){

		int numDays = daysBetween(d1, d2);

		double rate = coupon / 100; //convert 6.5 to .065 for example

		double dailyInt = fvalue * rate;

		return dailyInt*numDays;
	}

	public int computeQuantityOwned(ArrayList<BondOrder> orders){
		int total = 0;
		for(BondOrder order : orders){
			String type = order.getTransactiontype();
			if(type.equalsIgnoreCase("BUY")){
				total += order.getNumbondspurchased();
			}
			else if(type.equalsIgnoreCase("SELL")){
				total -= order.getNumbondspurchased();
			}
		}
		return total;
	}

}
