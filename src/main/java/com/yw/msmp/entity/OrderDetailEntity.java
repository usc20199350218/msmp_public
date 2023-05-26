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
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yanhaoyuwen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel( value = "订单详情表", description = "" )
@TableName( "tb_order_detail" )
public class OrderDetailEntity implements Serializable {

    /**
     * 订单详情编号
     */
    @ApiModelProperty( name = "订单详情编号", notes = "" )
    @TableId( value = "order_detail_id", type = IdType.AUTO )
    private Integer orderDetailId;
    /**
     * 订单编号
     */
    @ApiModelProperty( name = "订单编号", notes = "" )
    private Integer orderId;
    /**
     * 药品详情id
     */
    @ApiModelProperty( name = "药品详情id", notes = "" )
    private Integer drugDetailId;
    /**
     * 药品名称
     */
    @ApiModelProperty( name = "药品名称", notes = "" )
    private String drugName;
    /**
     * 零售价
     */
    @ApiModelProperty( name = "零售价", notes = "" )
    private BigDecimal drugPrice;
    /**
     * 商品总价
     */
    @ApiModelProperty( name = "商品总价", notes = "" )
    private BigDecimal totalPrice;

    /**
     * 商品数量
     */
    @ApiModelProperty( name = "商品数量", notes = "" )
    private Integer quantity;
    /**
     * 创建日期
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
    /**
     * 店铺批次id
     */
    @ApiModelProperty( name = "店铺批次id", notes = "" )
    private Integer storeBatchId;

}
