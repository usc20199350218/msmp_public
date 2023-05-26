package com.yw.msmp.dto;

import com.yw.msmp.entity.RightEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RightDTO {

    private Integer rightId;

    private String rightText;

    private Integer rightType;

    private String rightUrl;

    private Integer rightParentId;

    private Boolean rightMenu;

    private List< RightEntity > rightList;

}
