package com.dengooo.wx.resp;

public class OrderRefundRespVo extends PayRespBaseVo{
    /**
     *   微信订单号
     */
    private String transaction_id;
    /**
     *   商户退款单号
     */
    private String out_refund_no;
    /**
     *  微信退款单号
     */
    private String refund_id;
    /**
     *  现金支付金额
     */
    private String cash_fee;
    /**
     *   商户订单号
     */
    private String out_trade_no;
    /**
     *  代金券退款总金额
     */
    private String coupon_refund_fee;
    /**
     *  退款总金额,单位为分,可以做部分退款
     */
    private String refund_fee;
    /**
     *  订单总金额，单位为分，只能为整数
     */
    private String total_fee;
    /**
     *  退款代金券使用数量
     */
    private String coupon_refund_count;
    /**
     *   现金退款金额
     */
    private String cash_refund_fee;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
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

    public String getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(String coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
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

    public String getCoupon_refund_count() {
        return coupon_refund_count;
    }

    public void setCoupon_refund_count(String coupon_refund_count) {
        this.coupon_refund_count = coupon_refund_count;
    }

    public String getCash_refund_fee() {
        return cash_refund_fee;
    }

    public void setCash_refund_fee(String cash_refund_fee) {
        this.cash_refund_fee = cash_refund_fee;
    }

    @Override
    public String toString() {
        return "OrderRefundRespVo{" +
                "transaction_id='" + transaction_id + '\'' +
                ", out_refund_no='" + out_refund_no + '\'' +
                ", refund_id='" + refund_id + '\'' +
                ", cash_fee='" + cash_fee + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", coupon_refund_fee='" + coupon_refund_fee + '\'' +
                ", refund_fee='" + refund_fee + '\'' +
                ", total_fee='" + total_fee + '\'' +
                ", coupon_refund_count='" + coupon_refund_count + '\'' +
                ", cash_refund_fee='" + cash_refund_fee + '\'' +
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
