package com.putnam.model;

import java.io.Serializable;
import java.util.List;

public class Portfolio implements Serializable {

    private List<PortfolioEntry> holdings;

    public Portfolio (List<PortfolioEntry> holdings){
        this.holdings = holdings;
    }

    public List<PortfolioEntry> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<PortfolioEntry> holdings) {
        this.holdings = holdings;
    }
}
