package com.putnam.controller;

import com.putnam.model.BondOrder;
import com.putnam.model.Portfolio;
import com.putnam.model.PortfolioEntry;
import com.putnam.model.User;
import com.putnam.repository.BondHistoryRepository;
import com.putnam.repository.BondOrderRepository;
import com.putnam.repository.BondRepository;
import com.putnam.repository.UserRepository;
import com.putnam.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class PortfolioController {

    @Autowired
    UserRepository userRepo;

    @RequestMapping(value = "/portfolio", method = RequestMethod.GET)
    public Response returnPortfolio(HttpServletRequest req){
/**
 * dont need history or orders just bond info and order for that bond
 */
        //Get the User Object
        long userid = (long) req.getSession().getAttribute("user_id");
        User user = userRepo.findByUserid(userid);

        List<PortfolioEntry> holdings = new ArrayList<PortfolioEntry>();

        if(user != null){

            holdings = filterOrdersForPortfolio(user.getOrders());

            Portfolio userPortfolio = new Portfolio(holdings);

            return new Response("Success", new Portfolio(holdings));
        }

        return new Response("Fail", new Portfolio(holdings));
    }

    public List<PortfolioEntry> filterOrdersForPortfolio(List<BondOrder> orders){

        List<PortfolioEntry> buyOrders = new ArrayList<PortfolioEntry>();

        for(BondOrder order : orders){
            if(order.getTransactiontype().equalsIgnoreCase(BondOrder.BUY)){
                buyOrders.add(new PortfolioEntry(order.getBond(), order));
            }
        }
        return buyOrders;
    }

}
