package com.dengooo.wx.resp;

public class UnifiedOrderRespVo extends PayRespBaseVo{
    /**
     *   二维码地址
     */
    private String code_url;
    /**
     *
     *   交易类型
     *   JSAPI -JSAPI支付  --->
     *   NATIVE -Native支付 --->扫码
     *   APP -APP支付  --->APP拉起微信支付
     *   MWEB--H5支付  --->非微信内部的网页拉起微信支付
     */
    private String trade_type;
    /**
     *   预支付交易会话标识
     */
    private String prepay_id;

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    @Override
    public String toString() {
        return "UnifiedOrderRespVo{" +
                "code_url='" + code_url + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", prepay_id='" + prepay_id + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", appid='" + appid + '\'' +
                ", sign='" + sign + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                ", result_code='" + result_code + '\'' +
                ", err_code_des='" + err_code_des + '\'' +
                ", err_code='" + err_code + '\'' +
                '}';
    }
}
