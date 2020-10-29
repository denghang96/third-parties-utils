package com.dengooo.wx.pay;

import com.dengooo.wx.config.WxPayInitConfig;
import com.dengooo.wx.req.PayReqBaseVo;
import com.dengooo.wx.req.UnifiedOrderReqVo;
import com.dengooo.wx.resp.UnifiedOrderRespVo;
import com.dengooo.wx.utils.WXPayConstants;
import com.dengooo.wx.utils.WXPayUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.InvalidParameterException;
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
    /*
        预下单/统一下单
     */
    public UnifiedOrderRespVo unifiedorder(UnifiedOrderReqVo unifiedOrderReqVo){

        if (StringUtils.isEmpty(unifiedOrderReqVo.getNotify_url())) {
            unifiedOrderReqVo.setNotify_url(wxPayInitConfig.getNotifyUrl());
        }

        assignmentParams(unifiedOrderReqVo);

        Map<String, String> strMap = WXPayUtil.obj2Map(unifiedOrderReqVo);
        String mapWithSign = toWithSignXmlStr(strMap);

        String url = HTTPS_PREFIX + WXPayConstants.DOMAIN_API + WXPayConstants.UNIFIEDORDER_URL_SUFFIX;
        final String responseStr = sendPostXml(url, mapWithSign);

        UnifiedOrderRespVo unifiedOrderRespVo = new UnifiedOrderRespVo();
        try {
            Map<String, String> respStrMap = WXPayUtil.xmlToMap(responseStr);
            unifiedOrderRespVo = WXPayUtil.map2Obj(respStrMap, UnifiedOrderRespVo.class);
            logger.info("unifiedorder resp : {}", unifiedOrderRespVo.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return unifiedOrderRespVo;
    }
    /*
        完善请求对象的参数，用来将其转成XML
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

    /*
        将strMap转换成带有签名的XML字符串
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
    /*
        发送请求
     */
    private String sendPostXml(String url, String mapWithSign) {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity (mapWithSign, "utf-8");
            httpPost.setHeader("Content-Type", "application/xml");
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity responseEntity = response.getEntity();
            String result = EntityUtils.toString(responseEntity, "UTF-8");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
