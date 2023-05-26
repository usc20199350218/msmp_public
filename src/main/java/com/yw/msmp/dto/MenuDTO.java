package com.yw.msmp.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDTO {

    @ApiModelProperty( value = "菜单权限Id" )
    @TableId( value = "right_id", type = IdType.AUTO )
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
    @TableLogic
    private Integer deleted;

    @ApiModelProperty( value = "创建时间" )
    @TableField( value = "create_time", fill = FieldFill.INSERT )
    private Date
            createTime;

    @ApiModelProperty( value = "更新时间" )
    @TableField( value = "modified_time", fill = FieldFill.INSERT_UPDATE )
    private Date modifiedTime;

    private List< MenuDTO > menuDTO;

}
