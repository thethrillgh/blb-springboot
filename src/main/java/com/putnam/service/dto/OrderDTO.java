package com.putnam.service.dto;


import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Order entity.
 */
public class OrderDTO implements Serializable {

    private Long id;

    private ZonedDateTime orderTimeStamp;

    private LocalDate tradeDate;

    private Double transactionAmount;

    private Integer numBondsPurchased;

    private Long assocAcctId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getOrderTimeStamp() {
        return orderTimeStamp;
    }

    public void setOrderTimeStamp(ZonedDateTime orderTimeStamp) {
        this.orderTimeStamp = orderTimeStamp;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Integer getNumBondsPurchased() {
        return numBondsPurchased;
    }

    public void setNumBondsPurchased(Integer numBondsPurchased) {
        this.numBondsPurchased = numBondsPurchased;
    }

    public Long getAssocAcctId() {
        return assocAcctId;
    }

    public void setAssocAcctId(Long userAccountId) {
        this.assocAcctId = userAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderDTO orderDTO = (OrderDTO) o;
        if(orderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
            "id=" + getId() +
            ", orderTimeStamp='" + getOrderTimeStamp() + "'" +
            ", tradeDate='" + getTradeDate() + "'" +
            ", transactionAmount='" + getTransactionAmount() + "'" +
            ", numBondsPurchased='" + getNumBondsPurchased() + "'" +
            "}";
    }
}
