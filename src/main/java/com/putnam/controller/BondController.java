package com.putnam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.putnam.model.Bond;
import com.putnam.repository.BondRepository;
import com.putnam.response.Response;

@RestController
public class BondController {	
	@Autowired
	BondRepository bondRepo;

	
	@RequestMapping("/save")
	public String process(){
		bondRepo.save(new Bond("912821100","US TREASURY","05/15/2017","1YR",6.15,"05/15/2018",10000,"AA+","NO","FIXED",100.778343,101.4596284,6.102501603,6.061524272,101.1189857,6.082012938, 10.09));
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
}
