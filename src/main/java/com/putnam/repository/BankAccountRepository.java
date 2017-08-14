package com.putnam.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.putnam.model.Bank;

@Transactional
@Repository
public interface BankAccountRepository extends CrudRepository<Bank, Long>{
    public Bank findById(long id);
}