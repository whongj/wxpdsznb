package com.wxpdsznb.wx.Interceptors;

import com.sun.org.apache.regexp.internal.RE;
import com.wxpdsznb.wx.entity.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.SessionScope;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Tyranitarx on 2017/4/12.
 */
public class IfLoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session=httpServletRequest.getSession();
        String allowUrl =new String("/login");
        String url=httpServletRequest.getRequestURL().toString();
        UserBean user=(UserBean)session.getAttribute("user");
        if(url.contains(allowUrl))
        {
            return true;
        }
        if(user==null){
            httpServletRequest.setAttribute("message","您尚未登录，请先登录。");
            httpServletRequest.getRequestDispatcher("LoginForm").forward(httpServletRequest,httpServletResponse);
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
