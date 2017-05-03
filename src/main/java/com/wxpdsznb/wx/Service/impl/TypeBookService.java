package com.wxpdsznb.wx.Service.impl;

import com.wxpdsznb.wx.entity.BookBean;
import com.wxpdsznb.wx.mapper.TypeBookMapper;
import com.wxpdsznb.wx.Service.BookService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Tyranitarx on 2017/4/23.
 */
public class TypeBookService implements BookService {
    @Autowired
    private TypeBookMapper typeBookMapper;
    @Override
    public List<BookBean> getTypeBooks(String btype){
        return typeBookMapper.getALLTypeBooks(btype);
    }
}

