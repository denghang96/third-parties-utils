package com.dengooo.alipay.pay;

import com.alibaba.fastjson.JSON;

/**
 * 支付结果查询参数
 */
public class AliPayQueryParam {
    /*
        订单支付时传入的商户订单号,和支付宝交易号不能同时为空。
        trade_no,out_trade_no如果同时存在优先取trade_no
     */
    private String out_trade_no;
    /*
        支付宝交易号，和商户订单号不能同时为空
     */
    private String trade_no;
    /*
        查询选项，商户通过上送该参数来定制同步需要额外返回的信息字段，数组格式。
        如：["trade_settle_info","fund_bill_list","voucher_detail_list","discount_goods_detail"]
     */
    private String[] query_options;

    public AliPayQueryParam(String out_trade_no, String trade_no) {
        this.out_trade_no = out_trade_no;
        this.trade_no = trade_no;
    }

    public AliPayQueryParam(String out_trade_no, String trade_no, String[] query_options) {
        this.out_trade_no = out_trade_no;
        this.trade_no = trade_no;
        this.query_options = query_options;
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

    public String[] getQuery_options() {
        return query_options;
    }

    public void setQuery_options(String[] query_options) {
        this.query_options = query_options;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
