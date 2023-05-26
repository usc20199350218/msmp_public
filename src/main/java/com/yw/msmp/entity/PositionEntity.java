package com.yw.msmp.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel( value = "职位表", description = "" )
@TableName( "tb_position" )
public class PositionEntity implements Serializable {

    /**
     * 职位id
     */
    @ApiModelProperty( name = "职位id", notes = "" )
    @TableId( value = "position_id", type = IdType.AUTO )
    private Integer positionId;
    /**
     * 职位名称
     */
    @ApiModelProperty( name = "职位名称", notes = "" )
    private String positionName;
    /**
     * 职位名称
     */
    @ApiModelProperty( name = "职位名称", notes = "" )
    private String responsibility;
    /**
     * 标准薪水
     */
    @ApiModelProperty( name = "标准薪水", notes = "" )
    private String salary;
    /**
     * 创建时间
     */
    @ApiModelProperty( name = "创建时间", notes = "" )
    @TableField( value = "create_time", fill = FieldFill.INSERT )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty( name = "更新时间", notes = "" )
    @TableField( value = "modified_time", fill = FieldFill.INSERT_UPDATE )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;

}
