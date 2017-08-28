package com.putnam.repository;

import com.putnam.model.TransactionHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transaction;
import javax.transaction.Transactional;
import java.util.ArrayList;

import com.putnam.model.User;

@Transactional
@Repository
public interface TransactionHistoryRepository extends CrudRepository<TransactionHistory, Long>{

        public ArrayList<TransactionHistory> findByUser(User user);
}
