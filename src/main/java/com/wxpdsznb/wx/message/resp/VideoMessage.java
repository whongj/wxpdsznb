package com.wxpdsznb.wx.message.resp;

/**
 * Created by ${wanghongjie} on 2017/5/7.
 */
public class VideoMessage extends BaseMessage {
    // 视频
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }
}
