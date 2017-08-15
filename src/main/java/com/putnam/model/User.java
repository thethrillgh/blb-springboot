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
	private long userid;
 
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "phonenum")
	private String phonenum;
	
	@Column(name = "acctemail")
	private String acctemail;

	@Column(name = "acctpass")
	private String acctpass;
	
	@Column(name = "acctssn")
	private String acctssn;
	
	@Column(name = "ssnlastfour")
	private String ssnlastfour;
	
	@Column(name = "passsalt")
	private String passsalt;
	
	@Column(name = "streetaddress")
	private String streetaddress;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "postalcode")
	private String postalcode;
	
	@Column(name = "acctbalance")
	private double acctbalance;
	
	protected User() {
		super();
	}
	
	public User(String firstname, String lastname, String phonenum, String acctemail, String acctpass, String acctssn,
			String ssnlastfour, String passsalt, String streetaddress, String city, String state, String postalcode,
			double acctbalance) {
		this();
		this.firstname = firstname;
		this.lastname = lastname;
		this.phonenum = phonenum;
		this.acctemail = acctemail;
		this.acctpass = acctpass;
		this.acctssn = acctssn;
		this.ssnlastfour = ssnlastfour;
		this.passsalt = passsalt;
		this.streetaddress = streetaddress;
		this.city = city;
		this.state = state;
		this.postalcode = postalcode;
		this.acctbalance = acctbalance;
	}
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstname=" + firstname + ", lastname=" + lastname + ", phonenum="
				+ phonenum + ", acctemail=" + acctemail + ", acctpass=" + acctpass + ", acctssn=" + acctssn
				+ ", ssnlastfour=" + ssnlastfour + ", passsalt=" + passsalt + ", streetaddress=" + streetaddress
				+ ", city=" + city + ", state=" + state + ", postalcode=" + postalcode + ", acctbalance=" + acctbalance
				+ "]";
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getAcctemail() {
		return acctemail;
	}

	public void setAcctemail(String acctemail) {
		this.acctemail = acctemail;
	}

	public String getAcctpass() {
		return acctpass;
	}

	public void setAcctpass(String acctpass) {
		this.acctpass = acctpass;
	}

	public String getAcctssn() {
		return acctssn;
	}

	public void setAcctssn(String acctssn) {
		this.acctssn = acctssn;
	}

	public String getSsnlastfour() {
		return ssnlastfour;
	}

	public void setSsnlastfour(String ssnlastfour) {
		this.ssnlastfour = ssnlastfour;
	}

	public String getPasssalt() {
		return passsalt;
	}

	public void setPasssalt(String passsalt) {
		this.passsalt = passsalt;
	}

	public String getStreetaddress() {
		return streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public double getAcctbalance() {
		return acctbalance;
	}

	public void setAcctbalance(double acctbalance) {
		this.acctbalance = acctbalance;
	}
}
