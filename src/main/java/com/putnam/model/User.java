package com.putnam.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "useraccount")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userid")
public class User implements Serializable {
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference(value="banks")
	private List<Bank> banks;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference(value="userorders")
	private List<BondOrder> orders;

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
	private Double acctbalance;

	@Column(name = "totalinvested")
	private Double totalinvested;

	@Column(name = "totalprofits")
	private Double totalprofits;
	
	private static String hashPassword(String password) {		
		return encoder.encode(password);
	}

	public static String testHash(String pass){
		return hashPassword(pass);
	}

	public static boolean isMatchingPassword(String password, String passsalt) {
		return encoder.matches(password, passsalt);
	}
	
	protected User() {
		
	}


	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

	public List<BondOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<BondOrder> orders) {
		this.orders = orders;
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
		this.passsalt = hashPassword(passsalt);
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

	public Double getAcctbalance() {
		return acctbalance;
	}

	public void setAcctbalance(Double acctbalance) {
		this.acctbalance = acctbalance;
	}

	public Double getTotalinvested() {
		return totalinvested;
	}

	public void setTotalinvested(Double totalinvested) {
		this.totalinvested = totalinvested;
	}

	public Double getTotalprofits() {
		return totalprofits;
	}

	public void setTotalprofits() {
		this.totalprofits = this.acctbalance - this.totalprofits;
	}

	public User(String firstname, String lastname, String phonenum, String acctemail, String acctpass, String acctssn,
			String ssnlastfour, String passsalt, String streetaddress, String city, String state, String postalcode,
			Double acctbalance, Double totalinvested) {
		super();
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
		this.totalinvested = totalinvested;
		this.totalprofits = acctbalance - totalinvested;
	}
	
	public User(User obj) {
		super();
		this.firstname = obj.getFirstname();
		this.lastname = obj.getLastname();
		this.phonenum = obj.getPhonenum();
		this.acctemail = obj.getAcctemail();
		this.acctpass = obj.getAcctpass();
		this.acctssn = obj.getAcctssn();
		this.ssnlastfour = obj.getSsnlastfour();
		this.passsalt = obj.getPasssalt();
		this.streetaddress = obj.getStreetaddress();
		this.city = obj.getCity();
		this.state = obj.getState();
		this.postalcode = obj.getPostalcode();
		this.acctbalance = obj.getAcctbalance();
		this.totalinvested = obj.getTotalinvested();
		this.totalprofits = obj.getTotalprofits();
	}
	
}
