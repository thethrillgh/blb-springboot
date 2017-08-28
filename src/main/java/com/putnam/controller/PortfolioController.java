package com.putnam.controller;

import com.putnam.model.BondOrder;
import com.putnam.model.Portfolio;
import com.putnam.model.PortfolioEntry;
import com.putnam.model.User;
import com.putnam.repository.UserRepository;
import com.putnam.response.Failed;
import com.putnam.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        Long userid = (Long) req.getSession().getAttribute("user_id");

        if(userid != null) {

            User user = userRepo.findByUserid(userid);

            if (user != null) {

                List<PortfolioEntry> holdings = new ArrayList<PortfolioEntry>();

                holdings = filterOrdersForPortfolio(user.getOrders());

                Portfolio userPortfolio = new Portfolio(holdings);

                return new Response("Success", new Portfolio(holdings));
            }
        }

        return new Response("Fail", new Failed("Unable to find user"));
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
