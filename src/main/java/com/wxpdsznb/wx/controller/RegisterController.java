package com.wxpdsznb.wx.controller;

import com.wxpdsznb.wx.Service.impl.MessageValidateService;
import com.wxpdsznb.wx.Service.impl.PhoneRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Random;


/**
 * Created by Tyranitarx on 2017/4/22.
 */
@Controller
public class RegisterController {
@Autowired
@Qualifier("PhoneRegisterService")
private PhoneRegisterService registerService;
    @RequestMapping("/phoneregist")
    public String PhoneRegister(@RequestBody Map<String,String> user, HttpServletRequest request)throws IOException,ServletException{
        if(registerService.register(user.get("phonenum"),user.get("pwd"))>0){
        if(user.get("messagenum").equals(request.getSession().getAttribute("code"))) {
            return "{\"code\":\"1\"}";
        }
    else {
            return "{\"code\":\"2\"}";
        }
    }
    else {
        return "{\"code\":\"0\"}";
    }
}
}
