package com.putnam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "bondorder")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BondOrder implements Serializable {

	public static final String BUY = "BUY";
	public static final String SELL = "SELL";
	public static final String BOUGHT = "BOUGHT";
	public static final String SOLD = "SOLD";


	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bondid", nullable=false)
    @JsonBackReference(value="bondorders")
	private Bond bond;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userid", nullable=false)
    @JsonBackReference(value="userorders")
	private User user;

	private static final long serialVersionUID = -3009157732242241606L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
 
	@Column(name = "ordertimestamp", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ordertimestamp;

	@Column(name = "tradedate")
	private Date tradedate;
	
	@Column(name = "settlementdate")
	private Date settlementdate;

	@Column(name = "principal")
	private double principal;

	@Column(name = "accruedinterest")
	private double accruedinterest;

	@Column(name = "total")
	private double total;
	
	@Column(name = "numbondspurchased")
	private int numbondspurchased;

	@Column(name = "transactiontype")
	private String transactiontype;
	
	protected BondOrder() {
		super();
	}

	public BondOrder(Date ordertimestamp,
					 Date tradedate,
					 Date settlementdate,
					 double principal,
					 double accruedinterest,
					 double total,
					 int numbondspurchased,
					 String transactiontype,
					 Bond bond,
					 User user) {
		this();
		this.bond = bond;
		this.user = user;
		this.ordertimestamp = ordertimestamp;
		this.tradedate = tradedate;
		this.settlementdate = settlementdate;
		this.principal = principal;
		this.accruedinterest = accruedinterest;
		this.total=total;
		this.numbondspurchased = numbondspurchased;
		this.transactiontype = transactiontype;
	}

	public Bond getBond() {
		return bond;
	}

	public void setBond(Bond bond) {
		this.bond = bond;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getOrdertimestamp() {
		return ordertimestamp;
	}

	public void setOrdertimestamp(Date ordertimestamp) {
		this.ordertimestamp = ordertimestamp;
	}

	public Date getTradedate() {
		return tradedate;
	}

	public void setTradedate(Date tradedate) {
		this.tradedate = tradedate;
	}

	public Date getSettlementdate() {
		return settlementdate;
	}

	public void setSettlementdate(Date settlementdate) {
		this.settlementdate = settlementdate;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getNumbondspurchased() {
		return numbondspurchased;
	}

	public void setNumbondspurchased(int numbondspurchased) {
		this.numbondspurchased = numbondspurchased;
	}

	public double getAccruedinterest() {
		return accruedinterest;
	}

	public void setAccruedinterest(double accruedinterest) {
		this.accruedinterest = accruedinterest;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		if(transactiontype.equalsIgnoreCase(BondOrder.BUY) || transactiontype.equalsIgnoreCase(BondOrder.SELL)){
			this.transactiontype = transactiontype.toUpperCase();
		}
	}
	
}
