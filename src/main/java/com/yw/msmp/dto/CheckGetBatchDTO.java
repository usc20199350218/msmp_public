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
public class CheckGetBatchDTO {

    /**
     * 药品详情id
     */
    @ApiModelProperty( name = "药品详情id", notes = "" )
    private Integer drugDetailId;
    /**
     * 店内批次Id
     */
    @ApiModelProperty( name = "店内批次Id", notes = "" )
    private Integer storeBatchId;
    /**
     * 店内批次Id
     */
    @ApiModelProperty( name = "数量", notes = "" )
    private Integer storeBatchPurchaseQuantity;

}
