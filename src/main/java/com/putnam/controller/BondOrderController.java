package com.putnam.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.putnam.model.Bond;
import com.putnam.model.BondOrder;
import com.putnam.model.User;
import com.putnam.repository.BondOrderRepository;
import com.putnam.repository.BondRepository;
import com.putnam.repository.UserRepository;

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
}
