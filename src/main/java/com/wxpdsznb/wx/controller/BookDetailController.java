package com.wxpdsznb.wx.controller;

import com.wxpdsznb.wx.dao.BookIsbnMapper;
import com.wxpdsznb.wx.Service.impl.BookDetailClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Tyranitarx on 2017/5/2.
 */
@Controller
public class BookDetailController{
    @Autowired
    @Qualifier("BookDetailClinetService")
    private BookDetailClientService bookDetailClientService;
    @Autowired
    private BookIsbnMapper bookIsbnMapper;
    @ResponseBody
    @RequestMapping("/getBookDetail")
    public Map getBookDetail(@RequestBody Map<String,String> BookName){
        String bisbn=bookIsbnMapper.getALLTypeBooks(BookName.get("bname"));
        return bookDetailClientService.doPost(bisbn,"UTF-8");
    }
}
