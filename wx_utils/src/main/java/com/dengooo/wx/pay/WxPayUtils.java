package com.dengooo.wx.pay;

import com.dengooo.wx.config.WxPayInitConfig;
import com.dengooo.wx.req.*;
import com.dengooo.wx.resp.*;
import com.dengooo.wx.sdk.WXPayConstants;
import com.dengooo.wx.utils.HttpUtils;
import com.dengooo.wx.utils.WXPayUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
    微信支付
 */
public class WxPayUtils {

    private static final String HTTPS_PREFIX = "https://";

    final Logger logger = LoggerFactory.getLogger(WxPayUtils.class);

    private WxPayInitConfig wxPayInitConfig;

    private WxPayUtils(WxPayInitConfig wxPayInitConfig) {
        this.wxPayInitConfig = wxPayInitConfig;
    }

    public static WxPayUtils build(WxPayInitConfig wxPayInitConfig) {
        return new WxPayUtils(wxPayInitConfig);
    }

    /**
     * NATIVE支付预下单/统一下单
     */
    public UnifiedOrderRespVo unifiedorder(UnifiedOrderReqVo unifiedOrderReqVo) {
        if (StringUtils.isEmpty(unifiedOrderReqVo.getNotify_url())) {
            unifiedOrderReqVo.setNotify_url(wxPayInitConfig.getNotifyUrl());
        }
        String url = HTTPS_PREFIX + WXPayConstants.DOMAIN_API + WXPayConstants.UNIFIEDORDER_URL_SUFFIX;
        return postAndGetRespVo(url, unifiedOrderReqVo, UnifiedOrderRespVo.class);
    }

    /**
     * 付款码支付
     * @param microPayReqVo
     * @return
     */
    public MicroPayRespVo micropay(MicroPayReqVo microPayReqVo) {
        String url = HTTPS_PREFIX + WXPayConstants.DOMAIN_API + WXPayConstants.MICROPAY_URL_SUFFIX;
        return postAndGetRespVo(url, microPayReqVo, MicroPayRespVo.class);
    }

