package com.example.shopeeappconnections.Model;

import java.util.Date;

public class Voucher {
    private int id;
    private String code;
    private String Storeid;
    private  int soluong;
    private float DiscountOercent;
    private Date StartDate;
    private Date Enddate;

    public Voucher(int id, String code, String storeid, int soluong, float discountOercent, Date startDate, Date enddate) {
        this.id = id;
        this.code = code;
        Storeid = storeid;
        this.soluong = soluong;
        DiscountOercent = discountOercent;
        StartDate = startDate;
        Enddate = enddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStoreid() {
        return Storeid;
    }

    public void setStoreid(String storeid) {
        Storeid = storeid;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public float getDiscountOercent() {
        return DiscountOercent;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEnddate() {
        return Enddate;
    }

    public void setEnddate(Date enddate) {
        Enddate = enddate;
    }
}
