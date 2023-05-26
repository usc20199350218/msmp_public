package com.yw.msmp.service;

import com.alibaba.fastjson.JSONObject;

public interface PayCommonService {

    void payproductcourse( JSONObject bodyJson, String userId, String orderNumber, String tradeno, String s );

}
