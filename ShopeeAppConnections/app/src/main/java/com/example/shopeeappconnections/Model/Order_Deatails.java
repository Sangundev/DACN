package com.example.shopeeappconnections.Model;

public class Order_Deatails {
    private int Od_id;
    private String Productid;
    private int num;
    private float tt_money;
    private String Storeid;
    private String VoucherCode;
    private float Totalinvoucher;

    public Order_Deatails(int od_id, String productid, int num, float tt_money, String storeid, String voucherCode, float totalinvoucher) {
        Od_id = od_id;
        Productid = productid;
        this.num = num;
        this.tt_money = tt_money;
        Storeid = storeid;
        VoucherCode = voucherCode;
        Totalinvoucher = totalinvoucher;
    }

    public int getOd_id() {
        return Od_id;
    }

    public void setOd_id(int od_id) {
        Od_id = od_id;
    }

    public String getProductid() {
        return Productid;
    }

    public void setProductid(String productid) {
        Productid = productid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getTt_money() {
        return tt_money;
    }

    public void setTt_money(float tt_money) {
        this.tt_money = tt_money;
    }

    public String getStoreid() {
        return Storeid;
    }

    public void setStoreid(String storeid) {
        Storeid = storeid;
    }

    public String getVoucherCode() {
        return VoucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        VoucherCode = voucherCode;
    }

    public float getTotalinvoucher() {
        return Totalinvoucher;
    }

    public void setTotalinvoucher(float totalinvoucher) {
        Totalinvoucher = totalinvoucher;
    }
}
