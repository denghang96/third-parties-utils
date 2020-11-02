package com.dengooo.wx.req;

import org.apache.commons.lang3.StringUtils;

public class OrderRefundQueryReqVo extends PayReqBaseVo {
    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 商户退款单号
     */
    private String out_refund_no;
    /**
     * 微信退款单号
     */
    private String refund_id;

    public OrderRefundQueryReqVo(String transaction_id, String out_trade_no, String out_refund_no, String refund_id) {
        if (StringUtils.isEmpty(transaction_id)
                && StringUtils.isEmpty(out_trade_no)
                && StringUtils.isEmpty(out_refund_no)
                && StringUtils.isEmpty(refund_id)) {
            throw new IllegalArgumentException("查询退款状态请至少传入一个参数");
        }
        this.transaction_id = transaction_id;
        this.out_trade_no = out_trade_no;
        this.out_refund_no = out_refund_no;
        this.refund_id = refund_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }
}
