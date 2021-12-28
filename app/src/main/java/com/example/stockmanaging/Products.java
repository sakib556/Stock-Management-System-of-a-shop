package com.example.stockmanaging;

public class Products {
    private String id,pname,pcode,pprice,pdetails;
    public Products(){

    }

    public Products(String id, String pname, String pcode, String pprice, String pdetails) {
        this.id = id;
        this.pname = pname;
        this.pcode = pcode;
        this.pprice = pprice;
        this.pdetails = pdetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getPdetails() {
        return pdetails;
    }

    public void setPdetails(String pdetails) {
        this.pdetails = pdetails;
    }
}
