package com.yw.msmp.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author yanhaoyuwen
 */
@Data
@Component
@Configuration
public class OssConfig {

    @Value( "${config.oss.endpoint}" )
    private String endpoint;
    @Value( "${config.oss.keyid}" )
    private String keyid;
    @Value( "${config.oss.keysecret}" )
    private String keysecret;
    @Value( "${config.oss.bucketname}" )
    private String bucketname;

}
