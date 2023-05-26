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
@ApiModel( value = "RoleRight对象", description = "角色菜单权限表" )
@TableName( "sys_role_right" )
public class RoleRightEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty( value = "角色菜单权限Id" )
    @TableId( value = "role_right_id", type = IdType.AUTO )
    private Integer roleRightId;

    @ApiModelProperty( value = "角色Id" )
    private Integer roleId;

    @ApiModelProperty( value = "菜单权限Id" )
    private Integer rightId;

    @ApiModelProperty( value = "创建时间" )
    @TableField( value = "create_time", fill = FieldFill.INSERT )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date
            createTime;

    @ApiModelProperty( value = "更新时间" )
    @TableField( value = "modified_time", fill = FieldFill.INSERT_UPDATE )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date
            modifiedTime;

}
