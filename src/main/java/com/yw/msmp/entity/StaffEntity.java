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
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel( value = "工作人员表", description = "" )
@TableName( "tb_staff" )
public class StaffEntity implements Serializable {

    /**
     * 职员id
     */
    @ApiModelProperty( name = "职员id", notes = "" )
    @TableId( value = "staff_id", type = IdType.AUTO )
    private Integer staffId;
    /**
     * 店铺id
     */
    @ApiModelProperty( name = "店铺id", notes = "" )
    private Integer storeId;
    /**
     * 用户id
     */
    @ApiModelProperty( name = "用户id", notes = "" )
    private Integer userId;
    /**
     * 职位id
     */
    @ApiModelProperty( name = "职位id", notes = "" )
    private Integer positionId;
    /**
     * 状态
     */
    @ApiModelProperty( name = "状态", notes = "" )
    private Integer status;
    /**
     * 实际薪水
     */
    @ApiModelProperty( name = "实际薪水", notes = "" )
    private BigDecimal actualSalary;
    /**
     * 创建时间
     */
    @ApiModelProperty( name = "创建时间", notes = "" )
    @TableField( value = "create_time", fill = FieldFill.INSERT )
    @JsonFormat( pattern = "yyyy-MM-dd" )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
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
