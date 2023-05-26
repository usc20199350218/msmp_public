package com.yw.msmp.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yanhaoyuwen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode( callSuper = false )
@Accessors( chain = true )
@ApiModel( value = "Right对象", description = "菜单权限表" )
@TableName( "sys_right" )
public class RightEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty( value = "创建时间" )
    @TableField( value = "create_time", fill = FieldFill.INSERT )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;

    @ApiModelProperty( value = "更新时间" )
    @TableField( value = "modified_time", fill = FieldFill.INSERT_UPDATE )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;

}
