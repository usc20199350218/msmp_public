package com.yw.msmp.entity;
/*
 * @PACKAGE_NAME com.yw.msmp.entity
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.baomidou.mybatisplus.annotation.IdType;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel( value = "购物车", description = "" )
@TableName( "tb_shopping_cart" )
public class ShoppingCartEntity implements Serializable {

    /**
     * 购物车id
     */
    @ApiModelProperty( name = "购物车id", notes = "" )
    @TableId( value = "cart_id", type = IdType.AUTO )
    private Integer cartId;
    /**
     * 用户id
     */
    @ApiModelProperty( name = "用户id", notes = "" )
    private Integer userId;
    /**
     * 详情id
     */
    @ApiModelProperty( name = "详情id", notes = "" )
    private Integer drugDetailId;
    /**
     * 数量
     */
    @ApiModelProperty( name = "数量", notes = "" )
    private Integer number;
    /**
     * 店铺id
     */
    @ApiModelProperty( name = "店铺id", notes = "" )
    private Integer storeId;
    /**
     * 最大数量
     */
    @ApiModelProperty( name = "最大数量", notes = "" )
    private Integer maxNum;

}
