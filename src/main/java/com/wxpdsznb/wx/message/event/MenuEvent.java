package com.wxpdsznb.wx.message.event;

/**
 * 自定义菜单
 * Created by ${wanghongjie} on 2017/5/7.
 */
public class MenuEvent extends BaseEvent {
    // 事件KEY值，与自定义菜单接口中KEY值对应
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
