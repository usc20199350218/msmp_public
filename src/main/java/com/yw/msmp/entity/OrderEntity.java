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
@ApiModel( value = "订单表", description = "" )
@TableName( "tb_order" )
public class OrderEntity implements Serializable {

    /**
     * 订单编号，主键
     */
    @ApiModelProperty( name = "订单Id，主键", notes = "" )
    @TableId( value = "order_id", type = IdType.AUTO )
    private Integer orderId;
    @ApiModelProperty( name = "订单编码", notes = "" )
    private String orderNum;
    /**
     * 用户Id
     */
    @ApiModelProperty( name = "用户Id", notes = "" )
    private Integer userId;
    /**
     * 操作人Id
     */
    @ApiModelProperty( name = "操作人Id", notes = "" )
    private Integer operateUserId;
    /**
     * 订单总金额
     */
    @ApiModelProperty( name = "订单总金额", notes = "" )
    private BigDecimal amount;
    /**
     * 订单状态
     */
    @ApiModelProperty( name = "订单状态", notes = "" )
    private String status;
    /**
     * 支付方式
     */
    @ApiModelProperty( name = "支付方式", notes = "" )
    private String paymentMethod;
    /**
     * 支付时间
     */
    @ApiModelProperty( name = "支付时间", notes = "" )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date paymentTime;
    /**
     * 送货地址
     */
    @ApiModelProperty( name = "送货地址", notes = "" )
    private String deliveryAddress;
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
     * 备注
     */
    @ApiModelProperty( name = "备注", notes = "" )
    private String remark;
    /**
     * 订单类型
     */
    @ApiModelProperty( name = "订单类型", notes = "" )
    private String orderType;
    /**
     * 购买店铺
     */
    @ApiModelProperty( name = "购买店铺", notes = "" )
    private Integer storeId;
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

}
