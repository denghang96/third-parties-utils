package com.dengooo.alipay.pay;

import com.alibaba.fastjson.JSON;

/**
 * 退款参数
 */
public class AliPayRefundParam {
    /*
        商户订单号
     */
    private String out_trade_no;
    /*
        支付宝流水号
     */
    private String trade_no;
    /*
        退款金额
     */
    private String refund_amount;

    /*
        本次退款的请求编号
     */
    private String out_request_no;

    public AliPayRefundParam(String out_request_no, String out_trade_no, String trade_no, String refund_amount) {
        this.out_request_no = out_request_no;
        this.out_trade_no = out_trade_no;
        this.trade_no = trade_no;
        this.refund_amount = refund_amount;
    }

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(String refund_amount) {
        this.refund_amount = refund_amount;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
