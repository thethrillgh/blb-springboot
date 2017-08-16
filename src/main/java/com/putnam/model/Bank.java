package com.putnam.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "bankaccount")
public class Bank implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userid", nullable=false)
    @JsonBackReference
    private User user;

    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "acctnum")
    private String acctnum;

    @Column(name = "routingnum")
    private String routingnum;

    @Column(name = "accttype")
    private String accttype;

    protected Bank() {
        super();
    }

    public Bank(String acctnum, String routingnum, String accttype, User user) {
        this.acctnum = acctnum;
        this.routingnum = routingnum;
        this.accttype = accttype;
        this.user = user;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAcctnum() {
		return acctnum;
	}

	public void setAcctnum(String acctnum) {
		this.acctnum = acctnum;
	}

	public String getRoutingnum() {
		return routingnum;
	}

	public void setRoutingnum(String routingnum) {
		this.routingnum = routingnum;
	}

	public String getAccttype() {
		return accttype;
	}

	public void setAccttype(String accttype) {
		this.accttype = accttype;
	}
    
    

}
