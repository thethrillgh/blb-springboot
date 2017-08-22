package com.putnam.model;

import java.io.Serializable;

public class PortfolioEntry implements Serializable {


    private Bond assocBond;

    private BondOrder assocOrder;

    public PortfolioEntry(Bond bond, BondOrder order){
        this.assocBond = bond;
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
