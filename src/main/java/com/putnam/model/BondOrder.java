package com.putnam.model;

import java.io.Serializable;
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
	private long ID;
 
	@Column(name = "orderTimeStamp")
	private LocalTime orderTimeStamp;

	@Column(name = "tradeDate")
	private Date tradeDate;
	
	@Column(name = "settlementDate")
	private Date settlementDate;
	
	@Column(name = "transactionAmt")
	private Double transactionAmt;
	
	@Column(name = "accruedInterest")
	private Double accruedInterest;
	
	@Column(name = "numBondsPurchased")
	private int numBondsPurchased;
	
	protected BondOrder() {
		
	}
	
	public BondOrder(LocalTime orderTimeStamp, Date tradeDate, Date settlementDate, Double transactionAmt,
			Double accruedInterest, int numBondsPurchased) {
		this.orderTimeStamp = orderTimeStamp;
		this.tradeDate = tradeDate;
		this.settlementDate = settlementDate;
		this.transactionAmt = transactionAmt;
		this.accruedInterest = accruedInterest;
		this.numBondsPurchased = numBondsPurchased;
	}
	
}
