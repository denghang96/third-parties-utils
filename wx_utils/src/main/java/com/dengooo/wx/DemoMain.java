package com.dengooo.wx;

import com.dengooo.wx.config.WxPayInitConfig;
import com.dengooo.wx.consts.PayType;
import com.dengooo.wx.pay.WxPayUtils;
import com.dengooo.wx.req.*;
import com.dengooo.wx.resp.*;

public class DemoMain {
    public static void main(String[] args) {
        WxPayInitConfig wxPayInitConfig = new WxPayInitConfig();
        //初始化WxPayInitConfig
//        WxPayInitConfig.setXXX();
        WxPayUtils wxPayUtils = WxPayUtils.build(wxPayInitConfig);
//        //NATIVE支付预下单
//        UnifiedOrderReqVo unifiedOrderReqVo = new UnifiedOrderReqVo("第一个商品", "202011021340", "2", PayType.NATIVE.PAYTYPE, "1");
//        wxPayUtils.unifiedorder(unifiedOrderReqVo);
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
    }
}
