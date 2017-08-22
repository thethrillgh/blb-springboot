package com.putnam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="bondhistory")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BondHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bondid", nullable=false)
    @JsonBackReference(value="bondhistory")
    private Bond bond;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "time")
    private Date time;

    @Column(name = "bid")
    private double bid;

    @Column(name = "ask")
    private double ask;

    @Column(name = "yieldbid")
    private double yieldbid;

    @Column(name = "yieldask")
    private double yieldask;

    @Column(name = "changeprice")
    private double changeprice;

    @Column(name = "cusip")
    private String cusip;


    protected BondHistory() {
        super();
    }

	public Bond getBond() {
		return bond;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getBid() {
		return bid;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public double getAsk() {
		return ask;
	}

	public void setAsk(double ask) {
		this.ask = ask;
	}

	public double getYieldbid() {
		return yieldbid;
	}

	public void setYieldbid(double yieldbid) {
		this.yieldbid = yieldbid;
	}

	public double getYieldask() {
		return yieldask;
	}

	public void setYieldask(double yieldask) {
		this.yieldask = yieldask;
	}

	public double getChangeprice() {
		return changeprice;
	}

	public void setChangeprice(double changeprice) {
		this.changeprice = changeprice;
	}

	public BondHistory(Bond bond, Date time, double bid, double ask, double yieldbid, double yieldask,
			double changeprice, String cusip) {
		super();
		this.bond = bond;
		this.time = time;
		this.bid = bid;
		this.ask = ask;
		this.yieldbid = yieldbid;
		this.yieldask = yieldask;
		this.changeprice = changeprice;
		this.cusip = cusip;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public void setBond(Bond bond) {
		this.bond = bond;
	}
	
	

    
}
