package com.putnam.controller;

import com.putnam.model.TransactionHistory;
import com.putnam.model.User;
import com.putnam.repository.TransactionHistoryRepository;
import com.putnam.repository.UserRepository;
import com.putnam.response.Failed;
import com.putnam.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class TransactionHistoryController {

    @Autowired
    TransactionHistoryRepository thRepo;

    @Autowired
    UserRepository userRepo;

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public Response getUserTransaction(HttpServletRequest req){

        Long userid = (Long) req.getSession().getAttribute("user_id");

        if (userid != null){

            User user = userRepo.findByUserid(userid);

            if(user != null){
                ArrayList<TransactionHistory> transactions = thRepo.findByUser(user);

                if(transactions != null && !transactions.isEmpty()){
                    return new Response("Success", transactions);
                }
            }
            return new Response("Fail", new Failed("Could not find user"));
        }
        return new Response("Fail", new Failed("Could not find user"));
    }
}
