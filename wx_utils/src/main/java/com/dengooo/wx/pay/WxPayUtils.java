package com.dengooo.wx.pay;

import com.dengooo.wx.config.WxPayInitConfig;

import java.util.logging.Logger;

/*
    微信支付
 */
public class WxPayUtils {

    final Logger logger = Logger.getLogger("WxPayUtils");

    private WxPayInitConfig wxPayInitConfig;

    private WxPayUtils(WxPayInitConfig wxPayInitConfig) {
        this.wxPayInitConfig = wxPayInitConfig;
    }

    public static WxPayUtils build(WxPayInitConfig wxPayInitConfig) {
        return new WxPayUtils(wxPayInitConfig);
    }


}
