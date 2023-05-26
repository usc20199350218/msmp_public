package com.yw.msmp.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfflineSaleVO {

    /**
     * 店内批次Id
     */
    @ApiModelProperty( name = "店内批次Id", notes = "" )
    private Integer storeBatchId;

    /**
     * 现存数量;出售从这里计算
     */
    @ApiModelProperty( name = "现存数量", notes = "" )
    private Integer storeBatchExistingQuantity;
    /**
     * 批次Id
     */
    @ApiModelProperty( name = "批次Id", notes = "" )
    private Integer batchId;

    /**
     * 批号编号;由供应商提供
     */
    @ApiModelProperty( name = "批号编号", notes = "由供应商提供" )
    private String batchNumber;
    /**
     * 生产日期
     */
    @ApiModelProperty( name = "生产日期", notes = "" )
    @JsonFormat( pattern = "yyyy-MM-dd" )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date batchProductionDate;
    /**
     * 药品详情Id
     */
    @ApiModelProperty( name = "药品详情Id", notes = "" )
    private Integer drugDetailId;
    /**
     * 规格
     */
    @ApiModelProperty( name = "规格", notes = "" )
    private String drugSpecification;
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
     * 订单数据
     */
    @ApiModelProperty( name = "订单数据", notes = "" )
    private Integer purchaseQuantity;

}
