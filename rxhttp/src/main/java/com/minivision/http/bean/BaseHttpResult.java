package com.minivision.http.bean;

import java.util.ArrayList;

/**
 * http result 基类
 *
 * @author gf
 * @date 2019/10/22
 */
public class BaseHttpResult<T> {
    private int resCode;
    private ArrayList<ResMsg> resMsg;
    private T resData;

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public ArrayList<ResMsg> getResMsg() {
        return resMsg;
    }

    public void setResMsg(ArrayList<ResMsg> resMsg) {
        this.resMsg = resMsg;
    }

    public T getResData() {
        return resData;
    }

    public void setResData(T resData) {
        this.resData = resData;
    }

    @Override public String toString() {
        return "BaseHttpResult{" +
                "resCode=" + resCode +
                ", resMsg=" + resMsg +
                ", data=" + resData +
                '}';
    }
}
