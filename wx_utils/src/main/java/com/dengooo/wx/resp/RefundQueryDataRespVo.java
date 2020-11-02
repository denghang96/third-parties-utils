package com.dengooo.wx.resp;

public class RefundQueryDataRespVo {
    /**
     *退款状态：
     * SUCCESS—退款成功
     * REFUNDCLOSE—退款关闭。
     * PROCESSING—退款处理中
     * CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。
     */
    private String refund_status;
    /**
     * 退款资金来源
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款/基本账户
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款
     */
    private String refund_account;
    /**
     * 退款总金额,单位为分,可以做部分退款
     */
    private String refund_fee;
    /**
     *退款入账账户
     * 取当前退款单的退款入账方
     * 1）退回银行卡：
     * {银行名称}{卡类型}{卡尾号}
     * 2）退回支付用户零钱:
     * 支付用户零钱
     * 3）退还商户:
     * 商户基本账户
     * 商户结算银行账户
     * 4）退回支付用户零钱通:
     * 支付用户零钱通
     */
    private String refund_recv_accout;
    /**
     *微信退款单号
     */
    private String refund_id;
    /**
     * 退款成功时间
     */
    private String refund_success_time;
    /**
     * 商户系统退款编号
     */
    private String out_refund_no;
    /**
     * 退款渠道
     * ORIGINAL—原路退款
     * BALANCE—退回到余额
     * OTHER_BALANCE—原账户异常退到其他余额账户
     * OTHER_BANKCARD—原银行卡异常退到其他银行卡
     */
    private String refund_channel;

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public String getRefund_account() {
        return refund_account;
    }

    public void setRefund_account(String refund_account) {
        this.refund_account = refund_account;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getRefund_recv_accout() {
        return refund_recv_accout;
    }

    public void setRefund_recv_accout(String refund_recv_accout) {
        this.refund_recv_accout = refund_recv_accout;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_success_time() {
        return refund_success_time;
    }

    public void setRefund_success_time(String refund_success_time) {
        this.refund_success_time = refund_success_time;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }

    @Override
    public String toString() {
        return "RefundQueryDataRespVo{" +
                "refund_status='" + refund_status + '\'' +
                ", refund_account='" + refund_account + '\'' +
                ", refund_fee='" + refund_fee + '\'' +
                ", refund_recv_accout='" + refund_recv_accout + '\'' +
                ", refund_id='" + refund_id + '\'' +
                ", refund_success_time='" + refund_success_time + '\'' +
                ", out_refund_no='" + out_refund_no + '\'' +
                ", refund_channel='" + refund_channel + '\'' +
                '}';
    }
}
