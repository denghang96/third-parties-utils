package com.dengooo.wx.resp;

public class OrderCloseRespVo extends PayRespBaseVo{
    /**
     *   对业务结果的补充说明
     */
    private String result_msg;

    public OrderCloseRespVo() {
    }

    public String getResult_msg() {
        return this.result_msg;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
    }

    @Override
    public String toString() {
        return "OrderCloseRespVo{" +
                "result_msg='" + result_msg + '\'' +
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
