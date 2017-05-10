package com.wxpdsznb.wx.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wanghongjie on 2017/5/10.
 */
public class GetAccesstoken implements HandlerInterceptor {

    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
            // 获取web.xml中配置的参数
            TokenThread.appid = getInitParameter("appid");
            TokenThread.appsecret = getInitParameter("appsecret");

            log.info("weixin api appid:{}", TokenThread.appid);
            log.info("weixin api appsecret:{}", TokenThread.appsecret);

            // 未配置appid、appsecret时给出提示
            if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {
                log.error("appid and appsecret configuration error, please check carefully.");
            } else {
                // 启动定时获取access_token的线程
                new Thread(new TokenThread()).start();
            }




        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
