package com.example.shopeeappconnections.Model;

public class Heart {

    private int ID;
    private String ProductName;
    private float Price;
    private String image;
    private int Productid;
    private String Userid;

    private String linkimage;

    public Heart(int id, String productName, float price, String image, int productid, String userid, String linkimage) {
        ID = id;
        ProductName = productName;
        Price = price;
        this.image = image;
        Productid = productid;
        Userid = userid;
        this.linkimage = linkimage;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProductid() {
        return Productid;
    }

    public void setProductid(int productid) {
        Productid = productid;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getLinkimage() {
        return linkimage;
    }

    public void setLinkimage(String linkimage) {
        this.linkimage = linkimage;
    }
}
