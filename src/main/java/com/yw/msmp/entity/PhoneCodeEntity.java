package com.yw.msmp.entity;
/*
 * @PACKAGE_NAME com.yw.msmp.entity
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel( value = "登录验证码", description = "" )
@TableName( "tb_phone_code" )
public class PhoneCodeEntity {

    /**
     * 登录验证码id
     */
    @ApiModelProperty( name = "登录验证码id", notes = "" )
    @TableId( value = "phone_code_id", type = IdType.AUTO )
    private Integer phoneCodeId;
    /**
     * 手机号
     */
    @ApiModelProperty( name = "手机号", notes = "" )
    private String phoneNum;
    /**
     * 验证码
     */
    @ApiModelProperty( name = "验证码", notes = "" )
    private String code;
    /**
     * 创建时间
     */
    @ApiModelProperty( name = "创建时间", notes = "" )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;


}
