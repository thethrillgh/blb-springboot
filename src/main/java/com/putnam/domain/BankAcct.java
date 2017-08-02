package com.putnam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A BankAcct.
 */
@Entity
@Table(name = "bank_acct")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BankAcct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "acct_num")
    private String acctNum;

    @Column(name = "routing_num")
    private String routingNum;

    @Column(name = "acct_type")
    private String acctType;

    @OneToOne(mappedBy = "assocBankAccount")
    @JsonIgnore
    private UserAccount assocAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcctNum() {
        return acctNum;
    }

    public BankAcct acctNum(String acctNum) {
        this.acctNum = acctNum;
        return this;
    }

    public void setAcctNum(String acctNum) {
        this.acctNum = acctNum;
    }

    public String getRoutingNum() {
        return routingNum;
    }

    public BankAcct routingNum(String routingNum) {
        this.routingNum = routingNum;
        return this;
    }

    public void setRoutingNum(String routingNum) {
        this.routingNum = routingNum;
    }

    public String getAcctType() {
        return acctType;
    }

    public BankAcct acctType(String acctType) {
        this.acctType = acctType;
        return this;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public UserAccount getAssocAccount() {
        return assocAccount;
    }

    public BankAcct assocAccount(UserAccount userAccount) {
        this.assocAccount = userAccount;
        return this;
    }

    public void setAssocAccount(UserAccount userAccount) {
        this.assocAccount = userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BankAcct bankAcct = (BankAcct) o;
        if (bankAcct.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankAcct.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BankAcct{" +
            "id=" + getId() +
            ", acctNum='" + getAcctNum() + "'" +
            ", routingNum='" + getRoutingNum() + "'" +
            ", acctType='" + getAcctType() + "'" +
            "}";
    }
}
