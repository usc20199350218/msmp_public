package com.yw.msmp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yanhaoyuwen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderVo {

    private Integer userId;
    private Integer storeId;
    private List< Integer > storeBatchId;

}
