package com.putnam.controller;

import com.putnam.model.*;
import com.putnam.repository.BondOrderRepository;
import com.putnam.repository.BondRepository;
import com.putnam.repository.UserRepository;
import com.putnam.response.Failed;
import com.putnam.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PortfolioController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    BondOrderRepository orderRepo;

    @Autowired
    BondRepository bondRepo;

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

                holdings = filterOrdersForPortfolio(user, user.getOrders());

                Portfolio userPortfolio = new Portfolio(holdings);

                return new Response("Success", new Portfolio(holdings));
            }
        }

        return new Response("Fail", new Failed("Unable to find user"));
    }

    @RequestMapping(value = "/refreshBond", method = RequestMethod.GET)
    public Response returnBondBought(@RequestParam("id") long bondid, HttpServletRequest req){

        Long userid = (Long) req.getSession().getAttribute("user_id");

        if(userid != null) {

            User user = userRepo.findByUserid(userid);

            Bond bond = bondRepo.findByBondid(bondid);

            if (user != null && bond != null) {

                PortfolioEntry entry = findForRefresh(bond, user.getOrders());

                if(entry != null){
                    return new Response("Success", entry);
                }
                else{
                    return new Response("Fail", new Failed("Could not find specified entry"));
                }
            }
        }

        return new Response("Fail", new Failed("Unable to find user or bond"));
    }

    public List<PortfolioEntry> filterOrdersForPortfolio(User user, List<BondOrder> orders){

        List<PortfolioEntry> buyOrders = new ArrayList<PortfolioEntry>();

        for(int idx = 0; idx < orders.size(); idx++){

//            BondOrder order = orders.get(idx);
//
//            if(order.getTransactiontype().equalsIgnoreCase(BondOrder.BUY)){
//                buyOrders.add(new PortfolioEntry(order.getBond(), order));
//            }
            BondOrder order = orders.get(idx);
            if(order.getNumbondspurchased() == 0){
                orderRepo.delete(order);
            }
            else{
                buyOrders.add(new PortfolioEntry(order.getBond(), order));
            }
        }
        return buyOrders;
    }

    public PortfolioEntry findForRefresh(Bond bond, List<BondOrder> orders){

        PortfolioEntry entry = null;

        for(int idx = 0; idx < orders.size(); idx++){

            BondOrder order = orders.get(idx);

            if(order.getBond().getBondid() == bond.getBondid()){
                entry = new PortfolioEntry(bond, order);
                break;
            }
        }

        return entry;
    }

}
