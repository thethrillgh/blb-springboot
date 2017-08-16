package com.putnam.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bond")
public class Bond implements Serializable {
	
	@OneToMany(mappedBy = "bond")
	private Collection<BondOrder> orders; 
 
	private static final long serialVersionUID = -3009157732242241606L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bondid;
 
	@Column(name = "cusip", unique=true)
	private String cusip;
 
	@Column(name = "issuer")
	private String issuer;
	
	@Column(name = "issuedate")
	private LocalDate issuedate;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "interestrate")
	private double interestrate;
	
	@Column(name = "maturitydate")
	private LocalDate maturitydate;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "creditrating")
	private String creditrating;
	
	@Column(name = "callable")
	private String callable;
	
	@Column(name = "coupontype")
	private String coupontype;
	
	@Column(name = "bid")
	private double bid;
	
	@Column(name = "ask")
	private double ask;
	
	@Column(name = "yieldbid")
	private double yieldbid;
	
	@Column(name = "yieldask")
	private double yieldask;
	
	@Column(name = "marketprice")
	private double marketprice;
	
	@Column(name = "marketyield")
	private double marketyield;

	@Column(name = "facevalue")
	private double facevalue;


	protected Bond() {
		super();
	}

	public Bond(String cusip, String issuer, LocalDate issuedate, String type, double interestrate, LocalDate maturitydate,
			int quantity, String creditrating, String callable, String coupontype, double bid, double ask,
			double yieldbid, double yieldask, double marketprice, double marketyield, double facevalue) {
		this();
		this.cusip = cusip;
		this.issuer = issuer;
		this.issuedate = issuedate;
		this.type = type;
		this.interestrate = interestrate;
		this.maturitydate = maturitydate;
		this.quantity = quantity;
		this.creditrating = creditrating;
		this.callable = callable;
		this.coupontype = coupontype;
		this.bid = bid;
		this.ask = ask;
		this.yieldbid = yieldbid;
		this.yieldask = yieldask;
		this.marketprice = marketprice;
		this.marketyield = marketyield;
		this.facevalue = facevalue;
	}


	@Override
	public String toString() {
		return "Bond [orders=" + orders + ", bondid=" + bondid + ", cusip=" + cusip + ", issuer=" + issuer
				+ ", issuedate=" + issuedate + ", type=" + type + ", interestrate=" + interestrate + ", maturitydate="
				+ maturitydate + ", quantity=" + quantity + ", creditrating=" + creditrating + ", callable=" + callable
				+ ", coupontype=" + coupontype + ", bid=" + bid + ", ask=" + ask + ", yieldbid=" + yieldbid
				+ ", yieldask=" + yieldask + ", marketprice=" + marketprice + ", marketyield=" + marketyield
				+ ", facevalue=" + facevalue + "]";
	}
	
	public Collection<BondOrder> getOrders() {
		return orders;
	}


	public void setOrders(Collection<BondOrder> orders) {
		this.orders = orders;
	}


	public long getBondid() {
		return bondid;
	}


	public void setBondid(long bondid) {
		this.bondid = bondid;
	}


	public String getCusip() {
		return cusip;
	}


	public void setCusip(String cusip) {
		this.cusip = cusip;
	}


	public String getIssuer() {
		return issuer;
	}


	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}


	public LocalDate getIssuedate() {
		return issuedate;
	}


	public void setIssuedate(LocalDate issuedate) {
		this.issuedate = issuedate;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public double getInterestrate() {
		return interestrate;
	}


	public void setInterestrate(double interestrate) {
		this.interestrate = interestrate;
	}


	public LocalDate getMaturitydate() {
		return maturitydate;
	}


	public void setMaturitydate(LocalDate maturitydate) {
		this.maturitydate = maturitydate;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getCreditrating() {
		return creditrating;
	}


	public void setCreditrating(String creditrating) {
		this.creditrating = creditrating;
	}


	public String getCallable() {
		return callable;
	}


	public void setCallable(String callable) {
		this.callable = callable;
	}


	public String getCoupontype() {
		return coupontype;
	}


	public void setCoupontype(String coupontype) {
		this.coupontype = coupontype;
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


	public double getMarketprice() {
		return marketprice;
	}


	public void setMarketprice(double marketprice) {
		this.marketprice = marketprice;
	}


	public double getMarketyield() {
		return marketyield;
	}


	public void setMarketyield(double marketyield) {
		this.marketyield = marketyield;
	}

	public double getFacevalue() {
		return facevalue;
	}

	public void setFacevalue(double facevalue) {
		this.facevalue = facevalue;
	}

	
}