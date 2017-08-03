package com.putnam.repository;

import com.putnam.domain.BankAcct;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the BankAcct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankAcctRepository extends JpaRepository<BankAcct,Long> {
    
}
