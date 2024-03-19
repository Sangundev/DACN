package com.example.shopeeappconnections.Model;

public class User {
    private String email;
    private String iduser;
    private String Diachi;
    private int SDT;
    private String Username;
    private String Fullname;

    private String Linkimage;

    public User(String email, String iduser, String diachi, int sdt, String username, String fullname,String Linkimage) {
        this.email = email;
        this.iduser = iduser;
        this.Diachi = diachi;
        this.SDT = sdt;
        this.Username = username;
        this.Fullname = fullname;
        this.Linkimage = Linkimage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getLinkimage() {
        return Linkimage;
    }

    public void setLinkimage(String linkimage) {
        Linkimage = linkimage;
    }
}
