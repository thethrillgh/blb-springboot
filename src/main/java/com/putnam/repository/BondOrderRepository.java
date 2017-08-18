package com.putnam.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.putnam.model.BondOrder;

@Transactional
@Repository
public interface BondOrderRepository extends CrudRepository<BondOrder, Long>{
    public BondOrder findById(long id);
}