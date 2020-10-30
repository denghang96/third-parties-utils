package com.dengooo.alipay.pay;

import com.alibaba.fastjson.JSON;

/*
    alipay.trade.precreate(统一收单线下交易预创建)
 */
public class AliPayPrecreateReqParam {
    /**
     *  订单号
     */
    private String out_trade_no;
    /**
     *  总金额
     */
    private String total_amount;
    /**
     *   订单标题
     */
    private String subject;
    /**
     *  商品描述
     */
    private String body;
    /**
     *  该笔订单允许的最晚付款时间，逾期将关闭交易，从生成二维码开始计时。
     *  取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
     *  该参数数值不接受小数点， 如 1.5h，需转换为 90m。
     */
    private String qr_code_timeout_express = "30m";

    public AliPayPrecreateReqParam(String out_trade_no, String total_amount, String subject, String qr_code_timeout_express) {
        this.out_trade_no = out_trade_no;
        this.total_amount = total_amount;
        this.subject = subject;
        this.qr_code_timeout_express = qr_code_timeout_express;
    }

    public AliPayPrecreateReqParam(String out_trade_no, String total_amount, String subject) {
        this.out_trade_no = out_trade_no;
        this.total_amount = total_amount;
        this.subject = subject;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
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

    public String getQr_code_timeout_express() {
        return qr_code_timeout_express;
    }

    public void setQr_code_timeout_express(String qr_code_timeout_express) {
        this.qr_code_timeout_express = qr_code_timeout_express;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
