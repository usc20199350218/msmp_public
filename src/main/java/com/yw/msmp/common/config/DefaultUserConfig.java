package com.yw.msmp.common.config;
/*
 * @PACKAGE_NAME com.yw.msmp.common.config
 * @author  yanhaoyuwen
 * @version  1.0
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author yanhaoyuwen
 **/
@Component
@Configuration
public class DefaultUserConfig {

    @Value( "${default.userAvatarUrl}" )
    public String userAvatarUrl;

    @Value( "${default.userPassWord}" )
    public String userPassWord;
    @Value( "${default.roleId}" )
    public Integer roleId;


}
