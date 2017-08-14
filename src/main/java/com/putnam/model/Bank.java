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

    @Column(name = "acctnum")
    private String acctnum;

    @Column(name = "routingnum")
    private String routingnum;

    @Column(name = "accttype")
    private String accttype;

    protected Bank() {

    }

    public Bank(String acctnum, String routingnum, String accttype) {
        this.acctnum = acctnum;
        this.routingnum = routingnum;
        this.accttype = accttype;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "user=" + user +
                ", ID=" + ID +
                ", acctnum='" + acctnum + '\'' +
                ", routingnum='" + routingnum + '\'' +
                ", accttype='" + accttype + '\'' +
                '}';
    }

}
