package com.putnam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Order.
 */
@Entity
@Table(name = "jhi_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "order_time_stamp")
    private ZonedDateTime orderTimeStamp;

    @Column(name = "trade_date")
    private LocalDate tradeDate;

    @Column(name = "transaction_amount")
    private Double transactionAmount;

    @Column(name = "num_bonds_purchased")
    private Integer numBondsPurchased;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Bond> assocBonds = new HashSet<>();

    @ManyToOne
    private UserAccount assocAcct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getOrderTimeStamp() {
        return orderTimeStamp;
    }

    public Order orderTimeStamp(ZonedDateTime orderTimeStamp) {
        this.orderTimeStamp = orderTimeStamp;
        return this;
    }

    public void setOrderTimeStamp(ZonedDateTime orderTimeStamp) {
        this.orderTimeStamp = orderTimeStamp;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public Order tradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
        return this;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public Order transactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Integer getNumBondsPurchased() {
        return numBondsPurchased;
    }

    public Order numBondsPurchased(Integer numBondsPurchased) {
        this.numBondsPurchased = numBondsPurchased;
        return this;
    }

    public void setNumBondsPurchased(Integer numBondsPurchased) {
        this.numBondsPurchased = numBondsPurchased;
    }

    public Set<Bond> getAssocBonds() {
        return assocBonds;
    }

    public Order assocBonds(Set<Bond> bonds) {
        this.assocBonds = bonds;
        return this;
    }

    public Order addAssocBond(Bond bond) {
        this.assocBonds.add(bond);
        bond.setOrder(this);
        return this;
    }

    public Order removeAssocBond(Bond bond) {
        this.assocBonds.remove(bond);
        bond.setOrder(null);
        return this;
    }

    public void setAssocBonds(Set<Bond> bonds) {
        this.assocBonds = bonds;
    }

    public UserAccount getAssocAcct() {
        return assocAcct;
    }

    public Order assocAcct(UserAccount userAccount) {
        this.assocAcct = userAccount;
        return this;
    }

    public void setAssocAcct(UserAccount userAccount) {
        this.assocAcct = userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        if (order.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + getId() +
            ", orderTimeStamp='" + getOrderTimeStamp() + "'" +
            ", tradeDate='" + getTradeDate() + "'" +
            ", transactionAmount='" + getTransactionAmount() + "'" +
            ", numBondsPurchased='" + getNumBondsPurchased() + "'" +
            "}";
    }
}
