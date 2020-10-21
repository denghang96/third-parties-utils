package com.dengooo.alipay.config;

public class AliPayConfig {
    /*
        应用公钥
     */
    private String appPublicKey;
    /*
        支付宝公钥
     */
    private String aliPayPublicKey;
    /*
        应用私钥
     */
    private String appPrivateKey;
    /*
        加密方式 建议RSA2
     */
    private String signType;
    /*
        appId
     */
    private String appId;
    /*
        支付宝网关
     */
    private String gateWay;
    /*
        商户UID
     */
    private String uid;
    /*
        请求使用的编码格式
     */
    private String charset;
    /*
        支付宝服务器主动通知商户服务器里指定的页面http/https路径。
     */
    private String notifyUrl;
    /*
        页面返回的Url
     */
    private String returnUrl;

    public String getAppPublicKey() {
        return appPublicKey;
    }

    public void setAppPublicKey(String appPublicKey) {
        this.appPublicKey = appPublicKey;
    }

    public String getAliPayPublicKey() {
        return aliPayPublicKey;
    }

    public void setAliPayPublicKey(String aliPayPublicKey) {
        this.aliPayPublicKey = aliPayPublicKey;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getGateWay() {
        return gateWay;
    }

    public void setGateWay(String gateWay) {
        this.gateWay = gateWay;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
