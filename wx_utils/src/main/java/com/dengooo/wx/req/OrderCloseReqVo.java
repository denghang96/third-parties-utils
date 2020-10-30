package com.dengooo.wx.req;

public class OrderCloseReqVo extends PayReqBaseVo {
    /**
     *   商户订单号
     */
    private String out_trade_no;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public OrderCloseReqVo(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
