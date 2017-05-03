package com.wxpdsznb.wx.Service.impl;

import com.wxpdsznb.wx.entity.UserBean;
import com.wxpdsznb.wx.Service.UserService;
import com.wxpdsznb.wx.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tyranitarx on 2017/4/5.
 */
@Service("PhoneLoginService")
public class PhoneLoginService implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserBean login(String phoneNum, String pwd) {

        return userMapper.login(phoneNum,pwd);
    }
}
