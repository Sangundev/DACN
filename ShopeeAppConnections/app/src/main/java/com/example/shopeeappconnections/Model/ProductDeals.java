package com.example.shopeeappconnections.Model;

public class ProductDeals {
    private String productId;
    private String productName;
    private String linkimage;
    private float price;
    private float GiaGiamTheoKhungGio;
    private int phantramgiamgia;

    public ProductDeals(String productId, String productName, float price, float giaGiamTheoKhungGio, int phantramgiamgia,String linkimage) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.GiaGiamTheoKhungGio = giaGiamTheoKhungGio;
        this.phantramgiamgia = phantramgiamgia;
        this.linkimage = linkimage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getGiaGiamTheoKhungGio() {
        return GiaGiamTheoKhungGio;
    }

    public void setGiaGiamTheoKhungGio(float giaGiamTheoKhungGio) {
        GiaGiamTheoKhungGio = giaGiamTheoKhungGio;
    }

    public int getPhantramgiamgia() {
        return phantramgiamgia;
    }

    public void setPhantramgiamgia(int phantramgiamgia) {
        this.phantramgiamgia = phantramgiamgia;
    }

    public String getLinkimage() {
        return linkimage;
    }

    public void setLinkimage(String linkimage) {
        this.linkimage = linkimage;
    }
}
