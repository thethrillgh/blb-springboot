package com.putnam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A UserAccount.
 */
@Entity
@Table(name = "user_account")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "acct_id")
    private Long acctID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "acct_email")
    private String acctEmail;

    @Column(name = "acct_password")
    private String acctPassword;

    @Column(name = "acct_ssn")
    private String acctSSN;

    @Column(name = "ssn_last_four")
    private String ssnLastFour;

    @Column(name = "pass_salt")
    private String passSalt;

    @Column(name = "acct_balance")
    private Double acctBalance;

    @OneToOne
    @JoinColumn(unique = true)
    private UserAddress userAddress;

    @OneToOne
    @JoinColumn(unique = true)
    private BankAcct assocBankAccount;

    @OneToMany(mappedBy = "assocAcct")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Order> orders = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcctID() {
        return acctID;
    }

    public UserAccount acctID(Long acctID) {
        this.acctID = acctID;
        return this;
    }

    public void setAcctID(Long acctID) {
        this.acctID = acctID;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserAccount firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserAccount lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public UserAccount phoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAcctEmail() {
        return acctEmail;
    }

    public UserAccount acctEmail(String acctEmail) {
        this.acctEmail = acctEmail;
        return this;
    }

    public void setAcctEmail(String acctEmail) {
        this.acctEmail = acctEmail;
    }

    public String getAcctPassword() {
        return acctPassword;
    }

    public UserAccount acctPassword(String acctPassword) {
        this.acctPassword = acctPassword;
        return this;
    }

    public void setAcctPassword(String acctPassword) {
        this.acctPassword = acctPassword;
    }

    public String getAcctSSN() {
        return acctSSN;
    }

    public UserAccount acctSSN(String acctSSN) {
        this.acctSSN = acctSSN;
        return this;
    }

    public void setAcctSSN(String acctSSN) {
        this.acctSSN = acctSSN;
    }

    public String getSsnLastFour() {
        return ssnLastFour;
    }

    public UserAccount ssnLastFour(String ssnLastFour) {
        this.ssnLastFour = ssnLastFour;
        return this;
    }

    public void setSsnLastFour(String ssnLastFour) {
        this.ssnLastFour = ssnLastFour;
    }

    public String getPassSalt() {
        return passSalt;
    }

    public UserAccount passSalt(String passSalt) {
        this.passSalt = passSalt;
        return this;
    }

    public void setPassSalt(String passSalt) {
        this.passSalt = passSalt;
    }

    public Double getAcctBalance() {
        return acctBalance;
    }

    public UserAccount acctBalance(Double acctBalance) {
        this.acctBalance = acctBalance;
        return this;
    }

    public void setAcctBalance(Double acctBalance) {
        this.acctBalance = acctBalance;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public UserAccount userAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
        return this;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public BankAcct getAssocBankAccount() {
        return assocBankAccount;
    }

    public UserAccount assocBankAccount(BankAcct bankAcct) {
        this.assocBankAccount = bankAcct;
        return this;
    }

    public void setAssocBankAccount(BankAcct bankAcct) {
        this.assocBankAccount = bankAcct;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public UserAccount orders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    public UserAccount addOrder(Order order) {
        this.orders.add(order);
        order.setAssocAcct(this);
        return this;
    }

    public UserAccount removeOrder(Order order) {
        this.orders.remove(order);
        order.setAssocAcct(null);
        return this;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserAccount userAccount = (UserAccount) o;
        if (userAccount.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userAccount.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserAccount{" +
            "id=" + getId() +
            ", acctID='" + getAcctID() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", phoneNum='" + getPhoneNum() + "'" +
            ", acctEmail='" + getAcctEmail() + "'" +
            ", acctPassword='" + getAcctPassword() + "'" +
            ", acctSSN='" + getAcctSSN() + "'" +
            ", ssnLastFour='" + getSsnLastFour() + "'" +
            ", passSalt='" + getPassSalt() + "'" +
            ", acctBalance='" + getAcctBalance() + "'" +
            "}";
    }
}
