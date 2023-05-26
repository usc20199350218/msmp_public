package com.yw.msmp.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreVo {

    /**
     * 店铺ID
     */
    @ApiModelProperty( name = "店铺ID", notes = "" )
    private Integer storeId;
    /**
     * 店铺名称
     */
    @ApiModelProperty( name = "店铺名称", notes = "" )
    private String storeName;

}
