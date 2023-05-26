package com.yw.msmp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FastStoreBatchDTO {

    private Integer drugDetailId;
    private String status;
    private String remarkText;
    private List< Integer > applyIdList;

}
