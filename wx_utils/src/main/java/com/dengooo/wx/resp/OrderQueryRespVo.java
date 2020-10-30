package com.dengooo.wx.resp;

public class OrderQueryRespVo extends PayRespBaseVo{
    /**
     *  微信支付订单号
     */
    private String transaction_id;
    /**
     *
     *   交易状态
     *   SUCCESS—支付成功
     *   REFUND—转入退款
     *   NOTPAY—未支付
     *   CLOSED—已关闭
     *   REVOKED—已撤销（付款码支付）
     *   USERPAYING--用户支付中（付款码支付）
     *   PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    private String trade_state;
    /**
     *   付款银行
     */
    private String bank_type;
    /**
     *   用户标识
     */
    private String openid;
    /**
     *   标价币种
     */
    private String fee_type;
    /**
     *   现金支付金额
     */
    private String cash_fee;
    /**
     *   商户订单号
     */
    private String out_trade_no;
    /**
     *  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    private String cash_fee_type;
    /**
     *  订单总金额，单位为分
     */
    private String total_fee;
    /**
     *  交易状态描述
     */
    private String trade_state_desc;
    /**
     *   调用接口提交的交易类型，JSAPI，NATIVE，APP，MICROPAY。。。
     */
    private String trade_type;
    /**
     *  附加数据
     */
    private String attach;
    /**
     *   支付完成时间
     */
    private String time_end;
    /**
     *  是否关注公众账号 用户是否关注公众账号，Y-关注，N-未关注
     */
    private String is_subscribe;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
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

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    @Override
    public String toString() {
        return "OrderQueryRespVo{" +
                "transaction_id='" + transaction_id + '\'' +
                ", trade_state='" + trade_state + '\'' +
                ", bank_type='" + bank_type + '\'' +
                ", openid='" + openid + '\'' +
                ", fee_type='" + fee_type + '\'' +
                ", cash_fee='" + cash_fee + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", cash_fee_type='" + cash_fee_type + '\'' +
                ", total_fee='" + total_fee + '\'' +
                ", trade_state_desc='" + trade_state_desc + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", attach='" + attach + '\'' +
                ", time_end='" + time_end + '\'' +
                ", is_subscribe='" + is_subscribe + '\'' +
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
