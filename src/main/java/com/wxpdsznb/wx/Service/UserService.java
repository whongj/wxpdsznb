package com.wxpdsznb.wx.Service;

import com.wxpdsznb.wx.entity.UserBean;

/**
 * Created by Tyranitarx on 2017/4/5.
 */
public interface UserService {
   UserBean login(String loginname, String password);
}
