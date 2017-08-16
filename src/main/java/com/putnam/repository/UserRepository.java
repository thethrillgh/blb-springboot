package com.putnam.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.putnam.model.User;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByAcctemailAndSsnlastfour(String email, String ssn);
	User findByUserid(long id);
	User findByAcctpass(String acctpass);
}