package com.dengooo.wx.consts;

public enum PayType {
    JSAPI("JSAPI"), NATIVE("NATIVE");
    PayType(String PAYTYPE) {
        this.PAYTYPE = PAYTYPE;
    }
    public String PAYTYPE;
}
