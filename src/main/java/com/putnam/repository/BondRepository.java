package com.putnam.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.putnam.model.Bond;

@Transactional
@Repository
public interface BondRepository extends CrudRepository<Bond, Long>{

	public Bond findByBondid(long id);

	public Bond findByCusip(String cusip);

}