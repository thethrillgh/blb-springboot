package com.putnam.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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

@Entity
@Table(name = "bondorder")
public class BondOrder implements Serializable {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bondid", nullable=false)
    @JsonBackReference
	private Bond bond;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userid", nullable=false)
    @JsonBackReference
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
		
	}
	
	public BondOrder(LocalTime ordertimestamp, Date tradedate, Date settlementdate, double transactionamt,
			double accruedinterest, int numBondspurchased, long userid, long bondid) {
		this.ordertimestamp = ordertimestamp;
		this.tradedate = tradedate;
		this.settlementdate = settlementdate;
		this.transactionamt = transactionamt;
		this.accruedinterest = accruedinterest;
		this.numBondspurchased = numBondspurchased;
		this.userid = userid;
		this.bondid = bondid;
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

	public double getTransactionamt() {
		return transactionamt;
	}

	public void setTransactionamt(double transactionamt) {
		this.transactionamt = transactionamt;
	}

	public double getAccruedinterest() {
		return accruedinterest;
	}

	public void setAccruedinterest(double accruedinterest) {
		this.accruedinterest = accruedinterest;
	}

	public int getNumBondspurchased() {
		return numBondspurchased;
	}

	public void setNumBondspurchased(int numBondspurchased) {
		this.numBondspurchased = numBondspurchased;
	}

	public BondOrder(Date ordertimestamp, Date tradedate, Date settlementdate,
			double transactionamt, double accruedinterest, int numBondspurchased, Bond bond, User user) {
		super();
		this.ordertimestamp = ordertimestamp;
		this.tradedate = tradedate;
		this.settlementdate = settlementdate;
		this.transactionamt = transactionamt;
		this.accruedinterest = accruedinterest;
		this.numBondspurchased = numBondspurchased;
		this.bond = bond;
		this.user = user;
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
				", userid=" + userid +
				", bondid=" + bondid +
				'}';
	}
	
}
