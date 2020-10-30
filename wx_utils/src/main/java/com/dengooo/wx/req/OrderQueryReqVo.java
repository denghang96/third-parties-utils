package com.dengooo.wx.req;

public class OrderQueryReqVo extends PayReqBaseVo{
    /**
     *   微信订单号,微信的订单号，建议优先使用
     */
    private String transaction_id;
    /**
     *  商户订单号.商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    private String out_trade_no;

    public OrderQueryReqVo(String transaction_id, String out_trade_no) {
        this.transaction_id = transaction_id;
        this.out_trade_no = out_trade_no;
    }
}
