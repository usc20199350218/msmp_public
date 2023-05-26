package com.yw.msmp.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffDTO {

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
     * 店铺名称
     */
    @ApiModelProperty( name = "店铺名称", notes = "" )
    private String storeName;
    /**
     * 用户id
     */
    @ApiModelProperty( name = "用户id", notes = "" )
    private Integer userId;
    /**
     * 用户名
     */
    @TableField( updateStrategy = FieldStrategy.IGNORED )
    @ApiModelProperty( name = "用户名", notes = "" )
    private String userName;
    /**
     * 手机号
     */
    @ApiModelProperty( name = "手机号", notes = "" )
    private String userPhone;
    /**
     * 性别;1-男/0-女
     */
    @ApiModelProperty( name = "性别", notes = "男/女/未知" )
    private String userGender;
    /**
     * 角色id;1-管理员/2-普通用户
     */
    @ApiModelProperty( name = "角色id", notes = "1-管理员" )
    private Integer roleId;
    /**
     * 真实名称
     */
    @ApiModelProperty( name = "真实名称", notes = "" )
    private String userRealName;
    /**
     * 职位id
     */
    @ApiModelProperty( name = "职位id", notes = "" )
    private Integer positionId;
    /**
     * 职位名称
     */
    @ApiModelProperty( name = "职位名称", notes = "" )
    private String positionName;
    /**
     * 状态
     */
    @ApiModelProperty( name = "状态", notes = "" )
    private Integer status;
    /**
     * 实际薪水
     */
    @ApiModelProperty( name = "实际薪水", notes = "" )
    private Double actualSalary;
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
