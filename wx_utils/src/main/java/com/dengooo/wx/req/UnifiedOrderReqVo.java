package com.dengooo.wx.req;

import org.apache.commons.lang3.StringUtils;

import java.security.InvalidParameterException;

public class UnifiedOrderReqVo extends PayReqBaseVo{

    /**
     *  交易截止时间，非必传格式为yyyyMMddHHmmss，因小于两小时
     */
    private String time_expire;
    /**
     *   商品描述
     */
    private String body;
    /**
     *   商户订单号
     */
    private String out_trade_no;
    /**
     *   订单总金额，单位为分
     */
    private String total_fee;
    /**
     *   异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    private String notify_url;
    /**
     *  交易类型
     *   JSAPI -JSAPI支付  --->
     *   NATIVE -Native支付 --->扫码
     *   APP -APP支付  --->APP拉起微信支付
     *   MWEB--H5支付  --->非微信内部的网页拉起微信支付
     */
    private String trade_type;
    /**
     *   商品ID,如果trade_type是NATIVE，此参数毕传
     */
    private String product_id;
    /**
     *   如果trade_type是NATIVE是JSAPI，此参数毕传
     */
    private String openid;

    public UnifiedOrderReqVo(String body, String out_trade_no, String total_fee, String trade_type, String product_id) {
        paramCheck();
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.trade_type = trade_type;
        this.product_id = product_id;
    }

    public UnifiedOrderReqVo(String body, String out_trade_no, String total_fee, String notify_url, String trade_type, String product_id) {
        paramCheck();
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.notify_url = notify_url;
        this.trade_type = trade_type;
        this.product_id = product_id;
    }

    public UnifiedOrderReqVo(String body, String out_trade_no, String total_fee, String notify_url, String trade_type, String product_id, String openid) {
        paramCheck();
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.notify_url = notify_url;
        this.trade_type = trade_type;
        this.product_id = product_id;
        this.openid = openid;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    private final void paramCheck() {
        if (StringUtils.isEmpty(openid) && "JSAPI".equals(this.trade_type)) {
            throw new InvalidParameterException("如果trade_type是NATIVE是JSAPI，参数openid必传");
        }
        if (StringUtils.isEmpty(product_id) && "NATIVE".equals(this.trade_type)) {
            throw new InvalidParameterException("如果trade_type是NATIVE是NATIVE，参数product_id必传");
        }
    }
}
