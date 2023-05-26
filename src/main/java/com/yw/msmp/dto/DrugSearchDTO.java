package com.yw.msmp.dto;
/*
 * @PACKAGE_NAME com.yw.msmp.dto
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrugSearchDTO {

    private Integer typeId;
    private String searchMethod;
    private String searchText;
    private Integer current;
    private Integer size;

}
