package com.wxpdsznb.wx.message.resp;

/**
 * Created by ${wanghongjie} on 2017/5/7.
 */
public class TextMessage extends BaseMessage {
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
