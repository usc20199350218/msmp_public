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

import java.util.List;

/**
 * @author yanhaoyuwen
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaEntity {

    private String code;
    private String value;
    private List< AreaEntity > children;

}
