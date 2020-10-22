package com.dengooo.alipay.pay;

import com.alibaba.fastjson.JSON;

/**
 * 用户被扫支付参数
 */
public class AliPayPayParam {
    /*
        商户订单号。64 个字符以内的大小，可包含字母、数字、下划线。需保证该参数在商户端不重复
     */
    private String out_trade_no;
    /*
        支付场景。 条码支付，取值：bar_code； 声波支付，取值：wave_code
     */
    private String scene = "bar_code";
    /*
        扫描用户出示的自己的付款二维码或者条形码获取到的数字
        支付授权码。25~30开头的长度为16~24位的数字，实际字符串长度以开发者获取的付款码长度为准
     */
    private String auth_code;
    /*
        订单标题
     */
    private String subject;
    /*
        订单总金额
     */
    private String total_amount;
    /*
        商品的编号
     */
    private String goods_id;
    /*
        商品名称
     */
    private String goods_name;
    /*
        商品数量
     */
    private String quantity;
    /*
        商品单价
     */
    private String price;

    public AliPayPayParam(String out_trade_no, String auth_code, String subject, String total_amount) {
        this.out_trade_no = out_trade_no;
        this.auth_code = auth_code;
        this.subject = subject;
        this.total_amount = total_amount;
    }

    public AliPayPayParam(String out_trade_no, String scene, String auth_code, String subject, String total_amount) {
        this.out_trade_no = out_trade_no;
        this.scene = scene;
        this.auth_code = auth_code;
        this.subject = subject;
        this.total_amount = total_amount;
    }

    public AliPayPayParam(String out_trade_no, String scene, String auth_code, String subject, String total_amount, String goods_id, String goods_name, String quantity, String price) {
        this.out_trade_no = out_trade_no;
        this.scene = scene;
        this.auth_code = auth_code;
        this.subject = subject;
        this.total_amount = total_amount;
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
