package com.yw.msmp.entity;
/*
 * @PACKAGE_NAME com.yw.msmp.entity
 * @author  yanhaoyuwen
 * @version  1.0
 */

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
public class SearchDrugDTO {

    private Integer brandId;
    private Integer isRx;
    private Integer typeId;
    private String searchMethod;
    private String searchText;
    private Integer current;
    private Integer size;

}
