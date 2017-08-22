package com.putnam.model;

import java.io.Serializable;

public class PortfolioEntry implements Serializable {


    private Bond assocBond;

    private BondOrder assocOrder;

    public PortfolioEntry(Bond bond, BondOrder order){
        //this.assocBond = bond;
        //this.assocOrder = order;
        this.assocBond = new Bond(bond.getCusip(), bond.getIssuer(), bond.getIssuedate(), bond.getType(),bond.getInterestrate(),bond.getMaturitydate(),bond.getQuantity(), bond.getCreditrating(),bond.getCallable(), bond.getCoupontype(),bond.getBid(), bond.getAsk(), bond.getYieldbid(), bond.getYieldask(), bond.getMarketprice(), bond.getMarketyield(), bond.getFacevalue());
        this.assocBond.setBondid(bond.getBondid());
        this.assocOrder = order;
    }

    public Bond getAssocBond() {
        return assocBond;
    }

    public void setAssocBond(Bond assocBond) {
        this.assocBond = assocBond;
    }

    public BondOrder getAssocOrder() {
        return assocOrder;
    }

    public void setAssocOrder(BondOrder assocOrder) {
        this.assocOrder = assocOrder;
    }
}
