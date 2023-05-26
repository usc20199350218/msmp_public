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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode( callSuper = false )
@Accessors( chain = true )
@ApiModel( value = "Role对象", description = "角色表" )
@TableName( "sys_role" )
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty( value = "角色Id" )
    @TableId( value = "role_id", type = IdType.AUTO )
    private Integer roleId;

    @ApiModelProperty( value = "角色名" )
    private String roleName;

    @ApiModelProperty( value = "逻辑删除" )
    @TableLogic
    private Integer deleted;

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
