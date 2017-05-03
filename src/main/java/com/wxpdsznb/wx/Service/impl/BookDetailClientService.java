package com.wxpdsznb.wx.Service.impl;

import com.wdsznb.books.clients.client.SSLHttpClient;
import com.wdsznb.books.services.HttpClientService;
import com.wxpdsznb.wx.Service.HttpClientService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;


import java.util.Map;

/**
 * Created by Tyranitarx on 2017/5/2.
 */
@Service("BookDetailClinetService")
public class BookDetailClientService implements HttpClientService {
    @Override
    public Map<String,String> doPost(String isbn,String charset){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        Map resultmap=null;
        try {
            httpClient = new SSLHttpClient().registerSSL("0","TLS",443,"https");
            httpPost = new HttpPost("https://api.douban.com/v2/book/isbn/"+isbn);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                    ObjectMapper mapper=new ObjectMapper();
                    resultmap=mapper.readValue(result,Map.class);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultmap;

    }
}