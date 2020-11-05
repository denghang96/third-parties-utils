package com.dengooo.wx;

import com.dengooo.wx.config.WxPayInitConfig;
import com.dengooo.wx.consts.PayType;
import com.dengooo.wx.mp.Test;
import com.dengooo.wx.pay.WxPayUtils;
import com.dengooo.wx.req.*;
import com.dengooo.wx.resp.*;

import java.util.Map;

public class DemoMain {
    public static void main(String[] args) {
        WxPayInitConfig wxPayInitConfig = new WxPayInitConfig();
        //初始化WxPayInitConfig WxPayInitConfig.setXXX();
        WxPayUtils wxPayUtils = WxPayUtils.build(wxPayInitConfig);
        //NATIVE支付预下单
//        UnifiedOrderReqVo unifiedOrderReqVo = new UnifiedOrderReqVo("第一个商品", "202011021340", "2", PayType.NATIVE.PAYTYPE, "1");
//        wxPayUtils.unifiedorder(unifiedOrderReqVo);
        //JSAPI支付预下单 得到一个map,map最终有nonceStr、package、signType、paySign、timestamp五个Key,这个map给到前端人员，前端人员调用js的Api直接拉起微信支付
//        UnifiedOrderReqVo unifiedOrderReqVo = new UnifiedOrderReqVo("第一个商品", "202011021341", "2", "http://www.dengooo.icu/paysuccess",PayType.JSAPI.PAYTYPE, "1","");
//        Map<String, Object> putUpWxPayParamMap = wxPayUtils.JsApiUnifiedorder(unifiedOrderReqVo);
//        System.out.println(putUpWxPayParamMap.toString());
        //查询订单
//        OrderQueryReqVo orderQueryReqVo = new OrderQueryReqVo("", "202010291606");
//        OrderQueryRespVo orderQueryRespVo = wxPayUtils.orderQuery(orderQueryReqVo);
//        //关闭订单
//        OrderCloseReqVo orderCloseReqVo = new OrderCloseReqVo("202011021338");
//        OrderCloseRespVo orderCloseRespVo = wxPayUtils.orderClose(orderCloseReqVo);
////        退款
//        OrderRefundReqVo orderRefundReqVo
//                = new OrderRefundReqVo("","202011021340","4","2","2");
//        OrderRefundRespVo orderRefundRespVo = wxPayUtils.refund(orderRefundReqVo);
//        //退款查询
//                OrderRefundQueryReqVo orderRefundQueryReqVo
//                = new OrderRefundQueryReqVo("","202011021340","","");
//        OrderRefundQueryRespVo orderRefundQueryRespVo = wxPayUtils.refundQuery(orderRefundQueryReqVo);

        Test test = new Test();
        //获取系统层面的AccessToken
//        AccessToken accessToken = test.getAccessToken("", "");
//        System.out.println(accessToken.toString());
        //通过AccessToken换取JsApiTicket
//        JsApiTicket jsApiTicket = test.getJsApiTicket(accessToken.getAccess_token());
//        System.out.println(jsApiTicket.toString());
        //通过JsApiTicket获取微信公众号初始化所需要的参数
//        MpInitParam mpInitParam = test.getMpToolInitParams(jsApiTicket.getTicket(), "");
//        System.out.println(mpInitParam.toString());

        OpenIdVo openIdVo = test.getOpenId("", "", "");
        System.out.println(openIdVo.toString());
    }
}
