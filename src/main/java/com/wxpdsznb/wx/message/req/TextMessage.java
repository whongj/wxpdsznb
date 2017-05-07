package com.wxpdsznb.wx.message.req;

/**
 * Created by ${wanghongjie} on 2017/5/7.
 */
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
