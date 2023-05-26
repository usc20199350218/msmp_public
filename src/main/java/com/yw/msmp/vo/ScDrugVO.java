package com.yw.msmp.vo;
/*
 * @PACKAGE_NAME com.yw.msmp.vo
 * @author  yanhaoyuwen
 * @version  1.0
 */


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author yanhaoyuwen
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScDrugVO {

    /**
     * 购物车id
     */
    @ApiModelProperty( name = "购物车id", notes = "" )
    @TableId( value = "cart_id", type = IdType.AUTO )
    private Integer cartId;
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
     * 规格
     */
    @ApiModelProperty( name = "规格", notes = "" )
    private String drugSpecification;
    /**
     * 图片
     */
    @ApiModelProperty( name = "图片", notes = "" )
    private String drugDetailPath;
    /**
     * 价格
     */
    @ApiModelProperty( name = "价格", notes = "" )
    private BigDecimal drugRetailPrice;
    /**
     * 药品id
     */
    @ApiModelProperty( name = "药品id", notes = "" )
    private Integer drugId;
    /**
     * 品名
     */
    @ApiModelProperty( name = "品名", notes = "" )
    private String drugName;

}
