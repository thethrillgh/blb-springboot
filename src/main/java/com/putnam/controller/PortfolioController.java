package com.putnam.controller;

import com.putnam.model.BondOrder;
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

@RestController
public class PortfolioController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    BondRepository bondRepo;

    @Autowired
    BondOrderRepository bondOrderRepo;

    @Autowired
    BondHistoryRepository bondHidtoryRepo;

    @RequestMapping(value = "/portfolio", method = RequestMethod.GET)
    public Response returnPortfolio(HttpServletRequest req){

        //Get the User Object
        long userid = (long) req.getSession().getAttribute("user_id");
        User user = userRepo.findByUserid(userid);

        ArrayList<PortfolioEntry> holdings = filterOrdersForPortfolio((ArrayList<BondOrder>) user.getOrders());

        //It failed
        return new Response("Fail", new Object());
    }

    public ArrayList<PortfolioEntry> filterOrdersForPortfolio(ArrayList<BondOrder> orders){

    }

}
