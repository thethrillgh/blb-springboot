package com.putnam.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.putnam.model.BondHistory;

@Transactional
@Repository
public interface BondHistoryRepository extends CrudRepository<BondHistory, Long>{
    public BondHistory findById(long id);
}