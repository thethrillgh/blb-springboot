package com.putnam.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Bond entity.
 */
public class BondDTO implements Serializable {

    private Long id;

    private String cusip;

    private Boolean isOwned;

    private String sandpRating;

    private String moodyRating;

    private String fitchRating;

    private String couponType;

    private Double yieldChange;

    private Double yieldChangePercent;

    private String debtOrAssetClass;

    private String securityId;

    private String issuerId;

    private String issuerDescrip;

    private String productType;

    private Double couponRate;

    private LocalDate maturityDate;

    private Double marketPrice;

    private Double faceValue;

    private String bondSymbol;

    private Boolean callable;

    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCusip() {
        return cusip;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    public Boolean isIsOwned() {
        return isOwned;
    }

    public void setIsOwned(Boolean isOwned) {
        this.isOwned = isOwned;
    }

    public String getSandpRating() {
        return sandpRating;
    }

    public void setSandpRating(String sandpRating) {
        this.sandpRating = sandpRating;
    }

    public String getMoodyRating() {
        return moodyRating;
    }

    public void setMoodyRating(String moodyRating) {
        this.moodyRating = moodyRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Double getYieldChange() {
        return yieldChange;
    }

    public void setYieldChange(Double yieldChange) {
        this.yieldChange = yieldChange;
    }

    public Double getYieldChangePercent() {
        return yieldChangePercent;
    }

    public void setYieldChangePercent(Double yieldChangePercent) {
        this.yieldChangePercent = yieldChangePercent;
    }

    public String getDebtOrAssetClass() {
        return debtOrAssetClass;
    }

    public void setDebtOrAssetClass(String debtOrAssetClass) {
        this.debtOrAssetClass = debtOrAssetClass;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getIssuerDescrip() {
        return issuerDescrip;
    }

    public void setIssuerDescrip(String issuerDescrip) {
        this.issuerDescrip = issuerDescrip;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getCouponRate() {
        return couponRate;
    }

    public void setCouponRate(Double couponRate) {
        this.couponRate = couponRate;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Double faceValue) {
        this.faceValue = faceValue;
    }

    public String getBondSymbol() {
        return bondSymbol;
    }

    public void setBondSymbol(String bondSymbol) {
        this.bondSymbol = bondSymbol;
    }

    public Boolean isCallable() {
        return callable;
    }

    public void setCallable(Boolean callable) {
        this.callable = callable;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BondDTO bondDTO = (BondDTO) o;
        if(bondDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bondDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BondDTO{" +
            "id=" + getId() +
            ", cusip='" + getCusip() + "'" +
            ", isOwned='" + isIsOwned() + "'" +
            ", sandpRating='" + getSandpRating() + "'" +
            ", moodyRating='" + getMoodyRating() + "'" +
            ", fitchRating='" + getFitchRating() + "'" +
            ", couponType='" + getCouponType() + "'" +
            ", yieldChange='" + getYieldChange() + "'" +
            ", yieldChangePercent='" + getYieldChangePercent() + "'" +
            ", debtOrAssetClass='" + getDebtOrAssetClass() + "'" +
            ", securityId='" + getSecurityId() + "'" +
            ", issuerId='" + getIssuerId() + "'" +
            ", issuerDescrip='" + getIssuerDescrip() + "'" +
            ", productType='" + getProductType() + "'" +
            ", couponRate='" + getCouponRate() + "'" +
            ", maturityDate='" + getMaturityDate() + "'" +
            ", marketPrice='" + getMarketPrice() + "'" +
            ", faceValue='" + getFaceValue() + "'" +
            ", bondSymbol='" + getBondSymbol() + "'" +
            ", callable='" + isCallable() + "'" +
            "}";
    }
}
