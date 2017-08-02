package com.putnam.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the BankAcct entity.
 */
public class BankAcctDTO implements Serializable {

    private Long id;

    private String acctNum;

    private String routingNum;

    private String acctType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(String acctNum) {
        this.acctNum = acctNum;
    }

    public String getRoutingNum() {
        return routingNum;
    }

    public void setRoutingNum(String routingNum) {
        this.routingNum = routingNum;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BankAcctDTO bankAcctDTO = (BankAcctDTO) o;
        if(bankAcctDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankAcctDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BankAcctDTO{" +
            "id=" + getId() +
            ", acctNum='" + getAcctNum() + "'" +
            ", routingNum='" + getRoutingNum() + "'" +
            ", acctType='" + getAcctType() + "'" +
            "}";
    }
}
