package com.putnam.controller;

import com.putnam.repository.UserRepository;
import com.putnam.response.Failed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.putnam.model.Bond;
import com.putnam.repository.BondRepository;
import com.putnam.response.Response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RestController
public class BondController {

	@Autowired
	BondRepository bondRepo;

	@Autowired
	UserRepository userRepo;

	@RequestMapping("/save")
	public String process(){
		return "Done";
	}

	@RequestMapping("/findall")
	public Response findAll(){
		Iterable<Bond> result = bondRepo.findAll();
		return new Response("Done", result);
		
	}
	
	@RequestMapping("/findbyid")
	public Bond findById(@RequestParam("id") long id){
		return bondRepo.findByBondid(id);
	}
	
	@RequestMapping("/findbycusip")
	public Bond findbycusip(@RequestParam("cusip") String id) {
		return bondRepo.findByCusip(id);
	}
	
	@RequestMapping("/count")
	public long count(){
		long result = bondRepo.count();
		return result;
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") long id){
		bondRepo.delete(id);
		return "Done";
	}

	@RequestMapping(value = "/findbest", method = RequestMethod.GET)
	public Response getTopPerformingBond(){

		long id = -1;
		double max = 0.00;

		Iterable<Bond> bonds = bondRepo.findAll();

		for(Bond curr: bonds){
			if(curr.getYieldask() > max){
				id = curr.getBondid();
				max = curr.getYieldask();
			}
		}

		Bond bond = bondRepo.findByBondid(id);

		if(bond != null){
			return new Response("Success", bond);
		}

		return new Response("Fail", new Failed("Problem finding best bond"));
	}

}
