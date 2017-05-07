package com.wxpdsznb.wx.controller;
import com.wxpdsznb.wx.entity.UserBean;
import com.wxpdsznb.wx.Service.impl.PhoneLoginService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;


/**
 * Created by Tyranitarx on 2017/4/5.
 */
@Controller
public class LoginController {
    private static Logger logger=Logger.getLogger(LoginController.class);
    @Autowired
    @Qualifier("PhoneLoginService")
    private PhoneLoginService loginService;
    @ResponseBody
    @RequestMapping("/phonelogin")
    public String UserLogin(@RequestBody HashMap<String,String> user)throws IOException,ServletException {
       if(loginService.login(user.get("phonenum"),user.get("pwd"))==null){
           return "{\"code\":\"0\"}";
       }
       else {
           return "{\"code\":\"1\"}";
       }
    }
}
