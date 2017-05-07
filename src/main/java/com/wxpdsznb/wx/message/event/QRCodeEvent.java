package com.wxpdsznb.wx.message.event;

/**
 * 二维码扫描
 * Created by ${wanghongjie} on 2017/5/7.
 */
public class QRCodeEvent extends BaseEvent {
    // 事件KEY值
    private String EventKey;
    // 用于换取二维码图片
    private String Ticket;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }
}
