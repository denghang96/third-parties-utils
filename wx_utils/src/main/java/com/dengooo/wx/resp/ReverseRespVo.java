package com.dengooo.wx.resp;

import com.alibaba.fastjson.JSON;

public class ReverseRespVo extends PayRespBaseVo{
    /**
     * 是否需要继续调用撤销，Y-需要，N-不需要
     */
    private String recall;

    public String getRecall() {
        return recall;
    }

    public void setRecall(String recall) {
        this.recall = recall;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
