package com.dengooo.wx.req;

import com.dengooo.wx.resp.PayRespBaseVo;

public class PayReqBaseVo {
    /**
     *
     *   appid是微信公众账号或开放平台APP的唯一标识，
     *   在公众平台申请公众账号或者在开放平台申请APP账号后，
     *  微信会自动分配对应的appid，用于标识该应用。
     *  可在微信公众平台-->开发-->基本配置里面查看，商户的微信支付审核通过邮件中也会包含该字段值。
     *  API参数名 appid
     */
    protected String appid;
    /**
     *
     *   微信支付商户号.商户申请微信支付后，由微信支付分配的商户收款账号。
     *   API参数名 mch_id
     */
    protected String mch_id;
    /*
        随机字符串，长度要求在32位以内。
     */
    protected String nonce_str;
    /**
     *   通过签名算法计算得出的签名值.
     */
    protected String sign;
    /**
     *   签名类型，只支持HMAC-SHA256或MD5。
     *   WXPayConstants.HMACSHA256
     *   WXPayConstants.MD5
     */
    protected String sign_type;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }
}
