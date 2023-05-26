package com.yw.msmp.common.config;
/*
 * @PACKAGE_NAME com.yw.msmp.common.config
 * @author  yanhaoyuwen
 * @version  1.0
 */


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yanhaoyuwen
 **/
@Data
@Component
@ConfigurationProperties( prefix = "config.sms" )
public class SmsConfig {

    private String accessKeyId;
    private String accessSecret;
    private String signName;
    private String regionId;

}
