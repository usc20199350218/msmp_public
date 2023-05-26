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

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yanhaoyuwen
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnlineOrderDTO {

    private Integer userId;
    private Integer storeId;
    private BigDecimal totalPrice;
    private String remark;
    private String address;
    private Integer addressId;
    private List< Integer > drugDetailIdList;

}
