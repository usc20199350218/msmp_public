package com.yw.msmp.vo;
/*
 * @PACKAGE_NAME com.yw.msmp.vo
 * @author  yanhaoyuwen
 * @version  1.0
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author yanhaoyuwen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffVO {

    /**
     * 职员id
     */
    @ApiModelProperty( name = "职员id", notes = "" )
    private Integer staffId;
    /**
     * 店铺id
     */
    @ApiModelProperty( name = "店铺id", notes = "" )
    private Integer storeId;
    /**
     * 用户id
     */
    @ApiModelProperty( name = "用户id", notes = "" )
    private Integer userId;
    /**
     * 职位id
     */
    @ApiModelProperty( name = "职位id", notes = "" )
    private Integer positionId;
    /**
     * 状态
     */
    @ApiModelProperty( name = "状态", notes = "" )
    private Integer status;
    /**
     * 实际薪水
     */
    @ApiModelProperty( name = "实际薪水", notes = "" )
    private BigDecimal actualSalary;
    /**
     * 用户名
     */
    @ApiModelProperty( name = "用户名", notes = "" )
    private String userName;
    /**
     * 手机号
     */
    @ApiModelProperty( name = "手机号", notes = "" )
    private String userPhone;
    /**
     * 性别;1-男/0-女
     */
    @ApiModelProperty( name = "性别", notes = "男/女/未知" )
    private String userGender;
    /**
     * 头像地址
     */
    @ApiModelProperty( name = "头像地址", notes = "" )
    private String userAvatarUrl;
    /**
     * 真实名称
     */
    @ApiModelProperty( name = "真实名称", notes = "" )
    private String userRealName;
    /**
     * 昵称
     */
    @ApiModelProperty( name = "昵称", notes = "" )
    private String userNickName;

}
