package com.wxpdsznb.wx.entity;

import java.io.Serializable;

/**
 * Created by Tyranitarx on 2017/4/22.
 */
public class BookBean implements Serializable {
private int bid;
private String isbn;
private String bname;

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    private String btype;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }
}
