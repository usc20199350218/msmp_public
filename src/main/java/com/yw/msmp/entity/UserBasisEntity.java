package com.yw.msmp.entity;

import com.baomidou.mybatisplus.annotation.*;
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
 */
@Data
@AllArgsConstructor
@NoArgsConstructor( force = true )
@Builder
@TableName( "sys_user_basis" )
public class UserBasisEntity {

    /**
     * 用户id
     */
    @ApiModelProperty( name = "用户id", notes = "" )
    @TableId( value = "user_id", type = IdType.AUTO )
    private Integer userId;

    /**
     * 用户名
     */
    @ApiModelProperty( name = "用户名", notes = "" )
    private String userName;

    /**
     * 用户密码
     */
    @ApiModelProperty( name = "用户密码", notes = "" )
    private String userPassword;

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
     * 角色id;1-管理员/2-普通用户
     */
    @ApiModelProperty( name = "角色id", notes = "1-管理员/2-普通用户" )
    private Integer roleId;

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
     * 更新时间
     */
    @ApiModelProperty( name = "更新时间", notes = "" )
    @TableField( value = "modified_time", fill = FieldFill.INSERT_UPDATE )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;

    /**
     * 创建时间
     */
    @ApiModelProperty( name = "创建时间", notes = "" )
    @TableField( value = "create_time", fill = FieldFill.INSERT )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;

    /**
     * 逻辑删除;1-删除/0-正常
     */
    @ApiModelProperty( name = "逻辑删除", notes = "1-删除/0-正常" )
    @TableLogic
    private Integer deleted;

    /**
     * 生日
     */
    @ApiModelProperty( name = "生日", notes = "" )
    @JsonFormat( pattern = "yyyy-MM-dd" )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date userBirthday;


}
