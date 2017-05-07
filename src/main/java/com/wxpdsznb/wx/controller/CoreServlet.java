package com.wxpdsznb.wx.controller;

import com.wxpdsznb.wx.Service.impl.WeixinService;
import com.wxpdsznb.wx.util.SignUtil;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.junit.experimental.theories.ParametersSuppliedBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ${wanghongjie} on 2017/5/6.
 */
/**
 * @author Binary Wang
 */
/**
 * 请求处理的核心类
 *

 */

@RestController
@RequestMapping(value = "/wechat/portal")
public class CoreServlet{
    private static final long serialVersionUID = 4440739483644821986L;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 请求校验（确认请求来自微信服务器）
     */
    @ResponseBody
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(@RequestParam(name = "signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr) {
        this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
           return echostr;
        }

        return "非法请求";
    }


    /**
     * 处理微信服务器发来的消息
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 消息的接收、处理、响应
    }
}
