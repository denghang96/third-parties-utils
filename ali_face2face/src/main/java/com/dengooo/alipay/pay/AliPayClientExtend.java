package com.dengooo.alipay.pay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.dengooo.alipay.config.AliPayConfig;

public class AliPayClientExtend extends DefaultAlipayClient {

    public AliPayClientExtend(String serverUrl, String appId, String privateKey) {
        super(serverUrl, appId, privateKey);
    }

    public AliPayClientExtend(String serverUrl, String appId, String privateKey, String format) {
        super(serverUrl, appId, privateKey, format);
    }

    public AliPayClientExtend(String serverUrl, String appId, String privateKey, String format, String charset) {
        super(serverUrl, appId, privateKey, format, charset);
    }

    public AliPayClientExtend(String serverUrl, String appId, String privateKey, String format, String charset, String alipayPublicKey) {
        super(serverUrl, appId, privateKey, format, charset, alipayPublicKey);
    }

    public AliPayClientExtend(String serverUrl, String appId, String privateKey, String format, String charset, String alipayPublicKey, String signType) {
        super(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
    }

    public AliPayClientExtend(String serverUrl, String appId, String privateKey, String format, String charset, String alipayPublicKey, String signType, String proxyHost, int proxyPort) {
        super(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType, proxyHost, proxyPort);
    }

    public AliPayClientExtend(String serverUrl, String appId, String privateKey, String format, String charset, String alipayPublicKey, String signType, String encryptKey, String encryptType) {
        super(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType, encryptKey, encryptType);
    }

    public static AlipayClient getInstance(AliPayConfig aliPayConfig) {
        AlipayClient alipayClient
                = new DefaultAlipayClient(aliPayConfig.getGateWay()
                ,aliPayConfig.getAppId()
                ,aliPayConfig.getAppPrivateKey()
                ,"json"
                ,aliPayConfig.getCharset()
                ,aliPayConfig.getAliPayPublicKey()
                ,aliPayConfig.getSignType());
        return alipayClient;
    }
}
