package com.minivision.http.bean;

/**
 * 错误信息
 * {"msgCode":"20001","msgText":"20001"}
 *
 * @author gf
 * @date 2019/10/22
 */
public class ResMsg {
    private String msgCode;
    private String msgText;

    public ResMsg() {
    }

    public ResMsg(String msgCode, String msgText) {
        this.msgCode = msgCode;
        this.msgText = msgText;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    @Override public String toString() {
        return "ResMsg{" +
                "msgCode='" + msgCode + '\'' +
                ", msgText='" + msgText + '\'' +
                '}';
    }
}
