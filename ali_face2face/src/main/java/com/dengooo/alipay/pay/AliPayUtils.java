package com.dengooo.alipay.pay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.dengooo.alipay.config.AliPayConfig;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


/*
    扫码付
 */
public class AliPayUtils {

    final Logger logger = Logger.getLogger("AliPayUtils");

    private AliPayConfig aliPayConfig;

    private AliPayUtils(AliPayConfig aliPayConfig) {
        this.aliPayConfig = aliPayConfig;
    }

    public static AliPayUtils build(AliPayConfig aliPayConfig) {
        return new AliPayUtils(aliPayConfig);
    }
    /*
        预下单，返回支付二维码
     */
    public AlipayTradePrecreateResponse prePay(AliPayPrecreateReqParam alipayPrecreateReqParam){
        AlipayClient alipayClient = AliPayClientExtend.getInstance(aliPayConfig);
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizContent(alipayPrecreateReqParam.toString());
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        AlipayTradePrecreateResponse response = null;
        try {
            response = alipayClient.execute(request);
            logger.log(Level.INFO, JSON.toJSONString(response));
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }
    /*
        查询支付状态
     */
    public AlipayTradeQueryResponse query(AliPayQueryParam aliPayQueryParam){
        AlipayClient alipayClient = AliPayClientExtend.getInstance(aliPayConfig);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent(aliPayQueryParam.toString());
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
            logger.log(Level.INFO, JSON.toJSONString(response));
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }
    /*
        撤销订单
     */
    public AlipayTradeCancelResponse cancel(AliPayCancelParam aliPayCancelParam){
        AlipayClient alipayClient = AliPayClientExtend.getInstance(aliPayConfig);
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest ();
        request.setBizContent(aliPayCancelParam.toString());
        AlipayTradeCancelResponse response = null;
        try {
            response = alipayClient.execute(request);
            logger.log(Level.INFO, JSON.toJSONString(response));
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }

    /*
        主动关闭订单，用于交易创建后，用户在一定时间内未进行支付，可调用该接口直接将未付款的交易进行关闭。
        也可以在预下单时传入一个时间参数，到时间后会自动关闭订单
     */
    public AlipayTradeCloseResponse close(AliPayCloseParam aliPayCloseParam){
        AlipayClient alipayClient = AliPayClientExtend.getInstance(aliPayConfig);
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest ();
        request.setBizContent(aliPayCloseParam.toString());
        AlipayTradeCloseResponse response = null;
        try {
            response = alipayClient.execute(request);
            logger.log(Level.INFO, JSON.toJSONString(response));
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }

    /*
        退款
     */
    public AlipayTradeRefundResponse refund(AliPayRefundParam aliPayRefundParam){
        AlipayClient alipayClient = AliPayClientExtend.getInstance(aliPayConfig);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent(aliPayRefundParam.toString());
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
            logger.log(Level.INFO, JSON.toJSONString(response));
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }

    /*
        退款查询
     */
    public AlipayTradeFastpayRefundQueryResponse refundQuery(AliPayRefundQueryParam aliPayRefundQueryParam){
        AlipayClient alipayClient = AliPayClientExtend.getInstance(aliPayConfig);
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizContent(aliPayRefundQueryParam.toString());
        AlipayTradeFastpayRefundQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
            logger.log(Level.INFO, JSON.toJSONString(response));
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }

    /*
        扫描用户支付二维码发起支付
     */
    public AlipayTradePayResponse pay(AliPayPayParam aliPayPayParam){
        AlipayClient alipayClient = AliPayClientExtend.getInstance(aliPayConfig);
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizContent(aliPayPayParam.toString());
        AlipayTradePayResponse response = null;
        try {
            response = alipayClient.execute(request);
            logger.log(Level.INFO, JSON.toJSONString(response));
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }
    /*
        预下单，一个支付宝支付的页面，用户扫码支付完成之后，回重定向到return_url
     */
    public AlipayTradePagePayResponse prePagePay(AlipayTradePagePayReqParam alipayTradePagePayReqParam){
        AlipayClient alipayClient = AliPayClientExtend.getInstance(aliPayConfig);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setBizContent(alipayTradePagePayReqParam.toString());
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        AlipayTradePagePayResponse response = null;
        try {
            response = alipayClient.pageExecute(request);
            logger.log(Level.INFO, JSON.toJSONString(response));
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }
}
