package com.putnam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.putnam.model.Bond;
import com.putnam.repository.BondHistoryRepository;
import com.putnam.repository.BondRepository;

@RestController
public class BondHistoryController {	
	
	@Autowired
	BondRepository bondRepo;
	
	@Autowired
	BondHistoryRepository historyRepo;
	
	@RequestMapping(value="/bondhistory/save/{id}", method = RequestMethod.GET)
	public String save(@PathVariable String cusip) {
		Bond bond = bondRepo.findByCusip(cusip);
		bondRepo.save(bond);
		return "Done";
		
	}
}
