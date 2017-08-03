package com.putnam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Bond.
 */
@Entity
@Table(name = "bond")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bond implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cusip")
    private String cusip;

    @Column(name = "is_owned")
    private Boolean isOwned;

    @Column(name = "sandp_rating")
    private String sandpRating;

    @Column(name = "moody_rating")
    private String moodyRating;

    @Column(name = "fitch_rating")
    private String fitchRating;

    @Column(name = "coupon_type")
    private String couponType;

    @Column(name = "yield_change")
    private Double yieldChange;

    @Column(name = "yield_change_percent")
    private Double yieldChangePercent;

    @Column(name = "debt_or_asset_class")
    private String debtOrAssetClass;

    @Column(name = "security_id")
    private String securityId;

    @Column(name = "issuer_id")
    private String issuerId;

    @Column(name = "issuer_descrip")
    private String issuerDescrip;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "coupon_rate")
    private Double couponRate;

    @Column(name = "maturity_date")
    private LocalDate maturityDate;

    @Column(name = "market_price")
    private Double marketPrice;

    @Column(name = "face_value")
    private Double faceValue;

    @Column(name = "bond_symbol")
    private String bondSymbol;

    @Column(name = "callable")
    private Boolean callable;

    @ManyToOne
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCusip() {
        return cusip;
    }

    public Bond cusip(String cusip) {
        this.cusip = cusip;
        return this;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    public Boolean isIsOwned() {
        return isOwned;
    }

    public Bond isOwned(Boolean isOwned) {
        this.isOwned = isOwned;
        return this;
    }

    public void setIsOwned(Boolean isOwned) {
        this.isOwned = isOwned;
    }

    public String getSandpRating() {
        return sandpRating;
    }

    public Bond sandpRating(String sandpRating) {
        this.sandpRating = sandpRating;
        return this;
    }

    public void setSandpRating(String sandpRating) {
        this.sandpRating = sandpRating;
    }

    public String getMoodyRating() {
        return moodyRating;
    }

    public Bond moodyRating(String moodyRating) {
        this.moodyRating = moodyRating;
        return this;
    }

    public void setMoodyRating(String moodyRating) {
        this.moodyRating = moodyRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public Bond fitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
        return this;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public String getCouponType() {
        return couponType;
    }

    public Bond couponType(String couponType) {
        this.couponType = couponType;
        return this;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Double getYieldChange() {
        return yieldChange;
    }

    public Bond yieldChange(Double yieldChange) {
        this.yieldChange = yieldChange;
        return this;
    }

    public void setYieldChange(Double yieldChange) {
        this.yieldChange = yieldChange;
    }

    public Double getYieldChangePercent() {
        return yieldChangePercent;
    }

    public Bond yieldChangePercent(Double yieldChangePercent) {
        this.yieldChangePercent = yieldChangePercent;
        return this;
    }

    public void setYieldChangePercent(Double yieldChangePercent) {
        this.yieldChangePercent = yieldChangePercent;
    }

    public String getDebtOrAssetClass() {
        return debtOrAssetClass;
    }

    public Bond debtOrAssetClass(String debtOrAssetClass) {
        this.debtOrAssetClass = debtOrAssetClass;
        return this;
    }

    public void setDebtOrAssetClass(String debtOrAssetClass) {
        this.debtOrAssetClass = debtOrAssetClass;
    }

    public String getSecurityId() {
        return securityId;
    }

    public Bond securityId(String securityId) {
        this.securityId = securityId;
        return this;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public Bond issuerId(String issuerId) {
        this.issuerId = issuerId;
        return this;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getIssuerDescrip() {
        return issuerDescrip;
    }

    public Bond issuerDescrip(String issuerDescrip) {
        this.issuerDescrip = issuerDescrip;
        return this;
    }

    public void setIssuerDescrip(String issuerDescrip) {
        this.issuerDescrip = issuerDescrip;
    }

    public String getProductType() {
        return productType;
    }

    public Bond productType(String productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getCouponRate() {
        return couponRate;
    }

    public Bond couponRate(Double couponRate) {
        this.couponRate = couponRate;
        return this;
    }

    public void setCouponRate(Double couponRate) {
        this.couponRate = couponRate;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public Bond maturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
        return this;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public Bond marketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
        return this;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getFaceValue() {
        return faceValue;
    }

    public Bond faceValue(Double faceValue) {
        this.faceValue = faceValue;
        return this;
    }

    public void setFaceValue(Double faceValue) {
        this.faceValue = faceValue;
    }

    public String getBondSymbol() {
        return bondSymbol;
    }

    public Bond bondSymbol(String bondSymbol) {
        this.bondSymbol = bondSymbol;
        return this;
    }

    public void setBondSymbol(String bondSymbol) {
        this.bondSymbol = bondSymbol;
    }

    public Boolean isCallable() {
        return callable;
    }

    public Bond callable(Boolean callable) {
        this.callable = callable;
        return this;
    }

    public void setCallable(Boolean callable) {
        this.callable = callable;
    }

    public Order getOrder() {
        return order;
    }

    public Bond order(Order order) {
        this.order = order;
        return this;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bond bond = (Bond) o;
        if (bond.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bond.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bond{" +
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
