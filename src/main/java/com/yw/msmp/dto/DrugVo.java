package com.yw.msmp.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrugVo {

    /**
     * 药品详情id
     */
    @ApiModelProperty( name = "药品详情id", notes = "" )
    private Integer drugDetailId;
    /**
     * 药品id
     */
    @ApiModelProperty( name = "药品id", notes = "" )
    private Integer drugId;
    /**
     * 药品名称
     */
    @ApiModelProperty( name = "药品名称", notes = "" )
    private String drugName;
    /**
     * 规格
     */
    @ApiModelProperty( name = "规格", notes = "" )
    private String drugSpecification;
    /**
     * 品牌名称
     */
    @ApiModelProperty( name = "品牌名称", notes = "" )
    private String brandName;

}
