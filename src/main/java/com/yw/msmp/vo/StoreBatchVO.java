package com.yw.msmp.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreBatchVO {

    /**
     * 数量
     */
    @ApiModelProperty( name = "未审批", notes = "" )
    private Integer totalCreated;
    /**
     * 数量
     */
    @ApiModelProperty( name = "调货", notes = "" )
    private Integer totalNormalPurchase;
    /**
     * 数量
     */
    @ApiModelProperty( name = "正常", notes = "" )
    private Integer totalSold;
    /**
     * 数量
     */
    @ApiModelProperty( name = "过期未处理", notes = "" )
    private Integer totalExpired;
    /**
     * 数量
     */
    @ApiModelProperty( name = "禁止销售", notes = "" )
    private Integer totalNormalForbidden;
    /**
     * 数量
     */
    @ApiModelProperty( name = "总数量", notes = "" )
    private Integer totalAll;

    /**
     * 药品详情id
     */
    @ApiModelProperty( name = "药品详情id", notes = "" )
    private Integer drugDetailId;
    /**
     * 规格
     */
    @ApiModelProperty( name = "规格", notes = "" )
    private String drugSpecification;
    /**
     * 单价
     */
    @ApiModelProperty( name = "单价", notes = "" )
    private Double drugUnitPrice;
    /**
     * 零售价
     */
    @ApiModelProperty( name = "零售价", notes = "" )
    private Double drugRetailPrice;

    /**
     * 药品ID
     */
    @ApiModelProperty( name = "药品ID", notes = "" )
    private Integer drugId;
    /**
     * 药品名称
     */
    @ApiModelProperty( name = "药品名称", notes = "" )
    private String drugName;
    /**
     * 是否非处方;0-非处方药/1-处方药
     */
    @ApiModelProperty( name = "是否非处方", notes = "0-非处方药/1-处方药" )
    private Integer isRx;

    /**
     * 品牌id
     */
    @ApiModelProperty( name = "品牌id", notes = "" )
    private Integer brandId;
    /**
     * 品牌名称
     */
    @ApiModelProperty( name = "品牌名称", notes = "" )
    private String brandName;
    /**
     * 联系人Id
     */
    @ApiModelProperty( name = "联系人Id", notes = "" )
    private Integer userId;


}
