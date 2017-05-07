package com.wxpdsznb.wx.message.resp;

/**
 * Created by ${wanghongjie} on 2017/5/7.
 */
public class VoiceMessage extends BaseMessage {
    // 语音
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
