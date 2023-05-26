package com.yw.msmp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayDTO {

    // 支付用户
    private Integer userId;
    // 支付产品id
    private String courseid;

    private Integer money;

}
