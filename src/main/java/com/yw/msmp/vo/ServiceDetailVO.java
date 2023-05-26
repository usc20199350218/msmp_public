package com.yw.msmp.vo;
/*
 * @PACKAGE_NAME com.yw.msmp.vo
 * @author  yanhaoyuwen
 * @version  1.0
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yanhaoyuwen
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceDetailVO {

    @ApiModelProperty( name = "", notes = "" )
    private Integer serviceDetailId;

    @ApiModelProperty( name = "", notes = "" )
    private Integer serviceEntryId;

    @ApiModelProperty( name = "", notes = "" )
    private String serviceEntryName;

    @ApiModelProperty( name = "", notes = "" )
    private String content;

    @ApiModelProperty( name = "说明", notes = "" )
    private String remark;

}
