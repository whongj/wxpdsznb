package com.wxpdsznb.wx.Service;

import java.util.Map;

/**
 * Created by Tyranitarx on 2017/5/2.
 */
public interface HttpClientService {
    public Map<String,String> doPost(String url, String charset);
}
