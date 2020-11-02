package com.dengooo.wx.resp;

import java.util.List;

public class OrderRefundQueryRespVo extends PayRespBaseVo{
    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     *现金支付金额，单位为分，只能为整数
     */
    private String cash_fee;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 退款总金额
     */
    private String refund_fee;
    /**
     * 订单总金额
     */
    private String total_fee;
    /**
     * 退款笔数
     */
    private String refund_count;
    /**
     * 每一笔退款的详情
     */
    private List<RefundQueryDataRespVo> refundQueryDataRespVos;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(String refund_count) {
        this.refund_count = refund_count;
    }

    public List<RefundQueryDataRespVo> getRefundQueryDataRespVos() {
        return refundQueryDataRespVos;
    }

    public void setRefundQueryDataRespVos(List<RefundQueryDataRespVo> refundQueryDataRespVos) {
        this.refundQueryDataRespVos = refundQueryDataRespVos;
    }

    @Override
    public String toString() {
        return "OrderRefundQueryRespVo{" +
                "transaction_id='" + transaction_id + '\'' +
                ", cash_fee='" + cash_fee + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", refund_fee='" + refund_fee + '\'' +
                ", total_fee='" + total_fee + '\'' +
                ", refund_count='" + refund_count + '\'' +
                ", refundQueryDataRespVos=" + refundQueryDataRespVos +
                ", nonce_str='" + nonce_str + '\'' +
                ", appid='" + appid + '\'' +
                ", sign='" + sign + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                ", result_code='" + result_code + '\'' +
                ", err_code_des='" + err_code_des + '\'' +
                ", err_code='" + err_code + '\'' +
                '}';
    }
}
