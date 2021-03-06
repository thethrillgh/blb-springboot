package com.putnam.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Table(name="transactionhistory")
public class TransactionHistory implements Serializable{

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="orderid", nullable=false)
    @JsonBackReference(value="bondtransactions")
    private BondOrder bondorder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userid", nullable=false)
    @JsonBackReference(value="userorders")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "timestamp", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    //Has to be "BUY" or "SELL"
    @Column(name = "transactiondesc")
    private String transactiondesc;

    protected TransactionHistory() {
        super();
    }

    public TransactionHistory(Date timestamp, String transactiontype, int quant, double orderTotal, User assocUser, BondOrder assocOrder){
        this();
        this.timestamp = timestamp;
        this.transactiondesc = getMessage(assocOrder, transactiontype, quant, orderTotal);
        this.user = assocUser;
        this.bondorder = assocOrder;
    }

    public BondOrder getBondorder() {
        return bondorder;
    }

    public void setBondorder(BondOrder bondorder) {
        this.bondorder = bondorder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransactiondesc() {
        return transactiondesc;
    }

    public void setTransactiondesc(String transactiondesc) {
        this.transactiondesc = transactiondesc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage(BondOrder order, String type, int quant, double orderTotal){

        DecimalFormat df = new DecimalFormat("#.##");

        return "You "+type.toLowerCase()+" "+quant+" bonds for a total of $"+Double.valueOf(df.format(orderTotal));

    }
}
