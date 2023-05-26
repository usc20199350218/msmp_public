package com.yw.msmp.entity;
/*
 * @PACKAGE_NAME com.yw.msmp.entity
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yanhaoyuwen
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName( "tb_pay_match" )
@ApiModel( value = "支付订单对应表", description = "" )
public class PayMatchEntity implements Serializable {

    /**
     * 商户订单号
     */
    @ApiModelProperty( name = "商家订单号", notes = "" )
    @TableId
    private String outTradeNo;
    /**
     * order订单号
     */
    @ApiModelProperty( name = "order订单号", notes = "" )
    private String orderNum;

    /**
     * 支付宝交易号
     */
    @ApiModelProperty( name = "支付宝订单号", notes = "" )
    private String tradeNo;

}
