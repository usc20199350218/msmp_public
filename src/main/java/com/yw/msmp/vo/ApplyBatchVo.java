package com.yw.msmp.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 仓库查看店内请求总览
 * <p>
 * 请求数量
 * 仓库数量
 * 结余
 * 药品id
 * 药品名称
 * 药品详情id
 * 药品规格
 * 品牌id
 * 品牌
 * 品类id
 * 品类
 * <p>
 * 处方
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplyBatchVo {

    /**
     * 店内批次Id
     */
    @ApiModelProperty( name = "店内批次Id", notes = "" )
    private Integer storeBatchId;
    /**
     * 药品详情id
     */
    @ApiModelProperty( name = "药品详情id", notes = "" )
    private Integer drugDetailId;
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
     * 规格
     */
    @ApiModelProperty( name = "规格", notes = "" )
    private String drugSpecification;
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
     * 品类Id
     */
    @ApiModelProperty( name = "品类Id", notes = "" )
    private Integer typeId;
    /**
     * 品类名称
     */
    @ApiModelProperty( name = "品类名称", notes = "" )
    private String typeName;
    @ApiModelProperty( name = "申请数量", notes = "" )
    private Integer totalCreated;
    @ApiModelProperty( name = "可用数量", notes = "" )
    private Integer totalSold;
    @ApiModelProperty( name = "结余", notes = "" )
    private Integer totalBalance;
    @ApiModelProperty( name = "对应请求id", notes = "" )
    private List< Integer > applyIdList;

}
