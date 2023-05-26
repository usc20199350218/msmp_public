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
@ApiModel( value = "记录条目", description = "" )
@TableName( "tb_service_detail" )
public class ServiceDetailEntity implements Serializable {

    @ApiModelProperty( name = "", notes = "" )
    @TableId( value = "service_detail_id", type = IdType.AUTO )
    private Integer serviceDetailId;

    @ApiModelProperty( name = "", notes = "" )
    private Integer serviceEntryId;

    @ApiModelProperty( name = "", notes = "" )
    private Integer userId;

    @ApiModelProperty( name = "", notes = "" )
    private String content;

    @ApiModelProperty( name = "", notes = "" )
    private Integer serviceId;

    @ApiModelProperty( name = "", notes = "" )
    @TableField( value = "create_time", fill = FieldFill.INSERT )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;

    @ApiModelProperty( value = "更新时间" )
    @TableField( value = "modified_time", fill = FieldFill.INSERT_UPDATE )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;

}
