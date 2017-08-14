package com.putnam.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="bondhistory")
public class BondHistory implements Serializable {

    @ManyToOne
    private Bond bond;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "bid")
    private double bid;

    @Column(name = "ask")
    private double ask;

    @Column(name = "yieldbid")
    private double yieldbid;

    @Column(name = "yieldask")
    private double yieldask;

    @Column(name = "changeprice")
    private double changeprice;

    @Column(name = "cusip")
    private String cusip;


    public BondHistory() {
    }

    public BondHistory(LocalTime time, double bid, double ask, double yieldbid, double yieldask, double changeprice, String cusip){
        this.time = time;
        this.bid = bid;
        this.ask = ask;
        this.yieldbid = yieldbid;
        this.yieldask = yieldask;
        this.changeprice = changeprice;
        this.cusip = cusip;
    }

    public Bond getBond() {
        return bond;
    }

    public void setBond(Bond bond) {
        this.bond = bond;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public double getYieldbid() {
        return yieldbid;
    }

    public void setYieldbid(double yieldbid) {
        this.yieldbid = yieldbid;
    }

    public double getYieldask() {
        return yieldask;
    }

    public void setYieldask(double yieldask) {
        this.yieldask = yieldask;
    }

    public double getChangeprice() {
        return changeprice;
    }

    public void setChangeprice(double changeprice) {
        this.changeprice = changeprice;
    }

    public String getCusip() {
        return cusip;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    @Override
    public String toString() {
        return "BondHistory{" +
                "bond=" + bond +
                ", ID=" + ID +
                ", time=" + time +
                ", bid=" + bid +
                ", ask=" + ask +
                ", yieldbid=" + yieldbid +
                ", yieldask=" + yieldask +
                ", changeprice=" + changeprice +
                ", cusip='" + cusip + '\'' +
                '}';
    }
}
