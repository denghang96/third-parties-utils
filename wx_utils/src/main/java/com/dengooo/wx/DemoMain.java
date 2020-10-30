package com.dengooo.wx;

import com.dengooo.wx.config.WxPayInitConfig;
import com.dengooo.wx.pay.WxPayUtils;
import com.dengooo.wx.req.OrderCloseReqVo;
import com.dengooo.wx.req.OrderQueryReqVo;
import com.dengooo.wx.req.OrderRefundReqVo;
import com.dengooo.wx.req.UnifiedOrderReqVo;
import com.dengooo.wx.resp.OrderCloseRespVo;
import com.dengooo.wx.resp.OrderQueryRespVo;
import com.dengooo.wx.resp.OrderRefundRespVo;
import com.dengooo.wx.resp.UnifiedOrderRespVo;

public class DemoMain {
    public static void main(String[] args) {
        WxPayInitConfig wxPayInitConfig = new WxPayInitConfig();
        WxPayUtils wxPayUtils = WxPayUtils.build(wxPayInitConfig);
//        UnifiedOrderReqVo unifiedOrderReqVo = new UnifiedOrderReqVo("第一个商品", "202010291606", "1", "NATIVE", "1");
//        wxPayUtils.unifiedorder(unifiedOrderReqVo);
//        OrderQueryReqVo orderQueryReqVo = new OrderQueryReqVo("", "202010291606");
//        OrderQueryRespVo orderQueryRespVo = wxPayUtils.orderQuery(orderQueryReqVo);
//        OrderCloseReqVo orderCloseReqVo = new OrderCloseReqVo("202010291606");
//        OrderCloseRespVo orderCloseRespVo = wxPayUtils.orderClose(orderCloseReqVo);
        OrderRefundReqVo orderRefundReqVo
                = new OrderRefundReqVo("","202010291606","1","1","1");
        OrderRefundRespVo orderRefundRespVo = wxPayUtils.refund(orderRefundReqVo);
    }
}
