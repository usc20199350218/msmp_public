package com.yw.msmp.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    @ApiModelProperty( value = "用户Id" )
    private Long userId;

    @ApiModelProperty( value = "用户手机号" )
    private Long userPhone;


    @ApiModelProperty( value = "初始用户角色Id——1—管理员=2-用户" )
    private Integer roleId;

    @ApiModelProperty( value = "用户角色" )
    private String roleName;

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

    @ApiModelProperty( value = "头像地址" )
    private String userAvatarUrl;


    @ApiModelProperty( value = "vip" )
    private Integer userVip;

    @ApiModelProperty( value = "用户名" )
    private String userName;


    @ApiModelProperty( value = "用户状态/1-正常/0-封禁" )
    private Integer userStatus;

    //    @ApiModelProperty( value = "version" )
    //    @Version
    //    private Long version;

    @ApiModelProperty( value = "逻辑删除" )
    @TableLogic
    private Integer deleted;

    @ApiModelProperty( value = "创建时间" )
    @TableField( value = "create_Time", fill = FieldFill.INSERT )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date
            createTime;

    @ApiModelProperty( value = "更新时间" )
    @TableField( value = "modified_Time", fill = FieldFill.INSERT_UPDATE )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date
            modifiedTime;
    /**
     * 生日
     */
    @ApiModelProperty( name = "生日", notes = "" )
    @JsonFormat( pattern = "yyyy-MM-dd" )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date userBirthday;

    /**
     * 性别;
     */
    @ApiModelProperty( name = "性别", notes = "男/女/未知" )
    private String userGender;

}
