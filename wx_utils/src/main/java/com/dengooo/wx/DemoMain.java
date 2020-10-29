package com.dengooo.wx;

import com.dengooo.wx.config.WxPayInitConfig;
import com.dengooo.wx.pay.WxPayUtils;
import com.dengooo.wx.req.UnifiedOrderReqVo;

public class DemoMain {
    public static void main(String[] args) {
        WxPayInitConfig wxPayInitConfig = new WxPayInitConfig();
        WxPayUtils wxPayUtils = WxPayUtils.build(wxPayInitConfig);
        UnifiedOrderReqVo unifiedOrderReqVo = new UnifiedOrderReqVo("第一个商品", "202010291606", "1", "NATIVE", "1");
        wxPayUtils.unifiedorder(unifiedOrderReqVo);
    }
}
