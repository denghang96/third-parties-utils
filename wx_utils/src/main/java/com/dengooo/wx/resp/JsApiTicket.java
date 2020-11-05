package com.dengooo.wx.resp;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class JsApiTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer errcode;

    private String errmsg;

    private String ticket;

    private Integer expires_in;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
