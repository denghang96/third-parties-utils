package com.dengooo.wx.req;

import com.alibaba.fastjson.JSON;

public class MicroPayReqVo extends PayReqBaseVo{
    /**
     * 商品简单描述，该字段须严格按照规范传递。
     * 如 image形象店-深圳腾大-QQ公仔，128个字符以内
     */
    private String body;
    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
     */
    private String out_trade_no;
    /**
     * 订单总金额，单位为分，只能为整数
     */
    private String total_fee;
    /**
     * 终端IP.支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
     */
    private String spbill_create_ip;
    /**
     * 扫码支付付款码，设备读取用户微信中的条码或者二维码信息
     */
    private String auth_code;
    /**
     * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    private String time_start;
    /**
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。
     * 注意：最短失效时间间隔需大于1分钟
     */
    private String time_expire;

    /**
     *
     * @param body 商品简单描述，该字段须严格按照规范传递。如 image形象店-深圳腾大-QQ公仔，128个字符以内
     * @param out_trade_no 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
     * @param total_fee 订单总金额，单位为分，只能为整数
     * @param spbill_create_ip 终端IP.支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
     * @param auth_code 扫码支付付款码，设备读取用户微信中的条码或者二维码信息
     */
    public MicroPayReqVo(String body, String out_trade_no, String total_fee, String spbill_create_ip, String auth_code) {
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.spbill_create_ip = spbill_create_ip;
        this.auth_code = auth_code;
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

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
