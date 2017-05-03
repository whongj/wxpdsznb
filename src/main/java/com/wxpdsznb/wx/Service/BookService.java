package com.wxpdsznb.wx.Service;

import com.wxpdsznb.wx.entity.BookBean;

import java.util.List;

/**
 * Created by Tyranitarx on 2017/4/23.
 */
public interface BookService {
    public List<BookBean> getTypeBooks(String btype);
}
