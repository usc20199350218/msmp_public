package com.yw.msmp.service.impl;
/*
 * @PACKAGE_NAME com.yw.msmp.service.impl
 * @author  yanhaoyuwen
 * @version  1.0
 */


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.yw.msmp.common.config.SmsConfig;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yanhaoyuwen
 **/
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    private SmsConfig smsConfig;

    @Override
    public void sendCheckCode( String phone, String checkCode ) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile( smsConfig.getRegionId( ),
                                                            smsConfig.getAccessKeyId( ), smsConfig.getAccessSecret( ) );
        // 创建阿里云的客户端
        IAcsClient client = new DefaultAcsClient( profile );

        // 请求对象
        CommonRequest request = new CommonRequest( );
        request.setSysMethod( MethodType.POST );
        request.setSysDomain( "dysmsapi.aliyuncs.com" );
        request.setSysVersion( "2017-05-25" );
        request.setSysAction( "SendSms" );
        request.putQueryParameter( "RegionId", smsConfig.getRegionId( ) );
        request.putQueryParameter( "PhoneNumbers", phone );
        request.putQueryParameter( "SignName", smsConfig.getSignName( ) );
        request.putQueryParameter( "TemplateCode", "SMS_150181589" );

        Map< String, String > map = new HashMap<>( );
        map.put( "code", checkCode );
        // jackson
        Gson gson = new Gson( );
        request.putQueryParameter( "TemplateParam", gson.toJson( map ) );

        // 用阿里云的客户端  给阿里云的sms服务 发送请求
        // 返回是阿里云给的响应对象
        CommonResponse response = client.getCommonResponse( request );

        // 要来解析 阿里云返回的response 发送还是失败 response 都会告诉咱们
        // 如果没有抛出异常  那么是否代表短信一定发出来了？？？？？
        //得到json字符串格式的响应结果
        String data = response.getData( );
        System.out.println( data );
        //解析json字符串格式的响应结果
        // 吧阿里云响应给我们的json 字符串 转成map
        HashMap< String, String > map1 = gson.fromJson( data, HashMap.class );
        String code = map1.get( "Code" );
        String message = map1.get( "Message" );

        //配置参考：短信服务->系统设置->国内消息设置
        //错误码参考：
        //https://help.aliyun.com/document_detail/101346.html?spm=a2c4g.11186623.6.613.3f6e2246sDg6Ry
        //控制所有短信流向限制（同一手机号：一分钟一条、一个小时五条、一天十条）
        if ( "isv.BUSINESS_LIMIT_CONTROL".equals( code ) ) {
            log.error( "短信发送过于频繁 " + "【code】" + code + ", 【message】" + message );
            throw new AppException( ResponseEnum.SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL );
        }

        if ( !"OK".equals( code ) ) {
            log.error( "短信发送失败 " + " - code: " + code + ", message: " + message );
            throw new AppException( ResponseEnum.SMS_SEND_ERROR );
        }


    }

}


