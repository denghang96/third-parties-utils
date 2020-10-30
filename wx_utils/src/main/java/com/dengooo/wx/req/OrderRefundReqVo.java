package com.dengooo.wx.req;

import org.apache.commons.lang3.StringUtils;

public class OrderRefundReqVo extends PayReqBaseVo{
    /**
     *   微信生成的订单号，在支付通知中有返回
     */
    private String transaction_id;
    /**
     *   transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id> out_trade_no
     */
    private String out_trade_no;
    /**
     *   商户退款单号。商户系统内部的退款单号，商户系统内部唯一，
     *   只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     *   如果一笔订单多次退款，则每次的out_refund_no应是不同的值
     */
    private String out_refund_no;
    /**
     *   订单总金额，单位为分，只能为整数
     */
    private String total_fee;
    /**
     *   退款总金额，订单总金额，单位为分，只能为整数
     */
    private String refund_fee;
    /**
     *   异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数
     *   如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效
     */
    private String notify_url;

    public OrderRefundReqVo(String transaction_id, String out_trade_no, String out_refund_no, String total_fee, String refund_fee) {
        if (StringUtils.isEmpty(transaction_id) && StringUtils.isEmpty(out_trade_no)) {
            throw new IllegalArgumentException("transaction_id 与 out_trade_no不能同时为空");
        }
        this.transaction_id = transaction_id;
        this.out_trade_no = out_trade_no;
        this.out_refund_no = out_refund_no;
        this.total_fee = total_fee;
        this.refund_fee = refund_fee;
    }

    public OrderRefundReqVo(String transaction_id, String out_trade_no, String out_refund_no, String total_fee, String refund_fee, String notify_url) {
        if (StringUtils.isEmpty(transaction_id) && StringUtils.isEmpty(out_trade_no)) {
            throw new IllegalArgumentException("transaction_id 与 out_trade_no不能同时为空");
        }
        this.transaction_id = transaction_id;
        this.out_trade_no = out_trade_no;
        this.out_refund_no = out_refund_no;
        this.total_fee = total_fee;
        this.refund_fee = refund_fee;
        this.notify_url = notify_url;
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

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
}
