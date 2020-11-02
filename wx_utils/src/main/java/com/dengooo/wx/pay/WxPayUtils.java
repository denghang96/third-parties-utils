package com.dengooo.wx.pay;

import com.dengooo.wx.config.WxPayInitConfig;
import com.dengooo.wx.req.*;
import com.dengooo.wx.resp.*;
import com.dengooo.wx.sdk.WXPayConstants;
import com.dengooo.wx.utils.WXPayUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.dengooo.wx.sdk.WXPayConstants.USER_AGENT;

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
     * 预下单/统一下单
     */
    public UnifiedOrderRespVo unifiedorder(UnifiedOrderReqVo unifiedOrderReqVo) {
        if (StringUtils.isEmpty(unifiedOrderReqVo.getNotify_url())) {
            unifiedOrderReqVo.setNotify_url(wxPayInitConfig.getNotifyUrl());
        }
        String url = HTTPS_PREFIX + WXPayConstants.DOMAIN_API + WXPayConstants.UNIFIEDORDER_URL_SUFFIX;
        return postAndGetRespVo(url, unifiedOrderReqVo, UnifiedOrderRespVo.class);
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
            respStr = this.sendPostXmlWithCert(url,mapWithSign, 6000, 8000,true);
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
            respStr = this.sendPostXml(url,mapWithSign);
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
        final String respStr = sendPostXml(url, mapWithSign);
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

    /**
     * 发送请求
     */
    private String sendPostXml(String url, String mapWithSign) {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(mapWithSign, "utf-8");
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

    /**
     *
     * @param url
     * @param data
     * @param connectTimeoutMs
     * @param readTimeoutMs
     * @param useCert
     * @return
     * @throws Exception
     */
    private String sendPostXmlWithCert(final String url, String data, int connectTimeoutMs, int readTimeoutMs, boolean useCert) throws Exception {
        BasicHttpClientConnectionManager connManager;
        if (useCert) {
            // 证书
            char[] password = wxPayInitConfig.getMchId().toCharArray();
            InputStream certStream = new FileInputStream(wxPayInitConfig.getCertFilePath());
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(certStream, password);

            // 实例化密钥库 & 初始化密钥工厂
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, password);

            // 创建 SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    new String[]{"TLSv1"},
                    null,
                    new DefaultHostnameVerifier());

            connManager = new BasicHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", sslConnectionSocketFactory)
                            .build(),
                    null,
                    null,
                    null
            );
        }
        else {
            connManager = new BasicHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", SSLConnectionSocketFactory.getSocketFactory())
                            .build(),
                    null,
                    null,
                    null
            );
        }

        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();

        HttpPost httpPost = new HttpPost(url);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeoutMs).setConnectTimeout(connectTimeoutMs).build();
        httpPost.setConfig(requestConfig);

        StringEntity postEntity = new StringEntity(data, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("User-Agent", USER_AGENT + " " + wxPayInitConfig.getMchId());
        httpPost.setEntity(postEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "UTF-8");
    }
}