    /**
     * 撤销订单
     * @param reverseReqVo
     * @return
     */
    public ReverseRespVo reverse(ReverseReqVo reverseReqVo) {
        String url = HTTPS_PREFIX + WXPayConstants.DOMAIN_API + WXPayConstants.REVERSE_URL_SUFFIX;
        assignmentParams(reverseReqVo);
        //将对象转map
        final Map<String, String> strMap = WXPayUtil.obj2Map(reverseReqVo);
        //将map转成带有签名的xml
        final String mapWithSign = toWithSignXmlStr(strMap);
        //发送HTTPS请求，并返回一个XML字符串
        String respStr = null;
        try {
            respStr = HttpUtils.sendPostXmlWithCert(url,mapWithSign, 6000, 8000,true,wxPayInitConfig.getMchId(), wxPayInitConfig.getCertFilePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ReverseRespVo reverseRespVo = null;
        try {

            Map<String, String> respStrMap = WXPayUtil.xmlToMap(respStr);
            WXPayConstants.SignType signType = "MD5".equals(wxPayInitConfig.getSignType())? WXPayConstants.SignType.MD5:WXPayConstants.SignType.HMACSHA256;
            if (WXPayUtil.isSignatureValid(respStrMap, wxPayInitConfig.getKey(), signType)) {
                reverseRespVo = WXPayUtil.map2Obj(respStrMap, ReverseRespVo.class);
                logger.info("refund resp : {}", reverseRespVo.toString());
                return reverseRespVo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSAPI 微信公众号支付预下单
     * @param unifiedOrderReqVo
     * @return
     */
    public Map<String, Object> JsApiUnifiedorder(UnifiedOrderReqVo unifiedOrderReqVo) {
        UnifiedOrderRespVo unifiedOrderRespVo = this.unifiedorder(unifiedOrderReqVo);
        String appId = unifiedOrderRespVo.getAppid();
        String nonceStr = unifiedOrderRespVo.getNonce_str();
        String packageStr = unifiedOrderRespVo.getPrepay_id();
        long timeStamp = System.currentTimeMillis()/1000;
        // 拼接chooseWXPay参数
        String timestamp = "timeStamp";
        Map<String,String> signParam = new HashMap<>();
        signParam.put("appId",appId);
        signParam.put(timestamp, Long.toString(timeStamp));
        signParam.put("nonceStr",nonceStr);
        signParam.put("package","prepay_id=" + packageStr);
        signParam.put("signType", WXPayConstants.SignType.MD5.name());
        String sign = null;
        try {
            sign = WXPayUtil.generateSignature(signParam, wxPayInitConfig.getKey(), WXPayConstants.SignType.MD5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        signParam.put("paySign",sign);
        signParam.remove("appId");
        Map<String, Object> map = new HashMap<>(signParam);
        map.remove(timestamp);
        map.put("timestamp", timeStamp);


        //map最终有nonceStr、package、signType、paySign、timestamp五个Key
        return map;
    }

    /**
     * 查询订单
     */
    public OrderQueryRespVo orderQuery(OrderQueryReqVo orderQueryReqVo) {
        String url = HTTPS_PREFIX + WXPayConstants.DOMAIN_API + WXPayConstants.ORDERQUERY_URL_SUFFIX;
        return postAndGetRespVo(url, orderQueryReqVo, OrderQueryRespVo.class);
    }

    /**
     * 关闭订单
     */
    public OrderCloseRespVo orderClose(OrderCloseReqVo orderCloseReqVo) {
        String url = HTTPS_PREFIX + WXPayConstants.DOMAIN_API + WXPayConstants.CLOSEORDER_URL_SUFFIX;
        return postAndGetRespVo(url, orderCloseReqVo, OrderCloseRespVo.class);
    }

    /**
     * 退款
     */
    public OrderRefundRespVo refund(OrderRefundReqVo orderRefundReqVo) {
        String url = HTTPS_PREFIX + WXPayConstants.DOMAIN_API + WXPayConstants.REFUND_URL_SUFFIX;
        assignmentParams(orderRefundReqVo);
        //将对象转map
        final Map<String, String> strMap = WXPayUtil.obj2Map(orderRefundReqVo);
        //将map转成带有签名的xml
        final String mapWithSign = toWithSignXmlStr(strMap);
        //发送HTTPS请求，并返回一个XML字符串
        String respStr = null;
        try {
            respStr = HttpUtils.sendPostXmlWithCert(url,mapWithSign, 6000, 8000,true,wxPayInitConfig.getMchId(), wxPayInitConfig.getCertFilePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        OrderRefundRespVo orderRefundRespVo = null;
        try {

            Map<String, String> respStrMap = WXPayUtil.xmlToMap(respStr);
            WXPayConstants.SignType signType = "MD5".equals(wxPayInitConfig.getSignType())? WXPayConstants.SignType.MD5:WXPayConstants.SignType.HMACSHA256;
            if (WXPayUtil.isSignatureValid(respStrMap, wxPayInitConfig.getKey(), signType)) {
                orderRefundRespVo = WXPayUtil.map2Obj(respStrMap, OrderRefundRespVo.class);
                logger.info("refund resp : {}", orderRefundRespVo.toString());
                return orderRefundRespVo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 退款结果查询
     * @param orderRefundQueryReqVo
     * @return
     */
    public OrderRefundQueryRespVo refundQuery(OrderRefundQueryReqVo orderRefundQueryReqVo) {
        String url = HTTPS_PREFIX + WXPayConstants.DOMAIN_API + WXPayConstants.REFUNDQUERY_URL_SUFFIX;
        assignmentParams(orderRefundQueryReqVo);
        //将对象转map
        final Map<String, String> strMap = WXPayUtil.obj2Map(orderRefundQueryReqVo);
        //将map转成带有签名的xml
        final String mapWithSign = toWithSignXmlStr(strMap);
        //发送HTTPS请求，并返回一个XML字符串
        String respStr = null;
        try {
            respStr = HttpUtils.sendPostXml(url,mapWithSign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OrderRefundQueryRespVo orderRefundRespVo = null;
        try {
            Map<String, String> respStrMap = WXPayUtil.xmlToMap(respStr);
            WXPayConstants.SignType signType = "MD5".equals(wxPayInitConfig.getSignType())? WXPayConstants.SignType.MD5:WXPayConstants.SignType.HMACSHA256;
            if (WXPayUtil.isSignatureValid(respStrMap, wxPayInitConfig.getKey(), signType)) {
                orderRefundRespVo = WXPayUtil.map2Obj(respStrMap, OrderRefundQueryRespVo.class);
                int refundCount = Integer.parseInt(respStrMap.get("refund_count"));
                List<RefundQueryDataRespVo> refundQueryDataRespVos = new ArrayList<>();
                for (int i = 0; i < refundCount; i++) {
                    RefundQueryDataRespVo refundQueryDataRespVo = new RefundQueryDataRespVo();
                    refundQueryDataRespVo.setOut_refund_no(respStrMap.get("out_refund_no_"+i));
                    refundQueryDataRespVo.setRefund_account(respStrMap.get("refund_account_"+i));
                    refundQueryDataRespVo.setRefund_channel(respStrMap.get("refund_channel_"+i));
                    refundQueryDataRespVo.setRefund_fee(respStrMap.get("refund_fee_"+i));
                    refundQueryDataRespVo.setRefund_id(respStrMap.get("refund_id_"+i));
                    refundQueryDataRespVo.setRefund_recv_accout(respStrMap.get("refund_recv_accout_"+i));
                    refundQueryDataRespVo.setRefund_status(respStrMap.get("refund_status_"+i));
                    refundQueryDataRespVo.setRefund_success_time(respStrMap.get("refund_success_time_"+i));
                    refundQueryDataRespVos.add(refundQueryDataRespVo);
                }
                orderRefundRespVo.setRefundQueryDataRespVos(refundQueryDataRespVos);
                logger.info("refund resp : {}", orderRefundRespVo.toString());
                return orderRefundRespVo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 各个接口调用统一的步骤
     */
    private <T extends PayReqBaseVo, R extends PayRespBaseVo> R postAndGetRespVo(String url, T son, Class<R> clazz) {
        //将请求对象的参数补充完整
        assignmentParams(son);
        //将对象转map
        final Map<String, String> strMap = WXPayUtil.obj2Map(son);
        //将map转成带有签名的xml
        final String mapWithSign = toWithSignXmlStr(strMap);
        //发送HTTPS请求，并返回一个XML字符串
        final String respStr = HttpUtils.sendPostXml(url, mapWithSign);
        logger.info("respStr : {}", respStr);
        R r = null;
        try {
            Map<String, String> respStrMap = WXPayUtil.xmlToMap(respStr);
            WXPayConstants.SignType signType = "MD5".equals(wxPayInitConfig.getSignType())? WXPayConstants.SignType.MD5:WXPayConstants.SignType.HMACSHA256;
            if (WXPayUtil.isSignatureValid(respStrMap, wxPayInitConfig.getKey(), signType)) {
                r = WXPayUtil.map2Obj(respStrMap, clazz);
                logger.info("resp : {}", r.toString());
                return r;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 完善请求对象的参数，用来将其转成XML
     */
    private <T extends PayReqBaseVo> void assignmentParams(T son) {
        son.setAppid(wxPayInitConfig.getAppId());
        son.setMch_id(wxPayInitConfig.getMchId());
        son.setNonce_str(WXPayUtil.generateNonceStr());
        if (StringUtils.isNotEmpty(wxPayInitConfig.getSignType())) {
            son.setSign_type(wxPayInitConfig.getSignType());
        } else {
            son.setSign_type(WXPayConstants.MD5);
        }
    }

    /**
     * 将strMap转换成带有签名的XML字符串
     */
    private <T extends PayReqBaseVo> String toWithSignXmlStr(Map<String, String> strMap) {
        String mapWithSign = "";
        try {
            if (WXPayConstants.MD5.equals(wxPayInitConfig.getSignType())) {
                mapWithSign = WXPayUtil.generateSignedXml(strMap, wxPayInitConfig.getKey(), WXPayConstants.SignType.MD5);
//                throw new InvalidParameterException("签名类型sign_type只能是HMAC-SHA256,区分大小写");
            } else if (WXPayConstants.HMACSHA256.equals(wxPayInitConfig.getSignType())) {
                mapWithSign = WXPayUtil.generateSignedXml(strMap, wxPayInitConfig.getKey(), WXPayConstants.SignType.HMACSHA256);
            } else {
                throw new InvalidParameterException("签名类型sign_type只能是HMAC-SHA256或MD5,区分大小写");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotEmpty(mapWithSign)) {
            return mapWithSign;
        } else {
            throw new RuntimeException("签名发生错误，签名为空");
        }
    }

}
