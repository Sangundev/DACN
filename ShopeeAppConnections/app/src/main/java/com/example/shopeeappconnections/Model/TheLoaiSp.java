package com.example.shopeeappconnections.Model;

public class TheLoaiSp {

    private int Categoryid;
    private String Categoryname;
    private String Linkanhtt;

    public TheLoaiSp(int categoryid, String categoryname, String linkanhtt) {
        Categoryid = categoryid;
        Categoryname = categoryname;
        Linkanhtt = linkanhtt;
    }

    public int getCategoryid() {
        return Categoryid;
    }

    public void setCategoryid(int categoryid) {
        Categoryid = categoryid;
    }

    public String getCategoryname() {
        return Categoryname;
    }

    public void setCategoryname(String categoryname) {
        Categoryname = categoryname;
    }

    public String getLinkanhtt() {
        return Linkanhtt;
    }

    public void setLinkanhtt(String linkanhtt) {
        Linkanhtt = linkanhtt;
    }
}
