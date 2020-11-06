package com.dengooo.wx.resp;

import com.alibaba.fastjson.JSON;

public class MicroPayRespVo extends PayRespBaseVo{
    /**
     * 用户在商户appid 下的唯一标识
     */
    private String openid;
    /**
     * 用户是否关注公众账号，仅在公众账号类型支付有效，取值范围：Y或N;Y-关注;N-未关注
     */
    private String is_subscribe;
    /**
     * 交易类型 MICROPAY 付款码支付
     */
    private String trade_type;
    /**
     * 银行类型，采用字符串类型的银行标识
     */
    private String bank_type;
    /**
     * 订单总金额，单位为分，只能为整数
     */
    private String total_fee;
    /**
     * 订单现金支付金额
     */
    private String cash_fee;
    /**
     * 微信支付订单号
     */
    private String transaction_id;
    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。
     */
    private String out_trade_no;
    /**
     * 支付完成时间
     */
    private String time_end;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
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

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
