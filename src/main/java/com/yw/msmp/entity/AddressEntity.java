package com.yw.msmp.entity;
/*
 * @PACKAGE_NAME com.yw.msmp.entity
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yanhaoyuwen
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel( value = "地址", description = "" )
@TableName( "tb_address" )
public class AddressEntity implements Serializable {

    /**
     * 地址id
     */
    @ApiModelProperty( name = "地址id", notes = "" )
    @TableId( value = "address_id", type = IdType.AUTO )
    private Integer addressId;
    /**
     * 地址内容
     */
    @ApiModelProperty( name = "地址内容", notes = "" )
    private String addressContent;
    /**
     * 姓名
     */
    @ApiModelProperty( name = "姓名", notes = "" )
    private String userName;
    /**
     * 用户id
     */
    @ApiModelProperty( name = "用户id", notes = "" )
    private Integer userId;
    /**
     * 默认
     */
    @ApiModelProperty( name = "默认", notes = "" )
    private Integer isDefault;
    /**
     * 手机号
     */
    @ApiModelProperty( name = "手机号", notes = "" )
    private String userPhone;
    /**
     * 创建时间
     */
    @ApiModelProperty( name = "创建时间", notes = "" )
    @TableField( value = "create_time", fill = FieldFill.INSERT )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty( name = "更新时间", notes = "" )
    @TableField( value = "modified_time", fill = FieldFill.INSERT_UPDATE )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;

}
