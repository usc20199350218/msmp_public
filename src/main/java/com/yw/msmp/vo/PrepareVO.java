package com.yw.msmp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrepareVO {

    private Integer orderId;
    private BigDecimal amount;
    private String orderNum;

}
