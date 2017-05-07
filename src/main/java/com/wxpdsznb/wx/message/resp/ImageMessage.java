package com.wxpdsznb.wx.message.resp;

/**
 * Created by ${wanghongjie} on 2017/5/7.
 */
public class ImageMessage extends BaseMessage {
    // 图片
    private Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}
