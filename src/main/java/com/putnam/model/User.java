package com.putnam.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "useraccount")
public class User implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userID;
 
	@Column(name = "FirstName")
	private String FirstName;
	
	@Column(name = "LastName")
	private String LastName;
	
	@Column(name = "PhoneNum")
	private String PhoneNum;
	
	@Column(name = "AcctEmail")
	private String AcctEmail;
	
	@Column(name = "AcctPass")
	private String AcctPass;
	
	@Column(name = "AcctSSN")
	private String AcctSSN;
	
	@Column(name = "SSNLastFour")
	private String SSNLastFour;
	
	@Column(name = "PassSalt")
	private String PassSalt;
	
	@Column(name = "StreetAddress")
	private String StreetAddress;
	
	@Column(name = "City")
	private String City;
	
	@Column(name = "State")
	private String State;
	
	@Column(name = "PostalCode")
	private String PostalCode;
	
	@Column(name = "AcctBalance")
	private Double AcctBalance;
	
	protected User() {
		
	}
	
	public User(String firstName, String lastName, String phoneNum, String acctEmail, String acctPass, String acctSSN,
			String sSNLastFour, String passSalt, String streetAddress, String city, String state, String postalCode,
			Double acctBalance) {
		this.FirstName = firstName;
		this.LastName = lastName;
		this.PhoneNum = phoneNum;
		this.AcctEmail = acctEmail;
		this.AcctPass = acctPass;
		this.AcctSSN = acctSSN;
		this.SSNLastFour = sSNLastFour;
		this.PassSalt = passSalt;
		this.StreetAddress = streetAddress;
		this.City = city;
		this.State = state;
		this.PostalCode = postalCode;
		this.AcctBalance = acctBalance;
	}
}
