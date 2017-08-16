package com.putnam.model;

import java.io.Serializable;
import java.time.LocalTime;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="bondhistory")
public class BondHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bondid", nullable=false)
    @JsonBackReference
    private Bond bond;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "time")
    private LocalTime time;

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

    public BondHistory(LocalTime time, double bid, double ask, double yieldbid, double yieldask, double changeprice, String cusip){
        this();
        this.time = time;
        this.bid = bid;
        this.ask = ask;
        this.yieldbid = yieldbid;
        this.yieldask = yieldask;
        this.changeprice = changeprice;
        this.cusip = cusip;
    }

	public Bond getBond() {
		return bond;
	}

    public void setBond(Bond bond) {
        this.bond = bond;
    }

	public void setBond(Bond bond) {
		this.bond = bond;
	}

    public void setTime(LocalTime time) {
        this.time = time;
    }

	public long getId() {
		return id;
	}

    public void setID(long id) {
        this.id = id;
    }

	public void setId(long id) {
		this.id = id;
	}

    public void setBid(double bid) {
        this.bid = bid;
    }

	public LocalTime getTime() {
		return time;
	}

    public void setAsk(double ask) {
        this.ask = ask;
    }

	public void setTime(LocalTime time) {
		this.time = time;
	}

    public void setYieldbid(double yieldbid) {
        this.yieldbid = yieldbid;
    }

	public double getBid() {
		return bid;
	}

    public void setYieldask(double yieldask) {
        this.yieldask = yieldask;
    }

	public void setBid(double bid) {
		this.bid = bid;
	}

    public void setChangeprice(double changeprice) {
        this.changeprice = changeprice;
    }

	public double getAsk() {
		return ask;
	}

    public void setCusip(String cusip) {
        this.cusip = cusip;
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


	public String getCusip() {
		return cusip;
	}


	public void setCusip(String cusip) {
		this.cusip = cusip;
	}


	public BondHistory(Bond bond, long id, LocalTime time, double bid, double ask, double yieldbid, double yieldask,
			double changeprice, String cusip) {
		super();
		this.bond = bond;
		this.id = id;
		this.time = time;
		this.bid = bid;
		this.ask = ask;
		this.yieldbid = yieldbid;
		this.yieldask = yieldask;
		this.changeprice = changeprice;
		this.cusip = cusip;
	}

    
}
