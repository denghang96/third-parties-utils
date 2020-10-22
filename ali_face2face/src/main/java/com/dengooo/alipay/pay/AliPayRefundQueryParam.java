package com.dengooo.alipay.pay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.internal.util.StringUtils;

/**
 * 退款结果查询参数
 */
public class AliPayRefundQueryParam {
    /*
        商户流水号
     */
    private String out_trade_no;
    /*
        支付宝支付流水号
     */
    private String trade_no;
    /*
        退款请求的编号
     */
    private String out_request_no;
    /*
        查询选项，商户通过上送该参数来定制同步需要额外返回的信息字段，数组格式。如：["refund_detail_item_list"]
     */
    private String[] query_options;

    public AliPayRefundQueryParam(String out_trade_no, String trade_no) {
        this.out_trade_no = out_trade_no;
        this.out_request_no = out_trade_no;
        this.trade_no = trade_no;
    }

    public AliPayRefundQueryParam(String out_trade_no, String trade_no, String out_request_no) {
        this.trade_no = trade_no;
        this.out_trade_no = out_trade_no;
        if (StringUtils.isEmpty(out_request_no)) {
            this.out_request_no = out_trade_no;
        }else {
            this.out_request_no = out_request_no;
        }
    }

    public AliPayRefundQueryParam(String out_trade_no, String trade_no, String out_request_no, String[] query_options) {
        this.trade_no = trade_no;
        this.out_trade_no = out_trade_no;
        if (StringUtils.isEmpty(out_request_no)) {
            this.out_request_no = out_trade_no;
        }else {
            this.out_request_no = out_request_no;
        }
        this.query_options = query_options;
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

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
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
