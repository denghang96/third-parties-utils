package com.dengooo.wx.resp;

public class PayRespBaseVo{
    /**
     *   随机字符串
     */
    protected String nonce_str;
    /**
     *  appid
     */
    protected String appid;
    /**
     *   签名
     */
    protected String sign;
    /**
     *   商户号
     */
    protected String mch_id;
    /**
     *  响应代码
     */
    protected String return_code;
    /**
     *   响应代码对应中文释义
     */
    protected String return_msg;
    /**
     *   业务代码
     */
    protected String result_code;
    /**
     *   错误信息描述
     */
    protected String err_code_des;
    /**
     *   错误代码
     */
    protected String err_code;

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }
}
