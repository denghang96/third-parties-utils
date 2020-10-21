package com.dengooo.alipay.pay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.dengooo.alipay.config.AliPayConfig;

/*
    扫码付
 */
public class AliScanPayUtils {

    private AliPayConfig aliPayConfig;

    private AliScanPayUtils(AliPayConfig aliPayConfig) {
        this.aliPayConfig = aliPayConfig;
    }

    public static AliScanPayUtils build(AliPayConfig aliPayConfig) {
        return new AliScanPayUtils(aliPayConfig);
    }
    /*
        预下单
     */
    public AlipayTradePrecreateResponse prePay(PrecreateReqParam precreateReqParam){
        AlipayClient alipayClient
                = new DefaultAlipayClient(aliPayConfig.getGateWay()
                ,aliPayConfig.getAppId()
                ,aliPayConfig.getAppPrivateKey()
                ,"json"
                ,aliPayConfig.getCharset()
                ,aliPayConfig.getAliPayPublicKey()
                ,aliPayConfig.getSignType());
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizContent(precreateReqParam.toString());
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        AlipayTradePrecreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }

}
