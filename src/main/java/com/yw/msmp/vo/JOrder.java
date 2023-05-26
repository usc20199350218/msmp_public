package com.yw.msmp.vo;
/*
 * @PACKAGE_NAME com.yw.msmp.vo
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
public class JOrder {

    private String outTradeNo;
    private String subject;
    private String totalAmount;
    private String description;

}
