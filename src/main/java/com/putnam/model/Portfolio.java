package com.putnam.model;

import java.io.Serializable;
import java.util.List;

public class Portfolio implements Serializable {

    private User owner;

    private List<PortfolioEntry> holdings;

    public Portfolio (User owner, List<PortfolioEntry> holdings){
        this.owner = owner;
        this.holdings = holdings;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<PortfolioEntry> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<PortfolioEntry> holdings) {
        this.holdings = holdings;
    }
}
