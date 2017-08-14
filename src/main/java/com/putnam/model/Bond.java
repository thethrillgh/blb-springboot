package com.putnam.model;
import java.io.Serializable;
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
	private String issuedate;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "interestrate")
	private Double interestrate;
	
	@Column(name = "maturitydate")
	private String maturitydate;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "creditrating")
	private String creditrating;
	
	@Column(name = "callable")
	private String callable;
	
	@Column(name = "coupontype")
	private String coupontype;
	
	@Column(name = "bid")
	private Double bid;
	
	@Column(name = "ask")
	private Double ask;
	
	@Column(name = "yieldbid")
	private Double yieldbid;
	
	@Column(name = "yieldask")
	private Double yieldask;
	
	@Column(name = "marketprice")
	private Double marketprice;
	
	@Column(name = "marketyield")
	private Double marketyield;
	
	@Column(name = "facevalue")
	private Double facevalue;
	
	public Bond(String cusip, String issuer, String issuedate, String type, Double interestrate, String maturitydate,
			int quantity, String creditrating, String callable, String coupontype, Double bid, Double ask,
			Double yieldbid, Double yieldask, Double marketprice, Double marketyield, Double facevalue) {
		super();
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


	public String getIssuedate() {
		return issuedate;
	}


	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Double getInterestrate() {
		return interestrate;
	}


	public void setInterestrate(Double interestrate) {
		this.interestrate = interestrate;
	}


	public String getMaturitydate() {
		return maturitydate;
	}


	public void setMaturitydate(String maturitydate) {
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


	public Double getBid() {
		return bid;
	}


	public void setBid(Double bid) {
		this.bid = bid;
	}


	public Double getAsk() {
		return ask;
	}


	public void setAsk(Double ask) {
		this.ask = ask;
	}


	public Double getYieldbid() {
		return yieldbid;
	}


	public void setYieldbid(Double yieldbid) {
		this.yieldbid = yieldbid;
	}


	public Double getYieldask() {
		return yieldask;
	}


	public void setYieldask(Double yieldask) {
		this.yieldask = yieldask;
	}


	public Double getMarketprice() {
		return marketprice;
	}


	public void setMarketprice(Double marketprice) {
		this.marketprice = marketprice;
	}


	public Double getMarketyield() {
		return marketyield;
	}


	public void setMarketyield(Double marketyield) {
		this.marketyield = marketyield;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	protected Bond() {
		
	}
	
}