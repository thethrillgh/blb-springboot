package com.putnam.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the UserAccount entity.
 */
public class UserAccountDTO implements Serializable {

    private Long id;

    private Long acctID;

    private String firstName;

    private String lastName;

    private String phoneNum;

    private String acctEmail;

    private String acctPassword;

    private String acctSSN;

    private String ssnLastFour;

    private String passSalt;

    private Double acctBalance;

    private Long userAddressId;

    private Long assocBankAccountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcctID() {
        return acctID;
    }

    public void setAcctID(Long acctID) {
        this.acctID = acctID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAcctEmail() {
        return acctEmail;
    }

    public void setAcctEmail(String acctEmail) {
        this.acctEmail = acctEmail;
    }

    public String getAcctPassword() {
        return acctPassword;
    }

    public void setAcctPassword(String acctPassword) {
        this.acctPassword = acctPassword;
    }

    public String getAcctSSN() {
        return acctSSN;
    }

    public void setAcctSSN(String acctSSN) {
        this.acctSSN = acctSSN;
    }

    public String getSsnLastFour() {
        return ssnLastFour;
    }

    public void setSsnLastFour(String ssnLastFour) {
        this.ssnLastFour = ssnLastFour;
    }

    public String getPassSalt() {
        return passSalt;
    }

    public void setPassSalt(String passSalt) {
        this.passSalt = passSalt;
    }

    public Double getAcctBalance() {
        return acctBalance;
    }

    public void setAcctBalance(Double acctBalance) {
        this.acctBalance = acctBalance;
    }

    public Long getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Long userAddressId) {
        this.userAddressId = userAddressId;
    }

    public Long getAssocBankAccountId() {
        return assocBankAccountId;
    }

    public void setAssocBankAccountId(Long bankAcctId) {
        this.assocBankAccountId = bankAcctId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserAccountDTO userAccountDTO = (UserAccountDTO) o;
        if(userAccountDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userAccountDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserAccountDTO{" +
            "id=" + getId() +
            ", acctID='" + getAcctID() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", phoneNum='" + getPhoneNum() + "'" +
            ", acctEmail='" + getAcctEmail() + "'" +
            ", acctPassword='" + getAcctPassword() + "'" +
            ", acctSSN='" + getAcctSSN() + "'" +
            ", ssnLastFour='" + getSsnLastFour() + "'" +
            ", passSalt='" + getPassSalt() + "'" +
            ", acctBalance='" + getAcctBalance() + "'" +
            "}";
    }
}
