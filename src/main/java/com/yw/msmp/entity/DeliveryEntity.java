package com.yw.msmp.entity;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel( value = "配送表", description = "" )
@TableName( "tb_delivery" )
public class DeliveryEntity implements Serializable {

    /**
     * 配送id
     */
    @ApiModelProperty( name = "配送id", notes = "" )
    @TableId( value = "delivery_id", type = IdType.AUTO )
    private Integer deliveryId;
    /**
     * 店铺id
     */
    @ApiModelProperty( name = "店铺id", notes = "" )
    private String storeId;
    /**
     * 配送状态;创建、分配、取货、配送、到达
     */
    @ApiModelProperty( name = "配送状态", notes = "创建、分配、取货、配送、到达" )
    private String deliveryStatus;
    /**
     * 仓库批次id
     */
    @ApiModelProperty( name = "仓库批次id", notes = "" )
    private Integer batchId;
    /**
     * 店铺批次id
     */
    @ApiModelProperty( name = "店铺批次id", notes = "" )
    private Integer storeBatchId;
    /**
     * 药品详情id
     */
    @ApiModelProperty( name = "药品详情id", notes = "" )
    private Integer drugDetailId;
    /**
     * 数量
     */
    @ApiModelProperty( name = "数量", notes = "" )
    private Integer deliveryNum;
    /**
     * 配送人员id
     */
    @ApiModelProperty( name = "配送人员id", notes = "" )
    private Integer userId;
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

}
