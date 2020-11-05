package com.dengooo.wx.resp;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class AccessToken implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信公众号初始化需要的票据。两个小时内是不变的
     */
    private String access_token;
    /**
     * 票据的过期时间，微信端给的是两个小时
     */
    private Long expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
