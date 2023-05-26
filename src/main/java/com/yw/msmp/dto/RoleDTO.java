package com.yw.msmp.dto;

import com.yw.msmp.entity.RightEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yanhaoyuwen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {

    List< RightEntity > rightList;
    private Integer roleId;
    private String roleName;
    List< Integer > rightIds;

}
