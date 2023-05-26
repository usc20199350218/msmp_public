package com.yw.msmp.vo;
/*
 * @PACKAGE_NAME com.yw.msmp.vo
 * @author  yuwenyanhao
 * @version  1.0
 */


import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yanhaoyuwen
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor( force = true )
@Builder
public class SelectVO {

    /**
     * 用户id
     */
    @ApiModelProperty( name = "用户id", notes = "" )
    private Integer userId;

    /**
     * 用户名
     */
    @TableField( updateStrategy = FieldStrategy.IGNORED )
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
     * 用户状态;1-正常/0-封禁
     */
    @ApiModelProperty( name = "用户状态", notes = "1-正常/0-封禁" )
    private Integer userStatus;

    /**
     * 昵称
     */
    @ApiModelProperty( name = "昵称", notes = "" )
    private String userNickName;

    /**
     * 真实名称
     */
    @ApiModelProperty( name = "真实名称", notes = "" )
    private String userRealName;

    /**
     * vip;0-普通/1-vip
     */
    @ApiModelProperty( name = "vip", notes = "0-普通/1-vip" )
    private Integer userVip;

    /**
     * 生日
     */
    @ApiModelProperty( name = "生日", notes = "" )
    @JsonFormat( pattern = "yyyy-MM-dd" )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date userBirthday;

}
