package com.dengooo.alipay.pay;

import com.alibaba.fastjson.JSON;

/**
 * 关闭订单参数
 */
public class AliPayCloseParam {
    /*
        支付宝交易流水号
     */
    private String trade_no;
    /*
        商户订单号
     */
    private String out_trade_no;

    public AliPayCloseParam(String out_trade_no, String trade_no) {
        this.trade_no = trade_no;
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
