package com.dengooo.wx.mp;

import com.alibaba.fastjson.JSON;
import com.dengooo.wx.resp.AccessToken;
import com.dengooo.wx.resp.JsApiTicket;
import com.dengooo.wx.resp.MpInitParam;
import com.dengooo.wx.resp.OpenIdVo;
import com.dengooo.wx.utils.HttpUtils;
import com.dengooo.wx.utils.WXPayUtil;
import org.apache.commons.lang3.StringUtils;
import org.weixin4j.util.SHA1;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 公众号相关
 */
public abstract class WxMpUser {

    // 授权类型
    private final String GRANT_TYPE = "client_credential";
    private final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    private final String GET_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
    private final String GET_OPEN_ID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。
     * 开发者需要进行妥善保存。
     * access_token的存储至少要保留512个字符空间。
     * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。
     * 参数URL为公众号支付的页面，如当前支付页面是www.dengooo.com/pay.html,这里url就传www.dengooo.com/pay.html
     * 需要在微信支付商户平台配置支付授权目录。详情见
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_3
     * @param appId 公众号的appid
     * @param appSecret 公众号的appSecret
     * @return accessToken 返回得到accessToken字符串，请将此字符串用setAccessTokenFromMemory方法缓存7200S
     */
    public AccessToken getAccessToken(String appId, String appSecret) {
        return this.getAccessToken(appId, appSecret,false);
    }

    /**
     *
     * @param appId
     * @param appSecret
     * @param autoSetAccessTokenIntoMemory 是否在获取accessToken之后调用子类的setAccessTokenIntoMemory方法将accessToken缓存
     * @return
     */
    public AccessToken getAccessToken(String appId, String appSecret, boolean autoSetAccessTokenIntoMemory) {
        AccessToken accessToken = getAccessTokenFromMemory();
        if (accessToken == null || StringUtils.isEmpty(accessToken.getAccess_token())) {
            Map<String, String> params = new HashMap<>();
            params.put("grant_type", GRANT_TYPE);
            params.put("appid", appId);
            params.put("secret", appSecret);
            String accessTokenVoStr = HttpUtils.onlyGet(TOKEN_URL, params);
            accessToken = JSON.parseObject(accessTokenVoStr, AccessToken.class);
            if (autoSetAccessTokenIntoMemory) {
                setAccessTokenIntoMemory(accessToken);
            }
        }
        return accessToken;
    }

    /**
     * 通过access_token换取JsApiTicket，JsApiTicket是公众号中初始化配置所必须的参数
     * @param access_token
     * @return
     */
    public JsApiTicket getJsApiTicket(String access_token) {
        return this.getJsApiTicket(access_token, false);
    }
    /**
     *
     * @param access_token
     * @param autoSetJsApiTicketIntoMemory 是否需要在向微信获取JsApiTicket之后调用setJsApiTicketIntoMemory方法
     * @return
     */
    public JsApiTicket getJsApiTicket(String access_token, boolean autoSetJsApiTicketIntoMemory) {
        // accessToken换取jsapiTicket
        JsApiTicket jsApiTicket = getJsApiTicketFromMemory();
        // ticket is Empty 从新获取
        if (jsApiTicket == null || StringUtils.isEmpty(jsApiTicket.getTicket())) {
            Map<String, String> params = new HashMap<>();
            params.put("access_token", access_token);
            params.put("type", "jsapi");
            String jsApiTicketStr = HttpUtils.onlyGet(GET_TICKET_URL, params);
            jsApiTicket = JSON.parseObject(jsApiTicketStr, JsApiTicket.class);
            if (autoSetJsApiTicketIntoMemory) {
                setJsApiTicketIntoMemory(jsApiTicket);
            }
        }
        return jsApiTicket;
    }

    /**
     * 获取微信公众号工具初始化的参数，只有拿到了这些参数以后，才能使用微信公众号的API。
     * 到了授权页面前端人员才能拿到微信返回的5分钟有效的Code
     * @param ticket
     * @param appId
     * @return
     */
    public MpInitParam getMpToolInitParams(String ticket, String appId) {
        // 使用jsapiTicket签名用于初始化配置
        String nonceStr = WXPayUtil.generateNonceStr();
        // 获时间戳
        String timestamp = System.currentTimeMillis() / 1000 + "";
        // 参数
        Map<String, String> packageParams = new HashMap<>();
        packageParams.put("noncestr", nonceStr);
        packageParams.put("jsapi_ticket", ticket);
        packageParams.put("timestamp", timestamp);
        // 获得拼接好的参数,按照ASCLL大小排序
        String createLinkString = WXPayUtil.createLinkString(packageParams);
        //SHA1签名,该类继承了weixin4J的WeixinSupport类, 使用的是父类的方法
        String signature = SHA1.encode(createLinkString);
        // 参数封装,返回前台
        MpInitParam mpInitParam = new MpInitParam();
        mpInitParam.setAppId(appId);
        mpInitParam.setNonceStr(nonceStr);
        mpInitParam.setSignature(signature);
        mpInitParam.setTimestamp(timestamp);
        return mpInitParam;
    }

    /**
     * 不想一步一步调用，直接使用本方法获取初始化微信公众号JSSDK初始化参数
     * @param appId
     * @param appSecret
     * @param autoSetAccessTokenIntoMemory
     * @param autoSetJsApiTicketIntoMemory
     * @return
     */
    public MpInitParam easyGetMpToolInitParams(String appId, String appSecret , boolean autoSetAccessTokenIntoMemory, boolean autoSetJsApiTicketIntoMemory) {
        AccessToken accessToken = this.getAccessToken(appId, appSecret, autoSetAccessTokenIntoMemory);
        JsApiTicket jsApiTicket = this.getJsApiTicket(accessToken.getAccess_token(),autoSetJsApiTicketIntoMemory);
        return this.getMpToolInitParams(jsApiTicket.getTicket(), appId);
    }
    /**
     * 获取openid
     * @param appId
     * @param appSecret
     * @param code 通过用户授权获取到的code 5分钟有效
     * @return
     */
    public OpenIdVo getOpenId(String appId, String appSecret, String code) {
        Map<String, String> params = new HashMap<>();
        params.put("appid",appId);
        params.put("secret",appSecret);
        params.put("code",code);
        params.put("grant_type","authorization_code");
        String respStr = HttpUtils.onlyGet(GET_OPEN_ID_URL, params);
        OpenIdVo openIdVo = JSON.parseObject(respStr, OpenIdVo.class);
        return openIdVo;
    }

    /**
     * 从缓存中获取AccessToken
     * @return
     */
    protected abstract AccessToken getAccessTokenFromMemory();
    /**
     * 向缓存中保存AccessToken
     * @return
     */
    protected abstract void setAccessTokenIntoMemory(AccessToken accessToken);

    /**
     * 从缓存中获取JsApiTicket
     * @return
     */
    protected abstract JsApiTicket getJsApiTicketFromMemory();

    /**
     * 向缓存中保存JsApiTicket
     * @return
     */
    protected abstract void setJsApiTicketIntoMemory(JsApiTicket jsApiTicket);



}
