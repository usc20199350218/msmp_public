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
public class RoleConfig {

    /**
     * 管理员角色ID
     */
    @Value( "${role.roleId.administrator}" )
    public Integer administrator;

    /**
     * 经理角色ID
     */
    @Value( "${role.roleId.manager}" )
    public Integer manager;

    /**
     * 职员角色ID
     */
    @Value( "${role.roleId.clerk}" )
    public Integer clerk;

    /**
     * 供应商角色ID
     */
    @Value( "${role.roleId.supplier}" )
    public Integer supplier;

    /**
     * 用户角色ID
     */
    @Value( "${role.roleId.user}" )
    public Integer user;

    /**
     * 配送员角色ID
     */
    @Value( "${role.roleId.delivery}" )
    public Integer delivery;

}
