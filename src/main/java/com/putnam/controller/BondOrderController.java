package com.putnam.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.putnam.model.TransactionHistory;
import com.putnam.repository.TransactionHistoryRepository;
import com.putnam.response.Response;
import com.putnam.response.Failed;
import org.hibernate.Transaction;
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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BondOrderController {	
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BondRepository bondRepo;
	
	@Autowired
	BondOrderRepository bondOrderRepo;

	@Autowired
	TransactionHistoryRepository thRepo;
	
	@RequestMapping(value="/order/save", method = RequestMethod.GET)
	public void save() {
		Bond bond = bondRepo.findByCusip("912828X88");
		User user = userRepo.findByUserid(1);
//		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),100000.0,120.050,100120.050,100, BondOrder.BUY, bond, user));
//		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),5000.0,85.090,85.090,50, BondOrder.BUY, bond, user));
//		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),1000000.0,2300.390,1002300.390,1000, BondOrder.BUY, bond, user));
//		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),10000.0,150.430,10150.430,100, BondOrder.SELL, bond, user));
//		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),500000.0,0.000,500000.000,500, BondOrder.BUY, bond, user));
//		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),100000.0,1020.220,101020.220,100, BondOrder.SELL, bond, user));
//		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),10000000.0,0.000,10000000.000,1000, BondOrder.BUY, bond, user));
//		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),50000.0,45.040,50045.040,50, BondOrder.SELL, bond, user));
//		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),100000.0,305.780,100305.780,1000, BondOrder.BUY, bond, user));
//		bondOrderRepo.save(new BondOrder(new Date(),new Date(),new Date(),100000.0,120.050,100120.050,100, BondOrder.SELL, bond, user));
		//buyBond(bond.getBondid(), 100, HttpServletRequest());
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

		Long userid = (Long) req.getSession().getAttribute("user_id");

		if(userid != null){
			User buyer = userRepo.findByUserid((long)userid);

			if(bondToBuy != null && buyer != null) {

				//avoid null pointer if user has never purchased this bond
				boolean newOrderFlag = false;

				BondOrder Uorder = bondOrderRepo.findByBondAndUser(bondToBuy, buyer);

				if (Uorder == null) {
					newOrderFlag = true;
				}

				Date td = new Date();
				Date sd = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(sd);
				if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
					c.add(Calendar.DATE, 3);
				} else {
					c.add(Calendar.DATE, 1); //US Treasury Bonds have a settlement date of +1 Business day
				}
				sd = c.getTime();

				double princ = bondToBuy.getMarketprice();

				double totalPrincipal = princ * quant;

				double interestOnPurchase = computeInterest(bondToBuy.getMarketprice(), bondToBuy.getInterestrate(), bondToBuy.getIssuedate(), sd, quant);

				double orderTotal = totalPrincipal + interestOnPurchase;

				double newBalance = buyer.getAcctbalance() - orderTotal;

				if (newBalance >= 0.00 && bondToBuy.getQuantity() >= quant) {

					buyer.setAcctbalance(newBalance);
					bondToBuy.setQuantity(bondToBuy.getQuantity() - quant);

					if (newOrderFlag) {
						BondOrder order = new BondOrder(td, td, sd, totalPrincipal, interestOnPurchase, orderTotal, quant, BondOrder.BUY, bondToBuy, buyer);

						TransactionHistory th = new TransactionHistory(td, BondOrder.BOUGHT, quant, orderTotal, buyer, order);

						bondOrderRepo.save(order);
						userRepo.save(buyer);
						bondRepo.save(bondToBuy);
						bondRepo.save(bondToBuy);
						thRepo.save(th);

						return new Response("Success", order);
					} else {
						BondOrder newOrder = new BondOrder(td, td, sd, totalPrincipal + Uorder.getPrincipal(), interestOnPurchase + Uorder.getAccruedinterest(), orderTotal + Uorder.getTotal(), quant + Uorder.getNumbondspurchased(), BondOrder.BUY, bondToBuy, buyer);
						newOrder.setId(Uorder.getId());

						TransactionHistory th = new TransactionHistory(td, BondOrder.BOUGHT, quant, orderTotal, buyer, newOrder);

						int idx = buyer.getOrders().indexOf(Uorder);
						buyer.getOrders().remove(idx); //maintain uniqueness
						bondOrderRepo.delete(Uorder);
						userRepo.save(buyer);
						bondOrderRepo.save(newOrder);
						bondRepo.save(bondToBuy);
						thRepo.save(th);

						return new Response("Success", newOrder);
					}

				}
				return new Response("Fail", new Failed("Insufficient funds or quantitiy, User has $"+buyer.getAcctbalance()+", and you tried to purchase "+quant+" bonds valued at $"+ (bondToBuy.getMarketprice()*quant)));
			}
		}
		return new Response("Fail", new Failed("Cannot find bond or user"));
	}

	@RequestMapping(value = "/order/sell", method = RequestMethod.GET)
	public Response sellBond(@RequestParam("id") long bondid, @RequestParam("quantity") int quant, HttpServletRequest req){

		//ID in param is the bondID
		Bond bondToSell = bondRepo.findByBondid(bondid);

		Long userid = (Long) req.getSession().getAttribute("user_id");

		if(userid != null) {

			User seller = userRepo.findByUserid((long)userid);

			if (bondToSell != null && seller != null) {

				BondOrder Uorder = bondOrderRepo.findByBondAndUser(bondToSell, seller);

				if (Uorder != null) {

					//int numOwned = assocOrder.getNumbondspurchased();
					int numOwned = Uorder.getNumbondspurchased();

					if (numOwned >= quant) { //double check you can't sell more than you own

						Date td = new Date();
						Date sd = new Date();
						Calendar c = Calendar.getInstance();
						c.setTime(sd);
						if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
							c.add(Calendar.DATE, 3);
						} else {
							c.add(Calendar.DATE, 1); //US Treasury Bonds have a settlement date of +1 << Business >> day
						}
						sd = c.getTime();

						double princ = bondToSell.getMarketprice();

						double totalPrincipal = princ * quant;

						double interestOnPurchase = computeInterest(bondToSell.getFacevalue(), bondToSell.getInterestrate(), bondToSell.getIssuedate(), sd, quant);

						double orderTotal = totalPrincipal + interestOnPurchase;

						double newBalance = seller.getAcctbalance() + orderTotal;

						//BondOrder order = new BondOrder(td, td, sd, totalPrincipal, interestOnPurchase, orderTotal, quant, BondOrder.SELL, bondToSell, seller);
						//quick fix to maintain one order with state...BUY is a backdoor for compatibiliity with portfolio
						BondOrder newOrder = new BondOrder(td, td, sd, Uorder.getPrincipal() - totalPrincipal, Uorder.getAccruedinterest() - interestOnPurchase, Uorder.getTotal() - orderTotal, Uorder.getNumbondspurchased() - quant, BondOrder.BUY, bondToSell, seller);
						newOrder.setId(Uorder.getId());

						TransactionHistory th = new TransactionHistory(td, BondOrder.SOLD, quant, orderTotal, seller, newOrder);

						seller.setAcctbalance(newBalance);
						bondToSell.setQuantity(bondToSell.getQuantity() + quant);

						int idx = seller.getOrders().indexOf(Uorder);
						seller.getOrders().remove(idx); //maintain uniqueness

						if((numOwned - quant) == 0){
							bondOrderRepo.delete(Uorder);
							bondOrderRepo.save(newOrder);
							//userRepo.save(seller);
							//bondOrderRepo.delete(newOrder);
							bondRepo.save(bondToSell);
							userRepo.save(seller);
							//update and re-save
							thRepo.save(th);
							bondOrderRepo.delete(newOrder);

							return new Response("Success", "All quantity of this bond sold");

						}
						else{
							bondOrderRepo.delete(Uorder);
							bondOrderRepo.save(newOrder);
							bondRepo.save(bondToSell);
							userRepo.save(seller);
							thRepo.save(th);

							return new Response("Success", newOrder);

						}
					}
					return new Response("Fail", new Failed("Cannot sell more than you own"));
				}
				return new Response("Fail", new Failed("Cannot find an order with specified user and bond"));
			}
		}
		return new Response("Fail", new Failed("Cannot located user or bond"));
	}

	public int daysBetween(Date d1, Date d2){
		return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	/**
	 *  d1 should be issue date and d2 should be the settlement date NOT buy date
	 */
	public double computeInterest(double fvalue, double coupon, Date d1, Date d2, int quant){

		int numDays = daysBetween(d1, d2);

		double rate = coupon / 100; //convert 6.5 to .065 for example

		double dailyInt = fvalue * rate;

		return Math.abs(dailyInt*numDays*quant);
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
