package com.putnam.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bondorder")
public class BondOrder implements Serializable {

	@ManyToOne
	private Bond bond;
	
	@ManyToOne
	private User user;
	
	private static final long serialVersionUID = -3009157732242241606L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
 
	@Column(name = "ordertimestamp")
	private LocalDateTime ordertimestamp;

	@Column(name = "tradedate")
	private Date tradedate;
	
	@Column(name = "settlementdate")
	private Date settlementdate;

	@Column(name = "transactionamt")
	private double transactionamt;
	
	@Column(name = "accruedinterest")
	private double accruedinterest;
	
	@Column(name = "numBondspurchased")
	private int numBondspurchased;

	@Column(name = "userid")
	private long userid;

	@Column(name = "bondid")
	private long bondid;
	
	protected BondOrder() {
		super();
	}
	
	public BondOrder(LocalDateTime ordertimestamp, Date tradedate, Date settlementdate, double transactionamt,
			double accruedinterest, int numBondspurchased) {
		this();
		this.ordertimestamp = ordertimestamp;
		this.tradedate = tradedate;
		this.settlementdate = settlementdate;
		this.transactionamt = transactionamt;
		this.accruedinterest = accruedinterest;
		this.numBondspurchased = numBondspurchased;
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

	public long getID() {
		return id;
	}

	public void setID(long id) {
		this.id = id;
	}

	public LocalDateTime getOrdertimestamp() {
		return ordertimestamp;
	}

	public void setOrdertimestamp(LocalDateTime ordertimestamp) {
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

	public Double getTransactionamt() {
		return transactionamt;
	}

	public void setTransactionamt(Double transactionamt) {
		this.transactionamt = transactionamt;
	}

	public Double getAccruedinterest() {
		return accruedinterest;
	}

	public void setAccruedinterest(Double accruedinterest) {
		this.accruedinterest = accruedinterest;
	}

	public int getNumBondspurchased() {
		return numBondspurchased;
	}

	public void setNumBondspurchased(int numBondspurchased) {
		this.numBondspurchased = numBondspurchased;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getBondid() {
		return bondid;
	}

	public void setBondid(long bondid) {
		this.bondid = bondid;
	}

	@Override
	public String toString() {
		return "BondOrder{" +
				"bond=" + bond +
				", user=" + user +
				", id=" + id +
				", ordertimestamp=" + ordertimestamp +
				", tradedate=" + tradedate +
				", settlementdate=" + settlementdate +
				", transactionamt=" + transactionamt +
				", accruedinterest=" + accruedinterest +
				", numBondspurchased=" + numBondspurchased +
				'}';
	}
	
}
