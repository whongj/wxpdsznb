package com.wxpdsznb.wx.Service.impl;

import com.wxpdsznb.wx.dao.UserMapper;
import com.wxpdsznb.wx.dao.UserRegisterMapper;
import com.wxpdsznb.wx.dao.UserRegisterService;
import com.wxpdsznb.wx.Service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tyranitarx on 2017/4/22.
 */
@Service("PhoneRegisterService")
public class PhoneRegisterService implements UserRegisterService {
    @Autowired
    private UserRegisterMapper registerMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public int register(String phonenum, String pwd) {
        if(userMapper.login(phonenum,pwd)!=null)
        return registerMapper.RegisterUser(phonenum,pwd);
        else
            return 0;
    }
}
