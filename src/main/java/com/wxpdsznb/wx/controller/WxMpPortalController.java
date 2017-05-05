package com.wxpdsznb.wx.controller;

import com.wxpdsznb.wx.Service.impl.*;
import com.wxpdsznb.wx.dao.BookIsbnMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Created by Tyranitarx  on 2017/5/3.
 */
@RestController
@RequestMapping("/wechat/portal")
public class WxMpPortalController {
    @Autowired
    @Qualifier("BookDetailClinetService")
    private BookDetailClientService bookDetailClientService;
    @Autowired
    private BookIsbnMapper bookIsbnMapper;
    @Autowired
    private WeixinService wxService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

        if (this.getWxService().checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

    @ResponseBody
    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@RequestBody String requestBody, @RequestParam("signature") String signature,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature,
                       @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce) {
        this.logger.info(
                "\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                signature, encType, msgSignature, timestamp, nonce, requestBody);

        if (!this.wxService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = null;
        if (encType == null) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toXml();
        } else if ("aes".equals(encType)) {
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody,
                    this.getWxService().getWxMpConfigStorage(), timestamp, nonce, msgSignature);
            this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
            WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toEncryptedXml(this.getWxService().getWxMpConfigStorage());
        }

        this.logger.debug("\n组装回复信息：{}", out);

        return out;
    }

    protected WeixinService getWxService() {
        return this.wxService;
    }
// getBookDetail
    @ResponseBody
    @GetMapping("/getBookDetail")
    public Map getBookDetail(@RequestBody Map<String,String> BookName){
        String bisbn=bookIsbnMapper.getALLTypeBooks(BookName.get("bname"));
        return bookDetailClientService.doPost(bisbn,"UTF-8");
    }

    @RequestMapping(value = "/{formName}")
    public String loginform(@PathVariable String formName){
        return formName;
    }

//    手机注册
    @Autowired
    @Qualifier("PhoneLoginService")
    private PhoneLoginService loginService;
    @ResponseBody
    @PostMapping("/phonelogin")
    public String UserLogin(@RequestBody HashMap<String,String> user)throws IOException,ServletException {
        if(loginService.login(user.get("phonenum"),user.get("pwd"))==null){
            return "{\"code\":\"0\"}";
        }
        else {
            return "{\"code\":\"1\"}";
        }
    }
//    手机注册的信息验证？
    @Autowired
    @Qualifier("MessageValidateService")
    private MessageValidateService messageValidateService;
        @ResponseBody
        @GetMapping("/getMessage")
        public String getMessage(@RequestBody Map<String,String> user, HttpServletRequest request){
            boolean json=true;
            String accountSid="9de3bf55d1bca522f0bc6bd1fa4cbcb6";
            String token="982f81349374e0eb9b95d64fcfe73095";
            String appId="00f29fdaa1d54801a39756a51196f845";
            String templateId="43550";
            String to=user.get("phonenum");
            Random random = new Random();
            int para = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
            String param=Integer.toString(para);
            request.getSession().setAttribute("code",param);
            Map resultmap=messageValidateService.testTemplateSMS(json, accountSid,token,appId, templateId,to,param);
            if (resultmap!=null) {
                Map resp = (Map) resultmap.get("resp");
                if ((resp.get("respCode").equals("000000"))) {
                    return "{\"code\":\"1\"}";
                } else {
                    return "{\"code\":\"0\"}";
                }

            }
            return "{\"code\":\"0\"}";
        }



    @Autowired
    @Qualifier("PhoneRegisterService")
    private PhoneRegisterService registerService;
    @PostMapping("/phoneregist")
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
