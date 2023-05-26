package com.yw.msmp.service;

import com.aliyuncs.exceptions.ClientException;

/*
 * @PACKAGE_NAME com.yw.msmp.service
 * @author  yanhaoyuwen
 * @version  1.0
 */
public interface SmsService {

    void sendCheckCode( String phone, String checkCode ) throws ClientException, ClientException;

}
