package com.putnam.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bankaccount")
public class Bank implements Serializable {

    @ManyToOne
    private User user;

    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @Column(name = "AcctNum")
    private String AcctNum;

    @Column(name = "RoutingNum")
    private String RoutingNum;

    @Column(name = "AcctType")
    private String AcctType;

    protected Bank() {

    }

    public Bank(String acctNum, String routingNum, String acctType) {
        this.AcctNum = acctNum;
        this.RoutingNum = routingNum;
        this.AcctType = acctType;
    }

}
