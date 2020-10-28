package com.dengooo.wx.config;

public class WxPayInitConfig {
    /*
        appid是微信公众账号或开放平台APP的唯一标识，
        在公众平台申请公众账号或者在开放平台申请APP账号后，
        微信会自动分配对应的appid，用于标识该应用。
        可在微信公众平台-->开发-->基本配置里面查看，商户的微信支付审核通过邮件中也会包含该字段值。
        API参数名 appid
     */
    private String appId = "";
    /*
        微信支付商户号.商户申请微信支付后，由微信支付分配的商户收款账号。
        API参数名 mch_id
     */
    private String mchId = "";
    /*
        API密钥。交易过程生成签名的密钥，仅保留在商户系统和微信支付后台，不会在网络中传播。
        商户妥善保管该Key，切勿在网络中传输，不能在其他客户端中存储，保证key不会被泄漏。
        商户可根据邮件提示登录微信商户平台进行设置。
        也可按以下路径设置：微信商户平台(pay.weixin.qq.com)-->账户中心-->账户设置-->API安全-->密钥设置
        API参数名 key
     */
    private String key = "";
    /*
        AppSecret是APPID对应的接口密码，用于获取接口调用凭证access_token时使用。
        在微信支付中，先通过OAuth2.0接口获取用户openid，
        此openid用于微信内网页支付模式下单接口使用。
        可登录公众平台-->微信支付，获取AppSecret（需成为开发者且帐号没有异常状态）。
        如果要在公众号中或者小程序中完成支付，则一定需要配置此秘钥
        API参数名 secret
     */
    private String appsecret = "";
    /*
        支付成功之后微信的回调地址
     */
    private String notifyUrl = "";
    /*
        证书路径，退款时需要用到
     */
    private String certFilePath = "";
}
