package com.wxpdsznb.wx.controller;

import com.wxpdsznb.wx.Service.impl.MessageValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;

/**
 * Created by Tyranitarx on 2017/4/27.
 */
@Controller
public class MessageValidateController {
    @Autowired
    @Qualifier("MessageValidateService")
    private MessageValidateService messageValidateService;
    @ResponseBody
    @RequestMapping("/getMessage")
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
}
