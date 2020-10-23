package com.dengooo.alipay.pay;

import com.alibaba.fastjson.JSON;

public class AlipayTradePagePayReqParam {
    /*
        商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
     */
    private String out_trade_no;
    /*
        销售产品码，与支付宝签约的产品码名称。
        注：目前仅支持FAST_INSTANT_TRADE_PAY
     */
    private String product_code = "FAST_INSTANT_TRADE_PAY";
    /*
        订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]。
     */
    private String total_amount;
    /*
        订单标题
     */
    private String subject;
    /*
        订单描述
     */
    private String 	body;
    /*
        该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。
        m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
        该参数数值不接受小数点， 如 1.5h，可转换为 90m
     */
    private String timeout_express = "30m";

    /*
        请求后页面的集成方式。
        取值范围：
        1. ALIAPP：支付宝钱包内
        2. PCWEB：PC端访问
        默认值为PCWEB。
     */
    private String integration_type = "PCWEB";

    public AlipayTradePagePayReqParam(String out_trade_no, String total_amount, String subject, String body) {
        this.out_trade_no = out_trade_no;
        this.total_amount = total_amount;
        this.subject = subject;
        this.body = body;
    }

    public AlipayTradePagePayReqParam(String out_trade_no, String total_amount, String subject, String body, String timeout_express) {
        this.out_trade_no = out_trade_no;
        this.total_amount = total_amount;
        this.subject = subject;
        this.body = body;
        this.timeout_express = timeout_express;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public String getIntegration_type() {
        return integration_type;
    }

    public void setIntegration_type(String integration_type) {
        this.integration_type = integration_type;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
