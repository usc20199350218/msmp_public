package com.yw.msmp.vo;
/*
 * @PACKAGE_NAME com.yw.msmp.vo
 * @author  yanhaoyuwen
 * @version  1.0
 */

import io.swagger.annotations.ApiModelProperty;
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
public class RightVO {


    @ApiModelProperty( value = "菜单权限Id" )
    private Integer rightId;

    @ApiModelProperty( value = "菜单权限标题" )
    private String rightText;

    @ApiModelProperty( value = "0: 父节点 1:子节点" )
    private Integer rightType;

    @ApiModelProperty( value = "菜单权限路径" )
    private String rightUrl;

    @ApiModelProperty( value = "父节点时为空，子节点时 父节点Id" )
    private Integer rightParentId;

    @ApiModelProperty( value = "1:菜单权限 0:button权限" )
    private Boolean rightMenu;

    @ApiModelProperty( value = "逻辑删除" )
    private Integer deleted;

    //    roleId
    private List< Integer > roleId;

}
