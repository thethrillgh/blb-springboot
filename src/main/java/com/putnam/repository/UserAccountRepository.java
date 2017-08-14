package com.putnam.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.putnam.model.User;

@Transactional
@Repository
public interface UserAccountRepository extends CrudRepository<User, Long>{
    public User findByBondid(long id);
}