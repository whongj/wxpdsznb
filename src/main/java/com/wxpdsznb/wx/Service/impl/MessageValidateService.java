package com.wxpdsznb.wx.Service.impl;

import com.wxpdsznb.wx.clients.client.AbsRestClient;
import com.wxpdsznb.wx.clients.client.JsonReqClient;
import com.wxpdsznb.wx.clients.client.XmlReqClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Tyranitarx on 2017/4/27.
 */
@Service("MessageValidateService")
public class MessageValidateService {
    public AbsRestClient InstantiationRestAPI(boolean enable) {
            if(enable) {
                return new JsonReqClient();
            } else {
                return new XmlReqClient();
            }
        }
        public Map<String,String> testTemplateSMS(boolean json, String accountSid, String authToken, String appId, String templateId, String to, String param) {
            try {
                ObjectMapper mapper=new ObjectMapper();
                Map<String,String> resultmap;
                String result = InstantiationRestAPI(json).templateSMS(accountSid, authToken, appId, templateId, to, param);
                resultmap=mapper.readValue(result,Map.class);
                return resultmap;
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            return null;
        }

}
