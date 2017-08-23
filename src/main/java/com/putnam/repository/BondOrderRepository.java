package com.putnam.repository;

import javax.transaction.Transactional;
import com.putnam.model.Bond;
import com.putnam.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.putnam.model.BondOrder;

import java.util.ArrayList;

@Transactional
@Repository
public interface BondOrderRepository extends CrudRepository<BondOrder, Long>{
    public BondOrder findById(long id);

    BondOrder findByBondAndUser(Bond bond, User user);
}