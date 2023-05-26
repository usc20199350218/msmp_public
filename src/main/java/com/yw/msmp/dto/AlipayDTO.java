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

/**
 * @author yanhaoyuwen
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlipayDTO {

    // 商户订单号
    private String outTradeNo;
    // 订单总金额
    private BigDecimal totalAmount;
    // 订单标题
    private String subject;
    // 产品码
    private String productCode;
    // 订单相对超时时间。从商户首次请求时间开始计算
    private String timeoutExpress;
    // 商户门店编号
    private String storeId;
    // 商户的原始订单号
    private String merchantOrderNo;
    // 关联人
    private Integer userId;

}
